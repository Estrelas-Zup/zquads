package br.com.zup.estrelas.zquads.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
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
import br.com.zup.estrelas.zquads.repository.SquadRepository;
import br.com.zup.estrelas.zquads.repository.TaskRepository;
import br.com.zup.estrelas.zquads.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String TASK_SUCCESSFULLY_CREATED = "task successfully created";
    private static final String TASK_SUCCESSFULLY_UPDATED = "task successfully updated";
    private static final String TASK_SUCCESSFULLY_DELETED = "task successfully deleted";
    private static final String TASK_SUCCESSFULLY_FINISHED = "task successfully finished";
    private static final String TASK_NOT_FOUND = "Task Not Found";
    private static final String THIS_SQUAD_DOES_NOT_EXIST = "this squad doesn't exist";
    private static final String THIS_USER_DOES_NOT_EXIST = "the user responsible for this task doesn't exist";

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SquadRepository squadRepository;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    FeedElementService feedElement;

    public ResponseDTO createTask(TaskDTO taskDTO) {

        Optional<Squad> squad = this.squadRepository.findById(taskDTO.getIdSquad());
        if (squad.isEmpty()) {
            return new ResponseDTO(THIS_SQUAD_DOES_NOT_EXIST);
        }

        Optional<User> user = this.userRepository.findById(taskDTO.getIdUser());
        if (user.isEmpty()) {
            return new ResponseDTO(THIS_USER_DOES_NOT_EXIST);
        }

        Task taskDB = new Task();
        BeanUtils.copyProperties(taskDTO, taskDB);
        this.taskRepository.save(taskDB);
        return new ResponseDTO(TASK_SUCCESSFULLY_CREATED);
    }

    public Task readTask(Long idTask) {
        return this.taskRepository.findById(idTask).orElse(null);
    }

    public List<Task> listTasks() {
        return (List<Task>) this.taskRepository.findAll();
    }

    public ResponseDTO updateTask(Long idTask, UpdateTaskDTO taskDTO) {

        Optional<Task> taskExisting = this.taskRepository.findById(idTask);

        if (taskExisting.isEmpty()) {
            return new ResponseDTO(TASK_NOT_FOUND);
        }

        Task updatedTask = taskExisting.get();
        BeanUtils.copyProperties(taskDTO, updatedTask);
        this.taskRepository.save(updatedTask);

        return new ResponseDTO(TASK_SUCCESSFULLY_UPDATED);
    }

    public ResponseDTO deleteTask(Long idTask) {

        Optional<Task> task = this.taskRepository.findById(idTask);

        if (task.isEmpty()) {
            return new ResponseDTO(TASK_NOT_FOUND);
        }

        this.taskRepository.delete(task.get());
        return new ResponseDTO(TASK_SUCCESSFULLY_DELETED);
    }

    public ResponseDTO finishTask(Long idTask) {

        Optional<Task> taskToBeQuery = this.taskRepository.findById(idTask);

        if (taskToBeQuery.isEmpty()) {
            return new ResponseDTO(TASK_NOT_FOUND);
        }
        Task task = taskToBeQuery.get();
        task.setFinishingDate(LocalDateTime.now());
        task.setFinished(true);
        taskRepository.save(task);
        
        FeedElementDTO feedElementDto = new FeedElementDTO();
        BeanUtils.copyProperties(task, feedElementDto);
        feedElementDto.setType(FeedElementType.TASK);
        this.feedElement.createFeedElement(feedElementDto);

        return new ResponseDTO(TASK_SUCCESSFULLY_FINISHED);
    }

}
