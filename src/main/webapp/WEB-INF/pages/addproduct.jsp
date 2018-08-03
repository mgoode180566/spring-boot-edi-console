<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Status</title>

</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">SML EDI Console</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Status <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/all">All</a></li>
                            <li><a href="/locked">Locked</a></li>
                            <li><a href="/unlocked">Unlocked</a></li>
                            <li><a href="/favourites">Favourites</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Configurations <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">All Programs</a></li>
                            <li><a href="/config?id=50020">Test</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Logging <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">All</a></li>
                            <li><a href="#">By program</a></li>
                            <li><a href="#">Today</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Settings <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Servers</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">${user} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
        <!--/.container-fluid -->
    </nav>
</div>
<form:form action="saveproduct" method="post">
<div class="container">
    Add new product ${id}
    <table class="table table-striped">
        <tr><td>Lookup ID</td><td><form:input path="lookupId" value="${id}"/></td></tr>
        <tr><td>Callout</td><td><form:input path="CValue1"/></td>
        <td></td><td><form:input path="CValue2"/></td>
        <td>Secondary</td><td><form:input path="CValue3"/></td></tr>
        <tr><td>Product</td><td><form:input path="UValue1"/></td>
        <td>Style</td><td><form:input path="UValue2"/></td>
        <td></td><td><form:input path="UValue3"/></td></tr>
        <tr><td><button type="submit" class="btn btn-primary">Save</button></td></tr>
    </table>
</div>
</form:form>
</body>
</html>
