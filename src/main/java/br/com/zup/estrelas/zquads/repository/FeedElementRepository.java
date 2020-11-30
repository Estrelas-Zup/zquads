package br.com.zup.estrelas.zquads.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.zquads.domain.FeedElement;
import br.com.zup.estrelas.zquads.enums.FeedElementType;


@Repository
public interface FeedElementRepository extends CrudRepository<FeedElement, Long> {
    
    boolean existsByType(FeedElementType type);
    
}
