
package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Cliente;
import com.egg.ServiLand.entidades.Foto;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.entidades.Rol;
import com.egg.ServiLand.entidades.Usuario;
import com.egg.ServiLand.entidades.Zona;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.ClienteRepositorio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import java.util.ArrayList;
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
   
    @Autowired
    private PrestadorRepositorio prestadorRepositorio;
    @Autowired
    private UsuarioServicio us;
    @Autowired
    private ZonaServicio zs;
      
    @Transactional 
    public void registrar(MultipartFile archivo,String nombre, String apellido, String DNI, String telefono,String mail, String clave,Date fecha_nacimiento,String zona) throws ErrorServicio {
        validar(nombre, apellido, mail, clave);
        Cliente cliente= new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDNI(DNI);
        cliente.setTelefono(telefono);
        cliente.setFecha_nacimiento(fecha_nacimiento);
        Foto foto= fotoServicio.guardar(archivo);
        cliente.setFoto(foto);
    Usuario u= us.RegistrarUsuario(mail, clave, Rol.CLIENTE);
    cliente.setUsuario(u);
    cliente.getUsuario();
    cliente.setAlta(new Date());
    Zona z=zs.CrearZona(zona);
    cliente.setZona(z);
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
            String encriptada= new  BCryptPasswordEncoder().encode(clave);


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
    public void traerPrestador (String oficio){
        System.out.println(prestadorRepositorio.buscarporOficio(oficio));
       }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    }
    


    