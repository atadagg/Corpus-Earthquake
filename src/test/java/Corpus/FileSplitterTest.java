package Corpus;

import Corpus.TurkishSplitter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;

public class FileSplitterTest {
    public static void main(String[] args) {
        try {
            // Path to your text file
            String filePath = "src/test/java/Corpus/file.txt";
            String outputPath = "src/test/java/Corpus/sentence_splitter_results.txt";

            // Check if file exists
            if (!Files.exists(Paths.get(filePath))) {
                System.err.println("Error: File does not exist at path: " + filePath);
                return;
            }

            // Create StringBuilder to store results
            StringBuilder results = new StringBuilder();

            // Read the file content
            results.append("Reading file from: ").append(filePath).append("\n");
            String content = Files.readString(Paths.get(filePath));
            results.append("File size: ").append(content.length()).append(" characters\n\n");

            // Create a TurkishSplitter instance
            TurkishSplitter splitter = new TurkishSplitter();

            // Split the text into sentences
            results.append("Starting to split text...\n");
            long startTime = System.currentTimeMillis();

            // Split by newlines first to handle paragraphs
            String[] paragraphs = content.split("\n");
            ArrayList<Sentence> allSentences = new ArrayList<>();
            
            // Process paragraphs
            for (String paragraph : paragraphs) {
                if (!paragraph.trim().isEmpty()) {
                    var sentences = splitter.split(paragraph);
                    allSentences.addAll(sentences);
                }
            }

            long endTime = System.currentTimeMillis();

            // Add statistics to results
            results.append("\nProcessing completed in ").append(endTime - startTime).append(" ms\n");
            results.append("Total sentences found: ").append(allSentences.size()).append("\n");
            results.append("Total paragraphs: ").append(paragraphs.length).append("\n");
            
            // Add content statistics
            results.append("\nContent Statistics:\n");
            results.append("Number of periods (.): ").append(countChar(content, '.')).append("\n");
            results.append("Number of question marks (?): ").append(countChar(content, '?')).append("\n");
            results.append("Number of exclamation marks (!): ").append(countChar(content, '!')).append("\n");
            results.append("Number of newlines: ").append(countChar(content, '\n')).append("\n");
            
            // Calculate and add average words per sentence
            int totalWords = 0;
            for (Sentence sentence : allSentences) {
                totalWords += sentence.wordCount();
            }
            results.append("Average words per sentence: ").append(totalWords / (double)allSentences.size()).append("\n");

            // Add all sentences
            results.append("\nAll Split Sentences:\n");
            results.append("===================\n\n");
            for (int i = 0; i < allSentences.size(); i++) {
                results.append((i+1)).append(". ").append(allSentences.get(i)).append("\n\n");
            }

            // Write results to file
            Files.writeString(Paths.get(outputPath), results.toString());
            System.out.println("Results have been written to: " + outputPath);

        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error processing text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int countChar(String text, char c) {
        return (int) text.chars().filter(ch -> ch == c).count();
    }
}