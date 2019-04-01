<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统用户管理</title>
</head>
<body>
<table id="systemUser"></table>

<script type="text/javascript">
    $(function(){

        /*动态获取数据库的远程数据*/
        $("#systemUser").datagrid({
            fit:true,  //让table自适应，填充整个"中神通"
            rownumbers:true,//在表格前显示行号
            url:"<%=basePath %>/getSystemUser.do",//请求后台数据
            striped:true,//显示隔行变色
            columns:[//定义表头
                [
                    {title:'全选',checkbox:true},
                    {title:'管理员账号',field:'account',width:100},
                    {title:'管理员姓名',field:'uName',width:100},
                    {title:'管理员手机',field:'phone',width:100},
                    {title:'管理员邮箱',field:'email',width:100},
                    {title:'管理员状态',field:'status',width:100,formatter:
                            function(value,row,index){
                                if(row.status=='1'){
                                    return "<a href='javascript:void(0)' class='s' onclick='singleUpdate("+row.id+")'>启用</a>";
                                }else{
                                    return "<a href='javascript:void(0)' class='s' onclick='singleUpdate("+row.id+")'>禁用</a>";
                                }
                            }
                    },
                    {title:'权限管理',field:'operation1',width:100,formatter:
                            function(value,row,index){
                                return "<a href='javascript:void(0)'>设置权限</a>";
                            }
                        }
                ]
            ],

            pagination:true,//分页
            pageList:[1,5,10,20],  //定义每页显示的条数
            toolbar:[//在表格前定工具状态栏
                /*已处理按钮*/
                {
                    text:"新增系统用户",
                    iconCls:'icon-add',
                    handler:function() {
                       $("#addSystemUser").window('open').panel('refresh','/pages/admin/systemManagement/addSystemUser.jsp');
                    }
                }

            ]
        });

    });
</script>
<div id="addSystemUser">

</div>
<script type="text/javascript">
    $(function () {
        $("#addSystemUser").window({
            width:600,
            height:400,
            iconCls:'icon-add',
            title:'添加角色',
            minimizable:false,//关闭窗口缩小功能
            maximizable:false,//关闭窗口放大功能
            // draggable:false,//窗口不可移动
            resizable:false,//窗口不可更改尺寸
            closed:true,//前期隐藏窗口
            modal:true,//在未关闭窗口前不得进行其他操作

        })

    })
</script>
</body>
</html>