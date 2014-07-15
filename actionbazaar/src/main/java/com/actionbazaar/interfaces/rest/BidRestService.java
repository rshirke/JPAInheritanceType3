package com.actionbazaar.interfaces.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.actionbazaar.application.BidService;
import com.actionbazaar.domain.Bid;

@Stateless
@Path("/bids")
public class BidRestService {

	@EJB
	private BidService bidService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Bid addBid(Bid bid) {
		return bidService.addBid(bid);
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Bid getBid(@PathParam("id") Long id) {
		return bidService.getBid(id);
	}

	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void updateBid(@PathParam("id") Long id, Bid bid) {
		bidService.updateBid(bid);
	}

	@DELETE
	@Path("{id}")
	public void deleteBid(@PathParam("id") Long id) {
		Bid bid = bidService.getBid(id);
		bidService.deleteBid(bid);
	}
}
