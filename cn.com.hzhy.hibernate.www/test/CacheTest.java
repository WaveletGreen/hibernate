package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.ch04.City;

import base.Base;

@SuppressWarnings("unused")
public class CacheTest {

	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	// // sessionCache();
	// // sessionFactoryCache();
	// // queryCache();
	//
	// /*
	// * hibernate不能像JDBC那样子获得查询结果集吗？如果查询的结果是返回多行多列该怎么处理呢？
	// * list返回的是单列多行结果集，uniqueResult返回多列单行结果集，怎么将二者结合起来用呢？ 能否用一个类来承接这个结果集呢？
	// */
	// // simpleTest();
	// usingCommonQuery();
	// usingwithAggregationOperation();
	// }

	/**
	 * 聚合运算获得结果集，其结果是单行多列
	 */
	public static void usingwithAggregationOperation() {
		// 编写查询条件
		String[] condition = { "province=1" };
		Object[] cities = withAggregationOperation(City.class, condition, true, "count(*),sum(cityID)");
		// Object[] cities = withAggregationOperation(City.class, null, true,
		// "cityID,cityName");
		for (int i = 0; i < cities.length; i++) {
			System.out.println(cities[i]);
		}
	}

	/**
	 * 聚合运算获得结果集，其结果是单行多列
	 */
	public static void usingCommonQuery() {
		// 编写查询条件
		String[] condition = { "cityID=1", "province=1" };
		List<Object> cities = commonManyQuery(City.class, condition, false, "cityName");
		for (int i = 0; i < cities.size(); i++) {
			System.out.println(cities.get(i));
		}
	}

	/**
	 * query缓冲查询
	 */
	public static void queryCache() {
		Session session1 = Base.autoSession();
		Query query = session1.createQuery("from City where province=:province");
		query.setInteger("province", 1);
		City city = (City) query.uniqueResult();
		System.out.println("--------" + city.getCityName());
		City city2 = (City) query.uniqueResult();
		System.out.println("--------" + city2.getCityName());

	}

	/**
	 * 二级缓存
	 */
	public static void sessionFactoryCache() {
		String hql = "";
		Session session = Base.getOpenSession();
		Transaction tx = session.beginTransaction();
		City city = (City) session.get(City.class, 1);
		System.out.println("----------" + city.getCityName());
		city.setCityName("义乌市");
		// tx.commit();
		Base.closeSession();
		System.out.println("--------------------");
		System.out.println(city.getCityName());
		Session session2 = Base.getOpenSession();
		City city2 = (City) session2.get(City.class, 1);
		System.out.println("---------------" + city2.getCityName());

	}

	/**
	 * 一级缓存
	 */
	public static void sessionCache() {

	}

	/**
	 * 获得多个结果，无法通过聚合运算获得准确的结果，获得多行单列查询结果
	 * 
	 * @param <T>
	 *            结果的返回类型
	 * @param <E>
	 *            Class的类型
	 * @param className
	 *            entity中的类型，要求是完整限定名称
	 * @param condition
	 *            查询条件
	 * @param objs
	 * @param isAnd
	 *            是否以and的方式拼接查询条件，如果为true，则是将符合查询条件的交集结果返回；如果为false，则将符合查询条件的并集结果返回
	 * @return 查询得到的结果
	 * @throws ClassNotFoundException
	 *             如果className的完整限定名称传入错误的会出现该异常
	 */
	public static <T, E> List<T> commonManyQuery(Class<E> className, String[] condition, boolean isAnd,
			String results) {// 查询记录中部分字段
		/**
		 * 编写建立数据库连接的默认基础部分
		 */
		// 获取配置文件
		// Configuration config = new Configuration().configure();
		// // 获取sessionFactory对象
		// SessionFactory factory = config.buildSessionFactory();
		// // 获取session
		// Session session = factory.openSession();
		// 没有Base类请注释下面一行，解注上面各行
		Session session = Base.autoSession();
		// 建立事务
		Transaction tx = session.beginTransaction();
		/**
		 * 构建基础hql语句
		 */
		StringBuilder hql = new StringBuilder();
		if (results != null) {
			hql.append("select ").append(results + " ");
		}
		// 拼接类名
		hql.append("from " + className.getName() + " ");
		// 拼接查询条件
		if (condition != null) {
			hql.append("where ");
			if (isAnd) {
				for (int i = 0; i < condition.length; i++) {
					if (i == condition.length - 1) {
						hql.append(condition[i]);
					} else {
						hql.append(condition[i] + " and ");
					}
				}
			} else {
				for (int i = 0; i < condition.length; i++) {
					if (i == condition.length - 1) {
						hql.append(condition[i]);
					} else {
						hql.append(condition[i] + " or ");
					}
				}
			}

		}
		System.out.println("-------------测试" + hql.toString());
		Query query = session.createQuery(hql.toString()).setCacheable(true);
		// 获得多行单列结果
		List<T> obj = query.list();
		return obj;
	}

	/**
	 * 根据聚合运算获得结果集，结果集是唯一的，获得单行多列结果集
	 * 
	 * @param className
	 *            实体类名
	 * @param condition
	 *            查询条件
	 * @param isAnd
	 *            是否以and的方式拼接查询条件，如果为true，则是将符合查询条件的交集结果返回；如果为false，则将符合查询条件的并集结果返回
	 * @param results
	 *            需要查询的结果集
	 * @return
	 */
	public static <E, T> Object[] withAggregationOperation(Class<E> className, String[] condition, boolean isAnd,
			String... results) {
		// 获取配置文件
		// Configuration config = new Configuration().configure();
		// // 获取sessionFactory对象
		// SessionFactory factory = config.buildSessionFactory();
		// // 获取session
		// Session session = factory.openSession();
		// 没有Base类请注释下面一行，解注上面各行
		Session session = Base.autoSession();
		// 建立事务
		Transaction tx = session.beginTransaction();
		/**
		 * 构建通用hql语句
		 */
		StringBuilder hql = new StringBuilder();
		if (results.length > 0) {
			hql.append("select ");
			for (int i = 0; i < results.length; i++) {
				hql.append(results[i] + " ");
			}
		}
		// 拼接类名
		hql.append("from " + className.getName() + " ");
		// 动态拼接查询条件
		if (condition != null) {
			hql.append("where ");
			if (isAnd) {
				for (int i = 0; i < condition.length; i++) {
					if (i == condition.length - 1) {
						hql.append(condition[i]);
					} else {
						hql.append(condition[i] + " and ");
					}
				}
			} else {
				for (int i = 0; i < condition.length; i++) {
					if (i == condition.length - 1) {
						hql.append(condition[i]);
					} else {
						hql.append(condition[i] + " or ");
					}
				}
			}

		}
		System.out.println("-------------测试" + hql.toString());
		Query query = session.createQuery(hql.toString()).setCacheable(true);
		// 获得单行多列结果
		Object obj = query.uniqueResult();
		if (obj.getClass().isArray()) {
			Object[] obj2 = (Object[]) obj;
			return obj2;
		} else {
			Object[] objects = { obj };
			return objects;
		}
	}

	/**
	 * 简单测试是否能够获得
	 * 
	 * @return
	 */
	public static List simpleTest() {
		Session session = Base.autoSession();
		String hql = "select cityID,cityName from City";
		Query query = session.createQuery(hql);
		Iterator<Integer> list = query.iterate();
		while (list.hasNext()) {
			Object object = (Object) list.next();
			System.out.println(object);
		}
		return null;
	}
}