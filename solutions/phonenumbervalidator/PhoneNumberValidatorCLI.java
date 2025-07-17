package solutions.phonenumbervalidator;

import java.util.Scanner;

public class PhoneNumberValidatorCLI {
    public static boolean isValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        } else if (phoneNumber.startsWith("+")) {
            phoneNumber = phoneNumber.substring(3);
        }
        String digitsOnly = phoneNumber.replaceAll("\\D", "");
        return digitsOnly.matches("\\d{9}");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("--- Walidator Numerów Telefonów ---");
        System.out.println("Wpisz numer telefonu lub 'exit', aby zakończyć.");

        while (true) {
            System.out.print("\n  Podaj numer: ");
            userInput = scanner.nextLine();

            if ("exit".equalsIgnoreCase(userInput)) {
                break;
            }

            if (isValid(userInput)) {
                System.out.println("   Poprawny numer telefonu.");
            } else {
                System.out.println("Niepoprawny numer. Musi zawierać 9 cyfr.");
            }
        }

        System.out.println("Do zobaczenia!");
        scanner.close();
    }
}
