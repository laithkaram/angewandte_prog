import java.util.Scanner;

public class GesetzlicheVersicherung extends Krankenversicherung {
	
	private boolean istFamilienVersicherung;
	
	public GesetzlicheVersicherung(String name, boolean istFamilienVersicherung) {
		super(name);
		this.istFamilienVersicherung = istFamilienVersicherung; 
	}

	public boolean isIstFamilienVersicherung() {
		return istFamilienVersicherung;
	}

	public void setIstFamilienVersicherung(boolean istFamilienVersicherung) {
		this.istFamilienVersicherung = istFamilienVersicherung;
	}

	/**
	 * Returns the cost if current quarter != previous quarter, else 0
	 */
	@Override
	double calculateCoverage(double cost, int quarter, int previousQuarter) {
		return (quarter != previousQuarter) ? cost : 0;  
	}

	public static GesetzlicheVersicherung neuAnlegen() {
		System.out.println(" ====== ANLEGEN EINER NEUEN GESETZLICHEN KRANKENVERSICHERUNG ====== ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Wie heißt die gesetzliche Krankenversicherung: ");
		String name = sc.nextLine();
		System.out.println("Ist die Krankenversicherung für die gesamte Familie (J: Ja, N: Nein): ");
		boolean istFamilienversicherung;
		while(true) {
			String familienEingabe = sc.nextLine();
			if (familienEingabe.startsWith("J") || familienEingabe.startsWith("j")) {
				istFamilienversicherung = true;
				break;
			}
			if (familienEingabe.startsWith("N") || familienEingabe.startsWith("n")) {
				istFamilienversicherung = false;
				break;
			}
			System.out.println("Geben Sie ja oder nein ein: ");
		}
		GesetzlicheVersicherung gv = new GesetzlicheVersicherung(name, istFamilienversicherung);
		return gv;
	}
}
