package br.com.zup.estrelas.zquads.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.service.FeedElementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/feedElements")
@Api(value = "FeedElement")
public class FeedElementController {

    @Autowired
    FeedElementService feedElementService;
    
    @ApiOperation(value = "Create a feed element")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createFeedElement(@Valid @RequestBody FeedElementDTO feedElementDTO) { 
        return feedElementService.createFeedElement(feedElementDTO);
    }
    
    @ApiOperation(value = "Delete a feed element")
    @DeleteMapping(path = "/{idFeedElement}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteFeedElement(@PathVariable Long idFeedElement) {
        return feedElementService.deleteFeedElement(idFeedElement);
    }
}
