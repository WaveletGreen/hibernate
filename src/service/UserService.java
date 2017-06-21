package service;

import entity.Employee;

public interface UserService {
	/**
	 * 增加员工
	 * 
	 * @param em
	 *            需要增加的员工实体类
	 * @return 增加成功则返回员工的ID（由序列生成），否则返回0，增加失败
	 */
	public int addUser(Employee em);

	/**
	 * 删除一个员工，逻辑删除，将员工status更新为0即可，需要用到updateUser方法
	 * 
	 * @param em
	 * @return
	 */
	public int deletUser(Employee em);

	/**
	 * 真正将Employee对象从数据库中删除
	 * 
	 * @param em
	 *            需要物理删除的对象
	 * @return
	 */
	public int removeUser(Employee em);

	/**
	 * 更新员工信息
	 * 
	 * @param em
	 *            需要更新的员工实体类
	 * @return
	 */

	public int updateUser(Employee em);

	/**
	 * 根据员工的ID查询获得员工的实体类信息
	 * 
	 * @param userID
	 * @return
	 */
	public Employee getById(int empID);

}
