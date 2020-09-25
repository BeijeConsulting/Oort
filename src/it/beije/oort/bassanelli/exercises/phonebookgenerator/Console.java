package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		boolean exitFromApplication = false;

		String command;
		int currentCommand = 0;

		List<Contact> records;

		List<Contact> contactsList = new ArrayList<Contact>();

		while (!exitFromApplication) {

			System.out.println("- PHONEBOOK APPLICATION -");
			System.out.println("1) Add contact");
			System.out.println("2) Database client");
			System.out.println("3) Exit");

			System.out.print("Command: ");
			command = s.nextLine();

			switch (command) {
			case "1":

				Contact contact = new Contact();

				System.out.println("- ADD CONTACT - ");
				System.out.print("Name: ");
				contact.setName(s.nextLine());
				System.out.print("Surname: ");
				contact.setSurname(s.nextLine());
				System.out.print("Mobile: ");
				contact.setMobile(s.nextLine());
				System.out.print("Email: ");
				contact.setEmail(s.nextLine());
				System.out.println(contact.toString());

				System.out.println("- OPTIONS -");
				System.out.println("1) Save");
				System.out.println("2) Edit");
				System.out.println("3) Cancel");
				System.out.println("4) Exit");

				System.out.print("Command: ");
				command = s.nextLine();

				switch (Integer.parseInt(command)) {
				case 1:
					contactsList.add(contact);

					System.out.println("Saved");
					break;
				case 2:

					break;
				case 4:
					System.out.println("Exit");
					exitFromApplication = true;
					break;
				case 3:
				default:
					System.out.println("Cancel");
					break;
				}

				break;

			case "2":

				boolean exitFromDatabaseClient = false;

				while (!exitFromDatabaseClient) {

					System.out.println("- DATABASE CLIENT -");
					System.out.println("1) Search contact");
					System.out.println("2) View all contacts");
					System.out.println("3) Add contact");
					System.out.println("4) Edit contact");
					System.out.println("5) Delete contact");
					System.out.println("6) Export contacts");
					System.out.println("7) Back");

					System.out.print("Command: ");
					command = s.nextLine();

					switch (command) {
					case "1":

						boolean exitFromSearchContact = false;

						while (!exitFromSearchContact) {

							System.out.println("- SEARCH CONTACT -");
							System.out.println("1) Name");
							System.out.println("2) Surname");
							System.out.println("3) Mobile");
							System.out.println("4) Email");
							System.out.println("5) Back");

							System.out.print("Choose field: ");
							command = s.nextLine();

							switch (command) {
							case "1":

								System.out.print("Name: ");
								command = s.nextLine();

								records = DBManager.searchContact("name", command);
								Phonebook.printAllRecords(records, "ID;NAME;SURNAME;MOBILE;EMAIL");
								break;

							case "2":

								System.out.print("Surname: ");
								command = s.nextLine();

								records = DBManager.searchContact("surname", command);
								Phonebook.printAllRecords(records, "ID;SURNAME;NAME;MOBILE;EMAIL");
								break;

							case "3":
								System.out.print("Mobile: ");
								command = s.nextLine();

								records = DBManager.searchContact("mobile", command);
								Phonebook.printAllRecords(records, "ID;MOBILE;NAME;SURNAME;EMAIL");
								break;

							case "4":
								System.out.print("Email: ");
								command = s.nextLine();

								records = DBManager.searchContact("email", command);
								Phonebook.printAllRecords(records, "ID;EMAIL;NAME;SURNAME;MOBILE");
								break;

							case "5":

								exitFromSearchContact = true;
								break;

							default:

								System.out.println("Command not valid");
								break;
							}

						}

						break;
					case "2":

						boolean exitFromViewContacts = false;

						while (!exitFromViewContacts) {

							System.out.println("- VIEW CONTACTS -");
							System.out.println("1) View pages");
							System.out.println("2) View by initial");
							System.out.println("3) Back");

							System.out.print("Choose: ");
							command = s.nextLine();

							switch (command) {
							case "1":

								records = DBManager.readFromDatabase();
								Phonebook.sortByField(records, "ID");

								final int STEP = 5;

								int index = 0;
								int size = STEP;
								int currentPage = 1;
								int pages = (records.size() / size) + 1;

								boolean exitFromViewPages = false;

								while (!exitFromViewPages && !records.isEmpty()) {

									for (int i = index; i < size; i++) {
										System.out.println(records.get(i).toString("ID;NAME;SURNAME;MOBILE;EMAIL"));
									}
									System.out.println("Page: " + currentPage + " Total pages: " + pages);

									System.out.print("Command [<, >, q]: ");
									command = s.nextLine();

									switch (command.toLowerCase()) {

									case ">":

										currentPage++;
										if (currentPage == pages) {
											index += STEP;
											size += records.size() % size;
											currentPage = pages;
										} else if (currentPage >= pages) {
											currentPage = pages;
										} else {
											index += STEP;
											size += STEP;
										}
										break;

									case "<":

										currentPage--;
										if (currentPage <= 0) {
											index = 0;
											size = STEP;
											currentPage = 1;
										} else {
											index -= STEP;
											size -= STEP;
										}
										break;

									case "q":
										exitFromViewPages = true;
										break;

									default:
										System.out.println("Command not valid");
										break;
									}

								}

								break;

							case "2":
								break;

							case "3":
								exitFromViewContacts = true;
								break;

							default:

								System.out.println("Command not valid");
								break;
							}
						}
						break;
						
					case "3":

						boolean exitFromAddContact = false;

						while (!exitFromAddContact) {

							contact = new Contact();

							System.out.println("- ADD CONTACT -");
							System.out.print("Name: ");
							contact.setName(s.nextLine());
							System.out.print("Surname: ");
							contact.setSurname(s.nextLine());
							System.out.print("Mobile: ");
							contact.setMobile(s.nextLine());
							System.out.print("Email: ");
							contact.setEmail(s.nextLine());

							if (!(contact.getName().isEmpty() && contact.getSurname().isEmpty()
									&& contact.getMobile().isEmpty() && contact.getEmail().isEmpty())) {

								System.out.println("1) Ok");
								System.out.println("2) Cancel");

								System.out.print("Command: ");
								command = s.nextLine();

								switch (command) {
								case "1":
									DBManager.addContact(contact);
									break;
								case "2":
									exitFromAddContact = true;
									break;
								default:
									System.out.println("Command not valid");
									break;
								}

							}
						}
						break;
					case "4":
						
						boolean exitFromEditContact = false;
						
						while(!exitFromEditContact) {
							
							System.out.println("- EDIT CONTACT -");
							System.out.print("Insert ID: ");
							
							String id = s.nextLine();
							
							contact = DBManager.getContactById(Integer.valueOf(id));
							
							System.out.println("Id: " + contact.getId());
							System.out.println("Name: " + contact.getName());
							System.out.println("Surame: " + contact.getSurname());
							System.out.println("Mobile: " + contact.getMobile());
							System.out.println("Email: " + contact.getEmail());
							
							System.out.print("Name: ");
							contact.setName(s.nextLine());
							System.out.print("Surname: ");
							contact.setSurname(s.nextLine());
							System.out.print("Mobile: ");
							contact.setMobile(s.nextLine());
							System.out.print("Email: ");
							contact.setEmail(s.nextLine());
							
							if (!(contact.getName().isEmpty() && contact.getSurname().isEmpty()
									&& contact.getMobile().isEmpty() && contact.getEmail().isEmpty())) {

								System.out.println("1) Ok");
								System.out.println("2) Cancel");

								System.out.print("Command: ");
								command = s.nextLine();

								switch (command) {
								case "1":
									DBManager.editContact(contact);
									break;
								case "2":
									exitFromAddContact = true;
									break;
								default:
									System.out.println("Command not valid");
									break;
								}

							}
							
						}
						
						break;
					case "5":
						
						boolean exitFromDeleteContact = false;
						
						while(!exitFromDeleteContact) {
							
							System.out.println("- DELETE CONTACT -");
							System.out.print("Insert ID: ");
							
							
						}
						
						break;
					case "6":
						break;
					case "7":
						exitFromDatabaseClient = true;
						break;
					default:
						System.out.println("Command not valid");
						break;
					}
				}

				break;

			case "3":
				System.out.println("- EXIT -");
				exitFromApplication = true;
				break;
			default:
				System.out.println("Command not valid");
				break;
			}

		}

		s.close();

		System.gc();
		System.out.println("BYE!!");
		System.exit(0);

	}

}
