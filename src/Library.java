
/**
 * A simulated library which contains list of students and list of books.
 * 
 * Provides methods for: 
 * 1. Load books from file.
 * 2. Load students from file.
 * 3. Search book by ISBN .
 * 4. Issue book to student.
 * 5. Return book from student.
 * 6. View all books.
 * 8. View all students. 
 * 9. Write result to the file back.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Library {
	private String name;
	private Book bookList[]; // array of book object
	private Student studentList[]; // array of student object
	Scanner sc = null;

	/*
	 * Loads books from the given file and adds them to the bookList array.
	 * 
	 * @param file name of file to load
	 */
	public void loadBooks(String file) {
		try {

			// load file using scanner
			sc = new Scanner(new File(file));

			// get no. of lines in the file
			int lines = 0;
			while (sc.hasNext()) {
				sc.nextLine();
				lines = lines + 1; // increment line
			}

			// make book array as per lines
			bookList = new Book[lines];

			int index = 0;
			sc = new Scanner(new File(file));

			// iterate through stream and load data to array
			while (sc.hasNext()) {

				// split each line with space
				String[] str = sc.nextLine().split("\\s+");

				// get properties from string array
				int isbn = Integer.parseInt(str[0]);
				String name = str[1];
				String author = str[2];
				Double price = Double.parseDouble(str[3]);

				// make new book object
				Book book = new Book(isbn, name, author, price);

				// add book to the array
				bookList[index] = book;
				index++; // increment loop variable
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (sc != null)
				sc.close();
		}

	}

	/**
	 * Loads student from given file and add them to the studentList array
	 * 
	 * @param file
	 *            name of file to load
	 */
	public void loadStudents(String file) {
		try {

			// load file using scanner
			sc = new Scanner(new File(file));

			// get no. of lines in the file
			int lines = 0;
			while (sc.hasNext()) {
				sc.nextLine();
				lines = lines + 1; // increment line
			}

			// make student array as per lines
			studentList = new Student[lines];

			int index = 0;
			sc = new Scanner(new File(file));

			// iterate through stream and load data to array
			while (sc.hasNext()) {

				// split each line with space
				String[] str = sc.nextLine().split("\\s+");

				// get properties from string array
				int sId = Integer.parseInt(str[0]);
				String sName = str[1];
				String sClass = str[2];

				// make new book object
				Student student = new Student(sId, sName, sClass);

				// add book to the array
				studentList[index] = student;
				index++; // increment loop variable
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (sc != null)
				sc.close();
		}

	}

	/**
	 * Display all the books
	 */
	public void viewAllBooks() {

		System.out.println("------------------------------------------");
		System.out.println("ISBN\tTitle\tAuthor\tPrice\tStatus");
		System.out.println("------------------------------------------");

		int i = 0;
		while (i < bookList.length) {
			System.out.println(bookList[i].toString());
			i = i + 1; // iterate loop variable
		}
		System.out.println("------------------------------------------");
	}

	/**
	 * Display all the students
	 */
	public void viewAllStudents() {

		System.out.println("------------------------------------------");
		System.out.println("Id\tName\tClass\tBooks");
		System.out.println("------------------------------------------");

		int i = 0;
		while (i < studentList.length) {
			System.out.println(studentList[i].toString());
			i = i + 1; // iterate loop variable
		}
		System.out.println("------------------------------------------");
	}

	/**
	 * Issue given book to the student specified.
	 * 
	 * @param isbn
	 *            isbn no. of book
	 */
	public void issueBook(int isbn) {

		Scanner sc = new Scanner(System.in);

		// find book by given isbn
		Book book = findBookByIsbn(isbn);

		if (book == null) {
			System.out.println("Book not found for ISBN.");
			return;
		}

		// check for book status
		if (!book.isIssued()) {

			// if book is free then get student Id
			System.out.println("\nBook available.");
			System.out.println("Enter student Id: ");
			int sId = sc.nextInt();

			// find student by given student id
			Student student = findStudentById(sId);

			if (student != null) {

				System.out.println("limit " + student.getLimit());

				// check for limit of book
				if (student.getLimit() > 2) {

					// book limit exceed
					System.out.println("Book limit exceed.");

				} else {
					// get empty index of book array
					int index = student.emptyIndex();
					System.out.println("index " + student.emptyIndex());

					// issue book
					book.issue();

					// add book to student's book list
					student.getBooks()[index] = book;

					System.out.println("Book issued successfully.");

				}
			} else {
				// student id not matched.
				System.out.println("No student match");
			}

		} else {
			// book issued already
			System.out.println("Book already issued.");
		}
	}

	/**
	 * Returns given book from the student
	 * 
	 * @param isbn
	 *            isbn of book
	 */
	public void returnBook(int isbn) {
		Scanner sc = new Scanner(System.in);
		Student student = null;

		// find book by given isbn
		Book book = findBookByIsbn(isbn);

		if (book == null) {
			System.out.println("Book not found for ISBN.");
			return;
		}

		// check for book status
		if (book.isIssued()) {

			// find student having this book
			student = findStudentByBook(book);

			// return the book
			book.returnBook();

			// remove book from student's book list
			student.removeBook(book);

			System.out.println("Book returned successfully.");

		} else {
			// book is not issued to anyone
			System.out.println("Book is not issued.");
		}
	}

	/*
	 * Search for student by given Id
	 * 
	 * @param id id of the student
	 */
	public Student findStudentById(int id) {
		Student student = null;
		int i = 0;

		// iterate list and find matching Id
		while (i < studentList.length) {
			if (studentList[i].getsId() == id) {
				student = studentList[i]; // if Id found
			}
			i = i + 1; // iterate loop variable
		}
		return student; // return student object
	}

	/**
	 * Search for student by given book
	 * 
	 * @param book
	 *            object of the Book
	 * @return object of Student
	 */
	public Student findStudentByBook(Book book) {

		Student student = null;
		int i = 0;

		// iterate through student list
		while (i < studentList.length) {

			// find student by book
			int index = studentList[i].indexOf(book);

			if (index >= 0) {
				// if student found
				student = studentList[i];
			}

			i = i + 1; // iterate loop variable
		}

		return student;
	}

	/**
	 * Search for book by given ISBN
	 * 
	 * @param isbn
	 *            isbn of book
	 * @return object of Book
	 */
	public Book findBookByIsbn(int isbn) {
		int i = 0;
		Book book = null;

		// iterate through book list
		while (i < bookList.length && book == null) {

			// check for isbn match
			if (bookList[i].getISBN() == isbn) {
				book = bookList[i]; // if found
			}

			i = i + 1; // iterate loop variable
		}
		return book; // return book object
	}

	/**
	 * Writes data of books and students to the respective files.
	 */
	public void takeBackup() {
		System.out.println("Taking backup to file..");
		try {

			// make file out putstream object
			FileOutputStream fout = new FileOutputStream("backup_book.txt");

			// make buffered output stream object
			BufferedOutputStream bout = new BufferedOutputStream(fout);

			String data = "";

			// write title line
			data = "ISBN\tName\tAuthor\tPrice\tStatus";
			byte b[] = data.getBytes();
			bout.write(b);
			bout.write("\r\n".getBytes());

			int i = 0;
			while (i < bookList.length) {
				// get one by one book from list
				data = bookList[i].toString();
				byte b1[] = data.getBytes();
				bout.write(b1); // write data
				bout.write("\r\n".getBytes());
				i = i + 1;
			}

			bout.flush();
			bout.close();
			fout.close();

			/*
			 * Write student's data to the file from array
			 */
			fout = new FileOutputStream("backup_student.txt");
			bout = new BufferedOutputStream(fout);

			// write title line
			data = "SID\tName\tClass\tBooks";
			byte b2[] = data.getBytes();
			bout.write(b2);
			bout.write("\r\n".getBytes());

			i = 0;
			while (i < studentList.length) {
				// get one by one student from list
				data = studentList[i].toString();
				byte b1[] = data.getBytes();
				bout.write(b1); // write data
				bout.write("\r\n".getBytes());
				i = i + 1;
			}

			// close all streams
			bout.flush();
			bout.close();
			fout.close();
			System.out.println("Backup success");
			System.out.println("Data stored to backup_book.txt and backup_student.txt files");

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public int getMenuOption() {

		Scanner sc = new Scanner(System.in);
		System.out.println("\n---------------------------------------------");
		System.out.println("----------- LIBRARY MANAGEMENT SYSTEM -------");
		System.out.println("---------------------------------------------");
		System.out.println(
				"\n1.Issue Book\n2.Return Book\n3.View all Books" + "\n4.View all Students\n5.Take Backup\n6.Exit");
		System.out.println("Enter Choice: ");
		int ch = sc.nextInt();
		return ch;
	}

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		// make library objec
		Library lib = new Library();

		// load books from file
		lib.loadBooks("books.txt");

		// load students from file
		lib.loadStudents("students.txt");

		int opr = 0;

		while (opr != 6) {
			// show menu and get option
			opr = lib.getMenuOption();

			if (opr == 1) {
				System.out.println("Enter Book ISBN : ");
				int isbn = sc.nextInt();
				lib.issueBook(isbn);
			} else if (opr == 2) {
				System.out.println("Enter Book ISBN : ");
				int isbn = sc.nextInt();
				lib.returnBook(isbn);
			} else if (opr == 3) {
				lib.viewAllBooks();
			} else if (opr == 4) {
				lib.viewAllStudents();
			} else if (opr == 5) {
				lib.takeBackup();
			} else if (opr == 6) {
				System.out.println("Library system closing.. Goood Bye..");
			}
		}
	}

}
