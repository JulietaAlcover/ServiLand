
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Zona;
import com.egg.ServiLand.repositorios.ZonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaServicio {

    @Autowired
    private ZonaRepositorio zonaRepositorio;
    
    public Zona CrearZona (String nombre){
        
    Zona zona= new Zona();
    zona.setNombre(nombre);
        
    zonaRepositorio.save(zona);
    
    return zona;
    }
}
