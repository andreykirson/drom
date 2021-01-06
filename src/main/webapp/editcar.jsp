<%@ page import="model.CarsUsers" %>
<%@ page import="store.Store" %>
<%@ page import="store.HbmStore" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
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

<div class="card-header">
    <% if (request.getSession().getAttribute("user") == null) { %>
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
    </li>
    <% } else { %>
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> User:  <%=request.getSession().getAttribute("user")%> | Выйти</a>
    </li>
    <% } %>
</div>


<form action="<%=request.getContextPath()%>/updateCarUser.do" method="post" enctype="multipart/form-data">

    <p>
        <input type="file" name = "add-photo" onchange="previewFile(this);" required>
    </p>
    <img id="previewImg" src="<%=carsUsers.getCar().getImagePath()%>" alt="Add photo" width="150" height="150">

    <input type="text" hidden name="carUserId" id="carUserId" value="<%=carsUsers.getId()%>" />

    <div class="input">
        <label for="input-vin">
            <span>Body VIN</span>
        </label>
        <input type="text" name="input-vin" id="input-vin" value="<%=carsUsers.getCar().getVin()%>" />
    </div>

    <div class="input">
        <label for="input-brand">
            <span>Select brand</span>
        </label>
        <input type="text" name="input-brand" id="input-brand" value="<%=carsUsers.getCar().getModel().getBrand().getName()%>"/>
    </div>

    <div class="input">
        <label for="input-model">
            <span>Select model</span>
        </label>
        <input type="text" name="input-model" id="input-model" value="<%=carsUsers.getCar().getModel().getName()%>" />
    </div>

    <div class="input">
        <label for="input-year">
            <span>Year</span>
        </label>
        <input type="text" name="input-year" id="input-year" value="<%=carsUsers.getCar().getYear()%>" />
    </div>

    <div class="input">
        <label for="input-engine">
            <span>Engine</span>
        </label>
        <input type="text" name="input-engine" id="input-engine" value="<%=carsUsers.getCar().getEngine().getEngineType()%>"/>
    </div>

    <div class="input">
        <label for="input-engine-capacity">
            <span>Engine capacity</span>
        </label>
        <input type="text" name="input-engine-capacity" id="input-engine-capacity" value="<%=carsUsers.getCar().getEngine().getPower()%>" />
    </div>

    <div class="input">
        <label for="input-engine-power">
            <span>Engine Power</span>
        </label>
        <input type="text" name="input-engine-power" id="input-engine-power" value="<%=carsUsers.getCar().getEngine().getPower()%>"/>
    </div>


    <div class="input">
        <label for="input-transmission">
            <span>Transmission</span>
        </label>
        <input type="text" name="input-transmission" id="input-transmission" value="<%=carsUsers.getCar().getTransmission().getTransmissionType()%>"/>
    </div>

    <div class="input">
        <label for="input-mileage">
            <span>Mileage</span>
        </label>
        <input type="text" name="input-mileage" id="input-mileage" value="<%=carsUsers.getCar().getMileage()%>"/>
    </div>

    <div class="input">
        <label for="color-picker">
            <span>Select color</span>
        </label>
        <input id="color-picker" name="color-picker" type="color" value="<%=carsUsers.getCar().getColor()%>" onchange="color()">
    </div>

    <div class="input">
        <label for="input-price">
            <span>Price</span>
        </label>
        <input type="text" name="input-price" id="input-price" value="<%=carsUsers.getCar().getPrice()%>"/>
    </div>

    <section>
        <div class="switch">
            <input type="checkbox" id="status-switch" name = "status-switch" onchange = handleCheckbox(this) class='checkbx' checked = "<%=carsUsers.getSoldStatus()%>" >
            <span class="selection"></span>
            <label for="status-switch">SOLD</label>
            <label for="status-switch">Active</label>
        </div>
    </section>


    <div class="input">
        <label for="input-description">
            <span>Description</span>
        </label>
        <textarea id="input-description" name="car_desc" rows="4" cols="50" placeholder="Enter Text Here" ><%=carsUsers.getCar().getDescription()%></textarea>
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
</script>

</html>
