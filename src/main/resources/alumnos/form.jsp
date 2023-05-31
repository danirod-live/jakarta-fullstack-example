<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de alumno</title>
    </head>
    <body>
        <p>
            <a href="${pageContext.request.contextPath}/alumnos">
                Atrás
            </a>
        </p>
        
        <form method="POST" action="${pageContext.request.contextPath}/alumnos/save">
            <input type="hidden" name="id" value="${alumno.id}" />
            <input type="hidden" name="action" value="${action}" />
            <p>
                Nombre:
                <input type="text" name="nombre" value="${alumno.nombre}" />
            </p>
            <p>
                Apellidos:
                <input type="text" name="apellidos" value="${alumno.apellidos}" />
            </p>
            <p>
                Fecha de nacimiento:
                <input type="date" name="fechanac" value="${alumno.fechaNacimiento}" />
            </p>
            <p>
                <button type="submit">Enviar</button>
            </p>
        </form>
    </body>
</html>
