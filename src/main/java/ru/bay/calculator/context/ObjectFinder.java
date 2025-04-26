package ru.bay.calculator.context;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static ru.bay.calculator.utility.CalculatorUtil.isNotDirectory;

interface ObjectFinder {
    String JAVA_EXTENSION = ".java";
    String EMPTY_STRING_FOR_REPLACEMENT = "";
    String RU_PREFIX = "ru\\";
    Character DOT_CHAR = '.';

    @SneakyThrows
    default List<Class<?>> findByCondition(String location, Predicate<Class<?>> predicate) {
        var drafts = new ArrayList<Class<?>>();
        var originalPath = Path.of(location);
        try (var stream = Files.walk(originalPath)) {
            stream.forEach(path -> processThePathAndAddToContainerByCondition(path, predicate, drafts));
        }
        return drafts;
    }

    @SneakyThrows
    private void processThePathAndAddToContainerByCondition(
            Path path,
            Predicate<Class<?>> predicate,
            List<Class<?>> container
    ) {
        if (isNotDirectory(path)) {
            var file = new File(path.toUri());
            var className = transformToClassName(file);
            var clazz = Class.forName(className);
            if (predicate.test(clazz)) container.add(clazz);
        }
    }

    private String transformToClassName(File file) {
        var fileName = file.getName().replace(JAVA_EXTENSION, EMPTY_STRING_FOR_REPLACEMENT);
        var parentPathName = file.getParent();
        var path = parentPathName.substring(parentPathName.lastIndexOf(RU_PREFIX)).replace(File.separatorChar, DOT_CHAR);
        return path + DOT_CHAR + fileName;
    }
}
