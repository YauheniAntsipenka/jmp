package core.util;

import core.model.Ticket;
import core.model.impl.TicketImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * XmlReaderUtil
 * Date: 03/06/2023
 *
 * @author Yauheni Antsipenka
 */
public final class XmlReaderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlReaderUtil.class);
    private static final String PATH = "init/tickets.xml";

    private XmlReaderUtil() {
    }

    public static List<Ticket> getTickets() throws URISyntaxException, ParserConfigurationException, IOException, SAXException {
        NodeList ticketNodes = readXml().getElementsByTagName("ticket");
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketNodes.getLength(); i++)
        {
            Node ticketNode = ticketNodes.item(i);
            if (ticketNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element studentElement = (Element) ticketNode;
                String userId = studentElement.getAttribute("user");
                String eventId = studentElement.getAttribute("event");
                String category = studentElement.getAttribute("category");
                String place = studentElement.getAttribute("place");
                Ticket ticket = new TicketImpl(Long.parseLong(eventId), Long.parseLong(userId),
                    Ticket.Category.valueOf(category), Integer.parseInt(place));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    private static Document readXml() throws URISyntaxException, ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(Objects.requireNonNull(XmlReaderUtil.class.getClassLoader().getResource(PATH)).toURI());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlFile);
    }
}
