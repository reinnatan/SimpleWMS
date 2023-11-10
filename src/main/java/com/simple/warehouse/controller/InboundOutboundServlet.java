package com.simple.warehouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.simple.warehouse.dao.InboundOutboundDao;
import com.simple.warehouse.dao.RackDao;
import com.simple.warehouse.enitty.Item;
import com.simple.warehouse.enitty.Rack;
import com.simple.warehouse.utils.HibernateUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InboundOutboundServlet extends HttpServlet{
	InboundOutboundDao inboundOutbound;
	
	@Override
	public void init() throws ServletException {
		inboundOutbound = new InboundOutboundDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		String[] endPath = path.split("/");
		switch (endPath[endPath.length-1]) {
			case "list-inbound-outbound":
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/inbound-outbound/listOutbound.jsp");
				dispatcher.forward(req, resp);
				break;
			case "list-inbound-outbound-data" :
				JSONObject jsonInboundOutbound = new JSONObject();	
				List<Rack> listInboundOutbound = inboundOutbound.getListInboundOutbound();
				jsonInboundOutbound.put("data",listInboundOutbound);
	
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				out.print(jsonInboundOutbound);
				break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

}
