package com.actionbazaar.interfaces.web;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AlertServletTest {

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "actionbazaar-test.war")
				.addClass(AlertServlet.class)
				.addAsWebInfResource("test-weblogic.xml", "weblogic.xml");
		File[] files = Maven
				.resolver()
				.resolve(
						"org.glassfish.jersey.media:jersey-media-json-processing:2.5.1")
				.withTransitivity().asFile();
		archive.addAsLibraries(files);

		return archive;
	}

	@Test
	public void testGetAlerts() {
		Client client = ClientBuilder.newClient();

		// Get account balance
		JsonObject response = client
				.target("http://localhost:7001/actionbazaar-test/alerts")
				.queryParam("user_id", "1111").request("application/json")
				.get(JsonObject.class);
		assertEquals(5, response.getJsonArray("alerts").size());
	}
}