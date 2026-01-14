package com.esisa.java;

public class Examples {

	public Examples() {
			exp07();
		}
		
		void exp01() {
			/*
			 * byte : 1 octet
			 * short : 2 octets
			 * int  : 4 octets
			 * long : 8 octets
			 * float : 4 octets
			 * double : 8 octets
			 * char : 2 octets : unicode
			 * boolean : true , false
			 */
			//float pi = 3.14; faux
			float pi = 3.14f;
			double x = 3.14;
			float y=(float)x; // casting = transtypage
			
			
			char c1= '\u0636';
			System.out.println("c1 =" + c1);
			char c2 = '\u20AC';
			System.out.println("c2 =" + c2);
			char c3 = 65;
			System.out.println("c3 =" + c3);
			
			int a = 66;
			char c4 = (char)a;
			System.out.println("c4 =" + c4);
			int b = c3;
			System.out.println("b =" + b);
			
			//Generer une lettre majuscule aléatoire :
			char r1 = (char)('A' + (int)(Math.random() * 26)); //Math.random donne toujours une valeur entre 0 et 1 
			System.out.println("r1 =" + r1);
			
			boolean b1 = true;
			if(b1) {
				System.out.println("b1 = " + b1);
			}
			
			boolean b2 = 20 < 30;
			System.out.println("b2 = " + b2);
		}
		
		void exp02() {
			int a = 20;
			inc(a); // affectation de valeur
			System.out.println("a = " + a);
		}
		
		void exp03() {
			Entier a = new Entier(20);
			inc(a); // affectation d'adresse
			System.out.println("a = " + a);
		}
		
		void inc(Entier a) { //passage par référence ou par addresse 
			a.setValue(a.getValue() + 1);
		}
		
		void inc(int p) { //passage par valeur
			p++;
		}
		
		void exp04() {
			Entier a = new Entier(20);
			Entier b = a;
			b.setValue(35);
			System.out.println("a = " + a);
			System.out.println("b = " + b);
		}
		
		void exp05() {
			Entier a = new Entier(20);
			a.inc();
			System.out.println("a = " + a);
		}
		//Type wrappers (classes wrappers)
		void exp06() {
			/*			Classes
			 * byte 	: Byte
			 * short	: Short
			 * int 		: Integer
			 * long		: Long
			 * float 	: Float
			 * double	: Double
			 * char 	: Character
			 * boolean	: Boolean
			 */
			//JDK 5 : AutoBoxing
			Integer x2 = 20;
			System.out.println("x2 = " + x2);
			int a = 30;
			Integer a1 = 30;
			System.out.println("a1 = " + a1);
			//Remarque : Le type Integer ainsi que toutes les autres
			//			classes Wrappers sont Immuable (Immutable)
			//==> Changement de valeur impossible (pas de setters)
			//Exemple de classe immuable : Point
			Point p1 = new Point(20, 30);
			//pas de moyen pour modifier le contenu de p1
			System.out.println("p1 = " + p1);
			
			//unboxing :
			int b = x2;
			int c = x2.intValue();
			
			System.out.println("b = " + b);
			System.out.println("c = " + c);
			
			String s = "35";
			int x3 = Integer.parseInt(s);
			System.out.println("x3 = " + x3);
		}
		
		void exp07() {
			String s1 = "3.14";
			double d1 = Double.parseDouble(s1);
			System.out.println(" d1 = " + d1);
			
			String s2 = "3,14";
			double d2 = 0;
			try {
				d2 = Double.parseDouble(s2);
			} catch (Exception e) {
				System.out.println("Erreur : " + e.getMessage());
			}
			
			System.out.println("d2 = " + d2);
		}
		
	public static void main(String[] args) {
		new Examples();

	}

}
