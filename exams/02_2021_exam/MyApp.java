import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyApp", value = "/MyApp")
public class MyApp extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String action = req.getParameter("richiesta");
        ServletContext ctx = getServletContext();

        if (action.equals("query")) {
            ctx.getNamedDispatcher("GetDati").include(req, resp);

            PrintWriter out = resp.getWriter();
            out.println((String) req.getAttribute("result"));
            out.close();
        } else if (action.equals("quit")) {
            ctx.getRequestDispatcher("/bye.html").forward(req, resp);
        } else {
            ctx.getRequestDispatcher("/error.html").forward(req, resp);
        }
    }
}