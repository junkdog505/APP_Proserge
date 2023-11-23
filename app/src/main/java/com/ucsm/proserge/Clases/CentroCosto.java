package com.ucsm.proserge.Clases;

public class CentroCosto {
    private String id, nombre;

    public CentroCosto(String i, String n){
        this.id = i;
        this.nombre = n;
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String toString(){
        return nombre;
    }
}
