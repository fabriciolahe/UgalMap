package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.Schedule;

public class ScheduleResponse extends GenericResponse {
	
	private List<Schedule> schedule;

	public ScheduleResponse() {
		
	}

	public ScheduleResponse(GenericHeader genericHeader,List<Schedule> schedule) {
		super(genericHeader);
		this.schedule = schedule;
	}
	
	
	public ScheduleResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

	
}
