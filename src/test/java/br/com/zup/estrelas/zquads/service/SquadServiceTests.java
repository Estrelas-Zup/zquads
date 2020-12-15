package br.com.zup.estrelas.zquads.service;

import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_REMOVED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.PROJECT_FINISHED;
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
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.SquadDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class SquadServiceTests {

    private static SquadDTO generateSquadDTO() {
        SquadDTO squadDto = new SquadDTO();
        squadDto.setIdUser(1l);
        squadDto.setName("Teste");
        squadDto.setProjectName("TesteZQuads");
        squadDto.setRepository("gitStar");
        squadDto.setBio("nada demais");
        return squadDto;
    }

    private static Squad generateSquad() {
        Squad squad = new Squad();
        squad.setIdSquad(1l);
        squad.setIdUser(1l);
        squad.setProjectName("Testando123");
        squad.setName("estelar");
        return squad;
    }

    private static User generateUser() {
        User user = new User();
        user.setIdUser(1l);
        return user;
    }

    @Mock
    SquadRepository squadRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    SquadServiceImpl squadService;

    @Test
    public void shouldCreateSquad() throws GenericException {
        SquadDTO createdSquad = generateSquadDTO();
        Squad squad = new Squad();
        copyProperties(createdSquad, squad);
        Optional<User> user = Optional.of(generateUser());
        Long idUser = user.get().getIdUser();

        when(userRepository.findById(idUser)).thenReturn(user);
        when(squadRepository.existsByName(createdSquad.getName())).thenReturn(false);
        when(squadRepository.save(any(Squad.class))).thenReturn(squad);

        Squad returnedResponse = squadService.createSquad(createdSquad);
        Squad expectedResponse = squad;
        assertEquals(returnedResponse, expectedResponse);
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateSquadWhenAlreadyExists() throws GenericException {

        SquadDTO createdSquad = generateSquadDTO();

        when(squadRepository.existsByName(createdSquad.getName())).thenReturn(true);

        this.squadService.createSquad(createdSquad);
    }

    @Test(expected = GenericException.class)
    public void shouldntCreateSquadIfUserNotFound() throws GenericException {

        SquadDTO createdSquad = generateSquadDTO();

        when(userRepository.findById(1l)).thenReturn(Optional.empty());

        this.squadService.createSquad(createdSquad);
    }

    @Test
    public void shouldUpdateSquadSuccessfully() throws GenericException {
        Optional<Squad> squad = Optional.of(generateSquad());
        SquadDTO squadUpdated = generateSquadDTO();
        Long idSqaud = squad.get().getIdSquad();
        copyProperties(squadUpdated, squad.get());

        when(squadRepository.findById(idSqaud)).thenReturn(squad);
        when(squadRepository.save(any(Squad.class))).thenReturn(squad.get());

        Squad returnedResponse = squadService.updateSquad(idSqaud, squadUpdated);
        Squad expectedResponse = squad.get();
        
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void shouldntUpdateSquadIfNotFound() throws GenericException {
        SquadDTO squadUpdated = generateSquadDTO();
        Long idSquad = 1l;
        
        when(squadRepository.findById(idSquad)).thenReturn(Optional.empty());
        
        this.squadService.updateSquad(idSquad, squadUpdated);
    }
    
    @Test(expected = GenericException.class)
    public void shouldntUpdateSquadIfUserNotFound() throws GenericException {
        SquadDTO squadUpdated = generateSquadDTO();
        Long idSquad = 1l;
        
        when(userRepository.findById(1l)).thenReturn(Optional.empty());
        
        this.squadService.updateSquad(idSquad, squadUpdated);
    }
    
    @Test
    public void shouldDeleteSquadSuccessfully() throws GenericException {
        Optional<Squad> squad = Optional.of(generateSquad());
        Long idSquad = squad.get().getIdSquad();
        
        when(squadRepository.findById(idSquad)).thenReturn(squad);
        
        ResponseDTO returnedResponse = squadService.deleteSquad(idSquad);
        ResponseDTO expectedResponse = new ResponseDTO(SQUAD_REMOVED);
        
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void shouldntDeleteSquadIfNotFound() throws GenericException {
        Long idSquad = 1l;
        
        when(squadRepository.findById(idSquad)).thenReturn(Optional.empty());
        
        this.squadService.deleteSquad(idSquad);
    }
    
    @Test
    public void shouldFinishProjectSuccessfully() throws GenericException {
        Optional<Squad> squad = Optional.of(generateSquad());
        Long idSquad = squad.get().getIdSquad();
        
        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(squadRepository.save(any(Squad.class))).thenReturn(squad.get());
        
        ResponseDTO returnedResponse = squadService.finishProject(idSquad);
        ResponseDTO expectedResponse = new ResponseDTO(PROJECT_FINISHED);
        
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void shouldntFinishProjectIfSquadNotFound() throws GenericException {
        Long idSquad = 1l;
        
        when(squadRepository.findById(idSquad)).thenReturn(Optional.empty());
        
        this.squadService.finishProject(idSquad);
    }

}
