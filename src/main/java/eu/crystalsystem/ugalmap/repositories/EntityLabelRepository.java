package eu.crystalsystem.ugalmap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.EntityLabel;

public interface EntityLabelRepository extends JpaRepository<EntityLabel, Integer> {


	List<EntityLabel> findByEntityEntityIdNotNullOrBuildingBuildingIdNotNullAndScheduleScheduleIdIsNull();

	EntityLabel findByLabelLabelNameAndValueValueContent(String labelName, String valueContent);
}
