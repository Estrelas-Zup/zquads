package br.com.zup.estrelas.zquads.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;

    public ResponseDTO createUser(UserDTO userDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public User readUser(Long idUser) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<User> listUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseDTO updateUser(Long idUser, UserDTO userDTO) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ResponseDTO deleteUser(Long idUser) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
