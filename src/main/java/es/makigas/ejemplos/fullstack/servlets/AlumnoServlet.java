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

@RequestScoped
@WebServlet(name = "alumnos", urlPatterns = {"/alumnos/"})
public class AlumnoServlet extends HttpServlet {
    
    @Inject
    AlumnoService alumnos;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = Optional.ofNullable(req.getParameter("accion")).orElse("listar");
        switch (accion) {
            case "nuevo":
                Alumno alumno = new Alumno();
                req.setAttribute("alumno", alumno);
                req.setAttribute("action", "new");
                getServletContext().getRequestDispatcher("/alumnos/form.jsp").forward(req, resp);
                break;
            case "editar":
                Optional<Alumno> alu = params.getParameter(req, "id").flatMap(alumnos::get);
                if (alu.isPresent()) {
                    req.setAttribute("alumno", alu.get());
                    req.setAttribute("action", "edit");
                    getServletContext().getRequestDispatcher("/alumnos/form.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "El alumno no existe en el sistema");
                    getServletContext().getRequestDispatcher("/alumnos/error.jsp").forward(req, resp);
                }
                break;
            case "eliminar":
                Optional<Alumno> maybeAlu = params.getParameter(req, "id").flatMap(alumnos::get);
                if (maybeAlu.isPresent()) {
                    req.setAttribute("alumno", maybeAlu.get());
                    getServletContext().getRequestDispatcher("/alumnos/delete.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "ID no válido");
                    getServletContext().getRequestDispatcher("/alumnos/error.jsp").forward(req, resp);
                }
                break;
            default:
                int page = params.getPositiveParameter(req, "page").map(Long::intValue).orElse(1);
                var data = alumnos.list().page(page).limit(10).get();
                req.setAttribute("alumnos", data);
                getServletContext().getRequestDispatcher("/alumnos/list.jsp").forward(req, resp);
                break;
        }
    }
    
}
