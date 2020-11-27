package br.com.zup.estrelas.zquads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.repository.SkillRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private static final String DOES_EXIST = "THIS USER ALREADY EXISTS";
    private static final String DOES_NOT_EXIST = "THIS USER DOES NOT EXIST";
    private static final String SKILL_DOES_EXIST = "THIS SKILL ALREADY EXISTS";
    private static final String SKILL_DOES_NOT_EXIST = "THIS SKILL DOES NOT EXIST";
    private static final String SUCCESSFULLY_CREATED = "THE USER WAS SUCCESSFULLY CREATED";
    private static final String SUCCESSFULLY_UPDATED = "THE USER WAS SUCCESSFULLY UPDATED";
    private static final String SUCESSFULLY_DELETED = "THE SECRETARIAT WAS SUCCESSFULLY DELETED";
    private static final String SKILL_SUCCESSFULLY_ADDED = "THE SKILL WAS SUCCESSFULLY ADDED";

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    // User

    public ResponseDTO createUser(UserDTO userDTO) {

        // Optional<User> user = userRepository.findByNickname(userDTO.getNickname());
        Optional<User> user = userRepository.findByNickname(userDTO.getEmail());

        if (user.isPresent()) {
            return new ResponseDTO(DOES_EXIST);
        }

        User createdUser = new User();
        BeanUtils.copyProperties(userDTO, createdUser);

        userRepository.save(createdUser);

        return new ResponseDTO(SUCCESSFULLY_CREATED);
    }

    public User readUser(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    public ResponseDTO updateUser(String email, UserDTO userDTO) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return new ResponseDTO(DOES_NOT_EXIST);
        }

        User updatedUser = user.get();
        BeanUtils.copyProperties(userDTO, updatedUser);

        userRepository.save(updatedUser);

        return new ResponseDTO(SUCCESSFULLY_UPDATED);
    }

    public ResponseDTO deleteUser(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return new ResponseDTO(DOES_NOT_EXIST);
        }

        userRepository.deleteByEmail(email);

        return new ResponseDTO(SUCESSFULLY_DELETED);
    }

    // Skill

    public ResponseDTO addSkill(String email, SkillDTO skillDTO) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return new ResponseDTO(DOES_NOT_EXIST);
        }

        User updatedUser = user.get();
        List<Skill> skillList = new ArrayList<Skill>();

        Optional<Skill> skill = skillRepository.findByName(skillDTO.getName());

        if (skill.isEmpty()) {
            return new ResponseDTO(SKILL_DOES_NOT_EXIST);
        }

        Skill searchedSkill = skill.get();
        List<Skill> updatedUserSkills = updatedUser.getSkills();
        
        for (Skill updatedUserSkill : updatedUserSkills) {
            
            if (updatedUserSkill.equals(searchedSkill)) {
                return new ResponseDTO(SKILL_DOES_EXIST);
            }
            
        }
        
        skillList.add(skill.get());
        updatedUser.setSkills(skillList);
        
        userRepository.save(updatedUser);

        return new ResponseDTO(SKILL_SUCCESSFULLY_ADDED);
    }

}
