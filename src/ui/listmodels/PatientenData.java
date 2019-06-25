package ui.listmodels;

import data.Patient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class PatientenData extends AbstractListModel<Patient> {

    private JFrame fenster;
    private Vector<Patient> patientenNamen;

    public PatientenData(JFrame fenster) {
        this.fenster = fenster;
        this.patientenNamen = new Vector<>();
    }

    public void setPatientenNamen(ArrayList<Patient> patientenNamen) {
        this.patientenNamen.clear();
        this.patientenNamen.addAll(patientenNamen);
    }

    @Override
    public Patient getElementAt(int index) {
        try {
            return this.patientenNamen.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int getSize() {
        return this.patientenNamen.size();
    }
}
