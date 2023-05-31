<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de asignaturas</title>
    </head>
    <body>
        <p>
            <a href="${pageContext.request.contextPath}/asignaturas?accion=nuevo">
                Nueva asignatura
            </a>
        </p>
        <c:if test="${empty asignaturas}">
            <p>No hay asignaturas en el sistema.</p>
        </c:if>
        <c:if test="${not empty asignaturas}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Profesor que la imparte</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${asignaturas}" var="asignatura">
                        <tr>
                            <td>${asignatura.id}</td>
                            <td>${asignatura.nombre}</td>
                            <td>${asignatura.profesor.nombre} ${asignatura.profesor.apellidos}</td>
                            <td><a href="${pageContext.request.contextPath}/asignaturas/?accion=editar&id=${fn:escapeXml(asignatura.id)}">Editar</a></td>
                            <td><a href="${pageContext.request.contextPath}/asignaturas/?accion=eliminar&id=${fn:escapeXml(asignatura.id)}">Eliminar</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
