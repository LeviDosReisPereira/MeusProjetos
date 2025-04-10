package pt.ulusofona.aed.deisimdb;

public class Diretores {
    int directorId;
    String directorName;
    int movieId;

    public Diretores(int directorId, String directorName, int movieId) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return  directorId + " | "+directorName + " | " + movieId ;

    }
}
