package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;

public interface SquadService {

    public ResponseDTO createSquad(SquadDTO squadDTO);

    public Squad readSquad(Long idSquad);

    public List<Squad> listSquads();

    public ResponseDTO updateSquad(Long idSquad, SquadDTO squadDTO);

    public ResponseDTO deleteSquad(Long idSquad);
    
    public ResponseDTO finishProject(Long idSquad);
    
    public ResponseDTO addMember(User user, Long idSquad);
    
    public ResponseDTO removeMember(User user, Long idSquad);

}
