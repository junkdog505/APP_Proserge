package com.ucsm.proserge.Clases;

public class Trabajador {
    private String dni, nombres, apellidos, cargo, tipocontrato;

    public Trabajador(String d, String n, String a, String c, String t){
        this.dni = d;
        this.nombres = n;
        this.apellidos = a;
        this.cargo = c;
        this.tipocontrato = t;
    }
    public Trabajador(String d, String n, String a, String c){
        this.dni = d;
        this.nombres = n;
        this.apellidos = a;
        this.cargo = c;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }
}
