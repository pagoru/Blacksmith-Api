package net.darkaqua.blacksmith.mod.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

	
	public static Object getValue(Class<?> clazz, String field, Object instance){
		try {
			Field f = clazz.getField(field);
			f.setAccessible(true);
			return f.get(instance);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object callMethod(Class<?> clazz, String method, Object instance, Class<?>[] params, Object[] args){
		try {
			Method f = clazz.getMethod(method, params);
			f.setAccessible(true);
			return f.invoke(instance, args);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}

