package es.makigas.ejemplos.fullstack.servlets;

import es.makigas.ejemplos.fullstack.models.Asignatura;
import es.makigas.ejemplos.fullstack.models.Profesor;
import es.makigas.ejemplos.fullstack.services.AsignaturaService;
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

/**
 *
 * @author danirod
 */
@RequestScoped
@WebServlet(name = "asignatura_guardar", urlPatterns = {"/asignaturas/save"})
public class SaveAsignaturaServlet extends HttpServlet {

    @Inject
    AsignaturaService asignaturas;
    
    @Inject
    ProfesorService profesores;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Asignatura asignatura;
        if (action.equals("new")) {
            asignatura = new Asignatura();
            fill(asignatura, req);
            asignaturas.insert(asignatura);
        } else {
            Optional<Asignatura> maybeAlumno = params
                    .getParameter(req, "id")
                    .map(Long::intValue)
                    .flatMap(asignaturas::get);
            if (maybeAlumno.isEmpty()) {
                req.setAttribute("error", "El alumno no existe en el sistema");
                getServletContext().getRequestDispatcher("/alumnos/error.jsp").forward(req, resp);
                return;
            }
            asignatura = maybeAlumno.get();
            fill(asignatura, req);
            asignaturas.update(asignatura);
        }
        
        resp.sendRedirect(req.getContextPath() + "/asignaturas/");
    }

    private void fill(Asignatura asignatura, HttpServletRequest req) {
        long profesorId = params.getPositiveParameter(req, "profesor_id").get();
        Optional<Profesor> profesor = profesores.get(profesorId);        
        asignatura.setNombre(req.getParameter("nombre"));
        asignatura.setProfesor(profesor.get());
    }
    
}
