<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>new jsp</title>
</head>
<body>
<center>
	<table border="1" cellpadding="10" cellspacing="0" id="aa">
      <c:forEach var="oneMenu" items="${oneMenu}">
          <tr>
              <td><input type="hidden" name="oneId" value="${oneMenu.oneId}">${oneMenu.oneText}</td>
              <td>
              <c:forEach var="twoMenu" items="${oneMenu.twoMenuList}">
                  <input type="checkbox" flag="${oneMenu.oneId}"   name="twoId" value="${twoMenu.twoId}">${twoMenu.twoText}
              </c:forEach>
              </td>
          </tr>
      </c:forEach>
        <tr>
            <td>操作</td>
            <td>
                <input type="button" value="修改" id="uRoles">
                <input type="button" value="取消" id="quxiao">
            </td>
        </tr>
    </table>
</center>
<script type="text/javascript">
    $(function () {

        //回显
      $.ajax({
          url:"<%=basePath %>/getAuthorityId.do",
          type:"POST",
          dataType:"JSON",
          data:{
            "roleId":${roleId}
          },
          success:function (rs) {
              console.log(rs)
              for (var i in rs){
                  $("input[value="+rs[i]+"]").prop("checked",true);
              }
          }
      })


        //实现取消功能
        $("#quxiao").click(function () {
            $("#editRoles").window("close")

        })

        //实现修改功能
        $("#uRoles").click(function () {
            //判断是否有勾选的权限
           var length= $("input[type=checkbox]:checked").size();
           alert(length)
            //获取勾选的二级权限，及其对应的一级权限
            if (length>=1){//有勾选中的权限
               var ids= $("#aa input[type=checkbox]:checked");
               //记录所有被选中的二级权限id
               var idStr="";
                 ids.each(function (index,dom) {
                    var a= $(dom);
                     idStr+=a.val()+","+a.attr("flag")+",";
                 })
                //开始触发ajax
                $.ajax({
                    url:"<%=basePath %>/updateRoleAndAuthority.do",
                    type:"POST",
                    dataType:"JSON",
                    data:{
                        "idStr":idStr,//所有被勾选中的一级和二级权限ID
                        "roleId":${roleId}
                    },
                    success:function (rs) {
                        if (rs) {
                            //1、关闭弹窗
                            $("#editRoles").window('close');
                            //2、刷新表格  element UI/lay ui
                            $("#editRoles").datagrid("reload");
                            //3、提示信息
                            $.messager.show({
                                title: '提示',
                                msg: '成功添加一套记录',
                                timeout: 3,
                                showType: 'slide'
                            });
                    }else {
                            $.messager.alert("提示","修改失败!")
                        }
                    }
                })

            } else {
                $.messager.alert("提示","至少勾选一个权限!")
            }
        })

    })


</script>
</body>

</html>