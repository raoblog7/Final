/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function logindata(x,y,z,m,id){
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
                    //alert("sdaf");
                    window.location.reload(false);
                    //window.open("controller?command=FullBlog&case=1&id="+id);
                }
            }
        }
    };
    var j="1";
    xhr.open("get","controller?command=AdminLogin&case=2&email="+a.value+"&pass="+b.value+"&display="+j+"&blog_id="+id,true);
    xhr.send(null);
}


function pass(x,y,id){
    var xhr=new XMLHttpRequest();
    var s=document.getElementById(y);
    //var z=document.getElementById(x);
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
                s.value='';
                displaycontent(x,id);
            }
        }
    };
    xhr.open("get","controller?command=AddComments&case=2&id="+id+"&msg="+s.value,true);
    xhr.send(null);
}

function displaycontent(x,id){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
                var arr=JSON.parse(xhr.responseText);
                var z=document.getElementById(x);
                z.innerHTML="";
                for(var i=0;i<arr.comments.length;i++){
                    var tr1=document.createElement("tr");
                    tr1.setAttribute("class","row");
                    var td1=document.createElement("td");
                    td1.setAttribute("class","col-md-3");
                    td1.innerHTML=arr.comments[i].name;
                    var td2=document.createElement("td");
                    td2.setAttribute("class","col-md-9");
                    td2.innerHTML=arr.comments[i].content;
                    tr1.appendChild(td1);
                    tr1.appendChild(td2);
                    z.appendChild(tr1);
                }
            }
        }
    };
    xhr.open("get","controller?command=DisplayComments&case=2&id="+id,true);
    xhr.send(null);
}


function checkpass(x,i){
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


function checkemail(x,z){
    var y=document.getElementById(x).value;
    var atpos = y.indexOf("@");
    var dotpos = y.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=y.length) {
        document.getElementById(z).innerHTML="Not a valid e-mail address";
        return false;
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

function photo(){
    var a=document.getElementById("fil");
    a.click();
    while(1){
    if(a.value!==""){
        document.getElementById("iupload").submit();
        
    }
    break;
    }
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