package showfolders;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class ShowFoldersDemoTest {

    @Test
    public void testShowMeDirectoriesPrintsPaths() throws Exception {
        Path root = Files.createTempDirectory("demo");
        Path sub = Files.createDirectory(root.resolve("sub"));
        Path nested = Files.createDirectory(sub.resolve("nested"));
        Path file1 = Files.createFile(sub.resolve("file1.txt"));
        Path file2 = Files.createFile(nested.resolve("file2.txt"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try {
            File[] files = root.toFile().listFiles();
            assertDoesNotThrow(() -> ShowFoldersDemo.showMeDirectories(files));
        } finally {
            System.setOut(original);
        }

        String output = out.toString();
        assertTrue(output.contains("DIR:" + sub.toFile().getAbsolutePath()));
        assertTrue(output.contains("DIR:" + nested.toFile().getAbsolutePath()));
        assertTrue(output.contains(file1.toFile().getAbsolutePath()));
        assertTrue(output.contains(file2.toFile().getAbsolutePath()));
    }
}
