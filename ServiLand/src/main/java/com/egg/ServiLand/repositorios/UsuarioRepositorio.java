/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Oficio;
import com.egg.ServiLand.entidades.Trabajo;
import com.egg.ServiLand.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jorge
 */
public interface UsuarioRepositorio extends JpaRepository <Trabajo, String> {

    public void save(Usuario usuario);

        @Query(" SELECT c FROM Usuario c WHERE c.mail= :mail")
    public Usuario buscarPorMail(@Param("mail") String mail);
   

}
