package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.CreateUserDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.enums.Gender;
import br.com.zup.estrelas.zquads.enums.Race;
import br.com.zup.estrelas.zquads.enums.Role;
import br.com.zup.estrelas.zquads.enums.SexualOrientation;
import br.com.zup.estrelas.zquads.exception.GenericException;

public interface UserService {

    // User

    public User createUser(CreateUserDTO userDTO) throws GenericException;

    public User searchUser(Long idUser) throws GenericException;

    public List<User> listUsers();

    public List<User> listUsersByName(String name);
    
    public List<User> listUsersByGender(Gender gender);
    
    public List<User> listUsersByRace(Race race);
    
    public List<User> listUsersByRole(Role role);
    
    public List<User> listUsersBySexOrientation(SexualOrientation sexOrientation);
    
    public User updateUser(Long idUser, UserDTO userDTO) throws GenericException;

    public ResponseDTO deleteUser(Long idUser) throws GenericException;

    // Skill

    public User addSkill(Long idUser, SkillDTO skillDTO) throws GenericException;

    public User deleteSkill(Long idUser, SkillDTO skillDTO) throws GenericException;

    // Auth
    
    public User getCurrentUser();
}
