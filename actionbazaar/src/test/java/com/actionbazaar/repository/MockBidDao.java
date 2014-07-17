package com.actionbazaar.repository;

import com.actionbazaar.domain.BidRepository;
import com.actionbazaar.domain.Bid;

public class MockBidDao implements BidRepository {

	public Bid addBid(Bid bid) {
		return bid;
	}

	public Bid getBid(Long id) {
		Bid bid = new Bid();
		bid.setId(id);
		bid.setBidder("nrahman");
		bid.setItem("Test item");
		bid.setAmount(100.00);

		return bid;
	}

	public void updateBid(Bid bid) {
	}

	public void deleteBid(Bid bid) {
	}
}