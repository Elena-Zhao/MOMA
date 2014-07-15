<%-- 
    Document   : SearchFriend
    Created on : 2013-5-15, 1:31:40
    Author     : bianshujun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="post" action="PersonalPageServlet">
        <table border="1">
            <c:forEach var="searchedFriend" items="${searchUserList}">
                <tr>
                    <td><c:out value="${searchedFriend.userName}"/></td>
                    <td><c:out value="${searchedFriend.userEmail}"/></td>
                    <td>&nbsp;</td>
                    <td><input type="checkbox" name="${searchedFriend.userName}" value="加好友？"></td>
                </tr>
            </c:forEach>
                 <tr>
                    <td><input type="submit" value="Submit"></td>
                </tr>
        </table>
        </form>
    </body>
</html>
