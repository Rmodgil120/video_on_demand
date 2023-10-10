import com.vmm.JHTTPServer;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import java.sql.*;



public class myServer extends JHTTPServer
{
    
    int otp;
    
    globalvariables gv = new globalvariables();
    public myServer(int port) throws IOException 
    {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) 
    {
        Response res = null;
        
    if(uri.equals("/"))
    {
        String ans = Math.random()+"";
       
        res = new Response(HTTP_OK, "text/plain", ans);
        
       
    }
    else if(uri.equals("/one"))
    {
        String ans = Math.random()+"";
       
        res = new Response(HTTP_OK, "text/plain", ans);
        
        
    }
    
    else if(uri.equals("/userlogin"))
    {
        
        String username = parms.getProperty("username");
        String password = parms.getProperty("password");
        System.out.println("----->"+username+"---->"+password);
        try {
            
       
        
        ResultSet rs = DBLoader.executeQuery("select * from users where useremail='"+username+"' and password = '"+password+"'");
        if(rs.next())
        {
            res = new Response(HTTP_OK, "text/plain", "success");
        }
        else
        {
             res = new Response(HTTP_OK, "text/plain", "fail");
        }
         }
        catch (Exception e) 
         {
           e.printStackTrace();
         }
    }
    
    else if(uri.equals("/usersignup"))
    {
        String email = parms.getProperty("email");
        String password = parms.getProperty("pass");
        String mobile = parms.getProperty("mobile");
        String address = parms.getProperty("address");
        String abspath = "src/uploads/";
        String name = saveFileOnServerWithRandomName(files, parms, "f1", abspath);
        try {
        
        ResultSet rs = DBLoader.executeQuery("select * from users");
      
            ResultSet rs1 = DBLoader.executeQuery("select * from users where mobile='"+mobile+"'");
            if(rs1.next())
            {
                 res = new Response(HTTP_OK, "text/plain", "mobile");
            }
            else
            {
                  rs1.moveToInsertRow();
            rs1.updateString("useremail", email);
            rs1.updateString("password", password);
            rs1.updateString("mobile", mobile);
            rs1.updateString("address", address);
            rs1.updateString("photo", "src/uploads/"+name);
            rs1.insertRow();
            gv.setMyotp(0);
             res = new Response(HTTP_OK, "text/plain", "success"); 
            }
         
        
      } catch (Exception e)
      {
      e.printStackTrace();
      }
    }
    
    else if(uri.equals("/verifyEmail"))
    {
        String email = parms.getProperty("email");
        
        try 
        {
        ResultSet rs = DBLoader.executeQuery("select * from users where useremail='"+email+"'");
        if(rs.next())
        {
                res = new Response(HTTP_OK, "text/plain", "exist");
        }
        else
        {
              otp =  (int) (1000+(9999-1000)*Math.random());
             System.out.println("--->"+otp);
             
//            sendemail obj = new sendemail(email,"hello","Your Otp is: "+otp);
             // set Otp to Global Variable
         
              gv.setMyotp(otp);
            
            res = new Response(HTTP_OK, "text/plain", otp+"");
        }
        } 
        catch (Exception e) 
        {
          e.printStackTrace();
        }
        
         
            
        
    }
    
    else if(uri.equals("/fetchcat"))
    {
        String ans="";
        try {
            
        
        ResultSet rs = DBLoader.executeQuery("select * from category");
        while(rs.next())
        {
           String name = rs.getString("name");
           String photo = rs.getString("photo");
           
           String row = name+"$"+photo;
           ans = ans+row+";;";
               res = new Response(HTTP_OK, "text/plain", ans);
        }
        
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/AdminLogin"))
    {
        String name = parms.getProperty("name");
        String pass = parms.getProperty("pass");
        
        System.out.println("name:"+name+"password:"+pass);
        
        try {
       
        
        ResultSet rs = DBLoader.executeQuery("select * from Admin where username='"+name+"' and password='"+pass+"'");
        if(rs.next())
        {
               res = new Response(HTTP_OK, "text/plain", "success"); 
        }
        else
        {
                res = new Response(HTTP_OK, "text/plain", "Fail");
        }
             
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/addcat"))
    {
        String category  = parms.getProperty("category");
        
        String name = saveFileOnServerWithRandomName(files, parms, "f1", "src/uploads/");
       
        try {
            
        
        ResultSet rs = DBLoader.executeQuery("select * from category where name='"+category+"'");
        if(rs.next())
        {
              res = new Response(HTTP_OK, "text/plain", "exist");
        }
        else
        {
            rs.moveToInsertRow();
            rs.updateString("name", category);
            rs.updateString("photo", "src/uploads/"+name);
            rs.insertRow();
            
              res = new Response(HTTP_OK, "text/plain", "success");
        }
        } 
        catch (Exception e) {
        }
    }
    
    else if(uri.equals("/fetchcategories"))
    {
        String ans="";
        
        try {
        
            ResultSet rs = DBLoader.executeQuery("select name from category");
            
            while(rs.next())
            {
                String name = rs.getString("name");
                
                String row = name;
                ans = ans+row+";;";
            }
            
             res = new Response(HTTP_OK, "text/plain", ans);
            
        } 
        catch (Exception e) {
        }
    }
    
    else if(uri.equals("/addmovie"))
    {
        String category = parms.getProperty("category");
        String movie = parms.getProperty("movie");
        String director = parms.getProperty("director");
        String cast = parms.getProperty("cast");
        String id = parms.getProperty("id");
       String name = saveFileOnServerWithRandomName(files, parms, "f2", "src/uploads/");
      String movie_name = saveFileOnServerWithRandomName(files, parms, "movie_link", "src/uploads/movies/");
      
      
       // for removing forward slash
       
       File folder = new File("src/uploads/movies");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                
                String name2 =listOfFiles[i].getName();
                
                StringBuilder st = new StringBuilder(name2);

                File f = new File("src/uploads/movies/"+listOfFiles[i].getName()); 
                 if(st.charAt(0) == '\\')
                         {
                 f.renameTo(new File("src/uploads/movies/"+st.deleteCharAt(0)));
                        }
            }
        }

       
       File folder2 = new File("src/uploads");
        File[] listOfFiles1 = folder2.listFiles();

        for (int i = 0; i < listOfFiles1.length; i++) {

            if (listOfFiles1[i].isFile()) {
                
                String name1 =listOfFiles1[i].getName();
                
                StringBuilder st = new StringBuilder(name1);

                File f = new File("src/uploads/"+listOfFiles1[i].getName()); 
                if(st.charAt(0) == '\\')
                {
                 f.renameTo(new File("src/uploads/"+st.deleteCharAt(0)));
                }      
            }
        }

        System.out.println("conversion is done");
        
        // end
        try 
        {
        ResultSet rs = DBLoader.executeQuery("select * from movies");
        rs.moveToInsertRow();
        rs.updateString("movie_name", movie);
        rs.updateString("director", director);
        rs.updateString("cast", cast);
        rs.updateString("movie_link","src/uploads/movies/"+movie_name);
        rs.updateString("trailer", id);
        rs.updateString("photo", "src/uploads/"+name);
        rs.updateString("category", category);
        rs.insertRow();
        
         res = new Response(HTTP_OK, "text/plain", "success");
        } 
        catch (Exception e) 
        {
         res = new Response(HTTP_OK, "text/plain", e.toString());
        
        }
        
    }
    
    else if(uri.equals("/fetchmovies"))
    {
        String ans="";
        String category = parms.getProperty("category");
        
        try 
        {
            ResultSet rs = DBLoader.executeQuery("select * from movies where category='"+category+"'");
            while(rs.next())
            {
              int id = rs.getInt("id");
              String name = rs.getString("movie_name");
              String photo  =rs.getString("photo");
              
              String row = id+"$"+name+"$"+photo;
              ans = ans+row+";;";
              
               res = new Response(HTTP_OK, "text/plain", ans);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/moviedetails"))
    {
        int id = Integer.parseInt(parms.getProperty("id"));
        
        try
        {
            ResultSet rs = DBLoader.executeQuery("select * from movies where id="+id);
            if(rs.next())
            {
                String name = rs.getString("movie_name");
                String cast = rs.getString("cast");
                String youtube_id = rs.getString("trailer");
                String photo = rs.getString("photo");
                String movie_link = rs.getString("movie_link");
                
                String ans = name+"$"+cast+"$"+youtube_id+"$"+photo+"$"+movie_link;
                
                 res = new Response(HTTP_OK, "text/plain", ans);
                
                
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/search"))
    {
        String ans="";
        
        String name = parms.getProperty("name");
        
        try {
            
          ResultSet rs = DBLoader.executeQuery("select * from movies where movie_name like '"+name+"%'");
          
           while(rs.next())
            {
              int id = rs.getInt("id");
              String name1 = rs.getString("movie_name");
              String photo  =rs.getString("photo");
              
              String row = id+"$"+name1+"$"+photo;
              ans = ans+row+";;";
              
               res = new Response(HTTP_OK, "text/plain", ans);
            }
          
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    else if(uri.equals("/savemovie"))
    {
        String movie_name = saveFileOnServerWithOriginalName(files, parms, "f1", "src/uploads/movies/");
           File folder = new File("src/uploads/movies");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                
                String name =listOfFiles[i].getName();
                
                StringBuilder st = new StringBuilder(name);

                File f = new File("src/uploads/movies/"+listOfFiles[i].getName()); 
                 if(st.charAt(0) == '\\')
                         {
                 f.renameTo(new File("src/uploads/movies/"+st.deleteCharAt(0)));
                        }
            }
        }

        System.out.println("conversion is done");
         res = new Response(HTTP_OK, "text/plain", movie_name);
       
    }
    return res;
    }
    
   
    

//    public static void main(String[] args) {
//        
//        try 
//        {
//             myServer obj = new myServer(9000);
//             Thread.sleep(1000000000);
//        } 
//        catch (Exception e) 
//        {
//         e.printStackTrace();
//        }
//        
//       
//        
//    }

}
