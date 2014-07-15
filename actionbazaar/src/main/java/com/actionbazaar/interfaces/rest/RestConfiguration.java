package com.actionbazaar.interfaces.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * JAX-RS configuration.
 */
@ApplicationPath("rest")
public class RestConfiguration extends ResourceConfig {

	public RestConfiguration() {
		// Resources
		packages(new String[] { BidRestService.class.getPackage().getName() });
		// Providers - JSON.
		register(new MoxyJsonFeature());
		register(new JsonMoxyConfigurationContextResolver()); // TODO See if
																// this can be
																// removed.
	}
}