package com.example.model.movie;
import com.example.service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DaoMovie {
    Connection con;
    CallableStatement cstm;
    ResultSet rs;
    Logger logger = LoggerFactory.getLogger(DaoMovie.class);

    public List<BeanMovie> findAll() {
        List<BeanMovie> listMovies = new ArrayList<>();
        try {
            // SELECT * FROM users AS U INNER JOIN persons AS P ON U.idPerson = P.id INNER JOIN roles AS R ON U.idRole = R.id;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findAll}");
            rs = cstm.executeQuery();

            while (rs.next()) {
                BeanMovie movie = new BeanMovie();

                movie.setId(rs.getInt("idMovie"));
                movie.setName(rs.getString("name"));
                movie.setDescription(rs.getString("description"));
                movie.setReleaseDate(rs.getDate("releaseDate"));
                movie.setTakings(rs.getInt("takings"));
                movie.setStatus(rs.getInt("status"));

                listMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listMovies;
    }

    public BeanMovie findById(int id) {
        BeanMovie movie = null;
        try {
            // SELECT * FROM users AS U INNER JOIN persons AS P ON U.idPerson = P.id INNER JOIN roles AS R ON U.idRole = R.id;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM movie WHERE idMovie = ?");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if (rs.next()) {
                movie = new BeanMovie();

                movie.setId(rs.getInt("idMovie"));
                movie.setName(rs.getString("name"));
                movie.setDescription(rs.getString("description"));
                movie.setReleaseDate(rs.getDate("releaseDate"));
                movie.setTakings(rs.getInt("takings"));
                movie.setStatus(rs.getInt("status"));

            }
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return movie;
    }

    public boolean create(BeanMovie movie) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call create_movie(?,?,?,?)}");
            cstm.setString(1, movie.getName());
            cstm.setString(2, movie.getDescription());
            cstm.setDate(3, movie.getReleaseDate());
            cstm.setInt(4, movie.getTakings());
            cstm.execute();
            flag = true;
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanMovie movie) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call update_movie(?,?,?,?,?)}");
            cstm.setInt(1, movie.getId());
            cstm.setString(2, movie.getName());
            cstm.setString(3, movie.getDescription());
            cstm.setDate(4, movie.getReleaseDate());
            cstm.setInt(5, movie.getTakings());

            flag = cstm.execute();
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(int id) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call delete_movie(?)}");
            cstm.setLong(1, id);

            flag = cstm.execute();
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public static void main(String[] args) {
        BeanMovie beanMovie = new BeanMovie();
        DaoMovie daoMovie = new DaoMovie();

        // Listando usuarios
        List<BeanMovie> listMovies = new ArrayList<>();
        listMovies = daoMovie.findAll();
        for (int i = 0; i < listMovies.size(); i++) {
            System.out.println(listMovies.get(i).getName());
        }
        boolean registed = false;
        beanMovie.setName("Cruella");
        beanMovie.setDescription("Pelicula disney");
        beanMovie.setReleaseDate(Date.valueOf("1980-04-09"));
        beanMovie.setTakings(2000);
        registed = daoMovie.create(beanMovie);
        System.out.println("Se ha registrado correctamente");

        boolean flag = false;
        flag = daoMovie.delete(1);
        System.out.println("Se realizó correctamente");

    }
}
/*
        // Registrando usuarios
        boolean registed = false;
        beanMovie.setName("Cruella");
        beanMovie.setDescription("Pelicula disney");
        beanMovie.setReleaseDate("2021-10-10");
        beanMovie.setTakings(2000)
        registed = daoMovie.create(beanMovie);
        System.out.println("Se ha registrado correctamente");

*/
        // Eliminar de manera "baja lógica"
/*
        boolean flag = false;
        flag = daoUser.delete(4);
        System.out.println("Se realizó correctamente");


    }
}*/
