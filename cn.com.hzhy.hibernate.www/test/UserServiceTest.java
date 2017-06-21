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
		employee.setEmpName("����");
		employee.setPassword("123456");
		employee.setRoleId(1);
		employee.setStatus(1);
		System.out.println("����ɹ�������IDΪ:" + addUser(employee));
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
