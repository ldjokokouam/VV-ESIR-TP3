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
- La longueur que peut prendre de la chaîne : longueur zéro (vide), longueur paire (ex. : (), {[()()]}  ) ou longueur impaire (ex. : {[().   )

J'ai commencé à identifier quelques partition blocks : 
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

2. J'ai commencé par écrire des tests couvrant les cas de chaque partitions de la première question. 

Pour vérifier leur couverture, j'ai utilisé l'option **"Coverage As > JUnit Test" dans Eclipse**, ce qui m'a permis de visualiser les parties du code couvertes par mes tests.

→ 59.8% de coverage 


- Amélioration de la couverture : 
Après avoir exécuté la couverture, certaines lignes de code restaient en rouge (non couvertes) 

J'ai donc ajouté des tests supplémentaires :
- Chaînes avec des imbrications complexes mais équilibrées 
- Chaînes avec des symboles de fermeture supplémentaires.
- Chaînes avec des imbrications incorrectes de symboles.
- Chaînes contenant uniquement des symboles de fermeture.
- .. 

→ Résultat : Après avoir ajouté ces tests et vérifié à nouveau la couverture dans Eclipse : 72,6 % 
mais ! 
ils restaient encore certaines lignes en rouge (en rapport les chaines équilibrées) 
(Les tests en rouge s'attendent à avoir une valeur de retour égale à True, puisque la chaine est équilibrée, mais la metode isbalanced retourne False) 

→ Modification et implémentation du code de la methode isbalanced pour qu'elle reconaisse les cas corrects oû les chaines sont équilibrées.

On revérifie et là on a un couverture de 100% !!