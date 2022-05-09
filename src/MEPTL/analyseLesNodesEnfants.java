package MEPTL;

import cXML.node;
import cXML.Run;

public class analyseLesNodesEnfants {

	/**
	 * Cette méthode récursive permet d'analyser et d'évaluer les nodes enfants.</br>
	 * 
	 * @param PourAnalyse : Le node pour écrire le node d'analyse contenant tous les résultats
	 * @param nomDuNodePourAnalyse : Le nom du node de l'analyse pour cette partie (exemple ana:page)
	 * @param nod2Student : Le node de l'étudiant le plus bas niveau (le dernier node trouvé)
	 * @param nod1Student : Le node de l'étudaint de niveau intermédiaire contenant le node nod2Student
	 * @param nodStudent : Le node de l'étudiant le plus haut niveau contenant les nodes nod1Student & nod2Student
	 * @param nodSujet : le node sujet.
	 * @param nodSujetParagraphs : node contenant l'ensemble des nodes styles de paragraphes du sujet.
	 * @param nodStudentParagraphes : node contenant l'ensemble des nodes styles de paragarphes de l'étudiants.
	 * @param a : Objet de cXML.Run
	 * @return le node pour analyse PourAnalyse
	 */
	static public node nodeNext(node PourAnalyse, String nomDuNodePourAnalyse, node nod2Student, node nod1Student, node nodStudent, node nodSujet, node nodSujetParagraphs, node nodStudentParagraphes, Run a) {
		node nodEnfantSujet = null;
		node nodStudentCorrespondantAuNodSujet = null;
		
		for(int j = 0 ; j < nodSujet.getNodes().size();j++ ) { 
			
			nodEnfantSujet = nodSujet.getNodes().get(j);
			String nameNode = nodEnfantSujet.getNomElt();
			
			
			//*****************************
			//** Ajoute un saut de ligne **
			//*****************************
			PourAnalyse = meptl.addSaut(nodEnfantSujet, PourAnalyse);
			
			//********************************
			//** Ajoute des sauts et titres **
			//********************************
			PourAnalyse = meptl.addNodeSautTitre(nodEnfantSujet, PourAnalyse);

			//***************************************************
			//** Recherche le node correspondant de l'étudiant **
			//***************************************************
			if(nod2Student!=null) {
				if(nod2Student.containElementByName(nameNode)) {
					nodStudentCorrespondantAuNodSujet = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode,nodEnfantSujet,nodStudent,nod1Student,nod2Student,a);
				}
			}
			if(nodStudentCorrespondantAuNodSujet==null && nod1Student!=null) {
				if(nod1Student.containElementByName(nameNode)) {
					nodStudentCorrespondantAuNodSujet = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode,nodEnfantSujet,nodStudent,nod1Student,nod2Student,a);
				}
			}
			if(nodStudentCorrespondantAuNodSujet==null && nodStudent!=null) {
				if(nodStudent.containElementByName(nameNode)) {
					nodStudentCorrespondantAuNodSujet = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode,nodEnfantSujet,nodStudent,nod1Student,nod2Student,a);
				}
			}
		
			//**************************************************************
			//** Analyse attribut et contenu du node enfant de l'étudiant **
			//**************************************************************
			PourAnalyse = meptl.evalLesAttributEtContenuDuNode(nodStudentCorrespondantAuNodSujet, nodEnfantSujet, PourAnalyse, nomDuNodePourAnalyse,nameNode);
		
			//*******************************
			//** méthode analyseStyle=true **
			//*******************************
			if(nameNode.contains("text:") && nodEnfantSujet.getAttributs().get("analyseStyle")!=null && nodSujetParagraphs!=null) {
				if(nodEnfantSujet.getAttributs().get("analyseStyle").equalsIgnoreCase("true")) {
					PourAnalyse = analyseStyle(PourAnalyse, nomDuNodePourAnalyse, nodEnfantSujet,nodStudentCorrespondantAuNodSujet, nodSujetParagraphs,nodStudentParagraphes);
				}
			}
			
			//************************************************************
			//** Analyse les nodes enfants du node enfant - Récursivité **
			//************************************************************
			if(nodEnfantSujet!=null) {
				PourAnalyse = nodeNext(PourAnalyse, nomDuNodePourAnalyse, nodStudentCorrespondantAuNodSujet, nod2Student, nod1Student, nodEnfantSujet, nodSujetParagraphs, nodStudentParagraphes, a);
			}
		
		}	
		
		return PourAnalyse;	
	}
	
	
	/**
	 * Permet d'analyser les styles depuis l'attribut <b>analyseStyle</b>.</br>
	 * @param PourAnalyse : node pour le node analyse
	 * @param : Le nom du node de l'analyse pour cette partie de l'analyse(exemple ana:page)
	 * @param nodSujet : le node du sujet (consignes).
	 * @param nodStudent : le node de l'étudiant.
	 * @param nodSujetParagraphs : le node contenant l'ensemble des styles du sujet.
	 * @param nodStudentParagraphs : le node contenant l'ensemble des style de l'étudiant.
	 * @return
	 */
	public static node analyseStyle(node PourAnalyse, String nomDuNodePourAnalyse, node nodSujet, node nodStudent, node nodSujetParagraphs, node nodStudentParagraphs ) {
		node StyleParagraphSujet = null;
		node StyleParagraphStudent = null;
		
		if(nodSujet.getAttributs().get("analyseStyle")!=null) {
			
			if(nodSujet.getAttributs().get("analyseStyle").equalsIgnoreCase("true") && nodSujet.getAttributs().get("text:style-name")!=null) {
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
				if(StyleParagraphStudent!=null) StyleParagraphStudent = meptl.ajouteValeurLesValeursDuStyleParagraphParent(nodStudentParagraphs , StyleParagraphStudent);
				
				//ajoute les valeurs par défaut.
				if(StyleParagraphStudent!=null) StyleParagraphStudent = meptl.ajouteValeurParDefautAuStyleParagraph(nodStudentParagraphs , StyleParagraphStudent);
				
				//*****************************
				//** Ajoute un saut de ligne **
				//*****************************
				PourAnalyse = meptl.addSaut(StyleParagraphSujet, PourAnalyse);
				
				//********************************
				//** Ajoute des sauts et titres **
				//********************************
				PourAnalyse = meptl.addNodeSautTitre(StyleParagraphSujet, PourAnalyse);

				
				//**************************************************************
				//** Analyse attribut et contenu du node enfant de l'étudiant **
				//**************************************************************
				PourAnalyse = meptl.evalLesAttributAnalyseStyle(StyleParagraphStudent, StyleParagraphSujet, PourAnalyse, nomDuNodePourAnalyse,"style:style");
			}
			
		}
		return PourAnalyse;
	}
	
	
	
	
	
	
}
