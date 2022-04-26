package MEPTL;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import cXML.node;

/**
 * 
 * @author pablo rodriguez - 2000
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
			txt="<H2>"+ txt +"</H2>";
			return txt;
		}
		
		// Titre 2
		public static String H2(String txt, String id) {
			txt="<H2 id=\"" + id + "\">"+ txt +"</H2>";
			return txt;
		}
		
		// Titre 3
		public static String H3(String txt) {
			txt="<H3>"+ txt +"</H3>";
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
		 * Style HTML pour paragraphe style class p11
		 * @param txt
		 * @return
		 */
		public static String Paragraph_classCommentaire(String txt) {
			return("<p class=\"commentaire\">" + txt + "</p>");
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
		
		//Saut de page puis tritre
		public static String SautP8(String titre) {
			return "<br><p class=\"p8\">" + titre+"</p>\r";
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
			return "<table width=\"92%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
					"<col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/>"+ 
					"<tr valign=\"top\"><td width=\"13%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font color=\"#000000\" size=\"5\" style=\"font-size: 14pt\"><b>" + "Correct/Erreur" + "</b></font></p></td>\r\n" + 
					"<td width=\"29%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font size=\"5\" style=\"font-size: 14pt\"><b>" + "Propriété" +"</b></font></p></td>\r\n" + 
					"<td width=\"29%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font size=\"5\" style=\"font-size: 14pt\"><b>Valeur fichier</p></td>\r\n" + 
					"<td width=\"29%\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>\r\n" + 
					"<font size=\"5\" style=\"font-size: 14pt\"><b>Consigne" +"</p></td>\r\n" + 
					"	</tr>\r\n" + 
					"</table>";
		}
		
		// Entête des tables pour ordre structure
		public static String TableEnteteOrdreStructure() {
			return "<table width=\"92%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
				"<col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/>"+ 
				"<tr valign=\"top\"><td width=\"13%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
				"<font color=\"#000000\" size=\"5\" style=\"font-size: 14pt\"><b>" + "Correct/Erreur" + "</b></font></p></td>\r\n" + 
				"<td width=\"29%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
				"<font size=\"5\" style=\"font-size: 14pt\"><b>" + "Propriété" +"</b></font></p></td>\r\n" + 
				"<td width=\"29%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
				"<font size=\"5\" style=\"font-size: 14pt\"><b>Ordre fichier</p></td>\r\n" + 
				"<td width=\"29%\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>\r\n" + 
				"<font size=\"5\" style=\"font-size: 14pt\"><b>Ordre consigne" +"</p></td>\r\n" + 
				"	</tr>\r\n" + 
				"</table>";
			}
		
		
		// Table pour les corrects dans les styles de paragraphe, styles de page
		public static String Table(String txt1, String txt2, String txt3, String txt4,int niveau) {
			String color = "#AAAAAA"; //#
			if(niveau==2) color =" #dc143c";
			if(niveau==1) color = "#32cd32";
			
			return "<table width=\"92%\" align=\"center\" cellpadding=\"4\" cellspacing=\"0\" style=\"page-break-after: avoid\">\r\n" + 
					"	<col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/><col width=\"64*\"/>"+ 
					"	<tr valign=\"top\"><td width=\"13%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					"<font color=\"#ffffff\"><b><span style=\"background:"+ color +"\">" + txt1 + "</p></td>\r\n" + 
					"		<td width=\"29%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p class=\"p1\">\r\n" + 
					txt2 +"</p></td>\r\n" + 
					"      <td width=\"29%\" style=\"border-top: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-right: none; padding-top: 0.1cm; padding-bottom: 0.1cm; padding-left: 0.1cm; padding-right: 0cm\"><p>\r\n" + 
					txt3 +"</p></td>\r\n" + 
					"	   <td width=\"29%\" style=\"border: 1px solid #000000; padding: 0.1cm\"><p>\r\n" + 
					txt4 +"</p></td>\r\n" + 
					"	</tr>\r\n" + 
					"</table>";
		}
		
		// table d'un node
		public static String Table(node nod) {
						
			 String code = HTML.SautLigne();
			 if(nod.getAttributs().get("titre")!=null) if(!nod.getAttributs().get("titre").isEmpty()) {
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
					// String Key = outils.Traduction(nod.getNodes().get(k).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getAttributs().get("attribut")));
					 String Key = outils.traduction.get(nod.getNodes().get(k).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getAttributs().get("attribut")));
					if(Key==null) Key = nod.getNodes().get(k).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getAttributs().get("attribut"));
					
					 String valueStudent = nod.getNodes().get(k).getAttributs().get("valueStudent");
					 String valueSujet = outils.withoutCodeAndPoint(nod.getNodes().get(k).getAttributs().get("valueSujet"));
					 int niveau = Integer.valueOf(nod.getNodes().get(k).getAttributs().get("niveau"));
					 
					 code = code + HTML.Table(Tst, Key, valueStudent, valueSujet, niveau);
				 }else {
					 
					 if(nod.getNodes().get(k).getAttributs().get("titre")!=null) if(!nod.getNodes().get(k).getAttributs().get("titre").isEmpty()){
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
								// String Key = outils.Traduction(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("attribut")));
								 String Key2 = outils.traduction.get(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("attribut")));
								 if(Key2==null) Key2 = nod.getNodes().get(k).getNodes().get(l).getAttributs().get("elt") + " " + outils.withoutCodeAndPoint(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("attribut"));
								 String valueStudent = nod.getNodes().get(k).getNodes().get(l).getAttributs().get("valueStudent");
								 String valueSujet = outils.withoutCodeAndPoint(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("valueSujet"));
								 int niveau = Integer.valueOf(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("niveau"));
								 
								 code = code + HTML.Table(Tst, Key2, valueStudent, valueSujet, niveau);
							 }else {
								if(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("titre")!=null) code = code + HTML.SautLigneOnduleBleu(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("titre"));
								if(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("titre2")!=null) code = code + HTML.SautP8(nod.getNodes().get(k).getNodes().get(l).getAttributs().get("titre2"));
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

		public static String NoteA( ) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
					+ "<!-- Created with Inkscape (http://www.inkscape.org/) -->\r\n"
					+ "\r\n"
					+ "<svg\r\n"
					+ "   width=\"38.443188mm\"\r\n"
					+ "   height=\"38.443161mm\"\r\n"
					+ "   viewBox=\"0 0 38.443188 38.443161\"\r\n"
					+ "   version=\"1.1\"\r\n"
					+ "   id=\"svg5\"\r\n"
					+ "   inkscape:version=\"1.1.1 (3bf5ae0d25, 2021-09-20)\"\r\n"
					+ "   sodipodi:docname=\"NoteA.svg\"\r\n"
					+ "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n"
					+ "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n"
					+ "   xmlns=\"http://www.w3.org/2000/svg\"\r\n"
					+ "   xmlns:svg=\"http://www.w3.org/2000/svg\">\r\n"
					+ "  <sodipodi:namedview\r\n"
					+ "     id=\"namedview7\"\r\n"
					+ "     pagecolor=\"#ffffff\"\r\n"
					+ "     bordercolor=\"#666666\"\r\n"
					+ "     borderopacity=\"1.0\"\r\n"
					+ "     inkscape:pageshadow=\"2\"\r\n"
					+ "     inkscape:pageopacity=\"0.0\"\r\n"
					+ "     inkscape:pagecheckerboard=\"0\"\r\n"
					+ "     inkscape:document-units=\"mm\"\r\n"
					+ "     showgrid=\"false\"\r\n"
					+ "     showguides=\"true\"\r\n"
					+ "     inkscape:guide-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox-edge-midpoints=\"true\"\r\n"
					+ "     inkscape:bbox-paths=\"true\"\r\n"
					+ "     inkscape:snap-nodes=\"false\"\r\n"
					+ "     inkscape:snap-bbox-midpoints=\"true\"\r\n"
					+ "     inkscape:snap-global=\"false\"\r\n"
					+ "     fit-margin-top=\"0\"\r\n"
					+ "     fit-margin-left=\"0\"\r\n"
					+ "     fit-margin-right=\"0\"\r\n"
					+ "     fit-margin-bottom=\"0\"\r\n"
					+ "     inkscape:zoom=\"4.3901061\"\r\n"
					+ "     inkscape:cx=\"61.729716\"\r\n"
					+ "     inkscape:cy=\"85.533241\"\r\n"
					+ "     inkscape:window-width=\"1920\"\r\n"
					+ "     inkscape:window-height=\"1009\"\r\n"
					+ "     inkscape:window-x=\"-8\"\r\n"
					+ "     inkscape:window-y=\"-8\"\r\n"
					+ "     inkscape:window-maximized=\"1\"\r\n"
					+ "     inkscape:current-layer=\"layer1\">\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"1,0\"\r\n"
					+ "       id=\"guide12970\" />\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"0,-1\"\r\n"
					+ "       id=\"guide12972\" />\r\n"
					+ "  </sodipodi:namedview>\r\n"
					+ "  <defs\r\n"
					+ "     id=\"defs2\" />\r\n"
					+ "  <g\r\n"
					+ "     inkscape:label=\"Calque 1\"\r\n"
					+ "     inkscape:groupmode=\"layer\"\r\n"
					+ "     id=\"layer1\"\r\n"
					+ "     transform=\"translate(-19.656191,-12.181169)\">\r\n"
					+ "    <g\r\n"
					+ "       id=\"g17608\"\r\n"
					+ "       transform=\"matrix(1.1948043,0,0,1.1948043,-3.82911,-2.3729436)\"\r\n"
					+ "       style=\"stroke-width:0.836957\">\r\n"
					+ "      <path\r\n"
					+ "         id=\"path12996\"\r\n"
					+ "         style=\"font-variation-settings:normal;opacity:1;vector-effect:none;fill:#0000ff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:3.1633;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;-inkscape-stroke:none;stop-color:#000000;stop-opacity:1\"\r\n"
					+ "         inkscape:transform-center-x=\"0.041927404\"\r\n"
					+ "         inkscape:transform-center-y=\"-13.470202\"\r\n"
					+ "         d=\"m 134.9043,46.039062 -5.68946,9.892579 -0.79296,1.378906 -1.10352,-1.103516 -8.07422,-8.066406 -2.94922,11.025391 -0.42383,1.583984 -1.39453,-0.804688 -9.88476,-5.701171 0.004,11.412109 v 1.640625 l -1.55273,-0.416016 -11.025392,-2.949218 2.958984,11.021484 0.425782,1.583984 h -1.611328 l -11.41211,0.0059 5.710938,9.880859 0.820312,1.419922 -1.554687,0.416016 -11.021485,2.958984 8.072266,8.066406 1.160156,1.158204 -1.394531,0.80664 -9.880859,5.70898 9.886718,5.70313 1.419922,0.81836 -1.136718,1.13867 -8.066407,8.07422 11.023438,2.94922 1.585937,0.42383 -0.804687,1.39453 -5.703125,9.88476 11.412109,-0.004 h 1.640625 l -0.416016,1.55273 -2.949218,11.02539 11.021486,-2.95898 1.58594,-0.42578 v 1.61132 l 0.004,11.41211 9.88086,-5.71093 1.41992,-0.82032 0.41797,1.55469 2.95703,11.02149 8.06641,-8.07227 1.16016,-1.16016 0.80468,1.39453 5.70899,9.88086 5.70312,-9.88671 0.82032,-1.42188 1.13867,1.13867 8.07226,8.06641 2.94922,-11.02344 0.42383,-1.58594 1.39453,0.80469 9.88477,5.70313 -0.004,-11.41211 v -1.64063 l 1.55469,0.41602 11.02344,2.94922 -2.95704,-11.02149 -0.42578,-1.58594 h 1.60938 l 11.41211,-0.004 -5.70899,-9.88086 -0.82031,-1.41992 1.55273,-0.41797 11.02344,-2.95703 -8.07422,-8.06641 -1.16015,-1.16016 1.39453,-0.80468 9.88086,-5.70899 -9.88477,-5.70312 -1.42187,-0.82032 1.13867,-1.138667 8.06641,-8.072265 -11.0254,-2.949219 -1.58398,-0.423828 0.80469,-1.394532 5.70117,-9.884765 -11.41211,0.0039 h -1.64063 l 0.41602,-1.554688 2.94922,-11.023437 -11.02148,2.957031 -1.58399,0.425782 v -1.609376 l -0.006,-11.412109 -9.88086,5.708985 -1.41992,0.820312 -0.41602,-1.552734 -2.95898,-11.023438 -8.06641,8.074219 -1.1875,1.189453 a 49.268015,49.860416 0 0 0 -0.01,-0.002 l -0.82813,-1.427735 z\"\r\n"
					+ "         transform=\"scale(0.26458333)\" />\r\n"
					+ "      <ellipse\r\n"
					+ "         style=\"fill:#ffffff;stroke:#ff0000;stroke-width:0.847001;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "         id=\"ellipse13122\"\r\n"
					+ "         cx=\"35.702724\"\r\n"
					+ "         cy=\"28.247471\"\r\n"
					+ "         rx=\"11.494\"\r\n"
					+ "         ry=\"11.632896\" />\r\n"
					+ "      <g\r\n"
					+ "         id=\"g12968\"\r\n"
					+ "         transform=\"translate(1.0501346,-0.42227552)\"\r\n"
					+ "         style=\"stroke-width:0.836957\">\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.539315,34.822811 q 0.01714,0.09353 0.08572,0.09353 l 0.04286,-0.09353 v -0.09353 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11809\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 40.739741,34.588973 v 0.140301 h 0.04286 q 0.08572,0 0.08572,-0.09353 v -0.04677 z\"\r\n"
					+ "           id=\"path11807\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.753609,34.542203 v 0.04677 h 0.04286 q 0.08572,0 0.08572,-0.09353 v -0.04677 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11805\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 27.596661,34.484573 0.128577,0.280601 v 0.467668 l -0.214294,0.04677 h -0.128577 q -0.08572,0 -0.08572,-0.09353 0.205723,-0.60797 0.300012,-0.701503 z\"\r\n"
					+ "           id=\"path11803\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 29.682135,34.261602 q 0.04286,0 0.04286,0.04677 0.171436,-0.07482 0.171436,-0.140301 l -0.08572,-0.04677 q -0.06,0 -0.128577,0.1403 z\"\r\n"
					+ "           id=\"path11801\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 29.167828,33.840701 v 0.04677 q 0.06857,0 0.171436,-0.187067 v -0.04677 q -0.06857,0 -0.171436,0.187067 z\"\r\n"
					+ "           id=\"path11799\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.539315,33.279498 v 0.04677 q 0.128577,0 0.128577,0.09353 l -0.04286,0.233834 0.08572,0.04677 q 0,-0.280601 0.04286,-0.280601 -0.08572,-0.149654 -0.08572,-0.233834 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11797\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 31.010763,33.232728 h 0.128576 v -0.09353 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11795\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 28.782098,33.139198 v 0.04677 q 0.01714,0.09353 0.08572,0.09353 h 0.171437 q 0,-0.06547 -0.128577,-0.140299 z\"\r\n"
					+ "           id=\"path11793\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 31.310776,33.092428 v 0.09353 h 0.342871 q 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11791\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.410738,32.250626 -0.04286,0.467668 v 0.04677 l 0.08572,0.140301 v 0.04677 q -0.06857,0.149654 -0.514307,0.420902 h -0.171435 q -0.154293,0 -0.385731,0.04677 -0.137148,-0.09353 -0.257154,-0.09353 0,0.06547 0.257154,0.233834 0.04286,0 0.342872,-0.09353 l 0.214294,0.04677 q 0.420018,-0.19642 0.600026,-0.467668 -0.04286,-0.102883 -0.08572,-0.420901 0.04286,-0.187068 0.04286,-0.327368 z\"\r\n"
					+ "           id=\"path11789\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 41.339766,31.829724 0.04286,0.09353 -0.08572,0.04677 h -0.04286 v -0.04677 q 0.01715,-0.09353 0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11787\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 38.039628,32.344159 q 0,0.121594 0.685743,0.514435 h 0.08572 l 0.08572,-0.04677 v 0.09353 q 0,0.04677 -0.04286,0.04677 0.06857,0.187068 0.128577,0.187068 0.09428,0 0.428589,-0.187068 l 0.300012,0.04677 q 0.257154,-0.04677 0.471449,-0.187067 0.291441,0.140299 0.471448,0.140299 h 0.171436 q 0.04286,0 0.04286,-0.04677 0.04286,0 0.04286,0.04677 l 0.514307,-0.04677 h 0.128577 q 0.257153,0.486375 0.257153,0.88857 0.04286,0 0.04286,-0.04677 0.171436,0 0.685744,1.91744 l -0.08572,0.04677 q -0.291441,0 -0.514307,-0.04677 -0.222866,0.04677 -0.514307,0.04677 -0.06857,-0.364781 -0.171436,-0.561202 L 41.76836,34.16806 41.81122,33.840693 H 41.7255 q -0.06,0 -0.214296,0.607969 -0.342871,0.439607 -0.342871,0.607968 v 0.09353 q 0.04286,0.261894 0.04286,0.467669 l -0.300013,0.09353 h -0.557167 l -0.171435,-0.140301 q -0.154293,0.140301 -0.300013,0.140301 h -0.257153 q -0.205724,0 -0.42859,-0.04677 -0.162864,0.04677 -0.38573,0.04677 h -0.171436 q -0.04286,0 -0.04286,-0.04677 l -0.08572,0.04677 h -0.514307 q -0.120005,0 -0.642884,-2.057741 H 37.05387 q -0.08572,0 -0.08572,-0.09353 v -0.04677 q 0.342872,0 0.342872,-0.327368 l -0.04286,-0.09353 q 0.205723,-0.748269 0.471448,-0.748269 z\"\r\n"
					+ "           id=\"path11785\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.196568,31.782957 q 0.04286,0.243188 0.04286,0.280601 0,0.215128 -0.128576,0.654736 0.214294,-0.205774 0.214294,-0.514436 -0.04286,-0.336721 -0.04286,-0.374134 0.214294,-0.121594 0.214294,-0.187067 v -0.04677 q -0.231438,0 -0.300013,0.187067 z\"\r\n"
					+ "           id=\"path11783\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.753609,31.642657 h 0.128577 v -0.09353 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11781\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.196443,31.362056 -0.04286,0.187068 q 0.08572,0.654734 0.171435,0.654734 h 0.04286 v -0.04677 q -0.137149,-0.654736 -0.171436,-0.654736 l 0.04286,-0.09353 v -0.04677 z\"\r\n"
					+ "           id=\"path11779\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 38.168205,31.315286 v 0.140301 h 0.08572 v -0.1403 z\"\r\n"
					+ "           id=\"path11777\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.410863,31.268516 v 0.140301 q 0.01715,0.09353 0.08572,0.09353 v -0.1403 q 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11775\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.625032,31.128216 v 0.187067 h 0.128577 q 0.08572,0 0.08572,-0.09353 l -0.128577,-0.09353 z\"\r\n"
					+ "           id=\"path11773\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 31.996518,31.174986 q -0.265725,-0.09353 -0.342871,-0.09353 -0.128577,0.04677 -0.128577,0.09353 -0.342871,-0.09353 -0.471448,-0.187067 h -0.04286 l 0.04286,0.09353 v 0.04677 l -0.08572,0.140301 v 0.04677 l 0.04286,0.09353 0.214294,-0.140301 q 0.780033,-0.04677 0.985756,-0.04677 0,-0.08418 0.428589,-0.187068 v -0.04677 q 0,-0.09353 -0.08572,-0.09353 h -0.04286 q -0.162864,0.16836 -0.514307,0.2806 z\"\r\n"
					+ "           id=\"path11771\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.753609,30.800852 -0.04286,0.233834 q 0.06,0 0.128577,-0.1403 v -0.09353 z\"\r\n"
					+ "           id=\"path11769\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 38.896806,30.847622 0.08572,0.04677 q 0.08572,0 0.08572,-0.09353 v -0.04677 h -0.08572 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11767\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.410738,30.754092 v 0.09353 h 0.128577 q 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11765\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 29.5107,30.800862 v 0.09353 h 0.08572 q 0,-0.102883 0.04286,-0.187068 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11763\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.282287,30.941162 0.128576,0.09353 q 0,-0.280601 0.214295,-0.280601 v -0.09353 h -0.04286 q -0.257154,0.102882 -0.300012,0.2806 z\"\r\n"
					+ "           id=\"path11761\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 39.925422,30.707328 q 0.01714,0.09353 0.08572,0.09353 h 0.04286 V 30.61379 h -0.04286 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11759\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.753735,30.567028 h 0.257153 q 0.08572,0 0.08572,-0.09353 h -0.257154 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11757\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 40.011139,30.426727 v 0.09353 h 0.128577 v -0.09353 z\"\r\n"
					+ "           id=\"path11755\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.282161,30.426727 v 0.140301 h 0.04286 q 0.08572,-0.04677 0.128577,-0.04677 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11753\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 39.711126,30.379957 v 0.04677 h 0.08572 q 0.08572,0 0.08572,-0.09353 v -0.04677 h -0.08572 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11751\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 31.953659,30.05259 -0.257153,0.561201 h 0.128577 q 0,-0.09353 0.171435,-0.327368 V 30.05259 Z\"\r\n"
					+ "           id=\"path11749\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 39.15396,30.00582 -0.04286,0.09353 h 0.128576 q 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11747\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 31.867941,29.678452 v 0.04677 q 0.04286,0.09353 0.04286,0.1403 h 0.04286 V 29.67845 Z\"\r\n"
					+ "           id=\"path11745\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 39.711126,29.304317 v 0.09353 q 0.06857,0 0.514307,0.327368 v -0.04677 q 0,-0.03742 -0.428588,-0.374135 z\"\r\n"
					+ "           id=\"path11743\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 38.896806,29.257547 v 0.04677 l 0.214296,0.04677 h 0.08572 q 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11741\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.453722,28.088376 v 0.09353 h 0.04286 q 0.08572,0 0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11739\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 32.896556,28.275443 h 0.04286 q 0.128576,0 0.257153,-0.280601 h -0.04286 q -0.06,0 -0.257153,0.280601 z\"\r\n"
					+ "           id=\"path11737\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 39.496832,27.901309 -0.04286,0.09353 0.08572,0.04677 h 0.171435 q 0,-0.08418 -0.214294,-0.140301 z\"\r\n"
					+ "           id=\"path11735\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.368004,27.714241 v 0.04677 h 0.08572 V 27.62071 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11733\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.410864,27.012739 0.04286,0.233834 h 0.08572 v -0.233834 z\"\r\n"
					+ "           id=\"path11731\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 32.167955,26.919209 -0.08572,0.1403 q 0.01714,0.09353 0.08572,0.09353 h 0.04286 q 0.08572,0 0.08572,-0.09353 v -0.09353 q -0.04286,0 -0.128577,-0.04677 z\"\r\n"
					+ "           id=\"path11729\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.753736,26.825679 v 0.04677 h 0.128576 q 0.08572,0 0.08572,-0.09353 v -0.04677 h -0.128577 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11727\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 36.925297,26.638611 -0.04286,0.09353 0.08572,0.04677 0.04286,-0.09353 z\"\r\n"
					+ "           id=\"path11725\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 32.76798,26.591841 q 0.05144,0.130946 0.428589,0.327367 h 0.04286 v -0.04677 q -0.05143,0 -0.428589,-0.2806 z\"\r\n"
					+ "           id=\"path11723\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 35.682387,26.45154 v 0.04677 h 0.08572 q 0.08572,0 0.08572,-0.09353 v -0.04677 h -0.08572 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11721\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 37.439604,26.40477 v 0.04677 q 0.08572,0 0.08572,-0.09353 v -0.04677 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11719\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 32.510826,26.26447 v 0.187067 h 0.08572 v -0.09353 q 0,-0.09353 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11717\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 36.025259,26.545071 v 0.04677 h 0.04286 q 1.457204,-0.280602 1.457204,-0.467669 h -0.04286 q -0.780032,0.205774 -1.457203,0.420901 z\"\r\n"
					+ "           id=\"path11715\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 39.411114,26.030636 q 0.102861,0 0.471448,1.403005 l -0.04286,0.09353 q 0.257154,0.355428 0.685743,1.77714 0.128577,0.383487 0.128577,0.420901 -0.08572,0.09353 -0.342871,0.09353 0,0.09353 0.428589,0.1403 0.342872,0.991457 0.557166,1.730372 -0.308584,0.327368 -0.514307,0.327368 -0.188579,0 -0.81432,-0.187067 0,0.06548 -0.257154,0.187067 l -0.514307,-0.04677 q -0.06857,0.187067 -0.300013,0.187067 h -0.04286 q -0.488592,-0.458315 -0.557166,-0.467669 -0.09428,0 -0.171436,0.04677 -0.171436,-0.09353 -0.257154,-0.09353 v -0.04677 l 0.171436,-0.187067 -0.08572,-0.140301 q 0.128577,-0.935336 0.857179,-1.91744 l -0.04286,-0.327368 v -0.327368 q -0.171435,0 -0.171435,-0.467668 0.04286,-0.177713 0.257154,-0.467668 v -0.140301 q -0.642884,-0.327367 -0.642884,-0.514435 -0.04286,-0.177714 -0.04286,-0.420901 0.06857,0 0.342871,-0.467669 0.505736,0 0.900037,-0.187067 z\"\r\n"
					+ "           id=\"path11713\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 36.025259,26.077406 h 0.128576 q 0.08572,0 0.08572,-0.09353 v -0.04677 h -0.08572 q -0.06001,0 -0.128576,0.140301 z\"\r\n"
					+ "           id=\"path11711\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 31.696506,25.843571 q 0,0.07482 0.300012,0.187067 h 0.128577 v -0.04677 z\"\r\n"
					+ "           id=\"path11709\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 30.839327,25.843571 v 0.04677 l 0.214295,0.04677 h 0.04286 v -0.09353 h -0.08572 q -0.04286,0 -0.04286,0.04677 l -0.08572,-0.04677 z\"\r\n"
					+ "           id=\"path11707\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 32.939415,25.282369 q 0.04286,0.09353 0.04286,0.140301 h 0.128577 v -0.140301 z\"\r\n"
					+ "           id=\"path11705\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 36.410989,23.972898 v 0.04677 l 0.214294,-0.04677 v -0.09353 h -0.128577 q -0.08572,0.01871 -0.08572,0.09353 z\"\r\n"
					+ "           id=\"path11703\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 35.982399,23.551996 0.171436,0.04677 q 0.231438,-0.04677 0.385731,-0.04677 0.07715,0.261895 0.557165,0.420902 0,0.25254 0.300013,1.215938 l -0.04286,0.327368 q 0.08572,0 0.257153,0.607968 l 0.300013,0.374135 -0.08572,0.374134 0.04286,0.327368 q -0.04286,0.252541 -0.04286,0.374135 l 0.642884,0.327368 q -0.300013,0.346074 -0.300013,0.561201 0,0.467669 -0.257154,0.467669 0.01714,0.09353 0.08572,0.09353 h 0.04286 l 0.128577,-0.09353 h 0.04286 q 0.120005,0 0.171435,0.467668 0,0.1403 -0.600025,1.169171 -0.111432,0.514435 -0.214294,0.514435 h -0.08572 q -0.728602,-0.327368 -0.857179,-0.327368 -0.09428,0 -0.171436,0.04677 -0.222866,-0.09353 -0.342871,-0.09353 l -0.81432,0.2806 -0.171436,0.04677 q -0.497163,-0.19642 -1.071473,-0.935337 0.01714,-0.09353 0.08572,-0.09353 h 2.100088 q 0,-0.177714 -0.557167,-2.198042 -0.128576,-0.430254 -0.128576,-0.514434 0.04286,0 0.04286,-0.04677 -0.145721,-0.140301 -0.728602,-2.525409 l -0.08572,-0.04677 q -0.06,0 -0.600025,1.964206 h 0.04286 q -0.497163,2.048387 -0.685742,2.338342 0.04286,0.09353 0.04286,0.1403 -0.06,0.327368 -0.171436,0.327368 h -0.171436 q -0.180007,0 -0.342871,0.04677 -0.557166,-0.02806 -0.557166,-0.374135 h -0.04286 l -0.128577,0.09353 q 0,0.177714 0.342872,0.467669 h 0.342871 l 0.514307,-0.04677 q 0.04286,0.09353 0.04286,0.140301 l -0.04286,0.233834 h 0.171436 q 0.231439,0 0.300012,0.187067 v 0.09353 l -0.171435,0.04677 v 0.1403 h 0.04286 l 0.171436,-0.1403 h 0.04286 q 0,0.30866 1.028614,0.88857 0,0.04677 0.08572,0.04677 l 0.900038,-0.280601 h 0.128577 q 0.154293,0 0.38573,0.04677 0.171436,-0.09353 0.257154,-0.09353 0.522879,0.233834 0.857179,0.233834 v 0.233835 q 0,0.04677 0.04286,0.04677 0,0.130947 -0.728602,0.420901 -0.08572,0 -0.42859,-0.420901 v 0.09353 q 0.145721,0.374134 0.385731,0.374134 0,0.879217 -0.342872,1.02887 -0.137148,0 -0.342871,0.327368 h -1.328627 l -0.128577,-0.09353 q -0.137148,0.09353 -0.257153,0.09353 h -1.371487 l -0.128576,-0.09353 -0.214295,0.04677 -0.04286,-0.09353 q -0.171435,0 -0.685742,0.2806 v -0.04677 q -0.342872,0.04677 -0.342872,0.1403 v 0.09353 q 0.171436,0.168361 0.171436,0.233835 h 0.04286 v -0.280601 q 0.360014,-0.09353 0.514307,-0.09353 -0.154293,0.88857 -0.300013,0.88857 v 0.233834 q -0.111433,0.748269 -0.257153,0.748269 h -0.985751 q -0.300012,0 -0.300012,-0.09353 -0.137149,0.09353 -0.257154,0.09353 h -1.62864 q -0.154292,0 -0.38573,-0.04677 -0.06857,0.04677 -0.214295,0.04677 -0.08572,-0.04677 -0.128576,-0.04677 v -0.140301 l 0.04286,-0.327368 q -0.257154,-0.233833 -0.257154,-0.607967 0.197152,-0.88857 0.300013,-0.88857 0.197151,0 0.385731,0.1403 h 0.08572 v -0.09353 q -0.265725,-0.09353 -0.428589,-0.09353 v -0.09353 q 0,-0.271249 0.771461,-2.385109 l 0.214295,0.04677 h 0.08572 q 0.06,0 0.171436,-0.233835 0,-0.09353 -0.08572,-0.09353 h -0.08572 q 0,0.187068 -0.08572,0.187068 h -0.08572 l 0.08572,-0.140301 v -0.04677 q -0.128577,0 -0.128577,-0.09353 0.900037,-2.628296 1.20005,-3.647812 h 0.04286 q 0.600025,0.336721 0.942896,1.403005 -0.08572,0.25254 -0.08572,0.514434 l 0.04286,0.420902 q -0.514307,0.776329 -0.771461,1.075637 0,0.06548 -0.514308,0.187068 -0.342871,0.177714 -0.342871,0.233833 v 0.140301 q 0.01714,0.09353 0.08572,0.09353 l 0.08572,-0.327368 h 0.171436 q 0.04286,0 0.342871,-0.09353 -0.120005,0.280602 -0.300013,0.280602 v 0.09353 h 0.08572 q 0.38573,-0.168361 0.38573,-0.514436 0.325728,-0.233833 0.428589,-0.233833 0.08572,0.243187 0.08572,0.374134 -0.08572,0.01871 -0.08572,0.09353 v 0.09353 h 0.04286 q 0.102862,0 0.128577,-0.420901 -0.08572,0 -0.08572,-0.09353 v -0.04677 q 0,-0.364782 0.557166,-0.935337 0.111433,0.187068 0.642884,0.233834 0.08572,-0.04677 0.128577,-0.04677 v -0.140301 q -0.04286,0 -0.128577,-0.04677 -0.231438,0.04677 -0.342871,0.04677 -0.42859,0 -0.42859,-0.607968 0.08572,-0.308662 0.08572,-0.420903 0,-0.439607 -1.071474,-1.636838 v -0.04677 q 0.754318,-2.441229 0.942897,-2.759243 h 0.04286 q 0.214295,0 0.214295,0.09353 0.02571,0 0.171435,-0.04677 0.720031,0.04677 1.114333,0.04677 0.762889,0.607968 0.942897,0.607968 0,-0.08418 0.428589,-0.233834 0.282869,0.09353 0.38573,0.09353 0.300013,-0.159007 0.300013,-0.233833 l 0.214294,0.04677 q 0.891467,-0.514435 0.985756,-0.514435 z\"\r\n"
					+ "           id=\"path11701\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 36.668143,21.213655 h 0.128576 q 0.06,0 0.128577,-0.140301 v -0.09353 h -0.04286 q -0.06,0 -0.214294,0.233834 z\"\r\n"
					+ "           id=\"path11699\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 32.682261,20.231552 q 0,0.140299 -0.171436,0.327368 l 0.08572,0.04677 0.171436,-0.327368 z\"\r\n"
					+ "           id=\"path11697\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 35.382374,20.231552 v 0.09353 h 0.257153 v -0.140301 h -0.04286 z\"\r\n"
					+ "           id=\"path11695\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.067991,20.091251 v 0.04677 h 0.08572 q 0.171435,-0.140301 0.514307,-0.187068 v -0.04677 h -0.171436 q -0.07715,0 -0.428589,0.187067 z\"\r\n"
					+ "           id=\"path11693\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke-width:0.383701\"\r\n"
					+ "           d=\"m 33.710875,19.857417 v 0.04677 l 0.214296,0.04677 h 0.08572 v -0.09353 z\"\r\n"
					+ "           id=\"path11691\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 36.196694,19.717117 q 0.171436,0.06548 0.171436,0.1403 v 0.1403 q 0,0.06547 -0.128577,0.140301 h -0.08572 q 0,-0.140301 -0.128577,-0.233834 0.03429,-0.140301 0.171435,-0.187067 z\"\r\n"
					+ "           id=\"path11689\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 36.110974,19.483282 h 0.04286 q 0.111433,0 0.342871,0.04677 0.171437,-0.04677 0.300013,-0.04677 0.05144,0.233835 0.128577,0.233835 0.04286,-0.08418 0.04286,-0.187068 h 0.257154 q 0.171436,0 0.214295,0.561202 -0.04286,0 -0.04286,0.04677 l -0.08572,-0.04677 -0.257153,0.04677 -0.214295,-0.187068 h -0.08572 q 0,0.252542 0.342871,0.327368 0.231439,-0.04677 0.385731,-0.04677 0.38573,0.86051 0.38573,1.075637 -0.06,0 -0.214294,0.233834 v 0.374134 q 0,0.271248 -0.300013,0.795037 h 0.08572 q 0.300012,-0.551849 0.300012,-1.122405 0.06,-0.09353 0.214295,-0.09353 1.157192,3.404625 1.328627,4.162247 v 0.04677 q -0.471448,0.2806 -1.114332,0.2806 -0.514307,-0.813742 -0.514307,-1.590072 -0.171436,-0.318015 -0.214295,-0.607968 0.04286,-0.08418 0.04286,-0.187068 0,-0.11224 -0.471448,-0.327367 0,-0.06548 -0.257154,-0.280602 -0.197151,0 -0.38573,0.140301 h -0.04286 q -0.342871,0 -0.342871,-0.935337 -0.04286,-0.07482 -0.04286,-0.280601 0.05143,0 0.342871,-0.374134 v -0.09353 q 0,-0.05613 -0.257153,-0.607969 v -0.04677 q 0.180008,-0.505082 0.514307,-0.795037 0.07715,0.04677 0.171436,0.04677 v -0.1403 q -0.300013,0 -0.300013,-0.280602 h -0.214294 q -0.08572,0 -0.08572,-0.09353 z\"\r\n"
					+ "           id=\"path11687\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-size:18.3379px;line-height:1.25;font-family:Crackvetica;-inkscape-font-specification:Crackvetica;fill:#1ae416;stroke:#000000;stroke-width:0.0485435;stroke-miterlimit:4;stroke-dasharray:none;stroke-opacity:1\"\r\n"
					+ "           d=\"m 32.596541,19.483282 h 1.457204 l 0.300012,0.09353 q 0.28287,-0.09353 0.385731,-0.09353 h 0.257153 q 0.04286,0 0.04286,0.04677 l 0.08572,-0.04677 h 0.04286 l 0.171436,0.233835 0.300012,-0.09353 q 0.317157,0.177714 0.342872,0.280602 v 0.467667 q 0,0.04677 0.04286,0.04677 0,0.04677 -0.214296,0.327368 v 0.187067 q 0,0.11224 0.214296,0.420901 0,0.261894 -0.342873,0.467669 v 0.1403 q 0,0.308661 0.171437,1.169171 -0.171437,0 -0.642884,0.420901 -0.102862,0 -0.300013,0.09353 l -0.08572,-0.04677 q -0.300013,0.187068 -0.428589,0.187068 l -0.214295,-0.04677 q 0,0.06547 -0.471449,0.187068 -0.05143,0 -0.857178,-0.514435 -0.07715,-0.04677 -0.171436,-0.04677 h -0.600025 q -0.102861,0 -0.38573,-0.09353 -0.171436,0.03742 -0.171436,0.09353 -0.197151,-0.09353 -0.214294,-0.187068 0.728601,-2.394461 0.900037,-2.572175 l -0.04286,-0.09353 q 0.188579,-0.617321 0.428589,-1.02887 z\"\r\n"
					+ "           id=\"path11248\" />\r\n"
					+ "      </g>\r\n"
					+ "    </g>\r\n"
					+ "  </g>\r\n"
					+ "</svg>\r\n";
		}

		public static String NoteB() {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
					+ "<!-- Created with Inkscape (http://www.inkscape.org/) -->\r\n"
					+ "\r\n"
					+ "<svg\r\n"
					+ "   width=\"38.443188mm\"\r\n"
					+ "   height=\"38.443161mm\"\r\n"
					+ "   viewBox=\"0 0 38.443188 38.443161\"\r\n"
					+ "   version=\"1.1\"\r\n"
					+ "   id=\"svg5\"\r\n"
					+ "   inkscape:version=\"1.1.1 (3bf5ae0d25, 2021-09-20)\"\r\n"
					+ "   sodipodi:docname=\"NoteB.svg\"\r\n"
					+ "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n"
					+ "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n"
					+ "   xmlns=\"http://www.w3.org/2000/svg\"\r\n"
					+ "   xmlns:svg=\"http://www.w3.org/2000/svg\">\r\n"
					+ "  <sodipodi:namedview\r\n"
					+ "     id=\"namedview7\"\r\n"
					+ "     pagecolor=\"#ffffff\"\r\n"
					+ "     bordercolor=\"#666666\"\r\n"
					+ "     borderopacity=\"1.0\"\r\n"
					+ "     inkscape:pageshadow=\"2\"\r\n"
					+ "     inkscape:pageopacity=\"0.0\"\r\n"
					+ "     inkscape:pagecheckerboard=\"0\"\r\n"
					+ "     inkscape:document-units=\"mm\"\r\n"
					+ "     showgrid=\"false\"\r\n"
					+ "     showguides=\"true\"\r\n"
					+ "     inkscape:guide-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox-edge-midpoints=\"true\"\r\n"
					+ "     inkscape:bbox-paths=\"true\"\r\n"
					+ "     inkscape:snap-nodes=\"false\"\r\n"
					+ "     inkscape:snap-bbox-midpoints=\"true\"\r\n"
					+ "     inkscape:snap-global=\"false\"\r\n"
					+ "     fit-margin-top=\"0\"\r\n"
					+ "     fit-margin-left=\"0\"\r\n"
					+ "     fit-margin-right=\"0\"\r\n"
					+ "     fit-margin-bottom=\"0\"\r\n"
					+ "     inkscape:zoom=\"4.3901061\"\r\n"
					+ "     inkscape:cx=\"63.096425\"\r\n"
					+ "     inkscape:cy=\"85.533241\"\r\n"
					+ "     inkscape:window-width=\"1920\"\r\n"
					+ "     inkscape:window-height=\"1009\"\r\n"
					+ "     inkscape:window-x=\"-8\"\r\n"
					+ "     inkscape:window-y=\"-8\"\r\n"
					+ "     inkscape:window-maximized=\"1\"\r\n"
					+ "     inkscape:current-layer=\"layer1\">\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"1,0\"\r\n"
					+ "       id=\"guide12970\" />\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"0,-1\"\r\n"
					+ "       id=\"guide12972\" />\r\n"
					+ "  </sodipodi:namedview>\r\n"
					+ "  <defs\r\n"
					+ "     id=\"defs2\" />\r\n"
					+ "  <g\r\n"
					+ "     inkscape:label=\"Calque 1\"\r\n"
					+ "     inkscape:groupmode=\"layer\"\r\n"
					+ "     id=\"layer1\"\r\n"
					+ "     transform=\"translate(-19.656191,-12.181169)\">\r\n"
					+ "    <path\r\n"
					+ "       id=\"path12996\"\r\n"
					+ "       style=\"font-variation-settings:normal;vector-effect:none;fill:#0000ff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.999999;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;-inkscape-stroke:none;stop-color:#000000\"\r\n"
					+ "       inkscape:transform-center-x=\"0.041927404\"\r\n"
					+ "       inkscape:transform-center-y=\"-13.470202\"\r\n"
					+ "       d=\"m 38.817552,12.181169 -1.798582,3.127294 -0.250675,0.435907 -0.34885,-0.348849 -2.552465,-2.549995 -0.932324,3.485405 -0.133983,0.500737 -0.440846,-0.254382 -3.124823,-1.802284 0.0013,3.607656 v 0.518643 l -0.490858,-0.131513 -3.485405,-0.932322 0.93541,3.48417 0.1346,0.500737 H 25.82067 l -3.607657,0.0019 1.805372,3.12359 0.259321,0.448873 -0.491476,0.131513 -3.48417,0.93541 2.551848,2.549995 0.366754,0.366137 -0.440846,0.255 -3.12359,1.804753 3.125442,1.802903 0.448873,0.258705 -0.359345,0.359962 -2.549995,2.552465 3.484787,0.932323 0.501355,0.133984 -0.254382,0.440846 -1.802902,3.124823 3.607656,-0.0013 h 0.518644 l -0.131514,0.490857 -0.932322,3.485405 3.48417,-0.935408 0.501356,-0.1346 v 0.509379 l 0.0013,3.607657 3.12359,-1.80537 0.448872,-0.259324 0.132131,0.491477 0.934792,3.484172 2.549996,-2.551849 0.366756,-0.366756 0.25438,0.440846 1.804756,3.12359 1.802901,-3.125439 0.259324,-0.449492 0.359962,0.359962 2.551846,2.549996 0.932323,-3.484788 0.133983,-0.501356 0.440846,0.254383 3.124826,1.802904 -0.0013,-3.607657 V 43.9042 l 0.491477,0.131514 3.484788,0.932323 -0.934795,-3.484172 -0.1346,-0.501355 h 0.508766 l 3.607656,-0.0013 -1.804756,-3.12359 -0.259321,-0.448872 0.490858,-0.132131 3.484788,-0.934792 -2.552465,-2.549997 -0.366753,-0.366755 0.440846,-0.25438 3.12359,-1.804756 -3.124826,-1.802901 -0.449489,-0.259324 0.359962,-0.359961 2.549997,-2.551847 -3.485408,-0.932323 -0.500736,-0.133983 0.254382,-0.440847 1.802285,-3.124824 -3.607657,0.0012 h -0.518645 l 0.131515,-0.491476 0.932323,-3.484788 -3.484169,0.934793 -0.500739,0.1346 V 18.34556 l -0.0019,-3.607657 -3.12359,1.804755 -0.448873,0.259321 -0.131514,-0.490858 -0.935408,-3.484788 -2.549997,2.552465 -0.375398,0.376016 a 15.574866,15.762139 0 0 0 -0.0032,-6.32e-4 l -0.261793,-0.451343 z\" />\r\n"
					+ "    <ellipse\r\n"
					+ "       style=\"fill:#ffffff;stroke:#ff0000;stroke-width:1.012;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "       id=\"ellipse13122\"\r\n"
					+ "       cx=\"38.828659\"\r\n"
					+ "       cy=\"31.377256\"\r\n"
					+ "       rx=\"13.733081\"\r\n"
					+ "       ry=\"13.899035\" />\r\n"
					+ "    <g\r\n"
					+ "       aria-label=\"B\"\r\n"
					+ "       transform=\"scale(0.94145664,1.0621838)\"\r\n"
					+ "       id=\"text18235\"\r\n"
					+ "       style=\"font-size:21.2861px;line-height:1.25;fill:#008000;stroke:#ff0000;stroke-width:0.1\">\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 37.058633,37.855426 h 0.311808 v 0.05197 q -0.08315,0.259841 -0.311808,0.259841 L 37.006663,38.0633 q 0.05197,-0.09354 0.05197,-0.207872 z\"\r\n"
					+ "         id=\"path22111\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.720072,37.699522 v 0.155904 h 0.207872 V 37.69952 Z\"\r\n"
					+ "         id=\"path22109\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 41.995595,37.647552 v 0.05197 q 0.02079,0.103936 0.103936,0.103936 0.103936,0 0.103936,-0.103936 v -0.05197 z\"\r\n"
					+ "         id=\"path22107\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 40.54049,37.543616 v 0.155904 h 0.05197 q 0.103936,0 0.103936,-0.103936 l -0.103936,-0.05197 z\"\r\n"
					+ "         id=\"path22105\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.373516,37.283776 v 0.05197 h 0.311808 v -0.05197 z\"\r\n"
					+ "         id=\"path22103\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 39.397194,36.92 q 0.415744,0.384563 0.415744,1.091328 h 0.103936 v 0.155905 h -2.338561 q -0.103936,0 -0.103936,-0.103937 v -0.05197 q 0,-0.114329 0.25984,-0.155904 l 0.467712,0.05197 q 0.779521,-0.322201 0.779521,-0.831488 0.176691,0 0.415744,-0.155904 z\"\r\n"
					+ "         id=\"path22101\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 46.30894,36.971976 v 0.05197 h 0.103936 L 46.464844,36.92 q -0.103936,0.05197 -0.155904,0.05197 z\"\r\n"
					+ "         id=\"path22099\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.841228,36.86804 q 0.05197,0.08315 0.05197,0.25984 H 46.0491 l 0.05197,-0.25984 -0.103936,-0.05197 h -0.05197 z\"\r\n"
					+ "         id=\"path22097\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 40.124746,36.712136 v 0.155904 h 0.103936 v -0.155904 z\"\r\n"
					+ "         id=\"path22095\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 39.137354,36.660166 h 0.155904 l 0.05197,0.25984 q -0.311808,-0.05197 -0.311808,-0.155904 0.02079,-0.103936 0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22093\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 44.542028,36.088518 v 0.103936 h 0.155904 q 0,-0.103936 -0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22091\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 43.190859,36.244422 h 0.103936 q 0.103936,0 0.103936,-0.103936 v -0.05197 h -0.05197 q -0.07276,0 -0.155904,0.155904 z\"\r\n"
					+ "         id=\"path22089\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.113676,36.03655 v 0.103936 q 0.25984,-0.135117 0.25984,-0.207872 V 35.88064 H 45.26958 q -0.07276,0 -0.155904,0.155904 z\"\r\n"
					+ "         id=\"path22087\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 42.307403,35.77671 v 0.103936 h 0.311808 v -0.05197 z\"\r\n"
					+ "         id=\"path22085\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 46.56878,35.672774 q 0.05197,0.103936 0.05197,0.155904 h 0.05197 q 0.07276,0 0.155904,-0.155904 V 35.6208 H 46.67272 Z\"\r\n"
					+ "         id=\"path22083\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.737292,35.672774 v 0.103936 h 0.05197 q 0.103936,-0.05197 0.155904,-0.05197 V 35.568836 H 45.84123 q -0.103936,0.02079 -0.103936,0.103936 z\"\r\n"
					+ "         id=\"path22081\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.356296,35.51687 q 0.571648,0.155904 0.883456,0.155904 0.291021,-0.05197 0.415744,-0.05197 0.623617,0.810701 0.623617,0.935424 0,0.25984 -0.103936,0.25984 0,0.135117 0.623616,0.831488 0.05197,0.09354 0.05197,0.207872 V 38.0633 q 0,0.103937 -0.103936,0.103937 h -2.650369 q -0.07276,0 -0.155904,-0.155905 v -0.77952 q 0,-0.270234 0.05197,-0.467712 -0.05197,-0.270234 -0.05197,-0.51968 v -0.51968 q 0,-0.155904 0.415744,-0.207872 z\"\r\n"
					+ "         id=\"path22079\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 35.863368,35.4649 h 0.155904 q 0.207873,0.08315 0.207873,0.363776 h -0.05197 Q 35.75943,35.651984 35.75943,35.568836 35.78022,35.4649 35.863366,35.4649 Z\"\r\n"
					+ "         id=\"path22077\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 42.047563,35.4649 v 0.1559 h 0.05197 q 0.207872,-0.08315 0.207872,-0.155904 l -0.103936,-0.05197 q -0.103936,0.05197 -0.155904,0.05197 z\"\r\n"
					+ "         id=\"path22075\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.348301,35.257027 v 0.05197 h 0.05197 q 0.166297,-0.103936 0.25984,-0.103936 0.103936,0.05197 0.155904,0.05197 v -0.103936 q 0,-0.103936 -0.103936,-0.103936 -0.280628,0 -0.363776,0.207872 z\"\r\n"
					+ "         id=\"path22073\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.036492,34.997187 v 0.155904 q 0.103936,0 0.103936,-0.103936 v -0.05197 z\"\r\n"
					+ "         id=\"path22071\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 48.283725,34.945217 v 0.155904 h 0.103936 v -0.05197 q 0,-0.103936 -0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22069\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.044488,33.749953 q 0.09354,0.09354 0.155904,0.623616 -0.05197,0.270234 -0.05197,0.51968 0.05197,0.22866 0.05197,0.363776 l -0.207872,0.05197 -0.05197,-0.103936 v -1.351168 q 0.02079,-0.103936 0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22067\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 48.439629,32.658625 q 0.07276,0.415744 0.155904,0.415744 -0.311808,0.77952 -0.311808,0.935424 -0.155904,0 -0.155904,0.103936 v 0.05197 l 0.103936,0.155904 v 0.05197 h -0.415744 q -0.11433,0 -0.207872,0.05197 -0.831489,-0.155904 -0.831489,-0.415744 0,-0.748339 0.883457,-0.987392 0.623616,-0.363776 0.77952,-0.363776 z\"\r\n"
					+ "         id=\"path22065\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 49.271117,32.554689 h 0.363776 q 0.103936,0 0.103936,-0.103937 -0.05197,0 -0.05197,-0.05197 -0.415744,0.05197 -0.415744,0.155905 z\"\r\n"
					+ "         id=\"path22063\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 46.932556,31.775168 0.103936,0.05197 h 0.103936 q 0.103937,0 0.103937,-0.103936 v -0.103936 q -0.311809,0.03118 -0.311809,0.155904 z\"\r\n"
					+ "         id=\"path22061\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 49.738829,31.879104 q 0.467713,0.135117 0.467713,1.247233 h -0.05197 l 0.05197,0.103936 v 0.363776 q 0,2.691943 -2.494465,4.105473 -0.717159,0.311808 -0.935425,0.311808 v -0.103936 h -0.103936 l -1.351168,0.519681 -0.103936,-0.05197 q -0.187085,0.103936 -0.415744,0.103936 L 44.7499,38.375105 V 37.95936 q 0,-0.05197 0.467712,-0.311808 l 0.103936,0.05197 0.05197,-0.103936 h -0.155904 q -0.09354,0 -0.467712,0.155904 l -0.05197,-0.103936 v -0.05197 l 0.05197,-0.363776 q -0.22866,0 -0.363777,-0.25984 l -0.623616,0.311808 q -0.05197,0 -0.05197,0.05197 v 0.103936 q 0.311808,0 0.311808,0.207872 0.291021,-0.07276 0.415744,-0.155904 h 0.05197 v 0.311808 q 0,0.05197 0.05197,0.05197 l -0.05197,0.103936 v 0.519681 q -0.436532,0.103936 -1.143297,0.103936 h -0.103936 q -0.270234,0 -0.467712,-0.05197 -0.270234,0.05197 -0.51968,0.05197 H 41.06017 q -0.654797,0 -0.77952,-1.039361 0,-0.09354 -0.727552,-0.675584 0.987392,0 0.987392,-0.77952 0,-0.124723 0.363776,-0.987393 v -0.415744 q 0,-0.07276 0.155904,-0.155904 h 0.415744 q 0.987393,0.800308 0.987393,0.987393 h 0.311808 q 0.228659,0 0.415744,0.207872 1.049754,-0.09354 1.247232,-0.25984 0.103937,0.05197 0.155905,0.05197 v 0.05197 q 0,0.155904 -0.259841,0.155904 v 0.415744 q 0,0.05197 0.05197,0.05197 -0.155904,0.342989 -0.155904,0.467712 v 0.103936 h 0.155904 q 0,-0.311808 0.259841,-0.311808 -0.103936,-0.124723 -0.103936,-0.207872 0.301414,-0.675584 0.987392,-0.675584 0.935424,-0.623617 1.247232,-0.623617 0.25984,-0.155904 0.415744,-0.155904 0.280628,0 0.415745,0.25984 0.155904,-0.05197 0.155904,-0.103936 0.05197,0 0.155904,0.05197 l 0.51968,-0.05197 q 0.09354,0.363776 0.207872,0.363776 v -0.155904 l -0.103936,-0.155904 0.103936,-0.05197 h 0.05197 l 0.103936,0.05197 v -0.05197 q -0.103936,-0.187085 -0.103936,-0.415744 0.124723,0 0.25984,-0.77952 l 0.25984,-0.25984 v -0.05197 q -0.311808,0 -0.311808,-0.415744 0.415744,0 0.571648,-1.091329 z\"\r\n"
					+ "         id=\"path22059\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 35.655496,30.94368 0.103936,0.05197 h 0.103936 q 0.103936,0 0.103936,-0.103936 0,-0.103936 -0.103936,-0.103936 -0.207872,0.07276 -0.207872,0.155904 z\"\r\n"
					+ "         id=\"path22057\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 37.526345,30.268096 -0.623616,-0.05197 h -0.05197 q -0.166298,0.14551 -0.623616,0.207872 v 0.103936 l 0.311808,0.05197 q 0.426137,-0.207872 0.467712,-0.207872 0.332595,0 0.883456,0.51968 0.249447,0 0.51968,0.103936 v -0.05197 q 0,-0.124723 -0.675584,-0.311808 -0.155904,-0.239053 -0.155904,-0.311808 0.103936,-0.166298 0.103936,-0.311808 v -0.05197 h -0.05197 q -0.103936,0 -0.103936,0.311808 z\"\r\n"
					+ "         id=\"path22055\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.400269,29.124799 h 0.05197 q 1.330381,0.467712 1.974784,1.766913 l 0.155904,0.311808 q -0.05197,0 -0.05197,0.05197 L 49.427021,31.20352 Q 48.99049,31.307456 48.491597,31.359424 48.169395,31.20352 48.023885,31.20352 v -0.05197 q 0,-0.176691 0.155904,-0.363776 v -0.207872 q -0.4885,-0.727552 -0.77952,-1.403137 z\"\r\n"
					+ "         id=\"path22053\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 41.891659,27.721663 h 0.05197 v 0.103936 h -0.155904 q 0.02079,-0.103936 0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22051\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 46.516812,27.305919 q 0.280628,0 0.311808,0.987392 0.25984,0.218266 0.311808,0.467712 -0.05197,0.09354 -0.05197,0.207872 v 0.103936 q 0.509287,1.039361 0.831489,1.455105 h 0.05197 v 0.103936 l -0.155904,0.51968 v 0.05197 q 0.654797,0.322202 0.831488,0.675584 l 0.103936,0.05197 V 31.82714 l -0.103936,-0.363776 0.103936,-0.05197 q 0.311808,0.03118 0.311808,0.155904 v 0.103936 q -0.176691,0.446925 -1.195264,0.935425 -0.561255,0.415744 -1.091329,0.623616 l -0.05197,0.103936 v 0.415744 q 0,0.436531 -1.03936,0.727552 -0.37417,0.394957 -1.195264,0.571648 -0.03118,-0.155904 -0.415745,-0.155904 h -0.25984 q -0.25984,0.01039 -0.25984,0.103936 -0.582042,-0.08315 -0.77952,-0.207872 -0.09354,0 -0.311808,0.103936 -0.987393,-0.571648 -0.987393,-0.675584 v -0.05197 l 0.103936,-0.05197 h 0.519681 l 0.51968,0.05197 q 0,-0.103936 0.935424,-0.207872 0.987393,-0.415744 0.987393,-1.143296 V 32.24288 q 0,-0.976998 -1.507073,-1.247232 -1.143296,0 -1.143296,-0.571648 v -0.05197 q 0.06236,-0.11433 1.195264,-0.467712 1.205658,-1.049754 1.351168,-1.507073 0.135117,0 1.091329,-0.935424 0.155904,-0.103936 0.987392,-0.155904 z\"\r\n"
					+ "         id=\"path22049\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.712077,27.305919 v 0.103936 q 0.384563,0.103936 0.51968,0.103936 v -0.103936 h -0.103936 q -0.228659,0 -0.415744,-0.103936 z\"\r\n"
					+ "         id=\"path22047\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 35.655496,27.098047 v 0.05197 l 0.05197,0.25984 q -0.311808,0.592435 -0.311808,0.675584 0.280628,0.571648 0.363776,0.571648 h 0.05197 q -0.25984,-0.509286 -0.25984,-0.571648 0,-0.166298 0.363776,-0.831488 z\"\r\n"
					+ "         id=\"path22045\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.044488,26.73427 q 0.270233,0.457319 0.311808,0.467713 -0.25984,0.696371 -0.25984,0.883456 l 0.155904,0.207872 v 0.155904 q 0,0.05197 -0.05197,0.05197 0.155904,0.25984 0.155904,0.415744 v 0.155904 q 0,0.467712 -0.363776,0.467712 l -0.05197,-0.103936 v -0.51968 q 0,-0.14551 0.103936,-0.311808 -0.103936,-0.488499 -0.103936,-0.77952 v -0.987393 q 0.02079,-0.103936 0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22043\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.824008,26.6823 q 0,0.09354 -0.155904,0.259841 h 0.05197 q 0.08315,0 0.207872,-0.207873 V 26.6823 Z\"\r\n"
					+ "         id=\"path22041\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.945164,26.42246 v 0.05197 q 0.332595,0 0.571648,0.571649 h 0.05197 V 26.890172 Q 46.236185,26.42246 46.101068,26.42246 Z\"\r\n"
					+ "         id=\"path22039\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.296333,25.850812 -0.05197,0.103936 0.103936,0.05197 h 0.05197 v -0.05197 q 0,-0.103936 -0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22037\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.824008,25.175228 q 0.02079,0.103936 0.103936,0.103936 h 0.05197 v -0.103936 z\"\r\n"
					+ "         id=\"path22035\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 34.564168,25.175228 v 0.103936 h 0.103936 q 0.103936,0 0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22033\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.633356,25.487036 0.05197,0.103936 -0.05197,0.311808 q 0.103936,0.363776 0.103936,0.415744 h 0.103936 l -0.103936,-0.727552 q 0.436531,-0.25984 0.623616,-0.25984 0,0.07276 0.155904,0.155904 0.363776,-0.103936 0.571648,-0.103936 0.09354,0 0.259841,0.103936 l 0.05197,-0.103936 v -0.103936 q -0.05197,0 -0.05197,0.05197 l -0.519681,-0.05197 h -0.25984 q -0.05197,0 -0.05197,0.05197 -0.239053,-0.07276 -0.25984,-0.207872 -0.363776,0.135117 -0.623616,0.363776 z\"\r\n"
					+ "         id=\"path22031\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.296333,24.967356 v 0.207872 h 0.05197 q 0.187084,0 0.467712,-0.05197 0.436531,0.103936 0.831488,0.415744 h 0.25984 q 0,-0.239053 0.05197,-0.311808 -0.103936,0 -0.103936,-0.25984 h -0.103936 l 0.05197,0.25984 v 0.05197 q 0,0.103936 -0.155904,0.103936 -0.706765,-0.415744 -0.883456,-0.415744 z\"\r\n"
					+ "         id=\"path22029\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 36.746825,24.707516 v 0.103936 h 0.25984 v -0.103936 z\"\r\n"
					+ "         id=\"path22027\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.52942,24.499644 q 0,0.25984 -0.25984,0.25984 v 0.155904 h 0.155904 q 0.08315,0 0.25984,-0.363776 v -0.05197 z\"\r\n"
					+ "         id=\"path22025\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 44.49006,24.239804 q 0,0.363776 0.363776,0.363776 h 0.25984 v -0.103936 h -0.25984 q -0.03118,0 -0.25984,-0.25984 z\"\r\n"
					+ "         id=\"path22023\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 43.970379,23.824059 v 0.103936 q 0.05197,0 0.155904,0.05197 l 0.05197,-0.103936 q -0.114329,0 -0.207872,-0.05197 z\"\r\n"
					+ "         id=\"path22021\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.373516,23.720123 v 0.05197 q 0,0.07276 0.25984,0.207872 h 0.25984 v 0.05197 l -0.51968,0.259841 v 0.103936 h 0.155904 q 0,-0.11433 0.311808,-0.25984 0.675584,0.135116 0.675584,0.311808 0,0.05197 -0.207872,0.311808 v 0.103936 h 0.05197 q 0.25984,-0.467712 0.51968,-0.623616 -0.04157,0 -0.415744,-0.05197 0,0.05197 -0.05197,0.05197 v -0.05197 l 0.103936,-0.155905 v -0.207872 q -0.374169,0.05197 -0.415744,0.05197 -0.249446,-0.155904 -0.675584,-0.155904 z\"\r\n"
					+ "         id=\"path22019\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 47.192396,23.876027 v 0.05197 h 0.05197 q 0.270233,-0.187084 0.363776,-0.415744 h -0.103936 q -0.09354,0 -0.311809,0.363776 z\"\r\n"
					+ "         id=\"path22017\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 43.398731,23.512251 v 0.103936 h 0.155904 v -0.103936 z\"\r\n"
					+ "         id=\"path22015\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 36.019272,23.408315 v 0.155904 h 0.207873 v -0.103936 h 0.103936 q 0,0.103936 0.05197,0.103936 l 0.467712,-0.155904 v -0.05197 q 0,-0.07276 -0.25984,-0.25984 z\"\r\n"
					+ "         id=\"path22013\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 44.282187,22.628795 -0.05197,0.103936 v 0.05197 h 0.05197 q 0.103936,0 0.103936,-0.103936 z\"\r\n"
					+ "         id=\"path22011\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 36.383049,22.472891 q 0.25984,0.08315 0.25984,0.25984 -0.103936,0.166298 -0.103936,0.311808 h 0.05197 q 0.103936,-0.103936 0.883456,-0.207872 0.05197,0.103936 0.05197,0.155904 l -0.05197,0.25984 q 0.135117,0.01039 0.363776,0.363776 v 0.05197 q 0,0.103936 -0.155904,0.103936 -0.06236,0 -0.571648,-0.207872 v 0.103936 q 0.623616,0.446925 0.623616,0.727553 0.25984,0.25984 0.675584,0.25984 0.03118,0 0.207872,-0.05197 0.103936,0.05197 0.155904,0.05197 0.280628,-0.05197 0.467713,-0.05197 0.301414,0.592435 0.415744,0.675584 0,0.05197 -0.05197,0.155904 0.05197,0.51968 0.05197,0.77952 0,0.155904 -1.091329,0.51968 -0.353382,0.155904 -1.507072,0.363777 v 0.05197 h 0.363776 q 1.330381,-0.301415 2.078721,-0.623617 h 0.05197 q 0.103936,0.02079 0.103936,0.103936 v 1.091329 h 1.766912 q 0.07276,0 0.155904,0.155904 v 0.25984 q -0.155904,0.675584 -0.155904,0.727552 v 0.25984 l 0.103936,0.155904 -0.155904,0.935425 0.415745,0.675584 -0.103936,0.05197 h -2.026753 v 0.51968 q 0,0.103936 -0.155904,0.103936 -0.06236,0 -0.571648,-0.415744 l -0.467713,-0.05197 q 0,0.14551 0.519681,0.207872 0.04157,0.155904 0.623616,0.415744 l 0.05197,0.25984 v 0.831489 q 0,0.218265 -0.05197,0.415744 0.05197,0.197478 0.05197,0.415744 v 0.467712 h 0.51968 q 0.103936,0.02079 0.103936,0.103936 v 0.155904 q 0,0.228659 -0.415744,1.091329 -0.103936,0.332595 -0.103936,0.467712 0.05197,0 0.05197,0.05197 -0.935424,0.08315 -0.935424,0.571648 0,0.623616 -0.831489,0.935424 h -0.05197 q -0.239053,-0.103936 -0.467712,-0.103936 -0.332595,0.103936 -0.467712,0.103936 0,-0.103936 -0.623616,-0.77952 0,-0.530074 -0.155904,-0.675584 v -0.05197 h 0.207872 l 0.25984,0.207872 h 0.311808 v -0.05197 q -0.540468,0 -0.883456,-0.831489 l -0.103936,-0.05197 q -0.124724,0 -0.467713,0.155905 -1.143296,-0.155905 -1.143296,-0.467713 0,-0.176691 -0.05197,-0.25984 0.05197,-0.280627 0.05197,-0.467712 v -0.415744 h 0.25984 q 0.103936,0 0.103936,-0.103936 0,-0.103936 -0.155904,-0.103936 -0.155904,0.05197 -0.155904,0.103936 -0.571648,0 -0.571648,-0.415744 0,-0.363776 0.05197,-0.415744 -0.05197,-0.353383 -0.05197,-0.571649 v -0.05197 q 0,-0.467712 0.05197,-0.623616 -0.05197,-0.457318 -0.05197,-0.77952 v -0.883456 q 0,-0.06236 0.311808,-0.259841 l 0.25984,0.05197 h 0.415744 q 0.925031,-0.207873 1.03936,-0.207873 0.145511,0.415745 0.207873,0.415745 h 0.103936 V 29.85235 l -0.155904,-0.207873 q 0.207872,-0.353382 0.207872,-0.623616 -0.207872,-0.08315 -0.207872,-0.363776 h -0.103937 l -0.05197,0.25984 q 0.08315,0 0.207873,0.207872 l -0.05197,0.25984 q -0.301415,0 -0.831489,0.05197 -0.103936,-0.05197 -0.155904,-0.05197 -0.166297,0.103936 -0.311808,0.103936 h -0.155904 q -0.103936,0 -0.207872,-0.415744 l 0.05197,-0.103936 v -0.103936 q -0.103936,-0.685978 -0.207872,-0.935424 0.311808,-0.696372 0.311808,-0.935424 -0.25984,0 -0.623616,-0.727553 -0.05197,-0.197478 -0.05197,-0.467712 v -0.467712 q 0,-0.11433 0.363776,-0.207872 l -0.103936,-0.207872 q 0.14551,-0.280628 0.25984,-1.091329 0.478106,-0.207872 0.571648,-0.207872 h 0.363776 q 0.11433,0 0.207872,0.05197 0.145511,0 0.623617,-1.03936 0,-0.155904 0.155904,-0.155904 z\"\r\n"
					+ "         id=\"path22009\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 44.645964,22.368955 v 0.103936 h 0.207872 v -0.103936 z\"\r\n"
					+ "         id=\"path22007\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 42.255435,22.161083 v 0.103936 q 0.311808,0 0.571648,0.311808 l 0.363776,-0.311808 h -0.25984 q -0.124723,0.103936 -0.207872,0.103936 -0.37417,-0.207872 -0.467712,-0.207872 z\"\r\n"
					+ "         id=\"path22005\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 36.954697,21.901243 q 0.02079,0.103936 0.103936,0.103936 0.103936,-0.05197 0.155904,-0.05197 v -0.05197 z\"\r\n"
					+ "         id=\"path22003\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 45.78926,21.745339 h 0.155904 v -0.103936 h -0.05197 q -0.103936,0.02079 -0.103936,0.103936 z\"\r\n"
					+ "         id=\"path22001\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 38.305865,21.225658 v 0.415745 q 0.363776,-0.103936 0.571649,-0.103936 v -0.103936 l -0.363777,0.05197 Q 38.357833,21.319203 38.357833,21.22566 Z\"\r\n"
					+ "         id=\"path21999\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 41.631818,20.809914 q 0.05197,0 0.155905,0.05197 0.04157,0 0.415744,-0.05197 1.143296,0.05197 1.507072,0.05197 0.02079,0.103936 0.103936,0.103936 0.05197,0 0.05197,-0.05197 l 0.25984,0.05197 q 0.05197,0 0.155904,-0.05197 0.259841,0.06236 0.259841,0.155904 l 0.103936,-0.05197 h 0.05197 q 1.683763,0 3.533825,1.403137 1.03936,1.174477 1.03936,2.598401 0,0.09354 -0.103936,0.25984 l 0.103936,0.207872 q -0.353382,2.026753 -0.883456,2.026753 0,0.415744 -0.987392,1.195264 -0.467713,-0.301414 -0.467713,-1.351168 h 0.05197 q 0.675585,-0.03118 0.675585,-0.103936 l 0.311808,-0.415745 v -0.103936 q -0.155904,-0.166297 -0.155904,-0.25984 h -0.103936 v 0.05197 l 0.05197,0.363776 q -0.207872,0.207873 -0.623617,0.311809 -0.301414,0 -0.623616,-0.103936 -0.05197,0 -0.05197,0.05197 l -0.363776,-0.05197 h -0.207872 q -0.77952,0 -1.03936,0.571648 -0.176692,0.311808 -0.727553,0.311808 0,0.77952 -0.363776,0.77952 0,0.103936 -0.935424,0.935424 l -0.103936,-0.05197 -0.77952,0.311809 q -0.239053,-0.103936 -0.25984,-0.207872 0.103936,-0.394957 0.103936,-0.727553 -0.103936,0 -0.155905,-0.311808 0.08315,-0.727552 0.467713,-0.727552 0.831488,-0.207872 0.935424,-0.207872 l 0.05197,0.103936 q 0.25984,-0.155904 0.415744,-0.155904 h 0.25984 v -0.05197 q 0,-0.07275 -0.155904,-0.155904 h -0.25984 v -0.05197 q 0.571648,-0.4885 0.571648,-0.987393 v -0.77952 q -0.249446,-0.322202 -0.25984,-0.415744 0.06236,0 0.415744,-0.51968 v -0.103936 l -0.467712,0.571648 h -0.103944 q -0.291021,-0.207866 -1.091328,-0.36377 l -0.51968,0.05197 h -0.935425 q -0.571648,0 -0.571648,-0.207872 0.831488,-0.384564 0.831488,-0.675585 h 0.207872 l 0.259841,-0.05197 v -0.05197 q -0.415745,0 -0.415745,-0.155904 0.09354,-0.498893 0.727553,-0.935424 0.05197,-0.09354 0.05197,-0.207872 0,-0.124723 -0.155904,-0.311808 v -0.103936 q 0,-0.08315 0.77952,-0.363776 0.11433,0 0.571648,0.05197 0.135117,0 0.623616,-0.207872 0.446925,0.05197 0.727553,0.05197 h 0.207872 l 0.25984,-0.05197 V 21.589435 H 44.7499 q 0,-0.25984 -0.103936,-0.25984 v 0.25984 q -0.103936,0.05197 -0.155904,0.05197 -0.05197,-0.09354 -0.05197,-0.207872 h -0.103936 q 0,0.08315 -0.103936,0.155904 l -0.25984,-0.05197 h -0.103936 q -0.176691,0 -0.363776,0.155904 h -0.51968 q -0.228659,0 -0.935424,0.311808 h -0.103936 q -0.124724,0 -0.25984,-0.51968 0.05197,-0.103936 0.05197,-0.155904 -0.207873,-0.426138 -0.207873,-0.467713 z\"\r\n"
					+ "         id=\"path21997\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 36.954697,20.809914 q 0.384563,0 0.571648,0.05197 0.342989,-0.05197 0.675584,-0.05197 h 0.623616 q 0.218266,0 0.415745,0.05197 0.197478,-0.05197 0.415744,-0.05197 h 0.935424 l 0.207872,0.207872 q 0,-0.08315 0.207872,-0.207872 h 0.155904 q 0.07276,0 0.155904,0.155904 -0.478105,0.415745 -1.091328,0.415745 -0.09354,-0.05197 -0.207872,-0.05197 v 0.05197 q 0,0.05197 0.207872,0.155904 0.457319,0 0.987392,-0.311809 0.187085,0 0.571649,1.403137 l -0.467713,0.51968 q 0,0.103936 -0.25984,0.467712 0,0.613223 -0.77952,0.883457 0,0.228659 -0.467712,0.363776 -0.197479,-0.06236 -0.51968,-0.675584 h -0.05197 l -0.103936,0.05197 -0.207872,-0.05197 q -0.363777,0.103936 -0.571649,0.103936 -0.228659,0 -0.623616,-0.259841 v -0.05197 q 0,-0.03118 0.207872,-0.25984 -0.311808,-0.478105 -0.363776,-0.935424 0.426138,-0.789914 0.623616,-1.03936 h -0.05197 q -0.301414,0.363776 -0.467712,0.363776 0.05197,0.155904 0.103936,0.155904 -0.103936,0.249446 -0.363776,0.467712 h -0.311808 q -0.384563,0 -0.415744,-0.155904 0,-0.05197 0.05197,-0.05197 -0.08315,-0.25984 -0.363776,-0.25984 h -0.207872 l -0.155905,-0.25984 q 0.166298,-1.195265 0.467713,-1.195265 z\"\r\n"
					+ "         id=\"path21995\" />\r\n"
					+ "      <path\r\n"
					+ "         style=\"font-family:Crackvetica;-inkscape-font-specification:Crackvetica\"\r\n"
					+ "         d=\"m 33.888584,20.602042 h 1.81888 l 0.103936,0.05197 q -0.207872,0.914637 -0.311808,1.039361 0.311808,0.530073 0.311808,0.727552 -0.613222,0.77952 -0.77952,0.77952 l -0.25984,-0.05197 -0.25984,0.05197 q -0.155904,0 -0.155904,-0.155904 H 34.25236 l 0.05197,0.103936 q -0.467712,0.394957 -0.467712,0.623616 H 33.73268 v -3.014145 q 0,-0.07276 0.155904,-0.155904 z\"\r\n"
					+ "         id=\"path21971\" />\r\n"
					+ "    </g>\r\n"
					+ "  </g>\r\n"
					+ "</svg>\r\n";
		}
		
		public static String NoteC() {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
					+ "<!-- Created with Inkscape (http://www.inkscape.org/) -->\r\n"
					+ "\r\n"
					+ "<svg\r\n"
					+ "   width=\"38.443188mm\"\r\n"
					+ "   height=\"38.443161mm\"\r\n"
					+ "   viewBox=\"0 0 38.443188 38.443161\"\r\n"
					+ "   version=\"1.1\"\r\n"
					+ "   id=\"svg5\"\r\n"
					+ "   inkscape:version=\"1.1.1 (3bf5ae0d25, 2021-09-20)\"\r\n"
					+ "   sodipodi:docname=\"NoteC.svg\"\r\n"
					+ "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n"
					+ "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n"
					+ "   xmlns=\"http://www.w3.org/2000/svg\"\r\n"
					+ "   xmlns:svg=\"http://www.w3.org/2000/svg\">\r\n"
					+ "  <sodipodi:namedview\r\n"
					+ "     id=\"namedview7\"\r\n"
					+ "     pagecolor=\"#ffffff\"\r\n"
					+ "     bordercolor=\"#666666\"\r\n"
					+ "     borderopacity=\"1.0\"\r\n"
					+ "     inkscape:pageshadow=\"2\"\r\n"
					+ "     inkscape:pageopacity=\"0.0\"\r\n"
					+ "     inkscape:pagecheckerboard=\"0\"\r\n"
					+ "     inkscape:document-units=\"mm\"\r\n"
					+ "     showgrid=\"false\"\r\n"
					+ "     showguides=\"true\"\r\n"
					+ "     inkscape:guide-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox-edge-midpoints=\"true\"\r\n"
					+ "     inkscape:bbox-paths=\"true\"\r\n"
					+ "     inkscape:snap-nodes=\"false\"\r\n"
					+ "     inkscape:snap-bbox-midpoints=\"true\"\r\n"
					+ "     inkscape:snap-global=\"false\"\r\n"
					+ "     fit-margin-top=\"0\"\r\n"
					+ "     fit-margin-left=\"0\"\r\n"
					+ "     fit-margin-right=\"0\"\r\n"
					+ "     fit-margin-bottom=\"0\"\r\n"
					+ "     inkscape:zoom=\"4.3901061\"\r\n"
					+ "     inkscape:cx=\"70.72722\"\r\n"
					+ "     inkscape:cy=\"85.53324\"\r\n"
					+ "     inkscape:window-width=\"1920\"\r\n"
					+ "     inkscape:window-height=\"1009\"\r\n"
					+ "     inkscape:window-x=\"-8\"\r\n"
					+ "     inkscape:window-y=\"-8\"\r\n"
					+ "     inkscape:window-maximized=\"1\"\r\n"
					+ "     inkscape:current-layer=\"text1481\">\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"1,0\"\r\n"
					+ "       id=\"guide12970\" />\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"0,-1\"\r\n"
					+ "       id=\"guide12972\" />\r\n"
					+ "  </sodipodi:namedview>\r\n"
					+ "  <defs\r\n"
					+ "     id=\"defs2\" />\r\n"
					+ "  <g\r\n"
					+ "     inkscape:label=\"Calque 1\"\r\n"
					+ "     inkscape:groupmode=\"layer\"\r\n"
					+ "     id=\"layer1\"\r\n"
					+ "     transform=\"translate(-19.656191,-12.181169)\">\r\n"
					+ "    <path\r\n"
					+ "       id=\"path12996\"\r\n"
					+ "       style=\"font-variation-settings:normal;vector-effect:none;fill:#0000ff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.999999;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;-inkscape-stroke:none;stop-color:#000000\"\r\n"
					+ "       inkscape:transform-center-x=\"0.041927404\"\r\n"
					+ "       inkscape:transform-center-y=\"-13.470202\"\r\n"
					+ "       d=\"m 38.817552,12.181169 -1.798582,3.127294 -0.250675,0.435907 -0.34885,-0.348849 -2.552465,-2.549995 -0.932324,3.485405 -0.133983,0.500737 -0.440846,-0.254382 -3.124823,-1.802284 0.0013,3.607656 v 0.518643 l -0.490858,-0.131513 -3.485405,-0.932322 0.93541,3.48417 0.1346,0.500737 H 25.82067 l -3.607657,0.0019 1.805372,3.12359 0.259321,0.448873 -0.491476,0.131513 -3.48417,0.93541 2.551848,2.549995 0.366754,0.366137 -0.440846,0.255 -3.12359,1.804753 3.125442,1.802903 0.448873,0.258705 -0.359345,0.359962 -2.549995,2.552465 3.484787,0.932323 0.501355,0.133984 -0.254382,0.440846 -1.802902,3.124823 3.607656,-0.0013 h 0.518644 l -0.131514,0.490857 -0.932322,3.485405 3.48417,-0.935408 0.501356,-0.1346 v 0.509379 l 0.0013,3.607657 3.12359,-1.80537 0.448872,-0.259324 0.132131,0.491477 0.934792,3.484172 2.549996,-2.551849 0.366756,-0.366756 0.25438,0.440846 1.804756,3.12359 1.802901,-3.125439 0.259324,-0.449492 0.359962,0.359962 2.551846,2.549996 0.932323,-3.484788 0.133983,-0.501356 0.440846,0.254383 3.124826,1.802904 -0.0013,-3.607657 V 43.9042 l 0.491477,0.131514 3.484788,0.932323 -0.934795,-3.484172 -0.1346,-0.501355 h 0.508766 l 3.607656,-0.0013 -1.804756,-3.12359 -0.259321,-0.448872 0.490858,-0.132131 3.484788,-0.934792 -2.552465,-2.549997 -0.366753,-0.366755 0.440846,-0.25438 3.12359,-1.804756 -3.124826,-1.802901 -0.449489,-0.259324 0.359962,-0.359961 2.549997,-2.551847 -3.485408,-0.932323 -0.500736,-0.133983 0.254382,-0.440847 1.802285,-3.124824 -3.607657,0.0012 h -0.518645 l 0.131515,-0.491476 0.932323,-3.484788 -3.484169,0.934793 -0.500739,0.1346 V 18.34556 l -0.0019,-3.607657 -3.12359,1.804755 -0.448873,0.259321 -0.131514,-0.490858 -0.935408,-3.484788 -2.549997,2.552465 -0.375398,0.376016 a 15.574866,15.762139 0 0 0 -0.0032,-6.32e-4 l -0.261793,-0.451343 z\" />\r\n"
					+ "    <ellipse\r\n"
					+ "       style=\"fill:#ffffff;stroke:#ff0000;stroke-width:1.012;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "       id=\"ellipse13122\"\r\n"
					+ "       cx=\"38.828659\"\r\n"
					+ "       cy=\"31.377256\"\r\n"
					+ "       rx=\"13.733081\"\r\n"
					+ "       ry=\"13.899035\" />\r\n"
					+ "    <g\r\n"
					+ "       aria-label=\"C\"\r\n"
					+ "       transform=\"scale(0.99914692,1.0008538)\"\r\n"
					+ "       id=\"text1481\"\r\n"
					+ "       style=\"font-size:25.9537px;line-height:1.25;fill:#e12aff;stroke:#000000;stroke-width:0.065\">\r\n"
					+ "      <g\r\n"
					+ "         id=\"g7800\">\r\n"
					+ "        <path\r\n"
					+ "           id=\"path5282\"\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas';stroke-width:0.245669\"\r\n"
					+ "           d=\"M 74.179688 35.861328 C 69.110589 35.890522 64.420661 36.838611 60.111328 38.707031 C 55.798931 40.605112 52.041474 43.224502 48.839844 46.5625 C 45.638216 49.835049 43.090153 53.695816 41.195312 58.146484 C 39.300469 62.59715 38.222293 67.343086 37.960938 72.382812 C 38.352976 72.251912 38.647735 72.022569 38.84375 71.695312 C 38.647735 72.022569 38.548828 72.350481 38.548828 72.677734 C 38.222123 72.677734 38.026268 72.643567 37.960938 72.578125 L 37.960938 73.363281 C 38.091623 73.49419 38.190529 73.461975 38.255859 73.265625 C 38.321189 73.069265 38.353516 72.938489 38.353516 72.873047 C 38.353516 73.331207 38.385842 73.659119 38.451172 73.855469 L 38.15625 73.855469 L 37.960938 73.757812 C 37.960938 73.954165 38.025565 74.181555 38.15625 74.443359 C 38.352265 74.639715 38.386545 74.836853 38.255859 75.033203 C 38.190504 74.967761 38.123924 74.935547 38.058594 74.935547 C 38.014904 74.935547 37.982714 74.931145 37.960938 74.923828 L 37.960938 77.291016 C 38.026268 77.291016 38.058594 77.258801 38.058594 77.193359 C 38.058594 77.062451 38.09092 76.996094 38.15625 76.996094 L 38.15625 77.095703 L 38.548828 76.800781 L 39.626953 76.703125 L 39.724609 76.703125 L 40.117188 76.603516 L 39.921875 76.408203 L 40.509766 76.408203 L 40.3125 76.603516 L 40.738281 76.533203 C 40.727288 76.525897 40.716071 76.516871 40.705078 76.505859 C 40.639748 76.440393 40.607422 76.374035 40.607422 76.308594 C 40.607422 76.112244 40.738645 76.015625 41 76.015625 L 41 76.505859 L 41.783203 76.408203 L 41.587891 76.996094 L 41.685547 76.996094 L 41.587891 77.193359 C 41.457205 77.520616 41.390625 77.814367 41.390625 78.076172 C 41.390625 78.337974 41.325998 78.46875 41.195312 78.46875 L 41 79.451172 C 41.653394 79.647522 42.404495 79.746094 43.253906 79.746094 L 43.351562 79.648438 L 43.449219 79.746094 L 44.429688 79.746094 C 44.756383 79.746094 45.083458 79.778308 45.410156 79.84375 C 45.540842 80.040103 45.637795 80.269446 45.703125 80.53125 C 45.833803 80.7276 46.029658 80.826172 46.291016 80.826172 C 46.487044 80.826172 46.618264 80.661238 46.683594 80.333984 C 46.748924 80.006728 46.78125 79.646612 46.78125 79.253906 C 46.78125 78.795749 46.748924 78.371221 46.683594 77.978516 C 46.618264 77.520356 46.585937 77.192444 46.585938 76.996094 L 47.859375 76.996094 L 47.957031 77.585938 L 49.330078 76.996094 L 49.330078 79.15625 C 49.983472 78.828996 50.571037 78.503037 51.09375 78.175781 C 51.681806 77.848528 52.237052 77.422046 52.759766 76.898438 C 52.825096 76.963889 52.924002 77.126867 53.054688 77.388672 C 53.185373 77.585035 53.25 77.715808 53.25 77.78125 L 54.818359 81.021484 L 53.25 79.548828 L 52.269531 80.53125 L 52.564453 80.628906 L 52.074219 80.628906 L 49.917969 82.691406 L 48.839844 82.789062 L 48.349609 83.279297 L 47.664062 82.886719 L 47.271484 83.083984 L 48.251953 85.832031 L 48.544922 86.029297 C 48.544922 86.094764 48.512596 86.159168 48.447266 86.224609 L 48.447266 86.324219 L 46.878906 87.697266 L 45.800781 87.304688 L 45.410156 87.599609 C 45.344826 87.599609 45.24592 87.535205 45.115234 87.404297 C 45.049879 87.273388 45.082205 87.174817 45.212891 87.109375 C 44.624837 86.847568 44.004956 86.618225 43.351562 86.421875 C 42.763509 86.22552 42.175944 86.028381 41.587891 85.832031 C 41.522561 85.96294 41.423654 85.96294 41.292969 85.832031 C 41.162283 85.701123 41.06533 85.636719 41 85.636719 L 40.117188 86.324219 L 39.529297 86.814453 L 39.724609 87.304688 L 40.019531 87.011719 L 40.214844 87.207031 L 39.822266 87.501953 C 39.952946 87.829207 40.116485 88.352304 40.3125 89.072266 C 40.508513 89.726775 40.672049 90.185461 40.802734 90.447266 L 41.490234 90.447266 L 41.097656 90.9375 L 41 90.9375 C 41 91.068409 41.032326 91.16698 41.097656 91.232422 C 41.228316 91.232422 41.260643 91.296826 41.195312 91.427734 L 41.978516 93 L 43.644531 93 L 44.234375 93.980469 L 42.371094 93.980469 C 42.697792 94.569527 43.024867 95.158989 43.351562 95.748047 C 43.678261 96.271656 44.005336 96.79475 44.332031 97.318359 L 44.527344 97.318359 L 44.429688 97.417969 C 44.756383 97.941578 45.083458 98.432468 45.410156 98.890625 C 45.802192 99.348782 46.193899 99.83967 46.585938 100.36328 L 46.878906 100.36328 L 46.78125 100.55859 C 47.107946 101.01675 47.467339 101.44323 47.859375 101.83594 C 48.251411 102.22864 48.643118 102.65317 49.035156 103.11133 L 49.134766 102.91602 C 49.200096 102.91602 49.232422 102.98042 49.232422 103.11133 C 49.232422 103.17677 49.200096 103.20898 49.134766 103.20898 L 50.505859 104.38867 L 50.505859 104.19141 L 50.603516 104.38867 C 50.603516 104.45414 50.635842 104.51854 50.701172 104.58398 L 51.681641 105.46875 C 51.550955 105.2724 51.583282 105.17383 51.779297 105.17383 C 52.040647 105.10839 52.238455 105.07617 52.369141 105.07617 C 52.499826 105.07617 52.499826 105.20695 52.369141 105.46875 C 52.255752 105.69589 52.165751 105.7985 52.101562 105.7793 L 52.662109 106.1543 L 53.152344 106.25391 C 53.152344 106.31935 53.053437 106.35156 52.857422 106.35156 L 53.447266 106.74414 L 53.740234 106.74414 L 53.642578 106.94141 L 54.525391 107.5293 L 54.525391 107.43164 L 54.916016 107.43164 L 54.623047 106.94141 L 55.308594 106.94141 L 54.818359 107.72656 C 55.341073 108.05382 55.831687 108.34757 56.289062 108.60938 C 56.746438 108.87118 57.235097 109.13273 57.757812 109.39453 C 57.692482 109.26363 57.660156 109.10065 57.660156 108.9043 L 57.660156 108.31445 L 58.738281 108.31445 L 58.935547 108.2168 L 59.228516 108.51172 L 58.935547 108.80664 L 58.542969 109.19922 C 58.412289 109.33011 58.24875 109.46089 58.052734 109.5918 L 58.738281 109.88672 L 58.935547 109.78711 L 59.033203 109.78711 L 59.130859 109.78711 L 59.033203 109.88672 L 58.935547 109.98438 C 60.438352 110.76979 61.908271 111.32507 63.345703 111.65234 L 63.541016 111.55469 L 63.541016 111.75195 C 64.782463 112.07921 65.991859 112.34076 67.167969 112.53711 C 68.409416 112.79891 69.683447 112.99409 70.990234 113.125 L 71.185547 112.24219 L 72.753906 112.83203 C 73.733995 112.04662 74.648635 111.22782 75.498047 110.37695 L 74.224609 109.98438 L 76.478516 108.60938 L 77.949219 110.17969 C 77.557183 110.5724 77.131204 110.8681 76.673828 111.06445 L 77.164062 113.02734 L 79.712891 112.83203 L 79.320312 112.33984 C 79.320312 111.75078 79.419219 111.19548 79.615234 110.67188 L 80.595703 112.24219 L 80.595703 112.33984 L 80.496094 112.33984 L 80.203125 112.73242 C 86.149008 111.81611 91.24513 109.65736 95.492188 106.25391 C 99.739245 102.85046 103.23426 98.496843 105.97852 93.195312 L 85.103516 83.574219 C 83.796728 85.472297 82.227941 87.174315 80.398438 88.679688 C 78.634274 90.18506 76.544507 90.9375 74.126953 90.9375 C 72.23211 90.9375 70.368563 90.478814 68.539062 89.5625 L 66.677734 89.5625 L 67.167969 88.679688 C 65.926522 87.763373 64.880661 86.61861 64.03125 85.244141 C 63.181839 83.804217 62.529641 82.297387 62.072266 80.726562 C 61.614895 79.155741 61.320138 77.552291 61.189453 75.916016 C 61.124348 74.220405 61.221222 72.656387 61.480469 71.220703 C 61.459839 71.275325 61.330572 71.302734 61.091797 71.302734 L 61.189453 71.205078 L 61.287109 71.105469 L 61.482422 71.007812 C 61.74378 69.371539 62.202075 67.76809 62.855469 66.197266 C 63.508862 64.626442 64.358868 63.252335 65.404297 62.074219 C 66.515066 60.8961 67.789095 59.946524 69.226562 59.226562 C 70.664027 58.506603 72.29745 58.146484 74.126953 58.146484 C 76.544507 58.146484 78.601956 58.800355 80.300781 60.109375 C 82.064944 61.418395 83.502514 63.088209 84.613281 65.117188 L 105.49023 55.103516 C 102.35395 48.885673 98.138192 44.139737 92.845703 40.867188 C 87.553217 37.529187 81.411234 35.861328 74.419922 35.861328 C 74.339269 35.861328 74.260149 35.860865 74.179688 35.861328 z M 61.480469 71.220703 C 61.48242 71.215538 61.482422 71.210732 61.482422 71.205078 C 61.481483 71.210252 61.481404 71.215526 61.480469 71.220703 z M 40.738281 76.533203 C 40.792622 76.569315 40.846033 76.560289 40.900391 76.505859 L 40.738281 76.533203 z M 37.960938 74.923828 L 37.960938 74.835938 C 37.917452 74.879497 37.917588 74.909263 37.960938 74.923828 z M 62.984375 47.947266 C 63.703109 48.339971 64.357259 48.798659 64.945312 49.322266 C 65.533366 49.845875 66.153247 50.368969 66.806641 50.892578 L 65.042969 52.365234 C 64.912286 52.168879 64.74875 51.973694 64.552734 51.777344 C 64.422062 51.515539 64.290842 51.253992 64.160156 50.992188 L 62.494141 51.580078 C 62.559471 50.991018 62.559471 50.369354 62.494141 49.714844 C 62.494141 49.060334 62.657679 48.470872 62.984375 47.947266 z M 52.074219 105.76172 C 52.082385 105.7699 52.090422 105.77623 52.099609 105.7793 L 52.074219 105.76172 z \"\r\n"
					+ "           transform=\"matrix(0.26480924,0,0,0.26435762,19.672974,12.170778)\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas';fill:#e12aff;fill-opacity:1\"\r\n"
					+ "           d=\"m 28.992856,26.65275 q -0.129768,0.207629 -0.129768,0.467166 0.02595,0.259537 0,0.493121 L 29.3043,27.457314 q 0.05191,0.103815 0.103815,0.20763 0.07786,0.07786 0.129769,0.155722 l 0.467166,-0.389305 q -0.259537,-0.20763 -0.49312,-0.41526 -0.233583,-0.207629 -0.519074,-0.363351 z\"\r\n"
					+ "           id=\"path5280\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.568082,31.954486 q -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5278\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.41236,31.461365 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5276\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.41236,31.850671 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5274\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.308545,32.317837 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5272\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.074962,32.447606 0.103815,0.07786 0.103815,0.02595 -0.103815,-0.07786 z\"\r\n"
					+ "           id=\"path5270\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.049012,32.317837 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5268\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.867336,32.162115 q -0.05191,0 -0.129768,0.07786 h 0.233583 q -0.07786,-0.07786 -0.103815,-0.07786 z\"\r\n"
					+ "           id=\"path5266\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.19254,31.046106 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5264\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.21849,31.643041 q -0.02595,-0.05191 -0.05191,-0.07786 0,-0.05191 -0.02595,-0.103815 l -0.02595,0.05191 q 0.05191,0.103814 0.103815,0.129768 z\"\r\n"
					+ "           id=\"path5262\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.088721,30.994199 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5260\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.984907,31.201828 0.07786,0.07786 0.02595,-0.05191 0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path5258\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.984907,31.35755 0.02595,0.05191 0.05191,0.02595 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5256\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.907047,31.539226 0.207629,0.155723 q -0.02595,-0.05191 -0.05191,-0.07786 0,-0.05191 -0.02595,-0.103814 h -0.07786 z\"\r\n"
					+ "           id=\"path5254\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.958957,31.928532 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5252\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.803235,31.876622 v 0.02595 q 0.05191,-0.02595 0.103815,-0.129768 l -0.05191,0.02595 z\"\r\n"
					+ "           id=\"path5250\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.803235,31.772808 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5248\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.751325,32.42165 q 0.129769,-0.05191 0.20763,-0.155722 h -0.05191 l -0.103815,0.05191 z\"\r\n"
					+ "           id=\"path5246\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.010862,32.188067 q 0,-0.02595 0.05191,-0.07786 0,-0.02595 -0.05191,-0.129769 z\"\r\n"
					+ "           id=\"path5244\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 30.244446,32.214017 H 30.08873 l 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path5242\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.296356,32.162107 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5240\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.270406,31.928524 q 0.07786,0 0.129769,-0.02595 0.07786,-0.05191 0.129768,-0.103815 -0.05191,0 -0.155722,0.05191 v -0.103815 h -0.181676 q 0.02595,0.02595 0.07786,0.07786 0.05191,0.02595 0.05191,0.05191 z\"\r\n"
					+ "           id=\"path5238\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.452082,31.513265 q 0.02595,-0.02595 0.02595,-0.05191 0.02595,-0.02595 0.05191,-0.05191 v -0.02595 h -0.207629 z\"\r\n"
					+ "           id=\"path5236\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.711619,32.058292 h -0.129768 l 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path5234\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.659709,31.90257 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5232\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.711619,31.40945 q -0.05191,-0.103815 -0.233583,-0.20763 -0.07786,0.02595 -0.181676,0.05191 -0.07786,0.02595 -0.129768,0.07786 0.07786,0 0.155722,-0.02595 0.103815,-0.02595 0.181676,-0.05191 0.07786,0.103814 0.207629,0.155722 z\"\r\n"
					+ "           id=\"path5230\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.841399,32.888811 -0.05191,-0.05191 q -0.155722,0.155723 -0.207629,0.259537 z\"\r\n"
					+ "           id=\"path5228\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.763539,33.174301 q 0.07786,-0.07786 0.155722,-0.155722 0.07786,-0.07786 0.155722,-0.181676 l -0.07786,0.02595 q -0.103815,0.07786 -0.259537,0.285491 z\"\r\n"
					+ "           id=\"path5226\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.178798,32.940718 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5224\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.204748,32.60332 -0.103815,-0.07786 -0.07786,0.05191 v 0.05191 l 0.07786,0.05191 z\"\r\n"
					+ "           id=\"path5222\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.334516,33.044533 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5220\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.282606,32.914764 q 0.05191,-0.05191 0.07786,-0.05191 H 31.20474 Z\"\r\n"
					+ "           id=\"path5218\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.360466,32.473552 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5216\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.386416,32.317829 q 0.05191,0 0.07786,0.02595 0.02595,0.02595 0.07786,0.05191 l 0.07786,-0.07786 q -0.155723,-0.05191 -0.233584,-0.103814 z\"\r\n"
					+ "           id=\"path5214\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.697861,32.759042 -0.07786,-0.07786 -0.103815,0.07786 0.103815,0.103815 z\"\r\n"
					+ "           id=\"path5212\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.775721,31.40945 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5210\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.879548,32.47355 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5208\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.879548,32.214013 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5206\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.268853,31.149911 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5204\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.03527,32.992624 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5202\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.03527,30.968235 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5200\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.294807,32.032337 -0.05191,-0.05191 h -0.05191 l 0.05191,0.05191 z\"\r\n"
					+ "           id=\"path5198\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.320757,32.47355 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5196\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.436766,31.305633 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5194\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.332951,31.305633 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5192\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.281041,30.942281 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5190\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.177227,30.994191 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5188\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.125317,31.098005 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5186\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.151267,31.824709 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5184\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.6841,31.046098 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5182\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.502414,31.279677 q 0.05191,-0.103814 0.05191,-0.129768 -0.05191,-0.103815 -0.05191,-0.129769 z\"\r\n"
					+ "           id=\"path5180\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.424554,31.565168 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5178\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.528369,31.746844 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5176\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.787906,31.409446 0.02595,0.02595 0.02595,0.02595 h 0.02595 q 0,-0.05191 -0.05191,-0.05191 -0.05191,-0.02595 -0.02595,-0.05191 l -0.07786,-0.181675 q -0.07786,0.07786 -0.155722,0.155722 -0.07786,0.07786 -0.129769,0.181676 h 0.363352 z\"\r\n"
					+ "           id=\"path5174\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.969582,31.02014 0.103815,-0.05191 q -0.05191,-0.05191 -0.05191,-0.07786 -0.05191,0.02595 -0.155722,0.129768 z\"\r\n"
					+ "           id=\"path5172\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.047442,31.253724 -0.02595,0.02595 0.02595,0.02595 0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path5170\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.021492,31.383492 q 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5168\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.943632,31.305632 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5166\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.995542,31.954475 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5164\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.021492,31.64303 0.07786,-0.07786 q -0.05191,-0.05191 -0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path5162\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.151261,32.603317 q 0.05191,-0.05191 0.05191,-0.07786 h -0.129768 z\"\r\n"
					+ "           id=\"path5160\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.255076,31.279678 0.05191,-0.07786 h -0.129768 z\"\r\n"
					+ "           id=\"path5158\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.462705,32.499502 -0.181676,-0.07786 0.07786,0.07786 0.181675,0.07786 z\"\r\n"
					+ "           id=\"path5156\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.452076,35.250594 q 0.103815,-0.103814 0.155722,-0.181676 -0.07786,0.02595 -0.181675,0.129769 z\"\r\n"
					+ "           id=\"path5154\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.607798,35.250594 -0.02595,-0.02595 q 0,-0.02595 0.02595,-0.02595 0.02595,0 0.02595,0.02595 0,0.02595 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5152\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.594039,35.899437 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5150\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.490224,36.13302 0.02595,0.02595 0.02595,-0.02595 -0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path5148\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.360456,35.977298 q 0.02595,0 0.02595,0.02595 0,0.02595 -0.02595,0.02595 -0.02595,0 -0.02595,-0.02595 0,-0.02595 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5146\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.308546,35.562039 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5144\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.100917,35.354409 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5142\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.074967,35.510131 q 0,0.02595 0.05191,0.07786 l 0.05191,-0.07786 0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path5140\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.204736,35.925391 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5138\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.997106,35.821576 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5136\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.893291,35.769666 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5134\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.919241,36.236833 q 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5132\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.360454,37.378796 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5130\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.568083,37.274981 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5128\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.671898,37.326891 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5126\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.801667,36.340651 q -0.103815,0.103814 -0.259537,0.181676 h -0.02595 q 0.02595,-0.155723 -0.05191,-0.337398 -0.259536,0.181675 -0.519073,0.545027 l 0.570981,-0.207629 h 0.02595 l -0.181676,0.570981 -0.02595,0.02595 v 0.103814 l 0.441213,-0.155722 0.02595,-0.363352 z\"\r\n"
					+ "           id=\"path5124\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.827617,35.458225 -0.02595,-0.02595 q 0,-0.02595 0.02595,-0.02595 0.02595,0 0.02595,0.02595 0,0.02595 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5122\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.879527,37.638336 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5120\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.879527,36.470419 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5118\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.009295,38.105502 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5116\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 40.18471,38.105502 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5114\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.925173,38.157412 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5112\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.665636,38.702439 q 0.05191,0.05191 0.05191,0.07786 l 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5110\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.717546,39.532958 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5108\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.639686,38.806254 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5106\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.535871,37.482615 q -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5104\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.509921,39.039837 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5102\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.276338,37.223078 q -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5100\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.068708,38.961976 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5098\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.757264,37.949782 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5096\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.393912,37.378801 0.05191,0.05191 q 0.02595,0 0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path5094\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.523681,38.936023 -0.07786,0.05191 0.02595,0.02595 0.07786,-0.05191 0.02595,-0.07786 z\"\r\n"
					+ "           id=\"path5092\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.342005,39.091745 0.05191,-0.02595 0.02595,-0.05191 -0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5090\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.186283,38.28718 0.02595,0.05191 0.02595,-0.05191 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5088\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.134373,38.079551 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5086\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.004605,38.339088 q 0.02595,0 0.07786,0.05191 l 0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path5084\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.978655,38.598625 q 0.05191,-0.05191 0.07786,-0.05191 l -0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5082\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.08247,38.832208 q 0,0.05191 -0.05191,0.129768 -0.05191,0.05191 -0.05191,0.103815 0,0.02595 0.02595,0.07786 0.02595,0.05191 0.05191,0.05191 0.05191,0 0.103815,-0.259537 0.05191,-0.259537 0.103815,-0.311445 -0.07786,0.05191 -0.181676,0.20763 z\"\r\n"
					+ "           id=\"path5080\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.238192,39.195566 q 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5078\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.004609,39.377241 -0.02595,0.02595 0.02595,0.05191 0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path5076\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.900794,39.169612 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5074\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.848884,39.065797 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5072\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.822934,38.624584 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5070\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.796984,36.158983 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5068\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.745074,38.157418 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5066\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.693164,38.442908 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5064\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.589349,38.339094 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5062\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.38172,36.003261 0.05191,0.05191 0.05191,-0.05191 z\"\r\n"
					+ "           id=\"path5060\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.43363,36.807825 v 0.155722 l 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5058\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.35577,36.574242 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5056\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.30386,36.755918 -0.07786,0.07786 0.05191,0.05191 q 0.103815,-0.05191 0.155722,-0.155722 z\"\r\n"
					+ "           id=\"path5054\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.836693,37.690251 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5052\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.784783,37.378807 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5050\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.810733,38.676492 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5048\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.784783,39.377241 -0.02595,0.02595 0.02595,0.05191 0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path5046\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.732873,37.638344 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5044\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.706923,38.884121 0.02595,-0.05191 0.05191,-0.02595 -0.05191,-0.02595 -0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path5042\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.629063,37.794066 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5040\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.629063,39.065797 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5038\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.577153,35.71777 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5036\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.447385,38.598631 0.07786,-0.05191 0.02595,-0.05191 H 36.44738 Z\"\r\n"
					+ "           id=\"path5034\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.447385,38.365047 -0.02595,-0.02595 -0.05191,0.02595 0.05191,0.02595 z\"\r\n"
					+ "           id=\"path5032\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.577153,39.325334 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5030\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.13594,37.794066 q 0,-0.05191 0.07786,-0.07786 0.07786,-0.02595 0.129769,-0.02595 h -0.02595 -0.05191 q -0.129769,0 -0.155722,0.07786 L 36.5512,38.183375 36.291663,37.79407 Z\"\r\n"
					+ "           id=\"path5028\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.980218,39.377241 0.07786,0.07786 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5026\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.265709,35.639909 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5024\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.239759,36.262798 q -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5022\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.239759,37.586436 q -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5020\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.161899,37.534526 q -0.07786,0 -0.155722,0.07786 h 0.07786 z\"\r\n"
					+ "           id=\"path5018\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.954269,34.26436 0.02595,-0.05191 0.05191,-0.02595 -0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path5016\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.954269,39.143655 q 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5014\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.642825,39.377238 q 0.103815,-0.02595 0.20763,-0.129768 l -0.103815,0.02595 z\"\r\n"
					+ "           id=\"path5012\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.668775,38.936026 0.05191,-0.02595 -0.05191,-0.02595 -0.02595,-0.05191 v 0.155722 z\"\r\n"
					+ "           id=\"path5010\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.746635,38.079554 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5008\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.824495,34.861295 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path5006\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.876405,37.482618 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5004\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.772591,31.253731 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5002\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.798541,32.447601 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path5000\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.720681,30.942286 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4998\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.720681,31.046101 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4996\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.616866,31.046101 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4994\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.513052,30.994191 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4992\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.513052,31.305635 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4990\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.590912,33.252163 h -0.02595 l -0.02595,0.02595 0.02595,0.07786 0.02595,0.02595 q -0.155723,-0.05191 -0.233584,-0.129768 l 0.155723,0.207629 0.415259,0.02595 -0.311445,-0.155722 q 0.05191,0 0.103815,0 0.07786,-0.02595 0.129769,-0.02595 h 0.07786 v -0.07786 q -0.05191,-0.155722 -0.103815,-0.20763 H 35.51305 q -0.07786,0.07786 -0.07786,0.103815 0,0.05191 0,0.103815 0,0.05191 0.05191,0.05191 l 0.05191,-0.02595 h 0.02595 0.05191 z\"\r\n"
					+ "           id=\"path4988\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.590912,38.650532 h -0.129769 l 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4986\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.487097,39.117699 v 0.155722 h 0.103815 v -0.155722 z\"\r\n"
					+ "           id=\"path4984\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.253514,32.39569 0.05191,0.02595 0.02595,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4982\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.045884,31.175867 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4980\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.786347,31.331589 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4978\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.838257,33.09644 q -0.129768,0.155723 -0.207629,0.389306 -0.05191,0.233583 -0.103815,0.415259 l 0.07786,0.285491 q 0.155722,-0.05191 0.285491,-0.129769 0.155722,-0.07786 0.311444,-0.129768 l 0.155722,0.155722 v -0.441213 q -0.07786,0.103815 -0.129768,0.02595 -0.02595,-0.103815 -0.07786,-0.155723 l -0.311445,0.20763 q 0.07786,-0.311444 0,-0.622889 z\"\r\n"
					+ "           id=\"path4976\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.149699,34.368172 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4974\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.942069,35.847532 -0.103815,0.07786 0.103815,0.07786 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path4972\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.52681,34.965107 v 0.129768 l 0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path4970\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.52681,34.731523 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4968\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 34.57872,35.69181 34.65658,35.61395 34.60467,35.588 34.57872,35.53609 Z\"\r\n"
					+ "           id=\"path4966\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.65658,34.99106 0.02595,-0.05191 -0.02595,-0.02595 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4964\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.65658,30.994191 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4962\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.500858,32.006385 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4960\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.448948,37.79406 q -0.05191,0.103815 -0.05191,0.155722 0.129769,-0.05191 0.259537,-0.129768 l -0.103814,-0.02595 z\"\r\n"
					+ "           id=\"path4958\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.345133,37.897875 v -0.441213 l -0.207629,0.207629 z\"\r\n"
					+ "           id=\"path4956\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.215365,38.183365 -0.07786,-0.02595 v 0.103814 h 0.129768 z\"\r\n"
					+ "           id=\"path4954\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.11155,35.847532 -0.155722,0.103815 0.07786,-0.02595 0.07786,-0.07786 v -0.05191 h -0.155722 z\"\r\n"
					+ "           id=\"path4952\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.319179,34.757477 h -0.129768 l 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4950\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.293229,34.913199 -0.02595,0.02595 0.02595,0.05191 0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path4948\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.189414,34.913199 -0.05191,0.02595 0.05191,0.05191 0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path4946\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.137504,35.587995 -0.05191,0.05191 0.05191,0.05191 0.07786,0.02595 v -0.155722 z\"\r\n"
					+ "           id=\"path4944\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.111554,36.210884 q -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4942\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.371091,38.7803 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4940\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.371091,38.884115 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4938\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.007739,32.1102 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4936\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.163461,31.40945 q -0.05191,-0.05191 -0.07786,-0.05191 v 0.129769 l 0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path4934\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.267277,32.862864 0.07786,-0.103815 -0.07786,-0.07786 -0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4932\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.163462,33.693382 q -0.155722,0.103815 -0.311444,0.20763 -0.155722,0.103815 -0.285491,0.233583 0.20763,0 0.389306,-0.05191 0.181676,-0.05191 0.389305,-0.103815 -0.02595,-0.129768 -0.181676,-0.285491 z\"\r\n"
					+ "           id=\"path4930\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.826064,35.63991 h 0.05191 L 33.826064,35.588 h -0.05191 z\"\r\n"
					+ "           id=\"path4928\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.722249,37.664298 v 0.155723 l 0.02595,-0.05191 0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path4926\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.852018,35.510141 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4924\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.592481,36.184938 -0.07786,0.07786 0.07786,0.07786 0.103815,-0.07786 z\"\r\n"
					+ "           id=\"path4922\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.592481,35.873493 q 0.02595,0 0.02595,0.02595 0,0.02595 -0.02595,0.02595 -0.02595,0 -0.02595,-0.02595 0,-0.02595 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4920\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.540571,35.717771 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4918\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.410803,35.821586 h 0.05191 l 0.05191,0.05191 -0.05191,0.07786 -0.05191,0.02595 z\"\r\n"
					+ "           id=\"path4916\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.514618,37.664298 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4914\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.384849,36.83378 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4912\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.332939,36.989502 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4910\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.332939,38.157419 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4908\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.281029,36.678058 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4906\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.203169,36.548289 0.02595,0.02595 0.05191,-0.05191 0.02595,-0.07786 z\"\r\n"
					+ "           id=\"path4904\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.229119,36.885687 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4902\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.787906,34.394132 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4900\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.735996,34.679623 q 0,0.129768 0.05191,0.259537 0.05191,0.103815 0.103815,0.233583 l 0.337398,-0.207629 z\"\r\n"
					+ "           id=\"path4898\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.177209,36.496382 v -0.233583 q 0,-0.20763 -0.07786,-0.389306 -0.07786,-0.181676 -0.129769,-0.389305 -0.311444,0.389305 -0.70075,0.70075 l 0.389306,0.233583 -0.02595,0.02595 -0.129769,0.02595 h -0.05191 l 0.05191,0.103815 q 0.02595,0 0.129769,-0.05191 0.129768,-0.05191 0.07786,-0.07786 l 0.181676,0.05191 q -0.02595,0.02595 0.02595,0.05191 0.05191,0 0.05191,-0.05191 z\"\r\n"
					+ "           id=\"path4896\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.099349,37.716206 -0.05191,0.02595 -0.02595,-0.02595 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4894\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.684089,36.937595 q 0,-0.02595 -0.103814,-0.07786 -0.07786,-0.07786 0.02595,-0.07786 0.05191,0 0.103815,0.07786 0.05191,0.07786 0.07786,0.103814 l 0.20763,-0.233583 h -0.363352 l 0.02595,-0.02595 -0.155723,-0.07786 v 0.20763 l -0.05191,-0.02595 h -0.02595 l 0.20763,0.181676 z\"\r\n"
					+ "           id=\"path4892\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.761949,35.276558 q 0,-0.02595 0.02595,-0.02595 0.02595,0 0.02595,0.02595 0,0.02595 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4890\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.891718,35.302508 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4888\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.969578,35.380368 q 0.05191,0.05191 0.103815,0.05191 h 0.02595 q -0.02595,-0.02595 -0.05191,-0.02595 0,0 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4886\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.047438,35.562044 -0.02595,0.02595 0.02595,0.02595 0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path4884\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.735994,35.458229 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4882\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.606225,34.887248 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4880\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.606225,34.316266 v -0.05191 q -0.02595,0 -0.05191,0 0,-0.02595 -0.02595,-0.02595 h -0.07786 q 0,0.05191 0.02595,0.07786 0.02595,0.02595 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path4878\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.372642,35.198692 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4876\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.320732,35.613951 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4874\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.139056,35.484183 h 0.155722 l -0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path4872\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.113106,38.494812 q -0.02595,0 -0.02595,-0.02595 0,-0.02595 0.02595,-0.02595 0.02595,0 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4870\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.165016,38.001691 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4868\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.346692,36.003257 0.181676,-0.155723 -0.05191,-0.129768 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0.02595,0.02595 0,0.02595 l -0.103815,-0.05191 -0.155722,0.181675 z\"\r\n"
					+ "           id=\"path4866\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.346692,38.287182 0.07786,-0.07786 H 32.26883 Z\"\r\n"
					+ "           id=\"path4864\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.528368,38.858164 h -0.155722 l 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4862\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.450508,38.728395 v -0.155722 l 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4860\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.735999,39.065793 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4858\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.735999,38.598627 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4856\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.099351,39.299376 q 0.07786,-0.05191 0,-0.129768 l 0.07786,-0.07786 -0.07786,-0.103815 -0.07786,0.103815 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4854\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.943628,38.728395 q 0.07786,0.05191 0.181676,0.20763 l 0.07786,-0.07786 q -0.129768,-0.05191 -0.259537,-0.129769 z\"\r\n"
					+ "           id=\"path4852\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.410795,39.896311 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4850\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.177212,39.792497 q 0.181676,0 0.259537,-0.02595 h -0.311445 z\"\r\n"
					+ "           id=\"path4848\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.51461,39.844407 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4846\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.59247,40.129898 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4844\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.59247,39.766546 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4842\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.696285,39.714636 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4840\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.696285,40.129895 q 0.05191,-0.02595 0.05191,-0.02595 0.02595,-0.02595 0.07786,-0.05191 -0.05191,-0.02595 -0.07786,-0.02595 0,-0.02595 -0.05191,-0.05191 z\"\r\n"
					+ "           id=\"path4838\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.748195,39.870358 q 0.07786,-0.07786 0.07786,-0.103815 H 33.72224 v 0.05191 z\"\r\n"
					+ "           id=\"path4836\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.877964,39.818448 q 0.05191,-0.02595 0.07786,-0.02595 0.02595,0 0.07786,-0.05191 -0.02595,0 -0.05191,0 0,-0.02595 -0.02595,-0.02595 h -0.07786 z\"\r\n"
					+ "           id=\"path4834\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.033686,38.676486 q 0,0.02595 0.05191,-0.02595 l 0.07786,-0.07786 Q 34.007739,38.28718 33.929878,38.001689 l -0.363352,0.363352 q -0.259537,-0.05191 -0.519074,-0.07786 -0.233583,-0.05191 -0.49312,-0.103815 l 0.259537,0.41526 q 0.285491,-0.05191 0.570981,-0.07786 0.285491,-0.05191 0.570982,-0.103814 V 38.7803 Z\"\r\n"
					+ "           id=\"path4832\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.267269,39.584865 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4830\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.422991,40.597059 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4828\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.267269,40.337522 q 0.05191,0 0.07786,-0.02595 0.02595,-0.02595 0.07786,-0.05191 -0.02595,0 -0.05191,0 0,-0.02595 -0.02595,-0.02595 h -0.07786 z\"\r\n"
					+ "           id=\"path4826\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.371084,39.636772 q 0.05191,0 0.129768,-0.07786 l -0.07786,0.02595 z\"\r\n"
					+ "           id=\"path4824\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.500852,40.077985 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4822\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.500852,40.337522 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4820\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.552762,40.441337 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4818\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.578712,40.648967 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4816\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.630622,40.752782 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4814\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.656572,39.818448 -0.07786,-0.07786 v 0.155722 z\"\r\n"
					+ "           id=\"path4812\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.682522,38.728393 q -0.103815,0.103815 -0.129768,0.181676 l 0.07786,-0.02595 0.05191,-0.07786 z\"\r\n"
					+ "           id=\"path4810\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.708472,38.235273 0.07786,-0.181676 v -0.103815 q 0,-0.05191 -0.05191,-0.02595 -0.05191,0 -0.103815,-0.02595 l -0.285491,0.181676 0.05191,0.103814 h 0.259537 q -0.07786,0 -0.129768,0.103815 0,0.05191 0,0.05191 h 0.05191 z\"\r\n"
					+ "           id=\"path4808\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.760382,38.858162 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4806\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.734432,38.209319 q 0.02595,0.05191 0,0.103815 0,0.02595 0.05191,0.02595 0.02595,0 0.103815,-0.02595 0.07786,-0.05191 0.103815,-0.07786 0,-0.181676 -0.129769,-0.363352 l -0.103815,-0.07786 0.129769,0.337398 v 0.07786 z\"\r\n"
					+ "           id=\"path4804\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.916107,38.49481 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4802\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.942057,38.728393 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4800\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.864197,40.67492 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4798\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.916107,40.493245 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4796\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 34.968017,39.974171 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4794\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.019927,38.858162 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4792\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.071837,40.623013 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4790\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.071837,40.233708 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4788\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.097787,38.598625 q 0.02595,0 0.07786,-0.05191 h -0.129768 z\"\r\n"
					+ "           id=\"path4786\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.227556,34.186496 -0.02595,-0.05191 -0.05191,-0.02595 v 0.155722 z\"\r\n"
					+ "           id=\"path4784\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.227556,40.077985 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4782\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.331371,39.948217 -0.02595,-0.02595 -0.05191,0.02595 0.05191,0.02595 z\"\r\n"
					+ "           id=\"path4780\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.383281,39.143652 0.05191,-0.07786 h -0.129768 l 0.02595,0.05191 z\"\r\n"
					+ "           id=\"path4778\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.409231,39.273421 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4776\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.487091,39.896309 -0.103815,0.103815 v 0.02595 l 0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path4774\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.590906,40.804689 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4772\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.642816,40.960411 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4770\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.720676,40.337522 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4768\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 35.590908,40.077985 q 0.05191,-0.02595 0.155722,-0.129768 l -0.05191,-0.05191 q -0.05191,0.05191 -0.07786,0.103815 0,0.02595 -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4766\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.44738,41.349717 -0.07786,0.02595 -0.05191,0.103815 v 0.05191 l 0.129769,-0.129769 z\"\r\n"
					+ "           id=\"path4764\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.42143,40.986365 0.02595,-0.02595 -0.02595,-0.02595 -0.05191,0.02595 z\"\r\n"
					+ "           id=\"path4762\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.47334,41.297809 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4760\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.654997,41.661164 -0.05191,-0.07786 -0.05191,-0.02595 v 0.129769 h 0.05191 z\"\r\n"
					+ "           id=\"path4758\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.654997,40.960414 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4756\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.654997,40.363479 q 0.05191,-0.05191 0.05191,-0.07786 h -0.129769 l 0.02595,0.05191 z\"\r\n"
					+ "           id=\"path4754\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.758812,41.713071 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4752\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.810722,40.960414 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4750\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.862632,39.636775 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4748\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.862632,39.532961 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 0.02595,-0.02595 h -0.207629 q -0.155722,0 -0.493121,0.20763 -0.311444,0.207629 -0.441212,0.311444 v 0.233584 l 0.02595,0.181675 0.07786,0.05191 q 0.285491,-0.20763 0.519074,-0.441213 0.233584,-0.233583 0.467167,-0.49312 z\"\r\n"
					+ "           id=\"path4746\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.940492,41.479488 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4744\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 36.992402,39.818451 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4742\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.148125,41.297812 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4740\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.148125,40.026081 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4738\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.200035,38.961979 h -0.155722 l 0.05191,0.02595 0.02595,0.05191 z\"\r\n"
					+ "           id=\"path4736\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.200035,35.717767 0.155722,-0.07786 q -0.05191,-0.103815 -0.129768,-0.181676 -0.05191,-0.103815 -0.155722,-0.181676 v 0.441213 z\"\r\n"
					+ "           id=\"path4734\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.251945,38.884118 q 0,0.02595 -0.02595,0.05191 0,0.02595 0,0.05191 v 0.103815 l 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path4732\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.303855,38.339091 0.02595,-0.05191 -0.02595,-0.02595 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4730\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.329805,41.713071 -0.05191,-0.07786 h -0.05191 l 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path4728\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.43362,39.74059 -0.07786,-0.103815 -0.07786,0.05191 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 0.02595,0.02595 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4726\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.43362,37.897878 -0.07786,-0.07786 -0.07786,0.07786 q -0.05191,0 -0.05191,0.02595 0,0.02595 0.05191,0.05191 0.05191,0 0.07786,0 z\"\r\n"
					+ "           id=\"path4724\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.43362,37.923828 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4722\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.667204,40.960411 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4720\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.641254,39.169606 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4718\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.641254,39.065791 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4716\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.641254,38.910069 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4714\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.511485,38.806254 q 0.05191,-0.02595 0.07786,-0.02595 0.05191,-0.02595 0.103815,-0.07786 l 0.02595,0.02595 0.02595,0.02595 0.02595,0.02595 -0.02595,-0.02595 v -0.02595 -0.02595 l -0.02595,-0.05191 h -0.259537 z\"\r\n"
					+ "           id=\"path4712\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.745069,39.325328 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4710\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 37.796979,40.38943 q 0.07786,-0.07786 0.07786,-0.103815 -0.02595,0 -0.103815,0.07786 z\"\r\n"
					+ "           id=\"path4708\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.108423,39.714634 0.129769,-0.155723 q 0.07786,0 0.07786,0 0,-0.07786 0,-0.07786 H 37.87484 Z\"\r\n"
					+ "           id=\"path4706\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.264146,40.337522 -0.103815,0.07786 0.103815,0.07786 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path4704\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.08247,40.1818 0.05191,-0.02595 q -0.07786,-0.07786 -0.103815,-0.07786 v 0.10381 z\"\r\n"
					+ "           id=\"path4702\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.34201,40.752776 v -0.129769 -0.02595 q -0.103815,0 -0.155722,0.103815 -0.02595,0.07786 -0.07786,0.129769 l 0.233584,0.207629 z\"\r\n"
					+ "           id=\"path4700\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.34201,39.143646 q 0,0.02595 0.05191,0.05191 0.05191,0 0.05191,0.02595 0.02595,0.05191 0.02595,0.129769 0,0.07786 0.02595,0.129768 l 0.129768,-0.07786 0.05191,-0.07786 v -0.02595 l 0.103815,0.05191 q 0,0.05191 -0.02595,0.07786 -0.02595,0 -0.02595,0.05191 v 0.02595 q 0.155722,0 0.181676,-0.02595 0.02595,-0.02595 0.415259,0.155722 V 39.19556 L 38.964907,39.1177 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.05191 l -0.389306,-0.103815 q -0.05191,0.05191 -0.129768,0.07786 -0.07786,0 -0.07786,0.05191 z\"\r\n"
					+ "           id=\"path4698\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.731315,40.052026 q 0,0.07786 -0.05191,0.07786 -0.02595,-0.02595 -0.129769,-0.02595 v 0.129769 h 0.07786 l 0.129769,-0.02595 0.02595,-0.05191 0.207629,0.233584 v -0.441213 l -0.207629,0.207629 q 0.02595,-0.05191 0.05191,-0.103814 0.05191,-0.07786 0,-0.07786 H 38.73131 Z\"\r\n"
					+ "           id=\"path4696\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.653455,39.558905 0.02595,-0.02595 -0.02595,-0.02595 -0.05191,0.02595 z\"\r\n"
					+ "           id=\"path4694\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.938946,39.740581 q 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4692\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.419872,40.233702 q 0.103815,-0.05191 0.02595,-0.155723 l 0.129768,-0.181676 q -0.103815,0.103815 -0.155722,0.129769 z\"\r\n"
					+ "           id=\"path4690\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 38.523687,41.739016 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4688\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.198483,40.830637 h -0.259537 l -0.363352,0.155722 -0.103815,0.181676 h 0.259537 l 0.363352,-0.181676 z\"\r\n"
					+ "           id=\"path4686\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.354205,40.103933 -0.181676,-0.155722 -0.155722,0.155722 0.155722,0.181676 z\"\r\n"
					+ "           id=\"path4684\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.45802,39.117693 q 0.103815,0.103814 0.129768,0.155722 l 0.103815,-0.05191 q -0.05191,-0.05191 -0.103815,-0.05191 -0.02595,-0.02595 -0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path4682\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.665649,39.636766 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 l 0.02595,0.02595 q 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4680\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.847325,40.000118 q -0.07786,-0.207629 -0.28549,-0.337398 -0.05191,0.103815 -0.05191,0.155722 v 0.181676 z\"\r\n"
					+ "           id=\"path4678\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 39.977094,39.013878 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4676\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 40.314492,38.365035 q -0.20763,0.07786 -0.20763,0.02595 0,-0.07786 -0.05191,-0.07786 -0.02595,0 -0.103815,0.05191 -0.05191,0.02595 -0.05191,0.05191 v 0.07786 q 0.103815,0 0.103815,-0.05191 0,-0.07786 0.233583,0.103815 H 39.97709 v 0.20763 q 0.07786,-0.103815 0.181676,-0.181676 v 0.129768 l 0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path4674\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 40.496168,37.690239 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4672\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.14188,37.923822 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4670\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 44.64876,37.378795 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4668\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 44.59685,37.794054 0.07786,0.07786 0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path4666\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.780091,36.626137 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4664\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.702231,37.949776 q -0.02595,-0.05191 -0.02595,-0.07786 0,-0.02595 -0.05191,-0.07786 l -0.02595,0.05191 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path4662\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.546509,37.664285 0.05191,0.05191 h 0.05191 l -0.05191,-0.05191 z\"\r\n"
					+ "           id=\"path4660\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.546509,37.820008 q -0.05191,-0.02595 -0.07786,-0.05191 -0.02595,-0.02595 -0.07786,-0.02595 0.02595,0.07786 0.02595,0.233584 h 0.05191 l 0.07786,-0.103815 z\"\r\n"
					+ "           id=\"path4658\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.416741,37.378795 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 q 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4656\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.286972,37.690239 0.02595,0.05191 q 0.07786,-0.07786 0.07786,-0.103814 h -0.103815 z\"\r\n"
					+ "           id=\"path4654\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.183157,37.742149 q 0.02595,0.05191 0.02595,0.129769 0.02595,0.07786 0.05191,0.129768 l 0.02595,0.02595 V 37.79406 Z\"\r\n"
					+ "           id=\"path4652\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.235067,38.650529 v 0.02595 q 0,0.02595 0.02595,0.02595 l 0.02595,-0.02595 v -0.02595 z\"\r\n"
					+ "           id=\"path4650\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.949576,39.507001 0.05191,0.05191 0.155723,-0.05191 z\"\r\n"
					+ "           id=\"path4648\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.131252,41.012316 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4646\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.027437,40.648964 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4644\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.7679,39.818445 h -0.05191 v 0.05191 l 0.07786,0.02595 v -0.103814 z\"\r\n"
					+ "           id=\"path4642\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.69004,36.807816 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4640\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.430503,36.859726 q 0,0.02595 0.02595,0.02595 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4638\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 40.963336,39.403188 0.02595,0.05191 0.02595,-0.05191 -0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4636\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.041196,41.16804 h -0.441212 l 0.233583,0.207629 z\"\r\n"
					+ "           id=\"path4634\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.093106,38.961975 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4632\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.170966,37.249031 -0.103815,-0.07786 -0.07786,0.07786 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4630\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.222876,40.752781 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4628\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.248826,36.67805 0.07786,-0.07786 -0.07786,-0.103815 -0.07786,0.103815 z\"\r\n"
					+ "           id=\"path4626\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.326686,40.908503 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4624\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.326686,36.859726 0.07786,-0.07786 -0.07786,-0.07786 z\"\r\n"
					+ "           id=\"path4622\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.508362,41.038271 -0.07786,-0.103815 -0.07786,0.103815 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4620\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.508362,40.778734 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4618\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.612177,39.584864 q 0.103815,-0.05191 0.129768,-0.103815 v -0.07786 q -0.07786,0 -0.07786,-0.02595 0,-0.02595 0.02595,0 0.02595,0 -0.02595,-0.05191 l -0.02595,0.02595 q -0.02595,0.02595 0,0.02595 0.02595,-0.02595 0.02595,0 h -0.05191 v 0.07786 l 0.02595,0.02595 v 0.05191 l -0.02595,0.02595 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4616\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.638127,40.96041 0.155722,0.05191 v -0.05191 q -0.02595,0 -0.05191,0 0,-0.02595 -0.02595,-0.02595 h -0.103815 v 0.02595 q 0,0.02595 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path4614\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 41.819802,40.88255 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4612\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.079336,41.142087 h -0.05191 q -0.02595,0 -0.05191,0.02595 0,0 -0.02595,0 l -0.05191,0.05191 0.07786,0.05191 z\"\r\n"
					+ "           id=\"path4610\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.183151,41.245902 h -0.103815 v 0.155722 z\"\r\n"
					+ "           id=\"path4608\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.157201,39.325328 q 0.02595,0 0.02595,-0.02595 l -0.02595,-0.02595 q -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4606\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.157201,38.339088 q 0.07786,-0.07786 0.07786,-0.103815 -0.07786,-0.07786 -0.103815,-0.07786 v 0.07786 q 0,0.02595 0,0.05191 0.02595,0.02595 0.02595,0.05191 z\"\r\n"
					+ "           id=\"path4604\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.261015,41.349717 q 0,-0.05191 -0.02595,-0.05191 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4602\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.36483,40.623013 h -0.155722 q 0.05191,0.05191 0.07786,0.05191 z\"\r\n"
					+ "           id=\"path4600\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.36483,39.792495 h -0.05191 v 0.02595 l 0.02595,0.02595 q -0.02595,0 -0.07786,0.05191 l 0.07786,-0.02595 0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path4598\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.468636,41.453531 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4596\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.520546,41.349717 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4594\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.286963,38.209319 q 0.20763,-0.103815 0.311444,-0.20763 v -0.05191 l -0.311444,0.233583 z\"\r\n"
					+ "           id=\"path4592\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.624361,41.297809 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4590\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.676271,40.38943 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4588\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.702221,37.223078 0.05191,0.05191 v -0.02595 q 0,-0.05191 0,-0.05191 0.02595,-0.02595 0.02595,-0.07786 h 0.07786 q -0.02595,0 -0.05191,-0.02595 0,-0.02595 -0.02595,-0.02595 h -0.02595 z\"\r\n"
					+ "           id=\"path4586\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.728171,39.636772 0.05191,0.05191 v -0.02595 l -0.02595,-0.02595 h 0.02595 l 0.02595,-0.02595 -0.02595,-0.02595 h -0.02595 v 0.02595 z\"\r\n"
					+ "           id=\"path4584\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.806031,40.077985 -0.05191,0.02595 v 0.02595 l 0.02595,0.02595 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4582\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.883891,39.221513 -0.02595,-0.02595 v -0.02595 q 0,0 -0.02595,0.05191 l -0.103815,-0.02595 v 0.07786 h 0.02595 l 0.07786,-0.05191 z\"\r\n"
					+ "           id=\"path4580\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.935801,36.574236 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4578\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.987711,39.870356 h -0.05191 l 0.02595,0.02595 v -0.02595 z\"\r\n"
					+ "           id=\"path4576\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 42.987711,34.809384 q -0.05191,-0.05191 -0.07786,-0.05191 v 0.129769 z\"\r\n"
					+ "           id=\"path4574\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 43.039621,40.052032 q 0.07786,-0.07786 0.07786,-0.103815 v -0.02595 l -0.07786,0.02595 -0.02595,0.02595 0.02595,0.05191 z\"\r\n"
					+ "           id=\"path4572\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 43.221297,40.700874 h 0.02595 v -0.02595 h -0.02595 q -0.07786,0.07786 -0.07786,0.103815 l 0.02595,0.02595 0.02595,-0.02595 V 40.67492 Z\"\r\n"
					+ "           id=\"path4570\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 43.402973,39.844402 q 0.155722,-0.05191 0.233583,-0.129768 -0.05191,-0.02595 -0.181676,0.05191 -0.103814,0.05191 -0.155722,0.05191 l -0.07786,0.05191 v 0.155722 q 0.05191,0 0.129769,-0.05191 0.07786,-0.07786 0.05191,-0.129769 z\"\r\n"
					+ "           id=\"path4568\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 43.688464,39.662726 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4566\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 43.740374,37.949782 q 0.02595,0 0.02595,-0.02595 0,-0.02595 -0.02595,-0.02595 l -0.02595,0.02595 q 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4564\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 44.259448,38.468856 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4562\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 44.311358,38.572671 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 0,0.02595 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4560\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 44.882339,38.98793 q 0.07786,-0.05191 0.181676,-0.20763 -0.259537,-0.103814 -0.389306,-0.129768 l 0.181676,-0.570981 -0.415259,0.622888 q 0.233583,0.103815 0.441213,0.285491 z\"\r\n"
					+ "           id=\"path4558\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.271652,39.299382 -0.02595,-0.05191 h -0.129769 q 0.05191,0.103815 -0.02595,0.20763 -0.07786,0.07786 -0.07786,0.181675 v 0.07786 q 0,0.129768 0.07786,0.129768 h 0.02595 l 0.02595,-0.02595 q 0.02595,-0.02595 0.02595,0 0,0 -0.02595,0 l -0.02595,-0.02595 -0.02595,-0.02595 -0.02595,-0.05191 0.02595,-0.103815 q 0.02595,-0.103815 0,-0.20763 l 0.129768,-0.103815 z\"\r\n"
					+ "           id=\"path4556\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.323562,39.766549 h -0.05191 0.02595 v 0.02595 z\"\r\n"
					+ "           id=\"path4554\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.349512,38.936031 h -0.155723 q 0.07786,0.07786 0.103815,0.07786 z\"\r\n"
					+ "           id=\"path4552\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.531187,39.610827 v 0.02595 h -0.02595 l 0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4550\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.583097,39.558917 -0.02595,-0.02595 v -0.05191 h -0.07786 q 0.05191,0.103815 0.05191,0.129769 z\"\r\n"
					+ "           id=\"path4548\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.686912,36.833779 h -0.07786 l 0.02595,0.02595 0.02595,-0.02595 z\"\r\n"
					+ "           id=\"path4546\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.868588,36.288751 h 0.05191 l 0.05191,-0.02595 v -0.05191 h -0.05191 l -0.05191,0.02595 z\"\r\n"
					+ "           id=\"path4544\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.842638,39.273427 -0.05191,0.05191 0.02595,0.02595 h 0.02595 z\"\r\n"
					+ "           id=\"path4542\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 45.868588,38.961982 0.07786,-0.129768 0.02595,-0.02595 h -0.05191 q -0.02595,0 -0.02595,0.07786 h -0.02595 q -0.07786,0.07786 -0.07786,0.155722 v 0.02595 h 0.07786 z\"\r\n"
					+ "           id=\"path4540\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 46.154078,38.624584 h -0.02595 l -0.02595,0.02595 V 38.7803 h 0.05191 z\"\r\n"
					+ "           id=\"path4538\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 46.257893,37.015455 h 0.05191 v -0.02595 l -0.02595,-0.02595 h -0.02595 l -0.05191,0.103815 q 0.05191,0 0.05191,0.02595 z\"\r\n"
					+ "           id=\"path4536\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 46.335753,38.832214 q 0,-0.02595 -0.02595,-0.02595 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4534\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 46.361703,37.145223 v -0.02595 z\"\r\n"
					+ "           id=\"path4532\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 46.387653,37.093313 h 0.02595 -0.07786 0.02595 v 0.02595 z\"\r\n"
					+ "           id=\"path4530\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.11938,36.366609 0.02595,-0.07786 h 0.05191 v 0.07786 z\"\r\n"
					+ "           id=\"path4528\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.352963,35.795628 v -0.07786 h 0.07786 v 0.07786 z\"\r\n"
					+ "           id=\"path4526\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 25.884231,34.497943 v 0.07786 l -0.07786,-0.02595 0.02595,-0.05191 z\"\r\n"
					+ "           id=\"path4524\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.249148,36.911637 -0.155722,-0.233583 h 0.103815 l 0.103815,0.02595 z\"\r\n"
					+ "           id=\"path4522\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.065907,35.95135 v 0.07786 h -0.07786 v -0.07786 z\"\r\n"
					+ "           id=\"path4520\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.482731,36.288748 h -0.155722 l 0.02595,-0.155722 0.129768,0.02595 z\"\r\n"
					+ "           id=\"path4518\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.143768,36.236838 0.02595,0.103815 -0.259537,-0.05191 0.155722,-0.129768 z\"\r\n"
					+ "           id=\"path4516\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.555897,38.936023 q 0.129769,0.02595 0.233583,0.07786 0.129769,0.02595 0.259537,0 l -0.259537,0.207629 z\"\r\n"
					+ "           id=\"path4514\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.230693,38.598625 -0.129768,-0.155723 0.129768,-0.02595 0.155722,0.02595 -0.05191,0.103815 z\"\r\n"
					+ "           id=\"path4512\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.139073,37.949782 v -0.07786 l -0.07786,-0.02595 v 0.07786 z\"\r\n"
					+ "           id=\"path4510\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.126878,39.740587 q 0.181676,0.129769 0.363352,0.285491 l -0.07786,0.20763 -0.337398,-0.20763 z\"\r\n"
					+ "           id=\"path4508\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.282601,40.700874 h 0.07786 v 0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4506\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.594045,38.468856 q -0.155722,-0.103815 -0.311444,-0.20763 -0.155723,-0.103814 -0.311445,-0.233583 l 0.07786,-0.207629 0.20763,0.28549 h 0.129768 l 0.07786,-0.07786 0.259537,0.337398 z\"\r\n"
					+ "           id=\"path4504\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.594045,39.68868 -0.07786,-0.02595 v -0.07786 l 0.07786,0.02595 z\"\r\n"
					+ "           id=\"path4502\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.842953,35.665857 -0.103815,0.389305 -0.05191,-0.545028 0.207629,-0.155722 0.103815,0.311445 z\"\r\n"
					+ "           id=\"path4500\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.568091,37.456662 0.05191,-0.181676 -0.285491,0.207629 0.02595,0.233584 q 0.103814,-0.103815 0.207629,-0.259537 z\"\r\n"
					+ "           id=\"path4498\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.801675,36.989495 q -0.155723,0 -0.20763,-0.02595 l 0.181676,0.233584 q 0,-0.155722 0.02595,-0.20763 z\"\r\n"
					+ "           id=\"path4496\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.957397,37.404754 0.05191,-0.155722 -0.05191,-0.103815 -0.05191,0.155723 z\"\r\n"
					+ "           id=\"path4494\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.465842,34.471986 q -0.103815,0.129769 -0.233584,0.233584 L 29.10249,34.549847 q 0.155722,-0.07786 0.363352,-0.07786 z\"\r\n"
					+ "           id=\"path4492\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.78948,40.000124 q 0.02595,-0.05191 0.02595,-0.181676 h 0.103815 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 z\"\r\n"
					+ "           id=\"path4490\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 25.702555,34.939153 h 0.103815 l 0.07786,0.02595 v 0.103814 q 0,0.02595 0,0.05191 -0.02595,0.02595 -0.02595,0.05191 z\"\r\n"
					+ "           id=\"path4488\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.467407,33.511699 h -0.07786 l 0.02595,-0.07786 h 0.05191 z\"\r\n"
					+ "           id=\"path4486\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.830759,35.146783 0.02595,-0.07786 0.07786,0.02595 -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4484\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.429259,34.471986 q 0,-0.02595 0.02595,-0.05191 0,-0.02595 0,-0.05191 h 0.103814 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 l -0.233583,0.155722 z\"\r\n"
					+ "           id=\"path4482\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.412369,40.337522 h 0.07786 v 0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4480\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.749767,40.077985 h 0.07786 v 0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4478\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.256647,40.311569 q -0.02595,0.07786 -0.02595,0.28549 L 30.97116,40.285615 h 0.129769 z\"\r\n"
					+ "           id=\"path4476\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.363592,36.366606 q -0.02595,0.05191 -0.02595,0.181676 L 27.00024,36.314699 Z\"\r\n"
					+ "           id=\"path4474\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 25.884231,40.000124 h -0.07786 v -0.07786 h 0.07786 z\"\r\n"
					+ "           id=\"path4472\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.426129,39.818448 v 0.07786 l -0.07786,-0.02595 v -0.05191 z\"\r\n"
					+ "           id=\"path4470\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.140638,39.584865 v 0.103815 l -0.02595,0.155722 -0.103815,-0.05191 -0.05191,-0.07786 z\"\r\n"
					+ "           id=\"path4468\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.80324,39.68868 0.259537,0.337398 0.155722,-0.129769 0.311444,0.41526 q 0,0.155722 -0.07786,0.259537 -0.129768,-0.285491 -0.311444,-0.41526 l -0.441213,0.337399 -0.233583,-0.181676 q 0.129768,0 0.259537,-0.02595 0.129768,-0.02595 0.259537,-0.05191 -0.05191,-0.129769 -0.155723,-0.20763 -0.103814,-0.103815 -0.207629,-0.181676 z\"\r\n"
					+ "           id=\"path4466\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.949898,38.520763 v 0.07786 l -0.07786,-0.02595 v -0.05191 z\"\r\n"
					+ "           id=\"path4464\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.559027,36.444467 q 0.103815,0.103815 0.20763,0.181676 0.103815,0.07786 0.233583,0.155723 l -0.02595,0.129768 v 0.07786 l -0.233584,-0.259537 -0.28549,-0.07786 -0.181676,0.02595 z\"\r\n"
					+ "           id=\"path4462\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.078101,36.833773 q 0.02595,-0.05191 0.02595,-0.181676 h 0.103815 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 z\"\r\n"
					+ "           id=\"path4460\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.247583,36.704004 v 0.07786 l -0.02595,0.103814 Q 26.143773,36.704002 25.98805,36.54828 Z\"\r\n"
					+ "           id=\"path4458\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.794176,37.508569 0.07786,0.02595 -0.02595,0.05191 h -0.05191 z\"\r\n"
					+ "           id=\"path4456\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.206305,39.948217 0.207629,0.285491 -0.07786,0.233583 q -0.05191,-0.05191 -0.103815,-0.155722 -0.02595,-0.129769 -0.02595,-0.181676 z\"\r\n"
					+ "           id=\"path4454\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.012435,39.247467 0.02595,-0.07786 0.07786,0.02595 -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4452\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.920814,30.812515 q -0.07786,0.02595 -0.233583,0.02595 l 0.441213,0.20763 q -0.259537,0.07786 -0.41526,0.181676 l 0.285491,0.181676 q -0.07786,0.155722 -0.181676,0.259537 l -0.233583,-0.155722 q 0,-0.363352 0.07786,-0.752658 l 0.259537,-0.181676 z\"\r\n"
					+ "           id=\"path4450\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.529943,33.122394 q 0.07786,-0.07786 0.155723,-0.155722 0.07786,-0.07786 0.181675,-0.129769 v 0.20763 l 0.259537,-0.02595 q 0.07786,0.155722 0.07786,0.311445 l 0.233583,-0.155723 -0.05191,0.181676 q 0,0.129769 0.02595,0.285491 0.02595,0.155722 0.103815,0.259537 l 0.181676,-0.129769 -0.20763,-0.103814 0.103815,-0.259537 0.233583,0.519074 0.233584,-0.02595 0.02595,0.103815 -0.103815,0.207629 -0.07786,0.05191 v -0.05191 l 0.02595,-0.129768 -0.908379,-0.259537 q 0.207629,0 0.28549,-0.02595 l -0.155722,-0.415259 -0.389305,-0.103815 0.233583,-0.155722 z\"\r\n"
					+ "           id=\"path4448\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.358897,36.418514 h 0.49312 l -0.02595,0.181676 0.311445,0.103814 v -0.129768 l -0.02595,-0.129769 -0.20763,0.155723 0.05191,-0.20763 q -0.20763,0 -0.389306,-0.07786 l 0.129769,-0.49312 -0.622889,-0.05191 q 0,0.155723 0.07786,0.363352 0.103815,0.181676 0.20763,0.285491 z\"\r\n"
					+ "           id=\"path4446\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.424563,40.1818 q 0.181676,0.07786 0.285491,0.233583 l -0.129768,0.103815 -0.129769,-0.181676 z\"\r\n"
					+ "           id=\"path4444\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.203174,39.792501 -0.155722,-0.02595 0.02595,-0.155722 0.129768,0.02595 z\"\r\n"
					+ "           id=\"path4442\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.813869,38.416955 v 0.07786 l -0.07786,-0.02595 v -0.05191 z\"\r\n"
					+ "           id=\"path4440\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.424563,37.897881 v 0.545027 l -0.129768,-0.07786 h -0.20763 l -0.07786,0.05191 q 0.155722,0.129768 0.311445,0.259537 0.181675,0.129768 0.311444,0.28549 0.129768,-0.02595 0.233583,-0.07786 0.103815,-0.07786 0.181676,-0.155722 -0.207629,-0.02595 -0.389305,0 -0.07786,-0.441213 -0.233584,-0.830518 z\"\r\n"
					+ "           id=\"path4438\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.462711,38.001695 -0.259537,-0.155722 0.233584,0.337398 0.02595,-0.07786 z\"\r\n"
					+ "           id=\"path4436\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.229128,38.261232 -0.02595,0.103815 v 0.103815 l 0.233584,-0.181676 -0.103815,-0.02595 z\"\r\n"
					+ "           id=\"path4434\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.685666,36.652103 0.181675,0.233583 -0.181675,0.155723 0.337398,0.259537 -0.05191,0.05191 q -0.103815,0.02595 -0.207629,0.05191 -0.07786,0.02595 -0.181676,0.02595 l 0.103815,-0.259537 -0.181676,-0.07786 z\"\r\n"
					+ "           id=\"path4432\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.142203,33.61552 v -0.07786 h 0.07786 v 0.07786 z\"\r\n"
					+ "           id=\"path4430\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.618434,38.31314 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 l -0.233583,0.155722 q 0.02595,-0.05191 0.02595,-0.181676 z\"\r\n"
					+ "           id=\"path4428\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.516184,34.238409 v -0.07786 h 0.07786 v 0.07786 z\"\r\n"
					+ "           id=\"path4426\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.960527,34.186499 q -0.181676,0.07786 -0.285491,0.233583 0.20763,0.20763 0.441213,0.337398 0.233584,0.103815 0.493121,0.233583 l -0.311445,0.20763 v -0.181676 q -0.311444,0 -0.648842,-0.129768 0.103815,-0.07786 0.259537,-0.07786 -0.20763,-0.155722 -0.337398,-0.181675 0.05191,-0.129769 0.103814,-0.233584 0.02595,-0.103815 0.07786,-0.233583 -0.103815,-0.103815 -0.181676,-0.259537 l 0.389305,0.181676 z\"\r\n"
					+ "           id=\"path4424\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.322314,33.797193 0.155722,0.155722 -0.155722,-0.02595 -0.155722,-0.155723 z\"\r\n"
					+ "           id=\"path4422\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.178786,34.290313 v 0.181676 l -0.337398,-0.207629 -0.181676,0.28549 -0.103815,-0.363351 q -0.129768,0.207629 -0.285491,0.363351 l -0.311444,-0.648842 q -0.103815,0.05191 -0.181676,0.129768 -0.07786,0.07786 -0.181676,0.129769 l -0.129768,-0.181676 q 0.103814,-0.05191 0.181676,-0.103815 0.103814,-0.07786 0.233583,-0.129768 l 0.415259,0.415259 q 0.181676,0 0.337398,-0.07786 -0.02595,-0.103815 -0.02595,-0.363352 l 0.129769,0.181676 0.285491,0.181676 0.129768,0.02595 z\"\r\n"
					+ "           id=\"path4420\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.868907,34.238403 v 0.07786 l -0.07786,-0.02595 v -0.05191 z\"\r\n"
					+ "           id=\"path4418\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.90862,34.446033 q 0.129768,0 0.259537,0 0.103815,0 0.233583,0.02595 v 0.07786 z\"\r\n"
					+ "           id=\"path4416\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.531508,31.798755 q -0.02595,0.05191 -0.02595,0.181676 -0.155722,-0.155722 -0.233583,-0.337398 z\"\r\n"
					+ "           id=\"path4414\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.882666,32.499505 v 0.02595 h -0.02595 z\"\r\n"
					+ "           id=\"path4412\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.920814,31.824709 v -0.07786 l 0.07786,0.02595 v 0.07786 z\"\r\n"
					+ "           id=\"path4410\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 28.012435,32.525459 27.90862,32.836903 27.88267,32.655227 V 32.525459 Z\"\r\n"
					+ "           id=\"path4408\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.972721,32.39569 q -0.02595,0.05191 -0.02595,0.181676 -0.07786,-0.181676 -0.233584,-0.337398 z\"\r\n"
					+ "           id=\"path4406\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.439888,32.032339 0.181676,-0.129769 q 0.07786,0.07786 0.07786,0.181676 z\"\r\n"
					+ "           id=\"path4404\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.232258,35.769671 q 0,0.181676 0.07786,0.285491 0.285491,0.05191 0.519074,0.155722 0.259537,0.07786 0.545028,0.155722 l 0.155722,0.337398 -0.648842,-0.415259 h -0.389306 q -0.233583,-0.259537 -0.49312,-0.49312 l 0.103815,-0.02595 z\"\r\n"
					+ "           id=\"path4402\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.424563,37.352847 0.129769,0.05191 q 0.285491,0.181676 0.363352,0.467167 l 0.233583,-0.181676 q -0.233583,-0.259537 -0.49312,-0.49312 z\"\r\n"
					+ "           id=\"path4400\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.386415,36.704004 h 0.07786 l 0.02595,-0.05191 -0.07786,-0.02595 z\"\r\n"
					+ "           id=\"path4398\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 34.189415,37.897875 33.903924,37.79406 q 0,0.05191 -0.02595,0.103815 -0.02595,0.05191 -0.05191,0.103814 0.155722,0.155723 0.233583,0.259537 l -0.155722,-0.337398 q 0.07786,-0.02595 0.285491,-0.02595 z\"\r\n"
					+ "           id=\"path4396\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.929878,38.442902 q -0.02595,0 -0.05191,0 -0.02595,-0.02595 -0.05191,-0.02595 h -0.07786 l -0.05191,0.181676 z\"\r\n"
					+ "           id=\"path4394\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.644387,36.937588 v -0.05191 q -0.103815,-0.02595 -0.311444,-0.02595 0.103815,0.07786 0.311444,0.07786 z\"\r\n"
					+ "           id=\"path4392\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.737573,35.562042 -0.02595,0.07786 h 0.07786 v -0.07786 z\"\r\n"
					+ "           id=\"path4390\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.282601,36.470421 v -0.07786 l -0.07786,-0.02595 v 0.07786 z\"\r\n"
					+ "           id=\"path4388\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.543703,35.847532 0.02595,-0.07786 h 0.07786 l -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4386\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.360462,35.639903 h 0.155722 l 0.05191,-0.05191 q -0.181676,-0.207629 -0.441213,-0.389305 0.02595,0.129768 0.07786,0.233583 0.07786,0.103815 0.155722,0.20763 z\"\r\n"
					+ "           id=\"path4384\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.50399,36.107069 q 0.02595,-0.05191 0.02595,-0.181675 h 0.103815 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 z\"\r\n"
					+ "           id=\"path4382\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.763527,33.537653 q -0.233584,0.07786 -0.493121,0.07786 -0.103814,0 -0.311444,-0.05191 l -0.05191,-0.07786 q 0.103814,0.02595 0.207629,0.02595 0.103815,0 0.20763,0 L 30.08873,33.200255 q 0.181676,0.05191 0.337399,0.07786 0.181675,0.02595 0.337398,0.129769 z\"\r\n"
					+ "           id=\"path4380\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.919249,35.068921 q 0,0.233584 -0.103815,0.363352 -0.259537,-0.155722 -0.415259,-0.415259 l -0.233583,0.181676 q 0.05191,0.20763 0.07786,0.415259 0.05191,0.181676 0.129768,0.389306 l -0.337398,-0.20763 -0.07786,0.20763 -0.155722,-0.41526 h 0.181676 l 0.181676,0.02595 -0.103815,-0.519074 -0.233584,-0.155722 q 0.20763,-0.103815 0.493121,-0.155722 0.05191,0.103815 0.129768,0.181676 0.07786,0.07786 0.155722,0.155722 l 0.103815,-0.233583 q -0.103815,-0.05191 -0.207629,-0.129769 -0.103815,-0.07786 -0.181676,-0.155722 l 0.49312,0.155722 -0.07786,0.233583 z\"\r\n"
					+ "           id=\"path4378\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.517749,33.355977 0.02595,-0.07786 h 0.07786 l -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4376\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.244453,32.473552 h 0.07786 v 0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4374\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.282601,34.861292 0.49312,0.05191 v 0.129769 q 0,0.05191 -0.05191,0.155722 l -0.07786,0.07786 -0.103814,-0.363352 -0.103815,0.233584 z\"\r\n"
					+ "           id=\"path4372\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.713184,33.849098 q 0,0.207629 -0.07786,0.363351 -0.259537,0 -0.389305,-0.103814 l 0.28549,-0.311445 z\"\r\n"
					+ "           id=\"path4370\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.619999,34.627709 h 0.07786 l -0.02595,0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4368\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.476471,35.562042 v -0.05191 q -0.103815,-0.02595 -0.311445,-0.02595 0.103815,0.07786 0.311445,0.07786 z\"\r\n"
					+ "           id=\"path4366\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.659712,36.158977 h -0.07786 l -0.05191,0.363352 0.363352,-0.05191 q -0.07786,-0.155722 -0.233583,-0.311444 z\"\r\n"
					+ "           id=\"path4364\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 31.49023,36.496375 V 35.89944 h -0.103815 l -0.103814,0.02595 z\"\r\n"
					+ "           id=\"path4362\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.827628,34.653663 q 0,-0.05191 0.02595,-0.129768 0.05191,-0.103815 0.07786,-0.155722 l 0.103815,-0.05191 -0.103815,0.311445 q 0.155722,-0.103815 0.415259,-0.181676 l 0.181676,0.259537 -0.155722,0.259537 -0.545028,-0.103815 z\"\r\n"
					+ "           id=\"path4360\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.957397,35.172737 0.103815,0.155722 -0.337399,0.20763 -0.103814,-0.129768 z\"\r\n"
					+ "           id=\"path4358\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.050582,32.551414 h 0.103815 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 l -0.233583,0.155723 v -0.103815 z\"\r\n"
					+ "           id=\"path4356\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.751332,32.629274 q 0.02595,0 0.02595,0.02595 0.02595,0 0.05191,0 v 0.103815 q 0,0.02595 -0.02595,0.05191 0,0 0,0.02595 L 29.64752,32.62927 Z\"\r\n"
					+ "           id=\"path4354\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.336073,32.1102 q -0.155722,-0.02595 -0.311444,-0.155722 l 0.155722,-0.129769 0.103815,0.129769 z\"\r\n"
					+ "           id=\"path4352\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.958965,32.421644 0.07786,0.02595 -0.02595,0.07786 -0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path4350\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.180354,32.707135 0.20763,0.285491 0.103814,-0.259537 0.02595,0.129768 -0.02595,0.181676 Q 29.258215,32.992623 29.1544,32.888811 v -0.07786 z\"\r\n"
					+ "           id=\"path4348\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.65815,35.458227 0.415259,-0.07786 -0.20763,-0.103815 -0.259537,-0.02595 z\"\r\n"
					+ "           id=\"path4346\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.165029,35.977301 0.233584,0.05191 0.02595,-0.129768 v -0.103815 z\"\r\n"
					+ "           id=\"path4344\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.983353,36.081116 v 0.441213 l 0.129769,-0.441213 -0.155722,-0.233584 -0.311445,0.389306 z\"\r\n"
					+ "           id=\"path4342\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.268844,35.717764 q -0.02595,0 -0.05191,0 0,-0.02595 -0.02595,-0.02595 h -0.103815 q 0,0.02595 -0.02595,0.05191 0,0 0,0.02595 v 0.103815 z\"\r\n"
					+ "           id=\"path4340\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.281038,35.613949 0.02595,0.129769 0.181676,-0.129769 -0.07786,-0.103815 -0.103815,-0.05191 z\"\r\n"
					+ "           id=\"path4338\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.384853,39.169606 0.07786,0.02595 v -0.07786 l -0.05191,-0.02595 z\"\r\n"
					+ "           id=\"path4336\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.021501,39.377235 q -0.181675,-0.155722 -0.28549,-0.363351 v 0.181676 q 0,0.05191 0.05191,0.207629 0.07786,0.155722 0.129769,0.20763 z\"\r\n"
					+ "           id=\"path4334\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.384853,38.806254 q 0.07786,0.07786 0.233584,0.129769 0.181675,-0.103815 0.363351,-0.285491 -0.337398,0.05191 -0.596935,0.155722 z\"\r\n"
					+ "           id=\"path4332\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.490233,39.48105 -0.02595,-0.129768 0.07786,-0.181676 0.155722,0.02595 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path4330\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.818567,35.536088 v -0.07786 h 0.07786 l -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4328\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.636891,35.587998 -0.02595,0.155723 -0.05191,-0.103815 0.02595,-0.155722 z\"\r\n"
					+ "           id=\"path4326\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 25.183484,35.302508 -0.02595,0.07786 h -0.05191 v -0.07786 z\"\r\n"
					+ "           id=\"path4324\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 25.546836,35.95135 Q 25.36516,35.795628 25.261345,35.587998 l 0.07786,-0.07786 h 0.129768 z\"\r\n"
					+ "           id=\"path4322\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.039956,35.042971 0.02595,-0.07786 0.05191,0.02595 v 0.07786 z\"\r\n"
					+ "           id=\"path4320\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.713187,35.354415 q -0.155722,0 -0.259537,-0.07786 l 0.181676,-0.129768 0.05191,0.07786 z\"\r\n"
					+ "           id=\"path4318\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.76666,35.899443 0.02595,-0.155722 q 0.103814,0.05191 0.155722,0.155722 l -0.181676,0.129768 z\"\r\n"
					+ "           id=\"path4316\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.46741,35.691813 -0.02595,0.07786 h -0.05191 v -0.07786 z\"\r\n"
					+ "           id=\"path4314\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.220067,35.95135 -0.207629,-0.467166 h 0.103814 q 0.20763,0.103814 0.285491,0.207629 z\"\r\n"
					+ "           id=\"path4312\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.012438,35.89944 0.02595,0.103815 -0.28549,-0.05191 0.181675,-0.155722 z\"\r\n"
					+ "           id=\"path4310\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.675039,35.795625 v 0.07786 h -0.07786 v -0.07786 z\"\r\n"
					+ "           id=\"path4308\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.117817,33.745283 -0.07786,0.05191 -0.155722,-0.181676 H 26.014 l 0.155722,0.02595 z\"\r\n"
					+ "           id=\"path4306\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.97429,33.745283 q -0.02595,0.05191 -0.02595,0.181676 l -0.337398,-0.233584 z\"\r\n"
					+ "           id=\"path4304\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.00024,33.070487 q -0.181676,0 -0.28549,-0.07786 l 0.129768,-0.02595 q 0.05191,0 0.103815,0.02595 0.02595,0 0.07786,0 z\"\r\n"
					+ "           id=\"path4302\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.155962,33.174305 q 0.05191,0.02595 0.181676,0.02595 l -0.207629,0.181676 v -0.103815 q 0,-0.02595 0.02595,-0.05191 0,-0.02595 0,-0.05191 z\"\r\n"
					+ "           id=\"path4300\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.455213,33.148355 v 0.07786 l -0.07786,-0.02595 v -0.07786 z\"\r\n"
					+ "           id=\"path4298\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.400175,38.987937 -0.07786,-0.02595 v -0.07786 l 0.07786,0.02595 z\"\r\n"
					+ "           id=\"path4296\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.647518,39.013887 h -0.155723 l -0.129768,-0.05191 0.181676,-0.129768 0.07786,0.07786 z\"\r\n"
					+ "           id=\"path4294\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.777286,39.117702 q -0.103815,0.129768 -0.233583,0.233583 L 29.439888,39.19556 Q 29.59561,39.1177 29.777286,39.1177 Z\"\r\n"
					+ "           id=\"path4292\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.920814,39.299377 h 0.07786 l -0.02595,0.07786 h -0.05191 z\"\r\n"
					+ "           id=\"path4290\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.192545,38.442905 0.02595,0.20763 -0.05191,0.129768 q -0.07786,-0.103814 -0.07786,-0.28549 l 0.05191,-0.129769 z\"\r\n"
					+ "           id=\"path4288\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.50399,39.377238 q 0.129768,0.155723 0.233583,0.41526 l -0.20763,-0.05191 -0.02595,-0.181676 z\"\r\n"
					+ "           id=\"path4286\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.50399,39.922266 h 0.103814 q 0.02595,0 0.05191,0.02595 0.02595,0 0.05191,0 -0.02595,0.05191 -0.02595,0.181676 z\"\r\n"
					+ "           id=\"path4284\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.739138,39.688683 q 0.181676,-0.129769 0.389306,-0.129769 l 0.103814,0.05191 v 0.07786 l -0.02595,0.103815 -0.155723,-0.20763 q 0,0.02595 -0.02595,0.129769 -0.02595,0.07786 -0.05191,0.07786 -0.02595,0 -0.103814,-0.02595 -0.07786,-0.05191 -0.129769,-0.07786 z\"\r\n"
					+ "           id=\"path4282\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.256647,39.325331 v -0.07786 l 0.07786,0.02595 v 0.07786 z\"\r\n"
					+ "           id=\"path4280\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.374221,39.195563 -0.02595,0.07786 h -0.07786 l 0.02595,-0.07786 z\"\r\n"
					+ "           id=\"path4278\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.362027,36.418517 q -0.02595,0.05191 -0.05191,0.103815 -0.02595,0.05191 -0.02595,0.103814 -0.155722,-0.02595 -0.311445,0 -0.155722,0 -0.311444,-0.05191 l 0.363352,-0.363352 q 0.02595,0.07786 0.02595,0.233583 h 0.155723 z\"\r\n"
					+ "           id=\"path4276\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.479601,38.728396 q -0.02595,0.181676 -0.129768,0.363352 0.05191,-0.02595 0.129768,-0.05191 0.07786,-0.02595 0.155722,-0.02595 v 0.103815 q 0,0.07786 -0.05191,0.181675 l -0.07786,0.05191 -0.389306,-0.363352 q 0.155723,0 0.20763,-0.02595 l -0.103815,-0.259537 h 0.129769 z\"\r\n"
					+ "           id=\"path4274\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.284166,38.910072 -0.129769,0.07786 -0.129768,-0.155722 q 0.103815,-0.05191 0.155722,-0.05191 h 0.20763 z\"\r\n"
					+ "           id=\"path4272\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.816999,38.858162 q -0.02595,0.05191 -0.02595,0.181675 l -0.233583,-0.337398 z\"\r\n"
					+ "           id=\"path4270\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.557462,38.235273 q 0.129769,0.02595 0.259537,0.155722 l -0.181676,0.07786 h -0.155722 z\"\r\n"
					+ "           id=\"path4268\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.90862,38.572671 h 0.103815 q 0.02595,0 0.05191,0.02595 0,0 0.02595,0 v 0.103814 q 0,0.02595 0,0.05191 -0.02595,0 -0.02595,0.02595 z\"\r\n"
					+ "           id=\"path4266\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.375786,38.390995 h -0.07786 v -0.07786 h 0.07786 z\"\r\n"
					+ "           id=\"path4264\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 24.275102,38.053597 h 0.05191 v 0.07786 l -0.07786,-0.02595 z\"\r\n"
					+ "           id=\"path4262\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 25.780416,32.39569 25.59874,32.265922 h 0.259537 q 0.05191,0 0.129769,0.02595 0.07786,0.02595 0.129768,0.05191 l -0.05191,0.103815 z\"\r\n"
					+ "           id=\"path4260\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.169722,31.980431 h 0.07786 v 0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4258\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 25.572787,33.018579 q -0.02595,0.07786 -0.129769,0.181676 l -0.129768,-0.181676 0.103815,-0.02595 z\"\r\n"
					+ "           id=\"path4256\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.50712,32.291876 0.20763,0.07786 0.129768,0.155722 -0.181676,-0.05191 z\"\r\n"
					+ "           id=\"path4254\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.074971,38.98793 0.752657,-0.103815 0.20763,0.415259 0.155722,0.02595 h 0.181676 q -0.155722,-0.207629 -0.311444,-0.389305 -0.129769,-0.20763 -0.20763,-0.441213 l 0.49312,0.363352 0.155722,0.519073 -0.389305,0.233584 q 0,-0.07786 -0.02595,-0.155722 -0.02595,-0.07786 -0.05191,-0.129769 l -0.20763,0.02595 -0.103815,-0.311445 z\"\r\n"
					+ "           id=\"path4252\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.282601,37.09331 q 0,0.129768 -0.05191,0.233583 -0.02595,0.07786 -0.07786,0.181676 0,-0.285491 0.129769,-0.49312 z\"\r\n"
					+ "           id=\"path4250\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.049017,37.482615 v 0.07786 h -0.07786 l 0.02595,-0.07786 z\"\r\n"
					+ "           id=\"path4248\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.89173,36.314699 -0.49312,-0.103815 q 0.129768,0 0.233583,0 0.129768,0 0.259537,0.02595 z\"\r\n"
					+ "           id=\"path4246\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 30.50399,37.352847 q -0.07786,0.155722 -0.181676,0.259537 l -0.155722,-0.337398 0.181675,-0.103815 z\"\r\n"
					+ "           id=\"path4244\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.128444,38.339088 q 0.181675,0.07786 0.415259,0.07786 0.129768,-0.02595 0.28549,-0.181676 -0.02595,0.103815 -0.129768,0.311444 0.155722,0.05191 0.233583,0.129769 -0.207629,-0.02595 -0.415259,-0.07786 -0.181676,-0.07786 -0.389305,-0.129769 l -0.05191,-0.155722 0.05191,-0.233583 0.07786,-0.05191 -0.20763,-0.285491 q -0.07786,0.20763 -0.07786,0.41526 l -0.05191,-0.181676 0.02595,-0.233584 0.233584,0.02595 0.155722,0.103815 z\"\r\n"
					+ "           id=\"path4242\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.362027,38.235273 0.02595,-0.07786 h 0.07786 l -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4240\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.061212,36.470421 0.311444,0.596935 q 0.07786,-0.07786 0.07786,-0.181676 l 0.129769,0.181676 0.28549,-0.207629 Q 32.6841,36.807819 32.424563,36.704004 32.19098,36.60019 32.061212,36.470421 Z\"\r\n"
					+ "           id=\"path4238\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 33.306989,37.664291 h 0.07786 v -0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4236\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.294795,37.352847 h -0.07786 l -0.02595,0.07786 h 0.07786 z\"\r\n"
					+ "           id=\"path4234\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 31.957397,37.534523 q 0.155722,0.155722 0.441213,0.207629 -0.155723,-0.155722 -0.441213,-0.207629 z\"\r\n"
					+ "           id=\"path4232\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.476471,37.534523 v 0.07786 h 0.07786 v -0.05191 z\"\r\n"
					+ "           id=\"path4230\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 32.710054,38.105504 h 0.07786 v -0.07786 h -0.07786 z\"\r\n"
					+ "           id=\"path4228\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.349833,31.461357 0.02595,-0.07786 h 0.07786 l -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4226\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 26.610935,31.980431 q 0.311444,0 0.648842,0.129769 l -0.02595,0.103815 q -0.181676,0 -0.311445,-0.07786 -0.155722,-0.07786 -0.311444,-0.155722 z\"\r\n"
					+ "           id=\"path4224\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.571222,31.798755 v -0.07786 h 0.07786 l -0.02595,0.07786 z\"\r\n"
					+ "           id=\"path4222\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.052148,33.407885 0.207629,0.259537 -0.07786,0.07786 -0.155722,-0.02595 -0.02595,-0.20763 z\"\r\n"
					+ "           id=\"path4220\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.725379,35.172736 q -0.05191,-0.05191 -0.129769,-0.155722 -0.05191,-0.103815 -0.05191,-0.20763 0.05191,-0.129768 0.103815,-0.259537 0.07786,-0.129768 0.129768,-0.259537 l 0.129769,-0.103814 0.129768,0.181676 q -0.07786,0.02595 -0.259537,0.02595 l 0.155722,0.181676 -0.259537,0.181676 z\"\r\n"
					+ "           id=\"path4218\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.465842,35.458227 -0.07786,-0.02595 v -0.05191 h 0.07786 z\"\r\n"
					+ "           id=\"path4216\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 29.362027,34.082681 0.07786,0.05191 q -0.233583,0.07786 -0.415259,0.05191 l 0.103815,-0.07786 z\"\r\n"
					+ "           id=\"path4214\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"M 27.337638,34.809384 26.948333,34.70557 q -0.07786,-0.285491 -0.233583,-0.519074 l 0.337398,-0.155723 q 0.233583,0.155723 0.389305,0.389306 z\"\r\n"
					+ "           id=\"path4212\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 28.739138,32.629274 q -0.05191,0.07786 -0.155722,0.181676 l -0.129769,-0.181676 0.129769,-0.02595 z\"\r\n"
					+ "           id=\"path4210\" />\r\n"
					+ "        <path\r\n"
					+ "           style=\"font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas'\"\r\n"
					+ "           d=\"m 27.856712,33.330024 h -0.05191 v -0.07786 l 0.07786,0.02595 z\"\r\n"
					+ "           id=\"path4186\" />\r\n"
					+ "      </g>\r\n"
					+ "    </g>\r\n"
					+ "  </g>\r\n"
					+ "</svg>\r\n";
		}

		public static String NoteD() {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
					+ "<!-- Created with Inkscape (http://www.inkscape.org/) -->\r\n"
					+ "\r\n"
					+ "<svg\r\n"
					+ "   width=\"38.443188mm\"\r\n"
					+ "   height=\"38.443161mm\"\r\n"
					+ "   viewBox=\"0 0 38.443188 38.443161\"\r\n"
					+ "   version=\"1.1\"\r\n"
					+ "   id=\"svg5\"\r\n"
					+ "   inkscape:version=\"1.1.1 (3bf5ae0d25, 2021-09-20)\"\r\n"
					+ "   sodipodi:docname=\"NoteD.svg\"\r\n"
					+ "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n"
					+ "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n"
					+ "   xmlns=\"http://www.w3.org/2000/svg\"\r\n"
					+ "   xmlns:svg=\"http://www.w3.org/2000/svg\">\r\n"
					+ "  <sodipodi:namedview\r\n"
					+ "     id=\"namedview7\"\r\n"
					+ "     pagecolor=\"#ffffff\"\r\n"
					+ "     bordercolor=\"#666666\"\r\n"
					+ "     borderopacity=\"1.0\"\r\n"
					+ "     inkscape:pageshadow=\"2\"\r\n"
					+ "     inkscape:pageopacity=\"0.0\"\r\n"
					+ "     inkscape:pagecheckerboard=\"0\"\r\n"
					+ "     inkscape:document-units=\"mm\"\r\n"
					+ "     showgrid=\"false\"\r\n"
					+ "     showguides=\"true\"\r\n"
					+ "     inkscape:guide-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox-edge-midpoints=\"true\"\r\n"
					+ "     inkscape:bbox-paths=\"true\"\r\n"
					+ "     inkscape:snap-nodes=\"false\"\r\n"
					+ "     inkscape:snap-bbox-midpoints=\"true\"\r\n"
					+ "     inkscape:snap-global=\"false\"\r\n"
					+ "     fit-margin-top=\"0\"\r\n"
					+ "     fit-margin-left=\"0\"\r\n"
					+ "     fit-margin-right=\"0\"\r\n"
					+ "     fit-margin-bottom=\"0\"\r\n"
					+ "     inkscape:zoom=\"7.8218088\"\r\n"
					+ "     inkscape:cx=\"91.347158\"\r\n"
					+ "     inkscape:cy=\"68.20673\"\r\n"
					+ "     inkscape:window-width=\"1920\"\r\n"
					+ "     inkscape:window-height=\"1009\"\r\n"
					+ "     inkscape:window-x=\"-8\"\r\n"
					+ "     inkscape:window-y=\"-8\"\r\n"
					+ "     inkscape:window-maximized=\"1\"\r\n"
					+ "     inkscape:current-layer=\"layer1\">\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"1,0\"\r\n"
					+ "       id=\"guide12970\" />\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"0,-1\"\r\n"
					+ "       id=\"guide12972\" />\r\n"
					+ "  </sodipodi:namedview>\r\n"
					+ "  <defs\r\n"
					+ "     id=\"defs2\" />\r\n"
					+ "  <g\r\n"
					+ "     inkscape:label=\"Calque 1\"\r\n"
					+ "     inkscape:groupmode=\"layer\"\r\n"
					+ "     id=\"layer1\"\r\n"
					+ "     transform=\"translate(-19.656191,-12.181169)\">\r\n"
					+ "    <path\r\n"
					+ "       id=\"path12996\"\r\n"
					+ "       style=\"font-variation-settings:normal;vector-effect:none;fill:#0000ff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.999999;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;-inkscape-stroke:none;stop-color:#000000\"\r\n"
					+ "       inkscape:transform-center-x=\"0.041927404\"\r\n"
					+ "       inkscape:transform-center-y=\"-13.470202\"\r\n"
					+ "       d=\"m 38.817552,12.181169 -1.798582,3.127294 -0.250675,0.435907 -0.34885,-0.348849 -2.552465,-2.549995 -0.932324,3.485405 -0.133983,0.500737 -0.440846,-0.254382 -3.124823,-1.802284 0.0013,3.607656 v 0.518643 l -0.490858,-0.131513 -3.485405,-0.932322 0.93541,3.48417 0.1346,0.500737 H 25.82067 l -3.607657,0.0019 1.805372,3.12359 0.259321,0.448873 -0.491476,0.131513 -3.48417,0.93541 2.551848,2.549995 0.366754,0.366137 -0.440846,0.255 -3.12359,1.804753 3.125442,1.802903 0.448873,0.258705 -0.359345,0.359962 -2.549995,2.552465 3.484787,0.932323 0.501355,0.133984 -0.254382,0.440846 -1.802902,3.124823 3.607656,-0.0013 h 0.518644 l -0.131514,0.490857 -0.932322,3.485405 3.48417,-0.935408 0.501356,-0.1346 v 0.509379 l 0.0013,3.607657 3.12359,-1.80537 0.448872,-0.259324 0.132131,0.491477 0.934792,3.484172 2.549996,-2.551849 0.366756,-0.366756 0.25438,0.440846 1.804756,3.12359 1.802901,-3.125439 0.259324,-0.449492 0.359962,0.359962 2.551846,2.549996 0.932323,-3.484788 0.133983,-0.501356 0.440846,0.254383 3.124826,1.802904 -0.0013,-3.607657 V 43.9042 l 0.491477,0.131514 3.484788,0.932323 -0.934795,-3.484172 -0.1346,-0.501355 h 0.508766 l 3.607656,-0.0013 -1.804756,-3.12359 -0.259321,-0.448872 0.490858,-0.132131 3.484788,-0.934792 -2.552465,-2.549997 -0.366753,-0.366755 0.440846,-0.25438 3.12359,-1.804756 -3.124826,-1.802901 -0.449489,-0.259324 0.359962,-0.359961 2.549997,-2.551847 -3.485408,-0.932323 -0.500736,-0.133983 0.254382,-0.440847 1.802285,-3.124824 -3.607657,0.0012 h -0.518645 l 0.131515,-0.491476 0.932323,-3.484788 -3.484169,0.934793 -0.500739,0.1346 V 18.34556 l -0.0019,-3.607657 -3.12359,1.804755 -0.448873,0.259321 -0.131514,-0.490858 -0.935408,-3.484788 -2.549997,2.552465 -0.375398,0.376016 a 15.574866,15.762139 0 0 0 -0.0032,-6.32e-4 l -0.261793,-0.451343 z\" />\r\n"
					+ "    <ellipse\r\n"
					+ "       style=\"fill:#ffffff;stroke:#ff0000;stroke-width:1.012;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "       id=\"ellipse13122\"\r\n"
					+ "       cx=\"38.828659\"\r\n"
					+ "       cy=\"31.377256\"\r\n"
					+ "       rx=\"13.733081\"\r\n"
					+ "       ry=\"13.899035\" />\r\n"
					+ "    <g\r\n"
					+ "       aria-label=\"C\"\r\n"
					+ "       transform=\"scale(0.99914692,1.0008538)\"\r\n"
					+ "       id=\"text1481\"\r\n"
					+ "       style=\"font-size:25.9537px;line-height:1.25;fill:#e12aff;stroke:#000000;stroke-width:0.065\">\r\n"
					+ "      <g\r\n"
					+ "         aria-label=\"D\"\r\n"
					+ "         transform=\"scale(0.96011067,1.0415466)\"\r\n"
					+ "         id=\"text16764\"\r\n"
					+ "         style=\"font-size:25.1465px;font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas';fill:#ff2a71;stroke-width:0.08\">\r\n"
					+ "        <path\r\n"
					+ "           id=\"path17499\"\r\n"
					+ "           style=\"stroke-width:0.302362\"\r\n"
					+ "           d=\"M 47.208984 35.134766 L 47.208984 90.318359 L 47.845703 89.625 C 47.78492 89.426851 47.633753 89.195841 47.390625 88.931641 C 47.8161 88.931641 48.21148 88.965191 48.576172 89.03125 L 49.851562 89.03125 L 50.035156 89.130859 C 50.035156 89.262959 49.882036 89.460409 49.578125 89.724609 C 49.33499 89.92275 49.152807 90.055035 49.03125 90.121094 L 48.392578 90.318359 L 49.123047 90.417969 L 49.943359 90.417969 C 50.125706 90.153771 50.338904 89.922761 50.582031 89.724609 L 50.945312 90.714844 L 50.308594 90.318359 L 50.308594 91.310547 C 50.916413 91.244488 51.58507 91.309661 52.314453 91.507812 L 52.496094 91.707031 L 51.494141 91.707031 L 51.857422 92.300781 L 49.943359 91.707031 C 50.125706 91.971231 50.459057 92.366131 50.945312 92.894531 L 50.671875 93.291016 L 50.490234 94.083984 L 50.582031 94.380859 L 50.582031 94.480469 C 50.399677 94.480469 50.246557 94.381745 50.125 94.183594 C 50.003439 93.985442 49.852269 93.918316 49.669922 93.984375 C 49.791503 94.314627 49.822509 94.646314 49.761719 94.976562 L 49.761719 95.966797 L 49.123047 95.570312 L 49.123047 96.560547 L 50.490234 96.363281 C 50.490234 95.900932 50.459228 95.569245 50.398438 95.371094 L 51.128906 95.371094 L 51.128906 95.867188 C 50.764215 96.395596 50.459925 96.72533 50.216797 96.857422 L 50.945312 97.650391 L 50.945312 96.759766 C 51.127657 97.023966 51.462959 97.286573 51.949219 97.550781 L 52.222656 97.550781 C 52.040309 97.088432 51.949219 96.561249 51.949219 95.966797 L 52.587891 95.867188 L 53.316406 95.867188 C 53.559532 95.801155 53.772731 95.834705 53.955078 95.966797 L 53.955078 97.650391 L 54.59375 96.857422 C 54.776095 97.187679 55.049371 97.418689 55.414062 97.550781 L 54.683594 98.443359 L 53.955078 97.75 L 53.955078 98.542969 C 54.258989 98.741118 54.532263 98.938567 54.775391 99.136719 C 55.079299 99.334878 55.383589 99.50072 55.6875 99.632812 L 55.595703 99.929688 L 55.140625 100.32617 C 54.836714 100.39223 54.56344 100.42383 54.320312 100.42383 L 53.589844 100.42383 L 53.589844 101.45508 C 53.711469 101.50794 53.833453 101.56042 53.955078 101.61328 C 54.258996 101.74538 54.472193 101.94478 54.59375 102.20898 C 54.715331 102.07688 54.744384 101.91104 54.683594 101.71289 C 54.683594 101.51474 54.745631 101.41602 54.867188 101.41602 C 54.927978 101.41602 55.019068 101.48119 55.140625 101.61328 C 55.262182 101.67931 55.353272 101.74644 55.414062 101.8125 L 55.414062 101.11914 L 55.140625 101.11914 L 54.775391 100.82031 L 54.775391 100.52344 C 55.079299 100.52344 55.383589 100.62216 55.6875 100.82031 L 56.052734 99.730469 L 57.509766 102.40625 L 57.054688 102.20898 L 56.052734 101.11914 L 55.6875 101.11914 L 56.416016 102.10938 C 56.659143 102.37358 56.93242 102.57103 57.236328 102.70312 L 58.421875 101.91016 C 58.482665 102.24041 58.573756 102.57209 58.695312 102.90234 C 58.877657 103.2326 59.059841 103.56233 59.242188 103.89258 C 59.485315 103.56233 59.760542 103.2642 60.064453 103 C 59.942882 102.93397 59.820775 102.86879 59.699219 102.80273 C 59.577648 102.73668 59.455541 102.70313 59.333984 102.70312 L 59.333984 100.91992 C 59.455555 101.18412 59.577662 101.44869 59.699219 101.71289 C 59.881571 101.91104 60.03274 102.10849 60.154297 102.30664 C 60.336641 102.37267 60.518825 102.4398 60.701172 102.50586 C 60.9443 102.57192 61.157497 102.60352 61.339844 102.60352 C 61.21828 102.20722 61.005083 101.91104 60.701172 101.71289 L 60.427734 101.71289 L 60.611328 101.41602 L 61.158203 101.11914 L 61.521484 101.11914 L 61.066406 101.51367 L 61.705078 102.30664 L 61.339844 103.39648 L 60.154297 103 L 60.154297 104.38867 L 58.96875 103.99219 L 58.96875 104.98242 L 60.337891 104.48633 C 60.641801 104.68448 60.944138 104.88388 61.248047 105.08203 C 61.612738 105.28018 61.948042 105.51119 62.251953 105.77539 L 62.251953 106.66602 L 62.251953 107.55859 L 61.066406 107.55859 L 61.066406 106.96289 L 61.339844 106.17188 C 61.096709 106.17187 60.914525 106.20347 60.792969 106.26953 L 60.792969 105.57617 L 59.152344 105.77539 L 58.96875 106.66602 C 59.151095 106.59996 59.395308 106.50123 59.699219 106.36914 C 60.003127 106.23704 60.154297 106.40289 60.154297 106.86523 C 60.154297 106.99733 60.094213 107.09605 59.972656 107.16211 C 59.851085 107.22817 59.728978 107.26172 59.607422 107.26172 C 59.546641 107.26172 59.424534 107.22817 59.242188 107.16211 C 59.059836 107.03002 58.908666 106.93129 58.787109 106.86523 L 58.421875 105.67578 L 59.060547 105.47852 L 58.332031 103.99219 C 57.724212 103.99219 57.206722 104.09091 56.78125 104.28906 L 55.779297 103.69336 L 55.414062 104.68555 C 55.778754 104.81765 56.112105 104.98154 56.416016 105.17969 C 56.780707 105.37784 57.116013 105.57724 57.419922 105.77539 C 57.176797 106.30379 56.963597 106.69869 56.78125 106.96289 L 56.326172 105.57617 L 55.503906 106.26953 L 54.228516 105.87305 C 54.167749 106.00515 54.198756 106.30327 54.320312 106.76562 C 54.441869 107.22798 54.53296 107.52416 54.59375 107.65625 L 53.681641 107.26172 C 53.620874 107.39383 53.56079 107.52416 53.5 107.65625 C 53.43921 107.78836 53.408203 107.92064 53.408203 108.05273 L 52.677734 107.0625 L 52.314453 107.75586 L 51.767578 108.25195 L 50.945312 106.66602 C 51.066876 106.46787 51.31109 106.23686 51.675781 105.97266 L 50.671875 106.36914 C 50.671875 106.03889 50.762966 105.70916 50.945312 105.37891 L 50.035156 105.37891 L 50.035156 106.17188 L 49.123047 104.88281 L 48.757812 105.2793 C 49.000938 105.60955 49.214137 105.93928 49.396484 106.26953 C 49.639612 106.53373 49.852809 106.83186 50.035156 107.16211 L 49.123047 108.05273 C 49.123047 107.45829 49.154053 106.99627 49.214844 106.66602 L 49.123047 106.26953 L 48.210938 106.26953 L 48.576172 107.35938 L 47.664062 106.56836 C 47.785626 106.03996 48.027887 105.47726 48.392578 104.88281 C 48.81805 104.22231 49.213433 103.72673 49.578125 103.39648 C 49.882036 103.39648 50.186326 103.49521 50.490234 103.69336 L 49.304688 102.40625 L 49.761719 101.91016 C 49.944066 102.37251 50.21734 102.7358 50.582031 103 C 50.88594 103.06606 51.310383 103.00089 51.857422 102.80273 C 52.404458 102.53853 52.830855 102.30753 53.134766 102.10938 L 52.130859 101.71289 L 51.767578 102.10938 L 50.671875 100.72266 L 50.671875 101.71289 C 50.367967 101.64684 50.06563 101.44939 49.761719 101.11914 C 50.308757 100.59075 50.855308 100.29262 51.402344 100.22656 C 51.645471 100.49076 51.858669 100.8205 52.041016 101.2168 L 52.404297 101.01953 L 52.769531 100.42383 L 52.769531 100.12695 L 51.857422 100.12695 C 51.735858 99.796704 51.584691 99.532136 51.402344 99.333984 C 51.280773 99.069784 51.158666 98.807169 51.037109 98.542969 L 50.855469 98.542969 C 51.037816 99.071369 51.128906 99.598553 51.128906 100.12695 C 50.885779 100.12695 50.612505 99.830777 50.308594 99.236328 C 50.065459 98.575828 49.883275 98.11381 49.761719 97.849609 C 49.944066 97.585409 50.035156 97.287283 50.035156 96.957031 L 49.214844 96.957031 L 47.208984 96.957031 L 47.208984 97.75 L 48.576172 98.146484 L 48.576172 97.550781 L 49.214844 97.550781 L 48.849609 97.947266 C 49.275082 98.277517 49.579372 98.707928 49.761719 99.236328 L 49.761719 99.632812 C 49.336246 99.632812 48.969926 99.46697 48.666016 99.136719 C 48.362107 98.806462 47.99774 98.575452 47.572266 98.443359 C 47.754613 98.773608 48.089916 99.069784 48.576172 99.333984 C 49.062427 99.598177 49.45781 99.764019 49.761719 99.830078 L 49.123047 100.72266 L 49.488281 101.51367 C 49.002026 101.51367 48.575629 101.61435 48.210938 101.8125 L 48.210938 102.40625 L 47.572266 102.00977 L 47.208984 102.50586 L 47.208984 105.2793 C 47.330548 105.60954 47.481716 105.80699 47.664062 105.87305 L 47.208984 106.07227 C 47.208984 106.53462 47.177978 107.06375 47.117188 107.6582 C 47.117188 108.1866 47.177272 108.64862 47.298828 109.04492 L 47.208984 108.94531 L 47.208984 110.03516 L 51.310547 110.03516 L 51.128906 109.83789 L 51.949219 108.8457 L 52.769531 109.24219 L 53.589844 108.54883 L 54.59375 108.44922 C 55.019222 108.44922 55.443666 108.51635 55.869141 108.64844 C 56.355396 108.7145 56.810856 108.81322 57.236328 108.94531 L 56.416016 109.53906 L 57.509766 109.53906 L 57.509766 110.03516 L 59.5625 110.03516 C 59.501718 110.00213 59.425158 109.93501 59.333984 109.83594 C 59.394775 109.57174 59.485865 109.30912 59.607422 109.04492 C 59.789776 108.78072 59.942897 108.54971 60.064453 108.35156 L 60.701172 107.95508 C 60.9443 108.08718 61.157497 108.25107 61.339844 108.44922 C 61.582969 108.58132 61.796169 108.74716 61.978516 108.94531 L 61.978516 110.03516 L 64.623047 110.03516 C 64.744603 109.83701 64.804688 109.63956 64.804688 109.44141 C 64.865454 109.17721 64.925538 108.9462 64.986328 108.74805 L 64.896484 108.44922 C 65.139612 108.71342 65.441949 109.01155 65.806641 109.3418 C 66.171332 109.606 66.506636 109.80345 66.810547 109.93555 L 66.445312 108.94531 C 66.68844 109.14346 66.99273 109.24219 67.357422 109.24219 L 67.357422 108.44922 L 66.263672 107.55859 C 67.053836 107.69069 67.722493 108.05399 68.269531 108.64844 L 68.269531 106.96289 L 67.539062 106.66602 C 67.903754 106.26973 68.23906 106.03872 68.542969 105.97266 L 68.542969 105.57617 L 68.542969 105.17969 C 68.299851 105.44389 68.146728 105.70846 68.085938 105.97266 C 67.84281 105.70846 67.540473 105.47745 67.175781 105.2793 L 68.269531 104.68555 L 68.269531 103.69336 L 69.271484 103.89258 C 69.271484 104.15678 69.362575 104.52007 69.544922 104.98242 C 69.78805 105.37872 70.032263 105.80718 70.275391 106.26953 C 70.579299 106.73188 70.883589 107.19585 71.1875 107.6582 C 71.491416 108.0545 71.702662 108.35068 71.824219 108.54883 L 74.287109 108.54883 C 73.983199 108.08648 73.587818 107.72318 73.101562 107.45898 C 72.615307 107.12874 72.128834 106.86417 71.642578 106.66602 C 72.00727 106.66602 72.280544 106.63442 72.462891 106.56836 L 72.462891 105.97266 L 71.916016 106.26953 L 72.097656 103.49609 L 72.462891 103.5957 C 73.009927 103.92595 73.40531 104.32085 73.648438 104.7832 C 73.344529 104.9153 73.100316 105.08115 72.917969 105.2793 L 73.283203 105.47852 L 73.648438 105.97266 L 73.648438 106.26953 L 72.644531 105.87305 C 72.826876 106.26935 73.040075 106.63264 73.283203 106.96289 C 73.587114 107.29314 73.89138 107.62483 74.195312 107.95508 L 74.742188 108.25195 L 74.650391 107.16211 L 76.566406 106.66602 L 76.566406 107.55859 L 75.746094 107.55859 L 76.109375 108.94531 C 76.474067 108.74716 76.838433 108.54971 77.203125 108.35156 C 77.567817 108.1534 77.9632 107.98756 78.388672 107.85547 L 78.388672 107.0625 C 78.571019 106.73225 78.815232 106.46768 79.119141 106.26953 L 77.933594 105.37891 L 78.935547 104.98242 L 79.300781 103.29883 L 80.304688 103.49609 L 80.304688 104.38867 L 81.763672 105.17969 C 82.249927 105.44389 82.734448 105.74202 83.220703 106.07227 L 82.400391 106.56836 C 82.278827 106.37021 82.036567 106.13725 81.671875 105.87305 L 81.125 106.66602 C 81.307347 107.06232 81.398438 107.45917 81.398438 107.85547 C 81.519999 107.92153 81.671169 108.02025 81.853516 108.15234 C 82.035853 108.2184 82.15796 108.25195 82.21875 108.25195 C 82.340307 108.25195 82.400391 108.18483 82.400391 108.05273 C 82.400391 107.85458 82.462428 107.69069 82.583984 107.55859 L 83.3125 108.05273 L 82.492188 106.96289 C 82.978443 106.96289 83.402887 107.06357 83.767578 107.26172 L 83.767578 106.66602 L 84.589844 106.96289 C 84.772191 106.30239 84.983435 105.77521 85.226562 105.37891 L 84.498047 104.58594 C 84.923519 104.85013 85.439056 105.01597 86.046875 105.08203 C 86.654694 105.14806 87.203198 105.21324 87.689453 105.2793 L 87.689453 104.94727 L 86.777344 104.58594 L 87.142578 103.49609 L 88.144531 103.89258 L 87.878906 104.68555 L 89.056641 104.68555 C 89.117407 104.75161 89.177491 104.85033 89.238281 104.98242 C 89.359838 105.11451 89.421875 105.21324 89.421875 105.2793 C 89.421875 105.41139 89.359838 105.51011 89.238281 105.57617 C 89.11671 105.57617 88.996557 105.60972 88.875 105.67578 L 89.238281 106.76562 C 88.812807 106.56747 88.479456 106.26935 88.236328 105.87305 C 88.114764 106.0712 87.870551 106.30416 87.505859 106.56836 L 86.412109 105.97266 L 86.412109 106.17188 L 85.957031 105.97266 L 85.591797 105.57617 L 86.412109 107.75586 C 87.749312 107.35956 89.026549 106.89754 90.242188 106.36914 C 91.518609 105.77469 92.733816 105.08166 93.888672 104.28906 C 93.706327 104.22301 93.493128 104.05717 93.25 103.79297 L 93.523438 102.40625 L 93.341797 102.40625 C 92.997003 102.54675 92.700376 102.75595 92.447266 103.0293 L 92.064453 103.69336 L 91.193359 101.71289 L 90.880859 101.71289 L 91.244141 102.30664 C 91.001013 102.24061 90.758753 102.17543 90.515625 102.10938 C 90.333278 102.04332 90.120081 102.00977 89.876953 102.00977 L 90.697266 103 L 91.335938 102.60352 C 91.214381 102.73561 91.12329 102.90146 91.0625 103.09961 C 91.0625 103.29776 91.002416 103.39648 90.880859 103.39648 C 90.820079 103.39648 90.697972 103.33131 90.515625 103.19922 C 90.333278 103.06713 90.242187 102.9684 90.242188 102.90234 L 90.332031 102.70312 L 89.785156 102.70312 C 89.785156 102.04262 89.694066 101.41476 89.511719 100.82031 L 90.607422 99.730469 L 90.332031 99.333984 C 90.088904 99.466084 89.846643 99.631927 89.603516 99.830078 C 89.421169 99.962186 89.178908 100.09447 88.875 100.22656 L 88.875 98.740234 L 90.697266 99.037109 C 90.697266 98.905017 90.728272 98.806294 90.789062 98.740234 C 90.849829 98.674175 90.909913 98.575452 90.970703 98.443359 L 90.242188 97.849609 L 89.695312 98.640625 L 89.96875 97.650391 L 92.248047 97.947266 L 92.429688 98.542969 L 92.976562 98.542969 L 93.433594 98.542969 L 92.611328 97.849609 L 93.341797 97.849609 L 94.162109 97.849609 L 94.890625 98.839844 L 94.162109 99.433594 L 94.34375 99.929688 L 92.337891 100.12695 L 92.703125 99.632812 L 91.882812 99.632812 L 92.511719 101.33984 L 92.703125 101.41602 L 92.675781 101.64258 L 93.888672 102.20898 C 94.010236 102.73738 94.223443 103.23103 94.527344 103.69336 L 94.800781 103.69336 C 95.10469 103.49521 95.34695 103.2326 95.529297 102.90234 L 94.070312 101.71289 L 95.255859 101.91016 L 95.166016 101.2168 C 95.59149 101.2168 95.984918 101.31552 96.349609 101.51367 C 96.228038 101.18343 96.107885 100.98598 95.986328 100.91992 C 95.925545 100.78782 96.045699 100.52325 96.349609 100.12695 L 97.169922 100.82031 L 98.355469 100.91992 L 98.447266 100.91992 C 98.994304 100.39152 99.511791 99.862385 99.998047 99.333984 C 100.54508 98.805584 101.06062 98.24484 101.54688 97.650391 L 100.08789 97.453125 L 100.45312 96.660156 L 99.267578 96.263672 L 99.267578 95.570312 L 100.81836 95.570312 L 100.81836 95.371094 L 99.998047 94.183594 L 101.0918 94.580078 L 100.9082 93.490234 L 99.451172 92.697266 C 99.6943 92.631206 100.05867 92.597656 100.54492 92.597656 L 100.81836 92.300781 L 100.81836 92.003906 L 100.08789 92.003906 L 100.81836 91.011719 C 100.81836 90.81357 100.78735 90.61612 100.72656 90.417969 C 100.6658 90.153771 100.60571 89.922761 100.54492 89.724609 L 101.54688 90.121094 L 101.54688 89.558594 L 101.18164 89.427734 L 101.36523 88.832031 L 101.54688 88.912109 L 101.54688 88.734375 L 100.72656 88.734375 L 101.45508 87.742188 C 101.6982 87.80822 101.9114 87.875347 102.09375 87.941406 C 102.33688 88.007465 102.58109 88.041016 102.82422 88.041016 C 102.64187 87.776815 102.55078 87.478686 102.55078 87.148438 L 103.46094 86.751953 L 103.09766 86.158203 L 102.00391 86.158203 L 102.00391 85.564453 L 103.73633 85.564453 C 103.79711 85.432353 103.64399 85.167785 103.2793 84.771484 C 102.97539 84.375184 102.58001 83.978332 102.09375 83.582031 L 100.63477 82.392578 C 100.14851 81.99627 99.815159 81.731702 99.632812 81.599609 L 99.90625 80.3125 L 98.8125 80.3125 C 99.237975 80.1804 99.602342 79.94939 99.90625 79.619141 L 100.08789 79.322266 C 100.08789 78.859917 99.9968 78.429503 99.814453 78.033203 C 98.781159 78.231344 97.779151 78.363628 96.806641 78.429688 C 95.894913 78.429688 94.921966 78.297403 93.888672 78.033203 L 94.070312 76.943359 L 95.529297 76.746094 L 95.166016 77.636719 C 95.59149 77.83487 96.046948 77.935547 96.533203 77.935547 C 96.47242 77.737398 96.290237 77.47283 95.986328 77.142578 L 95.712891 77.142578 L 95.712891 76.349609 C 96.077582 76.74591 96.379919 77.010478 96.623047 77.142578 L 96.896484 77.142578 L 96.896484 76.152344 L 98.082031 76.845703 L 98.082031 76.152344 L 98.994141 76.152344 L 98.994141 77.142578 L 99.814453 77.439453 L 100.17969 78.726562 L 100.81836 78.726562 L 100.45312 77.240234 L 101.36523 77.142578 L 102.27734 77.142578 L 102.27734 77.835938 L 101.63867 77.339844 L 101.63867 78.429688 L 101.0918 78.033203 L 101.0918 78.628906 C 101.45649 78.827058 101.82086 78.925781 102.18555 78.925781 C 102.48946 78.661592 102.82281 78.495747 103.1875 78.429688 L 104.19141 79.519531 C 104.73844 79.519531 105.31601 79.685373 105.92383 80.015625 L 106.19727 80.3125 L 104.83008 79.916016 L 105.65039 81.005859 L 106.5625 80.808594 L 106.74414 82.294922 L 106.4707 82.294922 C 105.86288 81.898622 105.2543 81.43465 104.64648 80.90625 L 104.09961 81.302734 L 105.4668 82.988281 L 106.74414 82.988281 L 106.37891 83.681641 L 107.19922 84.275391 L 105.83203 83.878906 L 105.83203 84.078125 C 106.31829 84.540477 106.92687 84.935376 107.65625 85.265625 L 107.38281 85.564453 L 105.28516 84.474609 L 104.64648 84.96875 L 106.19727 85.861328 L 106.83594 85.464844 C 106.59282 85.729044 106.4397 85.993612 106.37891 86.257812 C 106.56125 86.12572 106.77445 86.058594 107.01758 86.058594 C 107.26071 85.992561 107.50297 85.927387 107.74609 85.861328 L 107.92969 85.365234 C 107.80813 85.233126 107.71704 85.100842 107.65625 84.96875 C 107.59546 84.770591 107.50437 84.606702 107.38281 84.474609 L 108.38477 83.779297 L 108.6582 82.392578 C 108.53664 82.260486 108.38547 82.161762 108.20312 82.095703 C 108.02077 81.963595 107.86765 81.831311 107.74609 81.699219 C 108.23235 81.699219 108.59867 81.665669 108.8418 81.599609 L 108.8418 81.501953 L 108.8418 80.509766 C 108.41632 80.311617 107.80774 80.015441 107.01758 79.619141 C 106.22741 79.156781 105.64985 78.959331 105.28516 79.025391 L 105.28516 78.033203 L 107.29102 78.429688 L 107.65625 77.835938 L 106.74414 76.845703 C 107.29118 76.581511 107.80867 76.415669 108.29492 76.349609 L 108.01953 76.052734 C 107.47249 75.788526 106.86587 75.589123 106.19727 75.457031 C 106.25803 75.324939 106.31812 75.226215 106.37891 75.160156 L 106.74414 74.763672 C 107.2304 74.895772 107.62578 75.061614 107.92969 75.259766 C 108.2336 75.457917 108.65804 75.52309 109.20508 75.457031 L 108.8418 76.052734 C 108.96337 75.986701 109.08352 75.919575 109.20508 75.853516 C 109.32665 75.787456 109.44876 75.787456 109.57031 75.853516 L 109.66211 73.476562 C 109.54055 73.34447 109.38743 73.245747 109.20508 73.179688 L 108.8418 72.783203 L 109.11523 72.287109 L 109.66211 72.486328 L 109.66211 72.1875 L 109.66211 70.900391 C 109.54055 70.966424 109.20524 70.932873 108.6582 70.800781 C 108.17195 70.602625 107.8386 70.438733 107.65625 70.306641 L 107.47266 71.097656 C 106.92562 70.767405 106.37907 70.305387 105.83203 69.710938 L 106.28906 69.216797 L 106.74414 69.017578 L 107.74609 70.007812 L 108.6582 70.007812 C 108.96211 69.94178 109.2664 69.876606 109.57031 69.810547 L 109.47852 67.134766 L 109.29688 66.9375 L 109.20508 66.441406 L 109.38867 66.34375 L 109.11523 64.460938 L 109.02344 64.460938 L 109.11523 64.361328 L 108.93164 63.470703 C 108.26304 63.602803 107.68547 63.865418 107.19922 64.261719 L 108.38477 65.054688 L 107.92969 65.550781 L 107.10938 64.757812 L 107.10938 64.164062 L 107.29102 63.667969 C 107.83805 63.403769 108.23343 63.10564 108.47656 62.775391 L 108.75 62.875 L 108.8418 62.875 L 108.75 62.478516 C 108.32453 62.478516 107.80704 62.346231 107.19922 62.082031 C 107.74626 61.88388 108.23273 61.818706 108.6582 61.884766 C 107.62491 56.931013 105.92323 52.768944 103.55273 49.400391 C 101.24302 45.965788 98.417223 43.191727 95.074219 41.078125 C 91.791995 38.964523 88.14442 37.446185 84.132812 36.521484 C 80.121205 35.596784 75.958096 35.134766 71.642578 35.134766 L 47.208984 35.134766 z M 101.54688 88.912109 L 101.54688 89.558594 L 101.73047 89.625 L 101.82031 89.03125 L 101.54688 88.912109 z M 92.675781 101.64258 L 92.611328 101.61328 L 92.511719 101.33984 L 91.701172 101.01953 L 91.0625 101.41602 L 91.193359 101.71289 L 92.15625 101.71289 C 92.09546 102.04314 92.064453 102.53872 92.064453 103.19922 L 92.15625 103.39648 C 92.244663 103.26198 92.34342 103.14144 92.447266 103.0293 L 92.521484 102.90234 L 92.675781 101.64258 z M 87.878906 104.68555 L 87.689453 104.68555 L 87.689453 104.94727 L 87.779297 104.98242 L 87.878906 104.68555 z M 59.5625 110.03516 C 59.623282 110.06818 59.668828 110.06819 59.699219 110.03516 L 59.5625 110.03516 z M 49.214844 96.957031 L 49.03125 96.660156 L 47.755859 95.867188 L 47.572266 95.470703 L 48.849609 95.867188 L 48.119141 94.777344 L 49.03125 95.173828 L 49.03125 94.580078 C 48.909689 94.05168 48.758519 93.688386 48.576172 93.490234 L 48.576172 94.28125 L 48.029297 93.984375 L 47.208984 94.777344 L 47.208984 96.363281 C 47.512895 96.363281 47.846246 96.394878 48.210938 96.460938 C 48.575629 96.460938 48.910935 96.626782 49.214844 96.957031 z M 53.589844 101.45508 C 53.537729 101.43243 53.485709 101.40937 53.433594 101.38672 L 53.589844 101.51367 L 53.589844 101.45508 z M 53.433594 101.38672 L 53.224609 101.2168 L 53.042969 101.2168 C 53.173138 101.27338 53.303425 101.33014 53.433594 101.38672 z M 88.853516 49.09375 L 87.767578 50.564453 L 86.296875 50.691406 L 87.384766 49.605469 L 88.853516 49.09375 z M 85.410156 56.632812 L 85.591797 56.832031 L 85.410156 57.029297 L 85.226562 56.832031 L 85.410156 56.632812 z M 68.542969 56.931641 L 72.097656 56.931641 C 74.46815 56.931641 76.627244 57.227814 78.572266 57.822266 C 80.57807 58.416715 82.279751 59.340751 83.677734 60.595703 C 85.07572 61.784604 86.168821 63.370061 86.958984 65.351562 C 87.252741 66.088216 87.491233 66.880364 87.675781 67.726562 L 87.689453 67.730469 L 87.679688 67.736328 C 87.990049 69.1636 88.144531 70.747738 88.144531 72.486328 C 88.144531 73.212877 88.113525 73.939464 88.052734 74.666016 C 87.991944 75.326516 87.962891 76.019545 87.962891 76.746094 L 87.779297 76.746094 L 87.689453 77.439453 L 88.417969 77.835938 C 88.296407 77.90197 88.145238 77.967144 87.962891 78.033203 C 87.84132 78.099262 87.719213 78.132812 87.597656 78.132812 L 87.505859 78.033203 L 87.416016 78.628906 L 87.689453 78.925781 C 87.750244 78.925781 87.841334 78.990955 87.962891 79.123047 L 87.962891 78.429688 L 88.783203 78.429688 L 88.052734 80.015625 L 87.232422 79.816406 L 87.142578 79.322266 C 86.83866 80.048817 86.716553 80.741843 86.777344 81.402344 L 86.322266 81.302734 C 85.714446 82.491635 84.861653 83.581514 83.767578 84.572266 L 83.767578 84.869141 L 83.767578 86.058594 C 83.949923 86.586994 84.25423 87.017389 84.679688 87.347656 L 84.679688 87.048828 L 85.773438 88.535156 C 86.138129 88.601215 86.533512 88.699939 86.958984 88.832031 C 87.384459 88.89808 87.748826 89.030364 88.052734 89.228516 C 87.991954 89.030367 87.840784 88.799356 87.597656 88.535156 C 88.023129 88.535156 88.418512 88.635833 88.783203 88.833984 C 88.722422 89.230285 88.509223 89.658742 88.144531 90.121094 C 87.845581 90.510925 87.578026 90.867441 87.337891 91.193359 L 88.052734 92.101562 L 86.958984 91.707031 L 87.689453 92.697266 L 88.236328 92.697266 C 88.479456 92.697266 88.692653 92.730816 88.875 92.796875 C 88.996557 93.061075 89.056641 93.32369 89.056641 93.587891 L 89.056641 94.183594 L 86.685547 94.777344 L 85.773438 94.976562 C 85.773437 95.042622 85.804444 95.141345 85.865234 95.273438 C 85.986791 95.33947 86.077881 95.404644 86.138672 95.470703 L 85.226562 96.363281 L 86.322266 96.759766 L 87.597656 99.433594 L 87.050781 99.433594 L 86.685547 98.146484 L 85.591797 98.146484 L 85.318359 97.253906 L 84.40625 97.253906 L 83.677734 96.263672 L 85.136719 96.263672 L 83.767578 94.976562 L 83.767578 94.183594 L 84.953125 95.570312 L 85.318359 95.570312 L 85.683594 95.570312 L 84.589844 94.677734 L 84.953125 93.291016 C 84.527653 93.224956 84.13227 93.126233 83.767578 92.994141 C 83.402887 92.796 83.009459 92.663715 82.583984 92.597656 L 82.21875 92.697266 C 82.279516 92.631214 82.3396 92.431811 82.400391 92.101562 C 82.461181 91.771311 82.461181 91.607422 82.400391 91.607422 L 82.21875 91.904297 C 81.671714 91.904297 81.18524 91.805573 80.759766 91.607422 L 80.759766 92.300781 L 81.761719 91.904297 C 81.579372 92.168497 81.306098 92.498231 80.941406 92.894531 C 80.515934 92.69638 80.09149 92.366646 79.666016 91.904297 C 79.422888 91.243796 79.300781 90.71466 79.300781 90.318359 L 80.578125 91.507812 C 80.456564 91.243612 80.303441 90.979044 80.121094 90.714844 L 80.759766 90.714844 C 80.698975 90.516695 80.667969 90.319245 80.667969 90.121094 L 80.667969 89.427734 L 79.847656 89.130859 L 79.847656 90.021484 L 78.480469 89.427734 L 78.115234 89.824219 L 78.115234 89.03125 L 77.476562 89.427734 L 77.476562 88.4375 L 78.845703 88.634766 L 78.662109 87.841797 L 79.574219 87.841797 L 79.574219 88.734375 C 79.999691 88.734375 80.395074 88.833099 80.759766 89.03125 L 80.759766 88.138672 C 80.638195 88.138672 80.516088 88.107075 80.394531 88.041016 C 80.27296 87.974983 80.152807 87.907856 80.03125 87.841797 L 80.759766 87.048828 C 81.063676 87.37908 81.33695 87.710767 81.580078 88.041016 C 81.823206 88.371267 82.067434 88.701014 82.310547 89.03125 L 81.853516 89.03125 L 81.398438 88.832031 C 81.276874 89.294391 81.428043 89.592517 81.853516 89.724609 C 82.096643 89.592509 82.340857 89.426667 82.583984 89.228516 C 82.887895 89.030375 83.190232 88.89809 83.494141 88.832031 C 84.041179 88.964131 84.527653 89.163534 84.953125 89.427734 L 85.318359 88.734375 L 84.224609 88.734375 L 84.679688 88.337891 L 83.039062 87.148438 L 83.951172 87.148438 C 83.829601 86.818186 83.707494 86.488452 83.585938 86.158203 C 83.525157 85.761903 83.40305 85.398611 83.220703 85.068359 C 82.856012 85.398608 82.491645 85.663177 82.126953 85.861328 C 81.762262 86.05948 81.366881 86.290487 80.941406 86.554688 C 81.002197 86.554688 81.033203 86.619861 81.033203 86.751953 C 81.033203 86.818012 81.002197 86.851563 80.941406 86.851562 L 80.759766 86.654297 L 80.851562 86.554688 L 79.300781 87.148438 L 78.025391 87.544922 L 76.65625 89.130859 L 75.927734 89.130859 C 76.110086 88.932708 76.261256 88.66814 76.382812 88.337891 C 76.139685 88.60208 75.897425 88.765972 75.654297 88.832031 C 75.532733 88.633882 75.28852 88.402872 74.923828 88.138672 C 74.802264 88.138672 74.620081 88.205798 74.376953 88.337891 C 74.133825 88.403924 74.013672 88.370373 74.013672 88.238281 L 72.736328 88.337891 C 72.736328 88.271831 72.523129 88.238281 72.097656 88.238281 L 69.455078 88.238281 L 68.908203 88.832031 C 68.421948 88.567842 68.055628 88.40395 67.8125 88.337891 L 68.542969 88.138672 L 68.542969 87.148438 C 68.421405 87.148437 68.148129 87.114887 67.722656 87.048828 C 67.357965 86.916736 67.022661 86.851562 66.71875 86.851562 L 66.71875 86.158203 L 67.539062 86.455078 L 68.542969 85.861328 L 68.542969 85.564453 L 67.539062 85.564453 C 67.721407 85.234194 67.994683 85.001233 68.359375 84.869141 C 67.87312 84.737041 67.448676 84.539591 67.083984 84.275391 C 67.266331 83.945134 67.539605 83.714123 67.904297 83.582031 L 68.542969 83.878906 L 68.542969 83.384766 C 68.421398 83.318733 68.299291 83.251606 68.177734 83.185547 C 68.116944 83.053439 68.025853 82.921155 67.904297 82.789062 L 67.449219 83.681641 L 67.265625 83.185547 L 67.265625 82.392578 L 67.449219 81.996094 L 68.542969 81.996094 L 68.542969 79.71875 L 67.630859 79.71875 C 67.691643 79.520601 67.90484 79.289591 68.269531 79.025391 L 68.542969 79.322266 L 68.542969 77.242188 C 68.421388 77.242188 68.390381 77.208637 68.451172 77.142578 C 68.511962 77.076519 68.542969 77.076519 68.542969 77.142578 L 68.542969 70.900391 L 67.357422 70.800781 C 67.539774 70.668673 67.690943 70.536389 67.8125 70.404297 C 67.934071 70.20614 68.056178 70.042248 68.177734 69.910156 L 68.269531 69.314453 L 68.269531 69.017578 L 68.542969 69.117188 L 68.542969 66.738281 L 68.177734 66.738281 L 68.542969 66.244141 L 68.542969 56.931641 z M 87.337891 91.193359 L 87.324219 91.177734 L 87.324219 91.210938 C 87.328187 91.205547 87.333906 91.198768 87.337891 91.193359 z M 87.324219 91.177734 L 87.324219 90.318359 C 87.263453 90.318359 87.203369 90.385486 87.142578 90.517578 L 86.958984 90.714844 L 87.324219 91.177734 z M 87.679688 67.736328 C 87.678938 67.732883 87.676532 67.730006 87.675781 67.726562 L 86.322266 67.234375 L 85.957031 68.720703 L 87.679688 67.736328 z M 67.539062 57.128906 L 67.996094 57.326172 L 67.8125 57.822266 L 67.357422 57.625 L 67.539062 57.128906 z M 103.2793 57.326172 L 103.46094 57.525391 L 103.2793 57.625 L 103.1875 57.525391 L 103.2793 57.326172 z M 105.74023 58.615234 L 106.10547 58.615234 C 106.40938 58.615234 106.77375 58.812684 107.19922 59.208984 C 107.68548 59.539233 107.98977 59.837362 108.11133 60.101562 L 107.29102 60.001953 C 106.9871 59.803804 106.71383 59.606355 106.4707 59.408203 C 106.22758 59.210052 105.98336 58.945483 105.74023 58.615234 z M 105.37695 58.912109 L 105.55859 59.111328 L 105.37695 59.308594 L 105.28516 59.111328 L 105.37695 58.912109 z M 103.09766 59.011719 L 104.91992 59.011719 L 104.73828 59.505859 L 104.37305 59.902344 C 103.88679 59.704195 103.46235 59.408019 103.09766 59.011719 z M 106.10547 59.605469 L 106.5625 59.605469 L 106.37891 59.804688 C 106.13578 59.936795 105.77141 60.06908 105.28516 60.201172 L 105.37695 59.902344 L 106.10547 59.605469 z M 73.466797 60.201172 L 72.097656 60.894531 L 72.371094 61.587891 L 73.009766 62.181641 C 73.313674 61.52114 73.466797 60.861672 73.466797 60.201172 z M 108.11133 60.894531 L 108.20312 61.091797 L 108.11133 61.191406 L 107.92969 61.091797 L 108.11133 60.894531 z M 63.892578 61.488281 L 64.074219 61.685547 L 63.892578 61.884766 L 63.800781 61.685547 L 63.892578 61.488281 z M 61.613281 61.884766 L 62.34375 61.884766 L 61.978516 62.28125 L 61.613281 61.884766 z M 63.164062 63.470703 L 63.800781 63.964844 L 63.4375 64.164062 L 63.164062 64.164062 L 63.164062 63.470703 z M 97.808594 63.470703 L 98.355469 64.064453 C 98.537821 64.262605 98.690943 64.493612 98.8125 64.757812 C 98.447808 64.625712 98.143518 64.428263 97.900391 64.164062 L 97.535156 64.560547 L 96.714844 63.767578 L 96.259766 64.164062 L 96.441406 63.865234 C 96.927662 63.601034 97.383119 63.470703 97.808594 63.470703 z M 99.998047 63.470703 L 99.632812 63.865234 C 99.328902 63.667083 99.055628 63.568359 98.8125 63.568359 L 99.998047 63.470703 z M 66.628906 63.767578 L 66.71875 63.964844 L 66.628906 64.164062 L 66.445312 63.964844 L 66.628906 63.767578 z M 67.265625 64.064453 L 67.630859 64.261719 L 67.904297 64.658203 L 67.8125 64.757812 C 67.690929 64.757812 67.570775 64.659089 67.449219 64.460938 C 67.388453 64.262797 67.326415 64.130512 67.265625 64.064453 z M 92.611328 64.164062 L 93.433594 64.164062 C 93.372811 64.29617 93.21969 64.426502 92.976562 64.558594 L 92.611328 64.164062 z M 66.902344 64.460938 L 67.083984 64.658203 L 66.902344 64.757812 L 66.71875 64.658203 L 66.902344 64.460938 z M 96.623047 64.460938 L 96.896484 64.460938 L 97.169922 64.757812 L 96.896484 65.253906 L 96.623047 65.451172 L 96.623047 64.460938 z M 99.724609 64.460938 L 99.90625 64.658203 L 99.724609 64.757812 L 99.541016 64.658203 L 99.724609 64.460938 z M 62.798828 64.757812 L 63.527344 64.757812 C 63.405773 64.823861 63.285619 64.989706 63.164062 65.253906 C 63.042491 64.989717 62.920385 64.823872 62.798828 64.757812 z M 109.02344 64.757812 L 109.11523 64.955078 L 109.02344 65.054688 L 108.8418 64.955078 L 109.02344 64.757812 z M 61.613281 65.054688 L 62.34375 65.054688 C 62.222179 65.31888 62.100072 65.484722 61.978516 65.550781 C 61.856945 65.48473 61.734838 65.318888 61.613281 65.054688 z M 66.902344 65.054688 L 67.083984 65.253906 L 66.902344 65.451172 L 66.71875 65.253906 L 66.902344 65.054688 z M 78.935547 65.154297 L 79.939453 67.433594 L 80.578125 67.037109 L 80.941406 66.044922 L 79.757812 65.253906 L 78.935547 65.154297 z M 96.259766 65.451172 L 96.259766 66.441406 L 95.439453 66.441406 L 95.439453 66.144531 C 95.621798 65.814272 95.895074 65.583264 96.259766 65.451172 z M 61.886719 65.748047 L 62.707031 65.748047 C 62.463913 65.880155 62.312744 66.012439 62.251953 66.144531 L 61.886719 65.748047 z M 67.8125 65.748047 L 67.904297 65.947266 L 67.8125 66.044922 L 67.630859 65.947266 L 67.8125 65.748047 z M 69.728516 65.748047 L 69.544922 66.244141 L 70.091797 66.441406 L 70.275391 65.847656 L 69.728516 65.748047 z M 92.886719 65.748047 L 92.976562 65.947266 L 92.886719 66.044922 L 92.703125 65.947266 L 92.886719 65.748047 z M 66.171875 66.441406 L 66.71875 66.441406 L 67.357422 66.541016 C 66.931947 66.805205 66.536567 66.97105 66.171875 67.037109 L 66.171875 66.441406 z M 91.882812 66.441406 L 92.15625 66.738281 L 92.15625 67.037109 L 91.154297 67.037109 L 91.335938 66.738281 L 91.882812 66.441406 z M 95.439453 66.738281 L 96.259766 66.738281 L 96.259766 67.630859 L 95.894531 67.53125 L 95.439453 67.037109 L 95.439453 66.738281 z M 99.449219 66.738281 L 99.541016 66.837891 L 99.449219 67.037109 L 99.267578 66.837891 L 99.449219 66.738281 z M 66.902344 67.037109 L 67.083984 67.234375 L 66.902344 67.333984 L 66.71875 67.234375 L 66.902344 67.037109 z M 61.613281 67.630859 C 61.734852 67.762952 61.856959 67.861675 61.978516 67.927734 C 62.100072 67.993794 62.191163 68.092517 62.251953 68.224609 L 62.160156 68.324219 C 62.0386 68.25816 61.947509 68.159436 61.886719 68.027344 C 61.825928 67.895236 61.734838 67.762952 61.613281 67.630859 z M 93.888672 67.630859 L 94.253906 67.828125 L 94.527344 68.523438 L 94.527344 69.017578 L 93.888672 69.017578 L 93.888672 67.630859 z M 104.46484 67.828125 L 104.91992 67.828125 L 105.28516 68.224609 L 104.83008 68.126953 L 104.46484 67.828125 z M 60.611328 68.027344 L 60.792969 68.224609 L 60.611328 68.324219 L 60.519531 68.224609 L 60.611328 68.027344 z M 90.332031 68.027344 L 90.970703 68.027344 L 90.970703 68.324219 L 90.789062 68.621094 C 90.545944 68.356886 90.392822 68.159436 90.332031 68.027344 z M 89.876953 68.324219 L 90.058594 68.523438 L 89.876953 68.621094 L 89.695312 68.523438 L 89.876953 68.324219 z M 91.974609 68.324219 L 92.15625 68.523438 L 91.974609 68.621094 L 91.791016 68.523438 L 91.974609 68.324219 z M 88.144531 68.423828 L 88.601562 68.621094 L 88.417969 69.117188 L 87.962891 68.917969 L 88.144531 68.423828 z M 61.794922 68.621094 L 61.431641 69.017578 L 60.611328 68.720703 L 61.158203 68.720703 C 61.401328 68.720703 61.612575 68.687153 61.794922 68.621094 z M 96.714844 68.720703 L 97.71875 69.710938 L 99.085938 68.917969 L 99.724609 69.613281 L 97.992188 69.613281 L 98.8125 70.800781 C 98.690936 70.60264 98.508753 70.470356 98.265625 70.404297 C 98.022497 70.272189 97.778284 70.139905 97.535156 70.007812 L 97.171875 70.007812 L 97.808594 71.296875 L 97.171875 71.296875 L 97.353516 72.089844 L 98.539062 72.089844 C 98.478272 72.354044 98.478272 72.65022 98.539062 72.980469 C 98.599853 73.244669 98.690943 73.509237 98.8125 73.773438 L 98.953125 73.927734 L 98.082031 73.972656 L 98.082031 74.566406 C 97.960107 74.528546 97.825484 74.502055 97.683594 74.480469 L 97.535156 74.962891 L 97.080078 74.763672 L 97.179688 74.439453 C 96.951253 74.434189 96.708699 74.440388 96.441406 74.466797 C 95.772806 74.532846 95.226256 74.66513 94.800781 74.863281 L 94.800781 75.755859 L 93.615234 75.359375 L 94.800781 74.269531 L 94.800781 72.882812 L 95.712891 72.882812 C 95.652124 72.75072 95.590087 72.650044 95.529297 72.583984 C 95.468506 72.517925 95.439453 72.419202 95.439453 72.287109 C 95.804145 72.485261 96.228588 72.583984 96.714844 72.583984 L 95.986328 71.990234 L 96.988281 71.59375 L 96.259766 70.603516 L 96.988281 69.613281 L 95.986328 69.216797 C 96.168673 69.01864 96.410933 68.852795 96.714844 68.720703 z M 97.179688 74.439453 C 97.358914 74.443583 97.528287 74.456841 97.683594 74.480469 L 97.71875 74.367188 L 97.261719 74.169922 L 97.179688 74.439453 z M 93.25 68.917969 C 93.371571 69.050061 93.493678 69.150738 93.615234 69.216797 C 93.736791 69.282856 93.827881 69.38158 93.888672 69.513672 L 93.796875 69.613281 L 93.433594 69.314453 L 93.25 68.917969 z M 90.058594 69.017578 L 90.332031 69.314453 L 90.332031 69.613281 L 89.421875 69.613281 L 89.603516 69.314453 L 90.058594 69.017578 z M 68.451172 69.513672 L 68.542969 69.910156 L 68.542969 69.613281 L 68.451172 69.513672 z M 62.707031 69.613281 L 62.890625 69.810547 L 62.707031 70.007812 L 62.617188 69.810547 L 62.707031 69.613281 z M 94.34375 69.613281 L 94.527344 69.810547 L 94.34375 70.007812 L 94.253906 69.810547 L 94.34375 69.613281 z M 95.529297 69.613281 L 95.712891 69.810547 L 95.529297 70.007812 L 95.439453 69.810547 L 95.529297 69.613281 z M 64.53125 70.007812 L 64.623047 71.59375 L 64.349609 71.396484 L 64.074219 70.800781 L 64.257812 70.207031 L 64.53125 70.007812 z M 65.259766 70.207031 C 65.442113 70.471231 65.686324 70.637079 65.990234 70.703125 L 65.625 70.900391 L 65.078125 70.800781 L 64.896484 70.603516 L 65.259766 70.207031 z M 66.445312 70.207031 L 66.445312 71.097656 C 66.323741 70.833464 66.201635 70.669575 66.080078 70.603516 L 66.445312 70.207031 z M 93.523438 70.306641 L 94.34375 70.306641 C 94.10063 70.438749 93.949462 70.571033 93.888672 70.703125 L 93.523438 70.306641 z M 102.55078 70.503906 L 103.64453 72.287109 C 103.88766 72.088958 104.22101 71.990234 104.64648 71.990234 L 104.2832 72.882812 L 103.73438 72.486328 L 102.79883 73.357422 C 102.92627 73.196479 103.02877 73.007773 103.09766 72.783203 L 102.09375 71.890625 L 102.91406 71.890625 L 102.55078 70.503906 z M 63.345703 70.603516 L 63.4375 70.800781 L 63.345703 70.900391 L 63.164062 70.800781 L 63.345703 70.603516 z M 92.703125 70.603516 L 92.976562 70.900391 L 92.703125 71.296875 L 92.429688 70.900391 L 92.703125 70.603516 z M 108.11133 70.900391 L 108.20312 71.097656 L 108.11133 71.296875 L 107.92969 71.097656 L 108.11133 70.900391 z M 63.4375 71.197266 L 63.800781 71.396484 L 64.074219 71.792969 C 64.013453 71.792969 63.953369 71.824566 63.892578 71.890625 L 63.800781 71.890625 L 63.4375 71.890625 L 63.4375 71.197266 z M 67.904297 71.197266 L 67.904297 71.990234 L 67.539062 71.59375 L 67.904297 71.197266 z M 105.4668 71.396484 L 106.4707 72.980469 L 106.19727 73.179688 C 105.83257 73.113639 105.46821 72.914236 105.10352 72.583984 L 105.83203 72.583984 L 105.4668 71.396484 z M 106.92578 72.287109 L 107.01758 72.386719 L 106.92578 72.583984 L 106.74414 72.386719 L 106.92578 72.287109 z M 108.20312 72.287109 L 108.47656 72.287109 L 108.29492 73.376953 C 108.23414 73.376953 108.11203 73.27823 107.92969 73.080078 L 107.92969 72.583984 L 108.20312 72.287109 z M 101.27344 72.583984 C 101.63813 72.980277 101.97344 73.211285 102.27734 73.277344 L 101.91211 73.873047 L 100.63477 73.080078 L 101.45508 74.269531 L 100.63477 74.863281 C 100.93867 74.863281 101.18289 74.829731 101.36523 74.763672 C 101.60836 74.697639 101.88164 74.632465 102.18555 74.566406 L 102.91406 75.853516 C 103.33953 75.853516 103.85702 75.788342 104.46484 75.65625 C 105.07266 75.52415 105.52812 75.3267 105.83203 75.0625 L 105.83203 75.259766 C 105.71047 75.457914 105.5593 75.688925 105.37695 75.953125 C 105.25539 76.151284 105.10227 76.317127 104.91992 76.449219 L 104.37305 76.052734 L 104.37305 77.042969 L 103.55273 77.339844 L 102.82422 76.052734 L 102.27734 76.943359 L 102.27734 76.052734 L 101.45508 76.845703 L 101 76.150391 L 100.45312 76.546875 C 100.33155 76.216626 100.20945 75.886892 100.08789 75.556641 C 100.0271 75.16034 99.93601 74.797046 99.814453 74.466797 L 99.175781 74.466797 L 99.998047 73.873047 L 98.994141 73.925781 L 98.994141 73.179688 L 99.541016 73.576172 L 101.27344 72.583984 z M 60.064453 72.882812 L 60.154297 73.080078 L 60.064453 73.179688 L 59.880859 73.080078 L 60.064453 72.882812 z M 92.886719 72.882812 L 93.523438 73.277344 L 93.888672 73.773438 L 93.341797 73.773438 C 93.220233 73.773438 93.03805 73.708264 92.794922 73.576172 C 92.43023 73.972464 92.094924 74.203472 91.791016 74.269531 C 91.97336 73.741131 92.33968 73.279113 92.886719 72.882812 z M 90.242188 73.179688 L 91.517578 73.179688 L 91.335938 74.466797 L 90.242188 73.179688 z M 107.01758 73.179688 C 107.26071 73.311788 107.50297 73.475677 107.74609 73.673828 C 107.62453 73.739887 107.38227 73.840564 107.01758 73.972656 C 106.71366 74.104748 106.50046 74.169922 106.37891 74.169922 L 106.10547 74.169922 L 107.01758 73.179688 z M 104.55664 73.576172 L 105.37695 73.576172 C 105.31617 73.70828 105.16305 73.840564 104.91992 73.972656 L 104.55664 73.576172 z M 103.64453 74.466797 L 103.73438 74.666016 L 103.64453 74.863281 L 103.46094 74.666016 L 103.64453 74.466797 z M 108.38477 74.466797 L 108.56836 74.666016 L 108.38477 74.863281 L 108.20312 74.666016 L 108.38477 74.466797 z M 109.93555 74.566406 L 109.66211 75.556641 L 110.9375 76.152344 L 112.12305 76.052734 C 111.69757 75.194082 110.96884 74.698506 109.93555 74.566406 z M 112.39648 74.763672 C 113.0043 75.292072 113.64195 75.623759 114.31055 75.755859 L 114.49414 75.359375 C 114.19023 75.227283 113.85493 75.094999 113.49023 74.962891 C 113.12554 74.830798 112.76118 74.763672 112.39648 74.763672 z M 90.515625 74.863281 L 90.607422 74.962891 L 90.515625 75.160156 L 90.332031 74.962891 L 90.515625 74.863281 z M 91.517578 74.863281 L 91.882812 74.863281 L 92.556641 75.230469 C 92.602438 75.108362 92.646941 74.985388 92.703125 74.863281 L 93.25 75.359375 L 93.615234 76.152344 L 92.951172 76.488281 L 93.433594 76.449219 L 93.068359 76.845703 C 92.927043 76.753564 92.792236 76.684061 92.664062 76.634766 L 92.248047 76.845703 C 92.248047 76.746902 92.256872 76.64763 92.263672 76.548828 C 92.258569 76.548742 92.253123 76.546875 92.248047 76.546875 L 92.263672 76.544922 C 92.279405 76.319297 92.308179 76.092813 92.359375 75.867188 C 92.030087 75.664872 91.74898 75.397778 91.517578 75.0625 L 91.517578 74.863281 z M 92.359375 75.867188 C 92.549047 75.983723 92.754414 76.07797 92.976562 76.150391 L 92.976562 75.853516 L 92.611328 75.259766 L 92.556641 75.230469 C 92.476877 75.443139 92.407631 75.654517 92.359375 75.867188 z M 92.263672 76.544922 C 92.263581 76.546218 92.263761 76.547532 92.263672 76.548828 C 92.389324 76.550955 92.522344 76.580261 92.664062 76.634766 L 92.951172 76.488281 L 92.263672 76.544922 z M 98.082031 74.863281 L 98.355469 75.060547 L 98.355469 75.359375 L 97.535156 75.359375 L 97.71875 75.060547 L 98.082031 74.863281 z M 95.712891 75.160156 C 95.834454 75.226208 96.016636 75.392063 96.259766 75.65625 L 95.986328 75.853516 L 95.712891 75.853516 L 95.712891 75.160156 z M 67.175781 75.457031 L 67.357422 75.65625 L 67.175781 75.853516 L 67.083984 75.65625 L 67.175781 75.457031 z M 90.970703 75.457031 L 91.244141 75.853516 L 90.970703 76.150391 L 90.607422 75.853516 L 90.970703 75.457031 z M 89.96875 76.25 L 90.515625 76.449219 L 90.404297 76.748047 L 91.154297 76.845703 L 91.517578 77.142578 C 91.274441 77.472838 91.18335 77.703845 91.244141 77.835938 C 91.304921 77.901997 91.578198 77.901997 92.064453 77.835938 L 93.433594 78.726562 L 91.701172 78.529297 C 91.701172 78.925597 91.610081 79.32245 91.427734 79.71875 L 92.15625 80.509766 L 91.791016 79.025391 L 93.068359 80.412109 C 93.676179 80.213958 94.191715 79.915832 94.617188 79.519531 L 94.892578 79.025391 L 95.986328 79.419922 L 96.167969 79.025391 L 96.623047 78.726562 L 96.896484 78.726562 L 96.896484 79.519531 C 96.592574 79.84978 96.290237 80.114349 95.986328 80.3125 C 95.682417 80.510651 95.378127 80.741659 95.074219 81.005859 L 95.347656 81.501953 L 95.712891 81.699219 L 95.347656 82.591797 L 97.171875 84.375 L 96.623047 84.96875 L 97.171875 86.554688 L 95.986328 88.238281 L 96.349609 88.734375 C 95.984918 88.800434 95.651567 88.733308 95.347656 88.535156 L 95.255859 88.734375 L 95.439453 89.130859 L 94.527344 89.527344 L 94.890625 90.714844 L 93.523438 90.714844 C 93.28031 90.516692 92.915943 90.1534 92.429688 89.625 L 91.701172 90.714844 L 89.876953 91.111328 L 89.056641 90.021484 L 89.421875 89.625 L 90.423828 90.121094 L 89.603516 90.714844 L 90.607422 90.714844 C 90.728978 90.582744 90.789062 90.418855 90.789062 90.220703 L 90.789062 89.625 L 90.789062 89.228516 L 89.603516 89.228516 L 89.148438 87.445312 L 89.330078 87.445312 C 89.69477 87.775564 89.99906 88.172417 90.242188 88.634766 L 90.697266 88.138672 L 89.96875 87.148438 L 90.605469 87.148438 L 90.605469 87.841797 L 91.609375 87.841797 L 91.154297 89.130859 L 91.427734 89.130859 C 91.610079 88.998767 91.821325 88.931641 92.064453 88.931641 C 92.368364 88.865592 92.521484 88.733308 92.521484 88.535156 C 92.521484 88.469097 92.368364 88.4375 92.064453 88.4375 L 92.794922 87.445312 L 92.429688 86.158203 L 93.523438 86.158203 L 94.162109 87.841797 L 95.074219 87.841797 L 95.166016 85.662109 C 95.348363 86.05841 95.439453 86.455262 95.439453 86.851562 L 96.349609 86.851562 L 96.623047 85.861328 C 95.954447 85.266879 95.43891 84.605458 95.074219 83.878906 C 94.709527 83.086306 94.19204 82.393277 93.523438 81.798828 C 93.523438 81.996977 93.554444 82.293153 93.615234 82.689453 C 93.676001 83.085753 93.736085 83.516164 93.796875 83.978516 C 93.918446 84.374816 94.040553 84.771668 94.162109 85.167969 C 94.28368 85.498226 94.405787 85.729236 94.527344 85.861328 L 93.980469 86.158203 L 93.615234 85.662109 C 93.311324 85.596076 93.007033 85.629627 92.703125 85.761719 C 92.399214 85.893837 92.094924 85.927387 91.791016 85.861328 L 91.791016 86.554688 C 91.791016 86.752836 91.761962 86.950286 91.701172 87.148438 C 90.971789 86.950286 90.272116 86.586994 89.603516 86.058594 L 90.423828 85.761719 C 90.545385 85.893819 90.607422 86.091269 90.607422 86.355469 C 90.668212 86.55362 90.759303 86.652344 90.880859 86.652344 L 90.880859 86.455078 C 90.759303 86.190878 90.668212 85.92631 90.607422 85.662109 C 90.546631 85.397909 90.453588 85.133341 90.332031 84.869141 L 90.880859 84.869141 C 90.94164 84.406792 91.09281 83.879608 91.335938 83.285156 L 91.974609 83.285156 C 91.427571 82.888856 90.972113 82.393277 90.607422 81.798828 L 91.335938 81.798828 C 91.579065 81.798828 91.821325 81.832378 92.064453 81.898438 L 91.427734 80.015625 C 91.184607 80.081658 90.940393 80.146831 90.697266 80.212891 C 90.514919 80.27895 90.301721 80.3125 90.058594 80.3125 L 90.058594 79.71875 L 91.244141 79.71875 C 91.304907 79.388501 91.275853 78.958088 91.154297 78.429688 C 91.032733 77.901287 90.85055 77.504435 90.607422 77.240234 C 90.546641 77.636535 90.271412 78.033387 89.785156 78.429688 L 89.511719 78.429688 L 89.148438 77.042969 C 89.026876 77.175077 88.873753 77.307361 88.691406 77.439453 C 88.56985 77.571545 88.509766 77.571545 88.509766 77.439453 C 88.509766 77.373404 88.540772 77.24112 88.601562 77.042969 C 88.723119 76.844809 88.81421 76.678967 88.875 76.546875 L 89.888672 76.679688 L 89.96875 76.25 z M 89.888672 76.679688 L 89.876953 76.746094 L 90.332031 76.943359 L 90.404297 76.748047 L 89.888672 76.679688 z M 109.11523 76.349609 L 108.20312 77.339844 L 108.20312 79.025391 L 107.19922 79.025391 L 108.38477 80.015625 C 108.87101 79.883525 109.14429 79.717683 109.20508 79.519531 L 108.38477 78.132812 L 109.11523 77.339844 L 109.38867 77.439453 L 109.47852 76.546875 L 109.11523 76.349609 z M 84.953125 76.845703 L 84.498047 78.330078 L 86.230469 77.339844 C 85.804996 77.273798 85.3786 77.109903 84.953125 76.845703 z M 104.83008 77.142578 L 104.91992 77.240234 L 104.83008 77.439453 L 104.64648 77.240234 L 104.83008 77.142578 z M 60.792969 77.736328 L 60.974609 77.736328 C 61.460862 78.000528 61.796169 78.265097 61.978516 78.529297 L 61.613281 78.826172 C 61.370156 78.958264 61.156956 79.025391 60.974609 79.025391 L 60.792969 79.025391 L 60.792969 77.736328 z M 104.46484 77.736328 L 104.64648 77.935547 L 104.46484 78.033203 L 104.37305 77.935547 L 104.46484 77.736328 z M 93.796875 78.429688 L 93.888672 78.529297 L 93.796875 78.726562 L 93.615234 78.529297 L 93.796875 78.429688 z M 90.150391 78.726562 L 90.332031 78.925781 L 90.150391 79.025391 L 90.058594 78.925781 L 90.150391 78.726562 z M 88.509766 80.015625 L 89.695312 80.015625 L 89.511719 80.808594 C 89.633275 81.204894 89.724366 81.599793 89.785156 81.996094 C 89.906727 82.392394 90.028834 82.755689 90.150391 83.085938 L 89.056641 83.285156 C 89.299766 83.417256 89.512966 83.74699 89.695312 84.275391 C 89.93844 84.803791 90.058594 85.232248 90.058594 85.5625 L 88.783203 85.5625 L 89.421875 84.671875 L 87.962891 84.275391 L 87.962891 83.285156 L 86.685547 82.689453 L 88.509766 81.898438 L 88.509766 81.302734 L 89.421875 81.699219 L 89.421875 81.402344 L 88.509766 80.3125 L 88.509766 80.015625 z M 59.880859 80.3125 L 60.519531 80.808594 L 60.154297 81.005859 L 59.880859 81.005859 L 59.880859 80.3125 z M 91.0625 80.3125 L 91.244141 80.509766 L 91.0625 80.708984 L 90.970703 80.509766 L 91.0625 80.3125 z M 97.535156 80.412109 C 97.899848 80.610261 98.295228 80.708984 98.720703 80.708984 L 98.720703 81.402344 L 98.720703 82.392578 L 98.416016 82.126953 C 98.345375 82.347806 98.294663 82.568209 98.265625 82.789062 L 97.714844 81.654297 C 97.44245 81.813481 97.168876 81.898438 96.896484 81.898438 C 97.095076 81.596318 97.25232 81.277588 97.373047 80.947266 L 97.353516 80.90625 L 97.384766 80.917969 C 97.444032 80.752209 97.495398 80.584936 97.535156 80.412109 z M 97.384766 80.917969 C 97.381236 80.927842 97.376646 80.937418 97.373047 80.947266 L 97.714844 81.654297 C 97.746361 81.635879 97.777076 81.620159 97.808594 81.599609 L 98.416016 82.126953 C 98.49326 81.885455 98.593715 81.643841 98.720703 81.402344 L 97.384766 80.917969 z M 113.94727 80.412109 L 113.76367 81.005859 L 114.2207 81.205078 L 114.40234 80.609375 L 113.94727 80.412109 z M 87.232422 81.005859 L 87.324219 81.205078 L 87.232422 81.302734 L 87.050781 81.205078 L 87.232422 81.005859 z M 61.978516 81.205078 L 61.978516 82.095703 C 61.856945 82.029654 61.734838 81.86381 61.613281 81.599609 L 61.978516 81.205078 z M 60.337891 81.302734 L 60.519531 81.501953 L 60.337891 81.599609 L 60.154297 81.501953 L 60.337891 81.302734 z M 60.974609 81.302734 L 61.066406 81.501953 L 60.974609 81.599609 L 60.792969 81.501953 L 60.974609 81.302734 z M 60.064453 81.898438 L 61.066406 82.392578 C 61.066406 82.59073 60.975316 82.689453 60.792969 82.689453 L 60.064453 81.898438 z M 96.076172 81.996094 L 97.171875 82.392578 C 97.050311 82.656786 96.866175 82.856189 96.623047 82.988281 L 96.167969 82.492188 L 96.076172 81.996094 z M 99.085938 82.294922 L 99.541016 82.294922 L 100.45312 82.689453 L 100.81836 83.285156 L 100.81836 83.582031 L 100.17969 83.582031 C 99.693432 83.582031 99.206959 83.449747 98.720703 83.185547 L 99.085938 82.294922 z M 86.046875 83.582031 L 86.595703 83.582031 C 86.77805 83.582031 87.051324 83.647205 87.416016 83.779297 L 87.505859 84.474609 L 87.416016 84.96875 C 86.92976 85.23294 86.565393 85.398785 86.322266 85.464844 L 87.324219 85.958984 L 87.324219 86.951172 C 87.02031 87.149329 86.71602 87.31322 86.412109 87.445312 C 86.108201 87.57742 85.803911 87.709705 85.5 87.841797 L 85.318359 87.841797 L 85.773438 86.355469 L 86.503906 86.554688 L 86.138672 85.265625 L 86.958984 84.375 L 86.046875 83.582031 z M 98.447266 83.878906 C 98.568829 83.944958 98.751013 84.1108 98.994141 84.375 L 98.539062 84.572266 L 98.082031 84.572266 C 98.203588 84.440173 98.265625 84.34145 98.265625 84.275391 C 98.326391 84.143283 98.386475 84.010998 98.447266 83.878906 z M 71.550781 84.275391 L 71.369141 84.771484 L 71.916016 84.96875 L 72.007812 84.474609 L 71.550781 84.275391 z M 68.542969 84.474609 L 68.542969 84.572266 L 68.542969 84.474609 z M 101.54688 84.572266 L 101.63867 84.771484 L 101.54688 84.869141 L 101.36523 84.771484 L 101.54688 84.572266 z M 47.755859 85.861328 L 47.9375 86.058594 L 47.755859 86.158203 L 47.664062 86.058594 L 47.755859 85.861328 z M 82.947266 86.058594 C 82.947266 86.256745 82.918212 86.487753 82.857422 86.751953 C 82.796656 86.950102 82.734619 87.149505 82.673828 87.347656 L 81.853516 87.347656 C 81.853516 87.017405 81.762425 86.685718 81.580078 86.355469 L 82.037109 86.851562 L 82.947266 86.058594 z M 55.230469 86.158203 L 55.414062 86.355469 L 55.230469 86.554688 L 55.140625 86.355469 L 55.230469 86.158203 z M 56.78125 86.158203 L 56.873047 86.355469 L 56.78125 86.554688 L 56.599609 86.355469 L 56.78125 86.158203 z M 89.330078 86.158203 L 89.421875 86.355469 L 89.330078 86.554688 L 89.148438 86.355469 L 89.330078 86.158203 z M 99.814453 86.455078 L 100.81836 86.851562 L 100.81836 87.544922 L 99.541016 86.751953 L 99.814453 86.455078 z M 107.10938 86.455078 L 106.74414 87.048828 L 107.29102 87.445312 L 107.56445 86.554688 L 107.10938 86.455078 z M 65.716797 86.554688 L 65.898438 86.654297 L 65.716797 86.851562 L 65.533203 86.654297 L 65.716797 86.554688 z M 110.57422 86.654297 L 110.20898 87.742188 L 111.21094 88.041016 L 111.57617 87.048828 L 110.57422 86.654297 z M 98.447266 86.851562 L 98.890625 87.884766 C 99.404839 88.00281 99.816331 88.120249 100.08789 88.238281 L 100.45312 88.535156 L 99.541016 88.535156 C 99.401501 88.535156 99.191574 88.485531 98.945312 88.408203 L 97.787109 88.978516 C 98.232247 88.824112 98.559739 88.734375 98.720703 88.734375 L 99.267578 88.734375 L 99.267578 89.03125 L 98.720703 89.724609 C 97.930537 89.922758 97.321957 90.418337 96.896484 91.210938 L 96.167969 90.417969 L 97.261719 90.417969 L 96.533203 89.427734 C 96.662523 89.357466 96.831192 89.287061 97.029297 89.216797 L 96.441406 87.941406 L 98.447266 86.851562 z M 97.029297 89.216797 L 97.080078 89.328125 L 97.787109 88.978516 C 97.727976 88.999027 97.691102 89.008014 97.626953 89.03125 C 97.399337 89.093082 97.203639 89.154961 97.029297 89.216797 z M 98.945312 88.408203 L 99.085938 88.337891 L 98.890625 87.884766 C 98.82937 87.870704 98.785402 87.855859 98.720703 87.841797 L 98.720703 88.337891 C 98.806363 88.368917 98.869713 88.384464 98.945312 88.408203 z M 53.316406 87.148438 L 53.681641 87.148438 C 53.316949 87.544746 53.012659 87.809314 52.769531 87.941406 L 52.861328 87.544922 L 53.316406 87.148438 z M 61.705078 87.148438 C 61.826642 87.214486 62.008825 87.380331 62.251953 87.644531 L 61.978516 87.841797 L 61.705078 87.841797 L 61.705078 87.148438 z M 115.86133 87.445312 L 114.40234 88.138672 L 116.13477 89.328125 L 113.49023 89.527344 L 113.85547 90.220703 L 115.04102 90.714844 L 116.4082 90.021484 L 116.4082 89.427734 C 116.4082 88.701185 116.22602 88.039764 115.86133 87.445312 z M 108.56836 87.644531 L 107.19922 88.4375 C 107.62469 88.8338 108.20226 89.129974 108.93164 89.328125 L 108.93164 88.4375 L 108.56836 87.644531 z M 118.7793 87.644531 L 118.5957 88.138672 L 119.05273 88.337891 L 119.23438 87.841797 L 118.7793 87.644531 z M 66.902344 87.841797 L 67.083984 87.941406 L 66.902344 88.138672 L 66.71875 87.941406 L 66.902344 87.841797 z M 88.052734 87.841797 L 88.236328 87.941406 L 88.052734 88.138672 L 87.962891 87.941406 L 88.052734 87.841797 z M 48.119141 88.138672 L 48.941406 88.138672 L 48.576172 88.535156 C 48.333051 88.403048 48.179931 88.270764 48.119141 88.138672 z M 59.880859 88.238281 L 60.519531 88.337891 L 61.066406 88.734375 C 60.884062 89.064632 60.610785 89.295642 60.246094 89.427734 L 59.880859 88.734375 C 59.637732 88.998565 59.395471 89.162456 59.152344 89.228516 L 59.880859 88.238281 z M 61.613281 88.337891 C 61.856409 88.602099 62.069606 88.799548 62.251953 88.931641 L 62.160156 89.130859 C 61.856255 88.80061 61.674072 88.536042 61.613281 88.337891 z M 50.763672 88.4375 L 50.945312 88.634766 L 50.763672 88.734375 L 50.671875 88.634766 L 50.763672 88.4375 z M 62.890625 88.4375 L 63.345703 88.4375 L 64.166016 88.832031 C 64.34837 89.096231 64.50149 89.394358 64.623047 89.724609 C 64.805384 90.054858 64.925538 90.384592 64.986328 90.714844 L 66.171875 90.318359 C 66.293439 90.384408 66.475622 90.550253 66.71875 90.814453 C 66.414842 91.078653 66.141565 91.37678 65.898438 91.707031 C 65.412182 91.24468 64.925709 90.814269 64.439453 90.417969 C 64.013978 89.95562 63.558521 89.52716 63.072266 89.130859 L 62.890625 88.734375 L 62.890625 88.4375 z M 70.822266 88.4375 L 70.914062 88.634766 L 70.822266 88.734375 L 70.638672 88.634766 L 70.822266 88.4375 z M 91.701172 88.4375 L 91.791016 88.634766 L 91.701172 88.734375 L 91.517578 88.634766 L 91.701172 88.4375 z M 103.91797 88.4375 C 103.61406 89.031952 103.30977 89.626254 103.00586 90.220703 C 102.70195 90.749104 102.48875 91.375011 102.36719 92.101562 L 102.64062 92.597656 C 103.12688 92.069256 103.46218 91.640796 103.64453 91.310547 C 103.82688 90.914247 103.97805 90.351552 104.09961 89.625 L 103.91797 88.4375 z M 73.740234 88.734375 L 73.921875 88.931641 L 73.740234 89.130859 L 73.648438 88.931641 L 73.740234 88.734375 z M 51.494141 89.03125 C 51.858839 89.42755 52.1011 89.757284 52.222656 90.021484 L 51.857422 89.921875 L 51.494141 89.427734 L 51.494141 89.03125 z M 95.712891 89.130859 L 96.259766 89.130859 L 96.259766 89.427734 C 96.259766 89.493767 96.228759 89.558941 96.167969 89.625 L 96.167969 89.724609 C 95.924848 89.460401 95.773681 89.262952 95.712891 89.130859 z M 62.525391 89.328125 C 62.646947 89.394184 62.738038 89.492908 62.798828 89.625 C 62.920399 89.691059 63.042506 89.789783 63.164062 89.921875 L 63.072266 90.121094 C 62.768364 89.790845 62.586181 89.526276 62.525391 89.328125 z M 53.955078 89.724609 L 54.228516 89.724609 L 54.501953 89.921875 C 54.258818 90.186065 54.076635 90.35191 53.955078 90.417969 L 53.955078 89.724609 z M 55.140625 89.724609 C 55.262189 89.790658 55.444372 89.956503 55.6875 90.220703 L 55.414062 90.417969 L 55.140625 90.417969 L 55.140625 89.724609 z M 77.113281 89.724609 L 77.933594 89.724609 L 77.660156 89.921875 L 77.478516 90.220703 C 77.356945 89.956513 77.234838 89.790669 77.113281 89.724609 z M 95.529297 89.724609 L 95.712891 89.921875 L 95.529297 90.121094 L 95.439453 89.921875 L 95.529297 89.724609 z M 52.951172 90.121094 L 53.681641 90.121094 L 53.316406 90.517578 L 52.951172 90.121094 z M 58.513672 90.121094 L 58.695312 90.220703 L 58.513672 90.417969 L 58.421875 90.220703 L 58.513672 90.121094 z M 62.707031 90.417969 L 62.890625 90.517578 L 62.707031 90.714844 L 62.617188 90.517578 L 62.707031 90.417969 z M 67.8125 90.417969 L 67.904297 90.517578 L 67.8125 90.714844 L 67.630859 90.517578 L 67.8125 90.417969 z M 68.998047 90.714844 L 69.181641 90.914062 L 68.998047 91.011719 L 68.816406 90.914062 L 68.998047 90.714844 z M 72.007812 90.714844 L 72.097656 90.914062 L 72.007812 91.011719 L 71.824219 90.914062 L 72.007812 90.714844 z M 53.5 90.914062 L 54.228516 91.507812 L 54.046875 91.707031 L 53.5 90.914062 z M 55.414062 90.914062 L 55.414062 91.804688 L 55.048828 91.408203 L 55.414062 90.914062 z M 47.208984 91.011719 L 47.208984 92.597656 C 47.330555 92.729746 47.450709 92.828472 47.572266 92.894531 C 47.693837 92.96059 47.815943 93.059314 47.9375 93.191406 L 48.941406 92.003906 L 48.029297 92.003906 L 47.208984 91.011719 z M 91.791016 91.011719 L 92.248047 91.111328 L 92.703125 91.507812 C 92.520778 91.904113 92.187427 92.233847 91.701172 92.498047 C 91.761955 92.233847 91.913122 91.971231 92.15625 91.707031 L 91.335938 91.507812 L 91.791016 91.011719 z M 71.095703 91.111328 L 72.644531 91.408203 L 73.009766 92.994141 L 75.927734 91.310547 L 76.292969 91.507812 L 76.566406 92.101562 L 76.566406 92.300781 L 78.025391 92.300781 L 79.027344 93.984375 L 79.847656 92.796875 L 80.121094 93.191406 L 80.759766 93.490234 L 81.214844 93.490234 L 81.214844 93.587891 L 81.306641 94.777344 L 81.671875 94.876953 L 81.945312 95.273438 L 81.945312 95.570312 L 80.486328 95.570312 L 80.121094 94.876953 L 81.214844 93.587891 C 80.728588 93.78605 80.244068 93.951892 79.757812 94.083984 C 79.332338 94.216084 78.966018 94.545818 78.662109 95.074219 L 77.660156 93.886719 L 78.572266 94.380859 L 77.021484 92.994141 L 77.203125 93.587891 L 77.660156 94.083984 C 77.356248 93.951876 76.960865 93.819592 76.474609 93.6875 C 76.049135 93.489349 75.684768 93.424175 75.380859 93.490234 L 75.654297 92.697266 L 74.195312 92.400391 C 74.134546 92.532499 74.074462 92.664783 74.013672 92.796875 C 73.952881 92.928983 73.921875 93.059314 73.921875 93.191406 C 73.43562 93.125373 72.949146 93.158923 72.462891 93.291016 C 71.976635 93.357075 71.521178 93.455798 71.095703 93.587891 L 72.462891 92.597656 L 71.1875 91.408203 L 70.457031 91.804688 L 71.095703 91.111328 z M 67.449219 91.310547 C 67.509985 91.442639 67.570069 91.541363 67.630859 91.607422 C 67.752416 91.607422 67.8125 91.672595 67.8125 91.804688 C 67.8125 91.93678 67.692346 92.003906 67.449219 92.003906 C 67.266867 92.003906 67.113744 92.035503 66.992188 92.101562 L 67.722656 92.994141 L 68.908203 92.994141 L 68.542969 93.587891 L 69.728516 94.976562 C 69.24226 95.372871 68.693757 95.635486 68.085938 95.767578 C 67.782027 95.635481 67.448676 95.471589 67.083984 95.273438 C 66.780076 95.075286 66.475786 94.844278 66.171875 94.580078 L 66.171875 94.28125 L 65.078125 94.28125 L 65.990234 94.976562 L 65.259766 94.976562 L 65.259766 95.669922 L 65.806641 95.273438 L 65.533203 96.263672 L 64.623047 95.470703 L 64.804688 96.857422 C 64.379213 96.989522 63.923755 96.857238 63.4375 96.460938 L 63.4375 95.669922 C 63.619847 95.669922 63.831091 95.636372 64.074219 95.570312 C 64.378127 95.438194 64.622341 95.404644 64.804688 95.470703 C 64.743897 95.140452 64.712891 94.644876 64.712891 93.984375 L 65.259766 93.984375 L 64.896484 93.291016 L 66.71875 92.498047 L 66.537109 91.707031 L 67.449219 91.310547 z M 56.234375 91.408203 L 56.962891 91.408203 L 56.599609 91.804688 L 56.234375 91.408203 z M 64.53125 91.408203 L 64.623047 91.507812 L 64.53125 91.707031 L 64.349609 91.507812 L 64.53125 91.408203 z M 68.359375 91.408203 L 68.542969 91.507812 L 68.359375 91.707031 L 68.269531 91.507812 L 68.359375 91.408203 z M 89.148438 91.408203 C 89.452348 91.540303 89.754685 91.706145 90.058594 91.904297 C 89.937037 91.904297 89.845947 91.937847 89.785156 92.003906 L 89.603516 92.003906 L 89.148438 92.003906 L 89.148438 91.408203 z M 92.886719 91.408203 L 93.707031 91.408203 L 93.341797 91.804688 L 92.886719 91.408203 z M 61.521484 91.707031 L 61.705078 91.904297 L 61.521484 92.003906 L 61.339844 91.904297 L 61.521484 91.707031 z M 65.078125 91.707031 L 65.259766 91.904297 L 65.078125 92.003906 L 64.986328 91.904297 L 65.078125 91.707031 z M 69.544922 91.707031 L 69.728516 91.904297 L 69.544922 92.003906 L 69.455078 91.904297 L 69.544922 91.707031 z M 65.716797 92.003906 L 65.898438 92.201172 L 65.716797 92.300781 L 65.533203 92.201172 L 65.716797 92.003906 z M 76.019531 92.003906 L 76.019531 92.300781 L 76.292969 92.796875 L 76.566406 92.994141 L 76.566406 92.400391 L 76.019531 92.003906 z M 88.691406 92.003906 L 88.875 92.201172 L 88.691406 92.300781 L 88.509766 92.201172 L 88.691406 92.003906 z M 62.890625 92.201172 L 63.710938 92.201172 L 64.986328 92.300781 L 65.351562 92.498047 L 64.074219 93.09375 L 64.074219 94.083984 C 63.952655 93.753735 63.710395 93.489167 63.345703 93.291016 C 63.041792 93.026815 62.890625 92.730642 62.890625 92.400391 L 62.890625 92.201172 z M 92.703125 92.201172 L 92.703125 92.894531 L 92.611328 93.6875 L 91.974609 93.490234 L 91.517578 92.894531 L 91.517578 92.597656 L 92.15625 93.09375 L 92.703125 92.201172 z M 69.544922 92.300781 L 69.728516 92.498047 L 69.544922 92.697266 L 69.455078 92.498047 L 69.544922 92.300781 z M 121.33203 92.498047 C 120.72421 92.894347 120.23774 93.226034 119.87305 93.490234 C 119.50836 93.754435 119.20407 94.01705 118.96094 94.28125 C 118.71781 94.54545 118.47555 94.908745 118.23242 95.371094 C 118.05007 95.767394 117.83688 96.330089 117.59375 97.056641 L 119.69141 99.433594 L 123.24609 96.857422 L 123.97656 97.353516 L 126.25586 96.164062 L 124.52344 94.380859 L 122.60742 95.371094 L 121.87891 93.984375 C 121.63578 93.522026 121.4536 93.026447 121.33203 92.498047 z M 60.519531 92.597656 L 60.519531 92.697266 L 61.066406 92.697266 L 61.066406 92.994141 L 60.792969 93.291016 L 60.519531 93.291016 L 60.519531 92.697266 L 60.427734 92.697266 L 60.519531 92.597656 z M 96.259766 92.697266 L 96.533203 92.697266 L 96.533203 93.490234 L 96.441406 94.28125 L 96.259766 94.28125 C 96.016631 94.14915 95.834447 93.9517 95.712891 93.6875 C 95.773674 93.357248 95.955857 93.027515 96.259766 92.697266 z M 103.55273 92.894531 L 104.2832 94.380859 L 104.91992 94.083984 L 105.4668 93.390625 C 104.85898 93.060379 104.22134 92.894531 103.55273 92.894531 z M 69.818359 92.994141 L 70.914062 94.28125 L 70.730469 94.777344 L 70.457031 94.876953 L 69.818359 94.480469 L 69.818359 92.994141 z M 70.822266 92.994141 L 70.914062 93.191406 L 70.822266 93.291016 L 70.638672 93.191406 L 70.822266 92.994141 z M 89.603516 92.994141 L 90.423828 92.994141 L 90.058594 93.390625 L 89.603516 92.994141 z M 99.451172 93.191406 L 100.17969 93.787109 L 100.08789 93.984375 C 99.905546 93.852275 99.6943 93.587707 99.451172 93.191406 z M 55.869141 93.291016 L 56.052734 93.490234 L 55.869141 93.6875 L 55.6875 93.490234 L 55.869141 93.291016 z M 54.501953 93.587891 L 54.501953 93.886719 L 54.046875 94.380859 L 53.589844 94.580078 L 53.681641 94.183594 L 54.136719 93.6875 L 54.501953 93.587891 z M 51.037109 93.6875 L 51.220703 93.787109 L 51.037109 93.984375 L 50.945312 93.787109 L 51.037109 93.6875 z M 61.794922 93.6875 L 61.978516 93.787109 L 61.794922 93.984375 L 61.705078 93.787109 L 61.794922 93.6875 z M 56.599609 93.984375 L 57.509766 93.984375 L 57.509766 94.580078 L 56.599609 94.580078 L 56.599609 93.984375 z M 73.830078 93.984375 C 73.769288 94.116475 73.678197 94.282317 73.556641 94.480469 C 73.49585 94.612577 73.40476 94.744861 73.283203 94.876953 C 73.465557 95.009051 73.618678 95.172942 73.740234 95.371094 C 73.436326 95.305045 73.192113 95.141153 73.009766 94.876953 C 73.252893 94.546702 73.52617 94.248575 73.830078 93.984375 z M 74.103516 93.984375 C 74.589771 93.984375 75.016168 94.116659 75.380859 94.380859 L 76.65625 94.976562 L 77.203125 94.580078 L 77.203125 95.570312 L 76.839844 95.570312 C 77.082971 95.768472 77.325232 95.934314 77.568359 96.066406 C 77.811487 96.132465 78.084763 96.231189 78.388672 96.363281 L 77.203125 96.460938 L 76.019531 96.363281 L 76.382812 96.957031 L 77.476562 96.660156 L 78.480469 97.453125 C 77.994213 97.717323 77.629846 97.948333 77.386719 98.146484 L 76.474609 97.849609 C 76.900082 98.047758 77.233435 98.343934 77.476562 98.740234 C 77.415782 99.070486 77.204535 99.433778 76.839844 99.830078 L 76.65625 99.830078 L 77.021484 100.82031 C 77.143046 100.68822 77.296169 100.5895 77.478516 100.52344 C 77.66087 100.39133 77.812037 100.25905 77.933594 100.12695 L 78.207031 100.12695 L 77.478516 100.91992 L 77.478516 102.70312 L 76.566406 102.70312 L 76.929688 103.79297 L 76.019531 103 L 76.019531 104.28906 L 74.650391 103.89258 L 75.472656 104.98242 C 75.107965 104.91637 74.712582 104.75053 74.287109 104.48633 C 73.922418 104.15608 73.618128 103.8599 73.375 103.5957 C 73.557347 103.1994 73.648438 102.80255 73.648438 102.40625 L 74.376953 103 C 74.5593 102.80185 74.832574 102.63796 75.197266 102.50586 L 73.830078 102.10938 C 73.58695 102.30753 73.222584 102.67082 72.736328 103.19922 L 72.736328 102.60352 L 71.916016 103.39648 L 71.550781 102.90234 L 71.550781 104.88281 C 71.18609 104.94886 70.852739 105.11473 70.548828 105.37891 L 70.914062 103.99219 L 69.455078 103.99219 L 69.455078 103.79297 L 69.636719 103.29883 L 69.818359 103.09961 L 71.095703 103.79297 C 70.852578 103.59482 70.639378 103.36381 70.457031 103.09961 C 70.274677 102.83541 70.12351 102.57084 70.001953 102.30664 L 70.457031 102.10938 L 70.914062 102.10938 L 70.914062 101.41602 C 70.792491 101.48205 70.670385 101.54722 70.548828 101.61328 C 70.427257 101.67934 70.30515 101.71289 70.183594 101.71289 L 69.455078 100.0293 L 69.636719 99.730469 L 70.183594 99.433594 L 70.638672 99.433594 L 70.638672 99.037109 L 70.822266 98.542969 L 71.369141 99.037109 L 71.824219 99.929688 L 71.824219 100.12695 C 71.155619 100.12695 70.548992 100.02823 70.001953 99.830078 L 70.275391 100.62305 C 70.457735 100.55699 70.762025 100.52344 71.1875 100.52344 C 71.309057 100.72159 71.369141 100.9526 71.369141 101.2168 L 71.369141 101.8125 L 73.009766 102.00977 L 73.009766 101.41602 L 72.28125 101.41602 L 73.101562 100.82031 C 72.919218 100.82031 72.737034 100.78872 72.554688 100.72266 C 72.372336 100.65662 72.219213 100.5895 72.097656 100.52344 L 72.554688 99.433594 L 71.460938 98.740234 L 72.189453 97.849609 L 71.550781 97.849609 L 71.550781 97.253906 L 72.828125 97.650391 L 72.462891 96.560547 L 73.556641 97.15625 L 74.287109 94.677734 L 74.923828 95.074219 C 74.741484 94.611867 74.468207 94.248575 74.103516 93.984375 z M 97.169922 93.984375 C 97.71696 94.314627 98.143357 94.776645 98.447266 95.371094 C 98.751176 95.965546 99.055467 96.52824 99.359375 97.056641 C 99.906414 97.38689 100.33086 97.848908 100.63477 98.443359 C 100.20929 98.2452 99.78485 98.079358 99.359375 97.947266 C 98.994683 97.815158 98.5993 97.682873 98.173828 97.550781 L 98.447266 99.433594 L 97.808594 99.830078 C 97.261555 99.301678 96.683991 98.806102 96.076172 98.34375 C 95.529136 97.881401 95.013599 97.354217 94.527344 96.759766 C 94.588134 96.759766 94.679225 96.726215 94.800781 96.660156 C 94.983128 96.594123 95.074219 96.526997 95.074219 96.460938 C 95.074219 96.394878 95.012182 96.296155 94.890625 96.164062 C 94.829859 96.03197 94.769775 95.933247 94.708984 95.867188 L 94.34375 96.460938 L 92.064453 96.857422 L 92.794922 95.867188 L 92.064453 94.976562 C 92.368364 94.976562 92.672654 94.875886 92.976562 94.677734 L 93.341797 95.273438 L 95.074219 95.273438 L 95.074219 94.380859 L 97.169922 94.580078 L 97.169922 93.984375 z M 112.76172 94.380859 L 112.66992 94.876953 L 113.12695 95.074219 L 113.30859 94.480469 L 112.76172 94.380859 z M 71.734375 94.480469 L 72.462891 95.074219 L 72.28125 95.273438 L 71.916016 94.876953 C 71.855249 94.744845 71.795165 94.612561 71.734375 94.480469 z M 62.707031 94.580078 L 62.890625 94.777344 L 62.707031 94.976562 L 62.617188 94.777344 L 62.707031 94.580078 z M 64.257812 94.580078 L 64.349609 94.777344 L 64.257812 94.976562 L 64.074219 94.777344 L 64.257812 94.580078 z M 82.400391 94.580078 L 82.583984 94.777344 L 82.400391 94.976562 L 82.310547 94.777344 L 82.400391 94.580078 z M 99.359375 94.677734 L 99.90625 94.777344 L 100.17969 95.074219 L 99.724609 95.074219 L 99.359375 94.677734 z M 62.251953 94.876953 L 62.617188 95.570312 L 63.072266 95.273438 L 63.072266 96.560547 L 62.251953 95.767578 C 62.19117 95.96573 62.03805 96.196737 61.794922 96.460938 L 60.064453 94.976562 L 61.066406 94.976562 L 61.431641 95.669922 L 62.251953 94.876953 z M 71.369141 94.976562 L 71.550781 95.074219 L 71.369141 95.273438 L 71.1875 95.074219 L 71.369141 94.976562 z M 51.949219 95.273438 L 52.130859 95.470703 L 51.949219 95.570312 L 51.857422 95.470703 L 51.949219 95.273438 z M 111.84961 95.273438 C 111.24179 95.537638 110.7243 95.867372 110.29883 96.263672 C 109.87336 96.593921 109.4179 96.990773 108.93164 97.453125 L 109.57031 98.939453 C 110.05657 98.807361 110.54304 98.706684 111.0293 98.640625 C 111.51555 98.508533 112.00203 98.376249 112.48828 98.244141 L 112.94336 99.037109 L 113.03516 98.640625 C 113.03516 98.046173 112.91305 97.485432 112.66992 96.957031 C 112.42679 96.362582 112.15352 95.801838 111.84961 95.273438 z M 66.445312 95.570312 C 66.749221 95.702413 67.022497 95.868255 67.265625 96.066406 C 67.508753 96.198504 67.782027 96.362395 68.085938 96.560547 L 66.628906 96.164062 L 67.357422 97.15625 C 67.60055 96.958099 67.90484 96.857422 68.269531 96.857422 C 68.634223 97.25372 69.029606 97.551849 69.455078 97.75 C 69.637425 98.080252 69.728516 98.443543 69.728516 98.839844 L 69.455078 98.839844 C 68.968823 98.377492 68.57344 97.848355 68.269531 97.253906 C 67.96562 97.452058 67.66133 97.550781 67.357422 97.550781 L 66.353516 96.957031 L 66.263672 96.263672 L 66.445312 95.570312 z M 80.03125 95.570312 L 80.212891 95.767578 L 80.03125 95.867188 L 79.847656 95.767578 L 80.03125 95.570312 z M 54.501953 95.867188 C 54.62351 95.933247 54.7146 96.03197 54.775391 96.164062 C 54.896962 96.230122 55.019068 96.328845 55.140625 96.460938 C 55.079859 96.460937 55.017822 96.494488 54.957031 96.560547 L 54.775391 96.560547 L 54.501953 96.560547 L 54.501953 95.867188 z M 70.822266 95.867188 L 70.914062 96.066406 L 70.822266 96.263672 L 70.638672 96.066406 L 70.822266 95.867188 z M 71.642578 95.867188 L 71.824219 96.066406 L 71.642578 96.263672 L 71.550781 96.066406 L 71.642578 95.867188 z M 81.488281 95.867188 L 81.671875 96.066406 L 81.488281 96.263672 L 81.398438 96.066406 L 81.488281 95.867188 z M 87.505859 95.966797 L 87.962891 96.263672 L 88.236328 97.056641 L 88.236328 97.353516 C 88.114767 97.155367 87.961644 96.957917 87.779297 96.759766 C 87.65774 96.495565 87.56665 96.230997 87.505859 95.966797 z M 56.326172 96.263672 L 57.328125 96.660156 L 56.326172 96.957031 L 56.326172 97.550781 L 55.6875 97.15625 C 55.991416 96.958101 56.204615 96.659972 56.326172 96.263672 z M 82.673828 96.263672 L 82.857422 96.363281 L 82.673828 96.560547 L 82.583984 96.363281 L 82.673828 96.263672 z M 83.130859 96.460938 L 83.585938 96.857422 L 84.042969 97.75 L 84.042969 98.146484 C 83.678277 97.948333 83.311957 97.849609 82.947266 97.849609 C 82.825702 98.047758 82.643518 98.245208 82.400391 98.443359 C 82.218046 98.575467 82.035863 98.707752 81.853516 98.839844 L 81.671875 98.34375 L 81.671875 97.849609 L 82.765625 97.849609 L 83.130859 96.460938 z M 62.34375 96.660156 L 63.253906 96.957031 C 63.010779 97.155183 62.586335 97.38619 61.978516 97.650391 L 61.521484 97.15625 L 62.34375 96.660156 z M 70.275391 96.857422 L 70.638672 97.056641 L 70.914062 97.75 L 70.914062 98.146484 L 70.275391 98.146484 C 70.093044 97.882284 70.001953 97.55255 70.001953 97.15625 L 70.275391 96.857422 z M 89.603516 96.857422 L 89.695312 97.056641 L 89.603516 97.253906 L 89.421875 97.056641 L 89.603516 96.857422 z M 93.796875 96.857422 L 93.888672 97.056641 L 93.796875 97.253906 L 93.615234 97.056641 L 93.796875 96.857422 z M 56.962891 97.253906 L 57.509766 97.253906 L 58.058594 97.353516 C 57.997813 97.617716 58.088904 98.178457 58.332031 99.037109 C 58.635949 99.82971 58.847193 100.39241 58.96875 100.72266 L 58.148438 100.82031 L 57.328125 100.72266 L 57.966797 100.32617 L 58.148438 98.640625 L 57.419922 98.640625 L 56.599609 98.740234 L 55.595703 97.947266 C 56.142742 98.145417 56.720306 98.244141 57.328125 98.244141 L 56.962891 97.253906 z M 60.427734 97.453125 C 60.549291 97.519184 60.640381 97.617908 60.701172 97.75 C 60.822743 97.816059 60.94485 97.914783 61.066406 98.046875 L 60.974609 98.146484 L 60.611328 97.849609 L 60.427734 97.453125 z M 64.349609 97.453125 L 64.349609 98.146484 C 64.349609 98.344633 64.318603 98.542083 64.257812 98.740234 C 64.379369 98.674201 64.47046 98.609028 64.53125 98.542969 L 64.896484 98.146484 C 65.321959 98.278584 65.65531 98.476034 65.898438 98.740234 C 65.594527 98.806294 65.321253 98.839844 65.078125 98.839844 L 64.257812 98.839844 C 64.318603 99.037993 64.349609 99.367726 64.349609 99.830078 L 65.716797 99.632812 C 65.838368 99.897013 65.958522 100.15963 66.080078 100.42383 C 66.201635 100.68803 66.292725 100.9526 66.353516 101.2168 L 66.263672 101.61328 L 65.625 101.61328 L 64.986328 101.51367 L 64.986328 101.8125 C 65.168673 102.2088 65.381872 102.6037 65.625 103 C 65.868128 103.33025 66.141402 103.66194 66.445312 103.99219 C 66.445313 104.45454 66.414306 104.78427 66.353516 104.98242 L 65.259766 105.37891 C 65.138195 105.04865 65.018041 104.71892 64.896484 104.38867 C 64.835694 104.05842 64.744603 103.72673 64.623047 103.39648 C 64.50149 103.46252 64.4104 103.52964 64.349609 103.5957 C 64.288819 103.66176 64.195775 103.69336 64.074219 103.69336 L 64.439453 102.70312 L 63.4375 103.09961 L 63.800781 103.89258 L 63.800781 104.38867 L 63.527344 104.38867 C 63.223433 104.05842 62.950159 103.72673 62.707031 103.39648 L 61.978516 102.40625 C 62.464771 102.53835 62.920228 102.76936 63.345703 103.09961 L 63.527344 102.90234 C 63.041088 102.30789 62.525554 101.7452 61.978516 101.2168 L 62.34375 100.22656 L 61.066406 99.830078 L 61.066406 99.136719 L 62.707031 99.533203 L 62.34375 99.037109 C 62.647661 98.640801 62.981012 98.376233 63.345703 98.244141 C 63.710395 98.045989 64.045701 97.783374 64.349609 97.453125 z M 59.607422 98.146484 L 59.880859 98.146484 L 60.154297 98.443359 L 59.880859 99.037109 L 59.607422 99.236328 L 59.607422 98.146484 z M 117.04688 98.34375 L 116.86328 98.839844 L 117.32031 99.037109 L 117.50195 98.443359 L 117.04688 98.34375 z M 47.208984 98.542969 L 47.208984 99.136719 L 47.390625 99.236328 L 47.390625 98.542969 L 47.208984 98.542969 z M 95.802734 98.542969 C 96.106643 98.807169 96.379919 99.103342 96.623047 99.433594 C 96.440702 99.697794 96.229456 99.962362 95.986328 100.22656 C 95.803981 100.49077 95.559768 100.68822 95.255859 100.82031 L 95.166016 99.929688 L 95.255859 99.037109 C 95.498994 98.772917 95.681178 98.609028 95.802734 98.542969 z M 81.306641 98.640625 L 81.306641 99.333984 C 81.306641 99.532133 81.337647 99.731536 81.398438 99.929688 L 81.033203 99.533203 L 81.306641 98.640625 z M 78.845703 98.839844 C 79.149612 98.905903 79.453902 99.004627 79.757812 99.136719 C 80.061721 99.268827 80.364058 99.401111 80.667969 99.533203 L 80.667969 101.11914 L 80.304688 101.01953 C 80.183124 100.68928 79.969926 100.29243 79.666016 99.830078 C 79.422888 99.367729 79.149612 99.037995 78.845703 98.839844 z M 67.265625 99.037109 L 69.181641 99.236328 C 68.938513 99.500518 68.6943 99.66441 68.451172 99.730469 L 69.181641 100.82031 L 68.269531 100.82031 L 68.085938 100.32617 L 67.630859 99.830078 L 67.265625 99.830078 L 67.722656 101.11914 L 66.537109 100.32617 C 66.537109 99.929872 66.6282 99.566577 66.810547 99.236328 L 67.265625 99.037109 z M 57.328125 99.136719 L 57.509766 99.333984 L 57.328125 99.433594 L 57.236328 99.333984 L 57.328125 99.136719 z M 77.751953 99.136719 C 77.934298 99.20277 78.145542 99.368604 78.388672 99.632812 L 78.115234 99.830078 L 77.751953 99.830078 L 77.751953 99.136719 z M 98.628906 99.136719 L 99.175781 99.333984 L 98.994141 99.929688 L 98.447266 99.730469 L 98.628906 99.136719 z M 115.13281 99.136719 C 115.01126 99.46697 114.88915 99.830262 114.76758 100.22656 C 114.70679 100.55681 114.6157 100.88655 114.49414 101.2168 C 114.6157 101.34889 114.76686 101.48117 114.94922 101.61328 C 115.13157 101.67934 115.28469 101.77806 115.40625 101.91016 L 116.77344 101.11914 L 115.13281 99.136719 z M 47.298828 99.333984 L 47.664062 100.32617 L 48.576172 100.32617 L 48.576172 100.0293 L 48.392578 99.830078 C 48.210231 99.764045 47.998987 99.698872 47.755859 99.632812 C 47.573508 99.566753 47.420385 99.466077 47.298828 99.333984 z M 47.664062 100.32617 C 47.603296 100.19406 47.543212 100.06178 47.482422 99.929688 C 47.421631 99.797595 47.330541 99.698872 47.208984 99.632812 L 47.208984 100.32617 L 47.664062 100.32617 z M 88.417969 99.830078 L 88.783203 100.22656 L 87.871094 100.91992 L 87.689453 101.2168 L 87.962891 101.71289 L 86.503906 101.11914 L 86.138672 101.51367 L 86.685547 102.60352 L 85.5 102.60352 C 85.135308 102.60352 84.741879 102.63707 84.316406 102.70312 L 84.589844 101.51367 L 84.863281 101.11914 C 85.106409 101.31729 85.408746 101.41602 85.773438 101.41602 L 88.417969 99.830078 z M 105.83203 99.830078 C 105.34578 99.96217 104.83024 100.06089 104.2832 100.12695 C 103.79695 100.19301 103.31047 100.26014 102.82422 100.32617 L 103.91797 102.70312 L 106.19727 102.40625 L 106.19727 103.29883 L 106.10547 104.18945 L 104.64648 103.99219 L 104.00977 106.86523 L 105.92383 106.86523 L 109.38867 105.08203 L 109.47852 104.18945 L 108.6582 104.08984 L 107.47266 103.29883 L 107.10938 102.60352 C 108.50736 102.40536 109.81366 102.10919 111.0293 101.71289 L 109.38867 100.52344 L 108.47656 102.10938 C 108.05109 101.64703 107.65571 101.18501 107.29102 100.72266 C 106.92632 100.26031 106.43985 99.962178 105.83203 99.830078 z M 62.798828 99.929688 C 62.920399 100.45809 63.042506 100.95366 63.164062 101.41602 C 63.285634 101.87836 63.405787 102.34038 63.527344 102.80273 L 63.710938 102.60352 C 63.771728 102.53748 63.800781 102.47231 63.800781 102.40625 L 64.623047 102.40625 L 64.623047 101.41602 L 63.800781 101.41602 C 63.618437 101.21786 63.436253 100.98686 63.253906 100.72266 C 63.071554 100.45846 62.920385 100.19389 62.798828 99.929688 z M 79.574219 100.0293 L 79.574219 100.91992 L 79.208984 100.42383 L 79.574219 100.0293 z M 85.683594 100.12695 L 85.865234 100.32617 L 85.683594 100.42383 L 85.591797 100.32617 L 85.683594 100.12695 z M 78.388672 100.82031 L 79.574219 101.2168 L 79.574219 102.10938 L 78.115234 102.10938 C 77.993671 101.84517 77.842503 101.58061 77.660156 101.31641 L 78.662109 101.71289 C 78.479762 101.44869 78.388672 101.15056 78.388672 100.82031 z M 88.509766 101.11914 L 89.148438 101.11914 L 89.148438 101.41602 L 88.964844 101.71289 C 88.721726 101.44868 88.570556 101.25123 88.509766 101.11914 z M 66.71875 101.41602 C 67.022661 101.54811 67.326951 101.712 67.630859 101.91016 L 67.175781 102.10938 L 66.71875 102.10938 L 66.71875 101.41602 z M 80.121094 102.10938 L 81.033203 102.10938 L 81.033203 102.40625 L 80.759766 102.70312 L 80.304688 102.40625 L 80.121094 102.10938 z M 78.662109 102.40625 L 79.208984 102.40625 C 79.452112 102.40625 79.696325 102.47142 79.939453 102.60352 L 80.212891 102.90234 L 79.392578 103 L 78.662109 103 L 78.662109 102.40625 z M 68.085938 102.70312 L 68.269531 102.90234 L 68.085938 103 L 67.904297 102.90234 L 68.085938 102.70312 z M 89.330078 102.70312 L 89.421875 102.90234 L 89.330078 103 L 89.148438 102.90234 L 89.330078 102.70312 z M 90.242188 103 L 90.607422 104.08984 L 89.96875 103.5957 L 90.242188 103 z M 84.224609 103.29883 C 84.34618 103.36489 84.468287 103.46361 84.589844 103.5957 C 84.711415 103.66176 84.831568 103.76049 84.953125 103.89258 L 84.863281 103.99219 C 84.620154 103.79404 84.406956 103.56303 84.224609 103.29883 z M 61.431641 103.39648 L 61.978516 103.5957 C 62.707899 104.1241 63.314526 104.71841 63.800781 105.37891 C 63.983128 105.18075 64.227342 105.0149 64.53125 104.88281 L 63.800781 105.67578 L 61.705078 105.08203 L 60.701172 103.99219 L 61.431641 103.39648 z M 111.66797 103.49609 L 111.48438 103.89258 L 112.03125 103.69336 L 111.66797 103.49609 z M 83.404297 103.5957 L 84.498047 104.08984 L 83.404297 104.38867 L 83.404297 104.38672 C 83.343506 104.32069 83.252416 104.25551 83.130859 104.18945 C 83.070069 104.12342 83.039062 104.05825 83.039062 103.99219 C 83.039062 103.92615 83.070069 103.85903 83.130859 103.79297 C 83.252416 103.72694 83.343506 103.66176 83.404297 103.5957 z M 67.539062 103.69336 L 67.630859 103.89258 L 67.539062 103.99219 L 67.357422 103.89258 L 67.539062 103.69336 z M 78.388672 103.79297 L 78.115234 104.18945 L 77.751953 104.18945 C 77.995078 103.92525 78.206325 103.79297 78.388672 103.79297 z M 77.203125 103.89258 L 77.203125 104.7832 L 76.839844 104.38867 L 77.203125 103.89258 z M 99.541016 103.89258 L 99.085938 104.68555 L 98.902344 106.17188 L 99.90625 106.46875 L 100.54492 106.17188 L 99.541016 103.89258 z M 81.488281 103.99219 L 81.671875 104.18945 L 81.488281 104.38867 L 81.398438 104.18945 L 81.488281 103.99219 z M 81.580078 104.68555 L 82.400391 104.68555 C 82.157273 104.81765 82.006103 104.94994 81.945312 105.08203 L 81.580078 104.68555 z M 102.45898 104.88281 C 102.21586 105.34516 102.06469 105.80718 102.00391 106.26953 L 103.73438 105.37891 C 103.3089 105.11471 102.88446 104.94886 102.45898 104.88281 z M 82.765625 104.98242 L 83.585938 104.98242 C 83.525154 105.11452 83.373968 105.24683 83.130859 105.37891 L 82.765625 104.98242 z M 77.660156 105.2793 L 77.751953 105.47852 L 77.660156 105.67578 L 77.478516 105.47852 L 77.660156 105.2793 z M 74.103516 105.37891 L 75.5625 105.37891 C 75.440929 105.44494 75.318822 105.51011 75.197266 105.57617 C 75.075695 105.64223 74.955541 105.67578 74.833984 105.67578 C 74.712413 105.67578 74.590307 105.64223 74.46875 105.57617 C 74.347179 105.51014 74.225072 105.44497 74.103516 105.37891 z M 66.71875 105.57617 C 67.022661 105.70827 67.326951 105.87411 67.630859 106.07227 L 66.537109 106.17188 L 65.443359 106.17188 L 66.71875 105.57617 z M 75.835938 105.67578 L 76.019531 105.77539 L 75.835938 105.97266 L 75.746094 105.77539 L 75.835938 105.67578 z M 63.345703 106.26953 L 63.4375 106.46875 L 63.345703 106.56836 L 63.164062 106.46875 L 63.345703 106.26953 z M 66.445312 106.26953 L 67.996094 107.35938 C 67.813749 107.55752 67.60055 107.65625 67.357422 107.65625 C 66.99273 107.4581 66.68844 107.09676 66.445312 106.56836 L 66.445312 106.26953 z M 56.416016 106.36914 C 56.172888 106.56729 55.961644 106.7983 55.779297 107.0625 C 55.475388 106.86435 55.200159 106.76562 54.957031 106.76562 L 56.416016 106.36914 z M 66.080078 106.46875 L 66.171875 107.0625 L 66.171875 107.65625 L 65.806641 107.26172 L 66.080078 106.46875 z M 57.693359 106.56836 L 57.785156 106.76562 L 57.693359 106.96289 L 57.509766 106.76562 L 57.693359 106.56836 z M 84.771484 106.56836 C 85.183577 106.9522 85.537884 107.39849 85.835938 107.90625 L 85.683594 107.55859 C 85.835354 107.58215 85.986912 107.62311 86.138672 107.67188 L 86.138672 107.6582 L 85.683594 106.86523 L 84.771484 106.56836 z M 86.138672 107.67188 L 86.138672 107.85547 L 85.865234 107.95508 C 85.855676 107.93846 85.845616 107.92274 85.835938 107.90625 L 86.503906 109.44141 C 86.747034 108.97906 86.898204 108.51509 86.958984 108.05273 C 86.685272 107.88277 86.412385 107.75983 86.138672 107.67188 z M 47.390625 107.26172 L 48.576172 107.95508 C 48.393825 108.15322 48.302734 108.2855 48.302734 108.35156 L 47.390625 107.95508 L 47.390625 107.26172 z M 58.787109 107.26172 L 58.96875 107.26172 C 58.90796 107.52592 58.878906 107.92082 58.878906 108.44922 L 58.058594 108.15234 L 58.058594 108.8457 C 58.180155 108.91174 58.331325 108.97886 58.513672 109.04492 C 58.696026 109.11098 58.847193 109.2097 58.96875 109.3418 L 58.332031 109.3418 L 57.693359 108.64844 L 57.509766 108.15234 L 58.787109 107.26172 z M 82.126953 107.26172 L 82.310547 107.45898 L 82.126953 107.55859 L 81.945312 107.45898 L 82.126953 107.26172 z M 92.886719 107.26172 L 93.707031 109.14258 C 93.950159 108.68023 94.101326 108.21821 94.162109 107.75586 C 93.736635 107.49166 93.312191 107.32777 92.886719 107.26172 z M 84.042969 107.95508 L 83.767578 108.25195 L 83.767578 108.54883 L 84.589844 108.35156 C 84.529063 108.28551 84.34688 108.15323 84.042969 107.95508 z M 85.5 107.95508 L 85.136719 108.15234 L 85.591797 108.05273 L 85.5 107.95508 z M 50.216797 108.25195 L 50.308594 108.44922 L 50.216797 108.54883 L 50.035156 108.44922 L 50.216797 108.25195 z M 62.433594 108.25195 L 63.253906 108.25195 L 62.890625 108.64844 L 62.433594 108.25195 z M 101.54688 108.25195 L 100.81836 108.64844 L 100.36133 109.3418 L 100.63477 109.63867 L 101.82031 109.04492 L 101.54688 108.25195 z M 79.939453 108.35156 L 79.208984 109.63867 C 79.452112 109.77076 79.665312 109.90305 79.847656 110.03516 C 80.090784 110.23331 80.303981 110.39915 80.486328 110.53125 L 82.21875 108.54883 L 79.939453 108.35156 z M 65.990234 108.54883 L 66.171875 108.74609 L 65.990234 108.8457 L 65.898438 108.74609 L 65.990234 108.54883 z M 50.216797 108.8457 L 50.308594 109.04492 L 50.216797 109.24219 L 50.035156 109.04492 L 50.216797 108.8457 z M 54.683594 108.8457 L 56.052734 109.73828 L 55.595703 110.03516 L 56.234375 110.03516 L 56.326172 109.24219 C 56.083044 109.30825 55.809768 109.30825 55.505859 109.24219 C 55.201949 109.11008 54.926721 108.9778 54.683594 108.8457 z M 71.1875 108.94531 L 71.003906 109.44141 L 70.914062 109.73828 L 70.914062 109.83594 L 70.822266 110.03516 L 71.003906 110.03516 L 71.1875 110.03516 L 71.1875 109.63867 L 72.828125 109.93555 C 72.645778 109.67135 72.554688 109.37517 72.554688 109.04492 C 71.946868 109.04492 71.491408 109.01137 71.1875 108.94531 z M 61.794922 109.14258 L 60.064453 109.53906 L 59.880859 110.03516 L 61.066406 110.03516 L 61.794922 109.14258 z M 53.042969 109.24219 L 53.042969 109.44141 L 53.316406 110.03516 L 55.322266 110.03516 L 53.955078 109.73828 L 53.955078 109.24219 L 53.042969 109.24219 z M 62.980469 109.24219 L 63.164062 109.3418 L 62.980469 109.53906 L 62.890625 109.3418 L 62.980469 109.24219 z M 75.835938 109.24219 L 76.65625 111.125 C 76.899378 110.66265 77.052501 110.20063 77.113281 109.73828 C 76.687809 109.47408 76.261412 109.30824 75.835938 109.24219 z M 52.130859 109.3418 C 52.009298 109.60599 51.858128 109.837 51.675781 110.03516 L 52.130859 110.03516 L 52.130859 109.3418 z M 84.40625 109.3418 L 84.316406 109.93555 L 84.771484 110.03516 L 84.953125 109.53906 L 84.40625 109.3418 z M 64.986328 109.53906 L 64.986328 110.03516 L 65.625 110.03516 C 65.503429 109.77097 65.381322 109.60512 65.259766 109.53906 L 64.986328 109.53906 z M 88.509766 113.89844 L 86.958984 114.69141 C 87.141331 115.41796 87.232422 116.11098 87.232422 116.77148 C 87.53633 116.70543 87.871637 116.47441 88.236328 116.07812 C 88.661803 115.74787 88.995154 115.48331 89.238281 115.28516 L 88.509766 113.89844 z M 91.882812 113.99805 L 91.974609 114.29492 L 92.248047 114.19531 L 91.882812 113.99805 z M 80.03125 118.55469 C 79.788122 119.01704 79.634999 119.48101 79.574219 119.94336 L 81.306641 119.05078 C 80.881168 118.78658 80.456725 118.62074 80.03125 118.55469 z \"\r\n"
					+ "           transform=\"matrix(0.27581116,0,0,0.25381258,20.490319,11.685293)\" />\r\n"
					+ "      </g>\r\n"
					+ "      <text\r\n"
					+ "         xml:space=\"preserve\"\r\n"
					+ "         style=\"font-style:normal;font-weight:normal;font-size:10.5833px;line-height:1.25;font-family:'C.A. Gatintas';fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.0171979;-inkscape-font-specification:'C.A. Gatintas';font-stretch:normal;font-variant:normal\"\r\n"
					+ "         x=\"8.9887381\"\r\n"
					+ "         y=\"36.288406\"\r\n"
					+ "         id=\"text12910\"\r\n"
					+ "         transform=\"scale(1.0008538,0.99914691)\"><tspan\r\n"
					+ "           sodipodi:role=\"line\"\r\n"
					+ "           id=\"tspan12908\"\r\n"
					+ "           style=\"stroke-width:0.0171979;-inkscape-font-specification:'C.A. Gatintas';font-family:'C.A. Gatintas';font-weight:normal;font-style:normal;font-stretch:normal;font-variant:normal\"\r\n"
					+ "           x=\"8.9887381\"\r\n"
					+ "           y=\"36.288406\" /></text>\r\n"
					+ "      <path\r\n"
					+ "         style=\"fill:#ff2a71;stroke:#000000;stroke-width:0.065;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1;stroke-miterlimit:4;stroke-dasharray:none;fill-opacity:1\"\r\n"
					+ "         d=\"m 47.60355,25.723578 -0.28777,0.287279 0.389336,-0.0338 0.287769,-0.388671 z\"\r\n"
					+ "         id=\"path18589\" />\r\n"
					+ "    </g>\r\n"
					+ "  </g>\r\n"
					+ "</svg>\r\n"
					+ "";
		}
		
		public static String NoteE() {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n"
					+ "<!-- Created with Inkscape (http://www.inkscape.org/) -->\r\n"
					+ "\r\n"
					+ "<svg\r\n"
					+ "   width=\"38.443188mm\"\r\n"
					+ "   height=\"38.443161mm\"\r\n"
					+ "   viewBox=\"0 0 38.443188 38.443161\"\r\n"
					+ "   version=\"1.1\"\r\n"
					+ "   id=\"svg5\"\r\n"
					+ "   inkscape:version=\"1.1.1 (3bf5ae0d25, 2021-09-20)\"\r\n"
					+ "   sodipodi:docname=\"NoteE.svg\"\r\n"
					+ "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n"
					+ "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n"
					+ "   xmlns=\"http://www.w3.org/2000/svg\"\r\n"
					+ "   xmlns:svg=\"http://www.w3.org/2000/svg\">\r\n"
					+ "  <sodipodi:namedview\r\n"
					+ "     id=\"namedview7\"\r\n"
					+ "     pagecolor=\"#ffffff\"\r\n"
					+ "     bordercolor=\"#666666\"\r\n"
					+ "     borderopacity=\"1.0\"\r\n"
					+ "     inkscape:pageshadow=\"2\"\r\n"
					+ "     inkscape:pageopacity=\"0.0\"\r\n"
					+ "     inkscape:pagecheckerboard=\"0\"\r\n"
					+ "     inkscape:document-units=\"mm\"\r\n"
					+ "     showgrid=\"false\"\r\n"
					+ "     showguides=\"true\"\r\n"
					+ "     inkscape:guide-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox=\"true\"\r\n"
					+ "     inkscape:snap-bbox-edge-midpoints=\"true\"\r\n"
					+ "     inkscape:bbox-paths=\"true\"\r\n"
					+ "     inkscape:snap-nodes=\"false\"\r\n"
					+ "     inkscape:snap-bbox-midpoints=\"true\"\r\n"
					+ "     inkscape:snap-global=\"false\"\r\n"
					+ "     fit-margin-top=\"0\"\r\n"
					+ "     fit-margin-left=\"0\"\r\n"
					+ "     fit-margin-right=\"0\"\r\n"
					+ "     fit-margin-bottom=\"0\"\r\n"
					+ "     inkscape:zoom=\"3.9109044\"\r\n"
					+ "     inkscape:cx=\"91.411082\"\r\n"
					+ "     inkscape:cy=\"68.142806\"\r\n"
					+ "     inkscape:window-width=\"1920\"\r\n"
					+ "     inkscape:window-height=\"1009\"\r\n"
					+ "     inkscape:window-x=\"-8\"\r\n"
					+ "     inkscape:window-y=\"-8\"\r\n"
					+ "     inkscape:window-maximized=\"1\"\r\n"
					+ "     inkscape:current-layer=\"layer1\">\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"1,0\"\r\n"
					+ "       id=\"guide12970\" />\r\n"
					+ "    <sodipodi:guide\r\n"
					+ "       position=\"16.046533,22.376859\"\r\n"
					+ "       orientation=\"0,-1\"\r\n"
					+ "       id=\"guide12972\" />\r\n"
					+ "  </sodipodi:namedview>\r\n"
					+ "  <defs\r\n"
					+ "     id=\"defs2\" />\r\n"
					+ "  <g\r\n"
					+ "     inkscape:label=\"Calque 1\"\r\n"
					+ "     inkscape:groupmode=\"layer\"\r\n"
					+ "     id=\"layer1\"\r\n"
					+ "     transform=\"translate(-19.656191,-12.181169)\">\r\n"
					+ "    <path\r\n"
					+ "       id=\"path12996\"\r\n"
					+ "       style=\"font-variation-settings:normal;vector-effect:none;fill:#0000ff;fill-opacity:1;fill-rule:evenodd;stroke:none;stroke-width:0.999999;stroke-linecap:butt;stroke-linejoin:miter;stroke-miterlimit:4;stroke-dasharray:none;stroke-dashoffset:0;stroke-opacity:1;-inkscape-stroke:none;stop-color:#000000\"\r\n"
					+ "       inkscape:transform-center-x=\"0.041927404\"\r\n"
					+ "       inkscape:transform-center-y=\"-13.470202\"\r\n"
					+ "       d=\"m 38.817552,12.181169 -1.798582,3.127294 -0.250675,0.435907 -0.34885,-0.348849 -2.552465,-2.549995 -0.932324,3.485405 -0.133983,0.500737 -0.440846,-0.254382 -3.124823,-1.802284 0.0013,3.607656 v 0.518643 l -0.490858,-0.131513 -3.485405,-0.932322 0.93541,3.48417 0.1346,0.500737 H 25.82067 l -3.607657,0.0019 1.805372,3.12359 0.259321,0.448873 -0.491476,0.131513 -3.48417,0.93541 2.551848,2.549995 0.366754,0.366137 -0.440846,0.255 -3.12359,1.804753 3.125442,1.802903 0.448873,0.258705 -0.359345,0.359962 -2.549995,2.552465 3.484787,0.932323 0.501355,0.133984 -0.254382,0.440846 -1.802902,3.124823 3.607656,-0.0013 h 0.518644 l -0.131514,0.490857 -0.932322,3.485405 3.48417,-0.935408 0.501356,-0.1346 v 0.509379 l 0.0013,3.607657 3.12359,-1.80537 0.448872,-0.259324 0.132131,0.491477 0.934792,3.484172 2.549996,-2.551849 0.366756,-0.366756 0.25438,0.440846 1.804756,3.12359 1.802901,-3.125439 0.259324,-0.449492 0.359962,0.359962 2.551846,2.549996 0.932323,-3.484788 0.133983,-0.501356 0.440846,0.254383 3.124826,1.802904 -0.0013,-3.607657 V 43.9042 l 0.491477,0.131514 3.484788,0.932323 -0.934795,-3.484172 -0.1346,-0.501355 h 0.508766 l 3.607656,-0.0013 -1.804756,-3.12359 -0.259321,-0.448872 0.490858,-0.132131 3.484788,-0.934792 -2.552465,-2.549997 -0.366753,-0.366755 0.440846,-0.25438 3.12359,-1.804756 -3.124826,-1.802901 -0.449489,-0.259324 0.359962,-0.359961 2.549997,-2.551847 -3.485408,-0.932323 -0.500736,-0.133983 0.254382,-0.440847 1.802285,-3.124824 -3.607657,0.0012 h -0.518645 l 0.131515,-0.491476 0.932323,-3.484788 -3.484169,0.934793 -0.500739,0.1346 V 18.34556 l -0.0019,-3.607657 -3.12359,1.804755 -0.448873,0.259321 -0.131514,-0.490858 -0.935408,-3.484788 -2.549997,2.552465 -0.375398,0.376016 a 15.574866,15.762139 0 0 0 -0.0032,-6.32e-4 l -0.261793,-0.451343 z\" />\r\n"
					+ "    <ellipse\r\n"
					+ "       style=\"fill:#ffffff;stroke:#ff0000;stroke-width:1.012;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "       id=\"ellipse13122\"\r\n"
					+ "       cx=\"38.828659\"\r\n"
					+ "       cy=\"31.377256\"\r\n"
					+ "       rx=\"13.733081\"\r\n"
					+ "       ry=\"13.899035\" />\r\n"
					+ "    <g\r\n"
					+ "       aria-label=\"E\"\r\n"
					+ "       transform=\"scale(1.0754367,0.92985482)\"\r\n"
					+ "       id=\"text22560\"\r\n"
					+ "       style=\"font-size:27.1199px;line-height:1.25;font-family:'C.A. Gatintas';-inkscape-font-specification:'C.A. Gatintas';fill:#ff0000;stroke:#000000;stroke-width:0.075\">\r\n"
					+ "      <path\r\n"
					+ "         d=\"m 32.822129,42.500612 0.02712,-0.108479 0.08136,0.02712 -0.02712,0.08136 z m -4.718862,2.68487 -0.16272,-0.244079 q 0.1356,-0.108479 0.298319,-0.189839 0.162719,-0.08136 0.325439,-0.135599 v 0.162719 q -0.189839,0.298319 -0.461038,0.406798 z m -2.115353,1.654314 q -0.189839,0.21696 -0.352558,0.433919 -0.16272,0.244079 -0.406799,0.379678 l -0.162719,-0.461038 q 0.162719,-0.244079 0.244079,-0.515278 z m 0.542398,-0.677997 0.596638,-0.379679 0.379679,0.623758 q -0.1356,0.08136 -0.352559,0.135599 -0.216959,0.05424 -0.352559,0.08136 z m 4.067985,-2.63063 -0.02712,0.108479 -0.08136,-0.02712 0.02712,-0.108479 z m -0.976316,1.003436 0.244079,0.02712 0.1356,0.108479 -0.406799,0.244079 -0.05424,-0.135599 z m -2.033993,0.922076 0.271199,0.05424 -0.325438,0.18984 z m -1.301755,-6.210457 0.05424,-0.135599 0.1356,-0.1356 q 0.108479,0.16272 0.162719,0.352559 0.05424,0.189839 0.10848,0.379679 z m -0.786477,0.433919 0.271199,0.05424 -0.325439,0.216959 z m 0.18984,-1.247516 0.271199,-0.162719 0.216959,0.352559 -0.08136,0.08136 q -0.162719,0 -0.325439,-0.02712 -0.189839,-0.02712 -0.325439,-0.08136 0.08136,-0.08136 0.21696,-0.1356 -0.488159,-0.08136 -0.922077,-0.352558 L 24.57768,37.402071 h 0.379678 l 0.406799,0.02712 q 0.189839,0.271199 0.406798,0.461038 -0.08136,0.244079 -0.08136,0.542398 z m 0.515278,2.142472 q -0.08136,0.21696 -0.08136,0.461039 l -0.515278,0.298319 -0.650878,-0.05424 v -0.352559 l 0.786477,-0.515278 z m -0.623758,-0.379678 0.08136,0.02712 -0.02712,0.08136 h -0.08136 z m -1.844153,0.976316 0.298319,0.461039 -0.298319,0.352558 -0.271199,-0.433918 z m 3.091668,-2.332311 0.216959,0.325439 -0.271198,-0.05424 z m 2.603511,-1.220396 -0.16272,-0.271199 h 0.16272 q 0.108479,0 0.216959,0.05424 -0.08136,0.1356 -0.216959,0.216959 z m 0.786477,5.830779 0.02712,-0.10848 0.08136,0.02712 -0.02712,0.10848 z m 0,-3.796786 0.325439,-0.216959 -0.08136,-0.379679 0.488158,0.813597 -0.135599,0.406799 -0.271199,-0.05424 0.271199,0.406798 -0.271199,0.16272 q -0.10848,-0.298319 -0.18984,-0.569518 -0.08136,-0.271199 -0.135599,-0.569518 z m -3.769666,1.789913 0.244079,0.05424 -0.325439,0.216959 q 0,-0.135599 0.08136,-0.271199 z m 4.908702,-2.250951 0.08136,-0.298319 0.10848,-0.08136 0.216959,0.379679 -0.352559,0.216959 z m -3.823906,1.871273 v 0.108479 l -0.10848,-0.02712 0.02712,-0.108479 z m -1.220396,0.976316 0.244079,0.05424 -0.325438,0.216959 q 0,-0.135599 0.08136,-0.271199 z m 1.654314,-0.976316 -0.10848,-0.02712 v -0.02712 q 0,-0.05424 0.02712,-0.08136 l 0.10848,0.02712 z m -0.488158,2.874709 v 0.244079 l -0.02712,0.596638 q -0.244079,-0.10848 -0.461038,-0.1356 0.162719,-0.108479 0.244079,-0.298318 l -0.271199,-0.1356 q 0.244079,-0.189839 0.515278,-0.271199 z m 3.444227,-2.196712 -0.108479,-0.02712 v -0.02712 q 0,-0.05424 0.02712,-0.08136 l 0.08136,0.02712 z m 0.189839,-1.166156 v -0.02712 l 0.02712,-0.08136 0.02712,0.05424 q 0.05424,-0.244079 0.18984,-0.379679 v 0.542398 q 0,-0.02712 -0.02712,0 0,0.02712 0,0.05424 h 0.135599 0.1356 l 0.02712,-0.16272 h 0.271199 q 0,-0.05424 0.108479,-0.05424 -0.02712,-0.271199 0.05424,-0.596638 0.02712,0.135599 0.189839,0.298319 l 0.10848,-0.08136 0.461038,0.759357 q 0.05424,-0.02712 0.1356,0 0.08136,0.02712 -0.08136,0.08136 l 0.189839,0.271199 0.10848,-0.08136 0.135599,0.10848 v -0.05424 l 0.18984,0.10848 v -0.18984 l 0.298319,0.05424 -0.05424,0.244079 q -0.08136,-0.05424 -0.1356,-0.135599 l -0.108479,0.189839 -0.02712,-0.10848 -0.1356,0.02712 -0.08136,0.08136 0.216959,0.461038 -0.18984,0.16272 -0.352558,-0.352559 -0.05424,0.02712 v 0.488158 q -0.05424,0.05424 -0.162719,0.189839 -0.10848,0.10848 -0.10848,0.16272 h 0.08136 q 0.08136,0 0.189839,-0.08136 0.135599,-0.108479 0.189839,-0.162719 l 0.244079,0.08136 0.02712,0.05424 q -0.05424,0.08136 -0.135599,0.18984 -0.08136,0.08136 -0.18984,0.135599 0.02712,-0.02712 0,-0.05424 -0.02712,-0.02712 -0.05424,-0.02712 -0.1356,0.10848 -0.244079,0.244079 -0.02712,-0.05424 -0.02712,-0.135599 0,-0.10848 0,-0.162719 l -0.406799,0.162719 q 0.02712,0.05424 0.05424,0.189839 0.05424,0.1356 0.02712,0.18984 l -0.298319,0.162719 0.08136,-0.10848 q -0.02712,-0.08136 0.02712,-0.189839 0.05424,-0.135599 0.02712,-0.271199 l 0.271199,-0.244079 h -0.02712 0.135599 q -0.08136,-0.08136 -0.244079,-0.352559 -0.1356,-0.271199 -0.1356,-0.325439 v -0.02712 l 0.08136,-0.10848 0.02712,-0.02712 h 0.02712 q -0.05424,0 -0.05424,0.08136 0,0.08136 0.05424,0.08136 0.10848,0 0.10848,0 0,0 0,-0.108479 0,-0.05424 -0.02712,-0.10848 -0.02712,-0.08136 -0.05424,-0.135599 -0.08136,0 -0.10848,0 -0.02712,-0.02712 -0.08136,-0.08136 l -0.16272,0.244079 0.10848,0.10848 h -0.16272 l 0.1356,0.135599 0.08136,0.18984 -0.02712,0.02712 -0.21696,-0.162719 -0.08136,0.216959 q 0.05424,0.05424 -0.02712,0.05424 -0.08136,0 -0.10848,0 h -0.02712 q -0.05424,-0.05424 -0.135599,-0.08136 -0.08136,-0.05424 -0.08136,-0.108479 0,0 0.10848,0 l 0.08136,-0.05424 0.21696,-0.05424 q 0,-0.05424 -0.02712,-0.02712 -0.02712,0.02712 0,0.02712 l -0.08136,-0.135599 q -0.08136,-0.02712 -0.298319,-0.02712 l -0.05424,0.216959 q -0.02712,-0.1356 -0.05424,-0.10848 -0.02712,0 -0.02712,-0.189839 l 0.02712,-0.05424 q 0,-0.02712 -0.18984,-0.162719 -0.162719,-0.1356 -0.189839,-0.16272 l 0.05424,-0.433918 -0.02712,-0.10848 h 0.05424 v -0.135599 l 0.02712,0.135599 h 0.135599 q 0,-0.271199 -0.10848,-0.271199 z m -2.278071,1.111916 -0.325439,0.21696 0.05424,-0.271199 z m 1.898393,-0.677997 q -0.16272,0.108479 -0.379679,0.244079 -0.216959,0.10848 -0.433918,0.10848 l -0.271199,-0.02712 0.271199,-0.515278 -0.216959,-0.352559 0.352558,-0.216959 0.08136,0.108479 q -0.02712,0.10848 -0.05424,0.216959 -0.02712,0.08136 -0.02712,0.18984 0.1356,0.135599 0.325439,0.162719 0.189839,0.02712 0.352559,0.08136 z m -1.220396,0.515278 -0.02712,0.08136 h -0.08136 v -0.10848 z m 1.057676,0.08136 0.08136,0.108479 0.02712,0.216959 -0.135599,0.21696 -0.10848,0.05424 -0.216959,-0.352558 z m -5.36974,1.627194 0.244079,0.05424 -0.298318,0.21696 z m 3.200148,4.067985 0.271199,0.05424 -0.325438,0.18984 z m 3.769667,-2.87471 0.02712,-0.108479 0.08136,0.02712 -0.02712,0.08136 z m 0.705117,1.925513 q -0.05424,0.16272 -0.10848,0.325439 -0.05424,0.189839 -0.162719,0.325439 -0.02712,-0.216959 -0.08136,-0.406799 -0.05424,-0.189839 -0.108479,-0.406798 l 0.244079,-0.16272 z m -1.871273,-1.111916 h -0.325439 l -0.05424,-0.216959 0.02712,-0.244079 q 0.10848,0.10848 0.189839,0.216959 0.08136,0.10848 0.16272,0.244079 z m -5.939258,4.257824 0.05424,0.08136 -0.705118,0.433919 q -0.08136,-0.16272 -0.162719,-0.325439 -0.08136,-0.16272 -0.189839,-0.298319 0.189839,-0.10848 0.406798,-0.10848 0.162719,0 0.325439,0.05424 0.135599,0.08136 0.271199,0.162719 z m 3.227268,-3.173028 -0.02712,0.08136 h -0.08136 l 0.02712,-0.10848 z m 3.091668,-1.518714 -0.298318,-0.488158 0.244079,-0.1356 0.406798,0.596638 q -0.08136,0 -0.189839,0 -0.08136,0 -0.16272,0.02712 z m 0.949197,-0.1356 0.189839,0.325439 -0.244079,-0.05424 z m 0.135599,-0.298319 0.02712,-0.08136 0.10848,0.02712 -0.02712,0.08136 z m 0.24408,-0.135599 0.08136,-0.1356 0.1356,-0.08136 0.162719,0.244079 h -0.162719 z m -5.749419,4.474783 -0.16272,0.325439 -0.162719,-0.08136 -0.135599,-0.162719 q 0.05424,-0.02712 0.135599,-0.05424 0.05424,-0.02712 0.10848,-0.02712 z m 6.264697,-4.203584 0.05424,-0.189839 0.18984,0.02712 -0.05424,0.216959 z m -7.485093,4.176464 0.352559,-0.216959 0.162719,0.244079 -0.379678,0.21696 z M 34.259484,37.75463 v 0.108479 h -0.02712 q 0.02712,-0.02712 0.02712,-0.108479 z m 0,0.894957 v 0.02712 l -0.02712,-0.02712 z m 0.05424,0.488158 -0.10848,0.189839 -0.216959,-0.352559 0.244079,-0.108479 q 0,0.05424 0.02712,0.135599 0.05424,0.08136 0.05424,0.1356 z m 0.352558,0 H 35.2358 l -0.271199,0.162719 -0.271199,-0.162719 z m -4.718862,-0.569518 -0.1356,-0.189839 -0.08136,0.05424 q 0.02712,0 0.02712,-0.108479 l -0.135599,-0.1356 -0.298319,0.352559 q 0.02712,0.05424 -0.02712,0.08136 -0.02712,0.02712 -0.02712,0.05424 l 0.08136,0.05424 0.08136,0.515278 -0.05424,0.02712 v 0.02712 l -0.1356,-0.02712 v -0.08136 l -0.108479,-0.10848 -0.16272,0.10848 -0.02712,0.05424 -0.162719,-0.108479 h 0.05424 v -0.08136 l 0.05424,0.02712 0.02712,-0.05424 q 0,-0.05424 -0.08136,0 -0.05424,0.02712 -0.05424,-0.08136 l -0.10848,0.10848 -0.189839,-0.135599 0.16272,0.189839 -0.02712,0.02712 q 0,0.02712 0.05424,0.02712 h 0.05424 l 0.08136,0.135599 0.216959,0.1356 0.10848,-0.05424 -0.1356,-0.08136 h 0.08136 0.135599 l -0.05424,0.05424 h 0.216959 l -0.1356,0.759357 -0.244079,-0.02712 h -0.108479 q 0.05424,0.08136 0.244079,0.1356 0.189839,0.02712 0.108479,0.135599 l 0.10848,0.08136 -0.1356,0.189839 q -0.189839,0.08136 -0.352558,0.298319 -0.10848,-0.08136 -0.18984,-0.189839 l 0.18984,-0.244079 -0.05424,-0.10848 q -0.352559,0.189839 -0.786477,0.325439 v -0.1356 l 0.135599,-0.05424 0.08136,0.05424 0.05424,-0.05424 0.08136,-0.08136 q 0,-0.02712 -0.02712,-0.02712 -0.02712,0 -0.02712,0.02712 h 0.02712 0.162719 q 0,-0.02712 0.02712,-0.02712 l 0.02712,0.02712 q 0.05424,0 0.05424,-0.05424 0,-0.05424 -0.05424,-0.05424 0.08136,-0.135599 0.135599,-0.271199 0.05424,-0.135599 0.10848,-0.271199 l -0.162719,-0.189839 q -0.02712,-0.02712 -0.02712,-0.02712 0.02712,-0.02712 0,-0.08136 l -0.02712,0.02712 -0.08136,-0.108479 -0.162719,0.02712 -0.02712,-0.02712 v -0.16272 q -0.08136,0.08136 -0.18984,0.1356 l 0.05424,0.08136 h -0.08136 l -0.08136,0.02712 v -0.216959 l 0.02712,0.02712 q -0.08136,-0.135599 -0.1356,-0.271199 -0.02712,-0.162719 -0.08136,-0.298319 l -0.16272,0.325439 Q 27.587988,38.839426 27.425269,38.486867 27.26255,38.134308 27.26255,37.890229 l 0.02712,-0.189839 0.271199,0.433918 q 0.05424,-0.135599 0.135599,-0.271199 l 0.325439,0.298319 v -0.244079 l 0.135599,0.10848 q -0.02712,-0.08136 -0.135599,-0.244079 v -1.789914 l 0.108479,-0.08136 v 0.325439 h 0.325439 q -0.08136,-0.1356 -0.162719,-0.379679 -0.05424,-0.244079 -0.02712,-0.379679 l -0.216959,0.16272 -0.02712,0.05424 v -0.433919 l 0.352559,-0.352559 q 0,-0.162719 0,-0.325438 0,-0.18984 0.02712,-0.352559 0.05424,-0.05424 0.10848,-0.1356 0.05424,-0.108479 0.1356,-0.135599 l 0.08136,0.135599 v 0.298319 l 0.16272,0.08136 q 0.08136,0.1356 0.135599,0.244079 0.08136,0.10848 0.1356,0.244079 l -0.298319,0.18984 q 0,-0.10848 -0.10848,-0.10848 -0.02712,0 -0.08136,0.05424 -0.02712,0.05424 -0.02712,0.08136 h 0.244079 v -0.02712 h 0.135599 l -0.162719,0.162719 -0.1356,-0.02712 0.325439,0.216959 q -0.135599,0.10848 -0.244079,0.352559 -0.08136,0.244079 -0.10848,0.406798 l 0.352559,-0.135599 -0.244079,0.325439 0.05424,-0.16272 q -0.08136,0.08136 -0.18984,0.16272 -0.08136,0.08136 -0.162719,0.162719 v 0.271199 l -0.10848,-0.08136 0.16272,-0.271199 -0.244079,0.16272 v 0.135599 0.1356 h 0.162719 l 0.216959,0.298319 -0.05424,0.135599 0.02712,0.02712 0.21696,-0.162719 -0.244079,-0.352559 q 0.216959,0 0.325438,-0.02712 v 0.894957 q 0.1356,-0.02712 0.271199,-0.02712 0.1356,0 0.271199,0 l 0.1356,0.216959 0.271199,-0.162719 v 0.298319 h 0.108479 l 0.1356,-0.16272 q 0.02712,-0.108479 0.02712,-0.189839 0,-0.08136 0,-0.1356 l 0.08136,-0.05424 q 0,0.05424 0,0.108479 0,0.05424 0.05424,0.10848 -0.08136,0.162719 -0.162719,0.325439 -0.08136,0.162719 -0.1356,0.325438 l -0.08136,-0.108479 0.05424,0.189839 q -0.02712,0 -0.02712,0.02712 0,0.02712 -0.02712,0.05424 0,0 0,-0.02712 z m -1.925513,-5.017182 v -0.325438 l 0.135599,0.162719 z m 6.237577,3.552707 v 0.02712 q 0,0.02712 -0.02712,0.05424 -0.02712,0.02712 0.02712,0.02712 v 0.244079 l -0.02712,0.02712 0.02712,0.02712 q 0,0.02712 0,0.02712 l -0.05424,-0.02712 h -0.02712 l -0.05424,-0.02712 -0.08136,-0.02712 q -0.10848,0.08136 -0.18984,0.162719 -0.08136,0.05424 -0.189839,0.05424 l -0.02712,0.02712 -0.216959,-0.325439 q 0.135599,-0.135599 0.135599,-0.162719 0,-0.05424 0.1356,-0.189839 0.135599,0.02712 0.216959,0.05424 0.10848,0.02712 0.244079,0.02712 v 0.1356 q 0.02712,-0.02712 0.05424,-0.08136 0.02712,-0.05424 0.05424,-0.05424 z m -6.237577,5.668059 v -0.216959 l 0.108479,-0.162719 -0.108479,-0.02712 q 0,-0.16272 0.02712,-0.16272 l 0.08136,-0.08136 q 0,-0.02712 -0.05424,-0.05424 -0.05424,-0.05424 -0.05424,0 v -0.352559 l 0.135599,0.05424 q 0.10848,-0.10848 0.16272,-0.325439 l -0.1356,0.10848 -0.108479,-0.08136 q 0,-0.05424 0.05424,-0.162719 l -0.135599,-0.05424 0.135599,-0.05424 v -0.352559 h 0.10848 l 0.461038,0.05424 -0.162719,0.271199 h 0.135599 q 0.16272,-0.08136 0.298319,-0.189839 0.1356,-0.10848 0.271199,-0.216959 v 0.02712 q 0,0.135599 -0.05424,0.189839 -0.05424,0.02712 -0.16272,0.135599 h 0.02712 q -0.10848,0.08136 -0.189839,0.18984 -0.08136,0.08136 -0.16272,0.189839 l -0.02712,-0.02712 -0.244079,0.216959 h 0.1356 l -0.244079,0.298319 q -0.05424,0 -0.08136,0.02712 -0.02712,0 -0.05424,0 0,0.271199 -0.08136,0.461038 h 0.108479 0.10848 q 0,-0.244079 0.08136,-0.461038 h 0.05424 v 0.244079 q 0.05424,-0.05424 0.10848,-0.108479 0.05424,-0.05424 -0.05424,-0.1356 0.379679,0 0.759357,-0.244079 0.02712,0.216959 0.02712,0.406799 0,0.189839 0,0.406798 l -0.05424,0.05424 q 0,0.02712 0.02712,0.05424 0.02712,0.05424 0.05424,0.02712 0,0.298318 0.05424,0.542398 0.05424,0.216959 0.162719,0.515278 l 0.298319,-0.325439 0.189839,0.515278 q -0.189839,-0.05424 -0.379678,-0.08136 -0.189839,-0.02712 -0.379679,-0.108479 l 0.02712,0.05424 -0.135599,0.05424 -0.08136,-0.08136 0.189839,0.298318 -0.352558,0.24408 -0.406799,-0.759358 0.10848,-0.05424 0.162719,0.05424 0.08136,0.10848 0.108479,-0.10848 q 0.05424,-0.162719 0.05424,-0.298319 v -0.08136 l -0.650877,-0.244079 -0.10848,0.271199 q -0.162719,-0.02712 -0.298319,-0.1356 l -0.244079,0.298319 q -0.461038,0.08136 -0.867837,0.08136 l 0.08136,-0.244079 -0.298319,0.162719 -0.162719,-0.244079 q 0.352558,0 0.786477,-0.1356 0.433918,-0.135599 0.759357,-0.244079 z m 8.16309,1.084796 -0.08136,-0.08136 h 0.677998 l -0.08136,0.08136 -0.244079,0.10848 z m -3.064549,-0.05424 v 0.05424 h -0.02712 v 0.16272 l -0.02712,0.02712 -0.02712,-0.10848 h 0.05424 v -0.135599 z m -1.328875,0.135599 h -0.02712 l 0.02712,-0.08136 z m 0.135599,-0.271199 q 0.02712,-0.05424 0.05424,-0.08136 0.02712,-0.05424 0.05424,-0.108479 l 0.08136,0.02712 q -0.05424,0.02712 -0.18984,0.162719 z m 0.298319,-0.135599 -0.02712,0.02712 v 0.02712 q 0.08136,0.02712 0.16272,0.05424 0.108479,0.02712 0.189839,0.02712 l 0.05424,0.10848 q 0,0.05424 -0.08136,0.08136 -0.05424,0.02712 -0.08136,0.08136 h 0.162719 l -0.1356,0.02712 h -0.02712 l -0.02712,-0.02712 -0.02712,0.05424 h -0.05424 l -0.216959,-0.189839 0.05424,-0.1356 z M 32.71365,43.01589 h 0.08136 l -0.08136,0.05424 z m 0.08136,0.352559 0.02712,-0.05424 q 0.10848,0.10848 0.244079,0.10848 -0.02712,0.02712 -0.162719,0.10848 l -0.10848,-0.05424 z m 0.325439,0 q 0,-0.189839 -0.02712,-0.271199 l 0.05424,-0.02712 0.02712,0.05424 0.08136,0.1356 z m 0.1356,-0.352559 v -0.02712 l 0.189839,-0.10848 0.189839,0.135599 z m 0.461038,0.02712 0.135599,0.02712 q -0.108479,-0.271199 -0.406798,-0.569518 l 0.515278,0.1356 q 0.02712,-0.189839 0.05424,-0.352559 0.02712,-0.189839 0,-0.379678 l 0.135599,0.108479 q 0.1356,-0.08136 0.18984,-0.216959 0.05424,-0.1356 0.108479,-0.271199 h 0.10848 q 0.108479,0 0.08136,0.08136 -0.02712,0.05424 -0.08136,0.189839 0.135599,0.05424 0.244079,0.135599 0.108479,0.05424 0.244079,0.10848 l 0.271199,-0.10848 h -0.352559 v -0.108479 -0.10848 l 0.379679,0.10848 v -0.16272 h 0.298319 v 0.05424 q 0.216959,0.02712 0.515278,0.1356 v 0.08136 q 0,0.10848 -0.02712,0.08136 0,-0.02712 -0.05424,-0.02712 v 0.05424 l -0.216959,0.1356 -0.02712,-0.02712 0.10848,-0.10848 h -0.18984 l -0.244079,-0.189839 q -0.08136,0.08136 -0.135599,0.352558 -0.05424,0.24408 -0.02712,0.325439 0.05424,0 0.135599,0 0.10848,0 0.02712,-0.08136 h 0.18984 0.108479 l 0.02712,0.162719 q 0,-0.05424 -0.05424,-0.02712 -0.02712,0.02712 -0.02712,0.05424 -0.189839,-0.02712 -0.352558,-0.02712 -0.16272,0 -0.352559,0 v -0.08136 q -0.05424,-0.108479 -0.1356,-0.216959 -0.08136,-0.10848 -0.189839,-0.162719 l -0.108479,0.135599 -0.1356,-0.02712 -0.189839,0.244079 -0.16272,-0.108479 h -0.08136 v 0.406798 0.05424 l 0.02712,-0.02712 0.515278,0.16272 0.05424,0.05424 v 0.379679 h 0.10848 q 0,0.135599 -0.08136,0.216959 -0.08136,0.08136 -0.135599,0.216959 v -0.02712 l -0.1356,-0.298318 V 43.25997 l -0.271199,0.216959 q 0.05424,-0.08136 -0.02712,-0.08136 -0.05424,-0.02712 -0.108479,-0.02712 -0.1356,0 -0.05424,0.08136 0.08136,0.05424 0.08136,0.108479 0,0.02712 -0.02712,0.02712 -0.02712,0 -0.02712,-0.02712 v -0.108479 h -0.325439 v 0.108479 l -0.05424,0.1356 -0.135599,0.05424 q -0.02712,0.02712 -0.08136,-0.02712 -0.05424,-0.05424 -0.08136,-0.08136 l -0.189839,0.10848 -0.02712,-0.16272 0.05424,-0.189839 q 0.1356,0.08136 0.162719,0.10848 l 0.08136,-0.10848 q 0,0.05424 0.02712,0.02712 0.05424,-0.02712 0.10848,-0.08136 0.05424,-0.08136 0.108479,-0.162719 0.05424,-0.08136 0.10848,-0.1356 z m 0.894957,1.030557 0.05424,-0.02712 -0.02712,0.08136 z m 0.108479,-0.16272 v -0.08136 l 0.10848,-0.271199 0.189839,-0.108479 q 0.244079,0.244079 0.623758,0.271199 0.02712,0.135599 0.135599,0.216959 0.02712,0 0.05424,0.02712 0.02712,0 0,0 l -0.135599,0.10848 h -0.24408 q -0.108479,-0.1356 -0.216959,-0.24408 -0.08136,-0.108479 -0.216959,-0.162719 h -0.10848 v 0.325439 q -0.05424,-0.02712 -0.108479,-0.05424 -0.02712,-0.02712 -0.08136,-0.05424 z m 3.796786,0.05424 0.02712,-0.02712 v 0.02712 z m 0.189839,0 v -0.10848 l 0.05424,-0.05424 q 0.02712,0.02712 0.08136,0.135599 l 0.05424,0.02712 z m 1.464475,0.02712 0.135599,-0.16272 0.02712,0.1356 q -0.108479,0 -0.162719,0.02712 z m -10.71236,-4.745983 0.02712,-0.244079 -0.08136,-0.02712 q 0,-0.108479 0.108479,-0.162719 l 0.05424,0.05424 0.216959,-0.1356 0.08136,0.1356 -0.02712,0.189839 h -0.271199 l -0.02712,-0.02712 -0.02712,0.02712 z m 1.139035,-1.572954 v -0.02712 q 0,-0.02712 0.02712,-0.08136 0.02712,-0.08136 0.02712,-0.10848 l 0.1356,0.10848 h -0.02712 l -0.10848,0.08136 v 0.02712 z m 0.08136,0.02712 q 0,0.05424 0,0.10848 0,0.05424 0.02712,0.108479 l -0.10848,-0.05424 z m -0.162719,0.623758 h 0.05424 l 0.1356,-0.05424 0.216959,0.10848 0.05424,0.02712 q 0,0.10848 -0.05424,0.05424 -0.135599,0.08136 -0.244079,0.16272 -0.08136,0.08136 -0.189839,0.162719 v -0.162719 l 0.02712,-0.10848 0.02712,0.02712 q 0.08136,0 0.108479,-0.08136 0.02712,-0.08136 0.08136,-0.108479 -0.05424,0 -0.1356,0.02712 -0.08136,0 -0.08136,-0.05424 z m 0.515278,-0.02712 q 0,-0.05424 -0.02712,-0.08136 l 0.05424,0.05424 z m 0.08136,-0.05424 q 0.02712,0 0.02712,-0.02712 0,-0.02712 0.02712,-0.05424 l 0.08136,-0.08136 v 0.02712 q 0,0.02712 -0.02712,0.05424 0,0 0,0.02712 h -0.02712 z m 1.383115,-0.379679 0.05424,-0.02712 -0.05424,-0.10848 q 0.10848,0.02712 0.18984,0.05424 0.08136,0 0.189839,0 v -0.05424 q -0.1356,-0.189839 -0.1356,-0.271199 l -0.02712,-0.02712 -0.02712,-0.02712 -0.05424,0.05424 -0.02712,-0.05424 -0.189839,0.10848 -0.08136,-0.216959 0.135599,-0.21696 0.02712,-0.108479 0.08136,0.08136 q 0.216959,-0.108479 0.325439,-0.135599 -0.05424,-0.08136 0.05424,-0.08136 v 0.02712 l -0.02712,0.02712 0.02712,0.05424 q 0,0.05424 -0.10848,0.08136 -0.08136,0 -0.135599,0.05424 0.08136,0.05424 -0.02712,0.16272 0.02712,0.05424 0.05424,0.08136 0.05424,0 0.05424,0.05424 l -0.02712,0.08136 q -0.02712,0 -0.05424,0.02712 0,0 0.02712,0 0.05424,0 0.10848,-0.02712 0.05424,-0.02712 0.10848,-0.02712 l 0.02712,0.05424 0.271199,-0.379679 q -0.02712,0 -0.02712,0 0.02712,-0.02712 0.05424,-0.02712 h 0.02712 v 0.02712 q 0,0.05424 -0.05424,0.18984 -0.02712,0.108479 0,0.05424 l 0.02712,0.271199 q -0.08136,0.02712 -0.05424,-0.02712 0.05424,-0.08136 -0.02712,-0.08136 -0.02712,0 -0.05424,0.02712 -0.02712,0.02712 -0.05424,-0.02712 v 0.135599 l 0.08136,0.216959 0.02712,0.02712 -0.271199,-0.02712 q -0.02712,-0.02712 -0.08136,-0.05424 -0.05424,-0.02712 -0.10848,0 v 0.05424 z m 1.572955,-0.108479 q 0,0.02712 0.08136,0.108479 L 33.879805,37.75463 Z m 0.325438,3.037429 0.02712,0.02712 -0.02712,0.02712 z m 0.298319,0.244079 h 0.298319 l -0.108479,0.108479 q -0.05424,-0.02712 -0.10848,-0.05424 -0.02712,-0.02712 -0.08136,-0.05424 z m 0.16272,0.189839 -0.02712,0.02712 -0.1356,0.189839 h -0.02712 l -0.108479,-0.135599 v -0.08136 z m -4.040865,0.732237 h 0.02712 l 0.02712,0.02712 -0.05424,0.02712 h -0.05424 z m 0.461038,0.08136 -0.05424,0.02712 h 0.02712 q -0.05424,0 -0.08136,-0.02712 -0.02712,-0.05424 -0.08136,-0.10848 l -0.10848,0.02712 v -0.05424 h 0.10848 q 0.108479,0.108479 0.189839,0.135599 z m -0.05424,-0.271199 -0.02712,-0.135599 -0.02712,-0.02712 h 0.05424 z m 0.379679,1.030556 0.02712,0.02712 -0.08136,0.10848 v -0.02712 z m -0.08136,-0.162719 q 0.135599,0 0.244079,0 0.1356,0 0.271199,-0.02712 v 0.05424 q -0.02712,0.02712 -0.08136,0.135599 -0.02712,0.08136 -0.05424,0.08136 h -0.1356 q -0.05424,0 -0.08136,-0.05424 -0.02712,-0.05424 -0.08136,-0.08136 z m 1.410235,1.355995 -0.02712,-0.05424 h 0.08136 z m 0.162719,-4.149345 -0.325439,-0.108479 0.02712,-0.02712 0.298319,0.08136 z m 0.16272,-0.433918 -0.02712,0.08136 -0.08136,-0.05424 0.05424,-0.05424 -0.216959,-0.05424 0.02712,-0.08136 0.18984,0.1356 z m -0.16272,-0.298319 h 0.08136 l -0.05424,-0.16272 q 0,-0.02712 0.02712,-0.05424 0.02712,-0.05424 0.05424,-0.02712 0.08136,0.08136 0.189839,0.298319 h -0.02712 z m -1.708554,4.827342 -0.271199,0.05424 v -0.16272 h 0.24408 l -0.10848,-0.108479 -0.1356,0.05424 h -0.02712 v -0.244079 l -0.24408,-0.05424 0.05424,-0.271199 0.18984,0.271199 q -0.02712,-0.05424 0,-0.05424 0.05424,0 0.05424,-0.02712 l -0.08136,-0.08136 V 43.07013 l -0.08136,-0.189839 q 0.02712,0 0.02712,0 0.02712,-0.02712 0.05424,-0.02712 l 0.108479,0.298319 q 0.02712,0 0.02712,-0.02712 l -0.02712,-0.02712 q 0,-0.05424 0.05424,-0.05424 h 0.05424 l 0.02712,-0.02712 0.189839,0.05424 q 0,0.02712 0,0.08136 0.02712,0.05424 0.02712,0.02712 v -0.08136 h 0.135599 l -0.05424,0.05424 v 0.461038 q -0.05424,0 -0.05424,0.05424 0.02712,0.05424 0.05424,0.02712 l -0.16272,0.08136 -0.05424,-0.02712 z m 0.08136,-0.135599 0.1356,0.02712 v 0.05424 q -0.05424,-0.02712 -0.08136,-0.02712 0,-0.02712 -0.05424,-0.05424 z m -0.786477,-1.681434 h 0.162719 v 0.05424 0.162719 l 0.16272,-0.02712 0.162719,-0.10848 h 0.18984 q 0,0.02712 0,0.02712 l -0.02712,0.08136 q -0.02712,0.10848 -0.05424,0.216959 -0.02712,0.08136 -0.05424,0.18984 l -0.162719,-0.10848 q -0.02712,0.02712 -0.05424,0.10848 -0.02712,0.05424 -0.08136,0 l -0.05424,-0.10848 q -0.08136,0 -0.135599,0.05424 -0.05424,0.05424 -0.10848,0.108479 l -0.02712,-0.162719 v -0.189839 h 0.21696 z m 3.118789,-1.871273 v -0.08136 q 0.05424,-0.02712 0.216959,-0.08136 z m -0.1356,1.545834 v -0.02712 l -0.08136,-0.135599 h 0.271199 z m -2.549271,-1.220396 h -0.02712 z m 0.596638,2.467911 -0.05424,-0.02712 h 0.05424 z m -2.088232,-7.268133 h -0.244079 q 0.05424,-0.05424 0.08136,-0.05424 0.05424,-0.02712 0.108479,-0.05424 z m -0.244079,-0.894956 -0.02712,-0.02712 h 0.02712 z m -0.216959,-0.271199 0.02712,0.02712 0.135599,0.05424 -0.02712,0.18984 -0.08136,-0.1356 z m 0.216959,0.135599 h 0.02712 z m -0.515278,-1.681434 0.108479,-0.216959 v -0.02712 l 0.244079,0.244079 -0.244079,0.325439 v -0.352559 z m 0.596638,0.16272 0.108479,-0.05424 0.02712,0.08136 h -0.10848 z m 9.573324,9.058046 q 0.02712,0 0.02712,0.02712 v -0.02712 z m -0.894957,0.189839 0.05424,-0.02712 -0.05424,0.05424 -0.05424,-0.02712 z m -0.244079,0.10848 q 0.02712,-0.05424 0,-0.10848 -0.02712,-0.08136 -0.05424,-0.135599 l 0.08136,0.162719 0.1356,-0.02712 v 0.08136 l -0.02712,0.02712 z m -2.54927,0.542398 0.05424,0.10848 -0.352559,0.162719 v -0.08136 h 0.02712 L 35.26292,43.20573 q 0,-0.08136 0.08136,-0.08136 z m 0.08136,-0.325439 0.515278,-0.05424 0.05424,0.24408 q -0.05424,0.05424 -0.189839,0.05424 -0.135599,0 -0.189839,0.02712 -0.05424,0.02712 -0.08136,-0.08136 -0.02712,-0.10848 -0.108479,-0.18984 z m -5.830779,-4.529023 v 0.02712 z m -0.650878,-1.464475 -0.08136,-0.05424 -0.05424,0.08136 0.02712,0.05424 h -0.10848 v -0.08136 h 0.02712 0.02712 q 0.02712,-0.02712 0.02712,-0.08136 0,-0.05424 0.05424,0 l 0.135599,-0.05424 0.02712,0.05424 z m 2.983189,-0.542398 0.24408,-0.02712 -0.02712,0.135599 q -0.05424,0.02712 -0.10848,0.05424 -0.02712,0.02712 -0.08136,0 l 0.08136,-0.10848 -0.02712,-0.02712 q -0.05424,0.05424 -0.108479,0.10848 -0.05424,0.02712 -0.10848,0.05424 v -0.18984 q 0.02712,0 0.08136,0.02712 0.05424,0.02712 0.05424,-0.02712 z m 1.844154,-0.05424 v 0.02712 h -0.02712 q 0,-0.02712 0.02712,-0.02712 z m -2.196712,0.433918 v -0.02712 h 0.244079 z m 0.162719,0.433918 h 0.1356 l -0.02712,0.08136 q -0.02712,-0.02712 -0.05424,-0.02712 -0.02712,-0.02712 -0.05424,-0.05424 z m -3.200148,-5.42398 -0.08136,-0.08136 -0.05424,0.08136 0.05424,0.05424 z m 1.301755,0.16272 q -0.08136,0.108479 -0.189839,0.189839 h 0.379678 z m 1.166156,-1.925513 -0.05424,-0.05424 -0.08136,0.05424 0.08136,0.08136 z m 0.162719,0.298319 -0.08136,-0.08136 -0.08136,0.08136 0.08136,0.08136 z m 4.501904,2.196712 q -0.05424,-0.05424 -0.10848,-0.10848 -0.05424,-0.08136 -0.135599,-0.08136 -0.02712,0 -0.05424,0.02712 l 0.162719,0.16272 z m 0.433918,0.705117 -0.08136,-0.05424 -0.05424,0.05424 0.05424,0.08136 z m 0.298319,-0.786477 -0.08136,-0.135599 q -0.162719,0.162719 -0.244079,0.271199 h 0.325439 z m -8.000371,-5.3155 -0.05424,-0.08136 -0.08136,0.08136 0.08136,0.08136 z m 0,1.030556 -0.05424,-0.08136 -0.08136,0.08136 0.08136,0.05424 z m 0.298319,-0.298319 -0.08136,-0.08136 -0.05424,0.08136 0.05424,0.08136 z m 0.759358,-1.789913 v -0.10848 l 0.379678,-0.406798 q 0.216959,0.108479 0.325439,0.189839 L 29.94742,25.252356 h 0.298319 v -0.406798 l -0.352559,-0.1356 -0.325439,0.271199 q -0.08136,-0.108479 -0.189839,-0.189839 l -0.16272,0.461038 h -0.433918 l 0.325439,0.515278 h -0.05424 l -0.271199,0.542398 q 0.08136,0.1356 0.08136,0.271199 h -0.05424 q -0.216959,-0.352559 -0.352559,-0.759357 l -0.135599,0.135599 0.352558,0.732238 q 0.21696,0 0.433919,-0.08136 0,-0.189839 0,-0.352559 0.02712,-0.189839 0.08136,-0.379678 l 0.08136,0.135599 0.08136,-0.08136 v 0.02712 l 0.10848,0.16272 v -0.10848 l 0.05424,0.02712 q 0.08136,-0.02712 0.02712,-0.05424 -0.02712,-0.05424 -0.08136,-0.05424 z m 0.406798,0.623757 q 0,-0.02712 -0.135599,-0.05424 -0.10848,-0.05424 -0.1356,-0.05424 l -0.298319,-0.08136 -0.135599,0.406798 q 0.08136,0.05424 0.135599,0.16272 0.05424,0.08136 0.16272,0.08136 l 0.05424,-0.08136 -0.05424,-0.08136 -0.02712,0.02712 q -0.08136,-0.08136 -0.18984,-0.298319 l 0.1356,0.05424 q 0,0.02712 0.05424,0.08136 0.05424,0.02712 0.08136,-0.02712 l 0.18984,0.05424 z m 0,0.922077 -0.05424,-0.10848 -0.108479,0.08136 q -0.08136,-0.162719 -0.271199,-0.298319 l -0.05424,0.08136 q 0.05424,0.05424 0.135599,0.135599 0.08136,0.05424 0.16272,0.10848 l -0.298319,0.325439 q 0.352559,-0.10848 0.488158,-0.325439 z m 0.596638,-0.786477 q -0.05424,-0.05424 -0.05424,-0.1356 0,-0.108479 0,-0.162719 -0.10848,0.08136 -0.189839,0.189839 l 0.08136,0.10848 -0.189839,0.271199 q 0.189839,-0.10848 0.352559,-0.271199 z m 0,0.867837 -0.10848,-0.05424 q -0.216959,0.189839 -0.379678,0.379678 -0.1356,0.18984 -0.298319,0.406799 l 0.135599,-0.10848 v 0.1356 h 0.325439 L 29.94742,28.072826 q 0.108479,-0.1356 0.244079,-0.244079 0.135599,-0.1356 0.271199,-0.271199 z m 0.08136,-1.762794 q -0.10848,0.02712 -0.24408,0.05424 -0.135599,0.02712 -0.216959,0.108479 L 29.9203,25.794754 29.703341,25.713394 q 0.05424,0.05424 0.05424,0.10848 0,0.05424 0,0.10848 l 0.21696,0.216959 0.135599,-0.10848 h 0.10848 q -0.10848,0.08136 -0.216959,0.16272 -0.08136,0.08136 -0.21696,0.135599 h 0.18984 q 0.135599,0 0.352558,-0.189839 0.21696,-0.216959 0.21696,-0.352559 z m 0.189839,0.325439 -0.05424,-0.08136 -0.08136,0.08136 0.08136,0.08136 z m -0.05424,1.898393 -0.02712,-0.216959 q -0.108479,0.108479 -0.189839,0.216959 -0.08136,0.108479 -0.189839,0.216959 h 0.406798 z m 2.522151,4.067985 -0.05424,-0.08136 -0.08136,0.08136 0.08136,0.08136 z m 0.813597,1.247515 -0.216959,0.05424 -0.10848,0.08136 -0.02712,-0.05424 -0.135599,0.135599 q 0.135599,0 0.162719,0.02712 0.05424,0 0.05424,0.1356 0,0.02712 0.05424,0.05424 0.05424,0 0.02712,0.02712 0.05424,-0.05424 0.08136,-0.10848 0.02712,-0.08136 0.08136,-0.1356 h 0.02712 q 0.02712,0 0.02712,-0.02712 l -0.02712,-0.05424 z m 0.650877,1.247516 h -0.135599 q -0.08136,0 -0.216959,0.02712 -0.10848,0.02712 -0.16272,0.135599 h 0.08136 q 0.135599,0 0.216959,-0.05424 0.10848,-0.05424 0.216959,-0.108479 z m 0.596638,-1.328875 -0.08136,-0.08136 -0.08136,0.08136 0.08136,0.05424 z m 0.569518,1.762793 q -0.05424,-0.05424 -0.10848,-0.135599 -0.05424,-0.08136 -0.135599,-0.08136 l -0.05424,0.02712 0.16272,0.162719 z m 0.433918,-0.298319 -0.162719,0.189839 q 0.02712,0.18984 0.1356,0.379679 l 0.02712,-0.271199 z m -6.807094,-5.722299 q -0.08136,0.05424 -0.18984,0.216959 h 0.216959 v -0.108479 q 0,-0.02712 -0.02712,-0.05424 0,-0.02712 0,-0.05424 z m 2.006872,3.037429 -0.05424,0.05424 0.05424,0.05424 0.05424,-0.05424 z m -2.033992,-0.569518 0.08136,0.08136 h 0.02712 l -0.02712,0.05424 0.08136,0.05424 0.08136,-0.05424 -0.02712,-0.05424 0.05424,-0.08136 z m 0.515278,-1.193275 0.135599,0.135599 v -0.271199 l -0.08136,0.05424 z m -1.328875,0.08136 0.08136,-0.05424 0.08136,-0.216959 -0.08136,-0.244079 -0.08136,-0.08136 z m -0.406799,-1.084796 q -0.05424,0.08136 -0.05424,0.271199 v 0.271199 l 0.244079,-0.244079 -0.05424,-0.162719 z m 1.220396,0 0.08136,0.05424 0.05424,0.08136 v -0.271199 z m -1.111916,-0.325439 q -0.05424,0 -0.16272,0.05424 0.05424,0.05424 0.08136,0.10848 0.05424,0.02712 0.10848,0.05424 0.08136,-0.08136 0.135599,-0.21696 z m 1.817033,-0.08136 -0.05424,0.05424 0.05424,0.02712 0.05424,-0.02712 z m 1.003436,0 -0.05424,0.05424 0.05424,0.02712 0.05424,-0.02712 z m -1.708553,-0.216959 q 0,0.05424 0.02712,0.189839 0.02712,0.135599 0.08136,0.189839 v -0.216959 z m -0.1356,-0.24408 -0.433918,-0.162719 -0.16272,0.271199 h 0.18984 v 0.08136 l -0.21696,0.352558 0.21696,0.433919 -0.1356,0.08136 -0.08136,0.108479 0.271199,0.05424 h 0.433919 v -0.271199 h -0.352559 q 0,-0.216959 -0.02712,-0.406799 0,-0.216959 0,-0.433918 h 0.10848 l 0.162719,0.379679 q 0,-0.1356 0,-0.244079 0,-0.10848 0.02712,-0.24408 z m 3.742546,6.020618 -0.05424,-0.05424 -0.02712,0.05424 0.02712,0.05424 z m 0.325439,0.406799 -0.135599,0.135599 0.05424,0.08136 0.08136,0.05424 z m 0.298319,-0.352559 h -0.10848 q -0.02712,0 -0.05424,0.02712 -0.02712,0 -0.05424,0 0.05424,0.08136 0.216959,0.189839 z m 0.433918,-1.654314 -0.08136,-0.08136 q -0.02712,0 -0.08136,0.05424 -0.02712,0.02712 -0.05424,-0.02712 -0.08136,0.08136 -0.189839,0.16272 -0.08136,0.08136 -0.08136,0.216959 v 0.02712 q 0.244079,-0.1356 0.352559,-0.325439 0.02712,0.05424 0.08136,0.02712 0.05424,-0.02712 0.05424,-0.05424 z m -0.135599,1.193276 -0.05424,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m 0.08136,-0.894957 -0.02712,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m -0.406799,1.762794 q -0.108479,0.135599 -0.189839,0.325438 -0.05424,0.16272 -0.05424,0.352559 0.1356,-0.135599 0.216959,-0.298319 0.08136,-0.189839 0.16272,-0.352559 l -0.10848,0.623758 0.406799,-0.515278 z m 0.515278,-2.332312 h -0.108479 l -0.08136,0.05424 0.08136,0.10848 0.108479,0.05424 z m 0,1.274635 -0.05424,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m 0.10848,-0.922076 -0.05424,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m 0.189839,0.650877 q 0,-0.02712 -0.02712,-0.02712 0,-0.02712 0,-0.05424 -0.108479,0.08136 -0.189839,0.189839 h 0.216959 z m -5.152781,-1.003436 -0.05424,0.05424 0.05424,0.08136 0.08136,-0.08136 z m -0.05424,-0.677997 -0.135599,0.08136 -0.10848,0.21696 0.10848,0.135599 h 0.135599 z m 1.084796,-0.05424 -0.08136,0.08136 0.08136,0.05424 0.08136,-0.05424 z m 0.867837,-0.16272 -0.08136,0.08136 0.08136,0.08136 0.08136,-0.08136 z m -0.569518,0.10848 0.08136,0.1356 q 0.05424,0.08136 0.08136,0.05424 0.02712,-0.05424 0.05424,-0.05424 v 0.05424 0.16272 q -0.108479,-0.08136 -0.379678,-0.18984 l 0.379678,0.379679 0.16272,-0.189839 -0.10848,-0.271199 q 0.05424,-0.05424 0.05424,-0.08136 0.02712,-0.02712 0.08136,-0.08136 -0.10848,-0.189839 -0.1356,-0.379678 l -0.08136,0.108479 q 0,-0.216959 0.02712,-0.325438 h -0.379678 l 0.515278,-0.298319 v -0.488159 q -0.1356,0.18984 -0.298319,0.298319 -0.162719,0.08136 -0.379679,0.16272 -0.05424,0.189839 -0.08136,0.379678 -0.02712,0.16272 -0.05424,0.352559 0.244079,0.1356 0.461038,0.216959 z m 0.298319,-5.613819 q -0.189839,0.108479 -0.244079,0.271199 h 0.298319 v -0.1356 z m -0.162719,-0.596638 -0.08136,0.08136 0.08136,0.08136 0.08136,-0.08136 z m -1.274636,5.234141 q 0.08136,0.108479 0.18984,0.189839 v -0.379679 z m -0.325438,-0.569518 -0.08136,0.08136 0.08136,0.05424 0.08136,-0.05424 z m 1.193275,-0.433919 0.18984,0.18984 V 29.59154 q -0.10848,0.08136 -0.18984,0.189839 z m -0.162719,-0.433918 -0.08136,0.05424 0.08136,0.08136 0.05424,-0.08136 z m -0.542398,-0.244079 -0.05424,0.05424 q 0,0.406798 0.1356,0.542398 0.162719,0.135599 0.271199,0.433918 l 0.108479,-0.05424 0.08136,-0.162719 v -0.08136 H 29.9203 q -0.05424,-0.189839 -0.05424,-0.461038 0,-0.271199 -0.216959,-0.271199 z m 0.813597,0.08136 -0.05424,0.08136 0.05424,0.08136 0.08136,-0.08136 z m -0.569518,-0.1356 -0.08136,0.08136 0.08136,0.05424 0.08136,-0.05424 z m -1.301755,-0.135599 -0.08136,0.05424 0.08136,0.08136 0.05424,-0.08136 z m 1.003436,-0.461039 -0.08136,0.08136 0.08136,0.08136 0.08136,-0.08136 z m 0.298319,0 -0.08136,0.08136 0.08136,0.08136 0.08136,-0.08136 z m -0.298319,-0.433918 -0.08136,0.08136 0.08136,0.08136 0.08136,-0.08136 z m 1.735674,-0.1356 q -0.05424,-0.05424 -0.16272,-0.135599 -0.108479,-0.10848 -0.108479,-0.16272 l -0.16272,0.16272 v 0.135599 l 0.16272,-0.135599 0.108479,0.108479 z m -1.003437,-3.471347 0.352559,0.16272 v 0.433918 l 0.488158,-0.406798 q -0.05424,0.135599 -0.108479,0.271199 -0.02712,0.108479 -0.08136,0.244079 h 0.298319 v -0.542398 q -0.16272,-0.05424 -0.325439,-0.10848 -0.162719,-0.05424 -0.325439,-0.05424 z m -0.433918,11.607317 -0.08136,0.16272 v 0.135599 h 0.325439 Q 29.97454,36.100316 29.89318,36.018956 Z m 2.440791,-0.298319 -0.02712,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m -0.596638,1.111916 -0.05424,-0.05424 -0.02712,0.05424 0.02712,0.05424 z m 2.413671,-0.705117 -0.05424,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m 0,-0.976316 q -0.02712,0.05424 -0.135599,0.108479 l 0.135599,0.1356 z m -0.108479,0.298318 q -0.05424,0.02712 -0.10848,0.1356 0.05424,0.02712 0.10848,0.135599 z m -0.08136,0.433919 -0.05424,-0.10848 q -0.05424,0.05424 -0.108479,0.10848 -0.02712,0.02712 -0.05424,0.08136 h 0.216959 z m -0.216959,0.650877 -0.02712,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m -0.08136,0.298319 -0.05424,-0.05424 -0.05424,0.05424 0.05424,0.05424 z m -0.10848,-0.894956 -0.135599,0.135599 h 0.271199 l -0.08136,-0.05424 z m 0.10848,-0.325439 -0.05424,-0.02712 -0.05424,0.02712 0.05424,0.05424 z m -0.406798,-0.02712 -0.10848,-0.10848 -0.10848,0.10848 0.10848,0.08136 z m -3.796786,1.328875 0.162719,-0.05424 0.135599,-0.108479 -0.135599,-0.02712 h -0.162719 z m 6.291816,-0.10848 0.16272,-0.108479 0.05424,-0.10848 h -0.298319 v 0.10848 z m -2.142472,-0.108479 0.02712,-0.10848 v -0.108479 q -0.08136,0.02712 -0.189839,0.02712 -0.08136,0 -0.16272,0 0.21696,0.108479 0.325439,0.189839 z m 1.491595,-0.10848 0.05424,-0.05424 -0.05424,-0.05424 -0.05424,0.05424 z m -3.389988,-0.976316 v 0.216959 h 0.08136 l 0.10848,-0.05424 q -0.05424,-0.05424 -0.10848,-0.08136 -0.02712,-0.02712 -0.08136,-0.08136 z m -0.352558,-0.02712 0.05424,-0.05424 q 0,-0.135599 -0.108479,-0.135599 -0.02712,0 -0.08136,0.05424 -0.02712,0.02712 -0.05424,0.05424 l 0.10848,0.271199 q 0,-0.216959 -0.02712,-0.189839 0,0 0.10848,0 z m 0.325438,-0.244079 0.18984,0.271199 q 0,0.08136 0.05424,0.108479 v -0.02712 l 0.18984,0.162719 h 0.08136 l -0.162719,-0.298319 q 0,-0.02712 -0.02712,-0.05424 0,-0.05424 -0.02712,-0.02712 L 31.764453,35.124 q 0,0.05424 -0.02712,0.108479 -0.02712,0.05424 -0.05424,0.10848 z m 2.413671,1.111916 -0.08136,-0.08136 -0.08136,0.08136 0.08136,0.05424 z m -1.464474,-1.301755 -0.08136,-0.08136 -0.05424,0.08136 0.05424,0.05424 z m -1.030556,0.433918 -0.05424,-0.08136 -0.08136,0.08136 0.08136,0.05424 z m -0.569518,0.705117 -0.08136,-0.05424 -0.05424,0.05424 0.05424,0.08136 z m 4.040865,-1.166155 -0.05424,-0.05424 -0.08136,0.05424 0.08136,0.08136 z m -0.433919,0.298319 -0.05424,-0.05424 -0.08136,0.05424 0.08136,0.08136 z m 0.840717,3.715426 h 8.868208 v 4.529023 l -0.16272,0.244079 H 43.37177 v 0.271199 h -0.298319 l -0.271199,-0.02712 -0.379678,-0.271199 0.271199,-0.515278 Q 42.531053,43.20573 42.395454,43.17861 l -0.16272,0.786477 H 40.57842 q 0.02712,-0.10848 0,-0.189839 -0.02712,-0.10848 -0.05424,-0.18984 -0.21696,0.08136 -0.433919,0.21696 0,0.05424 0,0.162719 0.02712,0.10848 -0.05424,0.10848 l -0.162719,-0.02712 v -0.02712 l -0.08136,-0.16272 q -0.05424,0 -0.08136,0.02712 0,0 -0.05424,0 v -0.05424 -0.08136 l 0.135599,-0.18984 -0.05424,-0.108479 q -0.02712,0 -0.05424,0.02712 0,0.02712 -0.02712,0.02712 l -0.02712,-0.02712 h -0.298319 v 0.08136 l 0.10848,0.16272 -0.1356,0.162719 0.216959,-0.08136 0.05424,0.05424 q 0,0.05424 -0.162719,0.10848 -0.1356,0.05424 -0.16272,0.05424 h -0.05424 l -0.05424,-0.1356 q -0.216959,0.16272 -0.18984,0.08136 0.05424,-0.08136 0.10848,-0.244079 h -0.189839 l 0.05424,-0.1356 v -0.135599 h -0.325439 -0.325439 l 0.433918,0.271199 q -0.135599,0.02712 -0.406798,0.05424 -0.244079,0.02712 -0.244079,0.244079 l -0.352559,0.02712 -0.379678,-0.21696 q -0.10848,0.05424 -0.21696,0.1356 -0.08136,0.05424 -0.162719,0.10848 l -0.10848,-0.298319 1.030557,0.02712 q 0.298318,-0.406799 0.352558,-0.650878 0.08136,-0.244079 0.08136,-0.732237 0.135599,-0.05424 0.244079,-0.02712 0.10848,0 0.189839,0.02712 0.08136,0 0.10848,0 0.05424,0 0.05424,-0.10848 l 0.08136,-0.02712 q 0.08136,0 0.08136,0.02712 0.189839,0.244079 0.325439,0.379679 0.135599,0.108479 0.461038,0.05424 0.02712,0.02712 0.05424,0.05424 0.05424,0.02712 0.08136,0 l -0.05424,0.1356 q 0,-0.02712 -0.05424,-0.02712 -0.02712,0 -0.02712,0.02712 0,0.108479 0.216959,0.05424 0.21696,-0.08136 0.298319,-0.10848 l -0.02712,0.244079 q 0.1356,-0.189839 0.433919,-0.325439 0.08136,0.10848 0.189839,0.18984 h 0.08136 v -0.1356 -0.162719 H 40.76826 l -0.216959,-0.352559 q -0.10848,0.10848 -0.1356,0.216959 l -0.379679,0.08136 v -0.1356 q -0.271199,0 -0.244079,0 0.02712,-0.02712 -0.162719,-0.189839 0.325439,0.05424 0.650878,0.162719 -0.05424,-0.108479 -0.16272,-0.189839 -0.08136,-0.108479 -0.189839,-0.162719 v -0.325439 q -0.16272,0.10848 -0.216959,0.189839 l 0.108479,0.10848 h 0.02712 -0.02712 l -0.189839,-0.05424 0.02712,-0.02712 -0.05424,-0.05424 q -0.05424,0 -0.135599,0.05424 -0.08136,0.05424 -0.08136,0.10848 0,0.02712 -0.10848,0.02712 h -0.189839 q 0,-0.05424 0.02712,-0.10848 0.02712,-0.08136 0.02712,-0.10848 l -0.05424,-0.05424 q 0,0.02712 0,0.02712 v -0.05424 -0.02712 l -0.162719,-0.16272 q 0.02712,-0.05424 -0.05424,-0.08136 -0.08136,-0.05424 -0.10848,-0.08136 -0.379678,0 -0.759357,-0.02712 -0.352559,-0.05424 -0.732237,-0.108479 l 0.244079,0.406798 -0.189839,-0.05424 v 0.1356 0.135599 h 0.298318 l 0.1356,0.244079 -0.10848,-0.108479 q 0.05424,0.379678 0.05424,0.406798 0.02712,0.02712 -0.271199,0.02712 l -0.05424,0.05424 h -0.244079 q 0.1356,0.05424 0.271199,0.135599 0.16272,0.08136 0.298319,0.1356 l -0.216959,0.135599 h -0.02712 q -0.02712,0 -0.10848,-0.08136 v 0.02712 l -0.162719,-0.02712 -0.406798,-0.271199 -0.596638,0.244079 h -0.02712 v -0.05424 l -0.08136,-0.189839 q 0.1356,-0.05424 0.244079,-0.10848 0.10848,-0.08136 0.244079,-0.135599 v -0.02712 h 0.05424 v -0.216959 h -0.05424 l 0.10848,-0.325439 q -0.1356,-0.1356 -0.298319,-0.352559 -0.162719,-0.216959 -0.216959,-0.379679 l -0.461038,0.05424 q -0.02712,0.1356 -0.02712,0.379679 l -0.325439,0.108479 v -0.189839 h -0.515278 q 0.135599,-0.108479 0.244079,-0.189839 0.135599,-0.08136 0.216959,-0.216959 l -0.135599,-0.1356 -0.1356,0.08136 q -0.135599,-0.08136 -0.271199,-0.1356 -0.10848,-0.08136 -0.216959,-0.189839 -0.05424,0.10848 -0.1356,0.05424 v -0.16272 l -0.596637,0.379679 v -0.352559 l 0.488158,-0.352558 -0.02712,0.05424 0.108479,0.08136 0.08136,-0.08136 -0.08136,-0.108479 h -0.05424 q 0,-0.1356 0.135599,-0.16272 0.1356,-0.05424 0.216959,-0.135599 v 0.271199 l 0.271199,-0.162719 0.1356,0.298318 -0.02712,-0.162719 -0.216959,-0.244079 -0.1356,-0.05424 q 0.16272,-0.162719 0.216959,-0.271199 -0.352558,0.10848 -0.705117,0.379679 -0.02712,-0.05424 0,-0.08136 0.05424,-0.02712 0.05424,-0.08136 0,-0.05424 -0.10848,-0.02712 -0.08136,0 -0.135599,0 -0.18984,0 -0.325439,0.10848 -0.10848,0.10848 -0.1356,0.10848 l -0.352558,-0.10848 -0.05424,-0.02712 q 0,-0.02712 0.02712,-0.02712 h 0.05424 l 0.05424,-0.08136 q 0.08136,-0.1356 0.16272,-0.21696 0.108479,-0.08136 0.189839,-0.216959 h 0.1356 q -0.02712,-0.02712 -0.10848,-0.08136 -0.05424,-0.08136 -0.05424,-0.02712 -0.08136,-0.08136 -0.162719,-0.16272 -0.08136,-0.108479 -0.189839,-0.162719 0.135599,0.02712 0.325438,-0.05424 0.21696,-0.10848 0.352559,-0.1356 v 0.596638 l 0.569518,0.298319 q 0.135599,-0.02712 0.298319,-0.05424 0.162719,-0.02712 0.298319,-0.02712 0.244079,0.16272 0.406798,0.406799 l 0.08136,-0.05424 0.08136,-0.189839 q -0.05424,-0.10848 -0.1356,-0.216959 -0.08136,-0.10848 -0.189839,-0.16272 v 0.352559 q -0.08136,-0.08136 -0.244079,-0.1356 l 0.05424,-0.02712 0.108479,-0.325439 q 0.16272,0.108479 0.271199,0.108479 z M 28.021907,24.24892 h 16.19058 v 5.613819 h -9.953003 v 2.088232 H 40.8225 v 4.962942 h -6.346057 l 0.02712,-0.02712 h -0.325439 v -0.05424 l -0.298319,-0.108479 q 0,0.216959 -0.08136,0.189839 -0.08136,-0.02712 -0.298319,-0.02712 -0.18984,0 -0.18984,0 l -0.108479,0.08136 v 0.02712 l -0.1356,-0.10848 -0.02712,-0.08136 h 0.10848 0.244079 q 0.08136,0 0.08136,-0.135599 0,-0.1356 -0.1356,-0.08136 -0.135599,0.02712 -0.189839,0.02712 h -0.244079 v -0.05424 L 32.60517,36.317275 h 0.135599 q 0,0.02712 0.02712,0.05424 0.05424,0.02712 0.08136,-0.02712 l 0.162719,0.02712 h 0.02712 q -0.02712,-0.02712 -0.02712,0 0,0.1356 0.1356,0.1356 v -0.10848 h 0.02712 q 0.02712,-0.02712 0.02712,-0.08136 0,-0.05424 0.05424,-0.05424 0.189839,-0.02712 0.108479,0.05424 -0.05424,0.05424 0.08136,0.05424 h 0.05424 q 0.05424,0 0.05424,-0.02712 0,-0.02712 -0.02712,-0.02712 -0.02712,-0.02712 -0.02712,-0.05424 h 0.05424 0.271199 l 0.02712,0.05424 v -0.05424 h 0.02712 0.02712 -0.02712 -0.02712 v -0.08136 -0.1356 q 0,0.02712 -0.135599,0.08136 -0.1356,0.02712 -0.02712,0.08136 h -0.271199 l -0.05424,-0.05424 q -0.05424,0.02712 0,0.05424 0.05424,0.02712 -0.08136,0.02712 l -0.02712,-0.02712 v -0.271199 q -0.08136,0.08136 -0.1356,0.135599 -0.02712,0.02712 0.10848,0.1356 l -0.488159,-0.10848 -0.05424,-0.02712 q 0,-0.05424 0.05424,-0.08136 0.05424,-0.05424 0.05424,-0.108479 h -0.02712 l -0.02712,0.02712 v -0.244079 q 0.1356,-0.08136 0.24408,-0.271199 v -0.10848 l -0.24408,0.162719 -0.569517,-0.05424 v 0.08136 0.10848 l 0.542398,0.10848 q -0.05424,0.108479 -0.05424,0.08136 l -0.02712,0.02712 h 0.02712 q 0.05424,0 0.08136,0.10848 H 32.60517 q 0.08136,-0.05424 -0.08136,-0.08136 -0.162719,-0.05424 -0.271199,-0.02712 l -0.02712,0.1356 q -0.216959,0.02712 -0.406798,0.02712 -0.16272,0 -0.379679,0.05424 0.02712,0.02712 0.08136,0.10848 0.08136,0.05424 0.08136,0.08136 0,0.08136 -0.02712,0.162719 -0.02712,0.08136 -0.05424,0.162719 h 0.05424 q 0,0.02712 -0.08136,0.02712 -0.02712,0 -0.05424,0.02712 -0.08136,-0.189839 -0.216959,-0.379678 h 0.162719 v -0.10848 -0.08136 h -0.162719 -0.162719 v 0.08136 0.10848 h 0.162719 q -0.08136,0.271199 -0.189839,0.433918 l 0.244079,0.05424 v 0.108479 h 0.298319 v -0.05424 l 0.325439,0.05424 -0.02712,0.16272 -0.02712,-0.02712 -0.1356,0.135599 q -0.08136,-0.05424 -0.189839,-0.08136 -0.10848,-0.05424 -0.216959,-0.02712 0.08136,0.162719 0.189839,0.271199 h 0.352559 l -0.02712,0.325439 q 0.05424,0.02712 0.08136,0.05424 0.02712,0.02712 0.08136,0.05424 l -0.135599,0.05424 v -0.05424 l -0.10848,-0.08136 -0.135599,0.08136 -0.08136,0.10848 h 0.162719 l -0.108479,0.189839 q 0.162719,-0.10848 0.216959,-0.189839 0.02712,0 0.05424,0 0.02712,-0.02712 0,-0.02712 l 0.189839,0.135599 v 0.02712 q -0.08136,0.02712 -0.216959,0.05424 -0.10848,0.02712 -0.10848,0.05424 l -0.216959,0.05424 -0.02712,0.02712 -0.05424,-0.1356 q -0.02712,0.02712 -0.05424,0.02712 0,-0.02712 0.02712,-0.05424 l -0.05424,-0.135599 0.108479,0.02712 -0.02712,-0.05424 -0.135599,-0.16272 q 0.08136,-0.02712 0.189839,-0.135599 v 0.271199 l 0.1356,-0.1356 q 0,-0.02712 -0.05424,-0.162719 -0.05424,-0.1356 -0.10848,-0.189839 -0.108479,0.08136 -0.271199,0.244079 l -0.08136,-0.02712 q -0.16272,-0.16272 -0.379679,-0.244079 l -0.10848,0.08136 -0.02712,-0.02712 0.10848,-0.08136 0.05424,0.02712 -0.02712,-0.05424 0.08136,-0.08136 h 0.379678 l 0.05424,-0.162719 q 0,-0.02712 -0.05424,-0.02712 -0.02712,-0.02712 0,-0.05424 -0.162719,0 -0.325439,0.02712 -0.162719,0.02712 -0.298318,0.08136 -0.02712,-0.05424 -0.10848,-0.02712 -0.05424,0 -0.02712,0.05424 l -0.298319,0.02712 q 0.02712,-0.02712 0.02712,-0.1356 l -0.05424,-0.05424 q -0.08136,0 -0.108479,0.10848 -0.02712,0.108479 -0.10848,0.108479 v -0.08136 -0.216959 q -0.10848,0.162719 -0.189839,0.216959 l 0.08136,0.08136 q -0.244079,0.05424 -0.488158,0.08136 -0.244079,0 -0.488158,0.02712 l 0.08136,-0.189839 v -0.05424 l 0.569518,0.162719 v -0.216959 -0.189839 h -0.1356 l 0.05424,-0.05424 q 0,-0.05424 -0.05424,-0.05424 -0.05424,0 -0.05424,-0.05424 0,-0.02712 0.02712,-0.02712 0.05424,-0.02712 0.05424,-0.05424 0,-0.02712 -0.08136,-0.02712 -0.135599,0 -0.08136,0.05424 0.05424,0.05424 0.05424,0.162719 0,0.10848 -0.08136,0.05424 v -0.1356 l -0.08136,0.08136 -0.05424,-0.189839 -0.189839,0.02712 0.135599,-0.08136 q 0.02712,0.02712 0.10848,-0.05424 0.10848,-0.108479 0.1356,-0.135599 h 0.02712 q 0.05424,0.108479 0.1356,0.189839 0.08136,0.08136 0.162719,0.1356 l -0.02712,0.05424 h 0.02712 0.10848 q 0.08136,0.02712 0.189839,0.10848 0.1356,0.05424 0.216959,0.108479 -0.271199,-0.216959 -0.298318,-0.189839 0,0 0.108479,-0.135599 h -0.216959 l -0.08136,-0.08136 q 0.05424,-0.05424 0.1356,-0.08136 0.08136,-0.05424 0.08136,-0.135599 0,-0.05424 -0.05424,-0.02712 -0.05424,0 -0.02712,0.05424 h -0.461039 q 0.02712,0 0,-0.02712 -0.02712,-0.02712 -0.02712,0.02712 l 0.244079,-0.18984 0.189839,0.1356 -0.1356,-0.162719 0.16272,-0.1356 0.05424,0.162719 0.162719,-0.244079 q -0.10848,-0.162719 -0.189839,-0.216959 l 0.05424,-0.05424 h 0.162719 l 0.05424,-0.08136 q 0.08136,0.189839 0.135599,0.352558 0.05424,0.1356 0.244079,0.271199 l -0.05424,0.08136 0.08136,0.10848 h 0.02712 l -0.325439,0.623757 0.596638,-0.542398 q 0.02712,0.05424 0.05424,0.1356 0.02712,0.08136 0.02712,0.162719 h -0.08136 -0.162719 q 0.162719,0.16272 0.298319,0.189839 v 0.02712 0.02712 -0.02712 -0.02712 l 0.02712,0.02712 v -0.02712 l 0.02712,-0.08136 v -0.10848 h -0.05424 v -0.677997 l -0.352559,0.135599 q -0.02712,0 -0.02712,-0.108479 0,-0.05424 0.05424,-0.16272 l 0.08136,0.10848 0.135599,-0.515278 0.244079,0.135599 q -0.08136,-0.135599 -0.162719,-0.244079 -0.08136,-0.108479 -0.18984,-0.189839 l 0.813597,0.162719 0.1356,-0.189839 0.677997,0.10848 q 0.02712,-0.21696 0.1356,-0.515279 0.271199,0.10848 0.406798,0.1356 l -0.379678,-0.325439 q 0.135599,0 0.244079,0 0.10848,0 0.244079,-0.05424 v -0.325438 l 0.379679,-0.433919 0.02712,-0.271199 h 0.05424 l -0.05424,-0.05424 v -0.623757 q -0.1356,-0.02712 -0.24408,-0.02712 -0.08136,0 -0.216959,0 0.05424,-0.1356 0.02712,-0.271199 0,-0.1356 0,-0.271199 -0.135599,0.08136 -0.271199,0.189839 -0.108479,0.10848 -0.216959,0.216959 -0.05424,-0.02712 -0.08136,0.02712 -0.02712,0.02712 -0.05424,0.05424 L 32.008532,32.24929 q -0.108479,0.02712 -0.135599,-0.02712 0,-0.05424 -0.05424,-0.05424 -0.02712,0 -0.05424,0.02712 -0.02712,0.02712 0,0.05424 h -0.244079 v 0.08136 q 0.05424,0.10848 0.135599,0.189839 0.10848,0.05424 0.10848,0.16272 0,0.02712 -0.02712,0.05424 0,0 0,0.05424 -0.189839,-0.216959 -0.325439,-0.542398 h -0.162719 -0.162719 v 0.10848 l -0.24408,0.08136 v 0.108479 0.1356 l 0.24408,-0.1356 v 0.05424 l -0.433919,0.379679 h -0.216959 l -0.05424,-0.02712 q 0.10848,-0.10848 0.189839,-0.271199 l -0.108479,-0.16272 0.271199,-0.162719 -0.10848,-0.02712 -0.271199,0.08136 -0.135599,-0.18984 -0.216959,0.352559 Q 29.9203,32.41201 29.676221,32.24929 l -0.16272,0.1356 v 0.108479 q 0.298319,0.08136 0.379679,0.08136 0.08136,0 0.08136,0.271199 h 0.108479 q 0.10848,-0.05424 0.16272,-0.162719 v 0.162719 0.1356 q -0.10848,-0.02712 -0.298319,-0.05424 -0.16272,-0.02712 -0.18984,-0.16272 l -0.135599,0.05424 q -0.08136,0.02712 -0.08136,0.02712 0,0 -0.05424,0.02712 0.02712,0.08136 0.02712,0.298319 -0.135599,-0.1356 -0.244079,-0.244079 -0.108479,-0.10848 -0.244079,-0.216959 l 0.05424,-0.10848 -0.1356,0.05424 -0.135599,-0.08136 0.05424,0.10848 -0.16272,0.05424 v -0.189839 h -0.108479 -0.08136 l 0.10848,0.271199 q 0,0.02712 -0.16272,0.216959 -0.162719,0.18984 -0.189839,0.21696 l -0.08136,-0.379679 0.18984,-0.244079 h -0.352559 v -2.033993 l 0.108479,-0.271199 -0.108479,-0.108479 v -0.840717 q 0.02712,-0.05424 0.05424,-0.08136 0.05424,-0.05424 0,-0.108479 l -0.05424,0.02712 v -0.298318 q 0.189839,0.02712 0.379678,-0.05424 -0.05424,-0.02712 -0.135599,-0.10848 -0.05424,-0.08136 -0.10848,-0.08136 h -0.02712 q -0.02712,0.05424 -0.05424,0.08136 -0.02712,0.02712 -0.05424,0.08136 v -0.379679 l 0.189839,-0.189839 -0.189839,0.02712 v -1.030556 q 0.05424,0.08136 0.135599,0.135599 0.10848,0.02712 0.18984,0.08136 l 0.08136,0.108479 q 0.1356,-0.216959 0.298319,-0.406798 0.16272,-0.189839 0.271199,-0.406799 -0.271199,0.21696 -0.569518,0.596638 -0.244079,-0.08136 -0.406798,-0.298319 z\"\r\n"
					+ "         id=\"path22939\" />\r\n"
					+ "    </g>\r\n"
					+ "  </g>\r\n"
					+ "</svg>\r\n"
					+ "";
		}
		
		// image du bouton interrogation
		public static String imgInterogation() {
			return "<svg\r\n"
					+ "   width=\"5.2916665mm\"\r\n"
					+ "   height=\"5.2916665mm\"\r\n"
					+ "   viewBox=\"0 0 5.2916665 5.2916665\"\r\n"
					+ "   version=\"1.1\"\r\n"
					+ "   id=\"svg5\"\r\n"
					+ "   inkscape:version=\"1.1.1 (3bf5ae0d25, 2021-09-20)\"\r\n"
					+ "   sodipodi:docname=\"dessin.svg\"\r\n"
					+ "   xmlns:inkscape=\"http://www.inkscape.org/namespaces/inkscape\"\r\n"
					+ "   xmlns:sodipodi=\"http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd\"\r\n"
					+ "   xmlns=\"http://www.w3.org/2000/svg\"\r\n"
					+ "   xmlns:svg=\"http://www.w3.org/2000/svg\">\r\n"
					+ "  <sodipodi:namedview\r\n"
					+ "     id=\"namedview7\"\r\n"
					+ "     pagecolor=\"#ffffff\"\r\n"
					+ "     bordercolor=\"#666666\"\r\n"
					+ "     borderopacity=\"1.0\"\r\n"
					+ "     inkscape:pageshadow=\"2\"\r\n"
					+ "     inkscape:pageopacity=\"0.0\"\r\n"
					+ "     inkscape:pagecheckerboard=\"0\"\r\n"
					+ "     inkscape:document-units=\"mm\"\r\n"
					+ "     showgrid=\"false\"\r\n"
					+ "     fit-margin-top=\"0\"\r\n"
					+ "     fit-margin-left=\"0\"\r\n"
					+ "     fit-margin-right=\"0\"\r\n"
					+ "     fit-margin-bottom=\"0\"\r\n"
					+ "     inkscape:zoom=\"17.210034\"\r\n"
					+ "     inkscape:cx=\"14.032511\"\r\n"
					+ "     inkscape:cy=\"4.2126587\"\r\n"
					+ "     inkscape:window-width=\"1920\"\r\n"
					+ "     inkscape:window-height=\"1009\"\r\n"
					+ "     inkscape:window-x=\"-8\"\r\n"
					+ "     inkscape:window-y=\"-8\"\r\n"
					+ "     inkscape:window-maximized=\"1\"\r\n"
					+ "     inkscape:current-layer=\"layer1\" />\r\n"
					+ "  <defs\r\n"
					+ "     id=\"defs2\" />\r\n"
					+ "  <g\r\n"
					+ "     inkscape:label=\"Calque 1\"\r\n"
					+ "     inkscape:groupmode=\"layer\"\r\n"
					+ "     id=\"layer1\"\r\n"
					+ "     transform=\"translate(-0.9827652,-0.55546188)\">\r\n"
					+ "    <circle\r\n"
					+ "       style=\"fill:#0000ff;stroke-width:0.179715;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "       id=\"path853\"\r\n"
					+ "       cx=\"3.6285985\"\r\n"
					+ "       cy=\"3.2012951\"\r\n"
					+ "       r=\"2.6458333\" />\r\n"
					+ "    <circle\r\n"
					+ "       style=\"fill:#000080;stroke-width:0.149432;stroke-linecap:round;stroke-linejoin:round;paint-order:fill markers stroke\"\r\n"
					+ "       id=\"circle1101\"\r\n"
					+ "       cx=\"3.6285985\"\r\n"
					+ "       cy=\"3.2012951\"\r\n"
					+ "       r=\"2.1999936\" />\r\n"
					+ "    <g\r\n"
					+ "       aria-label=\"?\"\r\n"
					+ "       transform=\"scale(1.0734341,0.93158952)\"\r\n"
					+ "       id=\"text2351\"\r\n"
					+ "       style=\"font-size:5.79799px;line-height:1.25;fill:#ffffff;stroke-width:0.14495\">\r\n"
					+ "      <path\r\n"
					+ "         d=\"M 3.648299,5.462408 H 3.0707648 V 4.8650565 H 3.648299 Z\"\r\n"
					+ "         id=\"path913\"\r\n"
					+ "         sodipodi:nodetypes=\"ccccc\" />\r\n"
					+ "      <path\r\n"
					+ "         d=\"M 4.7522415,2.4358753 C 4.7629405,2.8121477 4.5728746,3.1724916 4.2889115,3.4124215 4.0765321,3.6004941 3.8326951,3.7482185 3.5886801,3.8910348 V 4.528021 H 3.0819222 V 3.6645509 C 3.4348884,3.4576031 3.8301244,3.2744103 4.0603153,2.9191443 4.186844,2.7177144 4.2076563,2.4538348 4.1329826,2.2298501 4.0340218,1.9717328 3.7399573,1.8592786 3.4826485,1.8466631 3.0951328,1.8205012 2.7139841,1.951098 2.380491,2.135784 2.336159,2.131554 2.371151,2.048014 2.360005,2.0105995 V 1.5582499 C 2.740861,1.4257904 3.1460986,1.3473326 3.5504973,1.3626891 c 0.3925487,0.01248 0.805306,0.162408 1.0344805,0.4971893 0.1168547,0.1674941 0.1684716,0.3733354 0.1672637,0.5759969 z\"\r\n"
					+ "         id=\"path851\"\r\n"
					+ "         sodipodi:nodetypes=\"ccccccccccccccc\"\r\n"
					+ "         style=\"fill:#ffffff\" />\r\n"
					+ "    </g>\r\n"
					+ "  </g>\r\n"
					+ "</svg>";
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
