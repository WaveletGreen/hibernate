package util;

/**
 * UserQueryCondition类，用于封装用户信息的查询条件
 * 
 * @author Administrator
 * 
 */
public class UserQueryCondition {
	private String userName;
	private String start;
	private String end;
	private int roleId;
	private int status=-1;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
