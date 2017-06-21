package base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Base {
	/**
	 * 解析基本配置信息
	 */
	private static Configuration config = null;
	/**
	 * 是一个全局变量
	 */
	private static SessionFactory factory = null;

	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	/**
	 * 创建Configuration单例
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
	 * 根据配置文件的完整限定名称生辰配置信息
	 * 
	 * @param qualifiedName
	 * @return
	 */
	public static Configuration getConfig(String qualifiedName) {
		return new Configuration().configure(qualifiedName);
	}

	/**
	 * 创建SessionFactory，全局变量
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
	 * 根据特定的配置信息生成对应的SessionFactory，如果是默认的hibernate.cfg.xml，则可使用getFactory()
	 * 保证SessionFactory全局只有一个
	 * 
	 * @param config
	 * @return
	 */
	public static SessionFactory getFactory(Configuration config) {
		return config.buildSessionFactory();
	}

	/**
	 * 获取OpenSession
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
	 * 关闭session
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
