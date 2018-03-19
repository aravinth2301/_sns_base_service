package lk.inova.dto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TESTTBL1")
public class TestTbl1 {

	@Id
	@SequenceGenerator(name = "TESTTBL1_ID_GENERATOR", sequenceName = "TESTTBL1_SEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TESTTBL1_ID_GENERATOR")
	private Long tblId1;
	
	private String content1;

	public Long getTblId1() {
		return tblId1;
	}

	public void setTblId1(Long tblId1) {
		this.tblId1 = tblId1;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}
	
	
	
	
}

