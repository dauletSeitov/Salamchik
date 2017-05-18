package kz.loungeEntertainment.FireHorse;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		//DataBaseConnection dn = new DataBaseConnection();
		//System.out.println(dn.getAllUsers());
		
		return "home";
	}
	
		
	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public String setText(
			@RequestParam(value = "setter") String setter,
			@RequestParam(value = "getter") String getter,
			@RequestParam(value = "text") String text
			){
		try{
			//System.out.println(setter);
			//System.out.println(getter);
			//System.out.println(text);
			DataBaseConnection dn = new DataBaseConnection();
			dn.insert(setter, getter, text);
			}
		catch(Exception e){
			System.out.println(e);
			return null;
		}

		return "ok";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public String getText(
			HttpServletResponse response,
			@RequestParam(value = "setter") String setter,
			@RequestParam(value = "getter") String getter){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		//System.out.println("entered to get text");
		try{
			DataBaseConnection dn = new DataBaseConnection();
			//System.out.println(dn.getAll(setter, getter));
			return dn.getAll(setter, getter);
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		
		
	}
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
	@ResponseBody
	public String getAllUser(/*@RequestParam(value = "data") String text1*/){
		
		//System.out.println("entered to get All User");
		String s = new String();
		try{
			DataBaseConnection dn = new DataBaseConnection();
			List<ArrayList<String>> result = dn.getAllUsers();
			
			for (ArrayList<String> arrayList : result) {
				
				s += arrayList.get(0).replaceAll(";", ":") + ";" + arrayList.get(1).replaceAll(";", ":") + ";" + arrayList.get(2).replaceAll(";", ":") + "\n"; 
				
			}
			
			return s;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		
		
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(
			@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password
			){
		
		//System.out.println("login");
		
		try{
			DataBaseConnection dn = new DataBaseConnection();
			//System.out.println(dn.getPassword(login));
			return String.valueOf(password.equals(dn.getPassword(login)));
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public String signUp(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password
			){
		
		//System.out.println("login");
		
		try{
			DataBaseConnection dn = new DataBaseConnection();
			
			dn.insertPssword(name, login, password);
			return "true";
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		
		
	}
	
	
	
	@RequestMapping(value = "/doIHaveNewMessage", method = RequestMethod.POST)
	@ResponseBody
	public String doIHaveNewMessage(
			@RequestParam(value = "setter") String setter
			){
		
		//System.out.println("doIHaveNewMessage");
		
		try{
			DataBaseConnection dn = new DataBaseConnection();
			return dn.doIHaveNewMessage(setter);
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
		
		
	}

	
	
}
