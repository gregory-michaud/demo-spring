package fr.eni.demo.bo;

import java.util.Objects;

public class Filiere {

    private String id;

    private String nom;

    public Filiere(){

    }

    public Filiere(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Filiere{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Filiere filiere = (Filiere) o;
        return Objects.equals(id, filiere.id) && Objects.equals(nom, filiere.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
}
