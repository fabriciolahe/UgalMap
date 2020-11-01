package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Language;
import eu.crystalsystem.ugalmap.models.User;

public class LanguageRequest {

	private Language language;

	private User requesUser;

	public LanguageRequest(Language language, User requesUser) {
		this.language = language;
		this.requesUser = requesUser;
	}

	public LanguageRequest() {

	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

}
