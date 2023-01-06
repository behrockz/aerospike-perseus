package com.aerospike.utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class ConfigFileProvider {
    private final Path path;

    public ConfigFileProvider(String fileName, String resourceName) {
        this.path = Paths.get(fileName).toAbsolutePath();
        InputStream testThreads = ConfigFileProvider.class.getClassLoader().getResourceAsStream(resourceName);
        try {
            if(Files.exists(path))
                return;
            if(!Files.exists(path.getParent()))
                Files.createDirectory(path.getParent());

            java.nio.file.Files.copy(
                    testThreads,
                    path,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

        }
    }

    public Map<String, Object> ReadYaml() {
        Yaml yamlReader = new Yaml();
        try (var reader = new FileReader(path.toFile())){
            Map<String, Object> load = yamlReader.load(reader);
            return load;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Path getPath() {
        return path;
    }
}
