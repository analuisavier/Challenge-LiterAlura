package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.api.ClienteAPI;
import br.com.alura.LiterAlura.modelo.Livro;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = "br.com.alura.LiterAlura.modelo")
public class Principal {

    private final ClienteAPI clienteAPI = new ClienteAPI();
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            System.out.println("""
                Escolha uma opção:
                1. Consultar livro por título
                2. Listar autores
                3. Listar autores vivos em determinado ano
                4. Sair
            """);

            switch (obterOpcao()) {
                case 1 -> consultarLivroPorTitulo();
                case 2 -> listarAutores();
                case 3 -> listarAutoresVivosEmAno();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private int obterOpcao() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.nextLine();
            }
        }
    }

    public void consultarLivroPorTitulo() {
        scanner.nextLine();
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();
        List<Livro> livros = clienteAPI.buscarLivroPorTitulo(titulo);

        Set<Livro> livrosUnicos = new HashSet<>(livros);

        if (livrosUnicos.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            livrosUnicos.forEach(livro -> {
                System.out.println("ID: " + livro.getId());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("---------------------------");
            });
        }
    }

    public void listarAutores() {
        scanner.nextLine();
        System.out.print("Informe o título de um livro para listar os autores: ");
        String titulo = scanner.nextLine();
        List<Livro> livros = clienteAPI.buscarLivroPorTitulo(titulo);
        Set<String> autoresUnicos = new HashSet<>();
        livros.forEach(livro -> autoresUnicos.add(livro.getAutor().getNome()));

        if (autoresUnicos.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
        } else {
            System.out.println("Lista de Autores:");
            autoresUnicos.forEach(System.out::println);
        }
    }

    public void listarAutoresVivosEmAno() {
        System.out.print("Informe o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Informe o título do livro para listar os autores vivos: ");
        String titulo = scanner.nextLine();
        List<Livro> livros = clienteAPI.buscarLivroPorTitulo(titulo);
        Set<String> autoresVivos = new HashSet<>();

        livros.forEach(livro -> {
            if ((livro.getAutor().getAnoFalecimento() == null || livro.getAutor().getAnoFalecimento() > ano) &&
                    livro.getAutor().getAnoNascimento() <= ano) {
                autoresVivos.add(livro.getAutor().getNome());
            }
        });

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo nesse ano.");
        } else {
            System.out.println("Autores vivos em " + ano + ":");
            autoresVivos.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        new Principal().exibirMenu();
    }
}
