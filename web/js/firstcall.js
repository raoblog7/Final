/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

function display(x){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            if(xhr.status===200){
                var arr=JSON.parse(xhr.responseText);
                var row1=document.createElement("div");
                row1.setAttribute("class","row");
                var col1=document.createElement("div");
                col1.setAttribute("class","col-md-6");
                var col2=document.createElement("div");
                col2.setAttribute("class","col-md-6");  
                var ss=document.getElementById(x);
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

function displayform(x,y){
    var y=document.getElementById(y);
    y.innerHTML="";
    var form=document.createElement("form");
    form.setAttribute("class","form");
    if(x===1){
        form.action="controller?command=UpdateMobile&case=2";
        form.setAttribute("method","post");
        var input=document.createElement("input");
        input.setAttribute("type","text");
        input.setAttribute("name","mobile");
        input.required=true;
        input.setAttribute("placeholder","Enter new mobile number");
        input.setAttribute("class","form-control form");
        var button=document.createElement("input");
        button.setAttribute("class","btn form btn-block btn-primary");
        button.setAttribute("type","submit");
        button.setAttribute("value","Update");
        form.appendChild(input);
        form.appendChild(button);
        y.appendChild(form);
    }
    if(x===2){
        form.action="controller?command=UpdateCity&case=2";
        form.setAttribute("method","post");
        var input=document.createElement("input");
        input.setAttribute("type","text");
        input.setAttribute("name","city");
        input.required=true;
        input.setAttribute("placeholder","Enter your current city");
        input.setAttribute("class","form-control form");
        var button=document.createElement("input");
        button.setAttribute("class","btn form btn-block btn-primary");
        button.setAttribute("type","button");
        button.setAttribute("value","Update");
        form.appendChild(input);
        form.appendChild(button);
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
        input1.onblur=checkemail('inemail');
        var input2=document.createElement("input");
        input2.setAttribute("type","password");
        input2.setAttribute("name","pass");
        input2.required=true;
        input2.setAttribute("placeholder","Enter your new password");
        input2.setAttribute("class","form-control form");
        var p=document.createElement("p");
        p.setAttribute("style","margin-top:10px");
        p.innerHTML="A link has been send to your email address. Please click on that link to reset your password.";
        var button=document.createElement("input");
        button.setAttribute("class","btn form btn-block btn-primary");
        button.setAttribute("type","button");
        button.setAttribute("value","Update");
        form.appendChild(input1);
        form.appendChild(input2);
        form.appendChild(p);
        form.appendChild(button);
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

function checkemail(x){
    var y=document.getElementById(x).value;
    if(y.length==0){
        return false;
    }
    var atpos = y.indexOf("@");
    var dotpos = y.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=y.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}

function subscribe(x,a,c){
    var xhr=new XMLHttpRequest();
    var y=document.getElementById(x);
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState===4){
            if(xhr.status===200){
                //alert(xhr.responseText);
                var b=document.getElementById(a);
                b.removeChild(document.getElementById(c));
                var d=document.createElement("div");
                d.setAttribute("class","alert alert-info");
                //d.setAttrinbute("style","margin-top:10px");
                d.innerHTML=xhr.responseText;
                b.appendChild(d);
                
                
                }
            }
        
    };
    xhr.open("get","controller?command=EmailSubscription&case=2&email="+y.value,true);
    xhr.send(null);
}