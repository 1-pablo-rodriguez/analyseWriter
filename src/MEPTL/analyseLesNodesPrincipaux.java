package MEPTL;

import java.util.ArrayList;
import java.util.Enumeration;

import cXML.Run;
import cXML.node;

public class analyseLesNodesPrincipaux {

	/**
	 * Analyse du node <b>bibliographies</b>.
	 * @param nodStudentBiblio
	 * @param nodSujetBiblio
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLaBiblio(node nodStudentBiblio, node nodSujetBiblio, Run a, node nodmenu) {
		node nodbiblio = new node();
		nodbiblio.setNomElt("bibliographies");
		nodbiblio.setAttributs(nodSujetBiblio.getAttributs());//ajoute tous les attributs du sujet
		nodbiblio.setContenu(nodSujetBiblio.getContenu());//ajoute le commentaire du sujet
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "biblio")!=null) {
			nodbiblio.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "biblio").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();

		//********************************************************
		//** Parcours les nodes enfants du node <structurepage> **
		//********************************************************
		for(int i = 0 ; i < nodSujetBiblio.getNodes().size(); i++) {
			if(nodSujetBiblio.getNodes().get(i).getNomElt().equals("text:bibliography")) {
				node nodSujetBibio = nodSujetBiblio.getNodes().get(i);
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDeLaBiblio = outils.withoutCodeAndPoint(nodSujetBibio.getAttributs().get("text:name"));
				node biblio = new node();
				biblio.setNomElt("biblio");
				biblio.getAttributs().put("namebiblio", nomDeLaBiblio);
				
				if(nodSujetBibio.getAttributs().get("titre")!=null) {
					biblio.getAttributs().put("titre", nodSujetBibio.getAttributs().get("titre"));
				}
						
				String TitreTable = outils.withoutCodeAndPoint(nodSujetBibio.retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));

				node nodSujet = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodSujetBiblio, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodStudentBiblio, "text:index-body"), TitreTable);
								
				biblio = meptl.analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, biblio, "ana:biblio", nodSujet.getNomElt());
				
				for(int j =0 ; j < nodSujet.getNodes().size();j++) {
					if(nodStudent!=null) {
						if(j<nodStudent.getNodes().size()) {
							biblio = meptl.analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j), nodSujet.getNodes().get(j), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNomElt());
							for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
								if(k<nodStudent.getNodes().get(j).getNodes().size()) {
									biblio = meptl.analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j).getNodes().get(k), nodSujet.getNodes().get(j).getNodes().get(k), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}else {
									biblio = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}
							}
						}else {
							biblio = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNomElt());
						}
					}else {
						biblio = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNomElt());
						for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
							biblio = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), biblio, "ana:biblio", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
						}
					}
				}
				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				biblio.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				biblio.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodbiblio.getNodes().add(biblio);
			}
		}
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
		nodbiblio.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodbiblio.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodbiblio.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodbiblio.setClose(true);		
		return nodbiblio;
	}
	
	/**
	 * Analyse du node <b>numerotationchapitre</b>.</br>
	 * @param nodStudentNumerotation
	 * @param nodSujetNumerotation
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLaNumerotationChapitre(node nodStudentNumerotation, node nodSujetNumerotation, Run a, node nodmenu) {
		node nodnumerotations = new node();
		nodnumerotations.setNomElt("numerotationchapitre");
		nodnumerotations.setAttributs(nodSujetNumerotation.getAttributs());//ajoute tous les attributs du sujet
		nodnumerotations.setContenu(nodSujetNumerotation.getContenu()); //ajoute le commantaire du sujet
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "numerotationchapitre")!=null) {
			nodnumerotations.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "numerotationchapitre").getAttributs().get("id"));
		}
						
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//*******************************************************************
		//** Parcours les nodes enfants du node <text:outline-level-style> **
		//*******************************************************************
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
				
				//********************************************
				//** analyse les attributs des nodes <page> **
				//********************************************
				numerotation = meptl.analyseLesAttributEtContenuDuNode(numerotationStudent, numerotationSujet, numerotation, "ana:numerotation",numerotationSujet.getNomElt());
				
				//************************************
				//** analyse tous les nodes enfants **
				//************************************
				numerotation = analyseLesNodesEnfants.nodeNext(numerotation, "ana:numerotation", numerotationStudent, null, null, numerotationSujet, null, null, a);
				
				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				numerotation.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				numerotation.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodnumerotations.getNodes().add(numerotation);

			}
		}
		
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
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
	public static node analyseLesFrames(node nodStudentFrames, node nodSujetFrames, Run a, node nodmenu) {
		node nodframes = new node();
		nodframes.setNomElt("frames");
		nodframes.setAttributs(nodSujetFrames.getAttributs()); //ajoute tous les attributs du sujet
		nodframes.setContenu(nodSujetFrames.getContenu()); //ajoute le commentaire du sujet
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "frames")!=null) {
			nodframes.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "frames").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//*****************************************************
		//** Parcours les nodes enfants du node <draw:frame> **
		//*****************************************************
		for(int i = 0 ; i < nodSujetFrames.getNodes().size(); i++) {
			if(nodSujetFrames.getNodes().get(i).getNomElt().equals("draw:frame")) {
				node frameSujet = nodSujetFrames.getNodes().get(i);
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDuFrame = outils.withoutCodeAndPoint(frameSujet.getAttributs().get("draw:name"));
				node frame = new node();
				frame.setNomElt("frame");
				frame.getAttributs().put("nameframe", nomDuFrame);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				frame = meptl.addNodeSautTitre(frameSujet,frame);
				
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node frameStudent = a.retourneFirstNodeByNameAttributValue(nodStudentFrames, "draw:frame", "draw:name", nomDuFrame);
				
				//********************************************
				//** analyse les attributs des nodes <page> **
				//********************************************
				frame = meptl.analyseLesAttributEtContenuDuNode(frameStudent, frameSujet, frame, "ana:frame",frameSujet.getNomElt());

				//************************************
				//** analyse tous les nodes enfants **
				//************************************
				frame = analyseLesNodesEnfants.nodeNext(frame, "ana:frame", frameStudent, null, null, frameSujet, null, null, a);
				
				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				frame.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				frame.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodframes.getNodes().add(frame);
			}
		}
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************		
		nodframes.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodframes.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodframes.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodframes.setClose(true);		
		return nodframes;
	}
	
	/**
	 * Analyse du node <b>office:meta</b>.
	 * @param nodStudentMeta
	 * @param nodSujetMeta
	 * @param a
	 * @param nodmenu
	 * @return
	 */
 	public static node analyseLesMeta(node nodStudentMeta, node nodSujetMeta, Run a, node nodmenu) {
		node nodmeta = new node();
		nodmeta.setNomElt("meta");
		nodmeta.setAttributs(nodSujetMeta.getAttributs());
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "office:meta")!=null) {
			nodmeta.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "office:meta").getAttributs().get("id"));	
		}
		
		//Applatir le node sujetMeta
		ArrayList<node> sujet = a.Applatir(nodSujetMeta.getNodes(), new ArrayList<node>());
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//***********************************************************************
		//** Parcours les nodes enfants du node <meta:user-defined>  ou autres **
		//***********************************************************************
		for(int i = 0 ; i < sujet.size(); i++) {
			node nodSujet = sujet.get(i);
			String namenode = nodSujet.getNomElt();
			
			//*********************
			//** Ajoute un titre **
			//*********************
			nodmeta = meptl.addNodeSautTitre(nodSujet,nodmeta);
			
			
			// parcours les attributs du node "meta:user-defined"
			if(namenode.equals("meta:user-defined")) {
				Enumeration<String> key = nodSujet.getAttributs().keys();
				while(key.hasMoreElements()) {
					String k = key.nextElement();
					if(nodSujet.getAttributs().get(k).contains("‽")){
						String valueOfAttribut = nodSujet.getAttributs().get(k);
						//*******************************
						//** Recherche le node Student **
						//*******************************
						node nodStudent = a.retourneFirstNodeByNameAttributContainsValueNetTexte(nodStudentMeta, namenode,k,outils.withoutCodeAndPointPourRechercheContenuExact(valueOfAttribut));
						nodmeta = meptl.analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, nodmeta, "ana:meta", namenode);
					}
				}
			}else {
				//*******************************
				//** Recherche le node Student **
				//*******************************
				ArrayList<node> NStudent = a.retourneNames(nodStudentMeta, namenode);
			
				if(NStudent!=null) {
					if(!NStudent.isEmpty()) {
						nodmeta = meptl.analyseLesAttributEtContenuDuNode(NStudent.get(0), sujet.get(i), nodmeta, "ana:meta", namenode);
					}else {
						nodmeta = meptl.analyseLesAttributEtContenuDuNode(null, sujet.get(i), nodmeta, "ana:meta", namenode);
					}
				}else {
					nodmeta = meptl.analyseLesAttributEtContenuDuNode(null, sujet.get(i), nodmeta, "ana:meta", namenode);
				}
				
			}
			

		}
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
		nodmeta.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodmeta.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodmeta.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodmeta.setClose(true);
		return nodmeta;
	}
	
	/**
	 * Analyse du node <b>sections</b>
	 * @param nodStudentSections
	 * @param nodSujetSections
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLesSections(node nodStudentSections, node nodSujetSections, Run a, node nodmenu) {
		node nodsections = new node();
		nodsections.setNomElt("sections");
		nodsections.setAttributs(nodSujetSections.getAttributs()); //ajoute tous les attributs du sujet
		nodsections.setContenu(nodSujetSections.getContenu()); //ajoute le commentaire du sujet
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "sections")!=null) {
			nodsections.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "sections").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//*******************************************************
		//** Parcours les nodes enfants du node <text:section> **
		//*******************************************************
		for(int i = 0 ; i < nodSujetSections.getNodes().size(); i++) { //niveau 1
			if(nodSujetSections.getNodes().get(i).getNomElt().equals("text:section")) {
				node sectionSujet = nodSujetSections.getNodes().get(i);
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDeLaSection = outils.withoutCodeAndPoint(sectionSujet.getAttributs().get("text:name"));
				
				node section = new node();
				section.setNomElt("section");
				section.getAttributs().put("namesection", nomDeLaSection);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				section = meptl.addNodeSautTitre(sectionSujet,section);
				
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node sectionStudent = a.retourneFirstNodeByNameAttributValue(nodStudentSections, "text:section", "text:name", nomDeLaSection);
				
				//***********************************************
				//** analyse les attributs des nodes <section> **
				//***********************************************
				section = meptl.analyseLesAttributEtContenuDuNode(sectionStudent, sectionSujet, section, "ana:section",sectionSujet.getNomElt());
	
				//************************************
				//** analyse tous les nodes enfants **
				//************************************
				section = analyseLesNodesEnfants.nodeNext(section, "ana:section", sectionStudent, null, null, sectionSujet, null, null, a);
				
				
//				// les enfants du premier niveau du node
//				for(int j = 0 ; j < sectionSujet.getNodes().size();j++ ) { //niveau 2
//						
//				node nodSujet = sectionSujet.getNodes().get(j);
//				String nameNode = nodSujet.getNomElt();
//				node nodStudent = null;	
//				
//				if(sectionStudent!=null) nodStudent = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode,nodSujet,null,null,sectionStudent,a);
//			
//				//insère un saut si titre pas vide et saut=true
//				section=addNodeSautTitre(nodSujet, section);
//				
//				
//				// analyse attribut et contenu des enfants du premier niveau
//				section = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, section, "ana:section",nameNode);
//				
//					
//					for(int k = 0 ; k < nodSujet.getNodes().size();k++) { //niveau 3
//						node nod2Sujet = nodSujet.getNodes().get(k);
//						String nameNode2 = nod2Sujet.getNomElt();
//						node nod2Student = null;	
//						
//						if(sectionStudent!=null) nod2Student = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode2,nod2Sujet,null,sectionStudent,nodStudent,a);
//						
//						//insère un saut si titre pas vide et saut=true
//						section=addNodeSautTitre(nod2Sujet, section);
//						
//						// analyse attribut et contenu des enfants du second niveau
//						section = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, section, "ana:section",nameNode2 );
//						
//						
//						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) { //niveau 4
//							node nod3Sujet = nod2Sujet.getNodes().get(l);
//							String nameNode3 = nod3Sujet.getNomElt();
//							node nod3Student = null;	
//							
//							if(sectionStudent!=null) nod3Student = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode3,nod3Sujet,sectionStudent,nodStudent,nod2Student,a);
//							
//							//insère un saut si titre pas vide et saut=true
//							section=addNodeSautTitre(nod3Sujet, section);
//							
//							// analyse attribut et contenu des enfants du second niveau
//							section = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, section, "ana:section", nameNode3);
//						
//						
//							for(int m = 0 ; m < nod3Sujet.getNodes().size();m++) { //niveau 5
//								node nod4Sujet = nod3Sujet.getNodes().get(m);
//								String nameNode4 = nod4Sujet.getNomElt();
//								node nod4Student = null;	
//								
//								if(sectionStudent!=null) nod4Student = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode4,nod4Sujet,nodStudent,nod2Student,nod3Student,a);
//								
//								//insère un saut si titre pas vide et saut=true
//								section=addNodeSautTitre(nod4Sujet, section);
//								
//								// analyse attribut et contenu des enfants du second niveau
//								section = analyseLesAttributEtContenuDuNode(nod4Student, nod4Sujet, section, "ana:section", nameNode4);
//							
//							}
//						
//						
//						}
//	
//					}
//						
//				}
				
				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				section.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				section.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodsections.getNodes().add(section);

			}
		}
		
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
		nodsections.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodsections.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodsections.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodsections.setClose(true);		
		return nodsections;
		
	}
	
	/**
	 * Analyse du node <b>sequences</b>.
	 * @param nodStudentSequence
	 * @param nodSujetSequence
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLesSequences(node nodStudentSequence, node nodSujetSequence, Run a, node nodmenu) {
		node nodseq = new node();
		nodseq.setNomElt("sequences");
		nodseq.setAttributs(nodSujetSequence.getAttributs());
		nodseq.setContenu(nodSujetSequence.getContenu()); //ajoute le commantire
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "sequences")!=null) {
			nodseq.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "sequences").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//*************************************************************
		//** Parcours les nodes enfants du node <text:sequence-decl> **
		//*************************************************************
		for(int i = 0 ; i < nodSujetSequence.getNodes().size(); i++) {
			if(nodSujetSequence.getNodes().get(i).getNomElt().equals("text:sequence-decl")) {
				node seqSujet = nodSujetSequence.getNodes().get(i);
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomSequence = outils.withoutCodeAndPoint(seqSujet.getAttributs().get("text:name"));
				node seq = new node();
				seq.setNomElt("sequence");
				seq.getAttributs().put("name", nomSequence);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				seq = meptl.addNodeSautTitre(seqSujet,seq);
				
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node seqStudent = a.retourneFirstNodeByNameAttributValue(nodStudentSequence, "text:sequence-decl", "text:name", nomSequence);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				seq = meptl.addNodeSautTitre(seqSujet, seq);
				
				//***********************************************
				//** analyse les attributs des nodes <section> **
				//***********************************************
				seq = meptl.analyseLesAttributEtContenuDuNode(seqStudent, seqSujet, seq, "ana:seq",seqSujet.getNomElt());

				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				seq.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				seq.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodseq.getNodes().add(seq);
			}
		}
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
		nodseq.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodseq.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodseq.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodseq.setClose(true);		
		return nodseq;
	}
	

	/**
	 * Analyse du node <b>tableaux</b>
	 * @param nodStudentSections
	 * @param nodSujetSections
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLesTableaux(node nodStudentTableaux, node nodSujetTableaux, Run a, node nodmenu) {
		node nodtableaux = new node();
		nodtableaux.setNomElt("tableaux");
		nodtableaux.setAttributs(nodSujetTableaux.getAttributs()); //ajoute tous les attributs du sujet
		nodtableaux.setContenu(nodSujetTableaux.getContenu()); //ajoute le commentaire du sujet
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "tableau")!=null) {
			nodtableaux.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "tableau").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//******************************************************
		//** Parcours les nodes enfants du node <table:table> **
		//******************************************************
		for(int i = 0 ; i < nodSujetTableaux.getNodes().size(); i++) { //niveau 1
			if(nodSujetTableaux.getNodes().get(i).getNomElt().equals("table:table")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				node tableSujet = nodSujetTableaux.getNodes().get(i);
				String nomDeLaTable = outils.withoutCodeAndPoint(tableSujet.getAttributs().get("table:name"));
				
				node tableau = new node();
				tableau.setNomElt("tableau");
				tableau.getAttributs().put("nametableau", nomDeLaTable);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				tableau = meptl.addNodeSautTitre(tableSujet,tableau);
				
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node tableauStudent = a.retourneFirstNodeByNameAttributValue(nodStudentTableaux, "table:table", "table:name", nomDeLaTable);
				
				//***************************************************
				//** analyse les attributs des nodes <table:table> **
				//***************************************************
				tableau = meptl.analyseLesAttributEtContenuDuNode(tableauStudent, tableSujet, tableau, "ana:tableau",tableSujet.getNomElt());
	
				//************************************
				//** analyse tous les nodes enfants **
				//************************************
				tableau = analyseLesNodesEnfants.nodeNext(tableau, "ana:tableau", tableauStudent, null, null, tableSujet, null, null, a);
				
//				// les enfants du premier niveau du node
//				for(int j = 0 ; j < tableSujet.getNodes().size();j++ ) { //niveau 2
//						
//				node nodSujet = tableSujet.getNodes().get(j);
//				String nameNode = nodSujet.getNomElt();
//				node nodStudent = null;	
//				
//				if(tableauStudent!=null) nodStudent = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode,nodSujet,null,null,tableauStudent,a);
//			
//				//insère un saut si titre pas vide et saut=true
//				tableau=addNodeSautTitre(nodSujet, tableau);
//				
//				
//				// analyse attribut et contenu des enfants du premier niveau
//				tableau = analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, tableau, "ana:tableau",nameNode);
//				
//					
//					for(int k = 0 ; k < nodSujet.getNodes().size();k++) { //niveau 3
//						node nod2Sujet = nodSujet.getNodes().get(k);
//						String nameNode2 = nod2Sujet.getNomElt();
//						node nod2Student = null;	
//						
//						if(tableauStudent!=null) nod2Student = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode2,nod2Sujet,null,tableauStudent,nodStudent,a);
//						
//						//insère un saut si titre pas vide et saut=true
//						tableau=addNodeSautTitre(nod2Sujet, tableau);
//						
//						// analyse attribut et contenu des enfants du second niveau
//						tableau = analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, tableau, "ana:tableau",nameNode2 );
//						
//						
//						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) { //niveau 4
//							node nod3Sujet = nod2Sujet.getNodes().get(l);
//							String nameNode3 = nod3Sujet.getNomElt();
//							node nod3Student = null;	
//							
//							if(tableauStudent!=null) nod3Student = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode3,nod3Sujet,tableauStudent,nodStudent,nod2Student,a);
//							
//							//insère un saut si titre pas vide et saut=true
//							tableau=addNodeSautTitre(nod3Sujet, tableau);
//							
//							// analyse attribut et contenu des enfants du second niveau
//							tableau = analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, tableau, "ana:tableau", nameNode3);
//						
//						
//							for(int m = 0 ; m < nod3Sujet.getNodes().size();m++) { //niveau 5
//								node nod4Sujet = nod3Sujet.getNodes().get(m);
//								String nameNode4 = nod4Sujet.getNomElt();
//								node nod4Student = null;	
//								
//								if(tableauStudent!=null) nod4Student = rechercherUnNodeStudent.rechercheLeNodeEnCascade(nameNode4,nod4Sujet,nodStudent,nod2Student,nod3Student,a);
//								
//								//insère un saut si titre pas vide et saut=true
//								tableau=addNodeSautTitre(nod4Sujet, tableau);
//								
//								// analyse attribut et contenu des enfants du second niveau
//								tableau = analyseLesAttributEtContenuDuNode(nod4Student, nod4Sujet, tableau, "ana:tableau", nameNode4);
//							}
//						}
//					}
//				}
				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				tableau.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				tableau.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodtableaux.getNodes().add(tableau);
			}
		}
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
		nodtableaux.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodtableaux.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodtableaux.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodtableaux.setClose(true);		
		return nodtableaux;
		
	}
	
	/**
	 * Analyse du node <b>tableillustrations</b>.
	 * @param nodStudentTableI
	 * @param nodSujetTableI
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLesTablesIllustrations(node nodStudentTableI, node nodSujetTableI, Run a, node nodmenu) {
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
				
				//*********************
				//** Ajoute un titre **
				//*********************
				if(nodSujetTableI.getNodes().get(i).getAttributs().get("titre")!=null) {
					String titre = nodSujetTableI.getNodes().get(i).getAttributs().get("titre");
					if(!titre.isEmpty()) table.getAttributs().put("titre", titre);
				}
				
				
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String TitreTable = outils.withoutCodeAndPoint(nodSujetTableI.getNodes().get(i).retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));
				
				node nodSujet = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodSujetTableI, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodStudentTableI, "text:index-body"), TitreTable);
		
				table = meptl.analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, table, "ana:tableillustration", nodSujet.getNomElt());
				
				for(int j =0 ; j < nodSujet.getNodes().size();j++) {
					if(nodStudent!=null) {
						if(j<nodStudent.getNodes().size()) {
							table = meptl.analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j), nodSujet.getNodes().get(j), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNomElt());
							for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
								if(k<nodStudent.getNodes().get(j).getNodes().size()) {
									table = meptl.analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j).getNodes().get(k), nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}else {
									table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}
							}
						}else {
							table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNomElt());
						}
					}else {
						table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNomElt());
						for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
							table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tableillustration", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
						}
					}
				}
				
				
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
	 * Analyse du node <b>tablematieres</b>.
	 * @param nodStudentTableM
	 * @param nodSujetTableM
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analyseLesTablesMatieres(node nodStudentTableM, node nodSujetTableM, Run a, node nodmenu) {
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
				
				//*********************
				//** Ajoute un titre **
				//*********************
				if(nodSujetTableM.getNodes().get(i).getAttributs().get("titre")!=null) {
					String titre = nodSujetTableM.getNodes().get(i).getAttributs().get("titre");
					if(!titre.isEmpty()) table.getAttributs().put("titre", titre);
				}
				
				
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String TitreTable = outils.withoutCodeAndPoint(nodSujetTableM.getNodes().get(i).retourneFirstEnfantsByName("text:index-title").retourneLesContenusEnfants(""));
				
				node nodSujet = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodSujetTableM, "text:index-body"), TitreTable);
				node nodStudent = a.retourneFirstNodeParagrapheContain(a.retourneNames(nodStudentTableM, "text:index-body"), TitreTable);

				table = meptl.analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, table, "ana:tablematiere", nodSujet.getNomElt());
				
				for(int j =0 ; j < nodSujet.getNodes().size();j++) {
					if(nodStudent!=null) {
						if(j<nodStudent.getNodes().size()) {
							table = meptl.analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j), nodSujet.getNodes().get(j), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNomElt());
							for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
								if(k<nodStudent.getNodes().get(j).getNodes().size()) {
									table = meptl.analyseLesAttributEtContenuDuNode(nodStudent.getNodes().get(j).getNodes().get(k), nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}else {
									table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
								}
							}
						}else {
							table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNomElt());
						}
					}else {
						table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNomElt());
						for(int k=0; k<nodSujet.getNodes().get(j).getNodes().size();k++) {
							table = meptl.analyseLesAttributEtContenuDuNode(null, nodSujet.getNodes().get(j).getNodes().get(k), table, "ana:tablematiere", nodSujet.getNodes().get(j).getNodes().get(k).getNomElt());
						}
					}
				}
				
				
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
	 * Analyse du node <b>style:page</b>.
	 * @param nodStudentPage
	 * @param nodSujetPage
	 * @param a
	 * @param nodmenu
	 * @return
	 */
	public static node analysePage(node nodStudentPage, node nodSujetPage, Run a, node nodmenu, node nodSujetParagraphes, node nodStudentParagraphes) {
		node nodpages = new node();
		nodpages.setNomElt("pages");
		nodpages.setAttributs(nodSujetPage.getAttributs());
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:page")!=null) {
			nodpages.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:page").getAttributs().get("id"));
		}

		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//****************************************
		//** Analyse tous les style:master-page **
		//****************************************
		for(int i = 0 ; i < nodSujetPage.getNodes().size(); i++) {
			
			if(nodSujetPage.getNodes().get(i).getNomElt().equals("style:master-page")) {
				node pageSujet = nodSujetPage.getNodes().get(i);
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				String nomDeLaPage = outils.withoutCodeAndPoint(pageSujet.getAttributs().get("style:name"));
				node page = new node();
				page.setNomElt("page");
				page.getAttributs().put("name", nomDeLaPage);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				page = meptl.addNodeSautTitre(pageSujet,page);
				
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node pageStudent = a.retourneFirstNodeByNameAttributValue(nodStudentPage, "style:master-page", "style:name", nomDeLaPage);
				
				//********************************************
				//** analyse les attributs des nodes <page> **
				//********************************************
				page = meptl.analyseLesAttributEtContenuDuNode(pageStudent, pageSujet, page, "ana:page",pageSujet.getNomElt());
	
				//**********************************************
				//** Analyse de tous les autres nodes enfants **
				//**********************************************
				page=analyseLesNodesEnfants.nodeNext(page, "ana:page", pageStudent, null, null, pageSujet, nodSujetParagraphes, nodStudentParagraphes, a);
				
				//*****************************************************************
				//** Insère les attributs des points dans les nodes de l'analyse **
				//*****************************************************************
				page.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				page.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodpages.getNodes().add(page);
			}
		}
		//*****************************************************************
		//** Insère les attributs des points dans les nodes de l'analyse **
		//*****************************************************************
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
	public static node analyseParagraph(node nodStudentParagraph, node nodSujetParagraph, Run a, node nodmenu) {
		node nodparagraphs = new node();
		nodparagraphs.setNomElt("paragraphs");
		nodparagraphs.setAttributs(nodSujetParagraph.getAttributs());
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:paragraph")!=null) {
			nodparagraphs.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "style:paragraph").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//******************************************************
		//** Parcours les nodes enfants du node <style:style> **
		//******************************************************
		for(int i = 0 ; i < nodSujetParagraph.getNodes().size(); i++) {
			if(nodSujetParagraph.getNodes().get(i).getNomElt().equals("style:style")) {
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				node paragraphSujet = nodSujetParagraph.getNodes().get(i);
				String nomDuParagraph = outils.withoutCodeAndPoint(paragraphSujet.getAttributs().get("style:name"));
				node paragraph = new node();
				paragraph.setNomElt("paragraph");
				paragraph.getAttributs().put("name", nomDuParagraph);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				if(paragraphSujet.getAttributs().get("titre")!=null) {
					paragraph.getAttributs().put("titre", nodSujetParagraph.getNodes().get(i).getAttributs().get("titre"));
				}
							
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node paragraphStudent = a.retourneFirstNodeByNameAttributValue(nodStudentParagraph, "style:style", "style:name", nomDuParagraph);
				
				// ajoute les valeurs par héritage
				if(paragraphStudent!=null) paragraphStudent = meptl.ajouteValeurLesValeursDuStyleParagraphParent(nodStudentParagraph, paragraphStudent);
				
				// ajoute les valeurs par défauts
				if(paragraphStudent!=null)	paragraphStudent = meptl.ajouteValeurParDefautAuStyleParagraph(nodStudentParagraph, paragraphStudent);
			
				// analyse les attributs du node
				paragraph = meptl.analyseLesAttributEtContenuDuNode(paragraphStudent, paragraphSujet, paragraph, "ana:paragraph",paragraphSujet.getNomElt());
	
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
				paragraph = meptl.addNodeSautTitre(nodSujet, paragraph);
				
				// analyse attribut et contenu des enfants du premier niveau
				paragraph = meptl.analyseLesAttributEtContenuDuNode(nodStudent, nodSujet, paragraph, "ana:paragraph",nodSujet.getNomElt());
				
					
					for(int k = 0 ; k < nodSujet.getNodes().size();k++) {
						node nod2Sujet = nodSujet.getNodes().get(k);
						String nameNode2 = nod2Sujet.getNomElt();
						node nod2Student = null;	
						if(nodStudent!=null) if(nodStudent.retourneFirstEnfantsByName(nameNode2).getNomElt().equals(nameNode2)) {
							nod2Student = paragraphStudent.retourneFirstEnfantsByName(nameNode2);
						}
						
						//insère un saut si titre pas vide et saut=true
						paragraph = meptl.addNodeSautTitre(nod2Sujet, paragraph);
						
						// analyse attribut et contenu des enfants du second niveau
						paragraph = meptl.analyseLesAttributEtContenuDuNode(nod2Student, nod2Sujet, paragraph, "ana:paragraph",nod2Sujet.getNomElt() );
						
						for(int l = 0 ; l < nod2Sujet.getNodes().size();l++) {
							node nod3Sujet = nod2Sujet.getNodes().get(l);
							String nameNode3 = nod3Sujet.getNomElt();
							node nod3Student = null;	
							if(nod2Student!=null) if(nod2Student.retourneFirstEnfantsByName(nameNode3).getNomElt().equals(nameNode3)) {
								nod3Student = paragraphStudent.retourneFirstEnfantsByName(nameNode3);
							}
							
							//insère un saut si titre pas vide et saut=true
							paragraph = meptl.addNodeSautTitre(nod3Sujet, paragraph);
							
							// analyse attribut et contenu des enfants du troisième niveau
							paragraph = meptl.analyseLesAttributEtContenuDuNode(nod3Student, nod3Sujet, paragraph, "ana:paragraph", nod3Sujet.getNomElt());
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
	 * Cette méthode permet d'analyse la structure du document.</br>
	 * La structure de l'étudiant est comparé avec la structure du sujet.</br>
	 * Le node d'analyse est retourné.</br>
	 * Le nom du node analysé pour cette partie est <page> puis <ana:page>.</br> 
	 * <br>
	 * @param nodStudentS : node de la structure de l'étudiant.
	 * @param nodSujetS : node de la structure du sujet.
	 * @param a : Objet de la class cXML
	 * @param nodmenu : node menu.
	 * @param nodSujetParagraphs : node contenant l'ensemble des styles de paragraphe du sujet.
	 * @param nodStudentParagraphs : node contenant l'ensemble des styles de paragraphe de l'étudiant.
	 * @return : le node d'analyse de la structure.
	 */
	public static node analyseStructurePage(node nodStudentS, node nodSujetS, Run a, node nodmenu, node nodSujetParagraphs, node nodStudentParagraphs) {
		node nodSpages = new node();
		nodSpages.setNomElt("structurepage");
		nodSpages.setAttributs(nodSujetS.getAttributs());
		nodSpages.setContenu(nodSujetS.getContenu()); //ajoute le commentaire du sujet
		
		//***************************************
		//** Ajoute l'identifiant pour le menu **
		//***************************************
		if(a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "structurepage")!=null) {
			nodSpages.getAttributs().put("id", a.retourneFirstNodeByNameAttributValue(nodmenu, "item", "name", "structurepage").getAttributs().get("id"));
		}
		
		//***************************
		//** initialise les points **
		//***************************
		outils.initiliseLesPoints();
		
		//********************************************************
		//** Parcours les nodes enfants du node <structurepage> **
		//********************************************************
		for(int i = 0 ; i < nodSujetS.getNodes().size(); i++) {
			if(nodSujetS.getNodes().get(i).getNomElt().equals("page")) {
				node pageSujet = nodSujetS.getNodes().get(i);
				int pointDebut = outils.getPointsClass();
				int pointTotalDebut = outils.getPointTotal();
				
				String nomDeLaPage = outils.withoutCodeAndPoint(pageSujet.getAttributs().get("style:master-page-name"));
				if(nomDeLaPage==null) nomDeLaPage="Défaut";
				String numeroabsolue = outils.withoutCodeAndPoint(pageSujet.getAttributs().get("numeroabsolue"));
				node page = new node();
				page.setNomElt("page");
				page.getAttributs().put("namepage", nomDeLaPage);
				page.getAttributs().put("numeroabsolue", numeroabsolue);
				
				//*********************
				//** Ajoute un titre **
				//*********************
				page = meptl.addNodeSautTitre(pageSujet,page);
		
				//*******************************
				//** Recherche le node Student **
				//*******************************
				node pageStudent = a.retourneFirstNodeByNameAttributValue(nodStudentS, "page", "numeroabsolue", numeroabsolue);
				
				//********************************************
				//** analyse les attributs des nodes <page> **
				//********************************************
				page = meptl.analyseLesAttributEtContenuDuNode(pageStudent, pageSujet, page, "ana:page",pageSujet.getNomElt());
				
				//************************************
				//** analyse tous les nodes enfants **
				//************************************
				page = analyseLesNodesEnfants.nodeNext(page, "ana:page", pageStudent, null, null, pageSujet, nodSujetParagraphs, nodStudentParagraphs, a);
			
				//****************************************************************
				//** Insère les attributs des points dans les node de l'analyse **
				//****************************************************************
				page.getAttributs().put("point", String.valueOf(outils.getPointsClass()-pointDebut));	
				page.getAttributs().put("pointTotal", String.valueOf(outils.getPointTotal()-pointTotalDebut));
				nodSpages.getNodes().add(page);
			}
		}
		//****************************************************************
		//** Insère les attributs des points dans les node de l'analyse **
		//****************************************************************
		nodSpages.getAttributs().put("pointgagner",String.valueOf(outils.getPointsClass()));
		nodSpages.getAttributs().put("pointtotal",String.valueOf(outils.getPointTotal()));
		nodSpages.getAttributs().put("proportioncorrect",String.valueOf(outils.getProportionCorrect()));
		nodSpages.setClose(true);		
	return nodSpages;
		
	}
	

	
}
