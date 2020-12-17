package br.com.zup.estrelas.zquads.service;

import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SKILL_ALREADY_PRESENT;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SKILL_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SUCCESSFULLY_DELETED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_ALREADY_PRESENT;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_NOT_FOUND;
import static org.springframework.beans.BeanUtils.copyProperties;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.CreateUserDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SkillRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;
import br.com.zup.estrelas.zquads.security.MyUserDetails;

@Service
public class UserServiceImpl implements UserService {

    

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    // User

    public User createUser( CreateUserDTO userDTO) throws GenericException {

        boolean userExists = userRepository.existsByEmail(userDTO.getEmail());

        if (userExists) {
            throw new GenericException(USER_ALREADY_PRESENT);
        }

        User createdUser = new User();
        copyProperties(userDTO, createdUser);
        createdUser.setPassword(encoder.encode(createdUser.getPassword()));

        return userRepository.save(createdUser);
    }

    public User searchUser(Long idUser) throws GenericException {
        return userRepository.findById(idUser).orElseThrow(() -> new GenericException(USER_NOT_FOUND));
    }


    public User updateUser(Long idUser, UserDTO userDTO) throws GenericException {

        Optional<User> user = userRepository.findById(idUser);

        if (user.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }

        User updatedUser = user.get();
        copyProperties(userDTO, updatedUser);

        return userRepository.save(updatedUser);
    }

    public ResponseDTO deleteUser(Long idUser) throws GenericException {

        Optional<User> user = userRepository.findById(idUser);

        if (user.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }

        userRepository.deleteById(idUser);

        return new ResponseDTO(SUCCESSFULLY_DELETED);
    }

    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    // Skill

    public User addSkill(Long idUser, SkillDTO skillDTO) throws GenericException {

        Optional<User> user = userRepository.findById(idUser);

        if (user.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }

        User updatedUser = user.get();

        Optional<Skill> skill = skillRepository.findByName(skillDTO.getName());

        if (skill.isEmpty()) {
            throw new GenericException(SKILL_NOT_FOUND);
        }

        Skill searchedSkill = skill.get();
        List<Skill> updatedUserSkills = updatedUser.getSkills();

        if (updatedUserSkills.contains(searchedSkill)) {
            throw new GenericException(SKILL_ALREADY_PRESENT);
        }

        updatedUserSkills.add(skill.get());

        return userRepository.save(updatedUser);
    }

    public User deleteSkill(Long idUser, SkillDTO skillDTO) throws GenericException {

        Optional<User> searchedUser = userRepository.findById(idUser);

        if (searchedUser.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }
        User user = searchedUser.get();

        Optional<Skill> skill = skillRepository.findByName(skillDTO.getName());

        if (skill.isEmpty()) {
            throw new GenericException(SKILL_NOT_FOUND);
        }

        Skill searchedSkill = skill.get();
        List<Skill> listUserSkills = user.getSkills();

        if (listUserSkills.contains(searchedSkill)) {
            listUserSkills.remove(searchedSkill);
        }

        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

}
