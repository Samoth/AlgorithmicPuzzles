package pl.florsoft.puzzles.other.sortbigfile;

import pl.florsoft.puzzles.other.sortbigfile.testimpl.OutputChecker;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.RandomLongGenerator;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.TestBufferManager;

import java.io.IOException;
import java.util.Arrays;

/**
 * Task: write a method to sort big file (bigger than available memory).
 */
public class SortBigFile {

    /**
     * @param maxMemUsage - max memory in bytes
     */
    public void sortLargeFileOfInt64s(Reader<Long> inputReader, Writer<Long> outputWriter, BufferManager<Long> bufferManager,
                                      long maxMemUsage) {
        int sortedTmpFiles = createSortedTmpFiles(inputReader, bufferManager, maxMemUsage);
        mergeAndWriteToOutput(sortedTmpFiles, 0, outputWriter, bufferManager);
    }

    private int createSortedTmpFiles(Reader<Long> inputReader, BufferManager<Long> bufferManager,
                                     long maxMemUsage) {
        int maxElemsInOneFile = (int) (maxMemUsage / (Long.SIZE / Byte.SIZE));
        Long[] tmpBuffer = new Long[maxElemsInOneFile];
        Long currentVal;
        int savedValsInBuffer = 0, files = 0;
        while ((currentVal = inputReader.read()) != null) {
            tmpBuffer[savedValsInBuffer++] = currentVal;
            if (savedValsInBuffer == maxElemsInOneFile) {
                saveToSortedFile(tmpBuffer, bufferManager);
                files++;
                savedValsInBuffer = 0;
            }
        }
        if (savedValsInBuffer > 0) {
            if (savedValsInBuffer < maxElemsInOneFile) {
                tmpBuffer = createSmallerBuffer(tmpBuffer, savedValsInBuffer);
            }
            saveToSortedFile(tmpBuffer, bufferManager);
            files++;
        }
        return files;
    }

    private Long[] createSmallerBuffer(Long[] tmpBuffer, int savedValsInBuffer) {
        Long[] newArray = new Long[savedValsInBuffer];
        System.arraycopy(tmpBuffer, 0, newArray, 0, savedValsInBuffer);
        return newArray;
    }

    private void saveToSortedFile(Long[] tmpBuffer, BufferManager<Long> bufferManager) {
        Arrays.sort(tmpBuffer);
        BufferWriter<Long> bufferWriter = bufferManager.getBufferWriter(0, false);
        for (Long val : tmpBuffer) {
            bufferWriter.write(val);
        }
        bufferWriter.endWriting();
    }

    private void mergeAndWriteToOutput(int files, int fileGroup, Writer<Long> outputWriter, BufferManager<Long> bufferManager) {
        if (files <= 2) {
            BufferReader<Long> firstReader = bufferManager.getBufferReader(fileGroup, 1);
            BufferReader<Long> secondReader = null;
            if (files == 2) {
                secondReader = bufferManager.getBufferReader(fileGroup, 2);
            }
            writeToOutput(outputWriter, firstReader, secondReader);
            return;
        }
        int currentFile = 0, outputFiles = 0;
        while (files > 0) {
            BufferWriter<Long> output = bufferManager.getBufferWriter(fileGroup + 1, true);
            BufferReader<Long> firstReader = bufferManager.getBufferReader(fileGroup, ++currentFile);
            BufferReader<Long> secondReader = null;
            files--;
            if (files > 0) {
                secondReader = bufferManager.getBufferReader(fileGroup, ++currentFile);
                files--;
            }
            writeToOutput(output, firstReader, secondReader);
            output.endWriting();
            outputFiles++;
        }
        mergeAndWriteToOutput(outputFiles, fileGroup + 1, outputWriter, bufferManager);
    }

    private void writeToOutput(Writer<Long> outputWriter, BufferReader<Long> firstReader, BufferReader<Long> secondReader) {
        Long firstVal = firstReader.read();
        Long secVal = secondReader != null ? secondReader.read() : null;
        while (firstVal != null || secVal != null) {
            if (firstVal != null && secVal != null) {
                if (firstVal <= secVal) {
                    outputWriter.write(firstVal);
                    firstVal = firstReader.read();
                } else {
                    outputWriter.write(secVal);
                    secVal = secondReader.read();
                }
            } else if (firstVal != null) {
                outputWriter.write(firstVal);
                firstVal = firstReader.read();
            } else {
                outputWriter.write(secVal);
                secVal = secondReader.read();
            }
        }
    }

    public static void main(String[] args){
        int longNumbersInBigFile = 10000000;
        long maxMemUsage = 1000000L; // 1000kB
        new SortBigFile().sortLargeFileOfInt64s(new RandomLongGenerator(longNumbersInBigFile),
                new OutputChecker(), new TestBufferManager(maxMemUsage), maxMemUsage);
    }

}
