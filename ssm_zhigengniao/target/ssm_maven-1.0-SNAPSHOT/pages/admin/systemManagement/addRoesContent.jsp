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
      <table border="1" width="50%" >
          <tr>
              <td>角色名：</td>
              <td><input id="vv" class="easyui-validatebox" data-options="required:true,validType:length[1,10]" />  </td>
          </tr>
          <tr>
              <td>权限：</td>
              <td><ul id="authority1">1</ul> </td>
          </tr>
          <tr>
              <td><a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">确定</a> </td>
              <td><a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a> </td>
          </tr>
      </table>
  </center>
  <script type="text/javascript">
      $(function () {

          //取消操作
          $("#btn2").click(function () {
              //关闭窗口
              $("#addRoles").window('close');
          })

          //自动获取角色可以授予的权限（这里指普通权限）
          $("#authority1").tree({
              url:"<%=basePath %>/getRoleAuthority.do",
              checkbox:true,
              lines:true,
          });

          //完成添加角色的功能
          $("#btn1").click(function () {

              var roleName=$("#vv").val();//获取角色名
              var noes= $('#authority1').tree('getChecked', ['checked','indeterminate']);//获取选中的权限id

              var authorityId="";
              for (var i in noes){
                  authorityId+=noes[i].id+",";
              }

              $.ajax({
                 url:"/addRoles.do",
                  type:"POST",
                  dataType:"JSON",
                  data:{
                     "roleName":roleName,
                      "authorityId":authorityId
                  },
                  success:function (rs) {
                      if (rs){
                          //1、校验数据:0成功、1失败、2用户名重复、3数据格式错误 -1未知错误
                          var status=rs.status;
                          if (status==0){
                              //关闭窗口
                              $("#addRoles").window('close');
                              //刷新
                              $("#roles").datagrid("reload");
                              //右下角显示弹窗
                              $.messager.show({
                                  title:'提示',
                                  msg:'成功添加一条记录',
                                  timeout:3000,
                                  showType:'slide',

                              });
                          } else if (status==1){
                              $.messager.alert("提示","添加失败.")
                          } else if (status==2){
                              $.messager.confirm('提示', '用户名已存在，您确认继续添加吗？', function(r){
                                  if (r){
                                      //清空输入的内容
                                     $("#vv").val("");
                                     //获取焦点，光标在输入框内
                                     $("#vv").focus();
                                  }
                              });

                          } else  if (status==3){
                              $.messager.alert("提示","数据格式错误。")
                          } else {
                              $.messager.alert("提示","出现未知错误。")
                          }

                      }
                  }
              })
          })
      })
  </script>
</body>

</html>