package com.actionbazaar.infrastructure.database;

import com.actionbazaar.domain.BidRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.actionbazaar.domain.Bid;

public class DefaultBidRepository implements BidRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Bid addBid(Bid bid) {
		entityManager.persist(bid);
		
		return bid;
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