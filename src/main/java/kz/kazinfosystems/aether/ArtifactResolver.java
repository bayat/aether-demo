package kz.kazinfosystems.aether;

import kz.kazinfosystems.aether.util.Booter;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.VersionRangeRequest;
import org.eclipse.aether.resolution.VersionRangeResult;
import org.eclipse.aether.version.Version;

import java.util.List;

public class ArtifactResolver {
    public static void resolveArtifact(String groupId, String artifactId, String version) throws Exception {
        RepositorySystem system = Booter.newRepositorySystem();
        RepositorySystemSession session = Booter.newRepositorySystemSession(system);
        List<RemoteRepository> repositories = Booter.newRepositories(system, session);
        Artifact artifact = null;
        if (resolveNewest(version)) {
            String preparedVersion = prepareVersion(version);
            artifact = new DefaultArtifact(groupId, artifactId, "jar", preparedVersion);
            VersionRangeRequest rangeRequest = new VersionRangeRequest();
            rangeRequest.setArtifact(artifact);
            rangeRequest.setRepositories(repositories);
            VersionRangeResult rangeResult = system.resolveVersionRange(session, rangeRequest);
            Version newestVersion = rangeResult.getHighestVersion();
            artifact = artifact.setVersion(newestVersion.toString());
        } else {
            artifact = new DefaultArtifact(groupId, artifactId, "jar", version);
        }
        ArtifactRequest artifactRequest = new ArtifactRequest();
        artifactRequest.setArtifact(artifact);
        artifactRequest.setRepositories(repositories);
        system.resolveArtifact(session, artifactRequest);
    }

    private static String prepareVersion(String version) {
        return "[" + version.replace("+", ",]");
    }

    private static boolean resolveNewest(String version) {
        if (version.indexOf("+") == -1) {
            return false;
        } else {
            return true;
        }
    }
}
