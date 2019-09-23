package app;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import utilitaires.Personne;

/**
 * Classe Controleur Cette classe controle le programme
 */
public class Controleur extends Application
{
	private Stage stage;
	private VueArbreGenealogique vue;
	private ArbreGenealogique arbreGenealogique = new ArbreGenealogique();


	public static void main(String[] args)
	{
		Application.launch(args);
	}

	/**
	 * Methode qui instancie l'interface, ajoute les ecouteurs et affiche
	 * l'interface de depart
	 *
	 * @param pStage
	 */
	public void start(Stage pStage)
	{
		stage = pStage;
		vue = new VueArbreGenealogique();

		stage.setTitle("ARBRE GENEALOGIQUE");
		stage.setScene(vue.scene);
		stage.show();
		stage.setResizable(false);
		ajouterEcouteurs();
	}

	/**
	 * Methode qui permet d'ajouter un ecouteur d'evenements
	 */
	private void ajouterEcouteurs()
	{
		vue.btnGenererArbre.setOnAction(new GestionnaireBouton());
		vue.btnAjouter.setOnAction(new GestionnaireBouton());
		vue.btnDeserialiser.setOnAction(new GestionnaireBouton());
		vue.btnSerialiser.setOnAction(new GestionnaireBouton());
		vue.btnAfficherArbre.setOnAction(new GestionnaireBouton());
		vue.btnAfficherList.setOnAction(new GestionnaireBouton());
		vue.btnAfficherMoins40.setOnAction(new GestionnaireBouton());
		vue.btnAfficherPersonne.setOnAction(new GestionnaireBouton());
		vue.btnQuitter.setOnAction(new GestionnaireBouton());
	}

	/**
	 * Effectue les operations necessaires pour permettre une sortie conviviale
	 * du programme.
	 */
	public void gestionQuitter()
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ARBRE GENEALOGIQUE");
		alert.setHeaderText(null);
		alert.setContentText("Voulez-vous vraiment quitter ce programme?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
			System.exit(0);
	}

	/**
	 * Effectue les operations necessaires pour permettre l'ajout d'un enfant
	 * par le tabPane.
	 */
	private void gestionAjouterBouton()
	{
		String enfant = vue.ajouterEnfant.getText();
		String parent = vue.ajouterParent.getText();

		if (enfant.length() != 0)
		{
			if (parent.length() == 0)
				parent = null;

			arbreGenealogique.ajouterEnfant(enfant, parent);
			vue.ajouterEnfant.setText("");
			vue.ajouterParent.setText("");
		} else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("ARBRE GENEALOGIQUE");
			alert.setHeaderText(null);
			alert.setContentText("Le champ du nom de l'enfant doit être remplie même si il n'a pas de parent");
			alert.showAndWait();
		}
	}

	/**
	 * Effectue les operations necessaires pour permettre l'ajout d'un enfant
	 * par le menu.
	 */
	public void gestionAjouter()
	{

		String enfant = null;
		String parent = null;

		enfant = saisirNom("Entrez le nom de l'enfant");
		parent = saisirNom("Entrez le nom du parent");

		if (enfant != null && parent != null)
			arbreGenealogique.ajouterEnfant(enfant, parent);
	}

	/**
	 * Permet d'afficher la boite de dialog pour l'ajout d'un enfant avec le
	 * message adequat methode appele par gestionAjouter()
	 */
	public static String saisirNom(String message)
	{

		Boolean valid = false;
		String retour = null;
		Optional<String> pNom = null;

		do
		{

			TextInputDialog saisirNom = new TextInputDialog();
			saisirNom.setTitle("AJOUTER ENFANT");
			saisirNom.setHeaderText("Saisir nom");
			saisirNom.setContentText(message);
			pNom = saisirNom.showAndWait();

			if (pNom.isPresent())
			{

				valid = Personne.valideNomPrenom(pNom.get());

				if (valid)
					retour = pNom.get();


			} else if (!pNom.isPresent())
				break;

		}
		while (!valid);

		return retour;
	}

	/**
	 * Classe permettant d'implementer la methode pour l'ecoute des clics sur
	 * les sous menu de la zone de menu
	 */
	private class GestionnaireBouton implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent e)
		{
			String ch = "";
			if (e.getSource() == vue.btnAfficherArbre)
			{
				vue.zoneTexte.setText(arbreGenealogique.toString());

			}
			else if (e.getSource() == vue.btnAfficherList)
			{

				for (String string : arbreGenealogique.getListeElement())
					ch += string + "\n";

				vue.zoneTexte.setText(ch);

			}
			else if (e.getSource() == vue.btnAfficherPersonne)
			{

				for (Personne pers : arbreGenealogique.transformeSetPeronne())
					ch += "Prenom : " + pers.getPrenom() + "  age :  " + pers.getAge() + "\n";
				vue.zoneTexte.setText(ch);

			}
			else if (e.getSource() == vue.btnAfficherMoins40)
			{

				for (Personne pers : arbreGenealogique.getListePersonne40ansEtMoins())
					ch += "Prenom : " + pers.getPrenom() + "  age :  " + pers.getAge() + "\n";

				vue.zoneTexte.setText(ch);
			}
			else if (e.getSource() == vue.btnGenererArbre)
			{
				arbreGenealogique.genererArbreGenealogique();
				vue.activerAffichage();
				vue.zoneTexte.setText(arbreGenealogique.toString());
			}
			else if (e.getSource() == vue.btnSerialiser)
			{
				arbreGenealogique.serialize();
			}
			else if (e.getSource() == vue.btnDeserialiser)
			{
				arbreGenealogique.deserialize();
				vue.activerAffichage();
				vue.zoneTexte.setText(arbreGenealogique.toString());
			}
			else if (e.getSource() == vue.btnAjouter)
			{
				gestionAjouterBouton();
				vue.zoneTexte.setText(arbreGenealogique.toString());
			}
			else if (e.getSource() == vue.btnQuitter)
			{
				gestionQuitter();
			}
		}
	}
}
