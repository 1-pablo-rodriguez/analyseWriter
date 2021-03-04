package MEPTL;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import cXML.node;

/**
 * 
 * @author pablo rodriguez
 *
 */
public class HTML {

		// Titre principaux des analyses avec identifiant pour lien
		public static String H1(String txt, String id) {
			txt = "<br id=\"" + id +"\"><br><br><br><br><br><h1 class=\"western\" align=\"center\" style=\"margin-left: 3cm; margin-right: 3cm; margin-bottom: 0cm; border: 2.00pt solid #FF0000; padding: 0.3cm 0.1cm; line-height: 100%\">\r\n" + 
					"<font size=\"5\" style=\"font-size: 20pt\"><b>"+ txt +"</b></font></h1><br>\r";
			return txt;
		}
		
		// Titre principaux des analyse
		public static String H1(String txt) {
			txt = "<h1 class=\"western\" align=\"center\" style=\"margin-left: 3cm; margin-right: 3cm; margin-bottom: 0cm; border: 2.00pt solid #FF0000; padding: 0.3cm 0.1cm; line-height: 100%\">\r\n" + 
					"<font size=\"5\" style=\"font-size: 20pt\"><b>"+ txt +"</b></font></h1><br>\r";
			return txt;
		}
		
		// Titre 2
		public static String H2(String txt) {
			//txt = "<font size=\"5\" style=\"font-size: 18pt\"><u>" + txt + "</u></font><br>\r";
			txt="<H2>"+ txt +"</H2>";
			return txt;
		}
		
		// Titre 2
		public static String H2(String txt, String id) {
			//txt = "<font size=\"5\" style=\"font-size: 18pt\"><u>" + txt + "</u></font><br>\r";
			txt="<H2 id=\"" + id + "\">"+ txt +"</H2>";
			return txt;
		}
		
		// paragraphe class pablo
		public static String Ppablo(String txt) {
			return "<p class=\"pablo\">" + txt +"</p>";
		}
		
		/**
		 * Style HTML pour paragraphe style class p1
		 * @param txt
		 * @return
		 */
		public static String Paragraph_classp1(String txt) {
			return("<p class=\"p1\">" + txt + "</p>");
		}
			
		/**
		 * Style HTML pour paragraphe style class p1
		 * @param txt
		 * @return
		 */
		public static String Paragraph_classp1(String txt, String id) {
			return("<p id=\"" + id + "\" class=\"p1\">" + txt + "</p>");
		}
				
		// paragraphe avec la class p5 fond ROUGE pour erreur
		public static String Paragraph_classp5(String txt) {
			return("<p class=\"p5\">" + txt + "</p>");
		}
		
		// paragraphe avec la class p5 fond ROUGE pour erreur
		public static String Paragraph_classp6(String txt) {
			return("<p class=\"p6\">" + txt + "</p>");
		}
				
		// style avec la class p7 fond VERT pour correct
		public static String Paragraph_classp7(String txt) {
			return("<p class=\"p7\">" + txt + "</p>");
		}
		
		//Saut de page puis tritre
		public static String SautLigneOnduleBleu(String titre) {
			return "<br><p class=\"p9\">" + titre+"</p>\r";
		}
				
		/**
		 * Un saut de ligne
		 * @return
		 */
		public static String SautLigne() {
			return "<br>\r";
		}
		
		/**
		 * Un saut de ligne puis un trait horizontal sans style
		 * @return
		 */
		public static String SautLigneEtBordure() {
			return "<br><hr>\r";
		}
		
		// Entête des tables pour les styles de paragraphe, styles de page, etc...
		public static String TableEnteteTableurSynthese() {
			return "<table width=\"80%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
					"	<col width=\"30%\"/><col width=\"20%\"/><col width=\"20%\"/><col width=\"20%\"/><col width=\"10%\"/>"+ 
					"	<tr valign=\"top\">"
					+ "<td width=\"20%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm; background:#000000\"><p>\r\n" + 
					"<font color=\"#ffffff\"><b>Sous partie</b></p></td>\r\n" + 
					"		<td width=\"20%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm; background:#000000\">\r\n" + 
					"<font color=\"#ffffff\"><b>Proportion (%)</b></p></td>\r\n" +  
					"		<td width=\"20%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm; background:#000000\">\r\n" + 
					"<font color=\"#ffffff\"><b>Nombre de points</b></p></td>\r\n" + 
					"      <td width=\"20%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm; background:#000000\"><p>\r\n" + 
					"<font color=\"#ffffff\"><b>Points gagnés</b></p></td>\r\n" + 
					"      <td width=\"20%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm; background:#000000\"><p>\r\n" + 
					"<font color=\"#ffffff\"><b>Coef.</b></p></td>\r\n" + 
					"	</tr>\r\n" + 
					"</table>";
		}
		
		// Table pour la synthèse au début du FeedBack : Style de paragraphe, Style de page
		public static String TablePointsSyntheseStyle(String nomstyle,double prop, String pointstotal, String pointsgagnes, String coef, String id ) {
			String propRed = Integer.toHexString(255 - (int) Math.round(Math.pow(prop,3)*255));
			String propGreen = Integer.toHexString((int) Math.round(Math.pow(prop,3)*255));
			if(propRed.length()==1) propRed= "0"+propRed;
			if(propGreen.length()==1) propGreen= "0"+propGreen;
			
			String color="#" + propRed + propGreen + "40";
			DecimalFormat df = new DecimalFormat("###.##");
			return "<table width=\"80%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
					"<col width=\"30%\"/><col width=\"20%\"/><col width=\"20%\"/><col width=\"20%\"/><col width=\"10%\"/>"
					+ "<tr valign=\"top\">"
					+ "<td width=\"20%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>" + 
					"<a href=\"#" + id + "\"><font size=\"4\">" + nomstyle +"</font></p></td>"
					+ "<td width=\"20%\" align=\"center\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>" + 
					"<b><span size\"6\" style=\"color: "+ color +"\">█     </span>" + df.format(prop*100) +"%</b></p></td>"
					+ "<td width=\"20%\" align=\"center\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>"+
					pointstotal + "</p></td>"
					+ "<td width=\"20%\" align=\"center\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>"+
					pointsgagnes + "</p></td>"
					+ "<td width=\"20%\" align=\"center\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>"+
					coef + "</p></td>"
					+ "</tr>\r\n" + 
					"</table>";
		}
		
		// Entête des tables pour les styles de paragraphe, styles de page, etc...
		public static String TableEntete() {
			return "<table width=\"90%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
					"<col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/>"+ 
					"<tr valign=\"top\"><td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font color=\"#000000\" size=\"5\" style=\"font-size: 14pt\"><b>" + "Correct/Erreur" + "</b></font></p></td>\r\n" + 
					"<td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font size=\"5\" style=\"font-size: 14pt\"><b>" + "Propriété" +"</b></font></p></td>\r\n" + 
					"<td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font size=\"5\" style=\"font-size: 14pt\"><b>Valeur fichier</p></td>\r\n" + 
					"<td width=\"25%\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>\r\n" + 
					"<font size=\"5\" style=\"font-size: 14pt\"><b>Consigne" +"</p></td>\r\n" + 
					"	</tr>\r\n" + 
					"</table>";
		}
		
		// Entête des tables pour ordre structure
		public static String TableEnteteOrdreStructure() {
			return "<table width=\"90%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
				"<col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/>"+ 
				"<tr valign=\"top\"><td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
				"<font color=\"#000000\" size=\"5\" style=\"font-size: 14pt\"><b>" + "Correct/Erreur" + "</b></font></p></td>\r\n" + 
				"<td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
				"<font size=\"5\" style=\"font-size: 14pt\"><b>" + "Propriété" +"</b></font></p></td>\r\n" + 
				"<td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
				"<font size=\"5\" style=\"font-size: 14pt\"><b>Ordre fichier</p></td>\r\n" + 
				"<td width=\"25%\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>\r\n" + 
				"<font size=\"5\" style=\"font-size: 14pt\"><b>Ordre consigne" +"</p></td>\r\n" + 
				"	</tr>\r\n" + 
				"</table>";
			}
		
		
		// Table pour les corrects dans les styles de paragraphe, styles de page
		public static String Table(String txt1, String txt2, String txt3, String txt4,int niveau) {
			String color = "#AAAAAA"; //#
			if(niveau==2) color =" #dc143c";
			if(niveau==1) color = "#32cd32";
			
			return "<table width=\"90%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
					"	<col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/>"+ 
					"	<tr valign=\"top\"><td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font color=\"#ffffff\"><b><span style=\"background:"+ color +"\">" + txt1 + "</p></td>\r\n" + 
					"		<td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p class=\"p1\">\r\n" + 
					txt2 +"</p></td>\r\n" + 
					"      <td width=\"25%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					txt3 +"</p></td>\r\n" + 
					"	   <td width=\"25%\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>\r\n" + 
					txt4 +"</p></td>\r\n" + 
					"	</tr>\r\n" + 
					"</table>";
		}
		
		// table d'un node
		public static String Table(node nod) {
			
			 String code = HTML.SautLigne();
			 if(!nod.getAttributs().get("titre").isEmpty()) {
				 code = code + HTML.H1(nod.getAttributs().get("titre"),nod.getAttributs().get("id"))+ HTML.SautLigne() ;
			 }else {
				 code = code + HTML.H1("",nod.getAttributs().get("id"))+ HTML.SautLigne() ;
			 }
			 
			 //ajoute le commentaire
			 if(!nod.getContenu().isEmpty()) if(nod.getNodes().size()==1) code = code + HTML.Paragraph_classp6(nod.getContenu()) + HTML.SautLigne();
			
			 code = code  + HTML.TableEntete(); //ajoute l'entête
		
			 
			 for(int k = 0 ; k < nod.getNodes().size();k++) {
				 if(nod.getNodes().get(k).getAttributs().get("niveau")!=null) {
					 
					 String Tst = nod.getNodes().get(k).getAttributs().get("resultat");
					 String Key = outils.Traduction(nod.getNodes().get(k).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getAttributs().get("attribut")));
					 String valueStudent = nod.getNodes().get(k).getAttributs().get("valueStudent");
					 String valueSujet = outils.withoutCodeAndPoint(nod.getNodes().get(k).getAttributs().get("valueSujet"));
					 int niveau = Integer.valueOf(nod.getNodes().get(k).getAttributs().get("niveau"));
					 
					 code = code + HTML.Table(Tst, Key, valueStudent, valueSujet, niveau);
				 }else {
					 
					 if(nod.getNodes().get(k).getAttributs().get("titre")!=null) {
						 code = code + HTML.SautLigne() + HTML.H2(nod.getNodes().get(k).getAttributs().get("titre")) 
						 + HTML.SautLigne();
					 }else {
						 code = code + HTML.SautLigne();
					 }
					 if(nod.getNodes().get(k).getAttributs().get("saut")!=null) if(nod.getNodes().get(k).getAttributs().get("saut").equals("true")) {
						 code = code + HTML.SautLigne();
					 }
						 
						 if(!nod.getContenu().isEmpty()) code = code + HTML.Paragraph_classp6(nod.getContenu()) + HTML.SautLigne();
						 
						 for(int l = 0 ; l < nod.getNodes().get(k).getNodes().size() ; l++) {
							if(!nod.getNodes().get(k).getNodes().get(l).getNomElt().equals("saut")){
								 String Tst = nod.getNodes().get(k).getNodes().get(l).getAttributs().get("resultat");
								 String Key = outils.Traduction(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("attribut")));
								 String valueStudent = nod.getNodes().get(k).getNodes().get(l).getAttributs().get("valueStudent");
								 String valueSujet = outils.withoutCodeAndPoint(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("valueSujet"));
								 int niveau = Integer.valueOf(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("niveau"));
								 
								 code = code + HTML.Table(Tst, Key, valueStudent, valueSujet, niveau);
							 }else {
								 code = code + HTML.SautLigneOnduleBleu(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("titre"));
							 }
							
						 }
					 
				 }

			 }
			 
			 code = code.replace("-NewLine-", "<br>");
			 
			 return code;
		}

		
		
		// Table correct pour application des styles
		public static String TableApplStyle(String NomStyle,ArrayList<String> T, ArrayList<String> S, boolean erreur) {
			String T1 = "";
			String T2 = "";
			for(int i=0; i<T.size(); i++) {
				T1=T1+T.get(i)+"<br/>\r";
			}
			for(int i=0; i<S.size(); i++) {
				T2=T2+outils.withoutPoint(S.get(i))+"<br/>\r";
			}
			
			String color = "#32cd32"; //vert
			if(erreur) color = "#dc143c"; //rouge
			
			
			// table pour l'application des styles
			return "<table width=\"95%\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">"
					+ "<col width=\"20%\"/>"
					+ "<col width=\"40%\"/>"
					+ "<col width=\"40%\"/>"
					+ "<tr>"
					+ "<td colspan=\"3\" width=\"100%\" valign=\"top\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p align=\"center\"><b>"
					+ "<font size=\"5\">Les paragraphes qui sont dans le style \"" + NomStyle + "\"</font></b></p>"
					+ "</td>"
					+ "</tr>"
					+ "<tr valign=\"top\">"
					+ "<td width=\"20%\" style=\"border-top: none; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font size=\"4\"><b>Correct/Erreur</b></font></p>"
					+ "</td>"
					+ "<td width=\"40%\" style=\"border-top: none; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font size=\"4\"><b>Dans fichier remis</b></font></p>"
					+ "</td>"
					+ "<td width=\"40%\" style=\"border-top: none; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000; padding-top: 0cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0.1cm\"><p>\r\n" + 
					"<font size=\"4\"><b>Consigne</b></font></p>"
					+ "</td>"
					+ "</tr>"
					+ "<tr valign=\"top\">"
					+ "<td width=\"20%\" style=\"border-top: none; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font color=\"#ffffff\"><b><span style=\"background: "+ color +"\">Correct</span></b></font><br/></p>"
					+ "</td>"
					+ "<td width=\"40%\" style=\"border-top: none; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					T1 + "<br/></p>"
					+ "</td>"
					+ "<td width=\"40%\" style=\"border-top: none; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: 1px solid #000000; padding-top: 0cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0.1cm\"><p>\r\n" + 
					T2 + "<br/></p>"
					+ "</td>"
					+ "</tr>\r\n" + 
					"</table>";
		}
		
		
		
		// Aller en haut
//		public static String GoToTop() {
//			return "<p><a href=\"#top\">" + ImgTop() +"</a></p>";
//		}
		
		// Les points obtenus sous les tables
		public static String Paragraph_SousPartieNote(int ptacquis, int ptotal, double prop ) {
			return "<h4>Total des points acquis = " + ptacquis + "<br/>Total des points analysés = " + ptotal + "<br/>Proportion = " + prop + "</h4>\r";
		}
		
		
		// image du logo de la fac
		public static String imgLogos() {
			return "<svg\r\n" + 
					"   xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\r\n" + 
					"   xmlns:cc=\"http://creativecommons.org/ns#\"\r\n" + 
					"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n" + 
					"   xmlns:svg=\"http://www.w3.org/2000/svg\"\r\n" + 
					"   xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"   id=\"svg4491\"\r\n" + 
					"   version=\"1.1\"\r\n" + 
					"   viewBox=\"0 0 37.144689 19.770884\"\r\n" + 
					"   height=\"74.724602\"\r\n" + 
					"   width=\"140.38937\">\r\n" + 
					"  <defs\r\n" + 
					"     id=\"defs4485\" />\r\n" + 
					"  <metadata\r\n" + 
					"     id=\"metadata4488\">\r\n" + 
					"    <rdf:RDF>\r\n" + 
					"      <cc:Work\r\n" + 
					"         rdf:about=\"\">\r\n" + 
					"        <dc:format>image/svg+xml</dc:format>\r\n" + 
					"        <dc:type\r\n" + 
					"           rdf:resource=\"http://purl.org/dc/dcmitype/StillImage\" />\r\n" + 
					"        <dc:title></dc:title>\r\n" + 
					"      </cc:Work>\r\n" + 
					"    </rdf:RDF>\r\n" + 
					"  </metadata>\r\n" + 
					"  <g\r\n" + 
					"     transform=\"translate(-62.264812,-135.92528)\"\r\n" + 
					"     id=\"layer1\">\r\n" + 
					"    <path\r\n" + 
					"       id=\"path2574\"\r\n" + 
					"       style=\"fill:#193476;fill-opacity:1;fill-rule:nonzero;stroke:#ffffff;stroke-width:0.5;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n" + 
					"       d=\"m 76.827536,137.94138 c 0.93856,0.1193 -2.989764,-0.78943 -4.712891,3.87747 -1.186656,2.67692 -0.984581,2.4755 -1.67349,3.91517 -0.73951,1.5455 -1.937082,2.84493 -1.725085,2.73678 0,0 3.079752,-0.29865 5.227177,-4.64906 1.184671,-2.40043 1.553434,-5.35053 2.979539,-5.88367\" />\r\n" + 
					"    <path\r\n" + 
					"       id=\"path2576\"\r\n" + 
					"       style=\"fill:#f46717;fill-opacity:1;fill-rule:nonzero;stroke:#ffffff;stroke-width:0.5;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n" + 
					"       d=\"m 68.716072,148.4708 c 0.153128,-0.16437 0.237794,-0.2745 0.237794,-0.2745 -1.940718,1.03253 -2.275747,0.44053 -2.432843,-0.28178 -0.155774,-0.72232 0.874117,-3.40685 1.650007,-5.93527 0.775891,-2.53008 -0.238786,-3.40684 -0.920088,-3.30465 -0.682294,0.10385 -1.931458,0.41342 -1.931458,0.41342 0,0 0.420356,-0.0516 0.746455,0.82616 0.256646,0.68957 -1.417505,5.47092 -1.417505,5.47092 0,0 -0.84435,2.82344 0.284096,3.81166 1.545828,1.05039 3.30663,-0.36546 4.021336,-1.00046\" />\r\n" + 
					"    <path\r\n" + 
					"       id=\"path2578\"\r\n" + 
					"       style=\"fill:#b3bbe5;fill-opacity:1;fill-rule:nonzero;stroke:#ffffff;stroke-width:0.5;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n" + 
					"       d=\"m 98.835246,144.58771 c -6.780278,-1.24321 -13.444471,-0.3304 -19.156164,1.313 -0.112117,0.33569 -0.214312,0.64293 -0.306916,0.91678 11.627776,-3.27091 19.46308,-2.22978 19.46308,-2.22978\" />\r\n" + 
					"    <path\r\n" + 
					"       id=\"path2580\"\r\n" + 
					"       style=\"fill:#b3bbe5;fill-opacity:1;fill-rule:nonzero;stroke:#ffffff;stroke-width:0.5;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n" + 
					"       d=\"m 76.287124,146.99343 c -7.94676,2.83535 -13.34856,6.66486 -13.34856,6.66486 4.617641,-2.57208 9.009724,-4.43971 13.037344,-5.7914 0.02479,-0.0704 0.04961,-0.13891 0.07375,-0.20737 0.08037,-0.22555 0.160073,-0.44714 0.237463,-0.66609\" />\r\n" + 
					"    <path\r\n" + 
					"       id=\"path2582\"\r\n" + 
					"       style=\"fill:#35af3f;fill-opacity:1;fill-rule:nonzero;stroke:#ffffff;stroke-width:0.5;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n" + 
					"       d=\"m 78.264554,153.17642 c -0.932987,-0.93299 0.304271,-4.0812 0.698831,-5.16732 0.0764,-0.20935 0.220265,-0.62772 0.408781,-1.19161 0.0926,-0.27385 0.194799,-0.58109 0.306916,-0.91678 0.536112,-1.61165 1.271323,-3.85366 1.957256,-5.95809 0.69883,-2.31246 0.03638,-3.23817 -1.390717,-3.23817 -1.010377,0.31122 -3.418085,1.23693 -3.418085,1.23693 0,0 2.640541,-0.57649 1.902354,1.75419 -0.451115,1.42709 -1.253464,3.94229 -2.442766,7.29786 -0.07739,0.21895 -0.157096,0.44054 -0.237463,0.66609 -0.02413,0.0685 -0.04895,0.13693 -0.07375,0.20737 -0.885362,2.51652 -1.79156,5.92766 0.660797,6.8871 1.78693,0.6995 4.619625,-0.99483 6.172398,-2.97523 -2.329987,1.59179 -3.612224,2.33064 -4.544549,1.39766\" />\r\n" + 
					"  </g>\r\n" + 
					"</svg>\r\n" + 
					"";
		}
		
		// image de la progression
		public static String imgProgression() {
			return "<svg\r\n" + 
					"   xmlns:dc=\"http://purl.org/dc/elements/1.1/\"\r\n" + 
					"   xmlns:cc=\"http://creativecommons.org/ns#\"\r\n" + 
					"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n" + 
					"   xmlns:svg=\"http://www.w3.org/2000/svg\"\r\n" + 
					"   xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n" + 
					"   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n" + 
					"   width=\"205.73206mm\"\r\n" + 
					"   height=\"66.057816mm\"\r\n" + 
					"   viewBox=\"0 0 205.73204 66.05781\"\r\n" + 
					"   version=\"1.1\"\r\n" + 
					"   id=\"svg8\"\r\n" + 
					"   inkscape:version=\"1.0 (4035a4fb49, 2020-05-01)\"\r\n" + 
					"   sodipodi:docname=\"graphique_progression.svg\">\r\n" + 
					"  <defs\r\n" + 
					"     id=\"defs2\">\r\n" + 
					"    <pattern\r\n" + 
					"       y=\"0\"\r\n" + 
					"       x=\"0\"\r\n" + 
					"       height=\"6\"\r\n" + 
					"       width=\"6\"\r\n" + 
					"       patternUnits=\"userSpaceOnUse\"\r\n" + 
					"       id=\"EMFhbasepattern\" />\r\n" + 
					"    <pattern\r\n" + 
					"       y=\"0\"\r\n" + 
					"       x=\"0\"\r\n" + 
					"       height=\"6\"\r\n" + 
					"       width=\"6\"\r\n" + 
					"       patternUnits=\"userSpaceOnUse\"\r\n" + 
					"       id=\"EMFhbasepattern-7\" />\r\n" + 
					"    <pattern\r\n" + 
					"       y=\"0\"\r\n" + 
					"       x=\"0\"\r\n" + 
					"       height=\"6\"\r\n" + 
					"       width=\"6\"\r\n" + 
					"       patternUnits=\"userSpaceOnUse\"\r\n" + 
					"       id=\"EMFhbasepattern-6\" />\r\n" + 
					"    <pattern\r\n" + 
					"       y=\"0\"\r\n" + 
					"       x=\"0\"\r\n" + 
					"       height=\"6\"\r\n" + 
					"       width=\"6\"\r\n" + 
					"       patternUnits=\"userSpaceOnUse\"\r\n" + 
					"       id=\"EMFhbasepattern-0\" />\r\n" + 
					"    <pattern\r\n" + 
					"       y=\"0\"\r\n" + 
					"       x=\"0\"\r\n" + 
					"       height=\"6\"\r\n" + 
					"       width=\"6\"\r\n" + 
					"       patternUnits=\"userSpaceOnUse\"\r\n" + 
					"       id=\"EMFhbasepattern-64\" />\r\n" + 
					"  </defs>\r\n" + 
					"  <sodipodi:namedview\r\n" + 
					"     id=\"base\"\r\n" + 
					"     pagecolor=\"#ffffff\"\r\n" + 
					"     bordercolor=\"#666666\"\r\n" + 
					"     borderopacity=\"1.0\"\r\n" + 
					"     inkscape:pageopacity=\"0.0\"\r\n" + 
					"     inkscape:pageshadow=\"2\"\r\n" + 
					"     inkscape:zoom=\"1.605\"\r\n" + 
					"     inkscape:cx=\"380.02947\"\r\n" + 
					"     inkscape:cy=\"125.14521\"\r\n" + 
					"     inkscape:document-units=\"mm\"\r\n" + 
					"     inkscape:current-layer=\"g3882\"\r\n" + 
					"     showgrid=\"false\"\r\n" + 
					"     fit-margin-top=\"0\"\r\n" + 
					"     fit-margin-left=\"0\"\r\n" + 
					"     fit-margin-right=\"0\"\r\n" + 
					"     fit-margin-bottom=\"0\"\r\n" + 
					"     inkscape:window-width=\"1920\"\r\n" + 
					"     inkscape:window-height=\"1017\"\r\n" + 
					"     inkscape:window-x=\"-8\"\r\n" + 
					"     inkscape:window-y=\"-8\"\r\n" + 
					"     inkscape:window-maximized=\"1\"\r\n" + 
					"     inkscape:snap-global=\"false\"\r\n" + 
					"     inkscape:document-rotation=\"0\" />\r\n" + 
					"  <metadata\r\n" + 
					"     id=\"metadata5\">\r\n" + 
					"    <rdf:RDF>\r\n" + 
					"      <cc:Work\r\n" + 
					"         rdf:about=\"\">\r\n" + 
					"        <dc:format>image/svg+xml</dc:format>\r\n" + 
					"        <dc:type\r\n" + 
					"           rdf:resource=\"http://purl.org/dc/dcmitype/StillImage\" />\r\n" + 
					"        <dc:title></dc:title>\r\n" + 
					"      </cc:Work>\r\n" + 
					"    </rdf:RDF>\r\n" + 
					"  </metadata>\r\n" + 
					"  <g\r\n" + 
					"     inkscape:label=\"Calque 1\"\r\n" + 
					"     inkscape:groupmode=\"layer\"\r\n" + 
					"     id=\"layer1\"\r\n" + 
					"     transform=\"translate(-7.7422525,-73.304108)\">\r\n" + 
					"    <g\r\n" + 
					"       id=\"g3882\"\r\n" + 
					"       transform=\"matrix(0.74357194,0,0,0.74357194,2.0228312,35.12985)\">\r\n" + 
					"      <rect\r\n" + 
					"         style=\"fill:#ffffff;fill-opacity:1;stroke:#766e19;stroke-width:1.18859;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;paint-order:normal\"\r\n" + 
					"         id=\"rect4295\"\r\n" + 
					"         width=\"275.49219\"\r\n" + 
					"         height=\"87.64991\"\r\n" + 
					"         x=\"8.2861147\"\r\n" + 
					"         y=\"51.933319\"\r\n" + 
					"         rx=\"6.3410912\"\r\n" + 
					"         ry=\"6.8764377\" />\r\n" + 
					"      <path\r\n" + 
					"         inkscape:connector-curvature=\"0\"\r\n" + 
					"         style=\"fill:#ffffff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.21258\"\r\n" + 
					"         d=\"M 73.774134,136.39998 H 9.944808 V 54.697807 H 137.60346 v 81.702173 z\"\r\n" + 
					"         id=\"path2359\" />\r\n" + 
					"      <g\r\n" + 
					"         id=\"g3052\"\r\n" + 
					"         transform=\"matrix(0.55341809,0,0,0.54332781,16.892618,3.0717534)\">\r\n" + 
					"        <g\r\n" + 
					"           id=\"g3920\"\r\n" + 
					"           transform=\"matrix(1.0277361,0,0,1.0277361,-4.6787217,-16.789525)\">\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1249\"\r\n" + 
					"             d=\"M 110.91385,252.25471 H -4.6322315 V 108.15784 H 226.45993 v 144.09687 z\"\r\n" + 
					"             style=\"fill:#ffffff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1251\"\r\n" + 
					"             d=\"M 118.29999,225.63775 H 21.608708 V 129.98053 H 214.99127 v 95.65722 h -96.69128\"\r\n" + 
					"             style=\"fill:none;stroke:#b3b3b3;stroke-width:0.0134293px;stroke-linecap:round;stroke-linejoin:round;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1253\"\r\n" + 
					"             d=\"m 21.272974,129.9671 h 0.335734 0.349163 v 95.65722 h -0.349163 -0.335734 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1255\"\r\n" + 
					"             d=\"m 40.61123,129.9671 h 0.335734 0.349163 v 95.65722 H 40.946964 40.61123 Z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1257\"\r\n" + 
					"             d=\"m 59.949486,129.9671 h 0.335734 0.349163 v 95.65722 H 60.28522 59.949486 Z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1259\"\r\n" + 
					"             d=\"m 79.287742,129.9671 h 0.335734 0.349163 v 95.65722 h -0.349163 -0.335734 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1261\"\r\n" + 
					"             d=\"m 98.625998,129.9671 h 0.33573 0.34917 v 95.65722 h -0.34917 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1263\"\r\n" + 
					"             d=\"m 117.95083,129.9671 h 0.33573 0.34916 v 95.65722 h -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1265\"\r\n" + 
					"             d=\"m 137.28908,129.9671 h 0.33574 0.34916 v 95.65722 h -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1267\"\r\n" + 
					"             d=\"m 156.62734,129.9671 h 0.33573 0.34917 v 95.65722 h -0.34917 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1269\"\r\n" + 
					"             d=\"m 175.96559,129.9671 h 0.33574 0.34916 v 95.65722 h -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1271\"\r\n" + 
					"             d=\"m 195.30385,129.9671 h 0.33573 0.34916 v 95.65722 h -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1273\"\r\n" + 
					"             d=\"m 214.65554,129.9671 h 0.33573 0.34916 v 95.65722 h -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1275\"\r\n" + 
					"             d=\"m 214.99127,225.28858 v 0.33574 0.34916 H 21.608708 v -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1277\"\r\n" + 
					"             d=\"m 214.99127,215.71346 v 0.33574 0.34916 H 21.608708 v -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1279\"\r\n" + 
					"             d=\"m 214.99127,206.15177 v 0.33573 0.34917 H 21.608708 v -0.34917 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1281\"\r\n" + 
					"             d=\"m 214.99127,196.57665 v 0.33573 0.34916 H 21.608708 v -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1283\"\r\n" + 
					"             d=\"m 214.99127,187.01495 v 0.33574 0.34916 H 21.608708 v -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1285\"\r\n" + 
					"             d=\"m 214.99127,177.46669 v 0.33573 0.34917 H 21.608708 v -0.34917 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1287\"\r\n" + 
					"             d=\"m 214.99127,167.89157 v 0.33573 0.34917 H 21.608708 v -0.34917 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1289\"\r\n" + 
					"             d=\"m 214.99127,158.32988 v 0.33573 0.34916 H 21.608708 v -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1291\"\r\n" + 
					"             d=\"m 214.99127,148.75475 v 0.33574 0.34916 H 21.608708 v -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1293\"\r\n" + 
					"             d=\"m 214.99127,139.19306 v 0.33573 0.34916 H 21.608708 v -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1295\"\r\n" + 
					"             d=\"m 214.99127,129.63136 v 0.33574 0.34916 H 21.608708 v -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#696969;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1297\"\r\n" + 
					"             d=\"m 22.159311,227.63872 h -0.550603 -0.537174 v -2.0144 h 0.537174 0.550603 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1299\"\r\n" + 
					"             d=\"m 22.159311,227.63872 h -0.550603 -0.537174 v -2.0144 h 0.537174 0.550603 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1301\"\r\n" + 
					"             d=\"m 41.497568,227.63872 h -0.550604 -0.537175 v -2.0144 h 0.537175 0.550604 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1303\"\r\n" + 
					"             d=\"m 41.497568,227.63872 h -0.550604 -0.537175 v -2.0144 h 0.537175 0.550604 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1305\"\r\n" + 
					"             d=\"M 60.835824,227.63872 H 60.28522 59.748045 v -2.0144 h 0.537175 0.550604 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1307\"\r\n" + 
					"             d=\"M 60.835824,227.63872 H 60.28522 59.748045 v -2.0144 h 0.537175 0.550604 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1309\"\r\n" + 
					"             d=\"m 80.17408,227.63872 h -0.550604 -0.537175 v -2.0144 h 0.537175 0.550604 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1311\"\r\n" + 
					"             d=\"m 80.17408,227.63872 h -0.550604 -0.537175 v -2.0144 h 0.537175 0.550604 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1313\"\r\n" + 
					"             d=\"m 99.512338,227.63872 h -0.55061 -0.53717 v -2.0144 h 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1315\"\r\n" + 
					"             d=\"m 99.512338,227.63872 h -0.55061 -0.53717 v -2.0144 h 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1317\"\r\n" + 
					"             d=\"m 118.83716,227.63872 h -0.5506 -0.53717 v -2.0144 h 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1319\"\r\n" + 
					"             d=\"m 118.83716,227.63872 h -0.5506 -0.53717 v -2.0144 h 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1321\"\r\n" + 
					"             d=\"m 138.17542,227.63872 h -0.5506 -0.53718 v -2.0144 h 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1323\"\r\n" + 
					"             d=\"m 138.17542,227.63872 h -0.5506 -0.53718 v -2.0144 h 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1325\"\r\n" + 
					"             d=\"m 157.51367,227.63872 h -0.5506 -0.53717 v -2.0144 h 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1327\"\r\n" + 
					"             d=\"m 157.51367,227.63872 h -0.5506 -0.53717 v -2.0144 h 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1329\"\r\n" + 
					"             d=\"m 176.85193,227.63872 h -0.5506 -0.53718 v -2.0144 h 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1331\"\r\n" + 
					"             d=\"m 176.85193,227.63872 h -0.5506 -0.53718 v -2.0144 h 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1333\"\r\n" + 
					"             d=\"m 196.19019,227.63872 h -0.55061 -0.53717 v -2.0144 h 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1335\"\r\n" + 
					"             d=\"m 196.19019,227.63872 h -0.55061 -0.53717 v -2.0144 h 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1337\"\r\n" + 
					"             d=\"m 215.54187,227.63872 h -0.5506 -0.53718 v -2.0144 h 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1339\"\r\n" + 
					"             d=\"m 215.54187,227.63872 h -0.5506 -0.53718 v -2.0144 h 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1341\"\r\n" + 
					"             d=\"m 21.608708,226.17492 v -0.5506 -0.53717 H 214.99127 v 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1343\"\r\n" + 
					"             d=\"m 19.594306,226.17492 v -0.5506 -0.53717 h 2.014402 v 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1345\"\r\n" + 
					"             d=\"m 19.594306,226.17492 v -0.5506 -0.53717 h 2.014402 v 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1347\"\r\n" + 
					"             d=\"m 19.594306,216.5998 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1349\"\r\n" + 
					"             d=\"m 19.594306,216.5998 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1351\"\r\n" + 
					"             d=\"m 19.594306,207.03811 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1353\"\r\n" + 
					"             d=\"m 19.594306,207.03811 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1355\"\r\n" + 
					"             d=\"m 19.594306,197.46299 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1357\"\r\n" + 
					"             d=\"m 19.594306,197.46299 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1359\"\r\n" + 
					"             d=\"m 19.594306,187.90129 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1361\"\r\n" + 
					"             d=\"m 19.594306,187.90129 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1363\"\r\n" + 
					"             d=\"m 19.594306,178.35303 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1365\"\r\n" + 
					"             d=\"m 19.594306,178.35303 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1367\"\r\n" + 
					"             d=\"m 19.594306,168.77791 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1369\"\r\n" + 
					"             d=\"m 19.594306,168.77791 v -0.55061 -0.53717 h 2.014402 v 0.53717 0.55061 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1371\"\r\n" + 
					"             d=\"m 19.594306,159.21621 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1373\"\r\n" + 
					"             d=\"m 19.594306,159.21621 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1375\"\r\n" + 
					"             d=\"m 19.594306,149.64109 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1377\"\r\n" + 
					"             d=\"m 19.594306,149.64109 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1379\"\r\n" + 
					"             d=\"m 19.594306,140.07939 v -0.5506 -0.53717 h 2.014402 v 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1381\"\r\n" + 
					"             d=\"m 19.594306,140.07939 v -0.5506 -0.53717 h 2.014402 v 0.53717 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1383\"\r\n" + 
					"             d=\"m 19.594306,130.5177 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1385\"\r\n" + 
					"             d=\"m 19.594306,130.5177 v -0.5506 -0.53718 h 2.014402 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1387\"\r\n" + 
					"             d=\"M 22.159311,225.62432 H 21.608708 21.071534 V 129.9671 h 0.537174 0.550603 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1389\"\r\n" + 
					"             d=\"m 21.608708,226.29579 v -0.67147 -0.67147 l 4.834564,-0.0134 v 0.67147 0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1391\"\r\n" + 
					"             d=\"m 26.443272,224.93942 c 0,0 0,0 0,0 v 0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1393\"\r\n" + 
					"             d=\"m 26.443272,226.29579 v -0.6849 -0.67147 h 4.834564 v 0.67147 0.6849 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1395\"\r\n" + 
					"             d=\"m 31.277836,226.28236 c 0,0 0,0.0134 0,0.0134 v -0.6849 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1397\"\r\n" + 
					"             d=\"m 31.277836,226.28236 v -0.67147 -0.67147 l 9.669128,-0.094 v 0.67146 0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1399\"\r\n" + 
					"             d=\"m 40.960393,226.18835 c 0,0 0,0 -0.01343,0 v -0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1401\"\r\n" + 
					"             d=\"m 40.960393,226.18835 -0.01343,-0.67147 -0.01343,-0.67146 4.834564,-0.0806 0.01343,0.67147 0.01343,0.67146 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1403\"\r\n" + 
					"             d=\"m 45.808386,226.10777 c -0.01343,0 -0.01343,0 -0.01343,0 l -0.01343,-0.67146 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1405\"\r\n" + 
					"             d=\"m 45.808386,226.10777 -0.02686,-0.67146 -0.02686,-0.67147 4.834564,-0.14772 0.02686,0.67146 0.02686,0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1407\"\r\n" + 
					"             d=\"m 50.64295,225.96005 c 0,0 0,0 0,0 l -0.02686,-0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1409\"\r\n" + 
					"             d=\"m 50.64295,225.96005 -0.02686,-0.67147 -0.02686,-0.67146 9.669128,-0.42974 0.02686,0.67147 0.02686,0.67146 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1411\"\r\n" + 
					"             d=\"m 60.338936,225.53031 c -0.01343,0 -0.01343,0 -0.02686,0 l -0.02686,-0.67146 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1413\"\r\n" + 
					"             d=\"m 60.338936,225.53031 -0.05372,-0.67146 -0.05372,-0.67147 9.669128,-0.73862 0.05372,0.67147 0.05372,0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1415\"\r\n" + 
					"             d=\"m 70.034925,224.7917 c -0.01343,0 -0.02686,0 -0.02686,0 l -0.05372,-0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1417\"\r\n" + 
					"             d=\"m 70.034925,224.7917 -0.08058,-0.67147 -0.08058,-0.67147 9.669128,-1.08777 0.08058,0.67147 0.08057,0.67146 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1419\"\r\n" + 
					"             d=\"m 79.730911,223.70392 c -0.01343,0 -0.02686,0 -0.02686,0 l -0.08057,-0.67146 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1421\"\r\n" + 
					"             d=\"m 79.730911,223.70392 -0.107435,-0.67146 -0.107435,-0.67147 9.669127,-1.51752 0.10744,0.67147 0.10743,0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1423\"\r\n" + 
					"             d=\"m 89.426898,222.17298 c -0.0134,0.0134 -0.0134,0.0134 -0.0269,0.0134 l -0.10743,-0.67147 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1425\"\r\n" + 
					"             d=\"m 89.426898,222.17298 -0.13429,-0.65804 -0.1343,-0.65804 9.66913,-2.0144 0.13429,0.65804 0.1343,0.65804 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1427\"\r\n" + 
					"             d=\"m 99.136308,220.15858 c -0.0134,0 -0.0268,0 -0.0403,0 l -0.1343,-0.65804 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1429\"\r\n" + 
					"             d=\"m 99.136308,220.15858 -0.17458,-0.65804 -0.17458,-0.65804 9.669132,-2.60529 0.17458,0.65803 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1431\"\r\n" + 
					"             d=\"m 108.84573,217.53985 c -0.0134,0 -0.0269,0 -0.0403,0.0134 l -0.17458,-0.65804 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1433\"\r\n" + 
					"             d=\"m 108.84573,217.53985 -0.21487,-0.64461 -0.21487,-0.6446 9.6557,-3.23647 0.21487,0.6446 0.21487,0.64461 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1435\"\r\n" + 
					"             d=\"m 118.54172,214.28995 c -0.0134,0 -0.0269,0.0134 -0.0403,0.0134 l -0.21487,-0.64461 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1437\"\r\n" + 
					"             d=\"m 118.54172,214.28995 -0.25516,-0.63118 -0.25516,-0.63118 9.66913,-3.96165 0.25516,0.63118 0.25516,0.63117 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1439\"\r\n" + 
					"             d=\"m 128.25113,210.30144 c -0.0134,0.0134 -0.0269,0.0134 -0.0403,0.0268 l -0.25516,-0.63117 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1441\"\r\n" + 
					"             d=\"m 128.25113,210.30144 -0.29544,-0.60432 -0.29545,-0.60432 9.66913,-4.74056 0.29545,0.60432 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1443\"\r\n" + 
					"             d=\"m 137.96055,205.54745 c 0,0 0,0 0,0 -0.0134,0 -0.0269,0.0134 -0.0403,0.0134 l -0.29544,-0.60432 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1445\"\r\n" + 
					"             d=\"m 137.96055,205.54745 -0.33573,-0.59089 -0.33574,-0.59089 9.66913,-5.61347 0.33573,0.59089 0.33574,0.59089 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1447\"\r\n" + 
					"             d=\"m 147.66997,199.90713 c -0.0134,0 -0.0269,0.0134 -0.0403,0.0268 l -0.33574,-0.59089 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1449\"\r\n" + 
					"             d=\"m 147.66997,199.90713 -0.37603,-0.56404 -0.37602,-0.56403 9.66913,-6.54009 0.37602,0.56403 0.37602,0.56403 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1451\"\r\n" + 
					"             d=\"m 157.36595,193.3536 c -0.0134,0 -0.0134,0 -0.0269,0.0134 l -0.37602,-0.56403 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1453\"\r\n" + 
					"             d=\"m 157.36595,193.3536 -0.40288,-0.5506 -0.40288,-0.5506 1.35637,-0.99377 0.40287,0.5506 0.40289,0.5506 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1455\"\r\n" + 
					"             d=\"m 158.73574,192.3464 c 0,0 -0.0134,0 -0.0134,0.0134 l -0.40289,-0.5506 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1457\"\r\n" + 
					"             d=\"m 158.73574,192.3464 -0.41631,-0.53717 -0.4163,-0.53717 8.31276,-6.55352 0.41631,0.53717 0.41631,0.53717 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1459\"\r\n" + 
					"             d=\"m 167.07537,185.76603 c 0,0 -0.0134,0.0134 -0.0269,0.0268 l -0.41631,-0.53717 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1461\"\r\n" + 
					"             d=\"m 167.07537,185.76603 -0.44317,-0.51032 -0.44317,-0.51032 8.43363,-7.43985 0.44317,0.51031 0.44317,0.51032 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1463\"\r\n" + 
					"             d=\"m 175.53585,178.31274 c -0.0134,0 -0.0134,0 -0.0269,0.0134 l -0.44317,-0.51032 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1465\"\r\n" + 
					"             d=\"m 175.53585,178.31274 -0.47002,-0.49689 -0.47003,-0.49688 1.2355,-1.16835 0.47003,0.49688 0.47003,0.49689 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1467\"\r\n" + 
					"             d=\"m 176.78478,177.13096 c 0,0 -0.0134,0 -0.0134,0.0134 l -0.47003,-0.49689 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1469\"\r\n" + 
					"             d=\"m 176.78478,177.13096 -0.48345,-0.48346 -0.48346,-0.48345 9.66913,-9.77657 0.48345,0.48346 0.48346,0.48345 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1471\"\r\n" + 
					"             d=\"m 186.48077,167.31411 c -0.0134,0.0134 -0.0134,0.0268 -0.0269,0.0403 l -0.48346,-0.48345 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1473\"\r\n" + 
					"             d=\"m 186.48077,167.31411 -0.51032,-0.44317 -0.51031,-0.44317 9.66913,-10.9852 0.51031,0.44316 0.51032,0.44317 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1475\"\r\n" + 
					"             d=\"m 196.17676,156.30204 c -0.0134,0.0134 -0.0134,0.0269 -0.0269,0.0269 l -0.51032,-0.44316 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1477\"\r\n" + 
					"             d=\"m 196.17676,156.30204 -0.53718,-0.41631 -0.53717,-0.41631 9.66913,-12.27442 0.53717,0.41631 0.53717,0.41631 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1479\"\r\n" + 
					"             d=\"m 205.85931,144.00076 c 0,0.0134 -0.0134,0.0269 -0.0134,0.0269 l -0.53717,-0.41631 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1481\"\r\n" + 
					"             d=\"m 205.85931,144.00076 -0.5506,-0.38945 -0.5506,-0.38945 9.68256,-13.64421 0.5506,0.38945 0.5506,0.38945 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1483\"\r\n" + 
					"             d=\"m 21.622137,226.29579 -0.01343,-0.67147 -0.01343,-0.67147 4.834564,-0.0671 0.01343,0.67146 0.01343,0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1485\"\r\n" + 
					"             d=\"m 26.47013,226.22864 c -0.01343,0 -0.01343,0 -0.01343,0 l -0.01343,-0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1487\"\r\n" + 
					"             d=\"m 26.47013,226.22864 -0.02686,-0.67147 -0.02686,-0.67146 4.834565,-0.17459 0.02686,0.67147 0.02686,0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1489\"\r\n" + 
					"             d=\"m 31.331552,226.05406 c -0.01343,0 -0.02686,0 -0.02686,0 l -0.02686,-0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1491\"\r\n" + 
					"             d=\"m 31.331552,226.05406 -0.05372,-0.67147 -0.05372,-0.67147 9.669128,-0.72518 0.05372,0.67147 0.05372,0.67146 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1493\"\r\n" + 
					"             d=\"m 41.027541,225.32887 c -0.01343,0 -0.02686,0 -0.02686,0 l -0.05372,-0.67146 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1495\"\r\n" + 
					"             d=\"m 41.027541,225.32887 -0.08058,-0.67146 -0.08058,-0.67147 4.834564,-0.53718 0.08058,0.67147 0.08058,0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1497\"\r\n" + 
					"             d=\"m 45.875534,224.7917 c -0.01343,0 -0.01343,0 -0.01343,0 l -0.08058,-0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1499\"\r\n" + 
					"             d=\"m 45.875534,224.7917 -0.09401,-0.67147 -0.09401,-0.67147 4.834564,-0.65803 0.09401,0.67146 0.09401,0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1501\"\r\n" + 
					"             d=\"m 50.736956,224.13366 c -0.01343,0 -0.02686,0 -0.02686,0 l -0.09401,-0.67147 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1503\"\r\n" + 
					"             d=\"m 50.736956,224.13366 -0.120864,-0.67147 -0.120864,-0.67146 9.669128,-1.66524 0.120864,0.67147 0.120864,0.67146 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1505\"\r\n" + 
					"             d=\"m 60.432942,222.45499 c -0.01343,0 -0.02686,0.0134 -0.02686,0.0134 l -0.120864,-0.67146 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1507\"\r\n" + 
					"             d=\"m 60.432942,222.45499 -0.147722,-0.65803 -0.147722,-0.65804 9.669128,-2.16212 0.147722,0.65803 0.147722,0.65804 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1509\"\r\n" + 
					"             d=\"m 70.128928,220.29287 c 0,0 -0.01343,0 -0.02686,0 l -0.14772,-0.65804 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1511\"\r\n" + 
					"             d=\"m 70.128928,220.29287 -0.17458,-0.65804 -0.174583,-0.65803 9.669128,-2.63216 0.174583,0.65804 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1513\"\r\n" + 
					"             d=\"m 79.824917,217.64729 c 0,0 -0.01343,0.0134 -0.02686,0.0134 l -0.17458,-0.65804 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1515\"\r\n" + 
					"             d=\"m 79.824917,217.64729 -0.201441,-0.64461 -0.201441,-0.64461 9.669123,-3.10218 0.20145,0.64461 0.20144,0.64461 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1517\"\r\n" + 
					"             d=\"m 89.534328,214.53168 c -0.0134,0.0134 -0.0268,0.0134 -0.0403,0.0134 l -0.20144,-0.64461 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1519\"\r\n" + 
					"             d=\"m 89.534328,214.53168 -0.24172,-0.63118 -0.24173,-0.63118 9.66913,-3.58563 0.24172,0.63118 0.24173,0.63118 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1521\"\r\n" + 
					"             d=\"m 99.230318,210.94605 c -0.0134,0 -0.0269,0 -0.0269,0 l -0.24173,-0.63118 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1523\"\r\n" + 
					"             d=\"m 99.230318,210.94605 -0.26859,-0.63118 -0.26859,-0.63118 9.669132,-4.0691 0.26859,0.63119 0.26859,0.63117 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1525\"\r\n" + 
					"             d=\"m 108.91288,206.86352 c 0,0 -0.0134,0 -0.0134,0.0134 l -0.26859,-0.63117 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1527\"\r\n" + 
					"             d=\"m 108.91288,206.86352 -0.28202,-0.61774 -0.28202,-0.61775 9.6557,-4.53912 0.28202,0.61775 0.28202,0.61775 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1529\"\r\n" + 
					"             d=\"m 118.59543,202.31098 c 0,0 -0.0134,0 -0.0268,0.0134 l -0.28202,-0.61775 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1531\"\r\n" + 
					"             d=\"m 118.59543,202.31098 -0.30887,-0.60432 -0.30888,-0.60432 9.66913,-5.03601 0.30888,0.60432 0.30887,0.60432 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1533\"\r\n" + 
					"             d=\"m 128.29142,197.26154 c -0.0134,0 -0.0134,0.0134 -0.0269,0.0134 l -0.30887,-0.60432 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1535\"\r\n" + 
					"             d=\"m 128.29142,197.26154 -0.33573,-0.59089 -0.33574,-0.59089 9.66913,-5.4926 0.33574,0.59089 0.33573,0.59089 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1537\"\r\n" + 
					"             d=\"m 137.98741,191.75551 c -0.0134,0 -0.0134,0 -0.0269,0.0134 0,0 0,0 0,0 l -0.33573,-0.59089 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1539\"\r\n" + 
					"             d=\"m 137.98741,191.75551 -0.36259,-0.57746 -0.3626,-0.57746 9.66913,-5.97606 0.36259,0.57746 0.3626,0.57746 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1541\"\r\n" + 
					"             d=\"m 147.66997,185.76603 c 0,0 -0.0134,0.0134 -0.0134,0.0134 l -0.3626,-0.57746 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1543\"\r\n" + 
					"             d=\"m 147.66997,185.76603 -0.37603,-0.56404 -0.37602,-0.56403 9.66913,-6.44609 0.37602,0.56404 0.37602,0.56403 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1545\"\r\n" + 
					"             d=\"m 157.35252,179.31994 c 0,0 -0.0134,0 -0.0134,0 l -0.37602,-0.56403 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1547\"\r\n" + 
					"             d=\"m 157.35252,179.31994 -0.38945,-0.56403 -0.38945,-0.56404 1.35636,-0.94005 0.38945,0.56403 0.38946,0.56404 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1549\"\r\n" + 
					"             d=\"m 158.72232,178.36646 c -0.0134,0 -0.0134,0 -0.0134,0.0134 l -0.38946,-0.56404 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1551\"\r\n" + 
					"             d=\"m 158.72232,178.36646 -0.40289,-0.55061 -0.40287,-0.5506 8.31276,-6.00292 0.40288,0.55061 0.40288,0.5506 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1553\"\r\n" + 
					"             d=\"m 167.04851,172.35011 c -0.0134,0 -0.0134,0.0134 -0.0134,0.0134 l -0.40288,-0.5506 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1555\"\r\n" + 
					"             d=\"m 167.04851,172.35011 -0.41631,-0.53717 -0.41631,-0.53718 8.43363,-6.43265 0.41631,0.53717 0.41631,0.53718 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1557\"\r\n" + 
					"             d=\"m 175.48214,165.91746 c 0,0 0,0 0,0 l -0.41631,-0.53718 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1559\"\r\n" + 
					"             d=\"m 175.48214,165.91746 -0.41631,-0.53718 -0.41631,-0.53717 1.2355,-0.98034 0.41631,0.53717 0.41631,0.53717 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1561\"\r\n" + 
					"             d=\"m 176.73107,164.92368 c 0,0 0,0 -0.0134,0.0134 l -0.41631,-0.53717 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1563\"\r\n" + 
					"             d=\"m 176.73107,164.92368 -0.42974,-0.52374 -0.42974,-0.52375 9.66913,-7.89645 0.42973,0.52374 0.42974,0.52375 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1565\"\r\n" + 
					"             d=\"m 186.41362,157.0138 c 0,0.0134 -0.0134,0.0134 -0.0134,0.0134 l -0.42974,-0.52375 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1567\"\r\n" + 
					"             d=\"m 186.41362,157.0138 -0.44317,-0.51032 -0.44316,-0.51031 9.66913,-8.36648 0.44316,0.51031 0.44317,0.51032 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1569\"\r\n" + 
					"             d=\"m 196.09618,148.63389 c 0,0.0134 -0.0134,0.0134 -0.0134,0.0134 l -0.44317,-0.51032 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1571\"\r\n" + 
					"             d=\"m 196.09618,148.63389 -0.4566,-0.49689 -0.4566,-0.49688 9.66913,-8.84994 0.4566,0.49688 0.4566,0.49689 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1573\"\r\n" + 
					"             d=\"m 205.77874,139.77052 c 0,0.0134 -0.0134,0.0134 -0.0134,0.0134 l -0.4566,-0.49689 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1575\"\r\n" + 
					"             d=\"m 205.77874,139.77052 -0.47003,-0.48346 -0.47003,-0.48345 9.68256,-9.31997 0.47003,0.48346 0.47003,0.48345 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1577\"\r\n" + 
					"             d=\"m 22.280175,225.63775 -0.671467,-0.0134 -0.671467,-0.0134 0.01343,-1.07435 0.671467,0.0134 0.671467,0.0134 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1579\"\r\n" + 
					"             d=\"m 20.95067,224.53654 c 0,-0.0672 0,-0.13429 0.02686,-0.20144 l 0.644609,0.21487 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1581\"\r\n" + 
					"             d=\"m 22.266745,224.76484 -0.644608,-0.21487 -0.644609,-0.21487 4.821135,-14.06052 0.644609,0.21487 0.644608,0.21487 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1583\"\r\n" + 
					"             d=\"m 25.798663,210.27458 c 0.01343,-0.0403 0.04029,-0.0806 0.05372,-0.12087 0.01343,-0.0268 0.04029,-0.0537 0.05372,-0.0806 l 0.537174,0.41631 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1585\"\r\n" + 
					"             d=\"m 26.980446,210.90576 -0.537174,-0.41631 -0.537174,-0.41631 4.834564,-6.25808 0.537174,0.41631 0.537175,0.41631 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1587\"\r\n" + 
					"             d=\"m 30.740662,203.81506 c 0.02686,-0.0268 0.05372,-0.0537 0.08058,-0.0806 l 0.456598,0.49688 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1589\"\r\n" + 
					"             d=\"m 31.734434,204.72826 -0.456598,-0.49689 -0.456598,-0.49688 9.669128,-8.86337 0.456598,0.49688 0.456598,0.49689 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1591\"\r\n" + 
					"             d=\"m 40.490366,194.87112 c 0.01343,-0.0134 0.04029,-0.0403 0.05372,-0.0537 l 0.402882,0.5506 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1593\"\r\n" + 
					"             d=\"m 41.349843,195.91861 -0.402879,-0.55061 -0.402882,-0.5506 4.834564,-3.5722 0.402882,0.5506 0.402879,0.5506 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1595\"\r\n" + 
					"             d=\"m 45.378646,191.2452 c 0.01343,0 0.01343,-0.0134 0.02686,-0.0134 l 0.376021,0.56403 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1597\"\r\n" + 
					"             d=\"m 46.157549,192.35983 -0.376021,-0.56403 -0.376021,-0.56403 4.834564,-3.23648 0.376021,0.56404 0.376021,0.56403 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1599\"\r\n" + 
					"             d=\"m 50.240071,187.99529 c 0.01343,0 0.01343,-0.0134 0.02686,-0.0134 l 0.349163,0.57746 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1601\"\r\n" + 
					"             d=\"m 50.965255,189.13679 -0.349163,-0.57746 -0.349163,-0.57746 9.669128,-5.7209 0.349163,0.57746 0.349163,0.57746 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1603\"\r\n" + 
					"             d=\"m 59.936057,182.26097 c 0,-0.0134 0.01343,-0.0134 0.01343,-0.0134 0,0 0.01343,0 0.02686,-0.0134 l 0.308876,0.60432 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1605\"\r\n" + 
					"             d=\"m 60.594096,183.44275 -0.308876,-0.60432 -0.308876,-0.60432 9.669128,-5.03601 0.308876,0.60432 0.308872,0.60432 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1607\"\r\n" + 
					"             d=\"m 69.645472,177.1981 c 0,0 0.01343,0 0.01343,-0.0134 l 0.295447,0.61774 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1609\"\r\n" + 
					"             d=\"m 70.249791,178.42017 -0.295443,-0.61775 -0.295447,-0.61774 9.669128,-4.56598 0.295447,0.61775 0.295443,0.61775 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1611\"\r\n" + 
					"             d=\"m 79.328029,172.6187 c 0.01343,0 0.01343,0 0.02686,0 l 0.268589,0.61775 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1613\"\r\n" + 
					"             d=\"m 79.892062,173.8542 -0.268586,-0.61775 -0.268589,-0.61775 9.669131,-4.20339 0.26859,0.61775 0.26858,0.61775 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1615\"\r\n" + 
					"             d=\"m 89.024018,168.41531 c 0,0 0.0134,-0.0134 0.0134,-0.0134 l 0.25516,0.63118 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1617\"\r\n" + 
					"             d=\"m 89.547758,169.66424 -0.25515,-0.63118 -0.25516,-0.63118 9.66913,-3.90794 0.25515,0.63118 0.25516,0.63118 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1619\"\r\n" + 
					"             d=\"m 98.706578,164.49394 c 0,0 0.0134,0 0.0134,0 l 0.24172,0.63118 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1621\"\r\n" + 
					"             d=\"m 99.203458,165.7563 -0.24173,-0.63118 -0.24172,-0.63118 9.669122,-3.66621 0.24173,0.63118 0.24173,0.63118 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1623\"\r\n" + 
					"             d=\"m 108.38913,160.82773 c 0,0 0.0134,0 0.0134,-0.0134 l 0.2283,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1625\"\r\n" + 
					"             d=\"m 108.85916,162.10352 -0.2283,-0.64461 -0.2283,-0.64461 9.6557,-3.4782 0.2283,0.64461 0.2283,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1627\"\r\n" + 
					"             d=\"m 118.05826,157.3361 c 0,0 0,0 0.0134,0 l 0.21487,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1629\"\r\n" + 
					"             d=\"m 118.50143,158.62532 -0.21487,-0.64461 -0.21487,-0.64461 9.66913,-3.30362 0.21487,0.64461 0.21487,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1631\"\r\n" + 
					"             d=\"m 127.74082,154.03248 c 0,0 0,0 0,0 l 0.21487,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1633\"\r\n" + 
					"             d=\"m 128.17056,155.3217 -0.21487,-0.64461 -0.21487,-0.64461 9.66913,-3.15589 0.21487,0.64461 0.21486,0.6446 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1635\"\r\n" + 
					"             d=\"m 137.40995,150.87659 c 0.0134,0 0.0134,0 0.0134,0 l 0.20144,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1637\"\r\n" + 
					"             d=\"m 137.82625,152.1658 -0.20143,-0.6446 -0.20144,-0.64461 9.66912,-3.0216 0.20144,0.6446 0.20144,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1639\"\r\n" + 
					"             d=\"m 147.0925,147.85499 c 0,0 0,0 0,0 l 0.20144,0.6446 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1641\"\r\n" + 
					"             d=\"m 147.49538,149.1442 -0.20144,-0.64461 -0.20144,-0.6446 9.66913,-2.91417 0.20144,0.64461 0.20144,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1643\"\r\n" + 
					"             d=\"m 156.76163,144.94082 c 0,0 0.0134,0 0.0134,0 l 0.18801,0.64461 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1645\"\r\n" + 
					"             d=\"m 157.15108,146.23004 -0.18801,-0.64461 -0.18801,-0.64461 1.35637,-0.40288 0.188,0.64461 0.18802,0.6446 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1647\"\r\n" + 
					"             d=\"m 158.13143,144.53794 c 0,0 0,0 0,-0.0134 l 0.188,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1649\"\r\n" + 
					"             d=\"m 158.50745,145.84058 -0.18802,-0.65803 -0.188,-0.65804 8.31276,-2.40386 0.18801,0.65804 0.18801,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1651\"\r\n" + 
					"             d=\"m 166.44419,142.12065 c 0,0 0,0 0,0 l 0.18801,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1653\"\r\n" + 
					"             d=\"m 166.82021,143.43673 -0.18801,-0.65804 -0.18801,-0.65804 8.43363,-2.37699 0.18801,0.65804 0.18801,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1655\"\r\n" + 
					"             d=\"m 174.87782,139.74366 c 0,0 0.0134,0 0.0134,0 l 0.17459,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1657\"\r\n" + 
					"             d=\"m 175.24041,141.05974 -0.17458,-0.65804 -0.17459,-0.65804 1.23551,-0.33573 0.17458,0.65803 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1659\"\r\n" + 
					"             d=\"m 176.47591,140.724 c 0,0 0,0 0,0 l -0.17458,-0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1661\"\r\n" + 
					"             d=\"m 176.47591,140.724 -0.17458,-0.65804 -0.17458,-0.65803 9.66912,-2.64558 0.17458,0.65803 0.17459,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1663\"\r\n" + 
					"             d=\"m 185.79587,136.76235 c 0,0 0,0 0,0 l 0.17458,0.65803 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1665\"\r\n" + 
					"             d=\"m 186.14504,138.07842 -0.17459,-0.65804 -0.17458,-0.65803 9.66913,-2.55158 0.17458,0.65804 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1667\"\r\n" + 
					"             d=\"m 195.465,134.21077 c 0,0 0,0 0,0 l 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1669\"\r\n" + 
					"             d=\"m 195.81416,135.52685 -0.17458,-0.65804 -0.17458,-0.65804 9.66913,-2.48443 0.17458,0.65804 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1671\"\r\n" + 
					"             d=\"m 205.13413,131.72634 c 0.0134,0 0.0134,0 0.0134,0 l 0.16115,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1673\"\r\n" + 
					"             d=\"m 205.46986,133.04242 -0.16115,-0.65804 -0.16115,-0.65804 9.68256,-2.41728 0.16115,0.65804 0.16115,0.65804 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1675\"\r\n" + 
					"             d=\"m 22.280175,225.62432 h -0.671467 -0.671467 l 0.01343,-4.79428 h 0.671467 0.671467 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1677\"\r\n" + 
					"             d=\"m 20.95067,220.83004 c 0,-0.0537 0,-0.094 0.01343,-0.13429 l 0.658038,0.13429 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1679\"\r\n" + 
					"             d=\"m 22.280175,220.96434 -0.658038,-0.1343 -0.658038,-0.13429 4.821135,-23.19248 0.658038,0.1343 0.658037,0.13429 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1681\"\r\n" + 
					"             d=\"m 25.785234,197.50327 c 0.01343,-0.0806 0.04029,-0.14772 0.06715,-0.20144 0.01343,-0.0134 0.01343,-0.0269 0.02686,-0.0403 l 0.564033,0.37603 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1683\"\r\n" + 
					"             d=\"m 27.007304,198.01359 -0.564032,-0.37602 -0.564033,-0.37603 4.834564,-7.26527 0.564033,0.37602 0.564033,0.37602 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1685\"\r\n" + 
					"             d=\"m 30.713803,189.99627 c 0.02686,-0.0403 0.06715,-0.0806 0.09401,-0.12086 l 0.470027,0.49688 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1687\"\r\n" + 
					"             d=\"m 31.747863,190.86917 -0.470027,-0.49688 -0.470027,-0.49688 9.669128,-9.15882 0.470027,0.49689 0.470027,0.49688 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1689\"\r\n" + 
					"             d=\"m 40.476937,180.71659 c 0.02686,-0.0134 0.05372,-0.0403 0.08057,-0.0537 l 0.389453,0.5506 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1691\"\r\n" + 
					"             d=\"m 41.336414,181.76408 -0.38945,-0.5506 -0.389453,-0.5506 4.834564,-3.41106 0.389453,0.5506 0.38945,0.55061 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1693\"\r\n" + 
					"             d=\"m 45.392075,177.25182 c 0.01343,-0.0134 0.01343,-0.0134 0.02686,-0.0269 l 0.362592,0.57746 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1695\"\r\n" + 
					"             d=\"m 46.14412,178.37989 -0.362592,-0.57747 -0.362592,-0.57746 4.834564,-3.00817 0.362592,0.57746 0.362592,0.57746 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1697\"\r\n" + 
					"             d=\"m 50.2535,174.21679 c 0.01343,0 0.01343,0 0.02686,-0.0134 0,0 0.01343,0 0.01343,-0.0134 l 0.322305,0.60432 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1699\"\r\n" + 
					"             d=\"m 50.938397,175.39857 -0.322305,-0.60432 -0.322305,-0.60432 9.669128,-5.11658 0.322305,0.60432 0.322305,0.60432 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1701\"\r\n" + 
					"             d=\"m 59.962915,169.07335 c 0.01343,0 0.02686,-0.0134 0.04029,-0.0134 l 0.282018,0.61775 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1703\"\r\n" + 
					"             d=\"m 60.567235,170.29542 -0.282015,-0.61775 -0.282018,-0.61775 9.669128,-4.31082 0.282018,0.61775 0.282015,0.61775 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1705\"\r\n" + 
					"             d=\"m 69.67233,164.7491 c 0.01343,0 0.02686,-0.0134 0.04029,-0.0134 l 0.241728,0.63118 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1707\"\r\n" + 
					"             d=\"m 70.196076,165.99803 -0.241728,-0.63118 -0.241728,-0.63118 9.669128,-3.78707 0.241728,0.63118 0.241728,0.63117 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1709\"\r\n" + 
					"             d=\"m 79.381748,160.9486 c 0,0 0.01343,0 0.01343,-0.0134 l 0.228299,0.64461 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1711\"\r\n" + 
					"             d=\"m 79.851775,162.22439 -0.228299,-0.64461 -0.228299,-0.64461 9.669131,-3.37077 0.2283,0.64461 0.22829,0.64461 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1713\"\r\n" + 
					"             d=\"m 89.064308,157.5644 c 0.0134,0 0.0134,0 0.0268,0 l 0.20145,0.64461 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1715\"\r\n" + 
					"             d=\"m 89.494048,158.85362 -0.20144,-0.64461 -0.20145,-0.64461 9.66913,-3.07532 0.20144,0.64461 0.20144,0.64461 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1717\"\r\n" + 
					"             d=\"m 98.760288,154.48908 c 0,0 0,0 0.0134,0 l 0.18801,0.64461 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1719\"\r\n" + 
					"             d=\"m 99.149738,155.7783 -0.18801,-0.64461 -0.18801,-0.64461 9.669132,-2.82016 0.18801,0.64461 0.18801,0.64461 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1721\"\r\n" + 
					"             d=\"m 108.44285,151.66892 c 0,-0.0134 0,-0.0134 0.0134,-0.0134 l 0.17458,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1723\"\r\n" + 
					"             d=\"m 108.80544,152.97156 -0.17458,-0.65803 -0.17458,-0.65804 9.6557,-2.61872 0.17458,0.65804 0.17458,0.65803 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1725\"\r\n" + 
					"             d=\"m 118.11198,149.03677 c 0,0 0,0 0.0134,0 l 0.16115,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1727\"\r\n" + 
					"             d=\"m 118.44771,150.35284 -0.16115,-0.65803 -0.16115,-0.65804 9.66912,-2.44414 0.16116,0.65804 0.16115,0.65803 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1729\"\r\n" + 
					"             d=\"m 127.79453,146.59263 c 0,0 0,0 0,0 l 0.16116,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1731\"\r\n" + 
					"             d=\"m 128.11684,147.9087 -0.16115,-0.65803 -0.16116,-0.65804 9.66913,-2.30985 0.16116,0.65804 0.16115,0.65803 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1733\"\r\n" + 
					"             d=\"m 137.46366,144.28278 c 0,0 0.0134,0 0.0134,0 l 0.14773,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1735\"\r\n" + 
					"             d=\"m 137.77254,145.59885 -0.14772,-0.65803 -0.14773,-0.65804 9.66913,-2.18898 0.14772,0.65803 0.14773,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1737\"\r\n" + 
					"             d=\"m 147.14622,142.0938 c 0,0 0,0 0,0 l 0.14772,0.65803 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1739\"\r\n" + 
					"             d=\"m 147.44167,143.40987 -0.14773,-0.65804 -0.14772,-0.65803 9.66913,-2.06812 0.14772,0.65803 0.14772,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1741\"\r\n" + 
					"             d=\"m 156.81535,140.02568 c 0.0134,0 0.0134,0 0.0134,0 l 0.13429,0.65803 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1743\"\r\n" + 
					"             d=\"m 157.09737,141.34175 -0.1343,-0.65804 -0.13429,-0.65803 1.35636,-0.28202 0.13429,0.65804 0.1343,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1745\"\r\n" + 
					"             d=\"m 158.18514,139.74366 c 0,0 0,0 0,0 l 0.13429,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1747\"\r\n" + 
					"             d=\"m 158.45373,141.05974 -0.1343,-0.65804 -0.13429,-0.65804 8.31276,-1.6921 0.1343,0.65804 0.13429,0.65804 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1749\"\r\n" + 
					"             d=\"m 166.4979,138.05156 c 0,-0.0134 0,-0.0134 0,-0.0134 l 0.1343,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1751\"\r\n" + 
					"             d=\"m 166.76649,139.38107 -0.13429,-0.67147 -0.1343,-0.67147 8.43364,-1.65181 0.13429,0.67147 0.13429,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1753\"\r\n" + 
					"             d=\"m 174.93154,136.38632 c 0,0 0,0 0,0 l 0.13429,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1755\"\r\n" + 
					"             d=\"m 175.20012,137.72926 -0.13429,-0.67147 -0.13429,-0.67147 1.23549,-0.24172 0.1343,0.67146 0.13429,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1757\"\r\n" + 
					"             d=\"m 176.16703,136.1446 c 0,0 0.0134,0 0.0134,0 l 0.12087,0.67146 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1759\"\r\n" + 
					"             d=\"m 176.42219,137.48753 -0.12086,-0.67147 -0.12087,-0.67146 9.66913,-1.81297 0.12086,0.67147 0.12087,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1761\"\r\n" + 
					"             d=\"m 185.84959,134.33163 c 0,0 0,0 0,0 l 0.12086,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1763\"\r\n" + 
					"             d=\"m 186.09132,135.67457 -0.12087,-0.67147 -0.12086,-0.67147 9.66913,-1.74581 0.12086,0.67147 0.12087,0.67146 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1765\"\r\n" + 
					"             d=\"m 195.51872,132.58582 c 0,0 0,0 0,0 l 0.12086,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1767\"\r\n" + 
					"             d=\"m 195.76045,133.92875 -0.12087,-0.67146 -0.12086,-0.67147 9.66913,-1.67867 0.12086,0.67147 0.12086,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1769\"\r\n" + 
					"             d=\"m 205.18785,130.90715 c 0,0 0.0134,0 0.0134,0 l 0.10744,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1771\"\r\n" + 
					"             d=\"m 205.41614,132.25009 -0.10743,-0.67147 -0.10744,-0.67147 9.68256,-1.61152 0.10744,0.67147 0.10743,0.67147 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1773\"\r\n" + 
					"             d=\"m 21.648996,226.16149 -0.04029,-0.53717 -0.04029,-0.53717 4.834564,-0.38946 0.04029,0.53718 0.04029,0.53717 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1775\"\r\n" + 
					"             d=\"m 26.523848,225.77204 c -0.01343,0 -0.02686,0 -0.04029,0 l -0.04029,-0.53717 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1777\"\r\n" + 
					"             d=\"m 26.523848,225.77204 -0.08058,-0.53717 -0.08058,-0.53718 4.834563,-0.68489 0.08058,0.53717 0.08058,0.53718 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1779\"\r\n" + 
					"             d=\"m 31.385271,225.08715 c -0.01343,0 -0.02686,0 -0.02686,0 l -0.08058,-0.53718 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1781\"\r\n" + 
					"             d=\"m 31.385271,225.08715 -0.107435,-0.53718 -0.107435,-0.53717 9.669128,-1.96069 0.107435,0.53718 0.107435,0.53717 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1783\"\r\n" + 
					"             d=\"m 41.081257,223.11303 c -0.01343,0 -0.01343,0.0134 -0.02686,0.0134 l -0.107435,-0.53717 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1785\"\r\n" + 
					"             d=\"m 41.081257,223.11303 -0.134293,-0.52374 -0.134293,-0.52375 4.834564,-1.19521 0.134293,0.52375 0.134293,0.52374 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1787\"\r\n" + 
					"             d=\"m 45.92925,221.91782 c -0.01343,0 -0.01343,0 -0.01343,0 l -0.134293,-0.52374 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1789\"\r\n" + 
					"             d=\"m 45.92925,221.91782 -0.147722,-0.52374 -0.147722,-0.52375 4.834564,-1.32951 0.147722,0.52375 0.147722,0.52375 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1791\"\r\n" + 
					"             d=\"m 50.777243,220.58832 c 0,0 -0.01343,0 -0.01343,0 l -0.147722,-0.52375 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1793\"\r\n" + 
					"             d=\"m 50.777243,220.58832 -0.161151,-0.52375 -0.161154,-0.52375 9.669128,-3.00817 0.161154,0.52375 0.161151,0.52374 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1795\"\r\n" + 
					"             d=\"m 60.4598,217.56671 c 0,0 -0.01343,0.0134 -0.01343,0.0134 l -0.161151,-0.52374 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1797\"\r\n" + 
					"             d=\"m 60.4598,217.56671 -0.17458,-0.51031 -0.174583,-0.51032 9.669128,-3.39762 0.174583,0.51031 0.17458,0.51032 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1799\"\r\n" + 
					"             d=\"m 70.155789,214.16909 c -0.01343,0 -0.01343,0 -0.02686,0 l -0.17458,-0.51032 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1801\"\r\n" + 
					"             d=\"m 70.155789,214.16909 -0.201441,-0.51032 -0.201441,-0.51031 9.669128,-3.76022 0.201441,0.51032 0.201441,0.51031 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1803\"\r\n" + 
					"             d=\"m 79.838346,210.39544 c -0.01343,0 -0.01343,0.0134 -0.01343,0.0134 l -0.201441,-0.51031 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1805\"\r\n" + 
					"             d=\"m 79.838346,210.39544 -0.21487,-0.49688 -0.21487,-0.49689 9.669132,-4.09595 0.21487,0.49689 0.21487,0.49688 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1807\"\r\n" + 
					"             d=\"m 89.520898,206.29949 c -0.0134,0 -0.0134,0 -0.0134,0 l -0.21487,-0.49688 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1809\"\r\n" + 
					"             d=\"m 89.520898,206.29949 -0.22829,-0.49688 -0.2283,-0.49689 9.66912,-4.39139 0.2283,0.49688 0.2283,0.49689 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1811\"\r\n" + 
					"             d=\"m 99.203458,201.89467 c -0.0134,0.0134 -0.0134,0.0134 -0.0134,0.0134 l -0.2283,-0.49689 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1813\"\r\n" + 
					"             d=\"m 99.203458,201.89467 -0.24173,-0.48346 -0.24172,-0.48345 9.669122,-4.67342 0.24173,0.48346 0.24173,0.48346 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1815\"\r\n" + 
					"             d=\"m 108.87259,197.22126 c 0,0 0,0 0,0 l -0.24173,-0.48346 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1817\"\r\n" + 
					"             d=\"m 108.87259,197.22126 -0.24173,-0.48346 -0.24173,-0.48346 9.6557,-4.942 0.24173,0.48346 0.24173,0.48346 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1819\"\r\n" + 
					"             d=\"m 118.54172,192.27926 c 0,0 0,0 -0.0134,0 l -0.24173,-0.48346 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1821\"\r\n" + 
					"             d=\"m 118.54172,192.27926 -0.25516,-0.48346 -0.25516,-0.48346 9.66913,-5.19715 0.25516,0.48345 0.25516,0.48346 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1823\"\r\n" + 
					"             d=\"m 128.22427,187.06867 c 0,0 -0.0134,0.0134 -0.0134,0.0134 l -0.25516,-0.48346 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1825\"\r\n" + 
					"             d=\"m 128.22427,187.06867 -0.26858,-0.47003 -0.26859,-0.47002 9.66913,-5.43889 0.26859,0.47003 0.26858,0.47003 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1827\"\r\n" + 
					"             d=\"m 137.8934,181.62979 c 0,0 0,0 0,0 0,0 0,0 0,0 l -0.26858,-0.47003 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1829\"\r\n" + 
					"             d=\"m 137.8934,181.62979 -0.26858,-0.47003 -0.26859,-0.47003 9.66913,-5.66718 0.26858,0.47003 0.26859,0.47002 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1831\"\r\n" + 
					"             d=\"m 147.57596,175.9626 c 0,0 0,0 -0.0134,0 l -0.26859,-0.47002 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1833\"\r\n" + 
					"             d=\"m 147.57596,175.9626 -0.28202,-0.47002 -0.28201,-0.47003 9.66912,-5.89548 0.28202,0.47003 0.28202,0.47002 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1835\"\r\n" + 
					"             d=\"m 157.24509,170.06712 c 0,0 0,0 0,0 l -0.28202,-0.47002 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1837\"\r\n" + 
					"             d=\"m 157.24509,170.06712 -0.28202,-0.47002 -0.28202,-0.47003 1.35637,-0.83262 0.28201,0.47002 0.28202,0.47003 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1839\"\r\n" + 
					"             d=\"m 158.61488,169.22107 c 0,0 -0.0134,0 -0.0134,0.0134 l -0.28202,-0.47003 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1841\"\r\n" + 
					"             d=\"m 158.61488,169.22107 -0.29545,-0.4566 -0.29544,-0.45659 8.31276,-5.27773 0.29545,0.45659 0.29544,0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1843\"\r\n" + 
					"             d=\"m 166.92764,163.94334 c 0,0 0,0 0,0 l -0.29544,-0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1845\"\r\n" + 
					"             d=\"m 166.92764,163.94334 -0.29544,-0.4566 -0.29545,-0.45659 8.43363,-5.49261 0.29545,0.4566 0.29544,0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1847\"\r\n" + 
					"             d=\"m 175.36127,158.45074 c 0,0 0,0 0,0 l -0.29544,-0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1849\"\r\n" + 
					"             d=\"m 175.36127,158.45074 -0.29544,-0.4566 -0.29545,-0.4566 1.2355,-0.81919 0.29545,0.4566 0.29544,0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1851\"\r\n" + 
					"             d=\"m 176.6102,157.63155 c 0,0 -0.0134,0 -0.0134,0 l -0.29544,-0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1853\"\r\n" + 
					"             d=\"m 176.6102,157.63155 -0.30887,-0.4566 -0.30888,-0.4566 9.66913,-6.51323 0.30887,0.4566 0.30888,0.45659 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1855\"\r\n" + 
					"             d=\"m 186.27933,151.10489 c 0,0 0,0 0,0.0134 l -0.30888,-0.4566 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1857\"\r\n" + 
					"             d=\"m 186.27933,151.10489 -0.30888,-0.44317 -0.30887,-0.44317 9.66913,-6.71467 0.30887,0.44317 0.30888,0.44317 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1859\"\r\n" + 
					"             d=\"m 195.96189,144.39022 c -0.0134,0 -0.0134,0 -0.0134,0 l -0.30888,-0.44317 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1861\"\r\n" + 
					"             d=\"m 195.96189,144.39022 -0.32231,-0.44317 -0.3223,-0.44317 9.66913,-6.90269 0.3223,0.44317 0.3223,0.44317 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1863\"\r\n" + 
					"             d=\"m 205.63101,137.48753 c 0,0 0,0 0,0 l -0.3223,-0.44317 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1865\"\r\n" + 
					"             d=\"m 205.63101,137.48753 -0.3223,-0.44317 -0.3223,-0.44317 9.68255,-7.07726 0.32231,0.44317 0.3223,0.44317 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1867\"\r\n" + 
					"             d=\"m 21.904153,226.22864 -0.295445,-0.60432 -0.295446,-0.60432 4.834564,-2.40385 0.295446,0.60432 0.295445,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1869\"\r\n" + 
					"             d=\"m 26.147826,222.61615 c 0,0 0,0 0,0 l 0.295446,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1871\"\r\n" + 
					"             d=\"m 26.738717,223.82479 -0.295445,-0.60432 -0.295446,-0.60432 4.834563,-2.39043 0.295447,0.60432 0.295444,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1873\"\r\n" + 
					"             d=\"m 31.57328,221.43436 -0.295444,-0.60432 -0.295447,-0.60432 9.669128,-4.78084 0.295447,0.60432 0.295444,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1875\"\r\n" + 
					"             d=\"m 41.242408,216.65352 -0.295444,-0.60432 -0.295447,-0.60432 4.834564,-2.39043 0.295447,0.60432 0.295444,0.60433 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1877\"\r\n" + 
					"             d=\"m 46.076972,214.2631 -0.295444,-0.60433 -0.295447,-0.60432 4.834564,-2.39042 0.295447,0.60432 0.295444,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1879\"\r\n" + 
					"             d=\"m 50.911536,211.87267 -0.295444,-0.60432 -0.295447,-0.60432 9.669128,-4.78085 0.295447,0.60432 0.295444,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1881\"\r\n" + 
					"             d=\"m 60.580664,207.09182 -0.295444,-0.60432 -0.295447,-0.60432 9.669128,-4.78084 0.295447,0.60432 0.295443,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1883\"\r\n" + 
					"             d=\"m 70.249791,202.31098 c 0,0 0,0 0,0 l -0.295443,-0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1885\"\r\n" + 
					"             d=\"m 70.249791,202.31098 -0.295443,-0.60432 -0.295447,-0.60432 9.669128,-4.79428 0.295447,0.60432 0.295443,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1887\"\r\n" + 
					"             d=\"m 79.328029,196.30806 c 0,0 0,0 0,0 l 0.295447,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1889\"\r\n" + 
					"             d=\"m 79.918919,197.5167 -0.295443,-0.60432 -0.295447,-0.60432 9.669129,-4.78085 0.29545,0.60432 0.29544,0.60433 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1891\"\r\n" + 
					"             d=\"m 89.588048,192.73586 -0.29544,-0.60433 -0.29545,-0.60432 9.66913,-4.78084 0.29544,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1893\"\r\n" + 
					"             d=\"m 99.257178,187.95501 -0.29545,-0.60432 -0.29544,-0.60432 9.669122,-4.78085 0.29545,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1895\"\r\n" + 
					"             d=\"m 108.33541,181.96552 c 0,0 0,0 0,0 l 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1897\"\r\n" + 
					"             d=\"m 108.92631,183.17416 -0.29545,-0.60432 -0.29545,-0.60432 9.6557,-4.76742 0.29545,0.60432 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1899\"\r\n" + 
					"             d=\"m 118.582,178.40674 c 0,0 0,0 0,0 l -0.29544,-0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1901\"\r\n" + 
					"             d=\"m 118.582,178.40674 -0.29544,-0.60432 -0.29545,-0.60432 9.66913,-4.79427 0.29545,0.60432 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1903\"\r\n" + 
					"             d=\"m 127.66024,172.40383 c 0,0 0,0 0,0 l 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1905\"\r\n" + 
					"             d=\"m 128.25113,173.61247 -0.29544,-0.60432 -0.29545,-0.60432 9.66913,-4.78085 0.29545,0.60432 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1907\"\r\n" + 
					"             d=\"m 137.92026,168.83162 -0.29544,-0.60432 -0.29545,-0.60432 9.66913,-4.78085 0.29544,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1909\"\r\n" + 
					"             d=\"m 147.58939,164.05077 -0.29545,-0.60432 -0.29544,-0.60432 9.66913,-4.78084 0.29544,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1911\"\r\n" + 
					"             d=\"m 157.25852,159.26993 c 0,0 0,0 0,0 l -0.29545,-0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1913\"\r\n" + 
					"             d=\"m 157.25852,159.26993 -0.29545,-0.60432 -0.29544,-0.60432 1.35636,-0.67147 0.29544,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1915\"\r\n" + 
					"             d=\"m 158.02399,157.38982 c 0,0 0,0 0,0 l 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1917\"\r\n" + 
					"             d=\"m 158.61488,158.59846 -0.29545,-0.60432 -0.29544,-0.60432 8.31276,-4.10938 0.29545,0.60432 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1919\"\r\n" + 
					"             d=\"m 166.92764,154.48908 c 0,0 0,0 0,0 l -0.29544,-0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1921\"\r\n" + 
					"             d=\"m 166.92764,154.48908 -0.29544,-0.60432 -0.29545,-0.60432 8.43363,-4.17652 0.29545,0.60431 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1923\"\r\n" + 
					"             d=\"m 175.3747,150.31255 c 0,0 -0.0134,0 -0.0134,0 l -0.29544,-0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1925\"\r\n" + 
					"             d=\"m 175.3747,150.31255 -0.30887,-0.60432 -0.30888,-0.60431 1.2355,-0.61776 0.30888,0.60433 0.30887,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1927\"\r\n" + 
					"             d=\"m 175.99245,148.48616 c 0,0 0.0134,0 0.0134,0 l 0.29545,0.60433 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1929\"\r\n" + 
					"             d=\"m 176.59677,149.69481 -0.29544,-0.60432 -0.29545,-0.60433 9.66913,-4.78084 0.29544,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1931\"\r\n" + 
					"             d=\"m 186.2659,144.91396 -0.29545,-0.60432 -0.29544,-0.60432 9.66913,-4.78085 0.29544,0.60432 0.29545,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1933\"\r\n" + 
					"             d=\"m 195.93503,140.13311 -0.29545,-0.60432 -0.29544,-0.60432 9.66913,-4.78085 0.29544,0.60432 0.29545,0.60433 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1935\"\r\n" + 
					"             d=\"m 205.01327,134.14362 c 0,0 0,0 0,0 l 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path1937\"\r\n" + 
					"             d=\"m 205.60416,135.35227 -0.29545,-0.60433 -0.29544,-0.60432 9.68255,-4.78084 0.29545,0.60432 0.29544,0.60432 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1943\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"17.512756\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1941\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"17.512756\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1939\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">0%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1949\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"35.252922\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1947\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"35.252922\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1945\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">10%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1955\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"54.591179\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1953\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"54.591179\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1951\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">20%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1961\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"73.929436\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1959\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"73.929436\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1957\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">30%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1967\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"93.267693\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1965\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"93.267693\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1963\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">40%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1973\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"112.60595\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1971\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"112.60595\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1969\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">50%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1979\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"131.9442\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1977\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"131.9442\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1975\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">60%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1985\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"151.28246\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1983\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"151.28246\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1981\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">70%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1991\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"170.62071\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1989\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"170.62071\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1987\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">80%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text1997\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"189.95897\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan1995\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"189.95897\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1993\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">90%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2003\"\r\n" + 
					"             y=\"233.95052\"\r\n" + 
					"             x=\"207.71255\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2001\"\r\n" + 
					"               y=\"233.95052\"\r\n" + 
					"               x=\"207.71255\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan1999\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">100%</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2009\"\r\n" + 
					"             y=\"227.58501\"\r\n" + 
					"             x=\"15.055187\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2007\"\r\n" + 
					"               y=\"227.58501\"\r\n" + 
					"               x=\"15.055187\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2005\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">0</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2015\"\r\n" + 
					"             y=\"218.00987\"\r\n" + 
					"             x=\"15.055187\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2013\"\r\n" + 
					"               y=\"218.00987\"\r\n" + 
					"               x=\"15.055187\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2011\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">2</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2021\"\r\n" + 
					"             y=\"208.44818\"\r\n" + 
					"             x=\"15.055187\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2019\"\r\n" + 
					"               y=\"208.44818\"\r\n" + 
					"               x=\"15.055187\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2017\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">4</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2027\"\r\n" + 
					"             y=\"198.87306\"\r\n" + 
					"             x=\"15.055187\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2025\"\r\n" + 
					"               y=\"198.87306\"\r\n" + 
					"               x=\"15.055187\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2023\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">6</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2033\"\r\n" + 
					"             y=\"189.31137\"\r\n" + 
					"             x=\"15.055187\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2031\"\r\n" + 
					"               y=\"189.31137\"\r\n" + 
					"               x=\"15.055187\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2029\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">8</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2039\"\r\n" + 
					"             y=\"179.74968\"\r\n" + 
					"             x=\"11.859003\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2037\"\r\n" + 
					"               y=\"179.74968\"\r\n" + 
					"               x=\"11.859003\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2035\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">10</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2045\"\r\n" + 
					"             y=\"170.17456\"\r\n" + 
					"             x=\"11.859003\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2043\"\r\n" + 
					"               y=\"170.17456\"\r\n" + 
					"               x=\"11.859003\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2041\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">12</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2051\"\r\n" + 
					"             y=\"160.61285\"\r\n" + 
					"             x=\"11.859003\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2049\"\r\n" + 
					"               y=\"160.61285\"\r\n" + 
					"               x=\"11.859003\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2047\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">14</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2057\"\r\n" + 
					"             y=\"151.03773\"\r\n" + 
					"             x=\"11.859003\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2055\"\r\n" + 
					"               y=\"151.03773\"\r\n" + 
					"               x=\"11.859003\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2053\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">16</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2063\"\r\n" + 
					"             y=\"141.47604\"\r\n" + 
					"             x=\"11.859003\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2061\"\r\n" + 
					"               y=\"141.47604\"\r\n" + 
					"               x=\"11.859003\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2059\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">18</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2069\"\r\n" + 
					"             y=\"131.91435\"\r\n" + 
					"             x=\"11.859003\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2067\"\r\n" + 
					"               y=\"131.91435\"\r\n" + 
					"               x=\"11.859003\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2065\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.671px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">20</tspan></tspan></text>\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2085\"\r\n" + 
					"             d=\"m 163.83889,186.26291 c 0,-0.0537 0.0134,-0.12086 0.0403,-0.17458 0.0269,-0.0537 0.0671,-0.094 0.12087,-0.12086 0.0537,-0.0269 0.12086,-0.0403 0.17458,-0.0403 v 0.33573 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2139\"\r\n" + 
					"             y=\"247.30235\"\r\n" + 
					"             x=\"80.060562\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:8.79923px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.472441\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2137\"\r\n" + 
					"               y=\"247.30235\"\r\n" + 
					"               x=\"80.060562\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.472441\"><tspan\r\n" + 
					"                 id=\"tspan2135\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:8.79923px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.472441\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Proportion correcte</tspan></tspan></text>\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2145\"\r\n" + 
					"             y=\"2.8053498\"\r\n" + 
					"             x=\"-202.98183\"\r\n" + 
					"             transform=\"rotate(-90)\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:9.60255px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.515573\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2143\"\r\n" + 
					"               y=\"2.8053498\"\r\n" + 
					"               x=\"-202.98183\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.515573\"><tspan\r\n" + 
					"                 id=\"tspan2141\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:9.60255px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.515573\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Note sur 20</tspan></tspan></text>\r\n" + 
					"          <path\r\n" + 
					"             id=\"path3478\"\r\n" + 
					"             d=\"M 21.388188,177.69129 215.2118,177.89271\"\r\n" + 
					"             style=\"fill:none;stroke:#000000;stroke-width:1.4194;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\" />\r\n" + 
					"          <ellipse\r\n" + 
					"             ry=\"4.7163844\"\r\n" + 
					"             rx=\"6.1738563\"\r\n" + 
					"             cy=\"177.96814\"\r\n" + 
					"             cx=\"13.999765\"\r\n" + 
					"             id=\"path3480\"\r\n" + 
					"             style=\"opacity:0.99;fill:none;fill-opacity:0.761745;stroke:#ff3838;stroke-width:1.41781;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\" />\r\n" + 
					"        </g>\r\n" + 
					"        <g\r\n" + 
					"           id=\"g3476\"\r\n" + 
					"           transform=\"matrix(1.2416735,0,0,1.2416735,27.245694,-31.374873)\">\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2077\"\r\n" + 
					"             d=\"m 193.23573,222.75044 h -29.0611 v -36.48753 h 58.1222 v 36.48753 z\"\r\n" + 
					"             style=\"fill:#ffffff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2083\"\r\n" + 
					"             d=\"m 164.52379,222.75044 h -0.34916 -0.33574 v -36.48753 h 0.33574 0.34916 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2087\"\r\n" + 
					"             d=\"m 164.17463,186.61207 v -0.34916 -0.33573 h 72.38115 v 0.33573 0.34916 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.397082\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2091\"\r\n" + 
					"             d=\"m 235.85792,185.89405 h 0.33573 0.34916 v 36.48753 h -0.34916 -0.33573 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2095\"\r\n" + 
					"             d=\"m 236.51051,222.4147 v 0.33574 0.34916 h -72.73353 v -0.34916 -0.33574 z\"\r\n" + 
					"             style=\"fill:#000000;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.562925\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2097\"\r\n" + 
					"             d=\"m 168.52574,190.238 v -0.6849 -0.67147 h 10.74347 v 0.67147 0.6849 z\"\r\n" + 
					"             style=\"fill:#004586;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2099\"\r\n" + 
					"             d=\"m 168.52574,196.07976 v -0.5506 -0.53718 h 10.74347 v 0.53718 0.5506 z\"\r\n" + 
					"             style=\"fill:#e8a202;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2101\"\r\n" + 
					"             d=\"m 168.52574,202.19011 v -0.68489 -0.67147 h 10.74347 v 0.67147 0.68489 z\"\r\n" + 
					"             style=\"fill:#ff4200;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2103\"\r\n" + 
					"             d=\"m 168.52574,208.16617 v -0.68489 -0.67147 h 10.74347 v 0.67147 0.68489 z\"\r\n" + 
					"             style=\"fill:#a0522d;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2105\"\r\n" + 
					"             d=\"m 168.52574,214.14223 v -0.6849 -0.67146 h 10.74347 v 0.67146 0.6849 z\"\r\n" + 
					"             style=\"fill:#579d1c;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <path\r\n" + 
					"             id=\"path2107\"\r\n" + 
					"             d=\"m 168.52574,220.11829 v -0.6849 -0.67146 h 10.74347 v 0.67146 0.6849 z\"\r\n" + 
					"             style=\"fill:#9400d3;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.355827\" />\r\n" + 
					"          <text\r\n" + 
					"             id=\"text2133\"\r\n" + 
					"             y=\"191.16461\"\r\n" + 
					"             x=\"180.61215\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;line-height:126.158%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.355827\"\r\n" + 
					"             xml:space=\"preserve\"><tspan\r\n" + 
					"               id=\"tspan2111\"\r\n" + 
					"               y=\"191.16461\"\r\n" + 
					"               x=\"180.61215\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2109\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Progression 1 (linéaire)</tspan></tspan><tspan\r\n" + 
					"               id=\"tspan2115\"\r\n" + 
					"               y=\"197.14066\"\r\n" + 
					"               x=\"180.61215\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2113\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Progression 1,5</tspan></tspan><tspan\r\n" + 
					"               id=\"tspan2119\"\r\n" + 
					"               y=\"203.1167\"\r\n" + 
					"               x=\"180.61215\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2117\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Progression 0,33</tspan></tspan><tspan\r\n" + 
					"               id=\"tspan2123\"\r\n" + 
					"               y=\"209.09274\"\r\n" + 
					"               x=\"180.61215\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2121\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Progression 0,5</tspan></tspan><tspan\r\n" + 
					"               id=\"tspan2127\"\r\n" + 
					"               y=\"215.06877\"\r\n" + 
					"               x=\"180.61215\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2125\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Progression 2</tspan></tspan><tspan\r\n" + 
					"               id=\"tspan2131\"\r\n" + 
					"               y=\"221.04482\"\r\n" + 
					"               x=\"180.61215\"\r\n" + 
					"               sodipodi:role=\"line\"\r\n" + 
					"               style=\"stroke-width:0.355827\"><tspan\r\n" + 
					"                 id=\"tspan2129\"\r\n" + 
					"                 style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:4.73695px;font-family:'Liberation Sans';fill:#000000;stroke-width:0.355827\"\r\n" + 
					"                 dy=\"0\"\r\n" + 
					"                 dx=\"0\">Progression 3</tspan></tspan></text>\r\n" + 
					"        </g>\r\n" + 
					"      </g>\r\n" + 
					"      <text\r\n" + 
					"         xml:space=\"preserve\"\r\n" + 
					"         style=\"font-style:normal;font-variant:normal;font-weight:700;font-size:5.41776px;line-height:125%;font-family:'Liberation Sans';text-align:start;letter-spacing:0px;word-spacing:0px;text-anchor:start;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.291865\"\r\n" + 
					"         x=\"20.27533\"\r\n" + 
					"         y=\"59.82999\"\r\n" + 
					"         id=\"text3051\"><tspan\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:6.28029px;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.291865\"\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"20.27533\"\r\n" + 
					"           y=\"59.82999\"\r\n" + 
					"           id=\"tspan3049\"><tspan\r\n" + 
					"             dx=\"0\"\r\n" + 
					"             dy=\"0\"\r\n" + 
					"             style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:6.28029px;font-family:Arial;-inkscape-font-specification:'Arial Bold';fill:#000000;stroke-width:0.291865\"\r\n" + 
					"             id=\"tspan3047\">Progression de la note en fonction de la proportion correcte</tspan></tspan></text>\r\n" + 
					"      <text\r\n" + 
					"         xml:space=\"preserve\"\r\n" + 
					"         style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:5.66882px;line-height:100%;font-family:Zengo;-inkscape-font-specification:Zengo;letter-spacing:0px;word-spacing:0px;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.21258\"\r\n" + 
					"         x=\"140.30444\"\r\n" + 
					"         y=\"66.886063\"\r\n" + 
					"         id=\"text3888\"><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           id=\"tspan3886\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"66.886063\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\">Exemple pour avoir une note de <tspan\r\n" + 
					"   id=\"tspan5137\"\r\n" + 
					"   style=\"font-size:4.57426px;fill:#ff0000;stroke-width:0.21258\">10/20</tspan>, lorsque la progression est de</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"72.554886\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3890\">     </tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"78.223701\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3894\">     <tspan\r\n" + 
					"   id=\"tspan5115\"\r\n" + 
					"   style=\"font-size:4.57426px;fill:#ff0000;stroke-width:0.21258\">0,33.</tspan>   Il faut <tspan\r\n" + 
					"   style=\"fill:#0000ff\"\r\n" + 
					"   id=\"tspan3498\">12.5%</tspan> de proportion correcte.</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"83.892525\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3896\">    <tspan\r\n" + 
					"   id=\"tspan5121\"\r\n" + 
					"   style=\"fill:#ff0000;stroke-width:0.21258\"> </tspan><tspan\r\n" + 
					"   id=\"tspan5119\"\r\n" + 
					"   style=\"font-size:4.57426px;fill:#ff0000;stroke-width:0.21258\">0,5.</tspan>     Il faut <tspan\r\n" + 
					"   style=\"fill:#0000ff\"\r\n" + 
					"   id=\"tspan3496\">25%</tspan> de proportion correcte.</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"89.56134\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3898\">     <tspan\r\n" + 
					"   id=\"tspan5125\"\r\n" + 
					"   style=\"font-size:4.57426px;fill:#ff0000;stroke-width:0.21258\">1.</tspan>        Il faut <tspan\r\n" + 
					"   style=\"fill:#0000ff\"\r\n" + 
					"   id=\"tspan3494\">50%</tspan> de proportion correcte.</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"95.230164\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3482\">     <tspan\r\n" + 
					"   style=\"fill:#ff0000\"\r\n" + 
					"   id=\"tspan3484\">1,5.</tspan>      Il faut <tspan\r\n" + 
					"   style=\"fill:#0000ff\"\r\n" + 
					"   id=\"tspan3486\">63%</tspan> de proportion correcte.</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"100.89898\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3900\">     <tspan\r\n" + 
					"   id=\"tspan5129\"\r\n" + 
					"   style=\"font-size:4.57426px;fill:#ff0000;stroke-width:0.21258\">2.</tspan>        Il faut <tspan\r\n" + 
					"   style=\"fill:#0000ff\"\r\n" + 
					"   id=\"tspan3490\">70.7%</tspan> de proportion correcte.</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"106.5678\"\r\n" + 
					"           style=\"font-style:normal;font-variant:normal;font-weight:bold;font-stretch:normal;font-size:3.96818px;line-height:100%;font-family:Arial;-inkscape-font-specification:'Arial Bold';stroke-width:0.21258\"\r\n" + 
					"           id=\"tspan3902\">     <tspan\r\n" + 
					"   id=\"tspan5133\"\r\n" + 
					"   style=\"font-size:4.57426px;fill:#ff0000;stroke-width:0.21258\">3. </tspan>       Il faut <tspan\r\n" + 
					"   style=\"fill:#0000ff\"\r\n" + 
					"   id=\"tspan3492\">79.4%</tspan> de proportion correcte.</tspan><tspan\r\n" + 
					"           sodipodi:role=\"line\"\r\n" + 
					"           x=\"140.30444\"\r\n" + 
					"           y=\"112.23662\"\r\n" + 
					"           id=\"tspan3892\"\r\n" + 
					"           style=\"stroke-width:0.21258\" /></text>\r\n" + 
					"    </g>\r\n" + 
					"  </g>\r\n" + 
					"</svg>";
		}

		/**
		 * Retourne l'entête et les styles CSS du formulaire
		 * @return
		 */
		public static String getHTMLenteteEtCssFormulaireHTML() {
			Date aujourdhui = new Date();
			DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
			
			return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r"
					+ "<html>\r"
					+ "<head>\r"
					+ "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\r"
					+ "<title>Analyse mise en page d'un texte long</title>\r"
					
					+ "<meta name=\"generator\" content=\"AnalyseWriter V1.0\"/>"
					+ "<meta name=\"author\" content=\"Pablo Rodriguez\"/>"
					+ "<meta name=\"created\" content=\""+  mediumDateFormat.format(aujourdhui) +"\"/>"
					+ "<meta name=\"changedby\" content=\"Pablo Rodriguez\"/>"
					+ "<meta name=\"changed\" content=\"" + mediumDateFormat.format(aujourdhui) + "\"/>"
					
					+"<style type=\"text/css\">" 
					+ "p.pablo { margin-bottom: 0.25cm; line-height: 100%; background: transparent;  margin-left: 1cm; }"
					+ ".header {background-color: #f1f1f1;padding: 30px;text-align:center;}"
					+ "h1 { margin-bottom: 0.25cm; background: transparent;}"
					+ "header.h1.western { font-family: \"Arial\"; font-size: 18pt; font-weight: bold; backgroung: #adff2f;}"
					+ ".header p {color:blue; font-size:30px;}"
					+ ".header h4 {text-align:left;font-family: \"Arial\"; font-size: 12pt; font-weight: bold; line-height: 110%;}"
					+ "h4.western { font-family: \"Arial\"; font-size: 14pt; font-style: italic; font-weight: bold; line-height: 40%}"
					+ "a:link { color: #000080; so-language: zxx; text-decoration: underline }" 
					+ "a:visited { color: #800000; so-language: zxx; text-decoration: underline }"
					+ "hr { display: block; margin-top: 0.5em; margin-bottom: 8em; margin-left: 2em; margin-right: 2em; border-style: inset; border-width: 4px;}"
					+ "spanpablo { float: right; width: 8em; font-size: 250%; font-family: algerian, courier; line-height: 80%; margin-right: 1%; color: red; text-align: center}"
					+ "p.p1{margin-bottom: 0cm; margin-top: 0cm; line-height: 100%; background: transparent;  margin-left: 0cm; white-space: pre;}"
					+ "p.p2{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; line-height: 115%}"
					+ "p.p3{margin-left: 20px; line-height: 100%; border: 1px solid black; background-color: lightcyan; margin-right: 10px;  }"
					+ "p.p4{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; margin-right: 4px; line-height: 115%; background: darkblue; color:white; font-size: 20px; white-space: pre;}"
					+ "p.p5{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: red; color:white; font-size: 20px;}"
					+ "p.p6{margin-left: 0px; margin-bottom: 0cm; margin-top: 4px; margin-right: 4px; line-height: 115%; background: beige; color:darkcyan; font-size: 20px; white-space: pre;}"
					+ "p.p7{margin-left: 80px; margin-bottom: 0cm; margin-top: 4px; margin-right: 80px; line-height: 115%; background: #7FFF00; font-size: 20px;}"
					+ "#navbar {overflow: hidden;background-color: #333;width:100%;box-shadow: 5px 10px 8px #888888;}"
					+ "#navbar a {float: left;display: block;color: #f2f2f2;text-align: center;padding: 14px 16px;text-decoration: none;font-size: 17px;}"
					+ "#navbar a:hover {background-color: #ddd;color: black;}" 
					+ "#navbar a.active {background-color: #4CAF50;color: white;}"
					+ "#navbar a.active2 {background-color: #FF8050;color: white;}"
					+ "div.sticky {position: fixed;top: 0;width: 100%;}"
					+ ".sticky + .content {padding-top: 60px;}"
					+ ".tooltip {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
					+ ".tooltip .tooltiptext {visibility: hidden;background-color: black;color: #fff;text-align: center;border-radius: 6px;padding: 5px 0;position: absolute;z-index: 1;margin-left: -30px; width: 120px;top: 100%;left: 10%;}"
					+ ".tooltip .tooltiptext::after {content: \" \";position: absolute;bottom: 100%;left: 50%;margin-left: -5px;border-width: 5px;border-style: solid;border-color: transparent transparent black transparent;}"
					+ ".tooltip:hover .tooltiptext {visibility: visible;}"
					+ ".tooltip2 {position: relative;display: inline-block;border-bottom: 1px dotted black;}"
					+ ".tooltip2 .tooltiptext2 {visibility: hidden;background-color: black;color: #fff;text-align: center;border-radius: 6px;padding: 5px 0;position: absolute;z-index: 1;margin-top: -15px; width: 240px;left: 110%;}"
					+ ".tooltip2 .tooltiptext2::after {content: \" \";position: absolute;top: 50%;right: 100%;margin-top: -5px;border-width: 5px;border-style: solid;border-color: transparent black transparent transparent;}"
					+ ".tooltip2:hover .tooltiptext2 {visibility: visible;}"
					+ "</style>"
			
                    +"</head>\r";
		}

	
		/**
		 * Retourne l'ouverture de la balise body
		 * @return
		 */
		public static String getHTMLdebutBody(double note, String noteFrom, String NumSujet, String dossier, double proportioncorrect, String progression) {
			DecimalFormat df1 = new DecimalFormat("##.##");
			Date aujourdhui = new Date();
			DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
			
			return  "<body lang=\"fr-FR\" link=\"#000080\" vlink=\"#800000\" dir=\"ltr\">\r"
					+"<div class=\"header\">"
			        +"<h1 id=\"#top\" class=\"western\" align=\"center\" style=\"margin-left: 1cm; margin-right: 1cm; border: 2.00pt solid #ffffff; padding: 0.4cm 0.1cm; background: #505050\">\r\n" + 
					"<font color=\"#ffffff\" size=\"6\" style=\"font-size: 26pt\">Feedback - Analyse LibreOffice Writer<br/>"+HTML.imgLogos()+"</font></h1>\r"
					+"<p><spanpablo>" + df1.format(note) + " / " + noteFrom +"<br/><span style=\"color:blue; font-size:30px\">Sujet "+ NumSujet +"</span></spanpablo></p>\r"
					+"<h4>Date d'analyse :  <font color=\"#0000ff\">"+ mediumDateFormat.format(aujourdhui) + "</font><br/>"
				    +"<div class=\"tooltip2\">Dossier étudiant : <font color=\"#0000ff\"><b>"+ dossier + "</b></font><span class=\"tooltiptext2\">Dossier téléchargé depuis la plateforme Moodle.</span></div><br/>"
				    +"Analyse MEPTL : sujet "+ NumSujet + "<br/>\r"	
				    +"Méthode : <div class=\"tooltip\"><font color=\"#0000ff\">Progression " + progression + "</font><span class=\"tooltiptext\">Explication<br/>"+ HTML.imgProgression() +"</span></div> - Proportion correcte du fichier analysé : " + Math.floor(proportioncorrect*10000)/100 + "%<br/>"
				   	+"<br/><font color=\"#808080\" style=\"font-size: 9pt\"><i>Auteur : P. Rodriguez - Licence GPL v3.0 - Avril 2020</i></font></h4>"
				    +"</div>";
		}
		
		/**
		 * Retourne la fermeture de la balise body et html
		 * @return
		 */
		public static String getHTMLfinBodyHTML() {
			return 	"</body>\r</html>";
		}
		
		/**
		 * Retourne le code HTML du menu
		 * @param items
		 * @return
		 */
		public static String getHTMLmenu(ArrayList<node> items) {

			String codeHTMLMenu= "<div id=\"navbar\"  onclick=\"toggleMenu()\"><a class=\"active\" href=\"#top\">Note</a>";
			for(int i =0 ; i < items.size(); i++) {
				codeHTMLMenu = codeHTMLMenu + "<a class=\"active3\" href=\"#"+  items.get(i).getAttributs().get("id") + "\">" + items.get(i).getAttributs().get("titre") + "</a>";
			}
			codeHTMLMenu = codeHTMLMenu + "</div>";
			
			return 	codeHTMLMenu;
			
		}
		
		
		/**
		 * Du javascript pour le menu sticky
		 * @return
		 */
		public static String getHTMLJavaScript() {
			return "<script>"
					+"window.onscroll = function() {myFunction()};"
					+"var navbar = document.getElementById(\"navbar\");"
					+"var sticky = navbar.offsetTop;"
					+"function myFunction() {"
					+"if (window.pageYOffset >= sticky) {"
					+"navbar.classList.add(\"sticky\")"
					+"} else {"
					+"navbar.classList.remove(\"sticky\");"
					+"}"
					+"}"
					+"</script>";
		}
}
