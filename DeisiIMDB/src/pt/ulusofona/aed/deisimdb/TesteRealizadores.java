package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TesteRealizadores {
    @Test
    public void testCriacaoDiretor() {
        Diretores diretor = new Diretores(1, "Steven Spielberg", 101);
        assertNotNull(diretor);
    }

    @Test
    public void testAtributosDiretor() {
        Diretores diretor = new Diretores(2, "Christopher Nolan", 202);
        assertEquals(2, diretor.directorId);
        assertEquals("Christopher Nolan", diretor.directorName);
        assertEquals(202, diretor.movieId);
    }

    @Test
    public void testToStringDiretor() {
        Diretores diretor = new Diretores(3, "Quentin Tarantino", 303);
        assertEquals("3 | Quentin Tarantino | 303", diretor.toString());
    }

    @Test
    public void testDiretorComNomeVazio() {
        Diretores diretor = new Diretores(4, "", 404);
        assertEquals("4 |  | 404", diretor.toString());
    }

    @Test
    public void testDiretorComIdNegativo() {
        Diretores diretor = new Diretores(-5, "Martin Scorsese", 505);
        assertEquals("-5 | Martin Scorsese | 505", diretor.toString());
    }
}
