import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author laithkaram
 *@version 1.8
 */

public class Patientenverwaltung {
	/**
	* Hauptprogramm.
	*
	* @param args Kommandozeilenparameter
	*/
	public static void main(String[] args) {
		try {
			Krankenhaus kh = new Krankenhaus("Helios Krankenhaus", "DE812524991", new Adresse("Schwanebecker Chaussee 50", "", 13125, "Berlin"));

			Patient p1 = new Patient("P-234213", Anrede.HERR, "Max", "Mustermann", 
					new Adresse("afrikanischestrasse 23", "kottiplatz 23", 12412, "Berlin"), 
					new Date(1994- 1900, 10, 23),
					"+491534234234",
					"max.mustermann@gmail.com");
			
			Krankenversicherung k1_p1 = new Privatversicherung("TK", 2139861234, 300);
			Krankenversicherung k2_p1 = new GesetzlicheVersicherung("Allianz - Unfallversicherung", 124124, true);
			Krankenversicherung k3_p1 = new Privatversicherung("DBK - Arbeitsunfähigkeitsversicherung", 687283423, 450);
			p1.addKrankenversicherung(k1_p1);
			p1.addKrankenversicherung(k2_p1);
			p1.addKrankenversicherung(k3_p1);
			
			Patient p2 = new Patient("P-234244", Anrede.HERR, "Kross", "lucas", 
					new Adresse("Stromstrasse", "binjastrasse 23", 12412, "Berlin"), 
					new Date(1994- 1900, 10, 23),
					"max.mustermann@gmail.com",
					"+491534234234");
			Krankenversicherung k1_p2 = new GesetzlicheVersicherung("Allianz", 3434, false);
			p2.addKrankenversicherung(k1_p2);
			
			Patient p3 = new Patient("P-234213", Anrede.FRAU, "Julia", "kilman", 
					new Adresse("Schokolade Ecke", "Abgebissen 23", 12412, "Bremen"), 
					new Date(1994- 1900, 10, 23),
					"max.mustermann@gmail.com",
					"+491534234234");
			Krankenversicherung k1_p3 = new Privatversicherung("Allianz", 3434, 1250);
			p3.addKrankenversicherung(k1_p3);
			
			Patient p4 = new Patient("P-234213", Anrede.HERR, "jork", "klein", 
					new Adresse("reickinstrasse", "residenzplatz 23", 12433, "Hamburg"), 
					new Date(1994- 1900, 10, 23),
					"max.mustermann@gmail.com",
					"+491534234234");
			
			Patient p5 = new Patient("P-234213", Anrede.FRAU, "Heike", "Freter", 
					new Adresse("lipschitzalle23", "Johanstallerchausse", 12488, "Berlin"), 
					new Date(1994- 1900, 10, 23),
					"max.mustermann@gmail.com",
					"+491534234234");
			
			
			kh.addPatient(p1);
			kh.addPatient(p2);
			kh.addPatient(p3);
			kh.addPatient(p4);
			kh.addPatient(p5);
			
			System.out.println(kh);
			
			System.out.println();
			
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p3);
			System.out.println(p4);
			System.out.println(p5);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	
	}
}
