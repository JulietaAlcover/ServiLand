
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.entidades.Cliente;
import com.egg.ServiLand.entidades.Oficio;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.entidades.Zona;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import com.egg.ServiLand.servicios.PrestadorServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

  @Controller
    @RequestMapping("/prestador")
public class PrestadorControlador {
    @Autowired
    private PrestadorServicio prestadorServicio;
    @Autowired
    private PrestadorRepositorio prestadorRepositorio;
    @GetMapping("/registro")
    public String registroprestador(){
        return "crear_registro_prestador.html";
    }
    

    @PostMapping("crear1")
    public String crear1(@RequestParam MultipartFile archivo,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String cuit, @RequestParam String dni,@RequestParam String telefono,@RequestParam Date fecha_nacimiento,@RequestParam String clave,@RequestParam String zona,@RequestParam String oficio,@RequestParam String mail)throws ErrorServicio{
     prestadorServicio.registrar(null, nombre, apellido, cuit, dni, telefono, fecha_nacimiento, clave, zona, oficio, mail);
     return "home_prestador.html";
          //registrar(MultipartFile archivo, String nombre, String apellido, String cuit, String DNI, String telefono, Date fecha_nacimiento, String clave, String zona, String oficio, String mail
     }
    
  @GetMapping (value="/image/{id}")
  public ResponseEntity<byte[]> getImage(@PathVariable(value = "id") String id) {
  Prestador prestador = null;
  prestador = prestadorRepositorio.buscarporPrestador(id);
  byte[] foto = prestador.getFoto().getContenido();
  final HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.IMAGE_PNG);
  return new ResponseEntity<byte[]>(foto, headers, HttpStatus.OK);
  }
  }
