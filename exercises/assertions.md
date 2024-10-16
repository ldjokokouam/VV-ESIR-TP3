# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### **Question 1**
L'assertion `assertTrue(3 * .4 == 1.2)` ne marche pas car les nombres à virgule flottante ne sont pas représentés de manière exacte. Par conséquent, il peut y avoir des erreurs d'arrondi comme ici. Cela s'explique par le fait que les flotants sont stockés en mémoire avec une précision limitée. Donc le résultat de `3 * .4` ne peut pas être égal à 1.2.
    
    De plus, utiliser le comparateur == peut être trompeur à cause des arrondi. La meilleure approche est d'utiliser une marge d'erreur ε :
    
    double result = 3 * .4;
    double expected = 1.2;
    double epsilon = 10E-5;
    
    assertTrue(Math.abs(result - expected) < epsilon);

### **Question 2**
AssertEquals compare les valeurs tandis que AssertSame compare les références. Les deux méthodes prennent en paramètres le résultat attendu et le résultat réel : AssertEquals(expected, actual), AssertSame(expected, actual). 

Voici quelques exemples pour illustrer :

    /**
     * Dans cet exemple, str et str2 ne font pas référence au même objet.
     * En revanche, les valeurs de str1 et str2 sont égales.
     */
    public void testSameEquals() {
        String str1 = new String("Salut");
        String str2 = new String("Salut");
        assertSame("Les références ne sont pas les mêmes", str1, str2);
        assertEquals("Les valeurs ne sont pas les mêmes", str1, str2);
    }

    /**
     * Dans cet exemple, str et str2 font référence au même objet.
     * En revanche, les valeurs de str1 et str2 sont égales.
     */
    public void testSameEquals2() {
        String str1 = new String("Salut");
        String str2 = str1;
        assertSame("Les références ne sont pas les mêmes", str1, str2);
        assertEquals("Les valeurs ne sont pas les mêmes", str1, str2);
    }
    
### **Question 3**

**Premier exemple : Échec dû à des facteurs externes**

- Si un test échoue en raison de facteurs externes comme un problème de réseau, fail() peut permettre de signaler l'échec. Il permet également d'expliquer la raison de l'échec à l'aide d'un message.

    @Test
    public void testServiceExterne() {
        try {
            // Code interagissant avec un service externe
            // ...
        } catch (IOException e) {
            fail("Échec du test dû à une erreur réseau : " + e.getMessage());
        }
    }
    
**Deuxième exemple : Marquer un test comme non implémenté**

- On peut utiliser fail() pour signaler à l'utilisateur qu'un test n'est pas encore implémenté. Ici, les méthodes à tester sont testMultiply() et testDivide().

public class CalculatorImplTest {

    @Test
    public final void testMultiply() {
        fail("Not yet implemented"); // TODO
        
    }
    
    @Test
    public final void testDivide() {
        fail("Not yet implemented"); // TODO
    }
}


### **Question 4**

- Dans JUnit 4, on utilisait @Test et execpted pour tester une méthode.

**Exemple de test avec JUnit 4 :**

- Dans l'exemple ci-dessous, on vérifie que la méthode Operations.diviser(int) lève une IllegalArgumentException lorsque 1 est passé en paramètre. Dans le cas où l'exception serait levée, ce serait un succès. Dans le cas contraire ce serait un échec.

public class TestThrowable {

    @Test(expected=IllegalArgumentException.class)
    public void divisionAvecUnNombre() {
        Operations.diviser(1);
    }
}

**Exemple de test avec JUnit 5 :**

- Dans cet exemple, on va à nouveau vérifier que la méthode Operations.diviser(int) lève une IllegalArgumentException lorsque 1 est passé en paramètre. Pour cela, on utilise assertThrows() qui prend en paramètres le type d'exception attendue (IllegalArgumentException.class) et la fonction lambda (Operations.diviser(1)). Pour savoir si le test est un succès, il faut que la onction lambda lève une exception IllegalArgumentException. Sinon, le test échoue.

public class TestThrowable {

    @Test
    public void divisionAvecUnNombre() {
        assertThrows(IllegalArgumentException.class, () -> {
            Operations.diviser(1);
        });
    }
}

**Conclusion**

JUnit 5 offre de nombreux avantages :
- On peut cibler plus facile la partie du code qui est sensée lever une exception. Avec JUnit 4, on ne pas identifier clairement où est levée l'exception.
- Avec cette nouvelle version de JUnit, on a une meilleure lisibilité. En effet, assertThrows() rend le test plus compréhensible contrairement aux notations de JUnit 4 (@Test et expected)
- Enfin, on a la possibilité de consulter les messages d'erreur. Cela permet de vérifier le contenu de l'exception   
