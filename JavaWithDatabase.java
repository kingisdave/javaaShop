import java.sql.*;

public class JavaWithDatabase{  

	public static void main(String[] args){
		try{
            //register driver class
            Class.forName("com.mysql.jdbc.Driver");

            //establish connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

            //execute queries
            Statement st = conn.createStatement();
            // ResultSet rs = st.executeQuery("select full_name, user_name, email, password from students");
            
            // To do the inserting of data
            // st.executeUpdate("insert into students(full_name, user_name, email, password) values('java first','jfirst','jfirst@mail.com','jfirst')");
            
            // To do the updating of data
            // st.executeUpdate("update students set full_name='updated java' where user_name in('jfirst')");
            String query = "insert into students(full_name, user_name, email, password) values(?,?,?,?)";

            PreparedStatement myData = conn.prepareStatement(query);
            myData.setString(1, "java New");
            myData.setString(2, "jNew");
            myData.setString(3, "jNew@mail.com");
            myData.setString(4, "jnew");

            myData.execute();
            // To do the deleting of data
            // st.executeUpdate("delete from students where user_name in('jfirst')");
            // Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select full_name, user_name, email, password from students");

            while(rs.next()){
                System.out.println(rs.getString("full_name")+" "+rs.getString("user_name")+" "+rs.getString("email"));
            }

            //close connection
            conn.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
	

}

// java -cp .;mysql-connector.jar JavaWithDatabase