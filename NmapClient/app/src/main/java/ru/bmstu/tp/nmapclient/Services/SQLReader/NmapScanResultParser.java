package ru.bmstu.tp.nmapclient.Services.SQLReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class NmapScanResultParser {

    private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    public NmapScanResult parse(String xmlDocumentContent)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new InputSource(new StringReader(xmlDocumentContent)));
        doc.getDocumentElement().normalize();

        NmapScanResult nmapScanResult = new NmapScanResult();

        NodeList nodeList = doc.getElementsByTagName("nmaprun");
        Node nMapRun = nodeList.item(0);
        if (nMapRun.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) nMapRun;
            nmapScanResult.setArgs(element.getAttribute("args"));
            int start = Integer.parseInt(element.getAttribute("start"));
            nmapScanResult.setStart(new Date((long)start * 1000));
        }

        List<NmapScanResult.Host> hosts = new ArrayList<>();
        nodeList = doc.getElementsByTagName("host");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node host = nodeList.item(i);
            if (host.getNodeType() == Node.ELEMENT_NODE) {
                hosts.add(parseHost(doc, (Element) host));
            }
        }
        nmapScanResult.setHosts(hosts);

        return nmapScanResult;
    }

    private NmapScanResult.Host parseHost(Document doc, Element element) {
        NmapScanResult.Host host = new NmapScanResult.Host();
        String startTime = element.getAttribute("starttime");
        if (startTime != null && !startTime.isEmpty()) {
            host.setStarttime(new Date((long) Integer.parseInt(startTime) * 1000));
        }

        NodeList nodeList = doc.getElementsByTagName("address");
        Node address = nodeList.item(0);
        if (address.getNodeType() == Node.ELEMENT_NODE) {
            host.setAddress(parseAddress((Element) address));
        }

        List<NmapScanResult.HostName> hostNames = new ArrayList<>();
        nodeList = doc.getElementsByTagName("hostname");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node hostName = nodeList.item(i);
            if (hostName.getNodeType() == Node.ELEMENT_NODE) {
                hostNames.add(parseHostName((Element) hostName));
            }
        }
        host.setHostNames(hostNames);

        List<NmapScanResult.Port> ports = new ArrayList<>();
        nodeList = doc.getElementsByTagName("port");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node port = nodeList.item(i);
            if (port.getNodeType() == Node.ELEMENT_NODE) {
                ports.add(parsePort((Element) port));
            }
        }
        host.setPorts(ports);

        nodeList = doc.getElementsByTagName("os");
        Node os = nodeList.item(0);
        host.setOs(nodeToString(os));

        nodeList = doc.getElementsByTagName("trace");
        Node trace = nodeList.item(0);
        host.setTrace(nodeToString(trace));

        return host;
    }

    private NmapScanResult.Address parseAddress(Element element) {
        NmapScanResult.Address address = new NmapScanResult.Address();
        address.setAddr(element.getAttribute("addr"));
        address.setAddrtype(element.getAttribute("addrtype"));
        return address;
    }

    private NmapScanResult.HostName parseHostName(Element element) {
        NmapScanResult.HostName hostName = new NmapScanResult.HostName();
        hostName.setName(element.getAttribute("name"));
        hostName.setType(element.getAttribute("type"));
        return hostName;
    }

    private NmapScanResult.Port parsePort(Element element) {
        NmapScanResult.Port port = new NmapScanResult.Port();
        port.setProtocol(element.getAttribute("protocol"));
        port.setPortId(element.getAttribute("portid"));
        port.setContent(nodeToString(element));
        return port;
    }

    private String nodeToString(Node node) {
        DOMImplementationLS lsImpl = (DOMImplementationLS)node.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
        LSSerializer serializer = lsImpl.createLSSerializer();
        serializer.getDomConfig().setParameter("xml-declaration", false); //by default its true, so set it to false to get String without xml-declaration
        return serializer.writeToString(node);
    }

}
