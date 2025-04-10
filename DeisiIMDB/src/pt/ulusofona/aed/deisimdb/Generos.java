package pt.ulusofona.aed.deisimdb;

public class Generos {
    int generoID;
    String generoNome;

    public Generos(int generoID, String genero) {
        this.generoID = generoID;
        this.generoNome = genero;
    }

    @Override
    public String toString() {

        return generoID+" | "+generoNome;
    }
}