
package com.example.employees;

import java.util.Optional;
import java.io.File;



import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;



public class Main {
    
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    
    public static void main(String[] args) throws Exception {
        String webappDirLocation = "target/employees-app-1.0-SNAPSHOT-jar-with-dependencies.jar";
        Tomcat tomcat = new Tomcat();
        
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.valueOf(webPort));   
        
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}