package br.com.zup.estrelas.zquads.service;

import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.FEED_ELEMENT_DELETE_SUCESSFULLY;
import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.beans.BeanUtils.copyProperties;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.zquads.domain.Commentary;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.enums.FeedElementType;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.FeedElementRepository;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;


@RunWith(MockitoJUnitRunner.class)
public class FeedElementServiceTests {

    private static User generateUser() {
        User user = new User();
        
        user.setIdUser(1L);
        user.setName("José");
        user.setEmail("jose@email.com");
        user.setNickname("zeTeste");
        user.setPassword("1234");
        
        return user;
    }
    
    private static Squad generateSquad() {
        
        Squad squad = new Squad();
        
        squad.setIdSquad(1L);
        squad.setName("Squad Teste");
        squad.setProjectName("Projeto teste");
        squad.setBio("Descrição do projeto teste");
        squad.setFinished(false);
        
        return squad;
    }
    
    private static FeedElement generateFeedElement() {
        
        FeedElement feedElement = new FeedElement();
        
        feedElement.setIdFeedElement(1L);
        feedElement.setIdUser(1L);
        feedElement.setName("Nome do feed element");
        feedElement.setDate(now());
        feedElement.setContent("Conteúdo teste de um feed element");
        feedElement.setIdSquad(1L);
        feedElement.setType(FeedElementType.TASK);
        
        return feedElement;
    }
    
    private static FeedElementDTO generateFeedElementDTO() {
        
        FeedElementDTO feedElementDTO = new FeedElementDTO();
        
        feedElementDTO.setIdUser(1L);
        feedElementDTO.setContent("Conteúdo teste de um feed element");
        feedElementDTO.setIdSquad(1L);
        feedElementDTO.setType(FeedElementType.TASK);
        
        return feedElementDTO;
    }
    
    private static Commentary generateCommentary() {
        
        Commentary commentary = new Commentary();
        commentary.setIdUser(1L);
        commentary.setContent("Um comentário para testes");
        
        return commentary;
    }
    
    @Mock
    FeedElementRepository feedElementRepository;

    @Mock
    SquadRepository squadRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    FeedElementServiceImpl feedElementService;


    @Test
    public void shouldCreateAFeedElementSuccessfully() throws GenericException {

        Optional<User> user = Optional.of(generateUser());
        Optional<Squad> squad = Optional.of(generateSquad());
        FeedElementDTO feedElementDTO = generateFeedElementDTO();

        Long idSquad = feedElementDTO.getIdSquad();
        Long idUser = feedElementDTO.getIdUser();

        FeedElement expectedFeedElement = new FeedElement();
        copyProperties(feedElementDTO, expectedFeedElement);

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        when(feedElementRepository.save(any(FeedElement.class))).thenReturn(expectedFeedElement);

        FeedElement returnedFeedElement = this.feedElementService.createFeedElement(feedElementDTO);

        assertEquals(expectedFeedElement, returnedFeedElement);
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateFeedElementIfUserNotFound() throws GenericException {

        Optional<Squad> squad = Optional.of(generateSquad());
        Optional<User> user = Optional.empty();

        FeedElementDTO feedElementDTO = generateFeedElementDTO();
        Long idSquad = feedElementDTO.getIdSquad();
        Long idUser = 1L;

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        this.feedElementService.createFeedElement(feedElementDTO);
        
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateFeedElementIfSquadNotFound() throws GenericException {

        Optional<Squad> squad = Optional.empty();
        Optional<User> user = Optional.of(generateUser());

        Long idUser = user.get().getIdUser();
        Long idSquad = 1L;

        FeedElementDTO feedElementDTO = generateFeedElementDTO();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        this.feedElementService.createFeedElement(feedElementDTO);
    }
    
    @Test
    public void shouldCreateACommentarySuccessfully() throws GenericException {

        Optional<User> user = Optional.of(generateUser());
        Optional<Squad> squad = Optional.of(generateSquad());
        Commentary commentary = generateCommentary();

        Long idSquad = squad.get().getIdSquad();
        Long idUser = user.get().getIdUser();

        FeedElement expectedFeedElement = new FeedElement();
        copyProperties(commentary, expectedFeedElement);

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        when(feedElementRepository.save(any(FeedElement.class))).thenReturn(expectedFeedElement);

        FeedElement returnedFeedElement =
                this.feedElementService.createCommentary(idSquad, commentary);

        assertEquals(expectedFeedElement, returnedFeedElement);
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateACommentaryIfSquadNotFound() throws GenericException {

        Optional<User> user = Optional.of(generateUser());
        Optional<Squad> squad = Optional.empty();
        Commentary commentary = generateCommentary();

        Long idUser = user.get().getIdUser();
        Long idSquad = 1L;

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        this.feedElementService.createCommentary(idSquad, commentary);
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateACommentaryIfUserNotFound() throws GenericException {

        Optional<Squad> squad = Optional.of(generateSquad());
        Long idSquad = squad.get().getIdSquad();
        Optional<User> user = Optional.empty();
        Long idUser = 1L;

        Commentary commentary = generateCommentary();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        this.feedElementService.createCommentary(idSquad, commentary);
    }

    @Test
    public void mustSucceedWhenDeleteAFeedElement() throws GenericException {

        Optional<Squad> squad = Optional.of(generateSquad());
        Long idSquad = squad.get().getIdSquad();

        Optional<FeedElement> feedElement = Optional.of(generateFeedElement());
        Long idFeedElement = feedElement.get().getIdFeedElement();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(true);

        ResponseDTO expectedResponse =
                this.feedElementService.deleteFeedElement(idSquad, idFeedElement);
        ResponseDTO returnedResponse = new ResponseDTO(FEED_ELEMENT_DELETE_SUCESSFULLY);

        assertEquals(returnedResponse, expectedResponse);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenDeleteAFeedElementIfSquadNotExists() throws GenericException {

        Optional<Squad> squad = Optional.empty();
        Long idSquad = 1L;

        Optional<FeedElement> feedElement = Optional.of(generateFeedElement());
        Long idFeedElement = feedElement.get().getIdFeedElement();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(true);

        this.feedElementService.deleteFeedElement(idSquad, idFeedElement);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenDeleteAFeedElementIfFeedElementNotExists() throws GenericException {

        Optional<Squad> squad = Optional.of(generateSquad());
        Long idSquad = squad.get().getIdSquad();

        Long idFeedElement = 1L;

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(false);

        this.feedElementService.deleteFeedElement(idSquad, idFeedElement);

    }
}


