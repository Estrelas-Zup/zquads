package br.com.zup.estrelas.zquads.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.Squad;

@Repository
public interface SquadRepository extends CrudRepository<Squad, Long> {

    boolean existsByName(String name);

}
