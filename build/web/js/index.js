/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function logindata(x,y,z,m){
    var d=document.getElementById(m);
    d.disabled=true;
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    var c=document.getElementById(z);
    if(a.value==""||b.value==""){
        c.innerHTML="Please enter valid login details";
        d.disabled=false;
        return;
    }
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
                
                //alert(xhr.responseText);
                if(xhr.responseText.length<30){
                    d.disabled=false;
                //c=document.getElementById("main");
                    c.innerHTML=xhr.responseText;
                }else{
                    window.location.reload(false);
                }
            }
        }
    };
    xhr.open("get","controller?command=AdminLogin&case=2&email="+a.value+"&pass="+b.value+"&display=0",true);
    xhr.send(null);
}

function display(x){
     var ss=document.getElementById(x);
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState==1){
            var img=document.createElement("img");
            img.setAttribute("src","/blog/images/loading.gif");
            ss.appendChild(img);
        }    
        if(xhr.readyState==4){
            if(xhr.status===200){
                ss.innerHTML="";
                var arr=JSON.parse(xhr.responseText);
                var row1=document.createElement("div");
                row1.setAttribute("class","row");
                var col1=document.createElement("div");
                col1.setAttribute("class","col-md-6");
                var col2=document.createElement("div");
                col2.setAttribute("class","col-md-6");  
                row1.appendChild(col1);
                row1.appendChild(col2);
                ss.appendChild(row1);
                for(var i=0;i<arr.blog.length;i=i+2){
                    for(var j=i;j<i+2;j++){
                        var div1=document.createElement("div");
                        div1.setAttribute("style","background-color:#f9f9f9");
                        div1.setAttribute("class","thumbnail blog-box");
                        var img=document.createElement("img");
                        img.setAttribute("src","controller?command=GetImage&case=2&path="+arr.blog[j].path);
                        div1.appendChild(img);
                        var div2=document.createElement("div");
                        div2.setAttribute("class","caption");
                        var h4=document.createElement("h4");
                        var anc=document.createElement("a");
                        anc.setAttribute("href","controller?command=FullBlog&case=2&id="+arr.blog[j].id);
                        anc.innerHTML="read more";
                        h4.innerHTML=arr.blog[j].title;
                        div2.appendChild(h4);
                        var p=document.createElement("p");
                        p.innerHTML=arr.blog[j].content+".....";
                        p.appendChild(anc);
                        div2.appendChild(p);
                        div1.appendChild(div2);
                        if(j===i){
                            col1.appendChild(div1);
                        }
                        else{
                            col2.appendChild(div1);
                        }
                    }
                }
            }
        }
    };
    xhr.open("get","controller?command=DynamicBlogDisplay&case=2",true);
    xhr.send(null);
}



function datagetter2(x){
    //alert("hello");
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            if(xhr.status===200){
                //alert(xhr.responseText);
                var arr=JSON.parse(xhr.responseText);
                var z=document.getElementById(x);
                z.innerHTML="";
                for(var i=0;i<arr.blog.length;i++){
                    var tr1=document.createElement("tr");
                    tr1.setAttribute("class","row");
                    var anc=document.createElement("a");
                    anc.setAttribute("href","controller?command=FullBlog&case=2&id="+arr.blog[i].id);
                    anc.innerHTML=arr.blog[i].title;
                    tr1.appendChild(anc);
                    z.appendChild(tr1);
                }
            }
        }
    };
    xhr.open("get","controller?command=TrendingBlogs&case=2",true);
    xhr.send(null);
}


function datagetter1(x,y){
    //alert("hello");
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            if(xhr.status===200){
                //alert(xhr.responseText);
                var arr=JSON.parse(xhr.responseText);
                var z=document.getElementById(x);
                z.innerHTML="";
                for(var i=0;i<arr.blog.length;i++){
                   // alert("1");
                    var tr1=document.createElement("tr");
                    tr1.setAttribute("class","row");
                    var anc=document.createElement("a");
                    anc.setAttribute("href","controller?command=FullBlog&case=2&id="+arr.blog[i].id);
                   // alert("2");
                    anc.innerHTML=arr.blog[i].title;
                    tr1.appendChild(anc);
                    z.appendChild(tr1);
                   // alert("3");
                }
            }
        }
    };
    xhr.open("get","controller?command=FavouriteBlog&case=2&id="+y,true);
    xhr.send(null);
}


function checkpass(x,i){
    //alert("hii");
    var y=document.getElementById(x).value;
    var z=document.getElementById(i);
    if(y.length<6 || y.length>15){
       z.innerHTML="Password must be 6-15 characters";
    }else{
       z.innerHTML=""; 
    }
}

function checkemail(x,n){
    //alert("1");
    var a=document.getElementById(x);
    var y=a.value;
    var z=document.getElementById(n);
    var atpos = y.indexOf("@");
    var dotpos = y.lastIndexOf(".");
    //alert("2");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=y.length ||atpos==0 ||dotpos==0) {
        a.value="";
        z.innerHTML="Not a valid e-mail address";
        
    }
    else{
        z.innerHTML="";
    }
}


function checklength(x,y){
    //alert("ihi");
    var a=document.getElementById(x).value;
    var b=document.getElementById(y);
    if(a.length===0){
        b.innerHTML="Field can't be empty";
    }
    else{
        b.innerHTML="";
    }
}


function subscribe(x,y,z){
    var c=document.getElementById(z);
    c.setAttribute("class","alert alert-info");
    c.innerHTML="Processing...";
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    if(a.value==""){
        c.innerHTML="Please enter your email";
        return;
    }
    b.disabled=true;
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState===4){
            if(xhr.status===200){
                //alert(xhr.responseText);
                b.disabled=false;
                c.innerHTML=xhr.responseText;
               
                
                
                }
            }
        
    };
    xhr.open("get","controller?command=EmailSubscription&case=2&email="+a.value,true);
    xhr.send(null);
}

function disbutton(x){
    //var z=x;
    //var y=document.getElementById(x);
    //y.disabled = true;
    //setTimeout(function(){x =z;
      //  var y=document.getElementById(x);
    //y.disabled = true;},5000);
   // y.disabled=false;
    //y.disabled=false;
    //window.setTimeout(y.disabled =true, 30000);
    //y.disabled =false;
}


function checkalready(x,y){
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
                var c=xhr.responseText;
                if(c==1){
                    a.value="";
                    b.innerHTML="Email already exits";
                }
            }
        }
    };
    xhr.open("get","controller?command=CheckUser&case=2&email="+a.value,true);
    xhr.send(null);
}

function feedback(x,y,z,s,t){
    var m=document.getElementById(t);
    m.disabled=true;
    var d=document.getElementById(s);
    d.setAttribute("class","alert alert-info");
    d.innerHTML="Please wait...While sending your feedback.......";
    var a=document.getElementById(x).value;
    var b=document.getElementById(y).value;
    var c=document.getElementById(z).value;
    if(a==""||b==""||c==""){
         d.innerHTML="Please enter the details first";
         m.disabled=false;
         return;
    }
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
                
                m.disabled=false;
                d.innerHTML=xhr.responseText;
                
               // setTimeout(d.removeChild(div),5000);
            }
        }
    };
    xhr.open("get","controller?command=FootConnect&case=2&email="+b+"&name="+a+"&message="+c,true);
    xhr.send(null);
}

function signupform(x,y,z,m,n,l,o,p){
    var g=document.getElementById(p);
    g.disabled=true;
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    var c=document.getElementById(z);
    var d=document.getElementById(m);
    var e=document.getElementById(n);
    var f=document.getElementById(o);
    var h=document.getElementById(l);
      
    
    if(a.value==""||b.value==""||c.value==""||d.value==""||e.value==""){
        f.innerHTML="Please enter valid form details";
        g.disabled=false;
        return;
    }
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
                
                //alert(xhr.responseText);
                if(xhr.responseText.length<30){
                    g.disabled=false;
                //c=document.getElementById("main");
                    f.innerHTML=xhr.responseText;
                }else{
                    a.value="";
                    b.value="";
                    c.value="";
                    d.value="";
                    e.value="";
                    f.value="";
                    window.location.reload(false);
                }
            }
        }
    };
    xhr.open("get","controller?command=SignUp&case=2&fn="+a.value+"&ln="+b.value+"&email="+c.value+"&pass="+d.value+"&mobile="+h.value+"&city="+e.value,true);
    xhr.send(null);
}