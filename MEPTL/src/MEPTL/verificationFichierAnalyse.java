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
	  	  	clotureWithErrorInanalyzeFile();
		}
		
		//vérification des attributs du node fichier
		if(Sujet.getAttributs().size()>0) {
			verificationNodeFichier(Sujet.getAttributs());
		}else {
			System.out.println();
	  	  	System.out.println("**-** ERROR dans le fichier d'analyse.");
	  		System.out.println("* le node \"fichier\" ne contient aucun attribut.");
	  	  	System.out.println();
			erreur=true;
		}
		

		
		//Vérification des attributs du node style:paragraph et vérification style de paragraphe par défaut
		if(Sujet.retourneEnfantsByNameExist("style:paragraph")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("style:paragraph").getAttributs(),"style:paragraph");
			if(Sujet.retourneFirstEnfantsByName("style:paragraph").retourneEnfantsByNameExist("style:default-style")) {
				verifcationStyleParagraphDefaut(Sujet.retourneFirstEnfantsByName("style:paragraph").retourneFirstEnfantsByName("style:default-style"));
			}
		}
		
		//Vérification des attributs du node office:meta
		if(Sujet.retourneEnfantsByNameExist("office:meta")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("office:meta").getAttributs(),"office:meta");
		}
		
		//Vérification des attributs du node style:page
		if(Sujet.retourneEnfantsByNameExist("style:page")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("style:page").getAttributs(),"style:page");
		}
		
		//Vérification des attributs du node sequences
		if(Sujet.retourneEnfantsByNameExist("sequences")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("sequences").getAttributs(),"sequences");
		}
		
		//Vérification des attributs du node numerotationchapitre
		if(Sujet.retourneEnfantsByNameExist("numerotationchapitre")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("numerotationchapitre").getAttributs(),"numerotationchapitre");
		}

		//Vérification des attributs du node frames
		if(Sujet.retourneEnfantsByNameExist("frames")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("frames").getAttributs(),"frames");
		}
		
		//Vérification des attributs du node sections
		if(Sujet.retourneEnfantsByNameExist("sections")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("sections").getAttributs(),"sections");
		}
		
		//Vérification des attributs du node biblio
		if(Sujet.retourneEnfantsByNameExist("biblio")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("biblio").getAttributs(),"biblio");
		}
		
		//Vérification des attributs du node tablematieres
		if(Sujet.retourneEnfantsByNameExist("tablematieres")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("tablematieres").getAttributs(),"tablematieres");
		}
		
		//Vérification des attributs du node tableillustrations
		if(Sujet.retourneEnfantsByNameExist("tableillustrations")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("tableillustrations").getAttributs(),"tableillustrations");
		}
		
		//Vérification des attributs du node structurepage
		if(Sujet.retourneEnfantsByNameExist("structurepage")) {
			verificationNodeEvaluate(Sujet.retourneFirstEnfantsByName("structurepage").getAttributs(),"structurepage");
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
	
	/**
	 * Vérification de la présence de l'attribut addmenu=true lorsque l'attribut evaluer=true.<br/>
	 * Uniquement pour les nodes principaux.<br/>
	 * <br/>
	 * @param attribut
	 * @param nameNode
	 */
	private static void verificationNodeEvaluate(Dictionary<String, String> attribut, String nameNode) {
		if(attribut.get("evaluer")!=null) {
			if(attribut.get("evaluer").equals("true")) {
				if(attribut.get("addmenu")!=null) {
					if(!attribut.get("addmenu").equals("true")) {
			  			System.out.println();
			  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \""+ nameNode +"\".");
			  			System.out.println("Le node \"" + nameNode +"\" doit avoir l'attribut \"addmenu=true\".");
			  			System.out.println("Dans cette version, il est obligatoire de créer une synthèse et un menu pour les nodes principaux.");
			  			System.out.println();
			  			erreur=true;
					}
				}else {
					System.out.println();
		  			System.out.println("**-** ERROR dans le fichier d'analyse au niveau du node \""+ nameNode +"\".");
		  			System.out.println("Le node \"" + nameNode +"\" doit avoir l'attribut \"addmenu=true\". Cette attribut a été supprimé.");
		  			System.out.println("Dans le node \"" + nameNode +"\" replacer l'attribut \"addmenu=true\".");
		  			System.out.println("Dans cette version, il est obligatoire de créer une synthèse et un menu pour les nodes principaux.");
		  			System.out.println();
		  			erreur=true;
				}
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
	
	/**
	 * Clôture lorsqu'il y a une erreur dans le fichier d'analyse
	 */
	public static void clotureWithErrorInanalyzeFile() {
		System.out.println();
		System.out.println("\t\t┌─────────────────────────────────────────────┐");
		System.out.println("\t\t│  You made a mistake in your analyze file.   │");
		System.out.println("\t\t│                                             │");
		System.out.println("\t\t│  You need to look for your error in the     │");
		System.out.println("\t\t│  analyze file. Read the information above.  │");		
		System.out.println("\t\t│                                             │");
		System.out.println("\t\t│  (')_(')                                    │");
		System.out.println("\t\t│  (=`.°=)                                    │");
		System.out.println("\t\t│  (\")__(\") .. see you soon, analyseWriter.   │");
		System.out.println("\t\t└─────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
	}
	
	
}
