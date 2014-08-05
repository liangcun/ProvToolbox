//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.05 at 10:17:37 PM BST 
//


package org.openprovenance.prov.dot;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.openprovenance.prov.xml.builder.Equals;
import org.openprovenance.prov.xml.builder.HashCode;
import org.openprovenance.prov.xml.builder.ToString;
import org.openprovenance.prov.xml.builder.JAXBEqualsBuilder;
import org.openprovenance.prov.xml.builder.JAXBHashCodeBuilder;
import org.openprovenance.prov.xml.builder.JAXBToStringBuilder;


/**
 * <p>Java class for AccountMap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="account" type="{http://openprovenance.org/model/opmPrinterConfig}AccountColorMapEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="defaultAccount" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="defaultColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountMap", namespace = "http://openprovenance.org/model/opmPrinterConfig", propOrder = {
    "account"
})
public class AccountMap
    implements Equals, HashCode, ToString
{

    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected List<AccountColorMapEntry> account;
    @XmlAttribute(name = "defaultAccount")
    protected String defaultAccount;
    @XmlAttribute(name = "defaultColor")
    protected String defaultColor;

    /**
     * Gets the value of the account property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the account property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountColorMapEntry }
     * 
     * 
     */
    public List<AccountColorMapEntry> getAccount() {
        if (account == null) {
            account = new ArrayList<AccountColorMapEntry>();
        }
        return this.account;
    }

    /**
     * Gets the value of the defaultAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultAccount() {
        return defaultAccount;
    }

    /**
     * Sets the value of the defaultAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultAccount(String value) {
        this.defaultAccount = value;
    }

    /**
     * Gets the value of the defaultColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultColor() {
        return defaultColor;
    }

    /**
     * Sets the value of the defaultColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultColor(String value) {
        this.defaultColor = value;
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof AccountMap)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        final AccountMap that = ((AccountMap) object);
        equalsBuilder.append(this.getAccount(), that.getAccount());
        equalsBuilder.append(this.getDefaultAccount(), that.getDefaultAccount());
        equalsBuilder.append(this.getDefaultColor(), that.getDefaultColor());
    }

    public boolean equals(Object object) {
        if (!(object instanceof AccountMap)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EqualsBuilder equalsBuilder = new JAXBEqualsBuilder();
        equals(object, equalsBuilder);
        return equalsBuilder.isEquals();
    }

    public void hashCode(HashCodeBuilder hashCodeBuilder) {
        hashCodeBuilder.append(this.getAccount());
        hashCodeBuilder.append(this.getDefaultAccount());
        hashCodeBuilder.append(this.getDefaultColor());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public void toString(ToStringBuilder toStringBuilder) {
        {
            List<AccountColorMapEntry> theAccount;
            theAccount = this.getAccount();
            toStringBuilder.append("account", theAccount);
        }
        {
            String theDefaultAccount;
            theDefaultAccount = this.getDefaultAccount();
            toStringBuilder.append("defaultAccount", theDefaultAccount);
        }
        {
            String theDefaultColor;
            theDefaultColor = this.getDefaultColor();
            toStringBuilder.append("defaultColor", theDefaultColor);
        }
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

}
