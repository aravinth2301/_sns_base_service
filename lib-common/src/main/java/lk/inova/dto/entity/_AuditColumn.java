package lk.inova.dto.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lk.inova.util.JsonUtil;

@MappedSuperclass
public abstract class _AuditColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6039715863137437011L;
	private Integer currentHash;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "modified_by")
	private User modifiedBy;

	private Date modifiedDate;

	private String modifiedReason;
	
	public Integer getCurrentHash() {
		return currentHash;
	}

	public void setCurrentHash(Integer currentHash) {
		this.currentHash = currentHash;
	}

	
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedReason() {
		return modifiedReason;
	}

	public void setModifiedReason(String modifiedReason) {
		this.modifiedReason = modifiedReason;
	}

	public abstract Integer getChildCurrentHash();

	
	@Override
	public String toString() {
		return JsonUtil.parseToString(this);
	}
}
