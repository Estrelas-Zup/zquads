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
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class SquadServiceImpl implements SquadService {

    private static final String SQUAD_NOT_EXIST = "Squad does not exist!";
    private static final String SQUAD_REMOVED = "Squad removed";
    private static final String SUCCESSFULLY_CREATED_SQUAD = "Successfully created squad";
    private static final String EXISTING_SQUAD = "Existing squad";
    private static final String AMINSTRATOR_NOT_EXIST = "Adminstrator does not exist";
    private static final String SUCCESSFULLY_UPDATE = "Successfully updated";
    private static final String SQUAD_FINISHED = "Squad finished";
    private static final String MEMBER_ADDED = "Membro added";
    private static final String REMOVED_MEMBER = "Removed member";

    @Autowired
    SquadRepository squadRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseDTO createSquad(SquadDTO squadDTO) {

        if (squadRepository.existsByName(squadDTO.getName())) {
            return new ResponseDTO(EXISTING_SQUAD);
        }

        Optional<User> admin = userRepository.findById(squadDTO.getIdUser());
        if (admin.isEmpty()) {
            return new ResponseDTO(AMINSTRATOR_NOT_EXIST);
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

        squadRepository.save(squad);
        return new ResponseDTO(SUCCESSFULLY_CREATED_SQUAD);
    }

    public Squad readSquad(Long idSquad) {
        return squadRepository.findById(idSquad).orElse(null);
    }

    public List<Squad> listSquads() {
        return (List<Squad>) squadRepository.findAll();
    }

    public ResponseDTO updateSquad(Long idSquad, SquadDTO squadDTO) {

        Optional<Squad> consultedsquad = squadRepository.findById(idSquad);

        if (consultedsquad.isPresent()) {

            Squad modifiedSquad = consultedsquad.get();
            BeanUtils.copyProperties(squadDTO, modifiedSquad);
            squadRepository.save(modifiedSquad);
            return new ResponseDTO(SUCCESSFULLY_UPDATE);
        }

        return new ResponseDTO(SQUAD_NOT_EXIST);
    }

    public ResponseDTO deleteSquad(Long idSquad) {

        if (squadRepository.existsById(idSquad)) {
            squadRepository.deleteById(idSquad);
            return new ResponseDTO(SQUAD_REMOVED);
        }

        return new ResponseDTO(SQUAD_NOT_EXIST);
    }

    public ResponseDTO finishProject(Long idSquad) {
        Optional<Squad> squad = squadRepository.findById(idSquad);
        if (squad.isEmpty()) {
            return new ResponseDTO(SQUAD_NOT_EXIST);
        }
        squad.get().setFinishingDate(LocalDate.now());
        squad.get().setFinished(true);
        squadRepository.save(squad.get());
        return new ResponseDTO(SQUAD_FINISHED);
    }

    public ResponseDTO addMember(User user, Long idSquad) {

        Optional<Squad> squad = squadRepository.findById(idSquad);
        List<User> members = squad.get().getMembers();
        members.add(user);
        squadRepository.save(squad.get());
        return new ResponseDTO(MEMBER_ADDED);
    }

    public ResponseDTO removeMember(User user, Long idSquad) {
        Optional<Squad> squad = squadRepository.findById(idSquad);
        List<User> members = squad.get().getMembers();
        members.remove(user);
        squadRepository.save(squad.get());
        return new ResponseDTO(REMOVED_MEMBER);
    }

}
