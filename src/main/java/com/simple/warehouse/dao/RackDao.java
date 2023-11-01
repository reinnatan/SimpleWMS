package com.simple.warehouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
			return (List<Object[]>) session.createQuery("select r.id, r.name, r.count, r.dateInbound, r.dateOutbound from Rack r", Object[].class).list();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Object[]> inputInbound(){
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			return (List<Object[]>) session.createQuery("select r.id, r.name, r.count, r.dateInbound, r.dateOutbound from Rack r", Object[].class).list();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

}
