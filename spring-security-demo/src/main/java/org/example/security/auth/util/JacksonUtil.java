package org.example.security.auth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 封装了Jackson的json工具类，用于代替JSONObject
 * </p>
 *
 * @author rookie.zhang
 * @since 2020-03-13
 */

@Slf4j
public class JacksonUtil {

	public static ObjectMapper objectMapper = new ObjectMapper();


	/**
	 * Java对象转JSON字符串
	 * @param object
	 * @return
	 */
	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("The JacksonUtil toJsonString is error : \n", e);
			throw new RuntimeException();
		}
	}

	/**
	 * Java对象转JSON字符串 - 美化输出
	 * @param object
	 * @return
	 */
	public static String toJsonStringWithPretty(Object object) {
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("The JacksonUtil toJsonString is error : \n", e);
			throw new RuntimeException();
		}
	}


	/**
	 * Java对象转byte数组
	 * @param object
	 * @return
	 */
	public static byte[] toJsonBytes(Object object) {
		try {
			return objectMapper.writeValueAsBytes(object);
		} catch (JsonProcessingException e) {
			log.error("The JacksonUtil toJsonBytes is error : \n", e);
			throw new RuntimeException();
		}
	}


	/**
	 * JSON字符串转对象
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error("The JacksonUtil parseObject is error, json str is {}, class name is {} \n", json, clazz.getName(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * JSON字符串转List集合
	 * @param json
	 * @param elementClasses
	 * @param <T>
	 * @return
	 */
	@SafeVarargs
	public static <T> List<T> parseList(String json, Class<T>... elementClasses) {
		try {
			return objectMapper.readValue(json, getCollectionType(objectMapper, List.class, elementClasses));
		} catch (Exception e) {
			log.error("The JacksonUtil parseList is error, json str is {}, element class name is {} \n",
					json, elementClasses.getClass().getName(), e);
			throw new RuntimeException();
		}
	}


	/**
	 * JSON字符串转Set集合
	 * @param json
	 * @param elementClasses
	 * @param <T>
	 * @return
	 */
	@SafeVarargs
	public static <T> Set<T> parseSet(String json, Class<T>... elementClasses) {
		try {
			return objectMapper.readValue(json, getCollectionType(objectMapper, Set.class, elementClasses));
		} catch (Exception e) {
			log.error("The JacksonUtil parseSet is error, json str is {}, element class name is {} \n",
					json, elementClasses.getClass().getName(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * JSON字符串转Collection集合
	 * @param json
	 * @param elementClasses
	 * @param <T>
	 * @return
	 */
	@SafeVarargs
	public static <T> Collection<T> parseCollection(String json, Class<T>... elementClasses) {
		try {
			return objectMapper.readValue(json, getCollectionType(objectMapper, Collection.class, elementClasses));
		} catch (Exception e) {
			log.error("The JacksonUtil parseCollection is error, json str is {}, element class name is {} \n",
					json, elementClasses.getClass().getName(), e);
			throw new RuntimeException();
		}
	}

	/**
	 * 获取泛型的Collection Type
	 *
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses  元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}
