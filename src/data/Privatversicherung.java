package data;

import java.io.Serializable;
import java.util.Scanner;


public class Privatversicherung extends Krankenversicherung implements Serializable{

	private double deckungslimit;

	/**
	 * liegt privatecersicherung an
	 * @param name name dere privatenversicherung
	 * @param deckungslimit deckungslimit von der versicherung
	 */
	public Privatversicherung(String name, double deckungslimit) {
		super(name);
		this.deckungslimit = deckungslimit;
	}

	/**
	 * erzeugt de n deckungslimit und gibt den  zureck
	 * @return deckungslimit , gibt den deckungslimit tuereck
	 */
	public double getDeckungslimit() {
		return deckungslimit;
	}

	/**
	 * liegt den deuckungslimit ein
	 * @param deckungslimit , this dechkungslimit
	 */
	public void setDeckungslimit(double deckungslimit) {
		this.deckungslimit = deckungslimit;
	}

	/**
	 * macht die  Coverage zwischen 0 und deckungslimit zureuck
	 */
	@Override
	double calculateCoverage(double cost, int quarter, int previousQuarter) {
		return Math.max(0, Math.min(cost, this.deckungslimit));
	}

	/**
	 * erzeugt eine neue Privatenversicherung und gibt die zureck
	 * @return pv , die neue privateversicherung
	 */
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
