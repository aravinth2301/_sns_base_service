package lk.inova.dto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name="TBL_ZONE")
@JsonInclude(Include.NON_NULL)
public class Zone extends _CommonColumn{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7656870985620739910L;
	@Id
	@SequenceGenerator(name = "TBL_ZONE_ID_GENERATOR", sequenceName = "TBL_ZONE_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TBL_ZONE_ID_GENERATOR")
	private Long id;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public Integer getChildCurrentHash() {
		return hashCode();
	}
}
