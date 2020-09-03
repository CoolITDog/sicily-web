<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>西西里后台</title>
        <link rel="stylesheet" href="../static/css/main.css" />
        <link rel="stylesheet" href="../static/css/layui.css" media="all">
        <link rel="stylesheet" href="../static/css/admin.css" media="all">
    </head>
    <body class="layui-layout-body">
    <div id="LAY_app">
        <div class="layui-layout layui-layout-admin">
            <%--头部区域--%>
            <div class="layui-header">
                <ul class="layui-nav layui-layout-left">
                    <li class="layui-nav-item layadmin-flexible" lay-unselect>
                        <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                            <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                        </a>
                    </li>
                </ul>
            </div>

            <%--左侧菜单栏--%>
            <div class="layui-side layui-side-menu">
                <div class="layui-side-scroll">
                    <div class="layui-logo" lay-href="home/console.html">
                        <span>${user.userName}</span>
                    </div>
                    <%--菜单项--%>
                    <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
                        <%--菜单管理--%>
                        <li data-name="get" class="layui-nav-item">
                            <a href="javascript:;" lay-href="//www.layui.com/admin/#get" lay-tips="授权" lay-direction="2">
                                <i class="layui-icon layui-icon-auz"></i>
                                <cite>菜单管理</cite>
                            </a>
                        </li>
                        <%--商品管理--%>
                        <li data-name="get" class="layui-nav-item">
                            <a href="javascript:;" lay-href="//www.layui.com/admin/#get" lay-tips="授权" lay-direction="2">
                                <i class="layui-icon layui-icon-auz"></i>
                                <cite>商品管理</cite>
                            </a>
                        </li>
                        <%--订单管理--%>
                        <li data-name="get" class="layui-nav-item">
                            <a href="javascript:;" lay-href="//www.layui.com/admin/#get" lay-tips="授权" lay-direction="2">
                                <i class="layui-icon layui-icon-auz"></i>
                                <cite>订单管理</cite>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <%-- 主体内容--%>
            <div class="layui-body" id="LAY_app_body">
                <div class="layadmin-tabsbody-item layui-show">
                    <iframe src="/index.html" frameborder="0" class="layadmin-iframe"></iframe>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>