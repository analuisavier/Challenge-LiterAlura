package br.com.alura.LiterAlura.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolicitacaoAPI {

    @JsonProperty("search")
    private String search;

    @JsonProperty("languages")
    private String languages;

    public SolicitacaoAPI() {}

    public SolicitacaoAPI(String search, String languages) {
        this.search = search;
        this.languages = languages;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
