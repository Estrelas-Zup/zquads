package br.com.zup.estrelas.zquads.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.enums.SkillType;
import br.com.zup.estrelas.zquads.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> listSkill() {
        return (List<Skill>) skillRepository.findAll();
    }

    public List<Skill> searchBySkillType(SkillType type) {
        return skillRepository.findByType(type);
    }

}
