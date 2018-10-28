/**
 * A simulated student with properties like student id, name, class and No. of
 * book having.
 */

public class Student {
	private int sId;
	private String sName;
	private String sClass;
	private Book books[]; // no. of book issued

	/**
	 * Construct student with given student id,name and class.
	 * 
	 * @param sId
	 *            id of student
	 * @param sName
	 *            name of student
	 * @param sClass
	 *            class of student
	 */
	public Student(int sId, String sName, String sClass) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sClass = sClass;
		books = new Book[3]; // only 3 books allowed
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	public Book[] getBooks() {
		return books;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	/**
	 * Checks for no. of books students issued.
	 * 
	 * @return total books student have
	 */
	public int getLimit() {
		int i = 0;
		int cnt = 0;

		// iterate loop and find no. of issued books
		while (i < books.length) {
			if (books[i] != null) {
				cnt = cnt + 1; // if having book
			}
			i = i + 1; // increment iteration
		}
		return cnt; // return total
	}

	/**
	 * Gives index of given book from book array
	 * 
	 * @param book
	 *            object of the book
	 * @return index of book
	 */
	public int indexOf(Book book) {
		int i = 0;
		int index = -1;

		// itrate loop and find matching book
		while (i < books.length) {
			if (books[i] == book) {
				index = i; // if book found
			}
			i = i + 1; // iterate loop variable
		}
		return index; // return index
	}

	/**
	 * Gives empty index from book array
	 * 
	 * @param book
	 *            object of the book
	 * @return index of book
	 */
	public int emptyIndex() {
		int i = 0;
		int index = -1;

		// iterate loop and find index
		while (i < books.length) {
			if (books[i] == null) {
				index = i; // index found
			}
			i = i + 1; // iterate loop variable
		}
		return index;
	}

	/**
	 * Removes given book from the list
	 * 
	 * @param book
	 *            object of book
	 */
	public void removeBook(Book book) {
		int size = books.length; // size of book array
		int i = 0;

		// iterate loop and remove given book
		while (i < size) {

			// check for match
			if (books[i] == book) {
				--size; // decrease size of book array

				// re arrange book array
				while (i < size) {
					books[i] = books[i + 1]; // shift array elements
					++i;
				}

			}
			i = i + 1; // increment iteration
		}
	}

	/**
	 * Method prints the student object data.
	 * 
	 * @return string containing student's detail
	 */
	@Override
	public String toString() {
		String b = "";

		if (books.length > 0) {
			int i = 0;
			while (i < books.length) {
				// check for null in book array
				if (books[i] != null) {
					b = b + books[i].getBookName() + ",";
				}
				i = i + 1; // iterate loop variable
			}
		}
		return sId + "\t" + sName + "\t" + sClass + "\t" + b;
	}

}
