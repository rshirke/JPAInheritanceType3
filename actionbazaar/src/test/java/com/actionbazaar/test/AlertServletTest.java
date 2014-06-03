package com.actionbazaar.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;

import com.actionbazaar.web.AlertServlet;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

@RunWith(Arquillian.class)
public class AlertServletTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addClass(
				AlertServlet.class);
	}

	@Test
	public void testGetAlerts() {
		try {
			WebConversation webConversation = new WebConversation();
			WebRequest webRequest = new GetMethodWebRequest(
					"http://localhost:7070/test/alerts");
			webRequest.setParameter("user_id", "1111");
			WebResponse webResponse = webConversation.getResponse(webRequest);
			assertEquals(5, webResponse.getDOM().getElementsByTagName("alert")
					.getLength());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}