package pt.ulusofona.aed.deisimdb;

public class InputInvalido {
    String fileName;
    int linhasCorretas;
    int linhasErradas;
    int primeiraLinhaErrada;

    public InputInvalido(String fileName, int linhasCorretas, int linhasErradas, int primeiraLinhaErrada) {
        this.fileName = fileName;
        this.linhasCorretas = linhasCorretas;
        this.linhasErradas = linhasErradas;
        this.primeiraLinhaErrada = primeiraLinhaErrada;
    }
    public String toString() {
        return fileName + " | "+ linhasCorretas + " | " + linhasErradas + " | " + primeiraLinhaErrada;
    }
}
