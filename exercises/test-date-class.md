# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer
Nous allons dans cet exercice créer une classe Date et la tester. 

1. **Input Space Partitioning** = technique de conception de tests  (diviser les inputs possibles en plusieurs partitions (==blocs)
→ sélectionner des cas de test pour chaque bloc

* Définition des caractéristiques et blocs des méthodes de la classe Date : 
- Méthode isValidDate(int day, int month, int year)   //returns `true` if the three integers form a valid year, otherwise `false`
Caractéristiques et Blocs :

--Year :
Bloc 1 : Année valide (toute année positive >0, par exemple, 2020)
Bloc 2 : Année invalide (<=0 , par exemple, -1)
→ Cas de test :isValidDate(15, 1, -1) (année invalide)

--Month :
Bloc 1 : Mois valide (1 à 12)
Bloc 2 : Mois invalide (0, 13)
→ Cas de test : isValidDate(15, 0, 2020) (mois invalide)
                isValidDate(15, 13, 2020) (mois invalide)

--Day : ( en fonction du mois et de l'année)
Bloc 1 : Jour valide (1 à 28, 29, 30 ou 31)
Bloc 2 : Jour invalide (par exemple, 0, -1, 32)
→ Cas de test : isValidDate(29, 2, 2020) (année bissextile)
                isValidDate(31, 4, 2020) (avril n'a pas 31 jours)
                isValidDate(0, 1, 2020) (jour invalide)


- Méthode isLeapYear(int year)  // says if the given integer is a leap year
Caractéristiques et Blocs :

--Year :
Bloc 1 : Année bissextile (divisible par 4, sauf si divisible par 100 mais pas 400)
Bloc 2 : Année non bissextile
→ Cas de test : isLeapYear(2020) (bissextile)
                isLeapYear(1900) (non bissextile)
                isLeapYear(2000) (bissextile)
                isLeapYear(2021) (non bissextile)


- Méthode nextDate()   // returns a new `Date` instance representing the date of the following day
Caractéristiques et Blocs :

--Date retourné  :
Bloc 1 : Jour normal (ni premier jour ni dernier jour du mois)
Bloc 2 : Dernier jour du mois non bissextile (31 janvier, 28 février)
Bloc 3 : Dernier jour du mois bissextile (29 février)
Bloc 3 : Dernier jour du mois de l'année (31 décembre 2020) --> 1 janvier 2021
→ Cas de test : new Date(15, 1, 2020).nextDate() (jour normal)
                new Date(31, 1, 2020).nextDate() (changement de mois)
                new Date(28, 2, 2021).nextDate() (année non bissextile)
                new Date(29, 2, 2020).nextDate() (année bissextile)
                new Date(31, 12, 2020).nextDate() (changement d'année)


- Méthode previousDate()   // returns a new `Date` instance representing the date of the previous day
Caractéristiques et Blocs :

--Date retourné  :
Bloc 1 : Jour normal (ni premier jour ni dernier jour du mois)
Bloc 2 : Premier jour du mois (--> dernier jour du mois précédent)
Bloc 3 : Premier jour de l'année (1er janvier 2021)
→ Cas de test : new Date(15, 1, 2020).previousDate()
                new Date(1, 2, 2020).previousDate()  (--> 28/29 février, dernier jour du mois précédent)
                new Date(1, 1, 2021).previousDate() 

e. Méthode compareTo(Date other)
Caractéristiques et Blocs :

--Dates comparées (date1, date2) :
Bloc 1 : date1 est antérieure à date2
Bloc 2 : date1 est postérieure à date2
Bloc 3 : date1 et date2 sont égales
Tests de base : Comparer le 15 janvier 2020 avec le 16 janvier 2020
                Comparer le 15 janvier 2021 avec le 15 janvier 2020
                Comparer le 15 janvier 2020 avec le 15 janvier 2020

2. Le mieux c'est de faire une classe de test pour chaqu méthode , comme ça chaque classe de test ne contient que les tests d'une seule méthode. ++ Organisation
Toutes ces classes de test seront contenues dans notre classe DataTest