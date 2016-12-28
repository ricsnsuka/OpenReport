package training;

public class RandomThing {

	public static void main(String[] args) {
		Modify ola = new Modify("Ola");
		 
		modify(ola);
		System.out.println("Ola fora da funcao: " + ola);
	}
	
	public static void modify(Modify ola) {
		ola.setModify("Adeus");
		
		System.out.println("Ola dentro da funcao: " + ola);
	}
	
	private static class Modify {
		String modify;
		
		public Modify(String modify) {
			this.modify = modify;
		}
		public String getModify() {
			return modify;
		}
		public void setModify(String modify) {
			this.modify = modify;
		}
		public String toString() {
			return getModify();
		}
		
		
	}

}
