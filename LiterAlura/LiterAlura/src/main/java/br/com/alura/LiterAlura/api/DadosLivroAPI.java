package br.com.alura.LiterAlura.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DadosLivroAPI {

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AutorAPI> authors;

    @JsonProperty("languages")
    private List<String> languages;

    public DadosLivroAPI() {}

    public DadosLivroAPI(String title, List<AutorAPI> authors, List<String> languages) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AutorAPI> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AutorAPI> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
