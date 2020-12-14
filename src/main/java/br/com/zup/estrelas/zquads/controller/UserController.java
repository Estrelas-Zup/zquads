package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "User", tags = "User")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Sign up an user")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserDTO user) throws GenericException {
        return userService.createUser(user);
    }

    @ApiOperation(value = "Get current user info")
    @GetMapping(path = "/userInfo", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User readUser() throws GenericException {
        User currentUser = userService.getCurrentUser();
        return userService.readUser(currentUser.getIdUser());
    }

    @ApiOperation(value = "List all users")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @ApiOperation(value = "Change attributes of an user")
    @PutMapping(path = "/{idUser}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@PathVariable Long idUser, @Valid @RequestBody UserDTO user) throws GenericException {
        return userService.updateUser(idUser, user);
    }

    @ApiOperation(value = "Delete an account")
    @DeleteMapping(path = "/{idUser}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteUser(@PathVariable Long idUser) throws GenericException {
        return userService.deleteUser(idUser);
    }

    // Skill

    @ApiOperation(value = "Add a skill in a list of an user")
    @PutMapping(path = "/{idUser}/addSkill/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User addSkill(@PathVariable Long idUser, @Valid @RequestBody SkillDTO skill) throws GenericException {
        return userService.addSkill(idUser, skill);
    }
    
    @ApiOperation(value = "Remove a skill in a list of an user")
    @PutMapping(path = "/{idUser}/deleteSkill/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User deleteSkill(@PathVariable Long idUser, @Valid @RequestBody SkillDTO skill) throws GenericException {
        return userService.deleteSkill(idUser, skill);
    }

}
