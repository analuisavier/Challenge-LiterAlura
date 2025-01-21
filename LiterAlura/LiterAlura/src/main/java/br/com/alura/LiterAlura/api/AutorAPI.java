package br.com.alura.LiterAlura.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorAPI {

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private Integer birthYear;

    @JsonProperty("death_year")
    private Integer deathYear;

    public AutorAPI() {}

    public AutorAPI(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public boolean isAlive() {
        return deathYear == null;
    }

    public int getAgeAtDeath() {
        if (birthYear == null) {
            throw new IllegalArgumentException("Ano de nascimento não pode ser nulo");
        }
        if (deathYear == null) {
            throw new IllegalArgumentException("Ano de falecimento não pode ser nulo");
        }
        return deathYear - birthYear;
    }

    @Override
    public String toString() {
        return "AutorAPI{name='" + name + "', birthYear=" + birthYear + ", deathYear=" + deathYear + '}';
    }
}
