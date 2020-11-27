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
import br.com.zup.estrelas.zquads.dto.UserDTO;
import br.com.zup.estrelas.zquads.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createSecretariat(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User readSecretariat(@PathVariable String email) {
        return userService.readUser(email);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @PutMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateUser(@PathVariable String email, @RequestBody UserDTO user) {
        return userService.updateUser(email, user);
    }

    @DeleteMapping(path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }

}
