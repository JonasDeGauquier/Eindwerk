package model;

public class Medicatie {
    private Integer id;
    private  String medicatie;

    public Medicatie() {
    }

    public Medicatie(Integer id, String medicatie) {
        this.id = id;
        this.medicatie = medicatie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicatie() {
        return medicatie;
    }

    public void setMedicatie(String medicatie) {
        this.medicatie = medicatie;
    }

    @Override
    public String toString() {
        return medicatie ;
    }
}
