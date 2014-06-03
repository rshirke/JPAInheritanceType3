package com.actionbazaar.service;

import com.actionbazaar.domain.Bid;

public interface BidService {
	public void addBid(Bid bid);

	public Bid getBid(Long id);

	public void updateBid(Bid bid);

	public void deleteBid(Bid bid);
}
