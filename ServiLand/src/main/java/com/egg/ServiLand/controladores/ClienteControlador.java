
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.servicios.ClienteServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

  @Controller
    @RequestMapping("/")
public class ClienteControlador  {
    @Autowired
    private ClienteServicio clienteServicio;
     @GetMapping("index")
    public String index (){
        return "crear_registro_cliente.html";
   
   
    }
  @GetMapping("crear")
  public String crear(@RequestParam String nombre,@RequestParam String apellido,@RequestParam String mail,@RequestParam String clave,@RequestParam String telefono) throws ErrorServicio{
  
clienteServicio.registrar(null, nombre, apellido, mail, clave, mail, telefono);
 //(MultipartFile archivo,String nombre, String apellido, String mail, String clave,String DNI,String telefono
      
     return "index";
  }
}
