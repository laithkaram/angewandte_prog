package ui.listmodels;

import data.Aufenthalt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class AufenthaltData extends AbstractListModel<Aufenthalt> {

    private JFrame fenster;
    private Vector<Aufenthalt> aufenthalte;

    public AufenthaltData(JFrame fenster) {
        this.fenster = fenster;
        this.aufenthalte = new Vector<>();
    }

    public void setAufenthalte(ArrayList<Aufenthalt> aufenthalte) {
        this.aufenthalte.clear();
        this.aufenthalte.addAll(aufenthalte);
    }

    public void clearData() {
        this.aufenthalte.clear();
    }

    @Override
    public Aufenthalt getElementAt(int index) {
        try {
            return this.aufenthalte.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public int getSize() {
        return this.aufenthalte.size();
    }

}
