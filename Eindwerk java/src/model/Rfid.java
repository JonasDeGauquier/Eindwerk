package model;

public class Rfid {
    private Integer rfidId;
    private Login login;
    private Integer rfid;

    public Rfid(Login login, Integer rfid) {
        this.login = login;
        this.rfid = rfid;
    }

    public Rfid(Integer rfidId, Login login, Integer rfid) {
        this.rfidId = rfidId;
        this.login = login;
        this.rfid = rfid;
    }

    public Integer getRfidId() {
        return rfidId;
    }

    public void setRfidId(Integer rfidId) {
        this.rfidId = rfidId;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }
}
