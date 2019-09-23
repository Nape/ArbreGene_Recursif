package utilitaires;

import java.io.Serializable;


public class NoeudArbreDerive <T>implements Serializable
{
	private static final long serialVersionUID = 4207817224478742118L;
	private T element; // donnee
	private NoeudArbreDerive<T> fils; // premier fils
	private NoeudArbreDerive<T> frere; // frere

	/**
	 * Constructeur avec uniquement la donne comme parametre
	 *
	 * @param unElement l'objet a mettre dans le noeud
	 */
	NoeudArbreDerive(T unElement)
	{
		this(unElement, null, null);
	}

	/**
	 * Constructeur avec la donne, le noeud gauche et droit de l'arbre
	 *
	 * @param unElement l'objet a mettre dans le noeud
	 * @param fils      la valeur du pointeur fils
	 * @param frere     la valeur du pointeur frere
	 */
	NoeudArbreDerive(T unElement, NoeudArbreDerive<T> fils, NoeudArbreDerive<T> frere) throws RuntimeException
	{
		boolean ok = this.setElement(unElement) && this.setFils(fils) && this.setFrere(frere);

		if (!ok)
			throw new RuntimeException();
	}

	/**
	 * Determine si un noeud est une feuille ou non
	 *
	 * @return boolean, vrai si le noeud est une feuille
	 */
	public boolean estFeuille()
	{
		return ((this.fils == null) && (this.frere == null));
	}

	/**
	 * Obtenir l'element du noeud
	 *
	 * @return Object, l'objet present dans le noeud
	 */
	public T getElement()
	{
		return element;
	}

	/**
	 * Modifier l'element du noeud
	 *
	 * @param pElement la nouvelle valeur pour mettre a jour l'attribut "element"
	 */
	public boolean setElement(T pElement)
	{
		boolean ok = validerElement(pElement);

		if (ok)
		{
			this.element = pElement;
		}

		return ok;
	}

	/**
	 * Permet de valider si l'element que l'on veut mettre dans le noeud est
	 * different de nul
	 *
	 * @param pElement, l'element valider.
	 * @return boolean vrai si different de nul, faux sinon
	 */
	public boolean validerElement(T pElement)
	{
		return (pElement != null);
	}

	/**
	 * Obtenir la valeur du pointeur du noeud fils
	 *
	 * @return NoeudArbreDerive, la valeur du pointeur
	 */
	public NoeudArbreDerive<T> getFils()
	{
		return fils;
	}

	/**
	 * Modifier le pointeur sur le noeud fils
	 *
	 * @param fils la nouvelle valeur
	 */
	public boolean setFils(NoeudArbreDerive<T> fils)
	{
		this.fils = fils;

		return true;
	}

	/**
	 * Obtenir le pointeur sur le noeud frere
	 *
	 * @return NoeudArbreDerive, la valeur du pointeur frere
	 */
	public NoeudArbreDerive<T> getFrere()
	{
		return frere;
	}

	/**
	 * Modifier le pointeur sur le noeud frere
	 *
	 * @param frere la nouvelle valeur
	 */
	public boolean setFrere(NoeudArbreDerive<T> frere)
	{
		this.frere = frere;

		return true;
	}

	/**
	 * @param args test
	 */
	public static void main(String[] args)
	{

	}
}
