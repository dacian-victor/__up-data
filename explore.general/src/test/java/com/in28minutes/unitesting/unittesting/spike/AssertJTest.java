package com.in28minutes.unitesting.unittesting.spike;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertJTest {

    @Test
    public void learning() {
        List<Integer> numbers = Arrays.asList(12, 15, 45);

        assertThat(numbers)
                .hasSize(3)
                .contains(12, 15)
                .allMatch(x -> x > 10)
                .allMatch(x -> x < 100)
                .noneMatch(x -> x < 0);

        assertThat("").isEmpty();
        assertThat("ABCDE")
                .contains("BCD")
                .startsWith("ABC")
                .endsWith("CDE");
    }

//    @Test
//    public void allProductsSameCurrency_test() {
//        Amount[] amounts = {
//                new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
//                new AmountImpl(new BigDecimal("6.0"), Currency.EURO) };
//
//        Amount expected = new AmountImpl(new BigDecimal("5.0"), Currency.EURO);
//
//        List<Product> products = createProductListWithAmounts(amounts);
//
//        Amount actual = clientBO.getClientProductsSum(products);
//
//        assertAmount(actual, expected);
//    }
}
