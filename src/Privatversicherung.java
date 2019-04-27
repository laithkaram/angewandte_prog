
public class Privatversicherung extends Krankenversicherung {

	private double deckungslimit;
	
	public Privatversicherung(String name, int nummer, double deckungslimit) {
		super(name, nummer);
		this.deckungslimit = deckungslimit;
	}

	/**
	 * Returns the Coverage between 0 and deckungslimit
	 */
	@Override
	double calculateCoverage(double cost, int quarter, int previousQuarter) {
		return Math.max(0, Math.min(cost, this.deckungslimit));
	}
	


}
