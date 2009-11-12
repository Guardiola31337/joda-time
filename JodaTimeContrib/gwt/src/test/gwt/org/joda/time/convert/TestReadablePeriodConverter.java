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
package org.joda.time.convert;

// Removed for GWT import java.lang.reflect.Constructor;
// Removed for GWT import java.lang.reflect.Field;
// Removed for GWT import java.lang.reflect.Modifier;


import static org.joda.time.gwt.TestConstants.*;
//import junit.framework.TestSuite;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.PeriodType;
import org.joda.time.MutablePeriod;
import org.joda.time.ReadablePeriod;
import org.joda.time.Period;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.JulianChronology;
import org.joda.time.gwt.JodaGwtTestCase;

/**
 * This class is a Junit unit test for ReadablePeriodConverter.
 *
 * @author Stephen Colebourne
 */
public class TestReadablePeriodConverter extends JodaGwtTestCase {

    // Removed for GWT private static final DateTimeZone UTC = DateTimeZone.UTC;
    // Removed for GWT private static final DateTimeZone PARIS = DateTimeZone.forID("Europe/Paris");
    // Removed for GWT private static final Chronology ISO_PARIS = ISOChronology.getInstance(PARIS);
    private static Chronology JULIAN;
    private static Chronology ISO;
    
    private DateTimeZone zone = null;

    /* Removed for GWT public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    } */

    /* Removed for GWT public static TestSuite suite() {
        return new TestSuite(TestReadablePeriodConverter.class);
    } */

    /* Removed for GWT public TestReadablePeriodConverter(String name) {
        super(name);
    } */

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();
        JULIAN = JulianChronology.getInstance();
        ISO = ISOChronology.getInstance();
    }

    //-----------------------------------------------------------------------
    /* Removed for GWT public void testSingleton() throws Exception {
        Class cls = ReadablePeriodConverter.class;
        assertEquals(false, Modifier.isPublic(cls.getModifiers()));
        assertEquals(false, Modifier.isProtected(cls.getModifiers()));
        assertEquals(false, Modifier.isPrivate(cls.getModifiers()));
        
        Constructor con = cls.getDeclaredConstructor((Class[]) null);
        assertEquals(1, cls.getDeclaredConstructors().length);
        assertEquals(true, Modifier.isProtected(con.getModifiers()));
        
        Field fld = cls.getDeclaredField("INSTANCE");
        assertEquals(false, Modifier.isPublic(fld.getModifiers()));
        assertEquals(false, Modifier.isProtected(fld.getModifiers()));
        assertEquals(false, Modifier.isPrivate(fld.getModifiers()));
    } */

    //-----------------------------------------------------------------------
    public void testSupportedType() throws Exception {
        assertEquals(ReadablePeriod.class, ReadablePeriodConverter.INSTANCE.getSupportedType());
    }

    //-----------------------------------------------------------------------
    public void testGetPeriodType_Object() throws Exception {
        assertEquals(PeriodType.standard(),
            ReadablePeriodConverter.INSTANCE.getPeriodType(new Period(123L, PeriodType.standard())));
        assertEquals(PeriodType.yearMonthDayTime(),
            ReadablePeriodConverter.INSTANCE.getPeriodType(new Period(123L, PeriodType.yearMonthDayTime())));
    }

    public void testSetInto_Object() throws Exception {
        MutablePeriod m = new MutablePeriod(PeriodType.yearMonthDayTime());
        ReadablePeriodConverter.INSTANCE.setInto(m, new Period(0, 0, 0, 3, 0, 4, 0, 5), null);
        assertEquals(0, m.getYears());
        assertEquals(0, m.getMonths());
        assertEquals(0, m.getWeeks());
        assertEquals(3, m.getDays());
        assertEquals(0, m.getHours());
        assertEquals(4, m.getMinutes());
        assertEquals(0, m.getSeconds());
        assertEquals(5, m.getMillis());
    }

    //-----------------------------------------------------------------------
    public void testToString() {
        assertEquals("Converter[org.joda.time.ReadablePeriod]", ReadablePeriodConverter.INSTANCE.toString());
    }

}
