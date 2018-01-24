<%-- 
    Document   : errorpage
    Created on : 7 Jul, 2017, 12:30:50 AM
    Author     : rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/css/index.css" rel="stylesheet"/>
        <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
        <title>Rao's blog</title>
    </head>
    <%
        HttpSession sess=request.getSession(false);
    %>
    <body>
        <div class="container-fluid">
            <div class="row" id="affix1" data-spy="affix">
                <div class="col-md-1"></div>
                <div class="col-lg-4">
                    <h1>
                        <a href="#" id="head">Rao's blog</a>
                    </h1>
                </div>
            </div>
        </div>
        <nav class="navbar" id="affix3" data-spy="affix">
            <div class="container-fluid" id="cf">
		<ul class="nav navbar-nav">
                    <li><a href="controller?command=index&case=1">Home</a></li>
                    <li><a class="dropdown-toggle" href="#" data-toggle="dropdown">Subscribe
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Email</a></li>
                        </ul>  
                    </li>
                    
                    <li><a href="#show">Contact us</a></li>
                    <li><a href="#about">About us</a></li>
                    <li>
                        <form class="navbar-form">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search" style="width:350px">
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </li>
                     <%if(sess!=null){%>
                    <li><a href="controller?command=UserProfile&case=2">Your profile</a></li>
                    <%}%>
                    <% if(sess!=null){%>
                    
                        <li><a href="controller?command=SignOut&case=2">Sign out</a></li>
                    <%}%>
                    
		</ul>
            </div>
        </nav>        
        <div class="container-fluid">
            <div class="row" style="margin-top: 300px">
                <div class="col-lg-4 col-lg-offset-4">
                    <img src="<%=request.getContextPath()%>/images/error.jpeg" > <!-- image 1 -->
                </div>
            </div>
            <div class="row" id="show">
                <br><br><br><br><br>
            </div>
            <div id="footer" style="margin-top:100px">
                <div class="row" id="ft1">
                    <br>
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <img class="thumbnail" src="<%=request.getContextPath()%>/images/DSC_0266.Jpeg" width="160" height="208"/>
                        <h4>Rahul Rao</h4>
                        <h5>B.Tech(Computer Science)<br></h5>
                        <h5>Integral University, Lucknow</h5>
                    </div>
                    <div class="col-md-4">
                        <form action="controller?command=FootConnect&case=2" method="post">
                            <legend>Get Connected:</legend>
                            Name:<br>
                            <input type="text" name="name" class="form-control">
                            Email:<br>
                            <input type="text" name="email" class="form-control">
                            Message :<br>
                            <textarea type="text" class="form-control" name="message" style="height:70px;resize:vertical"></textarea>
                            <br>
                            <input type="submit" value="Submit" class="btn pull-right btn-primary">
                            <br>
                        </form> 
                    </div>
                </div>
                <div class="row" >
                    <br>
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="row" id="about">
                            <legend></legend>
                            <div class="col-md-2">About Rahul</div>
                            <div class="col-md-10">
                                <p>
                                    Rahul is a talented, ambitious and hardworking individual with broad skills in Coding and leading projects. Handling multiple tasks on a daily basis competently, working well under the pressure. One of his key strengths is communication, building strong relationships with people in order to deliver the best results. He is confident in his abilities to produce and while he prepare for the worst, he do the work necessary to tilt the odds that the best will happen. He want to be judged by individual performance and he want be rewarded for his efforts. He seek to work for a successful company that has strong leadership and vision and who recognizes and rewards performers. He is someone who is consistently growing himself and who takes the time to continue learning even though it's not a direct requirement of the job. He find that many times it's a combination of what you study both directly and indirectly related to work. He is someone who is mature, candid and who has integrity.  Everyday he work to improve himself and his skills which is part of maturing and becoming better at what he do.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" >
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="row">
                            <legend></legend>
                            <div class="col-md-2"></div>
                            <div class="col-md-10"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        Copyright@rb2017
                    </div>
                    <div class="col-md-3 pull-right">
                        <span>Send us feedback : </span>
                        <a href="#">www.rb.com/feedback</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
