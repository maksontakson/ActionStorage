package app.just.actionstorage.mapper;

import java.util.UUID;

public class MappingUtils {
    public static final String GENERATE_UUID = "java(app.just.actionstorage.mapper.MappingUtils.generateUuid())";

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
