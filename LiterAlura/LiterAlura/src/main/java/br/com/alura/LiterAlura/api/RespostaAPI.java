package br.com.alura.LiterAlura.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class RespostaAPI {

    @JsonProperty("results")
    private List<DadosLivroAPI> results;

    public RespostaAPI() {}

    public RespostaAPI(List<DadosLivroAPI> results) {
        this.results = results;
    }

    public List<DadosLivroAPI> getResults() {
        return results;
    }

    public void setResults(List<DadosLivroAPI> results) {
        this.results = results;
    }
}
