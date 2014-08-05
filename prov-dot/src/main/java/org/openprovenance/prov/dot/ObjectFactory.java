//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.05 at 10:17:37 PM BST 
//


package org.openprovenance.prov.dot;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openprovenance.prov.dot package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Configuration_QNAME = new QName("http://openprovenance.org/model/opmPrinterConfig", "configuration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openprovenance.prov.dot
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProvPrinterConfiguration }
     * 
     */
    public ProvPrinterConfiguration createProvPrinterConfiguration() {
        return new ProvPrinterConfiguration();
    }

    /**
     * Create an instance of {@link EntityMap }
     * 
     */
    public EntityMap createEntityMap() {
        return new EntityMap();
    }

    /**
     * Create an instance of {@link RelationStyleMap }
     * 
     */
    public RelationStyleMap createRelationStyleMap() {
        return new RelationStyleMap();
    }

    /**
     * Create an instance of {@link Map }
     * 
     */
    public Map createMap() {
        return new Map();
    }

    /**
     * Create an instance of {@link AgentMap }
     * 
     */
    public AgentMap createAgentMap() {
        return new AgentMap();
    }

    /**
     * Create an instance of {@link MapEntry }
     * 
     */
    public MapEntry createMapEntry() {
        return new MapEntry();
    }

    /**
     * Create an instance of {@link RelationStyleMapEntry }
     * 
     */
    public RelationStyleMapEntry createRelationStyleMapEntry() {
        return new RelationStyleMapEntry();
    }

    /**
     * Create an instance of {@link AgentMapEntry }
     * 
     */
    public AgentMapEntry createAgentMapEntry() {
        return new AgentMapEntry();
    }

    /**
     * Create an instance of {@link ActivityMapEntry }
     * 
     */
    public ActivityMapEntry createActivityMapEntry() {
        return new ActivityMapEntry();
    }

    /**
     * Create an instance of {@link AccountMap }
     * 
     */
    public AccountMap createAccountMap() {
        return new AccountMap();
    }

    /**
     * Create an instance of {@link AccountColorMapEntry }
     * 
     */
    public AccountColorMapEntry createAccountColorMapEntry() {
        return new AccountColorMapEntry();
    }

    /**
     * Create an instance of {@link ActivityMap }
     * 
     */
    public ActivityMap createActivityMap() {
        return new ActivityMap();
    }

    /**
     * Create an instance of {@link EntityMapEntry }
     * 
     */
    public EntityMapEntry createEntityMapEntry() {
        return new EntityMapEntry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvPrinterConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://openprovenance.org/model/opmPrinterConfig", name = "configuration")
    public JAXBElement<ProvPrinterConfiguration> createConfiguration(ProvPrinterConfiguration value) {
        return new JAXBElement<ProvPrinterConfiguration>(_Configuration_QNAME, ProvPrinterConfiguration.class, null, value);
    }

}