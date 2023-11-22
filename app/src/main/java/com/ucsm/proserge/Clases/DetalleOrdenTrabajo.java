package com.ucsm.proserge.Clases;

public class DetalleOrdenTrabajo {
    private String epps_nombre;

    public DetalleOrdenTrabajo(String epps_n){
        this.epps_nombre = epps_n;
    }

    public String getEpps_nombre() {
        return epps_nombre;
    }

    public void setEpps_nombre(String epps_nombre) {
        this.epps_nombre = epps_nombre;
    }
}
