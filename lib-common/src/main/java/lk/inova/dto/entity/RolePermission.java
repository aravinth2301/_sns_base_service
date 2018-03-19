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
@Table(name="TBL_ROLE_PERMISSION")
@JsonInclude(Include.NON_NULL)
public class RolePermission extends _AuditColumn{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 210621503985166904L;


	@Id
	@SequenceGenerator(name = "TBL_ROLE_PERMISSION_ID_GENERATOR", sequenceName = "TBL_ROLE_PERMISSION_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TBL_ROLE_PERMISSION_ID_GENERATOR")
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "permission_id")
	private Permission permission;
	
	private Boolean metaData;
	
	public RolePermission() {
	}
	
	
	
	public RolePermission(Role role, Permission permission) {
		super();
		this.role = role;
		this.permission = permission;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolePermission other = (RolePermission) obj;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
	
	
	
}