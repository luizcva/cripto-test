import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        //Key (HEX): 89791aadbab8b33a807e6b222edf241940315d14fd966b302bd4f9d3b82c1647, IV (HEX): 321d5dad4d0882fc63b3a1d694c63c11, Encrypted: V+lEhdA6UW35PpcA+8cKhmYxCCK/xUSxFsszPLEVx5wcyVwWT/msI+DSXPn/ewUw
                            
        final byte[] key = javax.xml.bind.DatatypeConverter.parseHexBinary("89791aadbab8b33a807e6b222edf241940315d14fd966b302bd4f9d3b82c1647");
        final byte[] iv = javax.xml.bind.DatatypeConverter.parseHexBinary("321d5dad4d0882fc63b3a1d694c63c11");
        String encryptedBase64 = "V+lEhdA6UW35PpcA+8cKhmYxCCK/xUSxFsszPLEVx5wcyVwWT/msI+DSXPn/ewUw";                            

        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv, 0, cipher.getBlockSize()));
        
        byte[] ciphertextBytes = Base64.decodeBase64(encryptedBase64.getBytes("UTF8"));
        byte[] recoveredPlaintextBytes = cipher.doFinal(ciphertextBytes);
        String recoveredPlaintext = new String(recoveredPlaintextBytes);            
        
        System.out.println("Texto descriptografado: " + recoveredPlaintext);
    }

}
