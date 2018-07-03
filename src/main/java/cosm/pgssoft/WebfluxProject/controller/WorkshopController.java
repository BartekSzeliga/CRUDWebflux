package cosm.pgssoft.WebfluxProject.controller;


import cosm.pgssoft.WebfluxProject.domain.Workshop;
import cosm.pgssoft.WebfluxProject.service.WorkshopService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/workshop")
public class WorkshopController {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Workshop> saveWorkshop(@RequestBody final Workshop workshop) {
        return workshopService.saveWorkshop(workshop);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Workshop> getWorkshop(@PathVariable(value = "id") String id) {
        return workshopService.getWorkshopById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Flux<Workshop> getAllUpSkill() {
        return workshopService.getAllWorshop();
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Workshop> updateWorkshop(@PathVariable(value = "id") String id, @RequestBody final Workshop workshop) {
        return workshopService.updateWorkshop(id, workshop);
    }

    @DeleteMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void deleteWorkshop(@PathVariable(value = "id") String id) {
        workshopService.deleteWorkshopById(id);
    }

    @PutMapping(value = "addParticipant/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Workshop> addParticipant(@PathVariable(value = "id") String id, @RequestBody final String participant) {
        return workshopService.addParticipant(id, participant);
    }

    @PutMapping(value = "deleteParticipant/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Workshop> deleteParticipant(@PathVariable(value = "id") String id, @RequestBody final String participant) {
        return workshopService.deleteParticipant(id, participant);
    }

}
