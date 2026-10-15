package com.plantcare.client;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class TrefleRateLimiter {

    private static final int MAX_REQUESTS = 50;
    private static final long WINDOW_SECONDS = 60;

    private final Deque<Instant> requestTimes = new ArrayDeque<>();

    public synchronized void acquire() {
        Instant now = Instant.now();
        Instant cutoff = now.minusSeconds(WINDOW_SECONDS);

        while (!requestTimes.isEmpty()
                && !requestTimes.peekFirst().isAfter(cutoff)) {
            requestTimes.removeFirst();
        }

        if (requestTimes.size() >= MAX_REQUESTS) {
            throw new ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "PlantCare has reached its Trefle API request limit. "
                            + "Please try again shortly."
            );
        }

        requestTimes.addLast(now);
    }
}