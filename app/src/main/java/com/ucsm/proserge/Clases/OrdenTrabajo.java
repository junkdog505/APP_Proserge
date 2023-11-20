package com.ucsm.proserge.Clases;

public class OrdenTrabajo {
    private String id, fecha, nombre, id_trabajador, id_epps, id_centrocosto, tipoentrega;

    public OrdenTrabajo(String id, String f, String n, String idt,String ide, String idc, String te){
        this.id = id;
        this.fecha = f;
        this.nombre = n;
        this.id_trabajador = idt;
        this.id_epps = ide;
        this.id_centrocosto = idc;
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
}
