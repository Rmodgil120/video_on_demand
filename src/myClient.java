
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.io.File;


public class myClient 
{

   public static String fetchIndex()
   {
       String url = "http://localhost:9000/";
       try 
       {
    HttpResponse<String> res = Unirest.get("http://localhost:9000/one").asString();
           
       if(res.getStatus() == 200)
       {
           String ans = res.getBody();
           System.out.println("---->"+ans);
           return ans;
       }
       } 
       catch (Exception e) 
       {
       e.printStackTrace();
       return e.toString();
       }
       
      return "error";
   }
   
    public static String userlogin(String username, String password)
   {
     
       try 
       {
           String url = "http://localhost:9000/userlogin";
   
           HttpResponse<String> res = Unirest.get(url)
                   .queryString("username", username)
                   .queryString("password", password)
                   .asString();
           
       if(res.getStatus() == 200)
       {
           String ans = res.getBody();
           System.out.println("-->"+ans);
           return ans;
       }
       } 
       catch (Exception e) 
       {
       e.printStackTrace();
       return e.toString();
       }
       
      return "error";
   }

    public static String usersignup(String email,String pass,String mobile,String address,File ph)
    {
        try {
              String url = "http://localhost:9000/usersignup";
               HttpResponse<String> res = Unirest.post(url)
                       .queryString("email", email)
                       .queryString("pass", pass)
                       .queryString("mobile", mobile)
                       .queryString("address", address)
                       .field("f1", ph)
                       .asString();
               
               if(res.getStatus()==200)
               {
                   return res.getBody();
               }
                       
        } 
        catch (Exception e) {
            return e.toString();
        }
       return "error";
    }
    
    public static String verifyEmail(String Email)
    {
        String url = "http://localhost:9000/verifyEmail";
        try {
            
        
        
      HttpResponse<String> res = Unirest.get(url)
         .queryString("email", Email)
         .asString();
      
      if(res.getStatus()==200) // OK Status
      {
       
          return res.getBody();
      }
      } 
      catch (Exception e) {
          return e.toString();
        }
     return null;
    }
   
    public static String fetchcat()
    {
        String url = "http://localhost:9000/fetchcat";
        
        try {
        HttpResponse<String> res = Unirest.get(url).asString();
        if(res.getStatus()==200)
        {
            return res.getBody();
        }
        else
        {
            return "server error";
        }
    }
     
        catch (Exception e) 
        {
          return e.toString();
        }
    }
    
    public static String AdminLogin(String name, String pass)
    {
         String url = "http://localhost:9000/AdminLogin";
        try {
             HttpResponse<String> res = Unirest.get(url)
                     .queryString("name",name)
                     .queryString("pass", pass)
                     .asString();
             if(res.getStatus()==200)
             {
                 return res.getBody();
             }
             else
             {
                 return "Server Error";
             }
        } 
        catch (Exception e)
        {
            return e.toString();
        }
       
    }
    
    public static String addcat(String category, File ph)
    {
          String url = "http://localhost:9000/addcat";
          try {
            
       
          HttpResponse<String> res = Unirest.post(url)
                  .queryString("category", category)
                  .field("f1", ph)
                  .asString();
          
          if(res.getStatus()==200)
          {
              return res.getBody();
          }
          else
          {
              return "Server Error";
          }
           } catch (Exception e)
           {
               return e.toString();
            }
    }
    
    public static String fetchcategories()
    {
         String url = "http://localhost:9000/fetchcategories";
         
         try {
         
         HttpResponse<String> res = Unirest.get(url).asString();
         
         if(res.getStatus()==200)
         {
             return res.getBody();
         }
         else
         {
             return "Server Error";
         }
             
        } catch (Exception e) 
        {
          return e.toString();
        }
        
    }
    
    public static String addmovie(String category, String movie, String director, String cast, String id, File ph , File movie_link)
    {
        String url = "http://localhost:9000/addmovie";
        try 
        {
         HttpResponse<String> res  = Unirest.post(url)
                 .queryString("category", category)
                 .queryString("movie", movie)
                 .queryString("director", director)
                 .queryString("cast", cast)
                 .queryString("id", id)
                 .field("f2", ph)
                 .field("movie_link", movie_link)
                 .asString();
         
         if(res.getStatus()==200)
         {
              
            return res.getBody();
           
         }
         else
         {
             return "Server Error";
         }
            
            
        } 
        catch (Exception e) 
        {
        return e.toString();
        }
    }
    
    public static String fetchmovies(String category)
    {
         String url = "http://localhost:9000/fetchmovies";
        
        try 
        {
          HttpResponse<String> res = Unirest.get(url)
                  .queryString("category",category)
                  .asString();
          
          if(res.getStatus() == 200)
          {
              return res.getBody();
          }
          else
          {
              return "Server Error";
          }
        }
        catch (Exception e) 
        {
        return e.toString();
        }
    }
    
    public static String moviedetail(int id)
    {
        String url = "http://localhost:9000/moviedetails";
        
        try 
        {
            HttpResponse<String> res = Unirest.get(url)
                    .queryString("id", id)
                    .asString();
            if(res.getStatus()==200)
            {
                return res.getBody();
            }
            else
            {
                return "Server Error";
                
            }
        } 
        catch (Exception e) 
        {
            return e.toString();
        }
    }
    
    public static String search(String name)
    {
         String url = "http://localhost:9000/search";
         
         try {
       
         HttpResponse<String> res  =  Unirest.get(url)
                 .queryString("name",name)
                 .asString();
         
         if(res.getStatus()==200)
         {
             return res.getBody();
         }
         else
         {
             return "server error";
         }
            } 
         catch (Exception e)
         {
             e.printStackTrace();
        }
               return null;
    }
    
    public static String savemovie(File ph)
    {
        String url = "http://localhost:9000/savemovie";
        try
        {
        HttpResponse<String> res = Unirest.post(url)
                .field("f1", ph)
                .asString();
        
        if(res.getStatus()==200)
        {
            return res.getBody();
        }
        else
        {
            return "Server Error";
        }
        }
        catch(Exception ex)
        {
           return ex.toString();
        }
    }
}
