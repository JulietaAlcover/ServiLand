
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Oficio;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.OficioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OficioServicio {
    @Autowired
    private OficioRepositorio oficioRepositorio;
    //no se si es prestadorServicio o prestador la entidad
   // @Autowired
    //private Prestador prestador;
    
    //public void guardarOficio (String nombre, String id) throws ErrorServicio {
     //   List<Oficio>oficio = null;
       // for (Oficio ofi : oficio){
            //oficio.add(prestador.getOficio());
      //  }
    }

