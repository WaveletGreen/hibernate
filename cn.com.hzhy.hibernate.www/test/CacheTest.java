package test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.ch04.City;

import base.Base;

public class CacheTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sessionCache();
		sessionFactoryCache();
	}

	/**
	 * 二级缓存
	 */
	private static void sessionFactoryCache() {
		String hql = "";
		Session session = Base.getOpenSession();
		Transaction tx = session.beginTransaction();
		City city = (City) session.get(City.class, 1);
		System.out.println("----------" + city.getCityName());
		city.setCityName("义乌市");
		tx.commit();
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
	private static void sessionCache() {

	}

}
