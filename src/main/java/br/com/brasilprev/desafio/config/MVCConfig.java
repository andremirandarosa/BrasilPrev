package br.com.brasilprev.desafio.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** Controlador MVC Padrao */
@Controller
public class MVCConfig implements ErrorController{
    
    private static final String ROOT_PATH = "/";
    
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ROOT_PATH)
    public String root(){
        
        return "index.html";
    }
    
    @RequestMapping(value = ERROR_PATH)
    public String error(){
        
        return "error.html";
    }

    @Override
    public String getErrorPath(){
        
        return ERROR_PATH;
    }
}
