package pl.florsoft.puzzles.other.sortbigfile;

import pl.florsoft.puzzles.other.sortbigfile.testimpl.OutputChecker;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.RandomLongGenerator;
import pl.florsoft.puzzles.other.sortbigfile.testimpl.TestBufferManager;
import pl.florsoft.puzzles.algorithms.sorting.HeapSort;

/**
 * Task: write a method to sort big file (bigger than available memory).
 */
public class SortBigFile {

    /**
     * @param maxMemUsage - max memory in bytes
     */
    public void sortLargeFileOfInt64s(Reader<Long> inputReader, Writer<Long> outputWriter, BufferManager<Long> bufferManager,
                                      long maxMemUsage) {
        int sortedTmpFiles = writeSortedDataToOutput(inputReader, bufferManager, maxMemUsage, outputWriter);
        mergeAndWriteToOutput(sortedTmpFiles, outputWriter, bufferManager);
    }

    private int writeSortedDataToOutput(Reader<Long> inputReader, BufferManager<Long> bufferManager,
                                        long maxMemUsage, Writer<Long> outputWriter) {
        int maxElemsInOneFile = (int) (maxMemUsage / (Long.SIZE / Byte.SIZE));
        long[] tmpBuffer = new long[maxElemsInOneFile];
        Long currentVal;
        int valCnt = 0, files = 0;
        while ((currentVal = inputReader.read()) != null) {
            tmpBuffer[valCnt++] = currentVal;
            if (valCnt == maxElemsInOneFile) {
                saveToSortedFile(tmpBuffer, valCnt, bufferManager);
                files++;
                valCnt = 0;
            }
        }
        if (valCnt > 0) {
            if (files == 0) { // sort and write to outputWriter: there is no need to use tmp files
                saveToSortedOutput(tmpBuffer, valCnt, outputWriter);
                return 0;
            } else {
                saveToSortedFile(tmpBuffer, valCnt, bufferManager);
                files++;
            }
        }
        bufferManager.markAsEndPhaseWriting();
        return files;
    }

    private void saveToSortedFile(long[] tmpBuffer, int valsToSort, BufferManager<Long> bufferManager) {
        HeapSort.sort(tmpBuffer, 0, valsToSort);
        BufferWriter<Long> bufferWriter = bufferManager.getBufferWriter(false);
        for (int i = 0; i < valsToSort; i++) {
            bufferWriter.write(tmpBuffer[i]);
        }
        bufferWriter.endWriting();
    }

    private void saveToSortedOutput(long[] tmpBuffer, int valsToSort, Writer<Long> outputWriter) {
        HeapSort.sort(tmpBuffer, 0, valsToSort);
        for (int i = 0; i < valsToSort; i++) {
            outputWriter.write(tmpBuffer[i]);
        }
    }

    private void mergeAndWriteToOutput(int files, Writer<Long> outputWriter, BufferManager<Long> bufferManager) {
        if (files == 0) {
            return;
        } else if (files <= 2) {
            writeToOutput(outputWriter, bufferManager.getBufferReader(1), bufferManager.getBufferReader(2));
            return;
        }
        int currentFile = 1, outputFiles = 0;
        while (files > 0) {
            if (files == 1) {
                bufferManager.moveReaderToNextPhase(currentFile);
                files--;
            } else {
                BufferWriter<Long> output = bufferManager.getBufferWriter(true);
                BufferReader<Long> firstReader = bufferManager.getBufferReader(currentFile++);
                BufferReader<Long> secondReader = bufferManager.getBufferReader(currentFile++);
                writeToOutput(output, firstReader, secondReader);
                output.endWriting();
                files -= 2;
            }
            outputFiles++;
        }
        bufferManager.markAsEndPhaseWriting();
        mergeAndWriteToOutput(outputFiles, outputWriter, bufferManager);
    }

    private void writeToOutput(Writer<Long> outputWriter, BufferReader<Long> firstReader, BufferReader<Long> secondReader) {
        Long firstVal = firstReader.read();
        Long secVal = secondReader.read();
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

    public static void main(String[] args) {
        int longNumbersInBigFile = 10000000;
        long maxMemUsage = 1000000L; // 1000kB
        new SortBigFile().sortLargeFileOfInt64s(new RandomLongGenerator(longNumbersInBigFile),
                new OutputChecker(), new TestBufferManager(maxMemUsage), maxMemUsage);
    }

}
