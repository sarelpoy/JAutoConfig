package com.sarel.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Config {

    public static void ConfigObject (String configFilePath,Object object) throws IOException {
        Properties props = new Properties();
        FileInputStream input = new FileInputStream(configFilePath);
        props.load(input);

        for (int i=0;i<object.getClass().getDeclaredFields().length;i++) {
            String PropertyName =object.getClass().getDeclaredFields()[i].getName();
            String setMethodName="set"+PropertyName.substring(0,1).toUpperCase()+PropertyName.substring(1);
            if (props.getProperty(PropertyName) !=null)
            {
                String myProperty=props.getProperty(PropertyName);
                try {

                    Class<?> type = object.getClass().getDeclaredFields()[i].getType();
                    Method setMethod = object.getClass().getMethod(setMethodName,type);

                    if(type.equals(boolean.class)){
                        if(String.valueOf(myProperty).equals("1")||Boolean.valueOf(myProperty)){
                            setMethod.invoke(object,true);
                        }else {
                            setMethod.invoke(object,false);
                        }
                    }
                    else if(type.equals(String.class)){
                        setMethod.invoke(object,new Object[] {myProperty});
                    }
                    else if(type.equals(float.class)){
                        setMethod.invoke(object,new Object[] {Float.valueOf(myProperty)});
                    }
                    else if(type.equals(int.class)){
                        setMethod.invoke(object,new Object[] {Integer.valueOf(myProperty)});
                    }


                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
