package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TesteAtores {
     @Test
     public void testaCriacaoAtores() {
          Atores ator = new Atores(1, "Ana Silva", "F", 101);
          assertNotNull(ator);
          assertEquals(1, ator.actorId);
          assertEquals("Ana Silva", ator.actorName);
          assertEquals("F", ator.actorGender);
          assertEquals(101, ator.movieId);
     }

     @Test
     public void testaToStringGeneroFeminino() {
          Atores ator = new Atores(2, "Maria Costa", "F", 202);
          String esperado = "2 | Maria Costa | Feminino | 202";
          assertEquals(esperado, ator.toString());
     }

     @Test
     public void testaToStringGeneroMasculino() {
          Atores ator = new Atores(3, "João Pereira", "M", 303);
          String esperado = "3 | João Pereira | Masculino | 303";
          assertEquals(esperado, ator.toString());
     }

     @Test
     public void testaToStringGeneroNaoDefinido() {
          Atores ator = new Atores(4, "Alex Souza", "X", 404);
          String esperado = "4 | Alex Souza | Masculino | 404";
          assertEquals(esperado, ator.toString());
     }

     @Test
     public void testaAtoresComMesmosValores() {
          Atores ator1 = new Atores(5, "Carlos Lima", "M", 505);
          Atores ator2 = new Atores(5, "Carlos Lima", "M", 505);
          assertEquals(ator1.toString(), ator2.toString());
     }
}

