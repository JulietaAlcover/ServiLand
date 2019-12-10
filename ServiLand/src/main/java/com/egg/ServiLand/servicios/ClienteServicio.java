
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Cliente;
import com.egg.ServiLand.entidades.Foto;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.ClienteRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteServicio implements UserDetailsService {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private FotoServicio fotoServicio;
      
    @Transactional 
    public void registrar(MultipartFile archivo,String nombre, String apellido, String mail, String clave,int DNI,int telefono) throws ErrorServicio {
        validar(nombre, apellido, mail, clave);
        Cliente cliente= new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMail(mail);
        cliente.setTelefono(telefono);
        cliente.setDNI(DNI);
                String encriptada=new BCryptPasswordEncoder().encode(clave);
        cliente.setClave(encriptada);

        cliente.setAlta(new Date());
        
        Foto foto= fotoServicio.guardar(archivo);
        cliente.setFoto(foto);
        
        clienteRepositorio.save(cliente);
    }
    
        public void validar(String nombre, String apellido, String mail, String clave) throws ErrorServicio {
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

@Transactional
    public void Modificar(MultipartFile archivo, String id, String nombre, String apellido, String mail, String clave) throws ErrorServicio {

        validar(nombre, apellido, mail, clave);

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = clienteRepositorio.findById(id).get();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setMail(mail);
            String encriptada= new  BCryptPasswordEncoder().encode(clave);

            cliente.setClave(encriptada);

            String idFoto = null;
            if (cliente.getFoto() != null) {
                idFoto = cliente.getFoto().getId();

                Foto foto = fotoServicio.actualizar(idFoto, archivo);
                cliente.setFoto(foto);
                clienteRepositorio.save(cliente);

            } else {
                throw new ErrorServicio("No se encontró el usuario solicitado");
            }
        }
    }

    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = clienteRepositorio.findById(id).get();
            cliente.setBaja(new Date());
            clienteRepositorio.save(cliente);

        } else {
            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
    }
@Transactional
    public void habilitar(String id) throws ErrorServicio {

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {

            Cliente cliente = clienteRepositorio.findById(id).get();
            cliente.setBaja(null);
            clienteRepositorio.save(cliente);

        } else {
            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    