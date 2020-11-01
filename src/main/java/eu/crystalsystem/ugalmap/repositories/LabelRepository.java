package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Label;

public interface LabelRepository extends JpaRepository<Label, Integer> {

	Label findByLabelId(int labelId);
	Label findByLabelName(String labelName);
}
