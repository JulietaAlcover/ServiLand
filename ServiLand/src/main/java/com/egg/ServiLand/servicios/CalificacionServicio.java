
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Calificacion;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.CalificacionRepositorio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionServicio {
    @Autowired
    private CalificacionRepositorio calificacionRepositorio;
    @Autowired
    private PrestadorRepositorio prestadorRepositorio;
    
    public void votar (String idPrestador, String idCliente, Integer puntuacion) throws ErrorServicio{
        Calificacion calificacion = new Calificacion ();
        if (idPrestador.equals(idCliente)){
            throw new ErrorServicio ("No esta permitido autovotarse");
        }
        Optional <Prestador> respuesta = prestadorRepositorio.findById(idPrestador);
        if (respuesta.isPresent()){
            Prestador prestador = respuesta.get();
            calificacion.setPuntuacion(puntuacion);
        }
     calificacionRepositorio.save(calificacion);
    }
   public void promedio (Integer puntuacion){
       calificacionRepositorio.promedioPuntuacion(puntuacion);
   }
}
