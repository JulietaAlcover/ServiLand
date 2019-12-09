
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Oficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficioRepositorio extends JpaRepository <Oficio,String> {
    
}
