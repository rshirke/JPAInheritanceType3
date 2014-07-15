package com.actionbazaar.interfaces.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * JAX-RS configuration.
 */
@ApplicationPath("rest")
public class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        // Resource
        packages(new String[]{BidRestService.class.getPackage().getName()});															// removed.
    }
}
