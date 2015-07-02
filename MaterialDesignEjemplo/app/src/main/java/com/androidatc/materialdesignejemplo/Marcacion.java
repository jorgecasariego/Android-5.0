package com.androidatc.materialdesignejemplo;

/**
 * Created by jorgecasariego on 2/7/15.
 */
public class Marcacion{
    private Integer id;
    private String nombre;
    private String tipo;
    private String hora;

    public Marcacion(Integer id, String nombre, String tipo, String hora) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getHora() {
        return hora;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
