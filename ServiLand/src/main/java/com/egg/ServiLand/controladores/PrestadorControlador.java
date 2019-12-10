
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.servicios.PrestadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

  @Controller
    @RequestMapping("/prestador")
public class PrestadorControlador {
    @Autowired
    PrestadorServicio prestadorServicio;
    @GetMapping("/registro_prestador")
    public String Registro(){
        return "crear_registro_prestador.html";
    }
}
