package com.actionbazaar.dao;

import com.actionbazaar.domain.Bid;

public interface BidDao {
	public void addBid(Bid bid);

	public Bid getBid(Long id);

	public void updateBid(Bid bid);

	public void deleteBid(Bid bid);
}
