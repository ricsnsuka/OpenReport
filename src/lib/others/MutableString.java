package lib.others;

public class MutableString  {
	private String string;
	
	public MutableString() {
		this.string = "";
	}
	
	public MutableString(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	public String toString() {
		return getString();
	}

}
