package model;

/**
 * Created by jonasdegauquier on 14/11/17.
 */
public class Adress {
    private Integer id;
    private String straat;
    private int huisnr;
    private String gemeente;
    private Integer postcode;

    public Adress() { }

    public Adress(Integer id, String straat, int huisnr, String gemeente, Integer postcode) {
        this.id = id;
        this.straat = straat;
        this.huisnr = huisnr;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public Adress( String straat, int huisnr, String gemeente, Integer postcode) {
        this.straat = straat;
        this.huisnr = huisnr;
        this.gemeente = gemeente;
        this.postcode = postcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public int getHuisnr() {
        return huisnr;
    }

    public void setHuisnr(int huisnr) {
        this.huisnr = huisnr;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }
}
