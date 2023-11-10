package com.simple.warehouse.dao;

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
	
	
}
