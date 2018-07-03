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

        return Optional.ofNullable(id).map(projectId ->
                reactiveWorkshopRepository.findById(id).map(original -> {
                    Optional.ofNullable(workshop.getNumber()).ifPresent(original::setNumber);
                    Optional.ofNullable(workshop.getOffice()).ifPresent(original::setOffice);
                    Optional.ofNullable(workshop.getParticipantsNumber()).ifPresent(original::setParticipantsNumber);
                    Optional.ofNullable(workshop.getPresenter()).ifPresent(original::setPresenter);
                    Optional.ofNullable(workshop.getRoom()).ifPresent(original::setRoom);
                    Optional.ofNullable(workshop.isRemote()).ifPresent(original::setRemote);
                    return original;
                }).flatMap(reactiveWorkshopRepository::save)).orElse(Mono.empty());
    }


    public void deleteWorkshopById(final String id) {
        reactiveWorkshopRepository.deleteById(id).subscribe();
    }


}
