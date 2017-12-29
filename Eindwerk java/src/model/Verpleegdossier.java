package model;

import java.sql.Timestamp;

public class Verpleegdossier {
    private Integer id;
    private Bewoner bewoner;
    private String wondzorg;
    private String bloedafname;
    private Boolean suikerziekte;
    private String beroepVroeger;
    private String specifiekeWensen;
    private Timestamp timestamp;

    public Verpleegdossier( ) {
    }

    public Verpleegdossier( String wondzorg, String bloedafname, Boolean suikerziekte, String beroepVroeger, String specifiekeWensen) {
        this.wondzorg = wondzorg;
        this.bloedafname = bloedafname;
        this.suikerziekte = suikerziekte;
        this.beroepVroeger = beroepVroeger;
        this.specifiekeWensen = specifiekeWensen;
    }

    public Verpleegdossier( Integer id, String wondzorg, String bloedafname, Boolean suikerziekte, String beroepVroeger, String specifiekeWensen) {
        this.id = id;
        this.wondzorg = wondzorg;
        this.bloedafname = bloedafname;
        this.suikerziekte = suikerziekte;
        this.beroepVroeger = beroepVroeger;
        this.specifiekeWensen = specifiekeWensen;
    }

    public Verpleegdossier(Integer id, Bewoner bewoner, String wondzorg, String bloedafname, Boolean suikerziekte, String beroepVroeger, String specifiekeWensen, Timestamp timestamp) {
        this.id = id;
        this.bewoner = bewoner;
        this.wondzorg = wondzorg;
        this.bloedafname = bloedafname;
        this.suikerziekte = suikerziekte;
        this.beroepVroeger = beroepVroeger;
        this.specifiekeWensen = specifiekeWensen;
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

    public String getWondzorg() {
        return wondzorg;
    }

    public void setWondzorg(String wondzorg) {
        this.wondzorg = wondzorg;
    }

    public String getBloedafname() {
        return bloedafname;
    }

    public void setBloedafname(String bloedafname) {
        this.bloedafname = bloedafname;
    }

    public Boolean getSuikerziekte() {
        return suikerziekte;
    }

    public void setSuikerziekte(Boolean suikerziekte) {
        this.suikerziekte = suikerziekte;
    }

    public String getBeroepVroeger() {
        return beroepVroeger;
    }

    public void setBeroepVroeger(String beroepVroeger) {
        this.beroepVroeger = beroepVroeger;
    }

    public String getSpecifiekeWensen() {
        return specifiekeWensen;
    }

    public void setSpecifiekeWensen(String specifiekeWensen) {
        this.specifiekeWensen = specifiekeWensen;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
