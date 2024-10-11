import java.util.Scanner;

public class Customer {
    private String ruserid;
    private String rpassword;
    private int[] bookedTickets = new int[4];
    private final int[] ticketPrices = { 250, 300, 400, 600 };

    public void registration(Scanner sc) {
        System.out.println("Registration");

        System.out.println("enter username: ");
        ruserid = sc.nextLine();

        System.out.println("enter password: ");
        rpassword = sc.nextLine();

        System.out.println("USERNAME = " + ruserid);
        System.out.println("PASSWORD =" + rpassword);
        System.out.println("Your registration is successful");
    }

    public void login(Scanner sc) {
        if (ruserid == null || rpassword == null) {
            System.out.println("No user registered. Please register first.");
            return;
        }
        System.out.println("Welcome to the login page:");

        System.out.println("enter username: ");
        String userid = sc.nextLine();

        System.out.println("enter password: ");
        String password = sc.nextLine();
        if (ruserid.equals(userid) && rpassword.equals(password)) {
            System.out.println("Login successfully");
        } else {
            System.out.println("Login error: Incorrect username or password");
        }
    }

    public void selectmovies(Scanner sc) {
        if (ruserid == null || rpassword == null) {
            System.out.println("Please log in first to select a movie.");
            return;
        }
        int movie;
        int quantity;

        System.out.println("press 1 to see PLAYERS");
        System.out.println("press 2 to see SHERSHAAH");
        System.out.println("press 3 to see BELL BOTTOM");
        System.out.println("press 4 to see BHUJ");

        movie = sc.nextInt();
        sc.nextLine();

        if (movie < 1 || movie > 4) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        System.out.println("You selected " + getMovieName(movie));
        int price = ticketPrices[movie - 1];

        System.out.print("How many tickets do you want to buy: ");
        quantity = sc.nextInt();
        bookedTickets[movie - 1] += quantity;

        System.out.println("The price of your tickets is: " + (price * quantity));
    }

    public void cancelTickets(Scanner sc) {
        if (ruserid == null || rpassword == null) {
            System.out.println("Please log in first to cancel tickets.");
            return;
        }
        System.out.println("Which movie tickets do you want to cancel?");
        System.out.println("Press 1 for PLAYERS");
        System.out.println("Press 2 for SHERSHAAH");
        System.out.println("Press 3 for BELL BOTTOM");
        System.out.println("Press 4 for BHUJ");

        int movie = sc.nextInt();
        sc.nextLine();

        if (movie < 1 || movie > 4 || bookedTickets[movie - 1] == 0) {
            System.out.println("No tickets booked for the selected movie or invalid selection.");
            return;
        }

        System.out.println("You have " + bookedTickets[movie - 1] + " tickets booked for this movie.");
        System.out.print("How many tickets do you want to cancel? ");
        int cancelQuantity = sc.nextInt();
        sc.nextLine();

        if (cancelQuantity > bookedTickets[movie - 1] || cancelQuantity <= 0) {
            System.out.println("Invalid cancellation quantity.");
        } else {
            bookedTickets[movie - 1] -= cancelQuantity;
            System.out.println(cancelQuantity + " tickets canceled successfully.");
            System.out.println("Remaining tickets for this movie: " + bookedTickets[movie - 1]);
            refund(movie, cancelQuantity);
        }
    }

    public void refund(int movie, int quantity) {
        int price = ticketPrices[movie - 1];
        int refundAmount = price * quantity;
        System.out.println("You will be refunded: " + refundAmount);
    }

    private String getMovieName(int movie) {
        switch (movie) {
            case 1:
                return "PLAYERS";
            case 2:
                return "SHERSHAAH";
            case 3:
                return "BELL BOTTOM";
            case 4:
                return "BHUJ";
            default:
                return "Unknown Movie";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        boolean exit = false;

        while (!exit) {
            System.out.println("enter your choice:");
            System.out.println("press 1 to register");
            System.out.println("press 2 to login");
            System.out.println("press 3 to selectmovie");
            System.out.println("press 4 to cancelTickets");
            System.out.println("press 5 to exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    customer.registration(sc);
                    break;
                case 2:
                    customer.login(sc);
                    break;
                case 3:
                    customer.selectmovies(sc);
                    break;
                case 4:
                    customer.cancelTickets(sc);
                case 5:
                    exit = true;
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}