package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Dictionary;
import eu.crystalsystem.ugalmap.models.User;

public class DictionaryRequest {

	private Dictionary dictionary;

	private User requesUser;

	public DictionaryRequest() {

	}

	public DictionaryRequest(Dictionary dictionary, User requesUser) {
		super();
		this.dictionary = dictionary;
		this.requesUser = requesUser;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}


}
