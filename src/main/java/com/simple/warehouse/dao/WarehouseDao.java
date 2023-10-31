package com.simple.warehouse.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.simple.warehouse.enitty.Warehouse;
import com.simple.warehouse.utils.HibernateUtils;

public class WarehouseDao {
	
	public void addWarehouse(Warehouse warehouse) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.persist(warehouse);
			transaction.commit();
		}catch (Exception e) {
			if (transaction !=null ) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
	}
	
	public List<Object[]> listWarehouse(){
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			return (List<Object[]>) session.createQuery("select w.id, w.name, w.address, w.phone from Warehouse w", Object[].class).list();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Object[]> getSelectedWarehouse(String id){
		Transaction transaction = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()){
			Integer idParse = Integer.parseInt(id);
			return (List<Object[]>) session.createQuery("select w.id, w.name, w.address, w.phone from Warehouse w where w.id=:id", Object[].class).setParameter("id", idParse).list();
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateWarehouse(String id, String name, String address, String phone) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			Warehouse selectedWarehouse = session.find(Warehouse.class, id);
			selectedWarehouse.setName(name);
			selectedWarehouse.setAddress(address);
			selectedWarehouse.setPhone(phone);
			session.persist(selectedWarehouse);
			transaction.commit();
		}catch (Exception e) {
			if (transaction !=null ) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteWarehouse(String id) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			Warehouse selectedWarehouse = session.find(Warehouse.class, id);
			session.remove(selectedWarehouse);
			transaction.commit();
		}catch (Exception e) {
			if (transaction !=null ) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	

}
