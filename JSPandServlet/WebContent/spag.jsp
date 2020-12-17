<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("result", "hello");
/**
	pageContext의 값을 불러올때에는 원래 pageContext.getRequest().getMethod() 라는 코드로 불러 올 수 있다.
	이것을 EL 형식으로 고칠때에는 메소드를 적어선 안되며 ${pageContext.request.method}와 같은 형식으로 불러온다.
*/
%>
<body>
	<%=request.getAttribute("result") %>입니다.<!-- 값을 꺼내기 위한 코드를 작성해야만 한다. -->
	<br>
	${requestScope.result}입니다. 
	<!-- 위처럼 키값을 꺼내기 위한 Controller 코드를 작성하지 않기 위해 EL를 사용하여 키워드만 선언해주어서 키값을 가져온다. -->
	<br>
	${names[0]}입니다.
	<!-- 배열, 리스트 형식의 데이터 구조는 마치 배열의 인덱스값을 조회하는 것 처럼 적어준다면 키값을 가져올 수 있다. -->
	<br>
	${notice.title}입니다.
	<!-- 이처럼, request 라는 저장소에 들어있는 것을 EL을 통해 쉽게 불러내어 MVC2 모델을 지킬 수 있다.  -->
	<br>
	${result}
	<!-- 저장 객체로서의 역할을 담당하는 내장객체는 page, request, session, application 등등이 있으며 이 순서로 키워드를 찾게된다.
		자바머신은 만약 우선순위가 높은 저장객체에서 키워드와 일치하는 값을 찾는다면 그 뒤로는 찾지 않는다. 
		
		이러한 문제때문에 키워드 값이 겹칠 경우 원하는 키값을 부를 수 있는 한정사가 존재한다.
		pageScope, requestScope, sessionScope, applicationScope가 그것이다.-->
		
	<br>
	<br>
	파라미터 값은 <br>${param.n}<br> 입니다.
	<br>
	<br>
	${3>4} 비교연산자의 일반적인 표현방식입니다.
	<br>
	${param.n>4 } n이라는 이름의 파라미터와 정수를 비교한 논리연산자의 표현입니다.
	<br>
	${param.n ge 4} Greater or Equal(ge)이라는 EL비교연산자를 사용한 표현입니다.
	<!-- 굳이 gt gl ge 등의 기호화된 연산자를 사용하는 이유는, 
		html이 기본적으로 태그를 만들기 위해 <>를 사용하고 있기 때문에 엄격한 html구문을 읽어들이는 경우 오류를 일으킬 가능성이 있다.-->
	<br>
	${empty param.n}
	<!-- empty 연산자는 값이 null(파라미터 없음)이어도  true를 리턴하고 값이 비어있어도(?n= 이후 값을 지정안함 즉 그 값은 "")true를 리턴하는 연산자.-->
	<br>
	<br>
	<br>
	헤더의 accept값은<br>${header.accept}<br>입니다.
	<br>
</body>
</html>