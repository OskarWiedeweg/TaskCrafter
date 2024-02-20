package com.oskarwiedeweg.taskcrafterbackend.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * <h1>Id Generator</h1>
 *
 * <p>This utility class creates random ids with given prefixes.</p>
 * <p>Those ids are meant for meaningful ids.</p>
 *
 * <p>Example usage:</p> {@code IdGenerator.generateId("pref")} (results in e.g. {@code "pref_a776df9sd76as87_434894093040"})
 */
@UtilityClass
public class IdGenerator {

    public String generateId(String prefix) {
        String upper = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .toLowerCase();
        String lower = String.valueOf(System.currentTimeMillis());

        return String.format("%s_%s_%s", prefix, upper, lower);
    }

}
