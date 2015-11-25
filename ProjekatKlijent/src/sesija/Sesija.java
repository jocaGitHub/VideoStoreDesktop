/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesija;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Joca
 */
public class Sesija {
    
    private static Sesija sesija;
    private Map<String, Object> mapa;

    public Map<String, Object> getMapa() {
        return mapa;
    }

    public static Sesija vratiInstancu() {
        if (sesija == null) {
            sesija = new Sesija();
        }
        return sesija;
    }

    private Sesija() {
        mapa = new HashMap<>();
    }
    
}
