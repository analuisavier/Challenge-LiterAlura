package br.com.alura.LiterAlura.servico;

import br.com.alura.LiterAlura.modelo.Autor;
import br.com.alura.LiterAlura.repositorio.AutorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServico {

    private final AutorRepositorio autorRepositorio;

    public AutorServico(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    public List<Autor> buscarAutoresPorNome(String nome) {
        return autorRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    public List<Autor> listarAutoresPorAnoNascimento(int ano) {
        return autorRepositorio.findByAnoNascimentoGreaterThanEqual(ano);
    }

    public List<Autor> listarAutoresPorAnoFalecimento(int ano) {
        return autorRepositorio.findByAnoFalecimentoLessThanEqual(ano);
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
        return autorRepositorio.findAutoresVivosEmAno(ano);
    }

    public Autor consultarAutorPorNome(String s) {
        return null;
    }

    public Autor salvarAutor(Autor autor) {
        return autor;
    }
}
