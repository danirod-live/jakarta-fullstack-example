/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.makigas.ejemplos.fullstack.servlets;

import es.makigas.ejemplos.fullstack.models.Alumno;
import es.makigas.ejemplos.fullstack.services.AlumnoService;
import es.makigas.ejemplos.fullstack.services.ParameterService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 *
 * @author danirod
 */
@RequestScoped
@WebServlet(name = "alumnos_delete", urlPatterns = {"/alumnos/delete"})
public class DeleteAlumnoServlet extends HttpServlet {

    @Inject
    AlumnoService alumnos;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Alumno> maybeAlumno = params
                    .getParameter(req, "id")
                    .map(Long::intValue)
                    .flatMap(alumnos::get);
        if (maybeAlumno.isPresent()) {
            alumnos.delete(maybeAlumno.get());
            resp.sendRedirect(req.getContextPath() + "/alumnos/");
        } else {
            req.setAttribute("error", "ID no válido");
            getServletContext().getRequestDispatcher("/alumnos/error.jsp").forward(req, resp);
        }
    }    
}
