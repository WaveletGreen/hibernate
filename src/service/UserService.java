package service;

import entity.Employee;

public interface UserService {
	/**
	 * ����Ա��
	 * 
	 * @param em
	 *            ��Ҫ���ӵ�Ա��ʵ����
	 * @return ���ӳɹ��򷵻�Ա����ID�����������ɣ������򷵻�0������ʧ��
	 */
	public int addUser(Employee em);

	/**
	 * ɾ��һ��Ա�����߼�ɾ������Ա��status����Ϊ0���ɣ���Ҫ�õ�updateUser����
	 * 
	 * @param em
	 * @return
	 */
	public int deletUser(Employee em);

	/**
	 * ������Employee��������ݿ���ɾ��
	 * 
	 * @param em
	 *            ��Ҫ����ɾ���Ķ���
	 * @return
	 */
	public int removeUser(Employee em);

	/**
	 * ����Ա����Ϣ
	 * 
	 * @param em
	 *            ��Ҫ���µ�Ա��ʵ����
	 * @return
	 */

	public int updateUser(Employee em);

	/**
	 * ����Ա����ID��ѯ���Ա����ʵ������Ϣ
	 * 
	 * @param userID
	 * @return
	 */
	public Employee getById(int empID);

}
