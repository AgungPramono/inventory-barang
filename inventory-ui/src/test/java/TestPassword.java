
import com.agung.inventory.util.PasswordHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agung
 */
@Tag("integration-test2")
public class TestPassword {
    
    @Test
    public void testGetPlainFromPassword(){
        String pass = PasswordHelper.getEncryptedTextFromPlainText("admin123");
        System.out.println(pass);
        for(int i=1; i<=10;i++){
            System.out.println(i);
        }
        
        String encrypt = PasswordHelper.getEncryptedTextFromPlainText2("71040788799");
        System.out.println(encrypt);
    }
    
}
