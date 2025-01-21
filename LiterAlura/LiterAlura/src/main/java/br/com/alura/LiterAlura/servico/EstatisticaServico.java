package br.com.alura.LiterAlura.servico;

import br.com.alura.LiterAlura.modelo.Livro;
import br.com.alura.LiterAlura.repositorio.LivroRepositorio;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaServico {

    private final LivroRepositorio livroRepositorio;

    public EstatisticaServico(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    public DoubleSummaryStatistics obterEstatisticasDownloads() {
        List<Livro> livros = livroRepositorio.findAll();

        return livros.stream()
                .mapToDouble(livro -> livro.getNumeroDownloads() != null ? livro.getNumeroDownloads() : 0.0)
                .summaryStatistics();
    }
}
