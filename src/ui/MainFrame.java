package ui;

import data.Aufenthalt;
import data.Patient;
import ui.listmodels.AufenthaltData;
import ui.listmodels.PatientenData;
import data.Patientenverwaltung;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainFrame extends JFrame implements ActionListener {

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    Patientenverwaltung pv;
    JList<Patient> patientenJList;
    PatientenData patientenData;

    Patient selectedPatient;
    JList<Aufenthalt> aufenthaltJList;
    AufenthaltData aufenthaltData;
    JLabel nameLabel;
    JLabel patientenNummerLabel;
    JTextField aufnahmeTextField;
    JTextField entlassenTextField;
    JButton aufnehmenButton;
    JButton entlassenButton;

    public MainFrame() throws HeadlessException {
        super("Patientenverwaltung");

        pv = new Patientenverwaltung();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	Klick	auf	x

        this.setSize(800,500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        initMenuBar();
        initContentPane();

        this.setVisible(true);
    }

    /**
     * inizialisiert menu
     */
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

        JLabel label = new JLabel("Patienten");
        label.setFont(new Font("sans serif", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label);

        patientenData = new PatientenData(this);
        patientenJList = new JList<>(patientenData);

        patientenJList.setLayoutOrientation(JList.VERTICAL);
        patientenJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientenJList.setVisibleRowCount(-1);

        /*
         * on select patient in the list
         */
        patientenJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedPatient = patientenJList.getSelectedValue();
                    updateDetailPanel();
                }
            }
        });

        /*
        wie ein patient in der liste angezeigt werden soll..
         */
        patientenJList.setCellRenderer(new ListCellRenderer<Patient>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Patient> list, Patient value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel thisLabel = new JLabel();
                thisLabel.setText(value.getName() + " " + value.getNachname());
                thisLabel.setOpaque(true);
                thisLabel.setBounds(0, 0, list.getWidth(), 32);
                if (isSelected) {
                    thisLabel.setBackground(list.getSelectionBackground());
                    thisLabel.setForeground(list.getSelectionForeground());
                } else {
                    thisLabel.setBackground(list.getBackground());
                    thisLabel.setForeground(list.getForeground());
                }
                return thisLabel;
            }
        });

        updatePatientenList();
        JScrollPane scrollPane = new JScrollPane(patientenJList);
        scrollPane.setLocation(8, 8);
        scrollPane.setPreferredSize(new Dimension(this.getWidth() / 2 - 16, this.getHeight() - 16 - 88));
        panel.add(scrollPane);
        return panel;
    }

    /**
     * in deiser mehtode wird das detail panel erstellt ..
     * @return jpanel mit der datei ansicht zu einem patienten mit seinem aufenthalten ,
     */
    private JPanel initDetailPanel() {
        JPanel panel = new JPanel();
        panel.setLocation(8, 8);
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(8, 8, 8, 8);

        modifyGridBagConstraints(gbc, 0, 0, 1, 1, 1, 1);
        JLabel label = new JLabel("Aufenthalte");
        label.setFont(new Font("sans serif", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label);

        modifyGridBagConstraints(gbc, 0, 10, 2, 1, 1, 7);
        this.aufenthaltData = new AufenthaltData(this);
        aufenthaltJList = new JList<>(this.aufenthaltData);
        aufenthaltJList.setLayoutOrientation(JList.VERTICAL);
        aufenthaltJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        aufenthaltJList.setVisibleRowCount(-1);
        aufenthaltJList.setCellRenderer(new ListCellRenderer<Aufenthalt>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Aufenthalt> list, Aufenthalt value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel thisLabel = new JLabel();
                String from = (value.getFrom() != null)? dateFormatter.format(value.getFrom()) : "";
                String to = (value.getTo() != null)? dateFormatter.format(value.getTo()) : "";
                thisLabel.setText(from + " - " + to);
                thisLabel.setOpaque(true);
                thisLabel.setBounds(0, 0, list.getWidth(), 32);
                if (isSelected) {
                    thisLabel.setBackground(list.getSelectionBackground());
                    thisLabel.setForeground(list.getSelectionForeground());
                } else {
                    thisLabel.setBackground(list.getBackground());
                    thisLabel.setForeground(list.getForeground());
                }
                return thisLabel;
            }
        });
        JScrollPane scrollPane = new JScrollPane(aufenthaltJList);
        scrollPane.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight() / 3));
        panel.add(scrollPane, gbc);

        Font boldFont = new Font("sans serif", Font.BOLD, 14);

        modifyGridBagConstraints(gbc, 0, 20, 1, 1, .5f, 1);
        JLabel label1 = new JLabel("Name");
        label1.setFont(boldFont);
        panel.add(label1, gbc);

        modifyGridBagConstraints(gbc, 1, 20, 1, 1, .5f, 1);
        nameLabel = new JLabel();
        nameLabel.setFont(boldFont);
        panel.add(nameLabel, gbc);

        modifyGridBagConstraints(gbc, 0, 30, 1, 1, .5f, 1);
        JLabel label2 = new JLabel("Patientennummer");
        label2.setFont(boldFont);
        panel.add(label2, gbc);

        modifyGridBagConstraints(gbc, 1, 30, 1, 1, .5f, 1);
        patientenNummerLabel = new JLabel();
        patientenNummerLabel.setFont(boldFont);
        panel.add(patientenNummerLabel, gbc);

        modifyGridBagConstraints(gbc, 0, 40, 1, 1, .5f, 1);
        JLabel label3 = new JLabel("Aufnahme-Datum");
        label3.setFont(boldFont);
        panel.add(label3, gbc);

        modifyGridBagConstraints(gbc, 1, 40, 1, 1, .5f, 1);
        aufnahmeTextField = new JTextField();
        aufnahmeTextField.setEnabled(false);
        panel.add(aufnahmeTextField, gbc);

        modifyGridBagConstraints(gbc, 0, 50, 1, 1, .5f, 1);
        JLabel label4 = new JLabel("Entlassen-Datum");
        label3.setFont(boldFont);
        panel.add(label4, gbc);

        modifyGridBagConstraints(gbc, 1, 50, 1, 1, .5f, 1);
        entlassenTextField = new JTextField();
        entlassenTextField.setEnabled(false);
        panel.add(entlassenTextField, gbc);

        gbc.fill = GridBagConstraints.NONE;
        modifyGridBagConstraints(gbc, 0, 60, 1, 1, .5f, 2);
        aufnehmenButton = new JButton("Aufnehmen");
        aufnehmenButton.setActionCommand("aufnehmen");
        aufnehmenButton.addActionListener(this);
        aufnehmenButton.setEnabled(false);
        panel.add(aufnehmenButton, gbc);

        modifyGridBagConstraints(gbc, 1, 60, 1, 1, .5f, 2);
        entlassenButton = new JButton("Entlassen");
        entlassenButton.setActionCommand("entlassen");
        entlassenButton.addActionListener(this);
        entlassenButton.setEnabled(false);
        panel.add(entlassenButton, gbc);

        panel.setPreferredSize(new Dimension(this.getWidth() / 2 - 16, this.getHeight() - 16 - 50));
        return panel;
    }

    /**
     * mit dieser methode wird die patienten liste aktualisiert
     */
    private void updatePatientenList() {
        this.patientenData.setPatientenNamen(this.pv.kh.getPatienten());
        this.patientenJList.updateUI();
    }

    /**
     * hier wird das detail panel mit den daten des derzeit ausgewählten patienten ausgefüllt.
     */
    private void updateDetailPanel() {
        if (selectedPatient == null) {
            return;
        }

        Patient p = selectedPatient;
        if (!this.pv.aufenthalte.containsKey(p.getPatientennummer())) {
            this.pv.aufenthalte.put(p.getPatientennummer(), new ArrayList<>());
        }
        ArrayList<Aufenthalt> aufenthalte = this.pv.aufenthalte.get(p.getPatientennummer());

        this.aufenthaltData.setAufenthalte(aufenthalte);

        this.nameLabel.setText(p.getName() + " " + p.getNachname());
        this.patientenNummerLabel.setText(p.getPatientennummer());

        if (aufenthalte.isEmpty() || !aufenthalte.get(aufenthalte.size() - 1).canCheckOut())  {
            this.aufnehmenButton.setEnabled(true);
            this.entlassenButton.setEnabled(false);
            this.aufnahmeTextField.setText(dateFormatter.format(new Date()));
            this.aufnahmeTextField.setEnabled(true);
            this.entlassenTextField.setText("");
            this.entlassenTextField.setEnabled(false);
        }
        else {
            this.aufnehmenButton.setEnabled(false);
            this.entlassenButton.setEnabled(true);
            this.aufnahmeTextField.setText("");
            this.aufnahmeTextField.setEnabled(false);
            this.entlassenTextField.setText(dateFormatter.format(new Date()));
            this.entlassenTextField.setEnabled(true);
            if (!aufenthalte.isEmpty()) {
                Aufenthalt a = aufenthalte.get(aufenthalte.size() - 1);
                this.aufnahmeTextField.setText(dateFormatter.format(a.getFrom()));
            }
        }
        this.aufenthaltJList.updateUI();
        this.aufenthaltJList.setSelectedIndex(-1);
    }

    private void modifyGridBagConstraints(GridBagConstraints gbc, int x, int y, int width, int height, float weightx, float weighty) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }

    /**
     * action lisner des mainFrames.
     * @param e
     */
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
            case "aufnehmen": {
                try {
                    Date d = dateFormatter.parse(this.aufnahmeTextField.getText());
                    Aufenthalt a = new Aufenthalt(selectedPatient);
                    a.checkIn(d);
                    this.pv.aufenthalte.get(selectedPatient.getPatientennummer()).add(a);
                    updateDetailPanel();
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(this,
                            "Datum konnte nicht interpretiert werden.\nVersuche das Datum in folgendem Format einzugeben: dd.MM.yyyy",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case "entlassen": {
                try {
                    Date d = dateFormatter.parse(this.entlassenTextField.getText());
                    Aufenthalt a = pv.aufenthalte.get(selectedPatient.getPatientennummer()).get(pv.aufenthalte.get(selectedPatient.getPatientennummer()).size() - 1);
                    a.checkOut(d);
                    updateDetailPanel();
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(this,
                            "Datum konnte nicht interpretiert werden.\nVersuche das Datum in folgendem Format einzugeben: dd.MM.yyyy",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            default: {
                System.out.println("Not known command " + action);
            }
        }
    }
}
