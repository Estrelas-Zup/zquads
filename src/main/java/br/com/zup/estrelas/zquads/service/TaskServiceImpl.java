package br.com.zup.estrelas.zquads.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;
import br.com.zup.estrelas.zquads.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    
    public ResponseDTO createTask(TaskDTO taskDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public Task readTask(Long idTask) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Task> listTasks() {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseDTO updateTask(Long idTask, TaskDTO taskDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    public ResponseDTO deleteTask(Long idTask) {
        // TODO Auto-generated method stub
        return null;
    }

}
