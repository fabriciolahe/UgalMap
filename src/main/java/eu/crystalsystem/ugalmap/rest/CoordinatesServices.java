package eu.crystalsystem.ugalmap.rest;

import java.util.*;

import eu.crystalsystem.ugalmap.models.BuildingCoordinates;
import eu.crystalsystem.ugalmap.repositories.BuildingCoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.crystalsystem.ugalmap.models.Coordinates;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.CoordinatesRepository;
import eu.crystalsystem.ugalmap.rest.request.CoordinatesRequest;
import eu.crystalsystem.ugalmap.rest.response.CoordinatesResponse;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesServices {
	
	@Autowired
	private CoordinatesRepository coordinatesRepo;
	@Autowired
	private BuildingCoordinatesRepository buildCordRepo;

	
	@GetMapping("/{id}")
	public CoordinatesResponse getCoordinatesById(@PathVariable int coordinatesId) {
		CoordinatesResponse coordinatesResponse;
		Optional<Coordinates> coordinates = coordinatesRepo.findById(coordinatesId);
		if (coordinates.isPresent()) {
			List<Coordinates> coordinatesList = new ArrayList<>();
			coordinatesList.add(coordinates.get());
			coordinatesResponse = new CoordinatesResponse(new GenericHeader(true, "Coordinatese found"), coordinatesList);
		} else {
			coordinatesResponse = new CoordinatesResponse(new GenericHeader(false, "Coordinatese not found"));
		}
		return coordinatesResponse;
	}
	
	@GetMapping("/all")
	public CoordinatesResponse getAll() {
		List<Coordinates> coordinates = coordinatesRepo.findAll();
		return new CoordinatesResponse(new GenericHeader(true, "Coordinates found"), coordinates);
	}
	
	@PostMapping(value = "/add")
	public CoordinatesResponse addCoordinates(@RequestBody CoordinatesRequest coordinatesRequest) {
		CoordinatesResponse coordinatesResponse;
		try {
			if (coordinatesRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| coordinatesRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				List<Coordinates> coordinatesList = new ArrayList<>();
				List<Coordinates> list = coordinatesRepo.findAll();

				for(Coordinates cord : list){
					if(cord.getCoordinatesLatitude()==coordinatesRequest.getCoordinates().getCoordinatesLatitude()&&
					cord.getCoordinatesLongitute()==coordinatesRequest.getCoordinates().getCoordinatesLongitute()){
						return new CoordinatesResponse(new GenericHeader(false, "Cordinate Already Exist"));
					}
				}
					coordinatesList.add(coordinatesRepo.save(coordinatesRequest.getCoordinates()));
					coordinatesResponse = new CoordinatesResponse(
							new GenericHeader(true,"Coordinate  Created Successfully "),
							coordinatesList);

			} else {
				coordinatesResponse = new CoordinatesResponse(new GenericHeader(false, "Access Denied"));
				}
		}catch(Exception e) {
			coordinatesResponse = new CoordinatesResponse(new GenericHeader(false, "Exception : " + e.getMessage()));
		}
		return coordinatesResponse;
	}

	@PostMapping(value = "/addFromMap")
	public CoordinatesResponse addCoordinates(@RequestBody LinkedHashMap<String,Double> map) {
		Coordinates  cord = new Coordinates();
		Iterator<String> iterator =  map.keySet().iterator();
		ArrayList<Double> values = new ArrayList<>();
		while(iterator.hasNext()) {
			String key = iterator.next();
			values.add(map.get(key));
		}
		cord.setCoordinatesLatitude(values.get(0));
		cord.setCoordinatesLongitute(values.get(1));
		List<Coordinates> list = coordinatesRepo.findAll();

		for(Coordinates cordinate : list){
			if(cord.getCoordinatesLatitude()==cordinate.getCoordinatesLatitude()&&
					cordinate.getCoordinatesLongitute()==cordinate.getCoordinatesLongitute()){
				return null;
			}
		}
		coordinatesRepo.save(cord);
		return new CoordinatesResponse(new GenericHeader(false, "Already Exist"));
	}
	
	@PutMapping("/update/{id}")
	public CoordinatesResponse updateCoordinates(@RequestBody CoordinatesRequest coordinatesRequest, @PathVariable int id) {
		Optional<Coordinates> coordinatesOptional = coordinatesRepo.findById(id);
		CoordinatesResponse coordinatesResponse;
		List<Coordinates> coordinatesList = new ArrayList<>();
		try {
			if (coordinatesRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| coordinatesRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if (coordinatesOptional.isPresent()) {
					coordinatesOptional.get().setCoordinatesLatitude(coordinatesRequest.getCoordinates().getCoordinatesLatitude());
					coordinatesOptional.get().setCoordinatesLongitute(coordinatesRequest.getCoordinates().getCoordinatesLongitute());
					coordinatesList.add(coordinatesRepo.save(coordinatesOptional.get()));
					coordinatesResponse = new CoordinatesResponse(
								new GenericHeader(true,
										"Coordinates " + coordinatesRequest.getCoordinates().getCoordinatesId() + " Updated"),
								coordinatesList);
				}else {
					coordinatesResponse = new CoordinatesResponse(new GenericHeader(false,"Coordinates not founded"));
				}
			} else {
				coordinatesResponse = new CoordinatesResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			coordinatesResponse = new CoordinatesResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return coordinatesResponse;
	}
	
	@DeleteMapping(value = "/delete/{coordinatesId}")
	public CoordinatesResponse delete(@PathVariable("coordinatesId") int coordinatesId) {
		Optional<Coordinates> coordinates = coordinatesRepo.findById(coordinatesId);
		CoordinatesResponse coordinatesResponse = null;
		if (coordinates.isPresent()) {
			List<BuildingCoordinates> list = buildCordRepo.findAll();
			Coordinates e = coordinatesRepo.findByCoordinatesId(coordinatesId);
			Boolean found =false;
			for(BuildingCoordinates cord : list){
				if(cord.getCoordinates().equals(e)){
					found = true;
					coordinatesResponse= new CoordinatesResponse(new GenericHeader(true, "Coordinate  cannot be removet because it is used"));
				}
			}
			if (found==false){
				coordinatesRepo.deleteById(coordinatesId);
				coordinatesResponse= new CoordinatesResponse(new GenericHeader(true, "Coordinates removed"));
			}
		} else {
			coordinatesResponse = new CoordinatesResponse(new GenericHeader(true, "Coordinates doesn't exist"));
		}
		return coordinatesResponse;
	}
}
