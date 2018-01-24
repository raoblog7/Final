/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function displayform(x,y){
    var y=document.getElementById(y);
    y.innerHTML="";
    var form=document.createElement("form");
    form.setAttribute("class","form");
    if(x===1){
        form.action="#";
        form.setAttribute("method","post");
        var input=document.createElement("input");
        input.setAttribute("type","text");
        input.setAttribute("name","mobile");
        input.required=true;
        input.setAttribute("placeholder","Enter new mobile number");
        input.setAttribute("class","form-control form");
        input.setAttribute("id","inp1")
        input.setAttribute("onblur","checklength('inp1','spmobile')");
        var span=document.createElement("span");
        span.setAttribute("class","text-center text-danger emsg");
        span.setAttribute("id","spmobile");
        var button=document.createElement("input");
        button.setAttribute("class","btn form btn-block btn-primary");
        button.setAttribute("type","button");
        button.setAttribute("value","Update");
        button.setAttribute("id","mobilebutton");
        button.setAttribute("onclick","updatemobile('inp1','spmobile2','mobilebutton')");
        var span1=document.createElement("span");
        span1.setAttribute("class","text-center text-danger emsg");
        span1.setAttribute("id","spmobile2");
        form.appendChild(input);
        form.appendChild(span);
        form.appendChild(button);
        form.appendChild(span1);
        y.appendChild(form);
    }
    if(x===2){
        form.action="#";
        form.setAttribute("method","post");
        var input=document.createElement("input");
        input.setAttribute("type","text");
        input.setAttribute("name","city");
        input.required=true;
        input.setAttribute("placeholder","Enter your current city");
        input.setAttribute("class","form-control form");
        input.id="ipcity";
        input.setAttribute("onblur","checklength('ipcity','spcity')")
        var span=document.createElement("span");
        span.setAttribute("class","text-center text-danger emsg");
        span.setAttribute("id","spcity");
        var button=document.createElement("input");
        button.setAttribute("class","btn form btn-block btn-primary");
        button.setAttribute("type","button");
        button.setAttribute("value","Update");
        button.id="citybutton";
        button.setAttribute("onclick","updatecity('ipcity','spcity2','citybutton')")
        var span1=document.createElement("span");
        span1.setAttribute("class","text-center text-danger emsg");
        span1.setAttribute("id","spcity2");
        form.appendChild(input);
        form.appendChild(span);
        form.appendChild(button);
        form.appendChild(span1);
        y.appendChild(form);
    }
    if(x===3){
        form.action="controller?command=UpdatePassword&case=2";
        form.setAttribute("method","post");
        var input1=document.createElement("input");
        input1.setAttribute("type","text");
        input1.setAttribute("name","email");
        input1.required=true;
        input1.setAttribute("placeholder","Enter your email");
        input1.setAttribute("class","form-control form");
        input1.id="inemail";
        input1.setAttribute("onblur","checkemail('inemail','spup1')");
        input1.setAttribute("onblur","checklength('inemail','spup1')");
        var span=document.createElement("span");
        span.setAttribute("class","text-center text-danger emsg");
        span.setAttribute("id","spup1");
        var input2=document.createElement("input");
        input2.setAttribute("type","password");
        input2.setAttribute("name","pass");
        input2.required=true;
        input2.id="inpass";
        input2.setAttribute("placeholder","Enter your new password");
        input2.setAttribute("class","form-control form");
        input2.setAttribute("onblur","checklength('inpass','spup2')");
        var span1=document.createElement("span");
        span1.setAttribute("class","text-center text-danger emsg");
        span1.setAttribute("id","spup2");
        var p=document.createElement("p");
        p.setAttribute("style","margin-top:10px");
        p.innerHTML="A link has been send to your email address. Please click on that link to reset your password.";
        var button=document.createElement("input");
        button.setAttribute("class","btn form btn-block btn-primary");
        button.setAttribute("type","button");
        button.setAttribute("value","Update");
        button.id="fbutton";
        button.setAttribute("onclick","updatepassword('inemail','inpass','spup3','fbutton')");
        var span2=document.createElement("span");
        span2.setAttribute("class","text-center text-danger emsg");
        span2.setAttribute("id","spup3");
        form.appendChild(input1);
        form.appendChild(span);
        form.appendChild(input2);
        form.appendChild(span1);
        form.appendChild(p);
        form.appendChild(button);
        form.appendChild(span2);
        y.appendChild(form);
    }
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



function updatemobile(x,y,z){
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    var c=document.getElementById(z);
    c.disabled=true;
    if(a.value==""){
        b.innerHTML="Please enter your mobile number";
        c.disabled=false;
        return;
    }
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
              window.location.reload(false);
            }
        }
    };
    xhr.open("get","controller?command=UpdateMobile&case=2&mobile="+a.value,true);
    xhr.send(null);
}


function updatecity(x,y,z){
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    var c=document.getElementById(z);
    c.disabled=true;
    if(a.value==""){
        b.innerHTML="Please enter your city";
        c.disabled=false;
        return;
    }
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
              window.location.reload(false);
            }
        }
    };
    xhr.open("get","controller?command=UpdateCity&case=2&city="+a.value,true);
    xhr.send(null);
}


function updatepassword(x,y,z,t){
    var a=document.getElementById(x);
    var b=document.getElementById(y);
    var c=document.getElementById(z);
    var d=document.getElementById(t);
    d.disabled=true;
    if(a.value==""||b.value==""){
        c.innerHTML="Please enter your details";
        d.disabled=false;
        return;
    }
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
        if(xhr.readyState===4){
            if(xhr.status===200){
              window.location.reload(false);
            }
        }
    };
    xhr.open("get","controller?command=ForgotPassword&case=2&email="+a.value+"&pass="+b.value,true);
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
