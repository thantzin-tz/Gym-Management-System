package models;

import java.util.ArrayList;
import java.util.List;

public class BodyPart {

	private int id;
	
	private String name;
	
	private List<BodyPart> bodyPart = new ArrayList<>();

	public BodyPart() {
	}

	public BodyPart(int id, String name, List<BodyPart> bodyPart) {
		super();
		this.id = id;
		this.name = name;
		this.bodyPart = bodyPart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BodyPart> getWorkout() {
		return bodyPart;
	}

	public void setWorkout(List<BodyPart> bodyPart) {
		this.bodyPart = bodyPart;
	}
	
	
}
