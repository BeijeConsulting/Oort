package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class PhonebookGenerator {

	public static void main(String[] args) throws IOException, Exception {

		File file = new File("/temp/records.csv");
		FileWriter writer = new FileWriter(file);

		writer.write("NOMI;COGNOMI;TELEFONO;EMAIL\n");

		List<String> namesList = listGenerator("/temp/nomi_italiani.txt");
		List<String> surnamesList = listGenerator("/temp/cognomi.txt");
		List<String> prefixsList = listGenerator("/temp/prefissi.txt");
		List<String> domainsList = listGenerator("/temp/domini.txt");

		int namesSize = namesList.size();
		int surnamesSize = surnamesList.size();
		int prefixsSize = prefixsList.size();
		int domainsSize = domainsList.size();

		List<String> recordsList = new ArrayList<String>();

		Random r = new Random();
		String name = "";
		String surname = "";
		String mobile = "";
		String domain = "";
		String email = "";

		System.out.println("Start: " + LocalTime.now());

		Contact contact = new Contact();

		for (int i = 0; i < 1_000_000; i++) {
		
			name = namesList.get(r.nextInt(namesSize));
			name = name.trim();
			name = toUpper(name);

			surname = surnamesList.get(r.nextInt(surnamesSize));
			surname = surname.trim();
			surname = toUpper(surname);

			mobile = mobileGenerator(prefixsList);

			domain = domainsList.get(r.nextInt(domainsSize));

			email = mailGenerator(name, surname, domain);

			contact.setName(name);
			contact.setSurname(surname);
			contact.setMobile(mobile);
			contact.setEmail(email);

			writer.write(contact.toRow());
		

			// recordsList.add(record);
		}

		System.out.println("Done records: " + LocalTime.now());

		writer.flush();
		writer.close();

		System.out.println("Done file: " + LocalTime.now());

	}

	public static List<String> listGenerator(String path) throws IOException, Exception {

		List<String> list = new ArrayList<>();

		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while (bufferedReader.ready()) {
			String row = bufferedReader.readLine();
			list.add(row);
		}

		bufferedReader.close();

		return list;
	}

	public static String mobileGenerator(List<String> prefixList) {
		Random r = new Random();
		
		char[] numbers = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	
		StringBuilder mobile = new StringBuilder(prefixList.get(r.nextInt(5)));
		
		for(int i = 0; i < 7; i++) {
			mobile.append(numbers[r.nextInt(numbers.length)]);
		}
		
		return mobile.toString();
	}

	public static String toUpper(String str) {
		String firstLetter = "" + str.charAt(0);
		StringBuilder finalWord = new StringBuilder();
		finalWord.append(firstLetter.toUpperCase()).append(str.substring(1));
		return finalWord.toString();
	}

	public static String mailGenerator(String name, String surname, String domain) {
		Random r = new Random();

		StringBuilder email = new StringBuilder();

		boolean nameEmpty = false;
		boolean surnameEmpty = false;

		String separetor = "";
		String prefixNumber = "";

		if (r.nextInt(5) + 1 == 1) {
			name = "";
			nameEmpty = true;
		}
		if (r.nextInt(3) + 1 == 1) {
			surname = "";
			surnameEmpty = true;
		}

		if (nameEmpty ^ surnameEmpty) {

			if (r.nextInt(10) + 1 != 1) {

				int num = r.nextInt(100);
				prefixNumber = (num > 10 ? "" + num : "0" + num);

			}
		}

		if (nameEmpty && surnameEmpty) {
			int randomNumber = r.nextInt((20 - 6) + 1) + 6;

			for (int i = 0; i < randomNumber; i++) {
				char c = (char) (r.nextInt((122 - 97) + 1) + 97);
				name += c;
			}

		}

		if (!nameEmpty && !surnameEmpty) {

			int num = r.nextInt(10) + 1;

			if (num == 1 || num == 2) {
				name = "" + name.charAt(0);
			} else if (num >= 3 && num <= 5) {
				for (int i = 0; i < name.length(); i++) {
					switch (name.charAt(i)) {
					case 'a':
					case 'e':
					case 'i':
					case 'o':
					case 'u':
						name = name.substring(0, i + 1);
						i = name.length();
						break;
					default:
						break;
					}
				}
			}

			num = r.nextInt(4) + 1;

			if (num == 1) {
				String temp = name;
				name = surname;
				surname = temp;
			}

			num = r.nextInt(10) + 1;

			if (num >= 1 && num <= 3) {
				separetor = "";
			} else if (num >= 4 && num <= 7) {
				separetor = ".";
			} else {
				separetor = "-";
			}

			num = r.nextInt(5) + 1;

			if (num == 1) {
				for (int i = 0; i < name.length(); i++) {
					switch (name.charAt(i)) {
					case 'a':
						name = name.replace('a', '4');
						break;
					case 'e':
						name = name.replace('e', '3');
						break;
					case 'i':
						name = name.replace('i', '1');
						break;
					case 'o':
						name = name.replace('o', '0');
						break;
					default:
						break;
					}
				}

				for (int i = 0; i < surname.length(); i++) {
					switch (surname.charAt(i)) {
					case 'a':
						surname = surname.replace('a', '4');
						break;
					case 'e':
						surname = surname.replace('e', '3');
						break;
					case 'i':
						surname = surname.replace('i', '1');
						break;
					case 'o':
						surname = surname.replace('o', '0');
						break;
					default:
						break;
					}
				}
			}
		}

		email.append(name.replace(" ", "_")).append(separetor).append(surname.replace(" ", "_")).append(prefixNumber)
				.append("@").append(domain);

		return email.toString().toLowerCase();
	}
}
