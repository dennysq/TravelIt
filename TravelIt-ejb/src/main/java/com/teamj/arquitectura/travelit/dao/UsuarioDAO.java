/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.arquitectura.travelit.dao;

import com.persist.common.dao.DefaultGenericDAOImple;
import com.teamj.arquitectura.travelit.model.Usuario;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dennys
 */
@LocalBean
@Stateless
public class UsuarioDAO extends DefaultGenericDAOImple<Usuario, Integer> {

    public UsuarioDAO() {
        super(Usuario.class);
    }
}
