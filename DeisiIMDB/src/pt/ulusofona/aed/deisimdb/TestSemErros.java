package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;

public class TestSemErros {
    private static final String TEST_FOLDER = "test-files";

    @Test
    void testParseFiles_Sucesso() {
        File pastaFicheiros = new File(TEST_FOLDER);
        assertTrue(Main.parseFiles(pastaFicheiros), "Erro ao processar os arquivos");
    }

    @Test
    void testGetObjects_Filmes() {
        File pastaFicheiros = new File(TEST_FOLDER);
        Main.parseFiles(pastaFicheiros);
        ArrayList<Filmes> filmes = Main.getObjects(TipoEntidade.FILME);
        System.out.println(filmes);
        assertFalse(filmes.isEmpty(), "Nenhum filme foi carregado");
    }

    @Test
    void testGetObjects_Atores() {
        File pastaFicheiros = new File(TEST_FOLDER);
        Main.parseFiles(pastaFicheiros);
        ArrayList<Atores> atores = Main.getObjects(TipoEntidade.ATOR);
        assertFalse(atores.isEmpty(), "Nenhum ator foi carregado");
    }

    @Test
    void testGetObjects_Diretores() {
        File pastaFicheiros = new File(TEST_FOLDER);
        Main.parseFiles(pastaFicheiros);
        ArrayList<Diretores> diretores = Main.getObjects(TipoEntidade.REALIZADOR);
        System.out.println(diretores);
        assertFalse(diretores.isEmpty(), "Nenhum diretor foi carregado");
    }

    @Test
    void testGetObjects_Votos() {
        File pastaFicheiros = new File(TEST_FOLDER);
        Main.parseFiles(pastaFicheiros);
        ArrayList<FilmeVotos> votos = Main.getObjects(TipoEntidade.VOTOS);
        assertFalse(votos.isEmpty(), "Nenhum voto foi carregado");
    }
}

