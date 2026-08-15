package dip;

public interface UrlStorage {
    void save(String shortUrl, String longUrl);
    
    static void runDemo() {
        UrlStorage storage = new MongoUrlStorage();
        SwiftUrlService urlService = new SwiftUrlService(storage);
        urlService.shortenAndSave("https://github.com/techAbhiCode/system-design-30-days");
    }
}

class MongoUrlStorage implements UrlStorage {
    @Override
    public void save(String shortUrl, String longUrl) {
        System.out.println("DIP: Saved " + shortUrl + " -> " + longUrl + " in MongoDB.");
    }
}

class SwiftUrlService {
    private UrlStorage storage; 
    public SwiftUrlService(UrlStorage storage) { this.storage = storage; }
    public void shortenAndSave(String longUrl) {
        String shortUrl = "swft.ly/1a2b"; 
        storage.save(shortUrl, longUrl);
    }
}