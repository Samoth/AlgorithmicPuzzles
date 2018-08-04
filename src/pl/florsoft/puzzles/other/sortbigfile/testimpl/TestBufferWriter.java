package pl.florsoft.puzzles.other.sortbigfile.testimpl;

import pl.florsoft.puzzles.other.sortbigfile.BufferWriter;
import pl.florsoft.puzzles.other.sortbigfile.ByteUtils;

import java.io.*;

public class TestBufferWriter implements BufferWriter<Long> {

    private OutputStream output;
    private int fileNumber;

    public TestBufferWriter(String fileName, int fileNumber, boolean useBuffer, long maxMemUsage) {
        this.fileNumber = fileNumber;
        try {
            File baseDir = new File(System.getProperty("java.io.tmpdir"));
            File file = new File(baseDir, fileName);
            file.deleteOnExit();
            this.output = new FileOutputStream(file);
            if (useBuffer) {
                this.output = new BufferedOutputStream(this.output, (int) Math.min(Integer.MAX_VALUE, maxMemUsage));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in TestBufferWriter(): " + e.getLocalizedMessage());
        }
    }

    @Override
    public void write(Long val) {
        try {
            output.write(ByteUtils.longToBytes(val));
        } catch (IOException e) {
            throw new RuntimeException("Error in write(): " + e.getLocalizedMessage());
        }
    }

    @Override
    public int endWriting() {
        try {
            output.flush();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in endWriting(): " + e.getLocalizedMessage());
        }
        return fileNumber;
    }

}
