package lib.gui.blocks.schedule;

import java.awt.Font;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class ScheduleWeekdayButton extends JToggleButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2357892955779325490L;
	private String weekDay;
	
	public ScheduleWeekdayButton(String weekDay, String shortName) {
		super(shortName);
		this.weekDay = weekDay;
		createButton();
	}
	
	private void createButton() {
		setHorizontalAlignment(SwingConstants.LEADING);
		setFont(new Font("Tahoma", Font.PLAIN, 11));
		setToolTipText("Run on " + weekDay + "s");
	}
	
	public String getWeekDay() {
		return this.weekDay;
	}
	
}
