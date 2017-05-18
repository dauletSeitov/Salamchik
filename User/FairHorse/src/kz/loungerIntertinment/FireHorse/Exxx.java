package kz.loungerIntertinment.FireHorse;

public class Exxx {

	public static void main(String[] args) {
		
		String message = "Abzal : fuck you\nDaulet : wwww\n";
		
		String lastLine = message.substring(message.lastIndexOf("\n",message.length()-2)).replace("\n", ""); 
		String title = lastLine.substring(0, lastLine.indexOf(":"));
		String lastMessag = lastLine.substring(lastLine.indexOf(":") + 2);

		System.out.println(title);
		System.out.println(lastMessag);
	}

}
