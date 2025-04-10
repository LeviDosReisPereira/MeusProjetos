package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestComErros {

    @Test
    void testParseFiles_DirectoryNotFound() {
        File pastaInexistente = new File("teste/diretorioInexistente");
        assertFalse(Main.parseFiles(pastaInexistente), "Erro: Diretório inexistente não deveria ser processado");
    }

    @Test
    void testParseFiles_EmptyDirectory() {
        File pastaVazia = new File("teste/pastaVazia");
        assertFalse(Main.parseFiles(pastaVazia), "Erro: Diretório vazio não deveria ser processado");
    }

    @Test
    void testParseFiles_MissingFiles() {
        File pastaIncompleta = new File("teste/dadosIncompletos"); // Pasta com alguns arquivos ausentes
        assertFalse(Main.parseFiles(pastaIncompleta), "Erro: Diretório com arquivos faltando não deveria ser aceito");
    }

    @Test
    void testParseFiles_CorruptedFiles() {
        File pastaCorrompida = new File("teste/dadosCorrompidos"); // Pasta com arquivos mal formatados
        assertFalse(Main.parseFiles(pastaCorrompida), "Erro: Diretório com arquivos corrompidos não deveria ser aceito");
    }

    @Test
    void testGetObjects_InvalidInput() {
        File pastaErros = new File("teste/dadosComErros");
        Main.parseFiles(pastaErros);
        ArrayList<Object> objetosInvalidos = Main.getObjects(TipoEntidade.INPUT_INVALIDO);
        assertFalse(objetosInvalidos.isEmpty(), "Erro: Era esperado encontrar entradas inválidas");
    }

    @Test
    void testParseFiles_SpecialCharactersInFileNames() {
        File pastaCaracteresEspeciais = new File("teste/caracteresEspeciais"); // Arquivos com nomes contendo caracteres especiais
        assertFalse(Main.parseFiles(pastaCaracteresEspeciais), "Erro: Arquivos com caracteres especiais não deveriam ser aceitos");
    }

}
