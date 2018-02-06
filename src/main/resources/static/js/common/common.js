/**
 * Created by rain on 2017/12/12
 * common层提供可复用的组件，供page层使用。
 */
(function($) {
    $.fn.extend({
        /**
         * JQuery EasyUI DataGrid 扩展方法
         * 修改DataGrid对象的默认大小，以适应页面宽度。
         * @param heightMargin 高度对页内边距的距离。
         * @param widthMargin 宽度对页内边距的距离。
         * @param minHeight 最小高度。
         * @param minWidth 最小宽度。
         */
        resizeDataGrid : function(heightMargin, widthMargin, minHeight, minWidth) {
            var height = $(window).height() - heightMargin;
            var width = $(window).width() - widthMargin;

            height = height < minHeight ? minHeight : height;
            width = width < minWidth ? minWidth : width;
            width = width -5;
            $(this).datagrid('resize', {
                height : height,
                width : width
            });
        },
        /**
         * JQuery EasyUI TreeGrid 扩展方法
         * 修改TreeGrid对象的默认大小，以适应页面宽度。
         * @param heightMargin 高度对页内边距的距离。
         * @param widthMargin 宽度对页内边距的距离。
         * @param minHeight 最小高度。
         * @param minWidth 最小宽度。
         */
        resizeTreeGrid : function(heightMargin, widthMargin, minHeight, minWidth) {
            var height = $(document.body).height() - heightMargin;
            var width = $(document.body).width() - widthMargin;
            height = height < minHeight ? minHeight : height;
            width = width < minWidth ? minWidth : width;
            $(this).treegrid('resize', {
                height : height,
                width : width
            });
        },

        /**
         * Form 表单序列化为JSON对象
         * 使用方法：$('#ffSearch').serializeObject()
         * @returns {{}}
         */
        serializeObject : function() {
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
            var $radio = $('input[type=radio],input[type=checkbox]',this);
            $.each($radio,function(){
                if(!o.hasOwnProperty(this.name)){
                    o[this.name] = '';
                }
            });
            return o;
        }
    });
})(window.jQuery);

/** 标准格式AjaxCall_ */
function standardAjaxCall(options){
    AjaxCall(options.url, options.data, function(jdata) {
        if (options.success && typeof(options.success) == 'function') {
            options.success(jdata);
        } else {
            MsgAlert(jdata);
        }
    });
}
function AjaxCall(url, args, fun){
    var options = {
        url: url,
        data: args,
        success : function(result) {
            if (AjaxSuccess(result)) {
                if ($.isFunction(fun)) {
                    fun(result);
                } else {
                    MsgAlert({content: result, type: 'error'});
                }
            }
        },
        error : function(jqXHR, textStatus, errorThrown){
                alert(textStatus);
        }
    };
    options = $.extend({}, options);
    Ajax_(options);
}

function Ajax_(opts){
    opts = $.extend({}, {type: 'post', data: '', dataType: 'json',
        success: function(){},
        error:function(){}
    }, opts);
    $.ajax({
        type : opts.type,
        url : opts.url,
        data : opts.data,
        dataType : opts.dataType,
        success : opts.success,
        error : opts.error
    });
}
function AjaxSuccess(result) {
    if (!result) {
        return false;
    }
    if (result.code == RESULT_CODE.NO_LOGIN_CODE) {
        layer.open({
            icon: 5,
            content: "未登陆或登陆超时，请重新登陆",
            yes: function (index, layero) {
                window.location.href = "/login.shtml";
                layer.close(index);
            }
        });
        return false;
    }
    if(result.msg){
        var reg = new RegExp("#\{[0-9]{1,}\}", 'g');
        var res = result.msg.match(reg) || [];
        for (var i = 0; i < res.length; i++) {
            result.msg = result.msg.replace(res[i], ((result.msgData && result.msgData[i]) || ''));
        }
    }
    return true;
}
function MsgAlert(opts){
    var icon_ = {'info': 1, 'error': 5};
    opts = $.extend({}, {PWindow: parent, type: 'info'}, opts);
    opts.PWindow.layer.msg(opts.content, {icon: icon_[opts.type]});
}
