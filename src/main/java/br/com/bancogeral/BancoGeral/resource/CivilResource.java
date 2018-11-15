package br.com.bancogeral.BancoGeral.resource;

import br.com.bancogeral.BancoGeral.domain.Civil;
import br.com.bancogeral.BancoGeral.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/civil")
public class CivilResource {

    @Autowired
    private Service service;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Civil consultarPorId(@PathVariable("id") Long id){
       return service.consultarPorId(id);
    }
}
