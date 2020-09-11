package test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.TestBase;

public class Test_Web_Table extends TestBase {

	String beforeXpath_Word = "//table[@class='WORDLIST']/tbody/tr[";
	String afterXpath_Word = "]/td[2]";

	String beforeXpath_Definition = "//table[@class='WORDLIST']/tbody/tr[";
	String afterXpath_Definition = "]/td[3]";

	String beforeXpath_Grouping = "//table[@class='WORDLIST']/tbody/tr[";
	String afterXpath_Grouping = "]/td[4]";

	@Test
	void read_Web_Table() {

		List<WebElement> rows = driver.findElements(By.xpath("//table[@class='WORDLIST']/tbody/tr"));
		System.out.println("total number of rows are: " + (rows.size()-1));

		// int rowCount = rows.size();
		
		excel.addSheet("Vocabulary");
		excel.addColumn("Vocabulary", "Word");
		excel.addColumn("Vocabulary", "Definition");
		excel.addColumn("Vocabulary", "Grouping");

		for (int i = 2; i <= 31; i++) {

			String actualXpath_Word = beforeXpath_Word + i + afterXpath_Word;
			String Word = driver.findElement(By.xpath(actualXpath_Word)).getText();
			log.debug(Word);
			
			excel.setCellData("Vocabulary", "Word", i, Word);

			String actualXpath_Definition = beforeXpath_Definition + i + afterXpath_Definition;
			String Definition = driver.findElement(By.xpath(actualXpath_Definition)).getText();
			log.debug(Definition);
			
			excel.setCellData("Vocabulary", "Definition", i, Definition);

			String actualXpath_Grouping = beforeXpath_Grouping + i + afterXpath_Grouping;
			String Grouping = driver.findElement(By.xpath(actualXpath_Grouping)).getText();
			log.debug(Grouping);
			
			excel.setCellData("Vocabulary", "Grouping", i, Grouping);

		}

	}

}//class
