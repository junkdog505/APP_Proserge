package com.ucsm.proserge.Clases;

public class OrdenTrabajo {
    private String id, fecha, nombre, id_trabajador, nombres_trabajador, apellidos_trabajador, cargo_trabajador , id_epps, nombre_epp, id_centrocosto, nombre_centrocosto, tipoentrega;

    public OrdenTrabajo(String id, String f, String n, String idt, String nt, String at, String ct, String ide, String ne, String idc, String ncc, String te){
        this.id = id;
        this.fecha = f;
        this.nombre = n;
        this.id_trabajador = idt;
        this.nombres_trabajador = nt;
        this.apellidos_trabajador = at;
        this.cargo_trabajador = ct;
        this.id_epps = ide;
        this.nombre_epp = ne;
        this.id_centrocosto = idc;
        this.nombre_centrocosto = ncc;
        this.tipoentrega = te;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(String id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getId_epps() {
        return id_epps;
    }

    public void setId_epps(String id_epps) {
        this.id_epps = id_epps;
    }

    public String getId_centrocosto() {
        return id_centrocosto;
    }

    public void setId_centrocosto(String id_centrocosto) {
        this.id_centrocosto = id_centrocosto;
    }

    public String getTipoentrega() {
        return tipoentrega;
    }

    public void setTipoentrega(String tipoentrega) {
        this.tipoentrega = tipoentrega;
    }

    public String getNombres_trabajador() {
        return nombres_trabajador;
    }

    public void setNombres_trabajador(String nombres_trabajador) {
        this.nombres_trabajador = nombres_trabajador;
    }

    public String getApellidos_trabajador() {
        return apellidos_trabajador;
    }

    public void setApellidos_trabajador(String apellidos_trabajador) {
        this.apellidos_trabajador = apellidos_trabajador;
    }

    public String getCargo_trabajador() {
        return cargo_trabajador;
    }

    public void setCargo_trabajador(String cargo_trabajador) {
        this.cargo_trabajador = cargo_trabajador;
    }

    public String getNombre_epp() {
        return nombre_epp;
    }

    public void setNombre_epp(String nombre_epp) {
        this.nombre_epp = nombre_epp;
    }

    public String getNombre_centrocosto() {
        return nombre_centrocosto;
    }

    public void setNombre_centrocosto(String nombre_centrocosto) {
        this.nombre_centrocosto = nombre_centrocosto;
    }
}
