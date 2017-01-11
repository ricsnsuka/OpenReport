package lib.gui.blocks.schedule;

import java.awt.Font;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class ScheduleWeekdayButton {
	private JToggleButton button;
	private String weekDay;
	private String shortName;
	
	public ScheduleWeekdayButton(String weekDay, String shortName) {
		this.weekDay = weekDay;
		this.shortName = shortName;
		createButton();
	}
	
	private void createButton() {
		button = new JToggleButton(this.shortName);
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setToolTipText("Run on " + weekDay + "s");
	}
	
	public String getWeekDay() {
		return this.weekDay;
	}
	
	public JToggleButton getToggleButton() {
		return this.button;
	}
}
