package lk.inova.dto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="TBL_ROLE")
@JsonInclude(Include.NON_NULL)
public class Role extends _CommonColumn{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6418625808590888284L;

	@Id
	@SequenceGenerator(name = "TBL_ROLE_ID_GENERATOR", sequenceName = "TBL_ROLE_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TBL_ROLE_ID_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "parent_role_id")
	private Role parentRole;
	
	private Boolean metaData;
	
	public Role() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Role getParentRole() {
		return parentRole;
	}

	public void setParentRole(Role parentRole) {
		this.parentRole = parentRole;
	}

	public Boolean getMetaData() {
		return metaData;
	}

	public void setMetaData(Boolean metaData) {
		this.metaData = metaData;
	}

	@Override
	public Integer getChildCurrentHash() {
		return hashCode();
	}
	
	
	
}