package br.com.zup.estrelas.zquads.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    public boolean existsByEmail(String email);
}
