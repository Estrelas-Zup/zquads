package br.com.zup.estrelas.zquads.service;

import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.PROJECT_FINISHED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_ALREADY_PRESENT;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_REMOVED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_ALREADY_PRESENT;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_NOT_FOUND;
import static java.time.LocalDate.now;
import static org.springframework.beans.BeanUtils.copyProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class SquadServiceImpl implements SquadService {


    @Autowired
    SquadRepository squadRepository;

    @Autowired
    UserRepository userRepository;

    public Squad createSquad(SquadDTO squadDTO) throws GenericException {
        
        boolean existingSquad = squadRepository.existsByName(squadDTO.getName());
        if (existingSquad) {
            throw new GenericException(SQUAD_ALREADY_PRESENT);
        }

        Optional<User> admin = userRepository.findById(squadDTO.getIdUser());
        if (admin.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }

        Squad squad = new Squad();
        copyProperties(squadDTO, squad);

        User user = admin.get();

        List<User> members = new ArrayList<>();
        members.add(user);
        squad.setMembers(members);

        return squadRepository.save(squad);
    }

    public Squad readSquad(Long idSquad) throws GenericException {
        return squadRepository.findById(idSquad).orElseThrow(() -> new GenericException(SQUAD_NOT_FOUND));
    }

    public List<Squad> listSquads() {
        return (List<Squad>) squadRepository.findAll();
    }

    public Squad updateSquad(Long idSquad, SquadDTO squadDTO) throws GenericException {

        Optional<Squad> consultedSquad = squadRepository.findById(idSquad);

        if (consultedSquad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        Squad modifiedSquad = consultedSquad.get();
        copyProperties(squadDTO, modifiedSquad);

        return squadRepository.save(modifiedSquad);
    }

    public ResponseDTO deleteSquad(Long idSquad) throws GenericException {

        Optional<Squad> consultedSquad = squadRepository.findById(idSquad);
        
        if (consultedSquad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        squadRepository.deleteById(idSquad);
        return new ResponseDTO(SQUAD_REMOVED);
        
    }

    public ResponseDTO finishProject(Long idSquad) throws GenericException {

        Optional<Squad> squad = squadRepository.findById(idSquad);

        if (squad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        squad.get().setFinishingDate(now());
        squad.get().setFinished(true);

        squadRepository.save(squad.get());
        return new ResponseDTO(PROJECT_FINISHED);
    }

    public ResponseDTO addMember(Long idUser, Long idSquad) throws GenericException {

        Optional<Squad> squad = squadRepository.findById(idSquad);
        Optional<User> member = userRepository.findById(idUser);
        
        if (member.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }
        List<User> members = squad.get().getMembers();
        
        if (members.contains(member.get())) {
            throw new GenericException(USER_ALREADY_PRESENT);
        }
        
        members.add(member.get());
        squadRepository.save(squad.get());
        
        return new ResponseDTO("Add member successfully");
    }

    public ResponseDTO removeMember(Long idUser, Long idSquad) throws GenericException {

        Optional<Squad> squad = squadRepository.findById(idSquad);
        Optional<User> member = userRepository.findById(idUser);
        
        if (member.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }
        
        List<User> members = squad.get().getMembers();
        
        
        if (members.contains(member.get())) {
            members.remove(member.get());
        }
        
        squadRepository.save(squad.get());
        
        return new ResponseDTO("Remove member successfully");
    }

}
