package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	
	
	Schedule findByScheduleId(int scheduleId);

	
}
