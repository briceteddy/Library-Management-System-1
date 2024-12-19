package Library;
import java.util.ArrayList;

public class Library {
    private Book[] books;
    private int bookCount;
    private ActivityStack activityStack;
    private BorrowingHistory borrowingHistory;




    public Library(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
        activityStack = new ActivityStack();
        borrowingHistory = new BorrowingHistory();
    }



    public Book[] getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (bookCount < books.length) { // Vérifie si le tableau n'est pas plein
            books[bookCount++] = book; // Ajoute le livre et incrémente le compteur
            System.out.println("Livre ajouté : " + book.getTitle());
        } else {
            System.out.println("La bibliothèque est pleine !");
        }
    }

    public void removeBook(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) { // Recherche par ISBN
                // Décaler les éléments pour combler l'espace vide
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                books[--bookCount] = null; // Réduit le compteur et supprime le dernier élément
                System.out.println("Livre supprimé avec succès.");
                return;
            }
        }
        System.out.println("Livre non trouvé.");
    }

    public void updateBook(String isbn, String newTitle, String newAuthor, int newYear, String newGenre) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) { // Recherche par ISBN
                books[i].setTitle(newTitle);
                books[i].setAuthor(newAuthor);
                books[i].setPublicationYear(newYear);
                books[i].setGenre(newGenre);
                System.out.println("Livre mis à jour avec succès.");
                return;
            }
        }
        System.out.println("Livre non trouvé.");
    }

    public void displayBooks() {
        if (bookCount == 0) {
            System.out.println("Aucun livre dans la bibliothèque.");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            System.out.println("Titre : " + books[i].getTitle() + ", Auteur : " + books[i].getAuthor() +
                    ", ISBN : " + books[i].getIsbn() + ", Année : " + books[i].getPublicationYear() +
                    ", Genre : " + books[i].getGenre());
        }
    }

    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i]; // Retourne le livre trouvé
            }
        }
        return null; // Si aucun livre n'est trouvé
    }

    public Book linearSearchByAuthor(String author) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                return books[i];
            }
        }
        return null;
    }

    public Book linearSearchByIsbn(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    public Book binarySearchByTitle(String title) {
        int left = 0, right = bookCount - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return books[mid]; // Livre trouvé
            } else if (comparison < 0) {
                left = mid + 1; // Rechercher dans la moitié droite
            } else {
                right = mid - 1; // Rechercher dans la moitié gauche
            }
        }
        return null; // Si aucun livre n'est trouvé
    }

    public Book searchBook(String criterion, String value, boolean useBinarySearch) {
        switch (criterion.toLowerCase()) {
            case "title":
                return useBinarySearch ? binarySearchByTitle(value) : linearSearchByTitle(value);
            case "author":
                return linearSearchByAuthor(value); // Binaire non implémentée pour auteur
            case "isbn":
                return linearSearchByIsbn(value); // ISBN unique, linéaire suffit
            default:
                System.out.println("Critère non valide.");
                return null;
        }
    }

    public void bubbleSortByTitle() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                    // Échanger les livres
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Livres triés par titre (tri à bulles).");
    }

    public void bubbleSortByAuthor() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (books[j].getAuthor().compareToIgnoreCase(books[j + 1].getAuthor()) > 0) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        System.out.println("Livres triés par auteur (tri à bulles).");
    }


    public void selectionSortByPublicationYear() {
        for (int i = 0; i < bookCount - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < bookCount; j++) {
                if (books[j].getPublicationYear() < books[minIndex].getPublicationYear()) {
                    minIndex = j;
                }
            }
            // Échanger les livres
            Book temp = books[minIndex];
            books[minIndex] = books[i];
            books[i] = temp;
        }
        System.out.println("Livres triés par année de publication (tri par sélection).");
    }


    public void quickSortByTitle(int low, int high) {
        if (low < high) {
            int pi = partitionByTitle(low, high);

            // Trier les sous-tableaux
            quickSortByTitle(low, pi - 1);
            quickSortByTitle(pi + 1, high);
        }
    }

    private int partitionByTitle(int low, int high) {
        String pivot = books[high].getTitle();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (books[j].getTitle().compareToIgnoreCase(pivot) <= 0) {
                i++;
                // Échanger les livres
                Book temp = books[i];
                books[i] = books[j];
                books[j] = temp;
            }
        }

        // Placer le pivot à sa position correcte
        Book temp = books[i + 1];
        books[i + 1] = books[high];
        books[high] = temp;

        return i + 1;
    }
    public void quickSortByTitle() {
        quickSortByTitle(0, bookCount - 1);
        System.out.println("Livres triés par titre (tri rapide).");
    }

    public void sortBooks(String criterion, String algorithm) {
        switch (criterion.toLowerCase()) {
            case "title":
                if (algorithm.equalsIgnoreCase("bubble")) bubbleSortByTitle();
                else if (algorithm.equalsIgnoreCase("quick")) quickSortByTitle();
                break;
            case "author":
                if (algorithm.equalsIgnoreCase("bubble")) bubbleSortByAuthor();
                break;
            case "year":
                if (algorithm.equalsIgnoreCase("selection")) selectionSortByPublicationYear();
                break;
            default:
                System.out.println("Critère ou algorithme non valide.");
        }
    }

    // Ajouter un emprunteur à l'historique
    public void borrowBook(String borrower, String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                borrowingHistory.addBorrower(borrower);
                activityStack.push("Livre emprunté : " + books[i].getTitle() + " par " + borrower);
                System.out.println("Livre emprunté avec succès !");
                return;
            }
        }
        System.out.println("Livre non trouvé.");
    }

    // Afficher l'historique des emprunts
    public void showBorrowingHistory() {
        System.out.println("Historique des emprunts : " + borrowingHistory.getBorrowers());
    }

    // Afficher les activités récentes
    public void showRecentActivities() {
        System.out.println("Activités récentes :");
        while (!activityStack.isEmpty()) {
            System.out.println(activityStack.pop());
        }
    }


}
