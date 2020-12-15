package br.com.zup.estrelas.zquads.service;

import static java.time.LocalDate.now;
import static org.springframework.beans.BeanUtils.copyProperties;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_ALREADY_PRESENT;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.ADMIN_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_REMOVED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.PROJECT_FINISHED;
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
            throw new GenericException(ADMIN_NOT_FOUND);
        }

        Squad squad = new Squad();
        copyProperties(squadDTO, squad);

        User user = admin.get();

        List<User> admins = new ArrayList<>();
        admins.add(user);
        squad.setAdmins(admins);

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

    public Squad addMember(User user, Long idSquad) {

        Optional<Squad> squad = squadRepository.findById(idSquad);
        List<User> members = squad.get().getMembers();
        members.add(user);

        return squadRepository.save(squad.get());
    }

    public Squad removeMember(User user, Long idSquad) {

        Optional<Squad> squad = squadRepository.findById(idSquad);
        List<User> members = squad.get().getMembers();
        members.remove(user);

        return squadRepository.save(squad.get());
    }

}
