package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.User;

public class DictionaryAddRequest {

	private int labelValueId;

	private int languageId;

	private String translatedValue;

	private String labelValueType;

	private User requesUser;

	public DictionaryAddRequest(int labelValueId, int languageId, String translatedValue, String labelValueType,
			User requesUser) {
		super();
		this.labelValueId = labelValueId;
		this.languageId = languageId;
		this.translatedValue = translatedValue;
		this.labelValueType = labelValueType;
		this.requesUser = requesUser;
	}

	public DictionaryAddRequest() {
	}

	public int getLabelValueId() {
		return labelValueId;
	}

	public void setLabelValueId(int labelValueId) {
		this.labelValueId = labelValueId;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getTranslatedValue() {
		return translatedValue;
	}

	public void setTranslatedValue(String translatedValue) {
		this.translatedValue = translatedValue;
	}

	public String getLabelValueType() {
		return labelValueType;
	}

	public void setLabelValueType(String labelValueType) {
		this.labelValueType = labelValueType;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

}
