package lab.ysng.theater;

public class Audience {

	private Bag bag;

	public Audience(Bag bag) {
		this.bag = bag;
	}

	public long buy(Ticket ticket) {
		return bag.hold(ticket);
	}
}
