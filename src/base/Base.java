package base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Base {
	/**
	 * ������������Ϣ
	 */
	private static Configuration config = null;
	/**
	 * ��һ��ȫ�ֱ���
	 */
	private static SessionFactory factory = null;

	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	/**
	 * ����Configuration����
	 * 
	 * @return
	 */
	public static Configuration getConfig() {
		if (config == null) {
			synchronized (threadLocal) {
				if (config == null) {
					config = new Configuration().configure();
				}
			}
		}
		return config;
	}

	/**
	 * ��������ļ��������޶������������Ϣ
	 * 
	 * @param qualifiedName
	 * @return
	 */
	public static Configuration getConfig(String qualifiedName) {
		return new Configuration().configure(qualifiedName);
	}

	/**
	 * ����SessionFactory��ȫ�ֱ���
	 * 
	 * @return
	 */
	public static SessionFactory getFactory() {

		if (factory == null) {
			synchronized (threadLocal) {
				if (factory == null) {
					factory = new Configuration().configure().buildSessionFactory();
				}
			}
		}
		return factory;
	}

	/**
	 * ����ض���������Ϣ��ɶ�Ӧ��SessionFactory�������Ĭ�ϵ�hibernate.cfg.xml�����ʹ��getFactory()
	 * ��֤SessionFactoryȫ��ֻ��һ��
	 * 
	 * @param config
	 * @return
	 */
	public static SessionFactory getFactory(Configuration config) {
		return config.buildSessionFactory();
	}

	/**
	 * ��ȡOpenSession
	 * 
	 * @return
	 */
	public static Session getOpenSession() {
		if (factory == null) {
			getFactory();
		}
		Session session = factory.openSession();
		threadLocal.set(session);
		return session;
	}

	/**
	 * �ر�session
	 */
	public static void closeSession() {
		Session session = threadLocal.get();
		if (session != null && session.isOpen()) {
			session.close();
		}
		threadLocal.remove();
		threadLocal.set(null);
	}

	public static Session autoSession() {
		getConfig();
		getFactory();

		return getOpenSession();
	}
}
