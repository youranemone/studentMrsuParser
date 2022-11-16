package parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class XMLParser implements Parser {

    @Override
    public void parse(File file) {
        List list = new ArrayList(readAddress(file));
    }

    private List<? extends Object> readAddress(File file) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = null;
        List result = new ArrayList();
        List repeater = new ArrayList();
        if(file.toString() == "address.xml"){
            try {
                parser = factory.createXMLStreamReader(new FileInputStream(file));
                int second = 0, third = 0;
                while(parser.hasNext()){
                    parser.next();
                    if(parser.isStartElement()){
                        if(parser.getLocalName().equals("address")){
                            Address address = new Address(
                                    parseInt( parser.getAttributeValue(0)),
                                    parser.getAttributeValue(1),
                                    parser.getAttributeValue(2),
                                    parseInt(parser.getAttributeValue(3)),
                                    parseInt(parser.getAttributeValue(4)),
                                    parseInt(parser.getAttributeValue(5)));
                            if(result.contains(address)){
                                repeater.add(address);
                            } else {
                                if(address.floor == 3)
                                    third++;
                                if(address.floor == 2)
                                    second++;
                                result.add(address);
                            }
                        }
                    }
                }
                for (int i = 0; i < result.size();i++){
                    System.out.println(result.get(i));
                }
                System.out.println("Repeat elemnts:");
                for (int i = 0; i < repeater.size();i++){
                    System.out.println(result.get(i));
                }
                System.out.println("On second floor: " + second);
                System.out.println("On third floor: " + third);
            } catch (FileNotFoundException e) {
                System.out.println("Check file path");
            } catch (XMLStreamException e) {
                System.out.println(e.getMessage());
            }
        }else if (file.toString() == "client.xml"){
            try {
                parser = factory.createXMLStreamReader(new FileInputStream(file));
                while(parser.hasNext()){
                    parser.next();
                    if(parser.isStartElement()){
                        if(parser.getLocalName().equals("client")){
                            Client client = new Client(
                                    parseInt( parser.getAttributeValue(0)),
                                    parser.getAttributeValue(1),
                                    parseInt( parser.getAttributeValue(2)),
                                    parser.getAttributeValue(3));
                            if(result.contains(client)){
                                repeater.add(client);
                            } else {
                                result.add(client);
                            }
                        }
                    }
                }
                for (int i = 0; i < result.size();i++){
                    System.out.println(result.get(i));
                }
                System.out.println("Repeat elemnts:");
                for (int i = 0; i < repeater.size();i++){
                    System.out.println(result.get(i));
                }
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
