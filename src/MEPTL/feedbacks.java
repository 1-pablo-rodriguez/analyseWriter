package MEPTL;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cXML.node;

public class feedbacks {

	public feedbacks() {
		
	}
	/**
 	 * Ecriture du fichier feedback.<br>
 	 * Si zip = true alors pour archive.<br>
 	 * Si zip = false alors pour fichier html.<br>
 	 * <br>
 	 * @param nodana : node de l'analyse de l'étudiant.
 	 * @param verif : node de vérification (ensemble des vérifications des historiques)
 	 * @parame zip : Pour créer un fichier html ou une archive zip.
 	 * @return
 	 * @throws IOException
 	 */
 	public static StringBuilder feedback(node nodana, node verif, Boolean zip) throws IOException {
 		
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
		 
 		//**********************
		//** Les informations **
 		//**********************
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
	    fichier.append("Nom du fichier : <span style=\"color:blue\">"+ ouvre.getAttributs().get("filename") + "</span><br>");
	    fichier.append("Date de la dernière modification du fichier analysé : <span style=\"color:purple\">"+ dateModif + "</span><br>");
	    fichier.append("Durée d'édition du fichier analysé : <span style=\"color:purple\">"+ meptl.traitementDureeEdition(ouvre.getAttributs().get("dureeEdition") + "</span><br>"));
	    if(producteur) {
	    	fichier.append("Suite de bureautique de l'étudiant : <span style=\"color:coral;\">"+ SuiteBureautique + "</span>");
	  	    fichier.append(" - Version : <span style=\"color:coral;\">"+ VersionLibreOffice + "</span>");
	  	    fichier.append(" - Système : <span style=\"color:coral;\">"+ SystemeStudent + "</span><br>");
	    }
	    fichier.append("<br>");
	    if(!auteurSujet.isEmpty()) {fichier.append("Sujet créé par : <span style=\"color:indigo\">"+ auteurSujet + "</span><br>");}
	    fichier.append("Hash du fichier analyse : <span style=\"color:red\">"+ ouvre.getAttributs().get("hash") + "</span><br>");
	    fichier.append("Nom du fichier analyse : <span style=\"color:red\">"+ commandes.nameSujet + "</span><br>");
	    fichier.append("Titre de l'exercice : <span style=\"color:red\">"+ ouvre.getAttributs().get("titre") + "</span><br>");
	      
	    if(!commandes.noNote) {
	    	if(!plagiat&&!copiercoller&&!pasAssezDeModification) fichier.append("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : " + nodana.retourneFirstEnfantsByName("bodyetnotation").getAttributs().get("proportioncorrect") +"<br>");
	    	 if(plagiat || copiercoller || pasAssezDeModification) {
	 			String AffichageNote = "";
	 			if(plagiat) AffichageNote = " Plagiat ";
	 			if(copiercoller) AffichageNote = AffichageNote + " Copier Coller ";
	 			if(pasAssezDeModification) AffichageNote = AffichageNote + " Pas assez de modification ";
	 			fichier.append("Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + ouvre.getAttributs().get("progression") + "</font><span class=\"tooltiptext\">Explication<br>"+ HTML.imgProgression() +"</span></div> - Pourcentage correcte : "+ AffichageNote +"<br>");
	 		}
	    	 if(baremeABC) {
	 	    	fichier.append("Barème : <div class=\"tooltip\"><font color=\"#0000ff\">0% → E → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneE"))*100) + "% → D → " +  Math.round(Double.valueOf(notation.getAttributs().get("BorneD"))*100) + "% → C → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneC"))*100) + "% → B → " + Math.round(Double.valueOf(notation.getAttributs().get("BorneB"))*100) + "% → A → 100%</font><span class=\"tooltiptext\">Prendre en compte le coefficient de progression.</span></div>");
	 	    }
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
			 fichier.append(HTML.H3(nodana.getContenu().get(0)).replace("-NewLine-", "<br>"));
			 
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
			 
			//section
			 if(nodana.retourneFirstEnfantsByName("sections")!=null) if(nodana.retourneFirstEnfantsByName("sections").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("sections")));
			 }	
			 
			 //tableau
			 if(nodana.retourneFirstEnfantsByName("tableaux")!=null) if(nodana.retourneFirstEnfantsByName("tableaux").isClose()) {
				 fichier.append(HTML.Table(nodana.retourneFirstEnfantsByName("tableaux")));
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
				
		
		if(!zip) {
			
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
			Path outputFilePath = Paths.get(commandes.path + "/" + cheminFeedBack);
			if(commandes.fourniDossierDestination) outputFilePath = Paths.get(commandes.path + "/" + commandes.pathDestination+ "/" + cheminFeedBack);
			
				
			BufferedWriter  fichier1 = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
			
			//ajoute le chemin vers le feedback dans le node d'analyse
			nodana.retourneFirstEnfantsByName("ouverture").getAttributs().put("feedback", commandes.path + "/" + cheminFeedBack);
			
			fichier1.append(fichier.toString());
			
			fichier1.close();
			
			//affichage dans la console
			if(!commandes.fourniDossierDestination) System.out.println("\n\t The feedback file has been written.\n\t " + commandes.path + "\\" + cheminFeedBack);
			if(commandes.fourniDossierDestination) System.out.println("\n\t The feedback file has been written.\n\t " + commandes.path + "\\" + commandes.pathDestination + "\\" + cheminFeedBack);
			
			
		}
		
		
		return fichier;
 	}
	
	
	
}
