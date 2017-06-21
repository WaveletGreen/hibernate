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
		// 1.���������ļ�
		try {

			// 2.����SessionFactory

			// 3.����session
			session = Base.getOpenSession();
			// 4.�������ݿ�����
			tx = session.beginTransaction();

			// 5.ִ������
			result = (Integer) session.save(em);
			// 6.�ύ����
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			// 7.�ͷ���Դ
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int deletUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		// 1.���������ļ�
		try {

			// 2.����SessionFactory

			// 3.����session
			session = Base.getOpenSession();
			// 4.�������ݿ�����
			tx = session.beginTransaction();
			// ����״̬�����û�״̬��Ϊ-1��ʾ�߼�ɾ��
			em.setStatus(-1);
			// 5.ִ������
			session.update(em);
			// 6.�ύ����
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			// 7.�ͷ���Դ
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int updateUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		// 1.���������ļ�
		try {

			// 2.����SessionFactory

			// 3.����session
			session = Base.getOpenSession();
			// 4.�������ݿ�����
			tx = session.beginTransaction();
			// ����״̬�����û�״̬��Ϊ-1��ʾ�߼�ɾ��
			// 5.ִ������
			session.update(em);
			// 6.�ύ����
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			// 7.�ͷ���Դ
			Base.closeSession();
		}
		return result;
	}

	@Override
	public Employee getById(int empID) {
		Session session = null;
		Employee result = null;
		// 1.���������ļ�
		try {

			// 2.����SessionFactory

			// 3.����session
			session = Base.getOpenSession();
			// 4.�������ݿ�����
			session.beginTransaction();
			// ����״̬�����û�״̬��Ϊ-1��ʾ�߼�ɾ��
			// 5.ִ������
			result = (Employee) session.get(entity.Employee.class, empID);
			// 6.�ύ����
		} catch (HibernateException e) {
			System.out.println("û��IDΪ" + empID + "��Ա��");
			e.printStackTrace();
		} finally {
			// 7.�ͷ���Դ
			Base.closeSession();
		}
		return result;
	}

	@Override
	public int removeUser(Employee em) {
		Session session = null;
		Transaction tx = null;
		int result = 1;
		// 1.���������ļ�
		try {

			// 2.����SessionFactory

			// 3.����session
			session = Base.getOpenSession();
			// 4.�������ݿ�����
			tx = session.beginTransaction();

			// 5.ִ������
			session.delete(em);
			// 6.�ύ����
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			result = -1;
			return result;
		} finally {
			// 7.�ͷ���Դ
			Base.closeSession();
		}
		return result;
	}

}
