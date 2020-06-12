package dictionaryprogram;

/**
* DictionaryCommandline la chuong trinh xu ly cac yeu cau
* @author Dat va HaiDB :v
* @version 10.0.8
* @since 2018 - 09 - 25
*/

import java.util.*;
import java.io.Console;

public class DictionaryCommandline
{
	/**
	 * ben duoi la ham co chuc nang hien thi kq danh sach data dictionary
	 * @param inp_list
	 */
	public void showAllWords(DictionaryManagement inp_list)
	{
		System.out.println();
		System.out.println("Cac tu trong danh sach la: ");
		int count = 0;
		for (Word w : inp_list.dic_list)
		{
			count++;
			System.out.println(count + ".\t" + w.word_target);
		}
	}

	/**
	 * ben duoi la ham dung de goi ham showAllWords va insertFromCommandline
	 * @param inp_list
	 */
	public void dictionaryBasic(DictionaryManagement inp_list)
	{
		inp_list.insertFromCommandline();
		showAllWords(inp_list);
	}

	/**
	 * ben duoi la ham de goi ham insertFromFile,
	 * showAllWords va insertFromCommandline
	 * @param inp_list
	 */
	public void dictionaryAdvanced(DictionaryManagement inp_list)
	{
		inp_list.insertFromFile();
		showAllWords(inp_list);

		int check_lookup = inp_list.dictionaryLookup("tree");
		if (check_lookup != -1)
			System.out.println(check_lookup);
		else
			System.out.println("Khong tim thay tu !");
	}

	/**
     * Hàm tìm ra các từ có bắt đầu bằng pre_word
     * Ham tim ra cac tu co bat dau bang pre_word
     * @param pre_word tu cho truoc
     * @return danh sach cac tu co bat dau bang pre_word
     */
    public ArrayList<String> dictionarySearcher(DictionaryManagement inp_list, String pre_word) {
        ArrayList<String> result = new ArrayList<String>();

        int start = Collections.binarySearch(inp_list.copy_list, pre_word);
        if (start < 0) start = - start - 1;
        int index = start;

        while (index < inp_list.copy_list.size() & inp_list.copy_list.get(index).startsWith(pre_word)) {
            result.add(inp_list.copy_list.get(index));
            index++;
        }

        return result;

    }

	/**
	 * ben duoi la ham tao ra cac lưa chon
	 * @return lua chon cua nguoi dung
	 */
	public int getChoice()
	{
		System.out.println("0. Tim tu co bat dau bang chu cho truoc.");
		System.out.println("1. Tra tu.");
		System.out.println("2. Them tu.");
		System.out.println("3. Xoa tu.");
		System.out.println("4. Xem tat ca tu.");
		System.out.println("5. Xuat tat ca tu ra file.");
		System.out.println("6. Thoat.");

		System.out.println();
		System.out.printf("Lua chon cua ban la: ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		return choice;
	}

	/**
	 * ben duoi la ham thuc hien lua chon nhap vao
	 * tu ban phim
	 * @param inp_list bien thuoc lop DictionaryManagement
	 */
	public void dictionaryRun(DictionaryManagement inp_list)
	{
		boolean dictionaryRunning = true;
		int _choice = -1;
		while (dictionaryRunning && _choice==-1)
		{
			System.out.println();
			_choice = this.getChoice();
			if (_choice == 0)
			{
				System.out.printf("Nhap tu can tim: ");
				String pre_word = inp_list.insertFromCommandline();
				System.out.println("Goi y cac tu co bat dau bang \""+pre_word+"\" la: ");
				int count = 0;
				for (String t : dictionarySearcher(inp_list, pre_word))
				{
					count++;
					System.out.println(count+".\t"+t);
				}
				System.out.println();
			}
			else
			if (_choice == 1)
			{
				System.out.printf("Nhap tu can tra: ");
				String find_word = inp_list.insertFromCommandline();
				//System.out.println("Word need to find: " + find_word);

				String explain_of_find_word = inp_list.getExplainOfWord(find_word);				
				System.out.println("Tu cua ban cho nghia la: " + explain_of_find_word);
			}
			if (_choice == 2)
			{
				System.out.printf("Tu can them: ");
				String add_target_word = inp_list.insertFromCommandline();
				System.out.printf("Nghia cua tu: ");
				String add_explain_word = inp_list.insertFromCommandline();
				Word add_word = new Word(add_target_word, add_explain_word);
				inp_list.addWord(add_word);
				inp_list.updateListFromTreeSet();
			}
			if (_choice == 3)
			{
				System.out.printf("Tu can xoa: ");
				String delete_word = inp_list.insertFromCommandline();
				inp_list.removeWord(delete_word);
				inp_list.updateListFromTreeSet();
				System.out.println("Da xoa tu: " + delete_word);
			}
			if (_choice == 4)
			{
				this.showAllWords(inp_list);
			}
			if (_choice ==  5)
			{
				inp_list.dictionaryExportToFile();
			}
			if (_choice == 6)
			{
				dictionaryRunning = false;
			}
			_choice = -1;
		}
	}

	/**
	 * main la ham xu ly cac yeu cau tren
	 * @param args khong su dung
	 */
	public static void main(String[] args) 
	{
		DictionaryManagement dic_Mana = new DictionaryManagement();
		DictionaryCommandline dic_command = new DictionaryCommandline();
		//dic_command.dictionaryAdvanced(dic_Mana);

		
		dic_Mana.insertFromFile();
		dic_Mana.updateListFromTreeSet();
		dic_command.dictionaryRun(dic_Mana);
	}
}