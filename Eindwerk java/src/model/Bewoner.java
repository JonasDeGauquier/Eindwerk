package model;

import org.postgresql.util.PGbytea;

import java.io.File;
import java.util.Date;

public class Bewoner {
    private Integer id;
    private String voornaam;
    private String achternaam;
    private Date geboortedatum;
    private String geboorteplaats;
    private String geslacht;
    private String burgerlijkestaat;
    private String gekoppeldMet;
    private Date opnamedatum;
    private String geloofsovertuiging;
    private String peter;
    private String meter;
    private String nationaliteit;
    private Long rijksregisternr;
    private String indetiteitskaartnr;
    private String huisdokter;
    private String voorkeurZiekenhuis;
    private Integer kamernr;
    private Adress adress;
    private static Integer selectedId;
    private byte[] foto;

    public Bewoner(){
    }

    public Bewoner(Integer id) {
        this.id = id;
    }

    public Bewoner(String voornaam, String achternaam) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    public Bewoner(Integer id, String voornaam, String achternaam){
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    public Bewoner(String voornaam, String achternaam, Date geboortedatum, String geboorteplaats, String geslacht, String burgerlijkestaat, String gekoppeldMet, Date opnamedatum, String geloofsovertuiging, String peter, String meter, String nationaliteit, Long rijksregisternr, String indetiteitskaartnr, String huisdokter, String voorkeurZiekenhuis, Integer kamernr, Adress adress, byte[] foto) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.geboorteplaats = geboorteplaats;
        this.geslacht = geslacht;
        this.burgerlijkestaat = burgerlijkestaat;
        this.gekoppeldMet = gekoppeldMet;
        this.opnamedatum = opnamedatum;
        this.geloofsovertuiging = geloofsovertuiging;
        this.peter = peter;
        this.meter = meter;
        this.nationaliteit = nationaliteit;
        this.rijksregisternr = rijksregisternr;
        this.indetiteitskaartnr = indetiteitskaartnr;
        this.huisdokter = huisdokter;
        this.voorkeurZiekenhuis = voorkeurZiekenhuis;
        this.kamernr = kamernr;
        this.adress = adress;
        this.foto = foto;
    }

    public Bewoner(Integer id, String voornaam, String achternaam, Date geboortedatum, String geboorteplaats, String geslacht, String burgerlijkestaat, String gekoppeldMet, Date opnamedatum, String geloofsovertuiging, String peter, String meter, String nationaliteit, Long rijksregisternr, String indetiteitskaartnr, String huisdokter, String voorkeurZiekenhuis, Integer kamernr, Adress adress, byte[] foto) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.geboorteplaats = geboorteplaats;
        this.geslacht = geslacht;
        this.burgerlijkestaat = burgerlijkestaat;
        this.gekoppeldMet = gekoppeldMet;
        this.opnamedatum = opnamedatum;
        this.geloofsovertuiging = geloofsovertuiging;
        this.peter = peter;
        this.meter = meter;
        this.nationaliteit = nationaliteit;
        this.rijksregisternr = rijksregisternr;
        this.indetiteitskaartnr = indetiteitskaartnr;
        this.huisdokter = huisdokter;
        this.voorkeurZiekenhuis = voorkeurZiekenhuis;
        this.kamernr = kamernr;
        this.adress = adress;
        this.foto = foto;
    }

    public static Integer getSelectedId() {
        return selectedId;
    }

    public static void setSelectedId(Integer selectedId) {
        Bewoner.selectedId = selectedId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getGeboorteplaats() {
        return geboorteplaats;
    }

    public void setGeboorteplaats(String geboorteplaats) {
        this.geboorteplaats = geboorteplaats;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getBurgerlijkestaat() {
        return burgerlijkestaat;
    }

    public void setBurgerlijkestaat(String burgerlijkestaat) {
        this.burgerlijkestaat = burgerlijkestaat;
    }

    public String getGekoppeldMet() {
        return gekoppeldMet;
    }

    public void setGekoppeldMet(String gekoppeldMet) {
        this.gekoppeldMet = gekoppeldMet;
    }

    public Date getOpnamedatum() {
        return opnamedatum;
    }

    public void setOpnamedatum(Date opnamedatum) {
        this.opnamedatum = opnamedatum;
    }

    public String getGeloofsovertuiging() {
        return geloofsovertuiging;
    }

    public void setGeloofsovertuiging(String geloofsovertuiging) {
        this.geloofsovertuiging = geloofsovertuiging;
    }

    public String getPeter() {
        return peter;
    }

    public void setPeter(String peter) {
        this.peter = peter;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getNationaliteit() {
        return nationaliteit;
    }

    public void setNationaliteit(String nationaliteit) {
        this.nationaliteit = nationaliteit;
    }

    public Long getRijksregisternr() {
        return rijksregisternr;
    }

    public void setRijksregisternr(Long rijksregisternr) {
        this.rijksregisternr = rijksregisternr;
    }

    public String getIndetiteitskaartnr() {
        return indetiteitskaartnr;
    }

    public void setIndetiteitskaartnr(String indetiteitskaartnr) {
        this.indetiteitskaartnr = indetiteitskaartnr;
    }

    public String getHuisdokter() {
        return huisdokter;
    }

    public void setHuisdokter(String huisdokter) {
        this.huisdokter = huisdokter;
    }

    public String getVoorkeurZiekenhuis() {
        return voorkeurZiekenhuis;
    }

    public void setVoorkeurZiekenhuis(String voorkeurZiekenhuis) {
        this.voorkeurZiekenhuis = voorkeurZiekenhuis;
    }

    public Integer getKamernr() {
        return kamernr;
    }

    public void setKamernr(Integer kamernr) {
        this.kamernr = kamernr;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return voornaam  + " " + achternaam ;
    }
}
