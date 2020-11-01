package eu.crystalsystem.ugalmap.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.crystalsystem.ugalmap.models.EntityLabel;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.models.Schedule;
import eu.crystalsystem.ugalmap.repositories.EntityLabelRepository;
import eu.crystalsystem.ugalmap.repositories.EntityRepository;
import eu.crystalsystem.ugalmap.repositories.ScheduleRepository;
import eu.crystalsystem.ugalmap.rest.request.ScheduleRequest;
import eu.crystalsystem.ugalmap.rest.response.ScheduleResponse;
import eu.crystalsystem.ugalmap.rest.response.ValueResponse;

@RestController
@RequestMapping("/schedule")
public class ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired
	private EntityRepository entityRepo;
	
	@Autowired
	private EntityLabelRepository entityLabelRepo;
	
	@GetMapping("/all")
	public ScheduleResponse getAll(){
		List<Schedule> schedule=scheduleRepo.findAll();
		return new ScheduleResponse(new GenericHeader(true,"Schedules founded"),schedule);
		
	}


	
	@PostMapping("/post")
	public ScheduleResponse postSchedule(@RequestBody ScheduleRequest scheduleRequest) {
		ScheduleResponse scheduleResponse;
		List<Schedule> listSchedule = new ArrayList<>();
		try {
		if(scheduleRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				Schedule schedule= new Schedule(scheduleRequest.getSchedule().getScheduleTimeStart(),
						scheduleRequest.getSchedule().getScheduleTimeEnd(),
						entityRepo.findByEntityId(scheduleRequest.getEntity().getEntityId()));
						listSchedule.add(scheduleRepo.save(schedule));
				scheduleResponse = new ScheduleResponse(new GenericHeader(true,"Schedule inserted"),listSchedule);
					
				
			} else {
				scheduleResponse = new ScheduleResponse(new GenericHeader(false,"Acces Denied"));
			 }
			} catch (Exception e) {
				scheduleResponse = new ScheduleResponse(new GenericHeader(false,"Exception: " + e.getMessage()));
				e.printStackTrace();
			}
			return scheduleResponse;
	}
	
	
	@PutMapping("/update/{id}")
	public ScheduleResponse updateSchedule(@RequestBody ScheduleRequest scheduleRequest,@PathVariable int id){
		Optional<Schedule> scheduleOptional=scheduleRepo.findById(id);
		ScheduleResponse scheduleResponse;
		List<Schedule> listSchedule= new ArrayList<>();
		try {
			if(scheduleRequest.getRequesUser().getRole().getRoleName().equals(Role.CONTENT_EDITOR)) {
				if(scheduleOptional.isPresent()) {
					scheduleOptional.get().setScheduleTimeStart(scheduleRequest.getSchedule().getScheduleTimeStart());
					scheduleOptional.get().setScheduleTimeEnd(scheduleRequest.getSchedule().getScheduleTimeEnd());
					scheduleOptional.get().setEntity(entityRepo.findByEntityId(scheduleRequest.getEntity().getEntityId()));
					listSchedule.add(scheduleRepo.save(scheduleOptional.get()));
					scheduleResponse= new ScheduleResponse(new GenericHeader(true,"Schedule updated"),listSchedule);
				} else {
					scheduleResponse = new ScheduleResponse(new GenericHeader(false,"Schedule not founded"));
				}
			} else {
				scheduleResponse= new ScheduleResponse(new GenericHeader(true,"Acces Denied"));
			}
		} catch(Exception e) {
			scheduleResponse = new ScheduleResponse(new GenericHeader(false , "Exception: " + e.getMessage()));
		}
	
		return scheduleResponse;
	}
	
	
	@GetMapping("/{id}")
	public ScheduleResponse getById(@PathVariable int id) {
		ScheduleResponse scheduleResponse;
		Optional<Schedule> schedule = scheduleRepo.findById(id);
		if(schedule.isPresent()) {
			List<Schedule> listSchedule= new ArrayList<>();
			listSchedule.add(schedule.get());
			scheduleResponse = new ScheduleResponse(new GenericHeader(true,"Schedule founded"),listSchedule);
		} else {
			scheduleResponse = new ScheduleResponse(new GenericHeader(false,"Schedule not founded"));
		}
		return scheduleResponse;
	}

	@DeleteMapping("/delete/{id}")
	public ScheduleResponse deleteSchedule(@PathVariable int id) {
		ScheduleResponse scheduleResponse;
		Optional<Schedule> scheduleOptional = scheduleRepo.findById(id);
		if(scheduleOptional.isPresent()) {
			List<EntityLabel> listEntityLab = entityLabelRepo.findAll();
			Schedule schedule = scheduleRepo.findByScheduleId(id);
			boolean found = false;
			for(EntityLabel entityLab : listEntityLab) {
				if(entityLab.getSchedule() == schedule) {
					found =true;
				}
			} if(found == false) {
				scheduleRepo.deleteById(id);
				scheduleResponse= new ScheduleResponse(new GenericHeader(true, "Schedule "+scheduleOptional.get().getScheduleId()+ " removed"));
			} else {
				scheduleResponse = new ScheduleResponse(new GenericHeader(true, "Schedule "  +scheduleOptional.get().getScheduleId() + " is in use and can't be deleted"));
			}
			
		} else {
			scheduleResponse=new ScheduleResponse(new GenericHeader(false, "Schedule doesnt exists"));
		}
		
		return scheduleResponse;
		
	}

}
