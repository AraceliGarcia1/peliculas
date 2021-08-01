package com.example.controller;

import com.example.model.movie.BeanMovie;
import com.example.model.movie.DaoMovie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ServletMovie", urlPatterns = {"/readMovies", "/createMovie", "/getUserById", "/updateMovie", "/deleteMovie"})
public class ServletMovie extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ServletMovie.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listMovies", new DaoMovie().findAll());
        request.getRequestDispatcher("/views/movie/movies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                // do something
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String description = request.getParameter("description");
                Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
                int takings = Integer.parseInt(request.getParameter("takings"));

                BeanMovie beanMovie = new BeanMovie(0, name, description, releaseDate, takings, 0);

                if (new DaoMovie().create(beanMovie)) {
                    request.setAttribute("message", "Pelicula registrada correctamente");
                } else {
                    request.setAttribute("message", "Pelicula no registrada");
                }
                doGet(request, response);
                break;

            case "getUserById":
                // do something
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("movie", new DaoMovie().findById(id));
                request.getRequestDispatcher("/views/movie/update.jsp").forward(request, response);
                break;

            case "update":
                // do something
                int id1 = Integer.parseInt(request.getParameter("id"));
                String name1 = request.getParameter("name") != null ? request.getParameter("name") : "";
                String description1 = request.getParameter("description");
                Date releaseDate1 = Date.valueOf(request.getParameter("releaseDate"));
                int takings1 = Integer.parseInt(request.getParameter("takings"));

                BeanMovie beanMovie1 = new BeanMovie(id1, name1, description1, releaseDate1, takings1, 0);

                if (new DaoMovie().update(beanMovie1)) {
                    request.setAttribute("message", "Pelicula modificada correctamente");
                } else {
                    request.setAttribute("message", "Pelicula no modificada");
                }
                doGet(request, response);
                break;
            case "delete":
                // do something
                int id2 = Integer.parseInt(request.getParameter("id"));
                if (new DaoMovie().delete(id2)) {
                    request.setAttribute("message", "Pelicula eliminada correctamente");
                } else {
                    request.setAttribute("message", "Pelicula no eliminada");
                }
                doGet(request, response);
                break;
            default:
                // no supported
                break;
        }
    }
}
