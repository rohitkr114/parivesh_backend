package com.backend.cin;
/*
 *  GstServiceStub java implementation
 */
//import org.apache.


public class GstServiceStub extends org.apache.axis2.client.Stub {
  protected org.apache.axis2.description.AxisOperation[] _operations;

  // hashmaps to keep the fault mapping
  private java.util.Map<org.apache.axis2.client.FaultMapKey, java.lang.String>
      faultExceptionNameMap =
          new java.util.HashMap<org.apache.axis2.client.FaultMapKey, java.lang.String>();
  private java.util.Map<org.apache.axis2.client.FaultMapKey, java.lang.String>
      faultExceptionClassNameMap =
          new java.util.HashMap<org.apache.axis2.client.FaultMapKey, java.lang.String>();
  private java.util.Map<org.apache.axis2.client.FaultMapKey, java.lang.String> faultMessageMap =
      new java.util.HashMap<org.apache.axis2.client.FaultMapKey, java.lang.String>();

  private static int counter = 0;

  private static synchronized java.lang.String getUniqueSuffix() {
    // reset the counter if it is greater than 99999
    if (counter > 99999) {
      counter = 0;
    }
    counter = counter + 1;
    return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
  }

  private void populateAxisService() throws org.apache.axis2.AxisFault {

    // creating the Service with a unique name
    _service = new org.apache.axis2.description.AxisService("GstService" + getUniqueSuffix());
    addAnonymousOperations();

    // creating the operations
    org.apache.axis2.description.AxisOperation __operation;

    _operations = new org.apache.axis2.description.AxisOperation[4];

    __operation = new org.apache.axis2.description.OutInAxisOperation();

    __operation.setName(new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDirectorInfo"));
    _service.addOperation(__operation);

    _operations[0] = __operation;

    __operation = new org.apache.axis2.description.OutInAxisOperation();

    __operation.setName(new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfo"));
    _service.addOperation(__operation);

    _operations[1] = __operation;

    __operation = new org.apache.axis2.description.OutInAxisOperation();

    __operation.setName(new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfo"));
    _service.addOperation(__operation);

    _operations[2] = __operation;

    __operation = new org.apache.axis2.description.OutInAxisOperation();

    __operation.setName(new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfo"));
    _service.addOperation(__operation);

    _operations[3] = __operation;
  }

  // populates the faults
  private void populateFaults() {}

  /** Constructor that takes in a configContext */
  public GstServiceStub(
      org.apache.axis2.context.ConfigurationContext configurationContext,
      java.lang.String targetEndpoint)
      throws org.apache.axis2.AxisFault {
    this(configurationContext, targetEndpoint, false);
  }

  /** Constructor that takes in a configContext and useseperate listner */
  public GstServiceStub(
      org.apache.axis2.context.ConfigurationContext configurationContext,
      java.lang.String targetEndpoint,
      boolean useSeparateListener)
      throws org.apache.axis2.AxisFault {
    // To populate AxisService
    populateAxisService();
    populateFaults();

    _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

    _serviceClient
        .getOptions()
        .setTo(new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
    _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

    // Set the soap version
    _serviceClient
        .getOptions()
        .setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
  }

  /** Default Constructor */
  public GstServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
      throws org.apache.axis2.AxisFault {

    this(
        configurationContext, "http://mca21v3services.mca.gov.in:80/MCAGstIntegration/GstService");
  }

  /** Default Constructor */
  public GstServiceStub() throws org.apache.axis2.AxisFault {

    this("http://mca21v3services.mca.gov.in:80/MCAGstIntegration/GstService");
  }

  /** Constructor taking the target endpoint */
  public GstServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
    this(null, targetEndpoint);
  }

  /**
   * Auto generated method signature
   *
   * @see com.backend.cin.GstService#getDirectorInfo
   * @param getDirectorInfo
   */
  public com.backend.cin.GstServiceStub.GetDirectorInfoResponseE getDirectorInfo(
      com.backend.cin.GstServiceStub.GetDirectorInfoE getDirectorInfo)
      throws java.rmi.RemoteException {

    org.apache.axis2.context.MessageContext _messageContext =
        new org.apache.axis2.context.MessageContext();
    try {
      org.apache.axis2.client.OperationClient _operationClient =
          _serviceClient.createClient(_operations[0].getName());
      _operationClient
          .getOptions()
          .setAction("http://www.mca.gov.in/GstServices/getDirectorInfoRequest");
      _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

      addPropertyToOperationClient(
          _operationClient,
          org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
          "&");

      // create SOAP envelope with that payload
      org.apache.axiom.soap.SOAPEnvelope env = null;

      env =
          toEnvelope(
              getFactory(_operationClient.getOptions().getSoapVersionURI()),
              getDirectorInfo,
              optimizeContent(
                  new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDirectorInfo")),
              new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDirectorInfo"));

      // adding SOAP soap_headers
      _serviceClient.addHeadersToEnvelope(env);
      // set the message context with that soap envelope
      _messageContext.setEnvelope(env);

      // add the message contxt to the operation client
      _operationClient.addMessageContext(_messageContext);

      // execute the operation client
      _operationClient.execute(true);

      org.apache.axis2.context.MessageContext _returnMessageContext =
          _operationClient.getMessageContext(
              org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
      org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
      _returnEnv.buildWithAttachments();

      java.lang.Object object =
          fromOM(
              _returnEnv.getBody().getFirstElement(),
              com.backend.cin.GstServiceStub.GetDirectorInfoResponseE.class);
      org.apache.axis2.kernel.TransportUtils.detachInputStream(_returnMessageContext);

      return (com.backend.cin.GstServiceStub.GetDirectorInfoResponseE) object;

    } catch (org.apache.axis2.AxisFault f) {

      org.apache.axiom.om.OMElement faultElt = f.getDetail();
      if (faultElt != null) {
        if (faultExceptionNameMap.containsKey(
            new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getDirectorInfo"))) {
          // make the fault by reflection
          try {
            java.lang.String exceptionClassName =
                faultExceptionClassNameMap.get(
                    new org.apache.axis2.client.FaultMapKey(
                        faultElt.getQName(), "getDirectorInfo"));
            java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
            java.lang.reflect.Constructor constructor =
                exceptionClass.getConstructor(java.lang.String.class);
            java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
            // message class
            java.lang.String messageClassName =
                faultMessageMap.get(
                    new org.apache.axis2.client.FaultMapKey(
                        faultElt.getQName(), "getDirectorInfo"));
            java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
            java.lang.Object messageObject = fromOM(faultElt, messageClass);
            java.lang.reflect.Method m =
                exceptionClass.getMethod("setFaultMessage", new java.lang.Class[] {messageClass});
            m.invoke(ex, new java.lang.Object[] {messageObject});

            throw new java.rmi.RemoteException(ex.getMessage(), ex);
          } catch (java.lang.ClassCastException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.ClassNotFoundException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.NoSuchMethodException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.reflect.InvocationTargetException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.IllegalAccessException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.InstantiationException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          }
        } else {
          throw f;
        }
      } else {
        throw f;
      }
    } finally {
      if (_messageContext.getTransportOut() != null) {
        _messageContext.getTransportOut().getSender().cleanup(_messageContext);
      }
    }
  }

  /**
   * Auto generated method signature
   *
   * @see com.backend.cin.GstService#getCINInfo
   * @param getCINInfo
   */
  public com.backend.cin.GstServiceStub.GetCINInfoResponseE getCINInfo(
      com.backend.cin.GstServiceStub.GetCINInfoE getCINInfo)
      throws java.rmi.RemoteException {

    org.apache.axis2.context.MessageContext _messageContext =
        new org.apache.axis2.context.MessageContext();
    try {
      org.apache.axis2.client.OperationClient _operationClient =
          _serviceClient.createClient(_operations[1].getName());
      _operationClient
          .getOptions()
          .setAction("http://www.mca.gov.in/GstServices/getCINInfoRequest");
      _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

      addPropertyToOperationClient(
          _operationClient,
          org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
          "&");

      // create SOAP envelope with that payload
      org.apache.axiom.soap.SOAPEnvelope env = null;

      env =
          toEnvelope(
              getFactory(_operationClient.getOptions().getSoapVersionURI()),
              getCINInfo,
              optimizeContent(
                  new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfo")),
              new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfo"));

      // adding SOAP soap_headers
      _serviceClient.addHeadersToEnvelope(env);
      // set the message context with that soap envelope
      _messageContext.setEnvelope(env);

      // add the message contxt to the operation client
      _operationClient.addMessageContext(_messageContext);

      // execute the operation client
      _operationClient.execute(true);

      org.apache.axis2.context.MessageContext _returnMessageContext =
          _operationClient.getMessageContext(
              org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
      org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
      _returnEnv.buildWithAttachments();

      java.lang.Object object =
          fromOM(
              _returnEnv.getBody().getFirstElement(),
              com.backend.cin.GstServiceStub.GetCINInfoResponseE.class);
      org.apache.axis2.kernel.TransportUtils.detachInputStream(_returnMessageContext);

      return (com.backend.cin.GstServiceStub.GetCINInfoResponseE) object;

    } catch (org.apache.axis2.AxisFault f) {

      org.apache.axiom.om.OMElement faultElt = f.getDetail();
      if (faultElt != null) {
        if (faultExceptionNameMap.containsKey(
            new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getCINInfo"))) {
          // make the fault by reflection
          try {
            java.lang.String exceptionClassName =
                faultExceptionClassNameMap.get(
                    new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getCINInfo"));
            java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
            java.lang.reflect.Constructor constructor =
                exceptionClass.getConstructor(java.lang.String.class);
            java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
            // message class
            java.lang.String messageClassName =
                faultMessageMap.get(
                    new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getCINInfo"));
            java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
            java.lang.Object messageObject = fromOM(faultElt, messageClass);
            java.lang.reflect.Method m =
                exceptionClass.getMethod("setFaultMessage", new java.lang.Class[] {messageClass});
            m.invoke(ex, new java.lang.Object[] {messageObject});

            throw new java.rmi.RemoteException(ex.getMessage(), ex);
          } catch (java.lang.ClassCastException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.ClassNotFoundException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.NoSuchMethodException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.reflect.InvocationTargetException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.IllegalAccessException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.InstantiationException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          }
        } else {
          throw f;
        }
      } else {
        throw f;
      }
    } finally {
      if (_messageContext.getTransportOut() != null) {
        _messageContext.getTransportOut().getSender().cleanup(_messageContext);
      }
    }
  }

  /**
   * Auto generated method signature
   *
   * @see com.backend.cin.GstService#getDINInfo
   * @param getDINInfo
   */
  public com.backend.cin.GstServiceStub.GetDINInfoResponseE getDINInfo(
      com.backend.cin.GstServiceStub.GetDINInfoE getDINInfo)
      throws java.rmi.RemoteException {

    org.apache.axis2.context.MessageContext _messageContext =
        new org.apache.axis2.context.MessageContext();
    try {
      org.apache.axis2.client.OperationClient _operationClient =
          _serviceClient.createClient(_operations[2].getName());
      _operationClient
          .getOptions()
          .setAction("http://www.mca.gov.in/GstServices/getDINInfoRequest");
      _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

      addPropertyToOperationClient(
          _operationClient,
          org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
          "&");

      // create SOAP envelope with that payload
      org.apache.axiom.soap.SOAPEnvelope env = null;

      env =
          toEnvelope(
              getFactory(_operationClient.getOptions().getSoapVersionURI()),
              getDINInfo,
              optimizeContent(
                  new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfo")),
              new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfo"));

      // adding SOAP soap_headers
      _serviceClient.addHeadersToEnvelope(env);
      // set the message context with that soap envelope
      _messageContext.setEnvelope(env);

      // add the message contxt to the operation client
      _operationClient.addMessageContext(_messageContext);

      // execute the operation client
      _operationClient.execute(true);

      org.apache.axis2.context.MessageContext _returnMessageContext =
          _operationClient.getMessageContext(
              org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
      org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
      _returnEnv.buildWithAttachments();

      java.lang.Object object =
          fromOM(
              _returnEnv.getBody().getFirstElement(),
              com.backend.cin.GstServiceStub.GetDINInfoResponseE.class);
      org.apache.axis2.kernel.TransportUtils.detachInputStream(_returnMessageContext);

      return (com.backend.cin.GstServiceStub.GetDINInfoResponseE) object;

    } catch (org.apache.axis2.AxisFault f) {

      org.apache.axiom.om.OMElement faultElt = f.getDetail();
      if (faultElt != null) {
        if (faultExceptionNameMap.containsKey(
            new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getDINInfo"))) {
          // make the fault by reflection
          try {
            java.lang.String exceptionClassName =
                faultExceptionClassNameMap.get(
                    new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getDINInfo"));
            java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
            java.lang.reflect.Constructor constructor =
                exceptionClass.getConstructor(java.lang.String.class);
            java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
            // message class
            java.lang.String messageClassName =
                faultMessageMap.get(
                    new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getDINInfo"));
            java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
            java.lang.Object messageObject = fromOM(faultElt, messageClass);
            java.lang.reflect.Method m =
                exceptionClass.getMethod("setFaultMessage", new java.lang.Class[] {messageClass});
            m.invoke(ex, new java.lang.Object[] {messageObject});

            throw new java.rmi.RemoteException(ex.getMessage(), ex);
          } catch (java.lang.ClassCastException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.ClassNotFoundException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.NoSuchMethodException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.reflect.InvocationTargetException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.IllegalAccessException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.InstantiationException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          }
        } else {
          throw f;
        }
      } else {
        throw f;
      }
    } finally {
      if (_messageContext.getTransportOut() != null) {
        _messageContext.getTransportOut().getSender().cleanup(_messageContext);
      }
    }
  }

  /**
   * Auto generated method signature
   *
   * @see com.backend.cin.GstService#getSIGInfo
   * @param getSIGInfo
   */
  public com.backend.cin.GstServiceStub.GetSIGInfoResponseE getSIGInfo(
      com.backend.cin.GstServiceStub.GetSIGInfoE getSIGInfo)
      throws java.rmi.RemoteException {

    org.apache.axis2.context.MessageContext _messageContext =
        new org.apache.axis2.context.MessageContext();
    try {
      org.apache.axis2.client.OperationClient _operationClient =
          _serviceClient.createClient(_operations[3].getName());
      _operationClient
          .getOptions()
          .setAction("http://www.mca.gov.in/GstServices/getSIGInfoRequest");
      _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

      addPropertyToOperationClient(
          _operationClient,
          org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
          "&");

      // create SOAP envelope with that payload
      org.apache.axiom.soap.SOAPEnvelope env = null;

      env =
          toEnvelope(
              getFactory(_operationClient.getOptions().getSoapVersionURI()),
              getSIGInfo,
              optimizeContent(
                  new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfo")),
              new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfo"));

      // adding SOAP soap_headers
      _serviceClient.addHeadersToEnvelope(env);
      // set the message context with that soap envelope
      _messageContext.setEnvelope(env);

      // add the message contxt to the operation client
      _operationClient.addMessageContext(_messageContext);

      // execute the operation client
      _operationClient.execute(true);

      org.apache.axis2.context.MessageContext _returnMessageContext =
          _operationClient.getMessageContext(
              org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
      org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
      _returnEnv.buildWithAttachments();

      java.lang.Object object =
          fromOM(
              _returnEnv.getBody().getFirstElement(),
              com.backend.cin.GstServiceStub.GetSIGInfoResponseE.class);
      org.apache.axis2.kernel.TransportUtils.detachInputStream(_returnMessageContext);

      return (com.backend.cin.GstServiceStub.GetSIGInfoResponseE) object;

    } catch (org.apache.axis2.AxisFault f) {

      org.apache.axiom.om.OMElement faultElt = f.getDetail();
      if (faultElt != null) {
        if (faultExceptionNameMap.containsKey(
            new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getSIGInfo"))) {
          // make the fault by reflection
          try {
            java.lang.String exceptionClassName =
                faultExceptionClassNameMap.get(
                    new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getSIGInfo"));
            java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
            java.lang.reflect.Constructor constructor =
                exceptionClass.getConstructor(java.lang.String.class);
            java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
            // message class
            java.lang.String messageClassName =
                faultMessageMap.get(
                    new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "getSIGInfo"));
            java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
            java.lang.Object messageObject = fromOM(faultElt, messageClass);
            java.lang.reflect.Method m =
                exceptionClass.getMethod("setFaultMessage", new java.lang.Class[] {messageClass});
            m.invoke(ex, new java.lang.Object[] {messageObject});

            throw new java.rmi.RemoteException(ex.getMessage(), ex);
          } catch (java.lang.ClassCastException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.ClassNotFoundException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.NoSuchMethodException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.reflect.InvocationTargetException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.IllegalAccessException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          } catch (java.lang.InstantiationException e) {
            // we cannot intantiate the class - throw the original Axis fault
            throw f;
          }
        } else {
          throw f;
        }
      } else {
        throw f;
      }
    } finally {
      if (_messageContext.getTransportOut() != null) {
        _messageContext.getTransportOut().getSender().cleanup(_messageContext);
      }
    }
  }

  private javax.xml.namespace.QName[] opNameArray = null;

  private boolean optimizeContent(javax.xml.namespace.QName opName) {

    if (opNameArray == null) {
      return false;
    }
    for (int i = 0; i < opNameArray.length; i++) {
      if (opName.equals(opNameArray[i])) {
        return true;
      }
    }
    return false;
  }
  // http://mca21uatservices.mca.gov.in:80/MCAGstIntegration/GstService
  public static class DinRelationMessageResponseDTO
      implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = dinRelationMessageResponseDTO
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Message This was an Array! */
    protected ItemDetails[] localMessage;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMessageTracker = false;

    public boolean isMessageSpecified() {
      return localMessageTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return ItemDetails[]
     */
    public ItemDetails[] getMessage() {
      return localMessage;
    }

    /** validate the array for Message */
    protected void validateMessage(ItemDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param Message
     */
    public void setMessage(ItemDetails[] param) {

      validateMessage(param);

      localMessageTracker = true;

      this.localMessage = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param ItemDetails
     */
    public void addMessage(ItemDetails param) {
      if (localMessage == null) {
        localMessage = new ItemDetails[] {};
      }

      // update the setting tracker
      localMessageTracker = true;

      java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localMessage);
      list.add(param);
      this.localMessage = (ItemDetails[]) list.toArray(new ItemDetails[list.size()]);
    }

    /** field for RelationComItems This was an Array! */
    protected ItemDetails[] localRelationComItems;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRelationComItemsTracker = false;

    public boolean isRelationComItemsSpecified() {
      return localRelationComItemsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return ItemDetails[]
     */
    public ItemDetails[] getRelationComItems() {
      return localRelationComItems;
    }

    /** validate the array for RelationComItems */
    protected void validateRelationComItems(ItemDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param RelationComItems
     */
    public void setRelationComItems(ItemDetails[] param) {

      validateRelationComItems(param);

      localRelationComItemsTracker = true;

      this.localRelationComItems = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param ItemDetails
     */
    public void addRelationComItems(ItemDetails param) {
      if (localRelationComItems == null) {
        localRelationComItems = new ItemDetails[] {};
      }

      // update the setting tracker
      localRelationComItemsTracker = true;

      java.util.List list =
          org.apache.axis2.databinding.utils.ConverterUtil.toList(localRelationComItems);
      list.add(param);
      this.localRelationComItems = (ItemDetails[]) list.toArray(new ItemDetails[list.size()]);
    }

    /** field for RelationItems This was an Array! */
    protected ItemDetails[] localRelationItems;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRelationItemsTracker = false;

    public boolean isRelationItemsSpecified() {
      return localRelationItemsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return ItemDetails[]
     */
    public ItemDetails[] getRelationItems() {
      return localRelationItems;
    }

    /** validate the array for RelationItems */
    protected void validateRelationItems(ItemDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param RelationItems
     */
    public void setRelationItems(ItemDetails[] param) {

      validateRelationItems(param);

      localRelationItemsTracker = true;

      this.localRelationItems = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param ItemDetails
     */
    public void addRelationItems(ItemDetails param) {
      if (localRelationItems == null) {
        localRelationItems = new ItemDetails[] {};
      }

      // update the setting tracker
      localRelationItemsTracker = true;

      java.util.List list =
          org.apache.axis2.databinding.utils.ConverterUtil.toList(localRelationItems);
      list.add(param);
      this.localRelationItems = (ItemDetails[]) list.toArray(new ItemDetails[list.size()]);
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":dinRelationMessageResponseDTO",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "dinRelationMessageResponseDTO",
              xmlWriter);
        }
      }
      if (localMessageTracker) {
        if (localMessage != null) {
          for (int i = 0; i < localMessage.length; i++) {
            if (localMessage[i] != null) {
              localMessage[i].serialize(new javax.xml.namespace.QName("", "message"), xmlWriter);
            } else {

              writeStartElement(null, "", "message", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "message", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      if (localRelationComItemsTracker) {
        if (localRelationComItems != null) {
          for (int i = 0; i < localRelationComItems.length; i++) {
            if (localRelationComItems[i] != null) {
              localRelationComItems[i].serialize(
                  new javax.xml.namespace.QName("", "relationComItems"), xmlWriter);
            } else {

              writeStartElement(null, "", "relationComItems", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "relationComItems", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      if (localRelationItemsTracker) {
        if (localRelationItems != null) {
          for (int i = 0; i < localRelationItems.length; i++) {
            if (localRelationItems[i] != null) {
              localRelationItems[i].serialize(
                  new javax.xml.namespace.QName("", "relationItems"), xmlWriter);
            } else {

              writeStartElement(null, "", "relationItems", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "relationItems", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static DinRelationMessageResponseDTO parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        DinRelationMessageResponseDTO object = new DinRelationMessageResponseDTO();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"dinRelationMessageResponseDTO".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (DinRelationMessageResponseDTO)
                    ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          java.util.ArrayList list1 = new java.util.ArrayList();

          java.util.ArrayList list2 = new java.util.ArrayList();

          java.util.ArrayList list3 = new java.util.ArrayList();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "message").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list1.add(null);
              reader.next();
            } else {
              list1.add(ItemDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone1 = false;
            while (!loopDone1) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone1 = true;
              } else {
                if (new javax.xml.namespace.QName("", "message").equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list1.add(null);
                    reader.next();
                  } else {
                    list1.add(ItemDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone1 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setMessage(
                (ItemDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        ItemDetails.class, list1));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "relationComItems").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list2.add(null);
              reader.next();
            } else {
              list2.add(ItemDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone2 = false;
            while (!loopDone2) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone2 = true;
              } else {
                if (new javax.xml.namespace.QName("", "relationComItems")
                    .equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list2.add(null);
                    reader.next();
                  } else {
                    list2.add(ItemDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone2 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setRelationComItems(
                (ItemDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        ItemDetails.class, list2));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "relationItems").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list3.add(null);
              reader.next();
            } else {
              list3.add(ItemDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone3 = false;
            while (!loopDone3) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone3 = true;
              } else {
                if (new javax.xml.namespace.QName("", "relationItems").equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list3.add(null);
                    reader.next();
                  } else {
                    list3.add(ItemDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone3 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setRelationItems(
                (ItemDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        ItemDetails.class, list3));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDirectorInfoE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDirectorInfo", "ns1");

    /** field for GetDirectorInfo */
    protected GetDirectorInfo localGetDirectorInfo;

    /**
     * Auto generated getter method
     *
     * @return GetDirectorInfo
     */
    public GetDirectorInfo getGetDirectorInfo() {
      return localGetDirectorInfo;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetDirectorInfo
     */
    public void setGetDirectorInfo(GetDirectorInfo param) {

      this.localGetDirectorInfo = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetDirectorInfo == null) {
        throw new org.apache.axis2.databinding.ADBException("getDirectorInfo cannot be null!");
      }
      localGetDirectorInfo.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDirectorInfoE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDirectorInfoE object = new GetDirectorInfoE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDirectorInfo")
                      .equals(reader.getName())) {

                object.setGetDirectorInfo(GetDirectorInfo.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class DirectorResponseDTO implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = directorResponseDTO
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for DirectorDetails This was an Array! */
    protected DirectorDetails[] localDirectorDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDirectorDetailsTracker = false;

    public boolean isDirectorDetailsSpecified() {
      return localDirectorDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return DirectorDetails[]
     */
    public DirectorDetails[] getDirectorDetails() {
      return localDirectorDetails;
    }

    /** validate the array for DirectorDetails */
    protected void validateDirectorDetails(DirectorDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param DirectorDetails
     */
    public void setDirectorDetails(DirectorDetails[] param) {

      validateDirectorDetails(param);

      localDirectorDetailsTracker = true;

      this.localDirectorDetails = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param DirectorDetails
     */
    public void addDirectorDetails(DirectorDetails param) {
      if (localDirectorDetails == null) {
        localDirectorDetails = new DirectorDetails[] {};
      }

      // update the setting tracker
      localDirectorDetailsTracker = true;

      java.util.List list =
          org.apache.axis2.databinding.utils.ConverterUtil.toList(localDirectorDetails);
      list.add(param);
      this.localDirectorDetails =
          (DirectorDetails[]) list.toArray(new DirectorDetails[list.size()]);
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":directorResponseDTO",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "directorResponseDTO",
              xmlWriter);
        }
      }
      if (localDirectorDetailsTracker) {
        if (localDirectorDetails != null) {
          for (int i = 0; i < localDirectorDetails.length; i++) {
            if (localDirectorDetails[i] != null) {
              localDirectorDetails[i].serialize(
                  new javax.xml.namespace.QName("", "directorDetails"), xmlWriter);
            } else {

              writeStartElement(null, "", "directorDetails", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "directorDetails", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static DirectorResponseDTO parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        DirectorResponseDTO object = new DirectorResponseDTO();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"directorResponseDTO".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (DirectorResponseDTO) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          java.util.ArrayList list1 = new java.util.ArrayList();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "directorDetails").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list1.add(null);
              reader.next();
            } else {
              list1.add(DirectorDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone1 = false;
            while (!loopDone1) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone1 = true;
              } else {
                if (new javax.xml.namespace.QName("", "directorDetails").equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list1.add(null);
                    reader.next();
                  } else {
                    list1.add(DirectorDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone1 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setDirectorDetails(
                (DirectorDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        DirectorDetails.class, list1));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class DinRelationServiceResponseDTO
      implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = dinRelationServiceResponseDTO
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for DinName */
    protected java.lang.String localDinName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinNameTracker = false;

    public boolean isDinNameSpecified() {
      return localDinNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDinName() {
      return localDinName;
    }

    /**
     * Auto generated setter method
     *
     * @param param DinName
     */
    public void setDinName(java.lang.String param) {
      localDinNameTracker = param != null;

      this.localDinName = param;
    }

    /** field for DinRelationMessageDetails */
    protected DinRelationMessageResponseDTO localDinRelationMessageDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinRelationMessageDetailsTracker = false;

    public boolean isDinRelationMessageDetailsSpecified() {
      return localDinRelationMessageDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return DinRelationMessageResponseDTO
     */
    public DinRelationMessageResponseDTO getDinRelationMessageDetails() {
      return localDinRelationMessageDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param DinRelationMessageDetails
     */
    public void setDinRelationMessageDetails(DinRelationMessageResponseDTO param) {
      localDinRelationMessageDetailsTracker = param != null;

      this.localDinRelationMessageDetails = param;
    }

    /** field for DinStatus */
    protected java.lang.String localDinStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinStatusTracker = false;

    public boolean isDinStatusSpecified() {
      return localDinStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDinStatus() {
      return localDinStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param DinStatus
     */
    public void setDinStatus(java.lang.String param) {
      localDinStatusTracker = param != null;

      this.localDinStatus = param;
    }

    /** field for Dob */
    protected java.lang.String localDob;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDobTracker = false;

    public boolean isDobSpecified() {
      return localDobTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDob() {
      return localDob;
    }

    /**
     * Auto generated setter method
     *
     * @param param Dob
     */
    public void setDob(java.lang.String param) {
      localDobTracker = param != null;

      this.localDob = param;
    }

    /** field for FatherName */
    protected java.lang.String localFatherName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFatherNameTracker = false;

    public boolean isFatherNameSpecified() {
      return localFatherNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFatherName() {
      return localFatherName;
    }

    /**
     * Auto generated setter method
     *
     * @param param FatherName
     */
    public void setFatherName(java.lang.String param) {
      localFatherNameTracker = param != null;

      this.localFatherName = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":dinRelationServiceResponseDTO",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "dinRelationServiceResponseDTO",
              xmlWriter);
        }
      }
      if (localDinNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dinName", xmlWriter);

        if (localDinName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dinName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDinName);
        }

        xmlWriter.writeEndElement();
      }
      if (localDinRelationMessageDetailsTracker) {
        if (localDinRelationMessageDetails == null) {
          throw new org.apache.axis2.databinding.ADBException(
              "dinRelationMessageDetails cannot be null!!");
        }
        localDinRelationMessageDetails.serialize(
            new javax.xml.namespace.QName("", "dinRelationMessageDetails"), xmlWriter);
      }
      if (localDinStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dinStatus", xmlWriter);

        if (localDinStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dinStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDinStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localDobTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dob", xmlWriter);

        if (localDob == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dob cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDob);
        }

        xmlWriter.writeEndElement();
      }
      if (localFatherNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fatherName", xmlWriter);

        if (localFatherName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fatherName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFatherName);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static DinRelationServiceResponseDTO parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        DinRelationServiceResponseDTO object = new DinRelationServiceResponseDTO();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"dinRelationServiceResponseDTO".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (DinRelationServiceResponseDTO)
                    ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dinName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dinName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDinName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dinRelationMessageDetails")
                  .equals(reader.getName())) {

            object.setDinRelationMessageDetails(
                DinRelationMessageResponseDTO.Factory.parse(reader));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dinStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dinStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDinStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dob").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dob" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDob(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fatherName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fatherName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFatherName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDINInfo implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getDINInfo
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Arg0 */
    protected java.lang.String localArg0;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localArg0Tracker = false;

    public boolean isArg0Specified() {
      return localArg0Tracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getArg0() {
      return localArg0;
    }

    /**
     * Auto generated setter method
     *
     * @param param Arg0
     */
    public void setArg0(java.lang.String param) {
      localArg0Tracker = param != null;

      this.localArg0 = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getDINInfo",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "getDINInfo", xmlWriter);
        }
      }
      if (localArg0Tracker) {
        namespace = "";
        writeStartElement(null, namespace, "arg0", xmlWriter);

        if (localArg0 == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localArg0);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDINInfo parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDINInfo object = new GetDINInfo();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getDINInfo".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetDINInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "arg0" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setArg0(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDINInfoResponse implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getDINInfoResponse
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for _return */
    protected DinRelationServiceResponseDTO local_return;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean local_returnTracker = false;

    public boolean is_returnSpecified() {
      return local_returnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return DinRelationServiceResponseDTO
     */
    public DinRelationServiceResponseDTO get_return() {
      return local_return;
    }

    /**
     * Auto generated setter method
     *
     * @param param _return
     */
    public void set_return(DinRelationServiceResponseDTO param) {
      local_returnTracker = param != null;

      this.local_return = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getDINInfoResponse",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "getDINInfoResponse",
              xmlWriter);
        }
      }
      if (local_returnTracker) {
        if (local_return == null) {
          throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
        }
        local_return.serialize(new javax.xml.namespace.QName("", "return"), xmlWriter);
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDINInfoResponse parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDINInfoResponse object = new GetDINInfoResponse();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getDINInfoResponse".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetDINInfoResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

            object.set_return(DinRelationServiceResponseDTO.Factory.parse(reader));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDINInfoResponseE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfoResponse", "ns1");

    /** field for GetDINInfoResponse */
    protected GetDINInfoResponse localGetDINInfoResponse;

    /**
     * Auto generated getter method
     *
     * @return GetDINInfoResponse
     */
    public GetDINInfoResponse getGetDINInfoResponse() {
      return localGetDINInfoResponse;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetDINInfoResponse
     */
    public void setGetDINInfoResponse(GetDINInfoResponse param) {

      this.localGetDINInfoResponse = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetDINInfoResponse == null) {
        throw new org.apache.axis2.databinding.ADBException("getDINInfoResponse cannot be null!");
      }
      localGetDINInfoResponse.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDINInfoResponseE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDINInfoResponseE object = new GetDINInfoResponseE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfoResponse")
                      .equals(reader.getName())) {

                object.setGetDINInfoResponse(GetDINInfoResponse.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetSIGInfoResponse implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getSIGInfoResponse
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for _return */
    protected SignatoryUsersResponseDTO local_return;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean local_returnTracker = false;

    public boolean is_returnSpecified() {
      return local_returnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return SignatoryUsersResponseDTO
     */
    public SignatoryUsersResponseDTO get_return() {
      return local_return;
    }

    /**
     * Auto generated setter method
     *
     * @param param _return
     */
    public void set_return(SignatoryUsersResponseDTO param) {
      local_returnTracker = param != null;

      this.local_return = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getSIGInfoResponse",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "getSIGInfoResponse",
              xmlWriter);
        }
      }
      if (local_returnTracker) {
        if (local_return == null) {
          throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
        }
        local_return.serialize(new javax.xml.namespace.QName("", "return"), xmlWriter);
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetSIGInfoResponse parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetSIGInfoResponse object = new GetSIGInfoResponse();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getSIGInfoResponse".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetSIGInfoResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

            object.set_return(SignatoryUsersResponseDTO.Factory.parse(reader));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetCINInfoE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfo", "ns1");

    /** field for GetCINInfo */
    protected GetCINInfo localGetCINInfo;

    /**
     * Auto generated getter method
     *
     * @return GetCINInfo
     */
    public GetCINInfo getGetCINInfo() {
      return localGetCINInfo;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetCINInfo
     */
    public void setGetCINInfo(GetCINInfo param) {

      this.localGetCINInfo = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetCINInfo == null) {
        throw new org.apache.axis2.databinding.ADBException("getCINInfo cannot be null!");
      }
      localGetCINInfo.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetCINInfoE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetCINInfoE object = new GetCINInfoE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfo")
                      .equals(reader.getName())) {

                object.setGetCINInfo(GetCINInfo.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDirectorInfo implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getDirectorInfo
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Arg0 */
    protected java.lang.String localArg0;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localArg0Tracker = false;

    public boolean isArg0Specified() {
      return localArg0Tracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getArg0() {
      return localArg0;
    }

    /**
     * Auto generated setter method
     *
     * @param param Arg0
     */
    public void setArg0(java.lang.String param) {
      localArg0Tracker = param != null;

      this.localArg0 = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getDirectorInfo",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "getDirectorInfo",
              xmlWriter);
        }
      }
      if (localArg0Tracker) {
        namespace = "";
        writeStartElement(null, namespace, "arg0", xmlWriter);

        if (localArg0 == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localArg0);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDirectorInfo parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDirectorInfo object = new GetDirectorInfo();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getDirectorInfo".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetDirectorInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "arg0" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setArg0(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDINInfoE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfo", "ns1");

    /** field for GetDINInfo */
    protected GetDINInfo localGetDINInfo;

    /**
     * Auto generated getter method
     *
     * @return GetDINInfo
     */
    public GetDINInfo getGetDINInfo() {
      return localGetDINInfo;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetDINInfo
     */
    public void setGetDINInfo(GetDINInfo param) {

      this.localGetDINInfo = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetDINInfo == null) {
        throw new org.apache.axis2.databinding.ADBException("getDINInfo cannot be null!");
      }
      localGetDINInfo.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDINInfoE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDINInfoE object = new GetDINInfoE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDINInfo")
                      .equals(reader.getName())) {

                object.setGetDINInfo(GetDINInfo.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class DirectorDetails implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = directorDetails
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for ContactNumber */
    protected java.lang.String localContactNumber;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localContactNumberTracker = false;

    public boolean isContactNumberSpecified() {
      return localContactNumberTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getContactNumber() {
      return localContactNumber;
    }

    /**
     * Auto generated setter method
     *
     * @param param ContactNumber
     */
    public void setContactNumber(java.lang.String param) {
      localContactNumberTracker = param != null;

      this.localContactNumber = param;
    }

    /** field for Din */
    protected java.lang.String localDin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinTracker = false;

    public boolean isDinSpecified() {
      return localDinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDin() {
      return localDin;
    }

    /**
     * Auto generated setter method
     *
     * @param param Din
     */
    public void setDin(java.lang.String param) {
      localDinTracker = param != null;

      this.localDin = param;
    }

    /** field for Name */
    protected java.lang.String localName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNameTracker = false;

    public boolean isNameSpecified() {
      return localNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getName() {
      return localName;
    }

    /**
     * Auto generated setter method
     *
     * @param param Name
     */
    public void setName(java.lang.String param) {
      localNameTracker = param != null;

      this.localName = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":directorDetails",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "directorDetails",
              xmlWriter);
        }
      }
      if (localContactNumberTracker) {
        namespace = "";
        writeStartElement(null, namespace, "contactNumber", xmlWriter);

        if (localContactNumber == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("contactNumber cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localContactNumber);
        }

        xmlWriter.writeEndElement();
      }
      if (localDinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "din", xmlWriter);

        if (localDin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("din cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDin);
        }

        xmlWriter.writeEndElement();
      }
      if (localNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "name", xmlWriter);

        if (localName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("name cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localName);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static DirectorDetails parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        DirectorDetails object = new DirectorDetails();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"directorDetails".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (DirectorDetails) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "contactNumber").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "contactNumber" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setContactNumber(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "din").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "din" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "name").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "name" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class ItemDetails implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = itemDetails
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for ActivityDesc */
    protected java.lang.String localActivityDesc;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localActivityDescTracker = false;

    public boolean isActivityDescSpecified() {
      return localActivityDescTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getActivityDesc() {
      return localActivityDesc;
    }

    /**
     * Auto generated setter method
     *
     * @param param ActivityDesc
     */
    public void setActivityDesc(java.lang.String param) {
      localActivityDescTracker = param != null;

      this.localActivityDesc = param;
    }

    /** field for AllPages */
    protected java.lang.String localAllPages;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAllPagesTracker = false;

    public boolean isAllPagesSpecified() {
      return localAllPagesTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getAllPages() {
      return localAllPages;
    }

    /**
     * Auto generated setter method
     *
     * @param param AllPages
     */
    public void setAllPages(java.lang.String param) {
      localAllPagesTracker = param != null;

      this.localAllPages = param;
    }

    /** field for AmountSecured */
    protected java.math.BigDecimal localAmountSecured;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAmountSecuredTracker = false;

    public boolean isAmountSecuredSpecified() {
      return localAmountSecuredTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getAmountSecured() {
      return localAmountSecured;
    }

    /**
     * Auto generated setter method
     *
     * @param param AmountSecured
     */
    public void setAmountSecured(java.math.BigDecimal param) {
      localAmountSecuredTracker = param != null;

      this.localAmountSecured = param;
    }

    /** field for Asset */
    protected java.lang.String localAsset;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAssetTracker = false;

    public boolean isAssetSpecified() {
      return localAssetTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getAsset() {
      return localAsset;
    }

    /**
     * Auto generated setter method
     *
     * @param param Asset
     */
    public void setAsset(java.lang.String param) {
      localAssetTracker = param != null;

      this.localAsset = param;
    }

    /** field for Asset1 */
    protected java.lang.String localAsset1;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAsset1Tracker = false;

    public boolean isAsset1Specified() {
      return localAsset1Tracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getAsset1() {
      return localAsset1;
    }

    /**
     * Auto generated setter method
     *
     * @param param Asset1
     */
    public void setAsset1(java.lang.String param) {
      localAsset1Tracker = param != null;

      this.localAsset1 = param;
    }

    /** field for AuthCapital */
    protected java.math.BigDecimal localAuthCapital;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localAuthCapitalTracker = false;

    public boolean isAuthCapitalSpecified() {
      return localAuthCapitalTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getAuthCapital() {
      return localAuthCapital;
    }

    /**
     * Auto generated setter method
     *
     * @param param AuthCapital
     */
    public void setAuthCapital(java.math.BigDecimal param) {
      localAuthCapitalTracker = param != null;

      this.localAuthCapital = param;
    }

    /** field for BankName */
    protected java.lang.String localBankName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBankNameTracker = false;

    public boolean isBankNameSpecified() {
      return localBankNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getBankName() {
      return localBankName;
    }

    /**
     * Auto generated setter method
     *
     * @param param BankName
     */
    public void setBankName(java.lang.String param) {
      localBankNameTracker = param != null;

      this.localBankName = param;
    }

    /** field for BankRegistered */
    protected java.lang.String localBankRegistered;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBankRegisteredTracker = false;

    public boolean isBankRegisteredSpecified() {
      return localBankRegisteredTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getBankRegistered() {
      return localBankRegistered;
    }

    /**
     * Auto generated setter method
     *
     * @param param BankRegistered
     */
    public void setBankRegistered(java.lang.String param) {
      localBankRegisteredTracker = param != null;

      this.localBankRegistered = param;
    }

    /** field for BeginDate */
    protected java.util.Calendar localBeginDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBeginDateTracker = false;

    public boolean isBeginDateSpecified() {
      return localBeginDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getBeginDate() {
      return localBeginDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param BeginDate
     */
    public void setBeginDate(java.util.Calendar param) {
      localBeginDateTracker = param != null;

      this.localBeginDate = param;
    }

    /** field for BirthDate */
    protected java.util.Calendar localBirthDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBirthDateTracker = false;

    public boolean isBirthDateSpecified() {
      return localBirthDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getBirthDate() {
      return localBirthDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param BirthDate
     */
    public void setBirthDate(java.util.Calendar param) {
      localBirthDateTracker = param != null;

      this.localBirthDate = param;
    }

    /** field for BpName */
    protected java.lang.String localBpName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBpNameTracker = false;

    public boolean isBpNameSpecified() {
      return localBpNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getBpName() {
      return localBpName;
    }

    /**
     * Auto generated setter method
     *
     * @param param BpName
     */
    public void setBpName(java.lang.String param) {
      localBpNameTracker = param != null;

      this.localBpName = param;
    }

    /** field for Bytes */
    protected javax.activation.DataHandler localBytes;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localBytesTracker = false;

    public boolean isBytesSpecified() {
      return localBytesTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return javax.activation.DataHandler
     */
    public javax.activation.DataHandler getBytes() {
      return localBytes;
    }

    /**
     * Auto generated setter method
     *
     * @param param Bytes
     */
    public void setBytes(javax.activation.DataHandler param) {
      localBytesTracker = param != null;

      this.localBytes = param;
    }

    /** field for Category */
    protected java.lang.String localCategory;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCategoryTracker = false;

    public boolean isCategorySpecified() {
      return localCategoryTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCategory() {
      return localCategory;
    }

    /**
     * Auto generated setter method
     *
     * @param param Category
     */
    public void setCategory(java.lang.String param) {
      localCategoryTracker = param != null;

      this.localCategory = param;
    }

    /** field for Certstatus */
    protected java.lang.String localCertstatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCertstatusTracker = false;

    public boolean isCertstatusSpecified() {
      return localCertstatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCertstatus() {
      return localCertstatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param Certstatus
     */
    public void setCertstatus(java.lang.String param) {
      localCertstatusTracker = param != null;

      this.localCertstatus = param;
    }

    /** field for ChargeAmount */
    protected java.math.BigDecimal localChargeAmount;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localChargeAmountTracker = false;

    public boolean isChargeAmountSpecified() {
      return localChargeAmountTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getChargeAmount() {
      return localChargeAmount;
    }

    /**
     * Auto generated setter method
     *
     * @param param ChargeAmount
     */
    public void setChargeAmount(java.math.BigDecimal param) {
      localChargeAmountTracker = param != null;

      this.localChargeAmount = param;
    }

    /** field for ChargeID */
    protected java.lang.String localChargeID;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localChargeIDTracker = false;

    public boolean isChargeIDSpecified() {
      return localChargeIDTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getChargeID() {
      return localChargeID;
    }

    /**
     * Auto generated setter method
     *
     * @param param ChargeID
     */
    public void setChargeID(java.lang.String param) {
      localChargeIDTracker = param != null;

      this.localChargeID = param;
    }

    /** field for ChargeStatus */
    protected java.lang.String localChargeStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localChargeStatusTracker = false;

    public boolean isChargeStatusSpecified() {
      return localChargeStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getChargeStatus() {
      return localChargeStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param ChargeStatus
     */
    public void setChargeStatus(java.lang.String param) {
      localChargeStatusTracker = param != null;

      this.localChargeStatus = param;
    }

    /** field for Cin */
    protected java.lang.String localCin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCinTracker = false;

    public boolean isCinSpecified() {
      return localCinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCin() {
      return localCin;
    }

    /**
     * Auto generated setter method
     *
     * @param param Cin
     */
    public void setCin(java.lang.String param) {
      localCinTracker = param != null;

      this.localCin = param;
    }

    /** field for CinLLPIN */
    protected java.lang.String localCinLLPIN;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCinLLPINTracker = false;

    public boolean isCinLLPINSpecified() {
      return localCinLLPINTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCinLLPIN() {
      return localCinLLPIN;
    }

    /**
     * Auto generated setter method
     *
     * @param param CinLLPIN
     */
    public void setCinLLPIN(java.lang.String param) {
      localCinLLPINTracker = param != null;

      this.localCinLLPIN = param;
    }

    /** field for CinLLPINDetails */
    protected java.lang.String localCinLLPINDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCinLLPINDetailsTracker = false;

    public boolean isCinLLPINDetailsSpecified() {
      return localCinLLPINDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCinLLPINDetails() {
      return localCinLLPINDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param CinLLPINDetails
     */
    public void setCinLLPINDetails(java.lang.String param) {
      localCinLLPINDetailsTracker = param != null;

      this.localCinLLPINDetails = param;
    }

    /** field for CinLLPInE */
    protected java.lang.String localCinLLPInE;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCinLLPInETracker = false;

    public boolean isCinLLPInESpecified() {
      return localCinLLPInETracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCinLLPInE() {
      return localCinLLPInE;
    }

    /**
     * Auto generated setter method
     *
     * @param param CinLLPInE
     */
    public void setCinLLPInE(java.lang.String param) {
      localCinLLPInETracker = param != null;

      this.localCinLLPInE = param;
    }

    /** field for Comment */
    protected java.lang.String localComment;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCommentTracker = false;

    public boolean isCommentSpecified() {
      return localCommentTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getComment() {
      return localComment;
    }

    /**
     * Auto generated setter method
     *
     * @param param Comment
     */
    public void setComment(java.lang.String param) {
      localCommentTracker = param != null;

      this.localComment = param;
    }

    /** field for CompClass */
    protected java.lang.String localCompClass;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompClassTracker = false;

    public boolean isCompClassSpecified() {
      return localCompClassTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompClass() {
      return localCompClass;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompClass
     */
    public void setCompClass(java.lang.String param) {
      localCompClassTracker = param != null;

      this.localCompClass = param;
    }

    /** field for CompLLPStatus */
    protected java.lang.String localCompLLPStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompLLPStatusTracker = false;

    public boolean isCompLLPStatusSpecified() {
      return localCompLLPStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompLLPStatus() {
      return localCompLLPStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompLLPStatus
     */
    public void setCompLLPStatus(java.lang.String param) {
      localCompLLPStatusTracker = param != null;

      this.localCompLLPStatus = param;
    }

    /** field for CompanyFlag */
    protected java.lang.String localCompanyFlag;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyFlagTracker = false;

    public boolean isCompanyFlagSpecified() {
      return localCompanyFlagTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyFlag() {
      return localCompanyFlag;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyFlag
     */
    public void setCompanyFlag(java.lang.String param) {
      localCompanyFlagTracker = param != null;

      this.localCompanyFlag = param;
    }

    /** field for CompanyID */
    protected java.lang.String localCompanyID;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyIDTracker = false;

    public boolean isCompanyIDSpecified() {
      return localCompanyIDTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyID() {
      return localCompanyID;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyID
     */
    public void setCompanyID(java.lang.String param) {
      localCompanyIDTracker = param != null;

      this.localCompanyID = param;
    }

    /** field for CompanyLLPName */
    protected java.lang.String localCompanyLLPName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyLLPNameTracker = false;

    public boolean isCompanyLLPNameSpecified() {
      return localCompanyLLPNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyLLPName() {
      return localCompanyLLPName;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyLLPName
     */
    public void setCompanyLLPName(java.lang.String param) {
      localCompanyLLPNameTracker = param != null;

      this.localCompanyLLPName = param;
    }

    /** field for CompanyName */
    protected java.lang.String localCompanyName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyNameTracker = false;

    public boolean isCompanyNameSpecified() {
      return localCompanyNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyName() {
      return localCompanyName;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyName
     */
    public void setCompanyName(java.lang.String param) {
      localCompanyNameTracker = param != null;

      this.localCompanyName = param;
    }

    /** field for ComplaintType */
    protected java.lang.String localComplaintType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localComplaintTypeTracker = false;

    public boolean isComplaintTypeSpecified() {
      return localComplaintTypeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getComplaintType() {
      return localComplaintType;
    }

    /**
     * Auto generated setter method
     *
     * @param param ComplaintType
     */
    public void setComplaintType(java.lang.String param) {
      localComplaintTypeTracker = param != null;

      this.localComplaintType = param;
    }

    /** field for ContribRecandAcc */
    protected java.lang.String localContribRecandAcc;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localContribRecandAccTracker = false;

    public boolean isContribRecandAccSpecified() {
      return localContribRecandAccTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getContribRecandAcc() {
      return localContribRecandAcc;
    }

    /**
     * Auto generated setter method
     *
     * @param param ContribRecandAcc
     */
    public void setContribRecandAcc(java.lang.String param) {
      localContribRecandAccTracker = param != null;

      this.localContribRecandAcc = param;
    }

    /** field for CopyChallan */
    protected java.lang.String localCopyChallan;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCopyChallanTracker = false;

    public boolean isCopyChallanSpecified() {
      return localCopyChallanTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCopyChallan() {
      return localCopyChallan;
    }

    /**
     * Auto generated setter method
     *
     * @param param CopyChallan
     */
    public void setCopyChallan(java.lang.String param) {
      localCopyChallanTracker = param != null;

      this.localCopyChallan = param;
    }

    /** field for CrDat */
    protected java.util.Calendar localCrDat;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCrDatTracker = false;

    public boolean isCrDatSpecified() {
      return localCrDatTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getCrDat() {
      return localCrDat;
    }

    /**
     * Auto generated setter method
     *
     * @param param CrDat
     */
    public void setCrDat(java.util.Calendar param) {
      localCrDatTracker = param != null;

      this.localCrDat = param;
    }

    /** field for CrDate */
    protected java.util.Calendar localCrDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCrDateTracker = false;

    public boolean isCrDateSpecified() {
      return localCrDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getCrDate() {
      return localCrDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param CrDate
     */
    public void setCrDate(java.util.Calendar param) {
      localCrDateTracker = param != null;

      this.localCrDate = param;
    }

    /** field for CreationDate */
    protected java.util.Calendar localCreationDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCreationDateTracker = false;

    public boolean isCreationDateSpecified() {
      return localCreationDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getCreationDate() {
      return localCreationDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param CreationDate
     */
    public void setCreationDate(java.util.Calendar param) {
      localCreationDateTracker = param != null;

      this.localCreationDate = param;
    }

    /** field for Curr */
    protected java.lang.String localCurr;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCurrTracker = false;

    public boolean isCurrSpecified() {
      return localCurrTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCurr() {
      return localCurr;
    }

    /**
     * Auto generated setter method
     *
     * @param param Curr
     */
    public void setCurr(java.lang.String param) {
      localCurrTracker = param != null;

      this.localCurr = param;
    }

    /** field for CurrentDesignation */
    protected java.lang.String localCurrentDesignation;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCurrentDesignationTracker = false;

    public boolean isCurrentDesignationSpecified() {
      return localCurrentDesignationTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCurrentDesignation() {
      return localCurrentDesignation;
    }

    /**
     * Auto generated setter method
     *
     * @param param CurrentDesignation
     */
    public void setCurrentDesignation(java.lang.String param) {
      localCurrentDesignationTracker = param != null;

      this.localCurrentDesignation = param;
    }

    /** field for DateAddition */
    protected java.util.Calendar localDateAddition;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateAdditionTracker = false;

    public boolean isDateAdditionSpecified() {
      return localDateAdditionTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateAddition() {
      return localDateAddition;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateAddition
     */
    public void setDateAddition(java.util.Calendar param) {
      localDateAdditionTracker = param != null;

      this.localDateAddition = param;
    }

    /** field for DateAppointment */
    protected java.util.Calendar localDateAppointment;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateAppointmentTracker = false;

    public boolean isDateAppointmentSpecified() {
      return localDateAppointmentTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateAppointment() {
      return localDateAppointment;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateAppointment
     */
    public void setDateAppointment(java.util.Calendar param) {
      localDateAppointmentTracker = param != null;

      this.localDateAppointment = param;
    }

    /** field for DateCessation */
    protected java.util.Calendar localDateCessation;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateCessationTracker = false;

    public boolean isDateCessationSpecified() {
      return localDateCessationTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateCessation() {
      return localDateCessation;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateCessation
     */
    public void setDateCessation(java.util.Calendar param) {
      localDateCessationTracker = param != null;

      this.localDateCessation = param;
    }

    /** field for DateFrom */
    protected java.util.Calendar localDateFrom;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateFromTracker = false;

    public boolean isDateFromSpecified() {
      return localDateFromTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateFrom() {
      return localDateFrom;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateFrom
     */
    public void setDateFrom(java.util.Calendar param) {
      localDateFromTracker = param != null;

      this.localDateFrom = param;
    }

    /** field for DateLastModification */
    protected long localDateLastModification;

    /**
     * Auto generated getter method
     *
     * @return long
     */
    public long getDateLastModification() {
      return localDateLastModification;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateLastModification
     */
    public void setDateLastModification(long param) {

      this.localDateLastModification = param;
    }

    /** field for DateOfAppointment */
    protected java.util.Calendar localDateOfAppointment;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateOfAppointmentTracker = false;

    public boolean isDateOfAppointmentSpecified() {
      return localDateOfAppointmentTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateOfAppointment() {
      return localDateOfAppointment;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateOfAppointment
     */
    public void setDateOfAppointment(java.util.Calendar param) {
      localDateOfAppointmentTracker = param != null;

      this.localDateOfAppointment = param;
    }

    /** field for DateOfCessation */
    protected java.util.Calendar localDateOfCessation;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateOfCessationTracker = false;

    public boolean isDateOfCessationSpecified() {
      return localDateOfCessationTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateOfCessation() {
      return localDateOfCessation;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateOfCessation
     */
    public void setDateOfCessation(java.util.Calendar param) {
      localDateOfCessationTracker = param != null;

      this.localDateOfCessation = param;
    }

    /** field for DateOfChangeDesig */
    protected java.util.Calendar localDateOfChangeDesig;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateOfChangeDesigTracker = false;

    public boolean isDateOfChangeDesigSpecified() {
      return localDateOfChangeDesigTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateOfChangeDesig() {
      return localDateOfChangeDesig;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateOfChangeDesig
     */
    public void setDateOfChangeDesig(java.util.Calendar param) {
      localDateOfChangeDesigTracker = param != null;

      this.localDateOfChangeDesig = param;
    }

    /** field for DateRoleCheck */
    protected java.util.Calendar localDateRoleCheck;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateRoleCheckTracker = false;

    public boolean isDateRoleCheckSpecified() {
      return localDateRoleCheckTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateRoleCheck() {
      return localDateRoleCheck;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateRoleCheck
     */
    public void setDateRoleCheck(java.util.Calendar param) {
      localDateRoleCheckTracker = param != null;

      this.localDateRoleCheck = param;
    }

    /** field for DateTo */
    protected java.util.Calendar localDateTo;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateToTracker = false;

    public boolean isDateToSpecified() {
      return localDateToTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateTo() {
      return localDateTo;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateTo
     */
    public void setDateTo(java.util.Calendar param) {
      localDateToTracker = param != null;

      this.localDateTo = param;
    }

    /** field for DateofFiling */
    protected java.util.Calendar localDateofFiling;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDateofFilingTracker = false;

    public boolean isDateofFilingSpecified() {
      return localDateofFilingTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDateofFiling() {
      return localDateofFiling;
    }

    /**
     * Auto generated setter method
     *
     * @param param DateofFiling
     */
    public void setDateofFiling(java.util.Calendar param) {
      localDateofFilingTracker = param != null;

      this.localDateofFiling = param;
    }

    /** field for DefaultYear */
    protected java.lang.String localDefaultYear;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDefaultYearTracker = false;

    public boolean isDefaultYearSpecified() {
      return localDefaultYearTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDefaultYear() {
      return localDefaultYear;
    }

    /**
     * Auto generated setter method
     *
     * @param param DefaultYear
     */
    public void setDefaultYear(java.lang.String param) {
      localDefaultYearTracker = param != null;

      this.localDefaultYear = param;
    }

    /** field for DefaultingStatus */
    protected java.lang.String localDefaultingStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDefaultingStatusTracker = false;

    public boolean isDefaultingStatusSpecified() {
      return localDefaultingStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDefaultingStatus() {
      return localDefaultingStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param DefaultingStatus
     */
    public void setDefaultingStatus(java.lang.String param) {
      localDefaultingStatusTracker = param != null;

      this.localDefaultingStatus = param;
    }

    /** field for Desc */
    protected java.lang.String localDesc;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDescTracker = false;

    public boolean isDescSpecified() {
      return localDescTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDesc() {
      return localDesc;
    }

    /**
     * Auto generated setter method
     *
     * @param param Desc
     */
    public void setDesc(java.lang.String param) {
      localDescTracker = param != null;

      this.localDesc = param;
    }

    /** field for DesigFL */
    protected java.lang.String localDesigFL;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDesigFLTracker = false;

    public boolean isDesigFLSpecified() {
      return localDesigFLTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDesigFL() {
      return localDesigFL;
    }

    /**
     * Auto generated setter method
     *
     * @param param DesigFL
     */
    public void setDesigFL(java.lang.String param) {
      localDesigFLTracker = param != null;

      this.localDesigFL = param;
    }

    /** field for DesigShort */
    protected java.lang.String localDesigShort;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDesigShortTracker = false;

    public boolean isDesigShortSpecified() {
      return localDesigShortTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDesigShort() {
      return localDesigShort;
    }

    /**
     * Auto generated setter method
     *
     * @param param DesigShort
     */
    public void setDesigShort(java.lang.String param) {
      localDesigShortTracker = param != null;

      this.localDesigShort = param;
    }

    /** field for Designation */
    protected java.lang.String localDesignation;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDesignationTracker = false;

    public boolean isDesignationSpecified() {
      return localDesignationTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDesignation() {
      return localDesignation;
    }

    /**
     * Auto generated setter method
     *
     * @param param Designation
     */
    public void setDesignation(java.lang.String param) {
      localDesignationTracker = param != null;

      this.localDesignation = param;
    }

    /** field for Din */
    protected java.lang.String localDin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinTracker = false;

    public boolean isDinSpecified() {
      return localDinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDin() {
      return localDin;
    }

    /**
     * Auto generated setter method
     *
     * @param param Din
     */
    public void setDin(java.lang.String param) {
      localDinTracker = param != null;

      this.localDin = param;
    }

    /** field for DinDpin */
    protected java.lang.String localDinDpin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinDpinTracker = false;

    public boolean isDinDpinSpecified() {
      return localDinDpinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDinDpin() {
      return localDinDpin;
    }

    /**
     * Auto generated setter method
     *
     * @param param DinDpin
     */
    public void setDinDpin(java.lang.String param) {
      localDinDpinTracker = param != null;

      this.localDinDpin = param;
    }

    /** field for DinPan */
    protected java.lang.String localDinPan;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDinPanTracker = false;

    public boolean isDinPanSpecified() {
      return localDinPanTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDinPan() {
      return localDinPan;
    }

    /**
     * Auto generated setter method
     *
     * @param param DinPan
     */
    public void setDinPan(java.lang.String param) {
      localDinPanTracker = param != null;

      this.localDinPan = param;
    }

    /** field for DisablePageRange */
    protected java.lang.String localDisablePageRange;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDisablePageRangeTracker = false;

    public boolean isDisablePageRangeSpecified() {
      return localDisablePageRangeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDisablePageRange() {
      return localDisablePageRange;
    }

    /**
     * Auto generated setter method
     *
     * @param param DisablePageRange
     */
    public void setDisablePageRange(java.lang.String param) {
      localDisablePageRangeTracker = param != null;

      this.localDisablePageRange = param;
    }

    /** field for Dob */
    protected java.util.Calendar localDob;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDobTracker = false;

    public boolean isDobSpecified() {
      return localDobTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getDob() {
      return localDob;
    }

    /**
     * Auto generated setter method
     *
     * @param param Dob
     */
    public void setDob(java.util.Calendar param) {
      localDobTracker = param != null;

      this.localDob = param;
    }

    /** field for DocCategoryCode */
    protected java.lang.String localDocCategoryCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDocCategoryCodeTracker = false;

    public boolean isDocCategoryCodeSpecified() {
      return localDocCategoryCodeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDocCategoryCode() {
      return localDocCategoryCode;
    }

    /**
     * Auto generated setter method
     *
     * @param param DocCategoryCode
     */
    public void setDocCategoryCode(java.lang.String param) {
      localDocCategoryCodeTracker = param != null;

      this.localDocCategoryCode = param;
    }

    /** field for DocID */
    protected java.lang.String localDocID;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDocIDTracker = false;

    public boolean isDocIDSpecified() {
      return localDocIDTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDocID() {
      return localDocID;
    }

    /**
     * Auto generated setter method
     *
     * @param param DocID
     */
    public void setDocID(java.lang.String param) {
      localDocIDTracker = param != null;

      this.localDocID = param;
    }

    /** field for DocStampDuty */
    protected java.math.BigDecimal localDocStampDuty;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDocStampDutyTracker = false;

    public boolean isDocStampDutySpecified() {
      return localDocStampDutyTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getDocStampDuty() {
      return localDocStampDuty;
    }

    /**
     * Auto generated setter method
     *
     * @param param DocStampDuty
     */
    public void setDocStampDuty(java.math.BigDecimal param) {
      localDocStampDutyTracker = param != null;

      this.localDocStampDuty = param;
    }

    /** field for DocumentID */
    protected java.lang.String localDocumentID;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDocumentIDTracker = false;

    public boolean isDocumentIDSpecified() {
      return localDocumentIDTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDocumentID() {
      return localDocumentID;
    }

    /**
     * Auto generated setter method
     *
     * @param param DocumentID
     */
    public void setDocumentID(java.lang.String param) {
      localDocumentIDTracker = param != null;

      this.localDocumentID = param;
    }

    /** field for DocumentName */
    protected java.lang.String localDocumentName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDocumentNameTracker = false;

    public boolean isDocumentNameSpecified() {
      return localDocumentNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDocumentName() {
      return localDocumentName;
    }

    /**
     * Auto generated setter method
     *
     * @param param DocumentName
     */
    public void setDocumentName(java.lang.String param) {
      localDocumentNameTracker = param != null;

      this.localDocumentName = param;
    }

    /** field for DpName */
    protected java.lang.String localDpName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDpNameTracker = false;

    public boolean isDpNameSpecified() {
      return localDpNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDpName() {
      return localDpName;
    }

    /**
     * Auto generated setter method
     *
     * @param param DpName
     */
    public void setDpName(java.lang.String param) {
      localDpNameTracker = param != null;

      this.localDpName = param;
    }

    /** field for DpinIncomeTaxPAN */
    protected java.lang.String localDpinIncomeTaxPAN;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDpinIncomeTaxPANTracker = false;

    public boolean isDpinIncomeTaxPANSpecified() {
      return localDpinIncomeTaxPANTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDpinIncomeTaxPAN() {
      return localDpinIncomeTaxPAN;
    }

    /**
     * Auto generated setter method
     *
     * @param param DpinIncomeTaxPAN
     */
    public void setDpinIncomeTaxPAN(java.lang.String param) {
      localDpinIncomeTaxPANTracker = param != null;

      this.localDpinIncomeTaxPAN = param;
    }

    /** field for DscReg */
    protected java.lang.String localDscReg;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localDscRegTracker = false;

    public boolean isDscRegSpecified() {
      return localDscRegTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getDscReg() {
      return localDscReg;
    }

    /**
     * Auto generated setter method
     *
     * @param param DscReg
     */
    public void setDscReg(java.lang.String param) {
      localDscRegTracker = param != null;

      this.localDscReg = param;
    }

    /** field for EmailNodal */
    protected java.lang.String localEmailNodal;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEmailNodalTracker = false;

    public boolean isEmailNodalSpecified() {
      return localEmailNodalTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getEmailNodal() {
      return localEmailNodal;
    }

    /**
     * Auto generated setter method
     *
     * @param param EmailNodal
     */
    public void setEmailNodal(java.lang.String param) {
      localEmailNodalTracker = param != null;

      this.localEmailNodal = param;
    }

    /** field for EndDate */
    protected java.util.Calendar localEndDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEndDateTracker = false;

    public boolean isEndDateSpecified() {
      return localEndDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getEndDate() {
      return localEndDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param EndDate
     */
    public void setEndDate(java.util.Calendar param) {
      localEndDateTracker = param != null;

      this.localEndDate = param;
    }

    /** field for EnterDetailsSno */
    protected java.lang.String localEnterDetailsSno;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEnterDetailsSnoTracker = false;

    public boolean isEnterDetailsSnoSpecified() {
      return localEnterDetailsSnoTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getEnterDetailsSno() {
      return localEnterDetailsSno;
    }

    /**
     * Auto generated setter method
     *
     * @param param EnterDetailsSno
     */
    public void setEnterDetailsSno(java.lang.String param) {
      localEnterDetailsSnoTracker = param != null;

      this.localEnterDetailsSno = param;
    }

    /** field for EnterDtlsName */
    protected java.lang.String localEnterDtlsName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEnterDtlsNameTracker = false;

    public boolean isEnterDtlsNameSpecified() {
      return localEnterDtlsNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getEnterDtlsName() {
      return localEnterDtlsName;
    }

    /**
     * Auto generated setter method
     *
     * @param param EnterDtlsName
     */
    public void setEnterDtlsName(java.lang.String param) {
      localEnterDtlsNameTracker = param != null;

      this.localEnterDtlsName = param;
    }

    /** field for EventDate */
    protected java.util.Calendar localEventDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEventDateTracker = false;

    public boolean isEventDateSpecified() {
      return localEventDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getEventDate() {
      return localEventDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param EventDate
     */
    public void setEventDate(java.util.Calendar param) {
      localEventDateTracker = param != null;

      this.localEventDate = param;
    }

    /** field for ExpiryDate */
    protected java.util.Calendar localExpiryDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localExpiryDateTracker = false;

    public boolean isExpiryDateSpecified() {
      return localExpiryDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getExpiryDate() {
      return localExpiryDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param ExpiryDate
     */
    public void setExpiryDate(java.util.Calendar param) {
      localExpiryDateTracker = param != null;

      this.localExpiryDate = param;
    }

    /** field for ExpiryDateandTime */
    protected java.lang.String localExpiryDateandTime;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localExpiryDateandTimeTracker = false;

    public boolean isExpiryDateandTimeSpecified() {
      return localExpiryDateandTimeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getExpiryDateandTime() {
      return localExpiryDateandTime;
    }

    /**
     * Auto generated setter method
     *
     * @param param ExpiryDateandTime
     */
    public void setExpiryDateandTime(java.lang.String param) {
      localExpiryDateandTimeTracker = param != null;

      this.localExpiryDateandTime = param;
    }

    /** field for FatherName */
    protected java.lang.String localFatherName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFatherNameTracker = false;

    public boolean isFatherNameSpecified() {
      return localFatherNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFatherName() {
      return localFatherName;
    }

    /**
     * Auto generated setter method
     *
     * @param param FatherName
     */
    public void setFatherName(java.lang.String param) {
      localFatherNameTracker = param != null;

      this.localFatherName = param;
    }

    /** field for Fcrn */
    protected java.lang.String localFcrn;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFcrnTracker = false;

    public boolean isFcrnSpecified() {
      return localFcrnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFcrn() {
      return localFcrn;
    }

    /**
     * Auto generated setter method
     *
     * @param param Fcrn
     */
    public void setFcrn(java.lang.String param) {
      localFcrnTracker = param != null;

      this.localFcrn = param;
    }

    /** field for Fee */
    protected java.math.BigDecimal localFee;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFeeTracker = false;

    public boolean isFeeSpecified() {
      return localFeeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getFee() {
      return localFee;
    }

    /**
     * Auto generated setter method
     *
     * @param param Fee
     */
    public void setFee(java.math.BigDecimal param) {
      localFeeTracker = param != null;

      this.localFee = param;
    }

    /** field for FeeAmount */
    protected java.math.BigDecimal localFeeAmount;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFeeAmountTracker = false;

    public boolean isFeeAmountSpecified() {
      return localFeeAmountTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getFeeAmount() {
      return localFeeAmount;
    }

    /**
     * Auto generated setter method
     *
     * @param param FeeAmount
     */
    public void setFeeAmount(java.math.BigDecimal param) {
      localFeeAmountTracker = param != null;

      this.localFeeAmount = param;
    }

    /** field for FeeAmt */
    protected java.lang.String localFeeAmt;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFeeAmtTracker = false;

    public boolean isFeeAmtSpecified() {
      return localFeeAmtTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFeeAmt() {
      return localFeeAmt;
    }

    /**
     * Auto generated setter method
     *
     * @param param FeeAmt
     */
    public void setFeeAmt(java.lang.String param) {
      localFeeAmtTracker = param != null;

      this.localFeeAmt = param;
    }

    /** field for FeeApplicable */
    protected boolean localFeeApplicable;

    /**
     * Auto generated getter method
     *
     * @return boolean
     */
    public boolean getFeeApplicable() {
      return localFeeApplicable;
    }

    /**
     * Auto generated setter method
     *
     * @param param FeeApplicable
     */
    public void setFeeApplicable(boolean param) {

      this.localFeeApplicable = param;
    }

    /** field for FeeType */
    protected java.lang.String localFeeType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFeeTypeTracker = false;

    public boolean isFeeTypeSpecified() {
      return localFeeTypeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFeeType() {
      return localFeeType;
    }

    /**
     * Auto generated setter method
     *
     * @param param FeeType
     */
    public void setFeeType(java.lang.String param) {
      localFeeTypeTracker = param != null;

      this.localFeeType = param;
    }

    /** field for FileName */
    protected java.lang.String localFileName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFileNameTracker = false;

    public boolean isFileNameSpecified() {
      return localFileNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFileName() {
      return localFileName;
    }

    /**
     * Auto generated setter method
     *
     * @param param FileName
     */
    public void setFileName(java.lang.String param) {
      localFileNameTracker = param != null;

      this.localFileName = param;
    }

    /** field for FileNameDetails */
    protected java.lang.String localFileNameDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFileNameDetailsTracker = false;

    public boolean isFileNameDetailsSpecified() {
      return localFileNameDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFileNameDetails() {
      return localFileNameDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param FileNameDetails
     */
    public void setFileNameDetails(java.lang.String param) {
      localFileNameDetailsTracker = param != null;

      this.localFileNameDetails = param;
    }

    /** field for FilingDate */
    protected java.util.Calendar localFilingDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFilingDateTracker = false;

    public boolean isFilingDateSpecified() {
      return localFilingDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getFilingDate() {
      return localFilingDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param FilingDate
     */
    public void setFilingDate(java.util.Calendar param) {
      localFilingDateTracker = param != null;

      this.localFilingDate = param;
    }

    /** field for FilingFormName */
    protected java.lang.String localFilingFormName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFilingFormNameTracker = false;

    public boolean isFilingFormNameSpecified() {
      return localFilingFormNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFilingFormName() {
      return localFilingFormName;
    }

    /**
     * Auto generated setter method
     *
     * @param param FilingFormName
     */
    public void setFilingFormName(java.lang.String param) {
      localFilingFormNameTracker = param != null;

      this.localFilingFormName = param;
    }

    /** field for Flag */
    protected java.lang.String localFlag;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFlagTracker = false;

    public boolean isFlagSpecified() {
      return localFlagTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFlag() {
      return localFlag;
    }

    /**
     * Auto generated setter method
     *
     * @param param Flag
     */
    public void setFlag(java.lang.String param) {
      localFlagTracker = param != null;

      this.localFlag = param;
    }

    /** field for Fllpin */
    protected java.lang.String localFllpin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFllpinTracker = false;

    public boolean isFllpinSpecified() {
      return localFllpinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFllpin() {
      return localFllpin;
    }

    /**
     * Auto generated setter method
     *
     * @param param Fllpin
     */
    public void setFllpin(java.lang.String param) {
      localFllpinTracker = param != null;

      this.localFllpin = param;
    }

    /** field for ForeignAddress */
    protected java.lang.String localForeignAddress;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localForeignAddressTracker = false;

    public boolean isForeignAddressSpecified() {
      return localForeignAddressTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getForeignAddress() {
      return localForeignAddress;
    }

    /**
     * Auto generated setter method
     *
     * @param param ForeignAddress
     */
    public void setForeignAddress(java.lang.String param) {
      localForeignAddressTracker = param != null;

      this.localForeignAddress = param;
    }

    /** field for FormName */
    protected java.lang.String localFormName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFormNameTracker = false;

    public boolean isFormNameSpecified() {
      return localFormNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFormName() {
      return localFormName;
    }

    /**
     * Auto generated setter method
     *
     * @param param FormName
     */
    public void setFormName(java.lang.String param) {
      localFormNameTracker = param != null;

      this.localFormName = param;
    }

    /** field for FormOfContribution */
    protected java.lang.String localFormOfContribution;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFormOfContributionTracker = false;

    public boolean isFormOfContributionSpecified() {
      return localFormOfContributionTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFormOfContribution() {
      return localFormOfContribution;
    }

    /**
     * Auto generated setter method
     *
     * @param param FormOfContribution
     */
    public void setFormOfContribution(java.lang.String param) {
      localFormOfContributionTracker = param != null;

      this.localFormOfContribution = param;
    }

    /** field for FormStatus */
    protected java.lang.String localFormStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFormStatusTracker = false;

    public boolean isFormStatusSpecified() {
      return localFormStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFormStatus() {
      return localFormStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param FormStatus
     */
    public void setFormStatus(java.lang.String param) {
      localFormStatusTracker = param != null;

      this.localFormStatus = param;
    }

    /** field for Formsrn */
    protected java.lang.String localFormsrn;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFormsrnTracker = false;

    public boolean isFormsrnSpecified() {
      return localFormsrnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFormsrn() {
      return localFormsrn;
    }

    /**
     * Auto generated setter method
     *
     * @param param Formsrn
     */
    public void setFormsrn(java.lang.String param) {
      localFormsrnTracker = param != null;

      this.localFormsrn = param;
    }

    /** field for FullAddress */
    protected java.lang.String localFullAddress;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFullAddressTracker = false;

    public boolean isFullAddressSpecified() {
      return localFullAddressTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFullAddress() {
      return localFullAddress;
    }

    /**
     * Auto generated setter method
     *
     * @param param FullAddress
     */
    public void setFullAddress(java.lang.String param) {
      localFullAddressTracker = param != null;

      this.localFullAddress = param;
    }

    /** field for FullName */
    protected java.lang.String localFullName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFullNameTracker = false;

    public boolean isFullNameSpecified() {
      return localFullNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFullName() {
      return localFullName;
    }

    /**
     * Auto generated setter method
     *
     * @param param FullName
     */
    public void setFullName(java.lang.String param) {
      localFullNameTracker = param != null;

      this.localFullName = param;
    }

    /** field for Id */
    protected java.lang.String localId;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdTracker = false;

    public boolean isIdSpecified() {
      return localIdTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getId() {
      return localId;
    }

    /**
     * Auto generated setter method
     *
     * @param param Id
     */
    public void setId(java.lang.String param) {
      localIdTracker = param != null;

      this.localId = param;
    }

    /** field for IdNUMBER */
    protected java.lang.String localIdNUMBER;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdNUMBERTracker = false;

    public boolean isIdNUMBERSpecified() {
      return localIdNUMBERTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIdNUMBER() {
      return localIdNUMBER;
    }

    /**
     * Auto generated setter method
     *
     * @param param IdNUMBER
     */
    public void setIdNUMBER(java.lang.String param) {
      localIdNUMBERTracker = param != null;

      this.localIdNUMBER = param;
    }

    /** field for IdNumberE */
    protected java.lang.String localIdNumberE;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdNumberETracker = false;

    public boolean isIdNumberESpecified() {
      return localIdNumberETracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIdNumberE() {
      return localIdNumberE;
    }

    /**
     * Auto generated setter method
     *
     * @param param IdNumberE
     */
    public void setIdNumberE(java.lang.String param) {
      localIdNumberETracker = param != null;

      this.localIdNumberE = param;
    }

    /** field for IdNumberDetails */
    protected java.lang.String localIdNumberDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdNumberDetailsTracker = false;

    public boolean isIdNumberDetailsSpecified() {
      return localIdNumberDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIdNumberDetails() {
      return localIdNumberDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param IdNumberDetails
     */
    public void setIdNumberDetails(java.lang.String param) {
      localIdNumberDetailsTracker = param != null;

      this.localIdNumberDetails = param;
    }

    /** field for IdentificationNum */
    protected java.lang.String localIdentificationNum;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIdentificationNumTracker = false;

    public boolean isIdentificationNumSpecified() {
      return localIdentificationNumTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIdentificationNum() {
      return localIdentificationNum;
    }

    /**
     * Auto generated setter method
     *
     * @param param IdentificationNum
     */
    public void setIdentificationNum(java.lang.String param) {
      localIdentificationNumTracker = param != null;

      this.localIdentificationNum = param;
    }

    /** field for IncorpCountry */
    protected java.lang.String localIncorpCountry;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIncorpCountryTracker = false;

    public boolean isIncorpCountrySpecified() {
      return localIncorpCountryTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIncorpCountry() {
      return localIncorpCountry;
    }

    /**
     * Auto generated setter method
     *
     * @param param IncorpCountry
     */
    public void setIncorpCountry(java.lang.String param) {
      localIncorpCountryTracker = param != null;

      this.localIncorpCountry = param;
    }

    /** field for IncorporationDate */
    protected java.util.Calendar localIncorporationDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIncorporationDateTracker = false;

    public boolean isIncorporationDateSpecified() {
      return localIncorporationDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getIncorporationDate() {
      return localIncorporationDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param IncorporationDate
     */
    public void setIncorporationDate(java.util.Calendar param) {
      localIncorporationDateTracker = param != null;

      this.localIncorporationDate = param;
    }

    /** field for IndianAddress */
    protected java.lang.String localIndianAddress;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIndianAddressTracker = false;

    public boolean isIndianAddressSpecified() {
      return localIndianAddressTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIndianAddress() {
      return localIndianAddress;
    }

    /**
     * Auto generated setter method
     *
     * @param param IndianAddress
     */
    public void setIndianAddress(java.lang.String param) {
      localIndianAddressTracker = param != null;

      this.localIndianAddress = param;
    }

    /** field for LlpName */
    protected java.lang.String localLlpName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localLlpNameTracker = false;

    public boolean isLlpNameSpecified() {
      return localLlpNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getLlpName() {
      return localLlpName;
    }

    /**
     * Auto generated setter method
     *
     * @param param LlpName
     */
    public void setLlpName(java.lang.String param) {
      localLlpNameTracker = param != null;

      this.localLlpName = param;
    }

    /** field for Llpin */
    protected java.lang.String localLlpin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localLlpinTracker = false;

    public boolean isLlpinSpecified() {
      return localLlpinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getLlpin() {
      return localLlpin;
    }

    /**
     * Auto generated setter method
     *
     * @param param Llpin
     */
    public void setLlpin(java.lang.String param) {
      localLlpinTracker = param != null;

      this.localLlpin = param;
    }

    /** field for Migrated */
    protected java.lang.String localMigrated;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMigratedTracker = false;

    public boolean isMigratedSpecified() {
      return localMigratedTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getMigrated() {
      return localMigrated;
    }

    /**
     * Auto generated setter method
     *
     * @param param Migrated
     */
    public void setMigrated(java.lang.String param) {
      localMigratedTracker = param != null;

      this.localMigrated = param;
    }

    /** field for ModifyDate */
    protected java.util.Calendar localModifyDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localModifyDateTracker = false;

    public boolean isModifyDateSpecified() {
      return localModifyDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getModifyDate() {
      return localModifyDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param ModifyDate
     */
    public void setModifyDate(java.util.Calendar param) {
      localModifyDateTracker = param != null;

      this.localModifyDate = param;
    }

    /** field for MonetaryValue */
    protected java.lang.String localMonetaryValue;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMonetaryValueTracker = false;

    public boolean isMonetaryValueSpecified() {
      return localMonetaryValueTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getMonetaryValue() {
      return localMonetaryValue;
    }

    /**
     * Auto generated setter method
     *
     * @param param MonetaryValue
     */
    public void setMonetaryValue(java.lang.String param) {
      localMonetaryValueTracker = param != null;

      this.localMonetaryValue = param;
    }

    /** field for MsgDetails */
    protected java.lang.String localMsgDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMsgDetailsTracker = false;

    public boolean isMsgDetailsSpecified() {
      return localMsgDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getMsgDetails() {
      return localMsgDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param MsgDetails
     */
    public void setMsgDetails(java.lang.String param) {
      localMsgDetailsTracker = param != null;

      this.localMsgDetails = param;
    }

    /** field for MsgNumber */
    protected java.lang.String localMsgNumber;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMsgNumberTracker = false;

    public boolean isMsgNumberSpecified() {
      return localMsgNumberTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getMsgNumber() {
      return localMsgNumber;
    }

    /**
     * Auto generated setter method
     *
     * @param param MsgNumber
     */
    public void setMsgNumber(java.lang.String param) {
      localMsgNumberTracker = param != null;

      this.localMsgNumber = param;
    }

    /** field for Name */
    protected java.lang.String localName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNameTracker = false;

    public boolean isNameSpecified() {
      return localNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getName() {
      return localName;
    }

    /**
     * Auto generated setter method
     *
     * @param param Name
     */
    public void setName(java.lang.String param) {
      localNameTracker = param != null;

      this.localName = param;
    }

    /** field for NameChargeHolder */
    protected java.lang.String localNameChargeHolder;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNameChargeHolderTracker = false;

    public boolean isNameChargeHolderSpecified() {
      return localNameChargeHolderTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNameChargeHolder() {
      return localNameChargeHolder;
    }

    /**
     * Auto generated setter method
     *
     * @param param NameChargeHolder
     */
    public void setNameChargeHolder(java.lang.String param) {
      localNameChargeHolderTracker = param != null;

      this.localNameChargeHolder = param;
    }

    /** field for NameCompLLP */
    protected java.lang.String localNameCompLLP;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNameCompLLPTracker = false;

    public boolean isNameCompLLPSpecified() {
      return localNameCompLLPTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNameCompLLP() {
      return localNameCompLLP;
    }

    /**
     * Auto generated setter method
     *
     * @param param NameCompLLP
     */
    public void setNameCompLLP(java.lang.String param) {
      localNameCompLLPTracker = param != null;

      this.localNameCompLLP = param;
    }

    /** field for NameFull */
    protected java.lang.String localNameFull;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNameFullTracker = false;

    public boolean isNameFullSpecified() {
      return localNameFullTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNameFull() {
      return localNameFull;
    }

    /**
     * Auto generated setter method
     *
     * @param param NameFull
     */
    public void setNameFull(java.lang.String param) {
      localNameFullTracker = param != null;

      this.localNameFull = param;
    }

    /** field for NameLastTwo */
    protected java.lang.String localNameLastTwo;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNameLastTwoTracker = false;

    public boolean isNameLastTwoSpecified() {
      return localNameLastTwoTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNameLastTwo() {
      return localNameLastTwo;
    }

    /**
     * Auto generated setter method
     *
     * @param param NameLastTwo
     */
    public void setNameLastTwo(java.lang.String param) {
      localNameLastTwoTracker = param != null;

      this.localNameLastTwo = param;
    }

    /** field for NewName */
    protected java.lang.String localNewName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNewNameTracker = false;

    public boolean isNewNameSpecified() {
      return localNewNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNewName() {
      return localNewName;
    }

    /**
     * Auto generated setter method
     *
     * @param param NewName
     */
    public void setNewName(java.lang.String param) {
      localNewNameTracker = param != null;

      this.localNewName = param;
    }

    /** field for NomineeBodyCorp */
    protected java.lang.String localNomineeBodyCorp;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNomineeBodyCorpTracker = false;

    public boolean isNomineeBodyCorpSpecified() {
      return localNomineeBodyCorpTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNomineeBodyCorp() {
      return localNomineeBodyCorp;
    }

    /**
     * Auto generated setter method
     *
     * @param param NomineeBodyCorp
     */
    public void setNomineeBodyCorp(java.lang.String param) {
      localNomineeBodyCorpTracker = param != null;

      this.localNomineeBodyCorp = param;
    }

    /** field for NumOfDesigPartners */
    protected java.lang.String localNumOfDesigPartners;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumOfDesigPartnersTracker = false;

    public boolean isNumOfDesigPartnersSpecified() {
      return localNumOfDesigPartnersTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNumOfDesigPartners() {
      return localNumOfDesigPartners;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumOfDesigPartners
     */
    public void setNumOfDesigPartners(java.lang.String param) {
      localNumOfDesigPartnersTracker = param != null;

      this.localNumOfDesigPartners = param;
    }

    /** field for NumOfLLPPartner */
    protected java.lang.String localNumOfLLPPartner;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumOfLLPPartnerTracker = false;

    public boolean isNumOfLLPPartnerSpecified() {
      return localNumOfLLPPartnerTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNumOfLLPPartner() {
      return localNumOfLLPPartner;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumOfLLPPartner
     */
    public void setNumOfLLPPartner(java.lang.String param) {
      localNumOfLLPPartnerTracker = param != null;

      this.localNumOfLLPPartner = param;
    }

    /** field for NumOfPartners */
    protected java.lang.String localNumOfPartners;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumOfPartnersTracker = false;

    public boolean isNumOfPartnersSpecified() {
      return localNumOfPartnersTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNumOfPartners() {
      return localNumOfPartners;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumOfPartners
     */
    public void setNumOfPartners(java.lang.String param) {
      localNumOfPartnersTracker = param != null;

      this.localNumOfPartners = param;
    }

    /** field for NumOfmembers */
    protected int localNumOfmembers;

    /**
     * Auto generated getter method
     *
     * @return int
     */
    public int getNumOfmembers() {
      return localNumOfmembers;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumOfmembers
     */
    public void setNumOfmembers(int param) {

      this.localNumOfmembers = param;
    }

    /** field for NumberOfCopies */
    protected java.lang.String localNumberOfCopies;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumberOfCopiesTracker = false;

    public boolean isNumberOfCopiesSpecified() {
      return localNumberOfCopiesTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNumberOfCopies() {
      return localNumberOfCopies;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumberOfCopies
     */
    public void setNumberOfCopies(java.lang.String param) {
      localNumberOfCopiesTracker = param != null;

      this.localNumberOfCopies = param;
    }

    /** field for NumberOfLLPDircetor */
    protected java.lang.String localNumberOfLLPDircetor;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumberOfLLPDircetorTracker = false;

    public boolean isNumberOfLLPDircetorSpecified() {
      return localNumberOfLLPDircetorTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNumberOfLLPDircetor() {
      return localNumberOfLLPDircetor;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumberOfLLPDircetor
     */
    public void setNumberOfLLPDircetor(java.lang.String param) {
      localNumberOfLLPDircetorTracker = param != null;

      this.localNumberOfLLPDircetor = param;
    }

    /** field for NumberOfPages */
    protected java.lang.String localNumberOfPages;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localNumberOfPagesTracker = false;

    public boolean isNumberOfPagesSpecified() {
      return localNumberOfPagesTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getNumberOfPages() {
      return localNumberOfPages;
    }

    /**
     * Auto generated setter method
     *
     * @param param NumberOfPages
     */
    public void setNumberOfPages(java.lang.String param) {
      localNumberOfPagesTracker = param != null;

      this.localNumberOfPages = param;
    }

    /** field for ObligContribution */
    protected java.math.BigDecimal localObligContribution;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localObligContributionTracker = false;

    public boolean isObligContributionSpecified() {
      return localObligContributionTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getObligContribution() {
      return localObligContribution;
    }

    /**
     * Auto generated setter method
     *
     * @param param ObligContribution
     */
    public void setObligContribution(java.math.BigDecimal param) {
      localObligContributionTracker = param != null;

      this.localObligContribution = param;
    }

    /** field for OldEndDate */
    protected java.util.Calendar localOldEndDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localOldEndDateTracker = false;

    public boolean isOldEndDateSpecified() {
      return localOldEndDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getOldEndDate() {
      return localOldEndDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param OldEndDate
     */
    public void setOldEndDate(java.util.Calendar param) {
      localOldEndDateTracker = param != null;

      this.localOldEndDate = param;
    }

    /** field for OldName */
    protected java.lang.String localOldName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localOldNameTracker = false;

    public boolean isOldNameSpecified() {
      return localOldNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getOldName() {
      return localOldName;
    }

    /**
     * Auto generated setter method
     *
     * @param param OldName
     */
    public void setOldName(java.lang.String param) {
      localOldNameTracker = param != null;

      this.localOldName = param;
    }

    /** field for OldStartDate */
    protected java.util.Calendar localOldStartDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localOldStartDateTracker = false;

    public boolean isOldStartDateSpecified() {
      return localOldStartDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getOldStartDate() {
      return localOldStartDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param OldStartDate
     */
    public void setOldStartDate(java.util.Calendar param) {
      localOldStartDateTracker = param != null;

      this.localOldStartDate = param;
    }

    /** field for OriginalDate */
    protected java.util.Calendar localOriginalDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localOriginalDateTracker = false;

    public boolean isOriginalDateSpecified() {
      return localOriginalDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getOriginalDate() {
      return localOriginalDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param OriginalDate
     */
    public void setOriginalDate(java.util.Calendar param) {
      localOriginalDateTracker = param != null;

      this.localOriginalDate = param;
    }

    /** field for PageNumbers */
    protected java.lang.String localPageNumbers;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPageNumbersTracker = false;

    public boolean isPageNumbersSpecified() {
      return localPageNumbersTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPageNumbers() {
      return localPageNumbers;
    }

    /**
     * Auto generated setter method
     *
     * @param param PageNumbers
     */
    public void setPageNumbers(java.lang.String param) {
      localPageNumbersTracker = param != null;

      this.localPageNumbers = param;
    }

    /** field for PaidupCapital */
    protected java.math.BigDecimal localPaidupCapital;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPaidupCapitalTracker = false;

    public boolean isPaidupCapitalSpecified() {
      return localPaidupCapitalTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getPaidupCapital() {
      return localPaidupCapital;
    }

    /**
     * Auto generated setter method
     *
     * @param param PaidupCapital
     */
    public void setPaidupCapital(java.math.BigDecimal param) {
      localPaidupCapitalTracker = param != null;

      this.localPaidupCapital = param;
    }

    /** field for Pan */
    protected java.lang.String localPan;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPanTracker = false;

    public boolean isPanSpecified() {
      return localPanTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPan() {
      return localPan;
    }

    /**
     * Auto generated setter method
     *
     * @param param Pan
     */
    public void setPan(java.lang.String param) {
      localPanTracker = param != null;

      this.localPan = param;
    }

    /** field for Partner */
    protected java.lang.String localPartner;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPartnerTracker = false;

    public boolean isPartnerSpecified() {
      return localPartnerTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPartner() {
      return localPartner;
    }

    /**
     * Auto generated setter method
     *
     * @param param Partner
     */
    public void setPartner(java.lang.String param) {
      localPartnerTracker = param != null;

      this.localPartner = param;
    }

    /** field for PartnerDesignation */
    protected java.lang.String localPartnerDesignation;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPartnerDesignationTracker = false;

    public boolean isPartnerDesignationSpecified() {
      return localPartnerDesignationTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPartnerDesignation() {
      return localPartnerDesignation;
    }

    /**
     * Auto generated setter method
     *
     * @param param PartnerDesignation
     */
    public void setPartnerDesignation(java.lang.String param) {
      localPartnerDesignationTracker = param != null;

      this.localPartnerDesignation = param;
    }

    /** field for PartnerFathName */
    protected java.lang.String localPartnerFathName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPartnerFathNameTracker = false;

    public boolean isPartnerFathNameSpecified() {
      return localPartnerFathNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPartnerFathName() {
      return localPartnerFathName;
    }

    /**
     * Auto generated setter method
     *
     * @param param PartnerFathName
     */
    public void setPartnerFathName(java.lang.String param) {
      localPartnerFathNameTracker = param != null;

      this.localPartnerFathName = param;
    }

    /** field for PartnerName */
    protected java.lang.String localPartnerName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPartnerNameTracker = false;

    public boolean isPartnerNameSpecified() {
      return localPartnerNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPartnerName() {
      return localPartnerName;
    }

    /**
     * Auto generated setter method
     *
     * @param param PartnerName
     */
    public void setPartnerName(java.lang.String param) {
      localPartnerNameTracker = param != null;

      this.localPartnerName = param;
    }

    /** field for PartnersName */
    protected java.lang.String localPartnersName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPartnersNameTracker = false;

    public boolean isPartnersNameSpecified() {
      return localPartnersNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPartnersName() {
      return localPartnersName;
    }

    /**
     * Auto generated setter method
     *
     * @param param PartnersName
     */
    public void setPartnersName(java.lang.String param) {
      localPartnersNameTracker = param != null;

      this.localPartnersName = param;
    }

    /** field for PayPerPage */
    protected java.lang.String localPayPerPage;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPayPerPageTracker = false;

    public boolean isPayPerPageSpecified() {
      return localPayPerPageTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPayPerPage() {
      return localPayPerPage;
    }

    /**
     * Auto generated setter method
     *
     * @param param PayPerPage
     */
    public void setPayPerPage(java.lang.String param) {
      localPayPerPageTracker = param != null;

      this.localPayPerPage = param;
    }

    /** field for PaymentStatus */
    protected java.lang.String localPaymentStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localPaymentStatusTracker = false;

    public boolean isPaymentStatusSpecified() {
      return localPaymentStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getPaymentStatus() {
      return localPaymentStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param PaymentStatus
     */
    public void setPaymentStatus(java.lang.String param) {
      localPaymentStatusTracker = param != null;

      this.localPaymentStatus = param;
    }

    /** field for ProfitPercentage */
    protected java.math.BigDecimal localProfitPercentage;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localProfitPercentageTracker = false;

    public boolean isProfitPercentageSpecified() {
      return localProfitPercentageTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getProfitPercentage() {
      return localProfitPercentage;
    }

    /**
     * Auto generated setter method
     *
     * @param param ProfitPercentage
     */
    public void setProfitPercentage(java.math.BigDecimal param) {
      localProfitPercentageTracker = param != null;

      this.localProfitPercentage = param;
    }

    /** field for RefundStatus */
    protected java.lang.String localRefundStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRefundStatusTracker = false;

    public boolean isRefundStatusSpecified() {
      return localRefundStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRefundStatus() {
      return localRefundStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param RefundStatus
     */
    public void setRefundStatus(java.lang.String param) {
      localRefundStatusTracker = param != null;

      this.localRefundStatus = param;
    }

    /** field for RegAddress */
    protected java.lang.String localRegAddress;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRegAddressTracker = false;

    public boolean isRegAddressSpecified() {
      return localRegAddressTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRegAddress() {
      return localRegAddress;
    }

    /**
     * Auto generated setter method
     *
     * @param param RegAddress
     */
    public void setRegAddress(java.lang.String param) {
      localRegAddressTracker = param != null;

      this.localRegAddress = param;
    }

    /** field for Region */
    protected java.lang.String localRegion;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRegionTracker = false;

    public boolean isRegionSpecified() {
      return localRegionTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRegion() {
      return localRegion;
    }

    /**
     * Auto generated setter method
     *
     * @param param Region
     */
    public void setRegion(java.lang.String param) {
      localRegionTracker = param != null;

      this.localRegion = param;
    }

    /** field for RegistrationDate */
    protected java.util.Calendar localRegistrationDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRegistrationDateTracker = false;

    public boolean isRegistrationDateSpecified() {
      return localRegistrationDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getRegistrationDate() {
      return localRegistrationDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param RegistrationDate
     */
    public void setRegistrationDate(java.util.Calendar param) {
      localRegistrationDateTracker = param != null;

      this.localRegistrationDate = param;
    }

    /** field for RelationNAMEFULL */
    protected java.lang.String localRelationNAMEFULL;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRelationNAMEFULLTracker = false;

    public boolean isRelationNAMEFULLSpecified() {
      return localRelationNAMEFULLTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRelationNAMEFULL() {
      return localRelationNAMEFULL;
    }

    /**
     * Auto generated setter method
     *
     * @param param RelationNAMEFULL
     */
    public void setRelationNAMEFULL(java.lang.String param) {
      localRelationNAMEFULLTracker = param != null;

      this.localRelationNAMEFULL = param;
    }

    /** field for Remarks */
    protected java.lang.String localRemarks;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRemarksTracker = false;

    public boolean isRemarksSpecified() {
      return localRemarksTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRemarks() {
      return localRemarks;
    }

    /**
     * Auto generated setter method
     *
     * @param param Remarks
     */
    public void setRemarks(java.lang.String param) {
      localRemarksTracker = param != null;

      this.localRemarks = param;
    }

    /** field for ResubDocId */
    protected java.lang.String localResubDocId;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localResubDocIdTracker = false;

    public boolean isResubDocIdSpecified() {
      return localResubDocIdTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getResubDocId() {
      return localResubDocId;
    }

    /**
     * Auto generated setter method
     *
     * @param param ResubDocId
     */
    public void setResubDocId(java.lang.String param) {
      localResubDocIdTracker = param != null;

      this.localResubDocId = param;
    }

    /** field for Roc */
    protected java.lang.String localRoc;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRocTracker = false;

    public boolean isRocSpecified() {
      return localRocTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRoc() {
      return localRoc;
    }

    /**
     * Auto generated setter method
     *
     * @param param Roc
     */
    public void setRoc(java.lang.String param) {
      localRocTracker = param != null;

      this.localRoc = param;
    }

    /** field for SatisfyDate */
    protected java.util.Calendar localSatisfyDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSatisfyDateTracker = false;

    public boolean isSatisfyDateSpecified() {
      return localSatisfyDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getSatisfyDate() {
      return localSatisfyDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param SatisfyDate
     */
    public void setSatisfyDate(java.util.Calendar param) {
      localSatisfyDateTracker = param != null;

      this.localSatisfyDate = param;
    }

    /** field for Scnid */
    protected java.lang.String localScnid;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localScnidTracker = false;

    public boolean isScnidSpecified() {
      return localScnidTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getScnid() {
      return localScnid;
    }

    /**
     * Auto generated setter method
     *
     * @param param Scnid
     */
    public void setScnid(java.lang.String param) {
      localScnidTracker = param != null;

      this.localScnid = param;
    }

    /** field for ServiceType */
    protected java.lang.String localServiceType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localServiceTypeTracker = false;

    public boolean isServiceTypeSpecified() {
      return localServiceTypeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getServiceType() {
      return localServiceType;
    }

    /**
     * Auto generated setter method
     *
     * @param param ServiceType
     */
    public void setServiceType(java.lang.String param) {
      localServiceTypeTracker = param != null;

      this.localServiceType = param;
    }

    /** field for ServiceTypeDesc */
    protected java.lang.String localServiceTypeDesc;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localServiceTypeDescTracker = false;

    public boolean isServiceTypeDescSpecified() {
      return localServiceTypeDescTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getServiceTypeDesc() {
      return localServiceTypeDesc;
    }

    /**
     * Auto generated setter method
     *
     * @param param ServiceTypeDesc
     */
    public void setServiceTypeDesc(java.lang.String param) {
      localServiceTypeDescTracker = param != null;

      this.localServiceTypeDesc = param;
    }

    /** field for SignatoryName */
    protected java.lang.String localSignatoryName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSignatoryNameTracker = false;

    public boolean isSignatoryNameSpecified() {
      return localSignatoryNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getSignatoryName() {
      return localSignatoryName;
    }

    /**
     * Auto generated setter method
     *
     * @param param SignatoryName
     */
    public void setSignatoryName(java.lang.String param) {
      localSignatoryNameTracker = param != null;

      this.localSignatoryName = param;
    }

    /** field for Srn */
    protected java.lang.String localSrn;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSrnTracker = false;

    public boolean isSrnSpecified() {
      return localSrnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getSrn() {
      return localSrn;
    }

    /**
     * Auto generated setter method
     *
     * @param param Srn
     */
    public void setSrn(java.lang.String param) {
      localSrnTracker = param != null;

      this.localSrn = param;
    }

    /** field for SrnNumber */
    protected java.lang.String localSrnNumber;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSrnNumberTracker = false;

    public boolean isSrnNumberSpecified() {
      return localSrnNumberTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getSrnNumber() {
      return localSrnNumber;
    }

    /**
     * Auto generated setter method
     *
     * @param param SrnNumber
     */
    public void setSrnNumber(java.lang.String param) {
      localSrnNumberTracker = param != null;

      this.localSrnNumber = param;
    }

    /** field for SrnStatus */
    protected java.lang.String localSrnStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSrnStatusTracker = false;

    public boolean isSrnStatusSpecified() {
      return localSrnStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getSrnStatus() {
      return localSrnStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param SrnStatus
     */
    public void setSrnStatus(java.lang.String param) {
      localSrnStatusTracker = param != null;

      this.localSrnStatus = param;
    }

    /** field for StampDuty */
    protected java.lang.String localStampDuty;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStampDutyTracker = false;

    public boolean isStampDutySpecified() {
      return localStampDutyTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getStampDuty() {
      return localStampDuty;
    }

    /**
     * Auto generated setter method
     *
     * @param param StampDuty
     */
    public void setStampDuty(java.lang.String param) {
      localStampDutyTracker = param != null;

      this.localStampDuty = param;
    }

    /** field for StampDutyFee */
    protected java.math.BigDecimal localStampDutyFee;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStampDutyFeeTracker = false;

    public boolean isStampDutyFeeSpecified() {
      return localStampDutyFeeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getStampDutyFee() {
      return localStampDutyFee;
    }

    /**
     * Auto generated setter method
     *
     * @param param StampDutyFee
     */
    public void setStampDutyFee(java.math.BigDecimal param) {
      localStampDutyFeeTracker = param != null;

      this.localStampDutyFee = param;
    }

    /** field for StampDutyFeeType */
    protected java.lang.String localStampDutyFeeType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStampDutyFeeTypeTracker = false;

    public boolean isStampDutyFeeTypeSpecified() {
      return localStampDutyFeeTypeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getStampDutyFeeType() {
      return localStampDutyFeeType;
    }

    /**
     * Auto generated setter method
     *
     * @param param StampDutyFeeType
     */
    public void setStampDutyFeeType(java.lang.String param) {
      localStampDutyFeeTypeTracker = param != null;

      this.localStampDutyFeeType = param;
    }

    /** field for StartTimeStamp */
    protected java.lang.String localStartTimeStamp;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStartTimeStampTracker = false;

    public boolean isStartTimeStampSpecified() {
      return localStartTimeStampTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getStartTimeStamp() {
      return localStartTimeStamp;
    }

    /**
     * Auto generated setter method
     *
     * @param param StartTimeStamp
     */
    public void setStartTimeStamp(java.lang.String param) {
      localStartTimeStampTracker = param != null;

      this.localStartTimeStamp = param;
    }

    /** field for State */
    protected java.lang.String localState;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStateTracker = false;

    public boolean isStateSpecified() {
      return localStateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getState() {
      return localState;
    }

    /**
     * Auto generated setter method
     *
     * @param param State
     */
    public void setState(java.lang.String param) {
      localStateTracker = param != null;

      this.localState = param;
    }

    /** field for Status */
    protected java.lang.String localStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatusTracker = false;

    public boolean isStatusSpecified() {
      return localStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getStatus() {
      return localStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param Status
     */
    public void setStatus(java.lang.String param) {
      localStatusTracker = param != null;

      this.localStatus = param;
    }

    /** field for StatusDetails */
    protected java.lang.String localStatusDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatusDetailsTracker = false;

    public boolean isStatusDetailsSpecified() {
      return localStatusDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getStatusDetails() {
      return localStatusDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param StatusDetails
     */
    public void setStatusDetails(java.lang.String param) {
      localStatusDetailsTracker = param != null;

      this.localStatusDetails = param;
    }

    /** field for StatusFndDetails */
    protected java.lang.String localStatusFndDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localStatusFndDetailsTracker = false;

    public boolean isStatusFndDetailsSpecified() {
      return localStatusFndDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getStatusFndDetails() {
      return localStatusFndDetails;
    }

    /**
     * Auto generated setter method
     *
     * @param param StatusFndDetails
     */
    public void setStatusFndDetails(java.lang.String param) {
      localStatusFndDetailsTracker = param != null;

      this.localStatusFndDetails = param;
    }

    /** field for SubCategory */
    protected java.lang.String localSubCategory;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSubCategoryTracker = false;

    public boolean isSubCategorySpecified() {
      return localSubCategoryTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getSubCategory() {
      return localSubCategory;
    }

    /**
     * Auto generated setter method
     *
     * @param param SubCategory
     */
    public void setSubCategory(java.lang.String param) {
      localSubCategoryTracker = param != null;

      this.localSubCategory = param;
    }

    /** field for TotalContribution */
    protected java.lang.String localTotalContribution;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTotalContributionTracker = false;

    public boolean isTotalContributionSpecified() {
      return localTotalContributionTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getTotalContribution() {
      return localTotalContribution;
    }

    /**
     * Auto generated setter method
     *
     * @param param TotalContribution
     */
    public void setTotalContribution(java.lang.String param) {
      localTotalContributionTracker = param != null;

      this.localTotalContribution = param;
    }

    /** field for TotalStampDuty */
    protected java.math.BigDecimal localTotalStampDuty;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTotalStampDutyTracker = false;

    public boolean isTotalStampDutySpecified() {
      return localTotalStampDutyTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.math.BigDecimal
     */
    public java.math.BigDecimal getTotalStampDuty() {
      return localTotalStampDuty;
    }

    /**
     * Auto generated setter method
     *
     * @param param TotalStampDuty
     */
    public void setTotalStampDuty(java.math.BigDecimal param) {
      localTotalStampDutyTracker = param != null;

      this.localTotalStampDuty = param;
    }

    /** field for TransactionDate */
    protected java.util.Calendar localTransactionDate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTransactionDateTracker = false;

    public boolean isTransactionDateSpecified() {
      return localTransactionDateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.util.Calendar
     */
    public java.util.Calendar getTransactionDate() {
      return localTransactionDate;
    }

    /**
     * Auto generated setter method
     *
     * @param param TransactionDate
     */
    public void setTransactionDate(java.util.Calendar param) {
      localTransactionDateTracker = param != null;

      this.localTransactionDate = param;
    }

    /** field for Type */
    protected java.lang.String localType;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTypeTracker = false;

    public boolean isTypeSpecified() {
      return localTypeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getType() {
      return localType;
    }

    /**
     * Auto generated setter method
     *
     * @param param Type
     */
    public void setType(java.lang.String param) {
      localTypeTracker = param != null;

      this.localType = param;
    }

    /** field for TypeOfChange */
    protected java.lang.String localTypeOfChange;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTypeOfChangeTracker = false;

    public boolean isTypeOfChangeSpecified() {
      return localTypeOfChangeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getTypeOfChange() {
      return localTypeOfChange;
    }

    /**
     * Auto generated setter method
     *
     * @param param TypeOfChange
     */
    public void setTypeOfChange(java.lang.String param) {
      localTypeOfChangeTracker = param != null;

      this.localTypeOfChange = param;
    }

    /** field for TypeOfOffice */
    protected java.lang.String localTypeOfOffice;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTypeOfOfficeTracker = false;

    public boolean isTypeOfOfficeSpecified() {
      return localTypeOfOfficeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getTypeOfOffice() {
      return localTypeOfOffice;
    }

    /**
     * Auto generated setter method
     *
     * @param param TypeOfOffice
     */
    public void setTypeOfOffice(java.lang.String param) {
      localTypeOfOfficeTracker = param != null;

      this.localTypeOfOffice = param;
    }

    /** field for UserID */
    protected java.lang.String localUserID;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localUserIDTracker = false;

    public boolean isUserIDSpecified() {
      return localUserIDTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getUserID() {
      return localUserID;
    }

    /**
     * Auto generated setter method
     *
     * @param param UserID
     */
    public void setUserID(java.lang.String param) {
      localUserIDTracker = param != null;

      this.localUserID = param;
    }

    /** field for WhetherResIndia */
    protected java.lang.String localWhetherResIndia;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localWhetherResIndiaTracker = false;

    public boolean isWhetherResIndiaSpecified() {
      return localWhetherResIndiaTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getWhetherResIndia() {
      return localWhetherResIndia;
    }

    /**
     * Auto generated setter method
     *
     * @param param WhetherResIndia
     */
    public void setWhetherResIndia(java.lang.String param) {
      localWhetherResIndiaTracker = param != null;

      this.localWhetherResIndia = param;
    }

    /** field for SNo */
    protected int localSNo;

    /**
     * Auto generated getter method
     *
     * @return int
     */
    public int getSNo() {
      return localSNo;
    }

    /**
     * Auto generated setter method
     *
     * @param param SNo
     */
    public void setSNo(int param) {

      this.localSNo = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":itemDetails",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "itemDetails", xmlWriter);
        }
      }
      if (localActivityDescTracker) {
        namespace = "";
        writeStartElement(null, namespace, "activityDesc", xmlWriter);

        if (localActivityDesc == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("activityDesc cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localActivityDesc);
        }

        xmlWriter.writeEndElement();
      }
      if (localAllPagesTracker) {
        namespace = "";
        writeStartElement(null, namespace, "allPages", xmlWriter);

        if (localAllPages == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("allPages cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localAllPages);
        }

        xmlWriter.writeEndElement();
      }
      if (localAmountSecuredTracker) {
        namespace = "";
        writeStartElement(null, namespace, "amountSecured", xmlWriter);

        if (localAmountSecured == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("amountSecured cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAmountSecured));
        }

        xmlWriter.writeEndElement();
      }
      if (localAssetTracker) {
        namespace = "";
        writeStartElement(null, namespace, "asset", xmlWriter);

        if (localAsset == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("asset cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localAsset);
        }

        xmlWriter.writeEndElement();
      }
      if (localAsset1Tracker) {
        namespace = "";
        writeStartElement(null, namespace, "asset1", xmlWriter);

        if (localAsset1 == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("asset1 cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localAsset1);
        }

        xmlWriter.writeEndElement();
      }
      if (localAuthCapitalTracker) {
        namespace = "";
        writeStartElement(null, namespace, "authCapital", xmlWriter);

        if (localAuthCapital == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("authCapital cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAuthCapital));
        }

        xmlWriter.writeEndElement();
      }
      if (localBankNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "bankName", xmlWriter);

        if (localBankName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("bankName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localBankName);
        }

        xmlWriter.writeEndElement();
      }
      if (localBankRegisteredTracker) {
        namespace = "";
        writeStartElement(null, namespace, "bankRegistered", xmlWriter);

        if (localBankRegistered == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("bankRegistered cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localBankRegistered);
        }

        xmlWriter.writeEndElement();
      }
      if (localBeginDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "beginDate", xmlWriter);

        if (localBeginDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("beginDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBeginDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localBirthDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "birthDate", xmlWriter);

        if (localBirthDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("birthDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBirthDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localBpNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "bpName", xmlWriter);

        if (localBpName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("bpName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localBpName);
        }

        xmlWriter.writeEndElement();
      }
      if (localBytesTracker) {
        namespace = "";
        writeStartElement(null, namespace, "bytes", xmlWriter);

        if (localBytes != null) {
          try {
            org.apache.axiom.util.stax.XMLStreamWriterUtils.writeDataHandler(
                xmlWriter, localBytes, null, true);
          } catch (java.io.IOException ex) {
            throw new javax.xml.stream.XMLStreamException(
                "Unable to read data handler for bytes", ex);
          }
        } else {

        }

        xmlWriter.writeEndElement();
      }
      if (localCategoryTracker) {
        namespace = "";
        writeStartElement(null, namespace, "category", xmlWriter);

        if (localCategory == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("category cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCategory);
        }

        xmlWriter.writeEndElement();
      }
      if (localCertstatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "certstatus", xmlWriter);

        if (localCertstatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("certstatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCertstatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localChargeAmountTracker) {
        namespace = "";
        writeStartElement(null, namespace, "chargeAmount", xmlWriter);

        if (localChargeAmount == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("chargeAmount cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChargeAmount));
        }

        xmlWriter.writeEndElement();
      }
      if (localChargeIDTracker) {
        namespace = "";
        writeStartElement(null, namespace, "chargeID", xmlWriter);

        if (localChargeID == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("chargeID cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localChargeID);
        }

        xmlWriter.writeEndElement();
      }
      if (localChargeStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "chargeStatus", xmlWriter);

        if (localChargeStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("chargeStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localChargeStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localCinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "cin", xmlWriter);

        if (localCin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("cin cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCin);
        }

        xmlWriter.writeEndElement();
      }
      if (localCinLLPINTracker) {
        namespace = "";
        writeStartElement(null, namespace, "cinLLPIN", xmlWriter);

        if (localCinLLPIN == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("cinLLPIN cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCinLLPIN);
        }

        xmlWriter.writeEndElement();
      }
      if (localCinLLPINDetailsTracker) {
        namespace = "";
        writeStartElement(null, namespace, "cinLLPINDetails", xmlWriter);

        if (localCinLLPINDetails == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("cinLLPINDetails cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCinLLPINDetails);
        }

        xmlWriter.writeEndElement();
      }
      if (localCinLLPInETracker) {
        namespace = "";
        writeStartElement(null, namespace, "cinLLPIn", xmlWriter);

        if (localCinLLPInE == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("cinLLPIn cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCinLLPInE);
        }

        xmlWriter.writeEndElement();
      }
      if (localCommentTracker) {
        namespace = "";
        writeStartElement(null, namespace, "comment", xmlWriter);

        if (localComment == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("comment cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localComment);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompClassTracker) {
        namespace = "";
        writeStartElement(null, namespace, "compClass", xmlWriter);

        if (localCompClass == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("compClass cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompClass);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompLLPStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "compLLPStatus", xmlWriter);

        if (localCompLLPStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("compLLPStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompLLPStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompanyFlagTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyFlag", xmlWriter);

        if (localCompanyFlag == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyFlag cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyFlag);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompanyIDTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyID", xmlWriter);

        if (localCompanyID == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyID cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyID);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompanyLLPNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyLLPName", xmlWriter);

        if (localCompanyLLPName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyLLPName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyLLPName);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompanyNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyName", xmlWriter);

        if (localCompanyName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyName);
        }

        xmlWriter.writeEndElement();
      }
      if (localComplaintTypeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "complaintType", xmlWriter);

        if (localComplaintType == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("complaintType cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localComplaintType);
        }

        xmlWriter.writeEndElement();
      }
      if (localContribRecandAccTracker) {
        namespace = "";
        writeStartElement(null, namespace, "contribRecandAcc", xmlWriter);

        if (localContribRecandAcc == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("contribRecandAcc cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localContribRecandAcc);
        }

        xmlWriter.writeEndElement();
      }
      if (localCopyChallanTracker) {
        namespace = "";
        writeStartElement(null, namespace, "copyChallan", xmlWriter);

        if (localCopyChallan == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("copyChallan cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCopyChallan);
        }

        xmlWriter.writeEndElement();
      }
      if (localCrDatTracker) {
        namespace = "";
        writeStartElement(null, namespace, "crDat", xmlWriter);

        if (localCrDat == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("crDat cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCrDat));
        }

        xmlWriter.writeEndElement();
      }
      if (localCrDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "crDate", xmlWriter);

        if (localCrDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("crDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCrDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localCreationDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "creationDate", xmlWriter);

        if (localCreationDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("creationDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCreationDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localCurrTracker) {
        namespace = "";
        writeStartElement(null, namespace, "curr", xmlWriter);

        if (localCurr == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("curr cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCurr);
        }

        xmlWriter.writeEndElement();
      }
      if (localCurrentDesignationTracker) {
        namespace = "";
        writeStartElement(null, namespace, "currentDesignation", xmlWriter);

        if (localCurrentDesignation == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "currentDesignation cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCurrentDesignation);
        }

        xmlWriter.writeEndElement();
      }
      if (localDateAdditionTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateAddition", xmlWriter);

        if (localDateAddition == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateAddition cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateAddition));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateAppointmentTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateAppointment", xmlWriter);

        if (localDateAppointment == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateAppointment cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localDateAppointment));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateCessationTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateCessation", xmlWriter);

        if (localDateCessation == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateCessation cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateCessation));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateFromTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateFrom", xmlWriter);

        if (localDateFrom == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateFrom cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateFrom));
        }

        xmlWriter.writeEndElement();
      }
      namespace = "";
      writeStartElement(null, namespace, "dateLastModification", xmlWriter);

      if (localDateLastModification == java.lang.Long.MIN_VALUE) {

        throw new org.apache.axis2.databinding.ADBException(
            "dateLastModification cannot be null!!");

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                localDateLastModification));
      }

      xmlWriter.writeEndElement();
      if (localDateOfAppointmentTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateOfAppointment", xmlWriter);

        if (localDateOfAppointment == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateOfAppointment cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localDateOfAppointment));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateOfCessationTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateOfCessation", xmlWriter);

        if (localDateOfCessation == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateOfCessation cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localDateOfCessation));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateOfChangeDesigTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateOfChangeDesig", xmlWriter);

        if (localDateOfChangeDesig == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateOfChangeDesig cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localDateOfChangeDesig));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateRoleCheckTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateRoleCheck", xmlWriter);

        if (localDateRoleCheck == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateRoleCheck cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateRoleCheck));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateToTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateTo", xmlWriter);

        if (localDateTo == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateTo cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateTo));
        }

        xmlWriter.writeEndElement();
      }
      if (localDateofFilingTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dateofFiling", xmlWriter);

        if (localDateofFiling == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dateofFiling cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateofFiling));
        }

        xmlWriter.writeEndElement();
      }
      if (localDefaultYearTracker) {
        namespace = "";
        writeStartElement(null, namespace, "defaultYear", xmlWriter);

        if (localDefaultYear == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("defaultYear cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDefaultYear);
        }

        xmlWriter.writeEndElement();
      }
      if (localDefaultingStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "defaultingStatus", xmlWriter);

        if (localDefaultingStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("defaultingStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDefaultingStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localDescTracker) {
        namespace = "";
        writeStartElement(null, namespace, "desc", xmlWriter);

        if (localDesc == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("desc cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDesc);
        }

        xmlWriter.writeEndElement();
      }
      if (localDesigFLTracker) {
        namespace = "";
        writeStartElement(null, namespace, "desigFL", xmlWriter);

        if (localDesigFL == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("desigFL cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDesigFL);
        }

        xmlWriter.writeEndElement();
      }
      if (localDesigShortTracker) {
        namespace = "";
        writeStartElement(null, namespace, "desigShort", xmlWriter);

        if (localDesigShort == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("desigShort cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDesigShort);
        }

        xmlWriter.writeEndElement();
      }
      if (localDesignationTracker) {
        namespace = "";
        writeStartElement(null, namespace, "designation", xmlWriter);

        if (localDesignation == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("designation cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDesignation);
        }

        xmlWriter.writeEndElement();
      }
      if (localDinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "din", xmlWriter);

        if (localDin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("din cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDin);
        }

        xmlWriter.writeEndElement();
      }
      if (localDinDpinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dinDpin", xmlWriter);

        if (localDinDpin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dinDpin cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDinDpin);
        }

        xmlWriter.writeEndElement();
      }
      if (localDinPanTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dinPan", xmlWriter);

        if (localDinPan == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dinPan cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDinPan);
        }

        xmlWriter.writeEndElement();
      }
      if (localDisablePageRangeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "disablePageRange", xmlWriter);

        if (localDisablePageRange == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("disablePageRange cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDisablePageRange);
        }

        xmlWriter.writeEndElement();
      }
      if (localDobTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dob", xmlWriter);

        if (localDob == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dob cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDob));
        }

        xmlWriter.writeEndElement();
      }
      if (localDocCategoryCodeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "docCategoryCode", xmlWriter);

        if (localDocCategoryCode == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("docCategoryCode cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDocCategoryCode);
        }

        xmlWriter.writeEndElement();
      }
      if (localDocIDTracker) {
        namespace = "";
        writeStartElement(null, namespace, "docID", xmlWriter);

        if (localDocID == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("docID cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDocID);
        }

        xmlWriter.writeEndElement();
      }
      if (localDocStampDutyTracker) {
        namespace = "";
        writeStartElement(null, namespace, "docStampDuty", xmlWriter);

        if (localDocStampDuty == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("docStampDuty cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDocStampDuty));
        }

        xmlWriter.writeEndElement();
      }
      if (localDocumentIDTracker) {
        namespace = "";
        writeStartElement(null, namespace, "documentID", xmlWriter);

        if (localDocumentID == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("documentID cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDocumentID);
        }

        xmlWriter.writeEndElement();
      }
      if (localDocumentNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "documentName", xmlWriter);

        if (localDocumentName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("documentName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDocumentName);
        }

        xmlWriter.writeEndElement();
      }
      if (localDpNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dpName", xmlWriter);

        if (localDpName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dpName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDpName);
        }

        xmlWriter.writeEndElement();
      }
      if (localDpinIncomeTaxPANTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dpinIncomeTaxPAN", xmlWriter);

        if (localDpinIncomeTaxPAN == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dpinIncomeTaxPAN cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDpinIncomeTaxPAN);
        }

        xmlWriter.writeEndElement();
      }
      if (localDscRegTracker) {
        namespace = "";
        writeStartElement(null, namespace, "dscReg", xmlWriter);

        if (localDscReg == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("dscReg cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localDscReg);
        }

        xmlWriter.writeEndElement();
      }
      if (localEmailNodalTracker) {
        namespace = "";
        writeStartElement(null, namespace, "emailNodal", xmlWriter);

        if (localEmailNodal == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("emailNodal cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localEmailNodal);
        }

        xmlWriter.writeEndElement();
      }
      if (localEndDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "endDate", xmlWriter);

        if (localEndDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("endDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEndDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localEnterDetailsSnoTracker) {
        namespace = "";
        writeStartElement(null, namespace, "enterDetailsSno", xmlWriter);

        if (localEnterDetailsSno == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("enterDetailsSno cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localEnterDetailsSno);
        }

        xmlWriter.writeEndElement();
      }
      if (localEnterDtlsNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "enterDtlsName", xmlWriter);

        if (localEnterDtlsName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("enterDtlsName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localEnterDtlsName);
        }

        xmlWriter.writeEndElement();
      }
      if (localEventDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "eventDate", xmlWriter);

        if (localEventDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("eventDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEventDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localExpiryDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "expiryDate", xmlWriter);

        if (localExpiryDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("expiryDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExpiryDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localExpiryDateandTimeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "expiryDateandTime", xmlWriter);

        if (localExpiryDateandTime == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("expiryDateandTime cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localExpiryDateandTime);
        }

        xmlWriter.writeEndElement();
      }
      if (localFatherNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fatherName", xmlWriter);

        if (localFatherName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fatherName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFatherName);
        }

        xmlWriter.writeEndElement();
      }
      if (localFcrnTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fcrn", xmlWriter);

        if (localFcrn == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fcrn cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFcrn);
        }

        xmlWriter.writeEndElement();
      }
      if (localFeeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fee", xmlWriter);

        if (localFee == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fee cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFee));
        }

        xmlWriter.writeEndElement();
      }
      if (localFeeAmountTracker) {
        namespace = "";
        writeStartElement(null, namespace, "feeAmount", xmlWriter);

        if (localFeeAmount == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("feeAmount cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFeeAmount));
        }

        xmlWriter.writeEndElement();
      }
      if (localFeeAmtTracker) {
        namespace = "";
        writeStartElement(null, namespace, "feeAmt", xmlWriter);

        if (localFeeAmt == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("feeAmt cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFeeAmt);
        }

        xmlWriter.writeEndElement();
      }
      namespace = "";
      writeStartElement(null, namespace, "feeApplicable", xmlWriter);

      if (false) {

        throw new org.apache.axis2.databinding.ADBException("feeApplicable cannot be null!!");

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFeeApplicable));
      }

      xmlWriter.writeEndElement();
      if (localFeeTypeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "feeType", xmlWriter);

        if (localFeeType == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("feeType cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFeeType);
        }

        xmlWriter.writeEndElement();
      }
      if (localFileNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fileName", xmlWriter);

        if (localFileName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fileName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFileName);
        }

        xmlWriter.writeEndElement();
      }
      if (localFileNameDetailsTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fileNameDetails", xmlWriter);

        if (localFileNameDetails == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fileNameDetails cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFileNameDetails);
        }

        xmlWriter.writeEndElement();
      }
      if (localFilingDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "filingDate", xmlWriter);

        if (localFilingDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("filingDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFilingDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localFilingFormNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "filingFormName", xmlWriter);

        if (localFilingFormName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("filingFormName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFilingFormName);
        }

        xmlWriter.writeEndElement();
      }
      if (localFlagTracker) {
        namespace = "";
        writeStartElement(null, namespace, "flag", xmlWriter);

        if (localFlag == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("flag cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFlag);
        }

        xmlWriter.writeEndElement();
      }
      if (localFllpinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fllpin", xmlWriter);

        if (localFllpin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fllpin cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFllpin);
        }

        xmlWriter.writeEndElement();
      }
      if (localForeignAddressTracker) {
        namespace = "";
        writeStartElement(null, namespace, "foreignAddress", xmlWriter);

        if (localForeignAddress == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("foreignAddress cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localForeignAddress);
        }

        xmlWriter.writeEndElement();
      }
      if (localFormNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "formName", xmlWriter);

        if (localFormName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("formName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFormName);
        }

        xmlWriter.writeEndElement();
      }
      if (localFormOfContributionTracker) {
        namespace = "";
        writeStartElement(null, namespace, "formOfContribution", xmlWriter);

        if (localFormOfContribution == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "formOfContribution cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFormOfContribution);
        }

        xmlWriter.writeEndElement();
      }
      if (localFormStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "formStatus", xmlWriter);

        if (localFormStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("formStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFormStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localFormsrnTracker) {
        namespace = "";
        writeStartElement(null, namespace, "formsrn", xmlWriter);

        if (localFormsrn == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("formsrn cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFormsrn);
        }

        xmlWriter.writeEndElement();
      }
      if (localFullAddressTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fullAddress", xmlWriter);

        if (localFullAddress == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fullAddress cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFullAddress);
        }

        xmlWriter.writeEndElement();
      }
      if (localFullNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "fullName", xmlWriter);

        if (localFullName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("fullName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFullName);
        }

        xmlWriter.writeEndElement();
      }
      if (localIdTracker) {
        namespace = "";
        writeStartElement(null, namespace, "id", xmlWriter);

        if (localId == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("id cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localId);
        }

        xmlWriter.writeEndElement();
      }
      if (localIdNUMBERTracker) {
        namespace = "";
        writeStartElement(null, namespace, "idNUMBER", xmlWriter);

        if (localIdNUMBER == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("idNUMBER cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIdNUMBER);
        }

        xmlWriter.writeEndElement();
      }
      if (localIdNumberETracker) {
        namespace = "";
        writeStartElement(null, namespace, "idNumber", xmlWriter);

        if (localIdNumberE == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("idNumber cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIdNumberE);
        }

        xmlWriter.writeEndElement();
      }
      if (localIdNumberDetailsTracker) {
        namespace = "";
        writeStartElement(null, namespace, "idNumberDetails", xmlWriter);

        if (localIdNumberDetails == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("idNumberDetails cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIdNumberDetails);
        }

        xmlWriter.writeEndElement();
      }
      if (localIdentificationNumTracker) {
        namespace = "";
        writeStartElement(null, namespace, "identificationNum", xmlWriter);

        if (localIdentificationNum == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("identificationNum cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIdentificationNum);
        }

        xmlWriter.writeEndElement();
      }
      if (localIncorpCountryTracker) {
        namespace = "";
        writeStartElement(null, namespace, "incorpCountry", xmlWriter);

        if (localIncorpCountry == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("incorpCountry cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIncorpCountry);
        }

        xmlWriter.writeEndElement();
      }
      if (localIncorporationDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "incorporationDate", xmlWriter);

        if (localIncorporationDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("incorporationDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localIncorporationDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localIndianAddressTracker) {
        namespace = "";
        writeStartElement(null, namespace, "indianAddress", xmlWriter);

        if (localIndianAddress == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("indianAddress cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIndianAddress);
        }

        xmlWriter.writeEndElement();
      }
      if (localLlpNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "llpName", xmlWriter);

        if (localLlpName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("llpName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localLlpName);
        }

        xmlWriter.writeEndElement();
      }
      if (localLlpinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "llpin", xmlWriter);

        if (localLlpin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("llpin cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localLlpin);
        }

        xmlWriter.writeEndElement();
      }
      if (localMigratedTracker) {
        namespace = "";
        writeStartElement(null, namespace, "migrated", xmlWriter);

        if (localMigrated == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("migrated cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localMigrated);
        }

        xmlWriter.writeEndElement();
      }
      if (localModifyDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "modifyDate", xmlWriter);

        if (localModifyDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("modifyDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localModifyDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localMonetaryValueTracker) {
        namespace = "";
        writeStartElement(null, namespace, "monetaryValue", xmlWriter);

        if (localMonetaryValue == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("monetaryValue cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localMonetaryValue);
        }

        xmlWriter.writeEndElement();
      }
      if (localMsgDetailsTracker) {
        namespace = "";
        writeStartElement(null, namespace, "msgDetails", xmlWriter);

        if (localMsgDetails == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("msgDetails cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localMsgDetails);
        }

        xmlWriter.writeEndElement();
      }
      if (localMsgNumberTracker) {
        namespace = "";
        writeStartElement(null, namespace, "msgNumber", xmlWriter);

        if (localMsgNumber == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("msgNumber cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localMsgNumber);
        }

        xmlWriter.writeEndElement();
      }
      if (localNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "name", xmlWriter);

        if (localName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("name cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localName);
        }

        xmlWriter.writeEndElement();
      }
      if (localNameChargeHolderTracker) {
        namespace = "";
        writeStartElement(null, namespace, "nameChargeHolder", xmlWriter);

        if (localNameChargeHolder == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("nameChargeHolder cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNameChargeHolder);
        }

        xmlWriter.writeEndElement();
      }
      if (localNameCompLLPTracker) {
        namespace = "";
        writeStartElement(null, namespace, "nameCompLLP", xmlWriter);

        if (localNameCompLLP == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("nameCompLLP cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNameCompLLP);
        }

        xmlWriter.writeEndElement();
      }
      if (localNameFullTracker) {
        namespace = "";
        writeStartElement(null, namespace, "nameFull", xmlWriter);

        if (localNameFull == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("nameFull cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNameFull);
        }

        xmlWriter.writeEndElement();
      }
      if (localNameLastTwoTracker) {
        namespace = "";
        writeStartElement(null, namespace, "nameLastTwo", xmlWriter);

        if (localNameLastTwo == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("nameLastTwo cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNameLastTwo);
        }

        xmlWriter.writeEndElement();
      }
      if (localNewNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "newName", xmlWriter);

        if (localNewName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("newName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNewName);
        }

        xmlWriter.writeEndElement();
      }
      if (localNomineeBodyCorpTracker) {
        namespace = "";
        writeStartElement(null, namespace, "nomineeBodyCorp", xmlWriter);

        if (localNomineeBodyCorp == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("nomineeBodyCorp cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNomineeBodyCorp);
        }

        xmlWriter.writeEndElement();
      }
      if (localNumOfDesigPartnersTracker) {
        namespace = "";
        writeStartElement(null, namespace, "numOfDesigPartners", xmlWriter);

        if (localNumOfDesigPartners == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "numOfDesigPartners cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNumOfDesigPartners);
        }

        xmlWriter.writeEndElement();
      }
      if (localNumOfLLPPartnerTracker) {
        namespace = "";
        writeStartElement(null, namespace, "numOfLLPPartner", xmlWriter);

        if (localNumOfLLPPartner == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("numOfLLPPartner cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNumOfLLPPartner);
        }

        xmlWriter.writeEndElement();
      }
      if (localNumOfPartnersTracker) {
        namespace = "";
        writeStartElement(null, namespace, "numOfPartners", xmlWriter);

        if (localNumOfPartners == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("numOfPartners cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNumOfPartners);
        }

        xmlWriter.writeEndElement();
      }
      namespace = "";
      writeStartElement(null, namespace, "numOfmembers", xmlWriter);

      if (localNumOfmembers == java.lang.Integer.MIN_VALUE) {

        throw new org.apache.axis2.databinding.ADBException("numOfmembers cannot be null!!");

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumOfmembers));
      }

      xmlWriter.writeEndElement();
      if (localNumberOfCopiesTracker) {
        namespace = "";
        writeStartElement(null, namespace, "numberOfCopies", xmlWriter);

        if (localNumberOfCopies == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("numberOfCopies cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNumberOfCopies);
        }

        xmlWriter.writeEndElement();
      }
      if (localNumberOfLLPDircetorTracker) {
        namespace = "";
        writeStartElement(null, namespace, "numberOfLLPDircetor", xmlWriter);

        if (localNumberOfLLPDircetor == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "numberOfLLPDircetor cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNumberOfLLPDircetor);
        }

        xmlWriter.writeEndElement();
      }
      if (localNumberOfPagesTracker) {
        namespace = "";
        writeStartElement(null, namespace, "numberOfPages", xmlWriter);

        if (localNumberOfPages == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("numberOfPages cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localNumberOfPages);
        }

        xmlWriter.writeEndElement();
      }
      if (localObligContributionTracker) {
        namespace = "";
        writeStartElement(null, namespace, "obligContribution", xmlWriter);

        if (localObligContribution == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("obligContribution cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localObligContribution));
        }

        xmlWriter.writeEndElement();
      }
      if (localOldEndDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "oldEndDate", xmlWriter);

        if (localOldEndDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("oldEndDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOldEndDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localOldNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "oldName", xmlWriter);

        if (localOldName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("oldName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localOldName);
        }

        xmlWriter.writeEndElement();
      }
      if (localOldStartDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "oldStartDate", xmlWriter);

        if (localOldStartDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("oldStartDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOldStartDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localOriginalDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "originalDate", xmlWriter);

        if (localOriginalDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("originalDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOriginalDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localPageNumbersTracker) {
        namespace = "";
        writeStartElement(null, namespace, "pageNumbers", xmlWriter);

        if (localPageNumbers == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("pageNumbers cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPageNumbers);
        }

        xmlWriter.writeEndElement();
      }
      if (localPaidupCapitalTracker) {
        namespace = "";
        writeStartElement(null, namespace, "paidupCapital", xmlWriter);

        if (localPaidupCapital == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("paidupCapital cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPaidupCapital));
        }

        xmlWriter.writeEndElement();
      }
      if (localPanTracker) {
        namespace = "";
        writeStartElement(null, namespace, "pan", xmlWriter);

        if (localPan == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("pan cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPan);
        }

        xmlWriter.writeEndElement();
      }
      if (localPartnerTracker) {
        namespace = "";
        writeStartElement(null, namespace, "partner", xmlWriter);

        if (localPartner == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("partner cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPartner);
        }

        xmlWriter.writeEndElement();
      }
      if (localPartnerDesignationTracker) {
        namespace = "";
        writeStartElement(null, namespace, "partnerDesignation", xmlWriter);

        if (localPartnerDesignation == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "partnerDesignation cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPartnerDesignation);
        }

        xmlWriter.writeEndElement();
      }
      if (localPartnerFathNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "partnerFathName", xmlWriter);

        if (localPartnerFathName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("partnerFathName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPartnerFathName);
        }

        xmlWriter.writeEndElement();
      }
      if (localPartnerNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "partnerName", xmlWriter);

        if (localPartnerName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("partnerName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPartnerName);
        }

        xmlWriter.writeEndElement();
      }
      if (localPartnersNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "partnersName", xmlWriter);

        if (localPartnersName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("partnersName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPartnersName);
        }

        xmlWriter.writeEndElement();
      }
      if (localPayPerPageTracker) {
        namespace = "";
        writeStartElement(null, namespace, "payPerPage", xmlWriter);

        if (localPayPerPage == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("payPerPage cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPayPerPage);
        }

        xmlWriter.writeEndElement();
      }
      if (localPaymentStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "paymentStatus", xmlWriter);

        if (localPaymentStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("paymentStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localPaymentStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localProfitPercentageTracker) {
        namespace = "";
        writeStartElement(null, namespace, "profitPercentage", xmlWriter);

        if (localProfitPercentage == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("profitPercentage cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localProfitPercentage));
        }

        xmlWriter.writeEndElement();
      }
      if (localRefundStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "refundStatus", xmlWriter);

        if (localRefundStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("refundStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRefundStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localRegAddressTracker) {
        namespace = "";
        writeStartElement(null, namespace, "regAddress", xmlWriter);

        if (localRegAddress == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("regAddress cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRegAddress);
        }

        xmlWriter.writeEndElement();
      }
      if (localRegionTracker) {
        namespace = "";
        writeStartElement(null, namespace, "region", xmlWriter);

        if (localRegion == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("region cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRegion);
        }

        xmlWriter.writeEndElement();
      }
      if (localRegistrationDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "registrationDate", xmlWriter);

        if (localRegistrationDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("registrationDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localRegistrationDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localRelationNAMEFULLTracker) {
        namespace = "";
        writeStartElement(null, namespace, "relationNAMEFULL", xmlWriter);

        if (localRelationNAMEFULL == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("relationNAMEFULL cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRelationNAMEFULL);
        }

        xmlWriter.writeEndElement();
      }
      if (localRemarksTracker) {
        namespace = "";
        writeStartElement(null, namespace, "remarks", xmlWriter);

        if (localRemarks == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("remarks cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRemarks);
        }

        xmlWriter.writeEndElement();
      }
      if (localResubDocIdTracker) {
        namespace = "";
        writeStartElement(null, namespace, "resubDocId", xmlWriter);

        if (localResubDocId == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("resubDocId cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localResubDocId);
        }

        xmlWriter.writeEndElement();
      }
      if (localRocTracker) {
        namespace = "";
        writeStartElement(null, namespace, "roc", xmlWriter);

        if (localRoc == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("roc cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRoc);
        }

        xmlWriter.writeEndElement();
      }
      if (localSatisfyDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "satisfyDate", xmlWriter);

        if (localSatisfyDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("satisfyDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSatisfyDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localScnidTracker) {
        namespace = "";
        writeStartElement(null, namespace, "scnid", xmlWriter);

        if (localScnid == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("scnid cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localScnid);
        }

        xmlWriter.writeEndElement();
      }
      if (localServiceTypeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "serviceType", xmlWriter);

        if (localServiceType == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("serviceType cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localServiceType);
        }

        xmlWriter.writeEndElement();
      }
      if (localServiceTypeDescTracker) {
        namespace = "";
        writeStartElement(null, namespace, "serviceTypeDesc", xmlWriter);

        if (localServiceTypeDesc == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("serviceTypeDesc cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localServiceTypeDesc);
        }

        xmlWriter.writeEndElement();
      }
      if (localSignatoryNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "signatoryName", xmlWriter);

        if (localSignatoryName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("signatoryName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localSignatoryName);
        }

        xmlWriter.writeEndElement();
      }
      if (localSrnTracker) {
        namespace = "";
        writeStartElement(null, namespace, "srn", xmlWriter);

        if (localSrn == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("srn cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localSrn);
        }

        xmlWriter.writeEndElement();
      }
      if (localSrnNumberTracker) {
        namespace = "";
        writeStartElement(null, namespace, "srnNumber", xmlWriter);

        if (localSrnNumber == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("srnNumber cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localSrnNumber);
        }

        xmlWriter.writeEndElement();
      }
      if (localSrnStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "srnStatus", xmlWriter);

        if (localSrnStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("srnStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localSrnStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localStampDutyTracker) {
        namespace = "";
        writeStartElement(null, namespace, "stampDuty", xmlWriter);

        if (localStampDuty == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("stampDuty cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localStampDuty);
        }

        xmlWriter.writeEndElement();
      }
      if (localStampDutyFeeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "stampDutyFee", xmlWriter);

        if (localStampDutyFee == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("stampDutyFee cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStampDutyFee));
        }

        xmlWriter.writeEndElement();
      }
      if (localStampDutyFeeTypeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "stampDutyFeeType", xmlWriter);

        if (localStampDutyFeeType == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("stampDutyFeeType cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localStampDutyFeeType);
        }

        xmlWriter.writeEndElement();
      }
      if (localStartTimeStampTracker) {
        namespace = "";
        writeStartElement(null, namespace, "startTimeStamp", xmlWriter);

        if (localStartTimeStamp == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("startTimeStamp cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localStartTimeStamp);
        }

        xmlWriter.writeEndElement();
      }
      if (localStateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "state", xmlWriter);

        if (localState == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("state cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localState);
        }

        xmlWriter.writeEndElement();
      }
      if (localStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "status", xmlWriter);

        if (localStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("status cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localStatusDetailsTracker) {
        namespace = "";
        writeStartElement(null, namespace, "statusDetails", xmlWriter);

        if (localStatusDetails == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("statusDetails cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localStatusDetails);
        }

        xmlWriter.writeEndElement();
      }
      if (localStatusFndDetailsTracker) {
        namespace = "";
        writeStartElement(null, namespace, "statusFndDetails", xmlWriter);

        if (localStatusFndDetails == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("statusFndDetails cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localStatusFndDetails);
        }

        xmlWriter.writeEndElement();
      }
      if (localSubCategoryTracker) {
        namespace = "";
        writeStartElement(null, namespace, "subCategory", xmlWriter);

        if (localSubCategory == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("subCategory cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localSubCategory);
        }

        xmlWriter.writeEndElement();
      }
      if (localTotalContributionTracker) {
        namespace = "";
        writeStartElement(null, namespace, "totalContribution", xmlWriter);

        if (localTotalContribution == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("totalContribution cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localTotalContribution);
        }

        xmlWriter.writeEndElement();
      }
      if (localTotalStampDutyTracker) {
        namespace = "";
        writeStartElement(null, namespace, "totalStampDuty", xmlWriter);

        if (localTotalStampDuty == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("totalStampDuty cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localTotalStampDuty));
        }

        xmlWriter.writeEndElement();
      }
      if (localTransactionDateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "transactionDate", xmlWriter);

        if (localTransactionDate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("transactionDate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                  localTransactionDate));
        }

        xmlWriter.writeEndElement();
      }
      if (localTypeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "type", xmlWriter);

        if (localType == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("type cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localType);
        }

        xmlWriter.writeEndElement();
      }
      if (localTypeOfChangeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "typeOfChange", xmlWriter);

        if (localTypeOfChange == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("typeOfChange cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localTypeOfChange);
        }

        xmlWriter.writeEndElement();
      }
      if (localTypeOfOfficeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "typeOfOffice", xmlWriter);

        if (localTypeOfOffice == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("typeOfOffice cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localTypeOfOffice);
        }

        xmlWriter.writeEndElement();
      }
      if (localUserIDTracker) {
        namespace = "";
        writeStartElement(null, namespace, "userID", xmlWriter);

        if (localUserID == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("userID cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localUserID);
        }

        xmlWriter.writeEndElement();
      }
      if (localWhetherResIndiaTracker) {
        namespace = "";
        writeStartElement(null, namespace, "whetherResIndia", xmlWriter);

        if (localWhetherResIndia == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("whetherResIndia cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localWhetherResIndia);
        }

        xmlWriter.writeEndElement();
      }
      namespace = "";
      writeStartElement(null, namespace, "sNo", xmlWriter);

      if (localSNo == java.lang.Integer.MIN_VALUE) {

        throw new org.apache.axis2.databinding.ADBException("sNo cannot be null!!");

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSNo));
      }

      xmlWriter.writeEndElement();

      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static ItemDetails parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        ItemDetails object = new ItemDetails();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"itemDetails".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (ItemDetails) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "activityDesc").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "activityDesc" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setActivityDesc(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "allPages").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "allPages" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setAllPages(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "amountSecured").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "amountSecured" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setAmountSecured(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "asset").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "asset" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setAsset(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "asset1").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "asset1" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setAsset1(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "authCapital").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "authCapital" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setAuthCapital(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "bankName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "bankName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setBankName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "bankRegistered").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "bankRegistered" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setBankRegistered(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "beginDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "beginDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setBeginDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "birthDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "birthDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setBirthDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "bpName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "bpName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setBpName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "bytes").equals(reader.getName())) {

            object.setBytes(
                org.apache.axiom.util.stax.XMLStreamReaderUtils.getDataHandlerFromElement(reader));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "category").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "category" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCategory(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "certstatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "certstatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCertstatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "chargeAmount").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "chargeAmount" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setChargeAmount(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "chargeID").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "chargeID" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setChargeID(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "chargeStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "chargeStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setChargeStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "cin").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "cin" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "cinLLPIN").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "cinLLPIN" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCinLLPIN(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "cinLLPINDetails").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "cinLLPINDetails" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCinLLPINDetails(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "cinLLPIn").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "cinLLPIn" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCinLLPInE(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "comment").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "comment" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setComment(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "compClass").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "compClass" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompClass(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "compLLPStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "compLLPStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompLLPStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyFlag").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyFlag" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyFlag(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyID").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyID" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyID(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyLLPName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyLLPName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyLLPName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "complaintType").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "complaintType" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setComplaintType(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "contribRecandAcc").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "contribRecandAcc" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setContribRecandAcc(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "copyChallan").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "copyChallan" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCopyChallan(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "crDat").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "crDat" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCrDat(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "crDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "crDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCrDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "creationDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "creationDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCreationDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "curr").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "curr" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCurr(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "currentDesignation").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "currentDesignation" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCurrentDesignation(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateAddition").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateAddition" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateAddition(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateAppointment").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateAppointment" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateAppointment(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateCessation").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateCessation" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateCessation(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateFrom").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateFrom" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateFrom(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateLastModification")
                  .equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateLastModification" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateLastModification(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));

            reader.next();

          } // End of if for expected property start element
          else {
            // 1 - A start element we are not expecting indicates an invalid parameter was passed
            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());
          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateOfAppointment").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateOfAppointment" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateOfAppointment(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateOfCessation").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateOfCessation" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateOfCessation(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateOfChangeDesig").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateOfChangeDesig" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateOfChangeDesig(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateRoleCheck").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateRoleCheck" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateRoleCheck(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateTo").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateTo" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateTo(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dateofFiling").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dateofFiling" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDateofFiling(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "defaultYear").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "defaultYear" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDefaultYear(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "defaultingStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "defaultingStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDefaultingStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "desc").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "desc" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDesc(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "desigFL").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "desigFL" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDesigFL(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "desigShort").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "desigShort" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDesigShort(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "designation").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "designation" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDesignation(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "din").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "din" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dinDpin").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dinDpin" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDinDpin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dinPan").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dinPan" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDinPan(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "disablePageRange").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "disablePageRange" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDisablePageRange(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dob").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dob" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDob(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "docCategoryCode").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "docCategoryCode" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDocCategoryCode(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "docID").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "docID" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDocID(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "docStampDuty").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "docStampDuty" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDocStampDuty(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "documentID").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "documentID" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDocumentID(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "documentName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "documentName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDocumentName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dpName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dpName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDpName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dpinIncomeTaxPAN").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dpinIncomeTaxPAN" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDpinIncomeTaxPAN(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "dscReg").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "dscReg" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setDscReg(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "emailNodal").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "emailNodal" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setEmailNodal(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "endDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "endDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setEndDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "enterDetailsSno").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "enterDetailsSno" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setEnterDetailsSno(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "enterDtlsName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "enterDtlsName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setEnterDtlsName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "eventDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "eventDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setEventDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "expiryDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "expiryDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setExpiryDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "expiryDateandTime").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "expiryDateandTime" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setExpiryDateandTime(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fatherName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fatherName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFatherName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fcrn").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fcrn" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFcrn(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fee").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fee" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFee(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "feeAmount").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "feeAmount" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFeeAmount(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "feeAmt").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "feeAmt" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFeeAmt(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "feeApplicable").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "feeApplicable" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFeeApplicable(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));

            reader.next();

          } // End of if for expected property start element
          else {
            // 1 - A start element we are not expecting indicates an invalid parameter was passed
            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());
          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "feeType").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "feeType" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFeeType(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fileName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fileName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFileName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fileNameDetails").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fileNameDetails" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFileNameDetails(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "filingDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "filingDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFilingDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "filingFormName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "filingFormName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFilingFormName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "flag").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "flag" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFlag(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fllpin").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fllpin" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFllpin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "foreignAddress").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "foreignAddress" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setForeignAddress(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "formName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "formName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFormName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "formOfContribution").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "formOfContribution" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFormOfContribution(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "formStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "formStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFormStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "formsrn").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "formsrn" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFormsrn(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fullAddress").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fullAddress" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFullAddress(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "fullName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "fullName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFullName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "id").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "id" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setId(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "idNUMBER").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "idNUMBER" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIdNUMBER(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "idNumber").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "idNumber" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIdNumberE(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "idNumberDetails").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "idNumberDetails" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIdNumberDetails(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "identificationNum").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "identificationNum" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIdentificationNum(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "incorpCountry").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "incorpCountry" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIncorpCountry(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "incorporationDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "incorporationDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIncorporationDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "indianAddress").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "indianAddress" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIndianAddress(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "llpName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "llpName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setLlpName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "llpin").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "llpin" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setLlpin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "migrated").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "migrated" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setMigrated(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "modifyDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "modifyDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setModifyDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "monetaryValue").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "monetaryValue" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setMonetaryValue(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "msgDetails").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "msgDetails" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setMsgDetails(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "msgNumber").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "msgNumber" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setMsgNumber(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "name").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "name" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "nameChargeHolder").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "nameChargeHolder" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNameChargeHolder(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "nameCompLLP").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "nameCompLLP" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNameCompLLP(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "nameFull").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "nameFull" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNameFull(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "nameLastTwo").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "nameLastTwo" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNameLastTwo(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "newName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "newName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNewName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "nomineeBodyCorp").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "nomineeBodyCorp" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNomineeBodyCorp(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numOfDesigPartners").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numOfDesigPartners" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumOfDesigPartners(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numOfLLPPartner").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numOfLLPPartner" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumOfLLPPartner(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numOfPartners").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numOfPartners" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumOfPartners(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numOfmembers").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numOfmembers" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumOfmembers(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

            reader.next();

          } // End of if for expected property start element
          else {
            // 1 - A start element we are not expecting indicates an invalid parameter was passed
            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());
          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numberOfCopies").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numberOfCopies" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumberOfCopies(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numberOfLLPDircetor")
                  .equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numberOfLLPDircetor" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumberOfLLPDircetor(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "numberOfPages").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "numberOfPages" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setNumberOfPages(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "obligContribution").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "obligContribution" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setObligContribution(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "oldEndDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "oldEndDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setOldEndDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "oldName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "oldName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setOldName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "oldStartDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "oldStartDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setOldStartDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "originalDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "originalDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setOriginalDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "pageNumbers").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "pageNumbers" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPageNumbers(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "paidupCapital").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "paidupCapital" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPaidupCapital(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "pan").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "pan" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPan(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "partner").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "partner" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPartner(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "partnerDesignation").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "partnerDesignation" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPartnerDesignation(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "partnerFathName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "partnerFathName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPartnerFathName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "partnerName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "partnerName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPartnerName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "partnersName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "partnersName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPartnersName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "payPerPage").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "payPerPage" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPayPerPage(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "paymentStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "paymentStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setPaymentStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "profitPercentage").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "profitPercentage" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setProfitPercentage(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "refundStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "refundStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRefundStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "regAddress").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "regAddress" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRegAddress(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "region").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "region" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRegion(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "registrationDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "registrationDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRegistrationDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "relationNAMEFULL").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "relationNAMEFULL" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRelationNAMEFULL(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "remarks").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "remarks" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRemarks(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "resubDocId").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "resubDocId" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setResubDocId(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "roc").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "roc" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRoc(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "satisfyDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "satisfyDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSatisfyDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "scnid").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "scnid" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setScnid(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "serviceType").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "serviceType" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setServiceType(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "serviceTypeDesc").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "serviceTypeDesc" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setServiceTypeDesc(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "signatoryName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "signatoryName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSignatoryName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "srn").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "srn" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSrn(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "srnNumber").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "srnNumber" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSrnNumber(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "srnStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "srnStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSrnStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "stampDuty").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "stampDuty" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStampDuty(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "stampDutyFee").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "stampDutyFee" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStampDutyFee(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "stampDutyFeeType").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "stampDutyFeeType" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStampDutyFeeType(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "startTimeStamp").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "startTimeStamp" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStartTimeStamp(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "state").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "state" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setState(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "status").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "status" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "statusDetails").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "statusDetails" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStatusDetails(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "statusFndDetails").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "statusFndDetails" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setStatusFndDetails(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "subCategory").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "subCategory" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSubCategory(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "totalContribution").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "totalContribution" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setTotalContribution(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "totalStampDuty").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "totalStampDuty" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setTotalStampDuty(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "transactionDate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "transactionDate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setTransactionDate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "type").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "type" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setType(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "typeOfChange").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "typeOfChange" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setTypeOfChange(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "typeOfOffice").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "typeOfOffice" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setTypeOfOffice(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "userID").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "userID" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setUserID(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "whetherResIndia").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "whetherResIndia" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setWhetherResIndia(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "sNo").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "sNo" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setSNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));

            reader.next();

          } // End of if for expected property start element
          else {
            // 1 - A start element we are not expecting indicates an invalid parameter was passed
            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());
          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetSIGInfo implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getSIGInfo
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Arg0 */
    protected java.lang.String localArg0;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localArg0Tracker = false;

    public boolean isArg0Specified() {
      return localArg0Tracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getArg0() {
      return localArg0;
    }

    /**
     * Auto generated setter method
     *
     * @param param Arg0
     */
    public void setArg0(java.lang.String param) {
      localArg0Tracker = param != null;

      this.localArg0 = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getSIGInfo",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "getSIGInfo", xmlWriter);
        }
      }
      if (localArg0Tracker) {
        namespace = "";
        writeStartElement(null, namespace, "arg0", xmlWriter);

        if (localArg0 == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localArg0);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetSIGInfo parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetSIGInfo object = new GetSIGInfo();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getSIGInfo".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetSIGInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "arg0" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setArg0(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDirectorInfoResponse implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getDirectorInfoResponse
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for _return */
    protected DirectorResponseDTO local_return;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean local_returnTracker = false;

    public boolean is_returnSpecified() {
      return local_returnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return DirectorResponseDTO
     */
    public DirectorResponseDTO get_return() {
      return local_return;
    }

    /**
     * Auto generated setter method
     *
     * @param param _return
     */
    public void set_return(DirectorResponseDTO param) {
      local_returnTracker = param != null;

      this.local_return = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getDirectorInfoResponse",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "getDirectorInfoResponse",
              xmlWriter);
        }
      }
      if (local_returnTracker) {
        if (local_return == null) {
          throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
        }
        local_return.serialize(new javax.xml.namespace.QName("", "return"), xmlWriter);
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDirectorInfoResponse parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDirectorInfoResponse object = new GetDirectorInfoResponse();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getDirectorInfoResponse".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetDirectorInfoResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

            object.set_return(DirectorResponseDTO.Factory.parse(reader));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetCINInfoResponseE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfoResponse", "ns1");

    /** field for GetCINInfoResponse */
    protected GetCINInfoResponse localGetCINInfoResponse;

    /**
     * Auto generated getter method
     *
     * @return GetCINInfoResponse
     */
    public GetCINInfoResponse getGetCINInfoResponse() {
      return localGetCINInfoResponse;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetCINInfoResponse
     */
    public void setGetCINInfoResponse(GetCINInfoResponse param) {

      this.localGetCINInfoResponse = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetCINInfoResponse == null) {
        throw new org.apache.axis2.databinding.ADBException("getCINInfoResponse cannot be null!");
      }
      localGetCINInfoResponse.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetCINInfoResponseE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetCINInfoResponseE object = new GetCINInfoResponseE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getCINInfoResponse")
                      .equals(reader.getName())) {

                object.setGetCINInfoResponse(GetCINInfoResponse.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class ExtensionMapper {

    public static java.lang.Object getTypeObject(
        java.lang.String namespaceURI,
        java.lang.String typeName,
        javax.xml.stream.XMLStreamReader reader)
        throws java.lang.Exception {

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getDirectorInfo".equals(typeName)) {

        return GetDirectorInfo.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI)
          && "dinRelationMessageResponseDTO".equals(typeName)) {

        return DinRelationMessageResponseDTO.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI)
          && "signatoryUsersResponseDTO".equals(typeName)) {

        return SignatoryUsersResponseDTO.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI)
          && "companyMasterDataResponseDTO".equals(typeName)) {

        return CompanyMasterDataResponseDTO.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI)
          && "dinRelationServiceResponseDTO".equals(typeName)) {

        return DinRelationServiceResponseDTO.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "itemDetails".equals(typeName)) {

        return ItemDetails.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getDINInfoResponse".equals(typeName)) {

        return GetDINInfoResponse.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "directorDetails".equals(typeName)) {

        return DirectorDetails.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "directorResponseDTO".equals(typeName)) {

        return DirectorResponseDTO.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getCINInfo".equals(typeName)) {

        return GetCINInfo.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getCINInfoResponse".equals(typeName)) {

        return GetCINInfoResponse.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI)
          && "getDirectorInfoResponse".equals(typeName)) {

        return GetDirectorInfoResponse.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getDINInfo".equals(typeName)) {

        return GetDINInfo.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "financialData".equals(typeName)) {

        return FinancialData.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getSIGInfo".equals(typeName)) {

        return GetSIGInfo.Factory.parse(reader);
      }

      if ("http://www.mca.gov.in/".equals(namespaceURI) && "getSIGInfoResponse".equals(typeName)) {

        return GetSIGInfoResponse.Factory.parse(reader);
      }

      throw new org.apache.axis2.databinding.ADBException(
          "Unsupported type " + namespaceURI + " " + typeName);
    }
  }

  public static class GetCINInfo implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getCINInfo
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Arg0 */
    protected java.lang.String localArg0;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localArg0Tracker = false;

    public boolean isArg0Specified() {
      return localArg0Tracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getArg0() {
      return localArg0;
    }

    /**
     * Auto generated setter method
     *
     * @param param Arg0
     */
    public void setArg0(java.lang.String param) {
      localArg0Tracker = param != null;

      this.localArg0 = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getCINInfo",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "getCINInfo", xmlWriter);
        }
      }
      if (localArg0Tracker) {
        namespace = "";
        writeStartElement(null, namespace, "arg0", xmlWriter);

        if (localArg0 == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("arg0 cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localArg0);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetCINInfo parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetCINInfo object = new GetCINInfo();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getCINInfo".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetCINInfo) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "arg0").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "arg0" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setArg0(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class CompanyMasterDataResponseDTO implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = companyMasterDataResponseDTO
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Cin */
    protected java.lang.String localCin;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCinTracker = false;

    public boolean isCinSpecified() {
      return localCinTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCin() {
      return localCin;
    }

    /**
     * Auto generated setter method
     *
     * @param param Cin
     */
    public void setCin(java.lang.String param) {
      localCinTracker = param != null;

      this.localCin = param;
    }

    /** field for CompanyName */
    protected java.lang.String localCompanyName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyNameTracker = false;

    public boolean isCompanyNameSpecified() {
      return localCompanyNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyName() {
      return localCompanyName;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyName
     */
    public void setCompanyName(java.lang.String param) {
      localCompanyNameTracker = param != null;

      this.localCompanyName = param;
    }

    /** field for CompanyStatus */
    protected java.lang.String localCompanyStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyStatusTracker = false;

    public boolean isCompanyStatusSpecified() {
      return localCompanyStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyStatus() {
      return localCompanyStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyStatus
     */
    public void setCompanyStatus(java.lang.String param) {
      localCompanyStatusTracker = param != null;

      this.localCompanyStatus = param;
    }

    /** field for Email */
    protected java.lang.String localEmail;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localEmailTracker = false;

    public boolean isEmailSpecified() {
      return localEmailTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getEmail() {
      return localEmail;
    }

    /**
     * Auto generated setter method
     *
     * @param param Email
     */
    public void setEmail(java.lang.String param) {
      localEmailTracker = param != null;

      this.localEmail = param;
    }

    /** field for FinancialAuditStatus */
    protected java.lang.String localFinancialAuditStatus;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFinancialAuditStatusTracker = false;

    public boolean isFinancialAuditStatusSpecified() {
      return localFinancialAuditStatusTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getFinancialAuditStatus() {
      return localFinancialAuditStatus;
    }

    /**
     * Auto generated setter method
     *
     * @param param FinancialAuditStatus
     */
    public void setFinancialAuditStatus(java.lang.String param) {
      localFinancialAuditStatusTracker = param != null;

      this.localFinancialAuditStatus = param;
    }

    /** field for FinancialDetails This was an Array! */
    protected FinancialData[] localFinancialDetails;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localFinancialDetailsTracker = false;

    public boolean isFinancialDetailsSpecified() {
      return localFinancialDetailsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return FinancialData[]
     */
    public FinancialData[] getFinancialDetails() {
      return localFinancialDetails;
    }

    /** validate the array for FinancialDetails */
    protected void validateFinancialDetails(FinancialData[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param FinancialDetails
     */
    public void setFinancialDetails(FinancialData[] param) {

      validateFinancialDetails(param);

      localFinancialDetailsTracker = true;

      this.localFinancialDetails = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param FinancialData
     */
    public void addFinancialDetails(FinancialData param) {
      if (localFinancialDetails == null) {
        localFinancialDetails = new FinancialData[] {};
      }

      // update the setting tracker
      localFinancialDetailsTracker = true;

      java.util.List list =
          org.apache.axis2.databinding.utils.ConverterUtil.toList(localFinancialDetails);
      list.add(param);
      this.localFinancialDetails = (FinancialData[]) list.toArray(new FinancialData[list.size()]);
    }

    /** field for Incorpdate */
    protected java.lang.String localIncorpdate;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localIncorpdateTracker = false;

    public boolean isIncorpdateSpecified() {
      return localIncorpdateTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getIncorpdate() {
      return localIncorpdate;
    }

    /**
     * Auto generated setter method
     *
     * @param param Incorpdate
     */
    public void setIncorpdate(java.lang.String param) {
      localIncorpdateTracker = param != null;

      this.localIncorpdate = param;
    }

    /** field for RegNo */
    protected java.lang.String localRegNo;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRegNoTracker = false;

    public boolean isRegNoSpecified() {
      return localRegNoTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRegNo() {
      return localRegNo;
    }

    /**
     * Auto generated setter method
     *
     * @param param RegNo
     */
    public void setRegNo(java.lang.String param) {
      localRegNoTracker = param != null;

      this.localRegNo = param;
    }

    /** field for RegisteredAddress */
    protected java.lang.String localRegisteredAddress;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRegisteredAddressTracker = false;

    public boolean isRegisteredAddressSpecified() {
      return localRegisteredAddressTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRegisteredAddress() {
      return localRegisteredAddress;
    }

    /**
     * Auto generated setter method
     *
     * @param param RegisteredAddress
     */
    public void setRegisteredAddress(java.lang.String param) {
      localRegisteredAddressTracker = param != null;

      this.localRegisteredAddress = param;
    }

    /** field for RegisteredContactNo */
    protected java.lang.String localRegisteredContactNo;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRegisteredContactNoTracker = false;

    public boolean isRegisteredContactNoSpecified() {
      return localRegisteredContactNoTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRegisteredContactNo() {
      return localRegisteredContactNo;
    }

    /**
     * Auto generated setter method
     *
     * @param param RegisteredContactNo
     */
    public void setRegisteredContactNo(java.lang.String param) {
      localRegisteredContactNoTracker = param != null;

      this.localRegisteredContactNo = param;
    }

    /** field for RocCode */
    protected java.lang.String localRocCode;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localRocCodeTracker = false;

    public boolean isRocCodeSpecified() {
      return localRocCodeTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getRocCode() {
      return localRocCode;
    }

    /**
     * Auto generated setter method
     *
     * @param param RocCode
     */
    public void setRocCode(java.lang.String param) {
      localRocCodeTracker = param != null;

      this.localRocCode = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":companyMasterDataResponseDTO",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "companyMasterDataResponseDTO",
              xmlWriter);
        }
      }
      if (localCinTracker) {
        namespace = "";
        writeStartElement(null, namespace, "cin", xmlWriter);

        if (localCin == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("cin cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCin);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompanyNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyName", xmlWriter);

        if (localCompanyName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyName);
        }

        xmlWriter.writeEndElement();
      }
      if (localCompanyStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyStatus", xmlWriter);

        if (localCompanyStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localEmailTracker) {
        namespace = "";
        writeStartElement(null, namespace, "email", xmlWriter);

        if (localEmail == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("email cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localEmail);
        }

        xmlWriter.writeEndElement();
      }
      if (localFinancialAuditStatusTracker) {
        namespace = "";
        writeStartElement(null, namespace, "financialAuditStatus", xmlWriter);

        if (localFinancialAuditStatus == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "financialAuditStatus cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localFinancialAuditStatus);
        }

        xmlWriter.writeEndElement();
      }
      if (localFinancialDetailsTracker) {
        if (localFinancialDetails != null) {
          for (int i = 0; i < localFinancialDetails.length; i++) {
            if (localFinancialDetails[i] != null) {
              localFinancialDetails[i].serialize(
                  new javax.xml.namespace.QName("", "financialDetails"), xmlWriter);
            } else {

              writeStartElement(null, "", "financialDetails", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "financialDetails", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      if (localIncorpdateTracker) {
        namespace = "";
        writeStartElement(null, namespace, "incorpdate", xmlWriter);

        if (localIncorpdate == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("incorpdate cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localIncorpdate);
        }

        xmlWriter.writeEndElement();
      }
      if (localRegNoTracker) {
        namespace = "";
        writeStartElement(null, namespace, "regNo", xmlWriter);

        if (localRegNo == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("regNo cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRegNo);
        }

        xmlWriter.writeEndElement();
      }
      if (localRegisteredAddressTracker) {
        namespace = "";
        writeStartElement(null, namespace, "registeredAddress", xmlWriter);

        if (localRegisteredAddress == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("registeredAddress cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRegisteredAddress);
        }

        xmlWriter.writeEndElement();
      }
      if (localRegisteredContactNoTracker) {
        namespace = "";
        writeStartElement(null, namespace, "registeredContactNo", xmlWriter);

        if (localRegisteredContactNo == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException(
              "registeredContactNo cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRegisteredContactNo);
        }

        xmlWriter.writeEndElement();
      }
      if (localRocCodeTracker) {
        namespace = "";
        writeStartElement(null, namespace, "rocCode", xmlWriter);

        if (localRocCode == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("rocCode cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localRocCode);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static CompanyMasterDataResponseDTO parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        CompanyMasterDataResponseDTO object = new CompanyMasterDataResponseDTO();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"companyMasterDataResponseDTO".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (CompanyMasterDataResponseDTO)
                    ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          java.util.ArrayList list6 = new java.util.ArrayList();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "cin").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "cin" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCin(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyStatus").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "email").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "email" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setEmail(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "financialAuditStatus")
                  .equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "financialAuditStatus" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setFinancialAuditStatus(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "financialDetails").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list6.add(null);
              reader.next();
            } else {
              list6.add(FinancialData.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone6 = false;
            while (!loopDone6) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone6 = true;
              } else {
                if (new javax.xml.namespace.QName("", "financialDetails")
                    .equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list6.add(null);
                    reader.next();
                  } else {
                    list6.add(FinancialData.Factory.parse(reader));
                  }
                } else {
                  loopDone6 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setFinancialDetails(
                (FinancialData[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        FinancialData.class, list6));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "incorpdate").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "incorpdate" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setIncorpdate(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "regNo").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "regNo" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRegNo(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "registeredAddress").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "registeredAddress" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRegisteredAddress(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "registeredContactNo")
                  .equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "registeredContactNo" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRegisteredContactNo(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "rocCode").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "rocCode" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setRocCode(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetDirectorInfoResponseE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getDirectorInfoResponse", "ns1");

    /** field for GetDirectorInfoResponse */
    protected GetDirectorInfoResponse localGetDirectorInfoResponse;

    /**
     * Auto generated getter method
     *
     * @return GetDirectorInfoResponse
     */
    public GetDirectorInfoResponse getGetDirectorInfoResponse() {
      return localGetDirectorInfoResponse;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetDirectorInfoResponse
     */
    public void setGetDirectorInfoResponse(GetDirectorInfoResponse param) {

      this.localGetDirectorInfoResponse = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetDirectorInfoResponse == null) {
        throw new org.apache.axis2.databinding.ADBException(
            "getDirectorInfoResponse cannot be null!");
      }
      localGetDirectorInfoResponse.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetDirectorInfoResponseE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetDirectorInfoResponseE object = new GetDirectorInfoResponseE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName(
                          "http://www.mca.gov.in/", "getDirectorInfoResponse")
                      .equals(reader.getName())) {

                object.setGetDirectorInfoResponse(GetDirectorInfoResponse.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class SignatoryUsersResponseDTO implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = signatoryUsersResponseDTO
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for Comments This was an Array! */
    protected ItemDetails[] localComments;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCommentsTracker = false;

    public boolean isCommentsSpecified() {
      return localCommentsTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return ItemDetails[]
     */
    public ItemDetails[] getComments() {
      return localComments;
    }

    /** validate the array for Comments */
    protected void validateComments(ItemDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param Comments
     */
    public void setComments(ItemDetails[] param) {

      validateComments(param);

      localCommentsTracker = true;

      this.localComments = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param ItemDetails
     */
    public void addComments(ItemDetails param) {
      if (localComments == null) {
        localComments = new ItemDetails[] {};
      }

      // update the setting tracker
      localCommentsTracker = true;

      java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localComments);
      list.add(param);
      this.localComments = (ItemDetails[]) list.toArray(new ItemDetails[list.size()]);
    }

    /** field for CompanyName */
    protected java.lang.String localCompanyName;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localCompanyNameTracker = false;

    public boolean isCompanyNameSpecified() {
      return localCompanyNameTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getCompanyName() {
      return localCompanyName;
    }

    /**
     * Auto generated setter method
     *
     * @param param CompanyName
     */
    public void setCompanyName(java.lang.String param) {
      localCompanyNameTracker = param != null;

      this.localCompanyName = param;
    }

    /** field for Messages This was an Array! */
    protected ItemDetails[] localMessages;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localMessagesTracker = false;

    public boolean isMessagesSpecified() {
      return localMessagesTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return ItemDetails[]
     */
    public ItemDetails[] getMessages() {
      return localMessages;
    }

    /** validate the array for Messages */
    protected void validateMessages(ItemDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param Messages
     */
    public void setMessages(ItemDetails[] param) {

      validateMessages(param);

      localMessagesTracker = true;

      this.localMessages = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param ItemDetails
     */
    public void addMessages(ItemDetails param) {
      if (localMessages == null) {
        localMessages = new ItemDetails[] {};
      }

      // update the setting tracker
      localMessagesTracker = true;

      java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localMessages);
      list.add(param);
      this.localMessages = (ItemDetails[]) list.toArray(new ItemDetails[list.size()]);
    }

    /** field for Signatories This was an Array! */
    protected ItemDetails[] localSignatories;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localSignatoriesTracker = false;

    public boolean isSignatoriesSpecified() {
      return localSignatoriesTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return ItemDetails[]
     */
    public ItemDetails[] getSignatories() {
      return localSignatories;
    }

    /** validate the array for Signatories */
    protected void validateSignatories(ItemDetails[] param) {}

    /**
     * Auto generated setter method
     *
     * @param param Signatories
     */
    public void setSignatories(ItemDetails[] param) {

      validateSignatories(param);

      localSignatoriesTracker = true;

      this.localSignatories = param;
    }

    /**
     * Auto generated add method for the array for convenience
     *
     * @param param ItemDetails
     */
    public void addSignatories(ItemDetails param) {
      if (localSignatories == null) {
        localSignatories = new ItemDetails[] {};
      }

      // update the setting tracker
      localSignatoriesTracker = true;

      java.util.List list =
          org.apache.axis2.databinding.utils.ConverterUtil.toList(localSignatories);
      list.add(param);
      this.localSignatories = (ItemDetails[]) list.toArray(new ItemDetails[list.size()]);
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":signatoryUsersResponseDTO",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "signatoryUsersResponseDTO",
              xmlWriter);
        }
      }
      if (localCommentsTracker) {
        if (localComments != null) {
          for (int i = 0; i < localComments.length; i++) {
            if (localComments[i] != null) {
              localComments[i].serialize(new javax.xml.namespace.QName("", "comments"), xmlWriter);
            } else {

              writeStartElement(null, "", "comments", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "comments", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      if (localCompanyNameTracker) {
        namespace = "";
        writeStartElement(null, namespace, "companyName", xmlWriter);

        if (localCompanyName == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("companyName cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localCompanyName);
        }

        xmlWriter.writeEndElement();
      }
      if (localMessagesTracker) {
        if (localMessages != null) {
          for (int i = 0; i < localMessages.length; i++) {
            if (localMessages[i] != null) {
              localMessages[i].serialize(new javax.xml.namespace.QName("", "messages"), xmlWriter);
            } else {

              writeStartElement(null, "", "messages", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "messages", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      if (localSignatoriesTracker) {
        if (localSignatories != null) {
          for (int i = 0; i < localSignatories.length; i++) {
            if (localSignatories[i] != null) {
              localSignatories[i].serialize(
                  new javax.xml.namespace.QName("", "signatories"), xmlWriter);
            } else {

              writeStartElement(null, "", "signatories", xmlWriter);

              // write the nil attribute
              writeAttribute(
                  "xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
              xmlWriter.writeEndElement();
            }
          }
        } else {

          writeStartElement(null, "", "signatories", xmlWriter);

          // write the nil attribute
          writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
          xmlWriter.writeEndElement();
        }
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static SignatoryUsersResponseDTO parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        SignatoryUsersResponseDTO object = new SignatoryUsersResponseDTO();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"signatoryUsersResponseDTO".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (SignatoryUsersResponseDTO)
                    ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          java.util.ArrayList list1 = new java.util.ArrayList();

          java.util.ArrayList list3 = new java.util.ArrayList();

          java.util.ArrayList list4 = new java.util.ArrayList();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "comments").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list1.add(null);
              reader.next();
            } else {
              list1.add(ItemDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone1 = false;
            while (!loopDone1) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone1 = true;
              } else {
                if (new javax.xml.namespace.QName("", "comments").equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list1.add(null);
                    reader.next();
                  } else {
                    list1.add(ItemDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone1 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setComments(
                (ItemDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        ItemDetails.class, list1));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "companyName").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "companyName" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setCompanyName(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "messages").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list3.add(null);
              reader.next();
            } else {
              list3.add(ItemDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone3 = false;
            while (!loopDone3) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone3 = true;
              } else {
                if (new javax.xml.namespace.QName("", "messages").equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list3.add(null);
                    reader.next();
                  } else {
                    list3.add(ItemDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone3 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setMessages(
                (ItemDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        ItemDetails.class, list3));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "signatories").equals(reader.getName())) {

            // Process the array and step past its final element's end.

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              list4.add(null);
              reader.next();
            } else {
              list4.add(ItemDetails.Factory.parse(reader));
            }
            // loop until we find a start element that is not part of this array
            boolean loopDone4 = false;
            while (!loopDone4) {
              // We should be at the end element, but make sure
              while (!reader.isEndElement()) reader.next();
              // Step out of this element
              reader.next();
              // Step to next element event.
              while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
              if (reader.isEndElement()) {
                // two continuous end elements means we are exiting the xml structure
                loopDone4 = true;
              } else {
                if (new javax.xml.namespace.QName("", "signatories").equals(reader.getName())) {

                  nillableValue =
                      reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
                  if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
                    list4.add(null);
                    reader.next();
                  } else {
                    list4.add(ItemDetails.Factory.parse(reader));
                  }
                } else {
                  loopDone4 = true;
                }
              }
            }
            // call the converter utility  to convert and set the array

            object.setSignatories(
                (ItemDetails[])
                    org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                        ItemDetails.class, list4));

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetSIGInfoE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfo", "ns1");

    /** field for GetSIGInfo */
    protected GetSIGInfo localGetSIGInfo;

    /**
     * Auto generated getter method
     *
     * @return GetSIGInfo
     */
    public GetSIGInfo getGetSIGInfo() {
      return localGetSIGInfo;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetSIGInfo
     */
    public void setGetSIGInfo(GetSIGInfo param) {

      this.localGetSIGInfo = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetSIGInfo == null) {
        throw new org.apache.axis2.databinding.ADBException("getSIGInfo cannot be null!");
      }
      localGetSIGInfo.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetSIGInfoE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetSIGInfoE object = new GetSIGInfoE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfo")
                      .equals(reader.getName())) {

                object.setGetSIGInfo(GetSIGInfo.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetCINInfoResponse implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = getCINInfoResponse
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for _return */
    protected CompanyMasterDataResponseDTO local_return;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean local_returnTracker = false;

    public boolean is_returnSpecified() {
      return local_returnTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return CompanyMasterDataResponseDTO
     */
    public CompanyMasterDataResponseDTO get_return() {
      return local_return;
    }

    /**
     * Auto generated setter method
     *
     * @param param _return
     */
    public void set_return(CompanyMasterDataResponseDTO param) {
      local_returnTracker = param != null;

      this.local_return = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":getCINInfoResponse",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "getCINInfoResponse",
              xmlWriter);
        }
      }
      if (local_returnTracker) {
        if (local_return == null) {
          throw new org.apache.axis2.databinding.ADBException("return cannot be null!!");
        }
        local_return.serialize(new javax.xml.namespace.QName("", "return"), xmlWriter);
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetCINInfoResponse parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetCINInfoResponse object = new GetCINInfoResponse();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"getCINInfoResponse".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (GetCINInfoResponse) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "return").equals(reader.getName())) {

            object.set_return(CompanyMasterDataResponseDTO.Factory.parse(reader));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class GetSIGInfoResponseE implements org.apache.axis2.databinding.ADBBean {

    public static final javax.xml.namespace.QName MY_QNAME =
        new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfoResponse", "ns1");

    /** field for GetSIGInfoResponse */
    protected GetSIGInfoResponse localGetSIGInfoResponse;

    /**
     * Auto generated getter method
     *
     * @return GetSIGInfoResponse
     */
    public GetSIGInfoResponse getGetSIGInfoResponse() {
      return localGetSIGInfoResponse;
    }

    /**
     * Auto generated setter method
     *
     * @param param GetSIGInfoResponse
     */
    public void setGetSIGInfoResponse(GetSIGInfoResponse param) {

      this.localGetSIGInfoResponse = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      // We can safely assume an element has only one type associated with it

      if (localGetSIGInfoResponse == null) {
        throw new org.apache.axis2.databinding.ADBException("getSIGInfoResponse cannot be null!");
      }
      localGetSIGInfoResponse.serialize(MY_QNAME, xmlWriter);
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static GetSIGInfoResponseE parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        GetSIGInfoResponseE object = new GetSIGInfoResponseE();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          while (!reader.isEndElement()) {
            if (reader.isStartElement()) {

              if (reader.isStartElement()
                  && new javax.xml.namespace.QName("http://www.mca.gov.in/", "getSIGInfoResponse")
                      .equals(reader.getName())) {

                object.setGetSIGInfoResponse(GetSIGInfoResponse.Factory.parse(reader));

              } // End of if for expected property start element
              else {
                // 3 - A start element we are not expecting indicates an invalid parameter was
                // passed

                throw new org.apache.axis2.databinding.ADBException(
                    "Unexpected subelement " + reader.getName());
              }

            } else {
              reader.next();
            }
          } // end of while loop

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  public static class FinancialData implements org.apache.axis2.databinding.ADBBean {
    /* This type was generated from the piece of schema that had
    name = financialData
    Namespace URI = http://www.mca.gov.in/
    Namespace Prefix = ns1
    */

    /** field for ProfitLoss */
    protected java.lang.String localProfitLoss;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localProfitLossTracker = false;

    public boolean isProfitLossSpecified() {
      return localProfitLossTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getProfitLoss() {
      return localProfitLoss;
    }

    /**
     * Auto generated setter method
     *
     * @param param ProfitLoss
     */
    public void setProfitLoss(java.lang.String param) {
      localProfitLossTracker = param != null;

      this.localProfitLoss = param;
    }

    /** field for TurnOver */
    protected java.lang.String localTurnOver;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localTurnOverTracker = false;

    public boolean isTurnOverSpecified() {
      return localTurnOverTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getTurnOver() {
      return localTurnOver;
    }

    /**
     * Auto generated setter method
     *
     * @param param TurnOver
     */
    public void setTurnOver(java.lang.String param) {
      localTurnOverTracker = param != null;

      this.localTurnOver = param;
    }

    /** field for Year */
    protected java.lang.String localYear;

    /*  This tracker boolean wil be used to detect whether the user called the set method
     *   for this attribute. It will be used to determine whether to include this field
     *   in the serialized XML
     */
    protected boolean localYearTracker = false;

    public boolean isYearSpecified() {
      return localYearTracker;
    }

    /**
     * Auto generated getter method
     *
     * @return java.lang.String
     */
    public java.lang.String getYear() {
      return localYear;
    }

    /**
     * Auto generated setter method
     *
     * @param param Year
     */
    public void setYear(java.lang.String param) {
      localYearTracker = param != null;

      this.localYear = param;
    }

    /**
     * @param parentQName
     * @param factory
     * @return org.apache.axiom.om.OMElement
     */
    public org.apache.axiom.om.OMElement getOMElement(
        final javax.xml.namespace.QName parentQName, final org.apache.axiom.om.OMFactory factory)
        throws org.apache.axis2.databinding.ADBException {

      return factory.createOMElement(
          new org.apache.axis2.databinding.ADBDataSource(this, parentQName));
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
      serialize(parentQName, xmlWriter, false);
    }

    public void serialize(
        final javax.xml.namespace.QName parentQName,
        javax.xml.stream.XMLStreamWriter xmlWriter,
        boolean serializeType)
        throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

      java.lang.String prefix = null;
      java.lang.String namespace = null;

      prefix = parentQName.getPrefix();
      namespace = parentQName.getNamespaceURI();
      writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

      if (serializeType) {

        java.lang.String namespacePrefix = registerPrefix(xmlWriter, "http://www.mca.gov.in/");
        if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              namespacePrefix + ":financialData",
              xmlWriter);
        } else {
          writeAttribute(
              "xsi",
              "http://www.w3.org/2001/XMLSchema-instance",
              "type",
              "financialData",
              xmlWriter);
        }
      }
      if (localProfitLossTracker) {
        namespace = "";
        writeStartElement(null, namespace, "profitLoss", xmlWriter);

        if (localProfitLoss == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("profitLoss cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localProfitLoss);
        }

        xmlWriter.writeEndElement();
      }
      if (localTurnOverTracker) {
        namespace = "";
        writeStartElement(null, namespace, "turnOver", xmlWriter);

        if (localTurnOver == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("turnOver cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localTurnOver);
        }

        xmlWriter.writeEndElement();
      }
      if (localYearTracker) {
        namespace = "";
        writeStartElement(null, namespace, "year", xmlWriter);

        if (localYear == null) {
          // write the nil attribute

          throw new org.apache.axis2.databinding.ADBException("year cannot be null!!");

        } else {

          xmlWriter.writeCharacters(localYear);
        }

        xmlWriter.writeEndElement();
      }
      xmlWriter.writeEndElement();
    }

    private static java.lang.String generatePrefix(java.lang.String namespace) {
      if (namespace.equals("http://www.mca.gov.in/")) {
        return "ns1";
      }
      return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    /** Utility method to write an element start tag. */
    private void writeStartElement(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String localPart,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
      } else {
        if (namespace.length() == 0) {
          prefix = "";
        } else if (prefix == null) {
          prefix = generatePrefix(namespace);
        }

        xmlWriter.writeStartElement(prefix, localPart, namespace);
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
    }

    /** Util method to write an attribute with the ns prefix */
    private void writeAttribute(
        java.lang.String prefix,
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
      if (writerPrefix != null) {
        xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
      } else {
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
        xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        java.lang.String attValue,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attValue);
      } else {
        xmlWriter.writeAttribute(
            registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
      }
    }

    /** Util method to write an attribute without the ns prefix */
    private void writeQNameAttribute(
        java.lang.String namespace,
        java.lang.String attName,
        javax.xml.namespace.QName qname,
        javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      java.lang.String attributeNamespace = qname.getNamespaceURI();
      java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
      if (attributePrefix == null) {
        attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
      }
      java.lang.String attributeValue;
      if (attributePrefix.trim().length() > 0) {
        attributeValue = attributePrefix + ":" + qname.getLocalPart();
      } else {
        attributeValue = qname.getLocalPart();
      }

      if (namespace.equals("")) {
        xmlWriter.writeAttribute(attName, attributeValue);
      } else {
        registerPrefix(xmlWriter, namespace);
        xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
      }
    }
    /** method to handle Qnames */
    private void writeQName(
        javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String namespaceURI = qname.getNamespaceURI();
      if (namespaceURI != null) {
        java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
        if (prefix == null) {
          prefix = generatePrefix(namespaceURI);
          xmlWriter.writeNamespace(prefix, namespaceURI);
          xmlWriter.setPrefix(prefix, namespaceURI);
        }

        if (prefix.trim().length() > 0) {
          xmlWriter.writeCharacters(
              prefix
                  + ":"
                  + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        } else {
          // i.e this is the default namespace
          xmlWriter.writeCharacters(
              org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
        }

      } else {
        xmlWriter.writeCharacters(
            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
      }
    }

    private void writeQNames(
        javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
        throws javax.xml.stream.XMLStreamException {

      if (qnames != null) {
        // we have to store this data until last moment since it is not possible to write any
        // namespace data after writing the charactor data
        java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
        java.lang.String namespaceURI = null;
        java.lang.String prefix = null;

        for (int i = 0; i < qnames.length; i++) {
          if (i > 0) {
            stringToWrite.append(" ");
          }
          namespaceURI = qnames[i].getNamespaceURI();
          if (namespaceURI != null) {
            prefix = xmlWriter.getPrefix(namespaceURI);
            if ((prefix == null) || (prefix.length() == 0)) {
              prefix = generatePrefix(namespaceURI);
              xmlWriter.writeNamespace(prefix, namespaceURI);
              xmlWriter.setPrefix(prefix, namespaceURI);
            }

            if (prefix.trim().length() > 0) {
              stringToWrite
                  .append(prefix)
                  .append(":")
                  .append(
                      org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            } else {
              stringToWrite.append(
                  org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
            }
          } else {
            stringToWrite.append(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
          }
        }
        xmlWriter.writeCharacters(stringToWrite.toString());
      }
    }

    /** Register a namespace prefix */
    private java.lang.String registerPrefix(
        javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
        throws javax.xml.stream.XMLStreamException {
      java.lang.String prefix = xmlWriter.getPrefix(namespace);
      if (prefix == null) {
        prefix = generatePrefix(namespace);
        javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
        while (true) {
          java.lang.String uri = nsContext.getNamespaceURI(prefix);
          if (uri == null || uri.length() == 0) {
            break;
          }
          prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }
        xmlWriter.writeNamespace(prefix, namespace);
        xmlWriter.setPrefix(prefix, namespace);
      }
      return prefix;
    }

    /** Factory class that keeps the parse method */
    public static class Factory {
      private static org.apache.commons.logging.Log log =
          org.apache.commons.logging.LogFactory.getLog(Factory.class);

      /**
       * static method to create the object Precondition: If this object is an element, the current
       * or next start element starts this object and any intervening reader events are ignorable If
       * this object is not an element, it is a complex type and the reader is at the event just
       * after the outer start element Postcondition: If this object is an element, the reader is
       * positioned at its end element If this object is a complex type, the reader is positioned at
       * the end element of its outer element
       */
      public static FinancialData parse(javax.xml.stream.XMLStreamReader reader)
          throws java.lang.Exception {
        FinancialData object = new FinancialData();

        int event;
        javax.xml.namespace.QName currentQName = null;
        java.lang.String nillableValue = null;
        java.lang.String prefix = "";
        java.lang.String namespaceuri = "";
        try {

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          currentQName = reader.getName();

          if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type")
              != null) {
            java.lang.String fullTypeName =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
            if (fullTypeName != null) {
              java.lang.String nsPrefix = null;
              if (fullTypeName.indexOf(":") > -1) {
                nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
              }
              nsPrefix = nsPrefix == null ? "" : nsPrefix;

              java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

              if (!"financialData".equals(type)) {
                // find namespace for the prefix
                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                return (FinancialData) ExtensionMapper.getTypeObject(nsUri, type, reader);
              }
            }
          }

          // Note all attributes that were handled. Used to differ normal attributes
          // from anyAttributes.
          java.util.Vector handledAttributes = new java.util.Vector();

          reader.next();

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "profitLoss").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "profitLoss" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setProfitLoss(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "turnOver").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "turnOver" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setTurnOver(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement()
              && new javax.xml.namespace.QName("", "year").equals(reader.getName())) {

            nillableValue =
                reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
            if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
              throw new org.apache.axis2.databinding.ADBException(
                  "The element: " + "year" + "  cannot be null");
            }

            java.lang.String content = reader.getElementText();

            object.setYear(
                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

            reader.next();

          } // End of if for expected property start element
          else {

          }

          while (!reader.isStartElement() && !reader.isEndElement()) reader.next();

          if (reader.isStartElement())
            // 2 - A start element we are not expecting indicates a trailing invalid property

            throw new org.apache.axis2.databinding.ADBException(
                "Unexpected subelement " + reader.getName());

        } catch (javax.xml.stream.XMLStreamException e) {
          throw new java.lang.Exception(e);
        }

        return object;
      }
    } // end of factory class
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetDirectorInfoE param,
      boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetDirectorInfoE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetDirectorInfoResponseE param,
      boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetDirectorInfoResponseE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetCINInfoE param, boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetCINInfoE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetCINInfoResponseE param,
      boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetCINInfoResponseE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetDINInfoE param, boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetDINInfoE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetDINInfoResponseE param,
      boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetDINInfoResponseE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetSIGInfoE param, boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetSIGInfoE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.om.OMElement toOM(
      com.backend.cin.GstServiceStub.GetSIGInfoResponseE param,
      boolean optimizeContent)
      throws org.apache.axis2.AxisFault {

    try {
      return param.getOMElement(
          com.backend.cin.GstServiceStub.GetSIGInfoResponseE.MY_QNAME,
          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
      org.apache.axiom.soap.SOAPFactory factory,
      com.backend.cin.GstServiceStub.GetDirectorInfoE param,
      boolean optimizeContent,
      javax.xml.namespace.QName elementQName)
      throws org.apache.axis2.AxisFault {

    try {

      org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
      emptyEnvelope
          .getBody()
          .addChild(
              param.getOMElement(
                  com.backend.cin.GstServiceStub.GetDirectorInfoE.MY_QNAME,
                  factory));
      return emptyEnvelope;
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  /* methods to provide back word compatibility */

  private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
      org.apache.axiom.soap.SOAPFactory factory,
      com.backend.cin.GstServiceStub.GetCINInfoE param,
      boolean optimizeContent,
      javax.xml.namespace.QName elementQName)
      throws org.apache.axis2.AxisFault {

    try {

      org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
      emptyEnvelope
          .getBody()
          .addChild(
              param.getOMElement(
                  com.backend.cin.GstServiceStub.GetCINInfoE.MY_QNAME, factory));
      return emptyEnvelope;
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  /* methods to provide back word compatibility */

  private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
      org.apache.axiom.soap.SOAPFactory factory,
      com.backend.cin.GstServiceStub.GetDINInfoE param,
      boolean optimizeContent,
      javax.xml.namespace.QName elementQName)
      throws org.apache.axis2.AxisFault {

    try {

      org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
      emptyEnvelope
          .getBody()
          .addChild(
              param.getOMElement(
                  com.backend.cin.GstServiceStub.GetDINInfoE.MY_QNAME, factory));
      return emptyEnvelope;
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  /* methods to provide back word compatibility */

  private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
      org.apache.axiom.soap.SOAPFactory factory,
      com.backend.cin.GstServiceStub.GetSIGInfoE param,
      boolean optimizeContent,
      javax.xml.namespace.QName elementQName)
      throws org.apache.axis2.AxisFault {

    try {

      org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
      emptyEnvelope
          .getBody()
          .addChild(
              param.getOMElement(
                  com.backend.cin.GstServiceStub.GetSIGInfoE.MY_QNAME, factory));
      return emptyEnvelope;
    } catch (org.apache.axis2.databinding.ADBException e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
  }

  /* methods to provide back word compatibility */

  /** get the default envelope */
  private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
    return factory.getDefaultEnvelope();
  }

  private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type)
      throws org.apache.axis2.AxisFault {

    try {

      if (com.backend.cin.GstServiceStub.GetCINInfoE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetCINInfoE.Factory.parse(reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetCINInfoResponseE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetCINInfoResponseE.Factory.parse(
                reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetDINInfoE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetDINInfoE.Factory.parse(reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetDINInfoResponseE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetDINInfoResponseE.Factory.parse(
                reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetDirectorInfoE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetDirectorInfoE.Factory.parse(reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetDirectorInfoResponseE.class.equals(
          type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetDirectorInfoResponseE.Factory.parse(
                reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetSIGInfoE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetSIGInfoE.Factory.parse(reader);
        reader.close();
        return result;
      }

      if (com.backend.cin.GstServiceStub.GetSIGInfoResponseE.class.equals(type)) {

        javax.xml.stream.XMLStreamReader reader = param.getXMLStreamReaderWithoutCaching();
        java.lang.Object result =
            com.backend.cin.GstServiceStub.GetSIGInfoResponseE.Factory.parse(
                reader);
        reader.close();
        return result;
      }

    } catch (java.lang.Exception e) {
      throw org.apache.axis2.AxisFault.makeFault(e);
    }
    return null;
  }
}
