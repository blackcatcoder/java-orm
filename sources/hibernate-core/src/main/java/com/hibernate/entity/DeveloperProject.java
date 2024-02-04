package com.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "developer_project")
public class DeveloperProject implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DeveloperProjectId developerProjectId = new DeveloperProjectId();

	@ManyToOne
	@MapsId("developerId")
	private Developer developer;

	@ManyToOne
	@MapsId("projectId")
	private Project project;

	@Column(name = "future_field")
	private String futureField;

	public DeveloperProjectId getDeveloperProjectId() {
		return developerProjectId;
	}

	public void setDeveloperProjectId(DeveloperProjectId developerProjectId) {
		this.developerProjectId = developerProjectId;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getFutureField() {
		return futureField;
	}

	public void setFutureField(String futureField) {
		this.futureField = futureField;
	}

}
