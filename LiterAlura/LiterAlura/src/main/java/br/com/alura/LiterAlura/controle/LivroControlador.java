package br.com.alura.LiterAlura.controle;

import br.com.alura.LiterAlura.modelo.Autor;
import br.com.alura.LiterAlura.modelo.Livro;
import br.com.alura.LiterAlura.servico.LivroServico;
import br.com.alura.LiterAlura.servico.AutorServico; // Serviço para autor
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroControlador {

    @Autowired
    private LivroServico livroServico;

    @Autowired
    private AutorServico autorServico;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarLivro() {
        Autor autor = autorServico.consultarAutorPorNome("J.R.R. Tolkien");

        if (autor == null) {
            autor = new Autor("J.R.R. Tolkien", 1892, 1973);
            autor = autorServico.salvarAutor(autor);
        }

        Livro livro = new Livro("O Senhor dos Anéis", "português", 1000, autor);

        return ResponseEntity.ok("Livro salvo com sucesso! ID: " + livro.getId());
    }
}
