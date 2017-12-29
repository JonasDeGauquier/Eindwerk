package model;

public class Zorgtaak {
    private Integer id;
    private String zorgtaak;

    public Zorgtaak() {
    }


    public Zorgtaak(String zorgtaak) {
        this.zorgtaak = zorgtaak;
    }

    public Zorgtaak(Integer id, String zorgtaak) {
        this.id = id;
        this.zorgtaak = zorgtaak;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZorgtaak() {
        return zorgtaak;
    }

    public void setZorgtaak(String zorgtaak) {
        this.zorgtaak = zorgtaak;
    }

    @Override
    public String toString() {
        return zorgtaak;
    }
}
