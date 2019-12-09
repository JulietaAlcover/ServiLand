
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Foto;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PrestadorServicio {
    @Autowired
    private PrestadorRepositorio prestadorRepositorio;
    @Autowired
    private FotoServicio fotoServicio;
    
    public void registrar (MultipartFile archivo, String nombre, String apellido, int DNI, int cuit, String mail, Date fecha_nacimiento, int telefono, String clave, String oficio) throws ErrorServicio{
       validar (nombre, apellido, mail, clave, oficio);
        Prestador prestador = new Prestador();
        prestador.setNombre(nombre);
        prestador.setApellido(apellido);
        prestador.setCuit(cuit);
        prestador.setDNI(DNI);
        prestador.setMail(mail);
        prestador.setTelefono(telefono);
        prestador.setOficio(oficio);
        prestador.setFecha_nacimiento(fecha_nacimiento);
        String encriptada=new BCryptPasswordEncoder().encode(clave);
        prestador.setClave(encriptada);
        prestador.setAlta(new Date());
        Foto foto= fotoServicio.guardar(archivo);
        prestador.setFoto(foto);
        
        prestadorRepositorio.save(prestador);
        
    }
    public void validar (String nombre,String apellido,String mail,String clave,String oficio) throws ErrorServicio{
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo");
        }
        if (clave == null || clave.isEmpty() || clave.length() < 6) {
            throw new ErrorServicio("La clave no puede ser nula ni con menos de 6 caracteres");
        }
        if (oficio == null || oficio.isEmpty()){
            throw new ErrorServicio("Debe ingresar un oficio");
        }
    }
}
