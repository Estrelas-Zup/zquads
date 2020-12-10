package br.com.zup.estrelas.zquads.service;

import static org.springframework.beans.BeanUtils.copyProperties;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.FEED_ELEMENT_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.FEED_ELEMENT_DELETE_SUCESSFULLY;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Commentary;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.FeedElementRepository;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class FeedElementServiceImpl implements FeedElementService {


    @Autowired
    FeedElementRepository feedElementRepository;

    @Autowired
    SquadRepository squadRepository;

    @Autowired
    UserRepository userRepository;

    public FeedElement createFeedElement(FeedElementDTO feedElementDTO) throws GenericException {

        Long squadToBeQuery = feedElementDTO.getIdSquad();
        Long idUserToBeQuery = feedElementDTO.getIdUser();

        Optional<Squad> squad = squadRepository.findById(squadToBeQuery);
        if (squad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        Optional<User> user = userRepository.findById(idUserToBeQuery);
        if (user.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }

        FeedElement feedElementDB = new FeedElement();
        copyProperties(feedElementDTO, feedElementDB);

        return this.feedElementRepository.save(feedElementDB);
    }

    public ResponseDTO deleteFeedElement(Long idSquad, Long idFeedElement) throws GenericException {

        Optional<Squad> squad = this.squadRepository.findById(idSquad);
        if (squad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        if (!feedElementRepository.existsById(idFeedElement)) {
            throw new GenericException(FEED_ELEMENT_NOT_FOUND);
        }

        feedElementRepository.deleteById(idFeedElement);
        return new ResponseDTO(FEED_ELEMENT_DELETE_SUCESSFULLY);
    }

    public FeedElement createCommentary(Long idSquad, Commentary commentary)
            throws GenericException {

        Optional<Squad> squad = this.squadRepository.findById(idSquad);
        if (squad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        FeedElementDTO feedElementDTO = new FeedElementDTO();
        copyProperties(commentary, feedElementDTO);
        feedElementDTO.setIdSquad(idSquad);

        return this.createFeedElement(feedElementDTO);
    }
}
