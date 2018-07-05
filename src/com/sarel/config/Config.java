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
            String PropertyFullName =object.getClass().getDeclaredFields()[i].toString();
            String PropertyName =object.getClass().getDeclaredFields()[i].toString().substring(PropertyFullName.lastIndexOf(".")+1);
            String setMethodName="set"+PropertyName.substring(0,1).toUpperCase()+PropertyName.substring(1);
            if (props.getProperty(PropertyName) !=null)
            {
                String myProperty=props.getProperty(PropertyName);
                try {
                    if(PropertyFullName.toLowerCase().contains("boolean")){
                        Method setMethod = object.getClass().getMethod(setMethodName,boolean.class);
                        if(String.valueOf(myProperty).equals("1")||Boolean.valueOf(myProperty)){
                            setMethod.invoke(object,true);
                        }else {
                            setMethod.invoke(object,false);
                        }                    }
                    else if(PropertyFullName.toLowerCase().split(" ")[0].equals("java.lang.string")){
                        Method setMethod = object.getClass().getMethod(setMethodName,String.class);
                        setMethod.invoke(object,new Object[] {myProperty});
                    }
                    else if(PropertyFullName.toLowerCase().split(" ")[0].equals("float")){
                        Method setMethod = object.getClass().getMethod(setMethodName,float.class);
                        setMethod.invoke(object,new Object[] {Float.valueOf(myProperty)});
                    }
                    else if(PropertyFullName.toLowerCase().split(" ")[0].equals("int")){
                        Method setMethod = object.getClass().getMethod(setMethodName,int.class);
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
