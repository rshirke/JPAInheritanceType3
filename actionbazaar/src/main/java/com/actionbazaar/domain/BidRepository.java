package com.actionbazaar.domain;

import com.actionbazaar.domain.Bid;

public interface BidRepository {
	public Bid addBid(Bid bid);

	public Bid getBid(Long id);

	public void updateBid(Bid bid);

	public void deleteBid(Bid bid);
}
