package phill;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
	
	
	
	@GetMapping("/user")
	public String getUser()
	{
		return "<h1>Hello USER</h1>";
	}

	@GetMapping("/admin")
	public String getAdmin()
	{
		return "<h1>Hello ADMIN</h1>";
	}
	
	@GetMapping("/")
	public void getDef(HttpServletResponse resp) throws IOException
	{
		resp.sendRedirect("/login");
	}
	
	@GetMapping("/redirect")
	public void getRedirect(HttpServletResponse resp,HttpServletRequest request) throws IOException
	{
		//exw kateuthian access sto /user me phillalexakis,google kateuthian xwris kanenan elenxo, twra tha kanw kai to redirect
		System.out.println("Auth: "+SecurityContextHolder.getContext().getAuthentication());
		
		Authentication auth=  SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Principal: "+auth.getPrincipal());
		System.out.println("Authorities: "+auth.getAuthorities()); //get it from here OR
		System.out.println(request.isUserInRole("ROLE_USER")); // get it from Here as WELL (ONLY IF YOU HAVE A REQUEST)
		
		if(request.isUserInRole("ROLE_USER"))
		{
			resp.sendRedirect("/user");
		}else if(request.isUserInRole("ROLE_ADMIN"))
		{
			resp.sendRedirect("/admin");
		}
	}

}


