package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Employee;
import service.UserService;
import service.impl.UserServiceImpl;

public class DoRegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String empName = request.getParameter("userName");
		String password = request.getParameter("password");
		int sex = Integer.parseInt(request.getParameter("sex"));
		Employee employee = new Employee();
		UserService service = new UserServiceImpl();
		employee.setEmpName(empName);
		employee.setPassword(password);
		employee.setSex(sex);
		employee.setBirthday(new Date());
		employee.setStatus(1);
		employee.setRoleId(1);
		int result = service.addUser(employee);
		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("loginedUser", employee);
			request.setAttribute("registSucz", result);
			request.getRequestDispatcher("/jsp/registerSuccess.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "注册失败，用户已存在，请重试");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}
