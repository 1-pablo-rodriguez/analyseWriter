package MEPTL;

import java.time.LocalDateTime;
import java.util.ArrayList;

import cXML.Run;
import cXML.node;


/**
 * 
 * @author pablo rodriguez
 *
 */
public class ecritureSujet {
	
	/**
	 * Fourni le node du sujet avec les attributs d'evaluation et les attributs de la mise en page du feedback.
	 * @param nod
	 * @param a
	 * @param i
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public static node nodePourEcritureSujet(node nod, Run a, Integer i) throws CloneNotSupportedException {
		LocalDateTime aujourdhui = LocalDateTime.now();
		
		// fichier
		nod.getAttributs().put("evaluer", "true");
		nod.getAttributs().put("progression", "1");
		nod.getAttributs().put("notefrom", "20");
		nod.getAttributs().put("baremeABC", "false");
		nod.getAttributs().put("date", String.valueOf(aujourdhui));
		nod.getAttributs().put("version", commandes.version);
		nod.getAttributs().put("titre", "Le titre de l'exercice");
		nod.getAttributs().put("link_sujet", "https://moodle.univ-artois.fr/cours/");
		nod.getAttributs().put("link_help", "https://moodle.univ-artois.fr/cours/");
		nod.getAttributs().put("historiquePresent", "false");
		nod.getAttributs().put("controleDateCreation", "false");
		nod.getAttributs().put("controle_Initial_Creator", "false");
		nod.getAttributs().put("presenceMetaSujet", "false");
		node b = a.retourneName(nod.retourneFirstEnfantsByName("office:meta"),"meta:user-defined","meta:name","Sujet");
		if(b!=null) { nod.getAttributs().put("metaSujet", b.getContenu().get(0));}else {nod.getAttributs().put("metaSujet", "?");}
		b = nod.retourneFirstEnfantsByName("office:meta").retourneFirstEnfantsByName("meta:creation-date");
		if(b!=null) if(b.getContenu().size()>0) {
			if(b.getContenu().get(0).contains(".")) {
				nod.getAttributs().put("creationDate", b.getContenu().get(0).substring(0, b.getContenu().get(0).lastIndexOf(".")));
			}else {
				nod.getAttributs().put("creationDate", b.getContenu().get(0));
			}
		}
		b = nod.retourneFirstEnfantsByName("office:meta").retourneFirstEnfantsByName("meta:initial-creator");
		if(b!=null) if(b.getContenu().size()>0) { nod.getAttributs().put("Initial_Creator", b.getContenu().get(0));}else {nod.getAttributs().put("Initial_Creator", "");}
		nod.getAttributs().put("auteur", "votre nom et prénom");
				nod.setContenu("Commentaire sur cet exercice.-NewLine-Seconde ligne de commentaire.");
		nod.getAttributs().remove("dossier");
		
		//
		//metadonnées
		node nodmeta = nod.retourneFirstEnfantsByName("office:meta");
		if(nodmeta.getNomElt().equals("office:meta")) {
			nod.getNodes().remove(nodmeta);
			nodmeta.getAttributs().put("evaluer", "false");
			nodmeta.getAttributs().put("addmenu", "false");
			nodmeta.getAttributs().put("poids", "1");
			nodmeta.getAttributs().put("titre", "Les metadonnées");
			ArrayList<node> no = nodmeta.getNodes();
			for(int j = 0 ; j < no.size(); j++) {
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
				no.get(j).getAttributs().put("evalNameNode", "0");
				if(no.get(j).getNomElt().equals("dc:creator")) no.get(j).getAttributs().put("evalNameCreator", "0");
				if(no.get(j).getNomElt().equals("meta:initial-creator")) no.get(j).getAttributs().put("evalNameInitialCreator", "0");
			}
			nod.getNodes().add(nodmeta);
		}

		
		//
		//style de page
		node nodpage = nod.retourneFirstEnfantsByName("style:page");
		if(nodpage.getNomElt().equals("style:page")) {
			nod.getNodes().remove(nodpage);
			nodpage.getAttributs().put("evaluer", "false");
			nodpage.getAttributs().put("addmenu", "false");
			nodpage.getAttributs().put("poids", "1");
			nodpage.getAttributs().put("titre", "Les styles de page");
			
			nodpage = addAttributsAnalyseWriter(nodpage, "style:master-page");
			nod.getNodes().add(nodpage);
		}

		
		//
		//style de paragraphe
		node nodparagraph = nod.retourneFirstEnfantsByName("style:paragraph");
		if(nodparagraph.getNomElt().equals("style:paragraph")) {
			nod.getNodes().remove(nodparagraph);
			nodparagraph.getAttributs().put("evaluer", "false");
			nodparagraph.getAttributs().put("addmenu", "false");
			nodparagraph.getAttributs().put("poids", "1");
			nodparagraph.getAttributs().put("titre", "Les styles de paragraphe");
			
			nodparagraph = addAttributsAnalyseWriter(nodparagraph, "style:style");
			nod.getNodes().add(nodparagraph);
		}
		
		
		//
		//style de texte
		node nodformatage = nod.retourneFirstEnfantsByName("style:formatagedirect");
		if(nodformatage.getNomElt().equals("style:formatagedirect")) {
			nod.getNodes().remove(nodformatage);
			nodformatage.getAttributs().put("evaluer", "false");
			nodformatage.getAttributs().put("addmenu", "false");
			nodformatage.getAttributs().put("poids", "1");
			nodformatage.getAttributs().put("titre", "Les styles de formatage direct");
			
			nodformatage = addAttributsAnalyseWriter(nodformatage, "style:style");
			nod.getNodes().add(nodformatage);
		}

		
		//
		//séquence
		node nodsequence = nod.retourneFirstEnfantsByName("sequences");
		if(nodsequence.getNomElt().equals("sequences")) {
			nod.getNodes().remove(nodsequence);
			nodsequence.getAttributs().put("evaluer", "false");
			nodsequence.getAttributs().put("addmenu", "false");
			nodsequence.getAttributs().put("poids", "1");
			nodsequence.getAttributs().put("titre", "Les variables de séquence");
			
			nodsequence = addAttributsAnalyseWriter(nodsequence, "text:sequence-decl");
			nod.getNodes().add(nodsequence);
		}
		
		//
		//numérotation des chapitres
		node nodnumerotationchapitre = nod.retourneFirstEnfantsByName("numerotationchapitre");
		if(nodnumerotationchapitre.getNomElt().equals("numerotationchapitre")) {
			nod.getNodes().remove(nodnumerotationchapitre);
			nodnumerotationchapitre.getAttributs().put("evaluer", "false");
			nodnumerotationchapitre.getAttributs().put("addmenu", "false");
			nodnumerotationchapitre.getAttributs().put("poids", "1");
			nodnumerotationchapitre.getAttributs().put("titre", "Numérotation des chapitres");
			
			nodnumerotationchapitre = addAttributsAnalyseWriter(nodnumerotationchapitre, "text:outline-level-style");
			nod.getNodes().add(nodnumerotationchapitre);
		}
		
		//
		//frame
		node nodframe = nod.retourneFirstEnfantsByName("frames");
		if(nodframe.getNomElt().equals("frames")) {
			nod.getNodes().remove(nodframe);
			nodframe.getAttributs().put("evaluer", "false");
			nodframe.getAttributs().put("addmenu", "false");
			nodframe.getAttributs().put("poids", "1");
			nodframe.getAttributs().put("titre", "Les frames (cadres et images)");
			
			nodframe = addAttributsAnalyseWriter(nodframe, "draw:frame");
			nod.getNodes().add(nodframe);
		}
		
		
		//
		//sections
		node nodsection = nod.retourneFirstEnfantsByName("sections");
		if(nodsection.getNomElt().equals("sections")) {
			nod.getNodes().remove(nodsection);
			nodsection.getAttributs().put("evaluer", "false");
			nodsection.getAttributs().put("addmenu", "false");
			nodsection.getAttributs().put("poids", "1");
			nodsection.getAttributs().put("titre", "Les sections");
			
			nodsection = addAttributsAnalyseWriter(nodsection, "text:section");
			nod.getNodes().add(nodsection);
		}
		
		//
		//tableaux
		node nodtableaux = nod.retourneFirstEnfantsByName("tableaux");
		if(nodtableaux.getNomElt().equals("tableaux")) {
			nod.getNodes().remove(nodtableaux);
			nodtableaux.getAttributs().put("evaluer", "false");
			nodtableaux.getAttributs().put("addmenu", "false");
			nodtableaux.getAttributs().put("poids", "1");
			nodtableaux.getAttributs().put("titre", "Les tableaux");
			
			nodtableaux = addAttributsAnalyseWriter(nodtableaux, "table:table");
			nod.getNodes().add(nodtableaux);
		}
		
		
		//
		//biblio
		node nodbiblio = nod.retourneFirstEnfantsByName("biblio");
		if(nodbiblio.getNomElt().equals("biblio")) {
			nod.getNodes().remove(nodbiblio);
			nodbiblio.getAttributs().put("evaluer", "false");
			nodbiblio.getAttributs().put("addmenu", "false");
			nodbiblio.getAttributs().put("poids", "1");
			nodbiblio.getAttributs().put("titre", "La bibliographie");
			//nodbiblio.getAttributs().put("styletitre", "H1");
			
			nodbiblio = addAttributsAnalyseWriter(nodbiblio, "text:bibliography");
			nod.getNodes().add(nodbiblio);
		}
		
		
		//
		//table des matières
		node nodtable = nod.retourneFirstEnfantsByName("tablematieres");
		if(nodtable.getNomElt().equals("tablematieres")) {
			nod.getNodes().remove(nodtable);
			nodtable.getAttributs().put("evaluer", "false");
			nodtable.getAttributs().put("addmenu", "false");
			nodtable.getAttributs().put("poids", "1");
			nodtable.getAttributs().put("titre", "Les index tables des matières");
			
			nodtable = addAttributsAnalyseWriter(nodtable, "text:table-of-content");
			nod.getNodes().add(nodtable);
		}		

		
		//
		//table des illustrations
		node nodillustrations = nod.retourneFirstEnfantsByName("tableillustrations");
		if(nodillustrations.getNomElt().equals("tableillustrations")) {
			nod.getNodes().remove(nodillustrations);
			nodillustrations.getAttributs().put("evaluer", "false");
			nodillustrations.getAttributs().put("addmenu", "false");
			nodillustrations.getAttributs().put("poids", "1");
			nodillustrations.getAttributs().put("titre", "Les index illustrations");
			
			nodillustrations = addAttributsAnalyseWriter(nodillustrations, "text:illustration-index");
			nod.getNodes().add(nodillustrations);
		}		

	
		//
		//structure document
		node nodstructurepage = nod.retourneFirstEnfantsByName("structurepage");
		if(nodstructurepage.getNomElt().equals("structurepage")) {
			nod.getNodes().remove(nodstructurepage);
			nodstructurepage.getAttributs().put("evaluer", "false");
			nodstructurepage.getAttributs().put("addmenu", "false");
			nodstructurepage.getAttributs().put("poids", "1");
			nodstructurepage.getAttributs().put("titre", "La structure du document");
			
			nodstructurepage = addAttributsAnalyseWriter(nodstructurepage, "page");
			nod.getNodes().add(nodstructurepage);
						
		}
		
		
		// ajoute le node setting et translation
		nod = addSetting(nod);
			 
		// ajoute le hash du code
		String hash = String.valueOf(Run.HashNode(Run.NodesAyantAttributEvaluerTRUEavecComplement(nod),0));
		nod.getAttributs().put("hash", hash);
		
		return nod;
	}
	
	
	/**
	 * Ajoute les différents attributs aux nodes enfants des nodes principaux.
	 * @param noPourPlacerEvaluer
	 * @param nameNodeRacine
	 * @return
	 */
	private static node addAttributsAnalyseWriter(node noPourPlacerEvaluer, String nameNodeRacine) {
		ArrayList<node> no = noPourPlacerEvaluer.retourneEnfantsByName(nameNodeRacine, new ArrayList<node>());
		for(int j = 0 ; j < no.size(); j++) {
			noPourPlacerEvaluer.getNodes().remove(no.get(j));
			no.get(j).getAttributs().put("saut", "false");
			no.get(j).getAttributs().put("evaluer", "false");
			no.get(j).getAttributs().put("titre", "");
			no.get(j).getAttributs().put("styletitre", "nostyle");
			ArrayList<node> no1 = no.get(j).getNodes();
			for(int k=0 ; k < no1.size(); k++) {
				no1.get(k).getAttributs().put("evaluer", "false");
				if(listeDesNodesAvecEvalNode(no1.get(k).getNomElt())) no1.get(k).getAttributs().put("evalNameNode", "0");
				if(listeDesNodesSautEtTitre(no1.get(k).getNomElt())) {no1.get(k).getAttributs().put("saut", "false"); no1.get(k).getAttributs().put("titre", ""); no1.get(k).getAttributs().put("styletitre", "nostyle");}
				if(listeDesNodesRechercheIndex(no1.get(k).getNomElt())) {no1.get(k).getAttributs().put("recherche_index", "false"); no1.get(k).getAttributs().put("recherche_contenu_exact", "false");}
				if(listeDesNodesAnalyseStyle(no1.get(k).getNomElt())) no1.get(k).getAttributs().put("analyseStyle", "false");
				if(listeDesNodesAllContent(no1.get(k).getNomElt())) no1.get(k).getAttributs().put("allContent", "strict0");
				
				
				ArrayList<node> no2 = no1.get(k).getNodes();
				for(int l=0 ; l < no2.size(); l++) {
					no2.get(l).getAttributs().put("evaluer", "false");
					if(listeDesNodesAvecEvalNode(no2.get(l).getNomElt())) no2.get(l).getAttributs().put("evalNameNode", "0");
					if(listeDesNodesSautEtTitre(no2.get(l).getNomElt())) {no2.get(l).getAttributs().put("saut", "false"); no2.get(l).getAttributs().put("titre", "");no2.get(l).getAttributs().put("styletitre", "nostyle");}
					if(listeDesNodesRechercheIndex(no2.get(l).getNomElt())) {no2.get(l).getAttributs().put("recherche_index", "false"); no2.get(l).getAttributs().put("recherche_contenu_exact", "false");}
					if(listeDesNodesAnalyseStyle(no2.get(l).getNomElt())) no2.get(l).getAttributs().put("analyseStyle", "false");
					if(listeDesNodesAllContent(no2.get(l).getNomElt())) no2.get(l).getAttributs().put("allContent", "strict0");
					
					ArrayList<node> no3 = no2.get(l).getNodes();
					for(int m=0 ; m < no3.size(); m++) {
						no3.get(m).getAttributs().put("evaluer", "false");
						if(listeDesNodesAvecEvalNode(no3.get(m).getNomElt())) no3.get(m).getAttributs().put("evalNameNode", "0");
						if(listeDesNodesSautEtTitre(no3.get(m).getNomElt())) {no3.get(m).getAttributs().put("saut", "false"); no3.get(m).getAttributs().put("titre", "");no3.get(m).getAttributs().put("styletitre", "nostyle");}
						if(listeDesNodesRechercheIndex(no3.get(m).getNomElt())) {no3.get(m).getAttributs().put("recherche_index", "false"); no3.get(m).getAttributs().put("recherche_contenu_exact", "false");}
						if(listeDesNodesAnalyseStyle(no3.get(m).getNomElt())) no3.get(m).getAttributs().put("analyseStyle", "false");
						if(listeDesNodesAllContent(no3.get(m).getNomElt())) no3.get(m).getAttributs().put("allContent", "strict0");
												
						ArrayList<node> no4 = no3.get(m).getNodes();
						for(int n=0 ; n < no4.size(); n++) {
							no4.get(n).getAttributs().put("evaluer", "false");
							if(listeDesNodesAvecEvalNode(no4.get(n).getNomElt())) no4.get(n).getAttributs().put("evalNameNode", "strict0");
							if(listeDesNodesSautEtTitre(no4.get(n).getNomElt())) {no4.get(n).getAttributs().put("saut", "false"); no4.get(n).getAttributs().put("titre", "");no4.get(n).getAttributs().put("styletitre", "nostyle");}
							if(listeDesNodesRechercheIndex(no4.get(n).getNomElt())) {no4.get(n).getAttributs().put("recherche_index", "false"); no4.get(n).getAttributs().put("recherche_contenu_exact", "false");}
							if(listeDesNodesAnalyseStyle(no4.get(n).getNomElt())) no4.get(n).getAttributs().put("analyseStyle", "false");
							if(listeDesNodesAllContent(no4.get(n).getNomElt())) no4.get(n).getAttributs().put("allContent", "strict0");
							
						}
					}
				}
			}
		}
		noPourPlacerEvaluer.getNodes().addAll(no);
		return noPourPlacerEvaluer;
	}
	
	
	/**
	 * Liste des nodes qui peuvent contenir l'attribut evalNameNode
	 * @param nameNode
	 * @return
	 */
	private static boolean listeDesNodesAvecEvalNode(String nameNode) {
		if(nameNode.contains("meta:")) return true;
		if(nameNode.contains("dc:")) return true;
		if(nameNode.contains("text:")) return true;
		return false;
	}
	
	/**
	 * Liste des nodes qui peuvent contenir les attributs saut et titre 
	 * @param nameNode
	 * @return
	 */
	private static boolean listeDesNodesSautEtTitre(String nameNode) {
		if(nameNode.contains("meta:")) return true;
		if(nameNode.contains("dc:")) return true;
		if(nameNode.contains("style:")) return true;
		if(nameNode.contains("text:")) return true;
		return false;
	}
	
	/**
	 * Liste des nodes qui peuvent contenir l'attribut recherche_index
	 * @param nameNode
	 * @return
	 */
	private static boolean listeDesNodesRechercheIndex(String nameNode) {
		if(nameNode.contains("text:")) return true;
		return false;
	}
	
	/**
	 * Liste des nodes qui peuvent contenir l'attribut analyseStyle
	 * @param nameNode
	 * @return
	 */
	private static boolean listeDesNodesAnalyseStyle(String nameNode) {
		if(nameNode.contains("text:")) return true;
		return false;
	}
	
	/**
	 * Liste des nodes qui peuvent contenir l'attribut allContent
	 * @param nameNode
	 * @return
	 */
	private static boolean listeDesNodesAllContent(String nameNode) {
		if(nameNode.contains("text:")) return true;
		if(nameNode.equals("table:table-cell")) return true;
		if(nameNode.equals("table:table-row")) return true;
		return false;
	}
	
	
	
	/**
	 * Ce node permet la configuration personnalisé de l'application.<br>
	 * Ajoute le node setting avec les différentes valeurs par défaut.<br>
	 *
	 * @param sujet Le node du sujet.
	 * @return Le node du sujet avec le node setting ajouté.
	 */
	private static node addSetting(node sujet) {
		//node setting
		node setting = new node();
		setting.setNomElt("setting");
		setting.getAttributs().put("culture","FR");
		
		
		//node csv
		node csv = new node();
		csv.setNomElt("csv");
		csv.getAttributs().put("encoding", "UTF-8");
		csv.getAttributs().put("separator", ";");
		csv.setContenu("choose the encoding from this list : UTF-8 US-ASCII ISO-8859-1 UTF-16BE UTF-16LE UTF-16");
		csv.setClose(true);
		
		//node export du csv
		node export = new node();
		export.setNomElt("import_moodle");
		export.getAttributs().put("email", "adresse");
		export.getAttributs().put("id", "identification");
		export.getAttributs().put("firstname", "prenom");
		export.getAttributs().put("name", "nom");
		export.setClose(true);
		
		//node taille zip
		node zip = new node();
		zip.setNomElt("zip");
		zip.getAttributs().put("size", "48000000");
		zip.getAttributs().put("nameZip", "feedbackMoodle");
		zip.isClose();
		
		//node verif
		node plagiarism  = new node();
		plagiarism.setNomElt("plagiarism");
		plagiarism.getAttributs().put("number_match", "2");
		plagiarism.getAttributs().put("mini_number_modification", "-1");
		plagiarism.getAttributs().put("nombres_modifications_simultané_maxi", "100");
		plagiarism.setClose(true);
		
		//construction node similitude
		node similarity = new node();
		similarity.setNomElt("text:similarity");
		similarity.getAttributs().put("tolerance_characters", "5");
		similarity.getAttributs().put("tolerance_text", "0.79");
		similarity.setClose(true);
		
		//node color
		node color = new node();
		color.setNomElt("color");
		color.getAttributs().put("tolerance_rouge", "30");
		color.getAttributs().put("tolerance_vert", "30");
		color.getAttributs().put("tolerance_bleu", "30");
		color.setClose(true);
		
		//construction du node setting
		csv.getNodes().add(export);
		setting.getNodes().add(csv);
		setting.getNodes().add(zip);
		setting.getNodes().add(plagiarism);
		setting.getNodes().add(similarity);
		setting.getNodes().add(color);
		
		//ajoute la node translation
		setting.getNodes().add(Run.translation());
		
		
		// ajoute le node setting au node sujet
		sujet.getNodes().add(setting);
		
		//fermeture du node
		setting.setClose(true);
		
		return sujet;
	}
	
	
}
