package it.beije.oort.bassanelli.exercises.phonebook_generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateManager {

	public static Map<String, Integer> searchDuplicates(List<Contact> list, String field) {

		Map<String, Integer> map = new HashMap<String, Integer>();

		// int duplicateCount = 0;

		for (Contact contact : list) {

			StringBuilder key;

			switch (field.toUpperCase()) {
			default:
			case "NOME":
				key = new StringBuilder(contact.getName());
				break;
			case "COGNOME":
				key = new StringBuilder(contact.getSurname());
				break;
			case "TELEFONO":
				key = new StringBuilder(contact.getMobile());
				break;
			case "EMAIL":
				key = new StringBuilder(contact.getEmail());
				break;
			}

			if (map.containsKey(key.toString())) {
//				if (map.get(key.toString()) == 1) {
//					duplicateCount++;
//				}
				map.put(key.toString(), map.get(key.toString()) + 1);
			} else {
				map.put(key.toString(), 1);
			}
		}

		return map;

	}

	public static List<Contact> joinContactsWithoutReintegration(List<Contact> list, String field) {

		List<Contact> listWithoutDuplicates = new ArrayList<Contact>();
		Map<String, Integer> contactsIndex = new HashMap<String, Integer>();

		for (Contact contact : list) {

			StringBuilder key;

			switch (field.toUpperCase()) {
			default:
			case "NOME":
				key = new StringBuilder(contact.getName());
				break;
			case "COGNOME":
				key = new StringBuilder(contact.getSurname());
				break;
			case "TELEFONO":
				key = new StringBuilder(contact.getMobile());
				break;
			case "EMAIL":
				key = new StringBuilder(contact.getEmail());
				break;
			}

			if (contactsIndex.containsKey(key.toString())) {
				int index = contactsIndex.get(key.toString());
				Contact currentContact = listWithoutDuplicates.get(index);

				switch (field.toUpperCase()) {

				case "NOME":

					if (currentContact.getSurname().equals("")) {
						currentContact.setName(contact.getSurname());
					}

					if (currentContact.getMobile().equals("")) {
						currentContact.setName(contact.getMobile());
					}

					if (currentContact.getEmail().equals("")) {
						currentContact.setName(contact.getEmail());
					}

					break;

				case "SURNAME":

					if (currentContact.getName().equals("")) {
						currentContact.setName(contact.getName());
					}

					if (currentContact.getMobile().equals("")) {
						currentContact.setName(contact.getMobile());
					}

					if (currentContact.getEmail().equals("")) {
						currentContact.setName(contact.getEmail());
					}

					break;

				case "TELEFONO":

					if (currentContact.getName().equals("")) {
						currentContact.setName(contact.getName());
					}

					if (currentContact.getSurname().equals("")) {
						currentContact.setName(contact.getSurname());
					}

					if (currentContact.getEmail().equals("")) {
						currentContact.setName(contact.getEmail());
					}

					break;

				case "EMAIL":

					if (currentContact.getName().equals("")) {
						currentContact.setName(contact.getName());
					}

					if (currentContact.getSurname().equals("")) {
						currentContact.setName(contact.getSurname());
					}

					if (currentContact.getMobile().equals("")) {
						currentContact.setName(contact.getMobile());
					}

					break;
				}

			} else {
				listWithoutDuplicates.add(contact);
				contactsIndex.put(key.toString(), listWithoutDuplicates.size() - 1);
			}

		}

		return listWithoutDuplicates;

	}

	public static List<Contact> joinContacts(List<Contact> list, String field) {

		List<Contact> listWithoutDuplicates = new ArrayList<Contact>();
		Map<String, List<Integer>> contactsIndex = new HashMap<String, List<Integer>>();

		for (Contact contact : list) {

			StringBuilder key;

			switch (field.toUpperCase()) {
			default:
			case "NOME":
				key = new StringBuilder(contact.getName());
				break;
			case "COGNOME":
				key = new StringBuilder(contact.getSurname());
				break;
			case "TELEFONO":
				key = new StringBuilder(contact.getMobile());
				break;
			case "EMAIL":
				key = new StringBuilder(contact.getEmail());
				break;
			}

			if (contactsIndex.containsKey(key.toString())) {
				boolean isJoinedContact = false;

				boolean isNameUpdated = false;
				boolean isSurnameUpdated = false;
				boolean isMobileUpdated = false;
				boolean isEmailUpdated = false;

				for (int index : contactsIndex.get(key.toString())) {

					Contact currentContact = listWithoutDuplicates.get(index);

					switch (field.toUpperCase()) {
					default:
					case "NOME":

						isSurnameUpdated = currentContact.getSurname().equals("") || contact.getSurname().equals("")
								|| currentContact.getSurname().equals(contact.getSurname());

						isMobileUpdated = currentContact.getMobile().equals("") || contact.getMobile().equals("")
								|| currentContact.getMobile().equals(contact.getMobile());

						isEmailUpdated = currentContact.getEmail().equals("") || contact.getEmail().equals("")
								|| currentContact.getEmail().equals(contact.getEmail());

						if (isSurnameUpdated && isMobileUpdated && isEmailUpdated) {
							isJoinedContact = true;
							if (currentContact.getSurname().isEmpty()) {
								currentContact.setSurname(contact.getSurname());
							}
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(contact.getMobile());
							}
							if (currentContact.getEmail().isEmpty()) {
								currentContact.setEmail(contact.getEmail());
							}
							break;
						}

						break;
					case "COGNOME":

						isNameUpdated = currentContact.getName().equals("") || contact.getName().equals("")
								|| currentContact.getName().equals(contact.getName());

						isMobileUpdated = currentContact.getMobile().equals("") || contact.getMobile().equals("")
								|| currentContact.getMobile().equals(contact.getMobile());

						isEmailUpdated = currentContact.getEmail().equals("") || contact.getEmail().equals("")
								|| currentContact.getEmail().equals(contact.getEmail());

						if (isNameUpdated && isMobileUpdated && isEmailUpdated) {
							isJoinedContact = true;
							if (currentContact.getName().isEmpty()) {
								currentContact.setName(contact.getName());
							}
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(contact.getMobile());
							}
							if (currentContact.getEmail().isEmpty()) {
								currentContact.setEmail(contact.getEmail());
							}
							break;
						}

						break;
					case "TELEFONO":

						isNameUpdated = currentContact.getName().equals("") || contact.getName().equals("")
								|| currentContact.getName().equals(contact.getName());

						isSurnameUpdated = currentContact.getSurname().equals("") || contact.getSurname().equals("")
								|| currentContact.getSurname().equals(contact.getSurname());

						isEmailUpdated = currentContact.getEmail().equals("") || contact.getEmail().equals("")
								|| currentContact.getEmail().equals(contact.getEmail());

						if (isNameUpdated && isSurnameUpdated && isEmailUpdated) {
							isJoinedContact = true;
							if (currentContact.getName().isEmpty()) {
								currentContact.setName(contact.getName());
							}
							if (currentContact.getSurname().isEmpty()) {
								currentContact.setSurname(contact.getSurname());
							}
							if (currentContact.getEmail().isEmpty()) {
								currentContact.setEmail(contact.getEmail());
							}
							break;
						}

						break;
					case "EMAIL":

						isNameUpdated = currentContact.getName().equals("") || contact.getName().equals("")
								|| currentContact.getName().equals(contact.getName());

						isSurnameUpdated = currentContact.getSurname().equals("") || contact.getSurname().equals("")
								|| currentContact.getSurname().equals(contact.getSurname());

						isMobileUpdated = currentContact.getMobile().equals("") || contact.getMobile().equals("")
								|| currentContact.getMobile().equals(contact.getMobile());

						if (isNameUpdated && isSurnameUpdated && isMobileUpdated) {
							isJoinedContact = true;
							if (currentContact.getName().isEmpty()) {
								currentContact.setName(contact.getName());
							}
							if (currentContact.getSurname().isEmpty()) {
								currentContact.setSurname(contact.getSurname());
							}
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(contact.getMobile());
							}
							break;
						}

						break;
					}

				}

				if (!isJoinedContact) {
					listWithoutDuplicates.add(contact);
					List<Integer> tempList = contactsIndex.get(key.toString());
					tempList.add(listWithoutDuplicates.size() - 1);
				}

			} else {
				listWithoutDuplicates.add(contact);
				List<Integer> tempList = new ArrayList<Integer>();
				tempList.add(listWithoutDuplicates.size() - 1);
				contactsIndex.put(key.toString(), tempList);
			}

		}

		return listWithoutDuplicates;

	}

	public static List<Contact> joinContattiWithAlias(List<Contact> list, String field) {

		List<Contact> listWithoutDuplicates = new ArrayList<Contact>();
		Map<String, Integer> contactsIndex = new HashMap<String, Integer>();

		for (Contact contact : list) {

			StringBuilder key;

			switch (field.toUpperCase()) {
			default:
			case "NOME":
				key = new StringBuilder(contact.getName());
				break;
			case "COGNOME":
				key = new StringBuilder(contact.getSurname());
				break;
			case "TELEFONO":
				key = new StringBuilder(contact.getMobile());
				break;
			case "EMAIL":
				key = new StringBuilder(contact.getEmail());
				break;
			}

			if (contactsIndex.containsKey(key.toString())) {

				boolean isNameUpdated = false;
				boolean isSurnameUpdated = false;
				boolean isMobileUpdated = false;
				boolean isEmailUpdated = false;

				if (contactsIndex.containsKey(key.toString())) {

					int index = contactsIndex.get(key.toString());
					Contact currentContact = listWithoutDuplicates.get(index);

					switch (field.toUpperCase()) {
					default:
					case "NOME":

						isSurnameUpdated = currentContact.getSurname().equals("") || contact.getSurname().equals("")
								|| currentContact.getSurname().equals(contact.getSurname());

						isMobileUpdated = currentContact.getMobile().equals("") || contact.getMobile().equals("")
								|| currentContact.getMobile().equals(contact.getMobile());

						isEmailUpdated = currentContact.getEmail().equals("") || contact.getEmail().equals("")
								|| currentContact.getEmail().equals(contact.getEmail());

						if (isSurnameUpdated && isMobileUpdated && isEmailUpdated) {
							if (currentContact.getSurname().isEmpty()) {
								currentContact.setSurname(contact.getSurname());
							}
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(contact.getMobile());
							}
							if (currentContact.getEmail().isEmpty()) {
								currentContact.setEmail(contact.getEmail());
							}
							break;
						} else {

						}

						break;
					case "COGNOME":

						isNameUpdated = currentContact.getName().equals("") || contact.getName().equals("")
								|| currentContact.getName().equals(contact.getName());

						isMobileUpdated = currentContact.getMobile().equals("") || contact.getMobile().equals("")
								|| currentContact.getMobile().equals(contact.getMobile());

						isEmailUpdated = currentContact.getEmail().equals("") || contact.getEmail().equals("")
								|| currentContact.getEmail().equals(contact.getEmail());

						if (isNameUpdated && isMobileUpdated && isEmailUpdated) {
							if (currentContact.getName().isEmpty()) {
								currentContact.setName(contact.getName());
							}
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(contact.getMobile());
							}
							if (currentContact.getEmail().isEmpty()) {
								currentContact.setEmail(contact.getEmail());
							}
							break;
						}

						break;
					case "TELEFONO":

						isNameUpdated = currentContact.getName().equals("") || contact.getName().equals("")
								|| currentContact.getName().equals(contact.getName());

						isSurnameUpdated = currentContact.getSurname().equals("") || contact.getSurname().equals("")
								|| currentContact.getSurname().equals(contact.getSurname());

						isEmailUpdated = currentContact.getEmail().equals("") || contact.getEmail().equals("")
								|| currentContact.getEmail().equals(contact.getEmail());

						if (isNameUpdated && isSurnameUpdated && isEmailUpdated) {
							if (currentContact.getName().isEmpty()) {
								currentContact.setName(contact.getName());
							}
							if (currentContact.getSurname().isEmpty()) {
								currentContact.setSurname(contact.getSurname());
							}
							if (currentContact.getEmail().isEmpty()) {
								currentContact.setEmail(contact.getEmail());
							}
							break;
						}

						break;
					case "EMAIL":

						isNameUpdated = currentContact.getName().equals("") || contact.getName().equals("")
								|| currentContact.getName().equals(contact.getName());

						isSurnameUpdated = currentContact.getSurname().equals("") || contact.getSurname().equals("")
								|| currentContact.getSurname().equals(contact.getSurname());

						isMobileUpdated = currentContact.getMobile().equals("") || contact.getMobile().equals("")
								|| currentContact.getMobile().equals(contact.getMobile());

						if (isNameUpdated && isSurnameUpdated /* && isMobileUpdated */) {
							if (currentContact.getName().isEmpty()) {
								currentContact.setName(contact.getName());
							}
							if (currentContact.getSurname().isEmpty()) {
								currentContact.setSurname(contact.getSurname());
							}
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(contact.getMobile());
							}
							break;
						} else {
							StringBuilder builder = new StringBuilder().append(contact.getName()).append(" ")
									.append(contact.getSurname());
							currentContact.getAlias().add(builder.toString().trim());
						}
						
						if(isMobileUpdated) {
							if (currentContact.getMobile().isEmpty()) {
								currentContact.setMobile(currentContact.getMobile());
							}
						}

						break;
					}

				}

			} else {
				listWithoutDuplicates.add(contact);
				contactsIndex.put(key.toString(), listWithoutDuplicates.size() - 1);
			}

		}

		return listWithoutDuplicates;

	}

}
