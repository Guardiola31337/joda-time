/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTimeZone;

/**
 * ZoneInfoProvider loads compiled data files as generated by
 * {@link ZoneInfoCompiler}.
 * <p>
 * ZoneInfoProvider is thread-safe and publicly immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class ZoneInfoProvider implements Provider {
    private static Map loadZoneInfoMap(InputStream in) throws IOException {
        Map map = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        DataInputStream din = new DataInputStream(in);
        try {
            ZoneInfoCompiler.readZoneInfoMap(din, map);
        } finally {
            try {
                din.close();
            } catch (IOException e) {
            }
        }
        map.put("UTC", new SoftReference(DateTimeZone.UTC));
        return map;
    }

    private final File iFileDir;
    private final String iResourcePath;
    private final ClassLoader iLoader;

    // Maps ids to strings or SoftReferences to DateTimeZones.
    private final Map iZoneInfoMap;

    /**
     * ZoneInfoProvider searches the given directory for compiled data files.
     *
     * @throws IOException if directory or map file cannot be read
     */
    public ZoneInfoProvider(File fileDir) throws IOException {
        if (fileDir == null) {
            throw new IllegalArgumentException("No file directory provided");
        }
        if (!fileDir.exists()) {
            throw new IOException("File directory doesn't exist: " + fileDir);
        }
        if (!fileDir.isDirectory()) {
            throw new IOException("File doesn't refer to a directory: " + fileDir);
        }

        iFileDir = fileDir;
        iResourcePath = null;
        iLoader = null;

        iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
    }

    /**
     * ZoneInfoProvider searches the given ClassLoader resource path for
     * compiled data files. Resources are loaded from the ClassLoader that
     * loaded this class.
     *
     * @throws IOException if directory or map file cannot be read
     */
    public ZoneInfoProvider(String resourcePath) throws IOException {
        this(resourcePath, null, false);
    }

    /**
     * ZoneInfoProvider searches the given ClassLoader resource path for
     * compiled data files.
     *
     * @param loader ClassLoader to load compiled data files from. If null,
     * use system ClassLoader.
     * @throws IOException if directory or map file cannot be read
     */
    public ZoneInfoProvider(String resourcePath, ClassLoader loader)
        throws IOException
    {
        this(resourcePath, loader, true);
    }

    /**
     * @param favorSystemLoader when true, use the system class loader if
     * loader null. When false, use the current class loader if loader is null.
     */
    private ZoneInfoProvider(String resourcePath,
                             ClassLoader loader, boolean favorSystemLoader) 
        throws IOException
    {
        if (resourcePath == null) {
            throw new IllegalArgumentException("No resource path provided");
        }
        if (!resourcePath.endsWith("/")) {
            resourcePath += '/';
        }

        iFileDir = null;
        iResourcePath = resourcePath;

        if (loader == null && !favorSystemLoader) {
            loader = getClass().getClassLoader();
        }

        iLoader = loader;

        iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
    }

    /**
     * If an error is thrown while loading zone data, uncaughtException is
     * called to log the error and null is returned for this and all future
     * requests.
     */
    public synchronized DateTimeZone getZone(String id) {
        if (id == null) {
            return null;
        }

        Object obj = iZoneInfoMap.get(id);
        if (obj == null) {
            return null;
        }

        if (id.equals(obj)) {
            // Load zone data for the first time.
            return loadZoneData(id);
        }

        if (obj instanceof SoftReference) {
            DateTimeZone tz = (DateTimeZone)((SoftReference)obj).get();
            if (tz != null) {
                return tz;
            }
            // Reference cleared; load data again.
            return loadZoneData(id);
        }

        // If this point is reached, mapping must link to another.
        return getZone((String)obj);
    }

    public synchronized Set getAvailableIDs() {
        return Collections.unmodifiableSet(iZoneInfoMap.keySet());
    }

    /**
     * Called if an exception is thrown from getZone while loading zone
     * data.
     */
    protected void uncaughtException(Exception e) {
        Thread t = Thread.currentThread();
        t.getThreadGroup().uncaughtException(t, e);
    }

    private InputStream openResource(String name) throws IOException {
        InputStream in;
        if (iFileDir != null) {
            in = new FileInputStream(new File(iFileDir, name));
        } else {
            String path = iResourcePath.concat(name);
            if (iLoader != null) {
                in = iLoader.getResourceAsStream(path);
            } else {
                in = ClassLoader.getSystemResourceAsStream(path);
            }
            if (in == null) {
                StringBuffer buf = new StringBuffer(40);
                buf.append("Resource not found: \"");
                buf.append(path);
                buf.append("\" ClassLoader: ");
                buf.append(iLoader != null ? iLoader.toString() : "system");
                throw new IOException(buf.toString());
            }
        }
        return in;
    }

    private DateTimeZone loadZoneData(String id) {
        InputStream in = null;
        try {
            in = openResource(id);
            DateTimeZone tz = DateTimeZoneBuilder.readFrom(in, id);
            iZoneInfoMap.put(id, new SoftReference(tz));
            return tz;
        } catch (IOException e) {
            uncaughtException(e);
            iZoneInfoMap.remove(id);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
