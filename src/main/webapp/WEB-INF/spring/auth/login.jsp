<%@page import="com.brokerexam.web.util.SessionUtils"%>
<%@ include file="/WEB-INF/spring/inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
	    String msg = (String) request.getAttribute("msg");
	%>
	<head>
		<title><%= SessionUtils.getMsg("common.app_title", session)%></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		
		<link rel="stylesheet" type="text/css" href="${appConfig.resources}/css/general.css" />
		<script type="text/javascript">
    	function init(){
			document.getElementById("name").focus();
        }
    </script>
	</head>
	
	<body onload="init()">
		
		<div style="align:center">					
		        	<form method="post">
		        		<input type="hidden" name="redirect" value="${appConfig.appContext}/pages/index.jsf" />
		        		<table align="center">
		        			<caption><h3><%= SessionUtils.getMsg("common.app_welcome", session)%></h3></caption>
		        			<c:if test="${msg != null}">
			        			<tr>
			        				<td align="right" colspan="2"><%= SessionUtils.getMsg(msg, session)%></td>
			        			</tr>
		        			</c:if>	
		        		
		        			<tr>
		        				<td><label for="name"><%= SessionUtils.getMsg("common.user", session)%>: </label></td>
		        				<td><input class="dbtInput" id="name"  name="name" type="text" value="${param.name}"/></td>
		        			</tr>
		        			
		        			<tr>
		        				<td><label for="pass"> <%= SessionUtils.getMsg("common.password", session)%>: </label></td>
		        				<td><input class="dbtInput" id="pass" name="pass" type="password" /></td>
		        			</tr>
		        			<tr>
		        				<td align="right" colspan="2"><input type="submit" value="<%= SessionUtils.getMsg("common.login_action", session)%>" /></td>
		        			</tr>
		        		</table>
		        		
		        	</form>
		</div>
</body>
</html>