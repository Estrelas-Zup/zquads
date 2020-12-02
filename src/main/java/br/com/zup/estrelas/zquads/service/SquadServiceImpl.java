package br.com.zup.estrelas.zquads.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
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

    private static final String SQUAD_NOT_EXIST = "Squad does not exist!";
    private static final String SQUAD_REMOVED = "Squad removed";
    private static final String EXISTING_SQUAD = "Existing squad";
    private static final String ADMIN_NOT_EXIST = "Adminstrator does not exist";

    @Autowired
    SquadRepository squadRepository;

    @Autowired
    UserRepository userRepository;

    public Squad createSquad(SquadDTO squadDTO) throws GenericException {

        if (squadRepository.existsByName(squadDTO.getName())) {
            throw new GenericException(EXISTING_SQUAD);
        }

        Optional<User> admin = userRepository.findById(squadDTO.getIdUser());
        if (admin.isEmpty()) {
            throw new GenericException(ADMIN_NOT_EXIST);
        }

        Squad squad = new Squad();
        BeanUtils.copyProperties(squadDTO, squad);

        User user = admin.get();

        List<User> admins = new ArrayList<>();
        admins.add(user);
        squad.setAdmins(admins);

        List<User> members = new ArrayList<>();
        members.add(user);
        squad.setMembers(members);

        return squadRepository.save(squad);
    }

    public Squad readSquad(Long idSquad) {
        return squadRepository.findById(idSquad).orElse(null);
    }

    public List<Squad> listSquads() {
        return (List<Squad>) squadRepository.findAll();
    }

    public Squad updateSquad(Long idSquad, SquadDTO squadDTO) throws GenericException {

        Optional<Squad> consultedSquad = squadRepository.findById(idSquad);

        if (consultedSquad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_EXIST);
        }

        Squad modifiedSquad = consultedSquad.get();
        BeanUtils.copyProperties(squadDTO, modifiedSquad);

        return squadRepository.save(modifiedSquad);
    }

    public ResponseDTO deleteSquad(Long idSquad) {

        if (squadRepository.existsById(idSquad)) {
            squadRepository.deleteById(idSquad);
            return new ResponseDTO(SQUAD_REMOVED);
        }

        return new ResponseDTO(SQUAD_NOT_EXIST);
    }

    public Squad finishProject(Long idSquad) throws GenericException {

        Optional<Squad> squad = squadRepository.findById(idSquad);

        if (squad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_EXIST);
        }

        squad.get().setFinishingDate(LocalDate.now());
        squad.get().setFinished(true);

        return squadRepository.save(squad.get());
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
