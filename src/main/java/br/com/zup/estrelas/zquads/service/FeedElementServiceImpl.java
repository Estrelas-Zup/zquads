package br.com.zup.estrelas.zquads.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.repository.FeedElementRepository;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class FeedElementServiceImpl implements FeedElementService {

    private static final String FEED_ELEMENT_CREATED_SUCESSFULLY = "The feed element has been created sucessfully.";
    private static final String FEED_ELEMENT_DELETE_SUCESSFULLY = "The feed element has been deleted sucessfully.";
    private static final String FEED_ELEMENT_NOT_FOUND = "This feed element not found.";
    private static final String SQUAD_NOT_FOUND = "This squad not found.";
    private static final String USER_NOT_FOUND = "This user not found.";
    private static final String TYPE_NOT_FOUND = "This feed element type not found.";
    
    
    @Autowired
    FeedElementRepository feedElementRepository;
    
    @Autowired
    SquadRepository squadRepository;
    
    @Autowired
    UserRepository userRepository;

    public ResponseDTO createFeedElement(FeedElementDTO feedElementDTO) {
       
       Long squadToBeQuery = feedElementDTO.getIdSquad();
       Long idUserToBeQuery = feedElementDTO.getIdUser();
       
       Optional<Squad> squad = squadRepository.findById(squadToBeQuery);
        if(squad.isEmpty()) {
            return new ResponseDTO(SQUAD_NOT_FOUND);
        } 
           
        Optional<User> user = userRepository.findById(idUserToBeQuery);
        if(user.isEmpty()) {
            return new ResponseDTO(USER_NOT_FOUND);
        }

        FeedElement feedElementDB = new FeedElement();
        BeanUtils.copyProperties (feedElementDTO, feedElementDB);
        feedElementDB.setAuthor(user.get());
        feedElementDB.setSquad(squad.get());
        feedElementRepository.save(feedElementDB);
        
        return new ResponseDTO(FEED_ELEMENT_CREATED_SUCESSFULLY);
    }

    public ResponseDTO deleteFeedElement(Long idFeedElement) {
        
        if(!feedElementRepository.existsById(idFeedElement)) {
            return new ResponseDTO(FEED_ELEMENT_NOT_FOUND);
        }
        
        feedElementRepository.deleteById(idFeedElement);
        return new ResponseDTO(FEED_ELEMENT_DELETE_SUCESSFULLY);
    }
}
