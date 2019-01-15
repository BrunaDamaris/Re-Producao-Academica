package sistemadeprodutividade;

import java.util.ArrayList;
public class Contributors{
	private String name;
	private String type;
	private String email;
	private String hasProject;
	private ArrayList<Project> MyProjects = new ArrayList<Project>();
	private ArrayList<Publications> MyPublications = new ArrayList<Publications>();
	
	public Contributors(String name,String type,String email){
		setName(name);
		setType(type);
		setEmail(email);
		setHasProject("2");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Project> getMyProjects() {
		return MyProjects;
	}

	public void setMyProjects(ArrayList<Project> myProjects) {
		this.MyProjects = myProjects;
	}

	public ArrayList<Publications> getMyPublications() {
		return MyPublications;
	}

	public void setMyPublications(ArrayList<Publications> myPublications) {
		this.MyPublications = myPublications;
	}
	public String getHasProject() {
		return hasProject;
	}

	public void setHasProject(String hasProject) {
		this.hasProject = hasProject;
	}
	
	public static void add(ArrayList<Contributors> contributors,String name,String type,String email) {
		boolean approve = true,exit = false;
		Contributors newcontributor = null;
		if(type.equals("Professor")) {
			newcontributor = new Professors(name,type,email);
		}
		else if(type.equals("Graduacao") || type.equals("Mestrado") || type.equals("Doutorado") || type.equals("Pesquisador")){
			newcontributor = new Contributors(name,type,email);
		}
		else {
			System.out.println("Tipo nao existe, nao foi possivel adicionar.");
			exit = true;
		}
		if(!exit) {
			if(contributors.contains(newcontributor)) {
				System.out.println("Colaborador ja existe");
				approve = false;
			}
			if(approve) {
				contributors.add(newcontributor);
				System.out.println("Colaborador adicionado.");
			}
		}
	}

}
