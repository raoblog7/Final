<%-- 
    Document   : admin
    Created on : 16 Jun, 2017, 7:52:25 PM
    Author     : rahul
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rao's blog</title>
        <script src="<%=request.getContextPath()%>/js/tinymce.min.js"></script>
        <link href="<%=request.getContextPath()%>/css/skin.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/css/content.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet"/>
        <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
        <script  type="text/javascript">
            tinymce.init({
                selector:'#myTextarea'
            });
        </script>
    </head>
    <%
        HttpSession sess=request.getSession(false);
    %>
    <body data-spy="scroll">
        <nav class="navbar" id="affix4" data-spy="affix">
            <div class="container-fluid">
                <div class="navbar-header" >
                    <a class="navbar-brand" href="#">ADMIN PORTAL</a>
		</div>
                <ul class="nav navbar-nav navbar-right ">
                    <li><a href="controller?command=SignOut&case=2">Sign out</a></li>
                </ul>
                <ul class="nav navbar-nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#add">Add a blog</a></li>
                    <li><a data-toggle="tab" href="#deactivateblog">Deactivate a blog</a></li>
                    <li><a data-toggle="tab" href="#deactivateuser">Deactivate a user</a></li>
                </ul>
            </div>
	</nav>
        <div class="container-fluid">
            <div class="tab-content" id="tc">
                <div id="add" class="tab-pane fade in active">
                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-4 thumbnail" id="cl3" >
                            <form action="controller?command=BlogSubmit&case=2" method="post" enctype="multipart/form-data">
                                <h3 style="margin-left: 15px;">Add a blog now</h3>
                                <input type="text" class="form-control" name="title" placeholder="TITLE" />
                                <input type="text" class="form-control form" name="category" placeholder="CATEGORY" />
                                <input type="text" class="form-control form" name="sub_category" placeholder="SUB-CATEGORY"/><br>
                                <textarea type="text" class="form-control form" name="content" id="myTextarea" style="height:170px;resize:vertical"></textarea>
                                <label class="form"> Select a image for your blog</label>
                                <input type="file" class="" name="image" value="fileupload" id="fileupload"/>
                                <input type="submit" value="ADD THIS BLOG" class="btn btn-primary form btn-block" />
                            </form>
                            <br>
                        </div>
                    </div>
                </div>
                <div id="deactivateblog" class="tab-pane fade">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6 thumbnail" id="cl3" >
                            <h3 style="margin-left: 15px;">Deactivate blog now</h3>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Blog name</th>
                                        <th>Activate</th>
                                        <th>Deactivate</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="x" items="${requestScope.blog}"> 
                                        <tr>
                                            <td>${x.key}</td>
                                            <c:choose>
                                                <c:when test="${x.value=='1'}">
                                                    <td class="col"><input type="radio" name="${x.key}" id="radioactive" name="${x.key}" value="${x.key}" onclick="activateblog('${x.key}')" checked>Activate</td>
                                                    <td><input type="radio" name="${x.key}" id="radiodeactive" name="${x.key}" value="${x.key}" onclick="deactivateblog('${x.key}')">Deactivate</td>
                                                </c:when>    
                                                <c:otherwise>
                                                    <td><input type="radio" id="radioactive"  value="${x.key}" onclick="activateblog('${x.key}')">Activate</td>
                                                    <td><input type="radio" id="radiodeactive" value="${x.key}" onclick="deactivateblog('${x.key}')" checked>Deactivate</td>
                                                </c:otherwise>
                                            </c:choose>
                                        </tr>
                                    </c:forEach>
                                </tbody>	
                            </table>
                        </div>
                    </div>
                </div>
                <div id="deactivateuser" class="tab-pane fade">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6 thumbnail" id="cl3" >
                            <h3 style="margin-left: 15px;">Deactivate user now</h3>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Email</th>
                                        <th>First Name</th>
                                        <th>Activate</th>
                                        <th>Deactivate</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="x" items="${requestScope.login}"> 
                                        <tr>
                                            <td>${x.key}</td>
                                            <td>${x.value}</td>
                                            <c:forEach var="y" items="${requestScope.user}"> 
                                                <c:choose>
                                                    <c:when test="${x.key==y.key}">
                                                        <c:choose>
                                                            <c:when test="${y.value=='1'}">
                                                                <td><input type="radio" id="radioactive" name="${x.key}" value="${x.key}" onclick="activateuser('${x.key}')" checked>Activate</td>
                                                                <td><input type="radio" id="radiodeactive" name="${x.key}" value="${x.key}" onclick="deactivateuser('${x.key}')">Deactivate</td>
                                                            </c:when>    
                                                            <c:otherwise>
                                                                <td><input type="radio" id="radioactive"  value="${x.key}" name="${x.key}" onclick="activateuser('${x.key}')">Activate</td>
                                                                <td><input type="radio" id="radiodeactive" value="${x.key}" name="${x.key}" onclick="deactivateuser('${x.key}')" checked>Deactivate</td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </tr>
                                     </c:forEach>
                                </tbody>	
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/blogad.js"></script>
    </body>
</html>
