/**
 * Created by zhangyong on 2016/06/03.
 */
var empty=function(v){
    switch (typeof v){
        case 'undefined' : return true;
        case 'string' : if(trim(v).length == 0) return true; break;
        case 'boolean' : if(!v) return true; break;
        case 'number' : if(0 === v) return true; break;
        case 'object' :
            if(null === v) return true;
            if(undefined !== v.length && v.length==0) return true;
            for(var k in v){return false;} return true;
            break;
    }
    return false;
};