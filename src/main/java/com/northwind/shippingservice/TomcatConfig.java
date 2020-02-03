package com.northwind.shippingservice;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;

public class TomcatConfig {

    public static final int port = 8086;

    public static void main(String args[]) throws Exception {

        // Declare the base path for the web app (not the server, that's different)
        // It's going to be the current path (where we're executing the jar from)
        String appBase = new File(".").getAbsolutePath();

        // Create an instance of Tomcat and set it up...
        Tomcat tomcat = new Tomcat();

        // Set the container's base directory (not the webApp's base directory)
        tomcat.setBaseDir(createTempDir());

        // Port to listen on. We'll default it to port 8080
        tomcat.setPort(port);

        // As of Tomcat 9, the default connector is not loaded by default, so
        // we need to explicitly get the default connector
        tomcat.getConnector();

        // Set the base path for the webApp and root context (they are the same in this case)
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp("", appBase);

        // Start the server and block the current thread till the server exits.
        tomcat.start();
        tomcat.getServer().await();
    }

    private static String createTempDir() {
        // We need a "temp" director that is going to be the "work" or container base path.
        try {
            File tempDir = File.createTempFile("tomcat.", "." + port);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit(); //self-cleanup by auto deleting when this process exits.
            return tempDir.getAbsolutePath();
        } catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            );
        }
    }
}
