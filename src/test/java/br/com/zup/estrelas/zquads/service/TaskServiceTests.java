package br.com.zup.estrelas.zquads.service;

import static java.time.LocalDateTime.now;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.junit.Assert.assertEquals;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.TASK_SUCCESSFULLY_DELETED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.TASK_SUCCESSFULLY_FINISHED;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;
import br.com.zup.estrelas.zquads.dto.UpdateTaskDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.FeedElementRepository;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.TaskRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTests {

    
    private static Task generateTask() {
        Task task = new Task();
        task.setIdTask(1l);
        task.setName("batata doce");
        task.setIdUser(1l);
        task.setIdSquad(1l);
        task.setContent("blablabla");
        task.setStartingDate(now());
        task.setFinished(false);
        return task;
    }
    
    private static TaskDTO generateTaskDto() {
        TaskDTO task = new TaskDTO();
        task.setName("batata doce");
        task.setIdUser(1l);
        task.setContent("I hate tests");
        
        return task;
    }
    
    private static UpdateTaskDTO generateTaskDtoFromUpdate() {
        UpdateTaskDTO task = new UpdateTaskDTO();
        task.setName("batata doce");
        task.setIdUser(1l);
        task.setContent("I hate tests");
        
        return task;
    }
   
    private static Squad generateSquad() {
        Squad squad = new Squad();
        squad.setIdSquad(1l);
        return squad;
    }
    
    private static User generateUser() {
        User user = new User();
        user.setIdUser(1l);
        return user;
    }
    
    @Mock
    TaskRepository taskRepository;
    
    @Mock
    FeedElementRepository feedRepository;
    
    @Mock
    UserRepository userRepository;
    
    @Mock
    SquadRepository squadRepository;
    
    @Mock
    FeedElementService feedElementService;
    
    @InjectMocks
    TaskServiceImpl taskService;
    
    @Test
    public void shouldCreateTask() throws GenericException {
        TaskDTO taskRequisition = generateTaskDto();
        Task task = new Task();
        copyProperties(taskRequisition, task);
        Optional<Squad> squad = Optional.of(generateSquad());
        Optional<User> user = Optional.of(generateUser());
        Long idSquad = squad.get().getIdSquad();
        Long idUser = user.get().getIdUser();
        
        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        
        Task returnedResponse = taskService.createTask(taskRequisition, idSquad);
        Task expectedResponse = task;
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void shouldntCreateTaskIfSquadNotFound() throws GenericException {
        TaskDTO taskRequisition = generateTaskDto();
        Long idSquad = 1l;
        
        when(squadRepository.findById(idSquad)).thenReturn(Optional.empty());
        
        this.taskService.createTask(taskRequisition, idSquad);
    }
    
    @Test(expected = GenericException.class)
    public void shouldntCreateTaskIfUserNotFound() throws GenericException {
        TaskDTO taskRequisition = generateTaskDto();
        Long idUser = 1l;
        Long idSquad = 1l;

        when(userRepository.findById(idUser)).thenReturn(Optional.empty());
        
        this.taskService.createTask(taskRequisition, idSquad);
    }
    
    @Test 
    public void shouldUpdateTaskSuccessfully() throws GenericException {
        Optional<Task> task = Optional.of(generateTask());
        Long idTask = task.get().getIdTask();
        UpdateTaskDTO taskUpadated = generateTaskDtoFromUpdate();
        copyProperties(taskUpadated, task.get());
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenReturn(task.get());
        
        Task returnedResponse = taskService.updateTask(idTask, taskUpadated);
        Task expectedResponse = task.get();
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontUpdateTaskIfNotFound() throws GenericException {
        Optional<Task> task = Optional.empty();
        Long idTask = 1l;
        UpdateTaskDTO taskUpadated = generateTaskDtoFromUpdate();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        this.taskService.updateTask(idTask, taskUpadated);
    }
    
    @Test
    public void deleteTaskSuccessfullyInDataBase() throws GenericException {
        Optional<Task> task = Optional.of(generateTask());
        Long idTask = task.get().getIdTask();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        ResponseDTO returnedResponse = taskService.deleteTask(idTask);
        ResponseDTO expectedResponse = new ResponseDTO(TASK_SUCCESSFULLY_DELETED);
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontDeleteTaskIfNotFound() throws GenericException {
        Long idTask = 1l;
        
        when(taskRepository.findById(1l)).thenReturn(Optional.empty());
        
        this.taskService.deleteTask(idTask);
    }
    
    @Test
    public void finishTaskSuccessfully() throws GenericException {
        Optional<Task> task = Optional.of(generateTask());
        Long idTask = task.get().getIdTask();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        ResponseDTO returnedResponse = taskService.finishTask(idTask);
        ResponseDTO expectedResponse = new ResponseDTO(TASK_SUCCESSFULLY_FINISHED);
        assertEquals(returnedResponse, expectedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontFinishTaskIfNotFound() throws GenericException {
        Long idTask = 1l;
        
        when(taskRepository.findById(idTask)).thenReturn(Optional.empty());
        
        this.taskService.finishTask(idTask);
    }
    
}
