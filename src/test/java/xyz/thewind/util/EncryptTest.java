package xyz.thewind.util;

import org.junit.jupiter.api.Test;
import xyz.thewind.global.util.encrypt.rsa.RsaEncryptUtil;

public class EncryptTest {

    @Test
    public void testRsaEncrypt() throws Exception {
        System.out.println(RsaEncryptUtil.encrypt("userName=yize&token=zr2014"));
    }

}
