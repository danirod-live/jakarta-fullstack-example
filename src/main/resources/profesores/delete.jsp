<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar los datos del profesor</title>
    </head>
    <body>
        <p><a href="${pageContext.request.contextPath}/profesores">Atrás</a></p>
        
        <h1>Eliminar datos</h1>
        <blockquote>
            Nombre: ${profesor.nombre}<br>
            Apellido: ${profesor.apellidos}
        </blockquote>
        <p>Esto no tiene vuelta atrás. Tú verás.</p>
        
        <form method="POST" action="${pageContext.request.contextPath}/profesores/delete">            
            <input type="hidden" name="id" value="${profesor.id}" />
            <button type="submit">Sin miedo al éxito</button>
        </form>
    </body>
</html>
