
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Oficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OficioRepositorio extends JpaRepository <Oficio,String> {
    @Query(" SELECT c FROM Oficio c WHERE c.nombre= : nombre")
    public Oficio buscarPorNombre(@Param("nombre") String nombre);
}
