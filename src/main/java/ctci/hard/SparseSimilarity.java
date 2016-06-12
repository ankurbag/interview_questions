package ctci.hard;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 17.26 Sparse Similarity: The similarity of two documents (each with distinct words) is defined to be the size of the
 * intersection divided by the size of the union. For example, if the documents consist of integers, the similarity of
 * {1, 5, 3} and {1, 7, 2, 3} is 0.4, because the intersection has size 2 and the union has size 5.
 * <p>
 * We have a long list of documents (with distinct values and each with an associated ID) where the similarity is
 * believed to be "sparse" that is, any two arbitrarily selected documents are very likely to have similarity 0. Design
 * an algorithm that returns a list of pairs of document IDs and the associated similarity.
 * <p>
 * Print only the pairs with similarity greater than 0. Empty documents should not be printed at all. For simplicity,
 * you may assume each document is represented as an array of distinct integers.
 * <p>
 * Example
 *      Input
 *      13: {14, 15, 100, 9, 3}
 *      16: {32, 1, 9, 3, 5}
 *      19: {15, 29, 2, 6, 8, 7}
 *      24: {7, 10}
 * <p>
 * Output:
 *      ID1, ID2 : SIMILARITY
 *      13, 19 : 0.1
 *      13, 16 : 0.25
 *      19, 24 : 0.1428571428714285
 */
public class SparseSimilarity {
    private final List<Document> documents;

    public SparseSimilarity(List<Document> documents) {
        this.documents = Collections.unmodifiableList(documents);
    }

    public Map<DocPair, BigDecimal> getDocumentSimilarities() {
        Map<Integer, List<Document>> documentsByWords = new HashMap<>();
        documents.stream().forEach(doc -> doc.getWords().stream()
                .forEach(word -> documentsByWords.putIfAbsent(word, new ArrayList<>()).add(doc)));

        HashMap<DocPair, Integer> docsSimilarities = new HashMap<>();
        documentsByWords.forEach((k, v) ->
            IntStream.range(0, v.size()).forEach(i ->
                IntStream.range(i + 1, v.size()).forEach(j -> {
                    DocPair docPair = new DocPair(v.get(i), v.get(j));
                    docsSimilarities.compute(docPair, (doc, intersection) -> intersection == null ? 1 : intersection++);
                })
            )
        );

        return docsSimilarities.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, SparseSimilarity::computeSimilarity));
    }

    private static BigDecimal computeSimilarity(Map.Entry<DocPair, Integer> entry) {
        DocPair docPair = entry.getKey();
        int intersection = entry.getValue();
        int union = docPair.getDoc1().size() + docPair.getDoc2().size() - intersection;
        return new BigDecimal(String.valueOf(intersection)).divide(new BigDecimal(String.valueOf(union)));

    }

    public static class DocPair {
        private final Document doc1;
        private final Document doc2;

        public DocPair(Document doc1, Document doc2) {
            this.doc1 = doc1;
            this.doc2 = doc2;
        }

        public Document getDoc1() {
            return doc1;
        }

        public Document getDoc2() {
            return doc2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DocPair that = (DocPair) o;
            return Objects.equals(doc1, that.doc1) && Objects.equals(doc2, that.doc2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(doc1, doc2);
        }
    }

    public static class Document {
        private final int documentID;
        private final List<Integer> words;

        public Document(int documentID, List<Integer> words) {
            this.words = Collections.unmodifiableList(words);
            this.documentID = documentID;
        }

        public List<Integer> getWords() {
            return words;
        }

        public int getDocumentID() {
            return documentID;
        }

        public int size() {
            return words.size();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Document document = (Document) o;
            return documentID == document.getDocumentID();
        }

        @Override
        public int hashCode() {
            return Objects.hash(documentID);
        }
    }
}
