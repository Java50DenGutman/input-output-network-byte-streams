package telran.io.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyAppl {

	public static void main(String[] args) {
		//args[0] - source file
		//args[2] - destination file
		//args[3] - "overwrite"
		//TODO write application for copying from source file to destination file
		//Implementation Requirement: to use while cycle with read call
		//main must not contain throws declaration
		if (args.length < 2) {
            System.out.println("Too few arguments");
            return;
        }

        String sourceFilePath = args[0];
        String destinationFilePath = args[1];
        boolean overwrite = args.length == 3 && "overwrite".equals(args[2]);

        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            System.out.printf("Source file %s must exist%n", sourceFilePath);
            return;
        }

        if (sourceFile.getParentFile() != null && !sourceFile.getParentFile().exists()) {
            System.out.printf("The directory %s must exist%n", sourceFile.getParent());
            return;
        }

        File destinationFile = new File(destinationFilePath);
        if (destinationFile.exists() && !overwrite) {
            System.out.printf("File %s cannot be overwritten%n", destinationFilePath);
            return;
        }

        if (destinationFile.getParentFile() != null && !destinationFile.getParentFile().exists()) {
            System.out.printf("The directory %s must exist%n", destinationFile.getParent());
            return;
        }

        long startTime = System.currentTimeMillis();

        try (
            InputStream input = new FileInputStream(sourceFile);
            OutputStream output = new FileOutputStream(destinationFile)
        ) {
            byte[] buffer = new byte[1024 * 1024]; // 1MB buffer
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            long endTime = System.currentTimeMillis();
            System.out.printf(
                "Successful copying of %d bytes have been copying from the file %s to the file %s. Time %d ms%n",
                sourceFile.length(), sourceFilePath, destinationFilePath, (endTime - startTime)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
