/**
 * Created by Administrator on 2017\12\3 0003.
 */
$(function(){
    $("#loginSubmit").keydown(function(event){
        if(event.keyCode==13){
            $("#loginSubmit").click();
        }
    });
    $('#logform').form({
        onSubmit:function(){
            var isValidate = $(this).form('validate');
            if(isValidate){
                var data = $('#logform').serializeObject();
                standardAjaxCall({
                    url: "/api/v1/loginGo",
                    data: data,
                    success : function(jdata){
                        if(jdata.code == RESULT_CODE.SUCCESS_CODE){
                            window.location.href = "/views/home.shtml";
                        }else{
                            layer.msg(jdata.msg ? jdata.msg : "登录失败!", {icon: 5});
                        }
                    }
                });
            }
            return false;
        }
    });
});

