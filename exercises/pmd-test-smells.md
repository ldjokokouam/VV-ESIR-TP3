# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer


J'ai choisi la règle **UnnecessaryBooleanAssertion**
→ Cette règle détecte les assertions inutiles dans les tests JUnit qui utilisent des littéraux booléens, comme assertTrue(true) ou assertFalse(false), car elles n'apportent aucune valeur ajoutée aux tests.

J'ai choisi le projet **Apache Commons Lang** 

J'ai exécuté PMD sur mon projet , suivant la commande suivante : 
pmd.bat check -d "C:\Users\nourc_3okniz2\Documents\ESIR2\SEM1\VV\TP\commons-lang" -R category/java/errorprone.xml/UnnecessaryBooleanAssertion -r C:\Users\nourc_3okniz2\Documents\ESIR2\SEM1\VV\TP\VV-ESIR-TP3\exercises\report.txt


→ Je me retrouve avec un fichier report.txt VIDE. Il n'y a pas de violations. Il n'y a rien à corriger dans ce cas.
Je vais quand même choisir une autre règle à appliquer à mon même projet pour détécter des violations : 

Choix n°2 de règle : **EmptyCatchBlock**
→ Détecte les blocs catch vides : des exceptions sont silencieusement ignorées dans le code (mauvaise pratique)

Pareil, j'ai exécuté PMD sur le même projet selon la règle précédente :
pmd.bat check -d "C:\Users\nourc_3okniz2\Documents\ESIR2\SEM1\VV\TP\commons-lang" -R category/java/errorprone.xml/EmptyCatchBlock -r C:\Users\nourc_3okniz2\Documents\ESIR2\SEM1\VV\TP\VV-ESIR-TP3\exercises\report.txt

→ PMD a détecté 17 violations de la règle 

Un exemple de violation de cette règle : 
(fichier:  src/main/java/org/apache/commons/lang3/ThreadUtils.java      Ligne : 567)

- code original : 
    public static void sleepQuietly(final Duration duration) {
        try {
            sleep(duration);
        } catch (final InterruptedException ignore) {
            // Ignore & be quiet.
        }
    }

- Amélioration proposée : 
    public static void sleepQuietly(final Duration duration) {
        try {
            sleep(duration);
        } catch (final InterruptedException ignore) {
            System.out.println("Le thread a été interrompu : " + e.getMessage());    // On affiche un message d'erreur 
        }
    }