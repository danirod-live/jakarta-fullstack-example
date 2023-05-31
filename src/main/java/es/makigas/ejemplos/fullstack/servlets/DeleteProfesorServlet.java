/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.makigas.ejemplos.fullstack.servlets;

import es.makigas.ejemplos.fullstack.models.Profesor;
import es.makigas.ejemplos.fullstack.services.ParameterService;
import es.makigas.ejemplos.fullstack.services.ProfesorService;
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
@WebServlet(name = "profesor_delete", urlPatterns = {"/profesores/delete"})
public class DeleteProfesorServlet extends HttpServlet {

    @Inject
    ProfesorService profesor;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Profesor> maybeProf = params
                    .getParameter(req, "id")
                    .map(Long::intValue)
                    .flatMap(profesor::get);
        if (maybeProf.isPresent()) {
            profesor.delete(maybeProf.get());
            resp.sendRedirect(req.getContextPath() + "/profesores/");
        } else {
            req.setAttribute("error", "ID no válido");
            getServletContext().getRequestDispatcher("/alumnos/error.jsp").forward(req, resp);
        }
    }    
}
