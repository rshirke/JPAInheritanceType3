package com.actionbazaar.test;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.actionbazaar.application.BidService;
import com.actionbazaar.application.DefaultBidService;
import com.actionbazaar.dao.MockBidDao;
import com.actionbazaar.domain.Bid;
import com.actionbazaar.repository.BidDao;
import com.actionbazaar.repository.DefaultBidDao;

@RunWith(Arquillian.class)
public class BidServiceUnitTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(BidService.class, DefaultBidService.class,
						BidDao.class, DefaultBidDao.class, MockBidDao.class,
						Bid.class)
				.addAsWebInfResource("test-beans.xml", "beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@EJB
	private BidService bidService;

	@Test
	public void testAddBid() {
		Bid bid = new Bid();
		bid.setBidder("rrahman");
		bid.setItem("Test item");
		bid.setAmount(100.50);

		bidService.addBid(bid);
	}

	@Test
	public void testGetBid() {
		Bid bid = bidService.getBid(1L);
		assertNotNull(bid);
	}

	@Test
	public void testUpdateBid() {
		Bid bid = bidService.getBid(1L);
		bid.setAmount(101.50);
		bidService.updateBid(bid);
	}

	@Test
	public void testDeleteBid() {
		Bid bid = bidService.getBid(1L);
		bidService.deleteBid(bid);
	}
}
