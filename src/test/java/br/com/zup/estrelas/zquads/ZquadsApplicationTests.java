package br.com.zup.estrelas.zquads;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.enums.Role;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@SpringBootTest
class ZquadsApplicationTests {

    Logger logger = LogManager.getLogger(ZquadsApplicationTests.class);
    
    @Autowired
    UserRepository userRepo;
    

    @Test
    void contextLoads() {

        User userTeste = new User();
        userTeste.setName("João");
        userTeste.setEmail("teste@teste.com");
        userTeste.setNickname("João");
        userTeste.setRole(Role.BACKEND);

        List<User> amigos = new ArrayList<User>();
        User userTesteF = new User();
        userTesteF.setName("João");
        userTesteF.setEmail("teste@teste.com");
        userTesteF.setNickname("João");
        userTesteF.setRole(Role.FRONTEND);
        User userDb = userRepo.save(userTeste);
        User friendDb = userRepo.save(userTesteF);


        amigos.add(friendDb);
        userDb.setFriends(amigos);
        userRepo.save(userDb);
    }
    
    @Test
    public void getUser() {
        List<User> usuarios = (List<User>) userRepo.findAll();
        
        
        List<User> friends = usuarios.get(0).getFriends();
        List<User> friends1 = usuarios.get(1).getFriends();
        
        usuarios.forEach(user -> {
            logger.error(user.toString());
        });
        System.out.println("Stop Here");
    }

}
