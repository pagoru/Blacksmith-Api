package net.darkaqua.blacksmith.api.config.util;

import net.darkaqua.blacksmith.api.config.IConfiguration;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cout970 on 09/01/2016.
 */
public class ConfigHandler {

    protected Object instance;
    protected IConfiguration config;
    protected List<FieldWrapper> wrappers;

    public ConfigHandler(Object instance, IConfiguration config) {
        this.instance = instance;
        this.config = config;
        if (instance == null)
            throw new NullPointerException();
        loadFields();
    }

    public Object getInstance() {
        return instance;
    }

    public IConfiguration getConfig() {
        return config;
    }

    public void save(){
        if (config.hasChanged()){
            config.save();
        }
    }

    public void read(){
        for(FieldWrapper fw : wrappers){
            try{
                fw.read(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected void loadFields(){
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        wrappers = new LinkedList<>();

        for(Field f : fields){
            if(f.isAnnotationPresent(ConfigValue.class)){
                f.setAccessible(true);
                Class type = f.getType();
                ConfigValue annotation = f.getAnnotation(ConfigValue.class);
                FieldWrapper wrapper = null;
                if(type == Integer.TYPE){
                    wrapper = new IntegerFieldWrapper(f, annotation);
                }else if(type == Double.TYPE){
                    wrapper = new DoubleFieldWrapper(f, annotation);
                }else if(type == Boolean.TYPE){
                    wrapper = new BooleanFieldWrapper(f, annotation);
                }else if(type == String.class){
                    wrapper = new StringFieldWrapper(f, annotation);
                }
                if (wrapper != null){
                    wrappers.add(wrapper);
                }
            }
        }
    }

    public static abstract class FieldWrapper {

        protected Field field;
        protected ConfigValueType type;
        protected ConfigValue annotation;

        public FieldWrapper(Field field, ConfigValue annotation, ConfigValueType type) {
            this.field = field;
            this.type = type;
            this.annotation = annotation;
        }

        public Field getField() {
            return field;
        }

        public ConfigValue getAnnotation() {
            return annotation;
        }

        public ConfigValueType getType() {
            return type;
        }

        protected String getKey(){
            if(annotation.key().equals("")){
                return field.getName();
            }
            return annotation.key();
        }

        public abstract void read(ConfigHandler handler) throws IllegalAccessException;
    }

    public static class IntegerFieldWrapper extends FieldWrapper{

        public IntegerFieldWrapper(Field field, ConfigValue annotation) {
            super(field, annotation, ConfigValueType.INT);
        }

        @Override
        public void read(ConfigHandler handler) throws IllegalAccessException {
            int val = handler.config.getInteger(annotation.category(), getKey(), field.getInt(handler.instance), annotation.comment());
            field.set(handler.instance, val);
        }
    }

    public static class DoubleFieldWrapper extends FieldWrapper{

        public DoubleFieldWrapper(Field field, ConfigValue annotation) {
            super(field, annotation, ConfigValueType.DOUBLE);
        }

        @Override
        public void read(ConfigHandler handler) throws IllegalAccessException {
            double val = handler.config.getDouble(annotation.category(), getKey(), field.getDouble(handler.instance), annotation.comment());
            field.set(handler.instance, val);
        }
    }

    public static class BooleanFieldWrapper extends FieldWrapper{

        public BooleanFieldWrapper(Field field, ConfigValue annotation) {
            super(field, annotation, ConfigValueType.BOOLEAN);
        }

        @Override
        public void read(ConfigHandler handler) throws IllegalAccessException {
            boolean val = handler.config.getBoolean(annotation.category(), getKey(), field.getBoolean(handler.instance), annotation.comment());
            field.set(handler.instance, val);
        }
    }

    public static class StringFieldWrapper extends FieldWrapper{

        public StringFieldWrapper(Field field, ConfigValue annotation) {
            super(field, annotation, ConfigValueType.STRING);
        }

        @Override
        public void read(ConfigHandler handler) throws IllegalAccessException {
            String val = handler.config.getString(annotation.category(), getKey(), (String)field.get(handler.instance), annotation.comment());
            field.set(handler.instance, val);
        }
    }
}
