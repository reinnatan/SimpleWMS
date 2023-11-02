package com.simple.warehouse.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.simple.warehouse.utils.HibernateUtils;

public class InboundOutboundDao {
	public List<Object[]>  getListInboundOutbound(){
		Transaction transaction = null;
		try(Session session = HibernateUtils.getSessionFactory().openSession()){
			List<Object[]> listRack = session.createQuery("select r.id, r.name, r.count, r.dateInbound, r.dateOutbound, r.item from Rack r", Object[].class).list();
			return listRack;
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
		

	}
}
