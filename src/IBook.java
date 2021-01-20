public interface IBook {
    void addBook(Book book);
    void removeBook(String ISBN);
    void updateBook(String newBookName, String newAuthorName, String newPublisher, String newIsbn);
}
