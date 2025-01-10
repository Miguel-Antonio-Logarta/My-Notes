public class Bookshelf {
    public String[] books;
    public int capacity;
    public int maxCapacity;
    public static int numBookshelves = 0;

    Bookshelf(String[] newBooks, int newMaxCapacity) {
        Bookshelf.numBookshelves = Bookshelf.numBookshelves + 1;
        if (newMaxCapacity > newBooks.length) {
            this.books = new String[newMaxCapacity];
            this.capacity = newBooks.length;
            for (int i = 0; i < newBooks.length; i++) {
                this.books[i] = newBooks[i];
            }
        } else {
            this.books = newBooks;
            this.maxCapacity = newBooks.length;
        }
    }

    public void addBook(String newBook) {
        if (this.capacity == this.maxCapacity) {
            System.out.println("Bookshelf is full!");
        } else {
            for (int i = 0; i < this.books.length; i++) {
                if (this.books[i] == null) {
                    this.books[i] = newBook;
                    break;
                }
            }
            this.capacity = this.capacity + 1;
            System.out.printf(" %s added\n", newBook);
        }
    }

    public void removeBook(String book) {
        boolean bookFound = false;
        for (int i = 0; i < this.books.length; i++) {
            if (this.books[i] == book) {
                this.books[i] = null;
                bookFound = true;
                break;
            }
        }

        if (bookFound) {
            this.capacity = this.capacity - 1;
            System.out.printf("%s Removed\n", book);
        } else {
            System.out.printf("%s Not found\n", book);
        }
    }

    public void printBooks() {
        System.out.println("-".repeat(20));
        for (String book : this.books) {
            System.out.println(book);
        }
    }
}