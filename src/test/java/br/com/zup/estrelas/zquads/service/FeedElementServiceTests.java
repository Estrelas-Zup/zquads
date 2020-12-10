package br.com.zup.estrelas.zquads.service;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static java.time.LocalDateTime.now;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.FEED_ELEMENT_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.FEED_ELEMENT_DELETE_SUCESSFULLY;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        feedElement.setDate(now());
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

        Optional<User> user = Optional.of(userInfo());
        Optional<Squad> squad = Optional.of(squadInfo());
        Commentary commentary = commentaryInfo();

        Long idSquad = squad.get().getIdSquad();
        Long idUser = user.get().getIdUser();

        FeedElement expectedFeedElement = new FeedElement();
        copyProperties(commentary, expectedFeedElement);

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        when(feedElementRepository.save(any(FeedElement.class))).thenReturn(expectedFeedElement);

        FeedElement returnedFeedElement =
                this.feedElementService.createCommentary(idSquad, commentary);

        assertEquals("Must create an commentary sucessfully", expectedFeedElement,
                returnedFeedElement);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenCreateACommentaryIfSquadDoesNotExists() throws GenericException {

        Optional<User> user = Optional.of(userInfo());
        Optional<Squad> squad = Optional.empty();
        Commentary commentary = commentaryInfo();

        Long idUser = user.get().getIdUser();
        Long idSquad = 1L;

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        FeedElement expectedFeedElement =
                this.feedElementService.createCommentary(idSquad, commentary);

        GenericException returnedResponse = new GenericException(SQUAD_NOT_FOUND);

        assertEquals("Must fail to create a comment if the squad does not exist", returnedResponse,
                expectedFeedElement);

    }

    @Test(expected = GenericException.class)
    public void mustFailTWhenCreateACommentaryIfUserDoesNotExists() throws GenericException {

        Optional<Squad> squad = Optional.of(squadInfo());
        Long idSquad = squad.get().getIdSquad();
        Optional<User> user = Optional.empty();
        Long idUser = 1L;

        Commentary commentary = commentaryInfo();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        FeedElement expectedFeedElement =
                this.feedElementService.createCommentary(idSquad, commentary);

        GenericException ReturnedResponse = new GenericException(USER_NOT_FOUND);
        assertEquals("Must fail to create a comment if the user does not exist", ReturnedResponse,
                expectedFeedElement);
    }

    @Test
    public void mustSucceedWhenCreateAFeedElement() throws GenericException {

        Optional<User> user = Optional.of(userInfo());
        Optional<Squad> squad = Optional.of(squadInfo());
        FeedElementDTO feedElementDTO = feedElementDTOInfo();

        Long idSquad = feedElementDTO.getIdSquad();
        Long idUser = feedElementDTO.getIdUser();

        FeedElement expectedFeedElement = new FeedElement();
        copyProperties(feedElementDTO, expectedFeedElement);

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        when(feedElementRepository.save(any(FeedElement.class))).thenReturn(expectedFeedElement);

        FeedElement returnedFeedElement = this.feedElementService.createFeedElement(feedElementDTO);

        assertEquals("Must create an feed element sucessfully", expectedFeedElement,
                returnedFeedElement);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenCreateAFeedElementIfUserNotExists() throws GenericException {

        Optional<Squad> squad = Optional.of(squadInfo());
        Optional<User> user = Optional.empty();

        FeedElementDTO feedElementDTO = feedElementDTOInfo();
        Long idSquad = feedElementDTO.getIdSquad();
        Long idUser = 1L;

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        FeedElement expectedFeedElement = this.feedElementService.createFeedElement(feedElementDTO);
        GenericException returnedResponse = new GenericException(USER_NOT_FOUND);

        assertEquals("Must fail to create a feed element if the user does not exist",
                returnedResponse, expectedFeedElement);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenCreateAFeedElementIfSquadDoesNotExists() throws GenericException {

        Optional<Squad> squad = Optional.empty();
        Optional<User> user = Optional.of(userInfo());

        Long idUser = user.get().getIdUser();
        Long idSquad = 1L;

        FeedElementDTO feedElementDTO = feedElementDTOInfo();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);

        FeedElement ExpectedFeedElement = this.feedElementService.createFeedElement(feedElementDTO);
        GenericException returnedResponse = new GenericException(SQUAD_NOT_FOUND);

        assertEquals("Must fail to create a comment if the squad does not exist", returnedResponse,
                ExpectedFeedElement);
    }

    @Test
    public void mustSucceedWhenDeleteAFeedElement() throws GenericException {

        Optional<Squad> squad = Optional.of(squadInfo());
        Long idSquad = squad.get().getIdSquad();

        Optional<FeedElement> feedElement = Optional.of(feedElementInfo());
        Long idFeedElement = feedElement.get().getIdFeedElement();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(true);

        ResponseDTO expectedResponse =
                this.feedElementService.deleteFeedElement(idSquad, idFeedElement);
        ResponseDTO returnedResponse = new ResponseDTO(FEED_ELEMENT_DELETE_SUCESSFULLY);

        assertEquals("Must delete an commentary sucessfully", returnedResponse, expectedResponse);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenDeleteAFeedElementIfSquadNotExists() throws GenericException {

        Optional<Squad> squad = Optional.empty();
        Long idSquad = 1L;

        Optional<FeedElement> feedElement = Optional.of(feedElementInfo());
        Long idFeedElement = feedElement.get().getIdFeedElement();

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(true);

        ResponseDTO expectedResponse =
                this.feedElementService.deleteFeedElement(idSquad, idFeedElement);
        GenericException returnedReponse = new GenericException(SQUAD_NOT_FOUND);

        assertEquals("Must fail to delete a comment if the squad does not exist", returnedReponse,
                expectedResponse);
    }

    @Test(expected = GenericException.class)
    public void mustFailWhenDeleteAFeedElementIfFeedElementNotExists() throws GenericException {

        Optional<Squad> squad = Optional.of(squadInfo());
        Long idSquad = squad.get().getIdSquad();

        Optional<FeedElement> feedElement = Optional.empty();
        Long idFeedElement = 1L;

        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(feedElementRepository.existsById(idFeedElement)).thenReturn(false);

        ResponseDTO expectedResponse =
                this.feedElementService.deleteFeedElement(idSquad, idFeedElement);
        GenericException returnedResponse = new GenericException(FEED_ELEMENT_NOT_FOUND);

        assertEquals("Must fail to create a comment if the feed element does not exist",
                returnedResponse, expectedResponse);

    }
}


