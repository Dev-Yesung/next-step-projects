package lab.ysng.theater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {

	private long amount;
	private List<Ticket> tickets = new ArrayList<>();

	public TicketOffice(long amount, Ticket... tickets) {
		this.amount = amount;
		this.tickets.addAll(Arrays.asList(tickets));
	}

	public Ticket getTicket() {
		return this.tickets.getFirst();
	}

	public void plusAmount(final long amount) {
		this.amount += amount;
	}
}
