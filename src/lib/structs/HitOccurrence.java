package lib.structs;

public class HitOccurrence {
	private String severityType;
	private int occurrencies;
	public HitOccurrence(String severityType) {
		this.severityType = severityType;
		occurrencies = 0;
	}
	
	public void incOccurrencies() {
		this.occurrencies++;
	}

	public  String getSeverityType() {
		return severityType;
	}

	public int getOccurrencies() {
		return occurrencies;
	}

	

}
