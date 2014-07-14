package com.actionbazaar.application;

import com.actionbazaar.domain.Bid;

public interface BidService {
	public Bid addBid(Bid bid);

	public Bid getBid(Long id);

	public void updateBid(Bid bid);

	public void deleteBid(Bid bid);
}
