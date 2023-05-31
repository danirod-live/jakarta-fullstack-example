<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de alumnos</title>
    </head>
    <body>
        <p>
            <a href="${pageContext.request.contextPath}/alumnos?accion=nuevo">
                Nuevo alumno
            </a>
        </p>
        <c:if test="${empty alumnos}">
            <p>No hay alumnos en el sistema.</p>
        </c:if>
        <c:if test="${not empty alumnos}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Fecha de nacimiento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${alumnos}" var="alumno">
                        <tr>
                            <td>${alumno.id}</td>
                            <td>${alumno.nombre}</td>
                            <td>${alumno.apellidos}</td>
                            <td>${alumno.fechaNacimiento}</td>
                            <td><a href="${pageContext.request.contextPath}/alumnos/?accion=editar&id=${fn:escapeXml(alumno.id)}">Editar</a></td>
                            <td><a href="${pageContext.request.contextPath}/alumnos/?accion=eliminar&id=${fn:escapeXml(alumno.id)}">Eliminar</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
