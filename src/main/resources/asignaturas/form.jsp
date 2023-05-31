<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de asignatura</title>
    </head>
    <body>
        <p>
            <a href="${pageContext.request.contextPath}/asignaturas">
                Atrás
            </a>
        </p>
        
        <form method="POST" action="${pageContext.request.contextPath}/asignaturas/save">
            <input type="hidden" name="id" value="${asignatura.id}" />
            <input type="hidden" name="action" value="${action}" />
            <p>
                Nombre de la asignatura:
                <input type="text" name="nombre" value="${asignatura.nombre}" />
            </p>
            <p>
                Profesor que la imparte:
                <select name="profesor_id">
                    <c:forEach items="${profesores}" var="profesor">
                        <option value="${profesor.id}">${profesor.nombre} ${profesor.apellidos}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <button type="submit">Enviar</button>
            </p>
        </form>
    </body>
</html>
