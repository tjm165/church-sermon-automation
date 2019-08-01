import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SermonInfo {
	
	private String FolderPath;
	private boolean useDefaultFolderPath;
	private boolean isEnglish;
	private PriestInfo priestInfo;
	private Calendar calendar;

	public SermonInfo() {
		this.FolderPath = null;
		this.useDefaultFolderPath = true;
		this.calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		this.isEnglish = true;
		this.priestInfo = new PriestInfo(PriestInfo.OTHER);
	}
	
	
	public void setDate(Date date) {
		calendar.setTime(date);
	}
	
	public boolean isEnglish() {
		return isEnglish;
	}
	
	public void setEnglish(boolean isEnglish) {
		this.isEnglish = isEnglish;
	}
	
	public void setPriest(int number) {
		this.priestInfo = new PriestInfo(number);
	}
	
	public String getLanguageCode() {
		if (isEnglish)
			return "E";
		else
			return "A";
	}
	
	public String getLanguage() {
		if (isEnglish)
			return "English";
		else
			return "Arabic";
	}
	
	public void setFolderPath(String FolderPath) {
		this.useDefaultFolderPath = false;
		this.FolderPath = FolderPath;
	}
	
	public String getFolderPath() {
		if (useDefaultFolderPath) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy\\yyyy-MM-dd");
			String date = format.format(calendar.getTime());
			String language = this.getLanguage();
			
			StringBuilder sb = new StringBuilder("H:\\My Documents\\My Videos");
			sb.append("\\" + date + "\\" + language);
			
			return sb.toString();
		}
		else {
			return this.FolderPath;
		}
	}
	
	public String getTemplatePath() {
		String templateName = this.priestInfo.getTemplateName();
		String language = this.getLanguage();
		
		StringBuilder sb = new StringBuilder("H:\\My Documents\\My Videos\\Templates");
		sb.append("\\Template" + language + templateName + ".veg");
		
		return sb.toString();
	}
	
	public String getMediaFolderPath() {
		String path = this.getFolderPath();
		StringBuilder sb = new StringBuilder(path);
		sb.append("\\Media");
		return sb.toString();
	}
	
	public String getVegasProPath() {
		String path = this.getFolderPath();
		String languageCode = this.getLanguageCode();
		String priestCode = this.priestInfo.getPriestCode();
		
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String date = format.format(calendar.getTime());
		
		StringBuilder sb = new StringBuilder(path);
		sb.append("\\" + languageCode + date + priestCode + ".veg");
		
		return sb.toString();
	}

}
	
