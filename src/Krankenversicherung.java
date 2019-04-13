
public class Krankenversicherung extends Object {
	private String name;
	private int nummer;

	
	
	public Krankenversicherung(String name, int nummer) {
		this.name = name;
		this.nummer = nummer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		else if (obj instanceof Krankenversicherung){
			Krankenversicherung k = (Krankenversicherung) obj;
			if (this.name == k.name && this.nummer == k.nummer) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "{ name: "+ this.name + ", nummer: " + this.nummer + "}";
	}
	
	
}
