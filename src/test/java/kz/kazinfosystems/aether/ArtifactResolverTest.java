package kz.kazinfosystems.aether;

import junit.framework.TestCase;

public class ArtifactResolverTest extends TestCase {
    public ArtifactResolverTest(String testName) {
        super(testName);
    }

    public void testResolveArtifacts() throws Exception {
        ArtifactResolver.resolveArtifact("commons-io", "commons-io", "sources", "2.1+");
        /*ArtifactResolver.resolveArtifact("org.jsoup", "jsoup", "1.7.2+");*/
    }

}
