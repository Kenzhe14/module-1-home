import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int copies;

    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopies() {
        return copies;
    }

    public void addCopies(int count) {
        this.copies += count;
    }

    public boolean borrowCopy() {
        if (copies > 0) {
            copies--;
            return true;
        }
        return false;
    }

    public void returnCopy() {
        copies++;
    }

    @Override
    public String toString() {
        return title + " автор " + author + " isbn " + isbn + " количество " + copies;
    }
}

class Reader {
    private String name;
    private String readerId;

    public Reader(String name, String readerId) {
        this.name = name;
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public String getReaderId() {
        return readerId;
    }

    @Override
    public String toString() {
        return name + " id = " + readerId;
    }
}

class Library {
    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
    }

    public void removeReader(String readerId) {
        readers.removeIf(reader -> reader.getReaderId().equals(readerId));
    }

    public boolean borrowBook(String isbn, String readerId) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.borrowCopy()) {
                    System.out.println(readerId + " взял " + book.getTitle());
                    return true;
                } else {
                    System.out.println("Нет  " + book.getTitle());
                    return false;
                }
            }
        }
        System.out.println("Книги нет");
        return false;
    }

    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.returnCopy();
                System.out.println(book.getTitle() + " Вернул " );
                return;
            }
        }
        System.out.println("Книги нет");
    }

    public void displayBooks() {
        System.out.println("Книг в библиотеке = ");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayReaders() {
        System.out.println("Регистрация = ");
        for (Reader reader : readers) {
            System.out.println(reader);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Абай", "Мухтар Ауээзовв", "1234", 5);
        Book book2 = new Book("40 кара соз", "Абай", "5678", 3);
        library.addBook(book1);
        library.addBook(book2);

        Reader reader1 = new Reader("Ерко", "1");
        Reader reader2 = new Reader("Дастиш", "2");
        library.registerReader(reader1);
        library.registerReader(reader2);

        library.displayBooks();
        library.displayReaders();

        library.borrowBook("1234", reader1.getReaderId());
        library.borrowBook("5678", reader2.getReaderId());

        library.returnBook("1234");

        library.removeBook("5678");
        library.removeReader("2");

        library.displayBooks();
        library.displayReaders();
    }
}
