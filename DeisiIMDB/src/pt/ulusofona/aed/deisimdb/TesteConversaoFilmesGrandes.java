package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteConversaoFilmesGrandes {

    @Test
    public void conversao_string_filme() {
        Filmes filme1 = new Filmes(56429, "Up from the Depths", 85, 0, "01-06-1979", 0);
        String resultadoEsperado = "56429 | Up from the Depths | 1979-06-01";
        String resultadoAtual = filme1.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_erro() {
        Filmes filme2 = new Filmes(56429, "Up from the Depths", 85, 0, "01-06-1979", 0);
        String resultadoEsperado = "56429 | Up from the Depths | 1979-06-01";
        String resultadoAtual = filme2.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_string_filme_recente() {
        Filmes filme3 = new Filmes(99999, "Oppenheimer", 180, 1, "21-07-2023", 0);
        String resultadoEsperado = "99999 | Oppenheimer | 2023-07-21";
        String resultadoAtual = filme3.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_filme_sem_titulo() {
        Filmes filme4 = new Filmes(12345, "", 120, 1, "15-03-2000", 0);
        String resultadoEsperado = "12345 |  | 2000-03-15";
        String resultadoAtual = filme4.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void conversao_string_filme_classico() {
        Filmes filme5 = new Filmes(1234, "Casablanca", 102, 1, "26-11-1942", 0);
        String resultadoEsperado = "1234 | Casablanca | 1942-11-26"; // Agora está correto, sem numAtores
        String resultadoAtual = filme5.toString();
        Assertions.assertEquals(resultadoEsperado, resultadoAtual);
    }
}
