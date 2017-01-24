package lib.adapters.applications;

import java.util.ArrayList;
import java.util.List;

import lib.structs.Application;

public class ApplicationsAdapter implements Cloneable {
	private List<Application> selectedValues;
	
	public ApplicationsAdapter() {
		selectedValues = new ArrayList<>();
	}

	public List<Application> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<Application> selectedValues) {
		this.selectedValues = selectedValues;
	}
	
	public ApplicationsAdapter clone() {
		ApplicationsAdapter clone = new ApplicationsAdapter();
		List<Application> cloneSelectedValues = getSelectedValues().subList(0, getSelectedValues().size());
		
		clone.setSelectedValues(cloneSelectedValues);
		
		return clone;
	}
}
