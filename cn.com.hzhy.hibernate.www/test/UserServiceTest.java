package test;

import java.util.Date;

import entity.Employee;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee employee = new Employee();
		employee.setSex(1);
		employee.setBirthday(new Date());
		employee.setEmpName("张三");
		employee.setPassword("123456");
		employee.setRoleId(1);
		employee.setStatus(1);
		System.out.println("插入成功，插入ID为:" + addUser(employee));
		deleteUser(employee);
		System.out.println(getByID(21).getEmpName());
	}

	private static Employee getByID(int i) {
		UserService service = new UserServiceImpl();
		return service.getById(i);
	}

	private static void deleteUser(Employee employee) {
		UserService service = new UserServiceImpl();
		int result = service.deletUser(employee);
		if (result > 0) {
			System.out.println("删除ID为" + employee.getEmpId() + employee.getEmpName() + "成功");
			return;
		}
		System.out.println("删除ID为" + employee.getEmpId() + employee.getEmpName() + "失败");
	}

	private static int addUser(Employee employee) {
		UserService service = new UserServiceImpl();
		return service.addUser(employee);
	}

}
