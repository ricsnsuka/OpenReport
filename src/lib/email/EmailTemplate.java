package lib.email;

import org.stringtemplate.v4.ST;

public class EmailTemplate {
	public static void main(String[] args) {
		ST template = new ST("Hello, <name>");
		template.add("name", "Ricardo");
		System.out.println(template.render());
		
		
	}
	
	
}
