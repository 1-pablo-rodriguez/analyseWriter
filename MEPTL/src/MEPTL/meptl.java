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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
 */
public class meptl {
	
	static DecimalFormat df = new DecimalFormat("###.##");
	static String patch ="";
	static double progression = 1.0;
	
	/**
	 * Start of application.<br/>
	 * <br/>
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, CloneNotSupportedException, InterruptedException {
		System.getProperty("file.encoding","UTF-8");
		node nodeCSV = null; //Permet de convertir le fichier contenant la liste des étudiants en node
				
		patch = System.getProperty("user.dir");
//		patch = "C:/Users/pabr6/Downloads/teste/";
//		patch = "C:/Users/pabr6/OneDrive/Desktop/presentation";
		
		//les commandes
		new commandes(args,patch);
		
		//Lancement des lectures des dossiers
		Run a = new Run(patch,commandes.Profil);

		
		//chargement du node sujet (fichier d'analyse)
		//La méthode verificationFichier Analyse permet de détecter des erreurs dans le fichier d'analyse
		node nodeSujet = new node();
		if(!commandes.ecritCode && commandes.analyse) {
			nodeSujet = chargementsujet(a, commandes.nameSujet);
			commandes.culture = nodeSujet.retourneFirstEnfantsByName("setting").getAttributs().get("culture"); //récupère la culture de l'utilisateur
			new verificationFichierAnalyse(nodeSujet);
			if(verificationFichierAnalyse.erreur==true) verificationFichierAnalyse.clotureWithErrorInanalyzeFile();
			//a.ecritureNodeEnXML(nodeSujet, "sujet","",false);  // ecriture du node sujet
			if(commandes.ecritSujet) {
				a.ecritureNodeEnXML(nodeSujet, "sujet","",false);  // ecriture du node sujet
				System.out.println();
				System.out.println("\tWriting of the \"sujet.xml\" file completed.");
				commandes.clotureApplication();
				System.exit(0);
			}
		}
		
		//chargement et verification du CVS fourni
		if(commandes.fourniCSV) nodeCSV = chargementFichierCSV(a, commandes.nameCSV);

		
		// vérification des historiques
		node verif  = new node();
		if(commandes.verifHisto || commandes.verifHisto2) {
			int nbFichierWriter = a.getLectDossiers().getEC().getListeContentWriter().size();
			node verification = new node();
			verification.setNomElt("verification");
			verification.getAttributs().put("nombre_fichier", String.valueOf(a.getLectDossiers().getEC().getListeFichierodt().size()));
			if(nodeSujet.containElementByName("plagiarism")) {
				node plagiarism = nodeSujet.retourneFirstEnfantsByName("plagiarism");
				if(plagiarism.getAttributs().get("number_match") != null) verification.getAttributs().put("number_match", plagiarism.getAttributs().get("number_match"));
			}
			for(int i = 0 ; i < nbFichierWriter ; i++) {
				node nod = a.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
				node nodStudent = LectureFichierEtudiantPourVerification(nod,a,i);
				verification.getNodes().add(nodStudent);
			}
			//a.ecritureNodeEnXML(verification, "VerificationHistorique","",false); //écriture du node de l'étudiant
			verif = verificationHistorique(verification, a);  // vérification des correspondance entre les fichiers
			a.ecritureNodeEnXML(verif, "Verif",commandes.pathDestination,commandes.fourniDossierDestination); //écriture du node de vérification
			if(!commandes.analyse) {commandes.clotureApplication();System.exit(0);}
		}
		
		
		//nombre de fichier writer à analyser
		int nbFichierWriter = a.getLectDossiers().getEC().getListeContentWriter().size();
		System.getProperty("file.encoding","UTF-8");
		
		//ensemble des analyses
		node ensembleanalyse = new node();
		ensembleanalyse.setNomElt("analyses");
		
		for(int i = 0 ; i < nbFichierWriter ; i++) {
			
			// Ne prends pas en compte le dossier destination créé par la commande -dest
			// Si pas d'analyse alors le nom doit contenir le caractère $ dans le nom du dossier.
			if(commandes.fourniDossierDestination)if(a.getLectDossiers().getEC().getListeNomDossier().get(i).equals(commandes.pathDestination)) continue;
			
			//Chargement du format (content) et transformation en node pour l'application
			node nod = a.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
			node nodStudent = LectureFichierEtudiantSousFormeDeNode(nod,a,i);
			//a.ecritureNodeEnXML(nodStudent, a.getLectDossiers().getEC().getListeNomDossier().get(i),"",false); //écriture du node de l'étudiant

			
			// ecriture d'une fichier d'analyse.
			// commande -write 
			if(commandes.ecritCode && ! commandes.verifHisto && !commandes.analyse) {
				node nodSujet = nodePourEcritureSujet(nodStudent,a,i);
				nodSujet = addSetting(nodSujet); // ajoute le node setting
				a.ecritureNodeEnXML(nodSujet, a.getLectDossiers().getEC().getListeNomDossier().get(i),"",false);
			}
			
			
			// analyse des fichiers student
			if(commandes.analyse) {
				node init = InitialisationAvantAnalyse(nodeSujet);
				if(!Boolean.valueOf(init.getAttributs().get("erreur"))) {
					node ana = analyse(nodStudent, nodeSujet, i, a);
					//a.ecritureNodeEnXML(ana, "nodana"+ana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"),"",false); //écriture du node analyse de l'étudiant
					
					// création des feedbacks avace des tailles défini
					if(!commandes.sansFeeback) if(!commandes.zipfeedback) feedback(ana, verif); //classique directement dans le répertoire
					if(!commandes.sansFeeback) if(commandes.zipfeedback) { // Dans une archive pour Moodle
						try {
							long size = 48000000; //valeur par défaut
							String nameZip = "feedbackMoodle";
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
		//exportation au format CSV
		if(commandes.ecritNoteCSV && !commandes.fourniCSV) {
			if(!commandes.verifHisto2) ecritureCSV(ensembleanalyse);
			if(commandes.verifHisto2) ecritureCSV(ensembleanalyse,verif,a);
			//a.ecritureNodeEnXML(ensembleanalyse, "ensembleAnalyse"); //écriture du node de l'étudiant
		}
		if(commandes.ecritNoteCSV && commandes.fourniCSV) {
			ecritureCSV(ensembleanalyse,verif,a,nodeCSV, nodeSujet.retourneFirstEnfantsByName("setting"));
			//a.ecritureNodeEnXML(ensembleanalyse, "ensembleAnalyse"); //écriture du node de l'étudiant
		}
		
		//bye bye
		commandes.clotureApplication();
		
	}
		


	/**
	 * Lecture du fichier Student pour vérification.<br/>
	 * <br/>
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
	 * @param nod : le node Writer de l'étudiant.
	 * @param a : objet de la class Run package cXML
	 * @param i : index de l'étudiant dans la liste EC de l'objet a.
	 * @return le node contenant tous les nodes de la lectures.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
 	private static node LectureFichierEtudiantSousFormeDeNode(node nod, Run a, Integer i) throws IOException {
		node nodecontent = nod.retourneFirstEnfantsByName("office:document-content");
		node nodestyle = nod.retourneFirstEnfantsByName("office:document-styles");
		node nodbody = a.NodeFirstName(nodecontent, "office:text");
		node nodmeta = nod.retourneFirstEnfantsByName("office:meta");
		
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
		fichier.getAttributs().put("filename", a.getLectDossiers().getEC().getListeFichierodt().get(i));
		if(commandes.Profil.equals(UserStatus.STUDENT)) {
			a.getLectDossiers().getEC().getListeNomDossier().add(i, a.getLectDossiers().getEC().getListeFichierodt().get(i).substring(0, a.getLectDossiers().getEC().getListeFichierodt().get(i).lastIndexOf(".")));
		}
		fichier.getAttributs().put("dossier", a.getLectDossiers().getEC().getListeNomDossier().get(i));
	
		
		
		fichier.getAttributs().put("producteur", nodmeta.retourneFirstEnfantsByName("meta:generator").getContenu());
		fichier.getAttributs().put("dureeEdition", nodmeta.retourneFirstEnfantsByName("meta:editing-duration").getContenu());
		fichier.getAttributs().put("dateModification", nodmeta.retourneFirstEnfantsByName("dc:date").getContenu());
		
		fichier.getNodes().add(nodmeta);
		fichier.getNodes().add(nodstylepage);
		fichier.getNodes().add(nodstyleparagraphe);
		fichier.getNodes().add(nodsequence);
		fichier.getNodes().add(nodnumerochapitre);
		
		nodframe = a.numeroteNameNode(nodframe, "text:p", 0);
		fichier.getNodes().add(nodframe);
		
		fichier.getNodes().add(nodsection);
		fichier.getNodes().add(nodbiblio);
		fichier.getNodes().add(nodtable);
		fichier.getNodes().add(nodillustrations);
		
		structurePage = a.numeroteNameNode(structurePage, "text:p", 0);
		structurePage = a.numeroteNameNode(structurePage, "text:span", 0);
		structurePage = a.numeroteNameNode(structurePage, "table:table-row", 0);
		structurePage = a.numeroteNameNode(structurePage, "table:table-cell", 0);
		fichier.getNodes().add(structurePage);
		
		fichier.getNodes().add(nodhistorique);
		return fichier;
	}
	
	/**
	 * Fourni le node du sujet avec les attributs d'evaluation et de mise en page pour le feedback.
	 * @param nod
	 * @param a
	 * @param i
	 * @return
	 */
	private static node nodePourEcritureSujet(node nod, Run a, Integer i) {
		LocalDateTime aujourdhui = LocalDateTime.now();
		
		// fichier
		nod.getAttributs().put("evaluer", "true");
		nod.getAttributs().put("progression", "1");
		nod.getAttributs().put("notefrom", "20");
		nod.getAttributs().put("date", String.valueOf(aujourdhui));
		nod.getAttributs().put("version", commandes.version);
		nod.getAttributs().put("titre", "Le titre de l'exercice");
		nod.getAttributs().put("link_sujet", "https://moodle.univ-artois.fr/cours/");
		nod.getAttributs().put("link_help", "https://moodle.univ-artois.fr/cours/");
		nod.getAttributs().put("historiquePresent", "true");
		nod.getAttributs().put("controleDateCreation", "true");
		node b = a.retourneName(nod.retourneFirstEnfantsByName("office:meta"),"meta:user-defined","meta:name","Sujet");
		if(b!=null) { nod.getAttributs().put("metaSujet", b.getContenu());}else {nod.getAttributs().put("metaSujet", "?");}
		b = nod.retourneFirstEnfantsByName("office:meta").retourneFirstEnfantsByName("meta:creation-date");
		if(b!=null) nod.getAttributs().put("creationDate", b.getContenu());
		nod.getAttributs().put("auteur", "pablo rodriguez");
		nod.getAttributs().put("affichecommentaire", "false");
		nod.setContenu("Commentaire sur cet exercice");
		nod.getAttributs().remove("dossier");
		
		
		//metadonnées
		node nodmeta = nod.retourneFirstEnfantsByName("office:meta");
		if(nodmeta.getNomElt().equals("office:meta")) {
			nod.getNodes().remove(nodmeta);
			nodmeta.getAttributs().put("evaluer", "false");
			nodmeta.getAttributs().put("addmenu", "false");
			nodmeta.getAttributs().put("poids", "1");
			nodmeta.getAttributs().put("titre", "Les metadonnées");
			nodmeta.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodmeta.retourneEnfantsByName("meta:user-defined", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodmeta.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodmeta.getNodes().addAll(no);
			nod.getNodes().add(nodmeta);
		}

		//style de page
		node nodpage = nod.retourneFirstEnfantsByName("style:page");
		if(nodpage.getNomElt().equals("style:page")) {
			nod.getNodes().remove(nodpage);
			nodpage.getAttributs().put("evaluer", "false");
			nodpage.getAttributs().put("addmenu", "false");
			nodpage.getAttributs().put("poids", "1");
			nodpage.getAttributs().put("titre", "Les styles de page");
			nodpage.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodpage.retourneEnfantsByName("style:master-page", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodpage.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodpage.getNodes().addAll(no);
			nod.getNodes().add(nodpage);
		}

		//style de paragraphe
		node nodparagraph = nod.retourneFirstEnfantsByName("style:paragraph");
		if(nodparagraph.getNomElt().equals("style:paragraph")) {
			nod.getNodes().remove(nodparagraph);
			nodparagraph.getAttributs().put("evaluer", "false");
			nodparagraph.getAttributs().put("addmenu", "false");
			nodparagraph.getAttributs().put("poids", "1");
			nodparagraph.getAttributs().put("titre", "Les styles de paragraphe");
			nodparagraph.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodparagraph.retourneEnfantsByName("style:style", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodparagraph.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodparagraph.getNodes().addAll(no);
			nod.getNodes().add(nodparagraph);
		}

		//séquence
		node nodsequence = nod.retourneFirstEnfantsByName("sequences");
		if(nodsequence.getNomElt().equals("sequences")) {
			nod.getNodes().remove(nodsequence);
			nodsequence.getAttributs().put("evaluer", "false");
			nodsequence.getAttributs().put("addmenu", "false");
			nodsequence.getAttributs().put("poids", "1");
			nodsequence.getAttributs().put("titre", "Les variables de séquence");
			nodsequence.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodsequence.retourneEnfantsByName("text:sequence-decl", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodsequence.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodsequence.getNodes().addAll(no);
			nod.getNodes().add(nodsequence);
		}
		
		//numérotation des chapitres
		node nodnumerotationchapitre = nod.retourneFirstEnfantsByName("numerotationchapitre");
		if(nodnumerotationchapitre.getNomElt().equals("numerotationchapitre")) {
			nod.getNodes().remove(nodnumerotationchapitre);
			nodnumerotationchapitre.getAttributs().put("evaluer", "false");
			nodnumerotationchapitre.getAttributs().put("addmenu", "false");
			nodnumerotationchapitre.getAttributs().put("poids", "1");
			nodnumerotationchapitre.getAttributs().put("titre", "Numérotation des chapitres");
			nodnumerotationchapitre.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodnumerotationchapitre.retourneEnfantsByName("text:outline-level-style", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodnumerotationchapitre.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodnumerotationchapitre.getNodes().addAll(no);
			nod.getNodes().add(nodnumerotationchapitre);
		}
		
		//frame
		node nodframe = nod.retourneFirstEnfantsByName("frames");
		if(nodframe.getNomElt().equals("frames")) {
			nod.getNodes().remove(nodframe);
			nodframe.getAttributs().put("evaluer", "false");
			nodframe.getAttributs().put("addmenu", "false");
			nodframe.getAttributs().put("poids", "1");
			nodframe.getAttributs().put("titre", "Les frames (cadres et images)");
			nodframe.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodframe.retourneEnfantsByName("draw:frame", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodframe.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodframe.getNodes().addAll(no);
			nod.getNodes().add(nodframe);
		}
		
		
		//sections
		node nodsection = nod.retourneFirstEnfantsByName("sections");
		if(nodsection.getNomElt().equals("sections")) {
			nod.getNodes().remove(nodsection);
			nodsection.getAttributs().put("evaluer", "false");
			nodsection.getAttributs().put("addmenu", "false");
			nodsection.getAttributs().put("poids", "1");
			nodsection.getAttributs().put("titre", "Les sections");
			nodsection.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodsection.retourneEnfantsByName("text:section", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodsection.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodsection.getNodes().addAll(no);
			nod.getNodes().add(nodsection);
		}
		
		//biblio
		node nodbiblio = nod.retourneFirstEnfantsByName("biblio");
		if(nodbiblio.getNomElt().equals("biblio")) {
			nod.getNodes().remove(nodbiblio);
			nodbiblio.getAttributs().put("evaluer", "false");
			nodbiblio.getAttributs().put("addmenu", "false");
			nodbiblio.getAttributs().put("poids", "1");
			nodbiblio.getAttributs().put("titre", "La bibliographie");
			nodbiblio.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodbiblio.retourneEnfantsByName("text:bibliography", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodbiblio.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodbiblio.getNodes().addAll(no);
			nod.getNodes().add(nodbiblio);
		}
		
		//table des matières
		node nodtable = nod.retourneFirstEnfantsByName("tablematieres");
		if(nodtable.getNomElt().equals("tablematieres")) {
			nod.getNodes().remove(nodtable);
			nodtable.getAttributs().put("evaluer", "false");
			nodtable.getAttributs().put("addmenu", "false");
			nodtable.getAttributs().put("poids", "1");
			nodtable.getAttributs().put("titre", "Les index tables des matières");
			nodtable.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodtable.retourneEnfantsByName("text:table-of-content", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodtable.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodtable.getNodes().addAll(no);
			nod.getNodes().add(nodtable);
		}		

		//table des illustrations
		node nodillustrations = nod.retourneFirstEnfantsByName("tableillustrations");
		if(nodillustrations.getNomElt().equals("tableillustrations")) {
			nod.getNodes().remove(nodillustrations);
			nodillustrations.getAttributs().put("evaluer", "false");
			nodillustrations.getAttributs().put("addmenu", "false");
			nodillustrations.getAttributs().put("poids", "1");
			nodillustrations.getAttributs().put("titre", "Les index illustrations");
			nodillustrations.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodillustrations.retourneEnfantsByName("text:illustration-index", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodillustrations.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodillustrations.getNodes().addAll(no);
			nod.getNodes().add(nodillustrations);
		}		

	
		
		//structure document
		node nodstructurepage = nod.retourneFirstEnfantsByName("structurepage");
		if(nodstructurepage.getNomElt().equals("structurepage")) {
			nod.getNodes().remove(nodstructurepage);
			nodstructurepage.getAttributs().put("evaluer", "false");
			nodstructurepage.getAttributs().put("addmenu", "false");
			nodstructurepage.getAttributs().put("poids", "1");
			nodstructurepage.getAttributs().put("titre", "La structure du document");
			nodstructurepage.getAttributs().put("styletitre", "H1");
			ArrayList<node> no = nodstructurepage.retourneEnfantsByName("page", new ArrayList<node>());
			for(int j = 0 ; j < no.size(); j++) {
				nodstructurepage.getNodes().remove(no.get(j));
				no.get(j).getAttributs().put("saut", "false");
				no.get(j).getAttributs().put("evaluer", "false");
				no.get(j).getAttributs().put("titre", "");
				no.get(j).getAttributs().put("styletitre", "nostyle");
			}
			nodstructurepage.getNodes().addAll(no);
			nod.getNodes().add(nodstructurepage);
		}
		
		return nod;
	}
	
	/**
	 * Chargement du sujet.<br/>
	 * Retourne l'ensemble des nodes qui possédent l'attribut evaluer="true".
	 * <br/>
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
			// ! Important nettoyage du fichier avant lecture avec bXML
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
	 * Vérification du node sujet (premier node <b>fichier</b> et des paramètres.<br/>
	 * <br/>
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
		
		nodouverture.setClose(true);
			
		//Body et note (par défaut valeur nulle)
		node nodbodyetnotation = new node();
		nodbodyetnotation.setNomElt("bodyetnotation");
		nodbodyetnotation.getAttributs().put("note", "0");
		nodbodyetnotation.getAttributs().put("proportioncorrect", "0");
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

		// analyse les pages
		if(nodSujet.retourneFirstEnfantsByName("style:page").getNomElt().equals("style:page")) {
			nodpage = analysePage(nodStudent.retourneFirstEnfantsByName("style:page"),  nodSujet.retourneFirstEnfantsByName("style:page"), a, nodmenu);
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
		
	
		// retourne le node analyse assemblé
		return clotureNodeAnalyse(nodouverture, nodbodyetnotation, nodmenu, erreurs, nodmeta, nodpage, nodparagraph, nodsequence, nodnumerochapitre, nodframes, nodsections, nodbiblio, nodtablematieres, nodtableillustrations, nodstructurepage,nodSujet.getContenu());
	
			
		}
		
		
	/**
	 * <br>Les erreurs dans le fichier étudiant : erreur de métadonnées Sujet, date de création pour identifier le fichier à analyser.
	 * <br>Il y a une erreur si pas d'historique de modification dans le fichier de l'étudiant.
	 * <br>Les erreurs sont dans les attributs du node <b>Erreurs</b>.
	 * <br><b> oneError</b> si VRAI, il y a au moins une erreur.
	 * <br><b> manqueMetaSujet</b> si VRAI, il n'y a pas de méta données <b>Sujet</b>.
	 * <br><b> manqueValeurMetaSujet</b> si VRAI, la valeur de la méta données <b>Sujet</b> n'est pas la bonne.
	 * <br><b> manqueCreationDate</b> si VRAI, il n'y a pas de méta données <b>creationDate</b>.
	 * <br><b> manqueValeurCreationDate</b> si VRAI, la date de la méta données <b>creationDate</b> n'est pas la bonne.
	 * <br><b> manqueHistorique</b> si VRAI, il n'y a pas d'historique des modifications.<br/>
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
		boolean oneError=false;
		
		node b = a.retourneFirstNodeByNameAttributValue(nodStudent, "meta:user-defined", "meta:name", "Sujet");
		if(b==null) {
			manqueMetaSujet=true;
			oneError=true;
		}else {
			if(!nodSujet.getAttributs().get("metaSujet").equals(b.getContenu())) {
				manqueValeurMetaSujet=true;
				oneError=true;
			}
		}
		
		b =  nodStudent.retourneFirstEnfantsByName("meta:creation-date");
		if(!b.getNomElt().equals("meta:creation-date")) {
			manqueValeurCreationDate=true;
			oneError=true;
		}else {
			if(!(b.getContenu().contains(nodSujet.getAttributs().get("creationDate")))) { //nodSujet.getAttributs().get("creationDate").contains(b.getContenu())
				manqueValeurCreationDate = true;
				oneError=true;
			}
			if(nodSujet.getAttributs().get("creationDate").contains("*")) {
				manqueValeurCreationDate = false;
				oneError=false;
			}
			if(nodSujet.getAttributs().get("controleDateCreation")!=null) if(nodSujet.getAttributs().get("controleDateCreation").equals("false")) {
				manqueValeurCreationDate = false;
				oneError=false;
			}
		}
		
		b =  nodStudent.retourneFirstEnfantsByName("historique");
		if(b.getNomElt().equals("historique")) {
			if(Integer.valueOf(b.getAttributs().get("nbrModif"))<1) {
				manqueHistorique =true;
				oneError=true;
			}
		}
		
		
		erreurs.getAttributs().put("manqueMetaSujet", String.valueOf(manqueMetaSujet));
		erreurs.getAttributs().put("manqueValeurMetaSujet", String.valueOf(manqueValeurMetaSujet));
		erreurs.getAttributs().put("manqueCreationDate", String.valueOf(manqueCreationDate));
		erreurs.getAttributs().put("manqueValeurCreationDate", String.valueOf(manqueValeurCreationDate));
		if(nodSujet.getAttributs().get("historiquePresent")!=null) if(nodSujet.getAttributs().get("historiquePresent").equals("true")) {
			erreurs.getAttributs().put("manqueHistorique", String.valueOf(manqueHistorique));
		}else {
			erreurs.getAttributs().put("manqueHistorique", "false");
			manqueHistorique = false;
		}
		if( manqueMetaSujet == false && manqueValeurMetaSujet == false && manqueCreationDate == false && manqueValeurCreationDate == false 
				&& manqueHistorique == false) {
			erreurs.getAttributs().put("oneError", "false");
		}else {
			erreurs.getAttributs().put("oneError", String.valueOf(oneError));
		}
		
		
		
		erreurs.setClose(true);
		
		return erreurs;
	}
	
	
	/**
	 * Assemblage du node annalyse à partir des différents nodes de l'analyse.<br/>
	 * <br/>
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
		if(nodouverture!=null) if(nodouverture.isClose()) {
			if(nodouverture.getAttributs().get("notefrom")!=null) {
				try {
					notefrom = Math.abs(Double.valueOf(nodouverture.getAttributs().get("notefrom")));
				}catch (Exception e) {
					System.out.println("The \"noteFrom\" attribute of the analyze file cannot be converted to a \"double\".");
				}
			}
			if(nodouverture.getAttributs().get("progression")!=null) {
				try {
					progression = Math.abs(Double.valueOf(nodouverture.getAttributs().get("progression")));
				}catch (Exception e) {
					System.out.println("The \"progression\" attribute of the analyze file cannot be converted to \"double\".");
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
					if( sujet.get(i).getAttributs().get(k).contains("‽")){
						boolean trouvelebonnode =false;
						ArrayList<node> nod = a.retourneNames(nodStudentMeta, namenode);
						for(int j = 0 ; j < nod.size(); j ++) {
							String Tst = outils.Compare(nod.get(j).getAttributs().get(k), sujet.get(i).getAttributs().get(k));
							if(Tst.contains("Correct")) {
								trouvelebonnode=true;
								node item = new node("ana:meta", Tst, outils.withoutPoint(sujet.get(i).getAttributs().get(k)), nod.get(j).getAttributs().get(k), sujet.get(i).getAttributs().get(k), 1, outils.getPointEnJeu(),namenode);
								nodmeta.getNodes().add(item);
								break;
							}else {
								outils.decrementPointEnJeuDuTotal();
							}
						}
						if(!trouvelebonnode) {
							String Tst = outils.Compare("null", sujet.get(i).getAttributs().get(k));
							node item = new node("ana:meta", Tst, outils.withoutPoint(sujet.get(i).getAttributs().get(k)), "null", sujet.get(i).getAttributs().get(k), 2, outils.getPointEnJeu(), namenode);
							nodmeta.getNodes().add(item);
						}
					}
				}
			}else {
				Enumeration<String> key = sujet.get(i).getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if( sujet.get(i).getAttributs().get(k).contains("‽")){
						String valueAttributStudent = nodStudentMeta.retourneFirstEnfantsByName(namenode).getAttributs().get(k);
						String valueAttributSujet = sujet.get(i).getAttributs().get(k);
						
						node item =retourneNoteAvecResultatsAnalyse("ana:meta", k, valueAttributStudent, valueAttributSujet,namenode);
						
						nodmeta.getNodes().add(item);
					}
				}
			}
			
			// parcours le contenu autre que "meta:user-defined"
			if(sujet.get(i).getContenu().contains("‽") && sujet.get(i).getAttributs().get("meta:user-defined")==null) {
				String contentStudent = nodStudentMeta.retourneFirstEnfantsByName(namenode).getContenu();
				String contentSujet = sujet.get(i).getContenu();

				node item =retourneNoteAvecResultatsAnalyse("ana:meta", "texte", contentStudent, contentSujet,namenode);

				nodmeta.getNodes().add(item);

			}

			
			
			
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
	private static node analysePage(node nodStudentPage, node nodSujetPage, Run a, node nodmenu) {
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

				node nodStudent = null;	
				if(pageStudent!=null) if(pageStudent.retourneFirstEnfantsByName(nameNode).getNomElt().equals(nameNode)) {
					nodStudent = pageStudent.retourneFirstEnfantsByName(nameNode);
				}
				
				// analyse attribut et contenu des enfants du premier niveau
				page = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, page, "ana:page",pageSujet.getNodes().get(j).getNomElt());
				
					
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) { //niveau 3
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						page = addNodeSautTitre(nod2Sujet, page); // ajoute des sauts s'il y a des sauts avec des titres

						node nod2Student = null;	
						if(nodStudent!=null) if(nodStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
							nod2Student = nodStudent.retourneFirstEnfantsByName(nameNode2);
						}
						// analyse attribut et contenu des enfants du second niveau
						page = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, page, "ana:page",nodSujet.getNodes().get(k).getNomElt() );
						
						
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) {
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							//page = addNodeSautTitre(nod3Sujet, page);
							
							node nod3Student = null;	
							if(nod2Student!=null) if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
								nod3Student = nod2Student.retourneFirstEnfantsByName(nameNode3);
							}
							// analyse attribut et contenu des enfants du troisième niveau
							page = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, page, "ana:page", nod2Sujet.getNodes().get(l).getNomElt());
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
						nodStudent = a.retourneFirstNodeByFindContent2(frameStudent.getNodes(), nodSujet.retourneLesContenusEnfants(""));
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
				
				//node biblioStudent = a.retourneFirstNodeByNameAttributValue(nodStudentBiblio, "text:bibliography", "text:name", nomDeLaBiblio);
				//node biblioSujet = nodSujetBiblio.getNodes().get(i);
				
				// analyse les attributs du node
				//biblio = analyseLesAttributEtContenuDuNode(biblioStudent, biblioSujet, biblio, "ana:biblio",biblioSujet.getNomElt());
	
				String TitreTable = outils.withoutCodeAndPoint(nodSujetBiblio.getNodes().get(i).retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));

				node nodSujet = a.retourneFirstNodeByFindContent2(a.retourneNames(nodSujetBiblio, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeByFindContent2(a.retourneNames(nodStudentBiblio, "text:index-body"), TitreTable);
				
				ArrayList<node> LestextpSujet = a.retourneNames(nodSujet, "text:p");
				ArrayList<node> LestextpStudent = null;
				if(nodStudent!=null) LestextpStudent = a.retourneNames(nodStudent, "text:p");
				
				biblio = analyseLesContenusDesArrayList(LestextpStudent,LestextpSujet,biblio,"ana:biblio","txt:p",a);
				
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
				
				node nodSujet = a.retourneFirstNodeByFindContent2(a.retourneNames(nodSujetTableM, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeByFindContent2(a.retourneNames(nodStudentTableM, "text:index-body"), TitreTable);
		
				ArrayList<node> LestextpSujet = a.retourneNames(nodSujet, "text:p");
				ArrayList<node> LestextpStudent = null;
				if(nodStudent!=null) LestextpStudent = a.retourneNames(nodStudent, "text:p");
				
				table = analyseLesContenusDesArrayList(LestextpStudent,LestextpSujet,table,"ana:tablematiere","txt:p",a);
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
				
				node nodSujet = a.retourneFirstNodeByFindContent2(a.retourneNames(nodSujetTableI, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeByFindContent2(a.retourneNames(nodStudentTableI, "text:index-body"), TitreTable);
		
				ArrayList<node> LestextpSujet = a.retourneNames(nodSujet, "text:p");
				ArrayList<node> LestextpStudent = a.retourneNames(nodStudent, "text:p");
				
				table = analyseLesContenusDesArrayList(LestextpStudent,LestextpSujet,table,"ana:tableillustration","txt:p",a);
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
	 * Cette méthode permet d'analyse la structure du document.<br/>
	 * La structure de l'étudiant est comparé avec la structure du sujet.<br/>
	 * <br/>
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
//				if(pageStudent!=null) {
//					if(nameNode.equals("text:p")) {
//						paragrapheTexte=true;
//						//si le node "text:p" contient un "text:user-defined" alors le recherche par le "text:name" de ce node "text:user-defined"
//						if(nodSujet.containElementByName("text:user-defined")) {
//							String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:user-defined").getAttributs().get("text:name"));
//							nodStudent = pageStudent.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
//							//nodStudent = pageStudent.retourneFirstNodeByNameAndAttributValue("text:user-defined", "text:name", outils.NetChiffreALaFin(valueAttribut));
//						}
//						//si le node "text:p" contient un "text:database-display" alors le recherche par le "text:column-name" de ce node "text:database-display"
//						if(nodSujet.containElementByName("text:database-display")) {
//							String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
//							nodStudent = pageStudent.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
//						}
//						//si le node "text:p" contient un "text:date" alors le recherche par le "text:fixed" de ce node "text:date"
//						if(nodSujet.containElementByName("text:date")) {
//							String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:date").getAttributs().get("text:fixed"));
//							nodStudent = pageStudent.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
//						}
//						
//						if(nodStudent==null) {
//							if(nodSujet.retourneLesContenusEnfants("").isEmpty()) { //s'il n'y a pas de contenu, passe par l'index
//								if(nodSujet.getAttributs().get("index")!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//							}else {
//								nodStudent = a.retourneFirstNodeByFindContent2(pageStudent.getNodes(), nodSujet.retourneLesContenusEnfants(""));
//							}
//						}
//					}
//					if(nameNode.equals("text:h")) {
//						nodStudent = a.retourneFirstNodeByFindContent2(pageStudent.getNodes(), nodSujet.retourneLesContenusEnfants(""));
//					}
//					
//					
//					if(nameNode.equals("text:section")) {
//						//recherche par text:name exacte mais nettexte
//						nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(pageStudent, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
//						if(nodStudent==null) {
//							//recherche si le nnme contient dans la page student
//							nodStudent = a.retourneFirstNodeByNameAttributContainsValueNetTexte(pageStudent, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
//						}
//						if(nodStudent==null) {
//							//recherche si le name contient dans le node student
//							nodStudent = a.retourneFirstNodeByNameAttributContainsValueNetTexte(nodStudentS, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
//						}
//					}
//					
//					if(nameNode.equals("text:database-display")) {
//						nodStudent = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
//					}
//					
//					if(nameNode.equals("draw:frame")) {
//						nodStudent = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode, "draw:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("draw:name")));
//					}
//					
//					if(nodStudent==null) {
//						if(pageStudent.retourneFirstEnfantsByName(nameNode).getNomElt().equals(nameNode)) {
//							nodStudent = pageStudent.retourneFirstEnfantsByName(nameNode);
//						}
//					}
//					
//					
//				}
				
				nodStudent = rechercheLeNodeEnCascade(nameNode,nodSujet,null,null,pageStudent,a);
				
				
				//insère un saut s'il y a un titre avec un saut=true
				page = addNodeSautTitre(nodSujet, page);

				// analyse attribut et contenu des enfants du premier niveau
				page = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, page, "ana:page",nodSujet.getNomElt());
				
				
				//********************************
				// analyse du style du paragarphe
				// Il faut déplacer dans une nouvelle méthode
				//analyse du style du paragraphe niveau 2 mais correspond au premier niveu  du node <text:p> dans la structure de la page
				if(nameNode.equals("text:p") && nodSujetParagraphs!=null && nodStudent!=null) {
					node StyleParagraphSujet = null;
					node StyleParagraphStudent = null;
					if(nodSujet.getAttributs().get("analyseStyle")!=null) {
						if(nodSujet.getAttributs().get("analyseStyle").equals("true") && nodSujet.getAttributs().get("text:style-name")!=null) {
							//paragrapheTexte=true;
							String StyleParagrapheSujet = nodSujet.getAttributs().get("text:style-name");
							StyleParagraphSujet = nodSujetParagraphs.retourneFirstNodeByNameAndAttributValue("style:style", "style:name", StyleParagrapheSujet);
						}
						if(nodStudent.getAttributs().get("text:style-name")!=null && StyleParagraphSujet!=null) {
							String StyleParagrapheStudent = nodStudent.getAttributs().get("text:style-name");
							StyleParagraphStudent = nodStudentParagraphs.retourneFirstNodeByNameAndAttributValue("style:style", "style:name", StyleParagrapheStudent);
						}
						// ajoute les valeurs par héritage.
						//StyleParagraphSujet = ajouteValeurLesValeursDuStyleParagraphParent(nodSujetParagraphs , StyleParagraphSujet);
						StyleParagraphStudent = ajouteValeurLesValeursDuStyleParagraphParent(nodStudentParagraphs , StyleParagraphStudent);
						
						//ajoute les valeurs par défaut.
						
						
						// analyse attribut et contenu des enfants du premier niveau
						page = analyseLesAttributAnalyseStyle(StyleParagraphStudent, StyleParagraphSujet, page, "ana:page","style:style");
					}
				}

					//***************************
					//troisieme niveau
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) {
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						node nod2Student = null;
//						if(nodStudent!=null) {
//							if(nameNode2.equals("text:user-defined") && paragrapheTexte) {
//								nod2Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode2, "text:name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:name")));
//								if(nod2Student==null) {
//									nod2Student = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode2, "text:name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:name")));
//								}
//							}
//							if(nameNode2.equals("text:database-display")) {
//								nod2Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode2, "text:column-name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:column-name")));
//								if(nod2Student==null) {
//									nod2Student = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode2, "text:column-name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:column-name")));
//								}
//							}
//							if(nameNode2.equals("table:table-row")) {
//								nod2Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode2, "index", nod2Sujet.getAttributs().get("index"));
//							}
//							if(nameNode2.equals("text:section")) {
//								//recherche par text:name exacte mais nettexte
//								nod2Student = a.retourneFirstNodeByNameAttributValueNetTexte(nodStudent, nameNode2, "text:name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:name")));
//								if(nod2Student==null) {
//									//recherche si le name contient dans la page student
//									nod2Student = a.retourneFirstNodeByNameAttributContainsValueNetTexte(nodStudent, nameNode2, "text:name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:name")));
//								}
//								if(nod2Student==null) {
//									//recherche si le name contient dans le node student
//									nod2Student = a.retourneFirstNodeByNameAttributContainsValueNetTexte(pageStudent, nameNode, "text:name", outils.withoutCodeAndPoint(nod2Sujet.getAttributs().get("text:name")));
//								}
//							}
//							if(nameNode2.equals("text:h")) {
//								nod2Student = a.retourneFirstNodeByFindContent2(nodStudent.getNodes(), nod2Sujet.retourneLesContenusEnfants(""));
//								if(nod2Student==null) {
//									nod2Student = a.retourneFirstNodeByFindContent2(pageStudent, nod2Sujet.retourneLesContenusEnfants(""));	
//								}
//							}
//							if(nameNode2.equals("text:p") || nameNode2.equals("text:span")) {
//								if(nod2Sujet.retourneLesContenusEnfants("").isEmpty()) {
//									nod2Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode2, "index", nod2Sujet.getAttributs().get("index"));
//								}else {
//									nod2Student = a.retourneFirstNodeByFindContent2(nodStudent.getNodes(),nod2Sujet.retourneLesContenusEnfants("") );
//									if(nod2Student==null) {
//										nod2Student = a.retourneFirstNodeByFindContent2(pageStudent.getNodes(),nod2Sujet.retourneLesContenusEnfants("") );
//									}
//								}
//							}
//							
//							if(nod2Student==null) {
//								if(pageStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
//									nod2Student = pageStudent.retourneFirstEnfantsByName(nameNode2);
//								}
//							}
//
//						}
						
						nod2Student = rechercheLeNodeEnCascade(nameNode2,nod2Sujet,null,pageStudent,nodStudent,a);
						
						//insère un saut s'il y a un titre avec un saut=true
						page = addNodeSautTitre(nod2Sujet, page);
						
						// analyse attribut et contenu des enfants du second niveau
						page = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, page, "ana:page",nod2Sujet.getNomElt() );
						
						//************************
						//quatrieme niveau
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) {
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							node nod3Student = null;	
//							if(nod2Student!=null) {
//								if(nameNode3.equals("table:table-cell")) {
//										nod3Student = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode3, "index", outils.withoutCodeAndPoint(nod3Sujet.getAttributs().get("index")));	
//								}
//								if(nameNode3.equals("text:database-display")) {
//									nod3Student = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode3, "text:column-name", outils.withoutCodeAndPoint(nod3Sujet.getAttributs().get("text:column-name")));
//									if(nod3Student==null) {
//										nod3Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode3, "text:column-name", outils.withoutCodeAndPoint(nod3Sujet.getAttributs().get("text:column-name")));
//									}
//									if(nod3Student==null) {
//										nod3Student = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode3, "text:column-name", outils.withoutCodeAndPoint(nod3Sujet.getAttributs().get("text:column-name")));
//									}
//								}
//								if(nameNode3.equals("text:p")) {
//									//si le node "text:p" contient un "text:database-display" alors le recherche par le "text:column-name" de ce node "text:database-display"
//									if(nod3Sujet.containElementByName("text:database-display")) {
//										String valueAttribut = outils.withoutCodeAndPoint(nod3Sujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
//										nod3Student = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
//									}
//									if(nod3Student==null) {
//										if(nod3Sujet.retourneLesContenusEnfants("").isEmpty()) { //s'il n'y a pas de contenu, passe par l'index
//											if(nod3Sujet.getAttributs().get("index")!=null) nod3Student = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode3, "index", outils.withoutCodeAndPoint(nod3Sujet.getAttributs().get("index")));
//										}else {
//											nod3Student = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nod3Sujet.retourneLesContenusEnfants(""));
//											if(nod3Student==null) {
//												nod3Student = a.retourneFirstNodeByFindContent2(nodStudent.getNodes(), nod3Sujet.retourneLesContenusEnfants(""));	
//											}
//										}
//									}
//								}
//								
//								if(nod3Student==null) {
//									if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
//										nod3Student = nod2Student.retourneFirstEnfantsByName(nameNode3);
//									}
//								}
//
//							}
							
							nod3Student = rechercheLeNodeEnCascade(nameNode3,nod3Sujet,pageStudent,nodStudent,nod2Student,a);
							
							//insère un saut s'il y a un titre avec un saut=true
							page = addNodeSautTitre(nod3Sujet, page);
							
							// analyse attribut et contenu des enfants du troisième niveau
							page = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, page, "ana:page", nod3Sujet.getNomElt());
						
							//****************
							//cinquième niveau
							for(int m = 0 ; m < nod3Sujet.getNodes().size();m++) {
								node nod4Sujet = nod3Sujet.getNodes().get(m);
								String nameNode4 = nod4Sujet.getNomElt();
								node nod4Student = null;
//								if(nod3Student!=null) {
//									if(nameNode4.equals("text:database-display")) {
//										nod4Student = a.retourneFirstNodeByNameAttributValue(nod3Student, nameNode4, "text:column-name", outils.withoutCodeAndPoint(nod4Sujet.getAttributs().get("text:column-name")));
//										if(nod4Student==null) {
//											nod4Student = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode4, "text:column-name", outils.withoutCodeAndPoint(nod4Sujet.getAttributs().get("text:column-name")));
//										}
//										if(nod4Student==null) {
//											nod4Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode4, "text:column-name", outils.withoutCodeAndPoint(nod4Sujet.getAttributs().get("text:column-name")));
//										}
//										if(nod4Student==null) {
//											nod4Student = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode4, "text:column-name", outils.withoutCodeAndPoint(nod4Sujet.getAttributs().get("text:column-name")));
//										}
//									}
//									if(nameNode4.equals("text:p")) {
//										//si le node "text:p" contient un "text:database-display" alors le recherche par le "text:column-name" de ce node "text:database-display"
//										if(nod4Sujet.containElementByName("text:database-display")) {
//											String valueAttribut = outils.withoutCodeAndPoint(nod4Sujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
//											nod4Student = nod3Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
//										}
//										if(nod4Student==null) {
//											if(nod4Sujet.retourneLesContenusEnfants("").isEmpty()) {
//												nod4Student = a.retourneFirstNodeByNameAttributValue(nod3Student, nameNode4, "index", nod4Sujet.getAttributs().get("index"));
//											}else {
//												nod4Student = a.retourneFirstNodeByFindContent2(nod3Student.getNodes(),nod4Sujet.retourneLesContenusEnfants("") );
//												if(nod4Student==null) {
//													nod4Student = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(),nod4Sujet.retourneLesContenusEnfants("") );
//												}
//											}
//										}
//									}
//									
//									if(nod4Student==null) {
//										if(nod3Student.retourneFirstEnfantsByName(nameNode4).getNomElt().equals(nameNode4)) {
//											nod4Student = nod3Student.retourneFirstEnfantsByName(nameNode4);
//										}
//									}
//								}
								
								nod4Student = rechercheLeNodeEnCascade(nameNode4,nod4Sujet,nodStudent,nod2Student,nod3Student,a);

								//insère un saut s'il y a un titre avec un saut=true
								page = addNodeSautTitre(nod4Sujet, page);

								// analyse attribut et contenu des enfants du troisième niveau
								page = analyseLesAttributEtContenuDuNode(nod4Student, nod4Sujet, page, "ana:page", nod4Sujet.getNomElt());
							
								
								//************
								// Sixieme niveau
								for(int p = 0 ; p < nod4Sujet.getNodes().size();p++) {
									node nod5Sujet = nod4Sujet.getNodes().get(p);
									String nameNode5 = nod5Sujet.getNomElt();
									node nod5Student = null;
//									if(nod4Student!=null) {
//										if(nameNode5.equals("text:database-display")) {
//											nod5Student = a.retourneFirstNodeByNameAttributValue(nod4Student, nameNode5, "text:column-name", outils.withoutCodeAndPoint(nod5Sujet.getAttributs().get("text:column-name")));
//											if(nod5Student==null) {
//												nod5Student = a.retourneFirstNodeByNameAttributValue(nod3Student, nameNode5, "text:column-name", outils.withoutCodeAndPoint(nod5Sujet.getAttributs().get("text:column-name")));
//											}
//											if(nod5Student==null) {
//												nod5Student = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode5, "text:column-name", outils.withoutCodeAndPoint(nod5Sujet.getAttributs().get("text:column-name")));
//											}
//											if(nod5Student==null) {
//												nod5Student = a.retourneFirstNodeByNameAttributValue(nodStudent, nameNode5, "text:column-name", outils.withoutCodeAndPoint(nod5Sujet.getAttributs().get("text:column-name")));
//											}
//											if(nod5Student==null) {
//												nod5Student = a.retourneFirstNodeByNameAttributValue(pageStudent, nameNode5, "text:column-name", outils.withoutCodeAndPoint(nod5Sujet.getAttributs().get("text:column-name")));
//											}
//										}
//										if(nameNode5.equals("text:p") || nameNode5.equals("text:span")) {
//											if(nod5Sujet.retourneLesContenusEnfants("").isEmpty()) {
//												nod5Student = a.retourneFirstNodeByNameAttributValue(nod4Student, nameNode5, "index", nod5Sujet.getAttributs().get("index"));
//											}else {
//												nod5Student = a.retourneFirstNodeByFindContent2(nod4Student.getNodes(),nod5Sujet.retourneLesContenusEnfants("") );
//												if(nod5Student==null) {
//													nod5Student = a.retourneFirstNodeByFindContent2(nod3Student.getNodes(),nod5Sujet.retourneLesContenusEnfants("") );
//												}
//											}
//										}
//										
//										if(nod5Student==null) {
//											if(nod4Student.retourneFirstEnfantsByName(nameNode5).getNomElt().equals(nameNode5)) {
//												nod5Student = nod4Student.retourneFirstEnfantsByName(nameNode5);
//											}
//										}
//										
//									}
									
									
									nod5Student = rechercheLeNodeEnCascade(nameNode5,nod5Sujet,nod2Student,nod3Student,nod4Student,a);
									
									//insère un saut s'il y a un titre avec un saut=true
									page = addNodeSautTitre(nod5Sujet, page);

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
	 * Analyse toutes les attributs et du contenu d'un node.<br/>
	 * Les attributs et les contenus doivent posséder la carcatère ‽.<br/>
	 * <br/>
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
		}
		//avec l'attribut allContent=true alors analyse tout le contenu du node
		if(sujet.getAttributs().get("allContent")!=null) if(!sujet.getAttributs().get("allContent").isEmpty()){
			String points ="‽0";
			if(sujet.getAttributs().get("allContent").contains("strict")) points = sujet.getAttributs().get("allContent").replace("strict", "‽");
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
  	 * Analyse tous les attributs des styles de paragraphes.<br/>
	 * Formatage direct des style de paragraphe. Les attribut doivenet contenir le code ‼.<br/>
	 * <br/>
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

		
	
		return retour;
	}
  	
  	
  	
  	
  	
  	
  	
  	/**
  	 * Analyse toutes les contenus des enfants et les compares.<br/>
  	 * Utiliser pour les nodes <text:p><br/>
  	 * <br/>
  	 * @param Student
  	 * @param Sujet
  	 * @param retour
  	 * @param nameItem
  	 * @param nameElt
  	 * @param a
  	 * @return
  	 */
  	private static node analyseLesContenusDesArrayList(ArrayList<node> Student, ArrayList<node> Sujet, node retour, String nameItem, String nameElt, Run a) {
  		
  		for(int i = 0 ; i < Sujet.size();i++) {
  			String sujetContent = Sujet.get(i).retourneLesContenusEnfants("");
  			node StudentNode = a.retourneFirstNodeByFindContent2(Student, outils.withoutCodeAndPoint(sujetContent));
  			String studentContent = "null";
  			if(StudentNode!=null) studentContent = outils.NetChiffreALaFin(StudentNode.retourneLesContenusEnfants(""));
  			
  			node item = retourneNoteAvecResultatsAnalyse(nameItem, "Contenu textuel", studentContent, sujetContent,nameElt);
  			retour.getNodes().add(item);
 			
  		}
  		
  		return retour;
  	}
  	
	
	/**
	 * Retourne le node avec les résultats de la comparaison entre les deux valeurs (étudiant et sujet).<br/>
	 * <br/>
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
		node item = new node(nameNode, Tst, property , valueStudent, valueSujet, niveau, outils.getPointEnJeu(),nameElt);
		return item;
	}
	
 	/**
 	 * Création du feedback (compte-rendu) au format HTML.<br/>
 	 * <br/>
 	 * @param nodana
 	 * @throws IOException
 	 */
 	private static void feedback(node nodana, node verif) throws IOException {
 		
 		System.getProperty("file.encoding","UTF-8");
 		Date aujourdhui = new Date();
 		
 		int number_match = 2;
		boolean plagiat = false;
		node verifStudent = null;
 		if((commandes.verifHisto||commandes.verifHisto2)&&commandes.ecritNoteCSV&&commandes.fourniCSV) {
 			if(verif.getAttributs().get("number_match") != null) number_match = Integer.valueOf(verif.getAttributs().get("number_match"));
 			//verification du plagiat
			verifStudent = verif.retourneFirstNodeByNameAndAttributValue("fichier", "dossier", nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"));
 			if(verifStudent != null) {
 				if(verifStudent.getAttributs().get("filename").equals(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("filename"))) {
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondance"))>number_match) plagiat=true;
 				}
 			}
 		}
 		
 		
 		//nom du fichier feedback
 		String metaS = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet");
 		if(metaS.equals("?")) metaS = "metaSujet-inconnu";
 		if(metaS.isEmpty()) metaS = "metaSujet-inconnu";
 		String cheminFeedBack = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier") + "-DateLong" + aujourdhui.getTime()+"-"+metaS;
  		if(!commandes.noNote) {
  			if(!plagiat)cheminFeedBack = cheminFeedBack + "-" + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note") + ".html";
  			if(plagiat) cheminFeedBack = cheminFeedBack + "- plagiat.html";
  		}else {
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
		
		fichier.write("<meta name=\"generator\" content=\"AnalyseMEPTL V1.0\"/>"
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
				+ "a:link { color: #0000ff; so-language: zxx; text-decoration: underline; margin-left: 10px; }" 
				+ "a:visited { color: #800000; so-language: zxx; text-decoration: underline; margin-left: 10px; }"
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
				+ ".tooltip2 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip2 .tooltiptext2 {visibility: hidden;background-color: black;color: #fff;text-align: left;border-radius: 8px;padding: 5px 0;position: absolute;z-index: 1;margin-left: -40px; width: 340px;top: 100%;left: 10%;}"
				+ ".tooltip2 .tooltiptext2::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
				+ ".tooltip2:hover .tooltiptext2 {visibility: visible;}"
				+ ".footer {position: fixed;left: 0;bottom: 0;width: 100%;background-color: white;color: black;text-align: center;}"
				+"</style>");
		
		fichier.write("</head>\r");
		fichier.write("<body lang=\"fr-FR\" link=\"#000080\" vlink=\"#800000\" dir=\"ltr\">\r");
		
		fichier.write("<div class=\"header\">");
		if(!commandes.noLogo) {
			fichier.write("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br/>"+HTML.imgLogos()+"</font></h1>\r");
		}else {
			fichier.write("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br/></font></h1>\r");
		}
		
		
		//Note
		node ouvre = nodana.retourneFirstEnfantsByName("ouverture");
		String noteFrom = ouvre.getAttributs().get("notefrom");
		if(noteFrom ==null) noteFrom="20";
		if(!commandes.noNote) if(!plagiat) fichier.write("<p><spanpablo>" +  nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note") + " / " + noteFrom +"<br/><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
		if(!commandes.noNote) if(plagiat) fichier.write("<p><spanpablo> Plagiat / " + noteFrom +"<br/><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
		 
		
		//informations
		// date d'analyse, dossier étudiant, auteur sujet, date de la dernière modificatio, lien, algorithme
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		LocalDateTime dateTimeModif = null;
		String dateModif="";
		if(!ouvre.getAttributs().get("dateModification").isEmpty()) {
			dateTimeModif = LocalDateTime.parse(ouvre.getAttributs().get("dateModification"));
			dateModif = dateTimeModif.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		}
		
		fichier.write("<h4>Date d'analyse : "+ mediumDateFormat.format(aujourdhui) + "<br/>");
	    fichier.write("Dossier étudiant : <span style=\"color:blue\">"+ ouvre.getAttributs().get("dossier") + "</span><br/>");
	    fichier.write("Nom du fichier analysé et évalué : <span style=\"color:blue\">"+ ouvre.getAttributs().get("filename") + "</span><br/>");
	    fichier.write("Date de la dernière modification du fichier analysé : <span style=\"color:purple\">"+ dateModif + "</span><br/>");
	    fichier.write("Durée d'édition du fichier analysé : <span style=\"color:purple\">"+ traitementDureeEdition(ouvre.getAttributs().get("dureeEdition") + "</span><br/>"));
	    if(!auteurSujet.isEmpty()) {fichier.write("Sujet créé par : <span style=\"color:blue\">"+ auteurSujet + "</span><br/>");}else {fichier.write("<br/>");}
		   
	    if(!commandes.noNote) if(!plagiat) fichier.write("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br/>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : " + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("proportioncorrect") +"<br/>");
	    if(!commandes.noNote) if(plagiat) fichier.write("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br/>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : plagiat <br/>");
	    	
	    if(ouvre.getAttributs().get("link_sujet")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_sujet");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.write("<br/><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le sujet</a><br/>");}
		}
  	  if(ouvre.getAttributs().get("link_help")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_help");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.write("<br/><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le support</a><br/>");}
		}
  	    
  	    fichier.write("<br/><font color=\"#808080\" style=\"font-size: 9pt\"><i>Analysé avec la version : " + commandes.version + "<br/></h4>");
		
		
	    fichier.write(HTML.SautLigne());
	   
	    fichier.write("</div>");
	   
	    //ajoute le menu 
	    fichier.write(HTML.getHTMLmenu(nodana.retourneFirstEnfantsByName("menu").getNodes()));
	   
		
		//Les erreurs
		node errors = nodana.retourneFirstEnfantsByName("erreurs");
	    if(Boolean.valueOf(errors.getAttributs().get("oneError"))) {
	    	fichier.write(HTML.SautLigne());
    		if(Boolean.valueOf(errors.getAttributs().get("manqueHistorique"))) fichier.write(HTML.Paragraph_classp5("ERREUR : Il n'y a pas d'historique des modifications dans ce fichier. Le fichier n'a pas été modifié ou il a été réinitialisé."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueCreationDate"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La date de création du fichier a été supprimée."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurCreationDate"))) fichier.write(HTML.Paragraph_classp5("ERREUR : Ce n'est pas la bonne date de création du fichier. Le fichier a été réinitialisé ou ce n'est pas le bon fichier."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueMetaSujet"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La méta donnée \"Sujet\" dans les propriétés du fichier a été supprimée ou renommée."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurMetaSujet"))) fichier.write(HTML.Paragraph_classp5("ERREUR : La valeur de la méta donnée \"Sujet\" dans les propriétés du fichier n'est pas \"" + nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet"))+"\"");
		    
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
		 
		 //Ajoute de commentaire de l'exercice
		 fichier.write(HTML.H3(nodana.getContenu()).replace("-NewLine-", "<br>"));
		 
		 fichier.write(HTML.SautLigne());
		 
		    
		 fichier.write(HTML.H2("Synthèse"));
		
		 fichier.write(HTML.TableEnteteTableurSynthese());
		 String IdError = ""; // permet de récupérer les id des menu ou la proportioncorrect est NaN. (à cause de l'attribut analyseStyle=true)
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
		 
		 fichier.write("<p><br/><br/></p>");
		 
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
 	 * Retourne le nom du fichier de l'étudiant pour le Zip de Moodle.<br/>
 	 * </br>
 	 * @param filename
 	 * @param nodana
 	 * @return
 	 */
 	private static String retourneLeNomDuFeedback( String filename,node nodana, node verif) {
 		System.getProperty("file.encoding","UTF-8");
 		
 		int number_match = 2;
		boolean plagiat = false;
		node verifStudent = null;
 		if((commandes.verifHisto||commandes.verifHisto2)&&commandes.ecritNoteCSV&&commandes.fourniCSV) {
 			if(verif.getAttributs().get("number_match") != null) number_match = Integer.valueOf(verif.getAttributs().get("number_match"));
 			//verification du plagiat
			verifStudent = verif.retourneFirstNodeByNameAndAttributValue("fichier", "dossier", nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"));
 			if(verifStudent != null) {
 				if(verifStudent.getAttributs().get("filename").equals(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("filename"))) {
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondance"))>number_match) plagiat=true;
 				}
 			}
 		}
 		
 		//nom du fichier feedback
 		String metaS = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet");
 		if(metaS.equals("?")) metaS = "metaSujet-inconnu";
 		if(metaS.isEmpty()) metaS = "metaSujet-inconnu";
 		String cheminFeedBack = nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier") +"-"+metaS;  //+ "-DateLong" + aujourdhui.getTime()
  		
 		if(!commandes.noNote) {
  			if(!plagiat) cheminFeedBack = cheminFeedBack + "-" + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note") + ".html";
  			if(plagiat) cheminFeedBack = cheminFeedBack + "-Plagiat.html";
 		}else {
  			cheminFeedBack = cheminFeedBack + ".html";
  		}
 		
 		return filename + cheminFeedBack;
 	}
 	
 	
 	/**
 	 * Ecriture du fichier pour l'archive ZIP de moodle.<br/>
 	 * <br/>
 	 * @param nodana
 	 * @return
 	 * @throws IOException
 	 */
 	private static StringBuilder feedbackForZip(node nodana, node verif) throws IOException {
 		
 		System.getProperty("file.encoding","UTF-8");
 		Date aujourdhui = new Date();
 		
 		int number_match = 2;
		boolean plagiat = false;
		node verifStudent = null;
 		if((commandes.verifHisto||commandes.verifHisto2)&&commandes.ecritNoteCSV&&commandes.fourniCSV) {
 			if(verif.getAttributs().get("number_match") != null) number_match = Integer.valueOf(verif.getAttributs().get("number_match"));
 			//verification du plagiat
			verifStudent = verif.retourneFirstNodeByNameAndAttributValue("fichier", "dossier", nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"));
 			if(verifStudent != null) {
 				if(verifStudent.getAttributs().get("filename").equals(nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("filename"))) {
 					if(Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondance"))>number_match) plagiat=true;
 				}
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
				+ "a:link { color: #0000ff; so-language: zxx; text-decoration: underline; margin-left: 10px; }" 
				+ "a:visited { color: #800000; so-language: zxx; text-decoration: underline; margin-left: 10px; }"
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
				+ ".tooltip2 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
				+ ".tooltip2 .tooltiptext2 {visibility: hidden;background-color: black;color: #fff;text-align: left;border-radius: 8px;padding: 5px 0;position: absolute;z-index: 1;margin-left: -40px; width: 340px;top: 100%;left: 10%;}"
				+ ".tooltip2 .tooltiptext2::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
				+ ".tooltip2:hover .tooltiptext2 {visibility: visible;}"
				+ ".footer {position: fixed;left: 0;bottom: 0;width: 100%;background-color: white;color: black;text-align: center;}"
				+"</style>");
		
		fichier.append("</head>\r");
		fichier.append("<body lang=\"fr-FR\" link=\"#000080\" vlink=\"#800000\" dir=\"ltr\">\r");
		
		fichier.append("<div class=\"header\">");
		if(!commandes.noLogo) {
			fichier.append("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br/>"+HTML.imgLogos()+"</font></h1>\r");
		}else {
			fichier.append("<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - AnalyseWriter - format ODF 1.2<br/></font></h1>\r");
		}
		
		//Note
		node ouvre = nodana.retourneFirstEnfantsByName("ouverture");
		String noteFrom = ouvre.getAttributs().get("notefrom");
		if(noteFrom ==null) noteFrom="20";
		if(!commandes.noNote) if(!plagiat) fichier.append("<p><spanpablo>" +  nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note") + " / " + noteFrom +"<br/><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");
		if(!commandes.noNote) if(plagiat) fichier.append("<p><spanpablo> Plagiat / " + noteFrom +"<br/><span style=\"color:blue; font-size:30px\">"+ ouvre.getAttributs().get("metaSujet") +"</span></spanpablo></p>\r");

		 
		//informations
		// date d'analyse, dossier étudiant, auteur sujet, date de la dernière modificatio, lien, algorithme
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
		LocalDateTime dateTimeModif = null;
		String dateModif="";
		if(!ouvre.getAttributs().get("dateModification").isEmpty()) {
			dateTimeModif = LocalDateTime.parse(ouvre.getAttributs().get("dateModification"));
			dateModif = dateTimeModif.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
		}
		
		fichier.append("<h4>Date d'analyse : "+ mediumDateFormat.format(aujourdhui) + "<br/>");
	    fichier.append("Dossier étudiant : <span style=\"color:blue\">"+ ouvre.getAttributs().get("dossier") + "</span><br/>");
	    fichier.append("Nom du fichier analysé et évalué : <span style=\"color:blue\">"+ ouvre.getAttributs().get("filename") + "</span><br/>");
	    fichier.append("Date de la dernière modification du fichier analysé : <span style=\"color:purple\">"+ dateModif + "</span><br/>");
	    fichier.append("Durée d'édition du fichier analysé : <span style=\"color:purple\">"+ traitementDureeEdition(ouvre.getAttributs().get("dureeEdition") + "</span><br/>"));
	    if(!auteurSujet.isEmpty()) {fichier.append("Sujet créé par : <span style=\"color:blue\">"+ auteurSujet + "</span><br/>");}else {fichier.append("<br/>");}
		   
	    if(!commandes.noNote) if(!plagiat) fichier.append("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br/>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : " + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("proportioncorrect") +"<br/>");
	    if(!commandes.noNote) if(plagiat) fichier.append("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br/>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : Plagiat <br/>");
	    
	    if(ouvre.getAttributs().get("link_sujet")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_sujet");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.append("<br/><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le sujet</a><br/>");}
		}
  	  if(ouvre.getAttributs().get("link_help")!=null) {
			String linkSujet= ouvre.getAttributs().get("link_help");
			Matcher m = Pattern.compile("^https://.{1,}|^http://.{1,}").matcher(linkSujet);
			if(m.find()) {fichier.append("<br/><a href=\"" + linkSujet + "\" target=\"_blank\">Lien vers le support</a><br/>");}
		}
  	    
  	    fichier.append("<br/><font color=\"#808080\" style=\"font-size: 9pt\"><i>Analysé avec la version : " + commandes.version + "<br/></h4>");
		
		
	    fichier.append(HTML.SautLigne());
	   
	    fichier.append("</div>");
	   
	    //ajoute le menu 
	    fichier.append(HTML.getHTMLmenu(nodana.retourneFirstEnfantsByName("menu").getNodes()));
	   
		
		//Les erreurs
		node errors = nodana.retourneFirstEnfantsByName("erreurs");
	    if(Boolean.valueOf(errors.getAttributs().get("oneError"))) {
	    	fichier.append(HTML.SautLigne());
    		if(Boolean.valueOf(errors.getAttributs().get("manqueHistorique"))) fichier.append(HTML.Paragraph_classp5("ERREUR : Il n'y a pas d'historique des modifications dans ce fichier. Le fichier n'a pas été modifié ou il a été réinitialisé."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueCreationDate"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La date de création du fichier a été supprimée."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurCreationDate"))) fichier.append(HTML.Paragraph_classp5("ERREUR : Ce n'est pas la bonne date de création du fichier. Le fichier a été réinitialisé ou ce n'est pas le bon fichier."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueMetaSujet"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La méta donnée \"Sujet\" dans les propriétés du fichier a été supprimée ou renommée."));
	    	if(Boolean.valueOf(errors.getAttributs().get("manqueValeurMetaSujet"))) fichier.append(HTML.Paragraph_classp5("ERREUR : La valeur de la méta donnée \"Sujet\" dans les propriétés du fichier n'est pas \"" + nodana.retourneFirstEnfantsByName("ouverture").getAttributs().get("metaSujet"))+"\"");
		    
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
		 
		 fichier.append("<p><br/><br/></p>");
		 
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
		System.out.println("\t Grade : " +  notation.getAttributs().get("note") + "/" + ouverture.getAttributs().get("notefrom"));
		if(flagError) {
			System.out.println("\t ERROR in student's file.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueHistorique"))) System.out.println("\t ERROR : There is no historic in the file. Perhaps, the file has not been modified or it has been reset by the student.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueCreationDate"))) System.out.println("\t ERROR : This is the wrong file creation date. The file has been reset or it is not the correct file.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueValeurCreationDate"))) System.out.println("\t ERROR : This is the wrong file creation date.");
			
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueMetaSujet"))) System.out.println("\t ERROR : The metadata \"Sujet\" has been deleted in the student's file. It is impossible to identify the exercise.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueValeurMetaSujet"))) System.out.println("\t ERROR : The metadata value of  \"Sujet \" in the student's file is not \"" + ouverture.getAttributs().get("metaSujet")+"\"");
		}
		System.out.println();
 	}
 	
//	/**
//	 * Ajoute au node le contenu du titre avec l'identifiants et le style
//	 * @param nod
//	 * @return
//	 */
// 	private static node placeLeTitre(node nod) {
//		if(nod.getAttributs().get("addmenu")!=null) {
//			if(nod.getAttributs().get("addmenu").equals("true")) {
//				if(nod.getAttributs().get("titre")!=null) {
//					if(!nod.getAttributs().get("titre").isEmpty()) {
//						if(nod.getAttributs().get("styletitre")!=null) {
//							
//							switch (nod.getAttributs().get("styletitre")) {
//							case "H1":
//								nod.addContenu(HTML.H1(nod.getAttributs().get("titre"),nod.getAttributs().get("id")));
//								break;
//							case "H2":
//								nod.addContenu(HTML.H2(nod.getAttributs().get("titre"),nod.getAttributs().get("id")));
//								break;
//							default:
//								nod.addContenu(HTML.Paragraph_classp1(nod.getAttributs().get("titre"),nod.getAttributs().get("id")));
//								break;
//							}
//							
//						}else {
//							nod.addContenu(HTML.H1(nod.getAttributs().get("titre"),nod.getAttributs().get("id")));
//						}
//					}
//			
//				}
//			}
//	
//		}else {
//			if(nod.getAttributs().get("titre")!=null) {
//				if(!nod.getAttributs().get("titre").isEmpty()) {
//					if(nod.getAttributs().get("styletitre")!=null) {
//						
//						switch (nod.getAttributs().get("styletitre")) {
//						case "H1":
//							nod.addContenu(HTML.H1(nod.getAttributs().get("titre")));
//							break;
//						case "H2":
//							nod.addContenu(HTML.H2(nod.getAttributs().get("titre")));
//							break;
//						default:
//							nod.addContenu(HTML.Paragraph_classp1(nod.getAttributs().get("titre")));
//							break;
//						}
//						
//					}else {
//						nod.addContenu(HTML.H1(nod.getAttributs().get("titre")));
//					}
//				}
//		
//			}
//		}
//		return nod;
//	}
	
	/**
	 * Place au node le contenu saut de ligne<br/>
	 * <br/>
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
			
			System.out.println(LesFichiers.get(i).getAttributs().get("dossier") + " -  number of modifications : " + nombreModifications);
			
			@SuppressWarnings("unchecked")
			ArrayList<node> LesFichiers2 = (ArrayList<node>) LesFichiers.clone();
			LesFichiers2.remove(i);
			
			for(int j = 0 ; j < HitoriqueDuFichier.size(); j++) {
				String dcdate1 = HitoriqueDuFichier.get(j).retourneFirstEnfantsByName("dc:date").getContenu();
				String dccreator = HitoriqueDuFichier.get(j).retourneFirstEnfantsByName("dc:creator").getContenu();
				
				node N1 = HitoriqueDuFichier.get(j).getNodes().get(0);
				
				for(int i2 = 0 ; i2 < LesFichiers2.size(); i2++) {
					String nameStudent2 = LesFichiers2.get(i2).getAttributs().get("dossier");
					ArrayList<node> HitoriqueDuFichier2 = LesFichiers2.get(i2).retourneEnfantsByName("text:changed-region", new ArrayList<node>());
					for(int j2 = 0 ; j2 <HitoriqueDuFichier2.size(); j2++ ) {
						String dcdate2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName("dc:date").getContenu();
						String dccreator2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName("dc:creator").getContenu();
						node N2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName(N1.getNomElt());
						if(dcdate1.equals(dcdate2) && N1.getNomElt().equals(N2.getNomElt()) && dccreator.equals(dccreator2)) {
							if(nodStudent.retourneFirstNodeByNameAndAttributValue("correspondance", "date", dcdate2)==null) {
								//affinage du match
								compteurnombreCorrespondance++;
								node correspondance = new node();
								correspondance.setNomElt("correspondance");
								correspondance.getAttributs().put("date", dcdate1);
								correspondance.getAttributs().put("type",N2.getNomElt());
								correspondance.getAttributs().put("Avec_etudiant", nameStudent2);
								correspondance.getAttributs().put("dc:creator", dccreator2);
								nodStudent.getNodes().add(correspondance);
								System.out.println("** Find a match ** " + dcdate1);
								break;
							}
						}
					}
					
				}
			}
			
			nodStudent.getAttributs().put("nombre_correspondance", String.valueOf(compteurnombreCorrespondance));
			verif.getNodes().add(nodStudent);
			
		}
		
		return verif;
		
	}
	
	/**
	 * Ecriture de l'ensemble des notes brut sans vérification et sans rechercher les identifiants des étudiants
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
			
			fichier.write(identification + ";" + dateModif + ";" + producteur + ";" + traitementDureeEdition(dureeEdition) + ";"+ sujet + ";" + traitementNote(note) + "\n");
			
		}
		fichier.close();
	}
	
	/**
	 * Ecriture du fichier CSV avec vérification mais sans rechercher les identifiants des étudiants
	 * @param ana
	 * @param verif
	 * @param a
	 * @throws IOException
	 */
	private static void ecritureCSV(node ana, node verif, Run a) throws IOException{
		
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
			
			node verifStudent = a.retourneNodeByNameAttributValueAttributValueExact(verif, "fichier", "dossier", identification, "dateModification", dateModif);
			String note = "plagiat";
			if(verifStudent!=null) {
				int nbreCorrespondance = Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondance"));
				if(nbreCorrespondance<=2) {
					note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note");
				}
			fichier.write(identification + ";" + dateModif + ";" + producteur + ";" + traitementDureeEdition(dureeEdition) + ";"+ sujet + ";" + traitementNote(note) + "\n");
			}

		}
		fichier.close();
	}
	
	
	/**
	 * Ecriture du fichier CSV avec ou sans vérification mais avec rechercher des identifiants des étudiants.<br/>
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
		String champMoodleEmail = "Adresse de courriel";
		String champMoodleNumeroEtudiant = "Numéro d'identification";
		String champPrenom = "prénom";
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
		fichier.write("prénom nom;email;identifiant;date modification;producteur;durée edition;sujet;note\n");
		
		for (int i = 0 ; i < ana.getNodes().size() ; i++) {
			node nodouverture =  ana.getNodes().get(i).retourneFirstEnfantsByName("ouverture");
			String identification = nodouverture.getAttributs().get("dossier");
			String sujet = nodouverture.getAttributs().get("metaSujet");
			String dateModif = nodouverture.getAttributs().get("dateModification");
			String producteur = nodouverture.getAttributs().get("producteur");
			String dureeEdition = nodouverture.getAttributs().get("dureeEdition");
			//String feedback = nodouverture.getAttributs().get("feedback");
			String mail = "-";
			String numeroEtudiant = "-";
			
			node verifStudent = null;
			
			if(commandes.verifHisto2) verifStudent = a.retourneNodeByNameAttributValueAttributValueExact(verif, "fichier", "dossier", identification, "dateModification", dateModif);
			String note = "plagiat";
			
			// rechercher les correspondances avec le prénom et le nom de l'étudiant
			String[] ident = identification.split(" ");
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
				int nbreCorrespondance = Integer.valueOf(verifStudent.getAttributs().get("nombre_correspondance"));
				if(nbreCorrespondance<=2) {
					note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note");
				}
				fichier.write(identification + separator + mail + separator + numeroEtudiant + separator + dateModif + separator + producteur + separator + traitementDureeEdition(dureeEdition) + separator + sujet + separator + traitementNote(note) + "\n");
			}
			
			if(!commandes.verifHisto2) {
				note = ana.getNodes().get(i).retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("note");
				fichier.write(identification + separator + mail + separator + numeroEtudiant + separator + dateModif + separator + producteur + separator + traitementDureeEdition(dureeEdition) + separator + sujet + separator + traitementNote(note) + "\n");
			}
			

		}
		fichier.close();
	}
	
	/**
	 * Traitement de la durée d'édition.<br/>
	 * <br/>
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
	 * Remplace la virgule par un point.<br/>
	 * <br/>
	 * @param note
	 * @return
	 */
	private static String traitementNote(String note) {
		note = note.replace(",", ".");
		return note;
	}
	
	/**
	 * Chargement dans un node du fichier CSV.<br/>
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
	 * Ajoute les valeurs par défauts pour les styles de paragraphes.<br/>
	 * <br/>
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
	 * Même méthode que "ajouteValeurParDefautAuStyleParagraph".<br/>
	 * <br/>
	 * @param parent
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
	 * Ce node permet la configuration personnalisé de l'application.<br/>
	 * Ajoute le node setting avec les différentes valeurs.<br/>
	 * <br/>
	 * @param sujet : le node du sujet
	 * @return retourne le node du sujet avec le node setting
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
		export.getAttributs().put("email", "Adresse de courriel");
		export.getAttributs().put("id", "Numéro d'identification");
		export.getAttributs().put("firstname", "prénom");
		export.getAttributs().put("name", "nom");
		export.setClose(true);
		
		//node taille zip
		node zip = new node();
		zip.setNomElt("zip");
		zip.getAttributs().put("size", "48000000");
		zip.getAttributs().put("nameZip", "48000000");
		zip.isClose();
		
		//node verif
		node plagiarism  = new node();
		plagiarism.setNomElt("plagiarism");
		plagiarism.getAttributs().put("number_match", "2");
		
		//construction du node setting
		csv.getNodes().add(export);
		setting.getNodes().add(csv);
		setting.getNodes().add(zip);
		setting.getNodes().add(plagiarism);
		
		// ajoute le node setting au node sujet
		sujet.getNodes().add(setting);
		
		//fermeture du node
		setting.setClose(true);
		
		return sujet;
	}
	
	
	private static node rechercheLeNodeEnCascade(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		
		node nodStudent =null;
		
		// recherche par différent contenu du node
		if(nameNode.equals("text:p")) {
			//si le node "text:p" contient un "text:user-defined" alors le recherche par le "text:name" de ce node "text:user-defined"
			if(nodSujet.containElementByName("text:user-defined")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:user-defined").getAttributs().get("text:name"));
				if(nod2Student!=null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
			}
			//si le node "text:p" contient un "text:database-display" alors le recherche par le "text:column-name" de ce node "text:database-display"
			if(nodSujet.containElementByName("text:database-display")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
				if(nod2Student!=null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
			}
			//si le node "text:p" contient un "text:date" alors le recherche par le "text:fixed" de ce node "text:date"
			if(nodSujet.containElementByName("text:date")) {
				String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:date").getAttributs().get("text:fixed"));
				if(nod2Student!=null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
			}
			
			if(nodStudent==null) {
				if(nodSujet.retourneLesContenusEnfants("").isEmpty()) { //s'il n'y a pas de contenu, passe par l'index
					if(nodSujet.getAttributs().get("index")!=null) {
						if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
						if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
						if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
					}
				}else {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nodSujet.retourneLesContenusEnfants(""));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), nodSujet.retourneLesContenusEnfants(""));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), nodSujet.retourneLesContenusEnfants(""));
				}
			}
		}
		
		if(nameNode.equals("text:user-defined")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
		}
		
		
		//recherche par le contenu enfant du node
		if(nameNode.equals("text:h")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), nodSujet.retourneLesContenusEnfants(""));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), nodSujet.retourneLesContenusEnfants(""));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), nodSujet.retourneLesContenusEnfants(""));
		}
		
		//recherche par text:name
		if(nameNode.equals("text:section")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
		}
		
		//recherche par le nom de la colonne
		if(nameNode.equals("text:database-display")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
		}
		
		//recherche par le nom de l'objet
		if(nameNode.equals("draw:frame")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("draw:name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("draw:name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("draw:name")));
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
		
		// dernière tentative si le node est vide, recherche par le nom du node
		if(nodStudent==null) {
			if(nod2Student!=null) if(nod2Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod2Student.retourneFirstEnfantsByName(nameNode);
			if(nod1Student!=null) if(nodStudent==null) if(nod1Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod1Student.retourneFirstEnfantsByName(nameNode);
			if(nod0Student!=null) if(nodStudent==null) if(nod0Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod0Student.retourneFirstEnfantsByName(nameNode);
		}
		
		return nodStudent;
	}
		
		

	

	
	
}
	
	

