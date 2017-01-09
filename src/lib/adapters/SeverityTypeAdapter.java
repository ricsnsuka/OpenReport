package lib.adapters;

public class SeverityTypeAdapter {
	private boolean allTypes;
	private boolean severe;
	private boolean info;
	private boolean warning;
	
	public SeverityTypeAdapter() {
	}
	
	public boolean isAllTypes() {
		return allTypes;
	}
	public void setAllTypes(boolean allTypes) {
		this.allTypes = allTypes;
	}
	public boolean isSevere() {
		return severe;
	}
	public void setSevere(boolean severity) {
		this.severe = severity;
	}
	public boolean isInfo() {
		return info;
	}
	public void setInfo(boolean info) {
		this.info = info;
	}
	public boolean isWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
}
