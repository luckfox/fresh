<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>index</title>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">   
	<style type="text/css">
		body{ font-family: 'Microsoft YaHei';}
		/*.panel-body{ padding: 0; }*/
	</style>
</head>
<body>
<div class="jumbotron">
	<div class="container">
  		<h3>——生鲜管理系统</h3>
  		
	</div>
</div>
<div class="container">
	<div class="main">
		<div class="row">
			<!-- 左侧内容 -->
			<div class="col-md-3">
				<div class="list-group">
					<a href="${pageContext.request.contextPath}/category-list.jsp" class="list-group-item text-center active">產品列表</a>
					<a href="${pageContext.request.contextPath}/category-add.jsp" class="list-group-item text-center ">新增產品</a>
				</div>
			</div>
			<!-- 右侧内容 -->
			<div class="col-md-9">
				<!-- 成功提示框 -->
				<div class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
					<strong>成功！</strong> 操作成功提示
				</div>
				<!-- 失败提示框 -->
				<div style="display: none" class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<strong>失败！</strong> 操作失败提示
				</div>
				<!-- 自定义内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">生鲜列表</div>
					<div class="panel-body">
						<table class="table table-striped table-responsive table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>添加时间</th>
									<th>类别</th>
									<th>产地</th>
									<th width="120">操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${Page.list}" var="category">
								<tr>
									<th>${category.c_id}</th>
									<td>${category.c_name}</td>
									<td>${category.createtime}</td>
									<c:if test="${category.type==0}" >
										<td>未知</td>
									</c:if>
									<c:if test="${category.type==1}" >
										<td>猪牛羊肉</td>
									</c:if>
									<c:if test="${category.type==2}" >
										<td>海鲜水产</td>
									</c:if>
									<td>${category.place}</td>


									<td>
										<%--<a href="">详情</a>--%>
										<a href="${pageContext.request.contextPath}/category?method=deleteCategory&c_id=${category.c_id}">删除</a>
										<a href="${pageContext.request.contextPath}/category-update.jsp?c_id=${category.c_id}&c_name=${category.c_name}&type=${category.type}&place=${category.place}">修改</a>
									</td>
								</tr>
							</c:forEach>


							</tbody>
						</table>
					</div>
				</div>

				<%--<nav>--%>
					<%--<ul class="pagination pull-right">--%>
					<%--<li  class="previous"><a href="#">&laquo;</a></li>--%>
						<%--<c:forEach begin="1" end="${pageBean.totalPage}" var="page">--%>
							<%--<li><a href="#">${page}</a></li>--%>
							<%--<!-- 判断是否是当前页 -->--%>
							<%--&lt;%&ndash;<c:if test="${page==pageBean.currentPage }">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<li class="active"><a href="javascript:void(0);">${page}</a></li>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
							<%--&lt;%&ndash;<c:if test="${page!=pageBean.currentPage }">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<li><a href="${pageContext.request.contextPath}/productListByCid?cid=${cid}&currentPage=${page }">${page }</a></li>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
						<%--</c:forEach>--%>

						<%--<li><a href="#">&raquo;</a></li>--%>
					<%--</ul>--%>

				<%--</nav>--%>

				<!--分页 -->
				<nav>
					<ul class="pagination pull-right">
						<li  class="previous"><a href="#">&laquo;</a></li>
						<c:forEach begin="1" end="${Page.totalPage}" var="Page">
							<li><a href="${pageContext.request.contextPath}/category?method=getCategoryList&currentPage=${Page}&currentCount=10">${Page}</a></li>
						</c:forEach>
						<li><a href="#">&raquo;</a></li>
					</ul>

				</nav>







				<!-- 分页结束 -->
				<%--<ul class="pagination pull-right">--%>
					<%--<li  class="previous"><a href="#">&laquo;</a></li>--%>
					<%--<c:forEach begin="1" end="${pageBean.totalPage+1}" var="page">--%>
						<%--<li><a href="${pageContext.request.contextPath}/category?method=getCategoryList&currentPage=${page}&currentCount=10">${page}</a></li>--%>
					<%--</c:forEach>--%>
					<%--<li><a href="#">&raquo;</a></li>--%>
				<%--</ul>--%>
			</div>
		</div>
  	</div>
</div>
<!-- 尾部 -->
<div class="jumbotron" style=" margin-bottom:0;margin-top:105px;">
	<div class="container">
	<span>&copy; 2016 Saitmob</span>
	</div>
</div>


	<script src="static/js/jquery-3.1.0.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>