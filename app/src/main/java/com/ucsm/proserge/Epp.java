package com.ucsm.proserge;

public class Epp {
    private int id;
    private String nombre;

    public Epp(int i, String s) {
        this.id = i;
        this.nombre = s;
    }

    // Otros atributos y métodos getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
