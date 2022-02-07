import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

package 25_01_2019_ exam;

@WebServlet(name = "Informazioni", value = "/Informazioni")
public class Informazioni extends HTTPServlet {
    
    public DAO dao;

    public void init() {
        dao = new DAO("jdbc:mysql://localhost:3306/InfoTreni", "admin", "password");
    }

    public void doGet(HTTPServletRequest req, HTTPServletResponse resp)
        throws IOException {
        
        String station = req.getParameter("stazione");
        PrintWriter out = resp.getPrintWriter();

        out.print("<html><body>");
        ResultSet res = dao.getData(station);
        if (res.isEmpty()){
            out.print("<p>ERROR<p>");
        }
        else {
            while(res.next()){
                out.print("<p>" + res.getString("idTreno") + ", "  + 
                    res.getString("ritardoPrevisto") + "</p>");

            }
        }
        out.print("</html></body>");
        out.flush();
        out.close();
    }

}

class DAO {

    private Connection conn;
    private String url;
    private String user;
    private String password;

    public DAO(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;

        registerDriver();
    }

    private void registerDriver() {
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getData(String param){
        try{
            conn = DriverManager.getConnection(url, user, password);

            Statement st = conn.createStatement();
            
            return st.executeStatement(
                "select * from passaggi s join situazione-treni t " + 
                "on s.idtreno = t.idTreno where stazione = " + param
            );
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

}