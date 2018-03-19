package lk.inova.util;

public enum ProcessId {

	CERT_APPROVAL("cert-approval");
	
	private String processId;

	private ProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	
}
