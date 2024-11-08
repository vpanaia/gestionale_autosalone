package com.apuliadigital.gestionaleautosalone;

import com.apuliadigital.gestionaleautosalone.common.AuthUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonTest {

    @Test
    void testExtractCredentials() {
        String header = "Basic dXNlcjpwYXNz";

        var result = AuthUtil.extractCredentials(header);

        assertNotNull(result);
        assertInstanceOf(String[].class, result);
        assertEquals(2, result.length);
        assertEquals("user", result[0]);
        assertEquals("pass", result[1]);

    }
}
