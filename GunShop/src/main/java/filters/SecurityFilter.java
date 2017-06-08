package filters;

import com.hegel.web.HttpFilter;
import model.Person;
import service.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static listeners.DaoProvider.SECURITY_SERVICE;

@WebFilter("/*")
public class SecurityFilter implements HttpFilter {

    private static final String PERSON = "person";
    public static final String REQUESTED_URL = "requestedUrl";
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        securityService = (SecurityService) filterConfig.getServletContext().getAttribute(SECURITY_SERVICE);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpSession session = request.getSession(true);
        if (Optional.ofNullable(session.getAttribute(PERSON)).isPresent()) {
            chain.doFilter(request, response);
        } else {
            final Optional<Person> personOptional = Optional.ofNullable(request.getParameter("j_username"))
                    .flatMap(userName -> Optional.ofNullable(request.getParameter("j_password"))
                            .flatMap(password -> securityService.checkAndGetPerson(userName, password))
                    );
            if (personOptional.isPresent()) {
                session.setAttribute(PERSON, personOptional.get());
                chain.doFilter(request, response);
            } else {
                request.setAttribute(REQUESTED_URL, request.getRequestURI());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
}
