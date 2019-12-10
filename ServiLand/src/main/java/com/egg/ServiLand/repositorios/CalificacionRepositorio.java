
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Calificacion;
import com.egg.ServiLand.entidades.Oficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositorio extends JpaRepository <Calificacion, String>{
    @Query("SELECT AVG(c.puntuacion) FROM Calificacion c")
    public Calificacion promedioPuntuacion(@Param("puntuacion") Integer puntuacion);
}
