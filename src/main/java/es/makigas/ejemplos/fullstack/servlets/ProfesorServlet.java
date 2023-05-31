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

@RequestScoped
@WebServlet(name = "profesores", urlPatterns = {"/profesores/"})
public class ProfesorServlet extends HttpServlet {
    
    @Inject
    ProfesorService profesores;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = Optional.ofNullable(req.getParameter("accion")).orElse("listar");
        switch (accion) {
            case "nuevo":
                Profesor profesor = new Profesor();
                req.setAttribute("profesor", profesor);
                req.setAttribute("action", "new");
                getServletContext().getRequestDispatcher("/profesores/form.jsp").forward(req, resp);
                break;
            case "editar":
                Optional<Profesor> prof = params.getParameter(req, "id").flatMap(profesores::get);
                if (prof.isPresent()) {
                    req.setAttribute("profesor", prof.get());
                    req.setAttribute("action", "edit");
                    getServletContext().getRequestDispatcher("/profesores/form.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "El profesor no existe en el sistema");
                    getServletContext().getRequestDispatcher("/profesores/error.jsp").forward(req, resp);
                }
                break;
            case "eliminar":
                Optional<Profesor> maybeProf = params.getParameter(req, "id").flatMap(profesores::get);
                if (maybeProf.isPresent()) {
                    req.setAttribute("profesor", maybeProf.get());
                    getServletContext().getRequestDispatcher("/profesores/delete.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "ID no válido");
                    getServletContext().getRequestDispatcher("/profesores/error.jsp").forward(req, resp);
                }
                break;
            default:
                int page = params.getPositiveParameter(req, "page").map(Long::intValue).orElse(1);
                var data = profesores.list().page(page).limit(10).get();
                req.setAttribute("profesores", data);
                getServletContext().getRequestDispatcher("/profesores/list.jsp").forward(req, resp);
                break;
        }
    }
    
}
