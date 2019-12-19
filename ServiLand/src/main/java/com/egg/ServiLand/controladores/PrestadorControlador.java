
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.entidades.Oficio;
import com.egg.ServiLand.entidades.Zona;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.servicios.PrestadorServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

  @Controller
    @RequestMapping("/prestador")
public class PrestadorControlador {
    @Autowired
    private PrestadorServicio prestadorServicio;
    @GetMapping("/registro")
    public String registroprestador(){
        return "crear_registro_prestador.html";
    }
    

    @GetMapping("crear1")
   
    public String crear1(MultipartFile archivo,@RequestParam String nombre,@RequestParam String apellido,@RequestParam String cuit, @RequestParam String dni,@RequestParam String telefono,@RequestParam Date fecha_nacimiento,@RequestParam String clave,@RequestParam String zona,@RequestParam String oficio,@RequestParam String mail)throws ErrorServicio{
   
        @GetMapping("/crear1")
     public String crear1(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String cuit, @RequestParam String dni,@RequestParam String mail,@RequestParam Date fecha_nacimiento,@RequestParam String telefono,@RequestParam String clave,@RequestParam String zona,@RequestParam String oficio)throws ErrorServicio{

     prestadorServicio.registrar(null, nombre, apellido, cuit, dni, telefono, fecha_nacimiento, clave, zona, oficio, mail);
          return "inicio_sesion.html";
          
          //registrar(MultipartFile archivo, String nombre, String apellido, String cuit, String DNI, String telefono, Date fecha_nacimiento, String clave, String zona, String oficio, String mail
     }
     
     }
  }
