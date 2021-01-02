<%--
  Created by IntelliJ IDEA.
  User: fruit
  Date: 12/27/2020
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src=https://code.jquery.com/jquery-3.1.1.min.js ></script>
    <script src="http://momentjs.com/downloads/moment.js"></script>
</head>
<body>

<ul class="nav">
    <li>
        <a class="active" href="#home">Home</a>
    </li>

    <li>
        <a href="<%=request.getContextPath()%>/addcar.jsp">AddCar</a>
    </li>

    <li>
        <div class="card-header">
                <% if (request.getSession().getAttribute("user") == null) { %>
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
    <% } else { %>
        <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> User:  <%=request.getSession().getAttribute("user")%> | Выйти</a>
    <% } %>
    </div>
    </li>

</ul>


<table id="cars-tbl" name = "cars-tbl">
    <thead>
    <tr>
        <td> Description </td>
        <td> Photo </td>
    </tr>
    </thead>
    <tbody>
    <tr>
    </tr>
    </tbody>
</table>
</body>

<script>
    $(document).ready(function () {
        console.log("Huraaaaa!!!!!!")
        $.ajax({
            url: 'http://localhost:8080/drom/getAllCar.do',
            type: "GET",
            dataType: "json",
            success: function(data) {
                console.log("Start success function")
                let jsonobj = JSON.parse(JSON.stringify(data));
                console.log("Object : " + jsonobj);
                $.each(data, function(index) {
                        let car = {
                            model: jsonobj[index].model,
                            username: jsonobj[index].username,
                            userphone: jsonobj[index].userphone,
                            description: jsonobj[index].description,
                            price: jsonobj[index].price,
                            year: jsonobj[index].year
                        };
                    console.log("THE CAR IS" + car.year);
                        let row =
                            "<tr>" +
                            "<td>"
                            + car.description + car.price + car.year + car.model + car.username + car.userphone +
                            "</td>" +
                            "<td>"+"</td>"+
                            "</tr>";
                        $("#cars-tbl tbody").append(row);
                    }
                );
            }
        });
    })

</script>


</html>
