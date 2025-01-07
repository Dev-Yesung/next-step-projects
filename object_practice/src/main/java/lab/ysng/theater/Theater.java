package lab.ysng.theater;

public class Theater {

	private TicketSeller ticketSeller;

	public Theater(final TicketSeller ticketSeller) {
		this.ticketSeller = ticketSeller;
	}

	public void enter(Audience audience) {
		ticketSeller.sellTo(audience);
	}
}
