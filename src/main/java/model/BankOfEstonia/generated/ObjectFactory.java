
package model.BankOfEstonia.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the model.BankOfEstonia.generated package. 
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

    private final static QName _Report_QNAME = new QName("http://producers.eestipank.info/types", "Report");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: model.BankOfEstonia.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReportType }
     * 
     */
    public ReportType createReportType() {
        return new ReportType();
    }

    /**
     * Create an instance of {@link BodyType }
     * 
     */
    public BodyType createBodyType() {
        return new BodyType();
    }

    /**
     * Create an instance of {@link CurrencyType }
     * 
     */
    public CurrencyType createCurrencyType() {
        return new CurrencyType();
    }

    /**
     * Create an instance of {@link HeaderType }
     * 
     */
    public HeaderType createHeaderType() {
        return new HeaderType();
    }

    /**
     * Create an instance of {@link CurrenciesType }
     * 
     */
    public CurrenciesType createCurrenciesType() {
        return new CurrenciesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://producers.eestipank.info/types", name = "Report")
    public JAXBElement<ReportType> createReport(ReportType value) {
        return new JAXBElement<ReportType>(_Report_QNAME, ReportType.class, null, value);
    }

}
