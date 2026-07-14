<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String pageTitle = "Add Student";
    String activeNav = "add-student";
%>
<%@ include file="WEB-INF/jspf/shell-top.jspf" %>

<c:if test="${not empty requestScope.error}">
    <div class="alert" role="alert"><span><c:out value="${requestScope.error}"/></span></div>
</c:if>

<p class="page-lede">Enter the student's details to add them to the register.</p>

<section class="panel panel--form">
    <form action="AddStudentServlet" method="post" novalidate>
        <div class="field-grid">
            <div class="field">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" placeholder="Enter first name" required autofocus>
            </div>

            <div class="field">
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" placeholder="Enter last name" required>
            </div>

            <div class="field">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter email address" required>
            </div>

            <div class="field">
                <label for="course">Course</label>
                <input type="text" id="course" name="course" placeholder="Enter course name" required>
            </div>
        </div>

        <button type="submit" class="btn">Save Student</button>
    </form>
</section>

<%@ include file="WEB-INF/jspf/shell-bottom.jspf" %>
