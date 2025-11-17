package define;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class VideoRecorder {

    private Process ffmpegProcess;
    private boolean isRecording = false;

    /**
     * Start recording the desktop to the given file path
     */
    public void startRecording(String filePath) throws IOException {
        // Ensure output folder exists
        File outputFile = new File(filePath).getParentFile();
        if (!outputFile.exists()) outputFile.mkdirs();

        String ffmpegPath = "D:\\Automation\\ffmpeg-master-latest-win64-gpl-shared\\bin\\ffmpeg.exe";

        // FFmpeg command for screen recording
        String ffmpegCommand = ffmpegPath
                + " -y -f gdigrab -framerate 30 -i desktop -c:v libx264 -crf 18 -preset ultrafast -pix_fmt yuv420p "
                + filePath;

        // Start FFmpeg process
        ffmpegProcess = Runtime.getRuntime().exec(ffmpegCommand);

        // Capture standard output
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(ffmpegProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("FFmpeg Output: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Capture error output
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(ffmpegProcess.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println("FFmpeg Error: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        isRecording = true;
    }

    /**
     * Stop recording gracefully by sending 'q' to FFmpeg process
     */
    public void stopRecording() {
        if (ffmpegProcess != null && isRecording) {
            try (Writer writer = new OutputStreamWriter(ffmpegProcess.getOutputStream())) {
                writer.write("q\n");  // Send 'q' to stop FFmpeg gracefully
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ffmpegProcess.waitFor(); // Wait for FFmpeg to finalize the file
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            isRecording = false;
            System.out.println("FFmpeg recording stopped successfully.");
        }
    }

    public boolean isRecording() {
        return isRecording;
    }

    public boolean isProcessAlive() {
        return ffmpegProcess != null && ffmpegProcess.isAlive();
    }
}
