<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新增系统用户</title>
</head>
<body>
<center>
    <form action="javascript:void(0)" id="addUser">
        <table border="1" cellpadding="8" cellspacing="0">

            <tr>
                <td>管理员姓名：</td>
                <td><input name="uName" class="easyui-validatebox" data-options="required:true" /> </td>
            </tr>
            <tr>
                <td>管理员手机号：</td>
                <td><input name="phone" class="easyui-validatebox" data-options="required:true" /> </td>
            </tr>
            <tr>
                <td>管理员邮箱：</td>
                <td><input name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" /> </td>
            </tr>
            <tr>
                <td>管理员账号：</td>
                <td><input name="account" class="easyui-validatebox" data-options="required:true" /> </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input name="password" type="password" class="easyui-validatebox" data-options="required:true" /> </td>
            </tr>

            <tr>
                <td>管理员状态</td>
                <td>
                    <select name="status">
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>角色授予</td>
                <td>
                    <select id="roleId" name="roleId">

                    </select>
                </td>
            </tr>
            <tr>
                <td>操作</td>
                <td>
                    <input type="submit" value="提交">&nbsp;&nbsp;
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
</center>

<script type="text/javascript">
    $(function () {

        //自动获取角色名称
       $.ajax({
           url:"<%=basePath %>/getRoles1.do",
           type:"POST",
           dataType:"JSON",
           success:function (rs) {
               var content="";
               for (var i in rs) {
                   content+="<option value='"+rs[i].id+"'>"+rs[i].roleName+"</option>";
               }
               $("#roleId").html(content);
           }
       })


        //新增系统用户
        $("input[type=submit]").click(function () {

            //将id=addUser标签下所有参数转换成json格式
            var json=$("#addUser").serializeObject();

            $.ajax({
                url:"<%=basePath %>/addSystemUser.do",
                type:"POST",
                dataType:"JSON",
                data:json,
                success:function (rs) {
                    if (rs){

                        var flag = rs.status;
                        if(flag=='0'){//保存成功
                            //1、关闭弹窗
                            $("#addSystemUser").window('close');
                            //2、刷新表格  element UI/lay ui
                            $("#systemUserDg").datagrid("reload");
                            //3、提示信息
                            $.messager.show({
                                title:'提示',
                                msg:'成功添加一套记录',
                                timeout:3,
                                showType:'slide'
                            });
                        }else if(flag=='2'){//账号已经存在
                            $("input[name=account1]").foucus();
                        }else{
                            $.messager.alert('提示','添加成功');
                        }
                    }
                }
            })
        })

    })
</script>
</body>
</html>