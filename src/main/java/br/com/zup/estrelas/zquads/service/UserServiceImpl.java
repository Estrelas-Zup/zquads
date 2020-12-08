package br.com.zup.estrelas.zquads.service;

import java.util.List;
import java.util.Optional;
import static org.springframework.beans.BeanUtils.copyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SkillRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final String DOES_EXIST = "THIS USER ALREADY EXISTS";
    private static final String DOES_NOT_EXIST = "THIS USER DOES NOT EXIST";
    private static final String SUCCESSFULLY_DELETED = "THIS USER WAS DELETED";
    private static final String SKILL_DOES_EXIST = "THIS SKILL ALREADY EXISTS";
    private static final String SKILL_DOES_NOT_EXIST = "THIS SKILL DOES NOT EXIST";

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    // User

    public User createUser(UserDTO userDTO) throws GenericException {

        // Optional<User> user = userRepository.findByNickname(userDTO.getNickname());
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());

        if (user.isPresent()) {
            throw new GenericException(DOES_EXIST);
        }

        User createdUser = new User();
        copyProperties(userDTO, createdUser);
        createdUser.setPassword(encoder.encode(createdUser.getPassword()));

        return userRepository.save(createdUser);
    }

    public User readUser(Long idUser) throws GenericException {
        return userRepository.findById(idUser).orElseThrow(() -> new GenericException(DOES_NOT_EXIST));
    }

    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User updateUser(Long idUser, UserDTO userDTO) throws GenericException {

        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());

        if (user.isEmpty()) {
            throw new GenericException(DOES_NOT_EXIST);
        }

        User updatedUser = user.get();
        copyProperties(userDTO, updatedUser);

        return userRepository.save(updatedUser);
    }

    public ResponseDTO deleteUser(Long idUser) throws GenericException {

        Optional<User> user = userRepository.findById(idUser);

        if (user.isEmpty()) {
            throw new GenericException(DOES_NOT_EXIST);
        }

        userRepository.deleteById(idUser);

        return new ResponseDTO(SUCCESSFULLY_DELETED);
    }

    // Skill

    public User addSkill(Long idUser, SkillDTO skillDTO) throws GenericException {

        Optional<User> user = userRepository.findById(idUser);

        if (user.isEmpty()) {
            throw new GenericException(DOES_NOT_EXIST);
        }

        User updatedUser = user.get();

        Optional<Skill> skill = skillRepository.findByName(skillDTO.getName());

        if (skill.isEmpty()) {
            throw new GenericException(SKILL_DOES_NOT_EXIST);
        }

        Skill searchedSkill = skill.get();
        List<Skill> updatedUserSkills = updatedUser.getSkills();

        for (Skill updatedUserSkill : updatedUserSkills) {

            if (updatedUserSkill.equals(searchedSkill)) {
                throw new GenericException(SKILL_DOES_EXIST);
            }

        }

        updatedUserSkills.add(skill.get());

        return userRepository.save(updatedUser);
    }

    public User deleteSkill(Long idUser, SkillDTO skillDTO) throws GenericException {

        Optional<User> user = userRepository.findById(idUser);

        if (user.isEmpty()) {
            throw new GenericException(DOES_NOT_EXIST);
        }

        User updatedUser = user.get();

        Optional<Skill> skill = skillRepository.findByName(skillDTO.getName());

        if (skill.isEmpty()) {
            throw new GenericException(SKILL_DOES_NOT_EXIST);
        }

        Skill searchedSkill = skill.get();
        List<Skill> updatedUserSkills = updatedUser.getSkills();

        for (Skill updatedUserSkill : updatedUserSkills) {

            if (updatedUserSkill.equals(searchedSkill)) {
                throw new GenericException(SKILL_DOES_EXIST);
            }

        }

        updatedUserSkills.remove(skill.get());

        return userRepository.save(updatedUser);
    }

}
