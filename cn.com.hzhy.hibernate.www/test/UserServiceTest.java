package test;

import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginContext;

import entity.Employee;
import service.UserService;
import service.impl.UserServiceImpl;
import util.UserQueryCondition;

public class UserServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee employee = new Employee();
		employee.setSex(1);
		employee.setBirthday(new Date());
		employee.setEmpName("����");
		employee.setPassword("123456");
		employee.setRoleId(1);
		employee.setStatus(1);
		// System.out.println("����ɹ�������IDΪ:" + addUser(employee));
		// deleteUser(employee);
		// System.out.println(getByID(21).getEmpName());
		Employee employee2 = Login(5, "1223456");
		if (employee2 == null) {
			System.out.println("用户或密码错误");
		} else
			System.out.println(employee2.getEmpName());
		UserQueryCondition condition=new UserQueryCondition();
		condition.setUserName("张");
		List<Employee> list=getAllUsersList(null);
		for (Employee employee3 : list) {
			System.out.println(employee3.getEmpName());
		}
		System.out.println("-----------------------");
		List<Employee> list2=getPageUserList(condition);
		for (Employee employee3 : list2) {
			System.out.println(employee3.getEmpName()+"---"+employee3.getEmpId());
		}
	}


	private static List<Employee> getPageUserList(UserQueryCondition condition) {
		UserService service=new UserServiceImpl();
		return service.getByPage(5,2,condition);
	}


	private static List<Employee> getAllUsersList(UserQueryCondition condition) {
		UserService service=new UserServiceImpl();
		return service.getByPage(condition);
	}


	private static Employee Login(int ID, String password) {
		UserService service = new UserServiceImpl();
		return service.login(ID, password);
	}

	private static Employee getByID(int i) {
		UserService service = new UserServiceImpl();
		return service.getById(i);
	}

	private static void deleteUser(Employee employee) {
		UserService service = new UserServiceImpl();
		int result = service.deletUser(employee);
		if (result > 0) {
			System.out.println("ɾ��IDΪ" + employee.getEmpId() + employee.getEmpName() + "�ɹ�");
			return;
		}
		System.out.println("ɾ��IDΪ" + employee.getEmpId() + employee.getEmpName() + "ʧ��");
	}

	private static int addUser(Employee employee) {
		UserService service = new UserServiceImpl();
		return service.addUser(employee);
	}

}
