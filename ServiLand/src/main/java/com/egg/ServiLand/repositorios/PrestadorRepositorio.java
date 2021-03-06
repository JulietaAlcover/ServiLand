
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Cliente;
import com.egg.ServiLand.entidades.Prestador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorRepositorio extends JpaRepository<Prestador, String> {
    
    
    @Query(" SELECT c FROM Prestador c WHERE c.usuario.id= :id")
    public Prestador buscarporPrestador(@Param("id") String id);
    
    @Query(" SELECT c FROM Prestador c WHERE c.oficio= :oficio")
    public List<Prestador> buscarporOficio(@Param("oficio") String oficio);
    
}

