package br.com.dio.springwebmvcintermediario.enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum Raca {
    HUMANO("humano"),
    ELFO("elfo"),
    ORC("orc"),
    ANAO("anao");

    private String value;

    Raca(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Raca of(String value) {
        Raca raca = Stream.of(Raca.values())
                .filter(r -> r.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow();
        return raca;
    }
}
