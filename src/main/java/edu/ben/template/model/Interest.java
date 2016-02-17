package edu.ben.template.model;

public class Interest {

	private Long id;
	private String Name;

	public Interest() {
		super();
	}
	
	public Interest(String name) {
		super();
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
