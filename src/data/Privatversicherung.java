package data;

import java.io.Serializable;
import java.util.Scanner;

public class Privatversicherung extends Krankenversicherung implements Serializable{

	private double deckungslimit;
	
	public Privatversicherung(String name, double deckungslimit) {
		super(name);
		this.deckungslimit = deckungslimit;
	}

	public double getDeckungslimit() {
		return deckungslimit;
	}

	public void setDeckungslimit(double deckungslimit) {
		this.deckungslimit = deckungslimit;
	}

	/**
	 * Returns the Coverage between 0 and deckungslimit
	 */
	@Override
	double calculateCoverage(double cost, int quarter, int previousQuarter) {
		return Math.max(0, Math.min(cost, this.deckungslimit));
	}

	public static Privatversicherung neuAnlegen() {
		System.out.println(" ====== ANLEGEN EINER NEUEN PRIVATEN KRANKENVERSICHERUNG ====== ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Wie heißt die private Krankenversicherung: ");
		String name = sc.nextLine();
		System.out.println("Wie hoch soll das Deckungslimit sein: ");
		double deckungslimit = 0;
		while(true) {
			String deckungslimitEingabe = sc.nextLine();
			try {
				deckungslimit = Double.parseDouble(deckungslimitEingabe);
				break;
			} catch (Exception e) {
				System.out.println("Fehlerhafte Eingabe für das Deckungslimit. Versuchen Sie es erneut: ");
			}
		}
		Privatversicherung pv = new Privatversicherung(name, deckungslimit);
		return pv;
	}


}
