<%@ page import="model.CarsUsers" %>
<%@ page import="store.Store" %>
<%@ page import="store.HbmStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/table.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/nav.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <script>
        function previewFile(input) {
            var file = $("input[type=file]").get(0).files[0];
            if(file){
                var reader = new FileReader();

                reader.onload = function(){
                    $("#previewImg").attr("src", reader.result);
                }

                reader.readAsDataURL(file);
            }
        }
    </script>



</head>
<body>

<div class="menu">
    <ul>
        <li><a href="<%=request.getContextPath()%>/index.jsp">All ads</a></li>
        <% if (request.getSession().getAttribute("user") == null) { %> <li>
        <a href="<%=request.getContextPath()%>/login.jsp">Login</a> </li>
        <% } else { %>
        <li>  <a href="<%=request.getContextPath()%>/login.jsp"> <%=request.getSession().getAttribute("user")%> | Logout</a>
            <% } %></li>
    </ul>
</div>

<form action="<%=request.getContextPath()%>/addcar.do" method="post" enctype="multipart/form-data" novalidate>

    <p>
        <input type="file" name = "add-photo" novalidate="novalidate" onchange="previewFile(this);" required>
    </p>
    <img id="previewImg" src="" alt="Add photo" width="350" height="200">

    <div class="input-group-prepend">
        <div class="input-group-text"> Body VIN </div>
        <input type="text" name="input-vin" id="input-vin" class="form-control"/>
    </div>


    <div class="input-group-prepend">
        <div class="input-group-text">Select brand</div>
        <input type="text" name="input-brand" id="input-brand" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text"> Select model </div>
        <input type="text" name="input-model" id="input-model" class="form-control" />
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Year</div>
    <input type="text" name="input-year" id="input-year" class="form-control" />
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Engine</div>
        <input type="text" name="input-engine" id="input-engine" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Engine capacity</div>
        <input type="text" name="input-engine-capacity" id="input-engine-capacity" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Engine Power</div>
        <input type="text" name="input-engine-power" id="input-engine-power" class="form-control"/>
    </div>


    <div class="input-group-prepend">
        <div class="input-group-text">Transmission</div>
        <input type="text" name="input-transmission" id="input-transmission" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Mileage</div>
    <input type="text" name="input-mileage" id="input-mileage" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Select color</div>
        <input id="color-picker" name="color-picker" type="color" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Price</div>
    <input type="text" name="input-price" id="input-price" class="form-control"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Description</div>
        <textarea id="input-description" name="car_desc" rows="4" cols="50" placeholder="Enter Text Here" class="form-control"></textarea>
    </div>
    <button type="submit" class="btn-primary mb-2">Add car</button>
    <button type="reset" class="btn-primary mb-2">Reset</button>

</form>

</body>



</html>
