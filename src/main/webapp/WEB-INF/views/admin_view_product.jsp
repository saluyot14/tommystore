<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row" style="padding: 2%;">
	<h1 class="page-header">Products</h1>
	<c:if test="${not empty productName}">
		<div class="alert alert-danger fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Failed!</strong> Product <strong>${productName}</strong> not
			found!
		</div>
	</c:if>
	<form:form class="form-inline" action="viewProducts?category="
		method="get">
		<label for="usertype">Filter by category: </label>
		<select class="form-control" name="category">
			<option selected>All</option>
			<c:forEach items="${categories}" var="category" varStatus="status">
				<option>${category.getName()}</option>
			</c:forEach>
		</select>
		<button type="submit" class="btn btn-primary">View</button>
	</form:form>
	<h5 class="text-muted">Category: ${not empty category ? category : 'All'}</h5>
	<h6 class="text-muted">${not empty inventories ? inventories.size() : '0'}
		product/s returned</h6>
	<div style="height: 50em; overflow: scroll;">
		<c:choose>
			<c:when test="${not empty inventories}">
				<c:forEach items="${inventories}" var="inventory" varStatus="status">
					<div class="col-xs-6 col-sm-3"
						style="padding: 1%; padding-top: 2%;">
						<div class="thumbnail">
							<img class="card-img-top img-responsive"
								src="${pageContext.request.contextPath}/image?name=${inventory.getProduct().getName()}"
								alt="Card image cap" style="height: 200px;">
							<div class="caption">
								<h4>${inventory.getProduct().getName()}</h4>
								<h4 class="text-primary">&#8369;
									${inventory.getProduct().getPrice()}</h4>
								<h4 class="text-muted">
									Stock: <span class="text-success">${inventory.getStock()}</span>
								</h4>
								<a href="updateProduct?name=${inventory.getProduct().getName()}"
									class="btn btn-success btn-xs" role="button">Update</a> <a
									href="updateInventory?name=${inventory.getProduct().getName()}"
									class="btn btn-success btn-xs" role="button">Re-stock</a> <a
									href="deleteProduct?name=${inventory.getProduct().getName()}"
									class="btn btn-danger btn-xs" role="button">Delete</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h1 class="text-muted text-center">No products yet. Start
					creating one!</h1>
				<div class="col-md-12 text-center" style="padding-top: 1%">
					<a class="btn btn-lg btn-success btn-center" href="addProduct">Add
						product</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
