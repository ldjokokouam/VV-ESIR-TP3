# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer

1. Nous allons commencer par identifier les caractéristiques : 
- les types de symboles qu'on peut trouver dans une chaîne :  (), {}, et [].
- Une chaîne peut être **balanced** (tous les symboles ouverts ont des correspondances fermées) ou **! balanced**.
- La longueur que peut prendre de la chaîne : longueur zéro (vide), longueur paire (ex. : (), {[()()]}  ) ou longueur impaire (ex. : {[().   )4

Nous allons aussi identifier quelques partition blocks : 
- Partition 1 : Chaînes équilibrées
Exemples : "" (chaine vide), 
"{}", "[]", "()", 
"{[()]}" (imbriqués)

- Partition 2 : Chaînes déséquilibrées
Exemples : "[}", "{[()}", "{[()]})", "{{}" 

- Partition 3 : Chaînes contenant uniquement des symboles d'ouverture
Exemples : "{", "[", "(",  (1 seul symbole ouvert et non fermé)
"{[(" (plusieurs symboles ouverts)

- Partition 4 : Chaînes contenant uniquement des symboles de fermeture
Exemples : "}", "]", ")", (1 seul symbole fermé et non ouvert)
"}}}" (plusieurs symboles fermés)

