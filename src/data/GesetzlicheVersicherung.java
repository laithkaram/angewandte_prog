package data;

import java.io.Serializable;
import java.util.Scanner;

public class GesetzlicheVersicherung extends Krankenversicherung implements Serializable {
	
	private boolean istFamilienVersicherung;

	/**
	 *liegt eine Gesezlicherverishceun an
	 * @param name name der GesezlichenVersicherung
	 * @param istFamilienVersicherung Familienversicherung
	 */
	public GesetzlicheVersicherung(String name, boolean istFamilienVersicherung) {
		super(name);
		this.istFamilienVersicherung = istFamilienVersicherung; 
	}

	/**
	 * Prueft ob die Verishceung eine Fmilienversicherung ist
	 * @return istFamilienVersicherung , die Familienversicherung
	 */
	public boolean isIstFamilienVersicherung() {
		return istFamilienVersicherung;
	}

	/**
	 *liegt die Fmilienversicherung ein
	 * @param istFamilienVersicherung die familienversicherung
	 */
	public void setIstFamilienVersicherung(boolean istFamilienVersicherung) {
		this.istFamilienVersicherung = istFamilienVersicherung;
	}

	/**
	 *
	 * @param cost Cost
	 * @param quarter quarter
	 * @param previousQuarter perviousQuarter
	 * @return calculateCoverage , der angerechneterzahl
	 */

	@Override
	double calculateCoverage(double cost, int quarter, int previousQuarter) {
		return (quarter != previousQuarter) ? cost : 0;  
	}

	/**
	 * Liegt neue GesetzlicheVersicherung an
	 * @return gv , Gesetzlicherversicherung
	 */
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
