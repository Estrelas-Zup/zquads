package br.com.zup.estrelas.zquads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.repository.FeedElementRepository;

@Service
public class FeedElementServiceImpl implements FeedElementService {

    @Autowired
    FeedElementRepository feedElementRepository;
    
    public ResponseDTO createFeedElement(FeedElementDTO feedElementDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseDTO deleteFeedElement(Long idFeedElement) {
        // TODO Auto-generated method stub
        return null;
    }

}
