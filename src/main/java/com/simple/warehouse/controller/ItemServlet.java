package com.simple.warehouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import com.simple.warehouse.dao.ItemDao;
import com.simple.warehouse.enitty.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ItemServlet extends HttpServlet{
	
	ItemDao itemDao;
	
	@Override
	public void init() throws ServletException {
		itemDao = new ItemDao();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nameRack = req.getParameter("rackName");	
		String dateProduction = req.getParameter("dateProduction");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dateParse=null;
		try {
			dateParse = formatter.parse(dateProduction);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		Item item = new Item(0, nameRack, dateParse);
		itemDao.addItem(item);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Object[]> listRack = itemDao.listItem();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", listRack);
		
		PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonObject);
	}
}
