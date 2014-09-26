
package model.BankOfEstonia.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BodyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FixingsDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Currencies" type="{http://producers.eestipank.info/types}CurrenciesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodyType", namespace = "http://producers.eestipank.info/types", propOrder = {
    "fixingsDate",
    "currencies"
})
public class BodyType {

    @XmlElement(name = "FixingsDate", namespace = "http://producers.eestipank.info/types", required = true)
    protected String fixingsDate;
    @XmlElement(name = "Currencies", namespace = "http://producers.eestipank.info/types", required = true)
    protected CurrenciesType currencies;

    /**
     * Gets the value of the fixingsDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixingsDate() {
        return fixingsDate;
    }

    /**
     * Sets the value of the fixingsDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixingsDate(String value) {
        this.fixingsDate = value;
    }

    /**
     * Gets the value of the currencies property.
     * 
     * @return
     *     possible object is
     *     {@link CurrenciesType }
     *     
     */
    public CurrenciesType getCurrencies() {
        return currencies;
    }

    /**
     * Sets the value of the currencies property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrenciesType }
     *     
     */
    public void setCurrencies(CurrenciesType value) {
        this.currencies = value;
    }

}
