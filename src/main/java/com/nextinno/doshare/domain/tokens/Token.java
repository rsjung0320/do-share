package com.nextinno.doshare.domain.tokens;

import lombok.Data;

/**
 * @author rsjung
 *
 */
@Data
public class Token {
    private String token = "";
    private String refreshToken = "";
}
