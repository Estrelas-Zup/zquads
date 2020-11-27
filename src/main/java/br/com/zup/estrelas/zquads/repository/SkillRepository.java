package br.com.zup.estrelas.zquads.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.Skill;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    Optional<Skill> findByName(String name);

}
