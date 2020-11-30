package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;
import br.com.zup.estrelas.zquads.dto.UpdateTaskDTO;
import br.com.zup.estrelas.zquads.service.TaskServiceImpl;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    TaskServiceImpl taskService;
    
    @PostMapping (produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createTask(@RequestBody TaskDTO taskDto) {
        return this.taskService.createTask(taskDto);
    }
    
    @GetMapping(path = "/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Task readTask(@PathVariable Long idTask) {
        return this.taskService.readTask(idTask);
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Task> listTask() {
        return this.taskService.listTasks();
    }
    
    @PutMapping(path = "/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateTask(@PathVariable Long idTask, @RequestBody UpdateTaskDTO taskDTO) {
        return this.taskService.updateTask(idTask, taskDTO);
    }
    
    @DeleteMapping(path = "/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteTask(@PathVariable Long idTask) {
        return this.taskService.deleteTask(idTask);
    }
    
    @PutMapping(path = "/finish/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO finishTask(@PathVariable Long idTask) {
        return this.taskService.finishTask(idTask);
    }

}
