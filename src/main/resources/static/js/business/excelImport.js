
        var param = {};
        param = getParentParam_();
        $(function () {
            $("#yzgx").click(function () {
                var input = $("input[name='yunzhuanggouxing']");
                var filePath = $.trim(input.val());
                if(filePath == ""){
                    MsgAlert({content: "请选择允装构型模板文件", type:'error' });
                    return;
                }
                var suffix = filePath.substring(filePath.lastIndexOf('.') + 1);
                if(',xls,xlsx,'.indexOf(suffix) <= -1){
                    MsgAlert({content: "允装构型模板文件必须是Excel格式!", type:'error' });
                    return;
                }
                var fileElementId = input.attr("id");
                $.ajaxFileUpload({
                    url: Constant.API_URL + 'YUNZHUANGGOUXING_IMPORT',
                    secureuri: false,
                    fileElementId: fileElementId, //file控件id
                    dataType: 'json',
                    data: {},
                    success: function (jdata) {
                        if(jdata.code == RESULT_CODE.SUCCESS_CODE){
                            MsgAlert({content: "上传成功" });
                        }else{
                            MsgAlert({content: "上传失败"  + jdata.msg, type:'error' });
                        }
                    },
                    error: function (data, status, e) {
                        alert(e);
                    }

                })
            }),
                    $("#tssyx").click(function () {
                        var input = $("input[name='teshushiyongxing']");
                        var filePath = $.trim(input.val());
                        if(filePath == ""){
                            MsgAlert({content: "请选择特殊适用性模板文件", type:'error' });
                            return;
                        }
                        var suffix = filePath.substring(filePath.lastIndexOf('.') + 1);
                        if(',xls,xlsx,'.indexOf(suffix) <= -1){
                            MsgAlert({content: "节点物料模板文件必须是Excel格式!", type:'error' });
                            return;
                        }
                        var fileElementId = input.attr("id");
                        $.ajaxFileUpload({
                            url: Constant.API_URL + 'TSSYX_IMPORT',
                            secureuri: false,
                            fileElementId: fileElementId,//file控件id
                            dataType: 'json',
                            //  data: {},
                            success: function (jdata) {
                                if(jdata.code == RESULT_CODE.SUCCESS_CODE){
                                    MsgAlert({content: "上传成功" });
                                }else{
                                    MsgAlert({content: "上传失败" + jdata.msg, type:'error' });
                                }
                            },
                            error: function (data, status, e) {
                                alert(e);
                            }
                        })
                    }),

                    $("#jdwl").click(function () {
                        var input = $("input[name='jiedianwuliao']");
                        var filePath = $.trim(input.val());
                        if(filePath == ""){
                            MsgAlert({content: "请选择节点物料模板文件", type:'error' });
                            return;
                        }
                        var suffix = filePath.substring(filePath.lastIndexOf('.') + 1);
                        if(',xls,xlsx,'.indexOf(suffix) <= -1){
                            MsgAlert({content: "节点物料模板文件必须是Excel格式!", type:'error' });
                            return;
                        }
                        var fileElementId = input.attr("id");
                        $.ajaxFileUpload({
                            url: Constant.API_URL + 'JDWL_IMPORT',
                            secureuri: false,
                            fileElementId: fileElementId,
                            dataType: 'json',
//                    data: {},
                            success: function (jdata) {
                                if(jdata.code == RESULT_CODE.SUCCESS_CODE){
                                    MsgAlert({content: "上传成功" });
                                }else{
                                    MsgAlert({content: "上传失败" + jdata.msg, type:'error' });
                                }
                            },
                            error: function (data, status, e) {
                                alert(e);
                            }

                        })
                    })

        })


