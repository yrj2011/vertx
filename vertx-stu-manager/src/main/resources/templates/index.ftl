<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
</head>
<body>
<h1>列表</h1>

<#list userList as user>
    Value: ${user.ID} - ${user.STU_NAME}  <a href="/user/delete?id=${user.ID}"> 删除</a><br>
</#list>

<h1>新增</h1>
<form action="/user/save" method="post">

    <input type="text" name="username"></input>
    <input type="text" name="tel"></input>
    <input type="submit" value="保存"></input>

</form>
</body>
</html>