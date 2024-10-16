package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {


	// Partition 1 : Chaînes équilibrées
    @Test
    void testChaineVideBALANCED() {
        assertTrue(isBalanced(""));
    }

    @Test
    void testPaire1SeulSymboleBALANCED() {
        assertTrue(isBalanced("{}"));
        assertTrue(isBalanced("[]"));
        assertTrue(isBalanced("()"));
    }
    
    @Test
    void testMultiplesPaire1SeulSymboleBALANCED() {
        assertTrue(isBalanced("{}[]()"));
        assertTrue(isBalanced("(){}[]"));
    }
    
    @Test
    void testPairesImbriqueesBALANCED() {
        assertTrue(isBalanced("{[()]}"));
        assertTrue(isBalanced("{([])}"));
        assertTrue(isBalanced("({[]})"));
        assertTrue(isBalanced("({[]})()"));
    }

    // Partition 2 : Chaînes déséquilibrées
    @Test
    void testPairesUNBALANCED() {
    	assertFalse(isBalanced("[}"));
        assertFalse(isBalanced("{]"));
        assertFalse(isBalanced("[)"));
    }


    @Test
    void testFermeturesSupplementairesUNBALANCED() {
        assertFalse(isBalanced("{[]}()}}")); 
    }

    @Test
    void testOuverturesSupplementairesUNBALANCED() {
        assertFalse(isBalanced("{{[()]")); 
    }

    @Test
    void testCombinaisonMixteBalancedUNBALANCED() {
        assertFalse(isBalanced("{[}]({[()]})"));
    }
    
    // Partition 3 : Chaînes contenant uniquement des symboles d'ouverture 
    @Test
    void test1SeulSymboleOuvertUNBALANCED() {
        assertFalse(isBalanced("{"));
        assertFalse(isBalanced("["));
        assertFalse(isBalanced("("));
    }
    
    @Test
    void testPlusieursSymbolesOuvertsUNBALANCED() {
        assertFalse(isBalanced("{{[(")); 
    }

    // Partition 4 : Chaînes contenant uniquement des symboles de fermeture
    @Test
    void test1SeulSymboleFermeUNBALANCED() {
        assertFalse(isBalanced("}"));
        assertFalse(isBalanced("]"));
        assertFalse(isBalanced(")"));
    }
    
    @Test
    void testPlusieursSymbolesFermesUNBALANCED() {
        assertFalse(isBalanced("}}}")); 
    }
    
    

}
    
    
    
    




