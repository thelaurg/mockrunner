/*
 * Generated by XDoclet - Do not edit!
 */
package com.mockrunner.example.ejb.interfaces;

/**
 * Remote interface for DBStateful.
 */
public interface DBStateful
   extends javax.ejb.EJBObject
{

   void beginTransaction()
      throws java.rmi.RemoteException;

   void executeSQL(java.lang.String sql)
      throws java.rmi.RemoteException;

   void endTransaction(boolean commit)
      throws java.rmi.RemoteException;

}
