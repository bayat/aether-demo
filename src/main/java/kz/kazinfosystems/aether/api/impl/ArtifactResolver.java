package kz.kazinfosystems.aether.api.impl;

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
    public static Artifact resolveArtifact(String groupId, String artifactId, String classifier, String version) throws Exception {
        RepositorySystem system = Booter.newRepositorySystem();
        RepositorySystemSession session = Booter.newRepositorySystemSession(system);
        List<RemoteRepository> repositories = Booter.newRepositories(system, session);

        Artifact artifact = new DefaultArtifact(groupId, artifactId, classifier.equalsIgnoreCase(ExtLibServiceImpl.TYPE_JAR) ? "" : classifier, "jar", version);
        ArtifactRequest artifactRequest = new ArtifactRequest();
        artifactRequest.setArtifact(artifact);
        artifactRequest.setRepositories(repositories);
        system.resolveArtifact(session, artifactRequest);
        return artifactRequest.getArtifact();
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

    public static String getLastVersion(String org, String module) throws Exception {
        RepositorySystem system = Booter.newRepositorySystem();
        RepositorySystemSession session = Booter.newRepositorySystemSession(system);
        Artifact artifact = new DefaultArtifact(org, module, "", "[,]");
        VersionRangeRequest rangeRequest = new VersionRangeRequest();
        rangeRequest.setArtifact(artifact);
        rangeRequest.setRepositories(Booter.newRepositories(system, session));
        VersionRangeResult rangeResult = system.resolveVersionRange(session, rangeRequest);
        Version lastVersion = rangeResult.getHighestVersion();
        return lastVersion.toString();
    }
}
