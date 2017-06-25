package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import base.Base;
import entity.ch04.City;
import entity.ch04.Province;

public class GetCities extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1596439685268470335L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = Base.autoSession();
		session.beginTransaction();
		List<City> cities = null;
		String delet = request.getParameter("delet");
		Query query = session.createQuery("from City where province=1");
		if (delet != null) {
			Transaction tx=session.beginTransaction();
			Province province = (Province) session.get(Province.class, 1);
			session.delete(province);
			tx.commit();
			response.sendRedirect("/news/jsp/ch04/GetCity.jsp");
			return;
		}
		cities = query.list();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("cityList", cities);
		response.sendRedirect("/news/jsp/ch04/ProvinceAndCity.jsp");
	}

}
