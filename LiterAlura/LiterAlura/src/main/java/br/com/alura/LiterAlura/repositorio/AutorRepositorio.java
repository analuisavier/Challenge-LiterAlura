package br.com.alura.LiterAlura.repositorio;

import br.com.alura.LiterAlura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento >= :ano OR a.anoFalecimento IS NULL)")
    List<Autor> findAutoresVivosEmAno(int ano);

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    List<Autor> findByAnoNascimentoGreaterThanEqual(int ano);

    List<Autor> findByAnoFalecimentoLessThanEqual(int ano);
}
