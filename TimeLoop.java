package first_java_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeLoop {
	private static final String FILE_PATH = "times.txt";

	static void diplayContent() throws IOException {
		File file = new File(FILE_PATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;

		System.out.println("Content of the file:");
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();

	}

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(() -> loopFunction(), 0, 3, TimeUnit.SECONDS); // Run the loop function with a
																					// initial delay of 0 seconds and
																					// then every 3 seconds
		try {
			Thread.sleep(30000); // Wait for 30 seconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown(); // Shutdown the executor after 30 seconds
		try {
			TimeLoop.diplayContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loopFunction() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Current date and time: " + sdf.format(date)); // Display current date and time in IST
		try {
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				file.createNewFile(); // Create the file if it doesn't exist
			}
//
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//            writer.write(sdf.format(date) + "\n");
//            writer.close();
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
				writer.append(sdf.format(date)).append("\n");
			}

//            System.out.println("Current date and time: " + formattedDate); // Display current date and time in IST
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


