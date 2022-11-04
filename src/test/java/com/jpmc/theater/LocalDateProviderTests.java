package com.jpmc.theater;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current Date is - " + LocalDateProvider.INSTANCE.currentDate());
    }
}
