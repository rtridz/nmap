package ru.bmstu.tp.nmapclient.Validator;

/**
 * Created by aleksei on 19.05.15.
 */
public class Query {
    String type;
    String domain;
    String ipFrom;
    String ipTo;
    String portFrom;
    String portTo;

    public Query(String _type, String _domain, String _ipFrom, String _ipTo, String _portFrom, String _portTo) {
        type = _type;
        domain = _domain;
        ipFrom = _ipFrom;
        ipTo = _ipTo;
        portFrom = _portFrom;
        portTo = _portTo;
    }

    public Query() {

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(String ipFrom) {
        this.ipFrom = ipFrom;
    }

    public String getIpTo() {
        return ipTo;
    }

    public void setIpTo(String ipTo) {
        this.ipTo = ipTo;
    }

    public String getPortFrom() {
        return portFrom;
    }

    public void setPortFrom(String portFrom) {
        this.portFrom = portFrom;
    }

    public String getPortTo() {
        return portTo;
    }

    public void setPortTo(String portTo) {
        this.portTo = portTo;
    }

}
