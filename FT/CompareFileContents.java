import java.io.File;
import java.lang.Object;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CompareFileContents {

	public static void main(String[] args) throws IOException {

		File file1 = new File("test1.minc");
		File file2 = new File("1.txt");

		boolean compare1and2 = FileUtils.contentEquals(file1, file2);

		System.out.println("Are test1.txt and test2.txt the same? " + compare1and2);
	}

}