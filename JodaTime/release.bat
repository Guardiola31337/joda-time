call maven site:sshdeploy
cd build
pscp joda-time-1.6-bundle.jar scolebourne@shell.sourceforge.net:/home/groups/j/jo/joda-time/htdocs
cd ..
svn mkdir "https://joda-time.svn.sourceforge.net/svnroot/joda-time/tags/v1_6_000" -m "Release 1.6"
svn copy https://joda-time.svn.sourceforge.net/svnroot/joda-time/trunk/JodaTime https://joda-time.svn.sourceforge.net/svnroot/joda-time/tags/v1_6_000/JodaTime -m "Release 1.6"