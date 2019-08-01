import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.*;

public class GUI {
	public static final Color COLOR_PLAIN = Color.decode("#FFFFFF");
	public static final Color COLOR_BACKGROUND = Color.decode("#B7D1F0");
	public static final Color COLOR_MAIN = Color.decode("#5BC0BE");
	public static final Color COLOR_TEXT = Color.decode("#3A506B");
	public static final Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 20);
	
	public static void main(String[] args) throws IOException {
		SermonInfo info = new SermonInfo();
		
		JFrame jframe=new JFrame();
		jframe.getContentPane().setBackground(COLOR_BACKGROUND);
		
		
		{//Date picker
			Date date = new Date();
			info.setDate(date);
			
			String[] monthOptions = {"January","February","March","April","May","June","July",
			                          "August","September","October","November","December"};
			JComboBox<String> monthDropdown = new JComboBox<String>(monthOptions);
			monthDropdown.setSelectedIndex(date.getMonth());
			monthDropdown.addActionListener((e) -> {
				date.setMonth(monthDropdown.getSelectedIndex());
				info.setDate(date);
				System.out.println(date);
			});

			Integer[] dayOptions = new Integer[31];
			for (int i = 0; i < dayOptions.length; i++) {
				dayOptions[i] = i + 1;
			}
			
			JComboBox<Integer> dayDropdown = new JComboBox<Integer>(dayOptions);
			dayDropdown.setSelectedIndex(date.getDate() - 1);
			dayDropdown.addActionListener((e) -> {
				date.setDate((Integer) (dayDropdown.getSelectedItem()));
				info.setDate(date);
				System.out.println(date);
			});
			
			Integer[] yearOptions = new Integer[10];
			yearOptions[0] = 1900 +  date.getYear();
			yearOptions[1] = yearOptions[0] - 5;
			yearOptions[2] = yearOptions[0] - 4;
			yearOptions[3] = yearOptions[0] - 3;
			yearOptions[4] = yearOptions[0] - 2;
			yearOptions[5] = yearOptions[0] - 1;
			yearOptions[6] = yearOptions[0] + 1;
			yearOptions[7] = yearOptions[0] + 2;
			yearOptions[8] = yearOptions[0] + 3;
			yearOptions[9] = yearOptions[0] + 4;
			
			JComboBox<Integer> yearDropdown = new JComboBox<Integer>(yearOptions);
			yearDropdown.setSelectedIndex(0);
			yearDropdown.addActionListener((e) -> {
				date.setYear((Integer) (yearDropdown.getSelectedItem()) - 1900);
				info.setDate(date);
				System.out.println(date);
			});

			JPanel datePanel = new JPanel(new GridLayout(0, 1));
			datePanel.setBackground(COLOR_BACKGROUND);
			datePanel.add(new JLabel("Select date"), 0, 0);
			datePanel.add(monthDropdown);
			datePanel.add(dayDropdown);
			datePanel.add(yearDropdown);
			datePanel.setBounds(100, 250, 200, 90);//x axis, y axis, width, height
			jframe.add(datePanel);
		}
		
		{// Priest Dropdown
			String[] priestOptions = {"Fr. Daniel", "Fr. Mikhail", "H.G. Seraphim", "Chior", "Other"};
			JComboBox<String> priestDropdown = new JComboBox<String>(priestOptions);
			priestDropdown.addActionListener((e) -> info.setPriest(priestDropdown.getSelectedIndex()));
			JPanel priestPanel = new JPanel(new GridLayout(0, 1));
			priestPanel.setBackground(COLOR_BACKGROUND);
			priestPanel.add(new JLabel("Select priest"));
			priestPanel.add(priestDropdown);
			priestPanel.setBounds(100, 150, 200, 70);//x axis, y axis, width, height
			jframe.add(priestPanel);
			info.setPriest(PriestInfo.DANIEL);
		}
		
		{// Language Radio Buttons
			JRadioButton englishButton = new JRadioButton("English", true);
			englishButton.addActionListener((e) -> info.setEnglish(true));
			
			JRadioButton arabicButton = new JRadioButton("Arabic");
			arabicButton.addActionListener((e) -> info.setEnglish(false));
	
			ButtonGroup language = new ButtonGroup();
			language.add(englishButton);
			language.add(arabicButton);
			
			JPanel languagePanel = new JPanel(new GridLayout(0, 1));
			languagePanel.add(new JLabel("Select language"));
			languagePanel.setBackground(COLOR_BACKGROUND);
			languagePanel.setBounds(100, 70, 200, 60);//x axis, y axis, width, height
			languagePanel.add(englishButton);
			languagePanel.add(arabicButton);

			jframe.add(languagePanel);
			info.setEnglish(true);
		}
		
		{//Header
			JPanel headerPanel = new JPanel(new GridLayout(0, 1));
			JLabel label = new JLabel("Sermon Folder Creator", SwingConstants.CENTER);
			label.setFont(FONT_HEADER);
			
			headerPanel.add(label);
			headerPanel.setBounds(0, 0, 400, 40);
			headerPanel.setBackground(COLOR_PLAIN);
			jframe.add(headerPanel);
		}

		{// Submit button
			JButton submit =new JButton("CREATE!");//creating instance of JButton
			submit.setBackground(COLOR_MAIN);
			submit.setBounds(150,400,100, 40);//x axis, y axis, width, height
			submit.addActionListener((e) -> onSubmit(info));
			jframe.add(submit);//adding button in JFrame  
		}
		
		jframe.setTitle("Sermon Folder Creator");
		jframe.setBackground(COLOR_MAIN);
		jframe.setSize(400,500);//400 width and 500 height  
		jframe.setLayout(null);//using no layout managers  
		jframe.setVisible(true);//making the frame visible 

	}
	
	private static void onSubmit(SermonInfo info) {
		try {
			File folder = SermonCreator.create(info);
			Runtime.getRuntime().exec("explorer.exe /select," + folder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
