package lk.inova.dto;

import java.io.Serializable;

public class WFRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4094368312373582211L;

	
	private WFUserDTO escalateTo;

	private WFUserDTO modifiedBy;
	
	private WFTaskDTO task;

	public WFUserDTO getEscalateTo() {
		return escalateTo;
	}


	public void setEscalateTo(WFUserDTO escalateTo) {
		this.escalateTo = escalateTo;
	}


	public WFUserDTO getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(WFUserDTO modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public WFTaskDTO getTask() {
		return task;
	}


	public void setTask(WFTaskDTO task) {
		this.task = task;
	}
	
	
	
}
