
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Foto;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
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
    
    @Transactional
    public void registrar (MultipartFile archivo, String nombre, String apellido, int DNI, int cuit, String mail, Date fecha_nacimiento, int telefono, String clave) throws ErrorServicio{
       validar (nombre, apellido, mail, clave);
        Prestador prestador = new Prestador();
        prestador.setNombre(nombre);
        prestador.setApellido(apellido);
        prestador.setCuit(cuit);
        prestador.setDNI(DNI);
        prestador.setMail(mail);
        prestador.setTelefono(telefono);
        prestador.setFecha_nacimiento(fecha_nacimiento);
        String encriptada=new BCryptPasswordEncoder().encode(clave);
        prestador.setClave(encriptada);
        prestador.setAlta(new Date());
        Foto foto= fotoServicio.guardar(archivo);
        prestador.setFoto(foto);
        
        prestadorRepositorio.save(prestador);
        
    }
    @Transactional
    public void modificar (String id, String nombre, String apellido, String mail, String clave) throws ErrorServicio{
        Optional <Prestador> respuesta = prestadorRepositorio.findById(id);
        if (respuesta.isPresent()){
        Prestador prestador = respuesta.get();
        prestador.setNombre(nombre);
        prestador.setApellido(apellido);
        prestador.setMail(mail);
        //duda si la clave se tiene que volver a hacer como encriptada o no, en este caso que se modifica y no se registra.
        String encriptada=new BCryptPasswordEncoder().encode(clave);
        prestador.setClave(encriptada);
        prestadorRepositorio.save(prestador);
        }else{
            throw new ErrorServicio("No se encontró un usuario existente");
        }
    }
    @Transactional
    public void dehabilitar (String id)throws ErrorServicio{
        Optional <Prestador> respuesta = prestadorRepositorio.findById(id);
        if (respuesta.isPresent()){
        Prestador prestador = respuesta.get();
        prestador.setBaja(new Date());
        prestadorRepositorio.save(prestador);
        } else {
            throw new ErrorServicio("El usuario no existe");
        }
    }
    @Transactional
    public void borrar (String id) throws ErrorServicio{
        Optional <Prestador> respuesta = prestadorRepositorio.findById(id);
        if (respuesta.isPresent()){
        Prestador prestador = respuesta.get();
        prestadorRepositorio.delete(prestador);
        } else {
            throw new ErrorServicio("El usuario no existe");
        }
    }
    private void validar (String nombre,String apellido,String mail,String clave) throws ErrorServicio{
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
    }
    
}
