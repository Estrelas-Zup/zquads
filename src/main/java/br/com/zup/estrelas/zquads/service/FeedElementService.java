package br.com.zup.estrelas.zquads.service;

import br.com.zup.estrelas.zquads.domain.Commentary;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;

public interface FeedElementService {

     ResponseDTO createFeedElement(FeedElementDTO feedElementDTO);
    
     ResponseDTO createCommentary(Commentary commentary);
        
     ResponseDTO deleteFeedElement(Long idFeedElement);
    
}