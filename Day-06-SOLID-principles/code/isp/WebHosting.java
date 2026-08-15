package isp;

public interface WebHosting { 
    void hostFrontend(); 
    
    static void runDemo() {
        WebHosting webHost = new VercelDeployment();
        webHost.hostFrontend();
    }
}

interface DatabaseHosting { void manageDatabase(); }

class VercelDeployment implements WebHosting {
    @Override
    public void hostFrontend() {
        System.out.println("ISP: Deploying Next.js frontend to Vercel... (No database overhead needed)");
    }
}