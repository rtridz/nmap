package ru.bmstu.tp.nmapclient.Validator;

public class Validator {

    IpUtils ipUtils;
    DomainUtils domainUtils;

    int PortFrom = 0;
    int PortTo = 65535;

    String domain_error = "invalid domain\n";
    String ip1_error = "invalid ip from\n";
    String ip2_error = "invalid ip to\n";
    String port1_error = "invalid port from\n";
    String port2_error = "invalid port to\n";
    String ok = "ok";

    boolean validIp(String ip) {
        return IpUtils.validIP(ip);
    }

    boolean validDomain(String domain) {
        return DomainUtils.isValidDomainName(domain);
    }

    boolean validPort(String port) {
        if (port.length() == 0)
            return false;
        int i_port = Integer.parseInt(port);
        return i_port > PortFrom && i_port <= PortTo;
    }

    public String validQuery(Query query) {
        String answer = "";
        if (query.getType().equals("domain")) {
            if (!validDomain(query.getDomain()))
                answer += domain_error;
        } else if (query.getType().equals("ip")) {
            if (!validIp(query.getIpFrom()))
                answer += ip1_error;
            if (!validIp(query.getIpTo()))
                answer += ip2_error;
        }
        if (!validPort(query.getPortFrom()))
            answer += port1_error;
        if (!validPort(query.getPortTo()))
            answer += port2_error;
        if (answer.equals(""))
            answer += ok;
        return answer;
    }
}