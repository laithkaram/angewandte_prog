
public class GesetzlicheVersicherung extends Krankenversicherung {
	
	private boolean istFamilienVersicherung;
	
	public GesetzlicheVersicherung(String name, int nummer, boolean istFamilienVersicherung) {
		super(name, nummer);
		this.istFamilienVersicherung = istFamilienVersicherung; 
	}

	/**
	 * Returns the cost if current quarter != previous quarter, else 0
	 */
	@Override
	double calculateCoverage(double cost, int quarter, int previousQuarter) {
		return (quarter != previousQuarter) ? cost : 0;  
	}
	
}
