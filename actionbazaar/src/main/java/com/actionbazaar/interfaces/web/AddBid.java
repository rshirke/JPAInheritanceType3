package com.actionbazaar.interfaces.web;

import com.actionbazaar.application.BidService;
import com.actionbazaar.domain.Bid;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddBid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BidService bidService;

    @Produces
    @Named
    @RequestScoped
    private final Bid bid = new Bid();

    public String onAdd() {
        bidService.addBid(bid);

        return "confirm_bid.jsf";
    }
}
