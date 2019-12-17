/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Rol;
import com.egg.ServiLand.entidades.Usuario;
import com.egg.ServiLand.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public Usuario RegistrarUsuario (String mail,String clave, Rol rol){
        
    Usuario usuario= new Usuario();
    usuario.setMail(mail);
    usuario.setClave(clave);
    usuario.getId();
    usuario.setRol(rol);
        
    usuarioRepositorio.save(usuario);
    
    return usuario;
    }
    
    
}
