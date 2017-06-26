package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import base.Base;

import entity.ch04.City;
import entity.ch04.Province;

@SuppressWarnings({ "unused", "unchecked" })
public class CityCasadeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// p1();
		p2();
		// delet();
	}

	private static void p2() {
		// TdODO Auto-generated method stub
		Session session = Base.autoSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("from City c where c.province=:province");
		// 这里setParament()并不是通用的，要根据类型使用set方法

		query.setInteger("province", 1);
		List<City> cities = query.list();
		for (City city : cities) {
			System.out.println(city.getProvince().getProName());
		}
	}

	private static void p1() {
		Province province = new Province();
		City city = new City();
		province.setProID(1);
		province.setProName("浙江省");
		city.setCityID(1);
		city.setCityName("杭州市");
		city.setProvince(province);
		Session session = Base.autoSession();
		Transaction tx = session.beginTransaction();
		session.save(province);
		session.save(city);
		tx.commit();
	}

	private static void delet() {
		Session session = Base.autoSession();
		Transaction tx = session.beginTransaction();
		Province province = (Province) session.get(Province.class, 1);
		City city = (City) session.get(City.class, 1);
		// session.delete(city);
		session.delete(province);
		tx.commit();

	}
}
