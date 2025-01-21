package br.com.alura.LiterAlura.repositorio;

import br.com.alura.LiterAlura.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepositorio extends JpaRepository<Livro, Long> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo); 

    List<Livro> findTop10ByOrderByNumeroDownloadsDesc();

    List<Livro> findByIdiomaContainingIgnoreCase(String idioma);
}
