package com.simple.warehouse.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RacksServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
