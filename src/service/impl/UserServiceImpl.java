package service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import base.Base;
import entity.Employee;
import service.UserService;
import util.StringUtil;
import util.UserQueryCondition;

public class UserServiceImpl implements UserService {

	@Override
	public int addUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 0;
		try {

			session = Base.getOpenSession();
			tx = session.beginTransaction();

			result = (Integer) session.save(em);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int deletUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		try {

			session = Base.getOpenSession();
			tx = session.beginTransaction();
			em.setStatus(-1);
			session.update(em);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int updateUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		try {

			session = Base.getOpenSession();
			tx = session.beginTransaction();
			session.update(em);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			Base.closeSession();
		}
		return result;
	}

	@Override
	public Employee getById(int empID) {
		Session session = null;
		Employee result = null;
		try {

			session = Base.getOpenSession();
			session.beginTransaction();
			result = (Employee) session.get(entity.Employee.class, empID);
		} catch (HibernateException e) {
			System.out.println("û��IDΪ" + empID + "��Ա��");
			e.printStackTrace();
		} finally {
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int removeUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		try {

			session = Base.getOpenSession();
			tx = session.beginTransaction();

			session.delete(em);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			Base.closeSession();
		}
		return result;
	}

	public List<Employee> getByPage(int pageSize, int pageIndex, UserQueryCondition condition) {
		List<Employee> lists = null;
		StringBuilder hql = new StringBuilder().append("from Employee where 1=1 ");
		Session session = Base.autoSession();
		if (condition == null) {
			System.out.println("--------------测试：condition为null");
			try {
				Query query = session.createQuery(hql.toString());
				lists = query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				Base.closeSession();
			}
		} else {
			if (!StringUtil.isNullOrEmpty(condition.getUserName())) {
				hql.append("and empName like :userName ");
			}
			if (!StringUtil.isNullOrEmpty(condition.getStart())) {
				hql.append("and birthday>=:Start ");
			}

			if (!StringUtil.isNullOrEmpty(condition.getEnd())) {
				hql.append("and birthday<:End ");
			}
			if (condition.getRoleId() > 0) {
				hql.append("and roleId=:roleId ");
			}
			if (condition.getStatus() != -1) {
				hql.append("and status=:status");
			}

			try {
				Query query = session.createQuery(hql.toString());
				if (!StringUtil.isNullOrEmpty(condition.getUserName())) {
					query.setParameter("userName", "%" + condition.getUserName() + "%");

				}
				if (!StringUtil.isNullOrEmpty(condition.getStart())) {
					query.setParameter("Start", condition.getStart());
				}

				if (!StringUtil.isNullOrEmpty(condition.getEnd())) {
					query.setParameter("End", condition.getEnd());
				}
				if (condition.getRoleId() > 0) {
					query.setParameter("roleId", condition.getRoleId());
				}
				if (condition.getStatus() != -1) {
					query.setParameter("status", condition.getStatus());
				}
				if (pageSize != -1 && pageIndex != -1) {
					query.setFirstResult(pageSize * (pageIndex - 1));
					query.setMaxResults(pageSize);
				}
				lists = query.list();
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				Base.closeSession();
			}
		}
		return lists;
	}

	@Override
	public List<Employee> getByPage(UserQueryCondition condition) {
		return getByPage(-1, -1, condition);
	}

	@Override
	public Employee login(int ID, String password) {
		Session session = Base.getOpenSession();
		session.beginTransaction();
		List<Employee> lists = null;
		String hql = "from Employee where empId=:ID and password=:password";
		Query query = session.createQuery(hql);
		query.setParameter("ID", ID);
		query.setParameter("password", password);
		lists = query.list();
		if (lists.size() == 0) {
			return null;
		}
		return lists.get(0);
	}
}
