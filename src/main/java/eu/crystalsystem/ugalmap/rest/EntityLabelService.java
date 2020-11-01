package eu.crystalsystem.ugalmap.rest;

import eu.crystalsystem.ugalmap.models.*;
import eu.crystalsystem.ugalmap.repositories.*;
import eu.crystalsystem.ugalmap.rest.request.EntityLabelRequest;
import eu.crystalsystem.ugalmap.rest.response.EntityLabelResponse;
import eu.crystalsystem.ugalmap.rest.response.MapEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entityLabel")
public class EntityLabelService {

    @Autowired
    private BuildingRepository buildRepo;
    @Autowired
    private LabelRepository labelRepo;
    @Autowired
    private ScheduleRepository schRepo;
    @Autowired
    private EntityRepository entityRepo;
    @Autowired
    private ValueRepository valueRepo;
    @Autowired
    private EntityLabelRepository entityLabelRepo;
    @Autowired
    private EntityLabelRepository entityLabelrepo;

    @GetMapping(value = "/all")
    public EntityLabelResponse getAllEntityLabel() {
        List<EntityLabel> entityLabel = entityLabelRepo.findAll();
        return new EntityLabelResponse(new GenericHeader(true, "EntityLabel found"), entityLabel);
    }

    @GetMapping(value = "/{id}")
    public EntityLabelResponse getEntityLabel(@PathVariable int id) {
        EntityLabelResponse entityLabelResponse;
        Optional<EntityLabel> entityLabel = entityLabelRepo.findById(id);
        if (entityLabel.isPresent()) {
            List<EntityLabel> entityLabelLIst = new ArrayList<>();
            entityLabelLIst.add(entityLabel.get());
            entityLabelResponse = new EntityLabelResponse(new GenericHeader(true, "EntityLabel is Found"),
                    entityLabelLIst);
        } else {
            entityLabelResponse = new EntityLabelResponse(new GenericHeader(false, "EntityLabel Not Found"), null);
        }
        return entityLabelResponse;
    }

    @PostMapping(value = "/post")
    public EntityLabelResponse addEntityLabelResponsee(@RequestBody EntityLabelRequest entityLabelRequest) {
        EntityLabelResponse entityLabelResponse;
        List<EntityLabel> entityLabelList = new ArrayList<>();
        try {
            if (entityLabelRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
                    || entityLabelRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
                List<EntityLabel> list = entityLabelRepo.findAll();
                EntityLabel entityLabel = new EntityLabel(
                        buildRepo.findByBuildingId(entityLabelRequest.getBuilding().getBuildingId()),
                        entityRepo.findByEntityId(entityLabelRequest.getEntity().getEntityId()),
                        schRepo.findByScheduleId(entityLabelRequest.getSchedule().getScheduleId()),
                        entityLabelRequest.getEntitylabel().getEntityLabelType(),
                        labelRepo.findByLabelId(entityLabelRequest.getLabel().getLabelId()),
                        valueRepo.findByValueId(entityLabelRequest.getValue().getValueId()));

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getBuilding() != null && list.get(i).getSchedule() != null && list.get(i).getLabel() != null && list.get(i).getValue() != null) {
                        if (entityLabel.getBuilding().getBuildingId() == list.get(i).getBuilding().getBuildingId() &&
                                entityLabel.getEntity().getEntityId() == list.get(i).getEntity().getEntityId() &&
                                entityLabel.getSchedule().getScheduleId() == list.get(i).getSchedule().getScheduleId() &&
                                entityLabel.getEntityLabelType().equals(list.get(i).getEntityLabelType()) &&
                                entityLabel.getLabel().getLabelId() == list.get(i).getLabel().getLabelId() &&
                                entityLabel.getValue().getValueId() == list.get(i).getValue().getValueId()
                        ) {
                            return new EntityLabelResponse(new GenericHeader(true, "Entity Label already Exist"), entityLabelList);
                        }
                    }
                }

                entityLabelList.add(entityLabelRepo.save(entityLabel));

                entityLabelResponse = new EntityLabelResponse(new GenericHeader(true, "Inserted EntityLabel"),
                        entityLabelList);
            } else {
                entityLabelResponse = new EntityLabelResponse(new GenericHeader(false, "Access Denied"), null);
            }
        } catch (Exception e) {
            entityLabelResponse = new EntityLabelResponse(new GenericHeader(false, "Exceptions " + e.getMessage()),
                    null);
        }
        return entityLabelResponse;
    }

    @PutMapping("/update/{id}")
    public EntityLabelResponse updateValue(@RequestBody EntityLabelRequest entityLabelReq, @PathVariable int id) {
        Optional<EntityLabel> value = entityLabelRepo.findById(id);
        EntityLabelResponse labelResponse;
        List<EntityLabel> listLabel = new ArrayList<>();
        try {
            if (entityLabelReq.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
                if (value.isPresent()) {

                    EntityLabel entitylabel = value.get();
                    Entity entity = entityRepo.findById(entityLabelReq.getEntity().getEntityId()).get();
                    Building building = buildRepo.findById(entityLabelReq.getBuilding().getBuildingId()).get();
                    Schedule schedule = schRepo.findById(entityLabelReq.getSchedule().getScheduleId()).get();
                    Label label = labelRepo.findById(entityLabelReq.getLabel().getLabelId()).get();
                    Value valueV = valueRepo.findById(entityLabelReq.getValue().getValueId()).get();
                    entitylabel.setEntityLabelType(entityLabelReq.getEntitylabel().getEntityLabelType());
                    entitylabel.setEntity(entity);
                    entitylabel.setBuilding(building);
                    entitylabel.setSchedule(schedule);
                    entitylabel.setLabel(label);
                    entitylabel.setValue(valueV);
                    listLabel.add(entityLabelRepo.save(entitylabel));

                    labelResponse = new EntityLabelResponse(new GenericHeader(true, "EntityLabel updated"), listLabel);
                } else {
                    labelResponse = new EntityLabelResponse(new GenericHeader(false, "EntityLabel not found"), null);
                }
            } else {
                labelResponse = new EntityLabelResponse(new GenericHeader(true, "Acces Denied"), null);
            }
        } catch (Exception e) {
            labelResponse = new EntityLabelResponse(new GenericHeader(false, "Exception: " + e.getMessage()), null);
        }
        return labelResponse;
    }

    @DeleteMapping(value = "/delete/{id}")
    public GenericHeader deleteEntityLabel(@PathVariable int id) {
        Optional<EntityLabel> entityLabel = entityLabelRepo.findById(id);
        GenericHeader genericHeader;
        if (entityLabel.isPresent()) {
            entityLabelRepo.deleteById(id);
            genericHeader = new GenericHeader(true, "EntityLabel " + entityLabel.get().getEntityLabelId() + " removed");
        } else {
            genericHeader = new GenericHeader(true, "EntityLabel doesn't exist");
        }
        return genericHeader;
    }

    @GetMapping(value = "/mapEntities")
    public MapEntities mapEntities() {
        return new MapEntities(
                entityLabelrepo.findByEntityEntityIdNotNullOrBuildingBuildingIdNotNullAndScheduleScheduleIdIsNull());
    }

    @GetMapping(value = "/buildingByEntity")
    public EntityLabel map(@Param("entityName") String entityName) {
        return entityLabelrepo.findByLabelLabelNameAndValueValueContent("name", entityName);
    }
}
