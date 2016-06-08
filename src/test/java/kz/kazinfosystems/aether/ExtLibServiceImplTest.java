package kz.kazinfosystems.aether;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import junit.framework.TestCase;
import kz.kazinfosystems.aether.api.ExtLibService;
import kz.kazinfosystems.aether.api.impl.ExtLibServiceImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ExtLibServiceImplTest extends TestCase {
    public ExtLibServiceImplTest(String testName) {
        super(testName);
    }

    public void testLoadArtifact() throws Exception {
        ExtLibService service = new ExtLibServiceImpl();
        assertTrue(service.loadArtifact("commons-io", "commons-io", "2.5", ExtLibService.TYPE_JAR, "C:/temp/myJar.jar", true));
        assertFalse(service.loadArtifact("wrong-org", "commons-io", "2.5", ExtLibService.TYPE_JAR, "C:/temp/myJar.jar", false));
    }

    public void testLoadLastVersion() throws Exception {
        ExtLibService service = new ExtLibServiceImpl();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(getClass().getClassLoader().getResourceAsStream("testData.xml"));
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("dependency");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            String org = ((DeferredElementImpl) nNode).getAttribute("org");
            String module = ((DeferredElementImpl) nNode).getAttribute("name");
            String version = service.loadLastVersion(org, module);
            System.out.println("-----------");
            System.out.printf("Version for %1s:%2s - %3s\n", org, module, version);
            System.out.println("-----------");
            assertTrue(version.length() > 0);
        }
    }


}
