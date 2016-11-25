package com.nextinno.doshare.token;

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
