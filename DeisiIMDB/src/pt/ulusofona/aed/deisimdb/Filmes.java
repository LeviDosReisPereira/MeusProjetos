package pt.ulusofona.aed.deisimdb;

public class Filmes {
    int movieId;
    String movieName;
    int movieDuration;
    int movieBudget;
    String[] movieReleaseDate;
    int numAtores; // Novo atributo

    public Filmes(int movieId, String movieName, int movieDuration, int movieBudget, String movieReleaseDate, int numAtores) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieBudget = movieBudget;
        this.movieReleaseDate = movieReleaseDate.split("-");
        this.numAtores = numAtores;
    }

    public int procuraMovieId() {
        return movieId;
    }
    public void mudaNumeroAtor(int atores) {
        this.numAtores = atores;
    }

    @Override
    public String toString() {
        String resultado = movieId + " | " + movieName + " | " + movieReleaseDate[2] + "-" + movieReleaseDate[1] + "-" + movieReleaseDate[0];

        if (movieId < 1000) {
            resultado += " | " + numAtores;
        }

        return resultado;
    }
}
