package br.com.bancogeral.BancoGeral.resource;

import br.com.bancogeral.BancoGeral.domain.Civil;
import br.com.bancogeral.BancoGeral.service.Service;
import br.com.bancogeral.BancoGeral.service.dto.CivilDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    @ResponseBody
    public List<Civil> consultarRGs(@RequestBody CivilDTO dto){
        List<Civil> civils = this.service.consultarRGs(dto);
        return civils;
    }

    @PostMapping("verificar")
    @ResponseBody
    public Boolean verificarSeExisteRG(@RequestBody CivilDTO dto){
        Boolean estado = this.service.verificarSeExisteRG(dto);
        return estado;
    }

    @PostMapping("antigo")
    @ResponseBody
    public Civil consultarRGMaisAntigo(@RequestBody CivilDTO dto){
        Civil civil = this.service.retornaRGMaisAntigo(dto);
        return civil;
    }
}
