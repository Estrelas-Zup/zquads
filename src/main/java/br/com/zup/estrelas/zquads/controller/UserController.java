package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SkillDTO;
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "User", tags = "User")
public class UserController {

    @Autowired
    UserService userService;

    // User

    @ApiOperation(value = "Sign up an user")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @ApiOperation(value = "List an user by your email")
    @GetMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User readUser(@PathVariable String email) {
        return userService.readUser(email);
    }

    @ApiOperation(value = "List all users")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @ApiOperation(value = "Change attributes of an user")
    @PutMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateUser(@PathVariable String email, @RequestBody UserDTO user) {
        return userService.updateUser(email, user);
    }

    @ApiOperation(value = "Delete an account")
    @DeleteMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }

    // Skill

    @ApiOperation(value = "Add a skill in a list of an user")
    @PutMapping(path = "/addSkill/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO addSkill(@PathVariable String email, @RequestBody SkillDTO skill) {
        return userService.addSkill(email, skill);
    }
    
    @ApiOperation(value = "Remove a skill in a list of an user")
    @PutMapping(path = "/deleteSkill/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteSkill(@PathVariable String email, @RequestBody SkillDTO skill) {
        return userService.deleteSkill(email, skill);
    }

}
