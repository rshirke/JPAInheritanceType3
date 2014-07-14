package com.actionbazaar.repository;

import javax.enterprise.inject.Alternative;

import com.actionbazaar.domain.Bid;
import com.actionbazaar.repository.BidDao;

@Alternative
public class MockBidDao implements BidDao {

	public Bid addBid(Bid bid) {
		return null;
	}

	public Bid getBid(Long id) {
		return null;
	}

	public void updateBid(Bid bid) {
	}

	public void deleteBid(Bid bid) {
	}
}