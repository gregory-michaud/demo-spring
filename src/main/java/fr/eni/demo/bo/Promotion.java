package fr.eni.demo.bo;

import java.util.Objects;

public class Promotion {

    private String code;
    private Filiere filiere;

    public Promotion(){

    }

    public Promotion(String code, Filiere filiere) {
        this.code = code;
        this.filiere = filiere;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "code='" + code + '\'' +
                ", filiere=" + filiere +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equals(code, promotion.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
