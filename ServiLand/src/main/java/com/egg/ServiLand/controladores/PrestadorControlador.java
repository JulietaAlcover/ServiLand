
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.servicios.PrestadorServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

  @Controller
    @RequestMapping("prestador")
public class PrestadorControlador {
    @Autowired
    private PrestadorServicio prestadorServicio;
    @GetMapping("index")
    public String index(){
        return "crear_registro_prestador.html";
    }
    
    @GetMapping("crear1")
     public String crear1(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String dni,@RequestParam String cuit,@RequestParam String mail,@RequestParam String zona,@RequestParam Date fecha_nacimiento,@RequestParam String telefono,@RequestParam String clave)throws ErrorServicio{
          prestadorServicio.registrar(null, nombre, apellido, dni, cuit, mail, fecha_nacimiento, telefono, clave);
          return "index";
     }
    
}
