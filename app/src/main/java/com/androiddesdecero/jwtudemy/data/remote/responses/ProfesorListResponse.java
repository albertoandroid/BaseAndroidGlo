package com.androiddesdecero.jwtudemy.data.remote.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfesorListResponse {

    @SerializedName("data")
    public List<ProfesorResponse> profesorResponses;

    public ProfesorListResponse(){}

    public ProfesorListResponse(List<ProfesorResponse> profesorResponses){
        this.profesorResponses = profesorResponses;
    }

    public void setProfesorResponses(List<ProfesorResponse> profesorResponses) {
        this.profesorResponses = profesorResponses;
    }

    public List<ProfesorResponse> getProfesorResponses() {
        return profesorResponses;
    }

    public class ProfesorResponse{
        private Long id;
        private String nombre;
        private String email;
        private String password;
        private String foto;

        public ProfesorResponse(Long id, String nombre, String email, String password, String foto) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.foto = foto;
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
    }
}
