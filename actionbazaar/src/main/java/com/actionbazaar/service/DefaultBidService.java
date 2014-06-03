package com.actionbazaar.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.actionbazaar.dao.BidDao;
import com.actionbazaar.domain.Bid;

@Stateless
public class DefaultBidService implements BidService {

	@Inject
	private BidDao bidDao;

	@Override
	public void addBid(Bid bid) {
		bidDao.addBid(bid);
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
