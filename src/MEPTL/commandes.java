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

	//** Les commandes par défaut
	static String nameSujet = "";  //sujet par défaut
	public static boolean analyse = false; //analyse des fichiers étudiants
	public static boolean ecritCode = false; // -write : ecriture du code du sujet
	public static boolean ecritSujet = false; // -sujet : ecriture 2 du code du sujet, uniquement les nodes évalués
	public static boolean ecritNoteCSV = false; // ecriture note.csv
	public static boolean writefiles = false; // -writefiles permet d'écrire tous les fichiers XML après la lecture en node
	public static boolean calculLeHashDuFichier = false; //  -hash retourne le hash du fichier d'analyse
	public static boolean sansFeeback = false; // pas de feedback étudiant
	public static boolean verifHisto = false; // vérification des historiques correspond à la commande -verif
	public static boolean verifHisto2 = false; // vérification des historiques lorsqu'il y a aussi analyse
	public static boolean fourniCSV = false; // fourni le fichier CSV contenant la liste des étudiants
	public static boolean badCommand = false ; //erreur dans les commandes
	public static boolean noNote =false; // pas de note dans les feedbacks
	public static boolean noLogo =false; // pas de logo dans les feedbacks
	public static boolean newLogo=false; // un nouveau logo dans le feedback
	public static boolean licence = false; // affiche la licence
	public static boolean zipfeedback = false;  // Les feedback dans une archive ZIP
	public static boolean fichierStudentMoodle = false;  // Les fichiers étudiants sont directement dans le dossier courant de l'application.
	static cXML.Run.UserStatus Profil = cXML.Run.UserStatus.TEACHER ; //Le profil TEACHER permet de lire dans les dossiers contenus dans le répertoire courant de l'application. Le profil STUDENT permet de lire au niveau du répertoire courant de l'application. 
	public static boolean fourniDossierDestination = false; //répertoire de destination des feedbacks et CSV;
	public static String nameCSV = ""; //le nom du fichier contenant la liste des étudiants
	public static String nameSVG =""; //le nom du nouveau logo
	public static String contenuFichierSVG =""; // Le nouveau logo
	public static String path ="";
	public static String pathDestination ="";
	public static String version ="3.6.0"; // La version
	public static String Command ="";
	
	//** setting valeur par défaut
	public static String culture = "FR";
	public static int tolerance_characters = 5;
	public static double tolerance_text = 0.79;
	public static int number_match = 2;
	public static int mini_number_modification = 0;
	public static int nombres_modifications_simultané_maxi = 100;
	public static int tolerance_rouge = 30;
	public static int tolerance_vert = 30;
	public static int tolerance_bleu = 30;
	
	
	
	/**
	 * 
	 * @param args : table des arguments
	 * @param patch : chemin vers le répertoire courant de l'application
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public commandes(String[] args, String path) throws IOException, InterruptedException {
		commandes.path = path;
		
		for(int i = 0 ; i < args.length ; i++) {
			Command = Command  + " " +args[i];
		}
		
		try {
			if(args.length==0) {badCommand=true; System.out.println("There must be at least one command.");System.out.println("You can type the -help command to get help.");}
			for(int i = 0 ; i < args.length ; i++) {
				if(args[i].equals("-use")) if((i+1)<args.length) { if(!args[i+1].contains(".xml")) {badCommand=true; System.out.println("The -use command must be followed by the name of analyze file.");}}else {badCommand=true; System.out.println("The -use command must be followed by the name of analyze file.");}
				if(args[i].contains(".xml")) {
					Matcher m = Pattern.compile("{1,}.xml$").matcher(args[i]);
					if(m.find()) {nameSujet = args[i];}else {badCommand=true;System.out.println("There is a problem with the extension of the analyze file. The extension must be \".xml\".");};
					if(i-1>=0){
						if(args[i-1].equals("-use")) analyse=true;
						if(args[i-1].equals("-hash")) calculLeHashDuFichier=true;
						if(!analyse&&!calculLeHashDuFichier) {
							badCommand=true;
							System.out.println("The -use or -hash command must be before the analysis file name.");
						}
					}
				}
				if(args[i].contains(".csv")) {
					Matcher m = Pattern.compile("{1,}.csv$").matcher(args[i]);
					if(m.find()) {fourniCSV=true; nameCSV = args[i];}else {badCommand=true;System.out.println("There is a problem with the extension of the CSV file. The extension must be \".csv\".");}
					if(!args[i-1].equals("-csv") && !args[i-1].equals("-verifcsv")) {badCommand=true; System.out.println("The -csv or -verifcsv command is missing in front of the name of the file containing the list of students with their identifiers.");}
				}
				if(args[i].equals("-csv")) {
					if(!analyse) {badCommand=true;System.out.println("You must type the -use analyzefileName.xml command then after the command -csv.");}
					ecritNoteCSV=true;
				}
				if(args[i].contains(".svg")) {
					Matcher m = Pattern.compile("{1,}.svg$").matcher(args[i]);
					if(m.find()) {newLogo=true; nameSVG = args[i];}else {badCommand=true;System.out.println("There is a problem with the extension of the SVG file. The extension must be \".svg\".");}
					if(!args[i-1].equals("-newLogo")) {badCommand=true; System.out.println("The -newLogo command is missing in front of the name of the SVG file.");}
				}
				if(args[i].equals("-newLogo")) {
					if(!analyse) {badCommand=true;System.out.println("You must type the -use analyzefileName.xml command then after the command -newLogo.");}
					if(noLogo) {badCommand=true;System.out.println("The -newLogo and -noLogo commands cannot be executed together.");}
					newLogo=true;
				}
				if(args[i].equals("-verifcsv")) {
					if(verifHisto) {badCommand=true;System.out.println("The -verifcsv and -verif commands cannot be executed together.");}
					ecritNoteCSV=true;
					verifHisto=false;
					verifHisto2=true;
					if(!analyse) {badCommand=true;System.out.println("You must type the command -use analyzeFileName.xml then after the command -verifcsv.");}
				}
				if(args[i].equals("-nofeedback")) {
					if(zipfeedback)  {badCommand=true;System.out.println("It is not possible to run the -nofeedback and -zipfeedback commands at the same time. ");}
					sansFeeback=true;
				}
				if(args[i].equals("-verif")) {
					if(verifHisto2) {badCommand=true;System.out.println("The -verifcsv and -verif commands cannot be executed together.");}
					verifHisto=true;
					verifHisto2=false;
				}
				if(args[i].equals("-help")) {
					if(args.length>1) {System.out.println("\n\n***\nThe -help command should be the only command.\n***");clotureWithError();}
					helpEN();
					clotureWithHelp();
				}
				if(args[i].equals("-aide")) {
					if(args.length>1) {System.out.println("\n\n***\nThe -aide command should be the only command.\n***");clotureWithError();}
					helpFR();
					clotureWithHelp();
				}
				if(args[i].equals("-licence")) {
					if(args.length>1) {System.out.println("\n\n***\nThe -licence command should be the only command.\n***");clotureWithError();}
					licence();
				}
				if(args[i].equals("-write")) {
					if(args.length>1) {System.out.println("\n\n***\nThe -write command should be the only command.\n***");clotureWithError();}
					ecritCode=true;
					Profil = UserStatus.STUDENT;
				}
				if(args[i].equals("-writefiles")) {
					if(!(args.length==1 && Command.contains("-writefiles") || args.length==2 && Command.contains("-writefiles")&&Command.contains("-f")) ) {System.out.println("\n\n***\nThe -writefiles command should be the only command or with -f.\n***");clotureWithError();}
					writefiles=true;
				}
				if(args[i].equals("-hash")) {
					if(args.length>2) {System.out.println("\n\n***\nThe -hash command should only be with file.xml\n***");clotureWithError();}
					calculLeHashDuFichier=true;
				}
				if(args[i].equals("-sujet")) {
					if(!analyse) {badCommand=true;System.out.println("You must type the command -use, then  the name of analysis file, then  the  command -sujet.");}
					if(args.length>3) {badCommand=true; System.out.println("If you use the command -sujet, you can't use other commands except -use and the filename.");}
					ecritSujet=true;
				}
				if(args[i].equals("-nologo")) {
					if(!analyse) {badCommand=true;System.out.println("You must type the command -use , then the filename and after then the command -sujet.");}
					if(newLogo) {badCommand=true;System.out.println("The -newLogo and -noLogo commands cannot be executed together.");}
					noLogo=true;
				}				
				if(args[i].equals("-about")) {
					if(args.length>1) {System.out.println("\n\n***\nThe -about command should be the only command.\n***");clotureWithError();}
					about();
					clotureApplication();
				}
				if(args[i].equals("-nonote")) {
					noNote=true;
				}
				if(args[i].equals("-zipfeedback")) {
					if(!analyse) {badCommand=true;System.out.println("You must type the command -use, then the name of analysis file, then  the  command -zipfeedback.");}
					if(sansFeeback) {badCommand=true;System.out.println("It is not possible to run the -nofeedback and -zipfeedback commands at the same time.");}
					zipfeedback=true;
				}
				if(args[i].equals("-f")) {
					if(Command.contains(" -d ")) {badCommand=true;System.out.println("The -f and -d commands cannot be executed together.");}
					fichierStudentMoodle=true;
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
				                System.out.println("The directory \"" + pathDestination + "\" has been created.");
				            } else {
				            	 System.out.println("Cannot create  the directory " + pathDestination);
				            }
				        }else {
				        	System.out.println("The directory \"" + pathDestination + "\" has not been created.");
				        }
					}else {
						System.out.println(args[i+1]);
						System.out.println("After the -dest command, there must be the path to the destination folder.\nThe path to the destination folder is not correct.");
						badCommand =true;
					}
				}
				Matcher m = Pattern.compile("^\\./.{1,}/$").matcher(args[i]);
				if(!args[i].equals("-use")&&!args[i].equals("-write")&&!args[i].equals("-csv")&&!args[i].equals("-verif")&&!args[i].equals("-verifcsv")
						&&!args[i].contains(".csv")&&!args[i].contains(".svg")&&!args[i].contains(".xml")&&!args[i].contains("-nofeedback")&&!args[i].contains("-help")&&!args[i].equals("-aide")
						&&!args[i].equals("-about")&&!args[i].equals("-nonote") &&!args[i].equals("-dest")&&!args[i].equals("-sujet")&&!args[i].equals("-nologo")
						&&!args[i].equals("-licence")&&!args[i].equals("-zipfeedback")&&!args[i].equals("-newLogo")&&!args[i].equals("-f")&&!m.find()&&!args[i].equals("-writefiles")
						&&!args[i].equals("-hash")&&!m.find()) {
					badCommand=true; System.out.println("the command " + args[i] + " is unknown.");System.out.println("You can type the -help command to get help.");
				}
			}
		}catch (ArrayIndexOutOfBoundsException e){
	        System.out.println("Error...bad argument.");
	        clotureWithError();
	        System.exit(0);
	 }
		
		
		if(badCommand) clotureWithError();

		//affichage dans la console des commandes passées 
		CLS();
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("AnalyseWriter Version : " + version );
		System.out.println();
		System.out.println("Analysis of student files = " + analyse);
		System.out.println("The name of the analysis file is = " + nameSujet);
		System.out.println("Student's file in the current directory = " + fichierStudentMoodle);
		System.out.println("Zip feedback = " + zipfeedback);
		System.out.println("No feedback = " + sansFeeback);
		System.out.println("No logo = " + noLogo);
		System.out.println("New logo = " + newLogo);
		System.out.println("Name of the SVG file = " + nameSVG);
		System.out.println("No student grade = " + noNote);
		System.out.println("Writing an analysis file = "+ ecritCode);
		System.out.println("Write the subject file \"sujet.xml\" = "+ ecritSujet);
		System.out.println("Write all student's files to XML = "+ writefiles);
		System.out.println("Hash = "+ calculLeHashDuFichier);
		System.out.println("Check historics (-verif) = " + verifHisto);
		System.out.println("Check historics and write CSV file (-verifcsv) = " + verifHisto2);
		System.out.println("Export in CSV format = " + ecritNoteCSV);
		System.out.println("File containing the student identifiers provided = " + fourniCSV);
		System.out.println("Name of the CSV file containing the student identifiers = " + nameCSV);
		System.out.println("Provides a destination directory = " + fourniDossierDestination);
		System.out.println("Destination directory = " + pathDestination);
		System.out.println();
		System.out.println("Command(s) = " + Command);
		System.out.println();
		System.out.println("Current directory = " + path);
		System.out.println("---------------------------------------------------");
		System.out.println();
		
		
	}
	
	/**
	 * Message dans la console
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private static void helpFR() throws InterruptedException, IOException {
		CLS();
		System.out.println();
		System.out.println("***************************************");
		System.out.println("* LISTE DES COMMANDES D'ANALYSEWRITER * ");
		System.out.println("***************************************");
		System.out.println();
		System.out.println("List of analiseWriter commands (only in french), soon in english.");
		System.out.println();
		
		System.out.println(" -use         : \t Permet d'indiquer le fichier d'analyse.");
		System.out.println("              : \t Le fichier d'analyse (format XML) doit être placé juste après la commande.");
		System.out.println("              : \t Les fichiers des étudiants doivent être dans des dossiers nominatifs (exporter depuis moodle).");
		System.out.println("              : \t Les fichiers des étudiants doivent être au format ODF avec l'extension .odt.");
		System.out.println();
		
		System.out.println(" file.xml     : \t Le fichier d'analyse au format XML.");
		System.out.println("              : \t file.xml doit être placé juste après la commande -use.");
		System.out.println("              : \t Le fchier file.xml doit se trouver dans le dossier courant*.");
		System.out.println("              : \t Ce fichier doit être obtenu avec la commande -write.");
		System.out.println("              : \t Ce fichier doit être manuellement modifié pour l'adapter à votre analyse.");
		System.out.println();
		
		System.out.println(" -f           : \t Permet d'indiquer que tous les fichiers des étudiants");
		System.out.println("              : \t sont directement dans le dossier courant de l'application.");
		System.out.println("              : \t Par défaut les fichiers des étudiants sont dans des dossiers nominatifs.");
		System.out.println("              : \t Les fichiers des étudiants doivent être au format ODF avec l'extension .odt.");
		System.out.println();
		
		System.out.println(" -verif       : \t Permet de comparer toutes les modifications entre les historiques du suivi de modification.");
		System.out.println("              : \t Si c'est la seule commande alors il n'y a pas d'analyse, pas de note, pas de feedback.");
		System.out.println("              : \t Cette commande ne dépend pas d'un fichier d'analyse (indépendant des sujets).");
		System.out.println("              : \t Vous pouvez analyser les historiques même si vous n'avez pas de fichier d'analyse.");
		System.out.println("              : \t Dans le dossier courant*, vous trouverez le fichier Verif.xml.");
		System.out.println();
		
		System.out.println(" -cvs         : \t Permet d'importer toutes les notes dans un fichier au format CSV (séparateur le point virgule).");
		System.out.println("              : \t La commande -use file.xml doit être placé avant la commande -csv.");
		System.out.println("              : \t Le fichier généré se trouve dans le dossier courant*.");
		System.out.println("              : \t Cette commande doit être suivi d'un fichier au format CSV contenant la liste des étudiants.");
		System.out.println("              : \t Si cette commande est suivi du fichier file.csv alors récupère les identifiants des étudiants.");
		System.out.println();
		
		System.out.println(" -verifcvs    : \t Permet de comparer toutes les modifications entre les historiques du suivi de modification.");
		System.out.println("              : \t Permet d'importer toutes les notes dans un fichier au format CSV (séparateur le point virgule).");
		System.out.println("              : \t La commande -use file.xml doit être placé avant la commande -verifcsv.");
		System.out.println("              : \t Dans le dossier courant, vous trouverez le fichier Verif.xml.");
		System.out.println("              : \t La commande -verifcsv peut être suivi d'un fichier au format CSV contenant la liste des étudiants.");
		System.out.println("              : \t Si cette commande est suivi du fichier file.csv alors récupère les identifiants des étudiants.");
		System.out.println();
		
		System.out.println(" file.csv     : \t Le fichier contenant la liste des étudiants au format CSV.");
		System.out.println("              : \t Le fichier file.csv doit être placé juste après la commande -csv ou -verifcsv.");
		System.out.println("              : \t Le fichier file.csv doit se trouver dans le dossier courant*.");
		System.out.println("              : \t Le séparateur dans le fichier CSV doit être le point virgule.");
		System.out.println("              : \t Dans ce fichier, il doit y avoir les colonnes \"prenom\", \"nom\",\"identification\" et \"adresse\".");
		System.out.println("              : \t Vous pouvez modifier les noms des colonnes dans les settings du fichier d'analyse.");
		System.out.println("              : \t Vous exportez ce fichier depuis le serveur Moodle (carnet de note, exporter).");
		System.out.println();
		
		System.out.println(" -write       : \t Permet d'écrire un fichier d'analyse.");
		System.out.println("              : \t Le fichier généré se trouve dans le dossier courant*.");
		System.out.println("              : \t Le fichier généré est au format XML.");
		System.out.println("              : \t Vous devez l'adapter en modifiant le code XML pour réaliser vos propres analyses.");
		System.out.println();
		
		System.out.println(" -writefiles  : \t Permet d'écrire tous les fichiers en XML.");
		System.out.println("              : \t Les fichiers générés se trouvent dans le dossier courant*.");
		System.out.println("              : \t Les fichiers générés sont au format XML.");
		System.out.println();
		
		System.out.println(" -zipfeedback : \t Permet de générer une archive ZIP contenant les feedbacks des étudiant.");
		System.out.println("              : \t L'archive se nomme moodleFeedback.zip.");
		System.out.println("              : \t L'archive respecte le format pour l'importation des feedbacks dans Moodle.");
		System.out.println();
		
		System.out.println(" -newLogo     : \t Permet l'affichage dans les feedbacks d'un nouveau logo autre que celui de l'université de l'Artois.");
		System.out.println("              : \t Cette commande doit être suivi d'un fichier au format SVG.");
		System.out.println();
		
		System.out.println(" file.svg     : \t Le fichier au format SVG du nouveau logo.");
		System.out.println("              : \t Le fichier file.svg doit être placé juste après la commande -newLogo.");
		System.out.println("              : \t Le fichier file.svg doit se trouver dans le dossier courant*.");
		System.out.println();
		
		System.out.println(" -nofeedback  : \t Permet de ne pas générer les feedbacks pour les étudiants.");
		System.out.println("              : \t Les feedbacks sont des fichiers au format HTML.");
		System.out.println("              : \t Les feedbacks se trouvent dans le dossier courant*.");
		System.out.println();
		
		System.out.println(" -nonote      : \t Evite l'affichage dans les feedbacks de la note.");
		System.out.println("              : \t Evite de placer la note dans le nom du fichier.");
		System.out.println();
		
		System.out.println(" -noLogo      : \t Evite l'affichage dans les feedbacks du logo de l'université de l'Artois.");
		System.out.println();
		
		System.out.println(" -dest        : \t Indique le répertoire de destination des feedbacks et des fichiers XML et CSV.");
		System.out.println("              : \t Exemple : -dest \"./sortie/\" dossier de destination le répertoire \"sortie\".");
		System.out.println("              : \t Le répertoire de destination se trouve obligatoirement dans le dossier courant de l'application.");
		System.out.println("              : \t Il ne peut y avoir qu'un seul répertoire (pas de sous répertoire).");
		System.out.println("              : \t Le chemin commence obligatoirement par \"./\".");
		System.out.println("              : \t Si le nom du dossier contient le symbole $ alors il est ignoré par l'analyse.");
		System.out.println();
		
		System.out.println(" -sujet       : \t Permet de récupérer le fichier d'analyse contenant uniquement les nodes évalués.");
		System.out.println("              : \t C'est à partir du fichier d'analyse \"file.xml\" qu'est généré le fichier \"sujet.xml\".");
		System.out.println("              : \t Le fichier \"sujet.xml\" se trouve dans le dossier courant de l'application.");
		System.out.println();
		
		System.out.println(" -hash        : \t Permet calculer le hash d'un fichier.");
		System.out.println("              : \t Permet de vérifier qu'un fichier d'analyse n'a pas été mofidié.");
		System.out.println();
		
		System.out.println(" -about       : \t Affiche la version, l'auteur et la licence.");
		System.out.println();
		
		System.out.println(" -licence     : \t Affiche le texte de la licence de l'application.");
		System.out.println();
		
		System.out.println(" -help        : \t Affiche l'aide de l'application en Anglais.");
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
	private static void helpEN() throws InterruptedException, IOException {
		CLS();
		System.out.println();
		System.out.println("**************************************");
		System.out.println("*   LIST OF ANALYSEWRITER COMMANDS   * ");
		System.out.println("**************************************");
		System.out.println();
		
		System.out.println(" -use         : \t Used to indicate the analysis file.");
		System.out.println("              : \t The analysis file (XML format) must be placed just after the command.");
		System.out.println("              : \t Student files must be in named folders (export from moodle).");
		System.out.println("              : \t Student files must be in ODF format with the extension .odt.");
		System.out.println();
		
		System.out.println(" file.xml     : \t The analysis file in XML format.");
		System.out.println("              : \t file.xml must be placed just after the -use command.");
		System.out.println("              : \t The file.xml file must be located in the current folder*.");
		System.out.println("              : \t This file must be obtained with the -write command.");
		System.out.println("              : \t This file must be manually modified to adapt it to your analysis.");
		System.out.println();
		
		System.out.println(" -f           : \t Indicates that all student files are directly in the current folder.");
		System.out.println("              : \t By default student files are in named folders.");
		System.out.println("              : \t Student files must be in ODF format with the extension .odt.");
		System.out.println();
		
		System.out.println(" -verif       : \t Allows you to compare all the modifications between the modification track logs.");
		System.out.println("              : \t If this is the only command then there is no analysis, no rating, no feedback.");
		System.out.println("              : \t This command does not depend on an analysis file (independent of subjects).");
		System.out.println("              : \t You can analyze the logs even if you do not have an analysis file.");
		System.out.println("              : \t In the current folder*, you will find the \"Verif.xml\" file.");
		System.out.println();
		
		System.out.println(" -cvs         : \t Allows to import all the notes in a file in CSV format (separator the semicolon).");
		System.out.println("              : \t The -use file.xml command must be placed before the -csv command.");
		System.out.println("              : \t The generated file is located in the current folder*.");
		System.out.println("              : \t This command can be followed by a file in CSV format containing the list of students.");
		System.out.println("              : \t If this command is followed by the \"file.csv\" file then retrieves the student IDs.");
		System.out.println();
		
		System.out.println(" -verifcvs    : \t Allows you to compare all the modifications between the modification track logs.");
		System.out.println("              : \t Allows to import all the notes in a file in CSV format (separator the semicolon).");
		System.out.println("              : \t The -use file.xml command must be placed before the -verifcsv command.");
		System.out.println("              : \t In the current folder, you will find the \"Verif.xml\" file.");
		System.out.println("              : \t The -verifcsv command can be followed by a file in CSV format containing the list of students.");
		System.out.println("              : \t If this command is followed by the \"file.csv\" file then retrieves the student IDs.");
		System.out.println();
		
		System.out.println(" file.csv     : \t The file containing the list of students in CSV format.");
		System.out.println("              : \t The \"file.csv\" file must be placed just after the -csv or -verifcsv command.");
		System.out.println("              : \t The \"file.csv\" file must be in the current folder*.");
		System.out.println("              : \t The default separator in the CSV file is the semicolon.");
		System.out.println("              : \t In this file, there must be the columns \"First name\", \"Last name\", \"Identification number\"and \"Email\".");
		System.out.println("              : \t You are exporting this file from the Moodle server (gradebook, exporter).");
		System.out.println();
		
		System.out.println(" -write       : \t Allows you to write an analysis file.");
		System.out.println("              : \t The generated file is located in the current folder*.");
		System.out.println("              : \t The generated file is in XML format.");
		System.out.println("              : \t You must adapt it by modifying the XML code to perform your own analyzes.");
		System.out.println();
		
		System.out.println(" -writefiles  : \t Allows you to write all files in XML.");
		System.out.println("              : \t The generated files are located in the current folder*.");
		System.out.println("              : \t The generated files are in XML format.");
		System.out.println();
		
		System.out.println(" -zipfeedback : \t Allows you to generate a ZIP archive containing student feedback. ");
		System.out.println("              : \t The archive is named \"moodleFeedback.zip\".");
		System.out.println("              : \t  The archive respects the format for importing feedback into Moodle.");
		System.out.println();
		
		System.out.println(" -nofeedback  : \t If TRUE allows you to not generate feedback for the students..");
		System.out.println("              : \t Feedbacks are files in HTML format..");
		System.out.println("              : \t Feedbacks can be found in the current folder*.");
		System.out.println();
		
		System.out.println(" -nonote      : \t Avoid display in the feedbacks of the grade.");
		System.out.println("              : \t Avoid placing the student's grade in the file name.");
		System.out.println();
		
		System.out.println(" -dest        : \t Specifies the destination directory for feedbacks and XML and CSV files.");
		System.out.println("              : \t Example: -dest \"./output/\" destination folder the \"output\" directory.");
		System.out.println("              : \t The destination directory must be in the current directory of the application.");
		System.out.println("              : \t There can only be one directory (no subdirectory). ");
		System.out.println("              : \t The path must begin with \"./\".");
		System.out.println("              : \t If the folder name contains the $ symbol, it is ignored by the scan. ");
		System.out.println();
		
		System.out.println(" -sujet       : \t Used to retrieve the scan file containing only the evaluated nodes. ");
		System.out.println("              : \t The \"sujet.xml\" file is generated from the \"file.xml\" analysis file. ");
		System.out.println("              : \t The \"subject.xml\" file is located in the current application folder. ");
		System.out.println();
		
		System.out.println(" -hash        : \t Allows you to calculate the hash of a file.");
		System.out.println("              : \t Allows you to verify that an analysis file has not been mofidized.");
		System.out.println();
		
		System.out.println(" -about       : \t Displays the version, author and license. ");
		System.out.println();
		
		System.out.println(" -licence     : \t Displays the text of the application license. ");
		System.out.println();
		
		System.out.println(" -aide        : \t Display application help in French.");
		System.out.println();
		
		System.out.println();
		System.out.println("---");
		System.out.println("* The current folder is the folder in which the application is located  \"analyseWriter.jar\".\n"
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
			System.out.println("\t\t│  Above, you have the app license.           │");
			System.out.println("\t\t│                                             │");
			System.out.println("\t\t│  (')_(')                                    │");
			System.out.println("\t\t│  ( `.° )                                    │");
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
		System.out.println("\t\t│  ( `.° )                                    │");
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
		System.out.println("\t\t│  ( `.° )     Well done, you've worked very hard!   │");
		System.out.println("\t\t│  (\")__(\") .. Bye Bye analyseWriter.                │");
		System.out.println("\t\t└────────────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
	}
	
	public static void clotureWithHelp() {
		System.out.println();
		System.out.println("\t\t┌─────────────────────────────────────────────────────┐");
		System.out.println("\t\t│  Above you have the help of the app.                │");
		System.out.println("\t\t│  But don't forget, you also have the documentation  │");
		System.out.println("\t\t│  on the github website. And soon in english.        │");
		System.out.println("\t\t│                                                     │");
		System.out.println("\t\t│  (')_(')                                            │");
		System.out.println("\t\t│  ( `.° )                                            │");
		System.out.println("\t\t│  (\")__(\") .. see you soon, analyseWriter.           │");
		System.out.println("\t\t└─────────────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
		System.exit(0); 
	}
	
	public static void clotureWithErrorFile(String filename) {
		int nbespace = 30-filename.length();
		if (nbespace<0) nbespace=1;
		System.out.println();
		System.out.println("\t\t┌───────────────────────────────────────────────────────────┐");
		System.out.println("\t\t│  The file \""+ filename + "\" does not exist." + new String(new char[nbespace]).replace("\0", " ") +"│" );
		System.out.println("\t\t│                                                           │");
		System.out.println("\t\t│  (')_(')                                                  │");
		System.out.println("\t\t│  ( `.° )                                                  │");
		System.out.println("\t\t│  (\")__(\") .. see you soon, analyseWriter.                 │");
		System.out.println("\t\t└───────────────────────────────────────────────────────────┘");
		System.out.println();
		System.exit(0);
	}
	
	
	
	/**
	 * Nettoyage de la console.<br/>
	 * <br/>
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void CLS() throws InterruptedException, IOException {
	   	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
}

