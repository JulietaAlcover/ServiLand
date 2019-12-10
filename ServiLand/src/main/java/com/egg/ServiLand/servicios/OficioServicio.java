
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Oficio;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.OficioRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OficioServicio {
    @Autowired
    private OficioRepositorio oficioRepositorio;
    
    @Transactional
    public void guardar (String nombre, String id) throws ErrorServicio{
        Oficio oficio = new Oficio();
        oficio.setNombre(nombre);
        oficioRepositorio.save(oficio);
    }
    
    public void buscarporOficio (String nombre){
        oficioRepositorio.buscarPorNombre(nombre);
    }
    
    }

