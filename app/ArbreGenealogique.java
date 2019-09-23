package app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import utilitaires.ArbreBinaireDerive;
import utilitaires.Personne;

public class ArbreGenealogique implements Serializable
{
	private static final long serialVersionUID = 5540367888891610814L;
	private ArbreBinaireDerive arbre = new ArbreBinaireDerive();
	private Set<Personne> personneSet;

	public ArbreGenealogique()
	{
	}

	public void genererArbreGenealogique()
	{
		arbre.ajouter(new String("Lucie"), null);
		arbre.ajouter(new String("Christian"), null);
		arbre.ajouter(new String("Mathieu"), new String("Lucie"));
		arbre.ajouter(new String("Roxanne"), new String("Mathieu"));
		arbre.ajouter(new String("Kim"), new String("Mathieu"));
		arbre.ajouter(new String("Lyne"), new String("Lucie"));
		arbre.ajouter(new String("Maxime"), new String("Lyne"));
		arbre.ajouter(new String("Ludovic"), new String("Christian"));
		arbre.ajouter(new String("Julliette"), new String("Maxime"));
		arbre.ajouter(new String("Simon"), new String("Maxime"));
		arbre.ajouter(new String("Leo"), new String("Simon"));
		arbre.ajouter(new String("Victor"), new String("Roxanne"));
	}

	public void ajouterEnfant(String enfant, String parent)
	{
		arbre.ajouter(enfant, parent);
	}

	public List<String> getListeElement()
	{
		return arbre.getListeElement();
	}

	public Set<Personne> transformeSetPeronne()
	{
		personneSet = getListeElement().stream().map(temp ->
		{
			Personne personne = new Personne();
			personne.setPrenom(temp);
			personne.setAge((int) (Math.random() * 100));

			return personne;
		}
		).collect(Collectors.toSet());

		return personneSet;
	}

	public List<Personne> getListePersonne40ansEtMoins()
	{
		List<Personne> personneList = new ArrayList<>();

		personneList = personneSet.stream().filter(personne -> personne.getAge() <= 40).collect(Collectors.toList());

		return personneList;
	}

	public void serialize()
	{
		try (
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("ArbreGenéalogique.ser")));
				ObjectOutputStream oos = new ObjectOutputStream(bos))
		{
			oos.writeObject(arbre);
			oos.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}

	}

	public void deserialize()
	{

		Object objDeserial = null;
		try (
				BufferedInputStream bos = new BufferedInputStream(new FileInputStream(new File("ArbreGenéalogique.ser")));
				ObjectInputStream ois = new ObjectInputStream(bos))
		{
			objDeserial = ois.readObject();

		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();

		}
		this.arbre = (ArbreBinaireDerive) objDeserial;
	}

	@Override
	public String toString()
	{
		return arbre.toString();
	}
}