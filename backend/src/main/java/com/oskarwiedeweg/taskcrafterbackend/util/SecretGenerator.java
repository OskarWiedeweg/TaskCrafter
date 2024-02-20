package com.oskarwiedeweg.taskcrafterbackend.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecretGenerator {

    public String generateSecret() {
        String upper = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String mid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String lower = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String appendix = String.valueOf(System.currentTimeMillis());

        return String.format("%s_%s_%s-%s", upper, mid, lower, appendix);
    }

}
