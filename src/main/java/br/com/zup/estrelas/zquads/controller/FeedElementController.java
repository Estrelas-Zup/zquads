package br.com.zup.estrelas.zquads.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.Commentary;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.service.FeedElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("squads/{idSquad}/feedElements")
@Api(value = "FeedElement", tags = "Feed Element")
public class FeedElementController {

    @Autowired
    FeedElementService feedElementService;

    @ApiOperation(value = "Create a commentary")
    @PostMapping(path = "/commentaries", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public FeedElement createCommentary(@Valid @RequestBody Commentary commentary)
            throws GenericException {
        return feedElementService.createCommentary(commentary);
    }

    @ApiOperation(value = "Delete a feed element")
    @DeleteMapping(path = "/{idFeedElement}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteFeedElement(@PathVariable Long idFeedElement) throws GenericException {
        return feedElementService.deleteFeedElement(idFeedElement);
    }

}
