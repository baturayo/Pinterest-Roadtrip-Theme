//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import com.github.scribejava.core.builder.ServiceBuilder;
//@WebServlet("/googleplus") 
//public class GooglePlusServlet extends HttpServlet {
//
//   private static final String CLIENT_ID = "708053299849-a1sjhsrfdcqv9ajs7h5453f9uv5thccl.apps.googleusercontent.com"; 
//   private static final String CLIENT_SECRET = "GgvCyjFUuQ3mvPQqBbCPCjSZ";
//
//   @Override 
//   protected void doGet(HttpServletRequest req, HttpServletResponse res) 
//      throws IOException, ServletException {
//
//      //Configure 
//      ServiceBuilder builder= new ServiceBuilder(CLIENT_ID); 
//      OAuthService service = builder.provider(Google2Api.class) 
//         .apiKey(CLIENT_ID) 
//         .apiSecret(CLIENT_SECRET) 
//         .callback("http://localhost:8080/mywebapp/oauth2callback&quot;) 
//         .scope("openid profile email " + 
//               "https://www.googleapis.com/auth/plus.login " + 
//               “https://www.googleapis.com/auth/plus.me”)  
//         .debug(System.out) 
//         .build(); //Now build the call
//
//      HttpSession sess = req.getSession(); 
//      sess.setAttribute("oauth2Service", service);
//
//      resp.sendRedirect(service.getAuthorizationUrl(null)); 
//   } 
//} 