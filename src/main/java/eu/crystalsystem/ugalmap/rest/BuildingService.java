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

import eu.crystalsystem.ugalmap.models.Building;
import eu.crystalsystem.ugalmap.models.BuildingCoordinates;
import eu.crystalsystem.ugalmap.models.BuildingEntity;
import eu.crystalsystem.ugalmap.models.EntityLabel;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.BuildingCoordinatesRepository;
import eu.crystalsystem.ugalmap.repositories.BuildingEntityRepository;
import eu.crystalsystem.ugalmap.repositories.BuildingRepository;
import eu.crystalsystem.ugalmap.repositories.EntityLabelRepository;
import eu.crystalsystem.ugalmap.rest.request.BuildingRequest;
import eu.crystalsystem.ugalmap.rest.response.BuildingResponse;

@RestController
@RequestMapping("/building")
public class BuildingService {
	
	@Autowired
	private BuildingRepository buildingRepo;
	
	@Autowired
	private BuildingEntityRepository buildingEntityRepository;
	
	@Autowired
	private EntityLabelRepository entityLabelRepository;
	
	@Autowired
	private BuildingCoordinatesRepository buildingCoordinatesRepository;
	
	
	@GetMapping("/{id}")
	public BuildingResponse getBuildingById(@PathVariable int buildingId) {
		BuildingResponse buildingResponse;
		Optional<Building> building = buildingRepo.findById(buildingId);
		if (building.isPresent()) {
			List<Building> buildingList = new ArrayList<>();
			buildingList.add(building.get());
			buildingResponse = new BuildingResponse(new GenericHeader(true, "Building found"), buildingList);
		} else {
			buildingResponse = new BuildingResponse(new GenericHeader(false, "Building not found"));
		}

		return buildingResponse;
	}
	
	@GetMapping("/all")
	public BuildingResponse getAll() {
		List<Building> building = buildingRepo.findAll();
		return new BuildingResponse(new GenericHeader(true, "Building found"), building);
	}
	
	@PostMapping(value = "/add")
	public BuildingResponse addBuilding(@RequestBody BuildingRequest buildingRequest) {
		BuildingResponse buildingResponse;
		try {
			if (buildingRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| buildingRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				List<Building> buildingList = new ArrayList<>();
				if (buildingRepo.findByBuildingId(buildingRequest.getBuilding().getBuildingId()) == null) {
					buildingList.add(buildingRepo.save(buildingRequest.getBuilding()));
					buildingResponse = new BuildingResponse(
							new GenericHeader(true,"Building " + buildingRequest.getBuilding().getBuildingId() + " Created"),
							buildingList);
				} else {
					buildingResponse = new BuildingResponse(
							new GenericHeader(false,"Building " + buildingRequest.getBuilding().getBuildingId() + " already Exists"));
				}
			} else {
				buildingResponse = new BuildingResponse(new GenericHeader(false, "Access Denied"));
				}
		}catch(Exception e) {
			buildingResponse = new BuildingResponse(new GenericHeader(false, "Exception : " + e.getMessage()));
		}
		return buildingResponse;
	}

	@PutMapping("/update/{id}")
	public BuildingResponse updateBuilding(@RequestBody BuildingRequest buildingRequest, @PathVariable int id) {
		Optional<Building> buildingOptional = buildingRepo.findById(id);
		BuildingResponse buildingResponse;
		List<Building> buildingList = new ArrayList<>();
		try {
			if (buildingRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| buildingRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if (buildingOptional.isPresent()) {
					buildingOptional.get().setBuildingId(buildingRequest.getBuilding().getBuildingId());
					buildingList.add(buildingRepo.save(buildingOptional.get()));
						buildingResponse = new BuildingResponse(
								new GenericHeader(true,
										"Building " + buildingRequest.getBuilding().getBuildingId() + " Updated"),
								buildingList);
				}else {
					buildingResponse = new BuildingResponse(new GenericHeader(false,"Building not founded"));
				}
			} else {
				buildingResponse = new BuildingResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			buildingResponse = new BuildingResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return buildingResponse;
	}
	
	@DeleteMapping(value = "/delete/{buildingId}")
	public BuildingResponse delete(@PathVariable("buildingId") int buildingId) {
		Optional<Building> building = buildingRepo.findById(buildingId);
		BuildingResponse buildingResponse = null;
		if (building.isPresent()) {
			List<BuildingEntity> listBuildingEntity = buildingEntityRepository.findAll();
			List<EntityLabel> listEntityLabel = entityLabelRepository.findAll();
			List<BuildingCoordinates>listBuildingCoordinates = buildingCoordinatesRepository.findAll();
			Building build = buildingRepo.findByBuildingId(buildingId);
			Boolean found =false;
			for(BuildingEntity buildingEntity : listBuildingEntity){
				if(buildingEntity.getBuilding() == build){
					found = true;
				}
			}
			
			for(EntityLabel entityLabel : listEntityLabel){
				if(entityLabel.getBuilding() == build){
					found = true;
				}
			}
			
			for(BuildingCoordinates buildingCoordinates : listBuildingCoordinates){
				if(buildingCoordinates.getBuilding() == build){
					found = true;
				}
			}
			
			if (found==false){
			buildingRepo.deleteById(buildingId);
			buildingResponse = new BuildingResponse(new GenericHeader(true, "Building " + building.get().getBuildingId() + " removed"));
			}else {
				buildingResponse= new BuildingResponse(new GenericHeader(true, "Building " + building.get().getBuildingId() + " cannot be removed because it is used"));
			}
		} else {
			buildingResponse = new BuildingResponse( new GenericHeader(true, "Building doesn't exist"));
		}
		return buildingResponse;
	}
	
}
