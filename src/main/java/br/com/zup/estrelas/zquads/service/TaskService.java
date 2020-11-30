package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;
import br.com.zup.estrelas.zquads.dto.UpdateTaskDTO;

public interface TaskService {

    public ResponseDTO createTask(TaskDTO taskDTO);
    
    public Task readTask(Long idTask);
    
    public List<Task> listTasks();
    
    public ResponseDTO updateTask(Long idTask, UpdateTaskDTO taskDTO);
    
    public ResponseDTO deleteTask(Long idTask);
    
    public ResponseDTO finishTask(Long idTask);
}