package com.androiddesdecero.jwtudemy.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

public class ProfesorEntity {

    @PrimaryKey
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String foto;
    private Long saveAt;

    public ProfesorEntity(Long id, String nombre, String email, String password, String foto, Long saveAt) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.saveAt = saveAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getSaveAt() {
        return saveAt;
    }

    public void setSaveAt(Long saveAt) {
        this.saveAt = saveAt;
    }
}
