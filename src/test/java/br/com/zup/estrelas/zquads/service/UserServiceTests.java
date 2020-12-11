package br.com.zup.estrelas.zquads.service;

import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SUCCESSFULLY_DELETED;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.beans.BeanUtils.copyProperties;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.CreateUserDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SkillRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
    
    private static User generateUser() {
        User user = new User();
        user.setIdUser(1l);
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setNickname("Test");
        user.setPassword("test@password");
        return user;
    }

    private static CreateUserDTO generateUserToSignIn() {
        CreateUserDTO userDTO = new CreateUserDTO();

        userDTO.setName("Test");
        userDTO.setEmail("test@test.com");
        userDTO.setNickname("Test");
        userDTO.setPassword("test@password");
        return userDTO;
    }
    
    private static UserDTO generateUserToUpdate() {
        UserDTO userDTO = new UserDTO();

        userDTO.setName("Modified Test");
        userDTO.setEmail("modified_test@test.com");
        userDTO.setNickname("Modified Test");
        userDTO.setPassword("modified_test@password");
        return userDTO;
    }
    
    @Mock
    UserRepository userRepository;

    @Mock
    SkillRepository skillRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void shouldCreateUserSuccessfully() throws GenericException {

        CreateUserDTO userDTO =  generateUserToSignIn();
        User expectedUser = new User();

        copyProperties(userDTO, expectedUser);

        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User returnedUser = this.userService.createUser(userDTO);

        assertEquals(expectedUser, returnedUser);
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateUserWhenAlreadyExists() throws GenericException {

        Optional <User> user = Optional.of(generateUser());
        Optional <CreateUserDTO> userDTO = Optional.of(generateUserToSignIn());
        
        when(userRepository.findByEmail(userDTO.get().getEmail())).thenReturn(user);

        this.userService.createUser(userDTO.get());
    }

    @Test
    public void shouldReadAnUser() throws GenericException {

        Optional<User> user = Optional.of(generateUser());
        Long idUser = user.get().getIdUser();

        when(userRepository.findById(idUser)).thenReturn(user);

        User foundUser = this.userService.readUser(idUser);

        assertEquals(user, foundUser);
    }

    @Test(expected = GenericException.class)
    public void shouldNotReadAnUserWhenDoesNotExist() throws GenericException {

        this.userService.readUser(any(Long.class));
    }

    @Test
    public void shouldUpdateAnUserSuccessfully() throws GenericException {

        Optional<User> user = Optional.of(generateUser());
        Optional<UserDTO> userDTO = Optional.of(generateUserToUpdate());
        
        copyProperties(userDTO, user);

        when(userRepository.findByEmail(userDTO.get().getEmail())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user.get());

        User returnedUser = this.userService.updateUser(user.get().getIdUser(), userDTO.get());

        assertEquals(user, returnedUser);
    }

    @Test(expected = GenericException.class)
    public void shouldNotUpdateAnUserWhenDoesNotExist() throws GenericException {

        UserDTO userDTO = new UserDTO();

        userDTO.setName("Modified Test");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());

        this.userService.updateUser(123L, userDTO);
    }

    @Test
    public void shouldDeleteAnUserSuccessfully() throws GenericException {

        Optional <User> user = Optional.of(generateUser());

        when(userRepository.findById(1l)).thenReturn(user);

        ResponseDTO returnedResponse = this.userService.deleteUser(user.get().getIdUser());
        ResponseDTO expectedResponse = new ResponseDTO(SUCCESSFULLY_DELETED);

        assertEquals(expectedResponse, returnedResponse);
    }

    @Test(expected = GenericException.class)
    public void shouldNotDeleteAnUserWhenDoesNotExist() throws GenericException {

        when(userRepository.findById(123L)).thenReturn(Optional.empty());

        this.userService.deleteUser(123L);
    }
}
