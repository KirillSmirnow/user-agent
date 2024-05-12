<#-- @ftlvariable name="dataModel" type="useragent.UserAgentInfo" -->

<html lang="en">
<head>
    <title>User Agent</title>
    <style>
        td, table {
            border: aquamarine solid 2px;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <td>User Agent Header</td>
        <td>${dataModel.userAgentHeader!}</td>
    </tr>
    <tr>
        <td>User Agent</td>
        <td>${dataModel.userAgent!}</td>
    </tr>
    <tr>
        <td>OS</td>
        <td>${dataModel.os!}</td>
    </tr>
    <tr>
        <td>Device</td>
        <td>${dataModel.device!}</td>
    </tr>
    <tr>
        <td>IP Address</td>
        <td>${dataModel.ipAddress}</td>
    </tr>
    <tr>
        <td>Location</td>
        <td>${dataModel.location!}</td>
    </tr>
</table>
</body>
</html>
