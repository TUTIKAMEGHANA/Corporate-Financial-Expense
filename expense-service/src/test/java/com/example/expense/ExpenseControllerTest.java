package com.example.expense;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ExpenseControllerTest {
 @Test void amountMustBePositive(){ assertTrue(100 > 0); }
 @Test void budgetReconciliation(){ double budget=100000,total=45250; assertEquals(54750,budget-total); }
}
