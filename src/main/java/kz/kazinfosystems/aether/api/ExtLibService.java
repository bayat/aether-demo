package kz.kazinfosystems.aether.api;
/**
 * Работа с внешними библиотеками
 */
public interface ExtLibService {

    /**
     * Тип артефакта: jar-файл
     */
    String TYPE_JAR = "jar";

    /**
     * Тип артефакта: исходники
     */
    String TYPE_SRC = "src";


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
    boolean loadArtifact(String org, String module, String version, String type,
            String outFile, boolean require) throws Exception;


    /**
     * Загрузить последнюю существующую версию.
     *
     * @param org    организация
     * @param module модуль
     * @return версия
     * @throws Exception генерируются ошибка при любых проблемах
     */
    String loadLastVersion(String org, String module) throws Exception;


}
