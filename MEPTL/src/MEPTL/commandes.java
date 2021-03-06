package MEPTL;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cXML.Run.UserStatus;





/**
 * 
 * @author pablo rodriguez
 *
 */
public class commandes {

	static String nameSujet = "";  //sujet par défaut
	public static boolean analyse = false; //analyse des fichier étudiants
	public static boolean ecritCode = false; // ecriture du code du sujet
	public static boolean ecritSujet = false; // ecriture 2 du code du sujet
	public static boolean ecritNoteCSV = false; // ecriture note.csv
	public static boolean sansFeeback = false; // pas de feedback étudiant
	public static boolean verifHisto = false; // vérification des historiques correspond à la commande -verif
	public static boolean verifHisto2 = false; // vérification des historiques lorsqu'il y a aussi analyse
	public static boolean fourniCSV = false; // fourni le fichier CSV contenant la liste des étudiants
	public static boolean badCommand = false ; //erreur dans les commandes
	public static boolean noNote =false;
	public static boolean noLogo =false;
	public static boolean licence = false;
	static cXML.Run.UserStatus Profil = cXML.Run.UserStatus.TEACHER ; //Le profil TEACHER permet de lire dans les dossiers contenus dans le répertoire courant de l'application. Le profil STUDENT permet de lire au niveau du répertoire courant de l'application. 
	public static boolean fourniDossierDestination = false; //répertoire de destination des feedbacks et CSV;
	public static String nameCSV = ""; //le nom du fichier contenant la liste des étudiants
	public static String path ="";
	public static String pathDestination ="";
	public static String version ="3.5.0";

	
	/**
	 * 
	 * @param args : table des arguments
	 * @param patch : chemin vers le répertoire courant de l'application
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public commandes(String[] args, String path) throws IOException, InterruptedException {
		commandes.path = path;
		
		try {
			if(args.length==0) {badCommand=true; System.out.println("Il doit y avoir au minimum une commande.");System.out.println("Tapez la commande -help pour obtenir de l'aide.");}
			for(int i = 0 ; i < args.length ; i++) {
				if(args[i].equals("-use")) if((i+1)<args.length) { if(!args[i+1].contains(".xml")) {badCommand=true; System.out.println("La commande -use doit être suivi du fichier d'analyse");}}else {badCommand=true; System.out.println("La commande -use doit être suivi du fichier d'analyse");}
				if(args[i].contains(".xml")) {
					Matcher m = Pattern.compile("{1,}.xml$").matcher(args[i]);
					if(m.find()) {nameSujet = args[i];}else {badCommand=true;System.out.println("Problème avec l'extension du fichier .xml");};
					if(i-1>=0) {if(args[i-1].equals("-use")) analyse=true;}else {badCommand=true; System.out.println("La commande -se doit être devant le fichier d'analyse.");}
					if(i-1>=0) {if(!args[i-1].equals("-use")) {badCommand=true; System.out.println("Manque la commande -use devant le nom du fichier d'analyse.");}}else {badCommand=true; System.out.println("La commande -se doit être devant le fichier d'analyse.");}
				}
				if(args[i].contains(".csv")) {
					Matcher m = Pattern.compile("{1,}.csv$").matcher(args[i]);
					if(m.find()) {fourniCSV=true; nameCSV = args[i];}else {badCommand=true;System.out.println("Problème avec l'extension du fichier .csv");}
					if(!args[i-1].equals("-csv") && !args[i-1].equals("-verifcsv")) {badCommand=true; System.out.println("Manque la commande -csv ou -verifcsv devant le nom du fichier contenant la liste des étudiants avec leurs identifiants.");}
				}
				if(args[i].equals("-csv")) {
					ecritNoteCSV=true;
					if(!analyse) {badCommand=true;System.out.println("Vous devez taper la commande -use analysefile.xml devant la commande -csv");}
				}
				if(args[i].equals("-verifcsv")) {
					if(verifHisto) {badCommand=true;System.out.println("La commande -verifcsv et -verif ne peuvent pas être exécutées ensemble");}
					ecritNoteCSV=true;
					verifHisto=false;
					verifHisto2=true;
					if(!analyse) {badCommand=true;System.out.println("Vous devez taper la commande -use analysefile.xml devant la commande -verifcsv");}
				}
				if(args[i].equals("-nofeedback")) {
					sansFeeback=true;
				}
				if(args[i].equals("-verif")) {
					if(verifHisto2) {badCommand=true;System.out.println("La commande -verifcsv et -verif ne peuvent pas être exécutées ensemble");}
					verifHisto=true;
					verifHisto2=false;
				}
				if(args[i].equals("-help")) {
					if(args.length>1) {System.out.println("\n\n***\nLa commande -help doit être unique.\n***");clotureWithError();}
					help();
					clotureApplication();
				}
				if(args[i].equals("-licence")) {
					if(args.length>1) {System.out.println("\n\n***\nLa commande -licence doit être unique.\n***");clotureWithError();}
					licence();
				}
				if(args[i].equals("-write")) {
					if(args.length>1) {System.out.println("\n\n***\nLa commande -write doit être unique.\n***");clotureWithError();}
					ecritCode=true;
					Profil = UserStatus.STUDENT;
				}
				if(args[i].equals("-sujet")) {
					if(!analyse) {badCommand=true;System.out.println("Vous devez taper la commande -use analysefile.xml devant la commande -sujet");}
					ecritSujet=true;
				}
				if(args[i].equals("-nologo")) {
					if(!analyse) {badCommand=true;System.out.println("Vous devez taper la commande -use analysefile.xml devant la commande -sujet");}
					noLogo=true;
				}				
				if(args[i].equals("-about")) {
					if(args.length>1) {System.out.println("\n\n***\nLa commande -about doit être unique.\n***");clotureWithError();}
					about();
					clotureApplication();
				}
				if(args[i].equals("-nonote")) {
					noNote=true;
				}
				if(args[i].equals("-dest")) if((i+1)<args.length) {
					Matcher m = Pattern.compile("^\\./.{1,}/$").matcher(args[i+1]);
					if(m.find()) {
						fourniDossierDestination=true;
						pathDestination = args[i+1].substring(2, args[i+1].length()-1);
						pathDestination = pathDestination.replace("\\","/");
						File file = new File(path+"/"+ pathDestination);
				        if (!file.exists()) {
				            if (file.mkdir()) {
				                System.out.println("Le répertoire \"" + pathDestination + "\" est créé.");
				            } else {
				            	 System.out.println("Peut pas créer le répertoire " + pathDestination);
				            }
				        }else {
				        	System.out.println("Le répertoire \"" + pathDestination + "\" n'est pas créé.");
				        }
					}else {
						System.out.println(args[i+1]);
						System.out.println("Après la commande -dest, il doit y avoir le chemin vers le dossier de destination.\nLe chemin vers le dossier de destination n'est pas correct.");
						badCommand =true;
					}
				}
				Matcher m = Pattern.compile("^\\./.{1,}/$").matcher(args[i]);
				if(!args[i].equals("-use")&&!args[i].equals("-write")&&!args[i].equals("-csv")&&!args[i].equals("-verif")&&!args[i].equals("-verifcsv")
						&&!args[i].contains(".csv")&&!args[i].contains(".xml")&&!args[i].contains("-nofeedback")&&!args[i].contains("-help")
						&&!args[i].equals("-about")&&!args[i].equals("-nonote") &&!args[i].equals("-dest")&&!args[i].equals("-sujet")&&!args[i].equals("-nologo")
						&&!args[i].equals("-licence")&&!m.find()) {
					badCommand=true; System.out.println("La commande " + args[i] + " est inconnue.");System.out.println("Tapez la commande -help pour obtenir de l'aide.");
				}
			}
		}catch (ArrayIndexOutOfBoundsException e){
	        System.out.println("Error...bad argument");
	        clotureWithError();
	        System.exit(0);
	 }
		
		if(badCommand) clotureWithError();

		//affichage dans la console des commandes passées
		CLS();
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("AnalyseWriter Version : " + version );
		System.out.println();
		System.out.println("Analyse des fichier étudiants = " + analyse);
		System.out.println("Fichier d'analyse au format XML = " + nameSujet);
		System.out.println("Pas de Feedback = " + sansFeeback);
		System.out.println("Pas de logo = " + noLogo);
		System.out.println("Ecriture d'un fichier d'analyse = "+ ecritCode);
		System.out.println("Ecriture d'un fichier sujet.xml = "+ ecritSujet);
		System.out.println("Vérification des historiques (-verif) = " + verifHisto);
		System.out.println("Vérification et écriture d'un fichier CSV (-verifcsv) = " + verifHisto2);
		System.out.println("Exportation au format CSV = " + ecritNoteCSV);
		System.out.println("Fichier contenant les identifiants des étudiants fourni = " + fourniCSV);
		System.out.println("Nom du fichier CSV contenant les identifiants des étudiants = " + nameCSV);
		System.out.println("Fourni un répertoire de destination = " + fourniDossierDestination);
		System.out.println("Répertoire de destination = " + pathDestination);
		System.out.println("Nombre de commande = " + args.length);
		
		System.out.println();
		System.out.println("Dossier de travail = " + path);
		System.out.println("-----------------------------------");
		System.out.println();
		
		
	}
	
	/**
	 * Message dans la console
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private static void help() throws InterruptedException, IOException {
		CLS();
		System.out.println();
		System.out.println("***************************************");
		System.out.println("* LISTE DES COMMANDES D'ANALYSEWRITER * ");
		System.out.println("***************************************");
		System.out.println();
		
		System.out.println(" -use       : \t Permet d'indiquer le fichier d'analyse.");
		System.out.println("            : \t Le fichier d'analyse (format XML) doit être placé juste après la commande.");
		System.out.println("            : \t Les fichiers des étudiants doivent être dans des dossiers nominatifs.");
		System.out.println("            : \t Les fichiers des étudiants doivent être au format ODF avec l'extension .odt.");
		System.out.println();
		
		System.out.println(" file.xml   : \t Le fichier d'analyse au format XML.");
		System.out.println("            : \t file.xml doit être placé juste après la commande -use.");
		System.out.println("            : \t Le fchier file.xml doit se trouver dans le dossier courant*.");
		System.out.println("            : \t Ce fichier doit être obtenu avec la commande -write.");
		System.out.println("            : \t Ce fichier doit être manuellement modifié pour l'adapter à votre analyse.");
		System.out.println();
		
		System.out.println(" -verif     : \t Permet de comparer toutes les modifications entre les historiques du suivi de modification.");
		System.out.println("            : \t Si c'est la seule commande alors il n'y a pas d'analyse, pas de note, pas de feedback.");
		System.out.println("            : \t Cette commande ne dépend pas d'un fichier d'analyse (indépendant des sujets).");
		System.out.println("            : \t Vous pouvez analyser les historiques même si vous n'avez pas de fichier d'analyse.");
		System.out.println("            : \t Dans le dossier courant*, vous trouverez le fichier Verif.xml.");
		System.out.println();
		
		System.out.println(" -cvs       : \t Permet d'importer toutes les notes dans un fichier au format CSV (séparateur le point virgule).");
		System.out.println("            : \t La commande -use file.xml doit être placé avant la commande -csv.");
		System.out.println("            : \t Le fichier généré se trouve dans le dossier courant*.");
		System.out.println("            : \t Cette commande peut être suivi d'un fichier au format CSV contenant la liste des étudiants.");
		System.out.println("            : \t Si cette commande est suivi du fichier file.csv alors récupère les identifiants des étudiants.");
		System.out.println();
		
		System.out.println(" -verifcvs  : \t Permet de comparer toutes les modifications entre les historiques du suivi de modification.");
		System.out.println("            : \t Permet d'importer toutes les notes dans un fichier au format CSV (séparateur le point virgule)");
		System.out.println("            : \t La commande -use file.xml doit être placé avant la commande -verifcsv.");
		System.out.println("            : \t Dans le dossier courant, vous trouverez le fichier Verif.xml.");
		System.out.println("            : \t La commande -verifcsv peut être suivi d'un fichier au format CSV contenant la liste des étudiants.");
		System.out.println("            : \t Si cette commande est suivi du fichier file.csv alors récupère les identifiants des étudiants.");
		System.out.println();
		
		System.out.println(" file.csv   : \t Le fichier contenant la liste des étudiants au format CSV.");
		System.out.println("            : \t Le fichier file.csv doit être placé juste après la commande -csv ou -verifcsv.");
		System.out.println("            : \t Le fichier file.csv doit se trouver dans le dossier courant*.");
		System.out.println("            : \t Le séparateur dans le fichier CSV doit être le point virgule.");
		System.out.println("            : \t Dans ce fichier, il doit y avoir les colonnes \"Prénom\", \"Nom\",\"Numéro d'identification\" et \"Adresse de courriel\".");
		System.out.println("            : \t Vous exportez ce fichier depuis le serveur Moodle (carnet de note, exporter).");
		System.out.println();
		
		System.out.println(" -write     : \t Permet d'écrire un fichier d'analyse.");
		System.out.println("            : \t Le fichier généré se trouve dans le dossier courant*.");
		System.out.println("            : \t Le fichier généré est au format XML.");
		System.out.println("            : \t Vous devez l'adapter en modifiant le code XML pour réaliser vos propres analyses.");
		System.out.println();
		
		System.out.println(" -nofeedback: \t Permet de ne pas générer les feedbacks pour les étudiants.");
		System.out.println("            : \t Les feedbacks sont des fichiers au format HTML.");
		System.out.println("            : \t Les feedbacks se trouvent dans le dossier courant*.");
		System.out.println();
		
		System.out.println(" -nonote    : \t Evite l'affichage dans les feedbacks de la note.");
		System.out.println("            : \t Evite de placer la note dans le nom du fichier.");
		System.out.println();
		
		System.out.println(" -dest      : \t Indique le répertoire de destination des feedbacks et des fichiers XML et CSV.");
		System.out.println("            : \t Exemple : -dest \"./sortie/\" dossier de destination le répertoire \"sortie\".");
		System.out.println("            : \t Le répertoire de destination se trouve obligatoirement dans le dossier courant de l'application.");
		System.out.println("            : \t Il ne peut y avoir qu'un seul répertoire (pas de sous répertoire).");
		System.out.println("            : \t Le chemin commence obligatoirement par \"./\".");
		System.out.println("            : \t Si le nom du dossier contient le symbole $ alors il est ignoré par l'analyse.");
		System.out.println();
		
		System.out.println(" -sujet     : \t Permet de récupérer le fichier d'analyse contenant uniquement les nodes évalués.");
		System.out.println("            : \t C'est à partir du fichier d'analyse \"file.xml\" qu'est généré le fichier \"sujet.xml\".");
		System.out.println("            : \t Le fichier \"sujet.xml\" se trouve dans le dossier courant de l'application.");
		System.out.println();
		
		System.out.println(" -doc       : \t Permet de générer la documentation de l'application.");
		System.out.println("            : \t Le fichier doc.pdf se trouve dans le dossier courant*.");
		System.out.println("            : \t Comment réaliser un fichier d'analyse au format XML?");
		System.out.println();
		
		System.out.println(" -about     : \t Affiche la version, la date du build, l'auteur, la licence.");
		System.out.println();
		
		System.out.println(" -licence   : \t Affiche le texte de la licence de l'application.");
		System.out.println();
		
		System.out.println();
		System.out.println("---");
		System.out.println("* Le dossier courant est le dossier dans lequel se trouve l'application \"analyseWriter.jar\".\n"
				+"Dossier courant -> " + path);
		System.out.println();
		System.out.println("P. Rodriguez");
		
	}
	
	/**
	 * Message dans la console
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private static void about() throws InterruptedException, IOException {
		CLS();
		System.out.println();
		System.out.println("*******************************************************************");
		System.out.println("application: analyseWriter");
		System.out.println();
		System.out.println("version : " + version);
		System.out.println("Mise à jour : https://github.com/1-pablo-rodriguez/analyseWriter\n");
		System.out.println("enseignant : pablo Rodriguez");
		System.out.println("Université d'Artois - 10 rue du temple 62000 Arras\n");
		System.out.println("LICENCE");
		System.out.println("GNU GENERAL PUBLIC LICENSE\r\nVersion 3, 29 June 2007");
		System.out.println("*******************************************************************");
		System.out.println();
	}
	
	/**
	 * Licence de l'application
	 * @throws IOException
	 */
	private static void licence() throws IOException {
		 ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = classLoader.getResourceAsStream("licence.txt");
			
			Reader initialReader  = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			char[] arr = new char[8 * 1024];
		    StringBuilder buffer = new StringBuilder();
		    int numCharsRead;
		    while ((numCharsRead = initialReader.read(arr, 0, arr.length)) != -1) {
		        buffer.append(arr, 0, numCharsRead);
		    }
		    initialReader.close();
		    System.out.println(buffer.toString());
		    
			System.out.println();
			System.out.println("\t\t┌─────────────────────────────────────────────┐");
			System.out.println("\t\t│  Above, you have the application license.   │");
			System.out.println("\t\t│                                             │");
			System.out.println("\t\t│  (')_(')                                    │");
			System.out.println("\t\t│  (=`.°=)                                    │");
			System.out.println("\t\t│  (\")__(\") .. see you soon, analyseWriter.   │");
			System.out.println("\t\t└─────────────────────────────────────────────┘");
			System.out.println();
			System.exit(0);
	 }
	
	
	/**
	 * Clôture avec une erreur de commande
	 */
	public static void clotureWithError() {
		System.out.println();
		System.out.println("\t\t┌─────────────────────────────────────────────┐");
		System.out.println("\t\t│  You made a mistake in your command.        │");
		System.out.println("\t\t│                                             │");
		System.out.println("\t\t│  You need to read the above information and │");
		System.out.println("\t\t│  start over.                                │");		
		System.out.println("\t\t│                                             │");
		System.out.println("\t\t│  (')_(')                                    │");
		System.out.println("\t\t│  (=`.°=)                                    │");
		System.out.println("\t\t│  (\")__(\") .. see you soon, analyseWriter.   │");
		System.out.println("\t\t└─────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
	}
	
	
	/**
	 * Bye Bye the application
	 */
	public static void clotureApplication() {
		System.out.println();
		System.out.println("\t\t┌────────────────────────────────────────────────────┐");
		System.out.println("\t\t│  (')_(')                                           │");
		System.out.println("\t\t│  (=`.°=)     Well done, you've worked very hard!   │");
		System.out.println("\t\t│  (\")__(\") .. Bye Bye analyseWriter.                │");
		System.out.println("\t\t└────────────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
	}
	
	public static void CLS() throws InterruptedException, IOException {
	   	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}

