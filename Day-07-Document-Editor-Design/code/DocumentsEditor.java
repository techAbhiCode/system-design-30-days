import java.util.ArrayList;
import java.util.List;

// 1. ELEMENTS (Abstraction & Implementations)

interface DocumentElement {
    String render();
}

class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return text;
    }
}

class ImageElement implements DocumentElement {
    private String imagePath;

    public ImageElement(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String render() {
        return "[Image Rendered: " + imagePath + "]";
    }
}

// 2. STORAGE / PERSISTENCE

interface Persistence {
    void save(String content);
}

class SaveToFile implements Persistence {
    @Override
    public void save(String content) {
        System.out.println("💾 Saving document to local Text File...\nContent: \n" + content);
        System.out.println("✅ File saved successfully!");
    }
}

class SaveToDB implements Persistence {
    @Override
    public void save(String content) {
        System.out.println("🗄️ Executing SQL/NoSQL query to save document in Database...\nContent: \n" + content);
        System.out.println("✅ Saved to DB successfully!");
    }
}

// 3. CORE LOGIC (Document, Renderer, Editor)

class Document {
    private List<DocumentElement> elements;

    public Document() {
        this.elements = new ArrayList<>();
    }

    public void addElement(DocumentElement element) {
        this.elements.add(element);
    }

    public List<DocumentElement> getElements() {
        return this.elements;
    }
}

class DocumentRenderer {
    public String render(Document doc) {
        StringBuilder renderedDocument = new StringBuilder();
        
        for (DocumentElement element : doc.getElements()) {
            renderedDocument.append(element.render()).append("\n");
        }
        
        return renderedDocument.toString();
    }
}

class DocumentEditor {
    private Document doc;
    private Persistence db;

    public DocumentEditor(Document doc, Persistence db) {
        this.doc = doc;
        this.db = db;
    }

    public void addText(String text) {
        doc.addElement(new TextElement(text));
    }

    public void addImage(String path) {
        doc.addElement(new ImageElement(path));
    }
}

// 4. MAIN CLIENT (The only public class)

public class DocumentsEditor {
    public static void main(String[] args) {
        System.out.println("🚀 Initializing Google Docs LLD...\n");

        // 1. Initialize core components
        Document document = new Document();
        Persistence fileStorage = new SaveToFile(); 
        DocumentRenderer renderer = new DocumentRenderer();

        // 2. Initialize Editor with Document and Storage
        DocumentEditor editor = new DocumentEditor(document, fileStorage);

        // 3. User actions via Editor
        editor.addText("Hello World! This is an LLD for Google Docs.");
        editor.addImage("path/to/profile_pic.png");
        editor.addText("Learning SOLID Principles is fun.");

        // 4. Render the document independently
        String finalOutput = renderer.render(document);
        System.out.println("--- 📄 RENDERED DOCUMENT ---");
        System.out.println(finalOutput);
        System.out.println("----------------------------\n");

        // 5. Save the document
        fileStorage.save(finalOutput);
    }
}