/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function activateblog(data1){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            //alert("1");
            if(xhr.status===200){
                
            }
        }
    };
    //alert(data1);
    xhr.open("get","controller?action="+escape(data1)+"&perform=activate&command=ADBlog&case=2",true);
    xhr.send(null);
}

function deactivateblog(data1){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            //alert("1");
            if(xhr.status===200){
                
            }
        }
    };
    //alert(data1);
    xhr.open("get","controller?action="+escape(data1)+"&perform=deactivate&command=ADBlog&case=2",true);
    xhr.send(null);
}
function activateuser(data1){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            //alert("1");
            if(xhr.status===200){
                
            }
        }
    };
    //alert(data1);
    xhr.open("get","controller?action="+escape(data1)+"&perform=activate&command=ADUser&case=2",true);
    xhr.send(null);
}
function deactivateuser(data1){
    var xhr=new XMLHttpRequest();
    xhr.onreadystatechange=function(){
       // alert("inside onreadystatechange");
        if(xhr.readyState==4){
            //alert("1");
            if(xhr.status===200){
                
            }
        }
    };
    //alert(data1);
    xhr.open("get","controller?action="+escape(data1)+"&perform=deactivate&command=ADUser&case=2",true);
    xhr.send(null);
}