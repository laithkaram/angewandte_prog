package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.CSVPersistenceManager;
import utils.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatientenverwaltungTest {

    Patientenverwaltung pv;

    @BeforeEach
    public void setup() {
       this.pv = new Patientenverwaltung();
    }

    /*
        - Der Export von Patientendaten (CSV) und
        - der Import von Patientendaten (CSV)
        sollen in zwei Testfälle überprüft werden.
     */
    @Test
    public void testImportPatientWithCSV() {
        assertEquals(0, pv.kh.getPatienten().size());
        // the csv file contains 5 Patients
        pv.importCSV("tst-patienten-import");
        assertEquals(5, pv.kh.getPatienten().size());
    }

    @Test
    public void testExportPatientWithCSV(){
        String filename = "tst-patienten-export";
        File f = new File(FileManager.manipulateFilename(filename, ".csv"));
        if (f.exists()) {
            f.delete();
        }
        assertFalse(f.exists());

        pv.loadSerializable();
        CSVPersistenceManager.exportPatientsOrderByName(pv.kh, filename);
        assertTrue(f.exists());
    }

    @Test
    public void testAnlegenEinerGesetzlichenVersicherung() {
        this.pv.loadSerializable();

        Patient p = this.pv.kh.getPatienten().get(0);
        GesetzlicheVersicherung newVersicherung = new GesetzlicheVersicherung("KV Name", true);

        assertFalse(p.getKrankenversicherung().contains(newVersicherung));
        p.getKrankenversicherung().add(newVersicherung);
        assertTrue(p.getKrankenversicherung().contains(newVersicherung));
    }

    @Test
    public void testPatientEinchecken() {
        this.pv.loadSerializable();
        Patient p = this.pv.kh.getPatienten().get(0);

        ArrayList<Aufenthalt> aufenthalts = this.pv.aufenthalte.get(p.getPatientennummer());
        assertTrue(aufenthalts == null || !aufenthalts.get(aufenthalts.size() - 1).canCheckOut());
        this.pv.patientAufnehmen(p, new Date());

        aufenthalts = this.pv.aufenthalte.get(p.getPatientennummer());
        assertTrue(aufenthalts.get(aufenthalts.size() - 1).canCheckOut());
    }

    @Test
    public void testPatientAuschecken() {
        this.testPatientEinchecken();

        Patient p = this.pv.kh.getPatienten().get(0);
        ArrayList<Aufenthalt> aufenthalts = this.pv.aufenthalte.get(p.getPatientennummer());
        assertTrue(aufenthalts.get(aufenthalts.size() - 1).canCheckOut());
        this.pv.patientAuschecken(p, new Date());

        aufenthalts = this.pv.aufenthalte.get(p.getPatientennummer());
        assertTrue(!aufenthalts.get(aufenthalts.size() - 1).canCheckOut());
    }
}