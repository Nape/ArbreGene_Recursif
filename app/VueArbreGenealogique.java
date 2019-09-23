package app;

/**
 * Classe Interface
 * Cette classe Créer l'interface
 * 
 */

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Classe representant l'interface principale
 */
public class VueArbreGenealogique {

	protected TabPane tabPane;
	protected Tab tabAJout, tabAffichage;
	protected HBox rangBouttonBas;
	protected VBox vboxAjout, vboxAffichage;
	protected SplitPane splitPane1;
	protected Button btnGenererArbre, btnSerialiser, btnDeserialiser, btnQuitter, btnAjouter, btnAfficherArbre, btnAfficherList,
			btnAfficherPersonne, btnAfficherMoins40;
	protected Scene scene;
	protected BorderPane root;
	protected VBox zoneDroite, zoneCentre, zoneHaut;
	protected TextArea zoneTexte;
	protected TextField ajouterParent, ajouterEnfant;

	/**
	 * Constructeur pour l'interface graphique. Instancie et initialise
	 * l'ensemble des composants pour que l'interface soit prête pour
	 * l'affichage.
	 */
	public VueArbreGenealogique() {
		creerZoneCentre();
		creerZoneBoutons();
		creerZoneDroite();
		root = new BorderPane();
		root.setTop(zoneHaut);
		root.setCenter(zoneCentre);
		root.setBottom(rangBouttonBas);
		root.setRight(zoneDroite);
		scene = new Scene(root);
	}

	/**
	 * Methode qui s'occupe de construire le rang des boutons qui seront
	 * ensuite placer dans le root en bas contient 3 boutons serialiser ,
	 * deserialiser et quitter
	 */
	private void creerZoneBoutons() {

		// Initialisation et alignement des differents items de la partie du bas
		rangBouttonBas = new HBox(25);
		rangBouttonBas.setPadding(new Insets(22, 0, 20, 50));
		btnGenererArbre =  new Button("Generer");
		btnSerialiser = new Button("Serialiser");
		btnDeserialiser = new Button("Deserialiser");
		btnQuitter = new Button("Quitter");

		btnSerialiser.setPrefSize(120, 25);
		btnDeserialiser.setPrefSize(120, 25);
		btnQuitter.setPrefSize(120, 25);

		btnGenererArbre.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnSerialiser.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnDeserialiser.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnQuitter.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");

		// Ajout des items du bas dans leur emplacement respectif dans la
		// fenetre
		rangBouttonBas.getChildren().addAll(btnGenererArbre,btnSerialiser, btnDeserialiser, btnQuitter);
	}

	/**
	 * Methode qui s'occupe de construire la partie du centre de l'interface,
	 * contenant un TextArea pour mettre les informations sur l'arbre
	 * genealogique.
	 */
	private void creerZoneCentre() {
		zoneCentre = new VBox();
		zoneTexte = new TextArea();
		zoneTexte.setEditable(false);
		zoneTexte.setMinHeight(575);
		zoneTexte.setMaxWidth(300);
		zoneTexte.setFont(Font.font(14));
		zoneTexte.setOpacity(0.8);
		zoneCentre.getChildren().add(zoneTexte);
	}

	/**
	 * Methode qui s'occupe de construire la partie de droite de l'interface,
	 * contenant un VBox qui contient un TabPane avec deux Tab un pour l'ajout
	 * d'un enfant et un autre pour les differents affichages.
	 */
	private void creerZoneDroite() {
		tabAJout = new Tab();
		tabAJout.setText("Ajouter une personne");

		Label lblAjouterEnfant = new Label("Nom de l'enfant :");
		lblAjouterEnfant.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		ajouterEnfant = new TextField();

		Label lblAjouterParent = new Label("Nom du parent :");
		lblAjouterParent.setFont(Font.font("Arial", FontWeight.BOLD, 13));

		ajouterParent = new TextField();

		btnAjouter = new Button("Ajouter");
		btnAjouter.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");

		btnAjouter.setPrefSize(80, 25);

		vboxAjout = new VBox(10);
		vboxAjout.setPadding(new Insets(10, 10, 10, 10));
		vboxAjout.getChildren().addAll(lblAjouterEnfant, ajouterEnfant, lblAjouterParent, ajouterParent, btnAjouter);

		tabAJout.setContent(vboxAjout);

		tabAffichage = new Tab();
		tabAffichage.setText("Afficher");

		vboxAffichage = new VBox(10);
		vboxAffichage.setPadding(new Insets(10, 10, 10, 10));

		btnAfficherArbre = new Button("Arbre");
		btnAfficherList = new Button("Liste");
		btnAfficherPersonne = new Button("Liste Personne");
		btnAfficherMoins40 = new Button("Liste Personne moins 40 ans");

		btnAfficherArbre.setPrefSize(200, 25);
		btnAfficherArbre.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnAfficherArbre.setAlignment(Pos.CENTER);

		btnAfficherList.setPrefSize(200, 25);
		btnAfficherList.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnAfficherList.setAlignment(Pos.CENTER);

		btnAfficherPersonne.setPrefSize(200, 25);
		btnAfficherPersonne.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnAfficherPersonne.setAlignment(Pos.CENTER);

		btnAfficherMoins40.setPrefSize(200, 25);
		btnAfficherMoins40.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 )");
		btnAfficherMoins40.setAlignment(Pos.CENTER);

		btnAfficherArbre.setDisable(true);
		btnAfficherList.setDisable(true);
		btnAfficherMoins40.setDisable(true);
		btnAfficherPersonne.setDisable(true);

		vboxAffichage.getChildren().addAll(btnAfficherArbre, btnAfficherList, btnAfficherPersonne, btnAfficherMoins40);
		vboxAffichage.setAlignment(Pos.CENTER);

		tabAffichage.setContent(vboxAffichage);

		tabPane = new TabPane();
		tabPane.getTabs().add(tabAJout);
		tabPane.getTabs().add(tabAffichage);
		zoneDroite = new VBox();
		zoneDroite.getChildren().add(tabPane);
	}

	/**
	 * Methode qui permet de rendre tous les boutons et MenuItem en lien avec
	 * l'affichage utilisable apres que l'arbre soit genere ou deserialiser
	 */
	protected void activerAffichage() {
		btnAfficherArbre.setDisable(false);
		btnAfficherList.setDisable(false);
		btnAfficherMoins40.setDisable(false);
		btnAfficherPersonne.setDisable(false);
	}
}