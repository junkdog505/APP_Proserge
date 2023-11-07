package com.ucsm.proserge;

public class Epp {
    private int id;
    private String nombre;
    private String tipo;
    private String clasificacion;

    public Epp(int i, String n, String t, String c) {
        this.id = i;
        this.nombre = n;
        this.tipo = t;
        this.clasificacion = c;
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
    public String getTipo(){
        return tipo;
    }
    public String getClasificacion(){
        return clasificacion;
    }
}
