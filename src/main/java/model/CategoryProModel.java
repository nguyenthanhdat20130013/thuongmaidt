package model;

public class CategoryProModel {
    private int id;
    private String name;
    private int numbOfPro;

    public CategoryProModel(int id, String name, int numbOfPro) {
        this.id = id;
        this.name = name;
        this.numbOfPro = numbOfPro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbOfPro() {
        return numbOfPro;
    }

    public void setNumbOfPro(int numbOfPro) {
        this.numbOfPro = numbOfPro;
    }
}
