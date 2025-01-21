package br.com.alura.LiterAlura.api;

import br.com.alura.LiterAlura.modelo.Autor;
import br.com.alura.LiterAlura.modelo.Livro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteAPI {

    private static final String BASE_URL = "https://gutendex.com/books";

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        List<Livro> livros = new ArrayList<>();
        try {
            String apiUrl = BASE_URL + "?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            URL url = new URI(apiUrl).toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder jsonResponse = new StringBuilder();
                while (scanner.hasNext()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonResponse.toString());
                JsonNode resultsNode = rootNode.path("results");

                if (resultsNode.isArray()) {
                    for (JsonNode node : resultsNode) {
                        String tituloLivro = node.path("title").asText();
                        String idioma = node.path("languages").get(0).asText();

                        JsonNode authorsNode = node.path("authors");
                        Autor autor = null;
                        if (authorsNode.isArray() && authorsNode.size() > 0) {
                            JsonNode authorNode = authorsNode.get(0);
                            autor = new Autor(
                                    authorNode.path("name").asText(),
                                    authorNode.path("birth_year").asInt(0),
                                    authorNode.path("death_year").asInt(0)
                            );
                        }

                        Integer numeroDeDownloads = node.path("downloads").asInt(0);

                        livros.add(new Livro(tituloLivro, idioma, numeroDeDownloads, autor));

                    }
                }
            } else {
                System.out.println("Erro na conexão com a API: Código " + conn.getResponseCode());
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Erro ao acessar a API: " + e.getMessage());
        }
        return livros;
    }
}
