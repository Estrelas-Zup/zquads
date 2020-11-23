package br.com.zup.estrelas.zquads.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

}
