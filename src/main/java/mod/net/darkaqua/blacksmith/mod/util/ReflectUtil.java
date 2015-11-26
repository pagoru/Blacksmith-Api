package net.darkaqua.blacksmith.mod.util;

import net.darkaqua.blacksmith.api.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

	public static void debugFields(Class<?> clazz){
		Field f = null;
		try {
			f = clazz.getDeclaredField("defaultResourcePacks");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < clazz.getFields().length; i++){

			if(f == clazz.getDeclaredFields()[i])
			Log.debug(clazz.getDeclaredFields()[i].getName()+" / "+i);
		}
	}
	
	public static Object getValue(Class<?> clazz, String field, Object instance){
		try {
			Field f = clazz.getDeclaredField(field);
			if(f == null)return  null;
			f.setAccessible(true);
			return f.get(instance);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object getValue(Class<?> clazz, int field, Object instance){
		try {
			Field f = clazz.getFields()[field];
			f.setAccessible(true);
			return f.get(instance);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
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

