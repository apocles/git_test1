<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <script>
        var account=<%=session.getAttribute("account")%>
        if(account==null){
            location.href="<%=basePath %>"
        }

    </script>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎来到xxx后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/js/easyui/themes/icon.css">
    <script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/js/easyui/locale/easyui-lang-zh_CN.js"></script>

    <%--引入kindeditor--%>
    <link rel="stylesheet" href="<%=basePath %>/static/js/kindeditor-4.1.10/themes/default/default.css" />
    <script charset="utf-8" src="<%=basePath %>/static/js/kindeditor-4.1.10/kindeditor-min.js"></script>
    <script charset="utf-8" src="<%=basePath %>/static/js/kindeditor-4.1.10/lang/zh-CN.js"></script>
    <script charset="utf-8" src="<%=basePath %>/static/js/jsonHandler.js"></script>
</head>
<body class="easyui-layout"  >

        <%--北--%>
        <div data-options="region:'north',title:'上',split:true" style="height: 80px">

        </div>
        <%--南--%>
        <div data-options="region:'south',split:true" style="height: 80px">
           <center style="line-height: 200px">&copy;版权所有|侵权必究</center>
        </div>
        <%--西--%>
        <div data-options="region:'west',iconCls:'icon-world',title:'菜单栏',split:true" style="width: 200px">
             <ul id="menu"></ul>
        </div>
        <%--中--%>
        <div data-options="region:'center',split:true">
            <%--选项卡--%>
            <div id="tt" class="easyui-tabs" data-options="fit:true">
                <div title="首页" style="padding:20px;" data-options="iconCls:'icon-house'">
                   <center><span style="line-height:400px;color: #00ee00;font-size:36px;text-shadow: 10px 10px 5px #ccc">欢迎来到xxx平台管理系统</span></center>
                </div>

            </div>

        </div>

<script type="text/javascript">
       $(function () {
           $("#menu").tree({
               url:"<%=basePath %>/getMenu.do",
               onClick:function (node) {
                   //判断当前是几级节点
                   if (node.href== undefined) {

                   }else {
                       //先判断当前选项卡是否存在
                       var flag=$("#tt").tabs("exists",node.text);
                       if (flag) {
                           $("#tt").tabs("select",node.text);//从菜单栏选择一个选项卡
                       }else {
                           $("#tt").tabs('add',{
                               title:node.text,  //选项卡的标题
                               iconCls:node.iconCls,//选项卡图标
                               closable:true,//关闭当前选项卡
                               href:node.href,//远程获取url
                           });
                       }
                   }
               }
           });

       });

</script>
</body>
</html>