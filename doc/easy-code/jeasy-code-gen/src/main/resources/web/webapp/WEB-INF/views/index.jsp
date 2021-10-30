<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <title>JEasy Admin系统</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <script src="/dist/static/update-browser/scripts/bowser.js"></script>
    <script>
        if (!((bowser.msie && bowser.version >= 10) || bowser.msedge || bowser.webkit || bowser.firefox))
            location.replace('/dist/static/update-browser/index.html')
    </script>
    <link rel="stylesheet" href="/dist/main.css">
    <link rel="stylesheet" name="theme" href="">
    <link rel="icon" href="./td_icon.ico" type="image/x-icon"/>
</head>

<body>
    <div id="app"></div>
    <div class="lock-screen-back" id="lock_screen_back"></div>
    <script type="text/javascript" src="/dist/vender-base.js"></script>
    <script type="text/javascript" src="/dist/vender-exten.js"></script>
    <script type="text/javascript" src="/dist/main.js"></script>
</body>

</html>
