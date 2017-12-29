package model;

import java.sql.Timestamp;

public class BewonersDossier {
    private Integer id;
    private Bewoner bewoner;
    private Boolean incontinentie;
    private Boolean privacy;
    private Boolean reanimatieWens;
    private String groteOperaties;
    private String allergieën;
    private Timestamp timestamp;

    public BewonersDossier( ) {
    }

    public BewonersDossier( Boolean incontinentie, Boolean privacy, Boolean reanimatieWens, String groteOperaties, String allergieën) {
        this.incontinentie = incontinentie;
        this.privacy = privacy;
        this.reanimatieWens = reanimatieWens;
        this.groteOperaties = groteOperaties;
        this.allergieën = allergieën;
    }

    public BewonersDossier(Integer id,Boolean incontinentie, Boolean privacy, Boolean reanimatieWens, String groteOperaties, String allergieën) {
        this.id = id;
        this.incontinentie = incontinentie;
        this.privacy = privacy;
        this.reanimatieWens = reanimatieWens;
        this.groteOperaties = groteOperaties;
        this.allergieën = allergieën;
    }

    public BewonersDossier(Integer id, Bewoner bewoner, Boolean incontinentie, Boolean privacy, Boolean reanimatieWens, String groteOperaties, String allergieën, Timestamp timestamp) {
        this.id = id;
        this.bewoner = bewoner;
        this.incontinentie = incontinentie;
        this.privacy = privacy;
        this.reanimatieWens = reanimatieWens;
        this.groteOperaties = groteOperaties;
        this.allergieën = allergieën;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bewoner getBewoner() {
        return bewoner;
    }

    public void setBewoner(Bewoner bewoner) {
        this.bewoner = bewoner;
    }

    public Boolean getIncontinentie() {
        return incontinentie;
    }

    public void setIncontinentie(Boolean incontinentie) {
        this.incontinentie = incontinentie;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Boolean getReanimatieWens() {
        return reanimatieWens;
    }

    public void setReanimatieWens(Boolean reanimatieWens) {
        this.reanimatieWens = reanimatieWens;
    }

    public String getGroteOperaties() {
        return groteOperaties;
    }

    public void setGroteOperaties(String groteOperaties) {
        this.groteOperaties = groteOperaties;
    }

    public String getAllergieën() {
        return allergieën;
    }

    public void setAllergieën(String allergieën) {
        this.allergieën = allergieën;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
