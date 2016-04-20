package crypto;

import org.junit.Test;

import static crypto.EncryptPassword.encryptPassword;

public class EncryptPasswordTest {

    @Test
    public void testEncryptPassword() throws Exception {

        String enc = encryptPassword("password");
        String enc1 = encryptPassword("password");
        String enc2 = encryptPassword("password");
        //String enc3 = encryptPassword("password", "SHA-128");
        String enc4 = encryptPassword("password");
        String enc5 = encryptPassword("password");

        System.out.println(enc + "\t" + "with length" + "\t" + enc.length());
        System.out.println(enc1 + "\t" + "with length" + "\t" + enc1.length());
        System.out.println(enc2 + "\t" + "with length" + "\t" + enc2.length());
        //System.out.println(enc3 + "\t" + "with length" + "\t" + enc3.length());
        System.out.println(enc4 + "\t" + "with length" + "\t" + enc4.length());
        System.out.println(enc5 + "\t" + "with length" + "\t" + enc5.length());
    }

    @Test
    public void heffelfingerWasRight() {
        String encryptedPassword;
        String pwd = "s3cret";
        encryptedPassword = encryptPassword(pwd);
        System.out.println(encryptedPassword);
    }
}
