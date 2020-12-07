package br.com.zup.estrelas.zquads.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.zquads.domain.Address;
import br.com.zup.estrelas.zquads.domain.Commentary;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.domain.Skill;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.enums.FeedElementType;
import br.com.zup.estrelas.zquads.enums.Gender;
import br.com.zup.estrelas.zquads.enums.Race;
import br.com.zup.estrelas.zquads.enums.Role;
import br.com.zup.estrelas.zquads.enums.SexualOrientation;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.FeedElementRepository;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class FeedElementServiceTests {

    private static final String FEED_ELEMENT_DELETE_SUCESSFULLY =
            "Feed Element was sucessfully deleted.";
    private static final String THIS_SQUAD_DOES_NOT_EXIST = "this squad doesn't exist";
    private static final String FEED_ELEMENT_NOT_FOUND = "This feed element not found.";

    @Mock
    FeedElementRepository feedElementRepository;

    @Mock
    SquadRepository squadRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    FeedElementServiceImpl feedElementService;

    @InjectMocks
    UserServiceImpl userService;

    @InjectMocks
    SquadServiceImpl squadService;

    private static User userInfo() {
        User user = new User();
        List<Squad> squads = new ArrayList<Squad>();
        List<Skill> skills = new ArrayList<Skill>();
        List<User> friends = new ArrayList<User>();
        List<Task> tasks = new ArrayList<Task>();
        List<FeedElement> feedElements = new ArrayList<FeedElement>();

        user.setIdUser(1L);
        user.setName("José");
        user.setEmail("jose@email.com");
        user.setNickname("zeTeste");
        user.setPassword("1234");
        user.setAddress(adressInfo());
        user.setGitHub("jose@github");
        user.setInstagram("joseInstagram");
        user.setRace(Race.BLACK);
        user.setGender(Gender.OTHER);
        user.setSexualOrientation(SexualOrientation.OTHER);
        user.setRole(Role.BACKEND);
        user.setSkills(skills);
        user.setSquads(squads);
        user.setFriends(friends);
        user.setTasks(tasks);
        user.setFeedElements(feedElements);

        return user;
    }

    private static Address adressInfo() {
        Address address = new Address();

        address.setStreet("Rua teste");
        address.setNeighborhood("Bairro teste");
        address.setCity("Cidade teste");
        address.setState("Estado teste");
        address.setCountry("Pais teste");
        address.setZipCode("1234-567");
        address.setNumber("123");
        address.setReference("Referencia teste");

        return address;
    }

    private static FeedElement feedElementInfo() {

        FeedElement feedElement = new FeedElement();

        feedElement.setIdFeedElement(1L);
        feedElement.setIdUser(1L);
        feedElement.setName("Nome do feed element");
        feedElement.setDate(LocalDateTime.now());
        feedElement.setContent("Conteúdo teste de um feed element");
        feedElement.setIdSquad(1L);
        feedElement.setType(FeedElementType.TASK);

        return feedElement;
    }

    private static FeedElementDTO feedElementDTOInfo() {

        FeedElementDTO feedElementDTO = new FeedElementDTO();

        feedElementDTO.setIdUser(1L);
        feedElementDTO.setContent("Conteúdo teste de um feed element");
        feedElementDTO.setIdSquad(1L);
        feedElementDTO.setType(FeedElementType.TASK);

        return feedElementDTO;
    }

    private static Commentary commentaryInfo() {

        Commentary commentary = new Commentary();
        commentary.setIdUser(1L);
        commentary.setContent("Um comentário para testes");

        return commentary;
    }

    private static Squad squadInfo() {

        Squad squad = new Squad();
        List<User> admins = new ArrayList<User>();
        List<User> members = new ArrayList<User>();
        List<Task> tasks = new ArrayList<Task>();
        List<FeedElement> feedElements = new ArrayList<FeedElement>();

        squad.setIdSquad(1L);
        squad.setName("Squad Teste");
        squad.setProjectName("Projeto teste");
        squad.setBio("Descrição do projeto teste");
        squad.setFinished(false);
        squad.setRepository("Repositorio teste");
        squad.setAdmins(admins);
        squad.setIdUser(1L);
        squad.setMembers(members);
        squad.setTasks(tasks);
        squad.setFeedElements(feedElements);

        return squad;
    }

    @Test
    public void mustSucceedWhenCreateACommentary() throws GenericException {

        Optional<User> userTest = Optional.of(userInfo());
        Optional<Squad> squadTest = Optional.of(squadInfo());
        Commentary commentaryTest = commentaryInfo();

        Long idSquad = squadTest.get().getIdSquad();
        Long idUser = userTest.get().getIdUser();

        when(squadRepository.findById(idSquad)).thenReturn(squadTest);
        when(userRepository.findById(idUser)).thenReturn(userTest);

        FeedElement feedElementTest =
                this.feedElementService.createCommentary(idSquad, commentaryTest);
        FeedElement expectedFeedElement = this.feedElementRepository.findById(1L).orElse(null);

        assertEquals(expectedFeedElement, feedElementTest);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenCreateACommentaryIfSquadDoesNotExists() throws GenericException {

        Optional<User> userTest = Optional.of(userInfo());
        Commentary commentaryTest = commentaryInfo();

        Long idUser = userTest.get().getIdUser();

        Long wrongIdSquad = 1L;
        when(squadRepository.existsById(wrongIdSquad)).thenReturn(false);
        when(userRepository.findById(idUser)).thenReturn(userTest);

        FeedElement feedElementTest = this.feedElementService.createCommentary(2L, commentaryTest);

        assertEquals(GenericException.class, feedElementTest);

    }

    @Test(expected = GenericException.class)
    public void mustFailTWhenCreateACommentaryIfUserDoesNotExists() throws GenericException {

        Optional<Squad> squadTest = Optional.of(squadInfo());
        Long idSquad = squadTest.get().getIdSquad();

        Commentary commentaryTest = commentaryInfo();

        Long wrongIdUser = 1L;
        when(squadRepository.findById(idSquad)).thenReturn(squadTest);
        when(userRepository.existsById(wrongIdUser)).thenReturn(false);

        FeedElement feedElementTest =
                this.feedElementService.createCommentary(idSquad, commentaryTest);

        assertEquals(GenericException.class, feedElementTest);
    }

    @Test
    public void mustSucceedWhenCreateAFeedElement() throws GenericException {

        Optional<User> userTest = Optional.of(userInfo());
        Optional<Squad> squadTest = Optional.of(squadInfo());
        FeedElementDTO feedElementDTOTest = feedElementDTOInfo();

        Long idSquad = feedElementDTOTest.getIdSquad();
        Long idUser = feedElementDTOTest.getIdUser();

        when(squadRepository.findById(idSquad)).thenReturn(squadTest);
        when(userRepository.findById(idUser)).thenReturn(userTest);

        FeedElement feedElementTest = this.feedElementService.createFeedElement(feedElementDTOTest);
        FeedElement expectedFeedElement = this.feedElementRepository.findById(idUser).orElse(null);

        assertEquals(expectedFeedElement, feedElementTest);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenCreateAFeedElementIfUserNotExists() throws GenericException {

        Optional<Squad> squadTest = Optional.of(squadInfo());

        FeedElementDTO feedElementDTOTest = feedElementDTOInfo();
        Long idSquad = feedElementDTOTest.getIdSquad();

        Long wrongIdUser = 1L;
        when(squadRepository.findById(idSquad)).thenReturn(squadTest);
        when(userRepository.existsById(wrongIdUser)).thenReturn(false);
        FeedElement feedElementTest = this.feedElementService.createFeedElement(feedElementDTOTest);

        assertEquals(GenericException.class, feedElementTest);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenCreateAFeedElementIfSquadDoesNotExists() throws GenericException {

        FeedElementDTO feedElementDTOTest = feedElementDTOInfo();
        Optional<User> userTest = Optional.of(userInfo());
        Long idUser = userTest.get().getIdUser();

        Long wrongIdSquad = 1L;
        when(squadRepository.existsById(wrongIdSquad)).thenReturn(false);
        when(userRepository.findById(idUser)).thenReturn(userTest);

        FeedElement feedElementTest = this.feedElementService.createFeedElement(feedElementDTOTest);

        assertEquals(GenericException.class, feedElementTest);
    }

    @Test
    public void mustSucceedWhenDeleteAFeedElement() throws GenericException {

        Optional<Squad> squadTest = Optional.of(squadInfo());
        Long idSquad = squadTest.get().getIdSquad();

        Optional<FeedElement> feedElementTest = Optional.of(feedElementInfo());
        Long idFeedElement = feedElementTest.get().getIdFeedElement();

        when(squadRepository.findById(idSquad)).thenReturn(squadTest);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(true);

        ResponseDTO responseMessage =
                this.feedElementService.deleteFeedElement(idSquad, idFeedElement);
        ResponseDTO expectedMessage = new ResponseDTO(FEED_ELEMENT_DELETE_SUCESSFULLY);

        assertEquals(expectedMessage, responseMessage);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenDeleteAFeedElementIfSquadNotExists() throws GenericException {

        Optional<FeedElement> feedElementTest = Optional.of(feedElementInfo());
        Long idFeedElement = feedElementTest.get().getIdFeedElement();

        Long wrongIdSquad = 2L;
        when(squadRepository.existsById(wrongIdSquad)).thenReturn(false);
        when(feedElementRepository.findById(idFeedElement)).thenReturn(feedElementTest);

        ResponseDTO responseMessage =
                this.feedElementService.deleteFeedElement(wrongIdSquad, idFeedElement);
        ResponseDTO expectedMessage = new ResponseDTO(THIS_SQUAD_DOES_NOT_EXIST);

        assertEquals(expectedMessage, responseMessage);

    }

    @Test(expected = GenericException.class)
    public void mustFailWhenDeleteAFeedElementIfFeedElementNotExists() throws GenericException {

        Optional<Squad> squadTest = Optional.of(squadInfo());
        Long idSquad = squadTest.get().getIdSquad();

        Long wrongIdFeedElement = 2L;
        when(squadRepository.findById(idSquad)).thenReturn(squadTest);
        when(feedElementRepository.existsById(wrongIdFeedElement)).thenReturn(false);

        ResponseDTO responseMessage =
                this.feedElementService.deleteFeedElement(idSquad, wrongIdFeedElement);
        ResponseDTO expectedMessage = new ResponseDTO(FEED_ELEMENT_NOT_FOUND);

        assertEquals(expectedMessage, responseMessage);

    }
}


