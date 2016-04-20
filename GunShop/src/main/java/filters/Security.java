package filters;

import com.epam.courses.jf.servlets.common.HttpFilter;
import model.Person;
import service.PersonService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static listeners.DaoProvider.PERSON_SERVICE;

@WebFilter("/*")
public class Security implements HttpFilter {

    public static final String PERSON = "Person";
    private static PersonService personService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        personService = (PersonService) filterConfig.getServletContext().getAttribute(PERSON_SERVICE);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        final HttpSession session = request.getSession(true);

        if (ofNullable(session.getAttribute(PERSON)).isPresent())
            chain.doFilter(request, response);
        else {
            final Optional<Person> personOptional = ofNullable(request.getParameter("j_username"))
                    .flatMap(login -> ofNullable(request.getParameter("j_password"))
                            .map(password -> new Person(login, password)));

            if (personOptional.isPresent() && personService.checkPwd(personOptional.get())) {
                session.setAttribute(PERSON, personOptional.get());
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/login.html").forward(request, response);
            }
        }


    }
}
