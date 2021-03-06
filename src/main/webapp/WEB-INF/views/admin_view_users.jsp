<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/admin_view_users.js" />"></script>
<div class="row"
	style="padding: 2%; padding-left: 5%; padding-right: 5%; margin-top: -4em;">
	<div class="col-md-12">
		<h1 class="page-header">Users</h1>
		<form:form 
			class="form-inline"
			action="viewUsers"
			method="get">
			<label for="usertype">Filter by type: </label> <select
				class="form-control" name="userType">
				<option selected>ALL</option>
				<option>ADMINISTRATOR</option>
				<option>CUSTOMER</option>
			</select>
			<button type="submit" class="btn btn-primary">View</button>
		</form:form>
		<div style="padding-top: 1%">
			<table class="table table-striped table-bordered">
				<thead class="bg-primary">
					<tr>
						<th class="col-xs-2">Email</th>
						<th class="col-xs-2">Name</th>
						<th class="col-xs-2">User type</th>
						<th class="col-xs-2">Date registered</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="eachUser" varStatus="status">
						<tr>
							<td>${eachUser.getEmail()}</td>
							<td>${eachUser.getFirstName()} &nbsp; ${eachUser.getLastName()}</td>
							<td>${eachUser.getUserType()}</td>
							<td>${eachUser.getDateCreated()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>