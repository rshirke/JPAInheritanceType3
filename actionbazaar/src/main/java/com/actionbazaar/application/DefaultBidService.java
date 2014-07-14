package com.actionbazaar.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.actionbazaar.domain.Bid;
import com.actionbazaar.repository.BidDao;

@Stateless
public class DefaultBidService implements BidService {

	@Inject
	private BidDao bidDao;

	@Override
	public Bid addBid(Bid bid) {
		return bidDao.addBid(bid);
	}

	@Override
	public Bid getBid(Long id) {
		return bidDao.getBid(id);
	}

	@Override
	public void updateBid(Bid bid) {
		bidDao.updateBid(bid);
	}

	@Override
	public void deleteBid(Bid bid) {
		bidDao.deleteBid(bid);
	}
}
