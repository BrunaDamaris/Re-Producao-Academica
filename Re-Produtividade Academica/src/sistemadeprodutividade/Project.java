package sistemadeprodutividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Project {
	private String title;
	private int dayOfBeginning;
	private int monthOfBeginning;
	private int yearOfBeginning;
	private int dayOfConclusion;
	private int monthOfConclusion;
	private int yearOfConclusion;
	private String financier;
	private double financierValue;
	private String objective;
	private String description;
	private String status;
	private String projectProfessor;
	private ArrayList<Contributors> ProjectContributors = new ArrayList<Contributors>();
	private ArrayList<Publications> ProjectPublications = new ArrayList<Publications>();
	private ArrayList<String> ProjectOrientation = new ArrayList<String>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDayOfBeginning() {
		return dayOfBeginning;
	}
	public void setDayOfBeginning(int dayOfBeginning) {
		this.dayOfBeginning = dayOfBeginning;
	}
	public int getMonthOfBeginning() {
		return monthOfBeginning;
	}
	public void setMonthOfBeginning(int monthOfBeginning) {
		this.monthOfBeginning = monthOfBeginning;
	}
	public int getYearOfBeginning() {
		return yearOfBeginning;
	}
	public void setYearOfBeginning(int yearOfBeginning) {
		this.yearOfBeginning = yearOfBeginning;
	}
	public int getDayOfConclusion() {
		return dayOfConclusion;
	}
	public void setDayOfConclusion(int dayOfConclusion) {
		this.dayOfConclusion = dayOfConclusion;
	}
	public int getMonthOfConclusion() {
		return monthOfConclusion;
	}
	public void setMonthOfConclusion(int monthOfConclusion) {
		this.monthOfConclusion = monthOfConclusion;
	}
	public int getYearOfConclusion() {
		return yearOfConclusion;
	}
	public void setYearOfConclusion(int yearOfConclusion) {
		this.yearOfConclusion = yearOfConclusion;
	}
	public String getFinancier() {
		return financier;
	}
	public void setFinancier(String financier) {
		this.financier = financier;
	}
	public double getFinancierValue() {
		return financierValue;
	}
	public void setFinancierValue(double financierValue) {
		this.financierValue = financierValue;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProjectProfessor() {
		return projectProfessor;
	}
	public void setProjectProfessor(String projectProfessor) {
		this.projectProfessor = projectProfessor;
	}
	public ArrayList<Contributors> getProjectContributors() {
		return ProjectContributors;
	}
	public void setProjectContributors(ArrayList<Contributors> projectContributors) {
		ProjectContributors = projectContributors;
	}
	public ArrayList<Publications> getProjectPublications() {
		return ProjectPublications;
	}
	public void setProjectPublications(ArrayList<Publications> projectPublications) {
		ProjectPublications = projectPublications;
	}
	public ArrayList<String> getProjectOrientation() {
		return ProjectOrientation;
	}
	public void setProjectOrientation(ArrayList<String> projectOrientation) {
		ProjectOrientation = projectOrientation;
	}
	public static boolean add(ArrayList<Project> projects, ArrayList<Contributors> contributors, String projectname,int b_day, int b_month, int b_year, int f_day, int f_month, int f_year, String newfinancier,double new_value, String newobjective, String newdescription, String newpmanager, String newmanageremail,int managerindex) {
		boolean accept = false;
		Project newproject = new Project();
		newproject.setTitle(projectname);
		newproject.setDayOfBeginning(b_day);
		newproject.setMonthOfBeginning(b_month);
		newproject.setYearOfBeginning(b_year);
		newproject.setDayOfConclusion(f_day);
		newproject.setMonthOfConclusion(f_month);
		newproject.setYearOfConclusion(f_year);
		newproject.setFinancier(newfinancier);
		newproject.setFinancierValue(new_value);
		newproject.setObjective(newobjective);
		newproject.setDescription(newdescription);
		newproject.setStatus("Em elaboracao");
		newproject.setProjectProfessor(newpmanager);
		newproject.getProjectContributors().add(contributors.get(managerindex));
		contributors.get(managerindex).getMyProjects().add(newproject);
		if(projects.contains(newproject)) {
			System.out.println("Projeto ja existe, nao foi possivel adicionar.");
		}
		else {
			projects.add(newproject);
			accept = true;
			System.out.println("Projeto adicionado com sucesso.");
		}
		return accept;
	}
	
	public static void sort(ArrayList<Project> projects) {
		Collections.sort(projects, new Comparator<Project>() {
            public int compare(Project first, Project second) {
            	if(first.getYearOfConclusion() > second.getYearOfConclusion()) return -1;
            	else if(first.getYearOfConclusion() < second.getYearOfConclusion()) return 1;
            	else {
            		if(first.getMonthOfConclusion() > second.getMonthOfConclusion()) return -1;
            		else if(first.getMonthOfConclusion() > second.getMonthOfConclusion()) return 1;
            		else {
            			if(first.getDayOfConclusion() > second.getDayOfConclusion()) return -1;
            			else if(first.getDayOfConclusion() < second.getDayOfConclusion()) return 1;
            			else return 0;
            		}
            	}
            }
        });
	}
}
