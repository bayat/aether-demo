package kz.kazinfosystems.aether;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 3 || args.length != 4) {
            throw new Exception("Неправильный формат записи");
        }
        ArtifactResolver.resolveArtifact(args[0], args[1], args[2], args[3]);
    }
}
