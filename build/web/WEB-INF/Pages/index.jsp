<%-- 
    Document   : index
    Created on : 18 Jun, 2017, 11:43:01 PM
    Author     : rahul
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html id="main">
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
    <%if(sess==null){%>
    <body onload="display('dd')" data-spy="scroll" data-target="#affix5 #affix2">
    <%}%>
    <%if(sess!=null){%>
    <body onload="display('dd');datagetter1('favourite',<%=sess.getAttribute("user_id")%>);datagetter2('trending');" data-spy="scroll" data-target="#affix5 #affix2">
    <%}%>    
        <div class="container-fluid">
            <div class="row affix-top" id="affix5" data-spy="affix">
                <div class="col-md-1"></div>
                <div class="col-lg-5">
                    <h1>
                        <a href="#" id="head">Rao's blog</a>
                    </h1>
                </div>
                <% if(sess==null){%>
                    <form action="#">
                        <div class="col-md-2 form" >
                            <div class="form-group">
                                <input type="text" class="form-control" name="email" placeholder="Email" style="width:200px" id="loginemail" onblur="checklength('loginemail','spand2');checkemail('loginemail','spand2')" required/>
                                <span id="spand2" class="emsg text-center text-danger"></span>
                            </div>
                        </div>
                        <div class="col-md-2 form">
                            <div class="form-group">
                                <input id="ff" type="password" class="form-control" name="pass" placeholder="Password" style="width:200px" onblur="checklength('ff','spand');checkpass('ff','spand')" required/>
                                <span id="spand" class="emsg text-center text-danger"></span>    
                                <a href="#" data-toggle="modal" data-target="#myModal" >Forgot password?</a>
                            </div>
                        </div>
                        <div class="col-md-2 form">
                            <div class="form-group">
                                    <input type="button" id="loginbutton" class="btn  btn-primary" onclick="disbutton('loginbutton');logindata('loginemail','ff','spand3','loginbutton')" value="Sign in"/>
                                    <br><span id="spand3" class="emsg text-center text-danger"></span>
                            </div>
                        </div>
                    </form>
                <%}%>
                <% if(sess!=null){%>
                <h4 style="text-align:right;margin-right:150px;margin-top:30px;"><a href="controller?command=UserProfile&case=2" id="head">Welcome <%=sess.getAttribute("user_name")%></a></h4>
                <%}%>
            </div>
        </div>
        <nav class="navbar affix-top" id="affix2" data-spy="affix">
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
                                    <button class="btn btn-primary" type="submit" disabled="">
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
            <div class="row" id="content">
                <div class="col-md-7 col-md-offset-1" id="dd"></div>
                <%if(sess!=null){%>
                <div class="col-md-3 thumbnail blog-box" id="fav" data-spy="affix" data-offset-bottom="826"  >
                    <h3>Favourite</h3>
                    <table class="table table-striped" >
                        <tbody id="favourite"></tbody>
                    </table>
                    <legend></legend>
                    <h3>Trending</h3>
                    <table class="table table-striped" >
                            <tbody id="trending"></tbody>
                    </table>
                </div>
                <%}%>
                <%if(sess==null){%>
                <div class="col-md-3 thumbnail blog-box affix-top" id="cl3" data-spy="affix" data-offset-bottom="770"  >
                    <form name="myform" onsubmit="return validateform()" action="controller?command=SignUp&case=2" method="post">
                        <h4 style="margin-left: 50px;">Be great at what you do</h4>
                        <input type="text" class="form-control" name="fn" placeholder="First name" id="fn" onblur="checklength('fn','sp1')" required/>
                        <span id="sp1" class="emsg text-center text-danger"></span>
                        <input type="text" class="form-control" name="ln" class="form" placeholder="Last name" style="margin-top:20px" id="ln" onblur="checklength('ln','sp2')" required/>
                        <span id="sp2" class="emsg text-center text-danger"></span>
                        <input type="text" class="form-control" name="email" class="form" placeholder="Email" id="email1" style="margin-top:20px" onblur="checklength('email1','sp3');checkemail('email1','sp3');checkalready('email1','sp3')" required/>
                        <span id="sp3" class="emsg text-center text-danger"></span>
                        <input type="password" id="pass1" class="form-control" name="pass" class="form" placeholder="Password (6 to 15 characters)" style="margin-top:20px" onblur="checklength('pass1','sp4');checkpass('pass1','sp4')" required/>
                        <span id="sp4" class="emsg text-center text-danger"></span>
                        <input type="text" class="form-control" name="mobile" id="mobile" class="form" placeholder="Mobile Number" style="margin-top:20px;"/>
                        <input type="text" class="form-control" name="city" class="form" id="city2" placeholder="City" style="margin-top:20px;" onblur="checklength('city2','sp5')" required/>
                        <span id="sp5" class="emsg text-center text-danger"></span>
                        <p style="margin-left:10px;margin-top:10px;">A verification link will be send to your email. Please verify your account to login.</p>
                        <span id="sp6" class="emsg text-center text-danger"></span>
                        <input type="button" name="button" value="Sign Up" id="formbutton" class="btn btn-primary btn-block form" onclick="signupform('fn','ln','email1','pass1','city2','mobile','sp6','formbutton')"/>
                    </form>
                    <br>
                </div>
                
                <%}%>
                
                
                <br>
            </div>
                <div class="row" id="show">
                    <br>
                    <br>
                    <br>
                    <br>
                    <br><br>
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
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog modal-md" style="width: 350px;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Forgot Password</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <form action="controller?command=ForgotPassword&case=2" method="post">
                                    <input type="text" class="form-control form" name="email" placeholder="Enter your email" id="emodal" onblur="checklength('emodal','em1');checkemail('emodal','em1');" required/>
                                    <span id="em1" class="emsg text-center text-danger"></span>
                                    <input type="password" class="form-control form" name="pass" placeholder="Enter new password" id="pmodal" onblur="checklength('pmodal','em2');checkpass('pmodal','em2');" required/>
                                    <span id="em2" class="emsg text-center text-danger"></span>
                                    <p style="margin-top:10px;">A link has been send to your email address. Please click on that link to reset your password.
                                    <input type="submit" class="btn  btn-primary form btn-block" id="modalbutton" onclick="disbutton('modalbutton')" value="Send"/>
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/js/index.js"></script>
    </body>
</html>
