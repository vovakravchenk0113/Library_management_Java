/**
 * A simulated book with properties like isbn,name,author,price.
 *
 */
public class Book {
	private int ISBN;
	private String bookName;
	private String authorName;
	private Double price;
	private boolean issued; // book status

	/**
	 * Construct book by given isbn, book name, author and price.
	 * 
	 * @param ISBN
	 *            isbn number of book
	 * @param bookName
	 *            name of book
	 * @param authorName
	 *            author name of book
	 * @param price
	 *            price of the book
	 */
	public Book(int ISBN, String bookName, String authorName, Double price) {
		super();
		this.ISBN = ISBN;
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
		this.issued = false; // default book not issued
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isIssued() {
		return issued;
	}

	/*
	 * Method marks book as issued.
	 */
	public void issue() {
		this.issued = true;
	}

	/*
	 * Method marks book as not issued.
	 */
	public void returnBook() {
		this.issued = false;
	}

	/**
	 * Method to print book data
	 * 
	 * @return string containing book's detail
	 */
	@Override
	public String toString() {
		String status = "";
		if (issued == true)
			status = "Issued";
		else
			status = "Not Issued";

		return ISBN + "\t" + bookName + "\t" + authorName + "\t" + price + "\t" + status;
	}

}
