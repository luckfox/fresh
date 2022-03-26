<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>form</title>
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
					<a href="${pageContext.request.contextPath}/category" class="list-group-item text-center ">生鲜列表</a>
					<a href="${pageContext.request.contextPath}/category-add.jsp" class="list-group-item text-center ">新增生鲜</a>
					<a href="${pageContext.request.contextPath}/layout-update.jsp" class="list-group-item text-center active">修改生鲜</a>

				</div>
			</div>
			<!-- 右侧内容 -->
			<div class="col-md-9">
				<!-- 错误提示 -->

				<div id="sucess-info" style="display: none" class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="false">&times;</span><span class="sr-only">Close</span></button>
					<strong>成功！</strong> 操作成功提示
				</div>
				<!-- 失败提示框 -->
				<div id="fail-info" style="display: none" class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<strong>失败！</strong> 操作失败提示
				</div>
				<!-- 自定义内容 -->
				<div class="panel panel-default">
					<div class="panel-heading">修改生鲜信息</div>
					<div class="panel-body">
						<form action="category" method="post" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-2 control-label">名称</label>
								<input hidden name="method" value="updateCategory">
								<div class="col-sm-5">
									<input type="text" name="c_name" value="${param.c_name}" class="form-control" placeholder="生鲜名称">
								</div>
								<div class="col-sm-5">
									<p class="form-control-static text-danger">名称不能为空</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">产地</label>
								<div class="col-sm-5">
									<input type="text" name="place"  value="${param.place}" class="form-control" placeholder="产地">
								</div>
								<input type="hidden" name="c_id" value="${param.c_id}">
								<div class="col-sm-5">
									<p class="form-control-static text-danger">产地不能为空</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">类别</label>
								<div class="col-sm-5">
									<label class="radio-inline">
										<input <c:if test="${param.type==0}">checked="checked"</c:if> type="radio" name="type" value="0">未知
									</label>
									<label class="radio-inline">
										<input <c:if test="${param.type==1}">checked="checked"</c:if> type="radio" name="type" value="1">猪牛羊肉
									</label>
									<label class="radio-inline">
										<input <c:if test="${param.type==2}">checked="checked"</c:if> type="radio" name="type" value="2">海鲜水产
									</label>
								</div>
								<div class="col-sm-5">
									<p class="form-control-static text-danger">请选择分类</p>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary">修改</button>
								</div>
							</div>
						</form>

					</div>
				</div>
				
				
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
<%
	System.out.println(response.getStatus()+"--------------status");
	if (response.getStatus()==200) {

	}else if(response.getStatus()==201){
		out.write("<script type=\"text/javascript\">\n" +
				"    window.onload=function(){\n" +
				"        showdiv();\n" +
				"       }</script>");
	}
	else {
		out.write("<script type=\"text/javascript\">\n" +
				"    window.onload=function(){\n" +
				"        offdiv();\n" +
				"       }</script>");
	}

%>


<script>


    function showdiv(){

    if(showdiv_display = document.getElementById('sucess-info').style.display=='none'){//如果show是隐藏的

        document.getElementById('sucess-info').style.display='block';//show的display属性设置为block（显示）
        document.getElementById('fail-info').style.display='none';//show的display属性设置为block（显示）
    }else{//如果show是显示的
        document.getElementById('fail-info').style.display='block';
        document.getElementById('show').style.display='none';//show的display属性设置为none（隐藏）

    }

}
function offdiv(){

   //如果show是显示的
        document.getElementById('fail-info').style.display='block';
        document.getElementById('sucess-info').style.display='none';//show的display属性设置为none（隐藏）



}
</script>

</body>
</html>