package br.com.zup.estrelas.zquads.repository;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    @Transactional
    Optional<User> deleteByEmail(String email);

    Optional<User> findByNickname(String nickname);

}
