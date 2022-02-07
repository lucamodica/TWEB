
@WebServlet(name = "GetDati", value = "/GetDati")
public class GetDati extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        req.setAttribute("result", "CIAO");
    }

}
