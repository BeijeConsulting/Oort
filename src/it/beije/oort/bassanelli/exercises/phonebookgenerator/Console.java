package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.io.File;
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
								Phonebook.sortByField(records, "NAME");
								Phonebook.printAllRecords(records, "ID;NAME;SURNAME;MOBILE;EMAIL");
								break;

							case "2":

								System.out.print("Surname: ");
								command = s.nextLine();

								records = DBManager.searchContact("surname", command);
								Phonebook.sortByField(records, "SURNAME");
								Phonebook.printAllRecords(records, "ID;SURNAME;NAME;MOBILE;EMAIL");
								break;

							case "3":

								System.out.print("Mobile: ");
								command = s.nextLine();

								records = DBManager.searchContact("mobile", command);
								Phonebook.sortByField(records, "MOBILE");
								Phonebook.printAllRecords(records, "ID;MOBILE;NAME;SURNAME;EMAIL");
								break;

							case "4":

								System.out.print("Email: ");
								command = s.nextLine();

								records = DBManager.searchContact("email", command);
								Phonebook.sortByField(records, "EMAIL");
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

								// records = DBManager.readFromDatabase();
								records = HDBManager.readFromDatabase();
								Phonebook.sortByField(records, "ID");

								final int STEP = 15;

								int index = 0;
								int size = STEP;
								int currentPage = 1;
								int pages = (records.size() / size) + 1;

								boolean overPage = false;

								boolean exitFromViewPages = false;

								while (!exitFromViewPages && !records.isEmpty()) {

									for (int i = index; i < size; i++) {
										System.out.println(/* "Index: " + i + " "+ */records.get(i)
												.toString("ID;NAME;SURNAME;MOBILE;EMAIL"));
									}

									System.out.println("Page: " + currentPage + " Total pages: " + pages);
									System.out.print("Command [< (back), > (forward), q (quit)]: ");
									command = s.nextLine();

									switch (command.toLowerCase()) {

									case ">":

										currentPage++;
										if (currentPage == pages) {
											index += STEP;
											size += records.size() % size;
											currentPage = pages;
											overPage = true;
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
										} else if (overPage == true) {
											index = STEP * (currentPage - 1);
											size = index + STEP;
											overPage = false;
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

						do {

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
						} while (!exitFromAddContact);

						break;
					case "4":

						boolean exitFromEditContact = false;

						do {

							System.out.println("- EDIT CONTACT -");
							System.out.print("Insert ID: ");

							String id = s.nextLine();

							contact = DBManager.getContactById(Integer.parseInt(id));

							if (contact == null) {
								System.out.println("Not found!");
							} else {
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
										System.out.println("Done!");
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
						} while (!exitFromEditContact);

						break;
					case "5":

						boolean exitFromDeleteContact = false;

						do {

							System.out.println("- DELETE CONTACT -");
							System.out.print("Insert ID: ");

							String id = s.nextLine();

							contact = DBManager.getContactById(Integer.valueOf(id));

							if (contact == null) {
								System.out.println("Not found!");
							} else {
								System.out.println("Id: " + contact.getId());
								System.out.println("Name: " + contact.getName());
								System.out.println("Surame: " + contact.getSurname());
								System.out.println("Mobile: " + contact.getMobile());
								System.out.println("Email: " + contact.getEmail());

								System.out.println("1) Delete");
								System.out.println("2) Cancel");

								System.out.print("Command: ");
								command = s.nextLine();

								switch (command) {
								case "1":

									DBManager.deleteById(Integer.parseInt(id));
									System.out.println("Done!");
									break;

								case "2":

									exitFromDeleteContact = true;
									break;

								default:

									System.out.println("Command not valid");
									break;
								}
							}

						} while (!exitFromDeleteContact);

						break;
					case "6":

						boolean exitFromExportContacts = false;
						
						String path = "/temp/rubrica/";

						do {

							System.out.println("- EXPORT CONTACTS -");
							System.out.println("1) File CSV");
							System.out.println("2) File XML");
							System.out.println("3) Back");

							System.out.print("Command: ");
							command = s.nextLine();

							switch (command) {
							case "1":

								System.out.print("File name: ");
								command = s.nextLine();

								File csvFile = new File(path + command + ".csv");

								records = DBManager.readFromDatabase();

								try {
									
									if (!csvFile.exists()) {
										csvFile.createNewFile();
									}
									
									Phonebook.writeCsvFile(records, "NOME;COGNOME;TELEFONO;EMAIL", path + command + ".csv",
											false);
								} catch (IOException e) {
									e.printStackTrace();
								}

								break;
								
							case "2":

								System.out.print("File name: ");
								command = s.nextLine();
								
								File xmlFile = new File(path + command + ".xml");

								records = DBManager.readFromDatabase();

								try {
									
									if (!xmlFile.exists()) {
										xmlFile.createNewFile();
									}
									
									Phonebook.writeXmlFile(records, path + command + ".xml");
								} catch (Exception e) {
									e.printStackTrace();
								}

								System.out.println("Done!");

								break;
							case "3":
								exitFromExportContacts = true;
							default:
								System.out.println("Command not valid");
								break;
							}

						} while (!exitFromExportContacts);

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
