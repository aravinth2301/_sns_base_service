package lk.inova.dto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="TBL_PERMISSION")
@JsonInclude(Include.NON_NULL)
public class Permission extends _CommonColumn{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7197235643615356766L;

	@Id
	@SequenceGenerator(name = "TBL_PERMISSION_ID_GENERATOR", sequenceName = "TBL_PERMISSION_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TBL_PERMISSION_ID_GENERATOR")
	private Long id;
	

	@Column(unique = true)
	private String scope;
	
	public Permission() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public Integer getChildCurrentHash() {
		return hashCode();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}
	
}