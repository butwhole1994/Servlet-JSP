package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
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
		
		expCookie.setPath("/");
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");//서버에서 페이지 전환해주기
		
	}
}
