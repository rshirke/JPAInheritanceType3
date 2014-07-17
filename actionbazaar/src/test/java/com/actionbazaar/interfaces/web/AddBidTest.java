package com.actionbazaar.interfaces.web;

import com.actionbazaar.application.BidService;
import com.actionbazaar.application.DefaultBidService;
import com.actionbazaar.domain.Bid;
import com.actionbazaar.domain.BidRepository;
import com.actionbazaar.infrastructure.database.DefaultBidRepository;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.Activity;
import org.jboss.arquillian.warp.Inspection;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
@WarpTest
public class AddBidTest {

    @Drone
    WebDriver browser;

    @ArquillianResource
    URL contextPath;

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(BidService.class, DefaultBidService.class,
                        BidRepository.class, DefaultBidRepository.class, Bid.class,
                        AddBid.class)
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
    public void testAddBid() {
        Warp.initiate(new Activity() {
            @Override
            public void perform() {
                browser.navigate().to(contextPath + "add_bid.jsf");
            }
        }).inspect(new Inspection() {
//                    @Inject
//                    CdiBean myBean;
//
//                    @AfterPhase(RENDER_RESPONSE)
//                    public void initial_state_havent_changed_yet() {
//                        assertEquals("John", myBean.getName());
//                    }
        });

//
//        JSFServerSession server = session.getJSFServerSession();
//
//        assertEquals(null, server.getManagedBeanValue("#{bid.item}"));
//        assertEquals(null, server.getManagedBeanValue("#{bid.bidder}"));
//        assertEquals(null, server.getManagedBeanValue("#{bid.amount}"));
//
//        JSFClientSession client = session.getJSFClientSession();
//
//        client.setValue("item", "Test Item");
//        client.setValue("bidder", "Test Bidder");
//        client.setValue("amount", "101.50");
//        client.click("add");
//
//        assertEquals("/confirm_bid.xhtml", server.getCurrentViewID());
//
//        assertEquals("Test Item", server.getManagedBeanValue("#{bid.item}"));
//        assertEquals("Test Bidder", server.getManagedBeanValue("#{bid.bidder}"));
//        assertEquals(101.50, server.getManagedBeanValue("#{bid.amount}"));
    }
}
