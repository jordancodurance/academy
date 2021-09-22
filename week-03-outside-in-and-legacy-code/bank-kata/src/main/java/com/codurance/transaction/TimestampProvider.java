package com.codurance.transaction;

import java.time.LocalDateTime;

public class TimestampProvider {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
