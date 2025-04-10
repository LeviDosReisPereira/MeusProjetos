package pt.ulusofona.aed.deisimdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String[] ARQUIVOS_ESPERADOS = {"movies.csv", "actors.csv", "directors.csv", "genres.csv", "genres_movies.csv", "movie_votes.csv"};

    static ArrayList<Atores> atores = new ArrayList<>();
    static ArrayList<Filmes> filmes = new ArrayList<>();
    static ArrayList<Generos> generos = new ArrayList<>();
    static ArrayList<GeneroFilmes> generosFilmes = new ArrayList<>();
    static ArrayList<Diretores> diretores = new ArrayList<>();
    static ArrayList<FilmeVotos> filmesVotos = new ArrayList<>();

    static DadosDosFicheiros filmesArmazenaDados = new DadosDosFicheiros();
    static DadosDosFicheiros atoresArmazenaDados = new DadosDosFicheiros();
    static DadosDosFicheiros diretoresArmazenaDados = new DadosDosFicheiros();
    static DadosDosFicheiros generosArmazenaDados = new DadosDosFicheiros();
    static DadosDosFicheiros generoFilmesArmazenaDados = new DadosDosFicheiros();
    static DadosDosFicheiros filmesVotosArmazenaDados = new DadosDosFicheiros();

    private static int parseIntSafe(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return 1;
        }
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    static class DadosDosFicheiros {
        int linhasCorretas = 0;
        int linhasErradas = 0;
        int primeiraLinhaErrada = -1;
    }
    private static DadosDosFicheiros getStats(String fileName) {
        return switch (fileName) {
            case "movies.csv" -> filmesArmazenaDados;
            case "actors.csv" ->  atoresArmazenaDados;
            case "directors.csv" -> diretoresArmazenaDados;
            case "genres.csv" -> generosArmazenaDados;
            case "genres_movies.csv" -> generoFilmesArmazenaDados;
            case "movie_votes.csv" -> filmesVotosArmazenaDados;
            default -> new DadosDosFicheiros();
        };
    }
    static boolean parseFiles(File ficheiros) {
        atores.clear();
        filmes.clear();
        generos.clear();
        generosFilmes.clear();
        diretores.clear();
        filmesVotos.clear();

        filmesArmazenaDados = new DadosDosFicheiros();
        atoresArmazenaDados = new DadosDosFicheiros();
        diretoresArmazenaDados = new DadosDosFicheiros();
        generosArmazenaDados = new DadosDosFicheiros();
        generoFilmesArmazenaDados = new DadosDosFicheiros();
        filmesVotosArmazenaDados = new DadosDosFicheiros();

        for (String nomeArquivo : ARQUIVOS_ESPERADOS) {

            File ficheiro = new File(ficheiros, nomeArquivo);

            try (Scanner scanner = new Scanner(ficheiro)) {
                boolean primeiraLinha = true;
                int numeroLinha = 0;
                DadosDosFicheiros stats = getStats(nomeArquivo);

                while (scanner.hasNext()) {
                    String linha = scanner.nextLine().trim();
                    if (primeiraLinha) {
                        primeiraLinha = false;
                        continue;
                    }
                    numeroLinha++;
                    String[] partes = linha.split(",");
                    boolean linhaValida = false;

                    switch (nomeArquivo) {
                        case "movies.csv":
                            if (partes.length == 5 && !partes[0].isEmpty() && !partes[1].isEmpty() && !partes[2].isEmpty() && !partes[3].isEmpty() && !partes[4].isEmpty()) {

                                int movieId = 0;

                                try {
                                    movieId = Integer.parseInt(partes[0]);
                                }catch (NumberFormatException e) {
                                    linhaValida = false;
                                }

                                boolean repetido = false;

                                for (Filmes filmeRepetido : filmes) {
                                    if (filmeRepetido.procuraMovieId() == movieId) {
                                        linhaValida = true;
                                        repetido = true;
                                        break;
                                    }
                                }
                                if (!repetido){
                                    filmes.add(new Filmes(movieId, partes[1].trim(), parseIntSafe(partes[2]), parseIntSafe(partes[3]), partes[4].trim(), 0));
                                    linhaValida = true;
                                }
                            }
                            else {
                                linhaValida = false;
                            }
                            break;

                        case "actors.csv":
                            if (partes.length == 4 && !partes[0].isEmpty() && !partes[1].isEmpty() && !partes[2].isEmpty() && !partes[3].isEmpty()) {
                                int movieId = 0;
                                int actorId = 0;

                                try {
                                 actorId = Integer.parseInt(partes[0].trim());
                                 movieId = Integer.parseInt(partes[3].trim());
                                }catch (NumberFormatException e) {
                                    linhaValida = false;
                                }
                                int numAtores = 1;

                                if (linhaValida = true) {

                                    if (movieId<1000) {
                                        for (Atores atoresFilmes : atores) {
                                            if (atoresFilmes.retornaFilme() == movieId) {
                                                numAtores++;
                                            }
                                        }
                                        for (Filmes filmes1 : filmes) {
                                            if (filmes1.procuraMovieId() == movieId) {
                                                filmes1.mudaNumeroAtor(numAtores);
                                            }
                                        }
                                    }
                                    atores.add(new Atores(actorId, partes[1].trim(), partes[2].trim(), movieId));
                                    linhaValida = true;
                                }
                            }
                            break;

                        case "directors.csv":
                            if (partes.length == 3) {
                                diretores.add(new Diretores(parseIntSafe(partes[0]),partes[1].trim(), parseIntSafe(partes[2])));
                                linhaValida = true;
                            }
                            break;

                        case "genres.csv":
                            if (partes.length == 2) {
                                generos.add(new Generos(parseIntSafe(partes[0]), partes[1].trim()));
                                linhaValida = true;
                            }
                            break;

                        case "genres_movies.csv":
                            if (partes.length == 2) {
                                generosFilmes.add(new GeneroFilmes(parseIntSafe(partes[0]), parseIntSafe(partes[1])));
                                linhaValida = true;
                            }
                            break;

                        case "movie_votes.csv":
                            if (partes.length == 3) {
                                filmesVotos.add(new FilmeVotos(parseIntSafe(partes[0]), parseIntSafe(partes[1]), parseIntSafe(partes[2])));
                                linhaValida = true;
                            }
                            break;
                    }
                    if (linhaValida) {
                        stats.linhasCorretas++;
                    } else {
                        stats.linhasErradas++;
                        if (stats.primeiraLinhaErrada == -1) {
                            stats.primeiraLinhaErrada = numeroLinha;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Arquivo não encontrado: " + ficheiro.getAbsolutePath());
                return false;
            }
        }
        return true;
    }
    static ArrayList getObjects(TipoEntidade tipo) {
        ArrayList objetos = new ArrayList<>();

        switch (tipo) {
            case ATOR -> objetos = atores;
            case REALIZADOR -> objetos = diretores;
            case GENERO_CINEMATOGRAFICO -> objetos = generos;
            case FILME -> objetos = filmes;
            case VOTOS -> objetos =filmesVotos;
            case INPUT_INVALIDO -> {
                objetos.add(new InputInvalido("movies.csv", filmesArmazenaDados.linhasCorretas, filmesArmazenaDados.linhasErradas, filmesArmazenaDados.primeiraLinhaErrada));
                objetos.add(new InputInvalido("actors.csv", atoresArmazenaDados.linhasCorretas, atoresArmazenaDados.linhasErradas, atoresArmazenaDados.primeiraLinhaErrada));
                objetos.add(new InputInvalido("directors.csv", diretoresArmazenaDados.linhasCorretas, diretoresArmazenaDados.linhasErradas, diretoresArmazenaDados.primeiraLinhaErrada));
                objetos.add(new InputInvalido("genres.csv", generosArmazenaDados.linhasCorretas, generosArmazenaDados.linhasErradas, generosArmazenaDados.primeiraLinhaErrada));
                objetos.add(new InputInvalido("genres_movies.csv", generoFilmesArmazenaDados.linhasCorretas, generoFilmesArmazenaDados.linhasErradas, generoFilmesArmazenaDados.primeiraLinhaErrada));
                objetos.add(new InputInvalido("movie_votes.csv", filmesVotosArmazenaDados.linhasCorretas, filmesVotosArmazenaDados.linhasErradas, filmesVotosArmazenaDados.primeiraLinhaErrada));
            }
            default -> objetos.add("Entrada Inválida");
        }
        return objetos;
    }

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao deisiIMDB");
        long start = System.currentTimeMillis();

        boolean leuBem = Main.parseFiles(new File("test-files"));

        if (!leuBem) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }
        System.out.println(getObjects(TipoEntidade.INPUT_INVALIDO));

        long end = System.currentTimeMillis();
        System.out.println("Ficheiros lidos com sucesso em " + (end - start) + "ms");
    }
}
