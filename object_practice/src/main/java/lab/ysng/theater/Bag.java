package lab.ysng.theater;

import java.util.Objects;

public class Bag {

	private long amount;
	private Invitation invitation;
	private Ticket ticket;

	public Bag(final long amount) {
		this(amount, null);
	}

	public Bag(final long amount, final Invitation invitation) {
		this.amount = amount;
		this.invitation = invitation;
	}

	public long hold(final Ticket ticket) {
		if (hasInvitation()) {
			setTicket(ticket);

			return 0L;
		} else {
			minusAmount(ticket.getFee());
			setTicket(ticket);

			return ticket.getFee();
		}
	}

	private boolean hasInvitation() {
		return Objects.nonNull(invitation);
	}

	private void setTicket(final Ticket ticket) {
		this.ticket = ticket;
	}

	private void minusAmount(final long amount) {
		this.amount -= amount;
	}
}
