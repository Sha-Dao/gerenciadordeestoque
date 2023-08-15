
package util;

import model.Pessoa;


public class Session {
    
    public static Pessoa pessoa;

    public static Pessoa getPessoa() {
        return pessoa;
    }

    public static void setPessoa(Pessoa pessoa) {
        Session.pessoa = pessoa;
    }
    
    
}
