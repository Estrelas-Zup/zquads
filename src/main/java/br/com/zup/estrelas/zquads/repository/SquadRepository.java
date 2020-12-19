package br.com.zup.estrelas.zquads.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.Squad;

@Repository
public interface SquadRepository extends CrudRepository<Squad, Long> {

    boolean existsByName(String name);
    
    @Query("SELECT s FROM Squad s WHERE s.name LIKE CONCAT('%',:name,'%')")
    List<Squad> findSquadByNameLike(@Param("name") String name);
    
    @Query("SELECT s FROM Squad s WHERE s.repository LIKE CONCAT('%',:repository,'%')")
    List<Squad> findSquadByRepositoryLike(@Param("repository") String repository);
}
