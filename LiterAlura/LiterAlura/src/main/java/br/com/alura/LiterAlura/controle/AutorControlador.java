package br.com.alura.LiterAlura.controle;

import br.com.alura.LiterAlura.modelo.Autor;
import br.com.alura.LiterAlura.servico.AutorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorControlador {

    @Autowired
    private AutorServico autorServico;

    @GetMapping("/buscar")
    public List<Autor> buscarAutoresPorNome(@RequestParam String nome) {
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        return autorServico.buscarAutoresPorNome(nome);
    }

    @GetMapping("/nascimento")
    public List<Autor> listarAutoresPorAnoNascimento(@RequestParam int ano) {
        validarAno(ano);
        return autorServico.listarAutoresPorAnoNascimento(ano);
    }

    @GetMapping("/falecimento")
    public List<Autor> listarAutoresPorAnoFalecimento(@RequestParam int ano) {
        validarAno(ano);
        return autorServico.listarAutoresPorAnoFalecimento(ano);
    }

    @GetMapping("/vivos")
    public List<Autor> listarAutoresVivosNoAno(@RequestParam int ano) {
        if (ano < 0 || ano > Year.now().getValue()) {
            throw new IllegalArgumentException("Ano inválido.");
        }
        return autorServico.listarAutoresVivosNoAno(ano);
    }

    private void validarAno(int ano) {
        if (ano < 0) {
            throw new IllegalArgumentException("O ano não pode ser negativo.");
        }
    }
}
