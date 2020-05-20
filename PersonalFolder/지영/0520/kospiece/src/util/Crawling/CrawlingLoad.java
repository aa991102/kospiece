package util.Crawling;

import java.io.File;
import java.io.IOException;

public class CrawlingLoad {

	private static void printStream(Process process)
            throws IOException, InterruptedException {
    process.waitFor();
	}
	public static void Load() throws IOException,InterruptedException{
		String currDir = CrawlingLoad.class.getResource(".").getPath();
		System.out.println(currDir);
		String Path = currDir.substring(1)+"stock.exe";
		System.out.println(Path);
//		Process p =Runtime.getRuntime().exec(Path);
//		p.waitFor();
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", Path);
		builder.directory(new File(currDir.substring(1)));
		
		Process process = builder.start();
		process.waitFor();
//		printStream(process);
		
	}
}
