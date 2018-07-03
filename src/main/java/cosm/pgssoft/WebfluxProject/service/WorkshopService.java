package cosm.pgssoft.WebfluxProject.service;


import cosm.pgssoft.WebfluxProject.domain.Workshop;
import cosm.pgssoft.WebfluxProject.repository.WorkshopRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class WorkshopService {

    private WorkshopRepository reactiveWorkshopRepository;

    public WorkshopService(WorkshopRepository reactiveWorkshopRepository) {
        this.reactiveWorkshopRepository = reactiveWorkshopRepository;
    }

    public Mono<Workshop> getWorkshopById(final String id) {
        return Optional.ofNullable(id).map(s -> reactiveWorkshopRepository.findById(s)).orElse(Mono.empty());
    }

    public Flux<Workshop> getAllWorshop() {
        return reactiveWorkshopRepository.findAll();
    }

    public Mono<Workshop> saveWorkshop(final Workshop workshop) {
        return reactiveWorkshopRepository.save(workshop);
    }

    public Mono<Workshop> updateWorkshop(String id, final Workshop workshop) {
        workshop.setId(id);
        return reactiveWorkshopRepository.save(workshop);
    }


    public void deleteWorkshopById(final String id) {
        reactiveWorkshopRepository.deleteById(id).subscribe();
    }


    public Mono<Workshop> addParticipant(String id, String participant) {
        return reactiveWorkshopRepository.findById(id).map(original -> {
            original.getParticipants().add(participant);
            return original;
        }).flatMap(reactiveWorkshopRepository::save);
    }


    public Mono<Workshop> deleteParticipant(String id, String participant) {
        return reactiveWorkshopRepository.findById(id).map(original -> {
            original.getParticipants().remove(participant);
            return original;
        }).flatMap(reactiveWorkshopRepository::save);
    }
}
