package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/spag")
public class Spag extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = 0; 
		
		String num_ = request.getParameter("n");
		if(num_ != null && !num_.equals("")) {
			num = Integer.parseInt(num_);
		}
		
		String result;
		if(num%2 != 0) {
			result = "홀수";
		}
		else {
			result = "짝수";
		}
		request.setAttribute("result", result);

		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names);
		//배열 형식의 데이터구조를 키값에 저장해보았다. 이것을 EL(Expression Language)를 사용하여 jsp 에서 출력해볼 것이다. 
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice);
		//이번에는 Map 형식의 데이터 구조를 만들었다. 이것을 EL을 통해 JSP에서 출력되로론 해본다.
		
		//redirect 현재 작업했떤 것과 전혀 상관없는 새로운 내용을 호출
		//forward 현재 작업한 내역을 이어갈 수 있게 공유
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
