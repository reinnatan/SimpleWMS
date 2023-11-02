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

public class ItemDao {
	
	public void addItem(Item item) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.persist(item);
			transaction.commit();
		}catch (Exception e) {
			if (transaction !=null ) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public void addItemWithRackId(Item item, String idRack) {
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			Rack rack = session.find(Rack.class, idRack);
			if (Objects.isNull(rack.getItem())){
				List<Item> list = new ArrayList<Item>();
				list.add(item);
				rack.setItem(list);
			}else {
				 rack.getItem().add(item);
			}
			transaction = session.beginTransaction();
			session.persist(item);
			session.merge(rack);
			transaction.commit();
		}catch (Exception e) {
			if (transaction !=null ) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	
	public List<Object[]> listItem(){
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			return (List<Object[]>) session.createQuery("from Item i", Object[].class).list();
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

}
