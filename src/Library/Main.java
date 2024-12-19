package Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(5); // Capacité initiale
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            // Afficher le menu
            System.out.println("\n--- Système de Gestion de Bibliothèque ---");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Supprimer un livre");
            System.out.println("3. Rechercher un livre");
            System.out.println("4. Trier les livres");
            System.out.println("5. Emprunter un livre");
            System.out.println("6. Afficher l'historique des emprunts");
            System.out.println("7. Afficher les activités récentes");
            System.out.println("8. Afficher tous les livres");
            System.out.println("9. Quitter");
            System.out.print("Entrez votre choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme le saut de ligne

            switch (choice) {
                case 1:
                    // Ajouter un livre
                    System.out.print("Titre : ");
                    String title = scanner.nextLine();
                    System.out.print("Auteur : ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN : ");
                    String isbn = scanner.nextLine();
                    System.out.print("Année de publication : ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Genre : ");
                    String genre = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn, year, genre));
                    break;

                case 2:
                    // Supprimer un livre
                    System.out.print("Entrez l'ISBN du livre à supprimer : ");
                    String isbnToRemove = scanner.nextLine();
                    library.removeBook(isbnToRemove);
                    break;

                case 3:
                    // Rechercher un livre
                    System.out.print("Rechercher par (title/author/isbn) : ");
                    String criterion = scanner.nextLine();
                    System.out.print("Entrez la valeur : ");
                    String value = scanner.nextLine();
                    System.out.print("Utiliser la recherche binaire (true/false) : ");
                    boolean useBinary = scanner.nextBoolean();
                    scanner.nextLine();
                    Book foundBook = library.searchBook(criterion, value, useBinary);
                    if (foundBook != null) {
                        System.out.println("Livre trouvé : " + foundBook.getTitle());
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;

                case 4:
                    // Trier les livres
                    System.out.print("Trier par (title/author/year) : ");
                    String sortCriterion = scanner.nextLine();
                    System.out.print("Algorithme de tri (bubble/selection/quick) : ");
                    String sortAlgorithm = scanner.nextLine();
                    library.sortBooks(sortCriterion, sortAlgorithm);
                    library.displayBooks();
                    break;

                case 5:
                    // Emprunter un livre
                    System.out.print("Nom de l'emprunteur : ");
                    String borrower = scanner.nextLine();
                    System.out.print("Entrez l'ISBN du livre à emprunter : ");
                    String isbnToBorrow = scanner.nextLine();
                    library.borrowBook(borrower, isbnToBorrow);
                    break;

                case 6:
                    // Afficher l'historique des emprunts
                    library.showBorrowingHistory();
                    break;

                case 7:
                    // Afficher les activités récentes
                    library.showRecentActivities();
                    break;

                case 8:
                    // Afficher tous les livres
                    library.displayBooks();
                    break;

                case 9:
                    System.out.println("Merci d'avoir utilisé le système !");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choice != 9);

        scanner.close();
    }
}
