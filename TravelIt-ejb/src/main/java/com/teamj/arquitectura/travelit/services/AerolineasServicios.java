/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class AerolineasServicios {

    public void consultar(String fechaSalida,String fechaRetorno, Integer totalPersonas, String ciudadOrigen, String ciudadDestino) {
          
    }
    public void reservar(String fechaSalida,String fechaRetorno, Integer totalPersonas, String ciudadOrigen, String ciudadDestino) {
     //yyyy-MM-dd HH:mm:ss     //asistencia y miespe
    }
}
