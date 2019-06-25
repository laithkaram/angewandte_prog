package data;

import utils.Logger;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Qualitaetsmanagementstelle implements Observer {

    private ArrayList<Aufenthalt> aufenthalte;

    public Qualitaetsmanagementstelle() {
        this.aufenthalte = new ArrayList<>();
    }

    public void addAufenthalt(Aufenthalt aufenthalt) {
        this.aufenthalte.add(aufenthalt);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) return;

        Aufenthalt a = ((Aufenthalt) arg);
        Logger.getInstance().log("Qualitätsmanagementstelle hat die Information erhalten, dass ein " +
                String.format("%.2f", a.getDays()) + "-Tage Aufenthalt\n" +
                "für den Patient " + a.getPatient().getName() + " " + a.getPatient().getNachname() +
                " mit der Patientennummer " + a.getPatient().getPatientennummer() + " stattgefunden hat.");
    }
}
