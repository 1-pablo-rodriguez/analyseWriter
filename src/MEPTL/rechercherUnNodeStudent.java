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
		
		//** Priorité à la recherche par contenu
		if(nodSujet.getAttributs().get("recherche_contenu_exact")!=null){
			nodStudent = findNodeParContenuEXACT(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
			if(nodStudent != null) return nodStudent;
		}
		
		// recherche le node par index uniquement
		if(nodSujet.getAttributs().get("recherche_index")!=null){
			nodStudent = findNodeParIndex(nameNode, nodSujet, nod0Student, nod1Student, nod2Student, a);
			if(nodStudent != null) return nodStudent;
		}
		
		//*****************************************************************
		//** Recherche Le node text:p par les différents contenu du node **
		//*****************************************************************
		if(nameNode.equals("text:p")) {
			nodStudent = findLeNodeTextpParDesNodesEnfantsSpecifique(nameNode, nodSujet,nod0Student, nod1Student, nod2Student, a );
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
		
		
		//recherche par le contenu enfant du node les titres par défaut
		if(nameNode.equals("text:h")) {
				String contenuSujet = nodSujet.retourneLesContenusEnfants("");
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
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
		
		//recherche par le nom de la colonne dans les database
		if(nameNode.equals("text:database-display")) {
			if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:column-name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:column-name")));
		}
		
		//recherche par le nom de l'objet draw:name ou par text:anchor-page-number si ancrer à la page
		if(nameNode.equals("draw:frame")) {
			if(nodSujet.getAttributs().get("recherche_anchor-page-number")==null) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
			}else {
				if(nodSujet.getAttributs().get("recherche_anchor-page-number").equals("false")) {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
				}
				if(nodSujet.getAttributs().get("recherche_anchor-page-number").equals("true")) {
					if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:anchor-page-number", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("text:anchor-page-number")));
					//si recherche l'ancrage de la page pas trouvé alors recherche par draw:name
					if(nodStudent==null) {
						if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod2Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
						if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod1Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
						if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValueNetTexte(nod0Student, nameNode, "draw:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("draw:name")));
					}
				}
			}
		}
		
		
		//recherche par le nom de l'objet
		if(nameNode.equals("style:graphic-properties")) {
			if(!nodSujet.getContenu().isEmpty()) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameContent(nod2Student, nameNode, nodSujet.getContenu().get(0));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameContent(nod1Student, nameNode, nodSujet.getContenu().get(0));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameContent(nod0Student, nameNode, nodSujet.getContenu().get(0));
			}
		}
		
		
		//Recherche un node table
		if(nameNode.contains("table")) {
			//recherche par le nom de la table
			if(nameNode.equals("table:table")) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "table:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("table:name")));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "table:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("table:name")));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "table:name", outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getAttributs().get("table:name")));
			}		
			
			//recherche par l'index
			if(nameNode.equals("table:table-row")) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", nodSujet.getAttributs().get("index"));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", nodSujet.getAttributs().get("index"));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			}
			
			//recherche par l'index
			if(nameNode.equals("table:table-cell")) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", nodSujet.getAttributs().get("index"));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", nodSujet.getAttributs().get("index"));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", nodSujet.getAttributs().get("index"));
			}

			//recherche par le nom du style qui est toujours formé par nomtable.colonne
			if(nameNode.equals("table:table-column")) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "table:style-name", nodSujet.getAttributs().get("table:style-name"));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "table:style-name", nodSujet.getAttributs().get("table:style-name"));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "table:style-name", nodSujet.getAttributs().get("table:style-name"));
			}
			
			//recherche par texte:name
			if(nameNode.equals("text:table-of-content")) {
				if(nod2Student!=null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));		
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "text:name", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("text:name")));
			}
		}
		
		
		//recherche par contenu
		if(nameNode.equals("text:span")) {
			String contenuSujet = nodSujet.retourneLesContenusEnfants("");
			if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
		}
		
		//recherche par contenu
		if(nameNode.equals("text:tab")) {
			String contenuSujet = nodSujet.retourneLesContenusEnfants("");
			if(!contenuSujet.trim().isEmpty()) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
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
		
	
	
	private static node findNodeParContenuEXACT(String nameNode, node nodSujet,node nod0Student, node nod1Student, node nod2Student, Run a ) {
		node nodStudent = null;
		if(nodSujet.getAttributs().get("recherche_contenu_exact").equals("true")) {
			String valueAttribut = outils.withoutCodeAndPointPourRechercheContenuExact(nodSujet.getContenu().get(0));
			if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod2Student.getNodes(), valueAttribut, nodSujet.getNomElt());
			if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod1Student.getNodes(), valueAttribut, nodSujet.getNomElt());
			if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContentExact(nod0Student.getNodes(), valueAttribut, nodSujet.getNomElt());
		}
		return nodStudent;
	}
	
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
		
		if(nodSujet.getAttributs().get("recherche_contenu_exact")!=null) {
			if(nodSujet.getAttributs().get("recherche_contenu_exact").equalsIgnoreCase("true")) {
				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
			}
		}
		
		
//		if(nodSujet.getAttributs().get("recherche_index")!=null) {
//			if(nodSujet.getAttributs().get("recherche_index").equalsIgnoreCase("true")) {
//				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//			}
//		}
//		
//		
//		if(nodStudent==null) {
//			if(nodSujet.retourneLesContenusEnfants("").isEmpty()) { //s'il n'y a pas de contenu, passe par l'index
//				if(nodSujet.getAttributs().get("index")!=null) {
//					if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod2Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//					if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod1Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//					if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByNameAttributValue(nod0Student, nameNode, "index", outils.withoutCodeAndPoint(nodSujet.getAttributs().get("index")));
//				}
//			}else {
//				String contenuSujet = nodSujet.retourneLesContenusEnfants("");
//				if(nod2Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod2Student.getNodes(),contenuSujet ,commandes.tolerance_characters,commandes.tolerance_text);
//				if(nod1Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod1Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
//				if(nod0Student!=null) if(nodStudent==null) nodStudent = a.retourneFirstNodeByFindContent2(nod0Student.getNodes(), contenuSujet,commandes.tolerance_characters,commandes.tolerance_text);
//			}
//		}
		
		
		return nodStudent;
	}
	
	
}
