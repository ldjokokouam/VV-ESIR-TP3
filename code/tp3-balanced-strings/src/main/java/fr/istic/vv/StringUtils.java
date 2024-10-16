package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) 
    {
        Stack<Character> stack = new Stack<>();    // L'équilibre de la chaine sera assuré par une PILE stack

        for (int i = 0; i < str.length(); i++)     // On parcourt la chaine
        {
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[')     // Si c un symbole d'ouverture , on le push dans notre pile
            { 
                stack.push(c);  
            } 
            else if (c == ')' || c == '}' || c == ']')   // Si un symbole de fermeture, on le pop de notre pile
            {
                if (stack.isEmpty()) 
                {
                    return false;  // Dans le cas ou un symbole de fermeture n'a pas de symbole d'ouverture correspondant
                }
                char last = stack.pop();            
                if ((c == ')' && last != '(') ||  (c == '}' && last != '{') || (c == ']' && last != '['))  // Verififcation des paires
                {
                    return false;  // Dans le cas d'une incohérance entre le symbole de fermeture et le dernier symbole d'ouverture
                }
            }
        }
        return stack.isEmpty();  // Si la pile est vide à la fin, tout est bien équilibré
    }

}
