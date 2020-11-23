package br.com.zup.estrelas.zquads.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;
import br.com.zup.estrelas.zquads.repository.SquadRepository;

@Service
public class SquadServiceImpl implements SquadService {

    @Autowired
    SquadRepository squadRepository;
    
    public ResponseDTO createSquad(SquadDTO squadDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public Squad readSquad(Long idSquad) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Squad> listSquads() {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseDTO updateSquad(Long idSquad, SquadDTO squadDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseDTO deleteSquad(Long idSquad) {
        // TODO Auto-generated method stub
        return null;
    }

}
