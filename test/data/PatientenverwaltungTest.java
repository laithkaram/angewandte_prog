package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.CSVPersistenceManager;
import utils.FileManager;

import java.io.File;
import java.io.IOException;

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

}