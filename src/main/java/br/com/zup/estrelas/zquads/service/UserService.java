package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;

public interface UserService {

    public ResponseDTO createUser(UserDTO userDTO);
    
    public User readUser(Long idUser);
    
    public List<User> listUsers();
    
    public ResponseDTO updateUser(Long idUser, UserDTO userDTO);
    
    public ResponseDTO deleteUser(Long idUser);
    
}
