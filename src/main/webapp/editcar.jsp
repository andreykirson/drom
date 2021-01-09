<%@ page import="model.CarsUsers" %>
<%@ page import="store.Store" %>
<%@ page import="store.HbmStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/nav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/switch.css"/>
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

<%
    String id = request.getParameter("id");
    CarsUsers carsUsers = new CarsUsers();
    if (id != null && !id.equals("null")) {
        Store store = HbmStore.getInstance();
        carsUsers = store.findCarsUsersById(Integer.valueOf(id));
    }
%>

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


<form action="<%=request.getContextPath()%>/updateCarUser.do" method="post" enctype="multipart/form-data">

    <p>
        <input type="file" name = "add-photo" onchange="previewFile(this);" required>
    </p>


    <img id="previewImg" src=<%=request.getContextPath()%>/download?name=<%=carsUsers.getCar().getImagePath()%> alt="Add photo" width="150" height="150">

    <input type="text" hidden name="carUserId" id="carUserId" value="<%=carsUsers.getId()%>" />

    <div class="input-group-prepend">
        <div class="input-group-text"> Body VIN </div>
        <input type="text" name="input-vin" id="input-vin" class="form-control" value="<%=carsUsers.getCar().getVin()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Select brand</div>
        <input type="text" name="input-brand" id="input-brand" class="form-control" value="<%=carsUsers.getCar().getModel().getBrand().getName()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text"> Select model </div>
        <input type="text" name="input-model" id="input-model" class="form-control" value="<%=carsUsers.getCar().getModel().getName()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Year</div>
        <input type="text" name="input-year" id="input-year" class="form-control" value="<%=carsUsers.getCar().getYear()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Engine</div>
        <input type="text" name="input-engine" id="input-engine" class="form-control" value="<%=carsUsers.getCar().getEngine().getEngineType()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Engine capacity</div>
        <input type="text" name="input-engine-capacity" id="input-engine-capacity" class="form-control" value="<%=carsUsers.getCar().getEngine().getCapacity()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Engine Power</div>
        <input type="text" name="input-engine-power" id="input-engine-power" class="form-control" value="<%=carsUsers.getCar().getEngine().getPower()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Transmission</div>
        <input type="text" name="input-transmission" id="input-transmission" class="form-control" value="<%=carsUsers.getCar().getTransmission().getTransmissionType()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Mileage</div>
        <input type="text" name="input-mileage" id="input-mileage" class="form-control" value="<%=carsUsers.getCar().getMileage()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Select color</div>
        <input id="color-picker" name="color-picker" type="color" class="form-control" value="<%=carsUsers.getCar().getColor()%>" onchange="color()"/>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Price</div>
        <input type="text" name="input-price" id="input-price" class="form-control" value="<%=carsUsers.getCar().getPrice()%>"/>
    </div>

    <div class="input-group-prepend">
        <div class="switch" class="form-control">
            <input type="checkbox" id="status-switch" name = "status-switch" onchange = handleCheckbox(this) class='checkbx' value = "<%=carsUsers.getSoldStatus()%>" >
            <span class="selection"></span>
            <label for="status-switch">SOLD</label>
            <label for="status-switch">Active</label>
        </div>
    </div>

    <div class="input-group-prepend">
        <div class="input-group-text">Description</div>
        <textarea id="input-description" name="car_desc" rows="4" cols="50" placeholder="Enter Text Here" class="form-control"><%=carsUsers.getCar().getDescription()%></textarea>
    </div>

    <button type="submit">Update add</button>
    <button type="button" id = "<%=carsUsers.getId()%>" onClick="fn_click(this.id)" class="delete-btn">Delete add</button>

</form>

</body>

<script>
    function fn_click(id) {
        let data = id;
        console.log("Delete :" + data);
        $.ajax({
            url: 'http://localhost:8080/drom/deleteCarUser.do',
            type: "POST",
            dataType: "text",
            data: data,
            success: window.location = "http://localhost:8080/drom/usercars.jsp"
        });
    }

    $(document).ready(function () {
        document.getElementById("status-switch").checked = <%=carsUsers.getSoldStatus()%>;
    })

</script>

</html>
