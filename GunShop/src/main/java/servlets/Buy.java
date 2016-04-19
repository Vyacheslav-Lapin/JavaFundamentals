package servlets;

import dao.interfaces.GunDao;
import model.Gun;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static listeners.DaoProvider.GUN_DAO;

@WebServlet("/buy")
public class Buy extends HttpServlet{

    private GunDao gunDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        gunDao = (GunDao) config.getServletContext().getAttribute(GUN_DAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int id = parseInt(request.getParameter("id"));
        final Gun gun = gunDao.getGunById(id).orElseThrow(RuntimeException::new);

        request.setAttribute("gun", gun);

        request.getRequestDispatcher("/buy/index.jsp").forward(request, response);
    }
}
