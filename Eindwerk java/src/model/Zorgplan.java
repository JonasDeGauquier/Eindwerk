package model;

import java.sql.Timestamp;

public class Zorgplan {
    private Integer id;
    private Medicatie medicatie;
    private Zorgtaak zorgtaak;
    private User user;
    private Bewoner bewoner;
    private String opmerking;
    private Timestamp timestamp;

    public Zorgplan( Medicatie medicatie, Zorgtaak zorgtaak, User user, String opmerking , Bewoner bewoner) {
        this.medicatie = medicatie;
        this.zorgtaak = zorgtaak;
        this.user = user;
        this.bewoner = bewoner;
        this.opmerking = opmerking;
    }

    public Zorgplan(Integer id, Medicatie medicatie, Zorgtaak zorgtaak, User user, Bewoner bewoner, String opmerking, Timestamp timestamp) {
        this.id = id;
        this.medicatie = medicatie;
        this.zorgtaak = zorgtaak;
        this.user = user;
        this.bewoner = bewoner;
        this.opmerking = opmerking;
        this.timestamp = timestamp;
    }

    public Zorgplan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medicatie getMedicatie() {
        return medicatie;
    }

    public void setMedicatie(Medicatie medicatie) {
        this.medicatie = medicatie;
    }

    public Zorgtaak getZorgtaak() {
        return zorgtaak;
    }

    public void setZorgtaak(Zorgtaak zorgtaak) {
        this.zorgtaak = zorgtaak;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bewoner getBewoner() {
        return bewoner;
    }

    public void setBewoner(Bewoner bewoner) {
        this.bewoner = bewoner;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
