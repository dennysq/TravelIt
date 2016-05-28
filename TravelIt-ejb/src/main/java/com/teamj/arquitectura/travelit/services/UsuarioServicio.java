/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.services;

import com.teamj.arquitectura.travelit.dao.UsuarioDAO;
import com.teamj.arquitectura.travelit.exception.ValidationException;

import com.teamj.arquitectura.travelit.model.Usuario;
import java.util.Date;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class UsuarioServicio {

    @EJB
    private UsuarioDAO usuarioDAO;

    public boolean insertar(Usuario u) throws ValidationException {
        boolean flag = false;
        Usuario temp = new Usuario();
        temp.setNombre(u.getNombre());
        List<Usuario> tempList = this.usuarioDAO.find(temp);
        if (tempList == null || tempList.isEmpty()) {//el nombre de usuario no existe
            try {
                String codecPassword = DigestUtils.md5Hex(u.getClave());
                u.setClave(codecPassword);
                u.setActivo(true);
                u.setCreated(new Date());
                this.usuarioDAO.insert(u);
                flag = true;
            } catch (Exception e) {
                throw new ValidationException(e, "Error al crear el nuevo usuario");
            }
        }
        return flag;
    }

    public void eliminar(Integer id) {
        Usuario temp = this.usuarioDAO.findById(id, false);
        if (temp != null) {
            this.usuarioDAO.remove(temp);
        }
    }

    public Usuario login(Usuario userOnlyWithUsername, String password) {
        List<Usuario> tempList = this.usuarioDAO.find(userOnlyWithUsername);
        if (tempList != null && tempList.size() == 1) {
            if (DigestUtils.md5Hex(password).equals(tempList.get(0).getClave())) {
                return tempList.get(0);
            }
        }
        return null;
    }

    public boolean actualizar(Usuario u) throws ValidationException {
        boolean flag = false;
        try {
            Usuario userToUpdate = this.usuarioDAO.findById(u.getId(), false);
            if (userToUpdate != null) {
                userToUpdate.setActivo(u.getActivo());
                userToUpdate.setNombre(u.getNombre());
                //el passoword no voy a tomar en cuenta
                userToUpdate.setEmail(u.getEmail());
                this.usuarioDAO.update(userToUpdate);
            }
            flag = true;

        } catch (Exception e) {
            throw new ValidationException(e, "Error  al actualizar el usuario");
        }

        return flag;
    }

    public boolean cambiarContraseña(Usuario u, String oldP, String newP, String reNewP) throws ValidationException {
        boolean flag = false;
        try {
            if (DigestUtils.md5Hex(oldP).equals(u.getClave()) && newP.equals(reNewP)) {
                u.setClave(DigestUtils.md5Hex(newP));
                this.usuarioDAO.update(u);
                flag = true;
            }
        } catch (Exception e) {
            throw new ValidationException(e, "Error  al actualizar el usuario");
        }
        return flag;
    }

//    public boolean guardarMochilaFactura(Integer user_id) throws ValidationException {
//        Factura f = new Factura();
//        f.setDocEmisor("0604133546001");
//        f.setTipoDocEmisor("04");
//        f.setClaveAcceso("xx");
//        f.setFechaEmision(new Date());
//        f.setDireccionEmisor("Quito, Sangolqui");
//        f.setTipoDocReceptor("04");
//        f.setTotal(BigDecimal.ZERO);
//        f.setSubtotal(BigDecimal.ZERO);
//        f.setDocReceptor(user_id.toString());
//        f.setRazonSocial("Excursiones S.A");
//        f.setSecuencial("000000000000000");
//        try {
//            this.facturaDAO.insert(f);
//            return true;
//        } catch (Exception ex) {
//            throw new ValidationException(ex, "Error al crear la factura mochila");
//
//        }
//    }
}
