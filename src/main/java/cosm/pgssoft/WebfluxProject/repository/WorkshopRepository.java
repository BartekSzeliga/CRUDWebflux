package cosm.pgssoft.WebfluxProject.repository;


import cosm.pgssoft.WebfluxProject.domain.Workshop;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WorkshopRepository extends ReactiveMongoRepository<Workshop, String> {
}
