package com.carrentalservice.ui_tests.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class RegisterBusinessTestData {

    public static Stream<Object[]> validLoginTestData() {
        return Stream.of(
                new Object[]{"test.user@example.com", "ValidPass123!", true, "Login successful"},
                new Object[]{"newbusinessemail@gmail.com", "newBusiness1@", true, "Login successful"},
                new Object[]{"newcustomeremail@gmail.com", "newCustomer1@", true, "Login successful"}
        );
    }

    public static Stream<Arguments> invalidAccountData() {
        return Stream.of(
                Arguments.of("", "test@example.com", "+233245323651", "SecurePass123!", "SecurePass123!", "Full name is required"),
                Arguments.of("John Doe", "", "+233245323651", "SecurePass123!", "SecurePass123!", "Email is required"),
                Arguments.of("John Doe", "invalid", "+233245323651", "SecurePass123!", "SecurePass123!", "Email invalid"),
                Arguments.of("John Doe", "test@example.com", "", "SecurePass123!", "SecurePass123!!", "Phone number is required"),
                Arguments.of("John Doe", "test@example.com", "12345678", "SecurePass123!", "SecurePass123!", "Invalid phone number"),
                Arguments.of("John Doe", "test@example.com", "+233245323651", "pass", "SecurePass123!", "Your passwords do not match"),
                Arguments.of("John Doe", "test@example.com", "+233245323651", "SecurePass123!", "", "Your passwords do not match"),
                Arguments.of("John Doe", "test@example.com", "+233245323651", "SecurePass123!", "WrongPass", "Your passwords do not match")
        );
    }

    public static Stream<Arguments> validAccountData() {
        return Stream.of(
                Arguments.of("John Doe", "newbusinessemail@gmail.com", "+233245323651", "SecurePass123!", "SecurePass123!"),
                Arguments.of("Kwame John", "newcustomeremail@gmail.com", "+233245323123", "SecurePass123!", "SecurePass123!"),
                Arguments.of("John Kwame", "test@example.com", "+233245323451", "SecurePass123!", "SecurePass123!")
        );
    }

    public static Stream<Arguments> validCompanyData() {
        return Stream.of(
                Arguments.of("Acme Car Rentals", "src/test/resources/files/logo.png", "Ghana", "Los Angeles", "src/test/resources/files/doc.pdf"),
                Arguments.of("Real Estate", "src/test/resources/files/logo.png", "Ghana", "Miami", "src/test/resources/files/doc.pdf")
                );
    }

    public static Stream<Arguments> invalidCompanyData() {
        return Stream.of(
                Arguments.of(
                        "John Doe", "john.doe@acme.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "", "src/test/resources/files/logo.png", "Ghana", "Kumasi", "src/test/resources/files/doc.pdf", "Company Name is required"
                ),
                Arguments.of(
                        "John Doe", "john.doe@acme.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "Company Name", "src/test/resources/files/logo.png", "Ghana", "", "src/test/resources/files/doc.pdf", "City / Location is required"
                ),
                Arguments.of(
                        "John Doe", "john.doe@acme.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "My Company", "src/test/resources/files/doc.pdf", "Ghana", "Kumasi", "src/test/resources/files/doc.pdf", "Invalid file type. Only .jpg, .png, .jpeg files are allowed."
                )
        );
    }

    public static Stream<Arguments> validRegistrationData() {
        return Stream.of(
                Arguments.of(
                        "John Doe", "newbusinessemail@gmail.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "Business Car Rentals", "src/test/resources/files/logo.png", "Ghana", "Kumasi", "src/test/resources/files/doc.pdf"
                ),
                Arguments.of(
                        "John Doe", "newcustomeremail@gmail.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "Customer Car Rentals", "src/test/resources/files/logo.png", "Ghana", "Kumasi", "src/test/resources/files/doc.pdf"
                ),
                Arguments.of(
                        "John Doe", "test@example.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "Test Car Rentals", "src/test/resources/files/logo.png", "Ghana", "Kumasi", "src/test/resources/files/doc.pdf"
                ),
                Arguments.of(
                        "John Doe", "jefferytechie@gmail.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "Techie Car Rentals", "src/test/resources/files/logo.png", "Ghana", "Kumasi", "src/test/resources/files/doc.pdf"
                )
        );
    }
}
