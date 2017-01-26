package lib.adapters;

import java.util.ArrayList;
import java.util.List;

import lib.structs.Application;

public class ApplicationAdapter implements Cloneable {
	private List<Application> selectedValues;
	
	public ApplicationAdapter() {
		selectedValues = new ArrayList<>();
	}

	public List<Application> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(List<Application> selectedValues) {
		this.selectedValues = selectedValues;
	}
	
	public ApplicationAdapter clone() {
		ApplicationAdapter clone = new ApplicationAdapter();
		List<Application> cloneSelectedValues = getSelectedValues().subList(0, getSelectedValues().size());
		
		clone.setSelectedValues(cloneSelectedValues);
		
		return clone;
	}
}
