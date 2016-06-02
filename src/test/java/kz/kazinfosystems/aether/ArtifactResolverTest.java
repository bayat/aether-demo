package kz.kazinfosystems.aether;

import junit.framework.TestCase;

public class ArtifactResolverTest extends TestCase {
    public ArtifactResolverTest(String testName) {
        super(testName);
    }

   public void testResolveArtifacts() throws Exception {
        ArtifactResolver.resolveArtifact("org.jsoup", "jsoup", "1.7.2");
        ArtifactResolver.resolveArtifact("org.jsoup", "jsoup", "1.7.2+");
    }

}
