package service;

import java.util.List;

import entity.Employee;
import util.UserQueryCondition;

public interface UserService {
	/**
	 * 添加一名员工
	 * 
	 * @param em
	 *            需要添加的员工实体类
	 * @return 如果添加成功则返回员工ID，否则返回0
	 */
	public int addUser(Employee em);

	/**
	 * 逻辑删除员工，即更新员工状态设为0
	 * 
	 * @param em
	 * @return 逻辑删除成功则返回1，删除失败则返回-1
	 */
	public int deletUser(Employee em);

	/**
	 * 移除员工，这次操作是将员工从物理存储介质中真正删除员工信息
	 * 
	 * @param em
	 *            需要删除的员工
	 * @return
	 */
	public int removeUser(Employee em);

	/**
	 * 更新员工信息
	 * 
	 * @param em
	 *            需要更新信息的员工
	 * @return
	 */

	public int updateUser(Employee em);

	/**
	 * 根据ID获取员工
	 * 
	 * @param userID
	 * @return
	 */
	public Employee getById(int empID);

	/**
	 * 分页查询指定记录范围内的员工记录
	 * 
	 * @param pageSize
	 *            每一页最大记录数
	 * @param pageIndex
	 *            开始显示的记录数
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public List<Employee> getByPage(int pageSize, int pageIndex, UserQueryCondition condition);

	/**
	 * 根据查询条件获取所有符合条件的员工记录
	 * 
	 * @param condition
	 * @return
	 */
	public List<Employee> getByPage(UserQueryCondition condition);

	public Employee login(int ID, String password);
	
	
}
