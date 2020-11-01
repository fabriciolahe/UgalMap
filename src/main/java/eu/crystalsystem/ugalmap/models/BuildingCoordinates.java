package eu.crystalsystem.ugalmap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@javax.persistence.Entity
@Table(name = "building_coordinates")
public class BuildingCoordinates {
	@XmlElement(required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "building_coordinates_id")
	protected int buildingCoordinatesId;

	@XmlElement(required = true)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "building_building_id")
	private Building building;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH })
	@JoinColumn(name = "coordinates_coordinates_id")
	@XmlElement(required = true)
	private Coordinates coordinates;

	@XmlElement(required = true)
	@Column(name = "building_coordinates_position")
	private int position;
	
	public BuildingCoordinates() {
	}

	public BuildingCoordinates(Building building, Coordinates coordinates, int position) {
		super();
		this.building = building;
		this.coordinates = coordinates;
		this.position = position;
	}

	public int getBuildingCoordinatesId() {
		return buildingCoordinatesId;
	}

	public void setBuildingCoordinatesId(int buildingCoordinatesId) {
		this.buildingCoordinatesId = buildingCoordinatesId;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
