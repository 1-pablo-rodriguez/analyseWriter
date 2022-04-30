package MEPTL;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cXML.node;
/**
 * 
 * @author pablo rodriguez
 *
 */
public class outils {
	
	public static Dictionary<String, String> traduction = new Hashtable<String, String>();
	
	private static int pointsClass = 0;
	private static int pointTotal=0;
	private static int pointEnJeu = 0;
	

	/**
	 * Chargement des traductions des éléments de l'exercice an utilsant le node "<b>translation</b>" du fichier d'analyse.<br>
	 * Les noms des éléments sont placés dans un dictionnaire la key et la valeur est le code HTML.
	 * 
	 * @param translation Le node "<b>translation</b>" qui se trouve dans le node "<b>setting</b>" du fichier d'analyse.
	 */
	public static void chargeTraduction(node translation) {
		if(translation!=null) {
			
			String classText = "";
			if(translation.getAttributs().get("classtext")!=null) if(!translation.getAttributs().get("classtext").isEmpty()) classText = "</span><span class=\""+ translation.getAttributs().get("classtext") +"\">";
			
			String className = "";
			if(translation.getAttributs().get("class")!=null) if(!translation.getAttributs().get("class").isEmpty())  className = "<div class=\"" + translation.getAttributs().get("class") + "\">";
			
			for(int i = 0 ; i < translation.getNodes().size();i++) {
				String translate = "";
				String contenu ="";
				String link = "";
				String color = "";
				String  codeHTML = "";
				boolean T = false;
				
				if(translation.getNodes().get(i).getAttributs().get("translate")!=null) if(!translation.getNodes().get(i).getAttributs().get("translate").isEmpty()) translate=translation.getNodes().get(i).getAttributs().get("translate");
				if(!translation.getNodes().get(i).getContenu().isEmpty()) contenu = translation.getNodes().get(i).getContenu().get(0).replace("-!", "<").replace("!-", ">");
				if(translation.getNodes().get(i).getAttributs().get("link")!=null) if(!translation.getNodes().get(i).getAttributs().get("link").isEmpty())  link="<p><a href=\"" + translation.getNodes().get(i).getAttributs().get("link") + "\"  target=\"_blank\">Lien</a></p>";
				if(translation.getNodes().get(i).getAttributs().get("color")!=null) if(!translation.getNodes().get(i).getAttributs().get("color").isEmpty())  color="<span style=\"color:" + translation.getNodes().get(i).getAttributs().get("color") + "\">";
				if(translation.getNodes().get(i).getAttributs().get("image")!=null) if(!translation.getNodes().get(i).getAttributs().get("image").isEmpty()) if(translation.getNodes().get(i).getAttributs().get("image").equals("true")) T=true;
				
				if(!contenu.isEmpty() && !className.isEmpty() && !classText.isEmpty()) {
					if(!link.isEmpty()) {
						if(!color.isEmpty()) {
							if(!T) codeHTML =  className + color + translate + "</span>" + classText   + contenu + link + "</span></div>" ;
							if(T) codeHTML =   className + HTML.imgInterogation() + color + translate + "</span>" + classText   + contenu + link + "</span></div>" ;
						}else {
							if(!T) codeHTML = className + translate + "</span>" + classText  + contenu + link + "</span></div>" ;
							if(T) codeHTML =  className + HTML.imgInterogation() + translate + "</span>" + classText  + contenu + link + "</span></div>" ;
						}
					}else {
						if(!color.isEmpty()) {
							if(!T) codeHTML = className + color +  translate + "</span>"+ classText + contenu  + "</span></div>" ;
							if(T) codeHTML = className + HTML.imgInterogation() + color +  translate + "</span>"+ classText + contenu  + "</span></div>" ;
						}else {
							if(!T) codeHTML = className +   translate + classText + contenu  + "</span></div>" ;
							if(T) codeHTML = className + HTML.imgInterogation() +   translate + classText + contenu  + "</span></div>" ;
						}
					}
				}else {
					if(!link.isEmpty()) {
						if(!color.isEmpty()) {
							if(!T) codeHTML = color + translate+ "</span>" + link;
							if(T) codeHTML = color + HTML.imgInterogation() + translate+ "</span>" + link;
						}else {
							if(!T) codeHTML =  translate + link;
							if(T) codeHTML =  HTML.imgInterogation() + translate + link;
						}
					}else {
						if(!color.isEmpty()) {
							if(!T) codeHTML = color + translate+ "</span>";
							if(T) codeHTML = color + HTML.imgInterogation() + translate+ "</span>";
						}else {
							if(!T) codeHTML =  translate;
							if(T) codeHTML =  HTML.imgInterogation() + translate;
						}
					}
				}
				codeHTML = codeHTML.replace("-!", "<").replace("!-", ">");
				if(!codeHTML.isEmpty()) {
					traduction.put(translation.getNodes().get(i).getNomElt().replace("..", " "),codeHTML);
				}
			}
		}
	}
	
	
	
	
	/**
	 * Compare les chaînes de caractères A et sujet en fonction des 8 fonctions ‽ † ¢ → ¦ ↕ ↑ † ×<br>
	 * 
	 * @param A le texte de l'étudiant.
	 * @param Sujet le tetxte du sujet.
	 * @return Une chaine de caractère contenant "Correct" ou "Erreur".
	 */
	public static String Compare(String A, String Sujet ) {
		
		// les points
		String pointString = "" ; 
		pointEnJeu=0;
		if(Sujet!=null) if(Sujet.contains("‽")) {
			pointString = TraitePoint(Sujet);
			Sujet=Sujet.substring(0, Sujet.indexOf("‽"));
		}
		
		// optionnel pas d'analyse
		if(pointEnJeu==0) return "Optionnel";
		
		
		// autre chose que null, none ou vide (analyse très simple) toutes les autres fonctions sont ignorées
		if(Sujet!=null) if(Sujet.contains("†")) {
			if(A==null) return "Erreur : -" + pointString;
			if(A.equals("none")) return "Erreur : -" + pointString;
			if(A.isEmpty()) return "Erreur : -" + pointString;
			IncrementPointClass(pointEnJeu); 
			return "Correct : +" + pointString;
		}
		
		
		// fonction ET CONTIENT plusieurs textes - quelque chose mais pas vide -
		if(Sujet!=null) if(Sujet.contains("×") && !Sujet.contains("¢") && !Sujet.contains("→") && !Sujet.contains("#") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") ) {
			if(A==null) return "Erreur : -" + pointString;
			if(A.equals("none")) return "Erreur : -" + pointString;
			if(A.isEmpty()) return "Erreur : -" + pointString;
			
			if(TraitementETContient(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}
			
			return "Erreur : -" + pointString;
		}
		
		
		// fonction OU puis ET CONTIENT plusieurs textes - quelque chose mais pas vide -
		if(Sujet!=null) if(Sujet.contains("×") && Sujet.contains("¦") && !Sujet.contains("¦(") && !Sujet.contains("¢") && !Sujet.contains("→") && !Sujet.contains("#") && !Sujet.contains("↕") && !Sujet.contains("↑") ) {
			if(A==null) return "Erreur : -" + pointString;
			if(A.equals("none")) return "Erreur : -" + pointString;
			if(A.isEmpty()) return "Erreur : -" + pointString;
					
			if(TraitementOUETcontient(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}
			return "Erreur : -" + pointString;
		}
		
		
		// nettoyage du texte avant de l'analyser et similitude 
		// si ne contient pas d'autres fonctions cette fonction ne peut pas être combinée
		if(Sujet!=null) if(Sujet.contains("¢") && !Sujet.contains("→") && !Sujet.contains("#") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") ) {
			if(A==null) A="null";
			if(similitudeString(A, Sujet,commandes.tolerance_text)) {
				IncrementPointClass(pointEnJeu); 
				return "Correct : +" + pointString;
			}
			return "Erreur : -" + pointString;
		}
		
		
		//intervalle valeur simple pas de bordure, pas de couleur, et pas d'autres fonctions
		if(Sujet.contains("→") && A!=null && !Sujet.contains("#") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if (TraitementIntervalle(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString; 
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		

		// bordure simple avec ou sans intervalles ou autres fonctions
		if(Sujet.contains("#") && Sujet.contains("pt") && Sujet.contains(" ")  && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢") ) {
			if(TraitementBordure2(Sujet,A).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString; 
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		
		// couleur simple pas de bordure ou pas d'autres fonctions
		if(Sujet.contains("#") && !Sujet.contains("pt") && Sujet.contains(" ") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢") ) {
			if(TraitementCouleur(Sujet,A).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString; 
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//Exact avec OU sans d'autres fonctions OU
		if(Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if(TraitementOUExact(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//OU Exact mais sans les espaces et sans d'autres fonctions OU
		if(Sujet.contains("≡") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if(TraitementOUExactSansEspace(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//Opérateur OU avec nettoyage du texte 
		if(Sujet.contains("↕") && !Sujet.contains("¦") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if(TraitementOU(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//Opérateur ou avec nettoyage des caractères spéciaux et des chiffres
		if(Sujet.contains("↑") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("¢")) {
			if(TraitementOUSupprimeChiffreEtsansCase(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		

		// ne contient aucune fonction
		if(!Sujet.contains("≡") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢") && !Sujet.contains("†") && !Sujet.contains("→")) {
			if(A!=null)if(A.equals("auto")) A=null;
			if(Sujet!=null)if(Sujet.equals("auto")) Sujet=null;
			
			if(A!=null) if(A.equals("0cm")) A=null;
			if(Sujet!=null) if(Sujet.equals("0cm")) Sujet=null;
			
			if(A!=null) if(A.equals("none")) A=null;
			if(Sujet!=null) if(Sujet.equals("none")) Sujet=null;
			
			if(A!=null) if(A.isEmpty()) A=null;
			if(Sujet!=null) if(Sujet.isEmpty()) Sujet=null;
			
			if(A!=null) if(A.equals("0")) A=null;
			if(Sujet!=null) if(Sujet.equals("0")) Sujet=null;
			
			if(A!=null) if(A.equals("null")) A=null;
			if(Sujet!=null) if(Sujet.equals("null")) Sujet=null;
			
			if(A!=null) if(A.equals("false")) A=null;
			if(Sujet!=null) if(Sujet.equals("false")) Sujet=null;
			
			if(A!=null) if(Sujet==null) if(!pointString.isEmpty()) { return "Erreur : -" + pointString;}
			if(A!=null) if(Sujet==null) if(pointString.isEmpty()) {return "Erreur";}
			if(A==null) if(Sujet==null) if(!pointString.isEmpty()){IncrementPointClass(pointEnJeu); return "Correct : +" + pointString;}
			if(A==null) if(Sujet==null) if(pointString.isEmpty()) {IncrementPointClass(pointEnJeu);  return "Correct";}
			
			if(A==null) if(Sujet!=null) if(!pointString.isEmpty()) return "Erreur : -" + pointString;
			if(A==null) if(Sujet!=null) if(pointString.isEmpty()) return "Erreur";
			
			if(!pointString.isEmpty()) {
				if(A.equals(Sujet)){
					IncrementPointClass(pointEnJeu); 
					return "Correct : +" + pointString;}
				else {
					return "Erreur : -" + pointString;
				}
			}else {
				if(A.equals(Sujet)){
					IncrementPointClass(pointEnJeu); 
					return "Correct";}
				else {
					return "Erreur";
				}
			}
		}else {
			 return "Erreur combinaison avec † ¢ → ¦ ↕ ↑ ×";
		}
	}
	
	
	/**
	 * Traitement des points du paramètre
	 * @param B
	 * @return
	 */
	public static String TraitePoint(String B) {
		int point = 0;
		pointEnJeu=0;
		String pointString ="";
		String C =B.substring(B.indexOf("‽")+1,B.length());
		if(B!=null) if(B.contains("‽")) if(B.length()>1) if(!C.isEmpty()) {
			try {
				point = Integer.valueOf(B.substring(B.indexOf("‽")+1,B.length()));
			}catch (Exception e) {
				System.out.println("ERREUR sur la ventillation des points : " +B);
				e.getMessage();
			}
		}else {
			point=0;
		}
		
		if(point==0) {
			pointString="";
		}
		
		if(point>0) {
			pointString = Integer.valueOf(point) + " pt";
			IncrementPointTotal(point);
			pointEnJeu= point;
		}
	
		return pointString;
	}
	
	/**
	 * Traitement des intervalles
	 * @return
	 */
	private static String TraitementIntervalle(String A, String B) {
		if(A.isEmpty()) return "Erreur : ";

		String Text[] = B.split("→");
		
		// rechercher un digite une ou plusieur fois en fin.
		Pattern p = Pattern.compile("[^0-9\\.]");
		
		// remplacement de toutes les occurrences par ""
		Text[0]= p.matcher(Text[0]).replaceAll("");
		Text[1] = p.matcher(Text[1]).replaceAll("");
		A = p.matcher(A).replaceAll("");
		
		double b0 = Double.valueOf(Text[0]);
		double b1 = Double.valueOf(Text[1]);
		
		if(A.isEmpty()) return "Erreur : ";
		double v = Double.valueOf(A);
		
		if(v<=b1 && v>=b0 ) return "Correct : ";
		
		return "Erreur : ";
	}
	

	
	/**
	 * Traitement des bordures
	 * Avec ou sans intervalle sur l'épaisseur des traits
	 * Avec 3 styles de tarits solid, dashed, double
	 * Avec traitement de la couleur
	 * @param Sujet
	 * @param B
	 * @return
	 */
	private static String TraitementBordure2(String Sujet, String B) {
		if(Sujet.isEmpty()) return "Erreur : ";
		if(B==null) return "Erreur : ";
		
		String TextB[] = B.split(" ");
		String TextA[] = Sujet.split(" ");
		
		if(TextB.length!=3 || TextA.length!=3) return "Erreur : ";
		
		boolean taille = false;
		boolean type = false;
		boolean couleur =false;
		
		// traitement de la taille (epaisseur du trait)
		if(TextA[0].contains("→")) {
			if(TraitementIntervalle(TextB[0], TextA[0]).equals("Correct : ")) taille = true;
		}else {
			double tailleA = Double.valueOf(TextA[0].replace("pt",""));
			double tailleB = Double.valueOf(TextB[0].replace("pt",""));
			double IT = 0.02;
			if((tailleA<tailleB+IT) && (tailleA>tailleB-IT))taille = true;
		}
		
		
		// traitement du type de trait
		String typeA = TextA[1];
		String typeB = TextB[1];
		
		//trait pointillé
		if(typeA.equals("dash-dot")) typeA="dashed";
		if(typeB.equals("dash-dot")) typeB="dashed";
		if(typeA.equals("dotted")) typeA="dashed";
		if(typeB.equals("dotted")) typeB="dashed";
		if(typeA.equals("fine-dashed")) typeA="dashed";
		if(typeB.equals("fine-dashed")) typeB="dashed";
		if(typeB.equals("dash-dot-dot")) typeB="dashed";
		if(typeA.equals("dash-dot-dot")) typeA="dashed";
		
		if(typeA.equals("double-thin")) typeA="double";
		if(typeB.equals("double-thin")) typeB="double";
		if(typeA.equals("outset")) typeA="double";
		if(typeB.equals("outset")) typeB="double";
		if(typeA.equals("inset")) typeA="double";
		if(typeB.equals("inset")) typeB="double";
		
		
		if(typeA.equals("groove")) typeA="solid";
		if(typeB.equals("groove")) typeB="solid";
		if(typeA.equals("ridge")) typeA="solid";
		if(typeB.equals("ridge")) typeB="solid";
		 
		
		if(typeA.equals(typeB)) type=true;
		
		// traitement de la couleur du trait
		String couleurA = TextA[2].replace("#", "");
		String couleurB = TextB[2].replace("#", "");
		
		if(couleurA.equals(couleurB)) couleur = true;
		
		if(!couleur) {
			if(TraitementCouleur(couleurA, couleurB).equals("Correct : ")) {
				couleur =true;
			}
		}
		
		if(taille && type && couleur) return "Correct : ";
		
		return "Erreur : ";
	}
	
	/**
	 * Traitement de la couleur par comparaison
	 * La tolérance sur les 3 couleurs primaires RVB est de plus ou moins 30 par défaut.
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementCouleur(String Sujet, String B) {
		if(B==null) return "Erreur : ";
		if(B.isEmpty()) return "Erreur : ";
		
		// traitement de la couleur du trait
		String couleurS = Sujet.replace("#", "");
		String couleurB = B.replace("#", "");
		
		if(couleurS.equals(couleurB)) return "Correct : ";
		
		if(couleurS.length()==6 && couleurB.length()==6) {
			int  Sred = Integer.parseInt(couleurS.substring(0, 2),16);
			int  Sgreen = Integer.parseInt(couleurS.substring(2, 4),16);
			int  Sblue = Integer.parseInt(couleurS.substring(4, 6),16);
			
			int  Bred = Integer.parseInt(couleurB.substring(0, 2),16);
			int  Bgreen = Integer.parseInt(couleurB.substring(2, 4),16);
			int  Bblue = Integer.parseInt(couleurB.substring(4, 6),16);
			
			// couleur dominante avec 10 niveau
//			if(Sred>Sgreen+10 && Sred>Sblue+10 && Bred>Bgreen+10 && Bred>=Bblue+10) return "Correct : ";//couleur=true; //Couleur dominante rouge
//			if(Sgreen>Sred+10 && Sgreen>Sblue+10 && Bgreen>Bred+10 && Bgreen>Bblue+10) return "Correct : "; //couleur = true; //Couleur dominante vert
//			if(Sblue>Sred+10 && Sblue>Sgreen+10 && Bblue>Bred+10 && Bblue>Bgreen+10) return "Correct : "; //couleur = true; //Couleur dominante bleu
//			if(Sblue==Sred && Sblue==Sgreen && Bblue==Bred && Bblue==Bgreen)  return "Correct : "; //couleur = true; //Couleur gris (tous les gris)
			if(procheCouleur(Sred,Bred,Sgreen,Bgreen,Sblue,Bblue))  return "Correct : "; //couleur = true; //Couleur gris (tous les gris)
		}
		//if(couleur) return "Correct : ";
		
		return "Erreur : ";
	}
	
	/**
	 * Couleur proche avec une tolérance par défaut de 20 nuances dans l'espace colorométrique RVB.
	 * @param R1 Rouge Sujet
	 * @param R2 Rouge Student
	 * @param V1 Vert Sujet
	 * @param V2 Vert Student
	 * @param B1 Bleu Sujet
	 * @param B2 Bleu Student
	 * @return
	 */
	private static Boolean procheCouleur(int R1, int R2, int V1, int V2, int B1, int B2) {
		int ecartR = Math.abs(R1-R2);
		int ecartV = Math.abs(V1-V2);
		int ecartB = Math.abs(B1-B2);
		if((ecartR <=commandes.tolerance_rouge)&&(ecartV <=commandes.tolerance_vert)&&(ecartB <=commandes.tolerance_bleu) )return true;
		return false;
	}
	
	/**
	 * Traitement des conditions OU avec nettoyage du texte
	 * Comparaison egale, contient , similitude
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementOU(String A, String B) {
		if(A==null) A="none";
		if(A.isEmpty()) A="none";
		
		A = NetTexte(A);
	
		
		String TextB[] = B.split("↕");
		for(int i=0;i<TextB.length;i++) {
			TextB[i] = NetTexte(TextB[i]);
			if(A.equals(TextB[i])) return "Correct : ";
			if(A.contains(TextB[i])) return "Correct : ";
			if(similitudeString(A, TextB[i],commandes.tolerance_text)) return "Correct : ";
		}
		
		return "Erreur : ";
	}
	
	
	
	/**
	 * Traitement Exact
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementOUExact(String A, String B) {
		if(A==null && !B.contains("null")) return "Erreur : ";
		if(A==null && B.contains("null")) return "Correct : ";
		
		String TextB[] = B.split("¦");
		for(int i=0;i<TextB.length;i++) {
			if(!TextB[i].contains("→")) {
				if(A.equals(TextB[i])) return "Correct : ";
			}else {
				if (TraitementIntervalle(A,TextB[i]).equals("Correct : ")) return "Correct : ";
			}
		}
		return "Erreur : ";
	}
	
	/**
	 * 
	 * @param A
	 * @param Sujet
	 * @return
	 */
	private static String TraitementOUExactSansEspace(String A, String Sujet) {
		if(A==null && !Sujet.contains("null")) return "Erreur : ";
		if(A==null && Sujet.contains("null")) return "Correct : ";
		
		A = A.replace(" ", "");
		String TextB[] = Sujet.split("≡");
		for(int i=0;i<TextB.length;i++) {
			if(!TextB[i].contains("→")) {
				if(A.equals(TextB[i].replace(" ", ""))) return "Correct : ";
			}else {
				if (TraitementIntervalle(A,TextB[i].replace(" ", "")).equals("Correct : ")) return "Correct : ";
			}
		}
		return "Erreur : ";
	}
	
	
	/**
	 * Fonction ET contient le texte du node Sujet
	 * @param A
	 * @param Sujet
	 * @return
	 */
	private static String TraitementETContient(String A, String Sujet) {
		String TextB[] = Sujet.split("×");
		boolean Correct = false;
		for(int i=0;i<TextB.length;i++) {
			if(!TextB[i].isEmpty()) {
				if(A.trim().contains(TextB[i].trim())) {
					Correct = true;
				}else {
					return "Erreur : ";
				}
			}
		}
		if(Correct) return "Correct : ";
		return "Erreur : ";
	}	
	
	/**
	 * Fonction OU contenant des ET contient le texte du node Sujet
	 * @param A
	 * @param Sujet
	 * @return
	 */
	private static String TraitementOUETcontient(String A, String Sujet) {
		String TextB[] = Sujet.split("¦");
		
		for(int i=0;i<TextB.length;i++) {
			if(TextB[i].contains("×")) {
					if(TraitementETContient( A, TextB[i]).equals("Correct : ")) return "Correct : ";
				}else {
					if(A.trim().contains(TextB[i].trim())) return "Correct : ";
			}
		}
		return "Erreur : ";
	}
	
	/**
	 * Traitement des condition OU en supprimant les chiffres
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementOUSupprimeChiffreEtsansCase(String A, String B) {
		if(A==null) A="none";
		if(A.isEmpty()) A="none";
		
		// rechercher un digite une ou plusieur fois en fin.
		Pattern p = Pattern.compile("[0-9]");
		// création du moteur associé à la regex sur la chaîne A
		Matcher m = p.matcher(A);
		// remplacement de toutes les occurrences par ""
		A= m.replaceAll("");
		A=A.toLowerCase();
		
		m = p.matcher(B);
		B = m.replaceAll("");
		B= B.toLowerCase();
		
		
		A = NetTexte(A);
		
		
		String TextB[] = B.split("↑");
		for(int i=0;i<TextB.length;i++) {
			TextB[i] = NetTexte(TextB[i]);
			if(A.equals(TextB[i].toLowerCase())) return "Correct : ";
		}
		
		return "Erreur : ";
	}
	
	
	
	/**
	 * Sans les point
	 * @param B
	 * @return
	 */
	public static String withoutPoint(String B) {
		if(B!=null) if(B.contains("‽")) {
			B=B.substring(0, B.indexOf("‽"));
		}
		return B;
	}
	

	public static String withoutCode(String B) {
		B=B.replace("¢", "");
		return B;
	}
	
	public static String withoutCodeAndPoint(String B) {
		if(B!=null) {
			if(B.contains("‽")) B=B.substring(0, B.indexOf("‽"));
			if(!B.isEmpty()) B=B.replace("¢", "");
		}
		return B;
	}
	
	/**
	 * Uniquement suppression des codes ‽, ↑, ↕, ≡, ¢<br>
	 * pour effectuer la recherche d'un node par son contenu.
	 * @param B un string
	 * @return B un string
	 */
	public static String withoutCodeAndPointPourRechercheContenuExact(String B) {
		if(B!=null) {
			if(B.contains("‽")) B=B.replaceAll("‽[0-9]{0,}", ""); //B=B.substring(0, B.indexOf("‽"));
			if(B.contains("↑")) B=B.replace("↑", "");
			if(B.contains("↕")) B=B.replace("↕", "");
			if(B.contains("≡")) B=B.replace("≡", "");
			if(!B.isEmpty()) B=B.replace("¢", "");
			 B=B.replaceAll("‽[0-9]{1,}", "");
		}
		return B;
	}
	
	
	/**
	 * Supprime les caracatères spéciaux, la pontuaction, tous les espaces, et les chiffres<br>
	 * Bascule  le texte en minuscule.<br>
	 * @param A Le texte qui doit être nettoyé.
	 * @return Le texte nettoyé.
	 */
	public static String NetTexte(String A) {
		if (A!=null) {
			A=A.toLowerCase().trim();
	        A = A.replace("&apos;", "");
	        A = A.replace("&quot", "");
	        
	        Pattern pt = Pattern.compile("[^a-z0-9]"); // avec les chiffres "[^a-zA-Z0-9]"
	        Matcher match= pt.matcher(A);
	        while(match.find()){
	            String s= match.group();
	            A=A.replaceAll("\\"+s, "");
	        }
		        
	        A=A.replaceAll("[0-9]", "");
	        A=A.replace(" ", "");
	        
	        A=A.toLowerCase();
	        
		}
		return A;
		}
	
	
	/**
	 * Netoyage les chiffres placés à la fin du texte.<br>
	 * Cette méthode est utilisée pour supprimer les numéros de page dans les index (table de matières par exemple).<br>
	 * @param A Le texte.
	 * @return Le texte A sans chiffre à la fin du texte.
	 */
	public static String NetChiffreALaFin(String A) {
		A=A.replaceAll("{1,}[0-9]", "");
	    return A;
	}
	
	
	
	/**
	 * Retourne la chaîne sans le numéro à la fin
	 * @param A
	 * @return
	 */
	public static String RemoveLastNumber(String A) {
		Pattern p = Pattern.compile("[0-9]+$");
		Matcher m = p.matcher(A);
		A= m.replaceAll("");
		return A;	
	}
	
	
	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static ArrayList<node> AjouteDansALesNodesB(ArrayList<node> A,ArrayList<node> B){
		for(int i=0; i<B.size();i++) {
			A.add(B.get(i));
		}
		return A;
	}
	
	/**
	 * Initialisation des points
	 */
	public static void initiliseLesPoints() {
		pointsClass=0;
		pointEnJeu=0;
		pointTotal=0;
				
	}

	

	/**
	 * Compare deux chaînes de caractères avec un seuil de similitude (79% par défaut).<br>
	 * Lorsque les deux chaînes de caractères partages 79% des caractères comparés par groupe de 2 ou 3 ou 4. Alors retourne TRUE.<br>
	 * Lorsque les deux chaînes de caractères partages moins de 79% des caractères comparés par groupe de 2 ou 3 ou 4. Alors retourne FALSE.<br>
	 * Les Chaînes de caractères doivent avoir au moins 6 caractères, après néttoyage du texte avec la méthode NetTexte().<br>
	 * Sinon le seuil de tolérance est de 99.99%.<br>
	 * <ul>
	 * <li>Lorsque la longueur de la chaîne de caractères est inférieure à 50 caractères alors compare par groupe de 2 caractères.</li>
	 * <li>Lorsque la longueur de la chaîne de caractères est entre à 50 et 200 caractères alors compare par groupe de 3 caractères.</li>
	 * <li>Lorsque la longueur de la chaîne de caractères est supérieure à 200 caractères alors compare par groupe de 4 caractères.</li>
	 * </ul>
	 * <br>
	 * @param A Chaîne de caracatères de l'étudiant.
	 * @param Modele Chaîne de caractères du sujet.
	 * @param tolerance_text Le seuil de tolérance pour la comparaison (valeur comprise entre 0 et 1).
	 * @return TRUE ou FALSE en fonction de la comparaison et du seuil de tolérance.
	 */
	private static boolean similitudeString(String A, String Modele, Double tolerance_text) {
		if(tolerance_text==null) tolerance_text = 0.79 ;
		
		Modele = NetTexte(Modele);
		A = NetTexte(A);
		
		double Sim = StringSimilarity.similarity(A, Modele);
		
		if(Sim<tolerance_text) return false;
		
//		if(A.length()<6 || Modele.length()<6) tolerance_text = 0.9999;
//		
//		int compteur2 = 0 ;
//		int total2 = 0;
//		
//		String B = "";
//		
//		int step = 2; //valeur par défaut du pas	
//		if(Modele.length()>=50 && Modele.length()<200) step=3;  
//		if(Modele.length()>=200) step = 4; 
//
//		
//		for(int i = 0 ; i < Modele.length()-step;i=i+step) {
//			B = Modele.substring(i, i+step);
//			if(A.contains(B)) compteur2++;
//			total2++;
//			if(i+step>Modele.length()-step) {
//				B = Modele.substring(i+step, Modele.length());
//				if(A.contains(B)) compteur2++;
//				total2++;
//			}
//		}
//		
//	
//		int compteur2b = 0 ;
//		int total2b = 0;
//		
//		for(int i = 0 ; i < A.length()-step;i=i+step) {
//			B = A.substring(i, i+step);
//			if(Modele.contains(B)) compteur2b++;
//			total2b++;
//			if(i+step>A.length()-step) {
//				B = A.substring(i+step, A.length());
//				if(Modele.contains(B)) compteur2b++;
//				total2b++;
//			}
//		}
//		
//		
//		double rapport1 = ((double)compteur2/(double)total2);
//		double rapport2 = ((double)compteur2b/(double)total2b);
//		
//		if(rapport1<tolerance_text){
//			return  false;
//		}
//
//		if(rapport2<tolerance_text){
//			return  false;
//		}

		return true;
	}
	
	
	/**
	 * Remplace les _20_ par un espace
	 * Remplace les _26_ par un &
	 * @param A
	 * @return
	 */
	static String remplaceCaracteresCodageAttribut(String A) {
		if(A!=null) {
			A = A.replace("_20_", " ");
			A = A.replace("_26_", "&");
		}
		return A;
	}
	
	
//	/**
//	 * Nettoyage (suppression) des caractères spéciaux
//	 * @param A
//	 * @return
//	 */
//	static String NetTexte21(String A) {
//		if (A!=null) {
//			A=A.toLowerCase().trim();
//			
//			ArrayList<Integer> table1 = new ArrayList<Integer>();
//			byte[] V = A.getBytes();
//		
//			byte[] W = new byte[V.length];
//			int c =0;
//			for(int i = 0 ; i < V.length;i++) {
//				if(V[i]>0) {
//					//if (V[i]!=63 && V[i]!=-128 && V[i]!=-103) {  //63 les accents é ; -128 le symbole € -30,-128,-103 l'apostrophe
//					W[c]=V[i];
//					table1.add((int) W[c]);
//					c++;
//				}
//			}
//			try {
//				A = new String(W,"UTF-8");
//
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			A=A.replace("@", "");
//			A=A.replace("?", "");
//			A=A.replace("!", "");
//			A=A.replace("α", "");
//			A=A.replace("β", "");
//			A=A.replace("γ", "");
//			A=A.replace("δ", "");
//			A=A.replace("ε", "");
//			A=A.replace("σ", "");
//			A=A.replace("τ", "");
//			A=A.replace("ζ", "");
//			A=A.replace("η", "");
//			A=A.replace("θ", "");
//			A=A.replace("ι", "");
//			A=A.replace("’", "");
//			A=A.replace("'", "");
//					
//			A=A.replace(" ", "");
//			A=A.replace("'", ""); 
//			A=A.replace("«", "");
//			A=A.replace("»", "");
//			A=A.replace("&apos;", "");
//			A=A.replace("d&apos;", "");
//			
//			
//			A=A.replace("’", "");
//			A=A.replace("e", "");
//			A=A.replace("é", "");
//			A=A.replace("é", ""); // pas le même encodage
//			
//			A=A.replace("è", "");
//			A=A.replace("è", ""); // pas le même encodage
//			A=A.replace("ê", "");
//			
//			
//			A=A.replace("o", "");
//			A=A.replace("ô", "");
//			
//			A=A.replace("a", "");
//			A=A.replace("â", "");
//			A=A.replace("â", ""); // pas le même encodage
//			
//			A=A.replace("à", "");
//			A=A.replace("à", ""); // pas le même encodage
//			
//			A=A.replace("u", "");
//			A=A.replace("ù", "");
//			A=A.replace("û", "");
//			
//			A=A.replace("c", "");
//			A=A.replace("ç", "");
//			
//			A=A.replace("i", "");
//			A=A.replace("î", "");
//			A=A.replace("ï", "");
//			A=A.replace("ï", ""); // pas le même encodage
//			
//			A=A.replace("0", "");
//			A=A.replace("1", "");
//			A=A.replace("2", "");
//			A=A.replace("3", "");
//			A=A.replace("4", "");
//			A=A.replace("5", "");
//			A=A.replace("6", "");
//			A=A.replace("7", "");
//			A=A.replace("8", "");
//			A=A.replace("9", "");
//			
//			
//			A=A.replace(":", "");
//			A=A.replace("-", "");
//			A=A.replace(".", "");
//			A=A.replace(",", "");
//			A=A.replace("_", "");
//			A=A.replace("_", "");
//			A=A.replace("(", "");
//			A=A.replace(")", "");
//			A=A.replace("\"", "");
//			A=A.replace("+", "");
//			A=A.replace("Œ", "");
//			A=A.replace("œ", "");
//			A=A.replace("oe", "");
//			A=A.replace("\r", "");
//			A=A.replace("\n", "");
//			A=A.replace("\t", "");
//			A=A.replace("/", "");
//			A=A.replace("(", "");
//			A=A.replace(")", "");
//			A=A.replace(" ","");
//			
//			
//			
//			A=A.trim();
//
//		}
//		return A;
//	}

	
	
	

	public static int getPointsClass() {
		return pointsClass;
	}


	public static int getPointTotal() {
		return pointTotal;
	}


	public static int getPointEnJeu() {
		return pointEnJeu;
	}

	public static double getProportionCorrect() {
		return (double) pointsClass/pointTotal;
	}

	public static void setPointsClass(int ptsClass) {
		pointsClass = ptsClass;
	}


	public static void setPointTotal(int ptTotal) {
		pointTotal = ptTotal;
	}


	public static void setPointEnJeu(int ptEnJeu) {
		pointEnJeu = ptEnJeu;
	}
	
	public static void IncrementPointTotal(Integer pt) {
		pointTotal=pointTotal+ pt;
	}
	
	public static void decrementPointEnJeuDuTotal() {
		pointTotal=pointTotal-pointEnJeu;
	}
	
	private static void IncrementPointClass(Integer pt) {
		pointsClass=pointsClass+ pt;
	}
	
	
	
	
}

