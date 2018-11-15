package br.com.bancogeral.BancoGeral.banco1.repository;

import br.com.bancogeral.BancoGeral.domain.Civil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Banco1Repository extends JpaRepository<Civil, Long> {

    @Query("SELECT c FROM Civil c WHERE c.nome = :nome and c.dataNascimento = :dataNascimento and c.nomeMae= :nomeMae")
    Civil consultarPorRGs(
            @Param("nome") String nome,
            @Param("dataNascimento") Date dataNascimento,
            @Param("nomeMae") String nomeMae
    );
}
