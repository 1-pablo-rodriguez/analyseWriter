package MEPTL;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cXML.node;
/**
 * 
 * @author pablo rodriguez
 *
 */
public class outils {
	
	private static int pointsClass = 0;
	private static int pointTotal=0;
	private static int pointEnJeu = 0;

	
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public static String Traduction(String code) {

		//**************************************************************
		//**  PARAGRAPHE
		//**************************************************************
		String colorPo = "#FF0000";
		//Nom des styles de paragraphe
		if(code.equals("Heading_20_1")) code="<b>Titre 1</b>";
		if(code.equals("Heading_20_2")) code="<b>Titre 2</b>";
		if(code.equals("Heading_20_3")) code="<b>Titre 3</b>";
		if(code.equals("Standard_20__28_user_29_")) code="<b>Standard</b>";
		
		//meta données
		if(code.equals("dc:subject texte")) code="<div class=\"tooltip2\">Valeur de la méta donnée Sujet<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Description</span></div>";
		if(code.equals("dc:title texte")) code="<div class=\"tooltip2\">Valeur de la méta donnée Titre<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Description</span></div>";
		if(code.equals("meta:user-defined Auteur2")) code="<div class=\"tooltip2\">La méta donnée Auteur2<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Propriétés personnalisées<br>Cliquez sur le bouton \"Ajouter une propriété\" pour ajouter une méta données.<br><br><b><u>ATTENTION</u></b>: Vous devez tapez comme nom de la méta donnée <b>Auteur2<b><br>Exactement ces caractères, ne tapez pas d'espace après le dernier caractère.<br>Sinon vous aurez une valeur <b>null</b></span></div>";
		if(code.equals("meta:user-defined Date du contrôle")) code="<div class=\"tooltip2\">La méta donnée Date du contrôle<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Propriétés personnalisées<br>Clique sur le bouton \"Ajouter une propriété\" pour ajouter une méta données.<br><br><b><u>ATTENTION</u></b>: Vous devez tapez comme nom de la méta donnée <b>Date du contrôle<b><br>Exactement ces caractères, ne tapez pas d'espace après le dernier caractère.<br>Sinon vous aurez une valeur <b>null</b></span></div>";
		if(code.equals("meta:user-defined Département")) code="<div class=\"tooltip2\">La méta donnée Département<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Propriétés personnalisées<br>Cliquez sur le bouton \"Ajouter une propriété\" pour ajouter une méta données.<br><br><b><u>ATTENTION</u></b>: Vous devez tapez comme nom de la méta donnée <b>Département<b><br>Exactement ces caractères, ne tapez pas d'espace après le dernier caractère.<br>Sinon vous aurez une valeur <b>null</b></span></div>";
		if(code.equals("meta:user-defined Groupe")) code="<div class=\"tooltip2\">La méta donnée Groupe<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Propriétés personnalisées<br>Clique sur le bouton \"Ajouter une propriété\" pour ajouter une méta données.<br><br><b><u>ATTENTION</u></b>: Vous devez tapez comme nom de la méta donnée <b>Groupe<b><br>Exactement ces caractères, ne tapez pas d'espace après le dernier caractère.<br>Sinon vous aurez une valeur <b>null</b></span></div>";
		
		if(code.equals("text:user-defined text:name")) code="<div class=\"tooltip2\">La méta donnée personnalisée<span class=\"tooltiptext2\">Menu Fichier/Propriétés<br>Onglet Propriétés personnalisées<br>Clique sur le bouton \"Ajouter une propriété\" pour ajouter une méta données.</span></div>";
		
		
		
		//style de paragraphe
		if(code.equals("style:style style:master-page-name")) code="Enchaînement insère le style de page";
		if(code.equals("style:style style:next-style-name")) code="Style du paragraphe suivant";
		if(code.equals("style:paragraph-properties fo:border-bottom")) code="Bordure basse du paragraphe";
		if(code.equals("style:paragraph-properties fo:border-top")) code="Bordure haute du paragraphe";
		if(code.equals("style:paragraph-properties fo:border-left")) code="Bordure gauche du paragraphe";
		if(code.equals("style:paragraph-properties fo:border-right")) code="Bordure droite du paragraphe";
		if(code.equals("style:paragraph-properties fo:keep-with-next")) code="Conserver avec le paragraphe suivant";
		if(code.equals("style:paragraph-properties fo:keep-together")) code="Ne pas scinder le paragraphe";
		if(code.equals("style:paragraph-properties fo:margin-top")) code="Espacement au dessus du paragraphe";
		if(code.equals("style:paragraph-properties fo:margin-bottom")) code="Espacement en dessous du paragraphe";
		if(code.equals("style:paragraph-properties fo:orphans")) code="Nombre de ligne d'orpheline";
		if(code.equals("style:paragraph-properties fo:widows")) code="Nombre de ligne de veuve";
		if(code.equals("style:text-properties style:text-underline-color")) code="Couleur de soulignement du paragraphe";
		if(code.equals("style:text-properties style:text-underline-style")) code="Style du soulignement du paragraphe";
		if(code.equals("style:text-properties style:text-underline-width")) code="Epaisseur du trait de soulignement du paragraphe";
		if(code.equals("style:text-properties style:font-name")) code="Police de caractère du paragraphe";
		if(code.equals("style:text-properties fo:font-size")) code="Taille de la police de caractère du paragraphe";
		if(code.equals("style:text-properties style:font-style-name")) code="Style de la police de caractère du paragraphe";
		if(code.equals("style:text-properties fo:text-shadow")) code="Effet de caractère ombré du paragraphe";
		if(code.equals("style:paragraph-properties fo:text-align")) code="Alignement du paragraphe";
		if(code.equals("style:text-properties fo:font-variant")) code="Effet de caractère petite majuscule";
		if(code.equals("style:paragraph-properties fo:line-height")) code="Interligne";
		if(code.equals("style:paragraph-properties fo:text-indent")) code="Retrait de première ligne";
		if(code.equals("style:style style:parent-style-name")) code="Hérite du style";
		if(code.equals("style:paragraph-properties fo:text-indent")) code="Retrait";
		if(code.equals("style:paragraph-properties fo:padding-top")) code="Remplissage (espacement) en haut";
		if(code.equals("style:paragraph-properties fo:padding-left")) code="Remplissage (espacement) à gauche";
		if(code.equals("style:paragraph-properties fo:padding-right")) code="Remplissage (espacement) à droite";
		if(code.equals("style:paragraph-properties fo:padding-bottom")) code="Remplissage (espacement) en bas";
		if(code.equals("style:text-properties style:text-underline-type")) code="Style du trait de soulignage";
		if(code.equals("style:paragraph-properties style:tab-stop-distance")) code="Distance du stop de la tabulation";
		if(code.equals("style:text-properties fo:font-weight")) code="Style texte GRAS";
		
		
		//style de page
		if(code.equals("style:master-page style:name")) code="Nom du style de page";
		if(code.equals("style:page-layout style:page-usage")) code="Mise en page de la page";
		if(code.equals("style:page-layout-properties fo:page-width")) code="Largeur de la page";
		if(code.equals("style:page-layout-properties fo:page-height")) code="Hauteur de la page";
		if(code.equals("style:page-layout-properties fo:margin-right")) code="Marge à droite de la page";
		if(code.equals("style:page-layout-properties fo:border")) code="Les 4 bordures de la page";
		if(code.equals("style:page-layout-properties fo:margin-left")) code="Marge à gauche de la page";
		if(code.equals("style:page-layout-properties fo:margin-bottom")) code="Marge en bas de la page";
		if(code.equals("style:page-layout-properties fo:margin-top")) code="Marge en haut de la page";
		if(code.equals("style:page-layout-properties fo:margin-right")) code="Marge à droite de la page";
		if(code.equals("text:chapter text:display")) code="Champ chapitre";
		if(code.equals("text:chapter Contenu textuel")) code="Valeur du champ chapitre";
		if(code.equals("style:header-footer-properties fo:border-bottom")) code="<b>Entête ou Pied de page</b> : Bordure inférieure";
		if(code.equals("style:header-footer-properties fo:border-right")) code="<b>Entête ou Pied de page</b> : Bordure droite";
		if(code.equals("style:header-footer-properties fo:border-left")) code="<b>Entête ou Pied de page</b> : Bordure gauche";
		if(code.equals("style:header-footer-properties fo:border-top")) code="<b>Entête ou Pied de page</b> : Bordure haute";
		if(code.equals("style:header-footer-properties fo:margin-bottom")) code="<b>Entête ou Pied de page</b> : Marge en dessous";
		if(code.equals("style:header-footer-properties fo:margin-top")) code="<b>Entête ou Pied de page</b> : Marge au dessus";
		if(code.equals("style:master-page style:next-style-name")) code="Style de la page suivante";
		if(code.equals("text:bookmark-ref text:ref-name")) code="Nom du repère de texte";
		if(code.equals("text:bookmark-ref text:reference-format")) code="Réfèrence du repère de texte";
		if(code.equals("style:columns fo:column-count")) code="Nombre de colonne";
		if(code.equals("style:columns fo:column-gap")) code="Espacement entre les colonnes<br><i>Gouttière</i>";
		if(code.equals("style:header-footer-properties fo:border")) code="Les 4 bordures du pied de page<br><i>droite gauche bas haut</i>";
		if(code.equals("style:header-footer-properties fo:border")) code="Les 4 bordures de l'entête<br><i>droite gauche bas haut</i>";
		
		
		
		
		//style structure
		if(code.equals("page style:master-page-name")) code="Nom du style de page";
		if(code.equals("page numeroabsolue")) code="<div class=\"tooltip2\">Position (numéro) absolue de la page<br>par rapport à l'ensemble des pages<span class=\"tooltiptext2\">C'est l'ordre d'apparition de la page lorsque le mode \"<b>Livre</b>\" est utilisé.<br>Dans le mode d'affichage \"<b>Livre</b>\" toutes les pages s'affichent, y compris les pages vides.";
		if(code.equals("text:title Contenu textuel")) code="Valeur de la méta donnée \"<b>Titre</b>\"";
		if(code.equals("text:subject Contenu textuel")) code="Valeur de la méta donnée \"<b>Sujet</b>\"";
		if(code.equals("draw:frame draw:name")) code="<div class=\"tooltip2\" style=\"color:>" + colorPo + "\">Nom de l'objet indiqué dans<br>l'onglet \"<b>Options</b>\"<br>de la boite \"<b><u>Propriétés</u></b>\" de l'objet.<span class=\"tooltiptext2\">Si l'objet ne se nomme pas <b><u>EXACTEMENT</u></b> comme indiqué dans la consigne.<br><br>L'algorithme d'analyse ne pourra pas trouver l'objet.Vous aurez que des valeurs <b><u>NULL</u></b><br><br>Faites attention à la case (majuscule et minuscule). Ne tapez pas d'espace après le dernier caractère. Ne tapez pas de guillemet, etc.</span></div>";
		if(code.equals("text:description Contenu textuel")) code="<div class=\"tooltip2\" style=\"color:>" + colorPo + "\">Champ <b>Commentaires</b><span class=\"tooltiptext2\">Pour insérer le champ \"<b>Commentaires</b>\".<br>Sélectionner le menu Insertion/Champ/Autres champs...<br><br>Dans la boite de dialogue \"Champ\"<br>Onglet \"Info document\"</span></div>";
		if(code.equals("text:description Contenu textuel")) code="<div class=\"tooltip2\" style=\"color:>" + colorPo + "\">Champ <b>Commentaires</b><span class=\"tooltiptext2\">Pour insérer le champ \"<b>Commentaires</b>\".<br>Sélectionner le menu Insertion/Champ/Autres champs...<br><br>Dans la boite de dialogue \"Champ\"<br>Onglet \"Info document\"</span></div>";
		if(code.equals("page style:page-usage")) code="<div class=\"tooltip2\">Mise en page de la page<span class=\"tooltiptext2\">Dans les <b>Propriétés</b> du style de page<br>Onglet <b>Page</b><br><b>Mise en page</b></span></div>";
		if(code.contains("text:illustration-index-source") && code.contains("text:caption-sequence-name")) code="Catégorie de la légende";
		if(code.equals("text:index-title-template Contenu textuel")) code="Titre de l'index";
		if(code.equals("text:table-of-content text:protected")) code="Protection de l'index";
		if(code.contains("text:table-of-content-source") && code.contains("text:outline-level")) code="Niveau de plan de l'index<br>Type &ldquo;Table des matières&ldquo;";
		if(code.equals("text:conditional-text text:condition")) code="La condition du texte conditionnel";
		if(code.equals("text:conditional-text text:string-value-if-true")) code="Si la condition est <b>Vrai</b> affiche le texte";
		if(code.equals("text:conditional-text text:string-value-if-false")) code="Si la condition est <b>Fausse</b> affiche le texte";
		if(code.equals("text:date text:fixed")) code="La date est fixe";
		if(code.equals("text:date style:data-style-name")) code="Style de la date";
		if(code.equals("text:span Contenu textuel")) code="Contenu textuel";
		if(code.equals("text:p Contenu textuel")) code="Contenu textuel";
		if(code.equals("text:p Contenu textuel")) code="Contenu textuel";
		if(code.equals("text:date text:date-value")) code="La date";
		if(code.equals("text:section text:name")) code="Nom de la section";
		if(code.equals("text:section text:condition")) code="<div class=\"tooltip2\">Condition de la section<span class=\"tooltiptext2\"><b><u>Attention :</u></b><br>Ne tapez pas d'espace après le dernier guillemet du texte.<br>Ne tapez pas d'espace après le dernier caractère de votre condition.</span></div>";
		if(code.equals("text:section Contenu textuel")) code="Contenu textuel de la section";
		if(code.equals("style:paragraph-properties fo:break-before")) code="Type de saut placé avant";
		if(code.equals("style:paragraph-properties fo:padding")) code="Remplissage (padding) du paragraphe";
		if(code.equals("style:text-properties fo:color")) code="Couleur de la police";
		if(code.equals("text:h text:style-name")) code="Nom du style du paragraphe Titre";
		if(code.equals("text:change-start Contenu textuel")) code="Insertion du texte";
		//if(code.equals("style:style fo:text-align")) code="Alignement du paragraphe";
		
		
		
		//frame
		if(code.equals("draw:frame text:anchor-type")) code="Ancrage de l'objet";
		if(code.equals("draw:frame text:anchor-page-number")) code="Ancrage dans la page numéro";
		if(code.equals("draw:frame svg:y")) code="Position (distance) verticale<br>de l'objet";
		if(code.equals("draw:frame svg:x")) code="Position (distance) horizontale<br>de l'objet";
		if(code.equals("draw:frame svg:height")) code="Hauteur de l'objet";
		if(code.equals("draw:frame svg:width")) code="Largeur de l'objet";
		if(code.equals("style:graphic-properties fo:padding")) code="Remplissage (marge)<br>avec les bords du cadre de l'objet";
		if(code.equals("style:graphic-properties style:vertical-pos")) code="Position verticale de l'objet par rapport à";
		if(code.equals("style:graphic-properties style:horizontal-pos")) code="Position horizontale de l'objet par rapport à";
		if(code.equals("style:graphic-properties fo:border")) code="Les 4 bordures de l'objet";
		if(code.equals("style:graphic-properties fo:margin-bottom")) code="Espacement en dessous de l'objet";
		if(code.equals("style:graphic-properties fo:margin-top")) code="Espacement au dessus de l'objet";
		if(code.equals("style:graphic-properties fo:margin-right")) code="Espacement à droite de l'objet";
		if(code.equals("style:graphic-properties fo:margin-left")) code="Espacement à gauche de l'objet";
		if(code.equals("style:graphic-properties style:horizontal-rel")) code="Position horizontale par rapport à";
		if(code.equals("style:graphic-properties style:vertical-rel")) code="Position verticale par rapport à";
		if(code.equals("style:graphic-properties style:wrap")) code="Adaptation du texte";
		if(code.equals("style:graphic-properties style:number-wrapped-paragraphs")) code="Adaptation du texte<br>nombre de paragraphe adapté";
		if(code.equals("style:graphic-properties style:wrap-contour")) code="Adaptation du texte \"<b>Contour</b>\"";
		if(code.equals("text:sequence text:name")) code="<div class=\"tooltip2\">Nom de la variable de <b>Séquence</b> pour légender<span class=\"tooltiptext2\">Menu Insertion/Champ/Autres champs...<br>Onglet \"<b>Variables</b>\"</span></div>";
		if(code.equals("text:sequence Contenu textuel")) code="<div class=\"tooltip2\">La légende avec la variable de <b>Séquence</b><span class=\"tooltiptext2\">Pour légender une image, il faut un clic droite sur l'image et sélectionner <b>Insérer une légende...</b><br>Cependant, il faut retirer la protection du contenu.</span></div>";
		if(code.equals("style:graphic-properties style:protect")) code="<div class=\"tooltip2\">Protection de l'objet</b><span class=\"tooltiptext2\">Dans la boite de dialogue <b>Propriétés</b><br>Onglet Option<br>Vou devez cocher les protections (case à cocher)</span></div>";
		if(code.equals("style:graphic-properties style:wrap-contour-mode")) code="Mode contour du texte";
		if(code.equals("draw:text-box fo:min-height")) code="Hauteur du frame<br>(cadre de texte)";
		if(code.equals("draw:frame Contenu textuel")) code="Paragraphe d'ancrage";
		if(code.equals("text:database-display text:table-type")) code="Type de la source de données";
		if(code.equals("text:database-display text:column-name")) code="<div class=\"tooltip2\">Nom du champ de données<span class=\"tooltiptext2\">Ne tapez pas d'espace à la fin du nom de la colonne, Sinon Null.</span></div>";
		if(code.equals("text:database-display text:database-name")) code="Nom de la base de données";
		if(code.equals("text:database-display text:table-name")) code="Nom de la table de données";
		
		//numérotation hiérarchisée
		if(code.equals("text:outline-level-style style:num-suffix")) code="Suffix (après la numérotation)";
		if(code.equals("text:outline-level-style style:num-prefix")) code="<div class=\"tooltip2\">Prefix (devant la numérotation)<span class=\"tooltiptext2\"><b><u>Attention :</u></b><br>Il peut y avoir devant la numération un espace.<br><br>Par exemple : <b>§[espace]</b>";
		if(code.equals("text:outline-level-style style:num-format")) code="Format de la numérotation";
		if(code.equals("text:outline-level-style text:level")) code="Niveau de la numérotation";
		if(code.equals("style:list-level-properties text:list-level-position-and-space-mode")) code="Position, Espacement de la numérotation";
		if(code.equals("style:list-level-label-alignment text:label-followed-by")) code="<div class=\"tooltip2\">Numérotation suivi d'un(e)<span class=\"tooltiptext2\">Dans la boite de dialogue \"Numérotation des chapitres\"<br>Onglet Position<br>Numerotation suivi par.</span></div>";
		if(code.equals("text:outline-level-style text:display-levels")) code="<div class=\"tooltip2\">Nombre de niveau affiché par la numérotation<span class=\"tooltiptext2\">Dans la boite de dialogue \"Numérotation des chapitre\"<br>Afficher les sous-niveaux.</span></div>";
		
		
		//Table, index, bibliographie
		if(code.equals("txt:p Contenu textuel")) code="<div class=\"tooltip2\">Texte recherché<span class=\"tooltiptext2\"><b><u>Attention</u></b>, il suffit qu'un seul caractère soit différent avec la consigne pour que l'algorithme ne trouve pas le texte.<br>S'il ne trouve pas votre texte alors la valeur est \"<b>null</b>\".<br>L'algorithme peut trouver votre texte, mais s'il y a trop de différence, il n'accorde pas les points.</span></div>";
		
		
		//variable de séquence
		if(code.equals("text:sequence-decl text:name")) code="<div class=\"tooltip2\">Variable de séquence<span class=\"tooltiptext2\">La variable de séquence permet de légender et de créer des index.<br>Pour ajouter une variable de séquence, vous devez sélectionner le menu<br>Insertion/Champ/Autres champs...<br>Onglet \"<b>Variables</b>\".</span></div>";
		
		
		
		// style paragraphe police
//		if(code.equals("style:font-name")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Nom de la police de caractères</p>";
//		if(code.equals("fo:font-size")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Taille des caractères</p>";
//		if(code.equals("style:font-style-name")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Style de la police</p>";
//		if(code.equals("fo:font-family")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Famille de police de caractères</p>";
//		if(code.equals("fo:font-weight")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Style <b>\"Gras\"</b></p>";
//		if(code.equals("fo:font-style")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Style de la font</b></p>";
//		if(code.equals("style:font-size-asian")) code="<b><u><span style=\"color:"+ colorPo +"\">Police</span></u></b><p class=\"p2\">Taille des caractères</b></p>";

		
		
		
		
		
		return code;
	}
	
	
	
	/**
	 * Compare les chaîne de caractères A et sujet en fonction des 7 fonctions ‽ † ¢ → ¦ ↕ ↑ †
	 * 
	 * @param A le texte de l'étudaint
	 * @param Sujet le tetxte du sujet
	 * @return une chaine de caractère contenant "Correct" ou "Erreur"
	 */
	public static String Compare(String A, String Sujet ) {
		
		// les points
		String pointString = "" ; 
		pointEnJeu=0;
		if(Sujet!=null) if(Sujet.contains("‽")) {
			pointString = TraitePoint(Sujet);
			Sujet=Sujet.substring(0, Sujet.indexOf("‽"));
		}
		
		// optionnel pas d'analyse
		if(pointEnJeu==0) return "Optionnel";
		
		
		// autre chose que null, none ou vide (analyse très simple) toutes les autres fonctions sont ignorées
		if(Sujet!=null) if(Sujet.contains("†")) {
			if(A==null) return "Erreur : -" + pointString;
			if(A.equals("none")) return "Erreur : -" + pointString;
			if(A.isEmpty()) return "Erreur : -" + pointString;
			IncrementPointClass(pointEnJeu); 
			return "Correct : +" + pointString;
		}
		
		
		// nettoyage du texte avant de l'analyser si ne contient pas d'autres fonctions cette fonction ne peut pas être combinée
		if(Sujet!=null) if(Sujet.contains("¢") && !Sujet.contains("→") && !Sujet.contains("#") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") ) {
			Sujet = NetTexte(Sujet);
			A = NetTexte(A);
			if(A==null) A="null";
			if(A.contains(Sujet)) {
				IncrementPointClass(pointEnJeu); 
				return "Correct : +" + pointString;
			}
			if(similirudeString(A, Sujet)) {
				IncrementPointClass(pointEnJeu); 
				return "Correct : +" + pointString;
			}
			return "Erreur : -" + pointString;
		}
		
		
		//intervalles valeur simple pas de bordure, pas de couleur, et pas d'autres fonctions
		if(Sujet.contains("→") && A!=null && !Sujet.contains("#") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if (TraitementIntervalle(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString; 
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		
		// bordures simple sans intervalles ou autres fonctions
		if(Sujet.contains("#") && Sujet.contains("pt") && Sujet.contains(" ")  && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢") && A!=null) {
			if(TraitementBordure2(Sujet,A).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString; 
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		
		// couleur simple pas de bordure ou pas d'autres fonctions
		if(Sujet.contains("#") && !Sujet.contains("pt") && Sujet.contains(" ") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢") && A!=null) {
			if(TraitementCouleur(Sujet,A).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString; 
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//Exact avec OU sans d'autres fonctions OU
		if(Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if(TraitementOUExact(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//Opérateur OU avec nettoyage du texte 
		if(Sujet.contains("↕") && !Sujet.contains("¦") && !Sujet.contains("↑") && !Sujet.contains("¢")) {
			if(TraitementOU(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		
		//Opérateur ou avec nettoyage des caractères spéciaux et des chiffres
		if(Sujet.contains("↑") && !Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("¢")) {
			if(TraitementOUSupprimeChiffreEtsansCase(A,Sujet).equals("Correct : ")) {
				IncrementPointClass(pointEnJeu);
				return "Correct : +" + pointString;
			}else {
				return "Erreur : -" + pointString; 
			}
		}
		

		// ne contient aucune fonction
		if(!Sujet.contains("¦") && !Sujet.contains("↕") && !Sujet.contains("↑") && !Sujet.contains("¢") && !Sujet.contains("†") && !Sujet.contains("→")) {
			if(A!=null)if(A.equals("auto")) A=null;
			if(Sujet!=null)if(Sujet.equals("auto")) Sujet=null;
			
			if(A!=null) if(A.equals("0cm")) A=null;
			if(Sujet!=null) if(Sujet.equals("0cm")) Sujet=null;
			
			if(A!=null) if(A.equals("none")) A=null;
			if(Sujet!=null) if(Sujet.equals("none")) Sujet=null;
			
			if(A!=null) if(A.isEmpty()) A=null;
			if(Sujet!=null) if(Sujet.isEmpty()) Sujet=null;
			
			if(A!=null) if(A.equals("0")) A=null;
			if(Sujet!=null) if(Sujet.equals("0")) Sujet=null;
			
			if(A!=null) if(A.equals("null")) A=null;
			if(Sujet!=null) if(Sujet.equals("null")) Sujet=null;
			
			if(A!=null) if(A.equals("false")) A=null;
			if(Sujet!=null) if(Sujet.equals("false")) Sujet=null;
			
			
			if(A!=null) if(Sujet==null) if(!pointString.isEmpty()) { return "Erreur : -" + pointString;}
			if(A!=null) if(Sujet==null) if(pointString.isEmpty()) {return "Erreur";}
			if(A==null) if(Sujet==null) if(!pointString.isEmpty()){IncrementPointClass(pointEnJeu); return "Correct : +" + pointString;}
			if(A==null) if(Sujet==null) if(pointString.isEmpty()) {IncrementPointClass(pointEnJeu);  return "Correct";}
			
			if(A==null) if(Sujet!=null) if(!pointString.isEmpty()) return "Erreur : -" + pointString;
			if(A==null) if(Sujet!=null) if(pointString.isEmpty()) return "Erreur";
			
			
			
			if(!pointString.isEmpty()) {
				if(A.equals(Sujet)){
					IncrementPointClass(pointEnJeu); 
					return "Correct : +" + pointString;}
				else {
					return "Erreur : -" + pointString;
				}
			}else {
				if(A.equals(Sujet)){
					IncrementPointClass(pointEnJeu); 
					return "Correct";}
				else {
					return "Erreur";
				}
			}
		}else {
			 return "Erreur combinaison †¢→¦↕↑";
		}

	}
	
	
	/**
	 * Traitement des points du paramètre
	 * @param B
	 * @return
	 */
	public static String TraitePoint(String B) {
		int point = 0;
		pointEnJeu=0;
		String pointString ="";
		String C =B.substring(B.indexOf("‽")+1,B.length());
		if(B!=null) if(B.contains("‽")) if(B.length()>1) if(!C.isEmpty()) {
			point = Integer.valueOf(B.substring(B.indexOf("‽")+1,B.length()));
		}else {
			point=0;
		}
		
		if(point==0) {
			pointString="";
		}
		
		if(point>0) {
			pointString = Integer.valueOf(point) + " pt";
			IncrementPointTotal(point);
			pointEnJeu= point;
		}
	
		return pointString;
	}
	
	/**
	 * Traitement des intervalles
	 * @return
	 */
	private static String TraitementIntervalle(String A, String B) {
		if(A.isEmpty()) return "Erreur : ";

	
		
		String Text[] = B.split("→");
		
		// rechercher un digite une ou plusieur fois en fin.
		Pattern p = Pattern.compile("[^0-9\\.]");
		// création du moteur associé à la regex sur la chaîne A
//		Matcher m0 = p.matcher(Text[0]);
		
		// remplacement de toutes les occurrences par ""
		Text[0]= p.matcher(Text[0]).replaceAll("");
		Text[1] = p.matcher(Text[1]).replaceAll("");
		A = p.matcher(A).replaceAll("");
		
		double b0 = Double.valueOf(Text[0]);
		double b1 = Double.valueOf(Text[1]);
		
		if(A.isEmpty()) return "Erreur : ";
		double v = Double.valueOf(A);
		
		if(v<=b1 && v>=b0 ) return "Correct : ";
		
		return "Erreur : ";
	}
	
	
	

	
	
	private static String TraitementBordure2(String Sujet, String B) {
		if(Sujet.isEmpty()) return "Erreur : ";
		
		String TextB[] = B.split(" ");
		String TextA[] = Sujet.split(" ");
		
		if(TextB.length!=3 || TextA.length!=3) return "Erreur : ";
		
		boolean taille = false;
		boolean type = false;
		boolean couleur =false;
		
		// traitement de la taille (epaisseur du trait)
		if(TextA[0].contains("→")) {
			if(TraitementIntervalle(TextB[0], TextA[0]).equals("Correct : ")) taille = true;
		}else {
			double tailleA = Double.valueOf(TextA[0].replace("pt",""));
			double tailleB = Double.valueOf(TextB[0].replace("pt",""));
			double IT = 0.02;
			if((tailleA<tailleB+IT) && (tailleA>tailleB-IT))taille = true;
		}
		
		
		// traitement du type de trait
		String typeA = TextA[1];
		String typeB = TextB[1];
		
		//trait pointillé
		if(typeA.equals("dash-dot")) typeA="dashed";
		if(typeB.equals("dash-dot")) typeB="dashed";
		if(typeA.equals("dotted")) typeA="dashed";
		if(typeB.equals("dotted")) typeB="dashed";
		if(typeA.equals("fine-dashed")) typeA="dashed";
		if(typeB.equals("fine-dashed")) typeB="dashed";
		if(typeB.equals("dash-dot-dot")) typeB="dashed";
		if(typeA.equals("dash-dot-dot")) typeA="dashed";
		
		if(typeA.equals("double-thin")) typeA="double";
		if(typeB.equals("double-thin")) typeB="double";
		if(typeA.equals("outset")) typeA="double";
		if(typeB.equals("outset")) typeB="double";
		if(typeA.equals("inset")) typeA="double";
		if(typeB.equals("inset")) typeB="double";
		
		
		if(typeA.equals("groove")) typeA="solid";
		if(typeB.equals("groove")) typeB="solid";
		if(typeA.equals("ridge")) typeA="solid";
		if(typeB.equals("ridge")) typeB="solid";
		 
		
		if(typeA.equals(typeB)) type=true;
		
		// traitement de la couleur du trait
		String couleurA = TextA[2].replace("#", "");
		String couleurB = TextB[2].replace("#", "");
		
		if(couleurA.equals(couleurB)) couleur = true;
		
		if(!couleur) {
			if(TraitementCouleur(couleurA, couleurB).equals("Correct : ")) {
				couleur =true;
			}
		}
		
		if(taille && type && couleur) return "Correct : ";
		
		return "Erreur : ";
	}
	
	/**
	 * Traitement de la couleur pour comparaison
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementCouleur(String A, String B) {
		if(A.isEmpty()) return "Erreur : ";
		
		// traitement de la couleur du trait
		String couleurA = A.replace("#", "");
		String couleurB = B.replace("#", "");
		
		if(couleurA.equals(couleurB)) return "Correct : ";
		
		if(couleurA.length()==6 && couleurB.length()==6) {
			int  Ared = Integer.parseInt(couleurA.substring(0, 2),16);
			int  Agreen = Integer.parseInt(couleurA.substring(2, 4),16);
			int  Ablue = Integer.parseInt(couleurA.substring(4, 6),16);
			
			int  Bred = Integer.parseInt(couleurB.substring(0, 2),16);
			int  Bgreen = Integer.parseInt(couleurB.substring(2, 4),16);
			int  Bblue = Integer.parseInt(couleurB.substring(4, 6),16);
			
			// couleur dominante avec 10 niveau
			if(Ared>Agreen+10 && Ared>Ablue+10 && Bred>Bgreen+10 && Bred>=Bblue+10) return "Correct : ";//couleur=true; //Couleur dominante rouge
			if(Agreen>Ared+10 && Agreen>Ablue+10 && Bgreen>Bred+10 && Bgreen>Bblue+10) return "Correct : "; //couleur = true; //Couleur dominante vert
			if(Ablue>Ared+10 && Ablue>Agreen+10 && Bblue>Bred+10 && Bblue>Bgreen+10) return "Correct : "; //couleur = true; //Couleur dominante bleu
			if(Ablue==Ared && Ablue==Agreen && Bblue==Bred && Bblue==Bgreen)  return "Correct : "; //couleur = true; //Couleur gris (tous les gris)
			if(procheCouleur(Ared,Bred,Agreen,Bgreen,Ablue,Bblue))  return "Correct : "; //couleur = true; //Couleur gris (tous les gris)
		}
		//if(couleur) return "Correct : ";
		
		return "Erreur : ";
	}
	
	/**
	 * couleur proche
	 * @param S1
	 * @param S2
	 * @return
	 */
	private static Boolean procheCouleur(int R1, int R2, int V1, int V2, int B1, int B2) {
		int ecartR = Math.abs(R1-R2);
		int ecartV = Math.abs(V1-V2);
		int ecartB = Math.abs(B1-B2);
		int sommeEcrat = ecartR+ecartV+ecartB;
		if(sommeEcrat < 135) return true;
		return false;
	}
	
	/**
	 * Traitement des conditions OU avec nettoyage du texte
	 * Comparaison egale, contient , similitude
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementOU(String A, String B) {
		if(A==null) A="none";
		if(A.isEmpty()) A="none";
		
		
		A = NetTexte(A);
		
		
		
		String TextB[] = B.split("↕");
		for(int i=0;i<TextB.length;i++) {
			TextB[i] = NetTexte(TextB[i]);
			if(A.equals(TextB[i])) return "Correct : ";
			if(A.contains(TextB[i])) return "Correct : ";
			if(similirudeString(A, TextB[i])) return "Correct : ";
		}
		
		return "Erreur : ";
	}
	
	
	
	
	
	
	/**
	 * Traitement Exact
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementOUExact(String A, String B) {
		if(A==null && !B.contains("null")) return "Erreur : ";
		if(A==null && B.contains("null")) return "Correct : ";
		
		String TextB[] = B.split("¦");
		for(int i=0;i<TextB.length;i++) {
			if(!TextB[i].contains("→")) {
				if(A.equals(TextB[i])) return "Correct : ";
			}else {
				if (TraitementIntervalle(A,TextB[i]).equals("Correct : ")) return "Correct : ";
			}
		}
		return "Erreur : ";
	}
	
	
	/**
	 * Traitement des condition OU en supprimant les chiffres
	 * @param A
	 * @param B
	 * @return
	 */
	private static String TraitementOUSupprimeChiffreEtsansCase(String A, String B) {
		if(A==null) A="none";
		if(A.isEmpty()) A="none";
		
		// rechercher un digite une ou plusieur fois en fin.
		Pattern p = Pattern.compile("[0-9]");
		// création du moteur associé à la regex sur la chaîne A
		Matcher m = p.matcher(A);
		// remplacement de toutes les occurrences par ""
		A= m.replaceAll("");
		A=A.toLowerCase();
		
		m = p.matcher(B);
		B = m.replaceAll("");
		B= B.toLowerCase();
		
		
		A = NetTexte(A);
		
		
		String TextB[] = B.split("↑");
		for(int i=0;i<TextB.length;i++) {
			TextB[i] = NetTexte(TextB[i]);
			if(A.equals(TextB[i].toLowerCase())) return "Correct : ";
		}
		
		return "Erreur : ";
	}
	
	
	
	/**
	 * Sans les point
	 * @param B
	 * @return
	 */
	public static String withoutPoint(String B) {
		if(B!=null) if(B.contains("‽")) {
			B=B.substring(0, B.indexOf("‽"));
		}
		return B;
	}
	

	public static String withoutCode(String B) {
		B=B.replace("¢", "");
		return B;
	}
	
	public static String withoutCodeAndPoint(String B) {
		if(B!=null) {
			if(B.contains("‽")) B=B.substring(0, B.indexOf("‽"));
			if(!B.isEmpty()) B=B.replace("¢", "");
		}
		return B;
	}
	
	
	/**
	 * Supprime les caracatères spéciaux etc...
	 * @param A
	 * @return
	 */
	private static String NetTexte(String A) {
	if (A!=null) {
		A=A.toLowerCase().trim();
        A = A.replace("&apos;", "");
        A = A.replace("&quot;", "");
        
        Pattern pt = Pattern.compile("[^a-zA-Z0-9]"); // avec les chiffres "[^a-zA-Z0-9]"
        Matcher match= pt.matcher(A);
        while(match.find()){
            String s= match.group();
            A=A.replaceAll("\\"+s, "");
        }
        
        A=A.replaceAll("[0-9]", "");
        A=A.replace(" ", "");
	}
	return A;
	}
	
	
	public static String NetChiffreALaFin(String A) {
		A=A.replaceAll("{1,}[0-9]", "");
	    return A;
	}
	
	
//	/**
//	 * Nettoyage des digites en fin de ligne juste avant le symbol $
//	 * Permet de supprimer les numéros de page dans les index
//	 * Puis nettoyage de la chaîne avec la fonction NetText
//	 * @param A
//	 * @return
//	 */
//	private static String Net2Texte(String A) {
//		// rechercher un digite une ou plusieur fois en fin.
//		Pattern p = Pattern.compile("[0-9]+$");
//		// création du moteur associé à la regex sur la chaîne A
//		Matcher m = p.matcher(A);
//		// remplacement de toutes les occurrences par ""
//		A= m.replaceAll("");
//		
//		A = NetTexte(A);
//		return A;
//	}
	
	
	/**
	 * Retourne la chaîne sans le numéro à la fin
	 * @param A
	 * @return
	 */
	public static String RemoveLastNumber(String A) {
		Pattern p = Pattern.compile("[0-9]+$");
		Matcher m = p.matcher(A);
		A= m.replaceAll("");
		return A;	
	}
	
	
	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static ArrayList<node> AjouteDansALesNodesB(ArrayList<node> A,ArrayList<node> B){
		for(int i=0; i<B.size();i++) {
			A.add(B.get(i));
		}
		return A;
	}
	
	/**
	 * Initialisation des points
	 */
	public static void initiliseLesPoints() {
		pointsClass=0;
		pointEnJeu=0;
		pointTotal=0;
				
	}

//	/**
//	 * La proportion ne doit pas être linéaire sinon c'est trop facile
//	 * @return
//	 */
//	public double getPropClass() {
//		double a = (double) this.pointsClass;
//		double b = (double) this.pointTotal;
//		
//		if(b!=0) {
//			return (double) Math.pow(a/b, 3);
//		}
//		 return 0;
//	}
	
	/**
	 * Similitudes des chaînes de caractères
	 * Tolérance un caractère de différence
	 * @param A
	 * @param Modele
	 * @return
	 */
	private static boolean similirudeString(String A, String Modele) {
		byte[] N1 = Modele.getBytes();
		byte[] N2 = A.getBytes();
		int Compteur = 0;
		int index2 = 0;
		for(int i = 0 ; i < N1.length;i++) {
			if(index2<N2.length) {
				if(N1[i]==N2[index2]) {
						Compteur++;
				}else {
					if(index2+1<N2.length) {
						if(N1[i]==N2[index2+1]) {
							Compteur++;
							index2++;
						}
					}
				}
			}
			index2++;
		}
		if(Compteur==N1.length) return true;
		return false;
		
	}
	
	
	static String NetTexte2(String A) {
		if (A!=null) {
			A=A.toLowerCase().trim();
			
			ArrayList<Integer> table1 = new ArrayList<Integer>();
			byte[] V = A.getBytes();
		
			byte[] W = new byte[V.length];
			int c =0;
			for(int i = 0 ; i < V.length;i++) {
				if(V[i]>0) {
					//if (V[i]!=63 && V[i]!=-128 && V[i]!=-103) {  //63 les accents é ; -128 le symbole € -30,-128,-103 l'apostrophe
					W[c]=V[i];
					table1.add((int) W[c]);
					c++;
				}
			}
			try {
				A = new String(W,"UTF-8");

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			A=A.replace("@", "");
			A=A.replace("?", "");
			A=A.replace("!", "");
			A=A.replace("α", "");
			A=A.replace("β", "");
			A=A.replace("γ", "");
			A=A.replace("δ", "");
			A=A.replace("ε", "");
			A=A.replace("σ", "");
			A=A.replace("τ", "");
			A=A.replace("ζ", "");
			A=A.replace("η", "");
			A=A.replace("θ", "");
			A=A.replace("ι", "");
			A=A.replace("’", "");
			A=A.replace("'", "");
					
			A=A.replace(" ", "");
			A=A.replace("'", ""); 
			A=A.replace("«", "");
			A=A.replace("»", "");
			A=A.replace("&apos;", "");
			A=A.replace("d&apos;", "");
			
			
			A=A.replace("’", "");
			A=A.replace("e", "");
			A=A.replace("é", "");
			A=A.replace("é", ""); // pas le même encodage
			
			A=A.replace("è", "");
			A=A.replace("è", ""); // pas le même encodage
			A=A.replace("ê", "");
			
			
			A=A.replace("o", "");
			A=A.replace("ô", "");
			
			A=A.replace("a", "");
			A=A.replace("â", "");
			A=A.replace("â", ""); // pas le même encodage
			
			A=A.replace("à", "");
			A=A.replace("à", ""); // pas le même encodage
			
			A=A.replace("u", "");
			A=A.replace("ù", "");
			A=A.replace("û", "");
			
			A=A.replace("c", "");
			A=A.replace("ç", "");
			
			A=A.replace("i", "");
			A=A.replace("î", "");
			A=A.replace("ï", "");
			A=A.replace("ï", ""); // pas le même encodage
			
			A=A.replace("0", "");
			A=A.replace("1", "");
			A=A.replace("2", "");
			A=A.replace("3", "");
			A=A.replace("4", "");
			A=A.replace("5", "");
			A=A.replace("6", "");
			A=A.replace("7", "");
			A=A.replace("8", "");
			A=A.replace("9", "");
			
			
			A=A.replace(":", "");
			A=A.replace("-", "");
			A=A.replace(".", "");
			A=A.replace(",", "");
			A=A.replace("_", "");
			A=A.replace("_", "");
			A=A.replace("(", "");
			A=A.replace(")", "");
			A=A.replace("\"", "");
			A=A.replace("+", "");
			A=A.replace("Œ", "");
			A=A.replace("œ", "");
			A=A.replace("oe", "");
			A=A.replace("\r", "");
			A=A.replace("\n", "");
			A=A.replace("\t", "");
			A=A.replace("/", "");
			A=A.replace("(", "");
			A=A.replace(")", "");
			A=A.replace(" ","");
			
			
			
			A=A.trim();

		}
		return A;
	}

	
	
	

	public static int getPointsClass() {
		return pointsClass;
	}


	public static int getPointTotal() {
		return pointTotal;
	}


	public static int getPointEnJeu() {
		return pointEnJeu;
	}

	public static double getProportionCorrect() {
		return (double) pointsClass/pointTotal;
	}

	public static void setPointsClass(int ptsClass) {
		pointsClass = ptsClass;
	}


	public static void setPointTotal(int ptTotal) {
		pointTotal = ptTotal;
	}


	public static void setPointEnJeu(int ptEnJeu) {
		pointEnJeu = ptEnJeu;
	}
	
	private static void IncrementPointTotal(Integer pt) {
		pointTotal=pointTotal+ pt;
	}
	
	public static void decrementPointEnJeuDuTotal() {
		pointTotal=pointTotal-pointEnJeu;
	}
	
	private static void IncrementPointClass(Integer pt) {
		pointsClass=pointsClass+ pt;
	}
	
	
	
	
}

