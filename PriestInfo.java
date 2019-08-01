
public class PriestInfo {
	public static final int DANIEL = 0;
	public static final int MIKHAIL = 1;
	public static final int SERAPHIM = 2;
	public static final int CHIOR = 3;
	public static final int OTHER = 4;
	
	private int priest;
	
	public PriestInfo(int priest) {
		this.priest = priest;
	}
	
	public String getPriestCode() {
		//follows the order on the white board
		//except for the last two which are swapped to make more sense
		switch(priest) {
			case DANIEL:
				return "DH";
			case MIKHAIL:
				return "MM";
			case SERAPHIM:
				return "SS";
			case CHIOR:
				return "CH";
			default:
				return "XX";
		}
	}
	
	public String getTemplateName() {
		//This is used for selecting templates. 
		//We use FrMikhail as the default template. We can change this in the future
		switch(priest) {
		case DANIEL:
			return "FrDaniel";
		case MIKHAIL:
			return "FrMikhail";
		case SERAPHIM:
			return "BishopSeraphim";
		case CHIOR:
			return "FrMikhail";
		default:
			return "FrMikhail";
		}
	}
	
}
