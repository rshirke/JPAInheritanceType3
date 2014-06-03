package com.actionbazaar.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.actionbazaar.domain.Bid;

@Stateless
@Path("/bid")
public class BidRestService {

	@EJB
	private BidService bidService;

	@POST
	@Consumes("text/xml")
	public void addBid(Bid bid) {
		bidService.addBid(bid);
	}

	@GET
	@Produces("text/xml")
	public Bid getBid(@QueryParam("id") Long id) {
		return bidService.getBid(id);
	}

	@PUT
	@Consumes("text/xml")
	public void updateBid(@QueryParam("id") Long id, Bid bid) {
		bidService.updateBid(bid);
	}

	@DELETE
	public void deleteBid(@QueryParam("id") Long id) {
		Bid bid = bidService.getBid(id);
		bidService.deleteBid(bid);
	}
}
