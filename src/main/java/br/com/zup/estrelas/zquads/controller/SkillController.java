package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.enums.SkillType;
import br.com.zup.estrelas.zquads.service.SkillServiceImpl;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillServiceImpl skillService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Skill> listSkill() {
        return skillService.listSkill();
    }
    
    @GetMapping(path = "/{type}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Skill> searchBySkillType(@PathVariable SkillType type){
        return skillService.searchBySkillType(type);
    }

}
