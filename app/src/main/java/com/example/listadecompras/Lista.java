package com.example.listadecompras;

public class Lista {
    private int id;
    private String titulo;


    @Override
    public String toString() {
        return this.titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
