import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.http.HttpRequest;
import java.rmi.ServerException;
import java.util.ArrayList;

@WebServlet(name = "esame", value = "/esame")
public class Esame extends HttpServlet {

    private class Coppia implements Serializable {
        public String nome;
        public String cognome;

        public Coppia(String nome, String cognome) {
            this.nome = nome;
            this.cognome = cognome;
        }

        @Override
        public String toString() {
            return '<' + nome + ',' + cognome + '>';
        }
    }

    @Override
    protected void doGet(HttpRequest req, HttpResponse resp)
            throws ServerException, IOException {

        HttpSession s = req.getSession(req);
        String role = s.getAttribute("role");
        String r = req.getParameter("query");
        ServletContext ctx = getServletContext();

        if (r.equals("informazioni") && role.equals("admin")) {
            ctx.getNamedDispatcher("Query").include(req, resp);

            PrintWriter out = resp.getWriter();
            ArrayList<Coppia> arr = (ArrayList<Coppia>) req.getAttribute("couples");
            out.print(arr.toString());
        } else if (r.equals("end")) {
            ctx.getRequestDispatcher("ciao.html").forward();
        } else {
            ctx.getRequestDispatcher("errore.html").forward();
        }
    }
}