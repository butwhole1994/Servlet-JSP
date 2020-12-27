package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calcpage")
public class CalcPage extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
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
		out.write("	<form method=\"post\" action=\"calc3\">");
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
}
