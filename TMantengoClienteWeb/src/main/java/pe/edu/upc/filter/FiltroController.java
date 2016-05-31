package pe.edu.upc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FiltroController", urlPatterns = {"/*"})
public class FiltroController implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(":::INICIO DEL FILTRO:::");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(":::METODO doFilter:::");
        boolean accesoPermitido = true;
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest requestTemp = (HttpServletRequest) request;
            HttpServletResponse responseTemp = (HttpServletResponse) response;
            HttpSession session = requestTemp.getSession(false);
            String paginaVisitada = requestTemp.getRequestURI();
            if (paginaVisitada.contains(".jsp")) {
                if (paginaVisitada.contains("iniciarSesion.jsp")) {
                    accesoPermitido = true;
                    if (session != null && session.getAttribute("sesionUsuario") != null) {
                        session.removeAttribute("sesionUsuario");
                        session.invalidate();
                    }
                } else {
                    if (session != null && session.getAttribute("sesionUsuario") != null) {
                        accesoPermitido = true;
                    } else {
                        accesoPermitido = false;
                    }
                }
            }
            if (!accesoPermitido) {
                responseTemp.sendRedirect("iniciarSesion.jsp");
                return;
            }
        }
        if (accesoPermitido) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("SE ELIMINO EL FILTRO");
    }
}