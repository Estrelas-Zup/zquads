package br.com.zup.estrelas.zquads.service;

import br.com.zup.estrelas.zquads.domain.Commentary;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;

public interface FeedElementService {

    FeedElement createFeedElement(FeedElementDTO feedElementDTO) throws GenericException;

    FeedElement createCommentary(Commentary commentary) throws GenericException;

    ResponseDTO deleteFeedElement(Long idFeedElement) throws GenericException;

}
