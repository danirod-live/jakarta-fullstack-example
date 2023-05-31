package es.makigas.ejemplos.fullstack.servlets;

import es.makigas.ejemplos.fullstack.models.Asignatura;
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
import java.util.Optional;

@RequestScoped
@WebServlet(name = "asignaturas", urlPatterns = {"/asignaturas/"})
public class AsignaturaServlet extends HttpServlet {
    
    @Inject
    AsignaturaService asignaturas;
    
    @Inject
    ProfesorService profesores;
    
    ParameterService params = ParameterService.instance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = Optional.ofNullable(req.getParameter("accion")).orElse("listar");
        switch (accion) {
            case "nuevo":
                Asignatura asig = new Asignatura();
                req.setAttribute("asignatura", asig);
                req.setAttribute("action", "new");
                req.setAttribute("profesores", profesores.list().get());
                getServletContext().getRequestDispatcher("/asignaturas/form.jsp").forward(req, resp);
                break;
            case "editar":
                Optional<Asignatura> maybeAsig = params.getParameter(req, "id").flatMap(asignaturas::get);
                if (maybeAsig.isPresent()) {
                    req.setAttribute("asignatura", maybeAsig.get());
                    req.setAttribute("action", "edit");
                    req.setAttribute("profesores", profesores.list().get());
                    getServletContext().getRequestDispatcher("/asignaturas/form.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "La asignatura no existe en el sistema");
                    getServletContext().getRequestDispatcher("/asignaturas/error.jsp").forward(req, resp);
                }
                break;
            case "eliminar":
                Optional<Asignatura> maybeAsigDel = params.getParameter(req, "id").flatMap(asignaturas::get);
                if (maybeAsigDel.isPresent()) {
                    req.setAttribute("asignatura", maybeAsigDel.get());
                    getServletContext().getRequestDispatcher("/asignaturas/delete.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "ID no válido");
                    getServletContext().getRequestDispatcher("/asignaturas/error.jsp").forward(req, resp);
                }
                break;
            default:
                int page = params.getPositiveParameter(req, "page").map(Long::intValue).orElse(1);
                var data = asignaturas.list().page(page).limit(10).get();
                req.setAttribute("asignaturas", data);
                getServletContext().getRequestDispatcher("/asignaturas/list.jsp").forward(req, resp);
                break;
        }
    }
    
}
