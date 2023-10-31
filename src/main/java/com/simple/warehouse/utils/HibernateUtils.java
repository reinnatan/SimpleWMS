package com.simple.warehouse.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.simple.warehouse.enitty.Item;
import com.simple.warehouse.enitty.Rack;
import com.simple.warehouse.enitty.Warehouse;

public class HibernateUtils {
	
	public static SessionFactory sesionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sesionFactory ==null) {
			try {
				Configuration configuration = new Configuration();
				Properties settings = new Properties();
				settings.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
				settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/warehouse");
				settings.put("hibernate.connection.username", "root");
				settings.put("hibernate.connection.password", "");
				settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(Warehouse.class);
				configuration.addAnnotatedClass(Rack.class);
				configuration.addAnnotatedClass(Item.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sesionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sesionFactory;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return sesionFactory;
	}

}
