
@WebServlet(name = "Info", value = "/Info")
public class Servlet extends HTTPServlet {

    public void doGet(HTTPServletRequest req, HTTPServletResponse resp)
            throws IOExcpetion, ServletException {

        String op = req.getAttribute("operazione");
        ServletContext ctx = getServletContext();

        if (op.equals("dati")) {
            ctx.getRequestDispatcher("showData.jsp").forward(req, resp);
        } else if (op.equals("exit")) {
            String user = req.getSession().getParameter("name");

            req.getSession().invalidate();
            ctx.getRequestDispatcher("quit.jsp").forward(req, resp);
        } else {
            ctx.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
