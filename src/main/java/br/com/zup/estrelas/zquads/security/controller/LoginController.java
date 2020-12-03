package br.com.zup.estrelas.zquads.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.security.JwtTokenUtil;
import br.com.zup.estrelas.zquads.security.dto.LoginDTO;
import br.com.zup.estrelas.zquads.security.dto.TokenDTO;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenDTO criaToken(@RequestBody LoginDTO loginInfo) throws Exception {
        auth(loginInfo.getEmail(), loginInfo.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginInfo.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        return new TokenDTO(token);
    }

    private void auth(String login, String senha) throws Exception {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login, senha));
        } catch (DisabledException e) {
            // Tratar usuário desabilitadu.
        } catch (BadCredentialsException e) {
            // Tratar credencial inválida.
        }
    }
}
