package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteConversaoFilmesPequenos {

    @Test
    public void conversao() {
        Filmes filme = new Filmes(713, "The Piano", 121, 7000000, "19-05-1993", 3);
        String resultadoEsperado = "713 | The Piano | 1993-05-19 | 3"; // Inclui numAtores porque 713 < 1000
        String resultadoAtual = filme.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_string_semNome() {
        Filmes filme = new Filmes(713, ".", 121, 7000000, "19-05-1993", 2);
        String resultadoEsperado = "713 | . | 1993-05-19 | 2"; // Inclui numAtores porque 713 < 1000
        String resultadoAtual = filme.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_filme_tituloNumero() {
        Filmes filme = new Filmes(850, "1984", 110, 500000, "07-12-1984", 4);
        String resultadoEsperado = "850 | 1984 | 1984-12-07 | 4"; // Inclui numAtores porque 850 < 1000
        String resultadoAtual = filme.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_sem_titulo() {
        Filmes filme = new Filmes(753, "Faces", 130, 275000, "24-11-1968", 5);
        String resultadoEsperado = "753 | Faces | 1968-11-24 | 5"; // Inclui numAtores porque 753 < 1000
        String resultadoAtual = filme.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_filme_titulo_estranho() {
        Filmes filme = new Filmes(555, "L'étranger", 120, 2000000, "15-07-1954", 6);
        String resultadoEsperado = "555 | L'étranger | 1954-07-15 | 6"; // Inclui numAtores porque 555 < 1000
        String resultadoAtual = filme.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }
}