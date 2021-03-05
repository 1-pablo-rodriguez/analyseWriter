package MEPTL;

import java.util.Dictionary;
import java.util.Enumeration;

import cXML.node;

public class verificationFichierAnalyse {
	public static boolean erreur = false;
	
	public verificationFichierAnalyse(node Sujet) throws CloneNotSupportedException{
		if(Sujet==null) {
			System.out.println();
	  	  	System.out.println("**-** Erreur le fichier d'analyse est null.");
	  	  	System.out.println();
	  	  	System.exit(0);
		}
		
		//vérification des attribut du node fichier
		if(Sujet.getAttributs().size()>0) {
			verificationNodeFichier(Sujet.getAttributs());
		}else {
			System.out.println();
	  	  	System.out.println("**-** ERROR dans le fichier d'analyse.");
	  		System.out.println("* le node \"fichier\" ne contient aucun attribut.");
	  	  	System.out.println();
			erreur=true;
		}
		
		//vérification style de paragraphe par défaut
		if(Sujet.retourneFirstEnfantsByName("style:paragraph").getNomElt().equals("style:paragraph")) {
			if(Sujet.retourneFirstEnfantsByName("style:paragraph").retourneEnfantsByNameExist("style:default-style")) {
				verifcationStyleParagraphDefaut(Sujet.retourneFirstEnfantsByName("style:paragraph").retourneFirstEnfantsByName("style:default-style"));
			}
		}
		
		
		
		//vérification du node structure
		if(Sujet.retourneEnfantsByNameExist("structurepage")) {
			verifNodeAutoriserDansStructure(Sujet.retourneFirstEnfantsByName("structurepage"));
		}
		
	}
	
	
	private static void verificationNodeFichier(Dictionary<String, String> attribut) {
  		// le node fichier ne doit pas avoir un attribut addmenu="true"
  		if(attribut.get("addmenu")!=null) if(attribut.get("addmenu").equals("true")) {
  			System.out.println();
  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \"fichier\".");
  			System.out.println("Le node \"fichier\" ne doit pas avoir l'attribut \"addmenu=true\".");
  			System.out.println("Il n'est pas autorisé de créer un menu pour l'ensemble du feedback.");
  			System.out.println("Seul les nodes principaux peuvent avoir cet attribut avec cette valeur.");
  			System.out.println();
  			erreur=true;
  		}
  		
  		// le node fichier doit avoir l'attribut evaluer=true
  		if(attribut.get("evaluer")==null) {
  			System.out.println();
  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \"fichier\".");
  			System.out.println("Le node \"fichier\" doit contenir l'attribut \"evaluer=true\".");
  			System.out.println("Ce node a été supprimé ou a été renommé.");
  			System.out.println();
  			erreur=true;
  		}else {
  			if(!attribut.get("evaluer").equals("true")) {
  				System.out.println();
  	  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \"fichier\".");
  	  			System.out.println("Le node \"fichier\" doit contenir l'attribut \"evaluer=true\".");
  	  			System.out.println("La valeur de ce node n'est pas correcte (différent de true).");
  	  			System.out.println();
  	  			erreur=true;
  			}
  		}
  		
  		// le node fichier doit contenir l'attribut metaSujet et une valeur autre que le point d'interrogation ou vide
  		if(attribut.get("metaSujet")==null) {
  			System.out.println();
  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \"fichier\".");
  			System.out.println("Le node \"fichier\" doit contenir l'attribut \"metaSujet\".");
  			System.out.println("Ce node a été supprimé ou a été renommé.");
  			System.out.println("Ce node doit contenir obligatoirement une valeur.");
  			System.out.println();
  			erreur=true;
  		}else {
  			if(attribut.get("evaluer").equals("?") || attribut.get("evaluer").isEmpty() ) {
  				System.out.println();
  	  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \"fichier\".");
  	  			System.out.println("Le node \"metaSujet\" doit contenir une valeur autre que \"?\" et pas vide.");
  	  			System.out.println("Dans les propriétés personnalisées du fichier, veuillez créer la propriété \"Sujet\" et saisir un texte comme valeur.");
  	  			System.out.println();
  	  			erreur=true;
  			}
  		}
  		
  		
	}
	
	
	private static void verifNodeAutoriserDansStructure(node structure) throws CloneNotSupportedException {
   		
		String nom = structure.getNomElt();
		if(nom.equals("text:p") || nom.equals("text:h")||nom.equals("text:database-display")||nom.equals("table:table-cell")
	  				||nom.equals("text:section")||nom.equals("draw:frame")||nom.equals("text:user-defined")||nom.equals("table:table-row") 
	  				||nom.equals("table:table-row")||nom.equals("structurepage")||nom.equals("page")||nom.contains("text:table-of-content")
	  				||nom.contains("text:illustration")||nom.equals("text:note")||nom.equals("text:conditional-text")||nom.equals("text:date")||nom.isEmpty()) {
	  		}else {
	  			Enumeration<String> key = structure.getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(structure.getAttributs().get(k).contains("‽")) {
						System.out.println();
		  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse au niveau du node \"structurepage\".");
		  	  	  		System.out.println("*");
		  	  	  		System.out.println("* Le node " +  nom  + " ne doit pas avoir d'attribut évalué.");
		  	  	  		System.out.println("* Le node " + nom + " peut avoir l'attribut evaluer=\"true\" mais aucun attribut ne peut contenir ‽.");
		  	  	  		System.out.println();
		  	  	  		erreur= true;
					}
				}
	  		}

		
		
		//verification des nodes autorisés dans la structure
  		for(int i = 0 ; i < structure.getNodes().size(); i++) {
  			nom = structure.getNodes().get(i).getNomElt();
  			
  			if(nom.equals("text:p") || nom.equals("text:h")||nom.equals("text:database-display")||nom.equals("table:table-cell")
  	  				||nom.equals("text:section")||nom.equals("draw:frame")||nom.equals("text:user-defined")||nom.equals("table:table-row") 
  	  				||nom.equals("table:table-row")||nom.equals("structurepage")||nom.equals("page")||nom.contains("text:table-of-content")
  	  				||nom.contains("text:illustration")||nom.equals("text:note")||nom.equals("text:conditional-text")||nom.equals("text:date")||nom.isEmpty()) {
  	  		}else {
	  	  		Enumeration<String> key = structure.getNodes().get(i).getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(structure.getNodes().get(i).getAttributs().get(k).contains("‽")) {
						System.out.println();
		  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse au niveau du node \"structurepage\".");
		  	  	  		System.out.println("*");
		  	  	  		System.out.println("* Le node " +  nom  + " ne doit pas avoir d'attribut évalué.");
		  	  	  		System.out.println("* Le node " + nom + " peut avoir l'attribut evaluer=\"true\" mais aucun attribut ne peut contenir ‽.");
		  	  	  		System.out.println("*");
		  	  	  		System.out.println("**-** FIN");
		  	  	  		System.out.println();
		  	  	  		erreur= true;
					}
				}
  	  		}		
  			verifNodeAutoriserDansStructure(structure.getNodes().get(i));
  		}
  	}
	
	private static void verifcationStyleParagraphDefaut(node styleParagraphDefault) {
		if(styleParagraphDefault.getAttributs().get("evaluer")!=null) {
			if(styleParagraphDefault.getAttributs().get("evaluer").equals("true")) {
				System.out.println();
	  			System.out.println("**-** WARNING dans le fichier d'analyse au niveau du node \"style:paragraph\".");
	  			System.out.println("Le node \"style:default-style\" ne doit pas contenir l'attribut \"evaluer=true\".");
	  			System.out.println("Les valeurs par défauts sont ajoutées aux différents nodes \"style:paragraph\" évalués.");
	  			System.out.println("Cependant, il faut que dans les attributs du node \"style:default-style\", il y ait le code d'évaluation \"?\".");
	  			System.out.println();
			}
		}
	}
	
	
}
