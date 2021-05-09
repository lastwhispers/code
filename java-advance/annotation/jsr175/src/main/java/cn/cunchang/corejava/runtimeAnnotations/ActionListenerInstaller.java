package cn.cunchang.corejava.runtimeAnnotations;

import java.awt.event.*;
import java.lang.reflect.*;

/**
 * @version 1.00 2004-08-17
 * @author Cay Horstmann
 */
public class ActionListenerInstaller
{
   /**
    * Processes all ActionListenerFor annotations in the given object.
    * @param obj an object whose methods may have ActionListenerFor annotations
    */
   public static void processAnnotations(Object obj)
   {
      try
      {
         Class<?> cl = obj.getClass();
         // 获取所有方法
         for (Method m : cl.getDeclaredMethods())
         {
            ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
            if (a != null)
            {
               // 获取方法上的注解，ActionListenerFor 里面的 source 值
               Field f = cl.getDeclaredField(a.source());
               f.setAccessible(true);
               addListener(f.get(obj), obj, m);
            }
         }
      }
      catch (ReflectiveOperationException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Adds an action listener that calls a given method.
    * @param source the event source to which an action listener is added
    * @param param the implicit parameter of the method that the listener calls
    * @param m the method that the listener calls
    */
   public static void addListener(Object source, final Object param, final Method m)
         throws ReflectiveOperationException
   {
      InvocationHandler handler = new InvocationHandler()
         {
            public Object invoke(Object proxy, Method mm, Object[] args) throws Throwable
            {
               return m.invoke(param);
            }
         };

      Object listener = Proxy.newProxyInstance(null,
            new Class[] { ActionListener.class }, handler);
      Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
      adder.invoke(source, listener);
   }
}
