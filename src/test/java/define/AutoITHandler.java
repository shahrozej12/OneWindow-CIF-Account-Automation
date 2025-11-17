package define;

import java.io.IOException;

public class AutoITHandler {

    // Existing method to close the file explorer
    public static void closeFileExplorer() throws IOException {
        String autoItScriptPath = "D:\\CIF_Image_Auto\\closeFileExplorer.exe";  // Path to the first AutoIT script
        Runtime.getRuntime().exec(autoItScriptPath);
    }

    // New method to handle SSL certificate acceptance
    public static void handleSSL() throws IOException {
        // Path to the compiled AutoIT executable for SSL certificate handling
        String autoItScriptPathForSSL = "D:\\CIF_Image_Auto\\SSL_Certificates.exe";  // Path to the second AutoIT script (handle_ssl.exe)

        // Execute the AutoIT script to accept the SSL certificate prompt
        Runtime.getRuntime().exec(autoItScriptPathForSSL);
    }
}
