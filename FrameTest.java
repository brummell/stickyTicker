package ticker;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import org.json.simple.*;

// *********************************************************
// Uses pushed Cay S. Horstmann's convenience class for 
// simplification/improved legibility when setting Grid Bag 
// Layout parameters. All rights, etc. as stated in said
// code, i.e. in the header of "GBC.java".
// *********************************************************

// Imminent upgrades to include adding/finalizing listeners/responses, 
// use of said responses to set temp variables in JSON config file 
// for parameters of "Ticker.java" as well as in-class method to 
// create crontab with job entry given said parameters via shell
// commands.

// Medium upgrades to include help bubbles for entry format for various
// fields on table and making the "fields" list from facade into a 
// working event, as the website/database are expanded to define/authenticate 
// users and customized tables.

// Less vital, and hence longer term, upgrades to include ability to 
// define storage location and name for created file, to show recently
// created and/or used configs, and to move away from cron and towards 
// an in program scheduler (if the need for interoperabilty between 
// OS's

public class FrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SimpleFrame frame = new SimpleFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

@SuppressWarnings("serial")
class SimpleFrame extends JFrame {
	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setTitle("StickyTicker");
 		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		hoursBox = new JComboBox(new String[] {"Seconds", "Minutes", "Hours", 
			"Days", "Weeks"});

		fieldsList = new JList(new String[] {"Open", "Previous_Close", 
				"Price_Paid", "Change_in_Percent", "Price/Sales", "Price/Book" ,
				"Ex-Dividend_Date", "P/E_Ratio", "Dividend_Pay_Date", 
				"P/E_Ratio_(Real-time)", "PEG_Ratio", "Price/EPS_Estimate_Current_Year" ,
				"Price/EPS_Estimate_Next_Year", "Symbol", "Shares_Owned", "Short_Ratio" ,
				"Last_Trade_Time", "Trade_Links", "Ticker_Trend", "1_yr_Target_Price", 
				"Volume", "Holdings_Value", "Holdings_Value_(Real-time)", "52-week_Range", 
				"Day’s_Value_Change", "Day’s_Value_Change_(Real-time)", "Stock_Exchange", 
				"Dividend_Yield"});
		daysList = new JList(new String[] {"Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday"});
		daysList.setVisibleRowCount(5);
		
		JScrollPane fieldsPane = new JScrollPane(fieldsList);
		JScrollPane daysPane = new JScrollPane(daysList);

		log = new JCheckBox("Log each transaction to text?");
		printToScreen = new JCheckBox("Print results to screeen?");

		buttonPane = new JPanel();
		
		symbolField = new JTextField("Enter symbols (X1+X2+...+X3)", 20);
		freqField = new JTextField("Enter integer", 8);
		hourBegField = new JTextField("Enter time (int.)", 10);
		hourEndField = new JTextField("Enter time (int.)", 10);
		fileNameField = new JTextField("Enter conf. name", 20);
		
		JLabel stocks = new JLabel("Symbols: "), options = new JLabel("Fields: "),
				run = new JLabel("Run every: "), days = new JLabel("On days: "),
				hourStart = new JLabel("Between: "), hourFinish = new JLabel("And: "),
				saveName = new JLabel("Save as: ");

		JButton subButton = new JButton("Submit");
		subButton.addActionListener( new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent event) {
				List<String> fields = new ArrayList<String>();
				List<String> days = new ArrayList<String>();
				Object[] fieldsValues = fieldsList.getSelectedValues();
				Object[] daysValues = daysList.getSelectedValues();
	
				for (Object value : fieldsValues) {
					fields.add((String) value); 
				}		
				for (Object value : daysValues) {
					days.add((String) value); 
				}
				
				JSONObject obj = new JSONObject();
				 	obj.put("symbolString",symbolField.getText());
				 	obj.put("freq",freqField.getText());
					obj.put("hourBeg",hourBegField.getText());
					obj.put("hourEnd",hourEndField.getText());
					obj.put("shouldLog", log.isSelected());
					obj.put("shouldPrint", printToScreen.isSelected());
					obj.put("unit", (String) hoursBox.getSelectedItem());
					obj.put("saveName", fileNameField.getText());
					obj.put("fields", fields);
					obj.put("days", days);
				
				  System.out.print(obj);
			}
		});
		
		add(stocks, new GBC(0, 0).setAnchor(GBC.WEST).setWeight(100, 100));
		add(options, new GBC(0, 1).setAnchor(GBC.WEST).setWeight(100, 100));
		add(run, new GBC(0, 2).setAnchor(GBC.WEST).setWeight(100, 100));
		add(days, new GBC(0, 3).setAnchor(GBC.WEST).setWeight(100, 100));
		add(hourStart, new GBC(2, 3).setAnchor(GBC.WEST).setWeight(0, 100));
		add(hourFinish, new GBC(2, 4).setAnchor(GBC.NORTHWEST).setWeight(0, 200));
		add(saveName, new GBC(0, 7).setAnchor(GBC.NORTHWEST).setWeight(100, 100));
		add(fieldsPane, new GBC(1, 1, 3, 1).setAnchor(GBC.WEST).setWeight(100, 100));
		add(daysPane, new GBC(1, 3, 1, 2 ).setAnchor(GBC.WEST).setWeight(100, 100));
		add(symbolField, new GBC(1, 0, 3, 1).setAnchor(GBC.WEST).setWeight(100, 100));
		add(hourBegField, new GBC(3, 3).setAnchor(GBC.WEST).setWeight(100, 100));
		add(hourEndField, new GBC(3, 4).setAnchor(GBC.NORTHWEST).setWeight(100, 200));
		add(freqField, new GBC(1, 2).setAnchor(GBC.WEST).setWeight(100, 100));
		add(hoursBox, new GBC(2, 2, 2, 1).setAnchor(GBC.WEST).setWeight(100, 100));
		add(log, new GBC(0, 5, 3, 1).setAnchor(GBC.NORTHWEST).setWeight(100, 100));
		add(printToScreen, new GBC(0, 6, 3, 1).setAnchor(GBC.NORTHWEST).setWeight(100, 100));
		add(fileNameField, new GBC(1, 7, 3, 1).setAnchor(GBC.NORTHWEST).setWeight(100, 100));
		add(subButton, new GBC(3, 8).setAnchor(GBC.NORTHEAST).setWeight(100, 100));
	
		add(buttonPane);
	}
	
	private JCheckBox log;
	private JCheckBox printToScreen;
	private JTextField freqField;
	private JTextField symbolField; 
	private JTextField hourBegField;
	private JTextField hourEndField;
	private JTextField fileNameField;
	private JPanel buttonPane;
	private JList fieldsList;
	private JList daysList;
	private JComboBox hoursBox;
	
	public static final int DEFAULT_WIDTH = 425;
	public static final int DEFAULT_HEIGHT = 470;
}

