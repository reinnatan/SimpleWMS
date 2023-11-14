package com.simple.warehouse.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simple.warehouse.enitty.Item;
import com.simple.warehouse.enitty.Rack;
import com.simple.warehouse.utils.HibernateUtils;

public class InboundOutboundDao {
	public List<Rack>  getListInboundOutbound(){
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			List<Rack> listRackNew = session.createQuery("from Rack r", Rack.class).list();
			return listRackNew;
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateInbound(String rackId, String itemId, String count) {
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			int itemIdParse = Integer.parseInt(itemId);
			int itemCountParse = Integer.parseInt(count);
			Rack rack = session.find(Rack.class, rackId);
			for(Item item:rack.getItem()) {
				if(item.getId()== itemIdParse) {
					item.setCountInboundItem(itemCountParse);
					item.setDateInbound(Calendar.getInstance().getTime());
					break;
				}
			}
			Transaction tr = session.beginTransaction();
			session.merge(rack);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateOutbound(String rackId, String itemId, String count) {
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			int itemIdParse = Integer.parseInt(itemId);
			int itemCountParse = Integer.parseInt(count);
			Rack rack = session.find(Rack.class, rackId);
			for(Item item:rack.getItem()) {
				if(item.getId()== itemIdParse) {
					item.setCountOutboundItem(itemCountParse);
					item.setDateOutbound(Calendar.getInstance().getTime());
					break;
				}
			}
			Transaction tr = session.beginTransaction();
			session.merge(rack);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
