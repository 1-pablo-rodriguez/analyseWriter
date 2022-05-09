package MEPTL;


import cXML.Run;
import cXML.node;

/**
 * 
 * @author pablo rodriguez
 *
 */
public class rechercherUnNodeStudent {

	/**
	 * Recherche en cascade des nodes en fonction de leur contenu.</br>
	 * Elargie sa recherche dans les nodes parents.</br>
	 * Permet de rechercher par l'index, le contenu ou le nom des objets.</br></br>
	 * @param nameNode : nom du node
	 * @param nodSujet : nod sujet
	 * @param nod0Student : node étudiant contenant le node nod1Student. Si pas trouvé dans le node nod1Student
	 * @param nod1Student : node étudiant contenant le node nod2Student. Si pas trouvé dans le node Nod2Student.
	 * @param nod2Student : node étudiant de niveau le plus haut. La recherche commence par ce node.
	 * @param a : Run cXML
	 * @return : le node recherché
	 */
	public static node rechercheLeNodeEnCascade(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		
		node nodStudent =null;
		
		//************************************************************************************************
		//** Recherche le node par le contenu exact du node en intégrant les contenus des nodes enfants **
		//************************************************************************************************
		if(nodSujet.getAttributs().get("recherche_contenu_exact")!=null){
			nodStudent = findNodeParContenuEXACT(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
			if(nodStudent != null) return nodStudent;
		}
		
		//***********************************
		//** Recherche le node par l'index **
		//***********************************
		if(nodSujet.getAttributs().get("recherche_index")!=null){
			nodStudent = findNodeParIndex(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
			if(nodSujet.getAttributs().get("recherche_index").equalsIgnoreCase("true")) return nodStudent;
		}
		
		//*****************************************************************
		//** Recherche le node text:p par les différents contenu du node **
		//*****************************************************************
		if(nameNode.equals("text:p")||nameNode.equals("text:span")) {
			nodStudent = findLeNodeTextpParDesNodesEnfantsSpecifique(nameNode, nodSujet,nod0Student, nod1Student, nod2Student, a );
		}
		
		//********************************************************************************
		//** Recherche le node draw:frame par draw:name ou recherche_anchor-page-number **
		//********************************************************************************
		if(nameNode.equals("draw:frame")) {
			nodStudent = findDrawFrame(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//*************************************************
		//** Recherche le nodes text:date par text:fixed **
		//*************************************************
		if(nameNode.equals("text:date")) {
			nodStudent = findTextDate(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//***********************************************
		//** Recherche le nodes text:xxx par text:name **
		//***********************************************
		if(nameNode.equals("text:section") || nameNode.equals("text:user-defined") || nameNode.equals("text:table-of-content")) {
			nodStudent = findByTextName(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//******************************************************************
		//** Recherche le node text:database-display par text:column-name **
		//******************************************************************
		if(nameNode.equals("text:database-display")) {
			nodStudent = findDataBase(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//**************************************************
		//** Recherche le node table:table par table:name **
		//**************************************************
		if(nameNode.equals("table:table")) {
			nodStudent = findTable(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//*************************************************************************
		//** Recherche le node text:h par le contenu avec tolérance de carcatère **
		//*************************************************************************
		if(nameNode.equals("text:h")) {
			nodStudent = findByContentWithTolerance(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		

		//*******************************************************
		//** Recherche le node par l'index mais si pas demandé **
		//*******************************************************
		if(nameNode.equals("table:table-row") || nameNode.equals("table:table-cell") || nameNode.equals("text:tab") || nameNode.equals("text:span")) {
			nodStudent = findByIndexEvenIsFalse(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//****************************************************************
		//** Recherche le node text:conditional-text par text:condition **
		//****************************************************************
		if(nameNode.equals("text:conditional-text") ) {
			nodStudent = findTextConditional(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		//***************************************************************
		//** Recherche le node table:table-column par table:style-name **
		//***************************************************************
		if(nameNode.equals("table:table-column")) {
			nodStudent = findTableByStyleName(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
			
		//*********************************************
		//** Recherche le node par le contenu simple **
		//*********************************************
		if(nameNode.equals("text:tab") || nameNode.equals("style:graphic-properties")) {
			nodStudent = findByContentZero(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
	
		//*****************************************************************
		//** Recherche Le node text:p par les différents contenu du node **
		//** text:title - text:subject - text:initial-creator - text:tab **
		//*****************************************************************
		if(nodStudent==null) {
			nodStudent = findByNameNode(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}

		//*********************************************
		//** Recherche le node par le contenu simple **
		//*********************************************
		if(nodStudent==null){
			nodStudent = findByContentZero(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
		}
		
		return nodStudent;
	}
	
	/**
	 * Recherche par le contenu exact du node. Englobe les contenus des nodes enfants.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findNodeParContenuEXACT(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		node nodStudent = null;
		if(nodSujet.getAttributs().get("recherche_contenu_exact").equals("true")) {
			if(!nodSujet.retourneLesContenusEnfants("").isEmpty()) {
				String valueAttribut = outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.retourneLesContenusEnfants(""));
				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod2Student.getNodes(), valueAttribut, nodSujet.getNomElt());
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod1Student.getNodes(), valueAttribut, nodSujet.getNomElt());
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod0Student.getNodes(), valueAttribut, nodSujet.getNomElt());
			}
		}
		return nodStudent;
	}
	
	/**
	 * Recherche par l'attribut index.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findNodeParIndex(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		node nodStudent = null;
		if(nodSujet.getAttributs().get("recherche_index").equals("true")) {
			String valueAttribut = nodSujet.getAttributs().get("index");
			if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
		}
		return nodStudent;
	}
	
	
	/**
	 * Recherche Le node text:p par les différents contenu du node.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findLeNodeTextpParDesNodesEnfantsSpecifique(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		
		node nodStudent =null;
		
		if(!nodSujet.getNodes().isEmpty()) return null;
		
		String nameNodeSujet = nodSujet.getNomElt();
		
		if(nodSujet.getNodes().size()>0) {
			for(int i = 0; i < nodSujet.getNodes().size() ; i++) {
				if(nodSujet.getNodes().get(i)!=null) {
					node nodEnfantSujet = nodSujet.getNodes().get(i);
					String nameNodeEnfantSujet = nodEnfantSujet.getNomElt();
					
					if(nameNodeEnfantSujet.equals("text:user-defined")) {
						String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:user-defined").getAttributs().get("text:name"));
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:user-defined", "text:name", valueAttribut);
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:user-defined", "text:name", valueAttribut);
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:user-defined", "text:name", valueAttribut);
					}
					
					if(nameNodeEnfantSujet.equals("text:conditional-text")) {
						String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:conditional-text").getAttributs().get("text:condition"));
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:conditional-text", "text:condition", valueAttribut);
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:conditional-text", "text:condition", valueAttribut);
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:conditional-text", "text:condition", valueAttribut);
					}
					
					if(nameNodeEnfantSujet.equals("text:database-display")) {
						String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:database-display", "text:column-name", valueAttribut);
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:database-display", "text:column-name", valueAttribut);
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:database-display", "text:column-name", valueAttribut);
					}
					
					if(nameNodeEnfantSujet.equals("text:date")) {
						String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:date").getAttributs().get("text:fixed"));
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:date", "text:fixed", valueAttribut);
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:date", "text:fixed", valueAttribut);
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue(nameNodeSujet,"text:date", "text:fixed", valueAttribut);
					}
					
					if(nameNodeEnfantSujet.equals("text:subject")) {
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:subject");
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:subject");
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:subject");
					}
					
					if(nameNodeEnfantSujet.equals("text:title")) {
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:title");
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:title");
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:title");
					}
					
					if(nameNodeEnfantSujet.equals("text:initial-creator")) {
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:initial-creator");
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:initial-creator");
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:initial-creator");
					}
					
					if(nameNodeEnfantSujet.equals("text:creator")) {
						if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:creator");
						if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:creator");
						if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2(nameNodeSujet,"text:creator");
					}
					
		
					if(nodStudent!=null) return nodStudent;
				}
			}
		}
		
//		//si le node "text:p" contient un "text:user-defined" alors le recherche par le "text:name" de ce node "text:user-defined"
//		if(nodSujet.containElementByName("text:user-defined")) {
//			String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:user-defined").getAttributs().get("text:name"));
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:user-defined", "text:name", valueAttribut);
//		}
//		//si le node "text:p" contient un "text:conditional-text" alors le recherche par le "text:condition" de ce node "text:conditional-text"
//		if(nodSujet.containElementByName("text:conditional-text")) {
//			String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:conditional-text").getAttributs().get("text:condition"));
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:conditional-text", "text:condition", valueAttribut);
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:conditional-text", "text:condition", valueAttribut);
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:conditional-text", "text:condition", valueAttribut);
//		}
//		//si le node "text:p" contient un "text:database-display" alors le recherche par le "text:column-name" de ce node "text:database-display"
//		if(nodSujet.containElementByName("text:database-display")) {
//			String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:database-display").getAttributs().get("text:column-name"));
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:database-display", "text:column-name", valueAttribut);
//		}
//		//si le node "text:p" contient un "text:date" alors le recherche par le "text:fixed" de ce node "text:date"
//		if(nodSujet.containElementByName("text:date")) {
//			String valueAttribut = outils.withoutCodeAndPoint(nodSujet.retourneFirstEnfantsByName("text:date").getAttributs().get("text:fixed"));
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstNodeByNameContainsNodeByNameAndAttributValue("text:p","text:date", "text:fixed", valueAttribut);
//		}
//		if(nodSujet.containElementByName("text:subject")) {
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:subject");
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:subject");
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:subject");
//		}
//		if(nodSujet.containElementByName("text:title")) {
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:title");
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:title");
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:title");
//		}
//		if(nodSujet.containElementByName("text:initial-creator")) {
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:initial-creator");
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:initial-creator");
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:initial-creator");
//		}
//		if(nodSujet.containElementByName("text:creator")) {
//			if(nod2Student!=null) if(nodStudent==null) nodStudent = nod2Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:creator");
//			if(nod1Student!=null) if(nodStudent==null) nodStudent = nod1Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:creator");
//			if(nod0Student!=null) if(nodStudent==null) nodStudent = nod0Student.retourneFirstEnfantsByNameNode1ContainNameNode2("text:p","text:creator");
//		}
		
		
		//** Recherche par contenu
		if(!nodSujet.getContenu().isEmpty()) {
			String valueAttribut = outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getContenu().get(0));
			if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod2Student.getNodes(), valueAttribut, nodSujet.getNomElt());
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod1Student.getNodes(), valueAttribut, nodSujet.getNomElt());
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod0Student.getNodes(), valueAttribut, nodSujet.getNomElt());
		}
		
		//** Recherche le node par index uniquement même si pas demandé. Si trouve pas retourne un null.
		if(nodSujet.getAttributs().get("index")!=null){
			String valueAttribut = nodSujet.getAttributs().get("index");
			if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student.getNodes(), nodSujet.getNomElt(),"index",valueAttribut);
		}
		
		
		return nodStudent;
	}
	
	
	
	/**
	 * Recherche Le node draw:frame par l'attribut draw:name ou recherche_anchor-apge-number.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findDrawFrame(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		//recherche par le nom de l'objet draw:name par défaut
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));

		if(nodStudent!=null) return nodStudent;
			
		if(nodSujet.getAttributs().get("recherche_anchor-page-number")!=null) if(nodSujet.getAttributs().get("recherche_anchor-page-number").equalsIgnoreCase("true")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
		}
		
		return nodStudent;
	}
	
	/**
	 * 
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findTextConditional(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		String valueAttribut = outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:condition"));
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:condition", valueAttribut);
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:condition", valueAttribut);
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:condition", valueAttribut);

		return nodStudent;
	}
	
	/**
	 * Recherche Le node text:section par l'attribut text:name;</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findByTextName(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));

		return nodStudent;
	}
	
	/**
	 * 
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findTextDate(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		String valueAttribut = outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:fixed"));
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:fixed", valueAttribut);
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:fixed", valueAttribut);
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:fixed", valueAttribut);

		return nodStudent;
	}

	/**
	 * Recherche le node par son nom. Retourne le premier node qui correspond au nom du node.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findByNameNode(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		if(nod2Student!=null) if(nod2Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod2Student.retourneFirstEnfantsByName(nameNode);
		if(nod1Student!=null) if(nodStudent==null) if(nod1Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod1Student.retourneFirstEnfantsByName(nameNode);
		if(nod0Student!=null) if(nodStudent==null) if(nod0Student.retourneEnfantsByNameExist(nameNode)) nodStudent = nod0Student.retourneFirstEnfantsByName(nameNode);
	
		return nodStudent;
	}
	
	/**
	 * Recherche le node text:database-display par text:column-name.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findDataBase(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		String valueAttribut = outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name"));
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:column-name", valueAttribut);
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:column-name", valueAttribut);
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:column-name", valueAttribut);

		return nodStudent;
	}
	
	/**
	 * Recherche le node par le contenu simple get(0).</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findByContentZero(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		if(!nodSujet.getContenu().isEmpty()) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameContent(nod2Student, nameNode, nodSujet.getContenu().get(0));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameContent(nod1Student, nameNode, nodSujet.getContenu().get(0));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameContent(nod0Student, nameNode, nodSujet.getContenu().get(0));
		}

		return nodStudent;
	}
	
	/**
	 * Rechercher le node table:table par table:name.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findTable(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "table:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("table:name")));
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "table:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("table:name")));
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "table:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("table:name")));
		
		return nodStudent;
	}
	
	/**
	 * Recherche le node par son contenu avec une tolérance de carcatère.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findByContentWithTolerance(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		String contenuSujet = nodSujet.retourneLesContenusEnfants("");
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);

		return nodStudent;
	}
	
	/**
	 * Rechreche le node par son index même si pas demandé.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findByIndexEvenIsFalse(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		if(nodSujet.getAttributs().get("index")!=null){
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", nodSujet.getAttributs().get("index"));
		}
		return nodStudent;
	}
	
	/**
	 * Recherche le node par table:style-name.</br>
	 * @param nameNode
	 * @param nodSujet
	 * @param nod0Student
	 * @param nod1Student
	 * @param nod2Student
	 * @param a
	 * @return
	 */
	private static node findTableByStyleName(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a) {
		node nodStudent = null;
		if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "table:style-name", nodSujet.getAttributs().get("table:style-name"));
		if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "table:style-name", nodSujet.getAttributs().get("table:style-name"));
		if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "table:style-name", nodSujet.getAttributs().get("table:style-name"));
		
		return nodStudent;
	}
	
	
}
