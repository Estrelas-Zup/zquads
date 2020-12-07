package br.com.zup.estrelas.zquads.service;

import static java.time.LocalDateTime.now;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
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
public class TaskServiceTest {

      private static final String TASK_SUCCESSFULLY_DELETED = "task successfully deleted";
      private static final String TASK_SUCCESSFULLY_FINISHED = "task successfully finished";
      private static final String TASK_NOT_FOUND = "Task Not Found";
      private static final String THIS_SQUAD_DOES_NOT_EXIST = "this squad doesn't exist";
      private static final String THIS_USER_DOES_NOT_EXIST = "the user responsible for this task doesn't exist";
    
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
   
    private static Squad createSquad() {
        Squad squad = new Squad();
        squad.setIdSquad(1l);
        return squad;
    }
    
    private static User createUser() {
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
    FeedElementService feedElementServiceh;
    
    @InjectMocks
    TaskServiceImpl taskService;
    
    @Test
    public void createTaskSuccessfullyInDatabase() throws GenericException {
        TaskDTO taskRequisition = generateTaskDto();
        Task task = new Task();
        Optional<Squad> squad = Optional.of(createSquad());
        Optional<User> user = Optional.of(createUser());
        Long idSquad = squad.get().getIdSquad();
        Long idUser = user.get().getIdUser();
        
        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        when(taskRepository.save(task)).thenReturn(task);
        
        Task expectedResponse = taskService.createTask(taskRequisition, idSquad);
        Task returnedResponse = task;
        Assert.assertEquals("Should create a task with successfully" ,expectedResponse, returnedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontCreateTaskIfSquadNotFound() throws GenericException {
        TaskDTO taskRequisition = generateTaskDto();
        Optional<Squad> squad = Optional.empty();
        Optional<User> user = Optional.of(createUser());
        Long idSquad = 1l;
        Long idUser = user.get().getIdUser();
        
        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        
        Task expectedResponse = taskService.createTask(taskRequisition, idSquad);
        GenericException returnedResponse =  new GenericException(THIS_SQUAD_DOES_NOT_EXIST);
        Assert.assertEquals("Shouldn't create a task if the squad wasn't found" ,expectedResponse, returnedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontCreateTaskIfUserNotFound() throws GenericException {
        TaskDTO taskRequisition = generateTaskDto();
        Optional<Squad> squad = Optional.of(createSquad());
        Optional<User> user = Optional.empty();
        Long idSquad = squad.get().getIdSquad();
        Long idUser = 1l;
        
        when(squadRepository.findById(idSquad)).thenReturn(squad);
        when(userRepository.findById(idUser)).thenReturn(user);
        
        Task expectedResponse = taskService.createTask(taskRequisition, idSquad);
        GenericException returnedResponse =  new GenericException(THIS_USER_DOES_NOT_EXIST);
        Assert.assertEquals("Shouldn't create a task if the squad wasn't found" ,expectedResponse, returnedResponse);
    }
    
    @Test 
    public void updateTaskSuccessfullyInDatabase() throws GenericException {
        Optional<Task> task = Optional.of(generateTask());
        Long idTask = task.get().getIdTask();
        UpdateTaskDTO taskUpadated = generateTaskDtoFromUpdate();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        Task expectedResponse = taskService.updateTask(idTask, taskUpadated);
        Task returnedResponse = task.get();
        Assert.assertEquals("Should update a task with successfully" ,expectedResponse, returnedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontUpdateTaskIfNotFound() throws GenericException {
        Optional<Task> task = Optional.empty();
        Long idTask = 1l;
        UpdateTaskDTO taskUpadated = generateTaskDtoFromUpdate();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        Task expectedResponse = taskService.updateTask(idTask, taskUpadated);
        GenericException returnedResponse = new GenericException(TASK_NOT_FOUND);
        Assert.assertEquals("Shouldn't update a task if cannot find" ,expectedResponse, returnedResponse);
    }
    
    @Test
    public void deleteTaskSuccessfullyInDataBase() throws GenericException {
        Optional<Task> task = Optional.of(generateTask());
        Long idTask = task.get().getIdTask();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        ResponseDTO expectedResponse = taskService.deleteTask(idTask);
        ResponseDTO returnedResponse = new ResponseDTO(TASK_SUCCESSFULLY_DELETED);
        Assert.assertEquals("", expectedResponse, returnedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontDeleteTaskIfNotFound() throws GenericException {
        Optional<Task> task = Optional.empty();
        Long idTask = 1l;
        
        when(taskRepository.findById(1l)).thenReturn(task);
        
        ResponseDTO expectedResponse = taskService.deleteTask(idTask);
        GenericException returnedResponse = new GenericException(TASK_NOT_FOUND);
        Assert.assertEquals(expectedResponse, returnedResponse);
    }
    
    @Test
    public void finishTaskSuccessfully() throws GenericException {
        Optional<Task> task = Optional.of(generateTask());
        Long idTask = task.get().getIdTask();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        ResponseDTO expectedResponse = taskService.finishTask(idTask);
        ResponseDTO returnedResponse = new ResponseDTO(TASK_SUCCESSFULLY_FINISHED);
        Assert.assertEquals(expectedResponse, returnedResponse);
    }
    
    @Test(expected = GenericException.class)
    public void dontFinishTaskIfNotFound() throws GenericException {
        Optional<Task> task = Optional.empty();
        Long idTask = task.get().getIdTask();
        
        when(taskRepository.findById(idTask)).thenReturn(task);
        
        ResponseDTO expectedResponse = taskService.finishTask(idTask);
        GenericException returnedResponse = new GenericException(TASK_NOT_FOUND);
        Assert.assertEquals(expectedResponse, returnedResponse);
    }
    
}
