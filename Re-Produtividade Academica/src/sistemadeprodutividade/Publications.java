package sistemadeprodutividade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Publications{
	private String publicationtitle;
	private int publicationyear;
	private ArrayList<String> authors = new ArrayList<String>();
	private String conferenceName;
	private String associatedProject;
	
	public String getPublicationtitle() {
		return publicationtitle;
	}
	public void setPublicationtitle(String title) {
		this.publicationtitle = title;
	}
	public int getPublicationyear() {
		return publicationyear;
	}
	public void setPublicationyear(int year) {
		this.publicationyear = year;
	}
	public ArrayList<String> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}
	public String getConferenceName() {
		return conferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	public String getAssociatedProject() {
		return associatedProject;
	}
	public void setAssociatedProject(String associatedProject) {
		this.associatedProject = associatedProject;
	}
	public static void add(ArrayList<Publications> publications, ArrayList<Contributors> contributors,String publicationname, String publicationconference, int publication_year, Scanner input) {
		Publications addPublication = new Publications();
		addPublication.setPublicationtitle(publicationname);
		addPublication.setConferenceName(publicationconference);
		addPublication.setPublicationyear(publication_year);
		addPublication.setAssociatedProject("-1");
		String authors,trash = null,option = null;
		int number_of_authors = 0,contributorindex = -1;
		boolean verify = false,correctInput = false;
		System.out.println("Informe quantos autores deseja adicionar a publicacao: ");
		//Try
		while(!correctInput) {
			try{
				number_of_authors = input.nextInt();
				correctInput = true;
			}
			catch(NumberFormatException e){
				System.out.println("Valor no formato incorreto.");
			}
			catch(InputMismatchException e) {
				System.out.println("Valor no formato incorreto.");
			}
			if(number_of_authors <= 0) {
				System.out.println("Valor nao aceito. Digite novamente:");
				correctInput = false;
			}
			trash = input.nextLine();
		}
		correctInput = false;
		if(contributors.size() != 0) {
			for(int i = 0;i < number_of_authors;i++) {
				System.out.println("Informe o(s) autor(es) da publicacao: ");
					authors = input.nextLine();
					for(int j = 0;j < contributors.size();j++) {
						if(contributors.get(j).getName().equals(authors)) {
							System.out.println("Deseja adicionar esse autor? " + "Nome: " + authors + "\nTipo: " + contributors.get(j).getType() +"\nEmail: " + contributors.get(j).getEmail());
							System.out.println("Se sim,pressione 1. Se nao, pressione 2");
							option = input.nextLine();
							if(option.equals("1")) {
								contributorindex = j;
								break;
							}
						}
					}
					if(contributorindex != -1) {
						addPublication.getAuthors().add(authors);
						contributors.get(contributorindex).getMyPublications().add(addPublication);
						verify = true;
					}
					else {
						number_of_authors++;
						System.out.println("Colaborador nao encontrado.");
					}
			}
			if(verify) {
				publications.add(addPublication);
				System.out.println("Publicacao criada com sucesso");
			}
			else System.out.println("Nao foi possivel adicionar os autores. Publicacao nao criada.");
		}
		else System.out.println("Devem existir colaboradores no laboratorio" + trash);
		
	}
	
	public static void sort(ArrayList<Publications> publications) {
		Collections.sort(publications, new Comparator<Publications>() {
            public int compare(Publications first, Publications second) {
            	if(first.getPublicationyear() > second.getPublicationyear()) return -1;
            	else if(first.getPublicationyear() < second.getPublicationyear()) return 1;
            	else return 0;
            }
        });
	}
}
