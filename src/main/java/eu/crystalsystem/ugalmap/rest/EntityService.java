package eu.crystalsystem.ugalmap.rest;

import eu.crystalsystem.ugalmap.models.BuildingEntity;
import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.models.Schedule;
import eu.crystalsystem.ugalmap.repositories.BuildingEntityRepository;
import eu.crystalsystem.ugalmap.repositories.EntityRepository;
import eu.crystalsystem.ugalmap.repositories.ScheduleRepository;
import eu.crystalsystem.ugalmap.rest.request.EntityRequest;
import eu.crystalsystem.ugalmap.rest.response.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entity")

public class EntityService {

    @Autowired
    private EntityRepository entityRepo;
    @Autowired
    private BuildingEntityRepository buildEntityRepo;
    @Autowired
    private ScheduleRepository schedueRepo;

    @GetMapping(value = "/all")
    public EntityResponse getAllEntity() {
        List<Entity> list = entityRepo.findAll();
        return new EntityResponse(new GenericHeader(true, "Entity founded"), list);

    }

    @GetMapping(value = "/{id}")
    public EntityResponse getEntity(@PathVariable int id) {
        EntityResponse entityResponse;
        Optional<Entity> entity = entityRepo.findById(id);
        if (entity.isPresent()) {
            List<Entity> entityList = new ArrayList<>();
            entityList.add(entity.get());
            entityResponse = new EntityResponse(new GenericHeader(true, "EntityLabel is Found"), entityList);
        } else {
            entityResponse = new EntityResponse(new GenericHeader(false, "EntityLabel Not Found"), null);
        }
        return entityResponse;
    }

    @PostMapping(value = "/post")
    public EntityResponse addEntity(@RequestBody EntityRequest entityRequest) {
        EntityResponse entityResponse;
        List<Entity> listEntity = new ArrayList<>();
        try {
            if (entityRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
                if (entityRepo.findByEntityType(entityRequest.getEntity().getEntityType()) == null) {
                    Entity entity = new Entity(entityRequest.getEntity().getEntityType());
                    listEntity.add(entityRepo.save(entity));
                    entityResponse = new EntityResponse(new GenericHeader(true, "Entity Created"), listEntity);
                } else {
                    entityResponse = new EntityResponse(new GenericHeader(false,
                            "Entity" + entityRequest.getEntity().getEntityType() + "Already exist"));
                }
            } else {
                entityResponse = new EntityResponse(new GenericHeader(false, "Access Denied"));
            }
        } catch (Exception e) {
            entityResponse = new EntityResponse(new GenericHeader(false, "Exceptions " + e.getMessage()));
        }
        return entityResponse;
    }

    @PutMapping(value = "/update/{id}")
    public EntityResponse updateEntity(@PathVariable int id, @RequestBody EntityRequest entityRequest) {
        Optional<Entity> entity = entityRepo.findById(id);
        EntityResponse entityResponse;
        List<Entity> listEntity = new ArrayList<>();
        try {
            if (entityRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
                if (entity.isPresent()) {
                    entity.get().setEntityType(entityRequest.getEntity().getEntityType());
                    listEntity.add(entityRepo.save(entity.get()));
                    entityResponse = new EntityResponse(new GenericHeader(true, "Entity updated"), listEntity);
                } else {
                    entityResponse = new EntityResponse(new GenericHeader(false, "Entity not found"));
                }
            } else {
                entityResponse = new EntityResponse(new GenericHeader(true, "Acces Denied"));
            }
        } catch (Exception e) {
            entityResponse = new EntityResponse(new GenericHeader(false, "Exception: " + e.getMessage()));
        }
        return entityResponse;
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public EntityResponse deleteEntityById(@PathVariable int id) {
        Optional<Entity> entity = entityRepo.findById(id);
        EntityResponse entityResponse;

        if (entity.isPresent()) {
            List<BuildingEntity> list = buildEntityRepo.findAll();
            List<Schedule> list2 =schedueRepo.findAll();
            Entity e = entityRepo.findByEntityId(id);
            boolean found = false;
            for (BuildingEntity buildEntity : list) {
                if (buildEntity.getEntity() == e) {
                    found = true;
                }
            }
            for(Schedule sch :list2){
                if(sch.getEntity() == e){
                    found = true;
                }
            }
            if (found == false) {
                entityRepo.deleteById(id);
                entityResponse = new EntityResponse(new GenericHeader(true, "Entity " + entity.get().getEntityId() + " removed"));
            } else {
                entityResponse = new EntityResponse(new GenericHeader(true, "Entity " + entity.get().getEntityId() + " is in use and cannot be deleted"));
            }
        } else {
            entityResponse = new EntityResponse(new GenericHeader(true, "Entity " + "doesn't exist"));
        }
        return entityResponse;
    }

}
