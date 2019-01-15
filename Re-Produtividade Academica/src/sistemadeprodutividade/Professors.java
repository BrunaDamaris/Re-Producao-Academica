package sistemadeprodutividade;

import java.util.ArrayList;

public class Professors extends Contributors{
	private ArrayList<String> orientation = new ArrayList<String>();
	private ArrayList<String> associateProject = new ArrayList<String>();
	
	public Professors(String name, String type, String email) {
		super(name, type, email);
		String first = "System Initialize";
		ArrayList<String> newa = new ArrayList<String>();
		newa.add(first);
		ArrayList<String> newa1 = new ArrayList<String>();
		newa1.add(first);
		setOrientation(newa);
		setAssociateProject(newa1);
	}
	public ArrayList<String> getOrientation() {
		return orientation;
	}
	public void setOrientation(ArrayList<String> orientation) {
		this.orientation = orientation;
	}
	public ArrayList<String> getAssociateProject() {
		return associateProject;
	}
	public void setAssociateProject(ArrayList<String> associateProject) {
		this.associateProject = associateProject;
	}
	
}
