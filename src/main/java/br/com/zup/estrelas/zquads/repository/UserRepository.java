package br.com.zup.estrelas.zquads.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.enums.Gender;
import br.com.zup.estrelas.zquads.enums.Race;
import br.com.zup.estrelas.zquads.enums.Role;
import br.com.zup.estrelas.zquads.enums.SexualOrientation;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.name LIKE CONCAT('%',:name,'%')")
    List<User> findUserByNameLike(@Param("name")String name);
    
    List<User> findByGender(@Param("gender") Gender gender);
    
    List<User> findByRace(@Param("race") Race race);
    
    List<User> findBySexualOrientation(@Param("sexual_orientation") SexualOrientation sexOrientation);
    
    List<User> findByRole(@Param("role") Role role);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

}
