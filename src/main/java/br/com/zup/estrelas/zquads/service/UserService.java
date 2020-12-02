package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;

public interface UserService {

    // User

    public User createUser(UserDTO userDTO) throws GenericException;

    public User readUser(String email);

    public List<User> listUsers();

    public User updateUser(String email, UserDTO userDTO) throws GenericException;

    public ResponseDTO deleteUser(String email) throws GenericException;

    // Skill

    public User addSkill(String email, SkillDTO skillDTO) throws GenericException;

    public User deleteSkill(String email, SkillDTO skillDTO) throws GenericException;

}
