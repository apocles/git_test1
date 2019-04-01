<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色列表</title>
</head>
<body>
<table id="roles"></table>
<script type="text/javascript">
    $(function(){
        //改变角色状态
         function singleUpdate(temp){
             $.ajax({
				 url:"/updateStatus.do",
				 type:"POST",
				 dataType:"JSON",
				 data:{
				     "id":temp
				 },
				 success:function (rs) {
					 if (rs){
					     //局部刷新
					     $("#roles").datagrid("reload");
					 } else {
					     $.messager.alert("提示","修改失败,请重试！")
					 }
                 }
			 });
		 }

        /*动态获取数据库的远程数据*/
        $("#roles").datagrid({
            fit:true,  //让table自适应，填充整个"中神通"
            rownumbers:true,//在表格前显示行号
            url:"<%=basePath %>/getRoles.do",//请求后台数据
            striped:true,//显示隔行变色
            columns:[//定义表头
                [
                    {title:'全选',checkbox:true},
                    {title:'角色名',field:'roleName',width:100},
                    {title:'角色状态',field:'status',width:100,formatter:
                            function(value,row,index){
                                if(row.status=='1'){
                                    return "启用";
                                }else{
                                    return "禁用";
                                }
                            }},
                    {title:'修改时间',field:'updateTime',width:100,sortable:true},
                    {title:'角色操作',field:'operation1',width:100,formatter:
                            function(value,row,index){
							if (row.isRoot=='1'){
	                          return "无操作";
							} else {
								if(row.status=='1'){

									return "<a href='javascript:void(0)' class='s' onclick='singleUpdate("+row.id+")'>禁用</a>";
								}else{
									return "<a href='javascript:void(0)' class='s' onclick='singleUpdate("+row.id+")'>启用</a>";
								}
							}
						}
                    },
                    {title:'权限操作',field:'operation2',width:100,formatter:
                            function(value,row,index){
							if (row.isRoot=='1') {
								return "无操作";
							}else {
							    console.log(row)
								return "<a href='javascript:void(0)' onclick='editJueSe("+row.id+")'>编辑权限</a>";
							}
						}
                    }
                ]
            ],
            sortName:'updateTime',//定义哪个列可以排序
            remoteSort:false,//关闭远程排序，否则前段排序失效
            pagination:true,//分页
            pageList:[1,5,10,20],  //定义每页显示的条数
            toolbar:[//在表格前定工具状态栏
                /*已处理按钮*/
                {
                    text:"添加角色",
                    iconCls:'icon-add',
                    handler:function() {
                        $("#addRoles").window('open').panel('refresh','/pages/admin/systemManagement/addRoesContent.jsp');
                    }
                },'-',{
                    text:"删除角色",
                    iconCls:'icon-cancel',
                    handler:function() {

                    }

                  }

            ]
        });

    });
</script>

<div id="addRoles">

</div>

<script type="text/javascript">

	$(function () {
		$("#addRoles").window({
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



<div id="editRoles">

</div>
<script type="text/javascript">
//编辑角色权限
   function editJueSe (roleId){

       $("#editRoles").window("open").panel("refresh","<%=basePath %>/getOneMenu.do?roleId="+roleId);
   }

    $(function () {
        $("#editRoles").window({
            width:600,
            height:400,
            iconCls:'icon-add',
            title:'编辑角色权限',
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