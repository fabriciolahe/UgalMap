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
import eu.crystalsystem.ugalmap.models.Language;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.repositories.DictionaryRepository;
import eu.crystalsystem.ugalmap.repositories.LanguageRepository;
import eu.crystalsystem.ugalmap.repositories.UserRepository;
import eu.crystalsystem.ugalmap.rest.request.LanguageRequest;
import eu.crystalsystem.ugalmap.rest.response.LanguageResponse;

@RestController
@RequestMapping(path = "/language")
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private DictionaryRepository dictionaryRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/{id}")
	public LanguageResponse getDictionaryById(@PathVariable int languageId) {
		LanguageResponse languageResponse;
		Optional<Language> language = languageRepository.findById(languageId);
		if (language.isPresent()) {
			List<Language> languageList = new ArrayList<>();
			languageList.add(language.get());
			languageResponse = new LanguageResponse(new GenericHeader(true, "Language found"), languageList);
		} else {
			languageResponse = new LanguageResponse(new GenericHeader(false, "Language not found"));
		}

		return languageResponse;
	}

	@GetMapping("/all")
	public LanguageResponse getAll() {
		List<Language> dictionary = languageRepository.findAll();
		return new LanguageResponse(new GenericHeader(true, "Languages found"), dictionary);
	}

	@PostMapping("/add")
	public LanguageResponse add(@RequestBody LanguageRequest languageRequest) {
		LanguageResponse languageResponse;
		try {
			if (languageRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| languageRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				List<Language> languageList = new ArrayList<>();
				if (languageRepository.findByLanguageName(languageRequest.getLanguage().getLanguageName()) == null) {
					languageList.add(languageRepository.save(languageRequest.getLanguage()));
					languageResponse = new LanguageResponse(
							new GenericHeader(true,
									"Language " + languageRequest.getLanguage().getLanguageName() + " Created"),
							languageList);
				} else {
					languageResponse = new LanguageResponse(
							new GenericHeader(false,
									"Language " + languageRequest.getLanguage().getLanguageName() + " already Exists"));
				}
			} else {
				languageResponse = new LanguageResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			languageResponse = new LanguageResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return languageResponse;
	}

	@PutMapping("/update")
	public LanguageResponse updateLanguage(@RequestBody LanguageRequest languageRequest) {
		LanguageResponse languageResponse;
		try {
			if (languageRequest.getRequesUser().getRole().getRoleName().equals(Role.ADMIN)
					|| languageRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				List<Language> languageList = new ArrayList<>();
					languageList.add(languageRepository.save(languageRequest.getLanguage()));
					languageResponse = new LanguageResponse(
							new GenericHeader(true,
									"Language " + languageRequest.getLanguage().getLanguageName() + " Updated"),
							languageList);
			} else {
				languageResponse = new LanguageResponse(new GenericHeader(false, "Access Denied"));
			}
		} catch (Exception e) {
			languageResponse = new LanguageResponse(new GenericHeader(false, "Exeption : " + e.getMessage()));
		}
		return languageResponse;
	}

	@DeleteMapping(value = "/delete/{languageId}")
	public LanguageResponse delete(@PathVariable("languageId") int languageId) {
		Optional<Language> language = languageRepository.findById(languageId);
		LanguageResponse languageResponse = null;
		if (language.isPresent()) {
			List<Dictionary> listDictionary = dictionaryRepository.findAll();
			Language lang = languageRepository.findByLanguageId(languageId);
			Boolean found =false;
			for(Dictionary dict : listDictionary){
				if(dict.getLanguage() == lang){
					found = true;
					languageResponse= new LanguageResponse(new GenericHeader(true, "Language " +language.get().getLanguageName() + " cannot be removed because it is used"));
				}
			}
			if (found==false){
			languageRepository.deleteById(languageId);
			languageResponse = new LanguageResponse(new GenericHeader(true, "Language " + language.get().getLanguageName() + " removed"));
			}
		} else {
			languageResponse = new LanguageResponse(new GenericHeader( true, "Language doesn't exist"));
		}
		return languageResponse;
	}

	@GetMapping("/testPayload")
	public LanguageRequest testPayload() {
		return new LanguageRequest(languageRepository.findByLanguageId(3), userRepository.findByUserId(1));
	}

}
