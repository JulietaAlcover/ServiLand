package com.egg.ServiLand.servicios;

import com.egg.ServiLand.entidades.Foto;
import com.egg.ServiLand.entidades.Oficio;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.entidades.Rol;
import com.egg.ServiLand.entidades.Usuario;
import com.egg.ServiLand.entidades.Zona;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import com.egg.ServiLand.repositorios.UsuarioRepositorio;
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

    @Autowired
    private UsuarioServicio us;

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String apellido, String cuit, String DNI, String telefono, Date fecha_nacimiento, String clave,String zona, String oficio, String mail) throws ErrorServicio {
        validar(nombre, apellido, mail, clave);
        Prestador prestador = new Prestador();
        prestador.setNombre(nombre);
        prestador.setApellido(apellido);
        prestador.setCuit(cuit);
        prestador.setDNI(DNI);

        prestador.setTelefono(telefono);
        prestador.setFecha_nacimiento(fecha_nacimiento);

        Foto foto = fotoServicio.guardar(archivo);
        prestador.setFoto(foto);

        Zona z = new Zona();
        z.setNombre(zona);

        prestador.setZona(z);
        Oficio o = new Oficio();
        o.setNombre(oficio);
        prestador.setOficio(o);

        Usuario u = us.RegistrarUsuario(mail, clave, Rol.PRESTADOR);
        prestador.setUsuario(u);

        prestador.setAlta(new Date());
        prestadorRepositorio.save(prestador);

    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, String mail, String clave) throws ErrorServicio {
        Optional<Prestador> respuesta = prestadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Prestador prestador = respuesta.get();
            prestador.setNombre(nombre);
            prestador.setApellido(apellido);
            //duda si la clave se tiene que volver a hacer como encriptada o no, en este caso que se modifica y no se registra.
            //String encriptada = new BCryptPasswordEncoder().encode(clave);
            //prestador.setClave(encriptada);
            prestadorRepositorio.save(prestador);
        } else {
            throw new ErrorServicio("No se encontró un usuario existente");
        }
    }

    @Transactional
    public void dehabilitar(String id) throws ErrorServicio {
        Optional<Prestador> respuesta = prestadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Prestador prestador = respuesta.get();
            prestador.setBaja(new Date());
            prestadorRepositorio.save(prestador);
        } else {
            throw new ErrorServicio("El usuario no existe");
        }
    }

    @Transactional
    public void borrar(String id) throws ErrorServicio {
        Optional<Prestador> respuesta = prestadorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Prestador prestador = respuesta.get();
            prestadorRepositorio.delete(prestador);
        } else {
            throw new ErrorServicio("El usuario no existe");
        }
    }

    private void validar(String nombre, String apellido, String mail, String clave) throws ErrorServicio {
       
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo");
        }
        if (clave == null || clave.isEmpty() || clave.length() < 6)  {
            throw new ErrorServicio("La clave no puede ser nula ni con menos de 6 caracteres");
        }
    }

}
