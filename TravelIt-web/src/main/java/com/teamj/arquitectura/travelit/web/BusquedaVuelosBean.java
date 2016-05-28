/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.web;

import com.teamj.arquitectura.travelit.services.AerolineasServicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dennys
 */
@ViewScoped
@ManagedBean
public class BusquedaVuelosBean {

    @EJB
    AerolineasServicios aerolineasServicios;
    private Integer numeroAsientos;
    
    private Date fechaRetorno;
    private Date fechaSalida;
    private String ciudadOrigen;
    private String ciudadDestino;
    private Boolean conDesayuno;
    private List<String> ciudadesOrigen;
    private List<String> ciudadesDestino;

    @PostConstruct
    public void init() {
        ciudadesOrigen = new ArrayList<>();
        ciudadesOrigen.add("Guayaquil");
        ciudadesOrigen.add("Manta");
        ciudadesOrigen.add("Quito");
        ciudadesDestino = new ArrayList<>();
        ciudadesDestino.add("Guayaquil");
        ciudadesDestino.add("Manta");
        ciudadesDestino.add("Quito");
        
        numeroAsientos = 1;
        fechaSalida = new Date();

    }

    public String todayDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

    public Integer getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(Integer numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public List<String> getCiudadesOrigen() {
        return ciudadesOrigen;
    }

    public void setCiudadesOrigen(List<String> ciudadesOrigen) {
        this.ciudadesOrigen = ciudadesOrigen;
    }

    public List<String> getCiudadesDestino() {
        return ciudadesDestino;
    }

    public void setCiudadesDestino(List<String> ciudadesDestino) {
        this.ciudadesDestino = ciudadesDestino;
    }



  

  

    public Date getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(Date fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }


    public Boolean getConDesayuno() {
        return conDesayuno;
    }

    public void setConDesayuno(Boolean conDesayuno) {
        this.conDesayuno = conDesayuno;
    }

    public void buscar() {
     //   hotelesServicio.consultar(fechaRetorno, fechaSalida, numeroAsientos, numeroHabitaciones, ciudad, true);
    }

}
