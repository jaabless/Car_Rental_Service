package com.carrentalservice.ui_tests.data;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class RegisterCustomerTestData {

    public static Stream<Arguments> invalidAccountData() {
        return Stream.of(
                Arguments.of("", "test@example.com", "+233245323651", "SecurePass123!", "SecurePass123!", "Full Name is required"),
                Arguments.of("John Doe", "", "+233245323651", "SecurePass123!", "SecurePass123!", "Email is required"),
                Arguments.of("John Doe", "invalid", "+233245323651", "SecurePass123!", "SecurePass123!", "Email invalid"),
                Arguments.of("John Doe", "test@example.com", "", "SecurePass123!", "SecurePass123!", "Phone Number is required"),
                Arguments.of("John Doe", "test@example.com", "12345678", "SecurePass123!", "SecurePass123!", "Phone number must start with a '+'."),
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

    public static Stream<Arguments> invalidDriverDetail() {
        return Stream.of(
                Arguments.of(
                        "John Doe", "john.doe@acme.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "DL0001","dadada", "src/test/resources/files/doc.pdf", "Country does not exist"
                ),
                Arguments.of(
                        "John Doe", "john.doe@acme.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "DL0001","Afghanistan", "src/test/resources/files/logo.png", "Invalid file type. Only .pdf files are allowed."
                )
        );
    }

    public static Stream<Arguments> validRegistrationData() {
        return Stream.of(
                Arguments.of(
                        "Customer John", "newcustomeremail12@gmail.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "DL0001","Afghanistan", "src/test/resources/files/doc.pdf"
                ),
                Arguments.of(
                        "Customer John", "newcustomeremail13@gmail.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "DL0001","Ghana", "src/test/resources/files/doc.pdf"
                ),
                Arguments.of(
                        "Customer John", "newcustomeremail14@gmail.com", "+233242531625", "SecurePass123!", "SecurePass123!",
                        "DL0001","Afghanistan", "src/test/resources/files/doc.pdf"
                )
        );
    }
}
