package com.example.listadisciplinas;

import java.io.Serializable;

public class Disciplina implements Serializable {
    private String nome;
    private double nota;
    private boolean notaInserida;
    private boolean ead;
    private String categoria;

    // Construtor
    public Disciplina(String nome, String categoria, boolean ead) {
        this.nome = nome;
        this.categoria = categoria;
        this.ead = ead;
        this.notaInserida = false;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public double getNota() { return nota; }
    public void setNota(double nota) {
        if (!this.notaInserida) {
            this.nota = nota;
            this.notaInserida = true;
        }
    }
    public boolean isNotaInserida() { return notaInserida; }
    public boolean isEad() { return ead; }
    public String getCategoria() { return categoria; }

}

