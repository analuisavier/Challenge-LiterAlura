package br.com.alura.LiterAlura.servico;

import br.com.alura.LiterAlura.modelo.Livro;
import br.com.alura.LiterAlura.modelo.Autor;
import br.com.alura.LiterAlura.repositorio.LivroRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroServico {

    private final LivroRepositorio livroRepositorio;

    public LivroServico(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    public List<Livro> listarTop10LivrosMaisBaixados() {
        return livroRepositorio.findTop10ByOrderByNumeroDownloadsDesc();
    }

    public long contarLivrosPorIdioma(String idioma) {
        return livroRepositorio.findByIdiomaContainingIgnoreCase(idioma).size();
    }

    public List<Livro> consultarLivroPorTitulo(String titulo) {
        return livroRepositorio.findByTituloContainingIgnoreCase(titulo.trim());
    }

    public void exibirLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepositorio.findByIdiomaContainingIgnoreCase(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma: " + idioma);
        } else {
            livros.forEach(livro -> {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido"));
                System.out.println("---------------------------");
            });
        }
    }

    public void listarAutoresVivosEmAno(int ano) {
        List<Livro> livros = livroRepositorio.findAll();

        livros.forEach(livro -> {
            Autor autor = livro.getAutor();
            if (autor != null) {
                int anoFalecimento = autor.getAnoFalecimento();
                if ((anoFalecimento == 0 || anoFalecimento > ano) && autor.getAnoNascimento() <= ano) {
                    System.out.println("Autor: " + autor.getNome() + " (Nascido em: " + autor.getAnoNascimento() + ")");
                }
            } else {
                System.out.println("Autor não encontrado para o livro: " + livro.getTitulo());
            }
        });
    }
}
