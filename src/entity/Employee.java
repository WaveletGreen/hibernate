package entity;

import java.util.Date;

/**
 * Employee实体类
 */
public class Employee {
	private Integer empId;
	private String empName;
	private String password;
	private Integer sex;
	private Date birthday;
	private Integer roleId;
	private Integer status;

	public Employee() {
		super();
	}

	public Employee(Integer empId, String empName, String password, Integer sex, Date birthday, Integer roleId,
			Integer status) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.roleId = roleId;
		this.status = status;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
