package br.com.bancogeral.BancoGeral.service;

import br.com.bancogeral.BancoGeral.banco1.repository.Banco1Repository;
import br.com.bancogeral.BancoGeral.banco2.repository.Banco2Repository;
import br.com.bancogeral.BancoGeral.banco3.repository.Banco3Repository;
import br.com.bancogeral.BancoGeral.domain.Civil;
import org.springframework.beans.factory.annotation.Autowired;

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




}
