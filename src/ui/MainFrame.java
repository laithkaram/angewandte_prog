package ui;

import data.Patientenverwaltung;
import javafx.scene.control.SelectionMode;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    Patientenverwaltung pv;
    JList<String> patientenListe;

    public MainFrame() throws HeadlessException {
        super("data.Patientenverwaltung");

        pv = new Patientenverwaltung();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	Klick	auf	x

        this.setSize(600,400);
        this.setLocationRelativeTo(null);

        initMenuBar();
        initContentPane();

        this.setVisible(true);
    }

    private void initMenuBar() {
        JMenuBar menu = new JMenuBar();

        // Patientenmenu
        JMenu patientenMenu = new JMenu("Patienten");

        JMenuItem anlegen = new JMenuItem("Neu Anlegen");
        JMenuItem searchByName = new JMenuItem("Nach Name suchen");
        JMenuItem searchByNumber = new JMenuItem("Nach Patientennummer suchen");
        JMenuItem importieren = new JMenuItem("Importieren");
        JMenuItem exportieren = new JMenuItem("Exportieren");
        JMenuItem close = new JMenuItem("Schließen");
        close.setActionCommand("close");
        close.addActionListener(this);

        patientenMenu.add(anlegen);
        patientenMenu.add(searchByName);
        patientenMenu.add(searchByNumber);
        patientenMenu.addSeparator();
        patientenMenu.add(importieren);
        patientenMenu.add(exportieren);
        patientenMenu.addSeparator();
        patientenMenu.add(close);

        menu.add(patientenMenu);

        // Versicherungen
        JMenu versicherungen = new JMenu("Versicherungen");

        JMenuItem privateAnlegen = new JMenuItem("Privatversicherung anlegen");
        JMenuItem gesetzlAnlegen = new JMenuItem("Gesetzliche Versicherung anlegen");
        JMenuItem versicherunAnzeigen = new JMenuItem("Versicherung anzeigen");

        versicherungen.add(privateAnlegen);
        versicherungen.add(gesetzlAnlegen);
        versicherungen.add(versicherunAnzeigen);

        menu.add(versicherungen);

        // aufenthalteﬂ
        JMenu aufenthalte = new JMenu("Aufenthalte");

        JMenuItem patientAufnehmen = new JMenuItem("PatientIn aufnehmen");
        JMenuItem patientEntlassen = new JMenuItem("PatientIn entlassen");

        aufenthalte.add(patientAufnehmen);
        aufenthalte.add(patientEntlassen);

        menu.add(aufenthalte);

        // info
        JMenu info = new JMenu("Info");

        JMenuItem about = new JMenuItem("About");
        about.setActionCommand("about");
        about.addActionListener(this);
        info.add(about);

        menu.add(info);

        this.setJMenuBar(menu);
    }

    private void initContentPane() {
        this.getContentPane().setLayout(new GridLayout(1, 2));

        JPanel patientenPanel = initPatientenPanel();
        JPanel detailPanel = initDetailPanel();

        this.add(patientenPanel);
        this.add(detailPanel);
    }


    private JPanel initPatientenPanel() {
        JPanel panel = new JPanel();

        patientenListe = new JList<>();
        patientenListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        patientenListe.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    System.out.println("index: " + patientenListe.getSelectedIndex()
                            + ", value: " + patientenListe.getSelectedValue());
                }
            }
        });

        updatePatientenList();
        JScrollPane scrollPane = new JScrollPane(patientenListe);

        panel.add(scrollPane);
        return panel;
    }

    private JPanel initDetailPanel() {
        JPanel panel = new JPanel();


        return panel;
    }

    private void updatePatientenList() {
        String[] elements = {"test", "test2"};
        patientenListe.setListData(elements);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "close": {
                final int answer = JOptionPane.showConfirmDialog(this, "Programm wirklich beenden?");

                if(answer==JOptionPane.YES_OPTION) {
                    this.setVisible(false);
                    this.dispose();
                    System.exit(0);
                }
                break;
            }
            case "about": {
                JOptionPane.showMessageDialog(this,
                        "Name     : Laith Alkorom\nMatrikel : s0566748",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }
}
