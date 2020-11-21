package com.example.parcialdam;

import android.os.AsyncTask;

import androidx.loader.content.AsyncTaskLoader;

public class Usuario {
    private String nombre;
    private String sexo;

    public Usuario(String nombre, String sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "usuario: " +
                "nombre='" + nombre + '\'' +
                ", sexo='" + sexo + '\'';
    }
}
