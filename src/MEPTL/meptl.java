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
//import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import cXML.node;
import cXML.Run;
import cXML.Run.UserStatus;
import net.lingala.zip4j.exception.ZipException;


/**
 * 
 * @author pablo rodriguez
 * 
 *
 */
public class meptl {
	
	static DecimalFormat df = new DecimalFormat("###.##");
	//static String patch ="";
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
				
		commandes.path = System.getProperty("user.dir");
//		patch = "C:/Users/pabr6/OneDrive/Desktop/Nouveau dossier";
		
		//*******************
		//** les commandes **
		//*******************
		new commandes(args,commandes.path);
		
		//** Node pour le chargement du node sujet (fichier d'analyse)
		node nodeSujet = new node();
		
		//** Nouveau node qui permet de convertir le fichier contenant la liste des étudiants en node.
		node nodeCSV = null;
		
		//**********************************
		//** Initialisation des variables **
		//**********************************
		Run a = null;
		Run.path = commandes.path;
		int nbFichierWriter=0;
		
		if(!commandes.calculLeHashDuFichier&&!commandes.ecritSujet) {
			//*****************************************************
			//** Lancement des lectures des dossiers ou fichiers **
			//*****************************************************
			a = new Run(commandes.path,commandes.Profil, commandes.fichierStudentMoodle);
			
			
			//*****************************************
			//** Nombre de fichier writer à analyser **
			//*****************************************
			nbFichierWriter = a.getLectDossiers().getEC().getListeContentWriter().size();
		}
		
		//************
		//** -sujet **
		//************
		if(commandes.ecritSujet) {
			verificationFichierAnalyse.ecrisLeFichierSujetXML();
		}
		
		
		//****************************************************************************
		//** Calcul le hash du fichier d'analyse et met à jour le fichier d'analyse **
		//****************************************************************************
		if(commandes.calculLeHashDuFichier) {
			verificationFichierAnalyse.MiseAJourFichierAnalyse();
		}
		
		
		//*****************
		//** -writefiles **
		//*****************
		if(commandes.writefiles) {
			for(int i = 0 ; i < nbFichierWriter ; i++) {
				//** Chargement du format (content) et transformation en node pour l'application
				node nod = Run.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
				node nodStudent = LectureFichierEtudiantSousFormeDeNode(nod,a,i);
				Run.ecritureNodeEnXML(nodStudent, a.getLectDossiers().getEC().getListeNomDossier().get(i),"",false,""); //écriture du node de l'étudiant
			}
			//** bye bye analyseWriter
			commandes.clotureApplication();
		}
		
		//********************************************************
		//** Ecriture d'une fichier d'analyse : commande -write **
		//********************************************************
		if(commandes.ecritCode) {
			for(int index = 0 ; index < nbFichierWriter ; index++) {
				node nodSujet = Run.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(index));
				nodSujet = LectureFichierEtudiantSousFormeDeNode(nodSujet,a,index);
				nodSujet.getAttributs().put("analysis_filename", a.getLectDossiers().getEC().getListeNomDossier().get(index)+".xml");
				nodSujet = ecritureSujet.nodePourEcritureSujet(nodSujet,a,index);
				Run.ecritureNodeEnXML(nodSujet, a.getLectDossiers().getEC().getListeNomDossier().get(index),"",false,"Sujet");
			}
			//** bye bye analyseWriter
			commandes.clotureApplication();
		}
		
		
		//***********************************************************************************
		//** PREPARATION du node Sujet  pour analyse -use file.xml ou -use file.xml -sujet **
		//***********************************************************************************
		if(commandes.analyse) {
			nodeSujet = chargementsujet(commandes.nameSujet, true);
			
			if(nodeSujet==null) {
				//***************************
				//** bye bye analyseWriter **
				//***************************
				commandes.clotureApplicationAvecErreur();
			}
			
			//** Chargement de la culture **
			if(nodeSujet.retourneFirstEnfantsByName("setting").isHasAttributs()) {
				commandes.culture = nodeSujet.retourneFirstEnfantsByName("setting").getAttributs().get("culture"); //récupère la culture de l'utilisateur
			}

			//** La méthode verificationFichier Analyse permet de détecter des erreurs dans le fichier d'analyse
			new verificationFichierAnalyse(nodeSujet);
			
			if(verificationFichierAnalyse.erreur==true) verificationFichierAnalyse.clotureWithErrorInanalyzeFile();
			//a.ecritureNodeEnXML(nodeSujet, "sujet","",false);  // ecriture du node sujet
			
			//** Nouvelle ecriture du fichier si MAJ fichier**
			if(commandes.MAJFichierAnalyse||commandes.MAJnameAnalysisFile) {
				nodeSujet.getAttributs().put("hash", commandes.hash);
				nodeSujet.getAttributs().put("analysis_filename", commandes.nameSujet);
			}

			
			try {
				
				//***********************
				//** -newlogo file.svg **
				//***********************
				if(commandes.newLogo && !commandes.nameSVG.isEmpty()) {
					commandes.contenuFichierSVG= chargementFichierSVG(a,commandes.nameSVG);
				}
				
				//***********************************************************************
				//** chargement du node translation qui se trouve dans le node setting **
				//***********************************************************************
				outils.chargeTraduction(nodeSujet.retourneFirstEnfantsByName("translation"));
				
				//************************************************
				//** Charge les nouvelles tolérances pour texte **
				//************************************************
				if(nodeSujet.containElementByName("text:similarity")) {
					node similarity = nodeSujet.retourneFirstEnfantsByName("text:similarity");
					if(similarity.getAttributs().get("tolerance_characters")!=null) commandes.tolerance_characters =  Integer.valueOf(similarity.getAttributs().get("tolerance_characters"));
					if(similarity.getAttributs().get("tolerance_text")!=null) commandes.tolerance_text =  Double.valueOf(similarity.getAttributs().get("tolerance_text"));
				}
				
				//***********************************************************
				//** Charge le nombre de match limite et le nombre minimal **
				//***********************************************************
				if(nodeSujet.containElementByName("plagiarism")) {
					node plagiarism = nodeSujet.retourneFirstEnfantsByName("plagiarism");
					if(plagiarism.getAttributs().get("number_match") != null) commandes.number_match = Integer.valueOf(plagiarism.getAttributs().get("number_match"));
					if(plagiarism.getAttributs().get("mini_number_modification") != null) commandes.mini_number_modification = Integer.valueOf(plagiarism.getAttributs().get("mini_number_modification"));
					if(plagiarism.getAttributs().get("nombres_modifications_simultané_maxi") != null) commandes.nombres_modifications_simultané_maxi = Integer.valueOf(plagiarism.getAttributs().get("nombres_modifications_simultané_maxi"));
				}
				
				//**************************************
				//** Charge tolerance pour la couleur **
				//**************************************
				if(nodeSujet.containElementByName("color")) {
					node color = nodeSujet.retourneFirstEnfantsByName("color");
					if(color.getAttributs().get("tolerance_rouge") != null) commandes.tolerance_rouge= Integer.valueOf(color.getAttributs().get("tolerance_rouge"));
					if(color.getAttributs().get("tolerance_vert") != null) commandes.tolerance_vert= Integer.valueOf(color.getAttributs().get("tolerance_vert"));
					if(color.getAttributs().get("tolerance_bleu") != null) commandes.tolerance_bleu= Integer.valueOf(color.getAttributs().get("tolerance_bleu"));
				}
				
				//**********************************************
				//** Chargement et verification du CVS fourni **
				//**********************************************
				if(commandes.fourniCSV) {
					nodeCSV = chargementFichierCSV(a, commandes.nameCSV);
				}
				
			}catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
		//***************************************
		//** -verif ou -use file.xml -verifcsv **
		//***************************************
		node verif  = new node();
		if(commandes.verifHisto || commandes.verifHisto2) {
			node verification = new node();
			verification.setNomElt("verification");
			verification.getAttributs().put("nombre_fichier", String.valueOf(a.getLectDossiers().getEC().getListeFichierodt().size()));
			for(int i = 0 ; i < nbFichierWriter ; i++) {
				node nod = Run.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
				node nodStudent = LectureFichierEtudiantPourVerification(nod,a,i);
				verification.getNodes().add(nodStudent);
			}
			//a.ecritureNodeEnXML(verification, "VerificationHistorique","",false); //écriture du node de l'étudiant
			verif = verificationHistorique(verification, a);  // vérification des correspondances entre les fichiers
			
			//********************************
			//** Ecriture du node verif.xml **
			//********************************
			Run.ecritureNodeEnXML(verif, "Verif",commandes.pathDestination,commandes.fourniDossierDestination, "Verif"); //écriture du node de vérification
			if(!commandes.analyse) {
				//** bye bye analyseWriter
				commandes.clotureApplication();
			}
		}
		
		System.getProperty("file.encoding","UTF-8");
		
		//*********************************************************
		//** Node contenant l'ensemble des analyses des étudiants **
		//*********************************************************
		node ensembleanalyse = new node();
		ensembleanalyse.setNomElt("analyses");

		
		//*****************************************************
		//** Parcours l'ensemble des fichiers des étudiants ***
		//*****************************************************
		for(int i = 0 ; i < nbFichierWriter ; i++) {
			
			//** Ne prends pas en compte le dossier destination créé par la commande -dest
			//** Si pas d'analyse alors le nom doit contenir le caractère $ dans le nom du dossier.
			if(commandes.fourniDossierDestination)if(a.getLectDossiers().getEC().getListeNomDossier().get(i).equals(commandes.pathDestination)) continue;
			
			//***********************************************************
			//** Lecture et transformation en node du fichier étudiant **
			//***********************************************************
			node nod = Run.XMLContent(a.getLectDossiers().getEC().getListeContentWriter().get(i));
			node nodStudent = LectureFichierEtudiantSousFormeDeNode(nod,a,i);
			//a.ecritureNodeEnXML(nodStudent, a.getLectDossiers().getEC().getListeNomDossier().get(i),"",false,""); //écriture du node de l'étudiant

			//**********************************
			//** Analyse des fichiers student **
			//**********************************
			if(commandes.analyse) {
				node init = InitialisationAvantAnalyse(nodeSujet);
				if(!Boolean.valueOf(init.getAttributs().get("erreur"))) {
//					a.ecritureNodeEnXML(nodStudent, "fichier student",patch,false,""); //écriture du node analyse de l'étudiant
					node ana = analyse(nodStudent, nodeSujet, i, a);
//					a.ecritureNodeEnXML(ana, "nodana"+ana.retourneFirstEnfantsByName("ouverture").getAttributs().get("dossier"),"",false,""); //écriture du node analyse de l'étudiant
					
					//****************************
					//** Création des feedbacks **
					//****************************
					if(!commandes.sansFeeback) {
						if(!commandes.zipfeedback) {
							//feedback(ana, verif); //classique directement dans le répertoire
							feedbacks.feedback(ana,verif, false);
						}
						if(commandes.zipfeedback) { // Dans une archive pour Moodle
							try {
								long size = 48000000; //valeur par défaut
								String nameZip = "feedbackMoodle"; //nom zip par défaut
								node zip = nodeSujet.retourneFirstEnfantsByName("zip");
								if(zip!=null) {
									if(zip.getAttributs().get("size")!=null)size = Long.valueOf(zip.getAttributs().get("size"));
									if(zip.getAttributs().get("name")!=null)nameZip = zip.getAttributs().get("name");
								}
									//a.AddStreamToZip(feedbackForZip(ana, verif), retourneLeNomDuFeedback(a.getLectDossiers().getEC().getListeNomFichierFeedBack().get(i),ana, verif),size,nameZip);
									a.AddStreamToZip(feedbacks.feedback(ana, verif, true), retourneLeNomDuFeedback(a.getLectDossiers().getEC().getListeNomFichierFeedBack().get(i),ana, verif),size,nameZip);
							} catch (ZipException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

					//********************************************************************************
					//** Ajoute au node ensembleanalyse lorsque -csv file.csv ou -verifcsv file.scv **
					//********************************************************************************
					if(commandes.ecritNoteCSV) ensembleanalyse.addNode(ana);
					
					//*********************************************************
					//** Message dans la console sur l'analyse de l'étudiant **
					//*********************************************************
					messageSystem(ana);
				}else {
					
				}
				
			}
		}
		
		if(nodeSujet!=null) {
			//*****************************************************
			//** Exportation au format CSV  si -csv ou -verifcsv **
			//*****************************************************
			if(commandes.ecritNoteCSV && !commandes.fourniCSV) {
				if(!commandes.verifHisto2) ecritureCSV(ensembleanalyse);
				if(commandes.verifHisto2) ecritureCSV(ensembleanalyse,verif,a,nodeSujet.retourneFirstEnfantsByName("setting"));
				//a.ecritureNodeEnXML(ensembleanalyse, "ensembleAnalyse"); //écriture du node de l'étudiant
			}
			
			//***********************************************************************
			//** Exportation au format CSV  si -csv file.csv ou -verifcsv file.csv **
			//***********************************************************************
			if(commandes.ecritNoteCSV && commandes.fourniCSV) {
				ecritureCSV(ensembleanalyse,verif,a,nodeCSV, nodeSujet.retourneFirstEnfantsByName("setting"));
				//a.ecritureNodeEnXML(ensembleanalyse, "ensembleAnalyse"); //écriture du node de l'étudiant
			}
			
			//**************************************
			//** Mise à jour du fichier d'analyse **
			//**************************************
			if(commandes.MAJFichierAnalyse||commandes.MAJnameAnalysisFile) {
				verificationFichierAnalyse.MiseAJourFichierAnalyse();
				verificationFichierAnalyse.messagMiseAJourFichierAnalyseAprèsAnalyse();
			}
		}
		
		//***************************
		//** bye bye analyseWriter **
		//***************************
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
		fichier.getAttributs().put("producteur", nodmeta.retourneFirstEnfantsByName("meta:generator").getContenu().get(0));
		fichier.getAttributs().put("dateModification", nodmeta.retourneFirstEnfantsByName("dc:date").getContenu().get(0));
		fichier.getAttributs().put("dureeEdition", nodmeta.retourneFirstEnfantsByName("meta:editing-duration").getContenu().get(0));
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
		node nodbody = nod.retourneFirstEnfantsByName("office:text"); //a.NodeFirstName(nodecontent, "office:text");
//		
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
		
//		// le node des styles de formatage direct
//		node nodstyleformatage = new node();
//		nodstyleformatage.setNomElt("style:formatagedirect");
//		nodstyleformatage.addNode(a.NodesAyantAttribut(nodstyle, "style:family","text")); //c'est redondant puisque déjà présent dans style:paragraph
		
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
		nodsection.addNode(a.retourneNames(nodbody, "text:section"));
		
		// le node table:table
		node nodtableaux = new node();
		nodtableaux.setNomElt("tableaux");
		nodtableaux.addNode(a.retourneNames(nodbody, "table:table"));
		
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
			String titreIndex=A.get(j).retourneFirstEnfantsByName("text:index-title-template").getContenu().get(0).toLowerCase();
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
			String titreIndex=A.get(j).retourneFirstEnfantsByName("text:index-title-template").getContenu().get(0);
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
		
		// Création du node structure à partir du node nodbody
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
		
		if(!nodmeta.retourneFirstEnfantsByName("meta:generator").getContenu().isEmpty()) {
			fichier.getAttributs().put("producteur", nodmeta.retourneFirstEnfantsByName("meta:generator").getContenu().get(0));
		}
		if(!nodmeta.retourneFirstEnfantsByName("meta:editing-duration").getContenu().isEmpty()) {
			fichier.getAttributs().put("dureeEdition", nodmeta.retourneFirstEnfantsByName("meta:editing-duration").getContenu().get(0));
		}
		if(!nodmeta.retourneFirstEnfantsByName("dc:date").getContenu().isEmpty()) {
			fichier.getAttributs().put("dateModification", nodmeta.retourneFirstEnfantsByName("dc:date").getContenu().get(0));
		}
				
		fichier.getNodes().add(nodmeta);
		nodstylepage = a.numeroteNameNode(nodstylepage, "0");		//ajoute les numéros d'index et des attributs
		fichier.getNodes().add(nodstylepage);
		fichier.getNodes().add(nodstyleparagraphe);
//		fichier.getNodes().add(nodstyleformatage);
		fichier.getNodes().add(nodsequence);
		fichier.getNodes().add(nodnumerochapitre);
		fichier.getNodes().add(nodframe);
		
		fichier.getNodes().add(nodsection);
		fichier.getNodes().add(nodtableaux);
		//nodbiblio = a.numeroteNameNode(nodbiblio, "0");			//ajoute les numéros d'index et des attributs
		fichier.getNodes().add(nodbiblio);
		//nodtable = a.numeroteNameNode(nodtable, "0");				//ajoute les numéros d'index et des attributs
		fichier.getNodes().add(nodtable);
		//nodillustrations = a.numeroteNameNode(nodillustrations, "0"); //ajoute les numéros d'index et des attributs
		fichier.getNodes().add(nodillustrations);
		
		structurePage = a.numeroteNameNode(structurePage,"");    //ajoute les numéros d'index et des attributs 
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
	public static node chargementsujet(String nameSujet, Boolean sansNodeEvaluer) throws  CloneNotSupportedException, IOException {
		String targetString = "";
		//read file into stream, try-with-resources

		try {
			BufferedReader br = new BufferedReader(
			        new InputStreamReader(
			            new FileInputStream(Run.path + "/" + nameSujet), "UTF-8")); 
			
			String line;
			while ((line = br.readLine()) != null) {
				targetString = targetString + line;
			}
			br.close();
		}catch (Exception e) {
			System.out.println();
			System.out.println("** Le fichier \"" + nameSujet + "\" n'est pas dans le dossier courant.");
			System.out.println("** Le dossier courant de l'application est : " + commandes.path);
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
			
			LeNodeSujet = Run.XMLContent(targetString);
		}
		
		if(sansNodeEvaluer) {
			LeNodeSujet = Run.NodesAyantAttributEvaluerTRUEavecComplement(LeNodeSujet);
		}
		
		
		
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
		node nodtableaux = new node();
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
		if(nodSujet.getAttributs().get("analysis_filename")!=null) nodouverture.getAttributs().put("filenameAnalyse", nodSujet.getAttributs().get("analysis_filename"));
		if(nodStudent.getAttributs().get("producteur")!=null) nodouverture.getAttributs().put("producteur", nodStudent.getAttributs().get("producteur"));
		if(nodStudent.getAttributs().get("dureeEdition")!=null) nodouverture.getAttributs().put("dureeEdition", nodStudent.getAttributs().get("dureeEdition"));
		if(nodStudent.getAttributs().get("dateModification")!=null) nodouverture.getAttributs().put("dateModification", nodStudent.getAttributs().get("dateModification"));
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
			return clotureNodeAnalyse(nodouverture, nodbodyetnotation, nodmenu, erreurs, nodmeta, nodpage, nodparagraph, nodsequence, nodnumerochapitre, nodframes, nodsections, nodtableaux, nodbiblio, nodtablematieres, nodtableillustrations, nodstructurepage, nodSujet.getContenu().get(0));
		}
		
		// analyse Meta
		if(nodSujet.retourneFirstEnfantsByName("office:meta").getNomElt().equals("office:meta")) {
			nodmeta = analyseLesNodesPrincipaux.analyseLesMeta(nodStudent.retourneFirstEnfantsByName("office:meta"), nodSujet.retourneFirstEnfantsByName("office:meta"), a, nodmenu);
		}

		// analyse les pages (nécessaire d'avoir aussi les styles de paragraphes)
		if(nodSujet.retourneFirstEnfantsByName("style:page").getNomElt().equals("style:page")) {
			if(nodSujet.containElementByName("style:paragraph")) {
				nodpage = analyseLesNodesPrincipaux.analysePage(nodStudent.retourneFirstEnfantsByName("style:page"),  nodSujet.retourneFirstEnfantsByName("style:page"), a, nodmenu,nodSujet.retourneFirstEnfantsByName("style:paragraph"),nodStudent.retourneFirstEnfantsByName("style:paragraph"));
			}else {
				nodpage = analyseLesNodesPrincipaux.analysePage(nodStudent.retourneFirstEnfantsByName("style:page"),  nodSujet.retourneFirstEnfantsByName("style:page"), a, nodmenu,null,null);
			}
		}
		
		// analyse les paragraphes
		if(nodSujet.retourneFirstEnfantsByName("style:paragraph").getNomElt().equals("style:paragraph")) {
			nodparagraph = analyseLesNodesPrincipaux.analyseParagraph(nodStudent.retourneFirstEnfantsByName("style:paragraph"),  nodSujet.retourneFirstEnfantsByName("style:paragraph"), a, nodmenu);
		}
		
		// analyse les variables de séquence
		if(nodSujet.retourneFirstEnfantsByName("sequences").getNomElt().equals("sequences")) {
			nodsequence = analyseLesNodesPrincipaux.analyseLesSequences(nodStudent.retourneFirstEnfantsByName("sequences"),  nodSujet.retourneFirstEnfantsByName("sequences"), a, nodmenu);
		}
		
		// analyse de la numérotation des chapitres
		if(nodSujet.retourneFirstEnfantsByName("numerotationchapitre").getNomElt().equals("numerotationchapitre")) {
			nodnumerochapitre = analyseLesNodesPrincipaux.analyseLaNumerotationChapitre(nodStudent.retourneFirstEnfantsByName("numerotationchapitre"),  nodSujet.retourneFirstEnfantsByName("numerotationchapitre"), a, nodmenu);
		}
		
		// analyse les frames
		if(nodSujet.retourneFirstEnfantsByName("frames").getNomElt().equals("frames")) {
			nodframes = analyseLesNodesPrincipaux.analyseLesFrames(nodStudent.retourneFirstEnfantsByName("frames"),  nodSujet.retourneFirstEnfantsByName("frames"), a, nodmenu);
		}
		
		// analyse des sections
		if(nodSujet.retourneFirstEnfantsByName("sections").getNomElt().equals("sections")) {
			nodsections = analyseLesNodesPrincipaux.analyseLesSections(nodStudent.retourneFirstEnfantsByName("sections"),  nodSujet.retourneFirstEnfantsByName("sections"), a, nodmenu);
		}
		
		// analyse les tableaux
		if(nodSujet.retourneFirstEnfantsByName("tableaux").getNomElt().equals("tableaux")) {
			nodtableaux = analyseLesNodesPrincipaux.analyseLesTableaux(nodStudent.retourneFirstEnfantsByName("tableaux"),  nodSujet.retourneFirstEnfantsByName("tableaux"), a, nodmenu);
		}
		
		// analyse la bibliographie de LibreOffice
		if(nodSujet.retourneFirstEnfantsByName("biblio").getNomElt().equals("biblio")) {
			nodbiblio = analyseLesNodesPrincipaux.analyseLaBiblio(nodStudent.retourneFirstEnfantsByName("biblio"),  nodSujet.retourneFirstEnfantsByName("biblio"), a, nodmenu);
		}
		
		// analyse des tables des matières
		if(nodSujet.retourneFirstEnfantsByName("tablematieres").getNomElt().equals("tablematieres")) {
			nodtablematieres = analyseLesNodesPrincipaux.analyseLesTablesMatieres(nodStudent.retourneFirstEnfantsByName("tablematieres"),  nodSujet.retourneFirstEnfantsByName("tablematieres"), a, nodmenu);
		}
		
		// analyse des tables illustrations
		if(nodSujet.retourneFirstEnfantsByName("tableillustrations").getNomElt().equals("tableillustrations")) {
			nodtableillustrations = analyseLesNodesPrincipaux.analyseLesTablesIllustrations(nodStudent.retourneFirstEnfantsByName("tableillustrations"),  nodSujet.retourneFirstEnfantsByName("tableillustrations"), a, nodmenu);
		}	
		
		// analyse la structure du document
		if(nodSujet.retourneFirstEnfantsByName("structurepage").getNomElt().equals("structurepage")) {
			node nodSujetParagraphs = null;
			if(nodSujet.retourneFirstEnfantsByName("style:paragraph").getNomElt().equals("style:paragraph")) nodSujetParagraphs = nodSujet.retourneFirstEnfantsByName("style:paragraph");
			node nodStudentParagraphs = nodStudent.retourneFirstEnfantsByName("style:paragraph");
			nodstructurepage = analyseLesNodesPrincipaux.analyseStructurePage(nodStudent.retourneFirstEnfantsByName("structurepage"),  nodSujet.retourneFirstEnfantsByName("structurepage"), a, nodmenu,nodSujetParagraphs, nodStudentParagraphs );
		}
		
	
		// retourne le node analyse assemblé et calcul de la note avec le barème
		return clotureNodeAnalyse(nodouverture, nodbodyetnotation, nodmenu, erreurs, nodmeta, nodpage, nodparagraph, nodsequence, nodnumerochapitre, nodframes, nodsections, nodtableaux, nodbiblio, nodtablematieres, nodtableillustrations, nodstructurepage,nodSujet.getContenu().get(0));
	
			
		}
		
		
	/**
	 * Les erreurs dans le fichier étudiant : erreur de métadonnées Sujet, date de création pour identifier le fichier à analyser.
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
	@SuppressWarnings("unlikely-arg-type")
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
					if(!nodSujet.getAttributs().get("metaSujet").equals(b.getContenu().get(0))) {
						manqueValeurMetaSujet=true;
					}
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
	 * @param nodtableaux
	 * @param nodbiblio
	 * @param nodtablematieres
	 * @param nodtableillustrations
	 * @param nodstructurepage
	 * @return
	 */
	private static node clotureNodeAnalyse(node nodouverture, node nodbodyetnotation, node nodmenu, node erreurs, node nodmeta, node nodpage,
			node nodparagraph, node nodsequence, node nodnumerochapitre, node nodframes, node nodsections, node nodtableaux, node nodbiblio, node nodtablematieres,
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
		double pointsection = 0; double pointsectiontotal = 0 ; double poidssection = 0;
		if(nodsections!=null) if(nodsections.isClose()) {
			if(nodsections.getAttributs().get("pointgagner")!=null) pointsection = Double.valueOf(nodsections.getAttributs().get("pointgagner"));
			if(nodsections.getAttributs().get("pointtotal")!=null) pointsectiontotal = Double.valueOf(nodsections.getAttributs().get("pointtotal"));
			if(nodsections.getAttributs().get("poids")!=null) try{poidssection = Math.abs(Double.valueOf(nodsections.getAttributs().get("poids")));}catch (Exception e) {			};
		}
		double pointtableau = 0; double pointtableautotal = 0 ; double poidstableau = 0;
		if(nodtableaux!=null) if(nodtableaux.isClose()) {
			if(nodtableaux.getAttributs().get("pointgagner")!=null) pointsection = Double.valueOf(nodtableaux.getAttributs().get("pointgagner"));
			if(nodtableaux.getAttributs().get("pointtotal")!=null) pointsectiontotal = Double.valueOf(nodtableaux.getAttributs().get("pointtotal"));
			if(nodtableaux.getAttributs().get("poids")!=null) try{poidssection = Math.abs(Double.valueOf(nodtableaux.getAttributs().get("poids")));}catch (Exception e) {			};
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
		
		double proportionCorrect = 0 ;
		double poidsTotal = 0;
		double pointsTotal = 0;
		double pointgagner = 0;
		double note = 0 ;
		

		
		proportionCorrect = (poidsmeta*pointmeta + poidspage*pointpage + poidsparagraph*pointparagraph + poidssequence*pointsequence + poidsnumerotation*pointnumerotation + poidsframe*pointframe + poidsbiblio*pointbiblio + poidstablematieres*pointtablematieres + poidstableillustration*pointtableillustration + poidsstructure*pointstructure + poidssection*pointsection + poidstableau*pointtableau)
				/ (poidsmeta*pointmetatotal + poidspage*pointpagetotal + poidsparagraph*pointparagraphtotal + poidssequence*pointsequencetotal + poidsnumerotation*pointnumerotationtotal + poidsframe*pointframetotal + poidsbiblio*pointbibliototal + poidstablematieres*pointtablematierestotal + poidstableillustration*pointtableillustrationtotal + poidsstructure*pointstructuretotal + poidssection*pointsectiontotal + poidstableau*pointtableautotal);
		
		poidsTotal = poidsmeta + poidspage + poidsparagraph + poidssequence + poidsnumerotation + poidsframe + poidsbiblio + poidstablematieres + poidstableillustration + poidsstructure + poidssection + poidstableau;
		pointsTotal = pointmetatotal + pointpagetotal + pointparagraphtotal + pointsequencetotal + pointnumerotationtotal + pointframetotal + pointbibliototal + pointtablematierestotal + pointtableillustrationtotal + pointstructuretotal + pointsectiontotal;
		pointgagner = pointmeta + pointpage + pointparagraph + pointsequence + pointnumerotation + pointframe + pointsection + pointtableau + pointbiblio + pointtablematieres + pointtableillustration + pointstructure;
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
		nodanalyse.ajouteEnfant(nodtableaux);
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
  	public static node analyseLesAttributEtContenuDuNode(node nodeStudent, node sujet, node retour, String nameItem, String nameElt) {
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
						retour = evaluNameNode(retour,nodeStudent, sujet.getNomElt(), sujet.getAttributs().get("evalNameNode"),sujet.getNomElt());
					}else {
						retour = evaluNameNode(retour,null, sujet.getNomElt(), sujet.getAttributs().get("evalNameNode"),sujet.getNomElt());
					}
				}
				
				if(k.equals("evalNameCreator") && sujet.getNomElt().equals("dc:creator")) {
					if(nodeStudent!=null) {
						retour = evaluNameCreator(retour,nodeStudent, nodeStudent.getContenu().get(0), sujet.getAttributs().get("evalNameCreator"),"Editeur");
					}else {
						retour = evaluNameCreator(retour,null, "Editeur inconnu", sujet.getAttributs().get("evalNameCreator"),"Editeur");
					}
				}
				
				if(k.equals("evalNameInitialCreator") && sujet.getNomElt().equals("meta:initial-creator")) {
					if(nodeStudent!=null) {
						retour = analyseNameInitialCreator(retour,nodeStudent, nodeStudent.getContenu().get(0), sujet.getAttributs().get("evalNameInitialCreator"),"Créateur");
					}else {
						retour = analyseNameInitialCreator(retour,null, "Créateur inconnu", sujet.getAttributs().get("evalNameInitialCreator"),"Créateur");
					}
				}
				
			}
			
		}
		//avec l'attribut allContent="strict1", allContent="strictSansEspace1" et allContent="environ1" alors analyse tout le contenu du node
		if(sujet.getAttributs().get("allContent")!=null) if(!sujet.getAttributs().get("allContent").isEmpty()){
			String points ="‽0";
			if(sujet.getAttributs().get("allContent").contains("strict")) points = sujet.getAttributs().get("allContent").replace("strict", "‽");
			if(sujet.getAttributs().get("allContent").contains("strictSansEspace")) points = sujet.getAttributs().get("allContent").replace("strictSansEspace", "≡‽");
			if(sujet.getAttributs().get("allContent").contains("environ")) points = sujet.getAttributs().get("allContent").replace("environ", "¢‽");
			
			String testPoint = points.substring(points.indexOf("‽")+1, points.length());
			boolean pasDeProblem = true;
			boolean pointSupAUn = false;
			
			try {
				if(Integer.valueOf(testPoint)>=1) pointSupAUn=true;
			}catch (Exception e) {
				System.out.println("Dans le node " + sujet.getNomElt() + ".\nIl y a un problème avec la valeur de l'attribut allContent=\"" + sujet.getAttributs().get("allContent") + "\"");
				System.out.println(e.toString());
				pasDeProblem=false;
			}
			
			if(pasDeProblem && pointSupAUn) {
				String allContentSujet = outils.withoutCodeAndPointPourRechercheContenuExact(sujet.retourneLesContenusEnfants("")) + points;
				String allContentStudent = "null";
				if( nodeStudent!=null) allContentStudent = nodeStudent.retourneLesContenusEnfants("");
				node item = retourneNoteAvecResultatsAnalyse(nameItem,"Contenu textuel", allContentStudent, allContentSujet, nameElt);
				retour.getNodes().add(item);
			}
			
		}
		
		// analyse le contenu du node avec tous les nodes sauf "text:sequence"
		if(sujet.contenuEvaluer() && !sujet.getNomElt().equals("text:sequence")) {
			String contenuStudent ="";
			if(nodeStudent!=null) if(nodeStudent.getContenu().size()>0) contenuStudent = nodeStudent.getContenu().get(0);
			String contenuSujet = sujet.getContenu().get(0);
			node item = retourneNoteAvecResultatsAnalyse(nameItem,"Contenu textuel", contenuStudent, contenuSujet, nameElt);
			retour.getNodes().add(item);
		}
		
		// analyse le contenu du node text:sequence et tous les enfants text:change (lorsque les légendes sont modifiées)
		if(sujet.contenuEvaluer() && sujet.getNomElt().equals("text:sequence")) {
			String contenuStudent ="";
			if(nodeStudent!=null) if(nodeStudent.getContenu().size()>0) contenuStudent = nodeStudent.getContenu().get(0); //contenuStudent = nodeStudent.retourneLesContenusEnfants("text:change");
			String contenuSujet = sujet.getContenu().get(0);
			node item = retourneNoteAvecResultatsAnalyse(nameItem,"Contenu textuel", contenuStudent, contenuSujet, nameElt);
			retour.getNodes().add(item);
		}
	
		return retour;
	}
  	
  	
	
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
  	 * Analyse tous les attributs des nodes <style:style>.<br>
	 * Formatage direct des styles de paragraphe.</br>
	 * Les attributs doivent contenir le code ‼.<br>
	 * <br>
	 * @param nodeStudent : le node de l'étudiant.
	 * @param sujet : le node du sujet
	 * @param retour : le node à retourner avec les enfants nommés nameItem.
	 * @param nameItem : le nom des nodes enfants.
	 * @param nameElt : le nom de l'élément (node) analysé.
	 * @return le node <b>retour</b> avec tous les nodes enfants <b>nameItem</b> contenant les différentes analyse. 
  	 */
	public static node analyseLesAttributAnalyseStyle(node nodeStyleParagraphStudent, node nodeStyleParagraphSujet, node retour, String nameItem, String nameElt) {

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
 	 * Affichage uniquement dans la console Les erreurs.
 	 * @param nod
 	 */
 	private static void messageSystem(node nod) {
 		node ouverture = nod.retourneFirstEnfantsByName("ouverture");
 		node notation = nod.retourneFirstEnfantsByName("bodyetnotation");
 		node erreurs = nod.retourneFirstEnfantsByName("erreurs");
 		boolean flagError = Boolean.valueOf(erreurs.getAttributs().get("oneError"));
 		
 		if(!commandes.fichierStudentMoodle) {System.out.println("\t Dossier analysé : " + ouverture.getAttributs().get("dossier"));}else {System.out.println("\t Fichier analysé : " + ouverture.getAttributs().get("dossier"));}
 		if(notation.getAttributs().get("baremeABC").equals("true")) {
 			System.out.println("\t Note : " +  notation.getAttributs().get("noteABC"));
 		}else {
 			System.out.println("\t Note : " +  notation.getAttributs().get("note") + "/" + ouverture.getAttributs().get("notefrom"));
 		}
		
		if(flagError) {
			System.out.println("\t ERREUR dans le fichier de l'étudiant.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueHistorique"))) System.out.println("\t Erreur : Il n'y a pas d'historique dans le fichier.Peut être que le fichier n'a pas été modifié ou il a été réïnitialisé par l'étudiant.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueCreationDate"))) System.out.println("\t Erreur : Ce n'est pas la bonne date de création du fichier. Le fichier a été réïnitialisé ou ce n'est pas le fichier de l'évaluation.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueValeurCreationDate"))) System.out.println("\t Erreur : Ce n'est pas la bonne date de création du fichier.");
			
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueMetaSujet"))) System.out.println("\t Erreur : La propriété personnalisé \"Sujet\" a été supprimé dans le fichier de l'étudiant.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueValeurMetaSujet"))) System.out.println("\t Erreur : La propriété personnalisé \"Sujet \" a été modifié par l'étudiant.\nLa valeur de cette propriété personnalisé dans le fichier de l'étudiant est \"" + ouverture.getAttributs().get("metaSujet")+"\".\nCe n'est pas la valeur correct.");
			if(Boolean.valueOf(erreurs.getAttributs().get("manqueInitialCreator"))) System.out.println("\t Erreur : La propriété personnalisé \"Sujet\" n'est pas correct.");
		}
		System.out.println();
 	}

	
	/**
	 * Ajoute dans le node nodanalyse.</br>
	 * Le node saut et son attribut titre</br>
	 * Et place un titre1, ou titre2, ou titre3</br>
	 * <br>
	 * @param nod
	 * @return
	 */
	public static node addNodeSautTitre(node nodSujet, node nodanalyse) {
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
				
				node nodDate = null;
				node nodDateCreator = null;
				String dcdate1 ="";
				String dccreator ="";
				nodDate = HitoriqueDuFichier.get(j).retourneFirstEnfantsByName("dc:date");
				nodDateCreator = HitoriqueDuFichier.get(j).retourneFirstEnfantsByName("dc:creator");
				if(nodDate!=null) {
					dcdate1 = nodDate.getContenu().get(0);
				}
				if(nodDate!=null) {
					dccreator = nodDateCreator.getContenu().get(0);
				}
				
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
						String dcdate2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName("dc:date").getContenu().get(0);
						String dccreator2 = HitoriqueDuFichier2.get(j2).retourneFirstEnfantsByName("dc:creator").getContenu().get(0);
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
		Path outputFilePath = Paths.get(commandes.path + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(commandes.path +"/"+ commandes.pathDestination + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
			
		if(!commandes.fourniDossierDestination) System.out.println(commandes.path +"\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) System.out.println(commandes.path +"\\"+ commandes.pathDestination + "\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
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
		Path outputFilePath = Paths.get(commandes.path + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(commandes.path +"/"+ commandes.pathDestination + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		if(!commandes.fourniDossierDestination) System.out.println(commandes.path +"\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) System.out.println(commandes.path +"\\"+ commandes.pathDestination + "\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		
		
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
		Path outputFilePath = Paths.get(commandes.path + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) outputFilePath = Paths.get(commandes.path +"/"+ commandes.pathDestination + "/DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
		if(!commandes.fourniDossierDestination) System.out.println(commandes.path +"\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		if(commandes.fourniDossierDestination) System.out.println(commandes.path +"\\"+ commandes.pathDestination + "\\DateLong" + aujourdhui.getTime()+ "-Notes.csv");
		
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
	public static String traitementDureeEdition(String dureeEdition) {
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
	public static node ajouteValeurParDefautAuStyleParagraph(node ensembleDesParagraphes , node styleParagraph) {
		
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
	public static node ajouteValeurLesValeursDuStyleParagraphParent(node ensembleDesParagraphes , node styleParagraph) {
		
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
	 * Analyse le nom du node
	 * 
	 * @param retour : le node retour qui contient l'item ajouté
	 * @param nodStudent : le node Student qui peut être null
	 * @param nameNode : le nom du node
	 * @param point : les points
	 * @param nameElt : le nom de l'élément
	 * @return
	 */
	private static node evaluNameNode(node retour, node nodStudent, String nameNode, String point, String nameElt) {
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
	
	/**
	 * 
	 * @param retour
	 * @param nodStudent
	 * @param nameCreator
	 * @param point
	 * @param nameElt
	 * @return
	 */
	private static node evaluNameCreator(node retour, node nodStudent, String nameCreator, String point, String nameElt) {
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
	
	
	/**
	 * 
	 * @param retour
	 * @param nodStudent
	 * @param nameCreator
	 * @param point
	 * @param nameElt
	 * @return
	 */
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
	
	

