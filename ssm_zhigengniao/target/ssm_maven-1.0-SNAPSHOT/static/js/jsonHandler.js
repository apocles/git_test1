$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || ''); 
       } else {    
           o[this.name] = this.value || '';    
       }    
   }); 
   //返回的是json对象
   return o;
   //返回的是json字符串
   //return JSON.stringify(o);    
};