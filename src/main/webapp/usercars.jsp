<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/switch.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<ul class="nav">
    <li>
        <a href="<%=request.getContextPath()%>/addcar.jsp">AddCar</a>
    </li>

    <li>
        <div class="card-header">
            <% if (request.getSession().getAttribute("user") == null) { %>
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
            <% } else { %>
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <%=request.getSession().getAttribute("user")%> | Выйти</a>
            <% } %>
        </div>
    </li>

</ul>

<table id="cars-tbl" name = "cars-tbl">
    <thead>
    <tr>
        <td> Description </td>
        <td> Photo </td>
        <td> Added Date </td>
        <td> Status </td>
        <td> Edit </td>
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
        $.ajax({
            url: 'http://localhost:8080/drom/getCarsByUserId.do',
            type: "GET",
            dataType: "json",
            success: function(data) {
                console.log("Start success function")
                let jsonobj = JSON.parse(JSON.stringify(data));
                console.log(jsonobj)
                $.each(data, function(index) {
                        let car = {
                            cuid:jsonobj[index].id,
                            date:jsonobj[index].date,
                            model:jsonobj[index].model,
                            brand:jsonobj[index].brand,
                            price:jsonobj[index].price,
                            year:jsonobj[index].year,
                            user:jsonobj[index].user,
                            imagePath:jsonobj[index].imagePath,
                            status:jsonobj[index].status
                        };

                        let row =
                            "<tr>" +
                            "<td>" +
                            car.brand + " " + car.price+ " " + car.year + " " + car.model + " " + car.user.name + " " + car.user.phone +
                            "</td>" +
                            "<td>" + "<img src="+ car.imagePath+">" +"</td>"+
                            "<td>" + "<span>" + car.date  + "</span>" +"</td>"+
                            "<td>" +
                            "<section>" +
                                "<div class='switch'>" +
                                    "<input type='checkbox' id='status-switch:" + car.cuid + "' name = 'status-switch' onchange = handleCheckbox(this) class='checkbx' checked = '" + car.status + "' >" +
                                    "<span class='selection'></span>" +
                                    "<label for='status-switch:" + car.cuid + "'>SOLD</label>" +
                                    "<label for='status-switch:" + car.cuid + "'>Active</label>" +
                               "</div>" +
                            "</section>" +
                            "</td>"+
                            "<td>" +
                            "<a href='<%=request.getContextPath()%>/editcar.jsp?id="+ car.cuid + "' + >" + "Edit ad" +
                            "</td>" +
                            "</tr>";
                        $("#cars-tbl tbody").append(row);

                        car.status === false ? $("#status-switch:" + car.cuid).prop("checked", false) : '';
                    }
                );
            }
        });
    })

</script>

<script>
    function handleCheckbox(checkbx) {
        let id = checkbx.getAttribute("id");
        let checked = checkbx.checked;
        let data = "" + id + " " + checked;
        console.log(checked);
        $.ajax({
            url: 'http://localhost:8080/drom/updateSoldStatus.do',
            type: "POST",
            dataType: "text",
            data: data,
            success: function(data){
                console.log(data)
            }
        });
    }
</script>

</html>
