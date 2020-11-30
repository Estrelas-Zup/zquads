package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.enums.SkillType;

public interface SkillService {
    
    public List<Skill> listSkill();
    
    public List<Skill> searchBySkillType(SkillType type);
    
}
