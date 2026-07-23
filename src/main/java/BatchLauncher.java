import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BatchLauncher {
    public static void main(String[] args) {
        try {
            Path jarDir = Paths.get(BatchLauncher.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getParent();
            
            File batchFile = jarDir.resolve("launch.bat").toFile();
            
            if (!batchFile.exists()) {
                System.err.println("Error: launch.bat not found in " + jarDir);
                System.exit(1);
            }
            
            ProcessBuilder pb = new ProcessBuilder(batchFile.getAbsolutePath());
            pb.directory(jarDir.toFile());
            pb.inheritIO();
            
            Process process = pb.start();
            int exitCode = process.waitFor();
            
            System.exit(exitCode);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    }
