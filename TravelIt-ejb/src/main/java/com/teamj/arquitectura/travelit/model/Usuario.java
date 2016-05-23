/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dennys
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_U")
    Integer id;

    @Column(name = "NOMBRE_U")
    String nombre;

    @Column(name = "CLAVE_U")
    String clave;

    @Column(name = "CREADO_U")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date created;
    
    @Column(name = "EMAIL_U")
    String email;

    @Column(name = "ACTIVO_U")
    Boolean activo;
 
    @OneToMany(mappedBy = "usuario", targetEntity = HistoricoReserva.class,
            fetch = FetchType.EAGER)
    List<HistoricoReserva> reservas;
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<HistoricoReserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<HistoricoReserva> reservas) {
        this.reservas = reservas;
    }
    

    public Usuario(Integer id, String nombre, String clave, Date created, String email, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.created = created;
        this.email = email;
        this.activo = activo;
    }

    public Usuario() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", created=" + created + ", email=" + email + ", activo=" + activo + '}';
    }

}
