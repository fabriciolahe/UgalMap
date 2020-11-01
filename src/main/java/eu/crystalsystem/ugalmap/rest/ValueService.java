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

import eu.crystalsystem.ugalmap.models.Dictionary;
import eu.crystalsystem.ugalmap.models.EntityLabel;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.models.Value;
import eu.crystalsystem.ugalmap.repositories.DictionaryRepository;
import eu.crystalsystem.ugalmap.repositories.EntityLabelRepository;
import eu.crystalsystem.ugalmap.repositories.UserRepository;
import eu.crystalsystem.ugalmap.repositories.ValueRepository;
import eu.crystalsystem.ugalmap.rest.request.ValueRequest;
import eu.crystalsystem.ugalmap.rest.response.ValueResponse;

@RestController
@RequestMapping("/value")
public class ValueService {
	
	@Autowired
	private ValueRepository valueRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DictionaryRepository dictionaryRepo;
	
	@Autowired
	private EntityLabelRepository entityLabelRepo;
	
	
	@GetMapping("/all")
	public ValueResponse showAll(){
		List<Value> value=valueRepo.findAll();
		return new ValueResponse(new GenericHeader(true,"Values founded"),value);
	}
	
	@PostMapping("/post")
	public ValueResponse postValue(@RequestBody ValueRequest valueRequest) {
		ValueResponse valueResponse;
		List<Value> listValues=new ArrayList<>();
		try {
			if(valueRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if(valueRepo.findByValueContent(valueRequest.getValue().getValueContent()) == null ) {
					Value value=new Value(valueRequest.getValue().getValueContent(), valueRequest.getValue().getValueType());
					listValues.add(valueRepo.save(value));
					valueResponse = new ValueResponse(new GenericHeader(true, "Value inserted"),listValues);
					
					
				} else {
					valueResponse = new ValueResponse(new GenericHeader(false, "Value "+ valueRequest.getValue().getValueContent()
							+ " already exists"));
				}
			} else {
			valueResponse = new ValueResponse(new GenericHeader(false, "Acces Denied"));
			}
			} catch (Exception e) {
				valueResponse = new ValueResponse(new GenericHeader(false,"Exception: " + e.getMessage()));
				e.printStackTrace();
			}
	
		return valueResponse;
	}
	
	@DeleteMapping("/delete/{id}")
	public ValueResponse delete(@PathVariable int id) {
		Optional<Value> value= valueRepo.findById(id);
		ValueResponse valueResponse;
		if(value.isPresent()) {
			List<Dictionary> listDictionary = dictionaryRepo.findAll();
			Value val = valueRepo.findByValueId(id);
			boolean found = false;
			for (Dictionary dictionary : listDictionary) {
				if(dictionary.getValue()== val) {
					found = true;
				}
			}	
			List<EntityLabel> listEntityLabel =	entityLabelRepo.findAll();	
			for(EntityLabel entityLab : listEntityLabel) {
				if(entityLab.getValue() == val) {
					found=true;
				}
			}
			if( found == false) {
				valueRepo.deleteById(id);
				valueResponse = new ValueResponse(new GenericHeader(true,"Value "+value.get().getValueContent() + " removed"));
					
		} else {
			valueResponse = new ValueResponse(new GenericHeader(true,"Value "+value.get().getValueContent() + " is in use and can't be deleted"));
		}
		} else {
			valueResponse = new ValueResponse(new GenericHeader(true,"Value "+value.get().getValueContent() + " doesn't exist"));
		}
		return valueResponse;	
	
	}
	
	
	@PutMapping("/update/{valueId}")
	public ValueResponse updateValue(@RequestBody ValueRequest valueRequest,@PathVariable("valueId") int valueId) {
		Optional<Value> value= valueRepo.findById(valueId);
		ValueResponse valueResponse;
		List<Value> listValues= new ArrayList<>();
		try {
		if(valueRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
			if(value.isPresent()) {
				value.get().setValueContent(valueRequest.getValue().getValueContent());
				value.get().setValueType(valueRequest.getValue().getValueType());
				listValues.add(valueRepo.save(value.get()));
				valueResponse = new ValueResponse(new GenericHeader(true,"Value updated"),listValues);
			}
			else {
				valueResponse = new ValueResponse(new GenericHeader(false,"Value not found"));
			}
			} else {
				valueResponse= new ValueResponse(new GenericHeader(true,"Acces Denied"));
			}
		} catch(Exception e) {
			valueResponse = new ValueResponse(new GenericHeader(false , "Exception: " + e.getMessage()));
		}
		return valueResponse;
	}
	
	

}
