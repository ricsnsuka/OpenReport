package lib.structs;

public class Application {
	private String product;
	private String name;
	
	public Application(String product, String name) {
		this.product = product;
		this.name = name;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String toString() {
		return "Product: " + getProduct() + "; Name: " + getName() + ";";
	}
	
	
}
