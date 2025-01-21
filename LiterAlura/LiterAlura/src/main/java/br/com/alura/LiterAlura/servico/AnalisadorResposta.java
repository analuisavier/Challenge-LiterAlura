package br.com.alura.LiterAlura.servico;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.alura.LiterAlura.modelo.Livro;
import java.io.IOException;

public class AnalisadorResposta {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Livro[] analisarJson(String json) throws IOException {
        return objectMapper.readValue(json, Livro[].class);
    }

    public static void exibirLivros(Livro[] livros) {
        if (livros == null || livros.length == 0) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }
        for (Livro livro : livros) {
            System.out.printf("Título: %s\nAutor(es): %s\nIdioma(s): %s\n---------------------------\n",
                    livro.getTitulo(), livro.getAutor().getNome(), livro.getIdioma());
        }
    }
}
