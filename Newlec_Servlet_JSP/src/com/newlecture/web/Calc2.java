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

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		//계산
		if(op.equals("=")) {
//			int x = (Integer)application.getAttribute("value");
//			int x = (Integer)session.getAttribute("value");
			
			int x = 0;
			for(Cookie c : cookies) 
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			
			
			int y = v;
//			String operator = (String)application.getAttribute("op");
//			String operator = (String)session.getAttribute("op");
			String operator = "";
			for(Cookie c : cookies) 
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			
			int result=0;
			
			if(operator.equals("+")) {
				result = x+y;
			}
			else {
				result = x-y;
			}
			out.printf("reulst is %d\n",result);
		}
		//값을 저장
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("op", op);//키와 값으로 이루어진 저장소
			
//			session.setAttribute("value", v);
//			session.setAttribute("op", op);//키와 값으로 이루어진 저장소
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			//쿠키값으로 보내는 값은 무조건 문자열이어야 한다., 그것도 URL에 사용할 수 있는 형태의 문자열만
			Cookie opCookie = new Cookie("op", op);
			
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);//초 단위의 만료시간
			opCookie.setPath("/calc2");//어떤 url에게 쿠키가 주어질 것인가를 지정하는 쿠키패스
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			//리스폰스 헤더에 심어진 형태로 클라이언트에게 전달
			
			response.sendRedirect("calc2.html");//서버에서 페이지 전환해주기
		}
		
		int result = 0;
		
	}
}
