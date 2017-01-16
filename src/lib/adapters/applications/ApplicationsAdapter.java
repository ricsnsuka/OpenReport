package lib.adapters.applications;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsAdapter implements Cloneable {
	private List<String> selectedValues;
	
	public ApplicationsAdapter() {
		selectedValues = new ArrayList<>();
	}

	public List<String> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<String> selectedValues) {
		this.selectedValues = selectedValues;
	}
	
	public ApplicationsAdapter clone() {
		ApplicationsAdapter clone = new ApplicationsAdapter();
		List<String> cloneSelectedValues = getSelectedValues().subList(0, getSelectedValues().size());
		
		clone.setSelectedValues(cloneSelectedValues);
		
		return clone;
	}
}
