package com.actionbazaar.interfaces.web;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.actionbazaar.domain.Bid;
import com.actionbazaar.service.BidService;

@Named
@RequestScoped
public class PlaceBid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private BidService bidService;

	@Produces
	@Named
	@RequestScoped
	private Bid bid = new Bid();

	public String placeBid() {
		bidService.addBid(bid);

		return "confirm_bid.jsf";
	}
}