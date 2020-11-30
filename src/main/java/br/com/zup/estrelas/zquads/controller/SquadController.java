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

@RestController
@RequestMapping("/squad")
public class SquadController {

    // POST
    // GET -: Busca
    // GET -: Listagem
    // DELETE
    // PUT
    @Autowired
    SquadService squadService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Squad> listSquads() {
        return squadService.listSquads();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createSquad(@RequestBody SquadDTO squad) {
        return squadService.createSquad(squad);
    }

    @GetMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Squad readSquad(@PathVariable Long idSquad) {
        return squadService.readSquad(idSquad);
    }

    @DeleteMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteSquad(@PathVariable Long idSquad) {
        return squadService.deleteSquad(idSquad);
    }

    @PutMapping(path = "/{idSquad}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateSquad(@PathVariable Long idSquad, @RequestBody SquadDTO squad) {
        return squadService.updateSquad(idSquad, squad);
    }

}
