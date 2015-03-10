<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
	<link href="${pageContext.request.contextPath}/assets/css/base.css" rel="stylesheet">
</head>
<body>
	<h2>Hello World!</h2>
	${txt}
	<br />
	<spring:message code="hello" />
</body> 
</html>
