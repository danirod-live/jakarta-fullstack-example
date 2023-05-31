package es.makigas.ejemplos.fullstack.servlets;

import es.makigas.ejemplos.fullstack.models.Alumno;
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
import java.time.LocalDate;
import java.util.Optional;

@RequestScoped
@WebServlet(name = "profesores_guardar", urlPatterns = {"/profesores/save"})
public class SaveProfesorServlet extends HttpServlet {

    @Inject
    ProfesorService profesores;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Profesor profesor;
        if (action.equals("new")) {
            profesor = new Profesor();
            fill(profesor, req);
            profesores.insert(profesor);
        } else {
            Optional<Profesor> maybeProfesor = params
                    .getParameter(req, "id")
                    .map(Long::intValue)
                    .flatMap(profesores::get);
            if (maybeProfesor.isEmpty()) {
                req.setAttribute("error", "El profesor no existe en el sistema");
                getServletContext().getRequestDispatcher("/profesores/error.jsp").forward(req, resp);
                return;
            }
            profesor = maybeProfesor.get();
            fill(profesor, req);
            profesores.update(profesor);
        }
        
        resp.sendRedirect(req.getContextPath() + "/profesores/");
    }

    private void fill(Profesor prof, HttpServletRequest req) {
        prof.setNombre(req.getParameter("nombre"));
        prof.setApellidos(req.getParameter("apellidos"));
    }
    
}
