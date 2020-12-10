package br.com.zup.estrelas.zquads.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
    boolean existsByEmail(String email);
    
    Optional<User> findByEmail(String email);
    
    Optional<User> findByName(String name);

    Optional<User> findByNickname(String nickname);

}
