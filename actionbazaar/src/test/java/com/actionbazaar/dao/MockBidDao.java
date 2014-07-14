package com.actionbazaar.dao;

import javax.enterprise.inject.Alternative;

import com.actionbazaar.domain.Bid;
import com.actionbazaar.repository.BidDao;

@Alternative
public class MockBidDao implements BidDao {

	public void addBid(Bid bid) {
	}

	public Bid getBid(Long id) {
		return null;
	}

	public void updateBid(Bid bid) {
	}

	public void deleteBid(Bid bid) {
	}
}