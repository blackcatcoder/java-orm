package com.hibernate.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeveloperProjectId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "developer_id")
	private Long developerId;

	@Column(name = "project_id")
	private Long projectId;

	public Long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(developerId, projectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeveloperProjectId other = (DeveloperProjectId) obj;
		return Objects.equals(developerId, other.developerId) && Objects.equals(projectId, other.projectId);
	}

}
