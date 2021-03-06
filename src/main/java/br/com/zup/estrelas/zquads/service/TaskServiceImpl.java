package br.com.zup.estrelas.zquads.service;

import static java.time.LocalDateTime.now;
import static org.springframework.beans.BeanUtils.copyProperties;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.TASK_SUCCESSFULLY_DELETED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.TASK_SUCCESSFULLY_FINISHED;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.TASK_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.SQUAD_NOT_FOUND;
import static br.com.zup.estrelas.zquads.constants.ConstantsResponsed.USER_NOT_FOUND;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Squad;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.domain.User;
import br.com.zup.estrelas.zquads.dto.FeedElementDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;
import br.com.zup.estrelas.zquads.dto.UpdateTaskDTO;
import br.com.zup.estrelas.zquads.enums.FeedElementType;
import br.com.zup.estrelas.zquads.exception.GenericException;
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.TaskRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SquadRepository squadRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedElementService feedElementService;

    public Task createTask(TaskDTO taskDTO, Long idSquad) throws GenericException {

        Optional<Squad> squad = this.squadRepository.findById(idSquad);
        if (squad.isEmpty()) {
            throw new GenericException(SQUAD_NOT_FOUND);
        }

        Optional<User> user = this.userRepository.findById(taskDTO.getIdUser());
        if (user.isEmpty()) {
            throw new GenericException(USER_NOT_FOUND);
        }

        Task taskDB = new Task();
        copyProperties(taskDTO, taskDB);
        taskDB.setIdSquad(idSquad);
        return this.taskRepository.save(taskDB);
    }

    public Task readTask(Long idTask) throws GenericException {
        return this.taskRepository.findById(idTask).orElseThrow(() -> new GenericException(TASK_NOT_FOUND));
    }

    public List<Task> listTasks() {
        return (List<Task>) this.taskRepository.findAll();
    }

    public Task updateTask(Long idTask, UpdateTaskDTO taskDTO) throws GenericException {

        Optional<Task> taskExisting = this.taskRepository.findById(idTask);

        if (taskExisting.isEmpty()) {
            throw new GenericException(TASK_NOT_FOUND);
        }

        Task updatedTask = taskExisting.get();
        copyProperties(taskDTO, updatedTask);

        this.taskRepository.save(updatedTask);
        return updatedTask;
    }

    public ResponseDTO deleteTask(Long idTask) throws GenericException {

        Optional<Task> task = this.taskRepository.findById(idTask);

        if (task.isEmpty()) {
            throw new GenericException(TASK_NOT_FOUND);
        }

        this.taskRepository.delete(task.get());

        return new ResponseDTO(TASK_SUCCESSFULLY_DELETED);
    }

    public ResponseDTO finishTask(Long idTask) throws GenericException {

        Optional<Task> taskToBeQuery = this.taskRepository.findById(idTask);

        if (taskToBeQuery.isEmpty()) {
            throw new GenericException(TASK_NOT_FOUND);
        }

        Task task = taskToBeQuery.get();
        task.setFinishingDate(now());
        task.setFinished(true);
        taskRepository.save(task);

        FeedElementDTO feedElementDto = new FeedElementDTO();
        copyProperties(task, feedElementDto);
        feedElementDto.setType(FeedElementType.TASK);

        feedElementService.createFeedElement(feedElementDto);
        
        return new ResponseDTO(TASK_SUCCESSFULLY_FINISHED);
    }

}
