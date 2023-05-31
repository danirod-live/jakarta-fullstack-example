<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de profesores</title>
    </head>
    <body>
        <p>
            <a href="${pageContext.request.contextPath}/profesores?accion=nuevo">
                Nuevo profesor
            </a>
        </p>
        <c:if test="${empty profesores}">
            <p>No hay profesores en el sistema.</p>
        </c:if>
        <c:if test="${not empty profesores}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${profesores}" var="profesor">
                        <tr>
                            <td>${profesor.id}</td>
                            <td>${profesor.nombre}</td>
                            <td>${profesor.apellidos}</td>
                            <td><a href="${pageContext.request.contextPath}/profesores/?accion=editar&id=${fn:escapeXml(profesor.id)}">Editar</a></td>
                            <td><a href="${pageContext.request.contextPath}/profesores/?accion=eliminar&id=${fn:escapeXml(profesor.id)}">Eliminar</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
