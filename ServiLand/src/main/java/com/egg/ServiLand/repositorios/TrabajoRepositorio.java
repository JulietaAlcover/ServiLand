
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepositorio extends JpaRepository <Trabajo, String> {
    
}
