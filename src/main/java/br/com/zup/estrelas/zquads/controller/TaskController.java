package br.com.zup.estrelas.zquads.controller;

import java.util.List;
import javax.validation.Valid;
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
import br.com.zup.estrelas.zquads.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tasks")
@Api(value = "Task", tags = "Task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @ApiOperation(value = "Create a task")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO createTask(@Valid @RequestBody TaskDTO taskDto) {
        return this.taskService.createTask(taskDto);
    }

    @ApiOperation(value = "List a task by your ID")
    @GetMapping(path = "/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Task readTask(@PathVariable Long idTask) {
        return this.taskService.readTask(idTask);
    }

    @ApiOperation(value = "List all tasks")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Task> listTask() {
        return this.taskService.listTasks();
    }

    @ApiOperation(value = "Change attributes of a task")
    @PutMapping(path = "/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO updateTask(@PathVariable Long idTask,@Valid @RequestBody UpdateTaskDTO taskDTO) {
        return this.taskService.updateTask(idTask, taskDTO);
    }

    @ApiOperation(value = "Delete a task")
    @DeleteMapping(path = "/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO deleteTask(@PathVariable Long idTask) {
        return this.taskService.deleteTask(idTask);
    }

    @ApiOperation(value = "Finish a task")
    @PutMapping(path = "/finish/{idTask}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO finishTask(@PathVariable Long idTask) {
        return this.taskService.finishTask(idTask);
    }

}
