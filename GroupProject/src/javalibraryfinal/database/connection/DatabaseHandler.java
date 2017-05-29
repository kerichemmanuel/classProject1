
package javalibraryfinal.database.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DatabaseHandler {
    
        private static DatabaseHandler handler;
        private static final String CONN_STRING = "jdbc:mysql://127.0.0.1:3306/lib-store";
	private static final String USERNAME = "kerich";
	private static final String PASSWORD = "";
        private static Connection conn = null;
        private static Statement stmt = null;

	public DatabaseHandler(){
            CreateConnection();
        }
	
	public void  CreateConnection(){
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
               conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
               
                   
            } catch (SQLException e) {
               System.err.println(e.getMessage());
                    
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        public ResultSet execQuery(String Query){
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(Query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }finally{
            
        }
        return result;
    } 
         
        public boolean execAction(String qu){        
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error:" + ex.getMessage(),"Error occured",JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }finally{   
        }
   }
}

        
       
    
   
      