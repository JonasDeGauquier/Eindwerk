package model;

public class Medicatie {
    private Integer id;
    private  String medicatie;
    private static Integer selectedId;

    public Medicatie() {
    }

    public Medicatie(Integer id, String medicatie) {
        this.id = id;
        this.medicatie = medicatie;
    }

    public Medicatie(String medicatie) {
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

    public static Integer getSelectedId() {
        return selectedId;
    }
    public static void setSelectedId(Integer selectedId) {
        Medicatie.selectedId = selectedId;
    }

    @Override
    public String toString() {
        return medicatie ;
    }
}
