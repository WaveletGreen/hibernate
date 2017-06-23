package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Employee;
import service.UserService;
import service.impl.UserServiceImpl;
import util.Page;
import util.StringUtil;
import util.UserQueryCondition;

public class GetUserListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7800102204692281067L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserService service = new UserServiceImpl();

		List<Employee> list = service.getByPage(null);
		Page<Employee> page = new Page<Employee>();
		page.setDatas(list);

		String userName = request.getParameter("userName");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		// 获取角色
		String strRoleId = request.getParameter("roleId");
		int roleId = 0;
		if (!StringUtil.isNullOrEmpty(strRoleId)) {
			roleId = Integer.parseInt(strRoleId);
		}
		// 获取状态
		String strStatus = request.getParameter("status");
		int status = -1;
		if (!StringUtil.isNullOrEmpty(strStatus)) {
			status = Integer.parseInt(strStatus);
		}

		UserQueryCondition condition = new UserQueryCondition();
		if (!StringUtil.isNullOrEmpty(userName)) {
			condition.setUserName(userName);
		}
		condition.setStart(start);
		condition.setEnd(end);
		condition.setRoleId(roleId);
		condition.setStatus(status);

		page.setPageSize(3);// 每页最大记录数
		page.setCurrentPage(1);// 当前页

		String strPageIndex = request.getParameter("pageIndex");
		if (!StringUtil.isNullOrEmpty(strPageIndex)) {
			page.setCurrentPage(Integer.parseInt(strPageIndex));
		}

		// 创建业务对象，查询总页数和用户信息
		UserService userService = new UserServiceImpl();
		page.setTotalRecord(userService.getByPage(condition).size());
		List<Employee> userList = userService.getByPage(page.getPageSize(), page.getCurrentPage(), condition);

		// 计算上一页
		page.setPrePageIndex(page.getCurrentPage() - 1);
		// 计算下一页
		page.setNextPageIndex(page.getCurrentPage() + 1);

		// 保存数据并实现页面跳转
		request.setAttribute("pageIndex", page.getCurrentPage());
		request.setAttribute("prePageIndex", page.getPrePageIndex());
		request.setAttribute("nextPageIndex", page.getNextPageIndex());
		request.setAttribute("totalPages", page.getTotalPage());
		if (!StringUtil.isNullOrEmpty(userName)) {
			condition.setUserName(userName.replace("%", ""));
		}
		request.setAttribute("condition", condition);
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/jsp/admin/userList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
