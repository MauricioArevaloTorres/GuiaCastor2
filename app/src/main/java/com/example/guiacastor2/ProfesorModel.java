package com.example.guiacastor2;

import android.hardware.Camera;

import io.opencensus.common.ServerStatsFieldEnums;

public class ProfesorModel {
    private String Nombre, Apellido, Area;
    private String IdP;

    private ProfesorModel() {
    }

    public ProfesorModel(String nombre, String apellido, String idP, String area) {
        Nombre      = nombre;
        Apellido    = apellido;
        IdP         = idP;
        Area        = area;
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

    public String getArea() {return Area;}

    public void setArea(String area) { Area = area;}
}
