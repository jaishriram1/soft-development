import java.io.*;
import java.util.*;

// Contact class to represent each contact
class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // String representation of the contact
    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email;
    }
}

// ContactManager class to manage contacts
class ContactManager {
    private ArrayList<Contact> contacts;
    private static final String FILENAME = "contacts.dat";

    public ContactManager() {
        this.contacts = new ArrayList<>();
        // Load contacts from file on startup
        loadContacts();
    }

    // Add a new contact
    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts(); // Save contacts to file after adding
        System.out.println("Contact added: " + contact.getName());
    }

    // Edit an existing contact
    public void editContact(int index, Contact newContact) {
        contacts.set(index, newContact);
        saveContacts(); // Save contacts to file after editing
        System.out.println("Contact updated: " + newContact.getName());
    }

    // Delete a contact
    public void deleteContact(int index) {
        Contact removedContact = contacts.remove(index);
        saveContacts(); // Save contacts to file after deleting
        System.out.println("Contact deleted: " + removedContact.getName());
    }

    // View all contacts
    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Contact List:");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    // Get the number of contacts
    public int getContactCount() {
        return contacts.size();
    }

    // Get a contact by index
    public Contact getContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            return contacts.get(index);
        }
        return null;
    }

    // Save contacts to file
    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Load contacts from file
    @SuppressWarnings("unchecked")
    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                contacts = (ArrayList<Contact>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or error reading, just continue with an empty list
            System.out.println("No contacts found or error loading contacts: " + e.getMessage());
        }
    }
}

// Main class for the contact management program
public class ContactManagerProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        while (true) {
            System.out.println("\nContact Manager Menu:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Edit a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. View all contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, email);
                    contactManager.addContact(newContact);
                    break;
                case 2:
                    if (contactManager.getContactCount() > 0) {
                        contactManager.viewContacts();
                        System.out.print("Enter the index of the contact to edit: ");
                        int editIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline left-over
                        if (editIndex >= 0 && editIndex < contactManager.getContactCount()) {
                            System.out.print("Enter new name: ");
                            name = scanner.nextLine();
                            System.out.print("Enter new phone number: ");
                            phoneNumber = scanner.nextLine();
                            System.out.print("Enter new email address: ");
                            email = scanner.nextLine();
                            Contact editedContact = new Contact(name, phoneNumber, email);
                            contactManager.editContact(editIndex, editedContact);
                        } else {
                            System.out.println("Invalid contact index.");
                        }
                    } else {
                        System.out.println("No contacts to edit.");
                    }
                    break;
                case 3:
                    if (contactManager.getContactCount() > 0) {
                        contactManager.viewContacts();
                        System.out.print("Enter the index of the contact to delete: ");
                        int deleteIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline left-over
                        if (deleteIndex >= 0 && deleteIndex < contactManager.getContactCount()) {
                            contactManager.deleteContact(deleteIndex);
                        } else {
                            System.out.println("Invalid contact index.");
                        }
                    } else {
                        System.out.println("No contacts to delete.");
                    }
                    break;
                case 4:
                    contactManager.viewContacts();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
