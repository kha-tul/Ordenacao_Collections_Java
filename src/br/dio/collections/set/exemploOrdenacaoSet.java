package br.dio.collections.set;

import com.sun.source.tree.Tree;

import java.util.*;

public class exemploOrdenacaoSet {
    public static void main(String[] args) {

        System.out.println("--\t Ordem Aleatória \t--");
        Set<Serie> minhasSeries = new HashSet<>(){{
            add(new Serie("Got", "fantasia", 60));
            add(new Serie("Dark", "drama", 60));
            add(new Serie("That's 70 show", "comédia", 25));

        }};
        for(Serie serie: minhasSeries) System.out.println(serie.getNome() + " - " +
              serie.getGenero() + " - " + serie.getTempoDeEpisodio() );

        System.out.println("--\t Ordem inserção\t --");
        Set<Serie> minhasSeries1 = new LinkedHashSet<>() {{
            add(new Serie("Got", "fantasia", 60));
            add(new Serie("Dark", "drama", 60));
            add(new Serie("That's 70s Show", "comédia", 25));
        }};
        for(Serie serie: minhasSeries1) System.out.println(serie.getNome() + " - " + serie.getGenero() +
                " - " + serie.getTempoDeEpisodio());

        System.out.println("--\t Ordem natural (tempoEpisodio)\t --");
        Set<Serie> minhasSeries2 = new TreeSet<>(minhasSeries1);
        System.out.println(minhasSeries2);
        for(Serie serie: minhasSeries2) System.out.println(serie.getNome() + " - " + serie.getGenero() +
                " - " + serie.getTempoDeEpisodio());


        System.out.println("--\t Ordem Nome/Genero/Episodio)\t --");
        Set<Serie> minhasSeries3 = new TreeSet<>(new ComparatorNomeGeneroEpisodio());
        minhasSeries3.addAll(minhasSeries);
        for (Serie serie: minhasSeries3) System.out.println(serie.getNome() + " - " + serie.getGenero() +
                " - " + serie.getTempoDeEpisodio());

    }
}

class Serie implements Comparable <Serie> {
    private String nome;
    private String genero;
    private Integer tempoDeEpisodio;

    // alt + insert > construtor

    public Serie(String nome, String genero, Integer tempoDeEpisodio) {
        this.nome = nome;
        this.genero = genero;
        this.tempoDeEpisodio = tempoDeEpisodio;
    }

    // alt + insert > getters

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getTempoDeEpisodio() {
        return tempoDeEpisodio;
    }

    // alt + insert > toString

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", tempoDeEpisodio=" + tempoDeEpisodio +
                '}';
    }

    // alt + insert > equals. hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return nome.equals(serie.nome) && genero.equals(serie.genero) && tempoDeEpisodio.equals(serie.tempoDeEpisodio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero, tempoDeEpisodio);
    }

    @Override
    public int compareTo(Serie serie) {
        int tempoEpisodio = Integer.compare(this.getTempoDeEpisodio(), serie.getTempoDeEpisodio());
        if (tempoEpisodio != 0) return tempoEpisodio;

        return this.getGenero().compareTo(serie.getGenero());

    }
}

class ComparatorNomeGeneroEpisodio implements Comparator<Serie>{

    @Override
    public int compare(Serie s1, Serie s2) {
        int nome = s1.getNome().compareTo(s2.getNome());
        if (nome != 0 ) return nome;

        int genero = s1.getGenero().compareTo(s2.getGenero());
        if (genero != 0) return genero;

        return Integer.compare(s1.getTempoDeEpisodio(), s2.getTempoDeEpisodio());
    }
}