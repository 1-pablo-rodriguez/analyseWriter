package MEPTL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cXML.Run;
import cXML.Run.UserStatus;
import cXML.node;
import net.lingala.zip4j.exception.ZipException;


/**
 * 
 * @author pablo rodriguez
 * 
 *
 */
public class meptl {
	
	static DecimalFormat df = new DecimalFormat("###.##");
	static String patch ="";
	static double progression = 1.0;
	
	/**
	 * Démarrage de l'application.<br>
	 * <br>
	 * @param args : les commandes de l'application.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, CloneNotSupportedException, InterruptedException {
		System.getProperty("file.encoding","UTF-8");
				
		patch = System.getProperty("user.dir");
//		patch = "C:/Users/pabr6/OneDrive/Desktop/Nouveau dossier";
		
		//** les commandes
		new commandes(args,patch);
		
		//** Chargement du node sujet (fichier d'analyse)
		node nodeSujet = new node();
		
		//** Nouveau node qui permet de convertir le fichier contenant la liste des étudiants en node.
		node nodeCSV = null;
		
		//** Lancement des lectures des dossiers ou fichiers
		Run a = new Run(patch,commandes.Profil, commandes.fichierStudentMoodle);
		
		if(!commandes.ecritCode && commandes.analyse) {
			nodeSujet = chargementsujet(a, commandes.nameSujet);
			commandes.culture = nodeSujet.retourneFirstEnfantsByName("setting").getAttributs().get("culture"); //récupère la culture de l'utilisateur
			
			//** La méthode verificationFichier Analyse permet de détecter des erreurs dans le fichier d'analyse
			new verificationFichierAnalyse(nodeSujet);
			
			if(verificationFichierAnalyse.erreur==true) verificationFichierAnalyse.clotureWithErrorInanalyzeFile();
			//a.ecritureNodeEnXML(nodeSujet, "sujet","",false);  // ecriture du node sujet
			
			//** Ecriture d'un nouveau sujet. Uniquement les nodes évalués.
			if(commandes.ecritSujet) {
				a.ecritureNodeEnXML(nodeSujet, "sujet","",false, "Sujet");  // ecriture du node sujet. Uniquement les nodes évalués.
				System.out.println();
				System.out.println("\tUn nouveau fichier \"sujet.xml\" a été créé dans le dossier courant.");
				commandes.clotureApplication();
				System.exit(0);
			}
			
			try {
				
				//** Chargement du contenu du nouveau logo
				if(commandes.newLogo && !commandes.nameSVG.isEmpty()) {
					commandes.contenuFichierSVG= chargementFichierSVG(a,commandes.nameSVG);
				}
				
				//** chargement du node translation qui se trouve dans le node setting
				outils.chargeTraduction(nodeSujet.retourneFirstEnfantsByName("translation"));
				
				// Charge les nouvelles tolérances du nombre de caractère et du texte pour la recherche et la comparaison des textes.
				if(nodeSujet.containElementByName("text:similarity")) {
					node similarity = nodeSujet.retourneFirstEnfantsByName("text:similarity");
					if(similarity.getAttributs().get("tolerance_characters")!=null) commandes.tolerance_characters =  Integer.valueOf(similarity.getAttributs().get("tolerance_characters"));
					if(similarity.getAttributs().get("tolerance_text")!=null) commandes.tolerance_text =  Double.valueOf(similarity.getAttributs().get("tolerance_text"));
				}
				// Charge le nombre de match limite et le nombre minimal de modification dans le node verification
				if(nodeSujet.containElementByName("plagiarism")) {
					node plagiarism = nodeSujet.retourneFirstEnfantsByName("plagiarism");
					if(plagiarism.getAttributs().get("number_match") != null) commandes.number_match = Integer.valueOf(plagiarism.getAttributs().get("number_match"));
					if(plagiarism.getAttributs().get("mini_number_modification") != null) commandes.mini_number_modification = Integer.valueOf(plagiarism.getAttributs().get("mini_number_modification"));
					if(plagiarism.getAttributs().get("nombres_modifications_simultané_maxi") != null) commandes.nombres_modifications_simultané_maxi = Integer.valueOf(plagiarism.getAttributs().get("nombres_modifications_simultané_maxi"));
				}
				// Charge tolerance pour la couleur
				if(nodeSujet.containElementByName("color")) {
					node color = nodeSujet.retourneFirstEnfantsByName("color");
					if(color.getAttributs().get("tolerance_rouge") != null) commandes.tolerance_rouge= Integer.valueOf(color.getAttributs().get("tolerance_rouge"));
					if(color.getAttributs().get("tolerance_vert") != null) commandes.tolerance_vert= Integer.valueOf(color.getAttributs().get("tolerance_vert"));
					if(color.getAttributs().get("tolerance_bleu") != null) commandes.tolerance_bleu= Integer.valueOf(color.getAttributs().get("tolerance_bleu"));
				}
				
				//** Chargement et verification du CVS fourni
				if(commandes.fourniCSV) {
					nodeCSV = chargementFichierCSV(a, commandes.nameCSV);
				}
				

				
			}catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
		
		
		
		//** Vérification des historiques
		node verif  = new node();
		if(commandes.verifHisto || commandes.verifHisto2) {
			int nbFichierWriter = a.getLectDossiers().getEC().getListeContentWriter().size();
			node verification = new node();
			verification.setNomElt("verification");
			verification.getAttributs().put("nombre_fichier", String.valueOf(a.getLectDossiers().getEC().getListeFichierodt().size()));
			for(int i = 0 ; i < nbFichierWriter ; i++) {
				node nod = a.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
				node nodStudent = LectureFichierEtudiantPourVerification(nod,a,i);
				verification.getNodes().add(nodStudent);
			}
			//a.ecritureNodeEnXML(verification, "VerificationHistorique","",false); //écriture du node de l'étudiant
			verif = verificationHistorique(verification, a);  // vérification des correspondances entre les fichiers
			a.ecritureNodeEnXML(verif, "Verif",commandes.pathDestination,commandes.fourniDossierDestination, "Verif"); //écriture du node de vérification
			if(!commandes.analyse) {commandes.clotureApplication();System.exit(0);}
		}
		
		
		//** Nombre de fichier writer à analyser
		int nbFichierWriter = a.getLectDossiers().getEC().getListeContentWriter().size();
		System.getProperty("file.encoding","UTF-8");
		
		//** Ensemble des analyses
		node ensembleanalyse = new node();
		ensembleanalyse.setNomElt("analyses");
		
		for(int i = 0 ; i < nbFichierWriter ; i++) {
			
			//** Ne prends pas en compte le dossier destination créé par la commande -dest
			//** Si pas d'analyse alors le nom doit contenir le caractère $ dans le nom du dossier.
			if(commandes.fourniDossierDestination)if(a.getLectDossiers().getEC().getListeNomDossier().get(i).equals(commandes.pathDestination)) continue;
			
			//** Chargement du format (content) et transformation en node pour l'application
			node nod = a.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
			node nodStudent = LectureFichierEtudiantSousFormeDeNode(nod,a,i);
//			a.ecritureNodeEnXML(nodStudent, a.getLectDossiers().getEC().getListeNomDossier().get(i),"",false,""); //écriture du node de l'étudiant

			//** Ecriture d'une fichier d'analyse.
			//** commande -write 
			if(commandes.ecritCode && ! commandes.verifHisto && !commandes.analyse) {
				node nodSujet = ecritureSujet.nodePourEcritureSujet(nodStudent,a,i);
				//nodSujet = ecritureSujet.addSetting(nodSujet); // ajoute le node setting et translation
				a.ecritureNodeEnXML(nodSujet, a.getLectDossiers().getEC().getListeNomDossier().get(i),"",false,"Sujet");
			}
			
			//** Analyse des fichiers student
			if(commandes.analyse) {
				node init = InitialisationAvantAnalyse(nodeSujet);
				if(!Boolean.valueOf(init.getAttributs().get("erreur"))) {
					node ana = analyse(nodStudent, nodeSujet, i, a);
//					a.ecritureNodeEnXML(ana, "nodana"+ana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"),"",false,""); //écriture du node analyse de l'étudiant
					
					//** Création des feedbacks avec des tailles définies
					if(!commandes.sansFeeback) if(!commandes.zipfeedback) feedback(ana, verif); //classique directement dans le répertoire
					if(!commandes.sansFeeback) if(commandes.zipfeedback) { // Dans une archive pour Moodle
						try {
							long size = 48000000; //valeur par défaut
							String nameZip = "feedbackMoodle"; //nom zip par défaut
							node zip = nodeSujet.retourneFirstEnfantsByName("zip");
							if(zip!=null) {
								if(zip.getAttributs().get("size")!=null)size = Long.valueOf(zip.getAttributs().get("size"));
								if(zip.getAttributs().get("name")!=null)nameZip = zip.getAttributs().get("name");
							}
								a.AddStreamToZip(feedbackForZip(ana, verif), retourneLeNomDuFeedback(a.getLectDossiers().getEC().getListeNomFichierFeedBack().get(i),ana, verif),size,nameZip);
						} catch (ZipException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(commandes.ecritNoteCSV) ensembleanalyse.addNode(ana);
					messageSystem(ana);
				}else {
					
				}
				
			}
		}
		//** Exportation au format CSV
		if(commandes.ecritNoteCSV && !commandes.fourniCSV) {
			if(!commandes.verifHisto2) ecritureCSV(ensembleanalyse);
			if(commandes.verifHisto2) ecritureCSV(ensembleanalyse,verif,a,nodeSujet.retourneFirstEnfantsByName("setting"));
			//a.ecritureNodeEnXML(ensembleanalyse, "ensembleAnalyse"); //écriture du node de l'étudiant
		}
		if(commandes.ecritNoteCSV && commandes.fourniCSV) {
			ecritureCSV(ensembleanalyse,verif,a,nodeCSV, nodeSujet.retourneFirstEnfantsByName("setting"));
			//a.ecritureNodeEnXML(ensembleanalyse, "ensembleAnalyse"); //écriture du node de l'étudiant
		}
		
		
		//** bye bye analyseWriter
		commandes.clotureApplication();
	}
		


	/**
	 * Lecture du fichier Student pour vérification.<br>
	 * <br>
	 * @param nod node
	 * @param a
	 * @param i
	 * @return
	 */
	private static node LectureFichierEtudiantPourVerification(node nod, Run a, Integer i) {
		node fichier = new node();
		fichier.setNomElt("fichier");
		fichier.getAttributs().put("filename", a.getLectDossiers().getEC().getListeFichierodt().get(i));
		fichier.getAttributs().put("dossier", a.getLectDossiers().getEC().getListeNomDossier().get(i));
		
		
		node nodmeta = nod.retourneFirstEnfantsByName("office:meta");
		fichier.getAttributs().put("producteur", nodmeta.retourneFirstEnfantsByName("meta:generator").getContenu());
		fichier.getAttributs().put("dateModification", nodmeta.retourneFirstEnfantsByName("dc:date").getContenu());
		fichier.getAttributs().put("dureeEdition", nodmeta.retourneFirstEnfantsByName("meta:editing-duration").getContenu());
		fichier.getNodes().add(nodmeta);
		
		node nodhistorique = new node();
		int nbrModif = nod.retourneFirstEnfantsByName("text:tracked-changes").getNodes().size();
		nodhistorique.setNomElt("historique");
		nodhistorique.getAttributs().put("nbrModif", String.valueOf(nbrModif));
		nodhistorique.getNodes().addAll(a.retourneNames(a.NodeFirstName(nod, "office:text"), "text:tracked-changes"));
		fichier.getNodes().add(nodhistorique);
		
		return fichier;
	}
	
	/**
	 * Lecture du fichier Writer des étudiants et fournir tous les nodes de la lecture dans un node nommé "fichier".
	 * @param nod Le node Writer de l'étudiant.
	 * @param a Objet de la class Run package cXML
	 * @param i Index de l'étudiant dans la liste EC de l'objet a.
	 * @return le node contenant tous les nodes de la lectures.
	 * @throws IOException Input Output exception file.
	 */
	@SuppressWarnings("unchecked")
 	private static node LectureFichierEtudiantSousFormeDeNode(node nod, Run a, Integer i) throws IOException {
		node nodecontent = nod.retourneFirstEnfantsByName("office:document-content");
		node nodestyle = nod.retourneFirstEnfantsByName("office:document-styles");
		node nodbody = a.NodeFirstName(nodecontent, "office:text");
		node nodmeta = nod.retourneFirstEnfantsByName("office:meta");
		
		// ajoute les créateurs ou éditeur dans les nodes dc:creator ou meta:initial-creator
		// Pour permettre l'analyse avec les attributs evalNameCreator ou evalNameInitialCreator
		if(!commandes.ecritCode) {
			if(nodmeta.retourneEnfantsByNameExist("dc:creator")) {
				if(a.getLectDossiers().getEC().getListeNomDossier().get(i)!=null) {
					nodmeta.retourneFirstEnfantsByName("dc:creator").getAttributs().put("creator", a.getLectDossiers().getEC().getListeNomDossier().get(i));	
				}else {
					nodmeta.retourneFirstEnfantsByName("dc:creator").getAttributs().put("creator", "null");	
				}
			}

			if(nodmeta.retourneEnfantsByNameExist("meta:initial-creator")) {
				if(a.getLectDossiers().getEC().getListeNomDossier().get(i)!=null) {
					nodmeta.retourneFirstEnfantsByName("meta:initial-creator").getAttributs().put("initial-creator", a.getLectDossiers().getEC().getListeNomDossier().get(i));	
				}else {
					nodmeta.retourneFirstEnfantsByName("meta:initial-creator").getAttributs().put("initial-creator", "null");				
				}
			}
		}
		
		
		node nodstyle = new node();
		nodstyle.setNomElt("styles");
		nodstyle.addNode(a.NodeFirstName(nodecontent,"office:automatic-styles"));
		nodstyle.addNode(a.NodeFirstName(nodestyle, "office:automatic-styles"));
		nodstyle.addNode(a.NodeFirstName(nodestyle, "office:styles"));
		nodstyle.addNode(a.NodeFirstName(nodestyle, "office:master-styles"));
		
		// Le node des styles de page
		node nodstylepage = new node();
		nodstylepage.setNomElt("style:page");
		nodstylepage.addNode(a.retourneNames(nodstyle, "style:master-page"));
		for(int j = 0 ; j < nodstylepage.getNodes().size();j++) {
			if(nodstylepage.getNodes().get(j).getAttributs().get("style:page-layout-name")!=null) {
				node nod1 = a.retourneFirstNodeByNameAttributValue(nodstyle, "style:page-layout", "style:name", nodstylepage.getNodes().get(j).getAttributs().get("style:page-layout-name"));
				if(nod1!=null) nodstylepage.getNodes().get(j).addNode(nod1);	
			}
		}
		
		// le node des styles de paragraphe
		node nodstyleparagraphe = new node();
		nodstyleparagraphe.setNomElt("style:paragraph");
		nodstyleparagraphe.addNode(a.NodesAyantAttribut(nodstyle, "style:family","paragraph"));
		nodstyleparagraphe.addNode(a.NodesAyantAttribut(nodstyle, "style:family","text"));
		
		// le node des styles de formatage direct
		node nodstyleformatage = new node();
		nodstyleformatage.setNomElt("style:formatagedirect");
		nodstyleformatage.addNode(a.NodesAyantAttribut(nodstyle, "style:family","text")); //c'est redondant puisque déjà présent dans style:paragraph
		
		//Le node numérotation des chapitres
		node nodnumerochapitre = new node();
		nodnumerochapitre.setNomElt("numerotationchapitre");
		nodnumerochapitre.addNode(a.retourneNames(nodstyle, "text:outline-level-style"));
		
		
		// le node variable de sequence
		node nodsequence = new node();
		nodsequence.setNomElt("sequences");
		nodsequence.addNode(a.retourneNames(nodbody, "text:sequence-decl"));
		
		// le node des frames : renomme les nodes style:style avec le nom de draw:name exemple style:styledraw:name
		// si une draw:text-box contenant un attribut fo:min-height alors ajoute dans  darw:farme l'attribut svg:height
		node nodframe = new node();
		nodframe.setNomElt("frames");
		nodframe.addNode(a.retourneNames(nodbody, "draw:frame"));
		for(int j = 0 ; j < nodframe.getNodes().size(); j++) {
			node nodframestyle = a.retourneFirstNodeByNameAttributValue(nodstyle, "style:style", "style:name", nodframe.getNodes().get(j).getAttributs().get("draw:style-name"));
			String nomObjet = nodframe.getNodes().get(j).getAttributs().get("draw:name");
			if(nomObjet!=null) {
				nomObjet= nomObjet.replace(" ", "_");
				if(nodframestyle!=null) nodframestyle.setNomElt("style:style"+nomObjet);
			}
			nodframe.getNodes().get(j).getNodes().add(nodframestyle);
			if(a.retourneName(nodframe.getNodes().get(j), "draw:text-box", "fo:min-height")!=null) {
				nodframe.getNodes().get(j).getAttributs().put("svg:height", a.retourneName(nodframe.getNodes().get(j), "draw:text-box", "fo:min-height").getAttributs().get("fo:min-height"));
			}
		}
		
		// le node section
		node nodsection = new node();
		nodsection.setNomElt("sections");
		//nodsection.addNode(a.retourneNames(nodbody, "text:section"));
		
		// la bibliographie
		node nodbiblio = new node();
		nodbiblio.setNomElt("biblio");
		nodbiblio.addNode(a.retourneNames(nodbody, "text:bibliography"));
		
		//table des matieres
		node nodtable = new node();
		nodtable.setNomElt("tablematieres");
//		nodtable.addNode(a.retourneNames(nodbody, "text:table-of-content"));
		// renomme le node text:table-of-content-source en text:table-of-content-sourceTitreIndex
		ArrayList<node> A = a.retourneNames(nodbody, "text:table-of-content");
		for(int j = 0 ; j<A.size(); j++) {
			String titreIndex=A.get(j).retourneFirstEnfantsByName("text:index-title-template").getContenu().toLowerCase();
			titreIndex = titreIndex.replace(" ", "_");
			A.get(j).retourneFirstEnfantsByName("text:table-of-content-source").setNomElt("text:table-of-content-source"+titreIndex);
			nodtable.getNodes().add(A.get(j));
		}
		
		
		
		//index illustration
		node nodillustrations = new node();
		nodillustrations.setNomElt("tableillustrations");
		//nodillustrations.addNode(a.retourneNames(nodbody, "text:illustration-index")); //avant
		// renomme le node text:illustration-index-source en text:illustration-index-sourceTitreIndex
		A = a.retourneNames(nodbody, "text:illustration-index");
		for(int j = 0 ; j<A.size(); j++) {
			String titreIndex=A.get(j).retourneFirstEnfantsByName("text:index-title-template").getContenu();
			titreIndex = titreIndex.replace(" ", "_");
			A.get(j).retourneFirstEnfantsByName("text:illustration-index-source").setNomElt("text:illustration-index-source"+titreIndex);
			nodillustrations.getNodes().add(A.get(j));
		}
		
		
		//historique des modifications
		node nodhistorique = new node();
		int nbrModif = nodbody.retourneFirstEnfantsByName("text:tracked-changes").getNodes().size();
		nodhistorique.setNomElt("historique");
		nodhistorique.getAttributs().put("nbrModif", String.valueOf(nbrModif));
		nodhistorique.getNodes().addAll(a.retourneNames(nodbody, "text:tracked-changes"));
		
		
		node nodstructure = new node();
		nodstructure.setNomElt("structure");
		for(int j = 0 ; j < nodbody.getNodes().size();j++) {
			String nomNode = nodbody.getNodes().get(j).getNomElt();
			if(nomNode.equals("draw:frame")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:p")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:h")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:table-of-content")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:list")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:illustration-index")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:bibliography")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("table:table")) nodstructure.addNode(nodbody.getNodes().get(j));
			if(nomNode.equals("text:section")) nodstructure.addNode(nodbody.getNodes().get(j));
		}
		
		
		// Struture et composition du document avec les pages
		// cette boucle utilise le node nodstructure
		ArrayList<node> nodpage = new ArrayList<node>();
		node page = new node();
		page.setNomElt("page");
		page.getAttributs().put("numero", "0");
		page.getAttributs().put("style:master-page-name", "Standard"); // style par défaut des pages
		nodpage.add(page);
		
		int compteurpage = 1;
		for(int j = 0 ; j< nodstructure.getNodes().size();j++) {
			
			page = nodpage.get(nodpage.size()-1); // dernière page enregistrer pour insérer des enfants en bas de la boucle
			
		
			// les sauts automatiques de page dans les paragraphe
			if(nodstructure.getNodes().get(j).retourneEnfantsByNameExist("text:soft-page-break")) {
				ArrayList<node> nods1 = a.retourneNames(nodstructure.getNodes().get(j), "text:soft-page-break");
				if(nods1.size()>1) {
					for(int k = 0 ; k < nods1.size();k++) {
						node p = new node();
						p.setNomElt("page");
						p.getAttributs().put("numero", String.valueOf(compteurpage));
						p.getAttributs().put("type","automatique");
						p.getAttributs().put("index",String.valueOf(j));
						p.getNodes().add(nods1.get(k));
						nodpage.add(p);
						compteurpage++;
					}
					page = nodpage.get(nodpage.size()-1);
					continue;
				}else {
					node p = new node();
					p.setNomElt("page");
					p.getAttributs().put("numero", String.valueOf(compteurpage));
					p.getAttributs().put("type","automatique");
					p.getAttributs().put("index",String.valueOf(j));
					p.getNodes().add(nodstructure.getNodes().get(j));
					nodpage.add(p);
					compteurpage++;
					page = nodpage.get(nodpage.size()-1);
					continue;
				}
			}
			
			// les sauts manuels de page dans les paragraphes <text:p> et <text:list>
			if(nodstructure.getNodes().get(j).getAttributs().get("text:style-name")!=null) {
				node nod2 = a.retourneFirstNodeByNameAttributValue(nodstyle.getNodes(), "style:style", "style:name", nodstructure.getNodes().get(j).getAttributs().get("text:style-name"));
				if(nod2!=null) {
					if(nod2.getAttributs().get("style:master-page-name")!=null) {
						node p = new node();
						p.setNomElt("page");
						p.getAttributs().put("numero", String.valueOf(compteurpage));
						p.getAttributs().put("type","manuel");
						p.getAttributs().put("index",String.valueOf(j));
						p.getAttributs().put("style:master-page-name",nod2.getAttributs().get("style:master-page-name"));			
						p.getAttributs().put("style:name",nod2.getAttributs().get("style:name"));
						node nod3 = a.retourneName(nod2.getNodes(), "style:paragraph-properties","fo:break-before");
						if(nod3!=null) {
							if(nod3.getAttributs().get("style:page-number")!=null) p.getAttributs().put("style:page_number", nod3.getAttributs().get("style:page-number"));
						}else {
							nod3 = nod2.retourneFirstEnfantsByName("style:paragraph-properties");
							if(nod3!=null) if(nod3.getAttributs().get("style:page-number")!=null) {
								p.getAttributs().put("style:page_number", nod3.getAttributs().get("style:page-number"));
							}
						}
						p.getNodes().add(nodstructure.getNodes().get(j));
						nodpage.add(p);
						compteurpage++;
						page = nodpage.get(nodpage.size()-1);
						continue;
					}
					if(a.retourneFirstNodeByNameAttributValue(nod2.getNodes(), "style:paragraph-properties","fo:break-before","page")!=null) {
						node p = new node();
						p.setNomElt("page");
						p.getAttributs().put("numero", String.valueOf(compteurpage));
						p.getAttributs().put("type","automatique");
						p.getAttributs().put("index",String.valueOf(j));
						p.getNodes().add(nodstructure.getNodes().get(j));
						nodpage.add(p);
						compteurpage++;
						page = nodpage.get(nodpage.size()-1);
						continue;
					}
				}
			}
			
			// les sauts manuels hors paragraphe et titre
			if(!nodstructure.getNodes().get(j).getNomElt().equals("text:p") && !nodstructure.getNodes().get(j).getNomElt().equals("text:h") ) {
				ArrayList<node> lesparagraphes = a.retourneNames(nodstructure.getNodes().get(j).getNodes(), "text:p");
				node nod2 = a.retourneName(lesparagraphes, "text:p", "text:style-name");
				if(nod2!=null) {
					node nod3 = a.retourneFirstNodeByNameAttributValue(nodstyle.getNodes(), "style:style", "style:name", nod2.getAttributs().get("text:style-name"));
					if(nod3!=null) {
						if(nod3.getAttributs().get("style:master-page-name")!=null) {
							node p = new node();
							p.setNomElt("page");
							p.getAttributs().put("numero", String.valueOf(compteurpage));
							p.getAttributs().put("type","manuel");
							p.getAttributs().put("index",String.valueOf(j));
							p.getAttributs().put("style:master-page-name",nod3.getAttributs().get("style:master-page-name"));
							p.getAttributs().put("style:name",nod3.getAttributs().get("style:name"));
							p.getNodes().add(nodstructure.getNodes().get(j));
							node nod4 = a.retourneName(nod3.getNodes(), "style:paragraph-properties","fo:break-before");
							if(nod4!=null) {
								if(nod4.getAttributs().get("style:page-number")!=null) p.getAttributs().put("style:page_number", nod4.getAttributs().get("style:page-number"));
							}
							nodpage.add(p);
							compteurpage++;
							page = nodpage.get(nodpage.size()-1);
							continue;
						}
						if(a.retourneFirstNodeByNameAttributValue(nod3.getNodes(), "style:paragraph-properties","fo:break-before","page")!=null) {
							node p = new node();
							p.setNomElt("page");
							p.getAttributs().put("numero", String.valueOf(compteurpage));
							p.getAttributs().put("type","automatique");
							p.getAttributs().put("index",String.valueOf(j));
							p.getNodes().add(nodstructure.getNodes().get(j));
							nodpage.add(p);
							compteurpage++;
							page = nodpage.get(nodpage.size()-1);
							continue;
						}
					}
				}
			}
			
			// ajoute au node de la page les éléments sauf les paragraphes de texte
				page.getNodes().add(nodstructure.getNodes().get(j));

			
			
					
		}
		
		
		
		
		// verification que dans la page numero 0 il y a un paragraphe.
		// s'il n'y a pas de paragraphe alors ce n'est pas une page et elle est supprimée
		// indique le style de la page
		// insère les pages vides
		// déplace les graphique dans les bonnes pages.
		if(nodpage.get(0)!=null) {
			boolean pagezerosupprimer = false;
			ArrayList<node> noddraw = a.retourneNames(nodpage.get(0), "draw:frame"); //il faut replacer peut être les cadre et image ancrer à la page après numérotation absolue
			
			//suppresion  de la page zéro si inutile
			if(!nodpage.get(0).retourneEnfantsByNameExist("text:p") && !nodpage.get(0).retourneEnfantsByNameExist("text:h")) {
				nodpage.remove(0);
				pagezerosupprimer=true;
			}

			
			//indiquer le style de chaque page
			for(int j = 0 ; j < nodpage.size();j++) {
				if(j!=0) {
					if(nodpage.get(j).getAttributs().get("type").equals("automatique")) {
						if(nodpage.get(j-1).getAttributs().get("style:master-page-name")!=null) {
							node nod5 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:master-page", "style:name", nodpage.get(j-1).getAttributs().get("style:master-page-name"));
							if(nod5.getAttributs().get("style:next-style-name")!=null) {
								nodpage.get(j).getAttributs().put("style:master-page-name", nod5.getAttributs().get("style:next-style-name"));
							}else {
								nodpage.get(j).getAttributs().put("style:master-page-name", nodpage.get(j-1).getAttributs().get("style:master-page-name"));
							}
						}else {
							nodpage.get(j).getAttributs().put("style:master-page-name", "Standard"); //défini une page en style page par défaut 
						}
					}
				}
			} 
			
			
			//insère les pages vides
			ArrayList<node> newnodpage = (ArrayList<node>) nodpage.clone();
			int compteurinsertion = 0 ;
			for(int j = 0 ; j < nodpage.size();j++) {
				if(j!=0) {
					String usage1 ="1";
					String usage2 ="2";
					
					if(nodpage.get(j).getAttributs().get("style:master-page-name")!=null) {
						node nod5 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:master-page", "style:name", nodpage.get(j).getAttributs().get("style:master-page-name"));
						if(nod5.getAttributs().get("style:page-layout-name")!=null) {
							node nod6 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:page-layout", "style:name", nod5.getAttributs().get("style:page-layout-name"));
							if(nod6.getAttributs().get("style:page-usage")!=null) {
								usage1 = nod6.getAttributs().get("style:page-usage");
								nodpage.get(j).getAttributs().put("style:page-usage", usage1);
							} 
						}
					}
					
					
					if(nodpage.get(j-1).getAttributs().get("style:master-page-name")!=null) {
						node nod5 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:master-page", "style:name", nodpage.get(j-1).getAttributs().get("style:master-page-name"));
						if(nod5.getAttributs().get("style:page-layout-name")!=null) {
							node nod6 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:page-layout", "style:name", nod5.getAttributs().get("style:page-layout-name"));
							if(nod6.getAttributs().get("style:page-usage")!=null) {
								usage2 = nod6.getAttributs().get("style:page-usage");
							}
						}
					}
					
					if(j-1==0) usage2="right"; //la première page est toujours à droite.
					
					//insertion d'un page vide
					if(usage1.equals(usage2)) {
						node p = new node();
						p.setNomElt("page");
						p.getAttributs().put("vide", "true");
						p.getAttributs().put("style:master-page-name", "page_vide");
						newnodpage.add(j+compteurinsertion,p);
						compteurinsertion++;
					}
					
				}else {
					if(nodpage.get(j).getAttributs().get("style:master-page-name")!=null) {
						node nod5 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:master-page", "style:name", nodpage.get(j).getAttributs().get("style:master-page-name"));
						if(nod5.getAttributs().get("style:page-layout-name")!=null) {
							node nod6 = a.retourneFirstNodeByNameAttributValue(nodstylepage, "style:page-layout", "style:name", nod5.getAttributs().get("style:page-layout-name"));
							if(nod6.getAttributs().get("style:page-usage")!=null) {
								nodpage.get(j).getAttributs().put("style:page-usage", nod6.getAttributs().get("style:page-usage"));
							}
							if(j==0) nodpage.get(j).getAttributs().put("style:page-usage", "right"); //la première page est toujours à droite.
						}
					}
				}
				
			} 
			
			nodpage = (ArrayList<node>) newnodpage.clone();
			
			
			
			// numerotation des pages
			int compteurnumeropage = 1 ;
			int compteurabsoluepage = 1 ; 
			for(int j = 0 ; j < nodpage.size();j++) {
				if(nodpage.get(j).getAttributs().get("style:page_number")!=null){
					if(!nodpage.get(j).getAttributs().get("style:page_number").equals("auto")) {
						compteurnumeropage= Integer.valueOf(nodpage.get(j).getAttributs().get("style:page_number"));
						nodpage.get(j).getAttributs().put("numero", String.valueOf(compteurnumeropage));
						nodpage.get(j).getAttributs().put("numeroabsolue", String.valueOf(compteurabsoluepage));
						compteurnumeropage++;
						compteurabsoluepage++;
						continue;
					}
				}
				nodpage.get(j).getAttributs().put("numero", String.valueOf(compteurnumeropage));
				nodpage.get(j).getAttributs().put("numeroabsolue", String.valueOf(compteurabsoluepage));
				compteurnumeropage++;
				compteurabsoluepage++;
				
			}
			
			// replacer les cadres et images ancrés dans les bonnes pages de la structure
			for(int j = 0 ; j <noddraw.size();j++) {
				if(noddraw.get(j).getAttributs().get("text:anchor-type")!=null) {
					if(noddraw.get(j).getAttributs().get("text:anchor-type").equals("page")) {
						if(noddraw.get(j).getAttributs().get("text:anchor-page-number")!=null) {
							node page1 = a.retourneFirstNodeByNameAttributValue(nodpage, "page", "numeroabsolue", noddraw.get(j).getAttributs().get("text:anchor-page-number"));
							if(page1!=null) {
								if(pagezerosupprimer) {
									page1.getNodes().add(noddraw.get(j));
								}else {
									if(!noddraw.get(j).getAttributs().get("text:anchor-page-number").equals("1")){
										page1.getNodes().add(noddraw.get(j));
										node page2 = a.retourneFirstNodeByNameAttributValue(nodpage, "page", "numeroabsolue", "1");
										page2.getNodes().remove(noddraw.get(j));
									}
								}
							}
						}
					}
				}
			}
			
			
		}
			

		// construction du node de l'étudiant	
		node structurePage = new node();
		structurePage.setNomElt("structurepage");
		structurePage.setNodes(nodpage);
		
		
		node fichier = new node();
		fichier.setNomElt("fichier");
		
		
		if(a.getLectDossiers().getEC().getListeFichierodt().get(i)!=null) {
			fichier.getAttributs().put("filename", a.getLectDossiers().getEC().getListeFichierodt().get(i));
			if(commandes.Profil.equals(UserStatus.STUDENT)) {
				a.getLectDossiers().getEC().getListeNomDossier().add(i, a.getLectDossiers().getEC().getListeFichierodt().get(i).substring(0, a.getLectDossiers().getEC().getListeFichierodt().get(i).lastIndexOf(".")));
			}
			fichier.getAttributs().put("dossier", a.getLectDossiers().getEC().getListeNomDossier().get(i));
		}
		
		
		fichier.getAttributs().put("producteur", nodmeta.retourneFirstEnfantsByName("meta:generator").getContenu());
		fichier.getAttributs().put("dureeEdition", nodmeta.retourneFirstEnfantsByName("meta:editing-duration").getContenu());
		fichier.getAttributs().put("dateModification", nodmeta.retourneFirstEnfantsByName("dc:date").getContenu());
		
		fichier.getNodes().add(nodmeta);
		nodstylepage = a.numeroteNameNode(nodstylepage, "0");		//ajoute les numéros d'index et des attrinuts
		fichier.getNodes().add(nodstylepage);
		fichier.getNodes().add(nodstyleparagraphe);
		fichier.getNodes().add(nodstyleformatage);
		fichier.getNodes().add(nodsequence);
		fichier.getNodes().add(nodnumerochapitre);
		fichier.getNodes().add(nodframe);
		
		fichier.getNodes().add(nodsection);
		nodbiblio = a.numeroteNameNode(nodbiblio, "0");			//ajoute les numéros d'index et des attrinuts
		fichier.getNodes().add(nodbiblio);
		nodtable = a.numeroteNameNode(nodtable, "0");				//ajoute les numéros d'index et des attrinuts
		fichier.getNodes().add(nodtable);
		nodillustrations = a.numeroteNameNode(nodillustrations, "0"); //ajoute les numéros d'index et des attrinuts
		fichier.getNodes().add(nodillustrations);
		
		structurePage = a.numeroteNameNode(structurePage,"0");    //ajoute les numéros d'index et des attributs 
		fichier.getNodes().add(structurePage);
		
		fichier.getNodes().add(nodhistorique);
		return fichier;
	}
	

	
	/**
	 * Chargement du sujet.<br>
	 * Retourne l'ensemble des nodes qui possédent l'attribut evaluer="true".
	 * <br>
	 * @param a
	 * @return le node du sujet qui contient les partie à analyser
	 * @throws IOException 
	 * @throws CloneNotSupportedException 
	 */
	private static node chargementsujet(Run a, String nameSujet) throws  CloneNotSupportedException, IOException {
		String targetString = "";
		//read file into stream, try-with-resources

		try {
			BufferedReader br = new BufferedReader(
			        new InputStreamReader(
			            new FileInputStream(a.getPatch() + "/" + nameSujet), "UTF-8")); 
			
			String line;
			while ((line = br.readLine()) != null) {
				targetString = targetString + line;
			}
			br.close();
		}catch (Exception e) {
			System.out.println();
			System.out.println("** The file \"" + nameSujet + "\" is not in the current directory.");
			System.out.println("** The current directory of the application is " + patch);
			System.out.println();
		}
		
		
		node LeNodeSujet = new node();
		if(!targetString.isEmpty()) {
			// ! Important nettoyage du fichier avant lecture avec cXML
		    targetString = targetString.replace("\r", "");
		    targetString = targetString.replace("\n", "");
		    targetString = targetString.replace("\t", "");
		   // supprime les espaces multiples au-delà de deux espaces après un guillemets
			Pattern p = Pattern.compile(" {2,}");
			targetString = p.matcher(targetString).replaceAll(" ");
		    
		    // supprime les espaces entre " et >
			p = Pattern.compile("\" {1,}>");
			targetString = p.matcher(targetString).replaceAll("\">");
			
			LeNodeSujet = a.XMLContent(targetString);
		}
		
		LeNodeSujet = a.NodesAyantAttributEvaluerTRUEavecComplement(LeNodeSujet);
		

		
		return LeNodeSujet;
	}

	
	
	/**
	 * Vérification du node sujet (premier node <b>fichier</b> et des paramètres.<br>
	 * <br>
	 * @param nodSujet
	 * @return
	 */
	private static node InitialisationAvantAnalyse(node nodSujet) {
		node initSujet = new node();
		initSujet.setNomElt("init");

		boolean erreur=false;
		boolean erreurNomPremierNodeFichier=false;
		boolean erreurManqueAttributEvaluerPremierNodeFichier=false;
		boolean erreurValeurAttributEvaluerPremierNodeFichier=false;
		boolean erreurPasNodesEnfantsAuPremierNodeFichier=false;
		boolean erreurPasAttributMetaSujetAuPremierNodeFichier=false;
		boolean erreurValeurVideAttributMetaSujetAuPremierNodeFichier=false;
		boolean erreurValeurAttributProgressionNonConvertibleEnDouble=false;
		boolean erreurValeurAttributNoteFromNonConvertibleEnDouble=false;
		
		if(!nodSujet.getNomElt().equals("fichier")) {
			erreur=true;
			erreurNomPremierNodeFichier = true;
		}
		if(nodSujet.getAttributs().get("evaluer")==null) {
			erreur=true;
			erreurManqueAttributEvaluerPremierNodeFichier = true;
		}
		if(!nodSujet.getAttributs().get("evaluer").equals("true")) {
			erreur=true;
			erreurValeurAttributEvaluerPremierNodeFichier=true;
		}
		if(nodSujet.getNodes().size()==0) {
			erreur=true;
			erreurPasNodesEnfantsAuPremierNodeFichier=true;
		}
		if(nodSujet.getAttributs().get("metaSujet")==null) {
			erreur=true;
			erreurPasAttributMetaSujetAuPremierNodeFichier=true;
		}
		if(nodSujet.getAttributs().get("metaSujet")!=null) {
			if(nodSujet.getAttributs().get("metaSujet").isEmpty()) {
				erreur=true;
				erreurValeurVideAttributMetaSujetAuPremierNodeFichier=true;
			}
		}
		if(nodSujet.getAttributs().get("progression")!=null) {
			String p = nodSujet.getAttributs().get("progression");
			try {
				Double.valueOf(p);
			} catch (Exception e) {
				erreur=true;
				erreurValeurAttributProgressionNonConvertibleEnDouble=true;
			}
		}
		if(nodSujet.getAttributs().get("notefrom")!=null) {
			String p = nodSujet.getAttributs().get("notefrom");
			try {
				Double.valueOf(p);
			} catch (Exception e) {
				erreur=true;
				erreurValeurAttributNoteFromNonConvertibleEnDouble=true;
			}
		}		
		
		initSujet.getAttributs().put("erreur",String.valueOf(erreur));
		initSujet.getAttributs().put("erreurNomPremierNodeFichier",String.valueOf(erreurNomPremierNodeFichier));
		initSujet.getAttributs().put("erreurManqueAttributEvaluerPremierNodeFichier",String.valueOf(erreurManqueAttributEvaluerPremierNodeFichier));
		initSujet.getAttributs().put("erreurValeurAttributEvaluerPremierNodeFichier",String.valueOf(erreurValeurAttributEvaluerPremierNodeFichier));
		initSujet.getAttributs().put("erreurPasNodesEnfantsAuPremierNodeFichier",String.valueOf(erreurPasNodesEnfantsAuPremierNodeFichier));
		initSujet.getAttributs().put("erreurPasAttributMetaSujetAuPremierNodeFichier",String.valueOf(erreurPasAttributMetaSujetAuPremierNodeFichier));
		initSujet.getAttributs().put("erreurValeurVideAttributMetaSujetAuPremierNodeFichier",String.valueOf(erreurValeurVideAttributMetaSujetAuPremierNodeFichier));
		initSujet.getAttributs().put("erreurValeurAttributProgressionNonConvertibleEnDouble",String.valueOf(erreurValeurAttributProgressionNonConvertibleEnDouble));
		initSujet.getAttributs().put("erreurValeurAttributNoteFromNonConvertibleEnDouble",String.valueOf(erreurValeurAttributNoteFromNonConvertibleEnDouble));
		
		return initSujet;
	}

	/**
	 * Début de l'analyse par comparaison du node étudiant avec le node sujet.
	 * @param nodStudent, le node étudiant.
	 * @param nodSujet, le node sujet.
	 * @param indexStudent, index de l'étudiant
	 * @param a, objet Run de la class cXML
	 * @return le node analyse contenant toute l'analyse.
	 */
	private static node analyse(node nodStudent, node nodSujet, Integer indexStudent, Run a) {
		
		// initialisation des nodes d'analyse
		node erreurs = new node();
		node nodmeta = new node();
		node nodpage = new node();
		node nodparagraph = new node();
		node nodsequence = new node();
		node nodnumerochapitre = new node();
		node nodframes = new node();
		node nodsections = new node();
		node nodbiblio = new node();
		node nodtablematieres = new node();
		node nodtableillustrations = new node();
		node nodstructurepage = new node();
	
		// ouverture
		node nodouverture = new node();
		nodouverture.setNomElt("ouverture");
		nodouverture.setAttributs(nodSujet.getAttributs());
		nodouverture.getAttributs().put("dossier",a.getLectDossiers().getEC().getListeNomDossier().get(indexStudent));
		nodouverture.getAttributs().put("filename", a.getLectDossiers().getEC().getListeFichierodt().get(indexStudent));
		nodouverture.getAttributs().put("producteur", nodStudent.getAttributs().get("producteur"));
		nodouverture.getAttributs().put("dureeEdition", nodStudent.getAttributs().get("dureeEdition"));
		nodouverture.getAttributs().put("dateModification", nodStudent.getAttributs().get("dateModification"));
		nodouverture.getAttributs().put("patch", a.getPatch());
		if(nodSujet.getAttributs().get("historiquePresent")!=null) nodouverture.getAttributs().put("historiquePresent", nodSujet.getAttributs().get("historiquePresent"));
		if(nodSujet.getAttributs().get("controleDateCreation")!=null) nodouverture.getAttributs().put("controleDateCreation", nodSujet.getAttributs().get("controleDateCreation"));
		if(nodSujet.getAttributs().get("presenceMetaSujet")!=null) nodouverture.getAttributs().put("presenceMetaSujet", nodSujet.getAttributs().get("presenceMetaSujet"));
		if(nodSujet.getAttributs().get("baremeABC")!=null) nodouverture.getAttributs().put("baremeABC", nodSujet.getAttributs().get("baremeABC"));
		nodouverture.setClose(true);
			
		//Body et note (par défaut valeur nulle)
		node nodbodyetnotation = new node();
		nodbodyetnotation.setNomElt("bodyetnotation");
		nodbodyetnotation.getAttributs().put("note", "0");
		nodbodyetnotation.getAttributs().put("commentaire", "");
		nodbodyetnotation.getAttributs().put("proportioncorrect", "0");
		nodbodyetnotation.getAttributs().put("baremeABC", "false");
		if(nodSujet.getAttributs().get("baremeABC")!=null) nodbodyetnotation.getAttributs().put("baremeABC", nodSujet.getAttributs().get("baremeABC"));
		nodbodyetnotation.setClose(true);
		
		//Le menu
		node nodmenu = new node();
		nodmenu.setNomElt("menu");
		nodmenu = a.retourneNodeMenu(nodSujet, nodmenu, 0, 0);
		nodmenu.setClose(true);
		
		// verification de la métadonnées Sujet
		erreurs = retourneNodeErreur(nodStudent, nodSujet, a);

		// verification si au moins une erreur alors l'analyse est terminée
		if(Boolean.valueOf(erreurs.getAttributs().get("oneError"))){
			return clotureNodeAnalyse(nodouverture, nodbodyetnotation, nodmenu, erreurs, nodmeta, nodpage, nodparagraph, nodsequence, nodnumerochapitre, nodframes, nodsections, nodbiblio, nodtablematieres, nodtableillustrations, nodstructurepage, nodSujet.getContenu());
		}
		
		// analyse Meta
		if(nodSujet.retourneFirstEnfantsByName("office:meta").getNomElt().equals("office:meta")) {
			nodmeta = analyseMeta(nodStudent.retourneFirstEnfantsByName("office:meta"), nodSujet.retourneFirstEnfantsByName("office:meta"), a, nodmenu);
		}

		// analyse les pages (nécessaire d'avoir aussi les styles de paragarphes)
		if(nodSujet.retourneFirstEnfantsByName("style:page").getNomElt().equals("style:page")) {
			if(nodSujet.containElementByName("style:paragraph")) {
				nodpage = analysePage(nodStudent.retourneFirstEnfantsByName("style:page"),  nodSujet.retourneFirstEnfantsByName("style:page"), a, nodmenu,nodSujet.retourneFirstEnfantsByName("style:paragraph"),nodStudent.retourneFirstEnfantsByName("style:paragraph"));
			}else {
				nodpage = analysePage(nodStudent.retourneFirstEnfantsByName("style:page"),  nodSujet.retourneFirstEnfantsByName("style:page"), a, nodmenu,null,null);
			}
		}
		
		
		// analyse les paragraphes
		if(nodSujet.retourneFirstEnfantsByName("style:paragraph").getNomElt().equals("style:paragraph")) {
			nodparagraph = analyseParagraph(nodStudent.retourneFirstEnfantsByName("style:paragraph"),  nodSujet.retourneFirstEnfantsByName("style:paragraph"), a, nodmenu);
		}
		
		// analyse les variables de séquence
		if(nodSujet.retourneFirstEnfantsByName("sequences").getNomElt().equals("sequences")) {
			nodsequence = analyseLesSequences(nodStudent.retourneFirstEnfantsByName("sequences"),  nodSujet.retourneFirstEnfantsByName("sequences"), a, nodmenu);
		}
		
		// analyse de la numérotation des chapitres
		if(nodSujet.retourneFirstEnfantsByName("numerotationchapitre").getNomElt().equals("numerotationchapitre")) {
			nodnumerochapitre = analyseLaNumerotationChapitre(nodStudent.retourneFirstEnfantsByName("numerotationchapitre"),  nodSujet.retourneFirstEnfantsByName("numerotationchapitre"), a, nodmenu);
		}
		
		// analyse les frames
		if(nodSujet.retourneFirstEnfantsByName("frames").getNomElt().equals("frames")) {
			nodframes = analyseLesFrames(nodStudent.retourneFirstEnfantsByName("frames"),  nodSujet.retourneFirstEnfantsByName("frames"), a, nodmenu);
		}
		
		// analyse des sections
//		if(nodSujet.retourneFirstEnfantsByName("sections").getNomElt().equals("sections")) {
//			nodframes = analyseLesFrames(nodStudent.retourneFirstEnfantsByName("sections"),  nodSujet.retourneFirstEnfantsByName("sections"), a, nodmenu);
//		}
		
		// analyse la bibliographie de LibreOffice
		if(nodSujet.retourneFirstEnfantsByName("biblio").getNomElt().equals("biblio")) {
			nodbiblio = analyseLaBiblio(nodStudent.retourneFirstEnfantsByName("biblio"),  nodSujet.retourneFirstEnfantsByName("biblio"), a, nodmenu);
		}
		
		// analyse des tables des matières
		if(nodSujet.retourneFirstEnfantsByName("tablematieres").getNomElt().equals("tablematieres")) {
			nodtablematieres = analyseLesTablesMatieres(nodStudent.retourneFirstEnfantsByName("tablematieres"),  nodSujet.retourneFirstEnfantsByName("tablematieres"), a, nodmenu);
		}
		
		// analyse des tables illustrations
		if(nodSujet.retourneFirstEnfantsByName("tableillustrations").getNomElt().equals("tableillustrations")) {
			nodtableillustrations = analyseLesTablesIllustrations(nodStudent.retourneFirstEnfantsByName("tableillustrations"),  nodSujet.retourneFirstEnfantsByName("tableillustrations"), a, nodmenu);
		}	
		
		// analyse la structure du document
		if(nodSujet.retourneFirstEnfantsByName("structurepage").getNomElt().equals("structurepage")) {
			node nodSujetParagraphs = null;
			if(nodSujet.retourneFirstEnfantsByName("style:paragraph").getNomElt().equals("style:paragraph")) nodSujetParagraphs = nodSujet.retourneFirstEnfantsByName("style:paragraph");
			node nodStudentParagraphs = nodStudent.retourneFirstEnfantsByName("style:paragraph");
			nodstructurepage = analyseStructurePage(nodStudent.retourneFirstEnfantsByName("structurepage"),  nodSujet.retourneFirstEnfantsByName("structurepage"), a, nodmenu,nodSujetParagraphs, nodStudentParagraphs );
		}
		
	
		// retourne le node analyse assemblé et calcul de la note avec le barème
		return clotureNodeAnalyse(nodouverture, nodbodyetnotation, nodmenu, erreurs, nodmeta, nodpage, nodparagraph, nodsequence, nodnumerochapitre, nodframes, nodsections, nodbiblio, nodtablematieres, nodtableillustrations, nodstructurepage,nodSujet.getContenu());
	
			
		}
		
		
	/**
	 * <br>Les erreurs dans le fichier étudiant : erreur de métadonnées Sujet, date de création pour identifier le fichier à analyser.
	 * <br>Il y a une erreur si pas d'historique de modification dans le fichier de l'étudiant.
	 * <br>Il y a une erreur si le premier auteur ne correspond pas.
	 * <br>Les erreurs sont dans les attributs du node <b>Erreurs</b>.
	 * <br><b> oneError</b> si VRAI, il y a au moins une erreur.
	 * <br><b> manqueMetaSujet</b> si VRAI, il n'y a pas de méta données <b>Sujet</b>.
	 * <br><b> manqueValeurMetaSujet</b> si VRAI, la valeur de la méta données <b>Sujet</b> n'est pas la bonne.
	 * <br><b> manqueCreationDate</b> si VRAI, il n'y a pas de méta données <b>creationDate</b>.
	 * <br><b> manqueValeurCreationDate</b> si VRAI, la date de la méta données <b>creationDate</b> n'est pas la bonne.
	 * <br><b> manqueHistorique</b> si VRAI, il n'y a pas d'historique des modifications.<br>
	 * <br><b> manqueInitialCreator</b> si VRAI, il n'y a pas de premier auteur ou qu'il ne correspond pas.<br>
	 * @param nodStudent : node de l'étudiants.
	 * @param nodSujet : node du sujet.
	 * @param a : objet Run de cXML.
	 * @return retourn le node erreurs.
	 */
	private static node retourneNodeErreur(node nodStudent, node nodSujet, Run a) {
		node erreurs = new node();
		erreurs.setNomElt("erreurs");
		boolean manqueMetaSujet = false;
		boolean manqueValeurMetaSujet = false;
		boolean manqueCreationDate = false;
		boolean manqueValeurCreationDate = false;
		boolean manqueHistorique = false;
		boolean manqueInitialCreator = false;
		
		node b = null;
		
		if(nodSujet.getAttributs().get("presenceMetaSujet")!=null) {
			if(nodSujet.getAttributs().get("presenceMetaSujet").equals("true")) {
				b = a.retourneFirstNodeByNameAttributValue(nodStudent, "meta:user-defined", "meta:name", "Sujet");
				if(b==null) {
					manqueMetaSujet=true;
				}else {
					if(!nodSujet.getAttributs().get("metaSujet").equals(b.getContenu())) {
						manqueValeurMetaSujet=true;
					}
				}
			}else {
				
			}
		}else {
			b = a.retourneFirstNodeByNameAttributValue(nodStudent, "meta:user-defined", "meta:name", "Sujet");
			if(b==null) {
				manqueMetaSujet=true;
			}else {
				if(!nodSujet.getAttributs().get("metaSujet").equals(b.getContenu())) {
					manqueValeurMetaSujet=true;
				}
			}
		}

		
		b =  nodStudent.retourneFirstEnfantsByName("meta:creation-date");

		if(!b.getNomElt().equals("meta:creation-date")) {
			manqueValeurCreationDate=true;
		}
		
		if(nodSujet.getAttributs().get("creationDate")!=null) {
			if(!(b.getContenu().contains(nodSujet.getAttributs().get("creationDate")))) { //nodSujet.getAttributs().get("creationDate").contains(b.getContenu())
				manqueValeurCreationDate = true;
			}
		}
		
		if(nodSujet.getAttributs().get("controleDateCreation")!=null) if(nodSujet.getAttributs().get("controleDateCreation").equals("false")) {
			manqueValeurCreationDate = false;
		}
		
		if(nodSujet.getAttributs().get("controle_Initial_Creator")!=null) {
			if(nodSujet.getAttributs().get("controle_Initial_Creator").equalsIgnoreCase("true")) {
				b =  nodStudent.retourneFirstEnfantsByName("meta:initial-creator");
				if(b.getNomElt().equals("meta:initial-creator")) {
					if(nodSujet.getAttributs().get("Initial_Creator")!=null) {
						 if( !b.getContenu().equals(nodSujet.getAttributs().get("Initial_Creator"))) {
							 manqueInitialCreator = true;
						 }
					}else {
						System.out.println("ERROR. There is no Initial_Creator.");
					}
				}else {
					 manqueInitialCreator = true;
				}
			}
		}
		
		
		b =  nodStudent.retourneFirstEnfantsByName("historique");
		if(b.getNomElt().equals("historique")) {
			if(Integer.valueOf(b.getAttributs().get("nbrModif"))<1) {
				manqueHistorique =true;
			}
		}
		
		
		erreurs.getAttributs().put("manqueMetaSujet", String.valueOf(manqueMetaSujet));
		erreurs.getAttributs().put("manqueValeurMetaSujet", String.valueOf(manqueValeurMetaSujet));
		erreurs.getAttributs().put("manqueCreationDate", String.valueOf(manqueCreationDate));
		erreurs.getAttributs().put("manqueValeurCreationDate", String.valueOf(manqueValeurCreationDate));
		erreurs.getAttributs().put("manqueInitialCreator", String.valueOf(manqueInitialCreator));
		
		if(nodSujet.getAttributs().get("historiquePresent")!=null) {
			if(nodSujet.getAttributs().get("historiquePresent").equalsIgnoreCase("true")) {
				if(manqueHistorique) {
					erreurs.getAttributs().put("manqueHistorique", "true");
				}else {
					erreurs.getAttributs().put("manqueHistorique", "false");
				}
			}else {
				erreurs.getAttributs().put("manqueHistorique", "false");
				manqueHistorique = false;
			}
		}else {
			erreurs.getAttributs().put("manqueHistorique", "false");
			manqueHistorique = false;
		}

		
		
		
		if( manqueMetaSujet == false && manqueValeurMetaSujet == false && manqueCreationDate == false && manqueValeurCreationDate == false 
				&& manqueHistorique == false && manqueInitialCreator == false) {
			erreurs.getAttributs().put("oneError", "false");
		}else {
			erreurs.getAttributs().put("oneError", "true");
		}
		
		
		
		erreurs.setClose(true);
		
		return erreurs;
	}
	
	
	/**
	 * Assemblage du node annalyse à partir des différents nodes de l'analyse.<br>
	 * <br>
	 * @param nodouverture
	 * @param nodbodyetnotation
	 * @param nodmenu
	 * @param erreurs
	 * @param nodmeta
	 * @param nodpage
	 * @param nodparagraph
	 * @param nodsequence
	 * @param nodnumerochapitre
	 * @param nodframes
	 * @param nodsections
	 * @param nodbiblio
	 * @param nodtablematieres
	 * @param nodtableillustrations
	 * @param nodstructurepage
	 * @return
	 */
	private static node clotureNodeAnalyse(node nodouverture, node nodbodyetnotation, node nodmenu, node erreurs, node nodmeta, node nodpage,
			node nodparagraph, node nodsequence, node nodnumerochapitre, node nodframes, node nodsections, node nodbiblio, node nodtablematieres,
			node nodtableillustrations, node nodstructurepage, String texteCommentaire) {
		
		node nodanalyse = new node();
				
		//recalcul les points pour les placer dans le node nodbodyetnotation
		double notefrom = 20;     // valeur par défaut
		double progression = 1;   // valeur par défaut
		boolean baremeABC = false;
		if(nodouverture!=null) if(nodouverture.isClose()) {
			if(nodouverture.getAttributs().get("notefrom")!=null) {
				try {
					notefrom = Math.abs(Double.valueOf(nodouverture.getAttributs().get("notefrom")));
				}catch (Exception e) {
					System.out.println("The \"noteFrom\" attribute of the analyze file cannot be converted to a \"double\".");
					System.out.println("Value of notefrom = 20.");
				}
			}
			if(nodouverture.getAttributs().get("progression")!=null) {
				try {
					progression = Math.abs(Double.valueOf(nodouverture.getAttributs().get("progression")));
				}catch (Exception e) {
					System.out.println("The \"progression\" attribute of the analyze file cannot be converted to \"double\".");
					System.out.println("Value of progression = 1.");
				}
			}
			if(nodouverture.getAttributs().get("baremeABC")!=null) {
				try {
					baremeABC = Boolean.valueOf(nodouverture.getAttributs().get("baremeABC"));
				}catch (Exception e) {
					System.out.println("The \"baremeABC\" attribute of the analyze file cannot be converted to \"boolean\".");
					System.out.println("Value of baremeABC = false.");
				}
			}
		}
			

		
		
		double pointmeta = 0; double pointmetatotal = 0 ; double poidsmeta = 0;
		if(nodmeta!=null) if(nodmeta.isClose()) {
			if(nodmeta.getAttributs().get("pointgagner")!=null) pointmeta = Double.valueOf(nodmeta.getAttributs().get("pointgagner"));
			if(nodmeta.getAttributs().get("pointtotal")!=null) pointmetatotal = Double.valueOf(nodmeta.getAttributs().get("pointtotal"));
			if(nodmeta.getAttributs().get("poids")!=null) try{poidsmeta = Math.abs(Double.valueOf(nodmeta.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointpage = 0; double pointpagetotal = 0 ; double poidspage = 0;
		if(nodpage!=null) if(nodpage.isClose()) {
			if(nodpage.getAttributs().get("pointgagner")!=null) pointpage = Double.valueOf(nodpage.getAttributs().get("pointgagner"));
			if(nodpage.getAttributs().get("pointtotal")!=null) pointpagetotal = Double.valueOf(nodpage.getAttributs().get("pointtotal"));
			if(nodpage.getAttributs().get("poids")!=null) try{poidspage = Math.abs(Double.valueOf(nodpage.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointparagraph = 0; double pointparagraphtotal = 0 ; double poidsparagraph = 0;
		if(nodparagraph!=null) if(nodparagraph.isClose()) {
			if(nodparagraph.getAttributs().get("pointgagner")!=null) pointparagraph = Double.valueOf(nodparagraph.getAttributs().get("pointgagner"));
			if(nodparagraph.getAttributs().get("pointtotal")!=null) pointparagraphtotal = Double.valueOf(nodparagraph.getAttributs().get("pointtotal"));
			if(nodparagraph.getAttributs().get("poids")!=null) try{Math.abs(poidsparagraph = Double.valueOf(nodparagraph.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointsequence = 0; double pointsequencetotal = 0 ; double poidssequence = 0;
		if(nodsequence!=null) if(nodsequence.isClose()) {
			if(nodsequence.getAttributs().get("pointgagner")!=null) pointsequence = Double.valueOf(nodsequence.getAttributs().get("pointgagner"));
			if(nodsequence.getAttributs().get("pointtotal")!=null) pointsequencetotal = Double.valueOf(nodsequence.getAttributs().get("pointtotal"));
			if(nodsequence.getAttributs().get("poids")!=null) try{poidssequence = Math.abs(Double.valueOf(nodsequence.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointnumerotation = 0; double pointnumerotationtotal = 0 ; double poidsnumerotation = 0;
		if(nodnumerochapitre!=null) if(nodnumerochapitre.isClose()) {
			if(nodnumerochapitre.getAttributs().get("pointgagner")!=null) pointnumerotation = Double.valueOf(nodnumerochapitre.getAttributs().get("pointgagner"));
			if(nodnumerochapitre.getAttributs().get("pointtotal")!=null) pointnumerotationtotal = Double.valueOf(nodnumerochapitre.getAttributs().get("pointtotal"));
			if(nodnumerochapitre.getAttributs().get("poids")!=null) try{poidsnumerotation = Math.abs(Double.valueOf(nodnumerochapitre.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointframe = 0; double pointframetotal = 0 ; double poidsframe = 0;
		if(nodframes!=null) if(nodframes.isClose()) {
			if(nodframes.getAttributs().get("pointgagner")!=null) pointframe = Double.valueOf(nodframes.getAttributs().get("pointgagner"));
			if(nodframes.getAttributs().get("pointtotal")!=null) pointframetotal = Double.valueOf(nodframes.getAttributs().get("pointtotal"));
			if(nodframes.getAttributs().get("poids")!=null) try{poidsframe = Math.abs(Double.valueOf(nodframes.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointbiblio = 0; double pointbibliototal = 0 ; double poidsbiblio = 0;
		if(nodbiblio!=null) if(nodbiblio.isClose()) {
			if(nodbiblio.getAttributs().get("pointgagner")!=null) pointbiblio = Double.valueOf(nodbiblio.getAttributs().get("pointgagner"));
			if(nodbiblio.getAttributs().get("pointtotal")!=null) pointbibliototal = Double.valueOf(nodbiblio.getAttributs().get("pointtotal"));
			if(nodbiblio.getAttributs().get("poids")!=null) try{poidsbiblio = Math.abs(Double.valueOf(nodbiblio.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointtablematieres = 0; double pointtablematierestotal = 0 ; double poidstablematieres = 0;
		if(nodtablematieres!=null) if(nodtablematieres.isClose()) {
			if(nodtablematieres.getAttributs().get("pointgagner")!=null) pointtablematieres = Double.valueOf(nodtablematieres.getAttributs().get("pointgagner"));
			if(nodtablematieres.getAttributs().get("pointtotal")!=null) pointtablematierestotal = Double.valueOf(nodtablematieres.getAttributs().get("pointtotal"));
			if(nodtablematieres.getAttributs().get("poids")!=null) try{poidstablematieres = Math.abs(Double.valueOf(nodtablematieres.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointtableillustration = 0; double pointtableillustrationtotal = 0 ; double poidstableillustration = 0;
		if(nodtableillustrations!=null) if(nodtableillustrations.isClose()) {
			if(nodtableillustrations.getAttributs().get("pointgagner")!=null) pointtableillustration = Double.valueOf(nodtableillustrations.getAttributs().get("pointgagner"));
			if(nodtableillustrations.getAttributs().get("pointtotal")!=null) pointtableillustrationtotal = Double.valueOf(nodtableillustrations.getAttributs().get("pointtotal"));
			if(nodtableillustrations.getAttributs().get("poids")!=null) try{poidstableillustration = Math.abs(Double.valueOf(nodtableillustrations.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointstructure = 0; double pointstructuretotal = 0 ; double poidsstructure = 0;
		if(nodstructurepage!=null) if(nodstructurepage.isClose()) {
			if(nodstructurepage.getAttributs().get("pointgagner")!=null) pointstructure = Double.valueOf(nodstructurepage.getAttributs().get("pointgagner"));
			if(nodstructurepage.getAttributs().get("pointtotal")!=null) pointstructuretotal = Double.valueOf(nodstructurepage.getAttributs().get("pointtotal"));
			if(nodstructurepage.getAttributs().get("poids")!=null) try{poidsstructure = Math.abs(Double.valueOf(nodstructurepage.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointsections = 0; double pointsectionstotal = 0 ; double poidssections = 0;
		if(nodsections!=null) if(nodsections.isClose()) {
			if(nodsections.getAttributs().get("pointgagner")!=null) pointsections = Double.valueOf(nodsections.getAttributs().get("pointgagner"));
			if(nodsections.getAttributs().get("pointtotal")!=null) pointsectionstotal = Double.valueOf(nodsections.getAttributs().get("pointtotal"));
			if(nodsections.getAttributs().get("poids")!=null) try{poidssections = Math.abs(Double.valueOf(nodsections.getAttributs().get("poids")));}catch (Exception e) {			};
		}		
		double proportionCorrect = 0 ;
		double poidsTotal = 0;
		double pointsTotal = 0;
		double pointgagner = 0;
		double note = 0 ;
		

		
		proportionCorrect = (poidsmeta*pointmeta + poidspage*pointpage + poidsparagraph*pointparagraph + poidssequence*pointsequence + poidsnumerotation*pointnumerotation + poidsframe*pointframe + poidsbiblio*pointbiblio + poidstablematieres*pointtablematieres + poidstableillustration*pointtableillustration + poidsstructure*pointstructure + poidssections*pointsections)
				/ (poidsmeta*pointmetatotal + poidspage*pointpagetotal + poidsparagraph*pointparagraphtotal + poidssequence*pointsequencetotal + poidsnumerotation*pointnumerotationtotal + poidsframe*pointframetotal + poidsbiblio*pointbibliototal + poidstablematieres*pointtablematierestotal + poidstableillustration*pointtableillustrationtotal + poidsstructure*pointstructuretotal + poidssections*pointsectionstotal);
		
		poidsTotal = poidsmeta + poidspage + poidsparagraph + poidssequence + poidsnumerotation + poidsframe + poidsbiblio + poidstablematieres + poidstableillustration + poidsstructure + poidssections;
		pointsTotal = pointmetatotal + pointpagetotal + pointparagraphtotal + pointsequencetotal + pointnumerotationtotal + pointframetotal + pointbibliototal + pointtablematierestotal + pointtableillustrationtotal + pointstructuretotal + pointsectionstotal;
		pointgagner = pointmeta + pointpage + pointparagraph + pointsequence + pointnumerotation + pointframe + pointbiblio + pointtablematieres + pointtableillustration + pointstructure + pointsections;
		note = Math.pow(proportionCorrect, progression)*notefrom;
		
		DecimalFormat df = new DecimalFormat("###.##");

		
		nodbodyetnotation.getAttributs().put("proportioncorrect", df.format(proportionCorrect*100) + "%");
		nodbodyetnotation.getAttributs().put("note", df.format(note));
		nodbodyetnotation.getAttributs().put("pointstotal", String.valueOf(pointsTotal));
		nodbodyetnotation.getAttributs().put("poidstotal", String.valueOf(poidsTotal));
		nodbodyetnotation.getAttributs().put("pointgagner", String.valueOf(pointgagner));
		
		if(Boolean.valueOf(erreurs.getAttributs().get("oneError"))) {
			nodbodyetnotation.getAttributs().put("proportioncorrect", "0%");
			nodbodyetnotation.getAttributs().put("note", "0.00");
			proportionCorrect = 0.00;
		}
		
		// si bareme ABC (5 intervalles A, B, C, D, E de 20%)
		if(baremeABC) {
			double intervalle1 = Math.pow(0.2, 1/progression) ;
			double intervalle2 = Math.pow(0.4, 1/progression) ;
			double intervalle3 = Math.pow(0.6, 1/progression) ;
			double intervalle4 = Math.pow(0.8, 1/progression) ;
			
			nodbodyetnotation.getAttributs().put("BorneE", String.valueOf(intervalle1));
			nodbodyetnotation.getAttributs().put("BorneD", String.valueOf(intervalle2));
			nodbodyetnotation.getAttributs().put("BorneC", String.valueOf(intervalle3));
			nodbodyetnotation.getAttributs().put("BorneB", String.valueOf(intervalle4));
			nodbodyetnotation.getAttributs().put("BorneA", "1.00");
			
			if(proportionCorrect<intervalle1) nodbodyetnotation.getAttributs().put("noteABC", "E");
			if(proportionCorrect>=intervalle1 && proportionCorrect<intervalle2) nodbodyetnotation.getAttributs().put("noteABC", "D");
			if(proportionCorrect>=intervalle2 && proportionCorrect<intervalle3) nodbodyetnotation.getAttributs().put("noteABC", "C");
			if(proportionCorrect>=intervalle3 && proportionCorrect<intervalle4) nodbodyetnotation.getAttributs().put("noteABC", "B");
			if(proportionCorrect>=intervalle4) nodbodyetnotation.getAttributs().put("noteABC", "A");
		}
		
		nodanalyse.ajouteEnfant(nodouverture);
		nodanalyse.ajouteEnfant(nodbodyetnotation);
		nodanalyse.ajouteEnfant(nodmenu);
		nodanalyse.ajouteEnfant(erreurs);
		
		nodanalyse.ajouteEnfant(nodmeta);
		nodanalyse.ajouteEnfant(nodpage);
		nodanalyse.ajouteEnfant(nodparagraph);
		nodanalyse.ajouteEnfant(nodsequence);
		nodanalyse.ajouteEnfant(nodnumerochapitre);
		nodanalyse.ajouteEnfant(nodframes);
		nodanalyse.ajouteEnfant(nodsections);
		nodanalyse.ajouteEnfant(nodbiblio);
		nodanalyse.ajouteEnfant(nodtablematieres);
		nodanalyse.ajouteEnfant(nodtableillustrations);
		nodanalyse.ajouteEnfant(nodstructurepage);
		
		node nodfermeturebodyHTML = new node();
		nodfermeturebodyHTML.setNomElt("fermeture");
		nodfermeturebodyHTML.setClose(true);
		
		nodanalyse.getNodes().add(nodfermeturebodyHTML);
		
		nodanalyse.setNomElt("analyse");
		nodanalyse.setContenu(texteCommentaire);
		nodanalyse.setClose(true);
		
		return nodanalyse;
	}

	
	/**
	 * Analyse du node <b>office:meta</b>.
	 * @param nodStudentMeta
	 * @param nodSujetMeta
	 * @param a
	 * @param nodmenu
	 * @return
	 */
 	private static node analyseMeta(node nodStudentMeta, node nodSujetMeta, Run a, node nodmenu) {
		node nodmeta = new node();
		nodmeta.setNomElt("meta");
		nodmeta.setAttributs(nodSujetMeta.getAttributs());
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "office:meta")!=null) {
			nodmeta.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "office:meta").getAttributs().get("id"));	
		}
		
		//Applatir le node sujetMeta
		ArrayList<node> sujet = a.Applatir(nodSujetMeta.getNodes(), new ArrayList<node>());
		
		//initialise les points
		outils.initiliseLesPoints();
		
	
		for(int i = 0 ; i < sujet.size(); i++) {
			
			String namenode = sujet.get(i).getNomElt();
			
			// parcours les attributs du node "meta:user-defined"
			if(namenode.equals("meta:user-defined")) {
				Enumeration<String> key = sujet.get(i).getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					
					if(sujet.get(i).getAttributs().get(k).contains("‽")){
						
						// PARTIE AVANT - CETTE PARTIE DONNER SATISFACTION
//						boolean trouvelebonnode =false;
//				
//						ArrayList<node> nod = a.retourneNames(nodStudentMeta, namenode);
//						for(int j = 0 ; j < nod.size(); j ++) {
//							String Tst = outils.Compare(nod.get(j).getAttributs().get(k), sujet.get(i).getAttributs().get(k));
//							if(Tst.contains("Correct")) {
//								trouvelebonnode=true;
//								node item = new node("ana:meta", Tst, outils.withoutPoint(sujet.get(i).getAttributs().get(k)), nod.get(j).getAttributs().get(k), sujet.get(i).getAttributs().get(k), 1, outils.getPointEnJeu(),namenode);
//								nodmeta.getNodes().add(item);
//								break;
//							}else {
//								outils.decrementPointEnJeuDuTotal();
//							}
//						}
//						
//						if(!trouvelebonnode) {
//							String Tst = outils.Compare("null", sujet.get(i).getAttributs().get(k));
//							node item = new node("ana:meta", Tst, outils.withoutPoint(sujet.get(i).getAttributs().get(k)), "null", sujet.get(i).getAttributs().get(k), 2, outils.getPointEnJeu(), namenode);
//							nodmeta.getNodes().add(item);
//						}
						
						
						String valueOfAttribut = sujet.get(i).getAttributs().get(k);
						
						node nod2 = a.retourneFirstNodeByNameAttributContainsValueNetTexte(nodStudentMeta, namenode,k,outils.withoutCodeAndPointPourRechercheContenuExact(valueOfAttribut));
						nodmeta = analyseLesAttributEtContenuDuNode(nod2, sujet.get(i), nodmeta, "ana:meta", namenode);
					}
				}
				
				
			}else {

				// PARTIE AVANT - CETTE PARTIE DONNER SATISFACTION
//				Enumeration<String> key = sujet.get(i).getAttributs().keys();
//				while(key.hasMoreElements()) {
//					String k = key.nextElement();
//					if( sujet.get(i).getAttributs().get(k).contains("‽")){
//						String valueAttributStudent = nodStudentMeta.retourneFirstEnfantsByName(namenode).getAttributs().get(k);
//						String valueAttributSujet = sujet.get(i).getAttributs().get(k);
//						node item =retourneNoteAvecResultatsAnalyse("ana:meta", k, valueAttributStudent, valueAttributSujet,namenode);						
//						nodmeta.getNodes().add(item);
//					}
//				}
				
				ArrayList<node> NStudent = a.retourneNames(nodStudentMeta, namenode);
			
				if(NStudent!=null) {
					if(!NStudent.isEmpty()) {
						nodmeta = analyseLesAttributEtContenuDuNode(NStudent.get(0), sujet.get(i), nodmeta, "ana:meta", namenode);
					}else {
						nodmeta = analyseLesAttributEtContenuDuNode(null, sujet.get(i), nodmeta, "ana:meta", namenode);
					}
				}else {
					nodmeta = analyseLesAttributEtContenuDuNode(null, sujet.get(i), nodmeta, "ana:meta", namenode);
				}
				
				
				
				
			}
			
			// PARTIE AVANT - CETTE PARTIE DONNER SATISFACTION
			// parcours le contenu autre que "meta:user-defined"
//			if(sujet.get(i).getContenu().contains("‽") && sujet.get(i).getAttributs().get("meta:user-defined")==null) {
//				String contentStudent = nodStudentMeta.retourneFirstEnfantsByName(namenode).getContenu();
//				String contentSujet = sujet.get(i).getContenu();
//
//				node item =retourneNoteAvecResultatsAnalyse("ana:meta", "texte", contentStudent, contentSujet,namenode);
//
//				nodmeta.getNodes().add(item);
//
//			}

			
			
			
		}
		nodmeta.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodmeta.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodmeta.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodmeta.setClose(true);
		return nodmeta;
	}
	
	/**
	 * Analyse du node <b>style:page</b>.
	 * @param nodStudentPage
	 * @param nodSujetPage
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analysePage(node nodStudentPage, node nodSujetPage, Run a, node nodmenu, node nodSujetParagraphes, node nodStudentParagraphes) {
		node nodpages = new node();
		nodpages.setNomElt("pages");
		nodpages.setAttributs(nodSujetPage.getAttributs());
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:page")!=null) {
			nodpages.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:page").getAttributs().get("id"));
		}

		//initialise les points
		outils.initiliseLesPoints();
		
		for(int i = 0 ; i < nodSujetPage.getNodes().size(); i++) { //niveau 1
			if(nodSujetPage.getNodes().get(i).getNomElt().equals("style:master-page")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDeLaPage = outils.withoutCodeAndPoint(nodSujetPage.getNodes().get(i).getAttributs().get("style:name"));
				node page = new node();
				page.setNomElt("page");
				page.getAttributs().put("name", nomDeLaPage);
				if(nodSujetPage.getNodes().get(i).getAttributs().get("titre")!=null) page.getAttributs().put("titre", nodSujetPage.getNodes().get(i).getAttributs().get("titre"));
				
				node pageStudent = a.retourneFirstNodeByNameAttributValue(nodStudentPage, "style:master-page", "style:name", nomDeLaPage);
				node pageSujet = nodSujetPage.getNodes().get(i);
				
				// analyse les attributs du node style:master-page
				page = analyseLesAttributEtContenuDuNode(pageStudent, pageSujet, page, "ana:page",pageSujet.getNomElt());
	
				// les enfants du premier niveau du node
				for(int j = 0 ; j < pageSujet.getNodes().size();j++ ) { //niveau 2
						
				node nodSujet = pageSujet.getNodes().get(j);
				String nameNode = nodSujet.getNomElt();
				page = addNodeSautTitre(nodSujet, page); // ajoute des saut de page s'il y a des sauts avec des titres

				//recherche le node correspondant de l'étudiant
				node nodStudent = null;	
				if(pageStudent!=null) if(pageStudent.retourneFirstEnfantsByName(nameNode).getNomElt().equals(nameNode)) {
					nodStudent = pageStudent.retourneFirstEnfantsByName(nameNode);
				}
				
				//analyse le nom du node
//				if(nodSujet.getAttributs().get("evalNameNode")!=null) {
//					String point = nodSujet.getAttributs().get("evalNameNode");
//					page =analyseNameNode(page,nodStudent,nodSujet.getNomElt(),point, nodSujet.getNomElt());
//				}
				
				
				// analyse attribut et contenu des enfants du premier niveau
				page = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, page, "ana:page",pageSujet.getNodes().get(j).getNomElt());
				
					
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) { //niveau 3
						// C'est à ce niveau que se trouve les nodes text:p dans les entêtes et les pieds de page
						
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						page = addNodeSautTitre(nod2Sujet, page); // ajoute des sauts s'il y a des sauts avec des titres

						
						//recherche le node correspondant de l'étudiant
						node nod2Student = null;	
						if(nodStudent!=null) if(nodStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
							//nod2Student = nodStudent.retourneFirstEnfantsByName(nameNode2);
							nod2Student = rechercheLeNodeEnCascade(nameNode2,nod2Sujet,null,null,nodStudent,a);
						}
						
					
						//analyse style du paragraphe
						if(nod2Sujet.getAttributs().get("analyseStyle")!=null) {
							if(nod2Sujet.getAttributs().get("analyseStyle").equals("true") && nod2Sujet.getNomElt().equals("text:p")) {
								page = analyseStyle(page, nod2Sujet, nod2Student, nodSujetParagraphes, nodStudentParagraphes);
							}
						}
						
						
						// analyse attribut et contenu des enfants du second niveau
						page = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, page, "ana:page",nodSujet.getNodes().get(k).getNomElt() );
						
						
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) { //niveau 4
							//C'est à ce niveau qi'il peut y avoir des nodes text:tab ou text:span
							
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							//page = addNodeSautTitre(nod3Sujet, page);
							
							//recherche du node correspondant de l'étudiant
							node nod3Student = null;	
							if(nod2Student!=null) if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
								nod3Student = rechercheLeNodeEnCascade(nameNode3,nod3Sujet,null,nodStudent,nod2Student,a);
							}
							
						
							// analyse attribut et contenu des enfants du troisième niveau
							page = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, page, "ana:page", nod2Sujet.getNodes().get(l).getNomElt());
						
						
							for(int m = 0 ; m < nod3Sujet.getNodes().size();m++) { //niveau 5
								node nod4Sujet = nod3Sujet.getNodes().get(m);
								String nameNode4 = nod4Sujet.getNomElt();
								
								//recherche du node correspondant de l'étudiant
								node nod4Student = null;	
								if(nod3Student!=null) if(nod3Student.retourneFirstEnfantsByName(nameNode4).getNomElt().equals(nameNode4)) {
									nod4Student = rechercheLeNodeEnCascade(nameNode4,nod4Sujet,nodStudent,nod2Student,nod3Student,a);
								}
								
								// analyse attribut et contenu des enfants du troisième niveau
								page = analyseLesAttributEtContenuDuNode(nod4Student, nod4Sujet, page, "ana:page", nod3Sujet.getNodes().get(m).getNomElt());
							
							}
						
						
						
						}
	
					}
						
				}
					
				page.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				page.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodpages.getNodes().add(page);


			}
		}
		
		nodpages.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodpages.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodpages.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodpages.setClose(true);		
		return nodpages;
	}
	
	/**
	 * Analyse du node <b>style:paragraph</b>.
	 * @param nodStudentParagraph
	 * @param nodSujetParagraph
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseParagraph(node nodStudentParagraph, node nodSujetParagraph, Run a, node nodmenu) {
		node nodparagraphs = new node();
		nodparagraphs.setNomElt("paragraphs");
		nodparagraphs.setAttributs(nodSujetParagraph.getAttributs());
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:paragraph")!=null) {
			nodparagraphs.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:paragraph").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		
		for(int i = 0 ; i < nodSujetParagraph.getNodes().size(); i++) {
			if(nodSujetParagraph.getNodes().get(i).getNomElt().equals("style:style")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				node paragraphSujet = nodSujetParagraph.getNodes().get(i);
				String nomDuParagraph = outils.withoutCodeAndPoint(paragraphSujet.getAttributs().get("style:name"));
				node paragraph = new node();
				paragraph.setNomElt("paragraph");
				paragraph.getAttributs().put("name", nomDuParagraph);
				if(paragraphSujet.getAttributs().get("titre")!=null) paragraph.getAttributs().put("titre", nodSujetParagraph.getNodes().get(i).getAttributs().get("titre"));
							
				// trouve le node de l'étudiant
				node paragraphStudent = a.retourneFirstNodeByNameAttributValue(nodStudentParagraph, "style:style", "style:name", nomDuParagraph);
				
				// ajoute les valeurs par héritage
				if(paragraphStudent!=null) paragraphStudent = ajouteValeurLesValeursDuStyleParagraphParent(nodStudentParagraph, paragraphStudent);
				
				// ajoute les valeurs par défauts
				if(paragraphStudent!=null)	paragraphStudent = ajouteValeurParDefautAuStyleParagraph(nodStudentParagraph, paragraphStudent);
			
				// analyse les attributs du node
				paragraph = analyseLesAttributEtContenuDuNode(paragraphStudent, paragraphSujet, paragraph, "ana:paragraph",paragraphSujet.getNomElt());
	
				// les enfants du premier niveau du node
				for(int j = 0 ; j < paragraphSujet.getNodes().size();j++ ) {
						
				node nodSujet = paragraphSujet.getNodes().get(j);
				String nameNode = nodSujet.getNomElt();
				node nodStudent = null;	
				if(paragraphStudent!=null) {
					if(paragraphStudent.retourneFirstEnfantsByName(nameNode).getNomElt().equals(nameNode)) {
						nodStudent = paragraphStudent.retourneFirstEnfantsByName(nameNode);
					}
				}
				
				//insère un saut si titre pas vide et saut=true
				paragraph = addNodeSautTitre(nodSujet, paragraph);
				
				// analyse attribut et contenu des enfants du premier niveau
				paragraph = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, paragraph, "ana:paragraph",nodSujet.getNomElt());
				
					
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) {
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						node nod2Student = null;	
						if(nodStudent!=null) if(nodStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
							nod2Student = paragraphStudent.retourneFirstEnfantsByName(nameNode2);
						}
						
						//insère un saut si titre pas vide et saut=true
						paragraph = addNodeSautTitre(nod2Sujet, paragraph);
						
						// analyse attribut et contenu des enfants du second niveau
						paragraph = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, paragraph, "ana:paragraph",nod2Sujet.getNomElt() );
						
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) {
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							node nod3Student = null;	
							if(nod2Student!=null) if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
								nod3Student = paragraphStudent.retourneFirstEnfantsByName(nameNode3);
							}
							
							//insère un saut si titre pas vide et saut=true
							paragraph = addNodeSautTitre(nod3Sujet, paragraph);
							
							// analyse attribut et contenu des enfants du troisième niveau
							paragraph = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, paragraph, "ana:paragraph", nod3Sujet.getNomElt());
						}
	
					}
						
				}
					
				paragraph.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				paragraph.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodparagraphs.getNodes().add(paragraph);


			}
		}
		
		nodparagraphs.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodparagraphs.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodparagraphs.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodparagraphs.setClose(true);		
		return nodparagraphs;
	}
	
	/**
	 * Analyse du node <b>sequences</b>.
	 * @param nodStudentSequence
	 * @param nodSujetSequence
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseLesSequences(node nodStudentSequence, node nodSujetSequence, Run a, node nodmenu) {
		node nodseq = new node();
		nodseq.setNomElt("sequences");
		nodseq.setAttributs(nodSujetSequence.getAttributs());
		nodseq.setContenu(nodSujetSequence.getContenu()); //ajoute le commantire
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "sequences")!=null) {
			nodseq.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "sequences").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		
		for(int i = 0 ; i < nodSujetSequence.getNodes().size(); i++) {
			if(nodSujetSequence.getNodes().get(i).getNomElt().equals("text:sequence-decl")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomSequence = outils.withoutCodeAndPoint(nodSujetSequence.getNodes().get(i).getAttributs().get("text:name"));
				node seq = new node();
				seq.setNomElt("sequence");
				seq.getAttributs().put("name", nomSequence);
				if(nodSujetSequence.getNodes().get(i).getAttributs().get("titre")!=null) seq.getAttributs().put("titre", nodSujetSequence.getNodes().get(i).getAttributs().get("titre"));
				
				node seqStudent = a.retourneFirstNodeByNameAttributValue(nodStudentSequence, "text:sequence-decl", "text:name", nomSequence);
				node seqSujet = nodSujetSequence.getNodes().get(i);
				
				//insère un saut si titre pas vide et saut=true
				seq = addNodeSautTitre(seqSujet, seq);
				
				// analyse les attributs du node
				seq = analyseLesAttributEtContenuDuNode(seqStudent, seqSujet, seq, "ana:seq",seqSujet.getNomElt());
			
				seq.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				seq.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodseq.getNodes().add(seq);
			}
		


		}
		nodseq.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodseq.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodseq.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodseq.setClose(true);		
		return nodseq;
	}
	
	/**
	 * Analyse du node <b>numerotationchapitre</b>.
	 * @param nodStudentNumerotation
	 * @param nodSujetNumerotation
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseLaNumerotationChapitre(node nodStudentNumerotation, node nodSujetNumerotation, Run a, node nodmenu) {
		node nodnumerotations = new node();
		nodnumerotations.setNomElt("numerotationchapitre");
		nodnumerotations.setAttributs(nodSujetNumerotation.getAttributs());//ajoute tous les attributs du sujet
		nodnumerotations.setContenu(nodSujetNumerotation.getContenu()); //ajoute le commantaire du sujet
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "numerotationchapitre")!=null) {
			nodnumerotations.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "numerotationchapitre").getAttributs().get("id"));
		}
						
		//initialise les points
		outils.initiliseLesPoints();
		for(int i = 0 ; i < nodSujetNumerotation.getNodes().size(); i++) {
			if(nodSujetNumerotation.getNodes().get(i).getNomElt().equals("text:outline-level-style")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String levelnumrotation = outils.withoutCodeAndPoint(nodSujetNumerotation.getNodes().get(i).getAttributs().get("text:level"));
				node numerotation = new node();
				numerotation.setNomElt("numerotation");
				numerotation.getAttributs().put("level", levelnumrotation);
				if(nodSujetNumerotation.getNodes().get(i).getAttributs().get("titre")!=null) numerotation.getAttributs().put("titre", nodSujetNumerotation.getNodes().get(i).getAttributs().get("titre"));
				
				node numerotationStudent = a.retourneFirstNodeByNameAttributValue(nodStudentNumerotation, "text:outline-level-style", "text:level", levelnumrotation);
				node numerotationSujet = nodSujetNumerotation.getNodes().get(i);
				
				// analyse les attributs du node
				numerotation = analyseLesAttributEtContenuDuNode(numerotationStudent, numerotationSujet, numerotation, "ana:numerotation",numerotationSujet.getNomElt());
	
				// les enfants du premier niveau du node
				for(int j = 0 ; j < numerotationSujet.getNodes().size();j++ ) {
						
				node nodSujet = numerotationSujet.getNodes().get(j);
				String nameNode = nodSujet.getNomElt();
				node nodStudent = null;	
				if(numerotationStudent!=null) if(numerotationStudent.retourneFirstEnfantsByName(nameNode).getNomElt().equals(nameNode)) {
					nodStudent = numerotationStudent.retourneFirstEnfantsByName(nameNode);
				}
				
				//insère un saut si titre pas vide et saut=true
				numerotation=addNodeSautTitre(nodSujet, numerotation);
				
				// analyse attribut et contenu des enfants du premier niveau
				numerotation = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, numerotation, "ana:numerotation",numerotationSujet.getNodes().get(j).getNomElt());
				
					
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) {
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						node nod2Student = null;	
						if(nodStudent!=null) if(nodStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
							nod2Student = numerotationStudent.retourneFirstEnfantsByName(nameNode2);
						}
						
						//insère un saut si titre pas vide et saut=true
						numerotation=addNodeSautTitre(nod2Sujet, numerotation);
						
						// analyse attribut et contenu des enfants du second niveau
						numerotation = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, numerotation, "ana:numerotation",nod2Sujet.getNomElt() );
						
						
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) {
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							node nod3Student = null;	
							if(nod2Student!=null) if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
								nod3Student = numerotationStudent.retourneFirstEnfantsByName(nameNode3);
							}
							
							//insère un saut si titre pas vide et saut=true
							numerotation=addNodeSautTitre(nod3Sujet, numerotation);
							
							// analyse attribut et contenu des enfants du troisième niveau
							numerotation = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, numerotation, "ana:paragraph", nod3Sujet.getNomElt());
						}
	
					}
						
				}
					
				numerotation.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				numerotation.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodnumerotations.getNodes().add(numerotation);


			}
		}
		
		nodnumerotations.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodnumerotations.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodnumerotations.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodnumerotations.setClose(true);		
		return nodnumerotations;
		
	}
	
	/**
	 * Analyse du node <b>frames</b>.
	 * @param nodStudentFrames
	 * @param nodSujetframes
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseLesFrames(node nodStudentFrames, node nodSujetFrames, Run a, node nodmenu) {
		node nodframes = new node();
		nodframes.setNomElt("frames");
		nodframes.setAttributs(nodSujetFrames.getAttributs()); //ajoute tous les attributs du sujet
		nodframes.setContenu(nodSujetFrames.getContenu()); //ajoute le commentaire du sujet
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "frames")!=null) {
			nodframes.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "frames").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		for(int i = 0 ; i < nodSujetFrames.getNodes().size(); i++) { //niveau 1
			if(nodSujetFrames.getNodes().get(i).getNomElt().equals("draw:frame")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDuFrame = outils.withoutCodeAndPoint(nodSujetFrames.getNodes().get(i).getAttributs().get("draw:name"));
				node frame = new node();
				frame.setNomElt("frame");
				frame.getAttributs().put("nameframe", nomDuFrame);
				if(nodSujetFrames.getNodes().get(i).getAttributs().get("titre")!=null) frame.getAttributs().put("titre", nodSujetFrames.getNodes().get(i).getAttributs().get("titre"));
				
				node frameStudent = a.retourneFirstNodeByNameAttributValue(nodStudentFrames, "draw:frame", "draw:name", nomDuFrame);
				node frameSujet = nodSujetFrames.getNodes().get(i);
				
				// analyse les attributs du node
				frame = analyseLesAttributEtContenuDuNode(frameStudent, frameSujet, frame, "ana:frame",frameSujet.getNomElt());
	
				// les enfants du premier niveau du node
				for(int j = 0 ; j < frameSujet.getNodes().size();j++ ) { //niveau 2
						
				node nodSujet = frameSujet.getNodes().get(j);
				String nameNode = nodSujet.getNomElt();
				node nodStudent = null;	
				if(frameStudent!=null) if(frameStudent.retourneFirstEnfantsByName(nameNode).getNomElt().equals(nameNode)) {
					nodStudent = frameStudent.retourneFirstEnfantsByName(nameNode);
				}
				if(frameStudent!=null) if(nameNode.equals("text:p")) {
					
					if(nodSujet.getAttributs().get("index")!=null) {
						nodStudent = a.retourneFirstNodeByNameAttributValue(frameStudent, nameNode, "text:p", frameSujet.getNodes().get(j).getAttributs().get("index"));
					}
					
					if(nodStudent==null)if(nodSujet.retourneLesContenusEnfants("").isEmpty()) { //si il n'y a pas de contenu, passe par l'index
						nodStudent = a.retourneFirstNodeByNameAttributValue(frameStudent, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
					}else {
						nodStudent = a.retourneFirstNodeByFindContent2(frameStudent.getNodes(), nodSujet.retourneLesContenusEnfants(""), commandes.tolerance_characters,commandes.tolerance_text);
					}
					
				}
				
				//insère un saut si titre pas vide et saut=true
				frame=addNodeSautTitre(nodSujet, frame);
				
				
				// analyse attribut et contenu des enfants du premier niveau
				frame = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, frame, "ana:frame",nodSujet.getNomElt());
				
					
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) { //niveau 3
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						node nod2Student = null;	
						if(nodStudent!=null) if(nodStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
							nod2Student = nodStudent.retourneFirstEnfantsByName(nameNode2);
						}
						if(nodStudent!=null) if(nameNode2.equals("text:p")) {
							if(nod2Sujet.getAttributs().get("index")!=null) {
								nod2Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode2, "index", nod2Sujet.getAttributs().get("index"));
							}
						}
						if(nodStudent!=null) if(nameNode2.equals("text:database-display")) {
							if(nod2Sujet.getAttributs().get("text:column-name")!=null) {
								nod2Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode2, "text:column-name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:column-name")));
							}
						}
						
						//insère un saut si titre pas vide et saut=true
						frame=addNodeSautTitre(nod2Sujet, frame);
						
						// analyse attribut et contenu des enfants du second niveau
						frame = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, frame, "ana:frame",nod2Sujet.getNomElt() );
						
						
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) { //niveau 4
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							node nod3Student = null;	
							if(nod2Student!=null) if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
								nod3Student = nod2Student.retourneFirstEnfantsByName(nameNode3);
							}
							if(nod2Student!=null) if(nameNode3.equals("text:database-display")) {
								if(nod3Sujet.getAttributs().get("text:column-name")!=null) {
									nod3Student = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode3, "text:column-name", outils.withoutCodeAndPoint(nod3Sujet.getAttributs().get("text:column-name")));
								}
							}

							// analyse attribut et contenu des enfants du troisième niveau
							if(nod3Student!=null) if(nod3Sujet.getNomElt().equals("text:sequence") && nod3Student.getNomElt().equals("text:sequence")) {
								ArrayList<node> changements = nod2Student.retourneEnfantsByName("text:change", new ArrayList<node>());
								nod3Student.setNodes(changements);
							}
							
							//insère un saut si titre pas vide et saut=true
							frame=addNodeSautTitre(nod3Sujet, frame);
							
							// analyse attribut et contenu des enfants du second niveau
							frame = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, frame, "ana:frame", nod3Sujet.getNomElt());
						}
	
					}
						
				}
					
				frame.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				frame.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodframes.getNodes().add(frame);


			}
		}
		
		nodframes.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodframes.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodframes.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodframes.setClose(true);		
		return nodframes;
		
	}
	
	/**
	 * Analyse du node <b>bibliographies</b>.
	 * @param nodStudentBiblio
	 * @param nodSujetBiblio
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseLaBiblio(node nodStudentBiblio, node nodSujetBiblio, Run a, node nodmenu) {
		node nodbiblio = new node();
		nodbiblio.setNomElt("bibliographies");
		nodbiblio.setAttributs(nodSujetBiblio.getAttributs());//ajoute tous les attributs du sujet
		nodbiblio.setContenu(nodSujetBiblio.getContenu());//ajoute le commentaire du sujet
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "biblio")!=null) {
			nodbiblio.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "biblio").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		
		// node biblio
		node biblio = new node();
		
		for(int i = 0 ; i < nodSujetBiblio.getNodes().size(); i++) {
			if(nodSujetBiblio.getNodes().get(i).getNomElt().equals("text:bibliography")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDeLaBiblio = outils.withoutCodeAndPoint(nodSujetBiblio.getNodes().get(i).getAttributs().get("text:name"));
				
				biblio.setNomElt("biblio");
				biblio.getAttributs().put("namebiblio", nomDeLaBiblio);
				if(nodSujetBiblio.getNodes().get(i).getAttributs().get("titre")!=null) biblio.getAttributs().put("titre", nodSujetBiblio.getNodes().get(i).getAttributs().get("titre"));
						
				String TitreTable = outils.withoutCodeAndPoint(nodSujetBiblio.getNodes().get(i).retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));

				node nodSujet = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodSujetBiblio, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodStudentBiblio, "text:index-body"), TitreTable);
								
				biblio = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, biblio, "ana:biblio", nodSujet.getNomElt());
				
				for(int j =0 ; j < nodSujet.getNodes().size();j++) {
					if(nodStudent!=null) {
						if(j<nodStudent.getNodes().size()) {
							biblio = analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j), nodSujet.getNodes().get(j), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNomElt());
							for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
								if(k<nodStudent.getNodes().get(j).getNodes().size()) {
									biblio = analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j).getNodes().get(k), nodSujet.getNodes().get(j).getNodes().get(k), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}else {
									biblio = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}
							}
						}else {
							biblio = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNomElt());
						}
					}else {
						biblio = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNomElt());
						for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
							biblio = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
						}
					}
				}
				
				
//				ArrayList<node> LestextpSujet = a.retourneNames(nodSujet, "text:p");
//				ArrayList<node> LestextpStudent = null;
//				if(nodStudent!=null) LestextpStudent = a.retourneNames(nodStudent, "text:p");
//				biblio = analyseLesContenusDesArrayList(LestextpStudent,LestextpSujet,biblio,"ana:biblio","txt:p",a);
				
				biblio.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				biblio.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodbiblio.getNodes().add(biblio);
			}
		}
			
		
		nodbiblio.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodbiblio.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodbiblio.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodbiblio.setClose(true);		
		return nodbiblio;
		
	}
	
	/**
	 * Analyse du node <b>tablematieres</b>.
	 * @param nodStudentTableM
	 * @param nodSujetTableM
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseLesTablesMatieres(node nodStudentTableM, node nodSujetTableM, Run a, node nodmenu) {
		node nodTablesMs = new node();
		nodTablesMs.setNomElt("tablematieres");
		nodTablesMs.setAttributs(nodSujetTableM.getAttributs());//ajoute tous les attributs du sujet
		nodTablesMs.setContenu(nodSujetTableM.getContenu());//ajoute le commentaire du sujet
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "tablematieres")!=null) {
			nodTablesMs.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "tablematieres").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		
		
		for(int i = 0 ; i < nodSujetTableM.getNodes().size(); i++) {
			if(nodSujetTableM.getNodes().get(i).getNomElt().equals("text:table-of-content")) {
				node table = new node();
				table.setNomElt("tablematiere");
				if(nodSujetTableM.getNodes().get(i).getAttributs().get("titre")!=null) {
					String titre = nodSujetTableM.getNodes().get(i).getAttributs().get("titre");
					if(!titre.isEmpty()) table.getAttributs().put("titre", titre);
				}
				
				
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String TitreTable = outils.withoutCodeAndPoint(nodSujetTableM.getNodes().get(i).retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));
				
				node nodSujet = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodSujetTableM, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodStudentTableM, "text:index-body"), TitreTable);

				table = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, table, "ana:tablematiere", nodSujet.getNomElt());
				
				for(int j =0 ; j < nodSujet.getNodes().size();j++) {
					if(nodStudent!=null) {
						if(j<nodStudent.getNodes().size()) {
							table = analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j), nodSujet.getNodes().get(j), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNomElt());
							for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
								if(k<nodStudent.getNodes().get(j).getNodes().size()) {
									table = analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j).getNodes().get(k), nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}else {
									table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}
							}
						}else {
							table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNomElt());
						}
					}else {
						table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNomElt());
						for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
							table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
						}
					}
				}
				
				
//				ArrayList<node> LestextpSujet = a.retourneNames(nodSujet, "text:p");
//				ArrayList<node> LestextpStudent = null;
//				if(nodStudent!=null) LestextpStudent = a.retourneNames(nodStudent, "text:p");
//				
//				table = analyseLesContenusDesArrayList(LestextpStudent,LestextpSujet,table,"ana:tablematiere","txt:p",a);
				
				table.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				table.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodTablesMs.getNodes().add(table);

			}
		}
		
			nodTablesMs.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
			nodTablesMs.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
			nodTablesMs.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
			nodTablesMs.setClose(true);		
		return nodTablesMs;
	}
	
	/**
	 * Analyse du node <b>tableillustrations</b>.
	 * @param nodStudentTableI
	 * @param nodSujetTableI
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	private static node analyseLesTablesIllustrations(node nodStudentTableI, node nodSujetTableI, Run a, node nodmenu) {
		node nodTablesMs = new node();
		nodTablesMs.setNomElt("tableillustrations");
		nodTablesMs.setAttributs(nodSujetTableI.getAttributs());//ajoute tous les attributs du sujet
		nodTablesMs.setContenu(nodSujetTableI.getContenu());//ajoute le commentaire du sujet
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "tableillustrations")!=null) {
			nodTablesMs.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "tableillustrations").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		
		
		for(int i = 0 ; i < nodSujetTableI.getNodes().size(); i++) {
			if(nodSujetTableI.getNodes().get(i).getNomElt().equals("text:illustration-index")) {
				node table = new node();
				table.setNomElt("tableillustrations");
				if(nodSujetTableI.getNodes().get(i).getAttributs().get("titre")!=null) {
					String titre = nodSujetTableI.getNodes().get(i).getAttributs().get("titre");
					if(!titre.isEmpty()) table.getAttributs().put("titre", titre);
				}
				
				
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String TitreTable = outils.withoutCodeAndPoint(nodSujetTableI.getNodes().get(i).retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));
				
				node nodSujet = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodSujetTableI, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodStudentTableI, "text:index-body"), TitreTable);
		
				table = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, table, "ana:tableillustration", nodSujet.getNomElt());
				
				for(int j =0 ; j < nodSujet.getNodes().size();j++) {
					if(nodStudent!=null) {
						if(j<nodStudent.getNodes().size()) {
							table = analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j), nodSujet.getNodes().get(j), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNomElt());
							for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
								if(k<nodStudent.getNodes().get(j).getNodes().size()) {
									table = analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j).getNodes().get(k), nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}else {
									table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}
							}
						}else {
							table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNomElt());
						}
					}else {
						table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNomElt());
						for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
							table = analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
						}
					}
				}
				
				
				
				
//				ArrayList<node> LestextpSujet = a.retourneNames(nodSujet, "text:p");
//				ArrayList<node> LestextpStudent = a.retourneNames(nodStudent, "text:p");
//				table = analyseLesContenusDesArrayList(LestextpStudent,LestextpSujet,table,"ana:tableillustration","txt:p",a);
				
				table.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				table.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodTablesMs.getNodes().add(table);

			}
		}
		
			nodTablesMs.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
			nodTablesMs.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
			nodTablesMs.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
			nodTablesMs.setClose(true);		
		return nodTablesMs;
	}
	
	/**
	 * Cette méthode permet d'analyse la structure du document.<br>
	 * La structure de l'étudiant est comparé avec la structure du sujet.<br>
	 * <br>
	 * @param nodStudentS : node de la structure de l'étudiant.
	 * @param nodSujetS : node de la structure du sujet.
	 * @param a : Objet de la class cXML
	 * @param nodmenu : node menu.
	 * @param nodSujetParagraphs : node contenant l'ensemble des styles de paragraphe du sujet.
	 * @param nodStudentParagraphs : node contenant l'ensemble des styles de paragraphe de l'étudiant.
	 * @return : le node d'analyse de la structure.
	 */
	private static node analyseStructurePage(node nodStudentS, node nodSujetS, Run a, node nodmenu, node nodSujetParagraphs, node nodStudentParagraphs) {
		node nodSpages = new node();
		nodSpages.setNomElt("structurepage");
		nodSpages.setAttributs(nodSujetS.getAttributs());
		nodSpages.setContenu(nodSujetS.getContenu()); //ajoute le commentaire du sujet
		
		//ajoute l'identifiant pour le menu
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "structurepage")!=null) {
			nodSpages.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "structurepage").getAttributs().get("id"));
		}
		
		//initialise les points
		outils.initiliseLesPoints();
		
		//********************************
		// Premier niveau que les pages
		for(int i = 0 ; i < nodSujetS.getNodes().size(); i++) {
			if(nodSujetS.getNodes().get(i).getNomElt().equals("page")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDeLaPage = outils.withoutCodeAndPoint(nodSujetS.getNodes().get(i).getAttributs().get("style:master-page-name"));
				if(nomDeLaPage==null) nomDeLaPage="Défaut";
				String numeroabsolue = outils.withoutCodeAndPoint(nodSujetS.getNodes().get(i).getAttributs().get("numeroabsolue"));
				node page = new node();
				page.setNomElt("page");
				page.getAttributs().put("namepage", nomDeLaPage);
				page.getAttributs().put("numeroabsolue", numeroabsolue);
				
				if(nodSujetS.getNodes().size()>0) if(nodSujetS.getNodes().get(i).getAttributs().get("titre")!=null) page.getAttributs().put("titre", nodSujetS.getNodes().get(i).getAttributs().get("titre"));
				
				node pageStudent = a.retourneFirstNodeByNameAttributValue(nodStudentS, "page", "numeroabsolue", numeroabsolue);
				node pageSujet = nodSujetS.getNodes().get(i);
				
				// analyse les attributs du node
				page = analyseLesAttributEtContenuDuNode(pageStudent, pageSujet, page, "ana:page",pageSujet.getNomElt());
	
				//**************************************
				// second niveau
				for(int j = 0 ; j < pageSujet.getNodes().size();j++ ) {
				
				//boolean paragrapheTexte = false;
				node nodSujet = pageSujet.getNodes().get(j);
				String nameNode = nodSujet.getNomElt();
				node nodStudent = null;	
		
				nodStudent = rechercheLeNodeEnCascade(nameNode,nodSujet,null,null,pageStudent,a);
			
				//insère un saut s'il y a un titre avec un saut=true
				page = addNodeSautTitre(nodSujet, page);

				// analyse attribut et contenu des enfants du premier niveau
				page = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, page, "ana:page",nodSujet.getNomElt());
				
				
				//analyse le nom du node
				if(nodSujet.getAttributs().get("evalNameNode")!=null) {
					String point = nodSujet.getAttributs().get("evalNameNode");
					page =analyseNameNode(page,nodStudent,nodSujet.getNomElt(),point, nodSujet.getNomElt());
				}
				
				// méthode analyseStyle
				if(nameNode.equals("text:p") && nodSujetParagraphs!=null) {
					page = analyseStyle(page, nodSujet,nodStudent, nodSujetParagraphs,nodStudentParagraphs);
				}

					//***************************
					//troisieme niveau
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) {
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						node nod2Student = null;
				
						nod2Student = rechercheLeNodeEnCascade(nameNode2,nod2Sujet,null,pageStudent,nodStudent,a);
						
						//insère un saut s'il y a un titre avec un saut=true
						page = addNodeSautTitre(nod2Sujet, page);
						
						//analyse le nom du node
						if(nod2Sujet.getAttributs().get("evalNameNode")!=null) {
							String point = nod2Sujet.getAttributs().get("evalNameNode");
							page =analyseNameNode(page,nod2Student,nod2Sujet.getNomElt(),point, nod2Sujet.getNomElt());
						}
						
						// méthode analyseStyle
						if(nameNode.equals("text:p") && nodSujetParagraphs!=null) {
							page = analyseStyle(page, nod2Sujet,nod2Student, nodSujetParagraphs,nodStudentParagraphs);
						}
						
						// analyse attribut et contenu des enfants du second niveau
						page = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, page, "ana:page",nod2Sujet.getNomElt() );
						
						//************************
						//quatrieme niveau
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) {
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							node nod3Student = null;	
		
							nod3Student = rechercheLeNodeEnCascade(nameNode3,nod3Sujet,pageStudent,nodStudent,nod2Student,a);
							
							//insère un saut s'il y a un titre avec un saut=true
							page = addNodeSautTitre(nod3Sujet, page);
							
							
							//analyse le nom du node
							if(nod3Sujet.getAttributs().get("evalNameNode")!=null) {
								String point = nod3Sujet.getAttributs().get("evalNameNode");
								page =analyseNameNode(page,nod3Student,nod3Sujet.getNomElt(),point, nod3Sujet.getNomElt());
							}
							
							// méthode analyseStyle
							if(nameNode.equals("text:p") && nodSujetParagraphs!=null) {
								page = analyseStyle(page, nod3Sujet,nod3Student, nodSujetParagraphs,nodStudentParagraphs);
							}
							
							// analyse attribut et contenu des enfants du troisième niveau
							page = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, page, "ana:page", nod3Sujet.getNomElt());
						
							//****************
							//cinquième niveau
							for(int m = 0 ; m < nod3Sujet.getNodes().size();m++) {
								node nod4Sujet = nod3Sujet.getNodes().get(m);
								String nameNode4 = nod4Sujet.getNomElt();
								node nod4Student = null;
						
								nod4Student = rechercheLeNodeEnCascade(nameNode4,nod4Sujet,nodStudent,nod2Student,nod3Student,a);

								//insère un saut s'il y a un titre avec un saut=true
								page = addNodeSautTitre(nod4Sujet, page);

								//analyse le nom du node
								if(nod3Sujet.getAttributs().get("evalNameNode")!=null) {
									String point = nod4Sujet.getAttributs().get("evalNameNode");
									page =analyseNameNode(page,nod4Student,nod4Sujet.getNomElt(),point, nod4Sujet.getNomElt());
								}
								
								// méthode analyseStyle
								if(nameNode.equals("text:p") && nodSujetParagraphs!=null) {
									page = analyseStyle(page, nod4Sujet,nod4Student, nodSujetParagraphs,nodStudentParagraphs);
								}
								
								// analyse attribut et contenu des enfants du troisième niveau
								page = analyseLesAttributEtContenuDuNode(nod4Student, nod4Sujet, page, "ana:page", nod4Sujet.getNomElt());
							
								
								//************
								// Sixieme niveau
								for(int p = 0 ; p < nod4Sujet.getNodes().size();p++) {
									node nod5Sujet = nod4Sujet.getNodes().get(p);
									String nameNode5 = nod5Sujet.getNomElt();
									node nod5Student = null;
					
									nod5Student = rechercheLeNodeEnCascade(nameNode5,nod5Sujet,nod2Student,nod3Student,nod4Student,a);
									
									//insère un saut s'il y a un titre avec un saut=true
									page = addNodeSautTitre(nod5Sujet, page);

									//analyse le nom du node
									if(nod5Sujet.getAttributs().get("evalNameNode")!=null) {
										String point = nod5Sujet.getAttributs().get("evalNameNode");
										page =analyseNameNode(page,nod5Student,nod5Sujet.getNomElt(),point, nod5Sujet.getNomElt());
									}
									
									// méthode analyseStyle
									if(nameNode.equals("text:p") && nodSujetParagraphs!=null) {
										page = analyseStyle(page, nod5Sujet,nod5Student, nodSujetParagraphs,nodStudentParagraphs);
									}
									
									// analyse attribut et contenu des enfants du troisième niveau
									page = analyseLesAttributEtContenuDuNode(nod5Student, nod5Sujet, page, "ana:page", nod5Sujet.getNomElt());
								
								}
							}
						
						}
	
					}
						
				}
					
				page.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				page.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodSpages.getNodes().add(page);


			}
		}
		
		nodSpages.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodSpages.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodSpages.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodSpages.setClose(true);		
	return nodSpages;
		
	}
	
	/**
	 * Analyse tous les attributs et les contenus d'un node.<br>
	 * Les attributs et les contenus doivent posséder la carcatère ‽ ou ‼.<br>
	 * <br>
	 * @param nodeStudent : le node de l'étudiant.
	 * @param sujet : le node du sujet
	 * @param retour : le node à retourner avec les enfants nommés nameItem.
	 * @param nameItem : le nom des nodes enfants.
	 * @param nameElt : le nom de l'élément (node) analysé.
	 * @return le node <b>retour</b> avec tous les nodes enfants <b>nameItem</b> contenant les différentes analyse. 
	 */
  	private static node analyseLesAttributEtContenuDuNode(node nodeStudent, node sujet, node retour, String nameItem, String nameElt) {
		Enumeration<String> key = sujet.getAttributs().keys();
		while(key.hasMoreElements()) {
			String k = key.nextElement();
			
			if( sujet.getAttributs().get(k).contains("‽")){
				if(nodeStudent!=null) {
					String valueAttributStudent = nodeStudent.getAttributs().get(k);
					String valueAttributSujet = sujet.getAttributs().get(k);

					node item = retourneNoteAvecResultatsAnalyse(nameItem,k, valueAttributStudent, valueAttributSujet,nameElt);
					retour.getNodes().add(item);
				}else {
					String valueAttributStudent = "null";
					String valueAttributSujet = sujet.getAttributs().get(k);
						
					node item = retourneNoteAvecResultatsAnalyse(nameItem, k, valueAttributStudent, valueAttributSujet,nameElt);
					retour.getNodes().add(item);
				}
			}
			
			if(!sujet.getAttributs().get(k).equals("0")) {
				if(k.equals("evalNameNode") && !sujet.getAttributs().get(k).equals("0")) {
					if(nodeStudent!=null) {
						retour = analyseNameNode(retour,nodeStudent, sujet.getNomElt(), sujet.getAttributs().get("evalNameNode"),sujet.getNomElt());
					}else {
						retour = analyseNameNode(retour,null, sujet.getNomElt(), sujet.getAttributs().get("evalNameNode"),sujet.getNomElt());
					}
				}
				
				if(k.equals("evalNameCreator") && sujet.getNomElt().equals("dc:creator")) {
					if(nodeStudent!=null) {
						retour = analyseNameCreator(retour,nodeStudent, nodeStudent.getContenu(), sujet.getAttributs().get("evalNameCreator"),"Editeur");
					}else {
						retour = analyseNameCreator(retour,null, "Editeur inconnu", sujet.getAttributs().get("evalNameCreator"),"Editeur");
					}
				}
				
				if(k.equals("evalNameInitialCreator") && sujet.getNomElt().equals("meta:initial-creator")) {
					if(nodeStudent!=null) {
						retour = analyseNameInitialCreator(retour,nodeStudent, nodeStudent.getContenu(), sujet.getAttributs().get("evalNameInitialCreator"),"Créateur");
					}else {
						retour = analyseNameInitialCreator(retour,null, "Créateur inconnu", sujet.getAttributs().get("evalNameInitialCreator"),"Créateur");
					}
				}
				
			}
			
		}
		//avec l'attribut allContent="strict1" ou  allContent="environ1" alors analyse tout le contenu du node
		if(sujet.getAttributs().get("allContent")!=null) if(!sujet.getAttributs().get("allContent").isEmpty()){
			String points ="‽0";
			if(sujet.getAttributs().get("allContent").contains("strict")) points = sujet.getAttributs().get("allContent").replace("strict", "‽");
			if(sujet.getAttributs().get("allContent").contains("strictSansEspace")) points = sujet.getAttributs().get("allContent").replace("strictSansEspace", "≡‽");
			if(sujet.getAttributs().get("allContent").contains("environ")) points = sujet.getAttributs().get("allContent").replace("environ", "¢‽");
			
			String allContentSujet = sujet.retourneLesContenusEnfants("") + points;
			String allContentStudent = "null";
			if( nodeStudent!=null) allContentStudent = nodeStudent.retourneLesContenusEnfants("");
			node item = retourneNoteAvecResultatsAnalyse(nameItem,"Contenu textuel", allContentStudent, allContentSujet, nameElt);
			retour.getNodes().add(item);
		}
		
		// analyse le contenu du node avec tous les nodes sauf "text:sequence"
		if(sujet.getContenu().contains("‽") && !sujet.getNomElt().equals("text:sequence")) {
			String contenuStudent ="";
			if(nodeStudent!=null) contenuStudent = nodeStudent.getContenu();
			String contenuSujet = sujet.getContenu();
			node item = retourneNoteAvecResultatsAnalyse(nameItem,"Contenu textuel", contenuStudent, contenuSujet, nameElt);
			retour.getNodes().add(item);
		}
		
		// analyse le contenu du node text:sequence et tous les enfants text:change (lorsque les légendes sont modifiées)
		if(sujet.getContenu().contains("‽") && sujet.getNomElt().equals("text:sequence")) {
			String contenuStudent ="";
			if(nodeStudent!=null) contenuStudent = nodeStudent.retourneLesContenusEnfants("text:change");
			String contenuSujet = sujet.getContenu();
			node item = retourneNoteAvecResultatsAnalyse(nameItem,"Contenu textuel", contenuStudent, contenuSujet, nameElt);
			retour.getNodes().add(item);
		}
	
		return retour;
	}
  	
  	/**
  	 * Analyse tous les attributs des styles de paragraphes.<br>
	 * Formatage direct des styles de paragraphe. Les attributs doivent contenir le code ‼.<br>
	 * <br>
	 * @param nodeStudent : le node de l'étudiant.
	 * @param sujet : le node du sujet
	 * @param retour : le node à retourner avec les enfants nommés nameItem.
	 * @param nameItem : le nom des nodes enfants.
	 * @param nameElt : le nom de l'élément (node) analysé.
	 * @return le node <b>retour</b> avec tous les nodes enfants <b>nameItem</b> contenant les différentes analyse. 
  	 */
	private static node analyseLesAttributAnalyseStyle(node nodeStyleParagraphStudent, node nodeStyleParagraphSujet, node retour, String nameItem, String nameElt) {

		Enumeration<String> key = nodeStyleParagraphSujet.getAttributs().keys();
		while(key.hasMoreElements()) {
			String k = key.nextElement();
			if(nodeStyleParagraphSujet.getAttributs().get(k).contains("‼") || nodeStyleParagraphSujet.getAttributs().get(k).contains("‽")){
				if(nodeStyleParagraphStudent!=null) {
					String valueAttributStudent = nodeStyleParagraphStudent.getAttributs().get(k);
					String valueAttributSujet = nodeStyleParagraphSujet.getAttributs().get(k).replace("‼", "‽");
					node item = retourneNoteAvecResultatsAnalyse(nameItem,k, valueAttributStudent, valueAttributSujet,nameElt);
					retour.getNodes().add(item);
				}else {
					String valueAttributStudent = "null";
					String valueAttributSujet = nodeStyleParagraphSujet.getAttributs().get(k).replace("‼", "‽");
					node item = retourneNoteAvecResultatsAnalyse(nameItem, k, valueAttributStudent, valueAttributSujet,nameElt);
					retour.getNodes().add(item);
				}
			}
		}

		if(nodeStyleParagraphStudent!=null) {
			if(nodeStyleParagraphSujet.retourneEnfantsByNameExist("style:paragraph-properties") && nodeStyleParagraphStudent.retourneEnfantsByNameExist("style:paragraph-properties") ) {
				node propertiesSujet = nodeStyleParagraphSujet.retourneFirstEnfantsByName("style:paragraph-properties");
				node propertiesStudent = nodeStyleParagraphStudent.retourneFirstEnfantsByName("style:paragraph-properties");
				key = propertiesSujet.getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(propertiesSujet.getAttributs().get(k).contains("‼") || propertiesSujet.getAttributs().get(k).contains("‽")){
						if(propertiesStudent!=null) {
							String valueAttributStudent = propertiesStudent.getAttributs().get(k);
							String valueAttributSujet = propertiesSujet.getAttributs().get(k).replace("‼", "‽");
							node item = retourneNoteAvecResultatsAnalyse(nameItem,k, valueAttributStudent, valueAttributSujet,"style:paragraph-properties");
							retour.getNodes().add(item);
						}else {
							String valueAttributStudent = "null";
							String valueAttributSujet = propertiesSujet.getAttributs().get(k).replace("‼", "‽");
							node item = retourneNoteAvecResultatsAnalyse(nameItem, k, valueAttributStudent, valueAttributSujet,"style:paragraph-properties");
							retour.getNodes().add(item);
						}
					}
				}
			}
		}else {
			node propertiesSujet = nodeStyleParagraphSujet.retourneFirstEnfantsByName("style:paragraph-properties");
			key = propertiesSujet.getAttributs().keys();
			while(key.hasMoreElements()) {
				String k = key.nextElement();
				if(propertiesSujet.getAttributs().get(k).contains("‼") || propertiesSujet.getAttributs().get(k).contains("‽")){
					String valueAttributStudent = "null";
					String valueAttributSujet = propertiesSujet.getAttributs().get(k).replace("‼", "‽");
							
					node item = retourneNoteAvecResultatsAnalyse(nameItem, k, valueAttributStudent, valueAttributSujet,"style:paragraph-properties");
					retour.getNodes().add(item);
				}
			}
		}

		if(nodeStyleParagraphStudent!=null) {
			if(nodeStyleParagraphSujet.retourneEnfantsByNameExist("style:text-properties") && nodeStyleParagraphStudent.retourneEnfantsByNameExist("style:text-properties") ) {
				node propertiesSujet = nodeStyleParagraphSujet.retourneFirstEnfantsByName("style:text-properties");
				node propertiesStudent = nodeStyleParagraphStudent.retourneFirstEnfantsByName("style:text-properties");
				key = propertiesSujet.getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(propertiesSujet.getAttributs().get(k).contains("‼") || propertiesSujet.getAttributs().get(k).contains("‽")){
						if(propertiesStudent!=null) {
							String valueAttributStudent = propertiesStudent.getAttributs().get(k);
							String valueAttributSujet = propertiesSujet.getAttributs().get(k).replace("‼", "‽");

							node item = retourneNoteAvecResultatsAnalyse(nameItem,k, valueAttributStudent, valueAttributSujet,"style:text-properties");
							retour.getNodes().add(item);
						}else {
							String valueAttributStudent = "null";
							String valueAttributSujet = propertiesSujet.getAttributs().get(k).replace("‼", "‽");
								
							node item = retourneNoteAvecResultatsAnalyse(nameItem, k, valueAttributStudent, valueAttributSujet,"style:text-properties");
							retour.getNodes().add(item);
						}
					}
				}
			}
		}else {
			node propertiesSujet = nodeStyleParagraphSujet.retourneFirstEnfantsByName("style:text-properties");
			key = propertiesSujet.getAttributs().keys();
			while(key.hasMoreElements()) {
				String k = key.nextElement();
				if(propertiesSujet.getAttributs().get(k).contains("‼") || propertiesSujet.getAttributs().get(k).contains("‽")){
					String valueAttributStudent = "null";
					String valueAttributSujet = propertiesSujet.getAttributs().get(k).replace("‼", "‽");
							
					node item = retourneNoteAvecResultatsAnalyse(nameItem, k, valueAttributStudent, valueAttributSujet,"style:text-properties");
					retour.getNodes().add(item);
				}
			}
		}

		return retour;
	}
  	
  	
//  	/**
//  	 * Analyse toutes les contenus des enfants et les compares.<br>
//  	 * Utiliser pour les nodes <text:p><br>
//  	 * <br>
//  	 * @param Student
//  	 * @param Sujet
//  	 * @param retour
//  	 * @param nameItem
//  	 * @param nameElt
//  	 * @param a
//  	 * @return
//  	 */
//  	private static node analyseLesContenusDesArrayList(ArrayList<node> Student, ArrayList<node> Sujet, node retour, String nameItem, String nameElt, Run a) {
//  		
//  		for(int i = 0 ; i < Sujet.size();i++) {
//  			String sujetContent = Sujet.get(i).retourneLesContenusEnfants("");
//  			node StudentNode = a.retourneFirstNodeByFindContent2(Student, outils.withoutCodeAndPoint(sujetContent),commandes.tolerance_characters,commandes.tolerance_text);
//  			String studentContent = "null";
//  			if(StudentNode!=null) studentContent = outils.NetChiffreALaFin(StudentNode.retourneLesContenusEnfants(""));
//  			
//  			node item = retourneNoteAvecResultatsAnalyse(nameItem, "Contenu textuel", studentContent, sujetContent,nameElt);
//  			retour.getNodes().add(item);
// 			
//  		}
//  		
//  		return retour;
//  	}
  	
	
	/**
	 * Retourne le node avec les résultats de la comparaison entre les deux valeurs (étudiant et sujet).<br>
	 * <br>
	 * @param nameNode
	 * @param Tst (résultat de la comparaison)
	 * @param property
	 * @param valueAttributStudent
	 * @param valueAttributSujet
	 * @return
	 */
 	private static node retourneNoteAvecResultatsAnalyse(String nameNode, String property, String valueStudent, String valueSujet, String nameElt) {
		String Tst = outils.Compare(valueStudent, valueSujet);
		int niveau = 3;
		if(Tst.contains("Correct")) niveau = 1;
		if(Tst.contains("Erreur")) niveau = 2;
		valueStudent = outils.remplaceCaracteresCodageAttribut(valueStudent);
		valueSujet = outils.remplaceCaracteresCodageAttribut(valueSujet);
		node item = new node(nameNode, Tst, property , valueStudent, valueSujet, niveau, outils.getPointEnJeu(),nameElt);
		return item;
	}
	
 	/**
 	 * Création du feedback (compte-rendu) au format HTML.<br>
 	 * <br>
 	 * @param nodana
 	 * @throws IOException
 	 */
 	private static void feedback(node nodana, node verif) throws IOException {
 		
 		System.getProperty("file.encoding","UTF-8");
 		Date aujourdhui = new Date();
 		
 		int number_match = 2;
 		int mini_modification = 0;
		boolean plagiat = false;
		boolean copiercoller = false;
		boolean pasAssezDeModification =false;
		boolean baremeABC = false;
		boolean producteur = false;
		String SuiteBureautique = "";
		String VersionLibreOffice = "";
		String SystemeStudent = "";
		node verifStudent = null;
 		if((commandes.verifHisto||commandes.verifHisto2)&&commandes.ecritNoteCSV&&commandes.fourniCSV) {
 			if(verif.getAttributs().get("number_match") != null) number_match = Integer.valueOf(verif.getAttributs().get("number_match"));
 			if(verif.getAttributs().get("mini_number_modification") != null) mini_modification = Integer.valueOf(verif.getAttributs().get("mini_number_modification"));
 			
 			//verification du plagiat
			verifStudent = verif.retourneFirstNodeByNameAndAttributValue("fichier", "dossier", nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"));
 			if(verifStudent != null) {
 				if(verifStudent.getAttributs().get("filename").equals(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("filename"))) {
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"))>number_match) plagiat=true;
 					if(!verifStudent.getAttributs().get("first_modification_identique").equals("null") && Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"))>=number_match) plagiat=true;
 					if(verifStudent.getAttributs().get("copier_coller")!=null) copiercoller=true;
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_modification"))<=mini_modification) pasAssezDeModification=true;
 				}
 			}
 		}
 		
 		//BaremeABC
 		if(nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("baremeABC")!=null) {
 			try {
 				baremeABC= Boolean.valueOf(nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("baremeABC"));
 			}catch (Exception e) {
				System.out.println("Problème avec la valeur binaire de l'attribut baremeABC.");
			}
 		}
 		
 		
 		
 		//nom du fichier feedback
 		String metaS = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet");
 		if(metaS.equals("?")) metaS = "metaSujet-inconnu";
 		if(metaS.isEmpty()) metaS = "metaSujet-inconnu";
 		String cheminFeedBack = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier") + "-DateLong" + aujourdhui.getTime()+"-"+metaS;
  		if(!commandes.noNote&&!baremeABC) {
  			if(!plagiat&&!copiercoller)cheminFeedBack = cheminFeedBack + "-" + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note") + ".html";
  			if(plagiat) cheminFeedBack = cheminFeedBack + "- plagiat.html";
  			if(copiercoller) cheminFeedBack = cheminFeedBack + "- copier-coller.html";
  			if(pasAssezDeModification) cheminFeedBack = cheminFeedBack + "- pas assez de modification.html";
  		}
  		if(!commandes.noNote&&baremeABC) {
  			if(!plagiat)cheminFeedBack = cheminFeedBack + "-" + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("noteABC") + ".html";
  			if(copiercoller) cheminFeedBack = cheminFeedBack + "- copier-coller.html";
  			if(plagiat) cheminFeedBack = cheminFeedBack + "- plagiat.html";
  			if(pasAssezDeModification) cheminFeedBack = cheminFeedBack + "- pas assez de modification.html";
  		}
  		if(commandes.noNote) {
  			cheminFeedBack = cheminFeedBack + ".html";
  		}
 		
  		// Chemin vers le dossier de destination
		Path outputFilePath = Paths.get(patch + "/" + cheminFeedBack);
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(patch + "/" + commandes.pathDestination+ "/" + cheminFeedBack);
		
			
		BufferedWriter  fichier = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
		
		//ajoute le chemin vers le feedback dans le node d'analyse
		nodana.retourneFirstEnfantsByName("ouverture").getAttributs().put("feedback", patch + "/" + cheminFeedBack);
		
		// auteur du sujet
		String auteurSujet = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("auteur");
		if(auteurSujet==null) auteurSujet="";
		
		
		//création du feedback
		fichier.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r"
				+ "<html>\r"
				+ "<head>\r"
				+ "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\r"
				+ "<title>Analyse LibreOffice Calc</title>\r");
		
		fichier.write("<meta name=\"generator\" content=\"analyseWriter V3.5.0\"/>"
				+ "<meta name=\"author\" content=\"Pablo Rodriguez\"/>"
				+ "<meta name=\"created\" content=\""+  nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("date") +"\"/>");

		
		fichier.write("<style type=\"text/css\">" 
				+ "p.pablo { margin-bottom: 0.25cm; line-height: 100%; background: transparent;  margin-left: 1cm; }"
				+ ".header {background-color: #f1f1f1;padding: 30px;text-align:center;}"
				+ "h1 { margin-bottom: 0.25cm; background: transparent;}"
				+ "h2 {color: blue;font-size:22pt;margin-bottom: 0cm; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 20pt;  margin-right: 10px; text-decoration: underline overline;}"
				+ "h3 {font-size:18pt;margin-bottom: 0cm; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 20pt;}"
				+ "header.h1.western { font-family: \"Arial\"; font-size: 18pt; font-weight: bold; backgroung: #adff2f;}"
				+ ".header p {color:blue; font-size:30px;}"
				+ ".triche {background: #AA0000;padding-top: 5px;padding-right: 5px;padding-bottom: 5px;padding-left: 5px;width=80%;margin-top:18px; box-shadow: 5px 10px 18px #800000;}"
				+ ".triche p {color:white; font-size:16px;margin-left:10px;margin-bottom:6px;margin-top:6px}"
				+ ".header h4 {text-align:left;font-family: \"Arial\"; font-size: 12pt; font-weight: bold; line-height: 110%;}"
				+ "h4.western { font-family: \"Arial\"; font-size: 14pt; font-style: italic; font-weight: bold; line-height: 40%}"
				+ "a:link { color: #000099; so-language: zxx; text-decoration: underline; margin-left: 10px; }" 
				+ "a:visited { color: #990000; so-language: zxx; text-decoration: underline; margin-left: 10px; }"
				+ "hr { display: block; margin-top: 0.5em; margin-bottom: 8em; margin-left: 2em; margin-right: 2em; border-style: inset; border-width: 4px;}"
				+ "spanpablo { float: right; width: 8em; font-size: 250%; font-family: algerian, courier; line-height: 80%; margin-right: 1%; color: red; text-align: center}"
				+ "p.p1{margin-bottom: 0cm; margin-top: 0cm; line-height: 100%; background: transparent;  margin-left: 0cm; white-space: pre;}"
				+ "p.p8{font-size:14pt;margin-bottom: 0cm; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 8pt;  margin-right: 10px;}"
				+ "p.p9{font-size:16pt;margin-bottom: 12px;text-align: left; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 40pt;  margin-right: 0cm;text-decoration: underline overline wavy blue;text-shadow: 0px 1px #101010;}"
				+ "p.p10{font-size:12pt;margin-bottom: 12px;text-align: left; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 30pt;  margin-right: 0cm;text-decoration: underline overline wavy red;}"
				+ ".commentaire{margin-left: 0px; margin-bottom: 24px; margin-top: 24px;font-size:1.4rem}"
				+ "p.p2{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; line-height: 115%}"
				+ "p.p3{margin-left: 20px; line-height: 100%; border: 1px solid black; background-color: lightcyan; margin-right: 10px;  }"
				+ "p.p4{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; margin-right: 4px; line-height: 115%; background: darkblue; color:white; font-size: 20px; white-space: pre;}"
				+ "p.p5{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: red; color:white; font-size: 20px;}"
				+ "p.p6{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: beige; color:darkcyan; font-size: 20px;}"
				+ "p.p7{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: #7FFF00; font-size: 20px;}"
				+ "#navbar {overflow: hidden;background-color: #333;width:100%;box-shadow: 5px 10px 8px #888888;}"
				+ "#navbar a {float: left;display: block;color: #f2f2f2;text-align: center;padding: 14px 16px;text-decoration: none;font-size: 17px;}"
				+ "#navbar a:hover {background-color: #ddd;color: black;}" 
				+ "#navbar a.active {background-color: #4CAF50;color: white;margin-left:0px;}"
				+ "#navbar a.active2 {background-color: #FF8050;color: white;margin-left:0px;}"
				+ "#navbar a.active3 {background-color: #5080FF;color: white;margin-left:0px;}"
				+ "div.sticky {position: fixed;top: 0;width: 100%;}"
				+ ".sticky + .content {padding-top: 60px;}"
				+ "#navbar2 {overflow: hidden; background-color: #333;}"
				+ "#navbar2 a {float: left; font-size: 18px; color: white; text-align: center; padding: 16px 18px; text-decoration: none;}"
				+ ".dropdown {position: relative; display: inline-block;}"
				+ ".dropbtn:hover, .dropbtn:focus { background-color: #3e8e41;}"
				+ ".dropdown-content {display: none; position: absolute; background-color: #f9f9f9; min-width: 160px; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);}"
				+ ".dropdown-content a {color: black; padding: 12px 16px; text-decoration: none; display: block;}"
				+ ".dropdown-content a:hover {background-color: #f1f1f1}"
				+".show {display:block;}"
				+ "#myDropdown {background-color: #508050;color: black;}"
				+ ".menu-box{display: none;}"
				+ ".menuopen{display: block;}"
				+ ".tooltip {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip .tooltiptext {visibility: hidden;background-color: black;color: #fff;text-align: center;border-radius: 6px;padding: 5px 0;position: absolute;z-index: 1;margin-left: -30px; width: 260px;top: 100%;left: 10%;}"
				+ ".tooltip .tooltiptext::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
				+ ".tooltip:hover .tooltiptext {visibility: visible;}"
				+ ".tooltip1 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip1 .tooltiptext1 {visibility: hidden;background-color: #0000CC;color: #fff;text-align: left;border-radius: 4px;padding: 10px;position: absolute;z-index: 1;margin-left: -40px; width: 280px;top: 100%;left: 10%;}"
				+ ".tooltip1 .tooltiptext1::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent #0000CC transparent;}"
				+ ".tooltip1:hover .tooltiptext1 {visibility: visible;}"
				+ ".tooltip2 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip2 .tooltiptext2 {visibility: hidden;background-color: black;color: #fff;text-align: left;border-radius: 8px;padding: 8px;position: absolute;z-index: 1;margin-left: -40px; width: 340px;top: 100%;left: 10%;}"
				+ ".tooltip2 .tooltiptext2::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
				+ ".tooltip2:hover .tooltiptext2 {visibility: visible;}"
				+ ".footer {position: fixed;left: 0;bottom: 0;width: 100%;background-color: white;color: black;text-align: center;}"
				+"</style>");
		
		fichier.write("</head>\r");
		fichier.write("<body lang=\"fr-FR\" link=\"#000080\" vlink=\"#800000\" dir=\"ltr\">\r");
		
		fichier.write("<div class=\"header\">");
		if(!commandes.noLogo) {
			if(!commandes.newLogo) {
				fichier.write("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
						"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br>"+HTML.imgLogos()+"</font></h1>\r");
			}else {
				
				fichier.write("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
						"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br>"+commandes.contenuFichierSVG+"</font></h1>\r");
			}
		}else {
			fichier.write("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br></font></h1>\r");
		}
		
		
		//Note
		node ouvre = nodana.retourneFirstEnfantsByName("ouverture");
		String noteFrom = ouvre.getAttributs().get("notefrom");
		node notation = nodana.retourneFirstEnfantsByName("bodyetnotation");
		if(!baremeABC) {
			if(noteFrom ==null) noteFrom="20";
			if(!commandes.noNote) if(!plagiat&&!copiercoller&&!pasAssezDeModification) fichier.write("<p><spanpablo>" +  notation.getAttributs().get("note") + " / " + noteFrom +"<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			if(plagiat || copiercoller || pasAssezDeModification) {
				notation.getAttributs().put("note","0");
				String AffichageNote = "";
				if(plagiat) AffichageNote = " Plagiat ";
				if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
				if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
				if(!commandes.noNote) fichier.write("<p><spanpablo>" + AffichageNote +  " / " + noteFrom +"<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			}
		}else {
			String imageNote = "";
			switch (notation.getAttributs().get("noteABC")) {
			case "A":
				imageNote = HTML.NoteA();
				break;
			case "B":
				imageNote = HTML.NoteB();
				break;
			case "C":
				imageNote = HTML.NoteC();
				break;
			case "D":
				imageNote = HTML.NoteD();
				break;
			case "E":
				imageNote = HTML.NoteE();
				break;
			default:
				imageNote = "";
				break;
			}
			if(!commandes.noNote) if(!plagiat&& !copiercoller &&!pasAssezDeModification) fichier.write("<p><spanpablo>" +  imageNote +"<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			if(plagiat || copiercoller || pasAssezDeModification) {
				notation.getAttributs().put("note","0");
				notation.getAttributs().put("noteABC","E");
				String AffichageNote = "";
				if(plagiat) AffichageNote = " Plagiat ";
				if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
				if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
				if(!commandes.noNote) fichier.write("<p><spanpablo>" + AffichageNote + " / " + "<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
				}
			}
		 
		//producteur
 		if(ouvre.getAttributs().get("producteur")!=null) {
 			try {
 				producteur= true;
 				String[] decompose = ouvre.getAttributs().get("producteur").split("/");
 				SuiteBureautique=decompose[0];
 				VersionLibreOffice=decompose[1].substring(0, decompose[1].lastIndexOf("$"));
 				SystemeStudent=decompose[1].substring(decompose[1].lastIndexOf("$")+1, decompose[1].lastIndexOf(" "));
 			}catch (Exception e) {
				System.out.println("Problème avec l'attribut producteur.");
				
			}finally {
				
			}
 		}
		
		//informations
		// date d'analyse, dossier étudiant, auteur sujet, date de la dernière modificatio, lien, algorithme
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		LocalDateTime dateTimeModif = null;
		String dateModif="";
		if(ouvre.getAttributs().get("dateModification")!=null) if(!ouvre.getAttributs().get("dateModification").isEmpty()) {
			try {
				dateTimeModif = LocalDateTime.parse(ouvre.getAttributs().get("dateModification"));
				dateModif = dateTimeModif.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		fichier.write("<h4>Date d'analyse : "+ mediumDateFormat.format(aujourdhui) + "<br>");
	    fichier.write("Dossier étudiant : <span style=\"color:blue\">"+ ouvre.getAttributs().get("dossier") + "</span><br>");
	    fichier.write("Nom du fichier analysé et évalué : <span style=\"color:blue\">"+ ouvre.getAttributs().get("filename") + "</span><br>");
	    fichier.write("Date de la dernière modification du fichier analysé : <span style=\"color:purple\">"+ dateModif + "</span><br>");
	    fichier.write("Durée d'édition du fichier analysé : <span style=\"color:purple\">"+ traitementDureeEdition(ouvre.getAttributs().get("dureeEdition") + "</span><br>"));
	    if(producteur) {
	    	fichier.write("Suite de bureautique : <span style=\"color:coral;\">"+ SuiteBureautique + "</span>");
	  	    fichier.write(" - Version : <span style=\"color:coral;\">"+ VersionLibreOffice + "</span>");
	  	    fichier.write(" - Système : <span style=\"color:coral;\">"+ SystemeStudent + "</span><br>");
	    }
	    if(!auteurSujet.isEmpty()) {fichier.write("Sujet créé par : <span style=\"color:indigo\">"+ auteurSujet + "</span><br>");}else {fichier.write("<br>");}
		
	    if(!commandes.noNote) {
	    	if(!plagiat&&!copiercoller&&!pasAssezDeModification) fichier.write("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : " + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("proportioncorrect") +"<br>");
	    	 if(plagiat || copiercoller || pasAssezDeModification) {
	 			String AffichageNote = "";
	 			if(plagiat) AffichageNote = " Plagiat ";
	 			if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
	 			if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
	 			fichier.write("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : "+ AffichageNote +"<br>");
	 		}
	    }
	        
	    if(baremeABC) {
	    	fichier.append("Barème : <div class=\"tooltip\"><font color=\"#0000ff\">0% → E → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneE"))*100) + "% → D → " +  Math.round(Double.valueOf(notation.getAttributs().get("BorneD"))*100) + "% → C → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneC"))*100) + "% → B → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneB"))*100) + "% → A → 100%</font><span class=\"tooltiptext\">Prendre en compte le coefficient de progression.</span></div>");
	    }
	    
	    if(ouvre.getAttributs().get("link_sujet")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_sujet");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.write("<br><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le sujet</a><br>");}
		}
  	  if(ouvre.getAttributs().get("link_help")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_help");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.write("<br><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le support</a><br>");}
		}
  	    
  	    fichier.write("<br><font color=\"#808080\" style=\"font-size: 9pt\"><i>Analysé avec la version : " + commandes.version + "<br></h4>");
		
		
	    fichier.write(HTML.SautLigne());
	   
	    fichier.write("</div>");
	   
	    //ajoute le menu 
	    fichier.write(HTML.getHTMLmenu(nodana.retourneFirstEnfantsByName("menu").getNodes()));
	   
		
		//Les erreurs
		node errors = nodana.retourneFirstEnfantsByName("erreurs");
	    if(Boolean.valueOf(errors.getAttributs().get("oneError"))) {
	    	fichier.write(HTML.SautLigne());
    		if(Boolean.valueOf(errors.getAttributs().get("manqueHistorique"))) fichier.write(HTML.Paragraph_classp5("ERREUR : Il n'y a pas d'historique des modifications dans ce fichier. Le fichier n'a pas été modifié ou il a été réinitialisé.<br>L'analyse de l'historique n'a pas pu se faire."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueCreationDate"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La date de création du fichier a été supprimée. Le fichier a été réïnitialisé ou ce n'est pas le fichier du sujet."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurCreationDate"))) fichier.write(HTML.Paragraph_classp5("ERREUR : Ce n'est pas la bonne date de création du fichier. Le fichier a été réïnitialisé ou ce n'est pas fichier du sujet."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueMetaSujet"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La méta donnée \"Sujet\" dans les propriétés du fichier a été supprimée ou renommée."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurMetaSujet"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La valeur de la méta donnée \"Sujet\" dans les propriétés du fichier n'est pas \"" + nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet"))+".\"");
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueInitialCreator"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La valeur de la méta donnée \"initial-creator\" dans les propriétés du fichier n'est pas \"" + nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("Initial_Creator"))+".\"");
	    }
	    
	    //plagiat
	    if(plagiat) if(verifStudent!=null){
	    	fichier.append(HTML.SautLigne());
	    	ArrayList<node> correspondance = verifStudent.retourneEnfantsByName("correspondance", new ArrayList<node>());
	    	for(int j = 0 ; j < correspondance.size();j++) {
	    		fichier.write(HTML.Paragraph_classp5("Correspondance à la date=" + correspondance.get(j).getAttributs().get("date") + " avec l'étudiant " + correspondance.get(j).getAttributs().get("Avec_etudiant")));
	    	}
	    }
	    
		
		 fichier.write(HTML.SautLigne());
		 
		 
		 if(!plagiat && !copiercoller && !pasAssezDeModification) {
			 
			 //Ajoute de commentaire de l'exercice
			 fichier.write(HTML.H3(nodana.getContenu()).replace("-NewLine-", "<br>"));
			 
			 fichier.write(HTML.SautLigne());
			 
			    
			 fichier.write(HTML.H2("Synthèse"));
			
			 fichier.write(HTML.TableEnteteTableurSynthese());
			 String IdError = ""; // permet de récupérer les id des menus ou la proportion correct est NaN. (à cause de l'attribut analyseStyle=true)
			 for(int k = 0 ; k < nodana.getNodes().size();k++) {
				 if(nodana.getNodes().get(k).getAttributs().get("addmenu")!=null) if(nodana.getNodes().get(k).getAttributs().get("addmenu").equals("true")) {
				    if(!nodana.getNodes().get(k).getAttributs().get("proportioncorrect").equals("NaN")) {
				    	 fichier.write(HTML.TablePointsSyntheseStyle(nodana.getNodes().get(k).getAttributs().get("titre"),Double.valueOf(nodana.getNodes().get(k).getAttributs().get("proportioncorrect")),nodana.getNodes().get(k).getAttributs().get("pointtotal") + " pt",nodana.getNodes().get(k).getAttributs().get("pointgagner") + " pt", nodana.getNodes().get(k).getAttributs().get("poids"),nodana.getNodes().get(k).getAttributs().get("id")));
				    }else {
				    	IdError = IdError + nodana.getNodes().get(k).getAttributs().get("id");
				    }
				 }
			  }
			
			 
			 //Metadonnées
			 if(nodana.retourneFirstEnfantsByName("meta")!=null) if(nodana.retourneFirstEnfantsByName("meta").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("meta")));
			 }
			 
			 //style de paragraphe
			 if(nodana.retourneFirstEnfantsByName("paragraphs")!=null) if(nodana.retourneFirstEnfantsByName("paragraphs").isClose()) {
				 //il est possible qu'il n'y ai aucun point car passé par analyseStyle dans le node structurepage
				 if(!IdError.contains(nodana.retourneFirstEnfantsByName("paragraphs").getAttributs().get("id"))){
					 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("paragraphs"))); 
				 }
			 }
			 
			 //pages
			 if(nodana.retourneFirstEnfantsByName("pages")!=null) if(nodana.retourneFirstEnfantsByName("pages").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("pages")));
			 }
			 
			 //sequences
			 if(nodana.retourneFirstEnfantsByName("sequences")!=null) if(nodana.retourneFirstEnfantsByName("sequences").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("sequences")));
			 }	 

			 //numerotationchapitre
			 if(nodana.retourneFirstEnfantsByName("numerotationchapitre")!=null) if(nodana.retourneFirstEnfantsByName("numerotationchapitre").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("numerotationchapitre")));
			 }
			 
			 //frames
			 if(nodana.retourneFirstEnfantsByName("frames")!=null) if(nodana.retourneFirstEnfantsByName("frames").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("frames")));
			 }
			 
			//section
			 if(nodana.retourneFirstEnfantsByName("sections")!=null) if(nodana.retourneFirstEnfantsByName("sections").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("sections")));
			 }	
			 
			 //bibliographies
			 if(nodana.retourneFirstEnfantsByName("bibliographies")!=null) if(nodana.retourneFirstEnfantsByName("bibliographies").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("bibliographies")));
			 }		 
			 
			 //tablematieres
			 if(nodana.retourneFirstEnfantsByName("tablematieres")!=null) if(nodana.retourneFirstEnfantsByName("tablematieres").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("tablematieres")));
			 }			

			 //tableillustrations
			 if(nodana.retourneFirstEnfantsByName("tableillustrations")!=null) if(nodana.retourneFirstEnfantsByName("tableillustrations").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("tableillustrations")));
			 }
			 
			 //structurepage
			 if(nodana.retourneFirstEnfantsByName("structurepage")!=null) if(nodana.retourneFirstEnfantsByName("structurepage").isClose()) {
				 fichier.write(HTML.Table(nodana.retourneFirstEnfantsByName("structurepage")));
			 }		
			 
		 }
		 
		 
		 		 
		 fichier.write("<p><br><br></p>");
		 
		 //footer
		 fichier.write("<div class=\"footer\">");
		 fichier.write("<font color=\"#808080\" style=\"font-size: 10pt\"><i>analyseWriter - P. Rodriguez (université d'Artois) - Licence GPL v3.0 - analysé avec la version : " + commandes.version + " - ");
		 fichier.write("Fichier d'analyse créé avec la version : " + ouvre.getAttributs().get("version") + "</i></font>");  
		 fichier.write("</div>");
		
		 fichier.write("<script>");
		   
	   	   fichier.write("window.onscroll = function() {myFunction()};");
		   fichier.write("var navbar = document.getElementById(\"navbar\");");
		   fichier.write("var sticky = navbar.offsetTop;");

		   fichier.write("function myFunction() {");
		   fichier.write("if (window.pageYOffset >= sticky) {");
		   fichier.write("navbar.classList.add(\"sticky\")");
		   fichier.write("} else {");
		   fichier.write("navbar.classList.remove(\"sticky\");");
		   fichier.write("}");
		   fichier.write("}\r\n");
		   
		   
		   fichier.write("function toggleMenu() {\r\n" + 
		   		"  var menuBox0 = document.getElementById('menu-box0');    \r\n" + 
		   		"  var menuBox1 = document.getElementById('menu-box1');    \r\n" + 
		   		"  var menuBox2 = document.getElementById('menu-box2');    \r\n" +
		   		"  var menuBox3 = document.getElementById('menu-box3');    \r\n" +
		   		"  var menuBox4 = document.getElementById('menu-box4');    \r\n" +
		   		"  var menuBox5 = document.getElementById('menu-box5');    \r\n" +
		   		"\r\n"+
		   		"  if(menuBox0.style.display == \"block\") { " + 
		   		"    menuBox0.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox0.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"\r\n"+
		   		"  if(menuBox1.style.display == \"block\") { " + 
		   		"    menuBox1.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox1.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"\r\n"+
		   		"  if(menuBox2.style.display == \"block\") { " + 
		   		"    menuBox2.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox2.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"\r\n"+
		   		"  if(menuBox3.style.display == \"block\") { " + 
		   		"    menuBox3.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox3.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"  if(menuBox4.style.display == \"block\") { " + 
		   		"    menuBox4.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox4.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"  if(menuBox5.style.display == \"block\") { " + 
		   		"    menuBox5.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox5.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"}");
		   
		fichier.write("</script>");
		   
		fichier.write("</body>\r");
		fichier.write("</html>");
			
		fichier.close();
		
		//affichage dans la console
		if(!commandes.fourniDossierDestination) System.out.println("\n\t The feedback file has been written.\n\t " + patch + "\\" + cheminFeedBack);
		if(commandes.fourniDossierDestination) System.out.println("\n\t The feedback file has been written.\n\t " + patch + "\\" + commandes.pathDestination + "\\" + cheminFeedBack);
				
		
 	}

 	
 	/**
 	 * Retourne le nom du fichier de l'étudiant pour le Zip de Moodle.<br>
 	 * </br>
 	 * @param filename
 	 * @param nodana
 	 * @return
 	 */
 	private static String retourneLeNomDuFeedback( String filename,node nodana, node verif) {
 		System.getProperty("file.encoding","UTF-8");
 		
 		int number_match = 2;
 		int mini_modification = 0;
		boolean plagiat = false;
		boolean copiercoller = false;
		boolean pasAssezDeModification =false;
		
		node verifStudent = null;
 		if((commandes.verifHisto||commandes.verifHisto2)) { //&&commandes.ecritNoteCSV&&commandes.fourniCSV
 			if(verif.getAttributs().get("number_match") != null) number_match = Integer.valueOf(verif.getAttributs().get("number_match"));
 			if(verif.getAttributs().get("mini_number_modification") != null) mini_modification = Integer.valueOf(verif.getAttributs().get("mini_number_modification"));
 			
 			
 			//verification du plagiat
			verifStudent = verif.retourneFirstNodeByNameAndAttributValue("fichier", "dossier", nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"));
 			if(verifStudent != null) {
 				if(verifStudent.getAttributs().get("filename").equals(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("filename"))) {
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"))>number_match) plagiat=true;
 					if(!verifStudent.getAttributs().get("first_modification_identique").equals("null") && Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"))>=number_match) plagiat=true;
 					if(verifStudent.getAttributs().get("copier_coller")!=null) copiercoller=true;
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_modification"))<=mini_modification) pasAssezDeModification=true;
 				}
 			}
 		}
 		
 		//nom du fichier feedback
 		String metaS = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet");
 		if(metaS.equals("?")) metaS = "metaSujet-inconnu";
 		if(metaS.isEmpty()) metaS = "metaSujet-inconnu";
 		String cheminFeedBack = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier") +"-"+metaS;  //+ "-DateLong" + aujourdhui.getTime()
  		
 		boolean baremeABC = false;
 		if(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("baremeABC")!=null) {
 			try {
 				baremeABC = Boolean.valueOf(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("baremeABC"));
 			}catch (Exception e) {
				
			}
 		}
 		
 		if(!baremeABC) {
 	 		if(!commandes.noNote) {
 	  			if(!plagiat) cheminFeedBack = cheminFeedBack + "-" + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note") + ".html";
 	  			if(plagiat) cheminFeedBack = cheminFeedBack + "-Plagiat.html";
 	  			if(copiercoller) cheminFeedBack = cheminFeedBack + "-Copier-Coller.html";
 	  			if(pasAssezDeModification) cheminFeedBack = cheminFeedBack + "-Pas assez de modification.html";
 	 		}else {
 	  			cheminFeedBack = cheminFeedBack + ".html";
 	  		}
 		}else {
 			if(!commandes.noNote) {
 	  			if(!plagiat) cheminFeedBack = cheminFeedBack + "-" + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("noteABC") + ".html";
 	  			if(plagiat) cheminFeedBack = cheminFeedBack + "-Plagiat.html";
 	  			if(copiercoller) cheminFeedBack = cheminFeedBack + "-Copier-Coller.html";
 	  			if(pasAssezDeModification) cheminFeedBack = cheminFeedBack + "-Pas assez de modification.html";
 	 		}else {
 	  			cheminFeedBack = cheminFeedBack + ".html";
 	  		}
 		}

 		
 		return filename + cheminFeedBack;
 	}
 	
 	
 	/**
 	 * Ecriture du fichier pour l'archive ZIP de moodle.<br>
 	 * <br>
 	 * @param nodana
 	 * @return
 	 * @throws IOException
 	 */
 	private static StringBuilder feedbackForZip(node nodana, node verif) throws IOException {
 		
 		System.getProperty("file.encoding","UTF-8");
 		Date aujourdhui = new Date();
 		
 		int number_match = 2;
 		int mini_modification = 0;
		boolean plagiat = false;
		boolean copiercoller = false;
		boolean pasAssezDeModification =false;
		boolean baremeABC = false;
		boolean producteur =false;
		String SuiteBureautique="";
		String	VersionLibreOffice="";
		String SystemeStudent="";
		node verifStudent = null;
 		if((commandes.verifHisto||commandes.verifHisto2)) { //&&commandes.ecritNoteCSV&&commandes.fourniCSV
 			if(verif.getAttributs().get("number_match") != null) number_match = Integer.valueOf(verif.getAttributs().get("number_match"));
 			if(verif.getAttributs().get("mini_number_modification") != null) mini_modification = Integer.valueOf(verif.getAttributs().get("mini_number_modification"));
 			
 			//verification du plagiat
			verifStudent = verif.retourneFirstNodeByNameAndAttributValue("fichier", "dossier", nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"));
 			if(verifStudent != null) {
 				if(verifStudent.getAttributs().get("filename").equals(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("filename"))) {
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"))>number_match) plagiat=true;
 					if(!verifStudent.getAttributs().get("first_modification_identique").equals("null") && Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"))>=number_match) plagiat=true;
 					if(verifStudent.getAttributs().get("copier_coller")!=null) copiercoller=true;
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_modification"))<=mini_modification) pasAssezDeModification=true;
 				}
 			}
 		}
 		if(nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("baremeABC")!=null) {
 			try {
 				baremeABC= Boolean.valueOf(nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("baremeABC"));
 			}catch (Exception e) {
 				System.out.println("Problème avec la valeur binaire de l'attribut baremeABC.");
			}
 		}
 		
		
		StringBuilder fichier = new StringBuilder();
		
		//ajoute le chemin vers le feedback dans le node d'analyse
		//nodana.retourneFirstEnfantsByName("ouverture").getAttributs().put("feedback", patch + "/" + cheminFeedBack);
		
		// auteur du sujet
		String auteurSujet = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("auteur");
		if(auteurSujet==null) auteurSujet="";
		
		
		//création du feedback
		fichier.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r"
				+ "<html>\r"
				+ "<head>\r"
				+ "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\r"
				+ "<title>Analyse LibreOffice Calc</title>\r");
		
		fichier.append("<meta name=\"generator\" content=\"AnalyseMEPTL V1.0\"/>"
				+ "<meta name=\"author\" content=\"Pablo Rodriguez\"/>"
				+ "<meta name=\"created\" content=\""+  nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("date") +"\"/>");

		
		fichier.append("<style type=\"text/css\">" 
				+ "p.pablo { margin-bottom: 0.25cm; line-height: 100%; background: transparent;  margin-left: 1cm; }"
				+ ".header {background-color: #f1f1f1;padding: 30px;text-align:center;}"
				+ "h1 { margin-bottom: 0.25cm; background: transparent;}"
				+ "h2 {color: blue;font-size:22pt;margin-bottom: 0cm; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 20pt;  margin-right: 10px; text-decoration: underline overline;}"
				+ "h3 {font-size:18pt;margin-bottom: 0cm; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 20pt;}"
				+ "header.h1.western { font-family: \"Arial\"; font-size: 18pt; font-weight: bold; backgroung: #adff2f;}"
				+ ".header p {color:blue; font-size:30px;}"
				+ ".triche {background: #AA0000;padding-top: 5px;padding-right: 5px;padding-bottom: 5px;padding-left: 5px;width=80%;margin-top:18px; box-shadow: 5px 10px 18px #800000;}"
				+ ".triche p {color:white; font-size:16px;margin-left:10px;margin-bottom:6px;margin-top:6px}"
				+ ".header h4 {text-align:left;font-family: \"Arial\"; font-size: 12pt; font-weight: bold; line-height: 110%;}"
				+ "h4.western { font-family: \"Arial\"; font-size: 14pt; font-style: italic; font-weight: bold; line-height: 40%}"
				+ "a:link { color: #000099; so-language: zxx; text-decoration: underline; margin-left: 10px; }" 
				+ "a:visited { color: #99000; so-language: zxx; text-decoration: underline; margin-left: 10px; }"
				+ "hr { display: block; margin-top: 0.5em; margin-bottom: 8em; margin-left: 2em; margin-right: 2em; border-style: inset; border-width: 4px;}"
				+ "spanpablo { float: right; width: 8em; font-size: 250%; font-family: algerian, courier; line-height: 80%; margin-right: 1%; color: red; text-align: center}"
				+ "p.p1{margin-bottom: 0cm; margin-top: 0cm; line-height: 100%; background: transparent;  margin-left: 0cm; white-space: pre;}"
				+ "p.p8{font-size:14pt;margin-bottom: 0cm; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 8pt;  margin-right: 10px;}"
				+ "p.p9{font-size:16pt;margin-bottom: 12px;text-align: left; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 40pt;  margin-right: 0cm;text-decoration: underline overline wavy blue;text-shadow: 0px 1px #101010;}"
				+ "p.p10{font-size:12pt;margin-bottom: 12px;text-align: left; margin-top: 0cm; line-height: 110%; background: transparent;  margin-left: 30pt;  margin-right: 0cm;text-decoration: underline overline wavy red;}"
				+ ".commentaire{margin-left: 0px; margin-bottom: 24px; margin-top: 24px;font-size:1.4rem}"
				+ "p.p2{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; line-height: 115%}"
				+ "p.p3{margin-left: 20px; line-height: 100%; border: 1px solid black; background-color: lightcyan; margin-right: 10px;  }"
				+ "p.p4{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; margin-right: 4px; line-height: 115%; background: darkblue; color:white; font-size: 20px; white-space: pre;}"
				+ "p.p5{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: red; color:white; font-size: 20px;}"
				+ "p.p6{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: beige; color:darkcyan; font-size: 20px;}"
				+ "p.p7{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: #7FFF00; font-size: 20px;}"
				+ "#navbar {overflow: hidden;background-color: #333;width:100%;box-shadow: 5px 10px 8px #888888;}"
				+ "#navbar a {float: left;display: block;color: #f2f2f2;text-align: center;padding: 14px 16px;text-decoration: none;font-size: 17px;}"
				+ "#navbar a:hover {background-color: #ddd;color: black;}" 
				+ "#navbar a.active {background-color: #4CAF50;color: white;margin-left:0px;}"
				+ "#navbar a.active2 {background-color: #FF8050;color: white;margin-left:0px;}"
				+ "#navbar a.active3 {background-color: #5080FF;color: white;margin-left:0px;}"
				+ "div.sticky {position: fixed;top: 0;width: 100%;}"
				+ ".sticky + .content {padding-top: 60px;}"
				+ "#navbar2 {overflow: hidden; background-color: #333;}"
				+ "#navbar2 a {float: left; font-size: 18px; color: white; text-align: center; padding: 16px 18px; text-decoration: none;}"
				+ ".dropdown {position: relative; display: inline-block;}"
				+ ".dropbtn:hover, .dropbtn:focus { background-color: #3e8e41;}"
				+ ".dropdown-content {display: none; position: absolute; background-color: #f9f9f9; min-width: 160px; box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);}"
				+ ".dropdown-content a {color: black; padding: 12px 16px; text-decoration: none; display: block;}"
				+ ".dropdown-content a:hover {background-color: #f1f1f1}"
				+".show {display:block;}"
				+ "#myDropdown {background-color: #508050;color: black;}"
				+ ".menu-box{display: none;}"
				+ ".menuopen{display: block;}"
				+ ".tooltip {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip .tooltiptext {visibility: hidden;background-color: black;color: #fff;text-align: center;border-radius: 6px;padding: 5px 0;position: absolute;z-index: 1;margin-left: -30px; width: 260px;top: 100%;left: 10%;}"
				+ ".tooltip .tooltiptext::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
				+ ".tooltip:hover .tooltiptext {visibility: visible;}"
				+ ".tooltip1 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip1 .tooltiptext1 {visibility: hidden;background-color: #0000CC;color: #fff;text-align: left;border-radius: 4px;padding: 10px;position: absolute;z-index: 1;margin-left: -40px; width: 280px;top: 100%;left: 10%;}"
				+ ".tooltip1 .tooltiptext1::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent #0000CC transparent;}"
				+ ".tooltip1:hover .tooltiptext1 {visibility: visible;}"
				+ ".tooltip2 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip2 .tooltiptext2 {visibility: hidden;background-color: black;color: #fff;text-align: left;border-radius: 8px;padding: 8px;position: absolute;z-index: 1;margin-left: -40px; width: 340px;top: 100%;left: 10%;}"
				+ ".tooltip2 .tooltiptext2::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
				+ ".tooltip2:hover .tooltiptext2 {visibility: visible;}"
				+ ".footer {position: fixed;left: 0;bottom: 0;width: 100%;background-color: white;color: black;text-align: center;}"
				+"</style>");
		
		fichier.append("</head>\r");
		fichier.append("<body lang=\"fr-FR\" link=\"#000080\" vlink=\"#800000\" dir=\"ltr\">\r");
		
		fichier.append("<div class=\"header\">");
		if(!commandes.noLogo) {
			if(!commandes.newLogo) {
				fichier.append("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
						"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br>"+HTML.imgLogos()+"</font></h1>\r");
			}else {
				
				fichier.append("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
						"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br>"+commandes.contenuFichierSVG+"</font></h1>\r");
			}
		}else {
			fichier.append("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br></font></h1>\r");
		}
		
		//Note
		node ouvre = nodana.retourneFirstEnfantsByName("ouverture");
		String noteFrom = ouvre.getAttributs().get("notefrom");
		node notation = nodana.retourneFirstEnfantsByName("bodyetnotation");
		if(!baremeABC) {
			if(noteFrom ==null) noteFrom="20";
			if(!commandes.noNote) if(!plagiat&&!copiercoller&&!pasAssezDeModification) fichier.append("<p><spanpablo>" +  notation.getAttributs().get("note") + " / " + noteFrom +"<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			if(plagiat || copiercoller || pasAssezDeModification) {
				notation.getAttributs().put("note","0");
				String AffichageNote = "";
				if(plagiat) AffichageNote = " Plagiat ";
				if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
				if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
				if(!commandes.noNote) fichier.append("<p><spanpablo>" + AffichageNote +  " / " + noteFrom +"<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			}
		}else {
			String imageNote = "";
			switch (notation.getAttributs().get("noteABC")) {
			case "A":
				imageNote = HTML.NoteA();
				break;
			case "B":
				imageNote = HTML.NoteB();
				break;
			case "C":
				imageNote = HTML.NoteC();
				break;
			case "D":
				imageNote = HTML.NoteD();
				break;
			case "E":
				imageNote = HTML.NoteE();
				break;
			default:
				imageNote = "";
				break;
			}
			if(!commandes.noNote) if(!plagiat&& !copiercoller &&!pasAssezDeModification) fichier.append("<p><spanpablo>" +  imageNote +"<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			if(plagiat || copiercoller || pasAssezDeModification) {
				notation.getAttributs().put("note","0");
				notation.getAttributs().put("noteABC","E");
				String AffichageNote = "";
				if(plagiat) AffichageNote = " Plagiat ";
				if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
				if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
				if(!commandes.noNote) fichier.append("<p><spanpablo>" + AffichageNote + " / " + "<br><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
			}
		}
		
		
		//producteur
 		if(ouvre.getAttributs().get("producteur")!=null) {
 			try {
 				producteur= true;
 				String[] decompose = ouvre.getAttributs().get("producteur").split("/");
 				SuiteBureautique=decompose[0];
 				VersionLibreOffice=decompose[1].substring(0, decompose[1].lastIndexOf("$"));
 				SystemeStudent=decompose[1].substring(decompose[1].lastIndexOf("$")+1, decompose[1].lastIndexOf(" "));
 			}catch (Exception e) {
				System.out.println("Problème avec l'attribut producteur.");
				
			}finally {
				
			}
 		}
		 
		//informations
		// date d'analyse, dossier étudiant, auteur sujet, date de la dernière modificatio, lien, algorithme
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		LocalDateTime dateTimeModif = null;
		String dateModif="";
		if(!ouvre.getAttributs().get("dateModification").isEmpty()) {
			try {
				dateTimeModif = LocalDateTime.parse(ouvre.getAttributs().get("dateModification"));
				dateModif = dateTimeModif.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
			}catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		fichier.append("<h4>Date d'analyse : "+ mediumDateFormat.format(aujourdhui) + "<br>");
	    fichier.append("Dossier étudiant : <span style=\"color:blue\">"+ ouvre.getAttributs().get("dossier") + "</span><br>");
	    fichier.append("Nom du fichier analysé et évalué : <span style=\"color:blue\">"+ ouvre.getAttributs().get("filename") + "</span><br>");
	    fichier.append("Date de la dernière modification du fichier analysé : <span style=\"color:purple\">"+ dateModif + "</span><br>");
	    fichier.append("Durée d'édition du fichier analysé : <span style=\"color:purple\">"+ traitementDureeEdition(ouvre.getAttributs().get("dureeEdition") + "</span><br>"));
	    if(producteur) {
	    	fichier.append("Suite de bureautique : <span style=\"color:coral;\">"+ SuiteBureautique + "</span>");
	  	    fichier.append(" - Version : <span style=\"color:coral;\">"+ VersionLibreOffice + "</span>");
	  	    fichier.append(" - Système : <span style=\"color:coral;\">"+ SystemeStudent + "</span><br>");
	    }
	    if(!auteurSujet.isEmpty()) {fichier.append("Sujet créé par : <span style=\"color:indigo\">"+ auteurSujet + "</span><br>");}else {fichier.append("<br>");}
		   
	    if(!commandes.noNote) {
	    	if(!plagiat&&!copiercoller&&!pasAssezDeModification) fichier.append("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : " + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("proportioncorrect") +"<br>");
	    	 if(plagiat || copiercoller || pasAssezDeModification) {
	 			String AffichageNote = "";
	 			if(plagiat) AffichageNote = " Plagiat ";
	 			if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
	 			if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
	 			fichier.append("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : "+ AffichageNote +"<br>");
	 		}
	    }
    	    
	    if(baremeABC) {
	    	fichier.append("Barème : <div class=\"tooltip\"><font color=\"#0000ff\">0% → E → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneE"))*100) + "% → D → " +  Math.round(Double.valueOf(notation.getAttributs().get("BorneD"))*100) + "% → C → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneC"))*100) + "% → B → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneB"))*100) + "% → A → 100%</font><span class=\"tooltiptext\">Prendre en compte le coefficient de progression.</span></div>");
	    }
	    
	    
	    if(ouvre.getAttributs().get("link_sujet")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_sujet");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.append("<br><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le sujet</a><br>");}
		}
  	  if(ouvre.getAttributs().get("link_help")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_help");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.append("<br><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le support</a><br>");}
		}
  	    
  	    fichier.append("<br><font color=\"#808080\" style=\"font-size: 9pt\"><i>Analysé avec la version : " + commandes.version + "<br></h4>");
		
		
	    fichier.append(HTML.SautLigne());
	   
	    fichier.append("</div>");
	   
	    //ajoute le menu 
	    fichier.append(HTML.getHTMLmenu(nodana.retourneFirstEnfantsByName("menu").getNodes()));
	   
		
		//Les erreurs
		node errors = nodana.retourneFirstEnfantsByName("erreurs");
	    if(Boolean.valueOf(errors.getAttributs().get("oneError"))) {
	    	fichier.append(HTML.SautLigne());
    		if(Boolean.valueOf(errors.getAttributs().get("manqueHistorique"))) fichier.append(HTML.Paragraph_classp5("ERREUR : Il n'y a pas d'historique des modifications dans ce fichier. Le fichier n'a pas été modifié ou il a été réïnitialisé.<br>L'analyse de l'historique n'a pas pu se faire."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueCreationDate"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La date de création du fichier a été supprimée. Le fichier a été réïnitialisé ou ce n'est pas le fichier du sujet."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurCreationDate"))) fichier.append(HTML.Paragraph_classp5("ERREUR : Ce n'est pas la bonne date de création du fichier. Le fichier a été réïnitialisé ou ce n'est pas le fichier du sujet."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueMetaSujet"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La méta donnée \"Sujet\" dans les propriétés du fichier a été supprimée ou renommée."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurMetaSujet"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La valeur de la méta donnée \"Sujet\" dans les propriétés du fichier n'est pas \"" + nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet"))+".\"");
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueInitialCreator"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La valeur de la méta donnée \"initial-creator\" dans les propriétés du fichier n'est pas \"" + nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("Initial_Creator"))+".\"");   
	    }
	    
	    //plagiat
	    if(plagiat) if(verifStudent!=null){
	    	fichier.append(HTML.SautLigne());
	    	ArrayList<node> correspondance = verifStudent.retourneEnfantsByName("correspondance", new ArrayList<node>());
	    	for(int j = 0 ; j < correspondance.size();j++) {
	    		fichier.append(HTML.Paragraph_classp5("Correspondance à la date=" + correspondance.get(j).getAttributs().get("date") + " avec l'étudiant " + correspondance.get(j).getAttributs().get("Avec_etudiant")));
	    	}
	    }
	    
	    
	    fichier.append(HTML.SautLigne());
	    
		if(!plagiat && !copiercoller && !pasAssezDeModification) {
			
			 //Ajoute de commentaire de l'exercice
			 fichier.append(HTML.H3(nodana.getContenu()).replace("-NewLine-", "<br>"));
			 
			 fichier.append(HTML.SautLigne());
			 
			    
			 fichier.append(HTML.H2("Synthèse"));
			
			 fichier.append(HTML.TableEnteteTableurSynthese());
			 String IdError = ""; // permet de récupérer les id des menu ou la proportioncorrect est NaN. (à cause de l'attribut analyseStyle=true)
			 for(int k = 0 ; k < nodana.getNodes().size();k++) {
				 if(nodana.getNodes().get(k).getAttributs().get("addmenu")!=null) if(nodana.getNodes().get(k).getAttributs().get("addmenu").equals("true")) {
				    if(!nodana.getNodes().get(k).getAttributs().get("proportioncorrect").equals("NaN")) {
				    	 fichier.append(HTML.TablePointsSyntheseStyle(nodana.getNodes().get(k).getAttributs().get("titre"),Double.valueOf(nodana.getNodes().get(k).getAttributs().get("proportioncorrect")),nodana.getNodes().get(k).getAttributs().get("pointtotal") + " pt",nodana.getNodes().get(k).getAttributs().get("pointgagner") + " pt", nodana.getNodes().get(k).getAttributs().get("poids"),nodana.getNodes().get(k).getAttributs().get("id")));
				    }else {
				    	IdError = IdError + nodana.getNodes().get(k).getAttributs().get("id");
				    }
				 }
			  }
			
			 
			 //Metadonnées
			 if(nodana.retourneFirstEnfantsByName("meta")!=null) if(nodana.retourneFirstEnfantsByName("meta").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("meta")));
			 }
			 
			 //style de paragraphe
			 if(nodana.retourneFirstEnfantsByName("paragraphs")!=null) if(nodana.retourneFirstEnfantsByName("paragraphs").isClose()) {
				 //il est possible qu'il n'y ai aucun point car passé par analyseStyle dans le node structurepage
				 if(!IdError.contains(nodana.retourneFirstEnfantsByName("paragraphs").getAttributs().get("id"))){
					 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("paragraphs"))); 
				 }
			 }
			 
			 //pages
			 if(nodana.retourneFirstEnfantsByName("pages")!=null) if(nodana.retourneFirstEnfantsByName("pages").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("pages")));
			 }
			 
			 //sequences
			 if(nodana.retourneFirstEnfantsByName("sequences")!=null) if(nodana.retourneFirstEnfantsByName("sequences").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("sequences")));
			 }	 

			 //numerotationchapitre
			 if(nodana.retourneFirstEnfantsByName("numerotationchapitre")!=null) if(nodana.retourneFirstEnfantsByName("numerotationchapitre").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("numerotationchapitre")));
			 }
			 
			 //frames
			 if(nodana.retourneFirstEnfantsByName("frames")!=null) if(nodana.retourneFirstEnfantsByName("frames").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("frames")));
			 }	 
			 
			 //bibliographies
			 if(nodana.retourneFirstEnfantsByName("bibliographies")!=null) if(nodana.retourneFirstEnfantsByName("bibliographies").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("bibliographies")));
			 }		 
			 
			 //tablematieres
			 if(nodana.retourneFirstEnfantsByName("tablematieres")!=null) if(nodana.retourneFirstEnfantsByName("tablematieres").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("tablematieres")));
			 }			

			 //tableillustrations
			 if(nodana.retourneFirstEnfantsByName("tableillustrations")!=null) if(nodana.retourneFirstEnfantsByName("tableillustrations").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("tableillustrations")));
			 }
			 
			 //structurepage
			 if(nodana.retourneFirstEnfantsByName("structurepage")!=null) if(nodana.retourneFirstEnfantsByName("structurepage").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("structurepage")));
			 }		 
			 
			 fichier.append("<p><br><br></p>");
		}
		
		 
		
		 
		 //footer
		 fichier.append("<div class=\"footer\">");
		 fichier.append("<font color=\"#808080\" style=\"font-size: 10pt\"><i>analyseWriter - P. Rodriguez (université d'Artois) - Licence GPL v3.0 - analysé avec la version : " + commandes.version + " - ");
		 fichier.append("Fichier d'analyse créé avec la version : " + ouvre.getAttributs().get("version") + "</i></font>");  
		 fichier.append("</div>");
		
		 fichier.append("<script>");
		   
	   	   fichier.append("window.onscroll = function() {myFunction()};");
		   fichier.append("var navbar = document.getElementById(\"navbar\");");
		   fichier.append("var sticky = navbar.offsetTop;");

		   fichier.append("function myFunction() {");
		   fichier.append("if (window.pageYOffset >= sticky) {");
		   fichier.append("navbar.classList.add(\"sticky\")");
		   fichier.append("} else {");
		   fichier.append("navbar.classList.remove(\"sticky\");");
		   fichier.append("}");
		   fichier.append("}\r\n");
		   
		   
		   fichier.append("function toggleMenu() {\r\n" + 
		   		"  var menuBox0 = document.getElementById('menu-box0');    \r\n" + 
		   		"  var menuBox1 = document.getElementById('menu-box1');    \r\n" + 
		   		"  var menuBox2 = document.getElementById('menu-box2');    \r\n" +
		   		"  var menuBox3 = document.getElementById('menu-box3');    \r\n" +
		   		"  var menuBox4 = document.getElementById('menu-box4');    \r\n" +
		   		"  var menuBox5 = document.getElementById('menu-box5');    \r\n" +
		   		"\r\n"+
		   		"  if(menuBox0.style.display == \"block\") { " + 
		   		"    menuBox0.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox0.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"\r\n"+
		   		"  if(menuBox1.style.display == \"block\") { " + 
		   		"    menuBox1.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox1.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"\r\n"+
		   		"  if(menuBox2.style.display == \"block\") { " + 
		   		"    menuBox2.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox2.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"\r\n"+
		   		"  if(menuBox3.style.display == \"block\") { " + 
		   		"    menuBox3.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox3.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"  if(menuBox4.style.display == \"block\") { " + 
		   		"    menuBox4.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox4.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"  if(menuBox5.style.display == \"block\") { " + 
		   		"    menuBox5.style.display = \"none\";\r\n" + 
		   		"  }\r\n" + 
		   		"  else {" + 
		   		"    menuBox5.style.display = \"block\";\r\n" + 
		   		"  }\r\n" +
		   		"}");
		   
		fichier.append("</script>");
		   
		fichier.append("</body>\r");
		fichier.append("</html>");
			
		
		//affichage dans la console
//		if(!commandes.fourniDossierDestination) System.out.println("\n\t The feedback file has been written.\n\t " + patch + "\\" + cheminFeedBack);
//		if(commandes.fourniDossierDestination) System.out.println("\n\t The feedback file has been written.\n\t " + patch + "\\" + commandes.pathDestination + "\\" + cheminFeedBack);
				
		return fichier;
 	}

 	
 	
 	/**
 	 * Affichage uniquement dans la console des erreurs
 	 * @param nod
 	 */
 	private static void messageSystem(node nod) {
 		node ouverture = nod.retourneFirstEnfantsByName("ouverture");
 		node notation = nod.retourneFirstEnfantsByName("bodyetnotation");
 		node erreurs = nod.retourneFirstEnfantsByName("erreurs");
 		boolean flagError = Boolean.valueOf(erreurs.getAttributs().get("oneError"));
 		
 		System.out.println("\t Folder analyzed : " + ouverture.getAttributs().get("dossier"));
 		if(notation.getAttributs().get("baremeABC").equals("true")) {
 			System.out.println("\t Grade : " +  notation.getAttributs().get("noteABC"));
 		}else {
 			System.out.println("\t Grade : " +  notation.getAttributs().get("note") + "/" + ouverture.getAttributs().get("notefrom"));
 		}
		
		if(flagError) {
			System.out.println("\t ERROR in student's file.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueHistorique"))) System.out.println("\t ERROR : There is no historic in the file. Perhaps, the file has not been modified or it has been reset by the student.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueCreationDate"))) System.out.println("\t ERROR : This is the wrong file creation date. The file has been reset or it is not the correct file.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueValeurCreationDate"))) System.out.println("\t ERROR : This is the wrong file creation date.");
			
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueMetaSujet"))) System.out.println("\t ERROR : The metadata \"Sujet\" has been deleted in the student's file. It is impossible to identify the exercise.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueValeurMetaSujet"))) System.out.println("\t ERROR : The metadata value of  \"Sujet \" in the student's file is not. \"" + ouverture.getAttributs().get("metaSujet")+"\"");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueInitialCreator"))) System.out.println("\t ERROR : The initial creator value in the student's file is wrong. \"" + ouverture.getAttributs().get("metaSujet")+"\"");
		}
		System.out.println();
 	}

	
	/**
	 * Place au node le contenu saut de ligne<br>
	 * Et place un titre1, ou titre2, ou titre3
	 * <br>
	 * @param nod
	 * @return
	 */
	private static node addNodeSautTitre(node nodSujet, node nodanalyse) {
		if(nodSujet.getAttributs().get("saut")!=null) if(Boolean.valueOf(nodSujet.getAttributs().get("saut"))) {
			if(nodSujet.getAttributs().get("titre")!=null) {
				node N = new node();
				N.setNomElt("saut");
				N.getAttributs().put("titre", nodSujet.getAttributs().get("titre"));
				N.setClose(true);
				nodanalyse.getNodes().add(N);
			}
			if(nodSujet.getAttributs().get("titre2")!=null) {
				node N = new node();
				N.setNomElt("saut");
				N.getAttributs().put("titre2", nodSujet.getAttributs().get("titre2"));
				N.setClose(true);
				nodanalyse.getNodes().add(N);
			}
			if(nodSujet.getAttributs().get("titre3")!=null) {
				node N = new node();
				N.setNomElt("saut");
				N.getAttributs().put("titre3", nodSujet.getAttributs().get("titre3"));
				N.setClose(true);
				nodanalyse.getNodes().add(N);
			}
		}
		return nodanalyse;	
	}
	
	/**
	 * Vérirication des historiques
	 * @param verification
	 * @param a
	 * @return le node verif
	 */
	private static node verificationHistorique(node verification, Run a) {
		
		
		node verif = new node();
		verif.setNomElt("verification");
		verif.setAttributs(verification.getAttributs());
		verif.getAttributs().put("number_match", String.valueOf(commandes.number_match));
		verif.getAttributs().put("mini_number_modification", String.valueOf(commandes.mini_number_modification));
		verif.getAttributs().put("nombres_modifications_simultané_maxi", String.valueOf(commandes.nombres_modifications_simultané_maxi));
		
		ArrayList<node> LesFichiers = verification.retourneLesEnfantsByName("fichier", new ArrayList<node>());
		
		for(int i = 0 ; i < LesFichiers.size() ; i++) {
			node nodStudent = new node();
			nodStudent.setNomElt("fichier");
			nodStudent.setAttributs(LesFichiers.get(i).getAttributs());
			
			//String nameStudent1 = LesFichiers.get(i).getAttributs().get("dossier");
			
			ArrayList<node> HitoriqueDuFichier = LesFichiers.get(i).retourneEnfantsByName("text:changed-region", new ArrayList<node>());
			int nombreModifications = HitoriqueDuFichier.size();
			
			nodStudent.getAttributs().put("nombre_modification", String.valueOf(nombreModifications));
			
			int compteurnombreCorrespondance = 0 ;
			int compteurnombreCorrespondancesSuivi = 0;
			int compteurnombreCorrespondancesconsecutive = 0;
			
			System.out.println(LesFichiers.get(i).getAttributs().get("dossier") + " -  number of modifications : " + nombreModifications);
			
			@SuppressWarnings("unchecked")
			ArrayList<node> LesFichiers2 = (ArrayList<node>) LesFichiers.clone();
			LesFichiers2.remove(i);
			Dictionary<String, Integer> lesdates = new Hashtable<String, Integer>();
			
			Date DateMini = null;  //Première date de modification
			
			// Parcours toutes les modifications dans l'historique
			for(int j = 0 ; j < HitoriqueDuFichier.size(); j++) {
				
				String dcdate1 = HitoriqueDuFichier.get(j).retourneFirstEnfantsByName("dc:date").getContenu();
				String dccreator = HitoriqueDuFichier.get(j).retourneFirstEnfantsByName("dc:creator").getContenu();
				
				Date DcDate1 = DateLibreOffice(dcdate1);
				
				if(DateMini==null) DateMini = DcDate1;  // Amorçage de la date premère date de modification
				if(DateMini!=null && DateMini.after(DcDate1)) DateMini = DcDate1;  //recherche la première date de modification
				
				
				if(lesdates.get(dcdate1)==null) {
					lesdates.put(dcdate1, 1);
				}else {
					int compteur = lesdates.get(dcdate1) + 1;
					lesdates.put(dcdate1, compteur);
				}
				
				
				node N1 = HitoriqueDuFichier.get(j).getNodes().get(0);
				
				
				for(int i2 = 0 ; i2 < LesFichiers2.size(); i2++) {
					String nameStudent2 = LesFichiers2.get(i2).getAttributs().get("dossier");
					ArrayList<node> HitoriqueDuFichier2 = LesFichiers2.get(i2).retourneEnfantsByName("text:changed-region", new ArrayList<node>());
					for(int j2 = 0 ; j2 <HitoriqueDuFichier2.size(); j2++ ) {
						String dcdate2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName("dc:date").getContenu();
						String dccreator2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName("dc:creator").getContenu();
						node N2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName(N1.getNomElt());
						
						if(a.equalNode(N1, N2)) {  //Verification des deux nodes de l'historique identiques
							if(dcdate1.equals(dcdate2) && N1.getNomElt().equals(N2.getNomElt()) && dccreator.equals(dccreator2)) {  //cette ligne est redondante
								
								if(nodStudent.retourneFirstNodeByNameAndAttributValueExactStrict("correspondance", "date", dcdate2)==null) {
									//affinage du match
									compteurnombreCorrespondance++;
									if(compteurnombreCorrespondancesSuivi==0) {
										compteurnombreCorrespondancesSuivi++;
									}else {
										ArrayList<node> correspondances = nodStudent.retourneEnfantsByName("correspondance", new ArrayList<node>());
										node c = correspondances.get(correspondances.size()-1);
										if(c.getAttributs().get("Avec_etudiant").equals(nameStudent2)&&c.getAttributs().get("dc:creator").equals(dccreator2)) {
											compteurnombreCorrespondancesSuivi++;
											if(compteurnombreCorrespondancesconsecutive+1>compteurnombreCorrespondancesconsecutive) compteurnombreCorrespondancesconsecutive++;
										}else {
											compteurnombreCorrespondancesSuivi=0;
										}
									}
	
									node correspondance = new node();
									correspondance.getNodes().add(N2);
									
									correspondance.setNomElt("correspondance");
									correspondance.getAttributs().put("date", dcdate1);
									correspondance.getAttributs().put("type",N1.getNomElt());
									correspondance.getAttributs().put("Avec_etudiant", nameStudent2);
									correspondance.getAttributs().put("dc:creator", dccreator2);
									nodStudent.getNodes().add(correspondance);
									System.out.println("\t** Find a match ** " + dcdate1);
									break;
								}
							}
						}
					}					
				}
				
			}
			
			Enumeration<String> key = lesdates.keys();
			while(key.hasMoreElements()) {
				String k = key.nextElement();
				if(lesdates.get(k)>1) {
					node modificationsMemeDate = new node();
					modificationsMemeDate.setNomElt("memeinstant");
					modificationsMemeDate.getAttributs().put("date", k);
					modificationsMemeDate.getAttributs().put("nombres_modifications_simultané", String.valueOf(lesdates.get(k)));
					if(commandes.nombres_modifications_simultané_maxi<lesdates.get(k)) {
						modificationsMemeDate.getAttributs().put("copier_coller", "true");
						nodStudent.getAttributs().put("copier_coller", "true");
					}else {
						modificationsMemeDate.getAttributs().put("copier_coller", "false");
					}
					nodStudent.getNodes().add(modificationsMemeDate);
				}				
			}
			
			node modificationsMemeDate = new node();
			modificationsMemeDate.setNomElt("memeinstant");
			
			
			
			if(compteurnombreCorrespondancesconsecutive>0)compteurnombreCorrespondancesconsecutive++; //C'est histoire des arbres et des intervalles entre les arbres. Il faut ajouter plus 1 au nombre de correspondances consecutives.
			
			nodStudent.getAttributs().put("nombre_correspondance", String.valueOf(compteurnombreCorrespondance));
			nodStudent.getAttributs().put("nombre_modifications_date_unique", String.valueOf(lesdates.size()));
			nodStudent.getAttributs().put("nombre_correspondances_consecutives", String.valueOf(compteurnombreCorrespondancesconsecutive));
			nodStudent.getAttributs().put("first_modification_date", String.valueOf(DateMini));

			verif.getNodes().add(nodStudent);
			System.out.println("\tDate de modification date unique " + String.valueOf(lesdates.size()));
			
		}
		
		// Ci-dessous le code pour vérifier la première date de modification dans le fichier.
		// Recherche dans le node verif
		int lastIndex = verif.getNodes().size();
		for(int i = 0 ; i < lastIndex;i++) {
			String D1 = verif.getNodes().get(i).getAttributs().get("first_modification_date");
			boolean trouve =false;
			for(int j = 0; j < lastIndex; j++) {
				if(i!=j) {
					String D2 = verif.getNodes().get(j).getAttributs().get("first_modification_date");
					if(!D1.equals("null") && D1.equals(D2)) {
						if(verif.getNodes().get(i).getAttributs().get("")==null) {
							verif.getNodes().get(i).getAttributs().put("first_modification_identique", verif.getNodes().get(j).getAttributs().get("dossier"));
						}else {
							verif.getNodes().get(i).getAttributs().put("first_modification_identique_2", verif.getNodes().get(j).getAttributs().get("dossier"));
						}
						if(verif.getNodes().get(j).getAttributs().get("")==null) {
							verif.getNodes().get(j).getAttributs().put("first_modification_identique", verif.getNodes().get(i).getAttributs().get("dossier"));
						}else {
							verif.getNodes().get(j).getAttributs().put("first_modification_identique_2", verif.getNodes().get(i).getAttributs().get("dossier"));
						}
						trouve=true;
					}
				}
			}
			if(!trouve) {
				verif.getNodes().get(i).getAttributs().put("first_modification_identique","null");
			}else {
				verif.getNodes().get(i).getAttributs().put("plagiat", "probable");
				System.out.println("\t\t ************************");
				System.out.println("\t\t **  Plagiat PROBABLE  **");
				System.out.println("\t\t ************************");
			}
		}
		
		return verif;
		
	}
	
	/**
	 * Ecriture de l'ensemble des notes brutes sans vérification et sans rechercher les identifiants des étudiants
	 * @param nodesana
	 * @throws IOException
	 */
	private static void ecritureCSV(node ana) throws IOException{
		Date aujourdhui = new Date();
		Path outputFilePath = Paths.get(patch + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(patch +"/"+ commandes.pathDestination + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
			
		if(!commandes.fourniDossierDestination) System.out.println(patch +"\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) System.out.println(patch +"\\"+ commandes.pathDestination + "\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		BufferedWriter  fichier = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
		fichier.write("prénom nom;date modification;producteur;durée edition;sujet;note\n");
		
		
		for (int i = 0 ; i < ana.getNodes().size() ; i++) {
			node nodouverture =  ana.getNodes().get(i).retourneFirstEnfantsByName("ouverture");
			String identification = nodouverture.getAttributs().get("dossier");
			String sujet = nodouverture.getAttributs().get("metaSujet");
			String dateModif = nodouverture.getAttributs().get("dateModification");
			String producteur = nodouverture.getAttributs().get("producteur");
			String dureeEdition = nodouverture.getAttributs().get("dureeEdition");
			String note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note");
			if(nodouverture.getAttributs().get("baremeABC")!=null) {
				try {
					if(Boolean.valueOf(nodouverture.getAttributs().get("baremeABC"))) {
						note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("noteABC");
					}
				}catch (Exception e) {
					
				}
			}
			fichier.write(identification + ";" + dateModif + ";" + producteur + ";" + traitementDureeEdition(dureeEdition) + ";"+ sujet + ";" + traitementNote(note) + "\n");
			
		}
		fichier.close();
	}
	
	/**
	 * Ecriture du fichier CSV avec vérification.<br>
	 * Mais sans rechercher les identifiants des étudiants.
	 * @param ana
	 * @param verif
	 * @param a
	 * @throws IOException
	 */
	private static void ecritureCSV(node ana, node verif, Run a, node setting) throws IOException{
		String separator =";"; //valeur par défaut du séparteur
		
	
		Date aujourdhui = new Date();
		Path outputFilePath = Paths.get(patch + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(patch +"/"+ commandes.pathDestination + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		if(!commandes.fourniDossierDestination) System.out.println(patch +"\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) System.out.println(patch +"\\"+ commandes.pathDestination + "\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		
		
		BufferedWriter  fichier = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
		fichier.write("prénom nom;date modification;producteur;durée edition;sujet;nbr modification;nbr modification date unique;nbr match;note\n");
		
		for (int i = 0 ; i < ana.getNodes().size() ; i++) {
			node nodouverture =  ana.getNodes().get(i).retourneFirstEnfantsByName("ouverture");
			String identification = nodouverture.getAttributs().get("dossier");
			String sujet = nodouverture.getAttributs().get("metaSujet");
			String dateModif = nodouverture.getAttributs().get("dateModification");
			String producteur = nodouverture.getAttributs().get("producteur");
			String dureeEdition = nodouverture.getAttributs().get("dureeEdition");
			
			node verifStudent = a.retourneNodeByNameAttributValueAttributValueExact(verif, "fichier", "dossier", identification, "dateModification", dateModif);
			String note = "plagiat";
			if(verifStudent!=null) {
				int nbreCorrespondance = Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondance"));
				int nbrDateModificationUnique = Integer.valueOf(verifStudent.getAttributs().get("nombre_modifications_date_unique"));
				
				if(nbreCorrespondance<=commandes.number_match) {
					note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note");
					if(nodouverture.getAttributs().get("baremeABC")!=null) {
						try {
							if(Boolean.valueOf(nodouverture.getAttributs().get("baremeABC"))) {
								note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("noteABC");
							}
						}catch (Exception e) {
							
						}
					}
				}
				if(nbrDateModificationUnique<commandes.mini_number_modification) {
					note = note + separator + "copy";
				}
				
				String nbrModification = verifStudent.getAttributs().get("nombre_modification");
				String nombre_modifications_date_unique = verifStudent.getAttributs().get("nombre_modifications_date_unique");
				
				
				fichier.write(identification + separator + dateModif + separator + producteur + separator + traitementDureeEdition(dureeEdition) + separator + sujet + separator + nbrModification + separator + nombre_modifications_date_unique + separator + nbreCorrespondance + separator + traitementNote(note) + "\n");
			}

		}
		fichier.close();
	}
	
	
	/**
	 * Ecriture du fichier CSV avec ou sans vérification.<br>
	 * Mais avec rechercher des identifiants des étudiants.<br>
	 * @param ana
	 * @param verif
	 * @param a
	 * @param nodeCVS
	 * @param verification
	 * @throws IOException
	 */
	private static void ecritureCSV(node ana, node verif, Run a, node nodeCVS, node setting) throws IOException {
		
		String separator =";"; //valeur par défaut du séparteur
		Charset encoding = StandardCharsets.UTF_8; //valeur par défaut
		String champMoodleEmail = "adresse";
		String champMoodleNumeroEtudiant = "identification";
		String champPrenom = "prenom";
		String champNom= "nom";
		
		if(setting.getNomElt().equals("setting")) {
			if(setting.containElementByName("csv")){
				node csv = setting.retourneFirstEnfantsByName("csv");
				if(csv.getAttributs().get("separator")!=null)separator = csv.getAttributs().get("separator");
				if(csv.getAttributs().get("encoding")!=null) {
					if(csv.getAttributs().get("encoding").equals("UFT-8")) encoding = StandardCharsets.UTF_8;
					if(csv.getAttributs().get("encoding").equals("ISO-8859-1")) encoding = StandardCharsets.ISO_8859_1;
					if(csv.getAttributs().get("encoding").equals("US-ASCII")) encoding = StandardCharsets.US_ASCII;
					if(csv.getAttributs().get("encoding").equals("UTF-16")) encoding = StandardCharsets.UTF_16;
					if(csv.getAttributs().get("encoding").equals("UTF-16BE")) encoding = StandardCharsets.UTF_16BE;
					if(csv.getAttributs().get("encoding").equals("UTF-16LE")) encoding = StandardCharsets.UTF_16LE;
					if(csv.containElementByName("import_moodle")) {
						node import_moodle = csv.retourneFirstEnfantsByName("import_moodle");
						if(import_moodle.getAttributs().get("email")!=null) champMoodleEmail=import_moodle.getAttributs().get("email");
						if(import_moodle.getAttributs().get("id")!=null) champMoodleNumeroEtudiant=import_moodle.getAttributs().get("id");
						if(import_moodle.getAttributs().get("firstname")!=null) champPrenom=import_moodle.getAttributs().get("firstname");
						if(import_moodle.getAttributs().get("name")!=null) champNom=import_moodle.getAttributs().get("name");
					}
				}
			}
		}
		
		
		
		Date aujourdhui = new Date();
		Path outputFilePath = Paths.get(patch + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(patch +"/"+ commandes.pathDestination + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		if(!commandes.fourniDossierDestination) System.out.println(patch +"\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) System.out.println(patch +"\\"+ commandes.pathDestination + "\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		BufferedWriter  fichier = Files.newBufferedWriter(outputFilePath, encoding);
		fichier.write("prénom nom"+separator+"email"+separator+"identifiant"+separator+"date modification"+separator+"producteur"+separator+"durée edition"+separator+"sujet"+separator+"nbr modification" + separator + "nbr modifications date unique" + separator+ "nbr match consecutif" + separator +"note" + separator +"commentaire\n");
		
		for (int i = 0 ; i < ana.getNodes().size() ; i++) {
			node nodouverture =  ana.getNodes().get(i).retourneFirstEnfantsByName("ouverture");
			node bodyetnotation = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation");
			String identification = nodouverture.getAttributs().get("dossier");
			String sujet = nodouverture.getAttributs().get("metaSujet");
			String dateModif = nodouverture.getAttributs().get("dateModification");
			String producteur = nodouverture.getAttributs().get("producteur");
			String dureeEdition = nodouverture.getAttributs().get("dureeEdition");
			String mail = "-";
			String numeroEtudiant = "-";
			
			node verifStudent = null;
			
			if(commandes.verifHisto2) verifStudent = a.retourneNodeByNameAttributValueAttributValueExact(verif, "fichier", "dossier", identification, "dateModification", dateModif);
			String note = "";
			
			//** rechercher les correspondances avec le prénom et le nom de l'étudiant
			String[] ident = identification.split(" "); //séparateur entre le prénom et le nom par un espace
			node A=null;
			if(ident.length==2) {
				A = a.retourneNodeByNameAttributValueAttributValueExact(nodeCVS, "student", champPrenom, ident[0], champNom, ident[1]);
				if(A!=null) {
					mail = A.getAttributs().get("\"" + champMoodleEmail + "\"");
					if(mail==null) mail = A.getAttributs().get("'"+ champMoodleEmail +"'");
					if(mail==null) mail = A.getAttributs().get(champMoodleEmail);
					numeroEtudiant = A.getAttributs().get("\"" + champMoodleNumeroEtudiant + "\"");
					if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get("'" + champMoodleNumeroEtudiant + "'");
					if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get(champMoodleNumeroEtudiant);
				}
			}
			if(ident.length==3) {
				A = a.retourneNodeByNameAttributValueAttributValueExact(nodeCVS, "student", champPrenom, "\"" + ident[0] + " " + ident[1] +"\"", champNom, ident[2]);
				if(A!=null) {
					mail = A.getAttributs().get("\"" + champMoodleEmail + "\"");
					if(mail==null) mail = A.getAttributs().get("'"+ champMoodleEmail +"'");
					if(mail==null) mail = A.getAttributs().get(champMoodleEmail);
					numeroEtudiant = A.getAttributs().get("\"" + champMoodleNumeroEtudiant + "\"");
					if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get("'" + champMoodleNumeroEtudiant + "'");
					if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get(champMoodleNumeroEtudiant);
				} 
				if(A==null) {
					A = a.retourneNodeByNameAttributValueAttributValueExact(nodeCVS, "student", champPrenom, ident[0], champNom, "\"" +ident[1] + " " + ident[2] + "\"");
					if(A!=null) {
						mail = A.getAttributs().get("\"" + champMoodleEmail + "\"");
						if(mail==null) mail = A.getAttributs().get("'"+ champMoodleEmail +"'");
						if(mail==null) mail = A.getAttributs().get(champMoodleEmail);
						numeroEtudiant = A.getAttributs().get("\"" + champMoodleNumeroEtudiant + "\"");
						if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get("'" + champMoodleNumeroEtudiant + "'");
						if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get(champMoodleNumeroEtudiant);
					}
				}
			}
			if(ident.length==4) {
				A = a.retourneNodeByNameAttributValueAttributValueExact(nodeCVS, "student", champPrenom, ident[0], champNom, "\"" + ident[1] + " " + ident[2] + " " + ident[3] + "\"");
				if(A!=null) {
					mail = A.getAttributs().get("\"" + champMoodleEmail + "\"");
					if(mail==null) mail = A.getAttributs().get("'"+ champMoodleEmail +"'");
					if(mail==null) mail = A.getAttributs().get(champMoodleEmail);
					numeroEtudiant = A.getAttributs().get("\"" + champMoodleNumeroEtudiant + "\"");
					if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get("'" + champMoodleNumeroEtudiant + "'");
					if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get(champMoodleNumeroEtudiant);
				} 
				if(A==null) {
					A = a.retourneNodeByNameAttributValueAttributValueExact(nodeCVS, "student", champPrenom, "\"" + ident[0] + " " + ident[1] +"\"", champNom, "\"" + ident[2] + " " + ident[3] + "\"");
					if(A!=null) {
						mail = A.getAttributs().get("\"" + champMoodleEmail + "\"");
						if(mail==null) mail = A.getAttributs().get("'"+ champMoodleEmail +"'");
						if(mail==null) mail = A.getAttributs().get(champMoodleEmail);
						numeroEtudiant = A.getAttributs().get("\"" + champMoodleNumeroEtudiant + "\"");
						if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get("'" + champMoodleNumeroEtudiant + "'");
						if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get(champMoodleNumeroEtudiant);
					}
				}
				if(A==null) {
					A = a.retourneNodeByNameAttributValueAttributValueExact(nodeCVS, "student", champPrenom, "\"" + ident[0] + " " + ident[1] + ident[2] + "\"", champNom, ident[3]);
					if(A!=null) {
						mail = A.getAttributs().get("\"" + champMoodleEmail + "\"");
						if(mail==null) mail = A.getAttributs().get("'"+ champMoodleEmail +"'");
						if(mail==null) mail = A.getAttributs().get(champMoodleEmail);
						numeroEtudiant = A.getAttributs().get("\"" + champMoodleNumeroEtudiant + "\"");
						if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get("'" + champMoodleNumeroEtudiant + "'");
						if(numeroEtudiant==null) numeroEtudiant = A.getAttributs().get(champMoodleNumeroEtudiant);
					}
				}
			}
			
			
			if(commandes.verifHisto2) if(verifStudent!=null) {
				int nbreCorrespondanceConsecutive = Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondances_consecutives"));
				int nbrDateModificationUnique = Integer.valueOf(verifStudent.getAttributs().get("nombre_modifications_date_unique"));
				note = bodyetnotation.getAttributs().get("note");
				if(bodyetnotation.getAttributs().get("baremeABC")!=null) {
					try {
						if(Boolean.valueOf(bodyetnotation.getAttributs().get("baremeABC"))) {
							note = bodyetnotation.getAttributs().get("noteABC");
						}
					}catch (Exception e) {
						
					}
				}
				if(nbreCorrespondanceConsecutive>commandes.number_match) {
					bodyetnotation.getAttributs().put("commentaire","Echange de fichier - plagiat");
					note = "0" ;
				}
				if(nbrDateModificationUnique<=commandes.mini_number_modification) {
					bodyetnotation.getAttributs().put("commentaire","pas assez de modification.");
					note="0";
				}
				if(!verifStudent.getAttributs().get("first_modification_identique").equals("null") && nbreCorrespondanceConsecutive>=commandes.number_match) {
					note = "0" ;
					bodyetnotation.getAttributs().put("commentaire","Echange de fichier - plagiat");
				}
				if(verifStudent.getAttributs().get("copier_coller")!=null){
					bodyetnotation.getAttributs().put("commentaire","des copiés et des collés");
				}
				String nbrModification = verifStudent.getAttributs().get("nombre_modification");
				String nombre_modifications_date_unique = verifStudent.getAttributs().get("nombre_modifications_date_unique");
				
				fichier.write(identification + separator + mail + separator + numeroEtudiant + separator + dateModif + separator + producteur + separator + traitementDureeEdition(dureeEdition) + separator + sujet + separator + nbrModification +separator + nombre_modifications_date_unique + separator + nbreCorrespondanceConsecutive + separator +  traitementNote(note) + separator + bodyetnotation.getAttributs().get("commentaire")+"\n");
			}
			
			if(!commandes.verifHisto2) {
				note = bodyetnotation.getAttributs().get("note");
				if(bodyetnotation.getAttributs().get("baremeABC")!=null) {
					try {
						if(Boolean.valueOf(bodyetnotation.getAttributs().get("baremeABC"))) {
							note = bodyetnotation.getAttributs().get("noteABC");
						}
					}catch (Exception e) {
						
					}
				}
				fichier.write(identification + separator + mail + separator + numeroEtudiant + separator + dateModif + separator + producteur + separator + traitementDureeEdition(dureeEdition) + separator + sujet + separator + "" + separator + "" + "" + separator + "" + separator + traitementNote(note) + separator + bodyetnotation.getAttributs().get("commentaire")+"\n");
			}
			

		}
		fichier.close();
	}
	
	/**
	 * Traitement de la durée d'édition.<br>
	 * <br>
	 * @param dureeEdition
	 * @return
	 */
	private static String traitementDureeEdition(String dureeEdition) {
		dureeEdition = dureeEdition.replace("P", "");
		dureeEdition = dureeEdition.replace("D", " j ");
		dureeEdition = dureeEdition.replace("T", " ");
		dureeEdition = dureeEdition.replace("H", " h ");
		dureeEdition = dureeEdition.replace("M", " min ");
		dureeEdition = dureeEdition.replace("S", " s");
		return dureeEdition;
	}
	
	/**
	 * Remplace la virgule par un point.<br>
	 * <br>
	 * @param note
	 * @return
	 */
	private static String traitementNote(String note) {
		if(note==null) return note;
		note = note.replace(",", ".");
		return note;
	}
	
	/**
	 * 
	 * @param libreoffice_date
	 * @return
	 */
	private static Date DateLibreOffice(String libreoffice_date){
		boolean contientHeure = false;
		if(libreoffice_date.contains("T")) {
			libreoffice_date=libreoffice_date.replace("T", " ");
			contientHeure=true;
		}
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = null;
		if(!contientHeure) simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			d = simpledateformat.parse(libreoffice_date);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		return  d;
	}
	
	
	/**
	 * Chargement dans un node du fichier CSV de la liset des étudiants.<br>
	 * La liste des étudiants doit contenir les champs Prénom, Nom, Numéro d'identification, Adresse de courriel
	 * Le séparateur doit être le point-virgule et l'encodage UTF-8
	 * @param a
	 * @param nameCSV
	 * @return
	 * @throws IOException
	 */
	private static node chargementFichierCSV(Run a, String nameCSV) {
		String targetString = "";
		try {
			BufferedReader br = new BufferedReader(
		        new InputStreamReader(
		            new FileInputStream(a.getPatch() + "/" + nameCSV), "UTF-8")); 
		
			String line;
			while ((line = br.readLine()) != null) {
				targetString = targetString + line + "\n";
			}
		
			br.close();
		} catch (IOException e) {
			commandes.clotureWithErrorFile(nameCSV); 
			e.printStackTrace();
		}
		
		
		String[] target = targetString.split("\\n");
		node nodeCVS = new node();
		nodeCVS.setNomElt("fileCSV");
		String[] line1 = target[0].split(";");
		for(int i = 1 ; i < target.length ; i++) {
			node nodeEtudiant = new node();
			nodeEtudiant.setNomElt("student");
			for(int j = 0 ; j < line1.length; j++) {
				nodeEtudiant.getAttributs().put(line1[j], target[i].split(";")[j]);
			}
			nodeCVS.getNodes().add(nodeEtudiant);
		}
		//a.ecritureNodeEnXML(nodeCVS, "nodeCVS"); //écriture du node du node CSV
		return nodeCVS;
	}
	
	/**
	 * Charge le fichier SVG pour le nouveau Logo dans les feedbacks
	 * @param a
	 * @param nameSVG
	 * @return
	 */
	private static String chargementFichierSVG(Run a, String nameSVG) {
		String targetString = "";
		try {
			BufferedReader br = new BufferedReader(
		        new InputStreamReader(
		            new FileInputStream(a.getPatch() + "/" + nameSVG), "UTF-8")); 
		
			String line;
			while ((line = br.readLine()) != null) {
				targetString = targetString + line + "\n";
			}
		
			br.close();
		} catch (IOException e) {
			commandes.clotureWithErrorFile(nameSVG); 
			e.printStackTrace();
		}
		return targetString;
	}
	
	/**
	 * Ajoute les valeurs par défauts pour les styles de paragraphes.<br>
	 * <br>
	 * @param LesStyleStudents : Tous les styles de paragraphes du fichiers de l'étudiants.
	 * @param styleParagraph : le node dont il faut ajouter les valerus par défauts.
	 * @return le node styleParagraph.
	 */
	private static node ajouteValeurParDefautAuStyleParagraph(node ensembleDesParagraphes , node styleParagraph) {
		
		node LesStyleDefaut = null;
		
		if(ensembleDesParagraphes.retourneEnfantsByNameExist("style:default-style")) {
			LesStyleDefaut = ensembleDesParagraphes.retourneFirstEnfantsByName("style:default-style");
		}
		
		if(LesStyleDefaut!=null && styleParagraph!=null) {
			
			//le paragarph properties
			node nodeStyleParDefautParagraphProperties = LesStyleDefaut.retourneFirstEnfantsByName("style:paragraph-properties");
			node LesNodesStyleParagraph = styleParagraph.retourneFirstEnfantsByName("style:paragraph-properties");
			Enumeration<String > K = nodeStyleParDefautParagraphProperties.getAttributs().keys();
			while(K.hasMoreElements()){
				String Key = K.nextElement();
				if(LesNodesStyleParagraph.getNomElt().equals("style:paragraph-properties")) {	
					if(LesNodesStyleParagraph.getAttributs().get(Key)==null) {
						LesNodesStyleParagraph.getAttributs().put(Key,  nodeStyleParDefautParagraphProperties.getAttributs().get(Key));
					}
				}else {
					node n = new node();
					n.setNomElt("style:paragraph-properties");
					n.setAttributs(nodeStyleParDefautParagraphProperties.getAttributs());
					styleParagraph.getNodes().add(n);
					break;
				}
			}
			
			//le text properties
			node nodeStyleParDefautTextProperties = LesStyleDefaut.retourneFirstEnfantsByName("style:text-properties");
			node LesNodesStyleText = styleParagraph.retourneFirstEnfantsByName("style:text-properties");
			K = nodeStyleParDefautTextProperties.getAttributs().keys();
			while(K.hasMoreElements()){
				String Key = K.nextElement();
				if(LesNodesStyleText.getNomElt().equals("style:text-properties")) {	
					if(LesNodesStyleText.getAttributs().get(Key)==null) {
						LesNodesStyleText.getAttributs().put(Key,  nodeStyleParDefautTextProperties.getAttributs().get(Key));
					}
				}else {
					node n = new node();
					n.setNomElt("style:text-properties");
					n.setAttributs(nodeStyleParDefautTextProperties.getAttributs());
					styleParagraph.getNodes().add(n);
					break;
				}
			}
			
			
			
		}
		return styleParagraph;
	}
	
	/**
	 * Même méthode que "ajouteValeurParDefautAuStyleParagraph".<br>
	 * <br>
	 * @param ensembleDesParagraphes
	 * @param styleParagraph
	 * @return
	 */
	private static node ajouteValeurLesValeursDuStyleParagraphParent(node ensembleDesParagraphes , node styleParagraph) {
		
		node parent = null;
		if(styleParagraph.getAttributs().get("style:parent-style-name")!=null) {
			String nameStyleParent = styleParagraph.getAttributs().get("style:parent-style-name");
			if(ensembleDesParagraphes.retourneFirstNodeByNameAndAttributValue("style:style", "style:name", nameStyleParent)!=null) {
				parent = ensembleDesParagraphes.retourneFirstNodeByNameAndAttributValue("style:style", "style:name", nameStyleParent);
			}
		}
		
		
		if(parent!=null) {
			
			//le paragarph properties
			node nodeStyleParDefautParagraphProperties = parent.retourneFirstEnfantsByName("style:paragraph-properties");
			node LesNodesStyleParagraph = styleParagraph.retourneFirstEnfantsByName("style:paragraph-properties");
			Enumeration<String > K = nodeStyleParDefautParagraphProperties.getAttributs().keys();
			while(K.hasMoreElements()){
				String Key = K.nextElement();
				if(LesNodesStyleParagraph.getNomElt().equals("style:paragraph-properties")) {	
					if(LesNodesStyleParagraph.getAttributs().get(Key)==null) {
						LesNodesStyleParagraph.getAttributs().put(Key,  nodeStyleParDefautParagraphProperties.getAttributs().get(Key));
					}
				}else {
					node n = new node();
					n.setNomElt("style:paragraph-properties");
					n.setAttributs(nodeStyleParDefautParagraphProperties.getAttributs());
					styleParagraph.getNodes().add(n);
					break;
				}
			}
			
			//le text properties
			node nodeStyleParDefautTextProperties = parent.retourneFirstEnfantsByName("style:text-properties");
			node LesNodesStyleText = styleParagraph.retourneFirstEnfantsByName("style:text-properties");
			K = nodeStyleParDefautTextProperties.getAttributs().keys();
			while(K.hasMoreElements()){
				String Key = K.nextElement();
				if(LesNodesStyleText.getNomElt().equals("style:text-properties")) {	
					if(LesNodesStyleText.getAttributs().get(Key)==null) {
						LesNodesStyleText.getAttributs().put(Key,  nodeStyleParDefautTextProperties.getAttributs().get(Key));
					}
				}else {
					node n = new node();
					n.setNomElt("style:text-properties");
					n.setAttributs(nodeStyleParDefautTextProperties.getAttributs());
					styleParagraph.getNodes().add(n);
					break;
				}
			}
		}
		return styleParagraph;
	}
	

	

	
	/**
	 * recherche en cascade des nodes en fonction de leur contenu.
	 * @param nameNode : nom du node
	 * @param nodSujet : nod sujet
	 * @param nod0Student : node contenant le node nod1Student
	 * @param nod1Student : node contenant le node nod2Student.
	 * @param nod2Student : node de niveau le plus haut.
	 * @param a : Run cXML
	 * @return : le node recherché
	 */
	private static node rechercheLeNodeEnCascade(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		
		node nodStudent =null;
		// recherche le node uniquement par son contenu et pas celui de ses enfants
		if(nodSujet.getAttributs().get("recherche_contenu_exact")!=null){
			if(nodSujet.getAttributs().get("recherche_index")!=null) {
				if(nodSujet.getAttributs().get("recherche_contenu_exact").equals("true") && nodSujet.getAttributs().get("recherche_index").equals("false") ) {
					String valueAttribut = outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getContenu());
					if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod2Student.getNodes(), valueAttribut);
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod1Student.getNodes(), valueAttribut);
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod0Student.getNodes(), valueAttribut);
					if(nodStudent!=null) {return nodStudent;}else {return null;}
				}
				if(nodSujet.getAttributs().get("recherche_contenu_exact").equals("true") && nodSujet.getAttributs().get("recherche_index").equals("true") && nodSujet.getAttributs().get("index")!=null ) {
					String valueAttribut = outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getContenu());
					String indexSujet = nodSujet.getAttributs().get("index");
					
					if(!valueAttribut.isEmpty()&&!indexSujet.isEmpty()) {
						if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod2Student.getNodes(), valueAttribut);
						if(nodStudent!=null) if(nodStudent.getAttributs().get("index").equals(indexSujet)) {return nodStudent;}else {nodStudent=null;}
						
						if(nod1Student!=null) if(nodStudent==null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod1Student.getNodes(), valueAttribut);
						if(nodStudent!=null) if(nodStudent.getAttributs().get("index").equals(indexSujet)) {return nodStudent;}else {nodStudent=null;}
						
						if(nod0Student!=null) if(nodStudent==null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod0Student.getNodes(), valueAttribut);
						if(nodStudent!=null) if(nodStudent.getAttributs().get("index").equals(indexSujet)) {return nodStudent;}else {nodStudent=null;}
					}
				}
			}else {
				if(nodSujet.getAttributs().get("recherche_contenu_exact").equals("true")) {
					String valueAttribut = outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getContenu());
					if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod2Student.getNodes(), valueAttribut);
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod1Student.getNodes(), valueAttribut);
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod0Student.getNodes(), valueAttribut);
					if(nodStudent!=null) {return nodStudent;}else {return null;}
				}
			}
		}
		
		// recherche le node par index uniquement
		if(nodSujet.getAttributs().get("recherche_index")!=null){
			if(nodSujet.getAttributs().get("recherche_index").equals("true")) {
				String valueAttribut = nodSujet.getAttributs().get("index");
				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
				if(nodStudent!=null) {return nodStudent;}else {return null;}
			}
		}
		
		
		
		// recherche par différent contenu du node
		if(nameNode.equals("text:p")) {
			//si le node "text:p" contient un "text:user-defined" alors le recherche par le "text:name" de ce node "text:user-defined"
			if(nodSujet.containElementByName("text:user-defined")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:user-defined").getAttributs().get("text:name"));
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
			}
			//si le node "text:p" contient un "text:conditional-text" alors le recherche par le "text:condition" de ce node "text:conditional-text"
			if(nodSujet.containElementByName("text:conditional-text")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:conditional-text").getAttributs().get("text:condition"));
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:conditional-text", "text:condition", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:conditional-text", "text:condition", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:conditional-text", "text:condition", valueAttribut);
			}
			//si le node "text:p" contient un "text:database-display" alors le recherche par le "text:column-name" de ce node "text:database-display"
			if(nodSujet.containElementByName("text:database-display")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
			}
			//si le node "text:p" contient un "text:date" alors le recherche par le "text:fixed" de ce node "text:date"
			if(nodSujet.containElementByName("text:date")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:date").getAttributs().get("text:fixed"));
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
			}
			if(nodSujet.containElementByName("text:subject")) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:subject");
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:subject");
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:subject");
			}
			if(nodSujet.containElementByName("text:title")) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:title");
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:title");
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:title");
			}
			if(nodSujet.containElementByName("text:initial-creator")) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:initial-creator");
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:initial-creator");
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:initial-creator");
			}
			if(nodSujet.containElementByName("text:creator")) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:creator");
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:creator");
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:creator");
			}
			if(nodStudent==null) {
				if(nodSujet.retourneLesContenusEnfants("").isEmpty()) { //s'il n'y a pas de contenu, passe par l'index
					if(nodSujet.getAttributs().get("index")!=null) {
						if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
						if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
						if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
					}
				}else {
					if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
				}
			}
		}
		
		if(nameNode.equals("text:title")) {
			if(nod2Student!=null) nodStudent = nod2Student.retourneFirstEnfantsByName("text:title");
			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByName("text:title");
			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByName("text:title");
			return nodStudent;
		}
		
		if(nameNode.equals("text:subject")) {
			if(nod2Student!=null) nodStudent = nod2Student.retourneFirstEnfantsByName("text:subject");
			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByName("text:subject");
			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByName("text:subject");
		}
		
		if(nameNode.equals("text:initial-creator")) {
			if(nod2Student!=null) nodStudent = nod2Student.retourneFirstEnfantsByName("text:initial-creator");
			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByName("text:initial-creator");
			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByName("text:initial-creator");
		}
		
		if(nameNode.equals("text:user-defined")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
		}
		
		
		//recherche par le contenu enfant du node
		if(nameNode.equals("text:h")) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
		}
		
		//recherche par text:name
		if(nameNode.equals("text:section")) {
			if(nodSujet.getAttributs().get("recherche_index")==null) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			}else {
				if(nodSujet.getAttributs().get("recherche_index").equals("false")) {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
				}
				if(nodSujet.getAttributs().get("recherche_index").equals("true")) {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "index", nodSujet.getAttributs().get("index"));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "index", nodSujet.getAttributs().get("index"));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "index", nodSujet.getAttributs().get("index"));
				}
			}
		}
		
		//recherche par le nom de la colonne
		if(nameNode.equals("text:database-display")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
		}
		
		//recherche par le nom de l'objet draw:name ou par text:anchor-page-number si ancrer à la page
		if(nameNode.equals("draw:frame")) {
			if(nodSujet.getAttributs().get("recherche_anchor-page-number")==null) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
			}else {
				if(nodSujet.getAttributs().get("recherche_anchor-page-number").equals("false")) {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
				}
				if(nodSujet.getAttributs().get("recherche_anchor-page-number").equals("true")) {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
					//si recherche l'ancrage de la page pas trouvé alors recherche par draw:name
					if(nodStudent==null) {
						if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
						if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
						if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
					}
				}
			}
		}
		//recherche par le nom de l'objet
		if(nameNode.equals("style:graphic-properties")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameContent(nod2Student, nameNode, nodSujet.getContenu());
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameContent(nod1Student, nameNode, nodSujet.getContenu());
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameContent(nod0Student, nameNode, nodSujet.getContenu());
		}
		
		//recherche par l'index
		if(nameNode.equals("table:table-row")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			
		}
		
		//recherche par texte:name
		if(nameNode.equals("text:table-of-content")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));		
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
		}
		
		//recherche par contenu
		if(nameNode.equals("text:span")) {
			if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
		
		}
		
		//recherche par contenu
		if(nameNode.equals("text:tab")) {
			if(!nodSujet.retourneLesContenusEnfants("").isEmpty()) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), nodSujet.retourneLesContenusEnfants(""),commandes.tolerance_characters,commandes.tolerance_text);
			}
		}
		
		
		// dernière tentative si le node est vide, recherche par le nom du node
		if(nodStudent==null && !nameNode.equals("text:p")) {
			if(nod2Student!=null) if(nod2Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod2Student.retourneFirstEnfantsByName(nameNode);
			if(nod1Student!=null) if(nodStudent==null) if(nod1Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod1Student.retourneFirstEnfantsByName(nameNode);
			if(nod0Student!=null) if(nodStudent==null) if(nod0Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod0Student.retourneFirstEnfantsByName(nameNode);
		}
		
		
		
		return nodStudent;
	}
		
		
	private static node analyseStyle(node page, node nodSujet, node nodStudent, node nodSujetParagraphs, node nodStudentParagraphs ) {
		node StyleParagraphSujet = null;
		node StyleParagraphStudent = null;
		
		if(nodSujet.getAttributs().get("analyseStyle")!=null) {
			
			if(nodSujet.getAttributs().get("analyseStyle").equals("true") && nodSujet.getAttributs().get("text:style-name")!=null) {
				//paragrapheTexte=true;
				String NameStyleParagrapheSujet = nodSujet.getAttributs().get("text:style-name");
				StyleParagraphSujet = nodSujetParagraphs.retourneFirstNodeStyleByValueAttribut("style:style", "style:name", NameStyleParagrapheSujet);
			}
			
			if(nodStudent!=null && StyleParagraphSujet!=null) {
				if(nodStudent.getAttributs().get("text:style-name")!=null && StyleParagraphSujet!=null) {
					String NameStyleParagrapheStudent = nodStudent.getAttributs().get("text:style-name");
					StyleParagraphStudent = nodStudentParagraphs.retourneFirstNodeStyleByValueAttribut("style:style", "style:name",NameStyleParagrapheStudent);
				}
			}
			
			// ajoute les valeurs par héritage.
			if(StyleParagraphSujet!=null) {
				if(StyleParagraphStudent!=null) StyleParagraphStudent = ajouteValeurLesValeursDuStyleParagraphParent(nodStudentParagraphs , StyleParagraphStudent);
				
				//ajoute les valeurs par défaut.
				if(StyleParagraphStudent!=null) StyleParagraphStudent = ajouteValeurParDefautAuStyleParagraph(nodStudentParagraphs , StyleParagraphStudent);
				
				
				// analyse attribut et contenu des enfants du premier niveau
				page = analyseLesAttributAnalyseStyle(StyleParagraphStudent, StyleParagraphSujet, page, "ana:page","style:style");
			}
			
		}
		return page;
	}
	
	
	/**
	 * Analyse le nom du node
	 * 
	 * @param retour : le node retour qui contient l'item ajouté
	 * @param nodStudent : le node Student qui peut être null
	 * @param nameNode : le nom du node
	 * @param point : les points
	 * @param nameElt : le nom de l'élément
	 * @return
	 */
	private static node analyseNameNode(node retour, node nodStudent, String nameNode, String point, String nameElt) {
		node item = null;
		if(nodStudent!=null) {
			item = retourneNoteAvecResultatsAnalyse(nameNode,"name", nodStudent.getNomElt(),nameNode + "‽" +point, nameElt );
		}else {
			outils.IncrementPointTotal(Integer.valueOf(point));
			item = new node(nameNode, "Erreur", "Nom du node" , "null", nameNode, 2, outils.getPointEnJeu(),nameElt);
		}
		retour.getNodes().add(item);
		return retour;
	}
	
	private static node analyseNameCreator(node retour, node nodStudent, String nameCreator, String point, String nameElt) {
		node item = null;
		if(nodStudent!=null) {
			item = retourneNoteAvecResultatsAnalyse(nameCreator,"name", nodStudent.getAttributs().get("creator"),nameCreator + "↑‽" +point, nameElt );
		}else {
			outils.IncrementPointTotal(Integer.valueOf(point));
			item = new node(nameCreator, "Erreur", "Nom du l'éditeur" , "null", nameCreator, 2, outils.getPointEnJeu(),nameElt);
		}
		retour.getNodes().add(item);
		return retour;
	}
	
	private static node analyseNameInitialCreator(node retour, node nodStudent, String nameCreator, String point, String nameElt) {
		node item = null;
		if(nodStudent!=null) {
			item = retourneNoteAvecResultatsAnalyse(nameCreator,"name", nodStudent.getAttributs().get("initial-creator"),nameCreator + "↑‽" +point, nameElt );
		}else {
			outils.IncrementPointTotal(Integer.valueOf(point));
			item = new node(nameCreator, "Erreur", "Nom du créateur" , "null", nameCreator, 2, outils.getPointEnJeu(),nameElt);
		}
		retour.getNodes().add(item);
		return retour;
	}
	
	
	
}
	
	

