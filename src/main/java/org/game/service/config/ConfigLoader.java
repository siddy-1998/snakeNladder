package org.game.service.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {

    public Map<String, Object> loadConfig(String fileName) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("[ERROR] The specified file does not exist in the classpath: " + fileName);
        }

        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }
}
