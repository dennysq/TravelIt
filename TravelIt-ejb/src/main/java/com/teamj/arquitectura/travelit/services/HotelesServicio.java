/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.services;

import com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS_Service;
import com.teamj.arquitectura.integracionhotel.ws.ReservaHotelPeticion;
import com.teamj.arquitectura.travelit.dao.HistoricoReservaDAO;
import com.teamj.arquitectura.travelit.dao.UsuarioDAO;
import com.teamj.arquitectura.travelit.model.HistoricoReserva;
import com.teamj.arquitectura.travelit.model.Usuario;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
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
    private IntegracionHotelWS_Service service;
    @EJB
    private HistoricoReservaDAO historicoReservaDAO;
    @EJB
    private UsuarioDAO usuarioDAO;

    public java.util.List<com.teamj.arquitectura.integracionhotel.ws.Consultahotelesresponse2> consultar(String nombreUsuario, String fechaEntrada, String fechaSalida, Integer totalPersonas, String ciudad, boolean desayunoIncluido) {
        try { // Call Web Service Operation

            com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS port = service.getIntegracionHotelWSPort();
            // TODO initialize WS operation arguments here
            com.teamj.arquitectura.integracionhotel.ws.Consultahotelesrequest2 parametrosBusqueda = new com.teamj.arquitectura.integracionhotel.ws.Consultahotelesrequest2();
            // TODO process result here
            parametrosBusqueda.setFechaEntrada(fechaEntrada);
            parametrosBusqueda.setFechaSalida(fechaSalida);
            parametrosBusqueda.setCiudad(ciudad);
            parametrosBusqueda.setDesayunoIncluido(desayunoIncluido);
            parametrosBusqueda.setNombreUsuario(nombreUsuario);
            parametrosBusqueda.setTotalPersonas(totalPersonas);

            java.util.List<com.teamj.arquitectura.integracionhotel.ws.Consultahotelesresponse2> result = port.consulta(parametrosBusqueda);
            System.out.println("Result = " + result);

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

    public String reservar(com.teamj.arquitectura.integracionhotel.ws.Consultahotelesresponse2 reserva, Integer numPersonas, boolean conDesayuno, Date fechaEntrada, Date fechaSalida, Usuario usuario) {
        com.teamj.arquitectura.integracionhotel.ws.ReservaHotelPeticion peticion = new ReservaHotelPeticion();
        peticion.setCodigoHabitacion(reserva.getCodigoHabitacion());
        peticion.setCodigoHotel(reserva.getCodigoHotel());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        peticion.setIdentificacion(usuario.getIdentificacion());
        peticion.setNombreUsuario(usuario.getNombre());
        peticion.setPrecio(reserva.getCotizacion());//cotizacion o precio habitacion tienen el mismo valor
        peticion.setFechaEntrada(sdf.format(fechaEntrada));
        peticion.setFechaSalida(sdf.format(fechaSalida));
        peticion.setNumPersonas(numPersonas);
        peticion.setConDesayuno(conDesayuno);

        try { // Call Web Service Operation
            com.teamj.arquitectura.integracionhotel.ws.IntegracionHotelWS port = service.getIntegracionHotelWSPort();
            // TODO initialize WS operation arguments here

            // TODO process result here
            com.teamj.arquitectura.integracionhotel.ws.ReservaHotelRespuesta result = port.reservar(peticion);
            System.out.println("Result = " + result);
            if (result.isEstado()) {
                HistoricoReserva historicoReserva = new HistoricoReserva();
                historicoReserva.setCantidadItems(1);
                historicoReserva.setCostoTotal(peticion.getPrecio().setScale(2, RoundingMode.HALF_UP));
                historicoReserva.setNumeroReserva(result.getNumeroReserva());
                historicoReserva.setFechaFin(fechaSalida);
                historicoReserva.setFechaInicio(fechaEntrada);
                historicoReserva.setIdEmpresa(peticion.getCodigoHotel());
                historicoReserva.setNombreEmpresa(reserva.getNombreHotel());
                historicoReserva.setTipoReserva("HO");
                historicoReserva.setCreado(new Date());
                System.out.println("" + usuario);
                //List<Usuario> user=
                Usuario user = this.usuarioDAO.findById(usuario.getId(), false);
                historicoReserva.setUsuario(user);
                historicoReserva.setUsuarioId(user.getId());

                this.historicoReservaDAO.insert(historicoReserva);
                return "Su Reserva ha sido exitosa, el código de reserva es: " + result.getNumeroReserva().toString();

            } else {
                return "Ha ocurrido un error en la reserva: " + result.getMensajeError();
            }
        } catch (Exception ex) {

            return "Ha ocurrido un error al reservar la habitación: " + ex.toString();

        }

    }

}
