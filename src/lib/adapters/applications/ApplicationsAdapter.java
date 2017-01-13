package lib.adapters.applications;

import java.util.List;

public abstract class ApplicationsAdapter {
	private List<String> selectedValues;
	
	public ApplicationsAdapter() {
	}

	public List<String> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<String> selectedValues) {
		this.selectedValues = selectedValues;
	}
}
