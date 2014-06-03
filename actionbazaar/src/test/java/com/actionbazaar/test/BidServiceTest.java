package com.actionbazaar.test;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.actionbazaar.dao.BidDao;
import com.actionbazaar.dao.DefaultBidDao;
import com.actionbazaar.domain.Bid;
import com.actionbazaar.service.BidService;
import com.actionbazaar.service.DefaultBidService;

@RunWith(Arquillian.class)
public class BidServiceTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(BidService.class, DefaultBidService.class,
						BidDao.class, DefaultBidDao.class, Bid.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml");
	}

	@EJB
	private BidService bidService;

	@Test
	@InSequence(1)
	public void testAddBid() {
		Bid bid = new Bid();
		bid.setBidder("rrahman");
		bid.setItem("Test item");
		bid.setAmount(100.50);

		bidService.addBid(bid);
	}

	@Test
	@InSequence(2)	
	public void testGetBid() {
		Bid bid = bidService.getBid(1L);
		assertNotNull(bid);
	}

	@Test
	@InSequence(3)	
	public void testUpdateBid() {
		Bid bid = bidService.getBid(1L);
		bid.setAmount(101.50);
		bidService.updateBid(bid);
	}

	@Test
	@InSequence(4)
	public void testDeleteBid() {
		Bid bid = bidService.getBid(1L);
		bidService.deleteBid(bid);
	}
}
