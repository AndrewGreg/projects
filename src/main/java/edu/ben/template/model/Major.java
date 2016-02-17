package edu.ben.template.model;

public class Major {

	private Long id;
	private String name;
	private boolean concentration;
	private Major parent;
	
	public Major() {
		super();
	}
	public Major(String name, boolean concentration) {
		super();
		this.name = name;
		this.concentration = concentration;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isConcentration() {
		return concentration;
	}
	public void setConcentration(boolean concentration) {
		this.concentration = concentration;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Major getParent() {
		return parent;
	}
	public void setParent(Major parent) {
		this.parent = parent;
	}
	
}
