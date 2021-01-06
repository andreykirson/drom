<%@ page import="model.CarsUsers" %>
<%@ page import="store.Store" %>
<%@ page import="store.HbmStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
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

<form action="<%=request.getContextPath()%>/addcar.do" method="post" enctype="multipart/form-data">

    <p>
        <input type="file" name = "add-photo" onchange="previewFile(this);" required>
    </p>
    <img id="previewImg" src="" alt="Add photo" width="150" height="150">

    <div class="input">
        <label for="input-vin">
            <span>Body VIN</span>
        </label>
    <input type="text" name="input-vin" id="input-vin"  />
    </div>

    <div class="input">
        <label for="input-brand">
            <span>Select brand</span>
        </label>
        <input type="text" name="input-brand" id="input-brand" />
    </div>

    <div class="input">
        <label for="input-model">
            <span>Select model</span>
        </label>
        <input type="text" name="input-model" id="input-model"  />
    </div>

    <div class="input">
        <label for="input-year">
            <span>Year</span>
        </label>
    <input type="text" name="input-year" id="input-year"  />
    </div>

    <div class="input">
        <label for="input-engine">
            <span>Engine</span>
        </label>
        <input type="text" name="input-engine" id="input-engine" />
    </div>

    <div class="input">
        <label for="input-engine-capacity">
            <span>Engine capacity</span>
        </label>
        <input type="text" name="input-engine-capacity" id="input-engine-capacity"  />
    </div>

    <div class="input">
        <label for="input-engine-power">
            <span>Engine Power</span>
        </label>
        <input type="text" name="input-engine-power" id="input-engine-power"/>
    </div>


    <div class="input">
        <label for="input-transmission">
            <span>Transmission</span>
        </label>
        <input type="text" name="input-transmission" id="input-transmission" />
    </div>

    <div class="input">
        <label for="input-mileage">
            <span>Mileage</span>
        </label>
    <input type="text" name="input-mileage" id="input-mileage" />
    </div>

    <div class="input">
        <label for="color-picker">
            <span>Select color</span>
        </label>
        <input id="color-picker" name="color-picker" type="color" >
    </div>

    <div class="input">
        <label for="input-price">
            <span>Price</span>
        </label>
    <input type="text" name="input-price" id="input-price" />
    </div>

    <div class="input">
        <label for="input-description">
            <span>Description</span>
        </label>
        <textarea id="input-description" name="car_desc" rows="4" cols="50" placeholder="Enter Text Here" ></textarea>
    </div>
    <button type="submit">Add car</button>
    <button type="reset">Reset</button>

</form>

</body>



</html>
