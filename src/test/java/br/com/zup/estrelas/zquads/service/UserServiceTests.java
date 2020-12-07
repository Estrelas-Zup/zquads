package br.com.zup.estrelas.zquads.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.beans.BeanUtils.copyProperties;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SkillRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    private static final String SUCCESSFULLY_DELETED = "THIS USER WAS DELETED";

    @Mock
    UserRepository userRepository;

    @Mock
    SkillRepository skillRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void shouldCreateUser() throws GenericException {

        UserDTO userDTO = new UserDTO();

        userDTO.setName("Test");
        userDTO.setEmail("test@test.com");
        userDTO.setNickname("Test");
        userDTO.setPassword("test@password");

        User expectedUser = new User();

        copyProperties(userDTO, expectedUser);

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User returnedUser = this.userService.createUser(userDTO);

        assertEquals(expectedUser, returnedUser);
    }

    @Test(expected = GenericException.class)
    public void shouldNotCreateUserWhenAlreadyExists() throws GenericException {

        User user = new User();

        user.setName("Test");
        user.setEmail("test@test.com");
        user.setNickname("Test");
        user.setPassword("test@password");

        UserDTO userDTO = new UserDTO();

        userDTO.setName("Test");
        userDTO.setEmail("test@test.com");
        userDTO.setNickname("Test");
        userDTO.setPassword("test@password");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(user));

        this.userService.createUser(userDTO);
    }

    @Test
    public void shouldReadAnUser() {

        User user = new User();

        user.setName("Test");
        user.setEmail("test@test.com");
        user.setNickname("Test");
        user.setPassword("test@password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = this.userService.readUser(1L);

        assertEquals(user, foundUser);
    }

    @Test
    public void shouldNotReadAnUserWhenDoesNotExist() {

        User foundUser = this.userService.readUser(1L);

        assertNull(foundUser);
    }

    @Test
    public void shouldUpdateAnUser() throws GenericException {

        User user = new User();

        user.setIdUser(123L);
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setNickname("Test");
        user.setPassword("test@password");

        UserDTO userDTO = new UserDTO();

        userDTO.setName("Modified Test");
        userDTO.setEmail("modified_test@test.com");
        userDTO.setNickname("Modified Test");
        userDTO.setPassword("modified_test@password");

        User expectedUser = new User();

        expectedUser.setIdUser(123L);
        copyProperties(userDTO, expectedUser);

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User returnedUser = this.userService.updateUser(123L, userDTO);

        assertEquals(expectedUser, returnedUser);
    }

    @Test(expected = GenericException.class)
    public void shouldNotUpdateAnUserWhenDoesNotExist() throws GenericException {

        UserDTO userDTO = new UserDTO();

        userDTO.setName("Modified Test");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());

        this.userService.updateUser(123L, userDTO);
    }

    @Test
    public void shouldDeleteAnUser() throws GenericException {

        User user = new User();

        user.setIdUser(123L);
        user.setName("Test");
        user.setEmail("test@test.com");
        user.setNickname("Test");
        user.setPassword("test@password");

        when(userRepository.findById(123L)).thenReturn(Optional.of(user));

        ResponseDTO returnedResponse = this.userService.deleteUser(123L);
        ResponseDTO expectedResponse = new ResponseDTO(SUCCESSFULLY_DELETED);

        assertEquals(expectedResponse, returnedResponse);
    }

    @Test(expected = GenericException.class)
    public void shouldNotDeleteAnUserWhenDoesNotExist() throws GenericException {

        when(userRepository.findById(123L)).thenReturn(Optional.empty());

        this.userService.deleteUser(123L);
    }
}
