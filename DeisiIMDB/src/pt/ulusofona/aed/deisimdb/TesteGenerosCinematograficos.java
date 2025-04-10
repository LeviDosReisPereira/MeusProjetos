package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TesteGenerosCinematograficos {
    @Test
    public void testCriacaoGenero() {
        Generos genero = new Generos(1, "Ação");
        assertNotNull(genero);
        assertEquals(1, genero.generoID);
        assertEquals("Ação", genero.generoNome);
    }

    @Test
    public void testGeneroIdNegativo() {
        Generos genero = new Generos(-1, "Terror");
        assertEquals(-1, genero.generoID);
    }

    @Test
    public void testGeneroNomeVazio() {
        Generos genero = new Generos(2, "");
        assertEquals("", genero.generoNome);
    }

    @Test
    public void testToStringFormatoCorreto() {
        Generos genero = new Generos(3, "Comédia");
        assertEquals("3 | Comédia", genero.toString());
    }

    @Test
    public void testGeneroComEspacos() {
        Generos genero = new Generos(4, "   Drama   ");
        assertEquals("   Drama   ", genero.generoNome);
    }
}

