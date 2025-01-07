package lab.ysng.theater;

public class TicketSeller {

	private TicketOffice ticketOffice;

	public TicketSeller(final TicketOffice ticketOffice) {
		this.ticketOffice = ticketOffice;
	}

	public void sellTo(final Audience audience) {
		final long amount = audience.buy(ticketOffice.getTicket());
		ticketOffice.plusAmount(amount);
	}

}
