
package model.BankOfLithuania.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.BankOfLithuania.generated package. 
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

    private final static QName _ExchangeRates_QNAME = new QName("", "ExchangeRates");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.BankOfLithuania.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExchangeRatesType }
     * 
     */
    public ExchangeRatesType createExchangeRatesType() {
        return new ExchangeRatesType();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangeRatesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ExchangeRates")
    public JAXBElement<ExchangeRatesType> createExchangeRates(ExchangeRatesType value) {
        return new JAXBElement<ExchangeRatesType>(_ExchangeRates_QNAME, ExchangeRatesType.class, null, value);
    }

}
