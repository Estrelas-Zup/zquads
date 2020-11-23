package br.com.zup.estrelas.zquads.service;

import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;

public interface FeedElementService {

    public ResponseDTO createFeedElement(FeedElementDTO feedElementDTO);
        
    public ResponseDTO deleteFeedElement(Long idFeedElement);
    
}