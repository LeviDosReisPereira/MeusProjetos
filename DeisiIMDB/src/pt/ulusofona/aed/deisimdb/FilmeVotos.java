package pt.ulusofona.aed.deisimdb;

public class FilmeVotos {
    int movieId;
    double movieRating;
    int movieRatingCount;

    public FilmeVotos(int movieId, double movieRating, int movieRatingCount) {
        this.movieId = movieId;
        this.movieRating = movieRating;
        this.movieRatingCount = movieRatingCount;
    }

    @Override
    public String toString() {
        return  movieId + " | " + movieRating + " | " + movieRatingCount;
    }
}