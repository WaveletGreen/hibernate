package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import entity.Employee;

import service.UserService;
import service.impl.UserServiceImpl;

import base.Base;

public class DoLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5207321182571467031L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int ID = Integer.parseInt(request.getParameter("userName"));
		String password = request.getParameter("password");
		UserService service = new UserServiceImpl();
		try {
			Employee employee = service.login(ID, password);
			if (employee == null) {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request,
						response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginedUser", employee);
				response.sendRedirect("/news/jsp/loginSuccess.jsp");

			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			Base.closeSession();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
