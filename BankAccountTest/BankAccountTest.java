import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @DisplayName("Check if we get the expected account number from getAccountNumber method")
    @Test
    void getAccountNumberTest() {

        // Arrange
        double initialBalance = 500.00;

        BankAccount account = new BankAccount(1337, initialBalance);

        // Assert
        double expectedAccountNumber = 1337;
        double actualAccountNumber = account.getAccountNumber();

        Assertions.assertEquals(expectedAccountNumber, actualAccountNumber);

    }

    @DisplayName("Check if we get the expected balance from getBalance method")
    @Test
    void getBalanceTest() {
        // Arrange
        double initialBalance = 500.00;

        BankAccount account = new BankAccount(1337, initialBalance);

        // Assert
        double expectedBalance = 500;
        double actualBalance = account.getBalance();

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @DisplayName("Check if we get the expected interest rate from getInterestRate method")
    @Test
    void getInterestRateTest() {
        // Arrange
        double initialBalance = 500.00;

        BankAccount account = new BankAccount(1337, initialBalance);

        // Act
        account.setInterestRate(0.03);

        // Assert
        double expectedInterestRate = 0.03;
        double actualInterestRate = account.getInterestRate();

        Assertions.assertEquals(expectedInterestRate, actualInterestRate);
    }


    @DisplayName("Test valid inputs (> 0) of deposit method")
    @Test
    void depositTestValidInputs() {
        // Arrange
        double initialBalance = 500.00;
        double amount = 500.00;
        BankAccount account = new BankAccount(1337, initialBalance);

        // Act
        account.deposit(amount);

        // Assert
        double expectedBalance = 1000.00;
        double actualBalance = account.getBalance();

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @DisplayName("Test Invalid inputs (< 0) of deposit method - should throw IllegalArgumentException")
    @Test
    void depositTestInvalidInputs() {
        // Arrange
        double initialBalance = 500.00;
        double amount = -1000.00;
        BankAccount account = new BankAccount(1337, initialBalance);


        // Act + assert
        Exception err = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(amount);
        });

        // Extra assert
        String expectedMessage = "Amount cannot be negative";
        String actualMessage = err.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @DisplayName("Test valid inputs (> 0) of withdraw method")
    @Test
    void withdrawTestValidInput() {
        // Arrange
        double initialBalance = 500.00;
        double amount = 500.00;
        BankAccount account = new BankAccount(1337, initialBalance);

        // Act
        account.withdraw(amount);

        // Assert
        double expectedBalance = 0.00;
        double actualBalance = account.getBalance();

        Assertions.assertEquals(expectedBalance, actualBalance);

    }

    @DisplayName("Test negative inputs of withdraw method - should throw IllegalArgumentException")
    @Test
    void withdrawTestNegativeInput() {
        // Arrange
        double initialBalance = 500.00;
        double amount = -500.00;
        BankAccount account = new BankAccount(1337, initialBalance);

        // Act + assert
        Exception err = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(amount);
        });

        // Extra assert
        String expectedMessage = "Amount cannot be negative";
        String actualMessage = err.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @DisplayName("Test inputs that exceeds initialBalance of withdraw method - should throw IllegalArgumentException")
    @Test
    void withdrawTestExceededInput() {
        // Arrange
        double initialBalance = 500.00;
        double amount = 1000.00;
        BankAccount account = new BankAccount(1337, initialBalance);


        // Act + assert
        Exception err = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(amount);
        });

        // Extra assert
        String expectedMessage = "Amount cannot be greater than balance";
        String actualMessage = err.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);

    }


    @DisplayName("Test valid inputs from 0.01-0.1(1%-10%)")
    @Test
    void setInterestRateTestValidInput() {
        double initialBalance = 500.00;
        double setInterestRate = 0.1;

        BankAccount account = new BankAccount(1337, initialBalance);

        // Act
        account.setInterestRate(setInterestRate);

        // Assert
        double expectedInterestRate = 0.1;
        double actualInterestRate = account.getInterestRate();

        Assertions.assertEquals(expectedInterestRate, actualInterestRate);

    }


    @DisplayName("Test inputs that is outside the range of 0.01-0.1(1%-10%) - should throw IllegalArgumentException")
    @Test
    void setInterestRateTestOutsideOfRange() {
        double initialBalance = 500.00;
        double setInterestRate = 0.2;

        BankAccount account = new BankAccount(1337, initialBalance);

        // Act + assert
        Exception err = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.setInterestRate(setInterestRate);
        });

        // Extra assert
        String expectedMessage = "Interest rate must be between 1-10%";
        String actualMessage = err.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @DisplayName("Test if we get the correct result, when we add interest to the balance")
    @Test
    void addInterestTest() {

        // Arrange
        double initialBalance = 500.00;
        double setInterestRate = 0.1;

        BankAccount account = new BankAccount(1337, initialBalance);

        // Act
        account.setInterestRate(setInterestRate);
        account.addInterest();

        // Assert
        double expectedBalance = 550.00;
        double actualBalance = account.getBalance();

        Assertions.assertEquals(expectedBalance, actualBalance);
    }
}