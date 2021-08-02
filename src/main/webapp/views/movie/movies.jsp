<%--
  Created by IntelliJ IDEA.
  User: Araceli
  Date: 30/07/2021
  Time: 09:51 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Listado de Peliculas</title>
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
</head>
<body>
<a href="${context}/views/movie/register.jsp" class="btn btn-success"><i class="fas fa-plus"></i> Agregar pelicula</a>
<table class="table table-info table-striped border-">
    <thead class="table-danger">
    <tr>
        <th>No.</th>
        <th>Nombre</th>
        <th>Descripcion</th>
        <th>Fecha de Estreno</th>
        <th>Recaudacion $</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ listMovies }" var="movie" varStatus="status">
        <tr>
            <td>${ status.count }</td>
            <td>${ movie.name }</td>
            <td>${ movie.description }</td>
            <td>${ movie.releaseDate }</td>
            <td>${ movie.takings }</td>
            <td>
                <c:if test="${ movie.status == 1 }">
                    <span class="badge rounded-pill bg-success">Activo</span>
                </c:if>
                <c:if test="${ movie.status == 0 }">
                    <span class="badge rounded-pill bg-danger">Inactivo</span>
                </c:if>
            </td>
            <td>
                <c:if test="${ movie.status == 1 }">
                    <form action="${context}/getUserById" method="POST" style="display: inline;">
                        <input type="hidden" name="action" value="getUserById">
                        <input type="hidden" name="id" value="${ movie.id }">
                        <button type="submit" class="btn btn-primary"><i class="fas fa-edit"></i> Modificar</button>
                    </form>
                    <button id="btn-delete-${ status.count }" data-code="${ movie.id }" data-text="${ movie.name } " type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete"><i class="fas fa-trash"></i> Eliminar</button>
                </c:if>
                <c:if test="${ movie.status == 0 }">
                    <button id="btn-details-${ status.count }"  data-text=" Nombre: ${ movie.name } . Descripcion: ${ movie.description } . Fecha de Estreno: ${ movie.releaseDate } . Recaudacion $ ${ movie.takings } " type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#details"><i class="fas fa-info-circle"></i> Detalles</button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%-- MODAL --%>
<div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${context}/deleteMovie" method="POST">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" id="id">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Eliminar usuario</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <label>¿Seguro de eliminar esta Pelicula?</label>
                    <h5 id="text-delete"></h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cerrar</button>
                    <button type="submit" class="btn btn-danger"><i class="fas fa-trash"></i> Eliminar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="details" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${context}/readMovies" method="POST">
                <input type="hidden" name="action" value="details">
                <input type="hidden" name="id" id="id2">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">Detalles de la Pelicula</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h5 id="text-details"></h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cerrar</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>
