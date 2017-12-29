package model;

import java.util.Date;

/**
 * Created by jonasdegauquier on 14/11/17.
 */
public class User {
    private Integer userId;
    private String voornaam;
    private String achternaam;
    private Date geboortedatum;
    private String email;
    private Adress adress;
    private Rol rol;
    private static Integer selectedId;
    private static Integer currentUser;

    public  User() {}

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String voornaam, String achternaam, Date geboortedatum, String email, Adress adress, Rol rol) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.email = email;
        this.adress = adress;
        this.rol = rol;
    }

    public static Integer getSelectedId() {
        return selectedId;
    }

    public static void setSelectedId(Integer selectedId) {
        User.selectedId = selectedId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public static Integer getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Integer currentUser) {
        User.currentUser = currentUser;
    }
}