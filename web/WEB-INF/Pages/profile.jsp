<%-- 
    Document   : profile
    Created on : 4 Jul, 2017, 4:44:43 PM
    Author     : rahul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
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
                <h4 style="text-align:right;margin-right:150px;margin-top:30px;"><a href="#" id="head">Welcome <%=sess.getAttribute("user_name")%></a></h4>
            </div>
        </div>
        <nav class="navbar" id="affix3" data-spy="affix">
            <div class="container-fluid" id="cf">
                    <ul class="nav navbar-nav">
                    <li><a href="controller?command=index&case=1">Home</a></li>
                    <li >
                            <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">Subscribe</a>
                            <div class="dropdown-menu" style="padding:17px;">
                                <div id="fsub" style="width:250px">
                                <form action="#" id="df"> 
                                    <input type="text" class="form-control form" id="email" name="email" placeholder="Enter your email" style="width:250px;margin-bottom:20px;" onblur="checkemail('email','sp12');checklength('email','sp12')" required/>
                                    <input type="button" class="btn btn-primary btn-block form" value="Subscribe" id="subbutton" onclick="subscribe('email','subbutton','sp12');"/>
                                    <div id="sp12" class="form" style="margin-top:20px"></div>
                                </form>
                                </div>
                            </div>
                        </li>  
                    
                    <li><a href="#show">Contact us</a></li>
                    <li><a href="#about">About us</a></li>
                    <li>
                        <form class="navbar-form">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search" style="width:350px" required disabled/>
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </li>
                    <li><a href="controller?command=SignOut&case=2">Sign out</a></li>
                
		</ul>
            </div>
	</nav>
        <div class="container-fluid">
            <div class="row" style="margin-top:150px">
                <div class="col-md-4 col-md-offset-1">
                    <br><br>
                </div>
            </div>
            <div class="row" >
                <div class="col-md-3 col-md-offset-1" >
                    <img class="thumbnail" src="controller?command=GetImage&case=2&path=${blog[0]}" alt="china thumbnail" width="250" height="250"> <!-- image 1 -->
                    <form action="controller?command=UpdateImage&case=2" method="post" enctype="multipart/form-data" id="iupload">
                        <a href="#" onclick="photo()" style="margin-left: 15px">Upload your photo</a>
                        <input type="submit"value="upload"/>
                        <input type="file" id="fil" name="image" hidden required/>
                    </form>
                </div>
                <div class="col-md-6">
                    <br>
                    <h5>Name : <%=sess.getAttribute("user_name")%></h5>
                    <br>
                    <h5>Email-id : ${blog[1]}</h5>
                    <br>
                    <h5 style="display:inline-block;">Mobile Number : ${blog[2]}  </h5> <h6 style="display:inline-block;"><a data-toggle="modal" data-target="#myModal" onclick="displayform(1,'dynamicform')">change</a></h6><br>
                    <br><h5 style="display:inline-block;">Current City : ${blog[3]}  </h5> <h6 style="display:inline-block;"><a data-toggle="modal" data-target="#myModal" onclick="displayform(2,'dynamicform')">change</a></h6><br>
                    <br><a data-toggle="modal" data-target="#myModal" onclick="displayform(3,'dynamicform')">change your password</a>
                </div>
            </div>
                    <div class="row" id="show">
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                    </div>
            <div id="footer">
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
                        <form action="#">
                            <legend>Get Connected:</legend>
                            Name:<br>
                            <input type="text" id="in1" name="name" class="form-control" onblur="checklength('in1','spin')" required>
                            <span id="spin" class="emsg text-center text-danger"></span><br>
                            Email:<br>
                            <input type="text" id="in2" name="email" class="form-control" onblur="checklength('in2','spin1');checkemail('in2','spin1')" required>
                            <span id="spin1" class="emsg text-center text-danger"></span><br>
                            Message :<br>
                            <textarea type="text" id="in3" class="form-control" name="message"  style="height:70px;resize:vertical" onblur="checklength('in3','spin2')" required></textarea>
                            <span id="spin2" class="emsg text-center text-danger"></span>
                            <br>
                            <input type="button" value="Submit" id="conbutton" onclick="feedback('in1','in2','in3','spin3','conbutton')" class="btn pull-right btn-primary" />
                            <br>
                            <div id="spin3" class="form" style="margin-top:30px"></div>
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
                    
                </div>
            </div>
        </div>
        <%--change mobile modal--%>
        <div class="container-fluid">
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog modal-md" style="width: 350px;margin-top:150px">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Update your profile</h4>
                        </div>
                        <div class="modal-body" id="dynamicform">
                            <div class="form-group" ></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/profile.js"></script>
    </body>
</html>