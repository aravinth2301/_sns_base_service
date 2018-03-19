package lk.inova.util;

import java.io.Serializable;

public class CRUDUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7325947163857504986L;
	private boolean create = false;
	private boolean read = false;
	private boolean update = false;
	private boolean delete = false;

	public CRUDUtil() {
	}

	public CRUDUtil(boolean grantAll) {
		this.create = grantAll;
		this.read = grantAll;
		this.update = grantAll;
		this.delete = grantAll;
	}

	public CRUDUtil(boolean create, boolean read, boolean update, boolean delete) {
		super();
		this.create = create;
		this.read = read;
		this.update = update;
		this.delete = delete;
	}

	public CRUDUtil(int permissions) {
		String bitPattern = Integer.toBinaryString(permissions);
		int bitLen = bitPattern.length();
		if (bitLen < 2)
			bitPattern = "000" + bitPattern;
		else if (bitLen < 3)
			bitPattern = "00" + bitPattern;
		else if (bitLen < 4)
			bitPattern = "0" + bitPattern;

		if (bitPattern.length() >= 4) {
			create = bitPattern.charAt(0) == '1' ? true : false;
			read = bitPattern.charAt(1) == '1' ? true : false;
			update = bitPattern.charAt(2) == '1' ? true : false;
			delete = bitPattern.charAt(3) == '1' ? true : false;
		}
	}

	public int getPermissions(boolean create, boolean read, boolean update,
			boolean delete) {
		String bitPattern = (create ? "1" : "0") + (read ? "1" : "0")
				+ (update ? "1" : "0") + (delete ? "1" : "0");
		return Integer.parseInt(bitPattern, 2);
	}

	public int getPermissions() {
		String bitPattern = (create ? "1" : "0") + (read ? "1" : "0")
				+ (update ? "1" : "0") + (delete ? "1" : "0");
		return Integer.parseInt(bitPattern, 2);
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean hasAccess() {
		return create || delete || read || update;
	}

	public boolean hasUpdateAccess() {
		return (delete || update);
	}
	
	public boolean hasReadAccess() {
		return (delete || read || update);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (create ? 1231 : 1237);
		result = prime * result + (delete ? 1231 : 1237);
		result = prime * result + (read ? 1231 : 1237);
		result = prime * result + (update ? 1231 : 1237);
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
		CRUDUtil other = (CRUDUtil) obj;
		if (create != other.create)
			return false;
		if (delete != other.delete)
			return false;
		if (read != other.read)
			return false;
		if (update != other.update)
			return false;
		return true;
	}

}
