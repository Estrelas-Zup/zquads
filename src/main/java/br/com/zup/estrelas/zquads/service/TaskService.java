package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;

public interface TaskService {

    public ResponseDTO createTask(TaskDTO taskDTO);
    
    public Task readTask(Long idTask);
    
    public List<Task> listTasks();
    
    public ResponseDTO updateTask(Long idTask, TaskDTO taskDTO);
    
    public ResponseDTO deleteTask(Long idTask);
    
}