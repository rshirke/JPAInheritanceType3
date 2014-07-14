package com.actionbazaar.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.actionbazaar.domain.Bid;

public class DefaultBidDao implements BidDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addBid(Bid bid) {
		entityManager.persist(bid);
	}

	@Override
	public Bid getBid(Long id) {
		return entityManager.find(Bid.class, id);
	}

	@Override
	public void updateBid(Bid bid) {
		entityManager.merge(bid);
	}

	@Override
	public void deleteBid(Bid bid) {
		entityManager.remove(entityManager.merge(bid));
	}
}