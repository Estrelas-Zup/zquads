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

    public User readUser(Long idUser) throws GenericException;

    public List<User> listUsers();

    public User updateUser(Long idUser, UserDTO userDTO) throws GenericException;

    public ResponseDTO deleteUser(Long idUser) throws GenericException;

    // Skill

    public User addSkill(Long idUser, SkillDTO skillDTO) throws GenericException;

    public User deleteSkill(Long idUser, SkillDTO skillDTO) throws GenericException;

    // Auth
    
    public User getCurrentUser();
}
