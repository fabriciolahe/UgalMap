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
import eu.crystalsystem.ugalmap.models.LabelValueType;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.DictionaryRepository;
import eu.crystalsystem.ugalmap.repositories.LabelRepository;
import eu.crystalsystem.ugalmap.repositories.LanguageRepository;
import eu.crystalsystem.ugalmap.repositories.UserRepository;
import eu.crystalsystem.ugalmap.repositories.ValueRepository;
import eu.crystalsystem.ugalmap.rest.request.DictionaryAddRequest;
import eu.crystalsystem.ugalmap.rest.request.DictionaryRequest;
import eu.crystalsystem.ugalmap.rest.response.DictionaryResponse;

@RestController
@RequestMapping(path = "/dictionary")
public class DictionaryService {

	@Autowired
	private DictionaryRepository dictionaryRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private ValueRepository valueRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{id}")
	public DictionaryResponse getDictionaryById(@PathVariable int dictionaryId) {
		DictionaryResponse dictionaryResponse;
		Optional<Dictionary> dictionary = dictionaryRepository.findById(dictionaryId);
		if(dictionary.isPresent()) {
			List<Dictionary> dictionaryList = new ArrayList<>();
			dictionaryList.add(dictionary.get());
			dictionaryResponse = new DictionaryResponse(new GenericHeader(true, "Language found"), dictionaryList);
		}else {
			dictionaryResponse = new DictionaryResponse(new GenericHeader(false, "Language not found"));
		}

		return dictionaryResponse;
	}

	@GetMapping("/all")
	public DictionaryResponse getAll() {
		List<Dictionary> dictionary = dictionaryRepository.findAll();
		return new DictionaryResponse(new GenericHeader(true, "Languages found"),
				dictionary);
	}

	@PostMapping("/add")
	public DictionaryResponse add(@RequestBody DictionaryRequest dictionaryRequest) {
		DictionaryResponse dictionaryResponse;
		List<Dictionary> dictionaryList = new ArrayList<>();
		try {
			if (dictionaryRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if (dictionaryRequest.getDictionary().getDictionaryLabelValueType().equals(LabelValueType.LABEL)) {
					Dictionary dictionary = new Dictionary(dictionaryRequest.getDictionary().getDictionaryTranslatedValue(),
							dictionaryRequest.getDictionary().getDictionaryLabelValueType(),
							languageRepository.findByLanguageId(dictionaryRequest.getDictionary().getLanguage().getLanguageId()),
							labelRepository.findByLabelId(dictionaryRequest.getDictionary().getLabel().getLabelId()), null);
					dictionaryList.add(dictionaryRepository.save(dictionary));
					dictionaryResponse = new DictionaryResponse(new GenericHeader(true, "Label successfuly translated"),
							dictionaryList);
				} else if (dictionaryRequest.getDictionary().getDictionaryLabelValueType().equals(LabelValueType.VALUE)) {
					Dictionary dictionary = new Dictionary(dictionaryRequest.getDictionary().getDictionaryTranslatedValue(),
							dictionaryRequest.getDictionary().getDictionaryLabelValueType(),
							languageRepository.findByLanguageId(dictionaryRequest.getDictionary().getLanguage().getLanguageId()), null,
							valueRepository.findByValueId(dictionaryRequest.getDictionary().getValue().getValueId()));
					dictionaryList.add(dictionaryRepository.save(dictionary));
					dictionaryResponse = new DictionaryResponse(new GenericHeader(true, "Value successfuly translated"),
							dictionaryList);
				} else {
					dictionaryResponse = new DictionaryResponse(
							new GenericHeader(false, "labelValueId  param invalid"));
				}
			}else {
				dictionaryResponse = new DictionaryResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			dictionaryResponse = new DictionaryResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return dictionaryResponse;
	}

	@PutMapping("/update")
	public DictionaryResponse updateDictionary(@RequestBody DictionaryRequest dictionaryRequest) {
		DictionaryResponse dictionaryResponse;
		List<Dictionary> dictionaryList = new ArrayList<>();
		try {
			if (dictionaryRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| dictionaryRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				dictionaryList.add(dictionaryRepository.save(dictionaryRequest.getDictionary()));
				dictionaryResponse = new DictionaryResponse(new GenericHeader(true, "Value successfuly updated"),
						dictionaryList);
			} else {
				dictionaryResponse = new DictionaryResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			dictionaryResponse = new DictionaryResponse(new GenericHeader(false, "Exeption : " + e));
		}
		return dictionaryResponse;
	}

	@DeleteMapping(value = "/delete/{dictionaryId}")
	public GenericHeader delete(@PathVariable("dictionaryId") int dictionaryId) {
		Optional<Dictionary> dictionary = dictionaryRepository.findById(dictionaryId);
		GenericHeader genericHeader;
		if (dictionary.isPresent()) {
			dictionaryRepository.deleteById(dictionaryId);
			genericHeader = new GenericHeader(true, "Dictionary " + dictionary.get().getDictionaryId() + " removed");
		} else {
			genericHeader = new GenericHeader(true, "Dictionary doesn't exist");
		}
		return genericHeader;
	}

	@GetMapping("/testPayload")
	public DictionaryAddRequest testPayload() {
		return new DictionaryAddRequest(1, 2, "translated Value", "LABEL", userRepository.findByUserId(1));
	}

}
