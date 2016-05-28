/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.services;

import com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS_Service;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class HotelesServicio implements Serializable {

    @WebServiceRef
    //@WebServiceRef(wsdlLocation = "http://192.168.100.25:29149/IntegracionHotel-web/IntegracionHotelWS?wsdl")
    private IntegracionHotelWS_Service service;

    public java.util.List<com.teamj.arquitectura.integracionhotel.ws.Consultahotelesresponse2> consultar(Date fechaEntrada, Date fechaSalida, Integer totalPersonas, String ciudad, boolean desayunoIncluido) {
        try { // Call Web Service Operation
            //com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS_Service service = new com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS_Service();
            com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS port = service.getIntegracionHotelWSPort();
            // TODO initialize WS operation arguments here
            com.teamj.arquitectura.integracionhotel.ws.Consultahotelesrequest2 parametrosBusqueda = new com.teamj.arquitectura.integracionhotel.ws.Consultahotelesrequest2();
            // TODO process result here
            java.util.List<com.teamj.arquitectura.integracionhotel.ws.Consultahotelesresponse2> result = port.consulta(parametrosBusqueda);
            System.out.println("Result = " + result.get(0).getNombreHotel());
            //h="Result = "+result.get(0).getNombreHotel();
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;

//        try { // Call Web Service Operation
//            com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS port = service.getIntegracionHotelWSPort();
//            // TODO initialize WS operation arguments here
//            com.teamj.arquitectura.integracionhotel.ws.Consultahotelesrequest2 parametrosBusqueda = new com.teamj.arquitectura.integracionhotel.ws.Consultahotelesrequest2();
//            // TODO process result here
//            java.util.List<com.teamj.arquitectura.integracionhotel.ws.Consultahotelesresponse2> result = port.consulta(parametrosBusqueda);
//            System.out.println("Result = "+result.get(0).getNombreHotel());
//        } catch (Exception ex) {
//            // TODO handle custom exceptions here
//        }
    }
}
