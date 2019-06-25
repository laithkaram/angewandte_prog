package data;

import java.util.Date;

public class Aufenthalt {

    private Patient patient;
    private Date from;
    private Date to;

    public Aufenthalt(Patient patient) {
        this.patient = patient;
    }

    public void checkIn() {
        checkIn(new Date());
    }

    public void checkIn(Date from) {
        this.from = from;
        this.to = null;
    }

    public void checkOut() {
        checkOut(new Date());
    }

    public void checkOut(Date to) {
        if (this.from == null) {
            System.out.println("Patient ist noch nicht eingecheckt.");
            return;
        }
        else if (from.after(to)) {
            System.out.println("Patient kann nicht zu einem früheren Zeitpunkt ausgecheckt werden.");
            System.out.println("Eingecheckt: " + this.from.toString());
            return;

        }
        this.to = to;
    }

    public boolean canCheckOut() {
        return this.to == null;
    }

    public double getDays() {
        if (to == null) {
            return -1;
        }
        return (to.getTime() - from.getTime()) / (1000 * 60 * 60 * 24.);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
