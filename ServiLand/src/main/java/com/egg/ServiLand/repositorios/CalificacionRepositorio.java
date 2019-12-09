
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositorio extends JpaRepository <Calificacion, String>{
    
}
