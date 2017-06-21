package service.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import base.Base;

import entity.Employee;
import service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public int addUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 0;
		// 1.解析配置文件
		try {

			// 2.创建SessionFactory

			// 3.创建session
			session = Base.getOpenSession();
			// 4.建立数据库连接
			tx = session.beginTransaction();

			// 5.执行事务
			result = (Integer) session.save(em);
			// 6.提交事务
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			// 7.释放资源
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int deletUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		// 1.解析配置文件
		try {

			// 2.创建SessionFactory

			// 3.创建session
			session = Base.getOpenSession();
			// 4.建立数据库连接
			tx = session.beginTransaction();
			// 更新状态，将用户状态设为-1表示逻辑删除
			em.setStatus(-1);
			// 5.执行事务
			session.update(em);
			// 6.提交事务
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			// 7.释放资源
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int updateUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		// 1.解析配置文件
		try {

			// 2.创建SessionFactory

			// 3.创建session
			session = Base.getOpenSession();
			// 4.建立数据库连接
			tx = session.beginTransaction();
			// 更新状态，将用户状态设为-1表示逻辑删除
			// 5.执行事务
			session.update(em);
			// 6.提交事务
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			// 7.释放资源
			Base.closeSession();
		}
		return result;
	}

	@Override
	public Employee getById(int empID) {
		Session session = null;
		Employee result = null;
		// 1.解析配置文件
		try {

			// 2.创建SessionFactory

			// 3.创建session
			session = Base.getOpenSession();
			// 4.建立数据库连接
			session.beginTransaction();
			// 更新状态，将用户状态设为-1表示逻辑删除
			// 5.执行事务
			result = (Employee) session.get(entity.Employee.class, empID);
			// 6.提交事务
		} catch (HibernateException e) {
			System.out.println("没有ID为" + empID + "的员工");
			e.printStackTrace();
		} finally {
			// 7.释放资源
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int removeUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		// 1.解析配置文件
		try {

			// 2.创建SessionFactory

			// 3.创建session
			session = Base.getOpenSession();
			// 4.建立数据库连接
			tx = session.beginTransaction();

			// 5.执行事务
			session.delete(em);
			// 6.提交事务
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			// 7.释放资源
			Base.closeSession();
		}
		return result;
	}

}
