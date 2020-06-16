package com.example.guiacastor2;

public class ComentariosModel {
    private String Comentario;
    private Float Calificacion;


    private ComentariosModel(){}

    public ComentariosModel(String comentario, Float calificacion) {
        Comentario = comentario;
        Calificacion = calificacion;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public Float getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(Float calificacion) {
        Calificacion = calificacion;
    }
}
