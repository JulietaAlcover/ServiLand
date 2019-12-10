
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

  @Controller
    @RequestMapping("/cliente")
public class ClienteControlador {
    @Autowired
    ClienteServicio clienteServicio;
    @GetMapping("/registro_cliente")
    public String Registro(){
        return "crear_registro_cliente.html";
    }
    
}
