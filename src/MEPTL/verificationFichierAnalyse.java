package MEPTL;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cXML.node;

public class verificationFichierAnalyse {
	public static boolean erreur = false;
	
	public verificationFichierAnalyse(node Sujet) throws CloneNotSupportedException{
		if(Sujet==null) {
			System.out.println();
	  	  	System.out.println("**-** Erreur, le fichier d'analyse est null.");
	  	  	System.out.println();
	  	  	clotureWithErrorInanalyzeFile();
		}
		
		//** Vérification des attributs du node fichier
		if(Sujet.getAttributs().size()>0) {
			verificationNodeFichier(Sujet.getAttributs());
		}else {
			System.out.println();
	  	  	System.out.println("**-** Erreur, le fichier d'analyse est vide.");
	  	  	System.out.println();
			erreur=true;
		}
		
		
		//** Vérification des attributs du node style:paragraph et vérification style de paragraphe par défaut
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
	
		//vérification du node setting
		if(Sujet.retourneEnfantsByNameExist("setting")) {
			verificationNodeSetting(Sujet.retourneFirstEnfantsByName("setting"));
		}
		
	}
	
	
	private static void verificationNodeFichier(Dictionary<String, String> attribut) {
  		// le node fichier ne doit pas avoir un attribut addmenu="true"
  		if(attribut.get("addmenu")!=null) {
  			System.out.println();
  			System.out.println("**-** Erreur dans le fichier d'analyse le node \"fichier\".");
  			System.out.println("Le node \"fichier\" ne doit pas contenir l'attribut \"addmenu = true\".");
  			System.out.println();
  			erreur=true;
  		}
  		
  		// le node fichier doit avoir l'attribut evaluer=true
  		if(attribut.get("evaluer")==null) {
  			System.out.println();
  			System.out.println("**-** Erreur dans le fichier d'analyse le node \"fichier\".");
  			System.out.println("Le node \"fichier\" doit contenir evaluer=\"true\".");
  			System.out.println("l'attribut \"evaluer\" a été supprimé.");
  			System.out.println();
  			erreur=true;
  		}else {
  			if(!attribut.get("evaluer").equals("true")) {
  				System.out.println();
  	  			System.out.println("**-** Erreur dans le fichier d'analyse le node \"fichier\".");
  	  			System.out.println("Le node \"fichier\" doit contenir evaluer=\"true\".");
  	  			System.out.println("La valeur doit être TRUE.");
  	  			System.out.println();
  	  			erreur=true;
  			}
  		}
  		
  		// le node fichier doit contenir l'attribut metaSujet et une valeur autre que le point d'interrogation ou vide
  		if(attribut.get("metaSujet")==null) {
  			System.out.println();
  			System.out.println("**-** Erreur dans le fichier d'analyse le node \"fichier\".");
  			System.out.println("Le node \"fichier\" doit contenir l'attribut \"sujetMeta\".");
  			System.out.println("L'attribut \"sujetMeta\" a été supprimé.");
  			System.out.println("Cet attribut doit contenir une valeur.");
  			System.out.println();
  			erreur=true;
  		}else {
  			if(attribut.get("evaluer").equals("?") || attribut.get("evaluer").isEmpty() ) {
  				System.out.println();
  	  			System.out.println("**-** Erreur dans le fichier d'analyse le node \"fichier\".");
  	  			System.out.println("L'attribut \"sujetMeta\" doit contenir une valeur autre que \"?\" ou vide.");
  	  			System.out.println("Dans les propriétés personnalisées des fichiers ODF, vous pouvez ajouter la propriété \"Sujet\" et faire correspondre à cette valeur.");
  	  			System.out.println("Voir la documentation d'analyseWriter.");
  	  			System.out.println();
  	  			erreur=true;
  			}
  		}
  		
  		
	}
	
	/**
	 * Vérification de la présence de l'attribut addmenu=true lorsque l'attribut evaluer=true.<br>
	 * Uniquement pour les nodes principaux.<br>
	 * <br>
	 * @param attribut
	 * @param nameNode
	 */
	private static void verificationNodeEvaluate(Dictionary<String, String> attribut, String nameNode) {
		if(attribut.get("evaluer")!=null) {
			if(attribut.get("evaluer").equals("true")) {
				if(attribut.get("addmenu")!=null) {
					if(!attribut.get("addmenu").equals("true")) {
			  			System.out.println();
			  			System.out.println("**-** Erreur dans le fichier d'analyse. Dans le node \""+ nameNode +"\".");
			  			System.out.println("Le node principal \"" + nameNode +"\" doit contenir l'attribut \"addmenu=true\".");
			  			System.out.println("Pour cette version, Il est necessaire de créer un lien de menu du node principal,(tableau de syntèse des feedback).");
			  			System.out.println();
			  			erreur=true;
					}
				}else {
					System.out.println();
		  			System.out.println("**-** Erreur dans le fichier d'analyse. Dans le node \""+ nameNode +"\".");
		  			System.out.println("Le node principal \"" + nameNode +"\" doit contenir l'attribut \"addmenu=true\". Cet attribut a été supprimé.");
		  			System.out.println("Pour cette version, Il est necessaire de créer un lien de menu du node principal,(tableau de syntèse des feedback).");
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
	  				||nom.contains("text:illustration")||nom.equals("text:note")||nom.equals("text:conditional-text")||nom.equals("text:date")
	  				||nom.contains("style:graphic-properties")||nom.isEmpty()) {
	  		}else {
	  			Enumeration<String> key = structure.getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(structure.getAttributs().get(k).contains("‽")) {
						System.out.println();
		  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"structurepage\".");
		  	  	  		System.out.println("Le node " +  nom  + " ne peut pas contenir des attributs évalués.");
		  	  	  		System.out.println("Le node " + nom + " peut contenir l'attribut \"evaluer=true\" ce qui permettra l'évaluation des nodes enfants mais aucun attribut de ce node sera évalué.");
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
  	  				||nom.contains("text:illustration")||nom.equals("text:note")||nom.equals("text:conditional-text")
  	  				||nom.contains("style:graphic-properties")||nom.equals("text:date")||nom.isEmpty()) {
  	  		}else {
	  	  		Enumeration<String> key = structure.getNodes().get(i).getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(structure.getNodes().get(i).getAttributs().get(k).contains("‽")) {
						System.out.println();
		  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"structurepage\".");
		  	  	  		System.out.println("Le node " +  nom  + " ne peut pas contenir des attributs évalués.");
		  	  	  		System.out.println("Le node " + nom + " peut contenir l'attribut \"evaluer=true\" ce qui permettra l'évaluation des nodes enfants mais aucun attribut de ce node sera évalué.");
		  	  	  		System.out.println();
		  	  	  		erreur= true;
					}
				}
  	  		}		
  			verifNodeAutoriserDansStructure(structure.getNodes().get(i));
  		}
  	}
	
	
	/**
	 * Evaluation du node seeting
	 * @param attribut
	 * @param nameNode
	 */
	private static void verificationNodeSetting(node setting) {
		if(setting.getAttributs().get("culture") != null) {
			if(!setting.getAttributs().get("culture").equals("FR")) {
				System.out.println();
  	  	  		System.out.println("**-** Avertissement dans le fichier d'analyse le node \"setting\".");
  	  	  		System.out.println("La culture ne peutr pas être différent de \"FR\".");
  	  	  		System.out.println();
			}
		}
		
		if(setting.containElementByName("csv")) {
			node csv = setting.retourneFirstEnfantsByName("csv");
			if(csv.getAttributs().get("encoding") != null) {
				if(!csv.getAttributs().get("encoding").equals("UTF-8") && !csv.getAttributs().get("encoding").equals("US-ASCII") && !csv.getAttributs().get("encoding").equals("ISO-8859-1")
						&& !csv.getAttributs().get("encoding").equals("UTF-16BE") && !csv.getAttributs().get("encoding").equals("UTF-16LE") && !csv.getAttributs().get("encoding").equals("UTF-16")) {
					System.out.println();
	  	  	  		System.out.println("**-** WARNING in analysis file at node \"setting\".");
	  	  	  		System.out.println("The encoding cannot be "+ csv.getAttributs().get("encoding") +"in csv node.");
	  	  	  		System.out.println();
				}
			}
		}
		
		if(setting.containElementByName("zip")) {
			node zip = setting.retourneFirstEnfantsByName("zip");
			if(zip.getAttributs().get("size") != null) {
				Long size = (long) 0;
				try {
					size = Long.valueOf(zip.getAttributs().get("size"));
				}catch (Exception e) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("La taille du fichier doit être une valeur numérique.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(size<1000000) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse \"setting\".");
	  	  	  		System.out.println("La taille de l'archive ZIP est trop faible (supérieure à 1 000 000).");
	  	  	  		System.out.println("La taille doit être supérieure à 1Mo.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(size>100000000) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse \"setting\".");
	  	  	  		System.out.println("La taille de l'archive ZIP est trop grande (inférieur à 100 000 000).");
	  	  	  		System.out.println("La taille doit être inférieure à 100Mo.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
			}
			if(zip.getAttributs().get("name") != null) {
				String name = zip.getAttributs().get("name");
				 Pattern pt = Pattern.compile("[^a-zA-Z0-9]"); // avec les chiffres "[^a-zA-Z0-9]"
			        Matcher match= pt.matcher(name);
			        if(match.find()) {
			        	System.out.println();
		  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
		  	  	  		System.out.println("Le nom de l'archive ne doit pas contenir de carcatères spéciaux.");
		  	  	  		System.out.println();
		  	  	  		erreur=true;
			        }
				}
			}
		
		if(setting.containElementByName("plagiarism")) {
			node plagiarism = setting.retourneFirstEnfantsByName("plagiarism");
			if(plagiarism.getAttributs().get("number_match") != null) {
				int number_match = -1;
				try {
					number_match = Integer.valueOf(plagiarism.getAttributs().get("number_match"));
				}catch (Exception e) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("Le nombre de correspondance \"number_match\" doit être une valeur numérique.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(number_match<0) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("Le nombre de correspondance \"number_match\" doit être unevaleur positive.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				
			}
			if(plagiarism.getAttributs().get("mini_number_modification") != null) {
				int mini_number_modification = -1;
				try {
					mini_number_modification = Integer.valueOf(plagiarism.getAttributs().get("mini_number_modification"));
				}catch (Exception e) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("Le nombre minimum de modification \"mini_number_modification\" doit être une valeur numérique.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(mini_number_modification<-1) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("Le nombre minimum de modification \"mini_number_modification\" doit être une valeur positive.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				
			}
			if(plagiarism.getAttributs().get("nombres_modifications_simultané_maxi") != null) {
				int nombres_modifications_simultané_maxi = -1;
				try {
					nombres_modifications_simultané_maxi = Integer.valueOf(plagiarism.getAttributs().get("nombres_modifications_simultané_maxi"));
				}catch (Exception e) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("Le nombre de modifications simultanées \"nombres_modifications_simultané_maxi\" doit être une valeur numérique.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(nombres_modifications_simultané_maxi<0) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("Le nombre de modifications simultanées \"nombres_modifications_simultané_maxi\" doit être une valeur positive.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				
			}
			
			
		}
		
		if(setting.containElementByName("text:similarity")) {
			node similarity = setting.retourneFirstEnfantsByName("text:similarity");
			if(similarity.getAttributs().get("tolerance_characters")!=null) {
				int tolerance_characters = -1;
				try {
					tolerance_characters = Integer.valueOf(similarity.getAttributs().get("tolerance_characters"));
				}catch (Exception e) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("La tolérance de catactère \"tolerance_characters\" n'est pas un entier.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(tolerance_characters<0) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("La tolérance de catactère \"tolerance_characters\" doit être une valeur positive.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
			}
			if(similarity.getAttributs().get("tolerance_text")!=null) {
				double tolerance_text = 0.0;
				try {
					tolerance_text = Double.valueOf(similarity.getAttributs().get("tolerance_text"));
				}catch (Exception e) {
					System.out.println();
	  	  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("La tolérance sur le texte (similitude) \"tolerance_text\" doit être un numérique compris entre 0.01 et 0.99.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				if(tolerance_text<0 || tolerance_text>1) {
					System.out.println();
		  	  		System.out.println("**-** Erreur dans le fichier d'analyse le node \"setting\".");
	  	  	  		System.out.println("La tolérance sur le texte (similitude) \"tolerance_text\" doit être un numérique compris entre 0.01 et 0.99.");
	  	  	  		System.out.println();
	  	  	  		erreur=true;
				}
				
			}
			
		}
		
	}
	
	
	
	
	private static void verifcationStyleParagraphDefaut(node styleParagraphDefault) {
		if(styleParagraphDefault.getAttributs().get("evaluer")!=null) {
			if(styleParagraphDefault.getAttributs().get("evaluer").equals("true")) {
				System.out.println();
	  			System.out.println("**-** AVERTISSEMENT dans le fichier d'analyse, le node \"style:default-style\".");
	  			System.out.println("Le node \"style:default-style\" ne doit pas contenir l'attribut \"evaluer=true\".");
	  			System.out.println("Les valeurs par défaut dans les styles de paragraphe (les nodes \"style:style\") ont été ajouté.");
	  			System.out.println("Lisez dans la documentation, les caractéristique du node principal \"style:paragraph\".");
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
		System.out.println("\t\t│  ( `.° )                                    │");
		System.out.println("\t\t│  (\")__(\") .. see you soon, analyseWriter.   │");
		System.out.println("\t\t└─────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
	}
	
	
}