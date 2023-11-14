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

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ItemServlet extends HttpServlet {

	ItemDao itemDao;

	@Override
	public void init() throws ServletException {
		itemDao = new ItemDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		String[] endPath = path.split("/");
		switch (endPath[endPath.length - 1]) {
		case "add-item":
			String nameItem = req.getParameter("nameItem");
			String dateProduction = req.getParameter("dateProduction");
			String countItem = req.getParameter("countItem");

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateParse = null;
			try {
				dateParse = formatter.parse(dateProduction);
				int countItemParse = Integer.parseInt(countItem);
				Item item = new Item(0, nameItem, dateParse, countItemParse);
				itemDao.addItem(item);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/item/listItem.jsp");
			dispatcher.forward(req, resp);
			break;
		case "edit-item":
			String itemId = req.getParameter("itemId");
			nameItem = req.getParameter("nameItem");
			dateProduction = req.getParameter("dateProduction");
			countItem = req.getParameter("countItem");
			formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			dateParse = null;
			try {
				dateParse = formatter.parse(dateProduction);
				int countItemParse = Integer.parseInt(countItem);
				Item selectedItem = itemDao.findItem(itemId);
				selectedItem.setCountItem(countItemParse);
				itemDao.updateItem(selectedItem, nameItem, dateParse);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

			dispatcher = req.getRequestDispatcher("/views/item/listItem.jsp");
			dispatcher.forward(req, resp);
			break;
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		String[] endPath = path.split("/");
		switch (endPath[endPath.length - 1]) {
		case "list-item-data":
			List<Object[]> listItem = itemDao.listItem();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", listItem);

			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(jsonObject);
			break;
		case "list-item":
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/item/listItem.jsp");
			dispatcher.forward(req, resp);
			break;
		case "add-item":
			dispatcher = req.getRequestDispatcher("/views/item/addItem.jsp");
			dispatcher.forward(req, resp);
			break;
		case "edit-item":
			String id = req.getParameter("id");
			Item itemSelected = itemDao.findItem(id);
			req.setAttribute("item", itemSelected);
			req.setAttribute("dateProduction",
					new SimpleDateFormat("yyyy-MM-dd").format(itemSelected.getDateProduction()));
			req.setAttribute("countItem", itemSelected.getCountItem());
			dispatcher = req.getRequestDispatcher("/views/item/editItem.jsp");
			dispatcher.forward(req, resp);
			break;
		case "delete-item":
			id = req.getParameter("id");
			itemDao.deleteItem(id);
			dispatcher = req.getRequestDispatcher("/views/item/listItem.jsp");
			dispatcher.forward(req, resp);
			break;
		default:
			dispatcher = req.getRequestDispatcher("/views/item/listItem.jsp");
			dispatcher.forward(req, resp);
			break;
		}

	}
}
