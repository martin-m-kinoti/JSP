<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String pageTitle = "Edit Student";
    String activeNav = "students";
%>
<%@ include file="WEB-INF/jspf/shell-top.jspf" %>

<c:if test="${not empty requestScope.error}">
    <div class="alert" role="alert"><span><c:out value="${requestScope.error}"/></span></div>
</c:if>

<p class="page-lede">Update this student's details, then save your changes.</p>

<section class="panel panel--form">
    <form action="EditStudentServlet" method="post" novalidate>
        <input type="hidden" name="id" value="<c:out value="${student.id}"/>">
        <div class="field-grid">
            <div class="field">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" placeholder="Enter first name"
                       value="<c:out value="${student.firstName}"/>" maxlength="50" required autofocus>
            </div>

            <div class="field">
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" placeholder="Enter last name"
                       value="<c:out value="${student.lastName}"/>" maxlength="50" required>
            </div>

            <div class="field">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter email address"
                       value="<c:out value="${student.email}"/>" maxlength="100" required>
            </div>

            <div class="field">
                <label for="course">Course</label>
                <input type="text" id="course" name="course" placeholder="Enter course name"
                       value="<c:out value="${student.course}"/>" maxlength="100" required>
            </div>
        </div>

        <div class="quick-actions">
            <button type="submit" class="btn">Save Changes</button>
            <a class="btn btn--secondary" href="ListStudentsServlet">Cancel</a>
        </div>
    </form>
</section>

<%@ include file="WEB-INF/jspf/shell-bottom.jspf" %>
