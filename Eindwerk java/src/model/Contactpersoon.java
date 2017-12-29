package model;

public class Contactpersoon {
    private Integer id;
    private Bewoner bewoner;
    private Adress adress;
    private String voornaam;
    private String achternaam;
    private String identiteitskaartnr;
    private Integer telefoon;
    private String email;
    private String relatie;

    public Contactpersoon() {
    }

    public Contactpersoon(Bewoner bewoner, Adress adress, String achternaam, String voornaam, Integer telefoon, String email, String relatie, String identiteitskaartnr) {
        this.bewoner = bewoner;
        this.adress = adress;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.relatie = relatie;
        this.identiteitskaartnr = identiteitskaartnr;
    }

    public Contactpersoon(Adress adress, String achternaam, String voornaam, Integer telefoon, String email, String relatie , String identiteitskaartnr) {
        this.adress = adress;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.relatie = relatie;
        this.identiteitskaartnr = identiteitskaartnr;
    }

    public Contactpersoon(Integer id, Bewoner bewoner, Adress adress, String achternaam, String voornaam, Integer telefoon, String email, String relatie, String identiteitskaartnr) {
        this.id = id;
        this.bewoner = bewoner;
        this.adress = adress;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.telefoon = telefoon;
        this.email = email;
        this.relatie = relatie;
        this.identiteitskaartnr = identiteitskaartnr;
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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getIdentiteitskaartnr() {
        return identiteitskaartnr;
    }

    public void setIdentiteitskaartnr(String identiteitskaartnr) {
        this.identiteitskaartnr = identiteitskaartnr;
    }

    public Integer getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(Integer telefoon) {
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelatie() {
        return relatie;
    }

    public void setRelatie(String relatie) {
        this.relatie = relatie;
    }
}

