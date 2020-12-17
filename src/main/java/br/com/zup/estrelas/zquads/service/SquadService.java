package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;

public interface SquadService {

    public Squad createSquad(SquadDTO squadDTO) throws GenericException;

    public Squad readSquad(Long idSquad) throws GenericException;

    public List<Squad> listSquads();

    public Squad updateSquad(Long idSquad, SquadDTO squadDTO) throws GenericException;

    public ResponseDTO deleteSquad(Long idSquad) throws GenericException;

    public ResponseDTO finishProject(Long idSquad) throws GenericException;

    public ResponseDTO addMember(Long idUser, Long idSquad) throws GenericException;

    public ResponseDTO removeMember(Long idUser, Long idSquad) throws GenericException;

}
