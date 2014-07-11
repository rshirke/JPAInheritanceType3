package com.actionbazaar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.faces.application.ProjectStage;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.jsfunit.framework.Environment;
import org.jboss.jsfunit.jsfsession.JSFClientSession;
import org.jboss.jsfunit.jsfsession.JSFServerSession;
import org.jboss.jsfunit.jsfsession.JSFSession;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.actionbazaar.dao.BidDao;
import com.actionbazaar.dao.DefaultBidDao;
import com.actionbazaar.domain.Bid;
import com.actionbazaar.interfaces.web.PlaceBid;
import com.actionbazaar.service.BidService;
import com.actionbazaar.service.DefaultBidService;

@RunWith(Arquillian.class)
public class AddBidTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(BidService.class, DefaultBidService.class,
						BidDao.class, DefaultBidDao.class, Bid.class,
						PlaceBid.class)
				.addAsWebInfResource("test-web.xml", "web.xml")
				.addAsWebResource("add_bid.xhtml", "add_bid.xhtml")
				.addAsWebResource("confirm_bid.xhtml", "confirm_bid.xhtml")
				.addAsWebInfResource("test-faces-config.xml",
						"faces-config.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml");
	}

	@Test
	public void testAddBid() throws Exception {
		JSFSession session = new JSFSession("/add_bid.jsf");

		assertTrue(Environment.is12Compatible());
		assertTrue(Environment.is20Compatible());
		assertEquals(2, Environment.getJSFMajorVersion());
		assertEquals(0, Environment.getJSFMinorVersion());

		JSFServerSession server = session.getJSFServerSession();

		assertEquals(ProjectStage.Development, server.getFacesContext()
				.getApplication().getProjectStage());

		assertEquals(null, server.getManagedBeanValue("#{bid.item}"));
		assertEquals(null, server.getManagedBeanValue("#{bid.bidder}"));
		assertEquals(null, server.getManagedBeanValue("#{bid.amount}"));

		JSFClientSession client = session.getJSFClientSession();

		client.setValue("item", "Test Item");
		client.setValue("bidder", "Test Bidder");
		client.setValue("amount", "101.50");
		client.click("add");

		assertEquals("/confirm_bid.xhtml", server.getCurrentViewID());

		assertEquals("Test Item", server.getManagedBeanValue("#{bid.item}"));
		assertEquals("Test Bidder", server.getManagedBeanValue("#{bid.bidder}"));
		assertEquals(101.50, server.getManagedBeanValue("#{bid.amount}"));
	}
}