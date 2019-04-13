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
	
	public Adresse(String adresse1, String adresse2, int postleizahl, String ort) {
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.postleizahl = postleizahl;
		this.ort = ort;
	}

	
	/*
	 * Getter und Setter
	 */
	
	
	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public int getPostleizahl() {
		return postleizahl;
	}

	public void setPostleizahl(int postleizahl) {
		this.postleizahl = postleizahl;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}


	@Override
	public String toString() {
		return "{ adresse1: " + adresse1 + ", adresse2: " + adresse2 + ", postleizahl: " + postleizahl + ", ort: "
				+ ort + "}";
	}

	
	
}
