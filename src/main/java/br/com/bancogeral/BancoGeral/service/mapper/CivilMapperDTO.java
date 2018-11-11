package br.com.bancogeral.BancoGeral.service.mapper;

import br.com.bancogeral.BancoGeral.repository.mapper.EntityMapper;
import br.com.bancogeral.BancoGeral.service.dto.Civil;
import br.com.bancogeral.BancoGeral.service.dto.CivilDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {CivilMapperDTO.class})
public interface CivilMapperDTO extends EntityMapper <CivilDTO, Civil>  {
}
