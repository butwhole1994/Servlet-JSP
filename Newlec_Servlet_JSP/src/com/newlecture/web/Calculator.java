package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String exp = "0";
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null)
			for(Cookie c : cookies) 
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input {");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("	height: 50px;");
		out.write("	background: #e9e9e9;");
		out.write("	font-size: 24px;");
		out.write("	font-weight: bold;");
		out.write("	text-align: right;");
		out.write("	padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form method=\"post\">");
		out.write("	<table>");
		out.write("<tr>");
		out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input name=\"operator\" value=\"CE\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"C\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"BACK\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"/\" type=\"submit\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input name=\"value\" value=\"7\" type=\"submit\"/></td>");
		out.write("<td><input name=\"value\" value=\"8\" type=\"submit\"/></td>");
		out.write("<td><input name=\"value\" value=\"9\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"*\" type=\"submit\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input name=\"value\" value=\"4\" type=\"submit\"/></td>");
		out.write("<td><input name=\"value\" value=\"5\" type=\"submit\"/></td>");
		out.write("<td><input name=\"value\" value=\"6\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"-\" type=\"submit\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input name=\"value\" value=\"1\" type=\"submit\"/></td>");
		out.write("<td><input name=\"value\" value=\"2\" type=\"submit\"/></td>");
		out.write("<td><input name=\"value\" value=\"3\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"+\" type=\"submit\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td></td>");
		out.write("<td><input name=\"value\" value=\"0\" type=\"submit\"/></td>");
		out.write("<td><input name=\"dot\" value=\".\" type=\"submit\"/></td>");
		out.write("<td><input name=\"operator\" value=\"=\" type=\"submit\"/></td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</form>");
		out.write("</body>");
		out.write("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		PrintWriter out = response.getWriter();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		if(cookies != null)
			for(Cookie c : cookies) 
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) {// 연산자가 C 일 경우 쿠키를 삭제
			exp = "";
		}
		else {
			exp += (value == null)? "":value;
			exp += (operator == null)? "":operator;
			exp += (dot == null)? "":dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		if(operator != null && operator.equals("C")) {
			expCookie.setMaxAge(0);
		} // 연산자가 C 일 경우 쿠키를 즉시 삭제하게함
		
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);
		response.sendRedirect("calculator");//서버에서 페이지 전환해주기
	}
}
