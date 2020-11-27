package br.com.zup.estrelas.zquads.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.enums.SkillType;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    List<Skill> findByType(SkillType type);
}
