package br.com.zup.estrelas.zquads.service;

import java.util.List;
import br.com.zup.estrelas.zquads.domain.Task;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import br.com.zup.estrelas.zquads.dto.TaskDTO;
import br.com.zup.estrelas.zquads.dto.UpdateTaskDTO;
import br.com.zup.estrelas.zquads.exception.GenericException;

public interface TaskService {

    public Task createTask(TaskDTO taskDTO, Long idSquad) throws GenericException;

    public Task readTask(Long idTask) throws GenericException;

    public List<Task> listTasks();

    public Task updateTask(Long idTask, UpdateTaskDTO taskDTO) throws GenericException;

    public ResponseDTO deleteTask(Long idTask) throws GenericException;

    public ResponseDTO finishTask(Long idTask) throws GenericException;

}
