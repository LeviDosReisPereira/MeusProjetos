package pt.ulusofona.aed.deisimdb;
import java.util.Objects;

public class Atores {
    int actorId;
    String actorName;
    String actorGender;
    int movieId;

    public Atores(int actorId, String actorName, String actorGender, int movieId) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.actorGender = actorGender;
        this.movieId = movieId;
    }

    public int getAtores() {
        return actorId;
    }
    public int retornaFilme() {
        return movieId;
    }

    @Override
    public String toString() {
        return actorId+ " | " + actorName + " | " + (Objects.equals(actorGender, "F") ? "Feminino" : "Masculino") + " | " + movieId;

    }
}
