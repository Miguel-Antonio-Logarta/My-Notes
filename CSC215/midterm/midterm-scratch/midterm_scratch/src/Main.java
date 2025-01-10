public class Main {
    public static void main(String[] args) {
Bookshelf fictionBooks = new Bookshelf(new String[]{"Harry Potter", "Dune", "Lord of the Rings", "The Witcher", "A Song of Ice and Fire"}, 10);
fictionBooks.addBook("The Hobbit");
fictionBooks.printBooks();
fictionBooks.removeBook("Dune");
fictionBooks.printBooks();
System.out.println(Bookshelf.numBookshelves);
    }
}