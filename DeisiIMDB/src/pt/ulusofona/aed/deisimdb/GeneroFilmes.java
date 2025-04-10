package pt.ulusofona.aed.deisimdb;


public class GeneroFilmes {
    int gen;
    int movieID;

    public GeneroFilmes(int gen, int movieID) {
        this.gen = gen;
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return  gen + " : " + movieID ;
    }
}