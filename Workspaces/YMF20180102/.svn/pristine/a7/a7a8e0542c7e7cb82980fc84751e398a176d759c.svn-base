/**
 * Created by Nature on 16/7/8.
 */
(function(){
    var ele = document.querySelector(".input-text");
    if(ele){ele.addEventListener("input" , retrieve , false);}
    function retrieve(){
        var val = ele.value;
        if(val.length > 0){
            document.querySelector(".input-btn").classList.remove("not");
        }else{
            document.querySelector(".input-btn").classList.add("not");
        }
    }
    var ele1 = document.querySelector(".input-money");
    if(ele1){ele1.addEventListener("input" , retrieve1 , false);}
    function retrieve1(){
    	var val = ele1.value;
    	if(val.length > 0){
    		document.querySelector(".input-btn").classList.remove("not");
    	}else{
    		document.querySelector(".input-btn").classList.add("not");
    	}
    }
})();
(function(){
    var childs = document.querySelectorAll(".pm-child");
    function start() {
        this.classList.add("pm-hover");
        if(!this.querySelector(".icon-yes").classList.contains("on")){
            for(var i=0, ln=childs.length; i<ln; i++){
                childs[i].querySelector(".icon-yes").classList.remove("on");
            }
            this.querySelector(".icon-yes").classList.add("on");
        }
    }
    function end(){
        this.classList.remove("pm-hover");
    }
    for(var i=0, ln=childs.length; i<ln; i++){
        childs[i].addEventListener("touchstart" , start , false);
        childs[i].addEventListener("touchend" , end , false);
    }
})();