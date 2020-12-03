package br.com.zup.estrelas.zquads.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.repository.UserRepository;
import br.com.zup.estrelas.zquads.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

        return new MyUserDetails(user);
    }

}
