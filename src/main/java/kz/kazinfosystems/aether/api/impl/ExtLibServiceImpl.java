package kz.kazinfosystems.aether.api.impl;

import kz.kazinfosystems.aether.api.ExtLibService;
import org.eclipse.aether.artifact.Artifact;

public class ExtLibServiceImpl implements ExtLibService {

    /**
     * Загрузить артефакт
     *
     * @param org     организация
     * @param module  модуль
     * @param version версия
     * @param type    тип артефакта (константа TYPE_xxx)
     * @param outFile полный путь выходного файла. Если каталог с файлом не существует, он
     *                будет создан
     * @param require true - артефакт должен существовать, если не существует - генерится ошибка.
     *                false - артефакт может и не существовать, в этом случае возвращается false.
     * @return false, если не существует такой артефакт
     * @throws Exception генерируются ошибка при любых проблемах, кроме описанных в параметре
     *                   require
     */
    @Override
    public boolean loadArtifact(String org, String module, String version, String type, String outFile, boolean require) throws Exception {
        Artifact artifact = null;
        try {
            artifact = ArtifactResolver.resolveArtifact(org, module, type, version, outFile);
        } catch (Exception e) {
            if (require) {
                throw new Exception("load " + org + ":" + module + " is failed");
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Загрузить последнюю существующую версию.
     *
     * @param org    организация
     * @param module модуль
     * @return версия
     * @throws Exception генерируются ошибка при любых проблемах
     */
    @Override
    public String loadLastVersion(String org, String module) throws Exception {
        try {
            return ArtifactResolver.getLastVersion(org, module);
        } catch (Exception e) {
            throw new Exception("loadLastVersion is failed", e);
        }
    }
}
