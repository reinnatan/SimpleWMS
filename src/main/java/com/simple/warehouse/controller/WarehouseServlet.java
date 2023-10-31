package com.simple.warehouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.simple.serializer.WarehouseJSON;
import com.simple.warehouse.dao.WarehouseDao;
import com.simple.warehouse.enitty.Warehouse;
import com.simple.warehouse.utils.HibernateUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class WarehouseServlet extends HttpServlet {
	WarehouseDao dao;
	
	@Override
	public void init() throws ServletException {
		dao = new WarehouseDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		int a = 100;
		switch(method) {
			case "add":
				PrintWriter out = resp.getWriter();
				String name = req.getParameter("name");	
				String address = req.getParameter("address");	
				String phone =  req.getParameter("phone");
				dao.addWarehouse(new Warehouse(name, address, phone));
				break;
			case "update":
				out = resp.getWriter();
				String id = req.getParameter("id");	
				name = req.getParameter("name");	
				address = req.getParameter("address");	
				phone =  req.getParameter("phone");
				dao.updateWarehouse(id, name, address, phone);
				break;
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/warehouse/listWarehouse.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		
		RequestDispatcher dispatcher;
		switch (action) {
		case "/add-warehouse": 
			dispatcher = req.getRequestDispatcher("/views/warehouse/addWarehouse.jsp");
			dispatcher.forward(req, resp);
			break;
		case "/list-warehouse-data":
			List<Object[]> listWarehouse = dao.listWarehouse();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", listWarehouse);
			
			PrintWriter out = resp.getWriter();
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");
	        out.print(jsonObject);
	        
			break;
		case "/edit":
			String id = req.getParameter("id");
			List<Object[]> listWarehouseEdit = dao.getSelectedWarehouse(id);
			req.setAttribute("listWarehouse", listWarehouseEdit.get(0));
			dispatcher = req.getRequestDispatcher("/views/warehouse/editWarehouse.jsp");
			dispatcher.forward(req, resp);
			break;
		case "/list-warehouse":
			dispatcher = req.getRequestDispatcher("/views/warehouse/listWarehouse.jsp");
			dispatcher.forward(req, resp);
			break;
		case "/delete":
			id = req.getParameter("id");
			dao.deleteWarehouse(id);
			dispatcher = req.getRequestDispatcher("/views/warehouse/listWarehouse.jsp");
			dispatcher.forward(req, resp);
			break;
		default:
			dispatcher = req.getRequestDispatcher("/views/warehouse/listWarehouse.jsp");
			dispatcher.forward(req, resp);
			break;
		}
	}
	
}
