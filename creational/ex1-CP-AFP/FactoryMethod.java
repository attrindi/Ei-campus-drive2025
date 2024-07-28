// Abstract Product
interface Document {
    void open();
}

// Concrete Products
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document.");
    }
}

class PDFDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document.");
    }
}

// Creator
abstract class DocumentFactory {
    public abstract Document createDocument();
}

// Concrete Creators
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PDFDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

// Usage
public class FactoryMethod{
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PDFDocumentFactory();

        Document wordDocument = wordFactory.createDocument();
        Document pdfDocument = pdfFactory.createDocument();

        wordDocument.open();  
        pdfDocument.open();   
  }
}