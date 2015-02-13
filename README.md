Testing Java EE Applications using Arquillian
================================================================
This application demonstrates how Java EE applications can be effectively 
tested using Arquillian. The application contains a 
set of JUnit tests that you should examine. The tests will be run as part 
of the Maven build. We used GlassFish 4.1 for the application (see setup 
instructions below) but it should be easy to port to any Java EE 7 application
server such as JBoss, WildFly, WebLogic or WebSphere.

Setup
-----
* Install GlassFish 4.1
* Please download this repository. You can use Git or just the simple zip
  download.
* The demo is just a simple Maven project under the [actionbazaar](actionbazaar)
  directory. You should be able to open it up in any Maven capable IDE, we used
  NetBeans.
* If desired setup GlassFish in your IDE.
* The tests in the Maven build are executed against an installed GlassFish instance.
  You will need to configure 
  [this file] (actionbazaar/src/test/resources/arquillian.xml) with the details
  of your GlassFish installation. For details on configuring GlassFish for 
  Arquillian, look 
  [here] (https://docs.jboss.org/author/display/ARQ/GlassFish+3.1+-+Managed). 
   Note that in this case we are using GlassFish in managed mode. This means that
   Arquillian still automatically start and stop GlassFish as needed. You must
   set an admin password for GlassFish. Due to a GlassFish bug, Arquillian can't
   start GlassFish with an empty admin password. Also, GlassFish embedded is 
   no longer supported as of GlassFish 4 and cannot be used. Similarly, we ran
   into GlassFish bugs trying to use remote mode (normally the fastest option
   with Arquillian).
* If desired, you can deploy and run the application manually. We did this both
  via NetBeans and by using the plain Maven generated war file.