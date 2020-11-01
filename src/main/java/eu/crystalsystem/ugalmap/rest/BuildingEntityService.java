package eu.crystalsystem.ugalmap.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.crystalsystem.ugalmap.models.BuildingEntity;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.BuildingCoordinatesRepository;
import eu.crystalsystem.ugalmap.repositories.BuildingEntityRepository;
import eu.crystalsystem.ugalmap.repositories.BuildingRepository;
import eu.crystalsystem.ugalmap.repositories.EntityRepository;
import eu.crystalsystem.ugalmap.rest.request.BuildingEntityRequest;
import eu.crystalsystem.ugalmap.rest.response.BuildingEntityListResponse;
import eu.crystalsystem.ugalmap.rest.response.BuildingEntityResponse;
import eu.crystalsystem.ugalmap.rest.response.BuildingLocation;
import eu.crystalsystem.ugalmap.rest.response.BuildingsLocationResponse;

@RestController
@RequestMapping("/buildingEntity")
public class BuildingEntityService {
	@Autowired
	private BuildingEntityRepository buildingEntityRepo;

	@Autowired
	private EntityRepository entityRepo;

	@Autowired
	private BuildingRepository buildingRepo;

	@Autowired
	BuildingCoordinatesRepository buildingCoordinatesRepository;

	@GetMapping(value = "/all")
	public BuildingEntityResponse getAllBuildEntity() {
		List<BuildingEntity> list = buildingEntityRepo.findAll();
		return new BuildingEntityResponse(new GenericHeader(true, "Entity founded"), list);

	}

	@GetMapping(value = "/{id}")
	public BuildingEntityResponse getBuildingEntity(@PathVariable int id) {
		BuildingEntityResponse buildingEntityResponse;
		Optional<BuildingEntity> entity = buildingEntityRepo.findById(id);
		if (entity.isPresent()) {
			List<BuildingEntity> entityList = new ArrayList<>();
			entityList.add(entity.get());
			buildingEntityResponse = new BuildingEntityResponse(new GenericHeader(true, "EntityLabel is Found"),
					entityList);
		} else {
			buildingEntityResponse = new BuildingEntityResponse(new GenericHeader(false, "EntityLabel Not Found"),
					null);
		}
		return buildingEntityResponse;
	}

	@GetMapping(value = "/building/{entityId}")
	public BuildingsLocationResponse getBuildingByEntityId(@PathVariable int entityId) {
		List<BuildingLocation> locationResponse = new ArrayList<>();
		BuildingEntityListResponse buildingEntityListResponse = new BuildingEntityListResponse(
				buildingEntityRepo.findByEntityEntityId(entityId));
		for (BuildingEntity b : buildingEntityListResponse.getBuildingEntityList()) {
			locationResponse.add(new BuildingLocation(
					buildingCoordinatesRepository.findByBuildingBuildingId(b.getBuilding().getBuildingId())));
		}

		return new BuildingsLocationResponse(locationResponse);
	}

	@PostMapping(value = "/post")
	public BuildingEntityResponse addBuildingEntityResponse(@RequestBody BuildingEntityRequest buildingEntityRequest) {
		BuildingEntityResponse buildingEntityResponse;
		List<BuildingEntity> buildEntityList = new ArrayList<>();
		try {
			if (buildingEntityRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				List<BuildingEntity> list = buildingEntityRepo.findAll();

				BuildingEntity buildingEntity = new BuildingEntity(
						buildingRepo.findByBuildingId(buildingEntityRequest.getBuilding().getBuildingId()),
						entityRepo.findByEntityId(buildingEntityRequest.getEntity().getEntityId()));

				for(int i=0;i<list.size();i++){
					if(buildingEntity.getEntity().getEntityId()==list.get(i).getEntity().getEntityId() && buildingEntity.getBuilding().getBuildingId()
							==list.get(i).getBuilding().getBuildingId()){
						return new BuildingEntityResponse(new GenericHeader(true, "This Building Entity already exist"),
								buildEntityList);
					}
				}
				buildEntityList.add(buildingEntityRepo.save(buildingEntity));
				buildingEntityResponse = new BuildingEntityResponse(new GenericHeader(true, "BuildEntity Inserted"),
						buildEntityList);
			} else {
				buildingEntityResponse = new BuildingEntityResponse(new GenericHeader(false, "Access Denied"), null);
			}
		} catch (Exception e) {
			buildingEntityResponse = new BuildingEntityResponse(
					new GenericHeader(false, "Exceptions " + e.getMessage()), null);
		}
		return buildingEntityResponse;
	}

	@PutMapping("/update/{id}")
	public BuildingEntityResponse updateValue(@RequestBody BuildingEntityRequest buildingreq, @PathVariable int id) {
		Optional<BuildingEntity> value = buildingEntityRepo.findById(id);
		BuildingEntityResponse buildingEntityResponse;
		List<BuildingEntity> listbuildEntity = new ArrayList<>();
		try {
		if (buildingreq.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if (value.isPresent()) {
					value.get().setBuilding(buildingreq.getBuilding());
					value.get().setEntity(buildingreq.getEntity());
					listbuildEntity.add(buildingEntityRepo.save(value.get()));
					buildingEntityResponse = new BuildingEntityResponse(
							new GenericHeader(true, "BuildingEntity updated"), listbuildEntity);
				} else {
					buildingEntityResponse = new BuildingEntityResponse(
							new GenericHeader(false, "BuildEntity not found"), null);
				}
			} else {
				buildingEntityResponse = new BuildingEntityResponse(new GenericHeader(true, "Acces Denied"), null);
			}
		} catch (Exception e) {
			buildingEntityResponse = new BuildingEntityResponse(
					new GenericHeader(false, "Exception: " + e.getMessage()), null);
		}
		return buildingEntityResponse;
	}

	@DeleteMapping("/delete/{id}")
	public GenericHeader delete(@PathVariable int id) {
		Optional<BuildingEntity> buildingentity = buildingEntityRepo.findById(id);
		GenericHeader genericHeader;
		if (buildingentity.isPresent()) {
			buildingEntityRepo.deleteById(id);
			genericHeader = new GenericHeader(true,
					"BuildingEntity " + buildingentity.get().getBuildingEntityId() + " removed");
		} else {
			genericHeader = new GenericHeader(false, "BuildingEntity does not exists");
		}
		return genericHeader;
	}

}
