package eu.crystalsystem.ugalmap.models;

public abstract class GenericResponse {

	private GenericHeader genericHeader;

	public GenericResponse() {

	}

	public GenericResponse(GenericHeader genericHeader) {

		this.genericHeader = genericHeader;
	}

	public GenericHeader getGenericHeader() {
		return genericHeader;
	}

	public void setGenericHeader(GenericHeader genericHeader) {
		this.genericHeader = genericHeader;
	}

}
