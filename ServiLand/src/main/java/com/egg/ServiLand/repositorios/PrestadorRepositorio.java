
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorRepositorio extends JpaRepository<Prestador, String> {
    
}
