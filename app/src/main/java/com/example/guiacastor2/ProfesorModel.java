package com.example.guiacastor2;

import io.opencensus.common.ServerStatsFieldEnums;

public class ProfesorModel {
    private String Nombre, Apellido;
    private String IdP;

    private ProfesorModel() {
    }

    public ProfesorModel(String nombre, String apellido, String idP) {
        Nombre = nombre;
        Apellido = apellido;
        IdP = idP;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getIdP() {
        return IdP;
    }

    public void setIdP(String idP) {
        IdP = idP;
    }
}
