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

import com.simple.warehouse.dao.ItemDao;
import com.simple.warehouse.dao.RackDao;
import com.simple.warehouse.dao.WarehouseDao;
import com.simple.warehouse.enitty.Item;
import com.simple.warehouse.enitty.Rack;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RacksServlet extends HttpServlet {
	RackDao rackDao;
	ItemDao itemDao;

	@Override
	public void init() throws ServletException {
		rackDao = new RackDao();
		itemDao = new ItemDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String methodName = req.getParameter("action");
		switch (methodName) {
		case "inbound":
			String rackId = req.getParameter("rackId");
			String itemId = req.getParameter("itemId");
			String countItem = req.getParameter("count");
			Rack rack = rackDao.findRack(rackId);
			rack.setDateInbound(Calendar.getInstance().getTime());
			Item item = itemDao.findItem(itemId);
			if (rack.getItem() == null) {
				ArrayList<Item> listItems = new ArrayList<Item>();
				listItems.add(item);
				rack.setItem(listItems);
				rackDao.putItemToRack(countItem, rack, itemId);
				// forward to view
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
				dispatcher.forward(req, resp);

			} else {
				List<Item> listItems = rack.getItem();
				listItems.add(item);
				rackDao.putItemToRack(countItem, rack, itemId);
				// forward to view
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
				dispatcher.forward(req, resp);
			}
			break;
		case "outbound":
			rackId = req.getParameter("rackId");
			itemId = req.getParameter("itemId");
			countItem = req.getParameter("count");
			rack = rackDao.findRack(rackId);
			rack.setDateOutbound(Calendar.getInstance().getTime());
			item = itemDao.findItem(itemId);
			if (rack.getItem() == null) {
				ArrayList<Item> listItems = new ArrayList<Item>();
				listItems.add(item);
				rackDao.putItemToRack(countItem, rack, itemId);
				// forward to view
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
				dispatcher.forward(req, resp);
			} else {
				List<Item> listItems = rack.getItem();
				rackDao.putItemToRack(countItem, rack, itemId);
				// forward to view
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
				dispatcher.forward(req, resp);
			}

			break;
		default:
			String idWarehouse = req.getParameter("warehouseId");
			String name = req.getParameter("rackName");

			Rack newRack = new Rack();
			newRack.setName(name);
			rackDao.addRacks(newRack, idWarehouse);

			// forward to view
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/listRacks.jsp");
			dispatcher.forward(req, resp);
			break;
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get last path from URI URL
		String path = req.getRequestURI();
		String[] endPath = path.split("/");
		switch (endPath[endPath.length - 1]) {
		case "add-racks":
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/racks/addRacks.jsp");
			dispatcher.forward(req, resp);
			break;
		case "list-racks-data":
			List<Object[]> listRack = rackDao.listRack();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", listRack);

			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(jsonObject);

			break;
		case "input-inbound":
			dispatcher = getServletContext().getRequestDispatcher("/views/inbound-outbound/inputInbound.jsp");
			dispatcher.forward(req, resp);
			break;
		case "input-outbound":
			dispatcher = getServletContext().getRequestDispatcher("/views/inbound-outbound/inputOutbound.jsp");
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
