package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.service.SquadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/squads")
@Api(value = "Squad", tags = "Squad")
public class SquadController {

    @Autowired
    SquadService squadService;
    
    @ApiOperation(value = "Create a squad")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Squad createSquad(@RequestBody SquadDTO squad) throws GenericException {
        return squadService.createSquad(squad);
    }

    @ApiOperation(value = "List squads of a currentUser")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Squad> listSquadsCurrentUser() throws GenericException {
        return squadService.listSquadsCurrentUser();
    }
    
    @ApiOperation(value = "List all Squads")
    @GetMapping(path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Squad> listSquads() {
        return squadService.listSquads();
    }
    
    @ApiOperation(value = "List Squads by Repository")
    @GetMapping(path = "/{repository}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Squad> listSquadsByRep(@RequestParam(name = "repository") String repository) {
        return squadService.listByRepository(repository);
    }
    
    @ApiOperation(value = "List Squads by Name")
    @GetMapping(path = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Squad> listSquadsByName(@RequestParam(name = "name") String name) {
        return squadService.listByName(name);
    }

    @ApiOperation(value = "List a squad by your ID")
    @GetMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Squad readSquad(@PathVariable Long idSquad) throws GenericException {
        return squadService.readSquad(idSquad);
    }

    @ApiOperation(value = "Delete a Squad")
    @DeleteMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteSquad(@PathVariable Long idSquad) throws GenericException {
        return squadService.deleteSquad(idSquad);
    }

    @ApiOperation(value = "Change the attributes of a squad")
    @PutMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Squad updateSquad(@PathVariable Long idSquad, @RequestBody SquadDTO squad)
            throws GenericException {
        return squadService.updateSquad(idSquad, squad);
    }

    @ApiOperation(value = "Finish a project of a squad")
    @PutMapping(path = "/finish/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO finishProject(@PathVariable Long idSquad) throws GenericException {
        return squadService.finishProject(idSquad);
    }
    
    @ApiOperation(value = "Add a member in a Squad")
    @PatchMapping(path = "/add/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO addMember(@PathVariable Long idSquad, @RequestBody Long idUser) throws GenericException {
        return squadService.addMember(idUser, idSquad);
    }

    @ApiOperation(value = "Remove a member in a Squad")
    @PatchMapping(path = "/remove/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO removeMember(@PathVariable Long idSquad, @RequestBody Long idUser) throws GenericException {
        return squadService.removeMember(idUser, idSquad);
    }
}
