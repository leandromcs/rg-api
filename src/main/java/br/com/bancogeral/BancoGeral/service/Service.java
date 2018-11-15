package br.com.bancogeral.BancoGeral.service;

import br.com.bancogeral.BancoGeral.banco1.repository.Banco1Repository;
import br.com.bancogeral.BancoGeral.banco2.repository.Banco2Repository;
import br.com.bancogeral.BancoGeral.banco3.repository.Banco3Repository;
import br.com.bancogeral.BancoGeral.domain.Civil;
import br.com.bancogeral.BancoGeral.service.dto.CivilDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Banco1Repository banco1Repository;

    @Autowired
    private Banco2Repository banco2Repository;

    @Autowired
    private Banco3Repository banco3Repository;


    //Busca RG por id
    public Civil consultarPorId(Long id){
        Optional<Civil> consultado = this.banco3Repository.findById(id);
        return consultado.get();
    }

    //Retorna os RGs da Pessoa
    public List<Civil> consultarRGs(CivilDTO dto){
        Civil civil = this.banco1Repository.consultarPorRGs(dto.getNome(), dto.getDataNascimento(), dto.getNomeMae());
        Civil civil2 = this.banco2Repository.consultarPorRGs(dto.getNome(), dto.getDataNascimento(), dto.getNomeMae());
        Civil civil3 = this.banco3Repository.consultarPorRGs(dto.getNome(), dto.getDataNascimento(), dto.getNomeMae());

        List<Civil> civils = new ArrayList<>();
        civils.add(civil);
        civils.add(civil2);
        civils.add(civil3);
        return civils;
    }

    //Verifica se existe RG para Pessoa
    public Boolean verificarSeExisteRG(CivilDTO dto){
        Civil civil = this.banco1Repository.consultarPorRGs(dto.getNome(), dto.getDataNascimento(), dto.getNomeMae());
        Civil civil2 = this.banco2Repository.consultarPorRGs(dto.getNome(), dto.getDataNascimento(), dto.getNomeMae());
        Civil civil3 = this.banco3Repository.consultarPorRGs(dto.getNome(), dto.getDataNascimento(), dto.getNomeMae());

        if(civil.equals(null) && civil2.equals(null) && civil3.equals(null)){
            return false;
        } else {
            return true;
        }
    }

//    public Civil retornaRGMaisAntigo(){
//
//    }


}
