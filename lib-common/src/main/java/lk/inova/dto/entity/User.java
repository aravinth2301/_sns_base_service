package lk.inova.dto.entity;

import javax.persistence.Column;
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
@Table(name = "TBL_USER")
@JsonInclude(Include.NON_NULL)
public class User extends _CommonColumn{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2260414726194462343L;

	@Id
	@SequenceGenerator(name = "TBL_USER_ID_GENERATOR", sequenceName = "TBL_USER_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TBL_USER_ID_GENERATOR")
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	private String designation;
	private String numHome;
	private String numMobile;
	private String address;
	private String nic;
	
	

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public Integer getChildCurrentHash() {
		return hashCode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((nic == null) ? 0 : nic.hashCode());
		result = prime * result + ((numHome == null) ? 0 : numHome.hashCode());
		result = prime * result + ((numMobile == null) ? 0 : numMobile.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (nic == null) {
			if (other.nic != null)
				return false;
		} else if (!nic.equals(other.nic))
			return false;
		if (numHome == null) {
			if (other.numHome != null)
				return false;
		} else if (!numHome.equals(other.numHome))
			return false;
		if (numMobile == null) {
			if (other.numMobile != null)
				return false;
		} else if (!numMobile.equals(other.numMobile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getNumHome() {
		return numHome;
	}

	public void setNumHome(String numHome) {
		this.numHome = numHome;
	}

	public String getNumMobile() {
		return numMobile;
	}

	public void setNumMobile(String numMobile) {
		this.numMobile = numMobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}
	
	
	
}