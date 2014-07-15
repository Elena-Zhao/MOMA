<%-- 
    Document   : Friends
    Created on : 2013-5-15, 10:16:10
    Author     : bianshujun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to MOMA</title>
        <meta http-equiv="Content-Type" CONTENT="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="./css/generalStyle.css" media="all" />
    </head>
    <body>
        <div class="navbar">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="brand" href="#">MOMA</a>
                    <ul class="nav">
                        <li><a href="#">首页</a></li>
                        <li><a href="PersonalSpace.jsp">我的纪念册</a></li>
                        <li class="active"><a href="#">我的好友</a></li>

                    </ul>
                    <form class="navbar-search pull-right">
                        <input type="text" class="search-query" placeholder="Search for friends">
                    </form>
                    <ul class="nav pull-right ">
                        <li><a href="#">新建纪念册</a></li>
                    </ul>

                </div>
            </div>
        </div>
        <br />
        <div class="span4">
            <div class="container hero-unit span2 pull-left">
                <div class="label label-warning">
                    <h5>好友分组</h5>
                </div>
                <div class="text-success">
                    <a href="#">同学</a><br/>
                    <a href="#">朋友</a><br/>
                    <a href="#">同事</a><br/>
                    <a href="#">家人</a><br/>
                    <a href="#">爱人</a><br/>
                </div>
            </div>

            <div class="container hero-unit span2">
                <div class="label label-warning">
                    <h5>好友纪念册</h5>
                </div>
                <div class="text-warning">
                    <a href="#">第一个纪念册</a><br/>
                    <a href="#">第二个纪念册</a><br/>
                    <a href="#">第三个纪念册</a><br/>
                    <a href="#">第四个纪念册</a><br/>
                    <a href="#">第五个纪念册</a><br/>
                </div>
            </div>
        </div>
        <div class="span11">
            <div class="row lead text-left"> 
                <a href="#">a</a>&nbsp;
                <a href="#">b</a>&nbsp;
                <a href="#">c</a>&nbsp;
                <a href="#">d</a>&nbsp;
                <a href="#">e</a>&nbsp;
                <a href="#">f</a>&nbsp;
                <a href="#">g</a>&nbsp;
                <a href="#">h</a>&nbsp;
                <a href="#">i</a>&nbsp;
                <a href="#">j</a>&nbsp;
                <a href="#">k</a>&nbsp;
                <a href="#">l</a>&nbsp;
                <a href="#">m</a>&nbsp;
                <a href="#">n</a>&nbsp;
                <a href="#">o</a>&nbsp;
                <a href="#">p</a>&nbsp;
                <a href="#">q</a>&nbsp;
                <a href="#">r</a>&nbsp;
                <a href="#">s</a>&nbsp;
                <a href="#">t</a>&nbsp;
                <a href="#">u</a>&nbsp;
                <a href="#">v</a>&nbsp;
                <a href="#">w</a>&nbsp;
                <a href="#">x</a>&nbsp;
                <a href="#">y</a>&nbsp;
                <a href="#">z</a>&nbsp;

            </div>
            <br />
            <div class="row">
                <ul class="thumbnails">
                    <li class="span2">
                        <div class="thumbnail">
                            <img src="img/portrait.png" alt="">
                            <h4>shitVincent</h4>
                            <a class="btn btn-success btn-mini" href="#">进入主页</a>
                            <a class="btn btn-danger btn-mini" href="#">解除关系</a>
                        </div>
                    </li>

                    <li class="span2">
                        <div class="thumbnail">
                            <img src="img/portrait.png" alt="">
                            <h4>巴蒂</h4>
                            <a class="btn btn-success btn-mini" href="#">进入主页</a>
                            <a class="btn btn-danger btn-mini" href="#">解除关系</a>
                        </div>
                    </li>
                    <li class="span2">
                        <div class="thumbnail">
                            <img src="img/portrait.png" alt="">
                            <h4>科比</h4>
                            <a class="btn btn-success btn-mini" href="#">进入主页</a>
                            <a class="btn btn-danger btn-mini" href="#">解除关系</a>
                        </div>
                    </li>
                    <li class="span2">
                        <div class="thumbnail">
                            <img src="img/portrait.png" alt="">
                            <h4>潘石屹</h4>
                            <a class="btn btn-success btn-mini" href="#">进入主页</a>
                            <a class="btn btn-danger btn-mini" href="#">解除关系</a>
                        </div>
                    </li>
                    <li class="span2">
                        <div class="thumbnail">
                            <img src="img/portrait.png" alt="">
                            <h4>严俊杰</h4>
                            <a class="btn btn-success btn-mini" href="#">进入主页</a>
                            <a class="btn btn-danger btn-mini" href="#">解除关系</a>
                        </div>

                </ul>
            </div>
        </div>
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="bootstrap.js"></script>
    </body>
</html>
