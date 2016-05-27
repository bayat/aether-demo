package kz.kazinfosystems.aether;

import junit.framework.TestCase;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;

public class AetherApiTest extends TestCase {
    public AetherApiTest(String testName) {
        super(testName);
    }

    public void testCheckNewVersion() {
        Artifact artifact = new DefaultArtifact("org.eclipse.aether:aether-util:[0,)");
        boolean hasNewVersion = AetherApi.hasNewVersion(artifact);
        assertTrue("There is no new version", hasNewVersion);
    }
}
