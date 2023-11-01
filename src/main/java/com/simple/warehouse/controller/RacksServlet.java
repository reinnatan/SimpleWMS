package com.simple.warehouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.json.JSONObject;

import com.simple.warehouse.dao.RackDao;
import com.simple.warehouse.dao.WarehouseDao;
import com.simple.warehouse.enitty.Rack;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RacksServlet extends HttpServlet {
	RackDao dao;
	
	@Override
	public void init() throws ServletException {
		dao = new RackDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idWarehouse = req.getParameter("warehouseId");	
		String name = req.getParameter("rackName");	
		//Integer count = Integer.parseInt(req.getParameter("count"));	
		//String inBoundDate =  req.getParameter("inboundDate");
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		//Date dateParse=null;
		//try {
		//	dateParse = formatter.parse(inBoundDate);
		//} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		//add data to new Rack
		Rack newRack = new Rack();
		newRack.setName(name);
		//newRack.setCount(count);
		//newRack.setDateInbound(dateParse);
		dao.addRacks(newRack, idWarehouse);
		
		//forward to view
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
		dispatcher.forward(req, resp);
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get last path from URI URL
		String path = req.getRequestURI();
		String[] endPath = path.split("/");
		switch(endPath[endPath.length-1]) {
			case "add-racks":
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/addRacks.jsp");
				dispatcher.forward(req, resp);
				break;
			case "list-racks-data":
				List<Object[]> listRack = dao.listRack();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("data", listRack);
				
				PrintWriter out = resp.getWriter();
		        resp.setContentType("application/json");
		        resp.setCharacterEncoding("UTF-8");
		        out.print(jsonObject);
		        
				break;
			case "input-inbound":
				dispatcher = getServletContext().getRequestDispatcher("/views/racks/inputInbound.jsp");
				dispatcher.forward(req, resp);
				break;
			case "list-racks":
				dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
				dispatcher.forward(req, resp);
				break;
			default:
				dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
				dispatcher.forward(req, resp);
		}
		
	}
}
