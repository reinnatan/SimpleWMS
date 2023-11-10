package com.simple.warehouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simple.warehouse.enitty.Item;
import com.simple.warehouse.enitty.Rack;
import com.simple.warehouse.enitty.Warehouse;
import com.simple.warehouse.utils.HibernateUtils;

public class RackDao {
	
	public void addRacks(Rack racks, String idWarehouse) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			Warehouse warehouse = session.find(Warehouse.class, idWarehouse);
			if (Objects.isNull(warehouse.getRack())){
				List<Rack> list = new ArrayList<Rack>();
				list.add(racks);
				warehouse.setRack(list);
			}else {
				 warehouse.getRack().add(racks);
			}
			transaction = session.beginTransaction();
			session.persist(racks);
			session.merge(warehouse);
			transaction.commit();
		}catch (Exception e) {
			if (transaction !=null ) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	

	public List<Object[]> listRack(){
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			return (List<Object[]>) session.createQuery("select r.id, r.name from Rack r", Object[].class).list();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public Rack findRack(String rackId) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			return session.find(Rack.class, rackId);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void putItemToRack(Rack rack, Item item,String count,String itemId) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			int countParse = Integer.parseInt(count);
			//rack.setCount(countParse);
			session.getTransaction().begin();
			session.merge(rack);
			session.merge(item);
			session.getTransaction().commit();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	

}
