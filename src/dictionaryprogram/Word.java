package dictionaryprogram;

/** 
* Word la chuong trinh dinh nghia 1 tu trong tu dien
* @author Dat va HaiDB :v
* @version 1.0
* @since 2018 - 09 - 25
*/

public class Word implements Comparable<Word>
{
    /**
     * target : tu moi
     * explain : giai nghia
     */
	String word_target;
	String word_explain;
	
	public Word()
	{

	}

	/**
	 * ham constructor 2 doi tuong target va explain
	 * @param w tham
	 */
	public Word(Word w)
	{
		this.word_target = w.word_target;
		this.word_explain = w.word_explain;
	}

	public Word(String wtarget, String wexplain)
	{
		this.word_target = wtarget;
		this.word_explain = wexplain;
	}

	public int compareTo(Word w)
	{
		return this.word_target.compareTo(w.word_target);
	}
}