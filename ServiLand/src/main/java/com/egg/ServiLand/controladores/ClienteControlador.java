
package com.egg.ServiLand.controladores;

import com.egg.ServiLand.entidades.Cliente;
import com.egg.ServiLand.entidades.Prestador;
import com.egg.ServiLand.entidades.Rol;
import com.egg.ServiLand.entidades.Usuario;
import com.egg.ServiLand.errores.ErrorServicio;
import com.egg.ServiLand.repositorios.ClienteRepositorio;
import com.egg.ServiLand.repositorios.PrestadorRepositorio;
import com.egg.ServiLand.repositorios.UsuarioRepositorio;
import com.egg.ServiLand.servicios.ClienteServicio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

  @Controller
    @RequestMapping("/cliente")
public class ClienteControlador  {
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private UsuarioRepositorio us;  
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private PrestadorRepositorio prestadorRepositorio;


     @GetMapping("/registro")
    public String registrocliente (){
        return "crear_registro_cliente.html";
   
   
        
    }
  @GetMapping("crear")
   public String crear(MultipartFile archivo, @RequestParam String nombre,@RequestParam String apellido,@RequestParam String dni,@RequestParam String telefono,@RequestParam String mail,@RequestParam String clave,@RequestParam Date fecha_nacimiento,@RequestParam String zona) throws ErrorServicio{
      
clienteServicio.registrar(archivo, nombre, apellido, dni, telefono, mail, clave, fecha_nacimiento, zona);
  return "inicio_sesion.html"; 

 
  }
    
    
     @GetMapping("/ingresar")
  public String ingresar (@RequestParam (required=false) String mail, @RequestParam (required=false) String clave, ModelMap model) throws ErrorServicio{
     // try{
          String vista=null;
          if (us.buscarPorMail(mail)!= null){
              
              Usuario usuario = us.buscarPorMail(mail);
              
              
              if(usuario.getClave().equals(clave)){

              if(usuario.getClave().equals(clave));

                  
              if (usuario.getRol()== Rol.CLIENTE){
                  
                  Cliente cliente= clienteRepositorio.buscarporCliente(usuario.getId());
              
              model.put("usuario", cliente);
                  
                 vista= "home_cliente.html";
                  
               } else if (usuario.getRol()== Rol.PRESTADOR) {
                  
                  Prestador prestador = prestadorRepositorio.buscarporPrestador(usuario.getId());
                  
                   model.put("usuario", prestador);
                  
                vista= "home_prestador.html";
               }
              }

          } 
<<<<<<< HEAD
                  
              
=======
              return "redirect:/login";
>>>>>>> 36e354ec7b8a08811bda70f18e20a9e502ab248f
                          
                           
     //}catch (Exception e) {
      
      //}  
      
        return vista;
  
  }
  @GetMapping ("/home_cliente")
  public String mostrarPrestador (@RequestParam String oficio, ModelMap model) throws ErrorServicio{
      clienteServicio.traerPrestador(oficio);
      return "home_cliente.html";
  }
  
  }
