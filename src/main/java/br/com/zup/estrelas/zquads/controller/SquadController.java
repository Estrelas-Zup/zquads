package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;
import br.com.zup.estrelas.zquads.service.SquadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/squad")
@Api(value = "Squad")
public class SquadController {

  
    @Autowired
    SquadService squadService;
    
    @ApiOperation(value = "Create a squad")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createSquad(@RequestBody SquadDTO squad) {
        return squadService.createSquad(squad);
    }
    
    @ApiOperation(value = "List all squads")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Squad> listSquads() {
        return squadService.listSquads();
    }

    @ApiOperation(value = "List a squad by your ID")
    @GetMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Squad readSquad(@PathVariable Long idSquad) {
        return squadService.readSquad(idSquad);
    }

    @ApiOperation(value = "Delete a Squad")
    @DeleteMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteSquad(@PathVariable Long idSquad) {
        return squadService.deleteSquad(idSquad);
    }

    @ApiOperation(value = "Change the attributes of a squad")
    @PutMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateSquad(@PathVariable Long idSquad, @RequestBody SquadDTO squad) {
        return squadService.updateSquad(idSquad, squad);
    }
    
    @ApiOperation(value = "Finish a project of a squad")
    @PutMapping(path = "/finish/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO finishProject(@PathVariable Long idSquad) {
        return squadService.finishProject(idSquad);
    }

}
