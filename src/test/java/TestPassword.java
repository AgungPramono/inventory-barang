
import com.agung.inventory.util.PasswordHelper;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agung
 */
public class TestPassword {
    
    @Test
    public void testGetPlainFromPassword(){
        String pass = PasswordHelper.getEncryptedTextFromPlainText("agung123");
        System.out.println(pass);
    }
    
}
