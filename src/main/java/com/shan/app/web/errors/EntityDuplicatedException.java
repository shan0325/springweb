package com.shan.app.web.errors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.thymeleaf.util.StringUtils;

public class EntityDuplicatedException extends RuntimeException {

	public EntityDuplicatedException(Class clazz, String... searchParamsMap) {
        super(EntityDuplicatedException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }
	
	private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " duplicated for parameters " + searchParams;
    }

    private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Invalid entries");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
