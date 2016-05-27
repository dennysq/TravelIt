/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dennys create table HISTORICO_RESERVAS ( CODIGO_H INTEGER not null,
 * NUM_RESERVA_H INTEGER not null, CODIGO_EMPRESA_H INTEGER not null,
 * NOMBRE_EMPRESA_H INTEGER not null, FECHA_INICIO_H DATE not null, FECHA_FIN_H
 * DATE, COSTO_TOTAL_H NUMBER(8,2) not null, CANTIDAD_ITEMS_H INTEGER not null,
 * TIPO_RESERVA_H VARCHAR2(2) not null, CREADO_H TIMESTAMP not null default
 * sysdate, constraint PK_HISTORICO_RESERVAS primary key (CODIGO_H) );
 */
@Entity
@Table(name = "HISTORICO_RESERVAS")
public class HistoricoReserva implements Serializable {

    @Id
    @SequenceGenerator(name = "H_SEQ1", sequenceName = "H_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "H_SEQ1")
    @Column(name = "CODIGO_H")
    private Integer id;

    @Column(name = "NUM_RESERVA_H")
    private Integer numeroReserva;
    
    @Column(name = "CODIGO_EMPRESA_H")
    private Integer idEmpresa;
    
    @Column(name = "NOMBRE_EMPRESA_H")
    private String nombreEmpresa;
    
    @Column(name = "FECHA_INICIO_H")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaInicio;
    
    @Column(name = "FECHA_FIN_H")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @Column(name = "COSTO_TOTAL_H")
    private BigDecimal costoTotal;
    
    @Column(name = "CANTIDAD_ITEMS_H")
    private Integer cantidadItems;
    
    @Column(name = "TIPO_RESERVA_H")
    private String tipoReserva;

    @Column(name = "CREADO_H")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creado;

    @ManyToOne
    @JoinColumn(name = "CODIGO_U", nullable = false,insertable = false,updatable = false)
    private Usuario usuario;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the numeroReserva
     */
    public Integer getNumeroReserva() {
        return numeroReserva;
    }

    /**
     * @param numeroReserva the numeroReserva to set
     */
    public void setNumeroReserva(Integer numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    /**
     * @return the idEmpresa
     */
    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the costoTotal
     */
    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return the cantidadItems
     */
    public Integer getCantidadItems() {
        return cantidadItems;
    }

    /**
     * @param cantidadItems the cantidadItems to set
     */
    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    /**
     * @return the tipoReserva
     */
    public String getTipoReserva() {
        return tipoReserva;
    }

    /**
     * @param tipoReserva the tipoReserva to set
     */
    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    /**
     * @return the creado
     */
    public Date getCreado() {
        return creado;
    }

    /**
     * @param creado the creado to set
     */
    public void setCreado(Date creado) {
        this.creado = creado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
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
        final HistoricoReserva other = (HistoricoReserva) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoricoReserva{" + "id=" + id + ", numeroReserva=" + numeroReserva + ", idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", costoTotal=" + costoTotal + ", cantidadItems=" + cantidadItems + ", tipoReserva=" + tipoReserva + ", creado=" + creado + '}';
    }
    

}
