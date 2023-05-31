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
import java.time.LocalDate;
import java.util.Optional;

/**
 *
 * @author danirod
 */
@RequestScoped
@WebServlet(name = "alumnos_guardar", urlPatterns = {"/alumnos/save"})
public class SaveAlumnoServlet extends HttpServlet {

    @Inject
    AlumnoService alumnos;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Alumno alumno;
        if (action.equals("new")) {
            alumno = new Alumno();
            fill(alumno, req);
            alumnos.insert(alumno);
        } else {
            Optional<Alumno> maybeAlumno = params
                    .getParameter(req, "id")
                    .map(Long::intValue)
                    .flatMap(alumnos::get);
            if (maybeAlumno.isEmpty()) {
                req.setAttribute("error", "El alumno no existe en el sistema");
                getServletContext().getRequestDispatcher("/alumnos/error.jsp").forward(req, resp);
                return;
            }
            alumno = maybeAlumno.get();
            fill(alumno, req);
            alumnos.update(alumno);
        }
        
        resp.sendRedirect(req.getContextPath() + "/alumnos/");
    }

    private void fill(Alumno alumno, HttpServletRequest req) {
        alumno.setNombre(req.getParameter("nombre"));
        alumno.setApellidos(req.getParameter("apellidos"));
        alumno.setFechaNacimiento(LocalDate.parse(req.getParameter("fechanac")));
    }
    
}
