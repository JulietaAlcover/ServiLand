
package com.egg.ServiLand.repositorios;

import com.egg.ServiLand.entidades.Cliente;
import com.egg.ServiLand.entidades.Oficio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
     
    
     @Query(" SELECT c FROM Cliente c WHERE c.usuario.id= :id")
    public Cliente buscarporCliente(@Param("id") String id);
    
     //@Query(" SELECT c FROM Oficio c WHERE c.nombre=:q")
    //public List<Oficio> buscarporOficio(@Param("q") String q);
}
