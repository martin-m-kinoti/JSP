<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String pageTitle = "Login Activity";
    String activeNav = "admin";
%>
<%@ include file="WEB-INF/jspf/shell-top.jspf" %>

<c:if test="${not empty requestScope.error}">
    <div class="alert" role="alert"><span><c:out value="${requestScope.error}"/></span></div>
</c:if>

<p class="page-lede">Every sign-in to the system, most recent first &mdash; who accessed the register and when.</p>

<div class="table-wrap">
    <table class="table">
        <thead>
            <tr>
                <th>User</th>
                <th>Signed In</th>
                <th>IP Address</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty logins}">
                    <tr>
                        <td colspan="3" class="table__empty">No sign-ins recorded yet.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="entry" items="${logins}">
                        <tr>
                            <td><c:out value="${entry.username}"/></td>
                            <td><c:out value="${entry.formattedLoginTime}"/></td>
                            <td><c:out value="${entry.ipAddress}"/></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<%@ include file="WEB-INF/jspf/shell-bottom.jspf" %>
