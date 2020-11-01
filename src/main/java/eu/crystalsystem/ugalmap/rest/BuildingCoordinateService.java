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

import eu.crystalsystem.ugalmap.models.BuildingCoordinates;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.BuildingCoordinatesRepository;
import eu.crystalsystem.ugalmap.repositories.BuildingRepository;
import eu.crystalsystem.ugalmap.repositories.CoordinatesRepository;
import eu.crystalsystem.ugalmap.rest.request.BuildingCoordinatesRequest;
import eu.crystalsystem.ugalmap.rest.response.BuildingCoordinateResponse;
import eu.crystalsystem.ugalmap.rest.response.LocationResponse;

@RestController
@RequestMapping("/location")
public class BuildingCoordinateService {

	@Autowired
	BuildingCoordinatesRepository buildingCoordinatesRepository;
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private CoordinatesRepository coordinatesRepository;

	@GetMapping("/{buildingId}")
	public LocationResponse getBuildingLocation(@PathVariable int buildingId) {
		return new LocationResponse(buildingCoordinatesRepository.findByBuildingBuildingId(buildingId));
	}

	@GetMapping("/all")
	public LocationResponse getAllBuildingsLocation() {
		return new LocationResponse(buildingCoordinatesRepository.findAll());
	}
	
	@PostMapping("/add")
	public BuildingCoordinateResponse addBuildingCoordinate(@RequestBody BuildingCoordinatesRequest buildingCoordinateRequest) {
		
		BuildingCoordinateResponse buildingCoordinateResponse;
		List<BuildingCoordinates> buildingCoordinatesList = new ArrayList<>();
		try {
			if (buildingCoordinateRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {			
				BuildingCoordinates buildingCoordinate = new BuildingCoordinates(
						buildingRepository.findByBuildingId(buildingCoordinateRequest.getBuildingCoordinates().getBuilding().getBuildingId()),
						coordinatesRepository.findByCoordinatesId(buildingCoordinateRequest.getBuildingCoordinates().getCoordinates().getCoordinatesId()),
						buildingCoordinateRequest.getBuildingCoordinates().getPosition());
			buildingCoordinatesList.add(buildingCoordinatesRepository.save(buildingCoordinate));
			buildingCoordinateResponse = new BuildingCoordinateResponse(new GenericHeader(true, "Location successfuly inserted"),
					buildingCoordinatesList);

		}else {
			buildingCoordinateResponse = new BuildingCoordinateResponse(new GenericHeader(false, "Access Denied"),null);
		}			
		}catch(Exception e) {
			buildingCoordinateResponse = new BuildingCoordinateResponse(new GenericHeader(false, "Exceptions " + e.getMessage()), null);
			e.printStackTrace();
		}
		return buildingCoordinateResponse;
		
	}
	
	@PutMapping("/update")
	public BuildingCoordinateResponse updateBuildingCoordinate(@RequestBody BuildingCoordinatesRequest buildingCoordinateRequest) {
		BuildingCoordinateResponse buildingCoordinateResponse;
		List<BuildingCoordinates> buildingCoordinatesList = new ArrayList<>();
		try {
			if (buildingCoordinateRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
					buildingCoordinatesList.add(buildingCoordinatesRepository.save(buildingCoordinateRequest.getBuildingCoordinates()));
					buildingCoordinateResponse = new BuildingCoordinateResponse(
							new GenericHeader(true, "BuildingCoordinates updated"), buildingCoordinatesList);
			} else {
				buildingCoordinateResponse = new BuildingCoordinateResponse(new GenericHeader(true, "Acces Denied"), null);
			}
		} catch (Exception e) {
			buildingCoordinateResponse = new BuildingCoordinateResponse(
					new GenericHeader(false, "Exception: " + e.getMessage()), null);
			e.printStackTrace();
		}
		return buildingCoordinateResponse;
	}

	@DeleteMapping("/delete/{id}")
	public GenericHeader delete(@PathVariable int id) {
		Optional<BuildingCoordinates> buildingCoordinatesOptional = buildingCoordinatesRepository.findById(id);
		GenericHeader genericHeader;
		if (buildingCoordinatesOptional.isPresent()) {
			buildingCoordinatesRepository.deleteById(id);
			genericHeader = new GenericHeader(true,
					"BuildingEntity " + buildingCoordinatesOptional.get().getBuildingCoordinatesId() + " removed");
		} else {
			genericHeader = new GenericHeader(false, "BuildingCoordinates does not exists");
		}
		return genericHeader;
	}
}

