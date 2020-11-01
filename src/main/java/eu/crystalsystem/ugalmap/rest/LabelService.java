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
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Label;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.DictionaryRepository;
import eu.crystalsystem.ugalmap.repositories.LabelRepository;
import eu.crystalsystem.ugalmap.repositories.UserRepository;
import eu.crystalsystem.ugalmap.rest.request.LabelRequest;
import eu.crystalsystem.ugalmap.rest.response.LabelResponse;

@RestController
@RequestMapping("/label")
public class LabelService {
	
	@Autowired
	private LabelRepository labelRepo;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/all")
	public LabelResponse getAll() {
		List<Label> labelList = labelRepo.findAll();
		return new LabelResponse(new GenericHeader(true, "Labels found"), labelList);
	}
	
	@GetMapping("/{id}")
	public LabelResponse getLabelById(@PathVariable int labelId) {
		LabelResponse labelResponse;
		Optional<Label> label = labelRepo.findById(labelId);
		if (label.isPresent()) {
			List<Label> labelList = new ArrayList<>();
			labelList.add(label.get());
			labelResponse = new LabelResponse(new GenericHeader(true, "Label found"), labelList);
		} else {
			labelResponse = new LabelResponse(new GenericHeader(false, "Label not found"));
		}

		return labelResponse;
	}
	
	@PostMapping(value = "/add")
	public LabelResponse addLabel(@RequestBody LabelRequest labelRequest) {
		LabelResponse labelResponse;
		try {
			if (labelRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| labelRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				List<Label> labelList = new ArrayList<>();
				if (labelRepo.findByLabelName(labelRequest.getLabel().getLabelName()) == null) {
					labelList.add(labelRepo.save(labelRequest.getLabel()));
					labelResponse = new LabelResponse(
							new GenericHeader(true,"Label " + labelRequest.getLabel().getLabelName() + " Created"),
							labelList);
				} else {
					labelResponse = new LabelResponse(
							new GenericHeader(false,"Label " + labelRequest.getLabel().getLabelName() + " already Exists"));
				}
			} else {
				labelResponse = new LabelResponse(new GenericHeader(false, "Access Denied"));
				}
		}catch(Exception e) {
			labelResponse = new LabelResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return labelResponse;
	}
	
	@PutMapping("/update/{id}")
	public LabelResponse updateLabel(@RequestBody LabelRequest labelRequest, @PathVariable int id) {
		Optional<Label> labelOptional = labelRepo.findById(id);
		LabelResponse labelResponse;
		List<Label> labelList = new ArrayList<>();
		try {
			if (labelRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| labelRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if (labelOptional.isPresent()) {
					labelOptional.get().setLabelName(labelRequest.getLabel().getLabelName());
					labelList.add(labelRepo.save(labelOptional.get()));
						labelResponse = new LabelResponse(
								new GenericHeader(true,
										"Label " + labelRequest.getLabel().getLabelName() + " Updated"),
								labelList);
				}else {
					labelResponse = new LabelResponse(new GenericHeader(false,"Label not founded"));
				}
			} else {
				labelResponse = new LabelResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			labelResponse = new LabelResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return labelResponse;
	}
	
	@DeleteMapping(value = "/delete/{labelId}")
	public LabelResponse delete(@PathVariable("labelId") int labelId) {
		Optional<Label> label = labelRepo.findById(labelId);
		LabelResponse labelResponse = null;
		if (label.isPresent()) {
			List<Dictionary> listDictionary = dictionaryRepository.findAll();
			Label lab = labelRepo.findByLabelId(labelId);
			Boolean found =false;
			for(Dictionary dict : listDictionary){
				if(dict.getLabel() == lab){
					found = true;
					labelResponse= new LabelResponse(new GenericHeader(true, "Label " +label.get().getLabelName() + " cannot be removed because it is used"));
				}
			}
			if (found==false){
			labelRepo.deleteById(labelId);
			labelResponse = new LabelResponse(new GenericHeader(true, "Label " + label.get().getLabelName() + " removed"));
			}
		} else {
			labelResponse = new LabelResponse(new GenericHeader(true, "Label doesn't exist"));
		}
		return labelResponse;
	}
	
	@GetMapping("/testPayload")
	public LabelRequest testPayload() {
		return new LabelRequest(labelRepo.findByLabelId(3), userRepository.findByUserId(1));
	}
	

}
