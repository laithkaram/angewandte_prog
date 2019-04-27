/**
 * 
 * @author laithkaram
 *
 */
public class Adresse {

	private String adresse1;
	private String adresse2;
	private int postleizahl;
	private String ort;
	/**
	* erzeugt die adresse der patienten oder von dem krankenhaus
	*
	* @param adresse1 , adresse2, postleizahl, ort 
	*/
	public Adresse(String adresse1, String adresse2, int postleizahl, String ort) {
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.postleizahl = postleizahl;
		this.ort = ort;
	}

	
	/*
	 * Getter und Setter
	 */
	

	/**
	 * Fuegt die erste adresse von den patienten	
	 * @return adresse1
	 */
	public String getAdresse1() {
		return adresse1;
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param patient
	 */
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param patient
	 */
	public String getAdresse2() {
		return adresse2;
	}

	/**
	 * Fuegt einen Patienten in die Liste, wenn freier Platz verfuegbar ist.
	 * @param adresse2
	 */
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	/**
	 * erzeugt den postleizahl von dem patient und gibt den zureck
	 * @return postleizahl
	 */
	public int getPostleizahl() {
		return postleizahl;
	}

	/**
	 * Fuegt den postleizahl einem patienten 
	 * @param postleizahl
	 */
	public void setPostleizahl(int postleizahl) {
		this.postleizahl = postleizahl;
	}

	/**
	 * gibt den ort zureck
	 * @return ort	 
	 * */
	public String getOrt() {
		return ort;
	}

	/**
	 * Ort vom krankenhaus oder von patienten
	 * @param ort
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	* eine ubernehmende Overrive methode gibt die adresse daten von den patienten an
	*
	* @return , adresse1,adresse2,ort,postleizahl
	*/
	@Override
	public String toString() {
		return "{ adresse1: " + adresse1 + ", adresse2: " + adresse2 + ", postleizahl: " + postleizahl + ", ort: "
				+ ort + "}";
	}

	
	
}
