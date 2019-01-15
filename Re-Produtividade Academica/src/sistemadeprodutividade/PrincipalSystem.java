package sistemadeprodutividade;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrincipalSystem{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String first_entry,second_entry,trash = null,projectname = null;
		boolean enterproject = false,correctInput = false;
		int projectsinelaboration = 0,projectsongoing = 0,finishedprojects  = 0,totalorientations = 0,currentproject = -1;
		ArrayList<Project> projects = new ArrayList<Project>();
		ArrayList<Contributors> contributors = new ArrayList<Contributors>();
		ArrayList<Publications> publications = new ArrayList<Publications>();
		
		while(true) {
			System.out.println("Escolha o que deseja acessar: \n");
			System.out.println("Colaboradores(1)\n\nProjetos(2)\n\nPublicacoes(3)\n\nFechar(0)\n");
			first_entry = input.nextLine();
			//Contributors
			if(first_entry.equals("1")) {
				while(true) {
					System.out.println("Adicionar Colaborador ao Laboratorio(1)\n\nVisualizar Colabores(2)\n\nVoltar(0)\n");
					second_entry = input.nextLine();
					//Option 1 - Add
					if(second_entry.equals("1")) {
						String name,type,email = null;
						System.out.println("Nome do Colaborador: ");
						name = input.nextLine();
						System.out.println("Tipo(Graduacao,Mestrado,Doutorado,Professor ou Pesquisador): ");
						type = input.nextLine();
						while(!correctInput) {
							boolean found = false;
							System.out.println("E-mail(Esse email deve ser unico): ");
							email = input.nextLine();
							for(int i = 0;i < contributors.size();i++) {
								if(contributors.get(i).getEmail().equals(email)) found = true;
							}
							if(found) System.out.println("Email ja no sistema. Digite novamente.");
							else correctInput = true;
						}
						correctInput = false;
						Contributors.add(contributors,name,type,email);
						
						System.out.println("Pressione enter para continuar");
						trash = input.nextLine();
					}
					//Option 2 - See
					else if(second_entry.equals("2")) {
						if(contributors.size() != 0) {
							String colab_entry;
							System.out.println("Visualizar um colaborador(1)\n\nVisualizar todos colaboradores(2)\n");
							colab_entry = input.nextLine();
							if(colab_entry.equals("1")) {
								String currentname,currentemail;
								int currentindex = -1;
								System.out.println("Informe o Nome do colaborador: ");
								currentname = input.nextLine();
								System.out.println("Informe o Email do colaborador: ");
								currentemail = input.nextLine();
								for(int i = 0;i < contributors.size();i++) {
									if((contributors.get(i).getName().equals(currentname)) && (contributors.get(i).getEmail().equals(currentemail))) {
										currentindex = i;
										break;
									}
								}
								if(currentindex != -1) {
									System.out.println("Nome: " + currentname);
									System.out.println("Tipo: " + contributors.get(currentindex).getType());
									System.out.println("Email: " + contributors.get(currentindex).getEmail());
									if(contributors.get(currentindex).getMyProjects().size() != 0) {
										System.out.println("Faz ou fez parte de Projeto(s)\nProjeto(s): ");
										Project.sort(contributors.get(currentindex).getMyProjects());
										for(int i = 0;i < contributors.get(currentindex).getMyProjects().size();i++) {
											System.out.println("Nome do Projeto: " + contributors.get(currentindex).getMyProjects().get(i).getTitle());
											System.out.println("Status do Projeto: " + contributors.get(currentindex).getMyProjects().get(i).getStatus());
											System.out.println("Descricao do Projeto: " + contributors.get(currentindex).getMyProjects().get(i).getDescription());
											System.out.println("Objetivo do Projeto: " + contributors.get(currentindex).getMyProjects().get(i).getObjective());
											System.out.println("Data de termino: " + "\nDia: " + contributors.get(currentindex).getMyProjects().get(i).getDayOfConclusion() + "\nMes de termino: " + contributors.get(currentindex).getMyProjects().get(i).getMonthOfConclusion() + "\nAno de termino: " + contributors.get(currentindex).getMyProjects().get(i).getYearOfConclusion());
											System.out.println("--------------------------------------------------------");
										}
									}
									else System.out.println("Nao faz ou fez parte de Projeto(s)");

									if(contributors.get(currentindex).getMyPublications().size() != 0) {
										System.out.println("Faz ou fez parte de Publicacao(oes)\nPublicacao(oes): ");
										Publications.sort(contributors.get(currentindex).getMyPublications());
										for(int i = 0;i < contributors.get(currentindex).getMyPublications().size();i++) {
											System.out.println("Nome da Publicacao: " + contributors.get(currentindex).getMyPublications().get(i).getPublicationtitle());
											System.out.println("Ano de Publicacao: " + contributors.get(currentindex).getMyPublications().get(i).getPublicationyear());
										}
									}
									else System.out.println("Nao faz ou fez parte de Publicacoes");
									System.out.println("\nOrientacoes: ");
									if(contributors.get(currentindex).getType().equals("Professor")) {
										if(contributors.get(currentindex) instanceof Contributors) {
											Professors current = (Professors) contributors.get(currentindex);
											if(current.getOrientation().size() > 1) {
												for(int i = 1;i < current.getOrientation().size();i++) {
													System.out.println("Orientacao: " + current.getOrientation().get(i));
													System.out.println("Projeto associado: " + current.getAssociateProject().get(i));
												}
											}
											else System.out.println("Nao ha orientacoes");
										}
									}
								}
								else System.out.println("Nao foi possivel encontrar o colaborador.");
							}
							else if(colab_entry.equals("2")) {
								System.out.println("Colaboradores: ");
								for(int i = 0;i < contributors.size();i++) {
									System.out.println("Nome: " + contributors.get(i).getName() + " - " + "Tipo: " + contributors.get(i).getType() + " - " + "Email: " + contributors.get(i).getEmail());
								}
							}
							else System.out.println("Entrada Invalida.");
						}
						else System.out.println("Nao existem colaboradores. Adicione primeiro.");
						System.out.println("Pressione enter para continuar");
						trash = input.nextLine();
					}
					//Exit
					else if(second_entry.equals("0")) break;
				}
			}
			//Projects
			else if(first_entry.equals("2")) {
				while(true) {
					if(contributors.size() <= 0) { 
						System.out.println("Nao existem colaboradores no laboratorio, portanto nao e possivel iniciar e/ou acessar projetos");
						break;
					}
					System.out.println("Criar Projeto(1)\n\nVisualizar Projeto(2)\n\nRelatorio de Producao(3)\n\nVoltar(0)\n");
					second_entry = input.nextLine();
					//Option 1 - Create
					if(second_entry.equals("1")) {
						String newfinancier,newdescription,newobjective,newpmanager = null,newmanageremail = null;
						int b_day = 0,b_month = 0,b_year = 0,f_day = 0,f_month = 0,f_year = 0;
						double new_value = 0;
						boolean hasmanager = false,correct = false;
						while(!correct) {
							System.out.println("Informe o nome do novo Projeto: ");
							projectname = input.nextLine();
							if(projects.size() <= 0) correct = true;
							else {
								boolean find = false;
								for(int i = 0;i < projects.size();i++) {
									if(projects.get(i).getTitle().equals(projectname)) {
										find = true;
										System.out.println("Projeto com esse Titulo ja existe.Tente novamente.");
										break;
									}
								}
								if(!find) correct = true;
							}
						}
						correct = false;
						System.out.println("Informe a data de inicio: \nDia: ");
						//Try
						while(!correctInput) {
							try{
								b_day = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Mes de inicio: ");
						//Try
						while(!correctInput) {
							try{
								b_month = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Ano de inicio: ");
						//Try
						while(!correctInput) {
							try{
								b_year = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Informe a data prevista para termino do projeto: ");
						System.out.println("Dia: ");
						//Try
						while(!correctInput) {
							try{
								f_day = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Mes de termino: ");
						//Try
						while(!correctInput) {
							try{
								f_month = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Ano de termino: ");
						//Try
						while(!correctInput) {
							try{
								f_year = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto. Digite novamente:");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Informe a agencia financiadora: ");
						newfinancier = input.nextLine();
						System.out.println("Informe o valor a ser financiado: ");
						//Try
						while(!correctInput) {
							try{
								new_value = input.nextDouble();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto.");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto.");
							}
							if(new_value <= 0) {
								System.out.println("Valor nao aceito. Digite novamente:");
								correctInput = false;
							}
							trash = input.nextLine();
						}
						correctInput = false;
						System.out.println("Informe o objetivo do Projeto: ");
						newobjective = input.nextLine();
						System.out.println("Informe a descricao do Projeto: ");
						newdescription = input.nextLine();
						System.out.println("Professores disponiveis: ");
						for(int i = 0;i < contributors.size();i++) {
							if(contributors.get(i).getType().equals("Professor")) {
								System.out.println("Nome: " + contributors.get(i).getName() + " - Email: " + contributors.get(i).getEmail()); 
								hasmanager = true;
							}
						}
						if(hasmanager) {
							int managerindex = -1;
							while(!correct) {
								System.out.println("Informe o Nome do professor escolhido: ");
								newpmanager = input.nextLine();
								System.out.println("Informe o Email do professor escolhido: ");
								newmanageremail = input.nextLine();
								for(int i = 0;i < contributors.size();i++) {
									if(contributors.get(i).getType().equals("Professor")) {
										if(contributors.get(i).getName().equals(newpmanager) && contributors.get(i).getEmail().equals(newmanageremail)) {
											correct = true;
											managerindex = i;
											break;
										}
									}
								}
								if(!correct)System.out.println("Professor nao encontrado.Tente novamente.");
							}
							enterproject = Project.add(projects,contributors,projectname,b_day,b_month,b_year,f_day,f_month,f_year,newfinancier,new_value,newobjective,newdescription,newpmanager,newmanageremail,managerindex);
						}
						else { 
							System.out.println("Nao ha professores disponiveis. Adicione professores ao laboratorio para criar projetos.");
							break;
						}
					}
					//Option 2 - See
					else if(second_entry.equals("2")) {
						if(projects.size() != 0) {
							System.out.println("Informe o nome do Projeto: ");
							projectname = input.nextLine();
							for(int i = 0;i < projects.size();i++) {
								if(projects.get(i).getTitle().equals(projectname)) {
									enterproject = true;
									System.out.println("Sucesso");
								}
							}
							if(!enterproject) System.out.println("Projeto nao encontrado");
						}
						else System.out.println("Nao existem Projetos.");
						System.out.println("Pressione enter para continuar");
						trash = input.nextLine();
					}
					//Option 3 - Production
					else if(second_entry.equals("3")) {
						System.out.println("Numero de colaboradores: " + contributors.size());
						for(int i = 0;i < projects.size();i++) {
							if(projects.get(i).getStatus().equals("Em elaboracao")) {
								projectsinelaboration++;
							}
							else if(projects.get(i).getStatus().equals("Em andamento")) {
								projectsongoing++;
							}
							else if(projects.get(i).getStatus().equals("Concluido")) {
								finishedprojects++;
							}
						}
						System.out.println("Numero de projetos em elaboracao: " + projectsinelaboration);
						System.out.println("Numero de projetos em andamento: " + projectsongoing);
						System.out.println("Numero de projetos concluidos: " + finishedprojects);
						System.out.println("Numero total de projetos: " + projects.size());
						System.out.println("Numero de producao academica por tipo de Producao(Publicacoes,Orientacoes): ");
						System.out.println("Publicacoes: " + publications.size());
						System.out.println("Orientacoes: " + totalorientations);
						projectsinelaboration = projectsongoing = finishedprojects = 0;
						System.out.println("Pressione enter para continuar");
						trash = input.nextLine();
					}
					//Exit
					else if(second_entry.equals("0")) break;
					//Access Project after authentication
					if(enterproject) {
						while(true) {
							String entry;
							for(int i = 0;i < projects.size();i++) {
								if(projects.get(i).getTitle().equals(projectname)) {
									currentproject = i;
								}
							}
							System.out.println("Adicionar Colaboradores(1)\n\nAtualizar Status do Projeto(2)\n\nInclusao de Producao Academica(3)\n\nDados do Projeto(4)\n\nVoltar(0)\n");
							entry = input.nextLine();
							//Option 1 - Add Contributors
							if(entry.equals("1")) {
								if(projects.get(currentproject).getStatus().equals("Em elaboracao")) {
									String contributor_name,contributor_email,option;
									System.out.println("Deseja ver os colaboradores disponiveis? Se sim,pressione 1. Se nao pressione enter");
									option = input.nextLine();
									if(option.equals("1")) {
										for(int i = 0;i < contributors.size();i++) {
											if(contributors.get(i).getType().equals("Graduacao")) {
												if(contributors.get(i).getHasProject().equals("2")) {
													System.out.println("Nome: " + contributors.get(i).getName());
													System.out.println("Tipo: " + contributors.get(i).getType());
													System.out.println("Email: " + contributors.get(i).getEmail());
													System.out.println("------------------------------------");
												}
											}
											else {
												System.out.println("Nome: " + contributors.get(i).getName());
												System.out.println("Tipo: " + contributors.get(i).getType());
												System.out.println("Email: " + contributors.get(i).getEmail());
												System.out.println("------------------------------------");
											}
										}
									}
									System.out.println("Informe o nome do colaborador que deseja adicionar: ");
									contributor_name = input.nextLine();
									System.out.println("Informe o email do colaborador que deseja adicionar: ");
									contributor_email = input.nextLine();
									int currentcontributor = -1;
									for(int i = 0;i < contributors.size();i++) {
										if((contributors.get(i).getName().equals(contributor_name)) && (contributors.get(i).getEmail().equals(contributor_email))) {
											currentcontributor = i;
										}
									}
									if(currentcontributor != -1) {
										boolean alreadyinproject = false;
										if(contributors.get(currentcontributor).getMyProjects().size() != 0) {
											for(int j = 0;j < contributors.get(currentcontributor).getMyProjects().size();j++) {
													if(contributors.get(currentcontributor).getMyProjects().get(j).getTitle().equals(projects.get(currentproject).getTitle())) {
														alreadyinproject = true;
														break;
												}
											}
										}
										if(!alreadyinproject) {
											if(contributors.get(currentcontributor).getType().equals("Graduacao")) {
												if(contributors.get(currentcontributor).getHasProject().equals("2")) {
													contributors.get(currentcontributor).setHasProject("1");
													projects.get(currentproject).getProjectContributors().add(contributors.get(currentcontributor));
													contributors.get(currentcontributor).getMyProjects().add(projects.get(currentproject));
													System.out.println("Colaborador adicionado ao projeto.");
												}
												else System.out.println("Aluno de graducao ja tem projeto, nao pode ser adicionado");
											}
											else {
												projects.get(currentproject).getProjectContributors().add(contributors.get(currentcontributor));
												contributors.get(currentcontributor).getMyProjects().add(projects.get(currentproject));
												System.out.println("Colaborador adicionado ao projeto.");
											}
										}
										else System.out.println("Colaborador ja esta no projeto.");
									}
									else System.out.println("Colaborador nao encontrado.");
								}
								else System.out.println("Projeto nao esta em elaboracao.");
								System.out.println("Pressione enter para continuar");
								trash = input.nextLine();
							}
							//Option 2 - Status
							else if(entry.equals("2")) {
								boolean verifynewstatus = false;
								String option;
								System.out.println("Status atual: " + projects.get(currentproject).getStatus());
								System.out.println("Deseja alterar o Status? Se sim, pressione 1. Se nao pressione enter");
								option = input.nextLine();
								if(option.equals("1")) {
									String newstatus;
									System.out.println("Informe o novo Status(Em andamento;Concluido): ");
									newstatus = input.nextLine();
									if(newstatus.equals("Em andamento")) {
										if(projects.get(currentproject).getStatus().equals("Em elaboracao")) {
											if((!projects.get(currentproject).getTitle().equals(null)) && (!projects.get(currentproject).getFinancier().equals(null)) && (projects.get(currentproject).getFinancierValue() != 0) && (!projects.get(currentproject).getObjective().equals(null)) && (!projects.get(currentproject).getDescription().equals(null)) && (!projects.get(currentproject).getProjectProfessor().equals(null))) {
												if(projects.get(currentproject).getProjectContributors().size() > 1){
													verifynewstatus = true;
												}
											}
											if(verifynewstatus) {
												projects.get(currentproject).setStatus(newstatus);
												System.out.println("Status atualizado");
											}
											else System.out.println("Nao foi possivel atualizar o status. Projeto nao esta em elaboracao e/ou nao cumpriu os requisitos minimos");
										}
									}
									else if(newstatus.equals("Concluido")) {
										if(projects.get(currentproject).getProjectPublications().size() > 0) {
											projects.get(currentproject).setStatus(newstatus);
											System.out.println("Status atualizado.");
										}
										else System.out.println("Nao foi possivel atualizar o status. Projeto nao esta em andamento e/ou nao contem produtividade minima");
									}
								}
								
								System.out.println("Pressione enter para continuar");
								trash = input.nextLine();
							}
							//Option 3 - Academic Production
							else if(entry.equals("3")) {
								String option;
								System.out.println("Associar Publicacao(1)\n\nVer Publicacoes associadas(2)\n\nNova Orientacao(3)\n\nVer Orientaçoes(4)\n");
								option = input.nextLine();
								if(option.equals("1")) {
									if(projects.get(currentproject).getStatus().equals("Em andamento")) {
										int currentpublicationindex = -1;
										String currentpublication;
										System.out.println("Informe o nome da publicacao que deseja associar: ");
										currentpublication = input.nextLine();
										for(int i = 0;i < publications.size();i++) {
											if(publications.get(i).getPublicationtitle().equals(currentpublication) && (publications.get(i).getAssociatedProject().equals("-1"))) {
												currentpublicationindex = i;
												break;
											}
										}
										if(currentpublicationindex != -1) {
											if(projects.get(currentproject).getYearOfConclusion() < publications.get(currentpublicationindex).getPublicationyear()) {
												System.out.println("Nao foi possivel associar. Falha no ano de publicacao.");
											}
											else if(projects.get(currentproject).getProjectPublications().size() > 0){
												boolean haspublication = false;
												for(int i = 0;i < projects.get(currentproject).getProjectPublications().size();i++) {
													if(projects.get(currentproject).getProjectPublications().get(i).getPublicationtitle().equals(currentpublication)) {
														haspublication = true;
													}
												}
												if(!haspublication) {
													projects.get(currentproject).getProjectPublications().add(publications.get(currentpublicationindex));
													publications.get(currentpublicationindex).setAssociatedProject(projects.get(currentproject).getTitle());
													System.out.println("Publicacao associada com sucesso");
												}
												else System.out.println("Publicacao ja esta associada a esse projeto.");
											}
											else
											{
												projects.get(currentproject).getProjectPublications().add(publications.get(currentpublicationindex));
												publications.get(currentpublicationindex).setAssociatedProject(projects.get(currentproject).getTitle());
												System.out.println("Publicacao associada com sucesso");
											}
										}
										else System.out.println("Publicacao nao encontrada ou Publicacao ja associada a um projeto.");
									}
									else System.out.println("Projeto nao esta em andamento.");
									
									System.out.println("Pressione enter para continuar");
									trash = input.nextLine();
								}
								else if(option.equals("2")) {
									if(projects.get(currentproject).getProjectPublications().size() > 0) {
										Publications.sort(projects.get(currentproject).getProjectPublications());
										for(int i  = 0;i < projects.get(currentproject).getProjectPublications().size();i++){
											System.out.println("Nome da publicacao: " + projects.get(currentproject).getProjectPublications().get(i).getPublicationtitle());
											System.out.println("Autor(es): ");
											for(int j = 0;j < projects.get(currentproject).getProjectPublications().get(i).getAuthors().size();j++) {
												System.out.println(projects.get(currentproject).getProjectPublications().get(i).getAuthors().get(j));
											}
											System.out.println("Nome da Conferencia de Publicacao: " + projects.get(currentproject).getProjectPublications().get(i).getConferenceName());
											System.out.println("Ano de publicacao: " + projects.get(currentproject).getProjectPublications().get(i).getPublicationyear());
											System.out.println("----------------------------------------------------");
										}
									}
									else System.out.println("Nao ha publicacoes associadas.");
									
									System.out.println("Pressione enter para continuar");
									trash = input.nextLine();
								}
								else if(option.equals("3")) {
									String mentorname,mentoremail,neworientation;
									int mentorindex = -1;
									System.out.println("Informe o nome do Orientador: ");
									mentorname = input.nextLine();
									System.out.println("Informe o Email do Orientador: ");
									mentoremail = input.nextLine();
									for(int i = 0;i < projects.get(currentproject).getProjectContributors().size();i++) {
										if(projects.get(currentproject).getProjectContributors().get(i).getName().equals(mentorname) && projects.get(currentproject).getProjectContributors().get(i).getEmail().equals(mentoremail) && projects.get(currentproject).getProjectContributors().get(i).getType().equals("Professor")){
											mentorindex = i;
										}
									}
									if(mentorindex != -1) {
										System.out.println("Informe a orientacao: ");
										neworientation = input.nextLine();
										projects.get(currentproject).getProjectOrientation().add(neworientation);
										if(contributors.get(mentorindex) instanceof Contributors) {
											Professors current = (Professors) contributors.get(mentorindex);
											System.out.println("here" + neworientation + projectname);
											current.getOrientation().add(neworientation);
											current.getAssociateProject().add(projectname);
											totalorientations++;
										}
									}
									else System.out.println("Orientador nao encontrado.");
									System.out.println("Pressione enter para continuar");
									trash = input.nextLine();
								}
								else if(option.equals("4")) {
									if(projects.get(currentproject).getProjectOrientation().size() != 0) {
										for(int i = 0;i < projects.get(currentproject).getProjectOrientation().size();i++) {
											System.out.println("- Orientacao: " + projects.get(currentproject).getProjectOrientation());
										}
									}
									else System.out.println("Nao ha orientacoes");
									
									System.out.println("Pressione enter para continuar");
									trash = input.nextLine();
								}
							}
							//Option 4 - Project Data
							else if(entry.equals("4")) {
								System.out.println("-Titulo: " + projects.get(currentproject).getTitle());
								System.out.println("-Status do Projeto: " + projects.get(currentproject).getStatus());
								System.out.println("-Descricao do Projeto: " + projects.get(currentproject).getDescription());
								System.out.println("-Objetivo do Projeto: " + projects.get(currentproject).getObjective());
								System.out.println("-Data de inicio: " + projects.get(currentproject).getDayOfBeginning() + "/" + projects.get(currentproject).getMonthOfBeginning() + "/" + projects.get(currentproject).getYearOfBeginning());
								System.out.println("\n-Data de termino: " + projects.get(currentproject).getDayOfConclusion() + "/" + projects.get(currentproject).getMonthOfConclusion() + "/" + projects.get(currentproject).getYearOfConclusion());
								System.out.println("\n-Finaciador(a): " + projects.get(currentproject).getFinancier());
								System.out.println("Valor financiado: " + projects.get(currentproject).getFinancierValue());
								System.out.println("\n-Participantes: ");
								for(int i = 0;i < projects.get(currentproject).getProjectContributors().size();i++) {
									System.out.println("Nome: " + projects.get(currentproject).getProjectContributors().get(i).getName());
									System.out.println("Tipo: " + projects.get(currentproject).getProjectContributors().get(i).getType());
									System.out.println("Email: " + projects.get(currentproject).getProjectContributors().get(i).getEmail());
									System.out.println("-----------------------------------------");
									
								}
								System.out.println("-Producao Academica: \n\nPublicacoes: ");
								if(projects.get(currentproject).getProjectPublications().size() != 0) {
									Publications.sort(projects.get(currentproject).getProjectPublications());
									for(int i = 0;i < projects.get(currentproject).getProjectPublications().size();i++) {
										System.out.println("Nome da Publicacao: " + projects.get(currentproject).getProjectPublications().get(i).getPublicationtitle());
										System.out.println("Ano de Publicacao: " + projects.get(currentproject).getProjectPublications().get(i).getPublicationyear());
									}
								}
								else System.out.println("Nao ha publicacoes.");
								System.out.println("\nOrientacoes: ");
								if(projects.get(currentproject).getProjectOrientation().size() != 0) {
									for(int j = 0;j < projects.get(currentproject).getProjectOrientation().size();j++){
										System.out.println(" - Orientacao: " + projects.get(currentproject).getProjectOrientation().get(j));
									}
								}
								else System.out.println("Nao existem orientacoes.");

								System.out.println("Pressione enter para continuar");
								trash = input.nextLine();
							}
							//Exit
							else if(entry.equals("0")) {
								enterproject = false;
								break;
							}
						}
					}
				}
			}
			//Publications
			else if(first_entry.equals("3")) {
				while(true) {
					if(contributors.size() == 0) { 
						System.out.println("Nao existem colaboradores no laboratorio, portanto nao e possivel iniciar e/ou acessar publicacoes");
						break;
					}
					System.out.println("Nova Publicacao(1)\n\nVisualizar Publicacoes(2)\n\nVoltar(0)\n");
					second_entry = input.nextLine();
					//Option 1 - Create
					if(second_entry.equals("1")) {
						String publicationname,publicationconference;
						int publicationyear = 0;
						boolean verifyexistence = false;
						System.out.println("Informe o nome da publicacao: ");
						publicationname = input.nextLine();
						System.out.println("Informe o nome da conferencia: ");
						publicationconference = input.nextLine();
						System.out.println("Informe o ano de publicacao: ");
						//Try
						while(!correctInput) {
							try{
								publicationyear = input.nextInt();
								correctInput = true;
							}
							catch(NumberFormatException e){
								System.out.println("Valor no formato incorreto.");
							}
							catch(InputMismatchException e) {
								System.out.println("Valor no formato incorreto.");
							}
							trash = input.nextLine();
						}
						correctInput = false;
						if(publications.size() != 0) {
							for(int i = 0;i < publications.size();i++){
								if((publications.get(i).getPublicationtitle().equals(publicationname))) {
									verifyexistence = true;
								}
							}
							if(!verifyexistence) {
								Publications.add(publications,contributors,publicationname,publicationconference,publicationyear,input);
							}
							else System.out.println("Publicacao ja existe.");
						}
						else {
							Publications.add(publications,contributors,publicationname,publicationconference,publicationyear,input);
						}
						System.out.println("Pressione enter para continuar");
						trash = input.nextLine();
					}
					//Option 2 - See
					else if(second_entry.equals("2")) {
						if(publications.size() != 0) {
							Publications.sort(publications);
							for(int i = 0;i < publications.size();i++) {
								System.out.println("Nome da Publicacao: " + publications.get(i).getPublicationtitle());
								System.out.println("Autor(es): ");
								for(int j = 0;j < publications.get(i).getAuthors().size();j++) {
									System.out.println(publications.get(i).getAuthors().get(j));
								}
								System.out.println("Nome da conferencia de publicacao: " + publications.get(i).getConferenceName());
								System.out.println("Ano de publicacao: " + publications.get(i).getPublicationyear());
								if(publications.get(i).getAssociatedProject().equals("-1")) System.out.println("Nao ha projetos associados.");
								else System.out.println("Projeto associado: " + publications.get(i).getAssociatedProject());

								System.out.println("----------------------------------------------------");
							}
						}
						else System.out.println("Nao existem publicacoes");
						
						System.out.println("Pressione enter para continuar");
						trash = input.nextLine();
					}
					//Exit
					else if(second_entry.equals("0")) break;
				}
			}
			else if(first_entry.equals("0")) {
				System.out.println("Fim!" + trash);
				break;
			}
		}
		input.close();
	}
}
